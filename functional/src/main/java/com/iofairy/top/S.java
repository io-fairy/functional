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
package com.iofairy.top;

import com.iofairy.except.UnexpectedParameterException;
import com.iofairy.tuple.Tuple;
import com.iofairy.tuple.Tuple2;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Global Variables And Methods for {@link String} Operations. <br>
 * 字符串相关操作的常用变量与函数。<br>
 * &gt;&gt;&gt;&gt;&gt;&gt; <br>
 * 采用简单类名 <b>S (String)</b> 模拟类似 Kotlin 的 <b>Top-level function</b>（顶级函数、全局函数）
 *
 * @since 0.3.0
 */
public final class S {

    /**
     * Whether CharSequence array contains {@code null} or empty {@code ""}
     * or CharSequence array is {@code null}. <br>
     * Especially, when <b>length of CharSequence array is 0</b> ({@code css.length == 0}), return {@code true}. <br>
     * 数组中包含{@code null}值或者空字符串{@code ""}或者CharSequence数组本身就为{@code null}，则返回true。
     * <b>特别地，当数组本身长度等于0时，也返回true。</b>
     *
     * @param css CharSequence array
     * @return {@code true} or {@code false}
     * @since 0.0.1
     */
    public static boolean hasEmpty(CharSequence... css) {
        if (css == null) return true;
        if (css.length == 0) return true;
        boolean hasEmpty = false;
        for (CharSequence cs : css) {
            if (isEmpty(cs)) {
                hasEmpty = true;
                break;
            }
        }
        return hasEmpty;
    }

    /**
     * {@code true} if all array values are {@code null} or empty {@code ""} value
     * or CharSequence array is {@code null}.  <br>
     * Especially, when <b>length of CharSequence array is 0</b> ({@code css.length == 0}), return {@code true}. <br>
     * 数组中所有的值都是{@code null}或者空字符串{@code ""}或者CharSequence数组本身就为{@code null}，则返回{@code true}。
     * <b>特别地，当数组本身长度等于0时，也返回true。</b>
     *
     * @param css CharSequence array
     * @return {@code true} or {@code false}
     * @since 0.0.1
     */
    public static boolean allEmpty(CharSequence... css) {
        if (css == null) return true;
        if (css.length == 0) return true;
        return Arrays.stream(css).allMatch(G::isEmpty);
    }

    /**
     * Whether CharSequence array contains {@code null} or empty {@code ""} or blank
     * or CharSequence array is {@code null}. <br>
     * Especially, when <b>length of CharSequence array is 0</b> ({@code css.length == 0}), return {@code true}. <br>
     * 数组中包含{@code null}值或空字符串{@code ""}或空白字符串或CharSequence数组本身就为{@code null}，则返回true。
     * <b>特别地，当数组本身长度等于0时，也返回true。</b>
     *
     * @param css string array
     * @return {@code true} or {@code false}
     * @see #isBlank(CharSequence)
     * @since 0.0.1
     */
    public static boolean hasBlank(CharSequence... css) {
        if (css == null) return true;
        if (css.length == 0) return true;
        boolean hasBlank = false;
        for (CharSequence cs : css) {
            if (isBlank(cs)) {
                hasBlank = true;
                break;
            }
        }
        return hasBlank;
    }

    /**
     * {@code true} if all array values are {@code null} or empty {@code ""} or blank
     * or CharSequence array is {@code null}. <br>
     * Especially, when <b>length of CharSequence array is 0</b> ({@code css.length == 0}), return {@code true}. <br>
     * 数组中所有的值都是{@code null}或空字符串{@code ""}或空白字符串或CharSequence数组本身就为{@code null}，则返回{@code true}。
     * <b>特别地，当数组本身长度等于0时，也返回true。</b>
     *
     * @param css CharSequence array
     * @return {@code true} or {@code false}
     * @see #isBlank(CharSequence)
     * @since 0.0.1
     */
    public static boolean allBlank(CharSequence... css) {
        if (css == null) return true;
        if (css.length == 0) return true;
        return Arrays.stream(css).allMatch(S::isBlank);
    }

