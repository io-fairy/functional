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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.*;
import java.time.format.DateTimeFormatter;
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
     * DateTime Formatters
     *
     * @since 0.2.3
     */
    private static class DTFormatters {
        public final static ZoneId DEFAULT_ZONE = Try.tcf(() -> ZoneId.systemDefault(), false);
        /*############################################
         ************* DateTime Formatter ************
         ############################################*/
        public final static DateTimeFormatter SIMPLE_DTF          = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        public final static DateTimeFormatter CONCISE_DTF         = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS '['VV xxx']'");
        public final static DateTimeFormatter CONCISE_OFFSET_DTF  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS '['xxx']'");
        public final static DateTimeFormatter DETAILED_DTF        = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSSSS '['VV xxx O E']'");
        public final static DateTimeFormatter DETAILED_OFFSET_DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSSSS '['xxx O E']'");
    }

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
     * Formatting DateTime use simple pattern (<b>{@code yyyy-MM-dd HH:mm:ss.SSS}</b>).
     *
     * @param date date
     * @return Time formatting String
     * @since 0.2.3
     */
    public static String dtSimple(Date date) {
        if (date == null) return "null";
        return dtSimple(date.toInstant());
    }

    /**
     * Formatting DateTime use simple pattern (<b>{@code yyyy-MM-dd HH:mm:ss.SSS}</b>).
     *
     * @param calendar calendar
     * @return Time formatting String
     * @since 0.2.3
     */
    public static String dtSimple(Calendar calendar) {
        if (calendar == null) return "null";
        return dtSimple(ZonedDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId()));
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
        if (temporal == null) return "null";

        if (temporal instanceof OffsetDateTime) {
            Integer defaultOffsetSeconds = Try.tcf(() -> OffsetDateTime.now().getOffset().getTotalSeconds(), false);
            OffsetDateTime offsetDT = (OffsetDateTime) temporal;
            int totalSeconds = offsetDT.getOffset().getTotalSeconds();
            return Objects.equals(totalSeconds, defaultOffsetSeconds)
                    ? offsetDT.format(DTFormatters.SIMPLE_DTF)
                    : offsetDT.format(DTFormatters.CONCISE_OFFSET_DTF);
        }

        ZonedDateTime zonedDT = temporalToZonedDT(temporal);
        if (zonedDT != null) {
            return Objects.equals(zonedDT.getZone(), DTFormatters.DEFAULT_ZONE)
                    ? zonedDT.format(DTFormatters.SIMPLE_DTF)
                    : zonedDT.format(DTFormatters.CONCISE_DTF);
        }

        return temporal.toString();
    }

    /**
     * Formatting DateTime use detailed pattern (<b>{@code yyyy-MM-dd HH:mm:ss.SSSSSSSSS '['VV xxx O E']'}</b>).
     *
     * @param date date
     * @return Time formatting String
     * @since 0.2.3
     */
    public static String dtDetail(Date date) {
        if (date == null) return "null";
        return dtDetail(date.toInstant());
    }

    /**
     * Formatting DateTime use detailed pattern (<b>{@code yyyy-MM-dd HH:mm:ss.SSSSSSSSS '['VV xxx O E']'}</b>).
     *
     * @param calendar calendar
     * @return Time formatting String
     * @since 0.2.3
     */
    public static String dtDetail(Calendar calendar) {
        if (calendar == null) return "null";
        return dtDetail(ZonedDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId()));
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
        if (temporal == null) return "null";
        ZonedDateTime zonedDT = temporalToZonedDT(temporal);
        return temporal instanceof OffsetDateTime
                ? ((OffsetDateTime) temporal).format(DTFormatters.DETAILED_OFFSET_DTF)
                : (zonedDT != null ? zonedDT.format(DTFormatters.DETAILED_DTF) : temporal.toString());
    }

    /**
     * Convert temporal to ZonedDateTime if temporal <b>{@code instanceof}</b> LocalDateTime or
     * <b>{@code instanceof}</b> Instant or <b>{@code instanceof}</b> ZonedDateTime, otherwise,
     * return <b>{@code null}</b>.
     *
     * @param temporal temporal
     * @return ZonedDateTime or null
     * @since 0.2.3
     */
    private static ZonedDateTime temporalToZonedDT(Temporal temporal) {
        return temporal instanceof LocalDateTime
                ? ZonedDateTime.of((LocalDateTime) temporal, DTFormatters.DEFAULT_ZONE)
                : (temporal instanceof Instant
                ? ZonedDateTime.ofInstant((Instant) temporal, DTFormatters.DEFAULT_ZONE)
                : (temporal instanceof ZonedDateTime ? (ZonedDateTime) temporal : null));
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

        if (O.isInfinityOrNaN(number)) return number.toString();

        if (O.isDouble(number) || number instanceof Float || number instanceof BigDecimal) {
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
        BigDecimal bd = bigDecimal.setScale(newScale, roundingMode);
        return isStripTrailingZeros ? bd.stripTrailingZeros().toPlainString() : bd.toPlainString();
    }
}
