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

import com.iofairy.except.UnexpectedTypeException;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.*;

/**
 * Global Variables And Methods for {@link Object} Operations. <br>
 * {@link Object} 相关操作的常用变量与函数。<br>
 * &gt;&gt;&gt;&gt;&gt;&gt; <br>
 * 采用简单类名 <b>O (Object / Other)</b> 模拟类似 Kotlin 的 <b>Top-level function</b>（顶级函数、全局函数）
 *
 * @since 0.3.0
 */
public final class O {

    /**
     * Creates a new <b>empty array</b> with the specified array class type. <br>
     * 根据传入的数组类型，创建一个该类型的<b>空数组</b>。
     *
     * @param tsClass array type
     * @param <T>     array type
     * @return empty array
     * @since 0.2.2
     */
    public static <T> T[] array0(Class<T[]> tsClass) {
        return arrayM(tsClass, 0);
    }

    /**
     * Creates a new <b>empty array</b> with the specified class type. <br>
     * 根据传入的类型，创建一个该类型的<b>空数组</b>。
     *
     * @param tClass array type
     * @param <T>    array type
     * @return empty array
     * @since 0.2.2
     */
    public static <T> T[] arrayO(Class<T> tClass) {
        return arrayN(tClass, 0);
    }

    /**
     * Creates a new <b>array</b> with the specified array class type and length. <br>
     *
     * @param tsClass array type
     * @param length  array length
     * @param <T>     array type
     * @return new array with length
     * @since 0.3.1
     */
    public static <T> T[] arrayM(Class<T[]> tsClass, int length) {
        @SuppressWarnings("unchecked")
        T[] ts = (T[]) Array.newInstance(tsClass.getComponentType(), length);
        return ts;
    }

    /**
     * Creates a new <b>array</b> with the specified class type and length. <br>
     *
     * @param tClass array type
     * @param length array length
     * @param <T>    array type
     * @return new array with length
     * @since 0.3.1
     */
    public static <T> T[] arrayN(Class<T> tClass, int length) {
        @SuppressWarnings("unchecked")
        T[] ts = (T[]) Array.newInstance(tClass, length);
        return ts;
    }

    /**
     * Creates a new <b>array</b> with the specified array class type and length,
     * and assigns the <b>t</b> object reference to each element of array. <br>
     *
     * @param tsClass array type
     * @param length  array length
     * @param t       t object
     * @param <T>     array type
     * @return new array with length and fill <b>t</b> elements
     * @since 0.3.1
     */
    public static <T> T[] arrayFillM(Class<T[]> tsClass, int length, T t) {
        T[] ts = arrayM(tsClass, length);
        Arrays.fill(ts, t);
        return ts;
    }

    /**
     * Creates a new <b>array</b> with the specified class type and length,
     * and assigns the <b>t</b> object reference to each element of array. <br>
     *
     * @param tClass array type
     * @param length array length
     * @param t      t object
     * @param <T>    array type
     * @return new array with length and fill <b>t</b> elements
     * @since 0.3.1
     */
    public static <T> T[] arrayFillN(Class<T> tClass, int length, T t) {
        T[] ts = arrayN(tClass, length);
        Arrays.fill(ts, t);
        return ts;
    }

    /**
     * Quietly throw Checked exceptions <b>(Checked exceptions -&gt; Unchecked Exceptions)</b>, so they don't have to be thrown explicitly. <br>
     * 静静地抛出受检查异常，使其不必显式抛出。
     *
     * @param e   Throwable
     * @param <E> Throwable type
     * @throws E Throwable
     * @since 0.5.4
     */
    @SuppressWarnings("unchecked")
    public static <E extends Throwable> void sneakyThrows(Throwable e) throws E {
        throw (E) e;
    }

    /**
     * Get {@link Throwable}'s all causes order by the shallow and deep <br>
     * 获取所有 {@link Throwable} 的 causes，由浅至深排序
     *
     * @param throwable Throwable
     * @return current {@code throwable} and throwable's all causes
     * @since 0.3.4
     */
    public static List<Throwable> causeTrace(Throwable throwable) {
        if (throwable == null) return null;

        List<Throwable> causes = new ArrayList<>();
        causes.add(throwable);
        Throwable cause = throwable;
        while ((cause = cause.getCause()) != null) {
            causes.add(cause);
        }
        return causes;
    }

