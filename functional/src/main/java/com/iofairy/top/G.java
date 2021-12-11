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
import java.lang.reflect.Array;
import java.util.*;

/**
 * Global Variables And Methods. <br>
 * 常用的变量与函数。<br>
 * &gt;&gt;&gt;&gt;&gt;&gt; <br>
 * 采用简单类名 <b>G (Global)</b> 模拟类似 Kotlin 的 <b>Top-level function</b>（顶级函数、全局函数）
 *
 * @since 0.0.1
 */
public final class G {
    /*
     * Collection
     */
    public final static String ARRAYS$ARRAYLIST = "java.util.Arrays$ArrayList";
    public final static String ARRAYLIST$SUBLIST = "java.util.ArrayList$SubList";
    public final static String SUBLIST = "java.util.SubList";
    public final static String RANDOMACCESSSUBLIST = "java.util.RandomAccessSubList";
    // JDK 9+
    public final static String ABSTRACTLIST$SUBLIST = "java.util.AbstractList$SubList";
    public final static String ABSTRACTLIST$RANDOMACCESSSUBLIST = "java.util.AbstractList$RandomAccessSubList";
    /*
     * Map
     */
    public final static String COLLECTIONS$SINGLETONMAP = "java.util.Collections$SingletonMap";
    public final static String TREEMAP$ASCENDINGSUBMAP = "java.util.TreeMap$AscendingSubMap";
    public final static String TREEMAP$DESCENDINGSUBMAP = "java.util.TreeMap$DescendingSubMap";
    // JDK 9+
    public final static String IMMUTABLECOLLECTIONS$MAP1 = "java.util.ImmutableCollections$Map1";
    public final static String IMMUTABLECOLLECTIONS$MAPN = "java.util.ImmutableCollections$MapN";


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

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * Return {@code true} when collection is {@code null} or collection is empty. <br>
     * 如果集合为{@code null}或集合中没有一个元素，则返回{@code true}
     * @param collection collection
     * @return {@code true} or {@code false}
     * @since 0.0.1
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * Return {@code true} when map is {@code null} or map is empty. <br>
     * 如果map为{@code null}或map中没有一个元素，则返回{@code true}
     * @param map map object
     * @return {@code true} or {@code false}
     * @since 0.0.1
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * Return {@code true} when object is {@code null}, or object is a array and this array is empty. <br>
     * 如果 object 为{@code null}或object是一个数组且数组中没有一个元素，则返回{@code true}
     * @param o object
     * @return {@code true} or {@code false}
     * @since 0.2.2
     */
    public static boolean isEmpty(Object o) {
        if (o == null)                  return true;
        if (o instanceof Object[])      return ((Object[]) o).length == 0;
        if (o instanceof int[])         return ((int[]) o).length == 0;
        if (o instanceof long[])        return ((long[]) o).length == 0;
        if (o instanceof float[])       return ((float[]) o).length == 0;
        if (o instanceof double[])      return ((double[]) o).length == 0;
        if (o instanceof char[])        return ((char[]) o).length == 0;
        if (o instanceof byte[])        return ((byte[]) o).length == 0;
        if (o instanceof short[])       return ((short[]) o).length == 0;
        if (o instanceof boolean[])     return ((boolean[]) o).length == 0;

        return false;
    }

    /**
     * Gets the first object that is not {@code null}. <br>
     * 获取第一个不为 {@code null} 的值
     * @param rs object array
     * @param <R> return type
     * @return first non {@code null} object
     * @since 0.0.7
     */
    @SafeVarargs
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

    /**
     * To string for any object <br>
     * <b>NOTE:</b><br>
     * When <b>{@code object instanceof Character}</b>, result will be enclosed in <b>single quotes({@code ''});</b><br>
     * When <b>{@code object instanceof CharSequence}</b>, result will be enclosed in <b>double quotes({@code ""}).</b><br>
     *
     * @param o object
     * @return string
     * @since 0.2.2
     */
    public static String toString(Object o) {
        // 如果 o == null，那么无论 o 是什么类型，`o instanceof Object` 都为 false
        if (o == null)                      return "null";
        if (o instanceof Character)         return "'" + o + "'";
        if (o instanceof CharSequence)      return '"' + o.toString() + '"';
        if (o instanceof Map.Entry)         return toString((Map.Entry<?, ?>) o);
        if (o instanceof Collection)        return toString((Collection<?>) o);
        if (o instanceof Map)               return toString((Map<?, ?>) o);
        if (o instanceof Character[])       return toString((Character[]) o);
        if (o instanceof CharSequence[])    return toString((CharSequence[]) o);
        if (o instanceof Object[])          return toString((Object[]) o);
        if (o instanceof int[])             return Arrays.toString((int[]) o);
        if (o instanceof long[])            return Arrays.toString((long[]) o);
        if (o instanceof float[])           return Arrays.toString((float[]) o);
        if (o instanceof double[])          return Arrays.toString((double[]) o);
        if (o instanceof char[])            return toString((char[]) o);
        if (o instanceof byte[])            return Arrays.toString((byte[]) o);
        if (o instanceof short[])           return Arrays.toString((short[]) o);
        if (o instanceof boolean[])         return Arrays.toString((boolean[]) o);
        if (o instanceof Throwable)         return stackTrace((Throwable) o);

        return o.toString();
    }

    /**
     * To string for Object array
     *
     * @param os Object array
     * @return string
     * @since 0.2.2
     */
    public static String toString(Object[] os) {
        if (os == null) return "null";
        int maxIndex = os.length - 1;
        if (maxIndex == -1) return "[]";

        StringBuilder b = new StringBuilder("[");
        for (int i = 0; ; i++) {
            b.append(toString(os[i]));
            if (i == maxIndex)
                return b.append("]").toString();
            b.append(", ");
        }
    }

