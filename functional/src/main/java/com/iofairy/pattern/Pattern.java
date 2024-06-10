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
package com.iofairy.pattern;

import com.iofairy.base.None;
import com.iofairy.lambda.R1;
import com.iofairy.pattern.mapping.*;
import com.iofairy.pattern.type.*;

import java.util.Objects;

/**
 * Pattern Matching for Java
 *
 * @since 0.0.1
 */
public class Pattern {
    public static final PatternDefault DEFAULT = PatternDefault.DEFAULT;      // DEFAULT can match by value or boolean
    public static final PatternValue VALUE = PatternValue.VALUE;              // match by value
    public static final PatternType TYPE = PatternType.TYPE;                  // match by value type(Class)
    public static final PatternString STRING = PatternString.STRING;          // match by String value
    public static final PatternString IGNORECASE = PatternString.IGNORECASE;  // match by String value ignore case
    public static final PatternString CONTAIN = PatternString.CONTAIN;        // match by String value using String.contains
    public static final PatternString PREFIX = PatternString.PREFIX;          // match by String value using String.startsWith
    public static final PatternString SUFFIX = PatternString.SUFFIX;          // match by String value using String.endsWith
    public static final PatternString ICCONTAIN = PatternString.ICCONTAIN;    // ignore case for contain
    public static final PatternString ICPREFIX = PatternString.ICPREFIX;      // ignore case for prefix
    public static final PatternString ICSUFFIX = PatternString.ICSUFFIX;      // ignore case for suffix

    public static None NONE = None.NONE;
    /*
     * Value for null pattern
     */
    public static final PatternNull1 VALUE1 = PatternNull1.VALUE1;
    public static final PatternNull2 VALUE2 = PatternNull2.VALUE2;
    public static final PatternNull3 VALUE3 = PatternNull3.VALUE3;
    public static final PatternNull4 VALUE4 = PatternNull4.VALUE4;
    public static final PatternNull5 VALUE5 = PatternNull5.VALUE5;
    public static final PatternNull6 VALUE6 = PatternNull6.VALUE6;
    public static final PatternNull7 VALUE7 = PatternNull7.VALUE7;
    public static final PatternNull8 VALUE8 = PatternNull8.VALUE8;


    /**
     * {@code match} can instead of {@code switch} statement or {@code if} statement. <br>
     * 使用 match 来替代 switch 和 if 语句 <br>
     * <b>Examples:</b>
     * <blockquote><pre>{@code
     * String s = "5";
     * String result = match(s)
     *         .when("1", v -> v + v)
     *         .when("2", v -> v + "a")
     *         .when(in("3", "4", "5", "6"), v -> v + " - abcd")
     *         .orElse(v -> "no match");
     *
     * System.out.println("match result: " + result);
     * }</pre></blockquote>
     *
     * @param value value
     * @param <V>   value type
     * @return MixMatcherMapping
     * @since 0.0.1
     */
    public static <V> ValueMatcherMapping<V> match(V value) {
        return match(value, DEFAULT);
    }

    public static <V> ValueMatcherMapping<V> match(V value, PatternDefault patternDefault) {
        Objects.requireNonNull(patternDefault);
        return new ValueMatcherMapping<V>(value);
    }

    public static <V> ValueMatcherMapping<V> match(V value, PatternValue patternValue) {
        Objects.requireNonNull(patternValue);
        return new ValueMatcherMapping<V>(value);
    }

    /**
     * There is multiple {@code if} statements, but they're not related,
     * use {@code match()} without value. <br>
     * 当你有多条if语句，但是彼此并不相关时，可以使用不带value的match。<br>
     * <b>Examples:</b>
     * <blockquote><pre>{@code
     * int i = 10;
     * String s = "abc";
     * Object o = new Object();
     *
     * String res = match()
     *              .when(i == 5,           v -> "i == 5")
     *              .when(s.equals("abc"),  v -> "abc")
     *              .when(o == null,        v -> "object is null")
     *              .orElse(v -> null);
     * }</pre></blockquote>
     *
     * @return BooleanMatcherMapping
     * @see #match(None)
     * @since 0.0.1
     */
    public static BooleanMatcherMapping<None> match() {
        return match(NONE);
    }