    /**
     * Whether CharSequence List contains {@code null} or empty {@code ""}
     * or CharSequence List is {@code null}. <br>
     * Especially, when <b>length of CharSequence List is 0</b> ({@code css.length == 0}), return {@code true}. <br>
     * 列表中包含{@code null}值或者空字符串{@code ""}或者CharSequence列表本身就为{@code null}，则返回true。
     * <b>特别地，当列表本身长度等于0时，也返回true。</b>
     *
     * @param css CharSequence List
     * @return {@code true} or {@code false}
     * @since 0.3.0
     */
    public static boolean hasEmpty(List<? extends CharSequence> css) {
        if (css == null) return true;
        if (css.size() == 0) return true;
        boolean hasEmpty = false;
        for (CharSequence cs : css) {
            if (isEmpty(cs)) {
                hasEmpty = true;
                break;
            }
        }
        return hasEmpty;
    }

    /**
     * {@code true} if all List values are {@code null} or empty {@code ""} value
     * or CharSequence List is {@code null}.  <br>
     * Especially, when <b>length of CharSequence List is 0</b> ({@code css.length == 0}), return {@code true}. <br>
     * 列表中所有的值都是{@code null}或者空字符串{@code ""}或者CharSequence列表本身就为{@code null}，则返回{@code true}。
     * <b>特别地，当列表本身长度等于0时，也返回true。</b>
     *
     * @param css CharSequence List
     * @return {@code true} or {@code false}
     * @since 0.3.0
     */
    public static boolean allEmpty(List<? extends CharSequence> css) {
        if (css == null) return true;
        if (css.size() == 0) return true;
        return css.stream().allMatch(G::isEmpty);
    }

    /**
     * Whether CharSequence List contains {@code null} or empty {@code ""} or blank
     * or CharSequence List is {@code null}. <br>
     * Especially, when <b>length of CharSequence List is 0</b> ({@code css.length == 0}), return {@code true}. <br>
     * 列表中包含{@code null}值或空字符串{@code ""}或空白字符串或CharSequence列表本身就为{@code null}，则返回true。
     * <b>特别地，当列表本身长度等于0时，也返回true。</b>
     *
     * @param css string List
     * @return {@code true} or {@code false}
     * @see #isBlank(CharSequence)
     * @since 0.3.0
     */
    public static boolean hasBlank(List<? extends CharSequence> css) {
        if (css == null) return true;
        if (css.size() == 0) return true;
        boolean hasBlank = false;
        for (CharSequence cs : css) {
            if (isBlank(cs)) {
                hasBlank = true;
                break;
            }
        }
        return hasBlank;
    }

    /**
     * {@code true} if all List values are {@code null} or empty {@code ""} or blank
     * or CharSequence List is {@code null}. <br>
     * Especially, when <b>length of CharSequence List is 0</b> ({@code css.length == 0}), return {@code true}. <br>
     * 列表中所有的值都是{@code null}或空字符串{@code ""}或空白字符串或CharSequence列表本身就为{@code null}，则返回{@code true}。
     * <b>特别地，当列表本身长度等于0时，也返回true。</b>
     *
     * @param css CharSequence List
     * @return {@code true} or {@code false}
     * @see #isBlank(CharSequence)
     * @since 0.3.0
     */
    public static boolean allBlank(List<? extends CharSequence> css) {
        if (css == null) return true;
        if (css.size() == 0) return true;
        return css.stream().allMatch(S::isBlank);
    }

