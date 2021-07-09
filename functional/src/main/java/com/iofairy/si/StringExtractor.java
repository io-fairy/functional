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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Extracting expression from ${}
 *
 * @since 0.0.1
 */
public class StringExtractor {

    /**
     * ${} will parse to $ (String literal). <br>
     * 遇到 ${} 则解析成字符 $
     */
    private final static String $ = "${}";
    private final static String DEFAULT_VALUE_DELIMITER = ": ";

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

            if ($.equals(matchStr)) {
                String value = source.substring(startIndex, start + 1);
                sts.add(new StringToken(StringType.STRING, value, value));
            } else {
                if (start != startIndex) {  // 不相等说明${}前面有一段字符串常量还未添加进列表
                    String value = source.substring(startIndex, start);
                    sts.add(new StringToken(StringType.STRING, value, value));
                }

                String strInBrace = matchStr.substring(2, matchStr.length() - 1);
                int index = strInBrace.indexOf(DEFAULT_VALUE_DELIMITER);
                if (index > -1) {
                    String defaultValue = "";
                    if (index + DEFAULT_VALUE_DELIMITER.length() < strInBrace.length()) {
                        defaultValue = strInBrace.substring(index + DEFAULT_VALUE_DELIMITER.length());

                        String value = strInBrace.substring(0, index);
                        sts.add(new StringToken(StringType.VALUE, value, defaultValue));
                    }
                }else {
                    sts.add(new StringToken(StringType.VALUE, strInBrace, matchStr));
                }
            }
            startIndex = end;
        }

        if (startIndex < source.length()) {
            String value = source.substring(startIndex);
            sts.add(new StringToken(StringType.STRING, value, value));
        }

        return sts;
    }
}