    public static BooleanMatcherMapping<None> match(None value) {
        return new BooleanMatcherMapping<>(value);
    }

    /**
     * Use Type Matcher instead of {@code instanceof}. <br>
     * 此 {@code match()} 函数可替代 {@code instanceof} 类型检测与类型转换功能。<br>
     * <b>Examples:</b>
     * <blockquote><pre>{@code
     * Object o = Tuple.of("zs", 20);
     *
     * Integer result = match(o, TYPE)
     *         .when(Integer.class, v -> v + 10)
     *         .when(Tuple2.class,  v -> v.arity())
     *         .when(String.class,  v -> v.contains("abc") ? 20 : 30)
     *         .orElse(v -> 40);
     * }</pre></blockquote>
     * <b>It is equivalent to the code below: </b>
     * <blockquote><pre>{@code
     * Integer ifResult;
     * if (o instanceof Integer) {
     *     ifResult = (Integer) o + 10;
     * } else if (o instanceof Tuple2) {
     *     ifResult = ((Tuple2) o).arity();
     * } else if (o instanceof String) {
     *     ifResult = ((String) o).contains("abc") ? 20 : 30;
     * } else {
     *     ifResult = 40;
     * }
     * }</pre></blockquote>
     *
     * @param value       value
     * @param patternType {@link PatternType}
     * @param <V>         value type
     * @return TypeMatcherMapping
     * @since 0.0.1
     */
    public static <V> TypeMatcherMapping<V> match(V value, PatternType patternType) {
        Objects.requireNonNull(patternType);
        return new TypeMatcherMapping<>(value);
    }

    /**
     * Pattern matching for String. <br>
     * 为字符串提供强大的模式匹配。<br>
     * <b>Examples:</b>
     * <blockquote><pre>{@code
     * String str = "aBcdE123.$fGHIj";
     *
     * // ignore case match
     * String res1 = match(str, IGNORECASE)
     *         .when((String) null,     v -> "match null")
     *         .when("abcd",            v -> "match abcd")
     *         .when("abcde123.$fGHIj", v -> "ignore case match")        // match this
     *         .orElse(v -> "no match");
     *
     * // CONTAIN match
     * String res2 = match(str, CONTAIN)
     *         .when("abcd", v -> "abcd")
     *         .when("E123", v -> "E123")        // match this
     *         .orElse(v -> "no match");
     *
     * // ignore case for contain
     * String res3 = match(str, ICCONTAIN)
     *         .when("abcd1",   v -> "abcd1")
     *         .when(in(null, "aaa", ".$fghi", "123"), v -> ".$fghi")    // match this
     *         .orElse(v -> "no match");
     *
     * // PREFIX
     * String res4 = match(str, PREFIX)
     *         .when("abcd",    v -> "abcd")
     *         .when("aBcd",    v -> "aBcd")         // match this
     *         .orElse(v -> "no match");
     *
     * // ignore case for suffix
     * String res5 = match(str, ICSUFFIX)
     *         .when("fghij",   v -> "fGHIj")        // match this
     *         .when("aBcd",    v -> "aBcd")
     *         .orElse(v -> "no match");
     * }</pre></blockquote>
     *
     * @param value         value
     * @param patternString {@link PatternString}
     * @return StringMatcherMapping
     * @since 0.0.1
     */
    public static StringMatcherMapping match(String value, PatternString patternString) {
        Objects.requireNonNull(patternString);
        return new StringMatcherMapping(value, patternString);
    }

    public static ClassValueMatcherMapping<Class<?>> match(Class<?> clazz) {
        return new ClassValueMatcherMapping<>(clazz);
    }

    /**
     * The values in {@code .when(value)} are preprocessed by {@code preAction} and then {@code match}. <br>
     * 对 when 中的值进行预处理以后再进行模式匹配 <br>
     * <b>Examples:</b>
     * <blockquote><pre>{@code
     * String str = "123abc";
     *
     * R1<String, String> preAction = s -> "123" + (s == null ? null : s.toLowerCase());
     * String res1 = match(str, preAction, String.class)
     *         .when("123", v -> "1 " + v + "-- 123")
     *         .when("123ABC", v -> "2 " + v + "-- 123ABC")
     *         .when("ABC", v -> "4 " + v + "-- ABC")           // will be matched
     *         .orElse(v -> "orElse " + v);
     * System.out.println(res1);   // output: 4 123abc-- ABC
     * }</pre></blockquote>
     *
     * @param value     value
     * @param preAction Preprocess for value in {@code .when(value)}
     * @param <V>       value type
     * @return ActionValueMatcherMapping
     * @since 0.0.1
     */
    public static <V> ActionValueMatcherMapping<V, V> match(V value, R1<? super V, V> preAction) {
        Objects.requireNonNull(preAction);
        return new ActionValueMatcherMapping<>(value, preAction);
    }

