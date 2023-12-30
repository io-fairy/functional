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
package com.iofairy.string;

import com.iofairy.top.G;
import com.iofairy.top.S;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Identifier
 *
 * @since 0.4.15
 */
public class Identifier {
    /**
     * 备用的合法标识符前缀
     */
    private static final String ALTERNATE_PREFIX = "ext_";
    /**
     * 合法标识符模式
     */
    public static final Pattern ID = Pattern.compile("[a-zA-Z_][a-zA-Z0-9_]*");
    /**
     * 带 {@code $} 的合法标识符模式
     */
    public static final Pattern ID$ = Pattern.compile("[a-zA-Z_$][a-zA-Z0-9_$]*");
    /**
     * 带中文的合法标识符模式
     */
    public static final Pattern ID_ZH = Pattern.compile("[a-zA-Z_\\u4e00-\\u9fa5][a-zA-Z0-9_\\u4e00-\\u9fa5]*");
    /**
     * 带中文和 {@code $} 的合法标识符模式
     */
    public static final Pattern ID_ZH$ = Pattern.compile("[a-zA-Z_$\\u4e00-\\u9fa5][a-zA-Z0-9_$\\u4e00-\\u9fa5]*");


    /**
     * 判断是否是标识符<br><br>
     * 是否支持 <b>{@code $}</b> 符号作为合法标识符：
     * <ul>
     *     <li> {@code 支持 ：Java、Javascript、C/C++、Oracle、达梦、Mysql、Greenplum}
     *     <li> {@code 不支持 ：Python}
     * </ul>
     *
     * @param inputStr           输入的字符串
     * @param containChineseChar 中文字符是否是合法标识符
     * @param containDollarSign  <b>{@code $}</b> 符号是否是合法的标识符
     * @param keywords           关键字列表
     * @return 是否是合法标识符
     */
    public static boolean isValidIdentifier(String inputStr, boolean containChineseChar, boolean containDollarSign, List<String> keywords) {
        if (S.isBlank(inputStr)) return false;
        if (!G.isEmpty(keywords) && keywords.contains(inputStr)) return false;

        Pattern pattern = containChineseChar ? (containDollarSign ? ID_ZH$ : ID_ZH) : (containDollarSign ? ID$ : ID);
        return pattern.matcher(inputStr).matches();
    }

    /**
     * 判断是否是标识符<br>
     *
     * @param inputStr           输入的字符串
     * @param containChineseChar 中文字符是否是合法标识符
     * @param keywords           关键字列表
     * @return 是否是合法标识符
     * @see #isValidIdentifier(String, boolean, boolean, List)
     */
    public static boolean isValidIdentifier(String inputStr, boolean containChineseChar, List<String> keywords) {
        return isValidIdentifier(inputStr, containChineseChar, true, keywords);
    }

    /**
     * 判断是否是标识符<br>
     *
     * @param inputStr           输入的字符串
     * @param containChineseChar 中文字符是否是合法标识符
     * @param containDollarSign  <b>{@code $}</b> 符号是否是合法的标识符
     * @return 是否是合法标识符
     * @see #isValidIdentifier(String, boolean, boolean, List)
     */
    public static boolean isValidIdentifier(String inputStr, boolean containChineseChar, boolean containDollarSign) {
        return isValidIdentifier(inputStr, containChineseChar, containDollarSign, null);
    }

    /**
     * 判断是否是标识符<br>
     *
     * @param inputStr           输入的字符串
     * @param containChineseChar 中文字符是否是合法标识符
     * @return 是否是合法标识符
     * @see #isValidIdentifier(String, boolean, boolean, List)
     */
    public static boolean isValidIdentifier(String inputStr, boolean containChineseChar) {
        return isValidIdentifier(inputStr, containChineseChar, true, null);
    }

    /**
     * 判断是否是标识符<br>
     *
     * @param inputStr 输入的字符串
     * @return 是否是合法标识符
     * @see #isValidIdentifier(String, boolean, boolean, List)
     */
    public static boolean isValidIdentifier(String inputStr) {
        return isValidIdentifier(inputStr, false, true, null);
    }

    /**
     * 将字符串转换为合法标识符
     *
     * @param input 输入的字符串
     * @return 合法标识符
     */
    public static String toIdentifier(CharSequence input) {
        return toIdentifier(input, null);
    }


    /**
     * 将字符串转换为合法标识符
     *
     * @param input  输入的字符串
     * @param prefix 不是合法标识符，补充前缀
     * @return 合法标识符
     */
    public static String toIdentifier(CharSequence input, String prefix) {
        String finalPrefix = ALTERNATE_PREFIX;

        if (!S.isBlank(prefix)) {
            String afterProcess = prefixIdentifier(prefix);
            if (!(afterProcess.isEmpty() || Character.isDigit(afterProcess.charAt(0)))) finalPrefix = afterProcess;
        }

        if (input == null) return finalPrefix + "null";

        /*
         * ➊ 将横线和空格替换为下划线
         * ➋ 去除字符串中的非字母、数字和下划线
         * ➌ 判断首字符是否符合要求，不符合要求，则添加前缀
         */
        String replaced = input.toString().trim().replaceAll("[-\\s]", "_");              // 将横线和空格替换为下划线
        String processed = replaced.replaceAll("[^a-zA-Z0-9_]", "").replaceAll("_+", "_");    // 去除字符串中的非字母、数字和下划线

        if (processed.isEmpty() || Character.isDigit(processed.charAt(0))) {
            return finalPrefix + processed;
        }

        return processed;
    }

    private static String prefixIdentifier(String prefix) {
        String replaced = prefix.trim().replaceAll("[-\\s]", "_");                 // 将横线和空格替换为下划线
        return replaced.replaceAll("[^a-zA-Z0-9_]", "").replaceAll("_+", "_");     // 去除字符串中的非字母、数字和下划线
    }

    /**
     * 将字符串列表转成合法的数据库列名
     *
     * @param cs 字符串列表
     * @return 数据库列名
     */
    public static List<String> toColumns(Collection<? extends CharSequence> cs) {
        return toColumns(null, cs);
    }

    /**
     * 将字符串列表转成合法的数据库列名
     *
     * @param prefix 不是合法标识符，补充前缀
     * @param cs     字符串列表
     * @return 数据库列名
     */
    public static List<String> toColumns(final String prefix, CharSequence... cs) {
        if (G.isEmpty(cs)) return new ArrayList<>();
        return toColumns(prefix, Arrays.asList(cs));
    }

    /**
     * 将字符串列表转成合法的数据库列名
     *
     * @param prefix 不是合法标识符，补充前缀
     * @param cs     字符串列表
     * @return 数据库列名
     */
    public static List<String> toColumns(final String prefix, Collection<? extends CharSequence> cs) {
        List<String> processedColumns = new ArrayList<>();
        if (G.isEmpty(cs)) return processedColumns;

        for (CharSequence s : cs) {
            processedColumns.add(toIdentifier(s, prefix));
        }
        processIdentifierList(processedColumns);
        return processedColumns;
    }

    private static void processIdentifierList(List<String> list) {
        Map<String, Integer> countMap = new HashMap<>();  // 使用 HashMap 记录每个字符串的出现次数
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            // 如果字符串已经出现过，则在字符串后面加上该元素已经出现过的次数
            if (countMap.containsKey(str)) {
                int count = countMap.get(str);
                list.set(i, str + "_" + count);
            }
            // 更新字符串的出现次数
            countMap.put(str, countMap.getOrDefault(str, 0) + 1);
        }
    }


}
