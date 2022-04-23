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

import com.iofairy.top.S;

/**
 * ASCII characters
 *
 * @since 0.3.0
 */
public final class Ascii {

    private Ascii() {
    }

    /**
     * Whether the character is an ASCII character
     *
     * @param c input character
     * @return {@code true} if character is an ASCII, otherwise, return {@code false}
     * @since 0.3.0
     */
    public static boolean isAscii(char c) {
        return c <= 127;
    }

    /**
     * Whether the character is a Printable ASCII character
     *
     * @param c input character
     * @return {@code true} if character is a Printable ASCII, otherwise, return {@code false}
     * @since 0.3.0
     */
    public static boolean isPrintableAscii(char c) {
        return c >= 32 && c <= 126;
    }

    /**
     * Whether the character is a Control ASCII character
     *
     * @param c input character
     * @return {@code true} if character is a Control ASCII, otherwise, return {@code false}
     * @since 0.3.0
     */
    public static boolean isControlAscii(final char c) {
        return c <= 31 || c == 127;
    }

    /**
     * Whether the character is a Letter ({@code a-z,A-Z})
     *
     * @param c input character
     * @return {@code true} if character is a Letter ({@code a-z,A-Z}), otherwise, return {@code false}
     * @since 0.3.0
     */
    public static boolean isLetter(char c) {
        return ((c >= 'A') && (c <= 'Z')) || (c >= 'a') && (c <= 'z');
    }

    /**
     * Whether the character is a Number ({@code 0-9})
     *
     * @param c input character
     * @return {@code true} if character is a Number ({@code 0-9}), otherwise, return {@code false}
     * @since 0.3.0
     */
    public static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    /**
     * Indicates the character is a <b>Upper ASCII Letter</b> ({@code A-Z})
     * or <b>Lower ASCII Letter</b> ({@code a-z})
     * or <b>Not ASCII Letter</b>. <br>
     * <ul>
     *     <li> -1: The character is <b>Not a ASCII Letter</b>;
     *     <li> &nbsp;0: The character is a <b>Lower ASCII Letter</b> ({@code a-z});
     *     <li> &nbsp;1: The character is a <b>Upper ASCII Letter</b> ({@code A-Z}).
     * </ul>
     *
     * @param c input character
     * @return <ul>
     *             <li> -1: The character is <b>Not a ASCII Letter</b>;
     *             <li> &nbsp;0: The character is a <b>Lower ASCII Letter</b> ({@code a-z});
     *             <li> &nbsp;1: The character is a <b>Upper ASCII Letter</b> ({@code A-Z}).
     *         </ul>
     * @since 0.3.0
     */
    public static int upperOrLowerOrNot(char c) {
        return isLetter(c) ? ((c >= 'A') && (c <= 'Z') ? 1 : 0) : -1;
    }

    /**
     * Convert lowercase character to uppercase
     *
     * @param c input character
     * @return uppercase character or original character
     * @since 0.3.0
     */
    public static char toUpper(char c) {
        return upperOrLowerOrNot(c) == 0 ? (char) (c ^ 0x20) : c;
    }

    /**
     * Convert uppercase character to lowercase
     *
     * @param c input character
     * @return uppercase character or original character
     * @since 0.3.0
     */
    public static char toLower(char c) {
        return upperOrLowerOrNot(c) == 1 ? (char) (c ^ 0x20) : c;
    }

    /**
     * Convert all lowercase characters to uppercase
     *
     * @param str string
     * @return uppercase string
     * @since 0.3.0
     */
    public static String toUpper(String str) {
        if (S.isEmpty(str)) return str;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (upperOrLowerOrNot(c) == 0) {
                chars[i] = (char) (c ^ 0x20);
            }
        }
        return String.valueOf(chars);
    }

    /**
     * Convert all uppercase characters to lowercase
     *
     * @param str string
     * @return lowercase string
     * @since 0.3.0
     */
    public static String toLower(String str) {
        if (S.isEmpty(str)) return str;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (upperOrLowerOrNot(c) == 1) {
                chars[i] = (char) (c ^ 0x20);
            }
        }
        return String.valueOf(chars);
    }

    /**
     * Convert first character in String to Upper.
     *
     * @param str string
     * @return string
     * @since 0.3.0
     */
    public static String toUpperFirstChar(String str) {
        if (S.isEmpty(str)) return str;
        return toUpper(str.charAt(0)) + str.substring(1);
    }

    /**
     * Convert first character in String to Lower.
     *
     * @param str string
     * @return string
     * @since 0.3.0
     */
    public static String toLowerFirstChar(String str) {
        if (S.isEmpty(str)) return str;
        return toLower(str.charAt(0)) + str.substring(1);
    }

    /**
     * Counts how many times the <b>upper letter</b> and <b>lower letter</b> and <b>not letter</b> occurrences in the given string.<br>
     * <p>Examples:
     * <pre>{@code
     * Ascii.countCases("$%^678 AblkjAE hhByyZ");  // returns [5, 8, 8];
     *                                             // upperCount: 5, lowerCount: 8, notLetterCount: 8
     * }</pre>
     *
     * @param str string
     * @return {@code int[]{upperCount, lowerCount, notLetterCount}}
     * @since 0.3.0
     */
    public static int[] countCases(final CharSequence str) {
        if (str == null) return new int[3];

        int upperCount = 0;
        int lowerCount = 0;
        int notLetterCount = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Ascii.isLetter(c)) {
                if (c >= 'A' && c <= 'Z') {
                    upperCount++;
                } else {
                    lowerCount++;
                }
            } else {
                notLetterCount++;
            }
        }
        return new int[]{upperCount, lowerCount, notLetterCount};
    }

}