    /**
     * Gets the first object that is not {@code null}. <br>
     * 获取第一个不为 {@code null} 的值
     *
     * @param rs  object array
     * @param <R> return type
     * @return first non {@code null} object
     * @since 0.0.7
     */
    @SafeVarargs
    public static <R> R firstNonNull(R... rs) {
        if (G.isEmpty(rs)) return null;
        for (R r : rs) {
            if (r != null) return r;
        }
        return null;
    }


    /**
     * Validate the key-value pair array when creating a map
     *
     * @param allowKeyIsNull       Whether null keys are allowed
     * @param whetherKeyIsString   Whether keys are of string type
     * @param whetherValueIsString Whether values are of string type
     * @param kvs                  Array of key-value pairs, in the form of {@code key1, value1, key2, value2, ...}
     * @since 0.4.19
     */
    public static void verifyMapKV(boolean allowKeyIsNull, boolean whetherKeyIsString, boolean whetherValueIsString, Object... kvs) {
        if (kvs != null) {
            if (kvs.length % 2 != 0) throw new RuntimeException("The parameters length must be even. ");

            for (int i = 0; i < kvs.length; i++) {
                Object o = kvs[i];
                if (i % 2 == 0) {       // Validate elements at even indexes (keys)
                    if (o == null && !allowKeyIsNull) {
                        throw new NullPointerException("Index: " + i + ". This parameter is a key, the key must be not null. ");
                    }

                    if (whetherKeyIsString && o != null && !(o instanceof String)) {
                        throw new UnexpectedTypeException("Index: ${i}. This parameter is a key, the key must be `java.lang.String` type. ", i);
                    }
                } else {                // Validate elements at odd indexes (values)
                    if (whetherValueIsString && o != null && !(o instanceof String)) {
                        throw new UnexpectedTypeException("Index: ${i}. This parameter is a value, the value must be `java.lang.String` type. ", i);
                    }
                }
            }
        }
    }

    /**
     * Convert dynamic arguments of type {@link Object} to an array of Objects.<br>
     *
     * @param objs Variable arguments of type {@link Object}
     * @return An {@link Object} array
     * @since 0.5.0
     */
    public static Object[] args(Object... objs) {
        return objs;
    }

    /**
     * Convert dynamic arguments of type {@link CharSequence} to an array of CharSequences.<br>
     *
     * @param cs Variable arguments of type {@link CharSequence}
     * @return An {@link CharSequence} array
     * @since 0.5.1
     */
    public static CharSequence[] args(CharSequence... cs) {
        return cs;
    }

    /**
     * Convert dynamic arguments of type {@link String} to an array of Strings.<br>
     *
     * @param ss Variable arguments of type {@link String}
     * @return An {@link String} array
     * @since 0.5.1
     */
    public static String[] args(String... ss) {
        return ss;
    }

    /**
     * Returns the provided default value if the specified value is null; otherwise, returns the original value.
     *
     * @param value        The original value
     * @param defaultValue The value to return if the original value is null
     * @param <T>          The generic type
     * @return The default value if it's null, otherwise, returns the original value.
     * @since 0.5.2
     */
    public static <T> T valueIfNull(T value, T defaultValue) {
        return value == null ? defaultValue : value;
    }

    /**
     * Returns the provided default value if the specified value is empty; otherwise, returns the original value.
     *
     * @param value        The original value
     * @param defaultValue The value to return if the original value is empty
     * @param <T>          The generic type
     * @return The default value if it's empty, otherwise, returns the original value.
     * @since 0.5.2
     */
    public static <T> T valueIfEmpty(T value, T defaultValue) {
        return G.isEmpty(value) ? defaultValue : value;
    }

    /**
     * Returns the provided default value if the specified value is blank; otherwise, returns the original value.
     *
     * @param value        The original value
     * @param defaultValue The value to return if the original value is blank
     * @param <T>          The generic type
     * @return The default value if it's blank, otherwise, returns the original value.
     * @since 0.5.2
     */
    public static <T extends CharSequence> T valueIfBlank(T value, T defaultValue) {
        return S.isBlank(value) ? defaultValue : value;
    }

