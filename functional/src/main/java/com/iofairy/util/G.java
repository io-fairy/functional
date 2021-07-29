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
package com.iofairy.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * Global Variables And Methods. <br>
 * 常用的变量与函数
 *
 * @since 0.0.1
 */
public class G {

    /**
     * Whether object array contains {@code null} value or object array is {@code null}. <br>
     * 数组中是否包含{@code null}值或数组本身就是{@code null}，则返回{@code true}
     * @param objects object array
     * @return {@code true} or {@code false}
     * @since 0.0.1
     */
    public static boolean hasNull(Object... objects) {
        if (objects == null) return true;
        if (objects.length == 0) return false;
        boolean hasNull = false;
        for (Object obj : objects) {
            if (obj == null) {
                hasNull = true;
                break;
            }
        }
        return hasNull;
    }

    /**
     * {@code true} if all array values are {@code null} or object array is {@code null}. <br>
     * 数组中所有的值都是{@code null}或数组本身就是{@code null}，则返回{@code true}
     * @param objects object array
     * @return {@code true} or {@code false}
     * @since 0.0.1
     */
    public static boolean allNull(Object... objects) {
        if (objects == null) return true;
        if (objects.length == 0) return false;
        return Arrays.stream(objects).allMatch(Objects::isNull);
    }

    /**
     * Whether CharSequence array contains {@code null} or empty {@code ""}
     * or CharSequence array is {@code null}. <br>
     * Especially, when <b>length of CharSequence array is 0</b> ({@code css.length == 0}), return {@code true}. <br>
     * 数组中包含{@code null}值或者空字符串{@code ""}或者CharSequence数组本身就为{@code null}，则返回true。
     * <b>特别地，当数组本身长度等于0时，也返回true。</b>
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
     * @param css CharSequence array
     * @return {@code true} or {@code false}
     * @since 0.0.1
     */
    public static boolean allEmpty(CharSequence... css) {
        if (css == null) return true;
        if (css.length == 0) return true;
        return Arrays.stream(css).allMatch(G::isEmpty);
    }

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * Whether CharSequence array contains {@code null} or empty {@code ""} or blank
     * or CharSequence array is {@code null}. <br>
     * Especially, when <b>length of CharSequence array is 0</b> ({@code css.length == 0}), return {@code true}. <br>
     * 数组中包含{@code null}值或空字符串{@code ""}或空白字符串或CharSequence数组本身就为{@code null}，则返回true。
     * <b>特别地，当数组本身长度等于0时，也返回true。</b>
     * @param css string array
     * @return {@code true} or {@code false}
     * @since 0.0.1
     * @see #isBlank(CharSequence)
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
     * @param css CharSequence array
     * @return {@code true} or {@code false}
     * @since 0.0.1
     * @see #isBlank(CharSequence)
     */
    public static boolean allBlank(CharSequence... css) {
        if (css == null) return true;
        if (css.length == 0) return true;
        return Arrays.stream(css).allMatch(G::isBlank);
    }

    /**
     * {@code true} if all chars in CharSequence are whitespace {@link Character#isWhitespace(char)}. <br>
     * 字符序列中的所有字符都是空白字符，则返回true。
     * @param cs CharSequence
     * @return {@code true} or {@code false}
     * @since 0.0.1
     */
    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
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

    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return map == null || map.isEmpty();
    }

    public static <T> boolean isEmpty(T[] arr) {
        return arr == null || arr.length == 0;
    }

    /**
     * Gets the first object that is not {@code null}. <br>
     * 获取第一个不为 {@code null} 的值
     * @param rs object array
     * @param <R> return type
     * @return first non {@code null} object
     */
    public static <R> R firstNonNull(R... rs) {
        if (isEmpty(rs)) return null;
        for (R r : rs) {
            if (r != null) return r;
        }
        return null;
    }

}
