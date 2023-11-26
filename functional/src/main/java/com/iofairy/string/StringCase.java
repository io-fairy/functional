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

import java.util.Arrays;
import java.util.List;

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
    UPPER_CAMEL('A', true, true),
    /**
     * ALL_SEPARATORS (separator is '-' or ' ' or '_'), it is used to convert "camel case"
     */
    ALL_SEPARATORS('*', false, false);

    /**
     * All available separators
     */
    public static final List<Character> SEPARATORS = Arrays.asList(' ', '-', '_');

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
     * @param toCase   target StringCase
     * @param inputStr string
     * @return new string
     */
    public String to(StringCase toCase, String inputStr) {
        if (S.isEmpty(inputStr)) return inputStr;

        if (toCase == ALL_SEPARATORS) throw new IllegalArgumentException("Parameter `toCase` cannot be `StringCase.ALL_SEPARATORS`! ");

        if (this == toCase) {
            return isCamelCase
                    ? (isUpper ? Ascii.toUpperFirstChar(inputStr) : Ascii.toLowerFirstChar(inputStr))
                    : (isUpper ? Ascii.toUpper(inputStr) : Ascii.toLower(inputStr));
        }

        if (this.isCamelCase || toCase.isCamelCase) {
            if (this.isCamelCase) { // (this.isCamelCase && toCase.isCamelCase) || (this.isCamelCase && !toCase.isCamelCase)
                return fromCamelCase(toCase, inputStr);
            } else {                // !this.isCamelCase && toCase.isCamelCase
                return toCamelCase(toCase, inputStr, this == ALL_SEPARATORS ? SEPARATORS : Arrays.asList(this.separator));
            }
        } else {
            if (this == ALL_SEPARATORS) {
                String replaceStr = inputStr.replaceAll("[\\- _]", toCase.separator + "");
                return toCase.isUpper ? Ascii.toUpper(replaceStr) : Ascii.toLower(replaceStr);
            } else {
                return toCase.isUpper
                        ? Ascii.toUpper(inputStr.replace(this.separator, toCase.separator))
                        : Ascii.toLower(inputStr.replace(this.separator, toCase.separator));
            }
        }
    }

    /**
     * Convert {@code StringCase} to {@link #LOWER_CAMEL} or {@link #UPPER_CAMEL}.
     *
     * @param toCase     {@link #LOWER_CAMEL} or {@link #UPPER_CAMEL}
     * @param inputStr   string
     * @param separators separators
     * @return CamelCase string
     */
    private String toCamelCase(StringCase toCase, String inputStr, List<Character> separators) {
        if (this.isCamelCase) {
            return toCase.isUpper ? Ascii.toUpperFirstChar(inputStr) : Ascii.toLowerFirstChar(inputStr);
        } else {
            TrimRegex regex = TrimRegex.regex(separators);
            inputStr = inputStr.replaceAll(regex.trimHeadRegex, "").replaceAll(regex.trimTailRegex, "");
            if (S.isEmpty(inputStr)) return inputStr;

            boolean isLastCharSeparator = false;        // Whether the last character was a separator
            boolean isCurrentCharSeparator;     // Whether the current character was a separator
            StringBuilder sb = new StringBuilder();
            for (char c : inputStr.toCharArray()) {
                isCurrentCharSeparator = separators.contains(c);

                if (!isCurrentCharSeparator) {
                    sb.append(isLastCharSeparator ? Ascii.toUpper(c) : Ascii.toLower(c));
                }

                isLastCharSeparator = isCurrentCharSeparator;
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
     * @param toCase   toCase
     * @param inputStr string
     * @return toCase string
     */
    private String fromCamelCase(StringCase toCase, String inputStr) {
        if (toCase.isCamelCase) {
            return toCase.isUpper ? Ascii.toUpperFirstChar(inputStr) : Ascii.toLowerFirstChar(inputStr);
        } else {
            StringBuilder sb = new StringBuilder();
            char[] chars = inputStr.toCharArray();
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


    /**
     * 修剪字符正则表达式
     *
     * @since 0.4.11
     */
    static class TrimRegex {
        String trimHeadRegex;
        String trimTailRegex;

        public TrimRegex(String trimHeadRegex, String trimTailRegex) {
            this.trimHeadRegex = trimHeadRegex;
            this.trimTailRegex = trimTailRegex;
        }

        static TrimRegex regex(List<Character> characters) {
            String trimRegex = "[";
            for (Character character : characters) {
                trimRegex += character == '-' ? "\\-" : character;
            }
            trimRegex += "]+";
            return new TrimRegex("^" + trimRegex, trimRegex + "$");
        }

        @Override
        public String toString() {
            return "TrimRegex{" +
                    "trimHeadRegex='" + trimHeadRegex + '\'' +
                    ", trimTailRegex='" + trimTailRegex + '\'' +
                    '}';
        }
    }

}