    /**
     * Returns the default value if the condition is {@code true}; otherwise, returns the original value. Equivalent to <b>Ternary Operator</b>.
     *
     * @param condition    The boolean condition to check. If true, the default value will be returned.
     * @param value        The original value
     * @param defaultValue The value to return when the condition is {@code true}
     * @param <T>          The generic type
     * @return The default value if the condition is {@code true}, otherwise, returns the original value.
     * @since 0.5.2
     */
    public static <T> T valueIfElse(boolean condition, T value, T defaultValue) {
        return condition ? defaultValue : value;
    }

    /**
     * Returns {@code true} if the arguments are equal to each other and {@code false} otherwise.
     * Consequently, if both arguments are {@code null}, {@code true} is returned and if exactly one argument is {@code null},
     * {@code false} is returned.  Otherwise, equality is determined by using the {@link Object#equals equals} method of the first argument.<br>
     * In particular, if both a and b are of type number, they are converted to strings and then compared for equality
     *
     * @param a an object
     * @param b an object to be compared with {@code a} for equality
     * @return {@code true} if the arguments are equal to each other and {@code false} otherwise
     * @see Object#equals(Object)
     * @since 0.5.5
     */
    public static boolean equals(Object a, Object b) {
        boolean equals = Objects.equals(a, b);
        if (!equals && a != null && b != null) {
            if (a instanceof Number && b instanceof Number) {
                String as = G.toString((Number) a, 10000);
                String bs = G.toString((Number) b, 10000);

                return as.equals(bs);
            }
        }
        return equals;
    }

    /*###################################################################################
     ************************************************************************************
     ------------------------------------------------------------------------------------
     ***********************   index of maximum value in arrays   ***********************
     ------------------------------------------------------------------------------------
     ************************************************************************************
     ###################################################################################*/

    /**
     * Getting index of maximum value in {@code byte[]}
     *
     * @param bs {@code byte[]}
     * @return index of maximum value, {@code -1} if not found.
     * @since 0.3.4
     */
    public static int indexOfMax(byte[] bs) {
        if (G.isEmpty(bs)) return -1;
        int index = 0;
        byte max = bs[0];
        for (int i = 1; i < bs.length; i++) {
            byte iByte = bs[i];
            if (iByte > max) {
                max = iByte;
                index = i;
            }
        }
        return index;
    }

    /**
     * Getting index of maximum value in {@code short[]}
     *
     * @param ss {@code short[]}
     * @return index of maximum value, {@code -1} if not found.
     * @since 0.3.4
     */
    public static int indexOfMax(short[] ss) {
        if (G.isEmpty(ss)) return -1;
        int index = 0;
        short max = ss[0];
        for (int i = 1; i < ss.length; i++) {
            short iShort = ss[i];
            if (iShort > max) {
                max = iShort;
                index = i;
            }
        }
        return index;
    }

    /**
     * Getting index of maximum value in {@code char[]}
     *
     * @param cs {@code char[]}
     * @return index of maximum value, {@code -1} if not found.
     * @since 0.3.4
     */
    public static int indexOfMax(char[] cs) {
        if (G.isEmpty(cs)) return -1;
        int index = 0;
        char max = cs[0];
        for (int i = 1; i < cs.length; i++) {
            char iChar = cs[i];
            if (iChar > max) {
                max = iChar;
                index = i;
            }
        }
        return index;
    }

    /**
     * Getting index of maximum value in {@code int[]}
     *
     * @param is {@code int[]}
     * @return index of maximum value, {@code -1} if not found.
     * @since 0.3.4
     */
    public static int indexOfMax(int[] is) {
        if (G.isEmpty(is)) return -1;
        int index = 0;
        int max = is[0];
        for (int i = 1; i < is.length; i++) {
            int iInt = is[i];
            if (iInt > max) {
                max = iInt;
                index = i;
            }
        }
        return index;
    }