    /**
     * {@code true} if all chars in CharSequence are whitespace {@link Character#isWhitespace(char)}. <br>
     * 字符序列中的所有字符都是空白字符，则返回true。
     *
     * @param cs CharSequence
     * @return {@code true} or {@code false}
     * @since 0.0.1
     */
    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!isBlankChar(cs.charAt(i)))
                    return false;
            }
        }
        return true;
    }

    public static boolean isBlankChar(char c) {
        return isBlankChar((int) c);
    }

    public static boolean isBlankChar(int c) {
        return Character.isWhitespace(c) || Character.isSpaceChar(c);
    }

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * Returns itself if string is non-null; otherwise, returns empty({@code ""}).
     *
     * @param s string
     * @return itself or empty({@code ""})
     */
    public static String nullToEmpty(String s) {
        return isEmpty(s) ? "" : s;
    }

    /**
     * Returns itself if string is non-empty; otherwise, returns {@code null}.
     *
     * @param s string
     * @return itself or {@code null}
     */
    public static String emptyToNull(String s) {
        return isEmpty(s) ? null : s;
    }

    /**
     * Returns itself if string is non-blank; otherwise, returns {@code null}.
     *
     * @param s string
     * @return itself or {@code null}
     */
    public static String blankToNull(String s) {
        return isBlank(s) ? null : s;
    }

    /**
     * Returns itself if string is non-blank; otherwise, returns empty({@code ""}).
     *
     * @param s string
     * @return itself or empty({@code ""}).
     */
    public static String blankToEmpty(String s) {
        return isBlank(s) ? "" : s;
    }

    /**
     * Padding chars to the left of the input char sequence to the given length. <br>
     * 在字符串左侧添加字符以达到给定的长度
     *
     * @param cs      input char sequence
     * @param padChar pad char
     * @param length  final length of returns string
     * @return string
     * @since 0.3.7
     */
    public static String padLeftChars(CharSequence cs, char padChar, int length) {
        if (cs == null) return null;
        if (cs.length() >= length) return cs.toString();
        int padLength = length - cs.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < padLength; i++) {
            sb.append(padChar);
        }
        sb.append(cs);
        return sb.toString();
    }

    /**
     * Padding chars to the left of the input number to the given length. <br>
     * 在数字左侧添加字符以达到给定的长度
     *
     * @param number  input number
     * @param padChar pad char
     * @param length  final length of returns string
     * @return string
     * @since 0.3.7
     */
    public static String padLeftChars(Number number, char padChar, int length) {
        if (number == null) return null;
        String numberStr = G.toString(number);
        if (numberStr.length() >= length) return numberStr;
        int padLength = length - numberStr.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < padLength; i++) {
            sb.append(padChar);
        }
        sb.append(numberStr);
        return sb.toString();
    }

    /**
     * Tests if the given string ends with the specified suffix, when compared starting from the specified offset from the end of the string. <br>
     * 检查给定的字符串是否以指定的后缀结尾，从字符串末尾的指定偏移量开始比较。
     *
     * @param str           the string to check
     * @param suffix        the suffix to check for
     * @param offsetFromEnd the offset from the end of the string to start the comparison from. 从字符串末尾开始的偏移量
     * @return true if the given string ends with the specified suffix, false otherwise
     * @since 0.3.11
     */
    public static boolean endsWith(String str, String suffix, int offsetFromEnd) {
        if (str == null || suffix == null || offsetFromEnd < 0) return false;
        return str.startsWith(suffix, str.length() - offsetFromEnd - suffix.length());
    }

    /**
     * Trims the string and returns a string that does not start with {@code trimHeadStr} and does not end with {@code trimTailStr}. <br>
     * 修剪字符串，并返回不以 {@code trimHeadStr} 开头 且 不以 {@code trimTailStr} 结尾的字符串
     *
     * @param str         待修剪的字符串
     * @param trimHeadStr 修剪头部指定的字符串
     * @param trimTailStr 修剪尾部指定的字符串
     * @return 修剪后的字符串
     * @since 0.3.11
     */
    public static String trim(String str, String trimHeadStr, String trimTailStr) {
        return trim(str, trimHeadStr, trimTailStr, true);
    }

    /**
     * Trims the string and returns a string that does not start with {@code trimHeadStr} and does not end with {@code trimTailStr}. <br>
     * 修剪字符串，并返回不以 {@code trimHeadStr} 开头 且 不以 {@code trimTailStr} 结尾的字符串
     *
     * @param str             待修剪的字符串
     * @param trimHeadStr     修剪头部指定的字符串
     * @param trimTailStr     修剪尾部指定的字符串
     * @param isTrimHeadFirst true，先修剪头部，再修剪尾部；false，先修剪尾部，再修剪头部
     * @return 修剪后的字符串
     * @since 0.3.11
     */
    public static String trim(String str, String trimHeadStr, String trimTailStr, boolean isTrimHeadFirst) {
        if (isEmpty(str)) return str;

        int start = 0;
        int end = str.length();

        if (isTrimHeadFirst) {
            if (!isEmpty(trimHeadStr)) {
                while (start < end && str.startsWith(trimHeadStr, start)) {
                    start += trimHeadStr.length();
                }
            }
            if (!isEmpty(trimTailStr)) {
                while (end - trimTailStr.length() >= start && endsWith(str, trimTailStr, str.length() - end)) {
                    end -= trimTailStr.length();
                }
            }
        } else {
            if (!isEmpty(trimTailStr)) {
                while (end - trimTailStr.length() >= start && endsWith(str, trimTailStr, str.length() - end)) {
                    end -= trimTailStr.length();
                }
            }
            if (!isEmpty(trimHeadStr)) {
                while (start < end && str.startsWith(trimHeadStr, start)) {
                    start += trimHeadStr.length();
                }
            }
        }


        return str.substring(start, end);
    }

    /**
     * Trim the given string <b>once</b> with the specified header string and tail string. <br>
     * 使用指定的头部字符串与尾部字符串对给定的字符串修剪<b>一次</b>
     *
     * @param str         待修剪的字符串
     * @param trimHeadStr 修剪头部指定的字符串
     * @param trimTailStr 修剪尾部指定的字符串
     * @return 修剪后的字符串
     * @since 0.3.11
     */
    public static String trimOnce(String str, String trimHeadStr, String trimTailStr) {
        return trimOnce(str, trimHeadStr, trimTailStr, true);
    }

    /**
     * Trim the given string <b>once</b> with the specified header string and tail string. <br>
     * 使用指定的头部字符串与尾部字符串对给定的字符串修剪<b>一次</b>
     *
     * @param str             待修剪的字符串
     * @param trimHeadStr     修剪头部指定的字符串
     * @param trimTailStr     修剪尾部指定的字符串
     * @param isTrimHeadFirst true，先修剪头部，再修剪尾部；false，先修剪尾部，再修剪头部
     * @return 修剪后的字符串
     * @since 0.3.11
     */
    public static String trimOnce(String str, String trimHeadStr, String trimTailStr, boolean isTrimHeadFirst) {
        if (isEmpty(str)) return str;

        if (isTrimHeadFirst) {
            if (!isEmpty(trimHeadStr) && str.startsWith(trimHeadStr)) {
                str = str.substring(trimHeadStr.length());
            }
            if (!isEmpty(trimTailStr) && str.endsWith(trimTailStr)) {
                str = str.substring(0, str.length() - trimTailStr.length());
            }
        } else {
            if (!isEmpty(trimTailStr) && str.endsWith(trimTailStr)) {
                str = str.substring(0, str.length() - trimTailStr.length());
            }
            if (!isEmpty(trimHeadStr) && str.startsWith(trimHeadStr)) {
                str = str.substring(trimHeadStr.length());
            }
        }

        return str;
    }

    /**
     * Using string to enclose a String<br>
     * <p>Examples:
     * <pre>{@code
     * S.enclose("abc", "||");              // returns "||abc||";
     * }</pre>
     *
     * @param enclosedStr 被包围的字符串
     * @param encloseStr  包围的字符串
     * @return 包围后的字符串
     * @since 0.3.11
     */
    public static String enclose(String enclosedStr, String encloseStr) {
        return enclose(enclosedStr, encloseStr, encloseStr);
    }

    /**
     * Using two strings to enclose a String<br>
     * <p>Examples:
     * <pre>{@code
     * S.enclose("abc", ">>", "<<");            // returns ">>abc<<";
     * }</pre>
     *
     * @param enclosedStr 被包围的字符串
     * @param encloseStr1 包围的字符串1
     * @param encloseStr2 包围的字符串2
     * @return 包围后的字符串
     * @since 0.3.11
     */
    public static String enclose(String enclosedStr, String encloseStr1, String encloseStr2) {
        if (enclosedStr == null) return null;
        if (encloseStr1 == null) encloseStr1 = "";
        if (encloseStr2 == null) encloseStr2 = "";
        return encloseStr1 + enclosedStr + encloseStr2;
    }

    /**
     * Using char to enclose a String
     * <p>Examples:
     * <pre>{@code
     * S.enclose("abc", '"');                   // returns "\"abc\"";
     * }</pre>
     *
     * @param enclosedStr 被包围的字符串
     * @param encloseChar 包围的字符
     * @return 包围后的字符串
     * @since 0.3.11
     */
    public static String enclose(String enclosedStr, char encloseChar) {
        return enclose(enclosedStr, encloseChar, encloseChar);
    }

    /**
     * Using two chars to enclose a String
     * <p>Examples:
     * <pre>{@code
     * S.enclose("abc", '>', '<');              // returns ">abc<";
     * }</pre>
     *
     * @param enclosedStr  被包围的字符串
     * @param encloseChar1 包围的字符1
     * @param encloseChar2 包围的字符2
     * @return 包围后的字符串
     * @since 0.3.11
     */
    public static String enclose(String enclosedStr, char encloseChar1, char encloseChar2) {
        if (enclosedStr == null) return null;
        return encloseChar1 + enclosedStr + encloseChar2;
    }

    /**
     * Split by the place where the delimiter first appears, only split once. <br>
     * 在分隔符第一次出现的地方切分字符串，将一个字符串分隔成两个字符串
     *
     * @param source    will be split
     * @param delimiter delimiter
     * @return Tuple2&lt;String, String&gt; -- first substring and second substring
     * @since 0.2.0
     */
    public static Tuple2<String, String> splitOnce(String source, String delimiter) {
        if (hasEmpty(source, delimiter)) return Tuple.of(source, null);
        int index = source.indexOf(delimiter);
        if (index > -1) {
            return Tuple.of(source.substring(0, index), source.substring(index + delimiter.length()));
        } else {
            return Tuple.of(source, null);
        }
    }

    /**
     * The index within this string of the specified occurrence order of the specified substring. <br>
     * 获取指定的子字符串的指定出现次序在原字符串中的位置序号
     *
     * @param source          原字符串
     * @param subStr          要匹配的子字符串
     * @param appearanceOrder 出现的次序（第几次出现），从 1 开始计数
     * @return index
     * @throws NullPointerException         if {@code source} or {@code subStr} is null
     * @throws UnexpectedParameterException if {@code appearanceOrder} less than 1.
     * @since 0.3.11
     */
    public static int indexOf(String source, String subStr, int appearanceOrder) {
        if (source == null || subStr == null) throw new NullPointerException("Parameters `source`, `subStr` must be non-null!");
        if (appearanceOrder < 1) throw new UnexpectedParameterException("Parameter `appearanceOrder` must be greater than 0");

        int index = -1;
        int count = 0;
        while ((index = source.indexOf(subStr, index + 1)) != -1) {
            count++;
            if (count == appearanceOrder) {
                return index;
            }
        }
        return -1;
    }


    /**
     * Counts how many times the char occurrences in the given string.
     *
     * @param str input string
     * @param c   match character
     * @return the number of occurrences
     * @since 0.3.0
     */
    public static int countChars(final CharSequence str, final char c) {
        if (isEmpty(str)) return 0;

        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (c == str.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Counts how many times the char in char array occurrences in the given string.
     * <p>Examples:
     * <pre>{@code
     * S.countMultiChars("abc- Defgh_IJ--klm  _nop-", '-', ' ', 'z', '_');    // returns [4, 3, 0, 2];
     *                                                                        // --------------------
     *                                                                        // The number of '-': 4
     *                                                                        // The number of ' ': 3
     *                                                                        // The number of 'z': 0
     *                                                                        // The number of '_': 2
     * }</pre>
     *
     * @param str string
     * @param cs  char array
     * @return each char occurrences times
     * @since 0.3.0
     */
    public static int[] countMultiChars(final CharSequence str, final char... cs) {
        if (G.isEmpty(cs)) return new int[0];
        int length = cs.length;
        int[] counts = new int[length];
        if (isEmpty(str)) return counts;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            for (int j = 0; j < cs.length; j++) {
                if (c == cs[j]) {
                    counts[j] += 1;
                    break;
                }
            }
        }
        return counts;
    }

    /**
     * Counts how many times the char in {@link CharSequence} occurrences in the given string.
     * <p>Examples:
     * <pre>{@code
     * S.countMultiChars("abc- Defgh_IJ--klm  _nop-", "- z_");    // returns [4, 3, 0, 2];
     *                                                            // --------------------
     *                                                            // The number of '-': 4
     *                                                            // The number of ' ': 3
     *                                                            // The number of 'z': 0
     *                                                            // The number of '_': 2
     * }</pre>
     *
     * @param str   string
     * @param chars {@link CharSequence}
     * @return each char occurrences times
     * @since 0.3.0
     */
    public static int[] countMultiChars(final CharSequence str, final CharSequence chars) {
        if (G.isEmpty(chars)) return new int[0];
        int length = chars.length();
        int[] counts = new int[length];
        if (isEmpty(str)) return counts;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            for (int j = 0; j < chars.length(); j++) {
                if (c == chars.charAt(j)) {
                    counts[j] += 1;
                    break;
                }
            }
        }
        return counts;
    }

    /**
     * Formats a given number according to a default pattern.
     *
     * @param number The number to be formatted.
     * @return The formatted number as a string.
     * @since 0.4.10
     */
    public static String format(Number number) {
        return format(number, "#.##", RoundingMode.HALF_UP, false);
    }


    /**
     * Formats a given number according to a specified pattern.
     *
     * @param number  The number to be formatted.
     * @param pattern The pattern to be applied during formatting.
     * @return The formatted number as a string.
     * @since 0.4.9
     */
    public static String format(Number number, String pattern) {
        return format(number, pattern, RoundingMode.HALF_UP, false);
    }

    /**
     * Formats a given number according to a specified pattern with a specific rounding mode.
     *
     * @param number       The number to be formatted.
     * @param pattern      The pattern to be applied during formatting.
     * @param roundingMode The rounding mode to be used during formatting.
     * @return The formatted number as a string.
     * @since 0.4.9
     */
    public static String format(Number number, String pattern, RoundingMode roundingMode) {
        return format(number, pattern, roundingMode, false);
    }

    /**
     * Formats a given number according to a default pattern.
     *
     * @param number       The number to be formatted.
     * @param isNullToZero Is {@code null} to {@code 0}
     * @return The formatted number as a string.
     * @since 0.4.10
     */
    public static String format(Number number, boolean isNullToZero) {
        return format(number, "#.##", RoundingMode.HALF_UP, isNullToZero);
    }

    /**
     * Formats a given number according to a specified pattern.
     *
     * @param number       The number to be formatted.
     * @param pattern      The pattern to be applied during formatting.
     * @param isNullToZero Is {@code null} to {@code 0}
     * @return The formatted number as a string.
     * @since 0.4.10
     */
    public static String format(Number number, String pattern, boolean isNullToZero) {
        return format(number, pattern, RoundingMode.HALF_UP, isNullToZero);
    }

    /**
     * Formats a given number according to a specified pattern with a specific rounding mode.
     *
     * @param number       The number to be formatted.
     * @param pattern      The pattern to be applied during formatting.
     * @param roundingMode The rounding mode to be used during formatting.
     * @param isNullToZero Is {@code null} to {@code 0}
     * @return The formatted number as a string.
     * @since 0.4.10
     */
    public static String format(Number number, String pattern, RoundingMode roundingMode, boolean isNullToZero) {
        if (number == null) return isNullToZero ? "0" : "null";
        DecimalFormat df = new DecimalFormat(pattern);
        df.setRoundingMode(roundingMode);
        return df.format(number);
    }

    /**
     * Repeats a string a specified number of times.
     *
     * @param str         The string to be repeated.
     * @param repeatTimes The number of times to repeat the string.
     * @return The repeated string.
     * @since 0.4.9
     */
    public static String repeat(String str, int repeatTimes) {
        if (str == null) return null;
        if (str.length() == 0 || repeatTimes <= 0) return "";
        if (repeatTimes == 1) return str;
        if (repeatTimes > Integer.MAX_VALUE - 8)
            throw new IllegalArgumentException("Parameter `repeatTimes` must be <= (Integer.MAX_VALUE - 8), otherwise, the memory will overflow! ");

        return String.join("", Collections.nCopies(repeatTimes, str));
    }


}
