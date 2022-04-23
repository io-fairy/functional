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

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Global Variables And Methods for {@link Object} Operations. <br>
 * {@link Object} 相关操作的常用变量与函数。<br>
 * &gt;&gt;&gt;&gt;&gt;&gt; <br>
 * 采用简单类名 <b>O (Object / Other)</b> 模拟类似 Kotlin 的 <b>Top-level function</b>（顶级函数、全局函数）
 *
 * @since 0.3.0
 */
public class O {

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
     * @since 0.2.2
     */
    public static <T> T[] arrayO(Class<T> tClass) {
        @SuppressWarnings("unchecked")
        T[] ts = (T[]) Array.newInstance(tClass, 0);
        return ts;
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
     * Getting index of maximum value in {@code int[] or long[] or float[] or double[] or char[] or byte[] or short[]}
     *
     * @param arr {@code int[] or long[] or float[] or double[] or char[] or byte[] or short[]}
     * @return index of maximum value, {@code -1} if not found.
     * @since 0.3.0
     */
    public static int indexOfMax(Object arr) {
        if (G.isEmpty(arr)) return -1;
        if (arr instanceof int[]) {
            int[] is = (int[]) arr;
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
        if (arr instanceof long[]) {
            long[] ls = (long[]) arr;
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
        if (arr instanceof float[]) {
            float[] fs = (float[]) arr;
            int index = 0;
            float max = fs[0];
            for (int i = 1; i < fs.length; i++) {
                float iFloat = fs[i];
                if (iFloat > max) {
                    max = iFloat;
                    index = i;
                }
            }
            return index;
        }
        if (arr instanceof double[]) {
            double[] ds = (double[]) arr;
            int index = 0;
            double max = ds[0];
            for (int i = 1; i < ds.length; i++) {
                double iDouble = ds[i];
                if (iDouble > max) {
                    max = iDouble;
                    index = i;
                }
            }
            return index;
        }
        if (arr instanceof char[]) {
            char[] cs = (char[]) arr;
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
        if (arr instanceof byte[]) {
            byte[] bs = (byte[]) arr;
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
        if (arr instanceof short[]) {
            short[] ss = (short[]) arr;
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

        return -1;
    }

    /**
     * Getting index of minimum value in {@code int[] or long[] or float[] or double[] or char[] or byte[] or short[]}
     *
     * @param arr {@code int[] or long[] or float[] or double[] or char[] or byte[] or short[]}
     * @return index of minimum value, {@code -1} if not found.
     * @since 0.3.0
     */
    public static int indexOfMin(Object arr) {
        if (G.isEmpty(arr)) return -1;
        if (arr instanceof int[]) {
            int[] is = (int[]) arr;
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
        if (arr instanceof long[]) {
            long[] ls = (long[]) arr;
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
        if (arr instanceof float[]) {
            float[] fs = (float[]) arr;
            int index = 0;
            float min = fs[0];
            for (int i = 1; i < fs.length; i++) {
                float iFloat = fs[i];
                if (iFloat < min) {
                    min = iFloat;
                    index = i;
                }
            }
            return index;
        }
        if (arr instanceof double[]) {
            double[] ds = (double[]) arr;
            int index = 0;
            double min = ds[0];
            for (int i = 1; i < ds.length; i++) {
                double iDouble = ds[i];
                if (iDouble < min) {
                    min = iDouble;
                    index = i;
                }
            }
            return index;
        }
        if (arr instanceof char[]) {
            char[] cs = (char[]) arr;
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
        if (arr instanceof byte[]) {
            byte[] bs = (byte[]) arr;
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
        if (arr instanceof short[]) {
            short[] ss = (short[]) arr;
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

        return -1;
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
        BigDecimal bd1 = null;
        BigDecimal bd2 = null;
        if (n1 instanceof BigDecimal) {
            bd1 = (BigDecimal) n1;
        } else if (n1 instanceof BigInteger) {
            bd1 = new BigDecimal((BigInteger) n1);
        } else {
            bd1 = BigDecimal.valueOf(n1.doubleValue());
        }

        if (n2 instanceof BigDecimal) {
            bd2 = (BigDecimal) n2;
        } else if (n2 instanceof BigInteger) {
            bd2 = new BigDecimal((BigInteger) n2);
        } else {
            bd2 = BigDecimal.valueOf(n2.doubleValue());
        }
        return bd1.compareTo(bd2);
    }

    /**
     * Getting index of maximum value in {@link Number} array.
     *
     * @param ts {@link Number} array
     * @return index of maximum value, {@code -1} if not found.
     * @since 0.3.0
     */
    @SafeVarargs
    public static <T extends Number> int indexOfMax(T... ts) {
        if (G.isEmpty(ts)) return -1;
        int index = -1;
        Number max = null;
        for (int i = 0; i < ts.length; i++) {
            T t = ts[i];
            if (t != null) {
                if (max == null) {
                    max = t;
                    index = i;
                } else {
                    if (compare(t, max) == 1) {
                        max = t;
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
     * @param ts {@link Number} array
     * @return index of minimum value, {@code -1} if not found.
     * @since 0.3.0
     */
    @SafeVarargs
    public static <T extends Number> int indexOfMin(T... ts) {
        if (G.isEmpty(ts)) return -1;
        int index = -1;
        Number min = null;
        for (int i = 0; i < ts.length; i++) {
            T t = ts[i];
            if (t != null) {
                if (min == null) {
                    min = t;
                    index = i;
                } else {
                    if (compare(t, min) == -1) {
                        min = t;
                        index = i;
                    }
                }
            }
        }
        return index;
    }

}