    /**
     * Getting index of maximum value in {@code long[]}
     *
     * @param ls {@code long[]}
     * @return index of maximum value, {@code -1} if not found.
     * @since 0.3.4
     */
    public static int indexOfMax(long[] ls) {
        if (G.isEmpty(ls)) return -1;
        int index = 0;
        long max = ls[0];
        for (int i = 1; i < ls.length; i++) {
            long iLong = ls[i];
            if (iLong > max) {
                max = iLong;
                index = i;
            }
        }
        return index;
    }

    /**
     * Getting index of maximum value in {@code float[]} ({@link Float#NaN} will be ignored)
     *
     * @param fs {@code float[]}
     * @return index of maximum value, {@code -1} if not found.
     * @since 0.3.4
     */
    public static int indexOfMax(float[] fs) {
        if (G.isEmpty(fs)) return -1;
        int index = -1;
        float max = Float.NaN;
        for (int i = 0; i < fs.length; i++) {
            float iFloat = fs[i];
            if (!Float.isNaN(iFloat)) {
                if (Float.isNaN(max)) {
                    max = iFloat;
                    index = i;
                } else {
                    if (iFloat > max) {
                        max = iFloat;
                        index = i;
                    }
                }
            }
        }
        return index;
    }

    /**
     * Getting index of maximum value in {@code double[]} ({@link Double#NaN} will be ignored)
     *
     * @param ds {@code double[]}
     * @return index of maximum value, {@code -1} if not found.
     * @since 0.3.4
     */
    public static int indexOfMax(double[] ds) {
        if (G.isEmpty(ds)) return -1;
        int index = -1;
        double max = Double.NaN;
        for (int i = 0; i < ds.length; i++) {
            double iDouble = ds[i];
            if (!Double.isNaN(iDouble)) {
                if (Double.isNaN(max)) {
                    max = iDouble;
                    index = i;
                } else {
                    if (iDouble > max) {
                        max = iDouble;
                        index = i;
                    }
                }
            }
        }
        return index;
    }

    /*###################################################################################
     ************************************************************************************
     ------------------------------------------------------------------------------------
     ***********************   index of minimum value in arrays   ***********************
     ------------------------------------------------------------------------------------
     ************************************************************************************
     ###################################################################################*/

    /**
     * Getting index of minimum value in {@code byte[]}
     *
     * @param bs {@code byte[]}
     * @return index of minimum value, {@code -1} if not found.
     * @since 0.3.4
     */
    public static int indexOfMin(byte[] bs) {
        if (G.isEmpty(bs)) return -1;
        int index = 0;
        byte min = bs[0];
        for (int i = 1; i < bs.length; i++) {
            byte iByte = bs[i];
            if (iByte < min) {
                min = iByte;
                index = i;
            }
        }
        return index;
    }

    /**
     * Getting index of minimum value in {@code short[]}
     *
     * @param ss {@code short[]}
     * @return index of minimum value, {@code -1} if not found.
     * @since 0.3.4
     */
    public static int indexOfMin(short[] ss) {
        if (G.isEmpty(ss)) return -1;
        int index = 0;
        short min = ss[0];
        for (int i = 1; i < ss.length; i++) {
            short iShort = ss[i];
            if (iShort < min) {
                min = iShort;
                index = i;
            }
        }
        return index;
    }

    /**
     * Getting index of minimum value in {@code char[]}
     *
     * @param cs {@code char[]}
     * @return index of minimum value, {@code -1} if not found.
     * @since 0.3.4
     */
    public static int indexOfMin(char[] cs) {
        if (G.isEmpty(cs)) return -1;
        int index = 0;
        char min = cs[0];
        for (int i = 1; i < cs.length; i++) {
            char iChar = cs[i];
            if (iChar < min) {
                min = iChar;
                index = i;
            }
        }
        return index;
    }

    /**
     * Getting index of minimum value in {@code int[]}
     *
     * @param is {@code int[]}
     * @return index of minimum value, {@code -1} if not found.
     * @since 0.3.4
     */
    public static int indexOfMin(int[] is) {
        if (G.isEmpty(is)) return -1;
        int index = 0;
        int min = is[0];
        for (int i = 1; i < is.length; i++) {
            int iInt = is[i];
            if (iInt < min) {
                min = iInt;
                index = i;
            }
        }
        return index;
    }

