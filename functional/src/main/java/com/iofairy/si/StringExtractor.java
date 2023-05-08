/*
 * Copyright (C) 2021 iofairy, <https://github.com/io-fairy/functional>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.iofairy.si;

import com.iofairy.top.S;
import com.iofairy.tuple.Tuple2;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.iofairy.si.SIBase.*;

/**
 * Extracting expression from ${}
 *
 * @since 0.0.1
 */
public class StringExtractor {

    /**
     * Regex for extracting expression from ${}, but { or } can't be included in ${}
     */
    private final static Pattern PATTERN = Pattern.compile("\\$\\{((?![{}]).)*}", Pattern.MULTILINE);

    /**
     * Get String Tokens by ${}
     *
     * @param source 待插值的字符串
     * @return StringToken列表
     */
    public static List<StringToken> split(String source) {
        List<StringToken> sts = new ArrayList<>();
        final Matcher matcher = PATTERN.matcher(source);

        int startIndex = 0;
        while (matcher.find()) {
            String matchStr = matcher.group();
            int start = matcher.start();    // 起始字符位置
            int end = matcher.end();        // 结束字符+1 的位置

            if ($__.equals(matchStr)) {
                String strBefore$ = source.substring(startIndex, start);  // ${} 前面还未加入列表的字符串，如： abc${}，则 strBefore$ == "abc"
                sts.add(new StringToken(StringType.STRING, strBefore$ + $, strBefore$ + $__));
            } else {
                if (start != startIndex) {  // 不相等说明${}前面有一段字符串常量还未添加进列表
                    String value = source.substring(startIndex, start);
                    sts.add(new StringToken(StringType.STRING, value, value));
                }

                String strInBrace = matchStr.substring(2, matchStr.length() - 1);   // 获取${}中的内容
                Tuple2<String, String> keyDefault = S.splitOnce(strInBrace, DEFAULT_VALUE_DELIMITER);
                sts.add(new StringToken(StringType.VARIABLE, keyDefault._1, keyDefault._2 == null ? matchStr : keyDefault._2));
            }
            startIndex = end;
        }

        if (startIndex < source.length()) {
            String value = source.substring(startIndex);
            sts.add(new StringToken(StringType.STRING, value, value));
        }

        return sts;
    }

    /**
     * 嵌套字符串插值分词器
     *
     * @param source 原字符串
     * @return 分词后的列表
     * @since 0.4.0
     */
    public static List<Object> nestedParse(String source) {
        LinkedList<Object> tokens = new LinkedList<>();
        nestedParse(source, tokens);
        return tokens;
    }

    /**
     * 嵌套字符串插值分词器
     *
     * @param source 原字符串
     * @param tokens 分词后的列表
     * @return 分词后的列表
     * @since 0.4.0
     */
    private static int nestedParse(String source, LinkedList<Object> tokens) {
        NestedStringToken currentNst = null;

        int pos = 0;
        int length = source.length();
        while (pos < length) {
            int matchLen = match(source, pos, $__);

            if (matchLen == 0) {                // 当前不是 ${} 字符串
                matchLen = match(source, pos, PREFIX);
                if (matchLen == 0) {            // 当前不是 ${ 字符串
                    if (currentNst == null) {
                        addToken(tokens, source.charAt(pos) + "");
                        pos++;
                    } else {
                        matchLen = match(source, pos, SUFFIX);
                        if (matchLen == 0) {    // 当前不是 } 字符串
                            currentNst.addToken(source.charAt(pos) + "");
                            pos++;
                        } else {                // 匹配到 } 字符串
                            currentNst.isClosed = true;
                            currentNst.endIndex = pos;
                            currentNst.mergeAndSplitTokens();

                            currentNst = currentNst.parentNode;
                            pos += matchLen;
                        }
                    }
                } else {        // 匹配到 ${ 字符串
                    NestedStringToken nst = new NestedStringToken(pos);
                    pos += matchLen;
                    if (currentNst == null) {
                        tokens.add(nst);
                    } else {
                        currentNst.addToken(nst);
                    }
                    currentNst = nst;
                }

            } else {            // 匹配到 ${} 字符串
                pos += matchLen;
                if (currentNst == null) {
                    addToken(tokens, $);
                } else {
                    currentNst.addToken($);
                }
            }
        }

        // 检查最后一个 NestedStringToken 是否未封闭
        while (currentNst != null) {
            currentNst.tokens.addFirst(PREFIX);
            NestedStringToken parentNode = currentNst.parentNode;
            if (parentNode == null) {
                tokens.remove(currentNst);
                addTokens(tokens, currentNst.tokens);
                currentNst = parentNode;
                break;
            } else {
                parentNode.removeToken(currentNst);
                parentNode.addTokens(currentNst.tokens);
            }
            currentNst = parentNode;
        }

        mergeTokens(tokens);

        return pos;
    }

    /**
     * 合并Token<br>
     * 注：会改变传入的List
     *
     * @param tokens tokens
     */
    private static void mergeTokens(LinkedList<Object> tokens) {
        LinkedList<Object> newTokens = new LinkedList<>();
        Iterator<Object> iter = tokens.iterator();
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (iter.hasNext()) {
            Object next = iter.next();
            if (next instanceof String) {
                sb.append(next);
            } else {
                if (count != 0) newTokens.add(sb.toString());

                newTokens.add(next);
                sb = new StringBuilder();
            }
            count++;
        }

        if (sb.length() > 0) newTokens.add(sb.toString());

        tokens.clear();
        tokens.addAll(newTokens);
    }

    private static void addTokens(LinkedList<Object> tokens, LinkedList<Object> subTokens) {
        for (Object subToken : subTokens) {
            if (subToken instanceof NestedStringToken) {
                ((NestedStringToken) subToken).parentNode = null;
            }
            tokens.add(subToken);
        }
    }

    private static void addToken(LinkedList<Object> tokens, String token) {
        tokens.add(token);
    }

    private static int match(String source, int start, String subStr) {
        int length = subStr.length();
        if (start + length > source.length()) {
            return 0;
        }
        for (int i = 0; i < length; i++) {
            if (source.charAt(start + i) != subStr.charAt(i)) {
                return 0;
            }
        }
        return length;
    }


}
