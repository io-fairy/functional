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
 * @since 0.0.1
 */
public class Pattern {
    public static PatternDefault DEFAULT = PatternDefault.DEFAULT;      // DEFAULT can match by value or boolean
    public static PatternValue VALUE = PatternValue.VALUE;              // match by value
    public static PatternBoolean BOOLEAN = PatternBoolean.BOOLEAN;      // match by boolean
    public static PatternType TYPE = PatternType.TYPE;                  // match by value type(Class)
    public static PatternString STRING = PatternString.STRING;          // match by String value
    public static PatternString IGNORECASE = PatternString.IGNORECASE;  // match by String value ignore case
    public static PatternString CONTAIN = PatternString.CONTAIN;        // match by String value using String.contains
    public static PatternString PREFIX = PatternString.PREFIX;          // match by String value using String.startsWith
    public static PatternString SUFFIX = PatternString.SUFFIX;          // match by String value using String.endsWith
    public static PatternString ICCONTAIN = PatternString.ICCONTAIN;    // ignore case for contain
    public static PatternString ICPREFIX = PatternString.ICPREFIX;      // ignore case for prefix
    public static PatternString ICSUFFIX = PatternString.ICSUFFIX;      // ignore case for suffix

    public static None NONE = None.NONE;

    /**
     * {@code match} can instead of {@code switch} statement or {@code if} statement. <br>
     * 使用 match 来替代 switch 和 if 语句 <br>
     * <b>Examples:</b>
     * <pre>
     * String s = "5";
     * String result = match(s)
     *         .when("1", v -&gt; v + v)
     *         .when("2", v -&gt; v + "a")
     *         .when(in("3", "4", "5", "6"), v -&gt; v + " - abcd")
     *         .orElse(v -&gt; "no match");
     *
     * System.out.println("match result: " + result);
     * </pre>
     * @param value value
     * @param <V> value type
     * @return MixMatcherMapping
     * @since 0.0.1
     */
    public static <V> MixMatcherMapping<V> match(V value) {
        return match(value, DEFAULT);
    }

    public static <V> MixMatcherMapping<V> match(V value, PatternDefault patternDefault) {
        Objects.requireNonNull(patternDefault);
        return new MixMatcherMapping<V>(value);
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
     * <pre>
     * int i = 10;
     * String s = "abc";
     * Object o = new Object();
     *
     * String res = match()
     *              .when(i == 5,           v -&gt; "i == 5")
     *              .when(s.equals("abc"),  v -&gt; "abc")
     *              .when(o == null,        v -&gt; "object is null")
     *              .orElse(v -&gt; null);
     * </pre>
     * @return BooleanMatcherMapping
     * @see #match(None)
     * @since 0.0.1
     */
    public static BooleanMatcherMapping<None> match() {
        return new BooleanMatcherMapping<>(NONE);
    }

    public static BooleanMatcherMapping<None> match(None value) {
        return new BooleanMatcherMapping<>(NONE);
    }

    public static <V> BooleanMatcherMapping<V> match(V value, PatternBoolean patternBoolean) {
        Objects.requireNonNull(patternBoolean);
        return new BooleanMatcherMapping<>(value);
    }

    public static <V> TypeMatcherMapping<V> match(V value, PatternType patternType) {
        Objects.requireNonNull(patternType);
        return new TypeMatcherMapping<>(value);
    }

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
     * <pre>
     * String str = "123abc";
     *
     * R1&lt;String, String&gt; preAction = s -&gt; "123" + (s == null ? null : s.toLowerCase());
     * String res1 = match(str, preAction, String.class)
     *         .when("123", v -&gt; "1 " + v + "-- 123")
     *         .when("123ABC", v -&gt; "2 " + v + "-- 123ABC")
     *         .when("ABC", v -&gt; "4 " + v + "-- ABC")           // will be matched
     *         .orElse(v -&gt; "orElse " + v);
     * System.out.println(res1);   // output: 4 123abc-- ABC
     * </pre>
     * @param value value
     * @param preAction Preprocess for value in {@code .when(value)}
     * @param <V> value type
     * @return ActionValueMatcherMapping
     * @since 0.0.1
     */
    public static <V> ActionValueMatcherMapping<V, V> match(V value, R1<V, V> preAction) {
        Objects.requireNonNull(preAction);
        return new ActionValueMatcherMapping<>(value, preAction);
    }

    public static <V, T> ActionValueMatcherMapping<V, T> match(V value, R1<T, V> preAction, Class<T> clazz) {
        Objects.requireNonNull(preAction);
        return new ActionValueMatcherMapping<>(value, preAction);
    }

    public static <T> ActionNoneMatcherMapping<T> match(R1<T, Boolean> preAction, Class<T> clazz) {
        Objects.requireNonNull(preAction);
        return new ActionNoneMatcherMapping<>(NONE, preAction);
    }


    /*###################################################################################
     ************************************************************************************
     ------------------------------------------------------------------------------------
     *********************   Referencing methods of other classes   *********************
     *********************        引用其他类中的方法，方便静态导入        *********************
     ------------------------------------------------------------------------------------
     ************************************************************************************
     ###################################################################################*/

    public static <T> PatternIn<T> in(T... values) {
        return PatternIn.in(values);
    }

}