    /**
     * Getting index of minimum value in {@code long[]}
     *
     * @param ls {@code long[]}
     * @return index of minimum value, {@code -1} if not found.
     * @since 0.3.4
     */
    public static int indexOfMin(long[] ls) {
        if (G.isEmpty(ls)) return -1;
        int index = 0;
        long min = ls[0];
        for (int i = 1; i < ls.length; i++) {
            long iLong = ls[i];
            if (iLong < min) {
                min = iLong;
                index = i;
            }
        }
        return index;
    }

    /**
     * Getting index of minimum value in {@code float[]} ({@link Float#NaN} will be ignored)
     *
     * @param fs {@code float[]}
     * @return index of minimum value, {@code -1} if not found.
     * @since 0.3.4
     */
    public static int indexOfMin(float[] fs) {
        if (G.isEmpty(fs)) return -1;
        int index = -1;
        float min = Float.NaN;
        for (int i = 0; i < fs.length; i++) {
            float iFloat = fs[i];
            if (!Float.isNaN(iFloat)) {
                if (Float.isNaN(min)) {
                    min = iFloat;
                    index = i;
                } else {
                    if (iFloat < min) {
                        min = iFloat;
                        index = i;
                    }
                }
            }
        }
        return index;
    }

    /**
     * Getting index of minimum value in {@code double[]} ({@link Double#NaN} will be ignored)
     *
     * @param ds {@code double[]}
     * @return index of minimum value, {@code -1} if not found.
     * @since 0.3.4
     */
    public static int indexOfMin(double[] ds) {
        if (G.isEmpty(ds)) return -1;
        int index = -1;
        double min = Double.NaN;
        for (int i = 0; i < ds.length; i++) {
            double iDouble = ds[i];
            if (!Double.isNaN(iDouble)) {
                if (Double.isNaN(min)) {
                    min = iDouble;
                    index = i;
                } else {
                    if (iDouble < min) {
                        min = iDouble;
                        index = i;
                    }
                }
            }
        }
        return index;
    }

    /**
     * Returns {@code true} if this {@code Number} value is {@link Double} or {@link DoubleAdder} or {@link DoubleAccumulator}, {@code false} otherwise.
     *
     * @param number number
     * @return Returns {@code true} if this {@code Number} value is {@link Double} or {@link DoubleAdder} or {@link DoubleAccumulator}, {@code false} otherwise.
     * @since 0.3.4
     */
    public static boolean isDouble(Number number) {
        return number instanceof Double || number instanceof DoubleAdder || number instanceof DoubleAccumulator;
    }

    /**
     * Returns {@code true} if this {@code Number} value is {@link Float} or {@link #isDouble(Number)}, {@code false} otherwise.
     *
     * @param number number
     * @return Returns {@code true} if this {@code Number} value is {@link Float} or {@link #isDouble(Number)}, {@code false} otherwise.
     * @since 0.3.6
     */
    public static boolean isFloat(Number number) {
        return number instanceof Float || isDouble(number);
    }

    /**
     * Returns {@code true} if this {@code Number} value is {@link Long} or {@link AtomicLong} or {@link LongAdder}
     * or {@link LongAccumulator}, {@code false} otherwise.
     *
     * @param number number
     * @return Returns {@code true} if this {@code Number} value is {@link Long} or {@link AtomicLong} or {@link LongAdder}
     * or {@link LongAccumulator}, {@code false} otherwise.
     * @since 0.3.6
     */
    public static boolean isLong(Number number) {
        return number instanceof Long
                || number instanceof AtomicLong
                || number instanceof LongAdder
                || number instanceof LongAccumulator;
    }

    /**
     * Returns {@code true} if this {@code Number} value is {@link Byte} or {@link Short} or {@link Integer}
     * or {@link AtomicInteger} or {@link #isLong(Number)}, {@code false} otherwise.
     *
     * @param number number
     * @return Returns {@code true} if this {@code Number} value is {@link Byte} or {@link Short} or {@link Integer}
     * or {@link AtomicInteger} or {@link #isLong(Number)}, {@code false} otherwise.
     * @since 0.3.6
     */
    public static boolean isInteger(Number number) {
        return number instanceof Byte
                || number instanceof Short
                || number instanceof Integer
                || number instanceof AtomicInteger
                || isLong(number);
    }