    public static <V, T> ActionValueMatcherMapping<V, T> match(V value, R1<? super T, V> preAction, Class<T> clazz) {
        Objects.requireNonNull(preAction);
        return new ActionValueMatcherMapping<>(value, preAction);
    }

    public static <T> ActionNoneMatcherMapping<T> match(R1<? super T, Boolean> preAction, Class<T> clazz) {
        Objects.requireNonNull(preAction);
        return new ActionNoneMatcherMapping<>(NONE, preAction);
    }

    /**
     * 适用于判断多个值是否为null值（或其他终止条件），只要其中一个值满足终止条件，则立即 {@code return} 方法，终止后续语句运算。<br><br>
     * <b>Examples:</b><br><br>
     * <blockquote><pre>{@code
     * public String patternCheckNull() {
     *     Account account = new Account("12345", "", "aaaabbbb");
     *     Order order = new Order("order_123456", 10.5, new User("zs", 10, account));
     *
     *     Tuple7<User, Account, String, Double, String, Integer, String> values = matchNull()
     *             .whenV(order,   v -> v.buyer,     "order is null or order.buyer is null!")
     *             .whenW(VALUE1,  v -> v.account,   v -> "user " + v._1.name + "'s account is null!")
     *             .whenV(order,   v -> v.orderId,   G::isBlank, "order.orderId is blank!")
     *             .whenV(order,   v -> v.price,     v -> v < 0, "order.price < 0!")
     *             .whenW(VALUE2,  v -> v.userName,  G::isEmpty, "order.buyer.account.userName is empty!")
     *             .whenW(VALUE1,  v -> v.age,       v -> v < 0, "order.buyer.age < 0!")
     *             .orElse(null);
     *
     *     User user = values._1;
     *     Account account1 = values._2;
     *     String orderId = values._3;
     *     Double price = values._4;
     *     String userName = values._5;
     *     Integer age = values._6;
     *     String msg = values._7;
     *
     *     return msg;
     * }
     * }</pre></blockquote>
     * <b>It is equivalent to the code below: </b><br><br>
     * <blockquote><pre>{@code
     * public String commonCheckNull() {
     *     Account account = new Account("12345", "", "aaaabbbb");
     *     Order order = new Order("order_123456", 10.5, new User("zs", 10, account));
     *
     *     String msg = null;
     *     if (order == null || order.buyer == null) {
     *         return "order is null or order.buyer is null!";
     *     }
     *     User user = order.buyer;
     *     if (user.account == null) {
     *         return "user " + user.name + "'s account is null!";
     *     }
     *     if (G.isBlank(order.orderId)) {
     *         return "order.orderId is blank!";
     *     }
     *     if (order.price < 0) {
     *         return "order.price < 0!";
     *     }
     *     if (G.isEmpty(user.account.userName)) {
     *         return "order.buyer.account.userName is empty!";
     *     }
     *     if (user.age < 0) {
     *         return "order.buyer.age < 0!";
     *     }
     *
     *     return null;
     * }
     * }</pre></blockquote>
     *
     * @return NullMatcherMapping
     */
    public static NullMatcherMapping<None> matchNull() {
        return new NullMatcherMapping<>(None.NONE);
    }

    /*###################################################################################
     ************************************************************************************
     ------------------------------------------------------------------------------------
     *********************   Referencing methods of other classes   *********************
     *********************        引用其他类中的方法，方便静态导入        *********************
     ------------------------------------------------------------------------------------
     ************************************************************************************
     ###################################################################################*/

    @SafeVarargs
    public static <T> PatternIn<T> in(T... values) {
        return PatternIn.in(values);
    }

}