    /**
     * To string for char array
     *
     * @param cs char array
     * @return string
     * @since 0.2.2
     */
    public static String toString(char[] cs) {
        if (cs == null) return "null";
        int maxIndex = cs.length - 1;
        if (maxIndex == -1) return "[]";

        StringBuilder b = new StringBuilder("[");
        for (int i = 0; ; i++) {
            b.append("'").append(cs[i]).append("'");
            if (i == maxIndex)
                return b.append("]").toString();
            b.append(", ");
        }
    }

    /**
     * To string for Character array
     *
     * @param cs Character array
     * @return string
     * @since 0.2.2
     */
    public static String toString(Character[] cs) {
        if (cs == null) return "null";
        int maxIndex = cs.length - 1;
        if (maxIndex == -1) return "[]";

        StringBuilder b = new StringBuilder("[");
        for (int i = 0; ; i++) {
            Character c = cs[i];
            b.append(c == null ? c : "'" + c + "'");
            if (i == maxIndex)
                return b.append("]").toString();
            b.append(", ");
        }
    }

    /**
     * To string for CharSequence array
     *
     * @param ss CharSequence array
     * @return string
     * @since 0.2.2
     */
    public static String toString(CharSequence[] ss) {
        if (ss == null) return "null";
        int maxIndex = ss.length - 1;
        if (maxIndex == -1) return "[]";

        StringBuilder b = new StringBuilder("[");
        for (int i = 0; ; i++) {
            CharSequence s = ss[i];
            b.append(s == null ? s : '"' + s.toString() + '"');
            if (i == maxIndex)
                return b.append("]").toString();
            b.append(", ");
        }
    }

    /**
     * To string for Collection
     *
     * @param collection Collection
     * @return string
     * @since 0.2.2
     */
    public static String toString(Collection<?> collection) {
        if (collection == null) return "null";

        String className = collection.getClass().getName();

        if (collection instanceof ArrayList
                || collection instanceof LinkedList
                || collection instanceof HashSet
                || collection instanceof TreeSet
                || className.equals(ARRAYS$ARRAYLIST)
                || className.equals(ARRAYLIST$SUBLIST)
                || className.equals(SUBLIST)
                || className.equals(RANDOMACCESSSUBLIST)
                || className.equals(ABSTRACTLIST$SUBLIST)
                || className.equals(ABSTRACTLIST$RANDOMACCESSSUBLIST)
        ) {
            Iterator<?> it = collection.iterator();
            if (!it.hasNext()) return "[]";

            StringBuilder b = new StringBuilder("[");
            for (; ; ) {
                Object o = it.next();
                b.append(o == collection ? "(this Collection)" : toString(o));
                if (!it.hasNext())
                    return b.append(']').toString();
                b.append(", ");
            }
        }

        return collection.toString();
    }

    /**
     * To string for Entry
     *
     * @param entry Map.Entry
     * @return string
     * @since 0.2.2
     */
    public static String toString(Map.Entry<?, ?> entry) {
        if (entry == null) return "null";
        Object key = entry.getKey();
        Object value = entry.getValue();
        return (key == entry ? "(this Entry)" : toString(key)) + "=" + (value == entry ? "(this Entry)" : toString(value));
    }

    /**
     * To string for Map
     *
     * @param map Map
     * @return string
     * @since 0.2.2
     */
    public static String toString(Map<?, ?> map) {
        if (map == null) return "null";

        String className = map.getClass().getName();

        if (map instanceof HashMap
                || map instanceof WeakHashMap
                || map instanceof TreeMap
                || className.equals(COLLECTIONS$SINGLETONMAP)
                || className.equals(TREEMAP$ASCENDINGSUBMAP)
                || className.equals(TREEMAP$DESCENDINGSUBMAP)
                || className.equals(IMMUTABLECOLLECTIONS$MAP1)
                || className.equals(IMMUTABLECOLLECTIONS$MAPN)
        ) {
            Iterator<? extends Map.Entry<?, ?>> it = map.entrySet().iterator();
            if (!it.hasNext()) return "{}";

            StringBuilder b = new StringBuilder("{");
            for (; ; ) {
                Map.Entry<?, ?> entry = it.next();
                Object key = entry.getKey();
                Object value = entry.getValue();
                b.append(key == entry ? "(this Entry)" : (key == map ? "(this Map)" : toString(key)));
                b.append('=');
                b.append(value == entry ? "(this Entry)" : (value == map ? "(this Map)" : toString(value)));
                if (!it.hasNext())
                    return b.append('}').toString();
                b.append(", ");
            }
        }

        return map.toString();
    }

    /**
     * Creates a new <b>empty array</b> with the specified array class type. <br>
     * 根据传入的数组类型，创建一个该类型的<b>空数组</b>。
     *
     * @param tsClass array type
     * @param <T>     array type
     * @return empty array
     */
    public static <T> T[] array0(Class<T[]> tsClass) {
        @SuppressWarnings("unchecked")
        T[] ts = (T[]) Array.newInstance(tsClass.getComponentType(), 0);
        return ts;
    }

    /**
     * Creates a new <b>empty array</b> with the specified class type. <br>
     * 根据传入的类型，创建一个该类型的<b>空数组</b>。
     *
     * @param tClass array type
     * @param <T>    array type
     * @return empty array
     */
    public static <T> T[] arrayO(Class<T> tClass) {
        @SuppressWarnings("unchecked")
        T[] ts = (T[]) Array.newInstance(tClass, 0);
        return ts;
    }

}
