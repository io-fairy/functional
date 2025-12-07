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
import com.iofairy.tuple.Tuple;
import com.iofairy.tuple.Tuple2;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.*;

import static com.iofairy.validator.Preconditions.*;

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
     * @param rs  list of elements to check
     * @param <R> return type
     * @return first not {@code null} object
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
     * Gets the first not {@code null} object and index {@code (object, index)}. <br>
     * 获取第一个不为 {@code null} 的值及序号{@code (对象, 序号)}
     *
     * @param rs  list of elements to check
     * @param <R> return type
     * @return first not {@code null} object and index {@code (object, index)}
     * @since 0.6.0
     */
    @SafeVarargs
    public static <R> Tuple2<R, Integer> firstNonNullWithIndex(R... rs) {
        if (G.isEmpty(rs)) return null;
        for (int i = 0; i < rs.length; i++) {
            R r = rs[i];
            if (r != null) return Tuple.of(r, i);
        }
        return null;
    }

    /**
     * Gets the first CharSequence that is not <b>empty</b>. <br>
     * 获取第一个不为<b>空</b>的值
     *
     * @param rs  list of elements to check
     * @param <R> return type
     * @return first not <b>empty</b> CharSequence
     * @since 0.6.1
     */
    @SafeVarargs
    public static <R extends CharSequence> R firstNonEmpty(R... rs) {
        if (G.isEmpty(rs)) return null;
        for (R r : rs) {
            if (S.isNotEmpty(r)) return r;
        }
        return null;
    }

    /**
     * Gets the first not <b>empty</b> CharSequence and index {@code (CharSequence, index)}. <br>
     * 获取第一个不为 <b>空</b> 的值及序号{@code (字符串, 序号)}
     *
     * @param rs  list of elements to check
     * @param <R> return type
     * @return first not <b>empty</b> CharSequence and index {@code (CharSequence, index)}
     * @since 0.6.1
     */
    @SafeVarargs
    public static <R extends CharSequence> Tuple2<R, Integer> firstNonEmptyWithIndex(R... rs) {
        if (G.isEmpty(rs)) return null;
        for (int i = 0; i < rs.length; i++) {
            R r = rs[i];
            if (S.isNotEmpty(r)) return Tuple.of(r, i);
        }
        return null;
    }

    /**
     * Gets the first CharSequence that is not <b>blank</b>. <br>
     * 获取第一个不为 <b>空白</b> 的值
     *
     * @param rs  list of elements to check
     * @param <R> return type
     * @return first not <b>blank</b> CharSequence
     * @since 0.6.1
     */
    @SafeVarargs
    public static <R extends CharSequence> R firstNonBlank(R... rs) {
        if (G.isEmpty(rs)) return null;
        for (R r : rs) {
            if (S.isNotBlank(r)) return r;
        }
        return null;
    }

    /**
     * Gets the first not <b>blank</b> CharSequence and index {@code (CharSequence, index)}. <br>
     * 获取第一个不为 <b>空白</b> 的值及序号{@code (字符串, 序号)}
     *
     * @param rs  list of elements to check
     * @param <R> return type
     * @return first not <b>blank</b> CharSequence and index {@code (CharSequence, index)}
     * @since 0.6.1
     */
    @SafeVarargs
    public static <R extends CharSequence> Tuple2<R, Integer> firstNonBlankWithIndex(R... rs) {
        if (G.isEmpty(rs)) return null;
        for (int i = 0; i < rs.length; i++) {
            R r = rs[i];
            if (S.isNotBlank(r)) return Tuple.of(r, i);
        }
        return null;
    }

    /**
     * Gets the first not <b>excludeValue</b> object. <br>
     * 获取第一个不为 <b>excludeValue</b> 的值
     *
     * @param excludeValue exclude value
     * @param rs           list of elements to check
     * @param <R>          return type
     * @return first not <b>excludeValue</b> object
     * @since 0.6.0
     */
    @SafeVarargs
    public static <R> R firstNonValue(R excludeValue, R... rs) {
        if (G.isEmpty(rs)) return null;
        for (R r : rs) {
            if (notEqual(excludeValue, r)) return r;
        }
        return null;
    }


    /**
     * Gets the first not <b>excludeValue</b> object and index {@code (object, index)}. <br>
     * 获取第一个不为 <b>excludeValue</b> 的值及序号{@code (对象, 序号)}
     *
     * @param excludeValue exclude value
     * @param rs           list of elements to check
     * @param <R>          return type
     * @return first not <b>excludeValue</b> object and index {@code (object, index)}
     * @since 0.6.0
     */
    @SafeVarargs
    public static <R> Tuple2<R, Integer> firstNonValueWithIndex(R excludeValue, R... rs) {
        if (G.isEmpty(rs)) return null;
        for (int i = 0; i < rs.length; i++) {
            R r = rs[i];
            if (notEqual(excludeValue, r)) return Tuple.of(r, i);
        }
        return null;
    }

    /**
     * Gets the first not in <b>excludeValues</b> object. <br>
     * 获取第一个不在 <b>excludeValues</b> 中的值
     *
     * @param excludeValues exclude values
     * @param rs            list of elements to check
     * @param <R>           return type
     * @return first not in <b>excludeValues</b> object
     * @since 0.6.0
     */
    @SafeVarargs
    public static <R> R firstNotInValues(R[] excludeValues, R... rs) {
        if (G.isEmpty(rs)) return null;
        if (G.isEmpty(excludeValues)) return rs[0];
        for (R r : rs) {
            if (!Arrays.asList(excludeValues).contains(r)) return r;
        }
        return null;
    }


    /**
     * Gets the first not in <b>excludeValues</b> object and index {@code (object, index)}. <br>
     * 获取第一个不在 <b>excludeValues</b> 中的值及序号{@code (对象, 序号)}
     *
     * @param excludeValues exclude values
     * @param rs            list of elements to check
     * @param <R>           return type
     * @return first not in <b>excludeValues</b> object and index {@code (object, index)}
     * @since 0.6.0
     */
    @SafeVarargs
    public static <R> Tuple2<R, Integer> firstNotInValuesWithIndex(R[] excludeValues, R... rs) {
        if (G.isEmpty(rs)) return null;
        if (G.isEmpty(excludeValues)) return Tuple.of(rs[0], 0);
        for (int i = 0; i < rs.length; i++) {
            R r = rs[i];
            if (!Arrays.asList(excludeValues).contains(r)) return Tuple.of(r, i);
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
     * Convert dynamic arguments of type Object to an array of Objects.<br>
     *
     * @param ts  Variable arguments of type Object
     * @param <T> parameter type
     * @return An Object array
     * @since 0.6.0
     */
    @SafeVarargs
    public static <T> T[] args(T... ts) {
        return ts;
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
     * @see Objects#equals(Object, Object)
     * @since 0.5.5
     */
    public static boolean equals(Object a, Object b) {
        boolean equals = Objects.equals(a, b);
        if (!equals && a != null && b != null) {
            if (a instanceof Number && b instanceof Number) {
                String as = G.toString((Number) a, 50);
                String bs = G.toString((Number) b, 50);

                return as.equals(bs);
            }
        }
        return equals;
    }

    /**
     * Returns {@code true} if the arguments are not equal to each other and {@code false} otherwise.
     *
     * @param a an object
     * @param b an object to be compared with {@code a} for equality
     * @return {@code true} if the arguments are not equal to each other and {@code false} otherwise
     * @see #equals(Object, Object)
     * @since 0.5.10
     */
    public static boolean notEqual(Object a, Object b) {
        return !equals(a, b);
    }

    /**
     * Compares given {@code value} to a Object vararg of {@code searchValues},
     * returning {@code true} if the {@code value} is equal to any of the {@code searchValues}. <br><br>
     * <b>Examples:</b>
     * <blockquote><pre>{@code
     * O.equalsAny(null, null);                              // returns false;
     * O.equalsAny(null, (Object[]) null);                   // returns false;
     * O.equalsAny(null, (Object) null);                     // returns true;
     * O.equalsAny(null, null, null);                        // returns true;
     * O.equalsAny(null, new Object[]{});                    // returns false;
     * O.equalsAny(null, new Object[]{null});                // returns true;
     * O.equalsAny(new BigInteger("5669887456526599999"), 2, 3, 5669887456526599999L);   // returns true;
     * O.equalsAny(1, 2, 3, 1.0f);                           // returns false;
     * O.equalsAny("ab", 2, "", 1.0f);                       // returns false;
     * O.equalsAny("ab", 2, "ab", 1.0f);                     // returns true;
     * O.equalsAny("ab", 2, "aB", 1.0f);                     // returns false;
     * O.equalsAny(new BigDecimal("1.8960"), 2, 3, 1.896f);  // returns true;
     * }</pre></blockquote>
     *
     * @param value        the value to find a match for
     * @param searchValues the values to search for a match
     * @return {@code true} if the {@code value} is equal to any other element of {@code searchValues} and {@code false} otherwise
     * @since 0.5.11
     */
    public static boolean equalsAny(Object value, Object... searchValues) {
        if (G.isNotEmpty(searchValues)) {
            for (final Object searchValue : searchValues) {
                if (equals(value, searchValue)) {
                    return true;
                }
            }
        }
        return false;
    }


    /*###################################################################################
     ************************************************************************************
     ------------------------------------------------------------------------------------
     ***********************           Number division            ***********************
     ------------------------------------------------------------------------------------
     ************************************************************************************
     ###################################################################################*/

    /**
     * Division calculation function used to divide two numbers.
     *
     * @param dividend       The dividend, cannot be null.
     * @param divisor        The divisor, cannot be null.
     * @param scale          The scale for rounding the result.
     * @param roundingMode   The rounding mode. If null, default to {@link RoundingMode#HALF_UP}.
     * @param alwaysSetScale flag indicating whether to always set scale.
     *                       {@code true}, the scale will always be set. <b>Otherwise</b>,
     *                       the scale will only be set when <b>the division is not exact</b>.
     * @return Quotient.
     * @throws NullPointerException if the dividend or divisor is null.
     * @since 0.6.0
     */
    public static BigDecimal divide(Number dividend, Number divisor, int scale, RoundingMode roundingMode, boolean alwaysSetScale) {
        checkHasNullNPE(args(dividend, divisor), args("dividend", "divisor"));

        if (roundingMode == null) roundingMode = RoundingMode.HALF_UP;
        BigDecimal bdDividend = O.toBigDecimal(dividend);
        BigDecimal bdDivisor = O.toBigDecimal(divisor);

        if (alwaysSetScale) {
            return bdDividend.divide(bdDivisor, scale, roundingMode);
        } else {
            try {
                return bdDividend.divide(bdDivisor);
            } catch (ArithmeticException e) {
                if (e.getMessage() != null && e.getMessage().contains("Non-terminating decimal expansion")) {
                    return bdDividend.divide(bdDivisor, scale, roundingMode);
                }
                throw e;
            }
        }
    }

    /**
     * Division calculation function used to divide two numbers.
     *
     * @param dividend       The dividend, cannot be null.
     * @param divisor        The divisor, cannot be null.
     * @param scale          The scale for rounding the result.
     * @param alwaysSetScale flag indicating whether to always set scale.
     *                       {@code true}, the scale will always be set. <b>Otherwise</b>,
     *                       the scale will only be set when <b>the division is not exact</b>.
     * @return Quotient.
     * @throws NullPointerException if the dividend or divisor is null.
     * @since 0.6.0
     */
    public static BigDecimal divide(Number dividend, Number divisor, int scale, boolean alwaysSetScale) {
        return divide(dividend, divisor, scale, RoundingMode.HALF_UP, alwaysSetScale);
    }

    /**
     * Division calculation function used to divide two numbers.
     *
     * @param dividend       The dividend, cannot be null.
     * @param divisor        The divisor, cannot be null.
     * @param alwaysSetScale flag indicating whether to always set scale.
     *                       {@code true}, the scale will always be set. <b>Otherwise</b>,
     *                       the scale will only be set when <b>the division is not exact</b>.
     * @return Quotient.
     * @throws NullPointerException if the dividend or divisor is null.
     * @since 0.6.0
     */
    public static BigDecimal divide(Number dividend, Number divisor, boolean alwaysSetScale) {
        return divide(dividend, divisor, 6, RoundingMode.HALF_UP, alwaysSetScale);
    }

    /**
     * Division calculation function used to divide two numbers.
     *
     * @param dividend     The dividend, cannot be null.
     * @param divisor      The divisor, cannot be null.
     * @param scale        The scale for rounding the result.
     * @param roundingMode The rounding mode. If null, default to {@link RoundingMode#HALF_UP}.
     * @return Quotient.
     * @throws NullPointerException if the dividend or divisor is null.
     * @since 0.6.0
     */
    public static BigDecimal divide(Number dividend, Number divisor, int scale, RoundingMode roundingMode) {
        return divide(dividend, divisor, scale, roundingMode, true);
    }

    /**
     * Division calculation function used to divide two numbers.
     *
     * @param dividend The dividend, cannot be null.
     * @param divisor  The divisor, cannot be null.
     * @param scale    The scale for rounding the result.
     * @return Quotient.
     * @throws NullPointerException if the dividend or divisor is null.
     * @since 0.6.0
     */
    public static BigDecimal divide(Number dividend, Number divisor, int scale) {
        return divide(dividend, divisor, scale, RoundingMode.HALF_UP);
    }

    /**
     * Division calculation function used to divide two numbers.
     *
     * @param dividend The dividend, cannot be null.
     * @param divisor  The divisor, cannot be null.
     * @return Quotient.
     * @throws NullPointerException if the dividend or divisor is null.
     * @since 0.6.0
     */
    public static BigDecimal divide(Number dividend, Number divisor) {
        return divide(dividend, divisor, 6, RoundingMode.HALF_UP);
    }


    /**
     * Division calculation function used to divide two numbers.
     *
     * @param dividend       The dividend, cannot be null.
     * @param divisor        The divisor, cannot be null.
     * @param scale          The scale for rounding the result.
     * @param roundingMode   The rounding mode. If null, default to {@link RoundingMode#HALF_UP}.
     * @param alwaysSetScale flag indicating whether to always set scale.
     *                       {@code true}, the scale will always be set. <b>Otherwise</b>,
     *                       the scale will only be set when <b>the division is not exact</b>.
     * @return Quotient.
     * @throws NullPointerException if the dividend or divisor is null.
     * @since 0.6.0
     */
    public static Number dividePower(Number dividend, Number divisor, int scale, RoundingMode roundingMode, boolean alwaysSetScale) {
        checkHasNullNPE(args(dividend, divisor), args("dividend", "divisor"));

        if (roundingMode == null) roundingMode = RoundingMode.HALF_UP;

        /*
         都是整型
         */
        if (O.isInteger(dividend) && O.isInteger(divisor)) {
            return new BigInteger(dividend.toString()).divide(new BigInteger(divisor.toString()));
        }
        /*
         存在一个是 BigInteger 或 BigDecimal
         */
        if (dividend instanceof BigInteger || divisor instanceof BigInteger || dividend instanceof BigDecimal || divisor instanceof BigDecimal) {
            return divide(dividend, divisor, scale, roundingMode, alwaysSetScale);
        }
        /*
         至少有一个是 浮点型
         */
        double quotient = dividend.doubleValue() / divisor.doubleValue();
        if (Double.isNaN(quotient) || Double.isInfinite(quotient)) { // 如果是 NaN 或无穷大，则直接返回
            return quotient;
        } else {
            return new BigDecimal(quotient).setScale(scale, roundingMode).doubleValue();
        }
    }

    /**
     * Division calculation function used to divide two numbers.
     *
     * @param dividend       The dividend, cannot be null.
     * @param divisor        The divisor, cannot be null.
     * @param scale          The scale for rounding the result.
     * @param alwaysSetScale flag indicating whether to always set scale.
     *                       {@code true}, the scale will always be set. <b>Otherwise</b>,
     *                       the scale will only be set when <b>the division is not exact</b>.
     * @return Quotient.
     * @throws NullPointerException if the dividend or divisor is null.
     * @since 0.6.0
     */
    public static Number dividePower(Number dividend, Number divisor, int scale, boolean alwaysSetScale) {
        return dividePower(dividend, divisor, scale, RoundingMode.HALF_UP, alwaysSetScale);
    }

    /**
     * Division calculation function used to divide two numbers.
     *
     * @param dividend       The dividend, cannot be null.
     * @param divisor        The divisor, cannot be null.
     * @param alwaysSetScale flag indicating whether to always set scale.
     *                       {@code true}, the scale will always be set. <b>Otherwise</b>,
     *                       the scale will only be set when <b>the division is not exact</b>.
     * @return Quotient.
     * @throws NullPointerException if the dividend or divisor is null.
     * @since 0.6.0
     */
    public static Number dividePower(Number dividend, Number divisor, boolean alwaysSetScale) {
        return dividePower(dividend, divisor, 6, RoundingMode.HALF_UP, alwaysSetScale);
    }

    /**
     * Division calculation function used to divide two numbers.
     *
     * @param dividend     The dividend, cannot be null.
     * @param divisor      The divisor, cannot be null.
     * @param scale        The scale for rounding the result.
     * @param roundingMode The rounding mode. If null, default to {@link RoundingMode#HALF_UP}.
     * @return Quotient.
     * @throws NullPointerException if the dividend or divisor is null.
     * @since 0.6.0
     */
    public static Number dividePower(Number dividend, Number divisor, int scale, RoundingMode roundingMode) {
        return dividePower(dividend, divisor, scale, roundingMode, true);
    }

    /**
     * Division calculation function used to divide two numbers.
     *
     * @param dividend The dividend, cannot be null.
     * @param divisor  The divisor, cannot be null.
     * @param scale    The scale for rounding the result.
     * @return Quotient.
     * @throws NullPointerException if the dividend or divisor is null.
     * @since 0.6.0
     */
    public static Number dividePower(Number dividend, Number divisor, int scale) {
        return dividePower(dividend, divisor, scale, RoundingMode.HALF_UP);
    }

    /**
     * Division calculation function used to divide two numbers.
     *
     * @param dividend The dividend, cannot be null.
     * @param divisor  The divisor, cannot be null.
     * @return Quotient.
     * @throws NullPointerException if the dividend or divisor is null.
     * @since 0.6.0
     */
    public static Number dividePower(Number dividend, Number divisor) {
        return dividePower(dividend, divisor, 6, RoundingMode.HALF_UP);
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
     * Returns {@code true} if this {@code Number} value is {@link Double} or {@link DoubleAdder} or {@link DoubleAccumulator} or {@link BigDecimal}, {@code false} otherwise.
     *
     * @param number number
     * @return Returns {@code true} if this {@code Number} value is {@link Double} or {@link DoubleAdder} or {@link DoubleAccumulator} or {@link BigDecimal}, {@code false} otherwise.
     * @since 0.3.4
     */
    public static boolean isDouble(Number number) {
        return number instanceof Double
                || number instanceof DoubleAdder
                || number instanceof DoubleAccumulator
                || number instanceof BigDecimal;
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
     * or {@link LongAccumulator} or {@link BigInteger}, {@code false} otherwise.
     *
     * @param number number
     * @return Returns {@code true} if this {@code Number} value is {@link Long} or {@link AtomicLong} or {@link LongAdder}
     * or {@link LongAccumulator} or {@link BigInteger}, {@code false} otherwise.
     * @since 0.3.6
     */
    public static boolean isLong(Number number) {
        return number instanceof Long
                || number instanceof AtomicLong
                || number instanceof LongAdder
                || number instanceof LongAccumulator
                || number instanceof BigInteger;
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
     * Whether the number is an integer
     *
     * @param number number
     * @return true if the number is an integer, false otherwise
     * @since 0.5.11
     */
    public static boolean isAnInteger(Number number) {
        if (isInteger(number)) return true;
        if (number instanceof BigDecimal) return ((BigDecimal) number).remainder(BigDecimal.ONE).stripTrailingZeros().equals(BigDecimal.ZERO);
        return number.doubleValue() % 1 == 0;
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
