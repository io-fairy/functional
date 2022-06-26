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

import com.iofairy.tuple.Tuple;
import com.iofairy.tuple.Tuple2;

import java.util.Arrays;
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

}
