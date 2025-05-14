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
import com.iofairy.range.Range;
import com.iofairy.top.base.PaddingStrategy;
import com.iofairy.tuple.Tuple;
import com.iofairy.tuple.Tuple2;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public static boolean hasEmpty(Collection<? extends CharSequence> css) {
        if (css == null) return true;
        if (css.isEmpty()) return true;
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
    public static boolean allEmpty(Collection<? extends CharSequence> css) {
        if (css == null) return true;
        if (css.isEmpty()) return true;
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
    public static boolean hasBlank(Collection<? extends CharSequence> css) {
        if (css == null) return true;
        if (css.isEmpty()) return true;
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
    public static boolean allBlank(Collection<? extends CharSequence> css) {
        if (css == null) return true;
        if (css.isEmpty()) return true;
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
     * Return {@code true} when CharSequence is not blank. <br>
     *
     * @param cs CharSequence
     * @return {@code true} or {@code false}
     * @since 0.5.2
     */
    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }

    /**
     * Return {@code true} when CharSequence is not empty. <br>
     *
     * @param cs CharSequence
     * @return {@code true} or {@code false}
     * @since 0.5.2
     */
    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
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
     * Returns itself if string is non-null; otherwise, returns blank({@code " "}).
     *
     * @param s string
     * @return itself or blank({@code " "}).
     * @since 0.5.2
     */
    public static String nullToBlank(String s) {
        return s == null ? " " : s;
    }

    /**
     * Returns itself if string is non-empty; otherwise, returns blank({@code " "}).
     *
     * @param s string
     * @return itself or blank({@code " "}).
     * @since 0.5.2
     */
    public static String emptyToBlank(String s) {
        return isEmpty(s) ? " " : s;
    }

    /**
     * Padding chars to the left of the input char sequence to the given length. <br>
     * 在字符串左侧添加字符以达到给定的长度
     *
     * @param cs      input char sequence
     * @param padChar pad char
     * @param length  final length of returns string
     * @return Padded string (<b>returns</b> {@code null} if input is null)
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
     * @return Padded string (<b>returns</b> {@code null} if input is null)
     * @since 0.3.7
     */
    public static String padLeftChars(Number number, char padChar, int length) {
        if (number == null) return null;
        String numberStr = G.toString(number);
        return padLeftChars(numberStr, padChar, length);
    }

    /**
     * Padding chars to the right of the input char sequence to the given length. <br>
     * 在字符串右侧添加字符以达到给定的长度
     *
     * @param cs      input char sequence
     * @param padChar pad char
     * @param length  final length of returns string
     * @return Padded string
     * @since 0.4.15
     */
    public static String padRightChars(CharSequence cs, char padChar, int length) {
        if (cs == null) return null;
        if (cs.length() >= length) return cs.toString();
        int padLength = length - cs.length();
        StringBuilder sb = new StringBuilder();
        sb.append(cs);
        for (int i = 0; i < padLength; i++) {
            sb.append(padChar);
        }
        return sb.toString();
    }

    /**
     * Padding chars to the right of the input number to the given length. <br>
     * 在数字右侧添加字符以达到给定的长度
     *
     * @param number  input number
     * @param padChar pad char
     * @param length  final length of returns string
     * @return Padded string (<b>returns</b> {@code null} if input is null)
     * @since 0.4.15
     */
    public static String padRightChars(Number number, char padChar, int length) {
        if (number == null) return null;
        String numberStr = G.toString(number);
        return padRightChars(numberStr, padChar, length);
    }

    /**
     * Pads a string with specified characters on both sides (prioritizes centering,
     * allows specifying side preference for odd padding amounts) <br>
     * 在字符串两侧填充指定字符（优先居中，奇数填充时可指定侧重方向）
     *
     * @param cs       input char sequence
     * @param padChar  pad char
     * @param length   final length of returns string
     * @param leftMore {@code true}, left more; otherwise, right more
     * @return Padded string (<b>returns</b> {@code null} if input is null)
     * @since 0.5.12
     */
    public static String padBothSidesChars(CharSequence cs, char padChar, int length, boolean leftMore) {
        if (cs == null) return null;
        int padding = length - cs.length();
        if (padding <= 0) {
            return cs.toString();
        }

        int leftPad = padding / 2;
        int rightPad = leftPad;

        // Handle the odd-numbered padding strategy
        if (padding % 2 != 0) {
            if (leftMore) {
                leftPad++;
            } else {
                rightPad++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < leftPad; i++) {
            sb.append(padChar);
        }
        sb.append(cs);
        for (int i = 0; i < rightPad; i++) {
            sb.append(padChar);
        }

        return sb.toString();
    }

    /**
     * Pads a number with specified characters on both sides (prioritizes centering,
     * allows specifying side preference for odd padding amounts) <br>
     * 在一个数字两侧填充指定字符（优先居中，奇数填充时可指定侧重方向）
     *
     * @param number   input number
     * @param padChar  pad char
     * @param length   final length of returns string
     * @param leftMore {@code true}, left more; otherwise, right more
     * @return Padded string (<b>returns</b> {@code null} if input is null)
     * @since 0.5.12
     */
    public static String padBothSidesChars(Number number, char padChar, int length, boolean leftMore) {
        if (number == null) return null;
        String numberStr = G.toString(number);
        return padBothSidesChars(numberStr, padChar, length, leftMore);
    }

    /**
     * Pads the input string with specified characters according to the padding strategy <br>
     * 根据填充策略填充字符串，使字符串达到指定的长度
     *
     * @param cs              Input CharSequence to be padded
     * @param padChar         Padding character (single character)
     * @param length          Target length
     * @param paddingStrategy Padding strategy (<b>LEFT/RIGHT/LEFT_MORE/RIGHT_MORE</b>). 填充策略（<b>左填充/右填充/左侧优先填充/右侧优先填充</b>）
     * @return Padded string (<b>returns</b> {@code null} if input is null)
     * @since 0.5.12
     */
    public static String padChars(CharSequence cs, char padChar, int length, PaddingStrategy paddingStrategy) {
        if (cs == null) return null;
        if (paddingStrategy == null) {
            paddingStrategy = PaddingStrategy.LEFT;
        }
        switch (paddingStrategy) {
            case RIGHT:
                return padRightChars(cs, padChar, length);
            case LEFT_MORE:
                return padBothSidesChars(cs, padChar, length, true);
            case RIGHT_MORE:
                return padBothSidesChars(cs, padChar, length, false);
            default: // LEFT
                return padLeftChars(cs, padChar, length);
        }
    }

    /**
     * Pads the input number with specified characters according to the padding strategy <br>
     * 根据填充策略填充数字，使数字达到指定的长度
     *
     * @param number          Input number to be padded
     * @param padChar         Padding character (single character)
     * @param length          Target length
     * @param paddingStrategy Padding strategy (<b>LEFT/RIGHT/LEFT_MORE/RIGHT_MORE</b>). 填充策略（<b>左填充/右填充/左侧优先填充/右侧优先填充</b>）
     * @return Padded string (<b>returns</b> {@code null} if input is null)
     * @since 0.5.12
     */
    public static String padChars(Number number, char padChar, int length, PaddingStrategy paddingStrategy) {
        if (number == null) return null;
        String numberStr = G.toString(number);
        return padChars(numberStr, padChar, length, paddingStrategy);
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
     * @param source    string will be split
     * @param delimiter delimiter
     * @return Tuple {@code (substring before the first delimiter, substring after the first delimiter)}, returns {@code (original source, null)} if the delimiter is not found
     * @since 0.2.0
     */
    public static Tuple2<String, String> splitOnce(String source, String delimiter) {
        return splitOnce(source, delimiter, 1);
    }

    /**
     * Split by the place where the delimiter nth appears, only split once. <br>
     * 在分隔符第n次出现的地方切分字符串，将一个字符串分隔成两个字符串
     *
     * @param source    string will be split
     * @param delimiter delimiter
     * @param atNth     The nth delimiter to split (starting from 1). 分隔符出现的次序（第几次出现），从 1 开始计数。
     * @return Tuple {@code (substring before the nth delimiter, substring after the nth delimiter)}, returns {@code (original source, null)} if the delimiter is not found
     * @since 0.5.12
     */
    public static Tuple2<String, String> splitOnce(String source, String delimiter, int atNth) {
        if (hasEmpty(source, delimiter)) return Tuple.of(source, null);

        int index = indexOf(source, delimiter, atNth);
        if (index > -1) {
            return Tuple.of(source.substring(0, index), source.substring(index + delimiter.length()));
        } else {
            return Tuple.of(source, null);
        }
    }

    /**
     * Split by the place where the delimiter nth appears, only split once. <br>
     * 在分隔符第n次出现的地方切分字符串，将一个字符串分隔成两个字符串
     *
     * @param source  string will be split
     * @param pattern delimiter pattern
     * @param atNth   The nth delimiter to split (starting from 1). 分隔符出现的次序（第几次出现），从 1 开始计数。
     * @return Tuple {@code (substring before the nth delimiter, substring after the nth delimiter)}, returns {@code (original source, null)} if the delimiter is not found
     * @since 0.5.12
     */
    public static Tuple2<String, String> splitOnceByRegex(String source, String pattern, int atNth) {
        if (pattern == null) return Tuple.of(source, null);
        if (isEmpty(source)) return Tuple.of(source, null);
        return splitOnceByRegex(source, Pattern.compile(pattern), atNth);
    }

    /**
     * Split by the place where the delimiter nth appears, only split once. <br>
     * 在分隔符第n次出现的地方切分字符串，将一个字符串分隔成两个字符串
     *
     * @param source  string will be split
     * @param pattern delimiter pattern
     * @param atNth   The nth delimiter to split (starting from 1). 分隔符出现的次序（第几次出现），从 1 开始计数。
     * @return Tuple {@code (substring before the nth delimiter, substring after the nth delimiter)}, returns {@code (original source, null)} if the delimiter is not found
     * @since 0.5.12
     */
    public static Tuple2<String, String> splitOnceByRegex(String source, Pattern pattern, int atNth) {
        if (pattern == null) return Tuple.of(source, null);
        if (isEmpty(source)) return Tuple.of(source, null);

        Range<Integer> startEnd = indexOfRegex(source, pattern, atNth);
        if (startEnd.start > -1) {
            return Tuple.of(source.substring(0, startEnd.start), source.substring(startEnd.end));
        } else {
            return Tuple.of(source, null);
        }
    }

    /**
     * The index within this string of the specified occurrence order of the specified substring. <br>
     * 获取指定的子字符串在原字符串中第n次出现的位置序号
     *
     * @param source          原字符串
     * @param subStr          要匹配的子字符串
     * @param appearanceOrder The nth delimiter to split (starting from 1). 分隔符出现的次序（第几次出现），从 1 开始计数。
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
     * The start index and end index within this string of the specified occurrence order of the specified regex pattern. <br>
     * 获取指定的正则表达式在原字符串中第n次匹配的位置序号
     *
     * @param source          原字符串
     * @param pattern         要匹配的正则表达式
     * @param appearanceOrder The nth delimiter to split (starting from 1). 分隔符出现的次序（第几次出现），从 1 开始计数。
     * @return {@code (startIndex, endIndex)}, returns {@code (-1, -1)} when not found
     * @throws NullPointerException         if {@code source} or {@code pattern} is null
     * @throws UnexpectedParameterException if {@code appearanceOrder} less than 1.
     * @since 0.5.12
     */
    public static Range<Integer> indexOfRegex(String source, Pattern pattern, int appearanceOrder) {
        if (source == null || pattern == null) throw new NullPointerException("Parameters `source`, `pattern` must be non-null!");
        if (appearanceOrder < 1) throw new UnexpectedParameterException("Parameter `appearanceOrder` must be greater than 0");

        int count = 0;
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            count++;
            if (count == appearanceOrder) {
                int start = matcher.start();
                int end = matcher.end();
                return Range.closedOpen(start, end);
            }
        }

        return Range.open(-1, -1);
    }

    /**
     * The start index and end index within this string of the specified occurrence order of the specified regex pattern. <br>
     * 获取指定的正则表达式在原字符串中第n次匹配的位置序号
     *
     * @param source          原字符串
     * @param pattern         要匹配的正则表达式
     * @param appearanceOrder The nth delimiter to split (starting from 1). 出现的次序（第几次出现），从 1 开始计数。
     * @return {@code (startIndex, endIndex)}, returns {@code (-1, -1)} when not found
     * @throws NullPointerException         if {@code source} or {@code pattern} is null
     * @throws UnexpectedParameterException if {@code appearanceOrder} less than 1.
     * @since 0.5.12
     */
    public static Range<Integer> indexOfRegex(String source, String pattern, int appearanceOrder) {
        if (source == null || pattern == null) throw new NullPointerException("Parameters `source`, `pattern` must be non-null!");
        if (appearanceOrder < 1) throw new UnexpectedParameterException("Parameter `appearanceOrder` must be greater than 0");

        return indexOfRegex(source, Pattern.compile(pattern), appearanceOrder);
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
