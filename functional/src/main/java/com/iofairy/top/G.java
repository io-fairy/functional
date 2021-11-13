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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

/**
 * Global Variables And Methods. <br>
 * 常用的变量与函数。<br>
 * &gt;&gt;&gt;&gt;&gt;&gt; <br>
 * 采用简单类名 <b>G (Global)</b> 模拟类似 Kotlin 的 <b>Top-level function</b>（顶级函数、全局函数）
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

    /**
     * Return {@code true} when collection is {@code null} or collection is empty. <br>
     * 如果集合为{@code null}或集合中没有一个元素，则返回{@code true}
     * @param collection collection
     * @param <T> type of elements in collection
     * @return {@code true} or {@code false}
     * @since 0.0.1
     */
    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * Return {@code true} when map is {@code null} or map is empty. <br>
     * 如果map为{@code null}或map中没有一个元素，则返回{@code true}
     * @param map map object
     * @param <K> type of key
     * @param <V> type of value
     * @return {@code true} or {@code false}
     * @since 0.0.1
     */
    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return map == null || map.isEmpty();
    }

    /**
     * Return {@code true} when array is {@code null} or array is empty. <br>
     * 如果数组为{@code null}或数组中没有一个元素，则返回{@code true}
     * @param arr array
     * @param <T> type of elements in array
     * @return {@code true} or {@code false}
     * @since 0.0.1
     */
    public static <T> boolean isEmpty(T[] arr) {
        return arr == null || arr.length == 0;
    }

    /**
     * Gets the first object that is not {@code null}. <br>
     * 获取第一个不为 {@code null} 的值
     * @param rs object array
     * @param <R> return type
     * @return first non {@code null} object
     * @since 0.0.7
     */
    public static <R> R firstNonNull(R... rs) {
        if (isEmpty(rs)) return null;
        for (R r : rs) {
            if (r != null) return r;
        }
        return null;
    }

    /**
     * Split by the place where the delimiter first appears, only split once. <br>
     * 在分隔符第一次出现的地方切分字符串，将一个字符串分隔成两个字符串
     * @param source will be split
     * @param delimiter delimiter
     * @return Tuple2&lt;String, String&gt; -- first substring and second substring
     * @since 0.2.0
     */
    public static Tuple2<String, String> splitOnce(String source, String delimiter) {
        if (G.hasEmpty(source, delimiter)) return Tuple.of(source, null);
        int index = source.indexOf(delimiter);
        if (index > -1) {
            return Tuple.of(source.substring(0, index), source.substring(index + delimiter.length()));
        }else {
            return Tuple.of(source, null);
        }
    }

    /**
     * Gets the full exception stack trace from {@link Throwable} object. <br>
     * 从 {@link Throwable} 对象中获取完整的异常堆栈信息
     * @param e Throwable object
     * @return full exception stack trace
     * @since 0.0.8
     */
    public static String stackTrace(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        e.printStackTrace(pw);
        return sw.getBuffer().toString();
    }

}
