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

/**
 * StringCase
 *
 * @since 0.3.0
 */
public enum StringCase {
    /**
     * LOWER_SPACE (separator is ' '), e.g. "lower space"
     */
    LOWER_SPACE(' ', false, false),
    /**
     * UPPER_SPACE (separator is ' '), e.g. "UPPER SPACE"
     */
    UPPER_SPACE(' ', false, true),
    /**
     * LOWER_HYPHEN (separator is '-'), e.g. "lower-hyphen"
     */
    LOWER_HYPHEN('-', false, false),
    /**
     * UPPER_HYPHEN (separator is '-'), e.g. "UPPER-HYPHEN"
     */
    UPPER_HYPHEN('-', false, true),
    /**
     * LOWER_UNDERSCORE (separator is '_'), e.g. "lower_underscore"
     */
    LOWER_UNDERSCORE('_', false, false),
    /**
     * UPPER_UNDERSCORE (separator is '_'), e.g. "UPPER_UNDERSCORE"
     */
    UPPER_UNDERSCORE('_', false, true),
    /**
     * LOWER_CAMEL (separator is "A-Z"), e.g. "lowerCamel"
     */
    LOWER_CAMEL('A', true, false),
    /**
     * UPPER_CAMEL (separator is "A-Z"), e.g. "UpperCamel"
     */
    UPPER_CAMEL('A', true, true);

    public final char separator;
    public final boolean isCamelCase;
    public final boolean isUpper;

    StringCase(char separator, boolean isCamelCase, boolean isUpper) {
        this.separator = separator;
        this.isCamelCase = isCamelCase;
        this.isUpper = isUpper;
    }

    /**
     * Convert this StringCase to another StringCase.
     *
     * @param toCase target StringCase
     * @param str    string
     * @return new string
     */
    String to(StringCase toCase, String str) {
        if (this == toCase) {
            return isCamelCase
                    ? (isUpper ? Ascii.toUpperFirstChar(str) : Ascii.toLowerFirstChar(str))
                    : (isUpper ? Ascii.toUpper(str) : Ascii.toLower(str));
        }

        if (this.isCamelCase || toCase.isCamelCase) {
            if (this.isCamelCase) { // (this.isCamelCase && toCase.isCamelCase) || (this.isCamelCase && !toCase.isCamelCase)
                return fromCamelCase(toCase, str);
            } else {                // !this.isCamelCase && toCase.isCamelCase
                return toCamelCase(toCase, str);
            }
        } else {
            return toCase.isUpper
                    ? Ascii.toUpper(str.replace(this.separator, toCase.separator))
                    : Ascii.toLower(str.replace(this.separator, toCase.separator));
        }
    }

    /**
     * Convert {@code StringCase} to {@link #LOWER_CAMEL} or {@link #UPPER_CAMEL}.
     *
     * @param toCase {@link #LOWER_CAMEL} or {@link #UPPER_CAMEL}
     * @param str    string
     * @return CamelCase string
     */
    private String toCamelCase(StringCase toCase, String str) {
        if (this.isCamelCase) {
            return toCase.isUpper ? Ascii.toUpperFirstChar(str) : Ascii.toLowerFirstChar(str);
        } else {
            boolean isLastCharSeparator = false;
            StringBuilder sb = new StringBuilder();
            for (char c : str.toCharArray()) {
                if (isLastCharSeparator && Ascii.isLetter(c)) {
                    sb.setCharAt(sb.length() - 1, Ascii.toUpper(c));
                } else {
                    sb.append(Ascii.toLower(c));
                }
                isLastCharSeparator = this.separator == c;
            }
            if (toCase.isUpper) {
                sb.setCharAt(0, Ascii.toUpper(sb.charAt(0)));
            }
            return sb.toString();
        }
    }

    /**
     * Convert {@link #LOWER_CAMEL} or {@link #UPPER_CAMEL} to {@code toCase}
     *
     * @param toCase toCase
     * @param str    string
     * @return toCase string
     */
    private String fromCamelCase(StringCase toCase, String str) {
        if (toCase.isCamelCase) {
            return toCase.isUpper ? Ascii.toUpperFirstChar(str) : Ascii.toLowerFirstChar(str);
        } else {
            StringBuilder sb = new StringBuilder();
            char[] chars = str.toCharArray();
            if (toCase.isUpper) {
                for (int i = 0; i < chars.length; i++) {
                    char c = chars[i];
                    if (i == 0) {
                        sb.append(Ascii.toUpper(c));
                    } else {
                        if (Ascii.upperOrLowerOrNot(c) == 1) {
                            sb.append(toCase.separator);
                            sb.append(c);
                        } else {
                            sb.append(Ascii.toUpper(c));
                        }
                    }
                }
            } else {
                for (int i = 0; i < chars.length; i++) {
                    char c = chars[i];
                    if (i == 0) {
                        sb.append(Ascii.toLower(c));
                    } else {
                        if (Ascii.upperOrLowerOrNot(c) == 1) {
                            sb.append(toCase.separator);
                            sb.append(Ascii.toLower(c));
                        } else {
                            sb.append(c);
                        }
                    }
                }
            }

            return sb.toString();
        }
    }

}