    /**
     * Returns {@code true} if this {@code Number} value is
     * a {@code Not-a-Number (NaN)} or {@code Infinite}, {@code false} otherwise.
     *
     * @param number number
     * @return Returns {@code true} if this {@code Number} value is
     * a {@code Not-a-Number (NaN)} or {@code Infinite}, {@code false} otherwise.
     * @since 0.3.4
     */
    public static boolean isInfinityOrNaN(Number number) {
        if (number == null) return false;
        if (isFloat(number)) {
            double d = number.doubleValue();
            return Double.isNaN(d) || Double.isInfinite(d);
        }
        return false;
    }

    /**
     * Convert {@code Number} to {@code BigDecimal}
     *
     * @param number number
     * @return BigDecimal
     * @since 0.3.4
     */
    public static BigDecimal toBigDecimal(Number number) {
        if (number == null) return null;
        if (isInfinityOrNaN(number))
            throw new NumberFormatException("The `number` is NaN or Infinity, can't convert to BigDecimal");

        return number instanceof BigDecimal
                ? (BigDecimal) number
                : (isDouble(number) ? BigDecimal.valueOf(number.doubleValue()) : new BigDecimal(number.toString())); // float 使用字符串更准确
    }

    /**
     * Compares two Numbers
     *
     * @param n1 number1
     * @param n2 number2
     * @return <ul>
     * <li>{@code -2}: one of the two numbers is {@code null};
     * <li>{@code -1}: number1 less than number2;
     * <li>&nbsp;{@code 0}: number1 equal number2;
     * <li>&nbsp;{@code 1}: number1 greater than number2.
     * </ul>
     * @since 0.3.0
     */
    public static int compare(Number n1, Number n2) {
        if (G.hasNull(n1, n2)) return -2;

        if (n1 == n2) return 0;
        boolean infinityOrNaN1 = isInfinityOrNaN(n1);
        boolean infinityOrNaN2 = isInfinityOrNaN(n2);
        if (infinityOrNaN1 || infinityOrNaN2) {
            if (infinityOrNaN1) {
                double d1 = n1.doubleValue();
                if (infinityOrNaN2) {   // infinityOrNaN1 为 true 且 infinityOrNaN2 为 true
                    return Double.compare(d1, n2.doubleValue());
                } else {     // infinityOrNaN1 为 true，但 infinityOrNaN2 为 false
                    if (Double.isNaN(d1) || d1 == Double.POSITIVE_INFINITY) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            } else {     // infinityOrNaN1 为 false，但 infinityOrNaN2 为 true
                double d2 = n2.doubleValue();
                if (Double.isNaN(d2) || d2 == Double.POSITIVE_INFINITY) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }

        return toBigDecimal(n1).compareTo(toBigDecimal(n2));
    }

    /**
     * Getting index of maximum value in {@link Number} array.
     *
     * @param numbers {@link Number} array
     * @return index of maximum value, {@code -1} if not found.
     * @since 0.3.0
     */
    public static int indexOfMax(Number[] numbers) {
        if (G.isEmpty(numbers)) return -1;
        int index = -1;
        Number max = null;
        for (int i = 0; i < numbers.length; i++) {
            Number number = numbers[i];
            if (number != null) {
                if (max == null) {
                    max = number;
                    index = i;
                } else {
                    if (compare(number, max) == 1) {
                        max = number;
                        index = i;
                    }
                }
            }
        }
        return index;
    }

    /**
     * Getting index of minimum value in {@link Number} array.
     *
     * @param numbers {@link Number} array
     * @return index of minimum value, {@code -1} if not found.
     * @since 0.3.0
     */
    public static int indexOfMin(Number[] numbers) {
        if (G.isEmpty(numbers)) return -1;
        int index = -1;
        Number min = null;
        for (int i = 0; i < numbers.length; i++) {
            Number number = numbers[i];
            if (number != null) {
                if (min == null) {
                    min = number;
                    index = i;
                } else {
                    if (compare(number, min) == -1) {
                        min = number;
                        index = i;
                    }
                }
            }
        }
        return index;
    }

}
