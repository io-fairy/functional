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

import com.iofairy.tcf.Try;
import com.iofairy.time.DateTimes;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.*;
import java.time.temporal.Temporal;
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
    /**
     * Collection Class Name
     *
     * @since 0.2.3
     */
    private static class CollectionCN {
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
    }

    /**
     * Map Class Name
     *
     * @since 0.2.3
     */
    private static class MapCN {
        /*
         * Map
         */
        public final static String COLLECTIONS$SINGLETONMAP = "java.util.Collections$SingletonMap";
        public final static String TREEMAP$ASCENDINGSUBMAP = "java.util.TreeMap$AscendingSubMap";
        public final static String TREEMAP$DESCENDINGSUBMAP = "java.util.TreeMap$DescendingSubMap";
        // JDK 9+
        public final static String IMMUTABLECOLLECTIONS$MAP1 = "java.util.ImmutableCollections$Map1";
        public final static String IMMUTABLECOLLECTIONS$MAPN = "java.util.ImmutableCollections$MapN";
    }

    /**
     * String used to represent "not a number".
     */
    public static final String NAN = "NaN";
    /**
     * String used to represent infinity.
     */
    public static final String INFINITY = "∞";
    /**
     * Is the default language Chinese? <br>
     * 默认语言是否是中文（是否中文环境）？
     */
    public static final boolean IS_ZH_LANG = Try.tcf(() -> Locale.getDefault().getLanguage().equals("zh"), false, false);

    /*###################################################################################
     ************************************************************************************
     ------------------------------------------------------------------------------------
     ********************************   Global methods   ********************************
     *******************************        全局方法        ******************************
     ------------------------------------------------------------------------------------
     ************************************************************************************
     ###################################################################################*/

    /**
     * Whether object array contains {@code null} value or object array is {@code null}. <br>
     * Especially, when <b>length of Object array is 0</b> ({@code objects.length == 0}), return {@code false}. <br>
     * 数组中是否包含{@code null}值或数组本身就是{@code null}，则返回{@code true}。<br>
     * <b>特别地，当数组本身长度等于0时，返回{@code false}。</b>
     *
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
     * Especially, when <b>length of Object array is 0</b> ({@code objects.length == 0}), return {@code false}. <br>
     * 数组中所有的值都是{@code null}或数组本身就是{@code null}，则返回{@code true}。<br>
     * <b>特别地，当数组本身长度等于0时，也返回{@code false}。</b>
     *
     * @param objects object array
     * @return {@code true} or {@code false}
     * @since 0.0.1
     */
    public static boolean allNull(Object... objects) {
        if (objects == null) return true;
        if (objects.length == 0) return false;
        boolean allNull = true;
        for (Object obj : objects) {
            if (obj != null) {
                allNull = false;
                break;
            }
        }
        return allNull;
    }

    /**
     * Whether object Collection contains {@code null} value or object Collection is {@code null}. <br>
     * Especially, when <b>length of Object List is 0</b> ({@code objects.size() == 0}), return {@code false}. <br>
     * Collection中是否包含{@code null}值或Collection本身就是{@code null}，则返回{@code true}。<br>
     * <b>特别地，当集合本身长度等于0时，也返回{@code false}。</b>
     *
     * @param objects object Collection
     * @return {@code true} or {@code false}
     * @since 0.5.0
     */
    public static boolean hasNull(Collection<?> objects) {
        if (objects == null) return true;
        if (objects.isEmpty()) return false;
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
     * {@code true} if all Collection values are {@code null} or object Collection is {@code null}. <br>
     * Especially, when <b>length of Object List is 0</b> ({@code objects.size() == 0}), return {@code false}. <br>
     * Collection中所有的值都是{@code null}或Collection本身就是{@code null}，则返回{@code true}。<br>
     * <b>特别地，当集合本身长度等于0时，也返回{@code false}。</b>
     *
     * @param objects object Collection
     * @return {@code true} or {@code false}
     * @since 0.5.0
     */
    public static boolean allNull(Collection<?> objects) {
        if (objects == null) return true;
        if (objects.isEmpty()) return false;
        boolean allNull = true;
        for (Object obj : objects) {
            if (obj != null) {
                allNull = false;
                break;
            }
        }
        return allNull;
    }

    /**
     * Return {@code true} when object is {@code null}<br>
     *
     * @param o object
     * @return {@code true} or {@code false}
     * @since 0.5.2
     */
    public static boolean isNull(Object o) {
        return o == null;
    }

    /**
     * Return {@code true} when object is {@code null}, or object is a array and this array is empty. <br>
     * 如果 object 为{@code null}或object是一个数组且数组中没有一个元素，则返回{@code true}
     *
     * @param o object
     * @return {@code true} or {@code false}
     * @since 0.2.2
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object o) {
        if (o == null)                  return true;
        if (o instanceof CharSequence)  return ((CharSequence) o).length() == 0;
        if (o instanceof Map)           return ((Map) o).isEmpty();
        if (o instanceof Collection)    return ((Collection) o).isEmpty();
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
     * Return {@code true} when object is not {@code null}<br>
     *
     * @param o object
     * @return {@code true} or {@code false}
     * @since 0.5.2
     */
    public static boolean isNotNull(Object o) {
        return !isNull(o);
    }

    /**
     * Return {@code true} when object is not empty. <br>
     *
     * @param o object
     * @return {@code true} or {@code false}
     * @since 0.5.2
     */
    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }


    /**
     * Whether Object array contains {@code null} or empty or Object array is {@code null}. <br>
     * Especially, when <b>length of Object array is 0</b> ({@code objects.length == 0}), return {@code true}. <br>
     * 数组中包含{@code null}值或者空对象（如：字符串{@code ""}、空数组、空集合等等）或者Object数组本身就为{@code null}，则返回{@code true}。<br>
     * <b>特别地，当数组本身长度等于0时，也返回{@code true}。</b>
     *
     * @param objects Object array
     * @return {@code true} or {@code false}
     * @since 0.5.12
     */
    public static boolean hasEmpty(Object... objects) {
        if (objects == null) return true;
        if (objects.length == 0) return true;
        boolean hasEmpty = false;
        for (Object obj : objects) {
            if (isEmpty(obj)) {
                hasEmpty = true;
                break;
            }
        }
        return hasEmpty;
    }

    /**
     * {@code true} if all array values are {@code null} or empty {@code ""} value
     * or Object array is {@code null}.  <br>
     * Especially, when <b>length of Object array is 0</b> ({@code objects.length == 0}), return {@code true}. <br>
     * 数组中所有的值都是{@code null}或者空对象（如：字符串{@code ""}、空数组、空集合等等）或者Object数组本身就为{@code null}，则返回{@code true}。<br>
     * <b>特别地，当数组本身长度等于0时，也返回{@code true}。</b>
     *
     * @param objects Object array
     * @return {@code true} or {@code false}
     * @since 0.5.12
     */
    public static boolean allEmpty(Object... objects) {
        if (objects == null) return true;
        if (objects.length == 0) return true;
        return Arrays.stream(objects).allMatch(G::isEmpty);
    }


    /**
     * Whether Object List contains {@code null} or empty {@code ""}
     * or Object List is {@code null}. <br>
     * Especially, when <b>length of Object List is 0</b> ({@code objects.size() == 0}), return {@code true}. <br>
     * 列表中包含{@code null}值或者空对象（如：字符串{@code ""}、空数组、空集合等等）或者Object列表本身就为{@code null}，则返回{@code true}。<br>
     * <b>特别地，当列表本身长度等于0时，也返回{@code true}。</b>
     *
     * @param objects Object Collection
     * @return {@code true} or {@code false}
     * @since 0.5.12
     */
    public static boolean hasEmpty(Collection<?> objects) {
        if (objects == null) return true;
        if (objects.isEmpty()) return true;
        boolean hasEmpty = false;
        for (Object obj : objects) {
            if (isEmpty(obj)) {
                hasEmpty = true;
                break;
            }
        }
        return hasEmpty;
    }

    /**
     * {@code true} if all List values are {@code null} or empty {@code ""} value or Object List is {@code null}.  <br>
     * Especially, when <b>length of Object List is 0</b> ({@code objects.size() == 0}), return {@code true}. <br>
     * 列表中所有的值都是{@code null}或者空对象（如：字符串{@code ""}、空数组、空集合等等）或者Object列表本身就为{@code null}，则返回{@code true}。<br>
     * <b>特别地，当列表本身长度等于0时，也返回true。</b>
     *
     * @param objects Object Collection
     * @return {@code true} or {@code false}
     * @since 0.5.12
     */
    public static boolean allEmpty(Collection<?> objects) {
        if (objects == null) return true;
        if (objects.isEmpty()) return true;
        return objects.stream().allMatch(G::isEmpty);
    }



    /**
     * Gets the full exception stack trace from {@link Throwable} object. <br>
     * 从 {@link Throwable} 对象中获取完整的异常堆栈信息
     * @param e Throwable object
     * @return full exception stack trace
     * @since 0.0.8
     */
    public static String stackTrace(Throwable e) {
        if (e == null) return "null";
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        e.printStackTrace(pw);
        return sw.getBuffer().toString();
    }


    /**
     * Obtain a more concise exception stack trace by filtering through package names.<br>
     * 通过包名过滤，获取更简洁的异常堆栈信息
     *
     * @param e        Exception object
     * @param packages Package names to be filtered
     * @return Exception stack trace information
     * @since 0.5.0
     */
    public static String stackTraceSimple(Throwable e, String... packages) {
        String stackTrace = G.stackTrace(e);
        if (e == null || G.isEmpty(packages)) return stackTrace;

        String[] stackTraceList = stackTrace.split("\n");
        List<String> stackTraces = new ArrayList<>();
        for (String stack : stackTraceList) {
            if (!stack.startsWith("\t") || stack.contains("Caused by: ") || stack.contains("Suppressed: ")) {
                stackTraces.add(stack);
            } else {
                for (String pack : packages) {
                    if (pack != null && stack.contains(pack)) {
                        stackTraces.add(stack);
                        break;
                    }
                }
            }
        }

        return String.join("\n", stackTraces) + "\n";
    }

    /**
     * Obtain a more concise exception stack trace by filtering through package names.<br>
     * 通过包名过滤，获取更简洁的异常堆栈信息
     *
     * @param e        Exception object
     * @param packages Package names to be filtered
     * @return Exception stack trace information
     * @since 0.5.0
     */
    public static String stackTraceConcise(Throwable e, String... packages) {
        if (e == null || G.isEmpty(packages)) return G.stackTrace(e);

        List<Throwable> throwables = O.causeTrace(e);
        List<String> stackTraces = new ArrayList<>();

        for (int i = 0; i < throwables.size(); i++) {
            Throwable throwable = throwables.get(i);
            if (throwable == null) {
                stackTraces.add(i == 0 ? "null" : "Caused by: null");
                continue;
            }
            stackTraces.add(i == 0 ? throwable.toString() : "Caused by: " + throwable);

            StackTraceElement[] stackTraceElements = throwable.getStackTrace();
            for (StackTraceElement stackTraceElement : stackTraceElements) {
                if (stackTraceElement == null) continue;
                String traceElementString = stackTraceElement.toString();
                for (String pack : packages) {
                    if (pack != null && traceElementString.contains(pack)) {
                        stackTraces.add("\tat " + traceElementString);
                        break;
                    }
                }
            }
        }

        return String.join("\n", stackTraces) + "\n";
    }


    /**
     * Formatting DateTime use simple pattern (<b>{@code yyyy-MM-dd HH:mm:ss.SSS}</b>).
     *
     * @param date date
     * @return Time formatting String
     * @since 0.2.3
     */
    public static String dtSimple(Date date) {
        return DateTimes.dtSimple(date);
    }

    /**
     * Formatting DateTime use simple pattern (<b>{@code yyyy-MM-dd HH:mm:ss.SSS}</b>).
     *
     * @param calendar calendar
     * @return Time formatting String
     * @since 0.2.3
     */
    public static String dtSimple(Calendar calendar) {
        return DateTimes.dtSimple(calendar);
    }

    /**
     * Formatting DateTime (only {@link LocalDateTime}, {@link Instant}, {@link OffsetDateTime}, {@link ZonedDateTime})
     * use simple pattern (<b>{@code yyyy-MM-dd HH:mm:ss.SSS}</b>).
     *
     * @param temporal temporal
     * @return Time formatting String
     * @since 0.2.3
     */
    public static String dtSimple(Temporal temporal) {
        return DateTimes.dtSimple(temporal);
    }

    /**
     * Formatting DateTime use detailed pattern (<b>{@code yyyy-MM-dd HH:mm:ss.SSSSSSSSS '['VV xxx O E']'}</b>).
     *
     * @param date date
     * @return Time formatting String
     * @since 0.2.3
     */
    public static String dtDetail(Date date) {
        return DateTimes.dtDetail(date);
    }

    /**
     * Formatting DateTime use detailed pattern (<b>{@code yyyy-MM-dd HH:mm:ss.SSSSSSSSS '['VV xxx O E']'}</b>).
     *
     * @param calendar calendar
     * @return Time formatting String
     * @since 0.2.3
     */
    public static String dtDetail(Calendar calendar) {
        return DateTimes.dtDetail(calendar);
    }

    /**
     * Formatting DateTime (only {@link LocalDateTime}, {@link Instant}, {@link OffsetDateTime}, {@link ZonedDateTime})
     * use detailed pattern (<b>{@code yyyy-MM-dd HH:mm:ss.SSSSSSSSS '['VV xxx O E']'}</b>).
     *
     * @param temporal temporal
     * @return Time formatting String
     * @since 0.2.3
     */
    public static String dtDetail(Temporal temporal) {
        return DateTimes.dtDetail(temporal);
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
        if (o instanceof int[])             return Arrays.toString((int[]) o);
        if (o instanceof long[])            return Arrays.toString((long[]) o);
        if (o instanceof float[])           return toString((float[]) o);
        if (o instanceof double[])          return toString((double[]) o);
        if (o instanceof char[])            return toString((char[]) o);
        if (o instanceof byte[])            return Arrays.toString((byte[]) o);
        if (o instanceof short[])           return Arrays.toString((short[]) o);
        if (o instanceof boolean[])         return Arrays.toString((boolean[]) o);
        if (o instanceof Number)            return toString((Number) o);
        if (o instanceof Number[])          return toString((Number[]) o);
        if (o instanceof Object[])          return toString((Object[]) o);
        if (o instanceof Date)              return dtSimple((Date) o);
        if (o instanceof Calendar)          return dtSimple((Calendar) o);
        if (o instanceof Temporal)          return dtSimple((Temporal) o);
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
                || className.equals(CollectionCN.ARRAYS$ARRAYLIST)
                || className.equals(CollectionCN.ARRAYLIST$SUBLIST)
                || className.equals(CollectionCN.SUBLIST)
                || className.equals(CollectionCN.RANDOMACCESSSUBLIST)
                || className.equals(CollectionCN.ABSTRACTLIST$SUBLIST)
                || className.equals(CollectionCN.ABSTRACTLIST$RANDOMACCESSSUBLIST)
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
                || className.equals(MapCN.COLLECTIONS$SINGLETONMAP)
                || className.equals(MapCN.TREEMAP$ASCENDINGSUBMAP)
                || className.equals(MapCN.TREEMAP$DESCENDINGSUBMAP)
                || className.equals(MapCN.IMMUTABLECOLLECTIONS$MAP1)
                || className.equals(MapCN.IMMUTABLECOLLECTIONS$MAPN)
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
     * To string for {@code float[]}
     *
     * @param floats float array
     * @return string
     * @since 0.3.4
     */
    public static String toString(float[] floats) {
        return toString(floats, 6, RoundingMode.HALF_UP, true);
    }

    /**
     * To string for {@code double[]}
     *
     * @param doubles double array
     * @return string
     * @since 0.3.4
     */
    public static String toString(double[] doubles) {
        return toString(doubles, 6, RoundingMode.HALF_UP, true);
    }

    /**
     * To string for {@code Number[]}
     *
     * @param numbers Number array
     * @return string
     * @since 0.3.4
     */
    public static String toString(Number[] numbers) {
        return toString(numbers, 6, RoundingMode.HALF_UP, true);
    }

    /**
     * To string for {@code Number}
     *
     * @param number Number
     * @return string
     * @since 0.3.4
     */
    public static String toString(Number number) {
        return toString(number, 6, RoundingMode.HALF_UP, true);
    }

    /**
     * To string for {@code float[]}
     *
     * @param floats   float array
     * @param newScale newScale
     * @return string
     * @since 0.3.4
     */
    public static String toString(float[] floats, int newScale) {
        return toString(floats, newScale, RoundingMode.HALF_UP, true);
    }

    /**
     * To string for {@code double[]}
     *
     * @param doubles  double array
     * @param newScale newScale
     * @return string
     * @since 0.3.4
     */
    public static String toString(double[] doubles, int newScale) {
        return toString(doubles, newScale, RoundingMode.HALF_UP, true);
    }

    /**
     * To string for {@code Number[]}
     *
     * @param numbers  Number array
     * @param newScale newScale
     * @return string
     * @since 0.3.4
     */
    public static String toString(Number[] numbers, int newScale) {
        return toString(numbers, newScale, RoundingMode.HALF_UP, true);
    }

    /**
     * To string for {@code Number}
     *
     * @param number   Number
     * @param newScale newScale
     * @return string
     * @since 0.3.4
     */
    public static String toString(Number number, int newScale) {
        return toString(number, newScale, RoundingMode.HALF_UP, true);
    }

    /**
     * To string for {@code float[]}
     *
     * @param floats       float array
     * @param newScale     newScale
     * @param roundingMode roundingMode
     * @return string
     * @since 0.3.4
     */
    public static String toString(float[] floats, int newScale, RoundingMode roundingMode) {
        return toString(floats, newScale, roundingMode, true);
    }

    /**
     * To string for {@code double[]}
     *
     * @param doubles      double array
     * @param newScale     newScale
     * @param roundingMode roundingMode
     * @return string
     * @since 0.3.4
     */
    public static String toString(double[] doubles, int newScale, RoundingMode roundingMode) {
        return toString(doubles, newScale, roundingMode, true);
    }

    /**
     * To string for {@code Number[]}
     *
     * @param numbers      Number array
     * @param newScale     newScale
     * @param roundingMode roundingMode
     * @return string
     * @since 0.3.4
     */
    public static String toString(Number[] numbers, int newScale, RoundingMode roundingMode) {
        return toString(numbers, newScale, roundingMode, true);
    }

    /**
     * To string for {@code Number}
     *
     * @param number       Number
     * @param newScale     newScale
     * @param roundingMode roundingMode
     * @return string
     * @since 0.3.4
     */
    public static String toString(Number number, int newScale, RoundingMode roundingMode) {
        return toString(number, newScale, roundingMode, true);
    }

    /**
     * To string for {@code float[]}
     *
     * @param floats               float array
     * @param newScale             newScale
     * @param roundingMode         roundingMode
     * @param isStripTrailingZeros isStripTrailingZeros
     * @return string
     * @since 0.3.4
     */
    public static String toString(float[] floats, int newScale, RoundingMode roundingMode, boolean isStripTrailingZeros) {
        if (floats == null) return "null";
        int maxIndex = floats.length - 1;
        if (maxIndex == -1) return "[]";

        StringBuilder b = new StringBuilder("[");
        for (int i = 0; ; i++) {
            b.append(toString(floats[i], newScale, roundingMode, isStripTrailingZeros));
            if (i == maxIndex)
                return b.append("]").toString();
            b.append(", ");
        }
    }

    /**
     * To string for {@code double[]}
     *
     * @param doubles              double array
     * @param newScale             newScale
     * @param roundingMode         roundingMode
     * @param isStripTrailingZeros isStripTrailingZeros
     * @return string
     * @since 0.3.4
     */
    public static String toString(double[] doubles, int newScale, RoundingMode roundingMode, boolean isStripTrailingZeros) {
        if (doubles == null) return "null";
        int maxIndex = doubles.length - 1;
        if (maxIndex == -1) return "[]";

        StringBuilder b = new StringBuilder("[");
        for (int i = 0; ; i++) {
            b.append(toString(doubles[i], newScale, roundingMode, isStripTrailingZeros));
            if (i == maxIndex)
                return b.append("]").toString();
            b.append(", ");
        }
    }

    /**
     * To string for {@code Number[]}
     *
     * @param numbers              Number array
     * @param newScale             newScale
     * @param roundingMode         roundingMode
     * @param isStripTrailingZeros isStripTrailingZeros
     * @return string
     * @since 0.3.4
     */
    public static String toString(Number[] numbers, int newScale, RoundingMode roundingMode, boolean isStripTrailingZeros) {
        if (numbers == null) return "null";
        int maxIndex = numbers.length - 1;
        if (maxIndex == -1) return "[]";

        StringBuilder b = new StringBuilder("[");
        for (int i = 0; ; i++) {
            b.append(toString(numbers[i], newScale, roundingMode, isStripTrailingZeros));
            if (i == maxIndex)
                return b.append("]").toString();
            b.append(", ");
        }
    }

    /**
     * To string for {@code Number}
     *
     * @param number               Number
     * @param newScale             newScale
     * @param roundingMode         roundingMode
     * @param isStripTrailingZeros isStripTrailingZeros
     * @return string
     * @since 0.3.4
     */
    public static String toString(Number number, int newScale, RoundingMode roundingMode, boolean isStripTrailingZeros) {
        if (number == null) return "null";

        if (O.isInfinityOrNaN(number)) {
            double d = number.doubleValue();
            return Double.isNaN(d) ? NAN : (d == Double.POSITIVE_INFINITY ? INFINITY : "-" + INFINITY);
        }

        if (O.isFloat(number)) {
            String numberStr = toString(O.toBigDecimal(number), newScale, roundingMode, isStripTrailingZeros);
            return numberStr.contains(".") ? numberStr : numberStr + ".0";
        } else {
            return newScale < 0 ? toString(O.toBigDecimal(number), newScale, roundingMode, isStripTrailingZeros) : number.toString();
        }
    }

    /**
     * To string for {@link BigDecimal}
     *
     * @param bigDecimal           bigDecimal
     * @param newScale             newScale
     * @param roundingMode         roundingMode
     * @param isStripTrailingZeros isStripTrailingZeros
     * @return string
     * @since 0.3.4
     */
    public static String toString(BigDecimal bigDecimal, int newScale, RoundingMode roundingMode, boolean isStripTrailingZeros) {
        if (bigDecimal == null) return "null";
        if (roundingMode == null) roundingMode = RoundingMode.HALF_UP;
        BigDecimal bd = bigDecimal.setScale(newScale, roundingMode);
        return isStripTrailingZeros ? bd.stripTrailingZeros().toPlainString() : bd.toPlainString();
    }
}
