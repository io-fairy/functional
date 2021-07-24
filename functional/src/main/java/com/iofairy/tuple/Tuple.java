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
package com.iofairy.tuple;

import com.iofairy.except.*;
import com.iofairy.si.SI;

import java.io.Serializable;
import java.util.*;

/**
 * Tuple Interface
 *
 * @since 0.0.1
 */
public interface Tuple extends Serializable {

    long serialVersionUID = 10065917080L;

    /**
     * The size of this Tuple.<br>
     * 元组的元素数量
     * @return 元素数量
     */
    int arity();

    /**
     * Setting tuple's aliases. When the number of aliases is not match for tuple's elements {@link #arity()} , will throw {@code NumberOfAliasesException}<br>
     * 为元组（Tuple）的元素设置别名。当设置的别名数量与元组的元素数量不匹配，将会抛出{@code NumberOfAliasesException} <p>
     * <b>Examples:</b>
     * <pre>
     * // MyTupleAlias.java
     * package mypackage;
     * public enum MyTupleAlias implements TupleAlias {
     *     // You can put All aliases in one `enum MyTupleAlias` for all Tuple Type,
     *     // or create more enum by category: enum UserAliases, enum AddressAliases ...
     *     // 可以把所有的Tuple要用到的别名全部放在一个枚举类型中，可以使用一些特殊命名为Alias分类，
     *     // 如下面的 $USER_ALIAS$, $ORDER_ALIAS$。
     *     // 也可以创建多个枚举类型用于存储不同的Tuple数据。
     *
     *     $USER_ALIAS$,
     *          ID, NAME, TEL, AGE, BIRTHDAY, PROVINCE, CITY, REGISTERTIME,
     *     $ORDER_ALIAS$,
     *          ORDERID, GOODSID, USERID, PRICE, QUANTITY, ORDERTIME, PAYTIME
     * }
     *
     * // TestMain.java
     * package test.xxx;
     * import static mypackage.MyTupleAlias.*;    // import MyTupleAlias
     *
     * Tuple3&lt;Integer, String, Integer&gt; tuple = new Tuple3&lt;&gt;(1, "Tom", 20);
     * tuple.alias(ID, NAME, AGE);
     * </pre>
     *
     * @param aliases aliases.　别名
     * @return tuple.　元组
     * @throws NumberOfAliasesException if the number of aliases is not equal {@link #arity()}. 如果设置的别名的数量不等于 {@link #arity()}，抛出此异常
     * @throws UnsupportedOperationException if this method is called by Tuple0 {@link Tuple0#alias(TupleAlias...)}.
     * @since 0.0.1
     */
    Tuple alias(TupleAlias... aliases);

    /**
     * Setting tuple's aliases. Recommend using {@link #alias(TupleAlias...)} <br>
     * 为元组（Tuple）的元素设置别名。推荐使用 {@link #alias(TupleAlias...)} 这个方法设置别名。
     *
     * @see #alias(TupleAlias...)
     * @param aliases aliases.　别名
     * @return tuple.　元组
     * @throws NumberOfAliasesException if the number of aliases is not equal {@link #arity()}. 如果设置的别名的数量不等于 {@link #arity()}，抛出此异常
     * @throws UnsupportedOperationException if this method is called by Tuple0 {@link Tuple0#alias(String...)}.
     */
    Tuple alias(String... aliases);

    /**
     * Get current alias type: null, tuple, string. <br>
     * <b>null: </b>not set alias; <br>
     * <b>tuple: </b>use TupleAlias, when call {@link #alias(TupleAlias...)}; <br>
     * <b>string: </b>use normal alias, when call {@link #alias(String...)}. <br><br>
     * 获取当前设置的别名类型（三种）：null, tuple, string. <br>
     * <b>null: </b>表示未设置别名； <br>
     * <b>tuple: </b>使用TupleAlias设置别名，在你调用 {@link #alias(TupleAlias...)} 之后； <br>
     * <b>string: </b>使用普通的字符串设置别名，在你调用 {@link #alias(String...)} 之后。<br>
     * @return alias type
     * @since 0.0.1
     */
    String aliasType();

    /**
     * Copy aliases from other tuple. <br>
     * 从其他tuple中复制别名到当前tuple中
     * @param tuple other tuple
     * @return this tuple
     * @since 0.0.1
     */
    Tuple copyAliases(Tuple tuple);

    /**
     * clear all aliases. 清空所有别名。
     * @since 0.0.1
     */
    void clearAlias();

    /**
     * Get list of aliases. 获取别名列表。
     * @return list of aliases
     * @since 0.0.1
     */
    List<TupleAlias> getTupleAliases();

    /**
     * Get list of aliases. 获取别名列表。
     * @return list of aliases
     */
    List<String> getAliases();

    /**
     * Indicates whether some other Tuple object's aliases is "equal to" this one. <br>
     * 比较两个Tuple的别名是否相等。
     * @param tuple other tuple object
     * @return return {@code true} if aliases are equal.　返回true，如果两个tuple的所有别名都相等。
     * @since 0.0.1
     */
    boolean aliasesEquals(Tuple tuple);

    /**
     * Get tuple element value by alias, it will throw {@code AliasNotFoundException} when alias not found.<br>
     * 通过别名获取元组元素的值，如果不存在该别名，将抛出异常 AliasNotFoundException。<br>
     * 注：`_`（下划线） 在 Java 9 中被定义成了关键字，无法单独使用 `_`（下划线） 作为标识符。
     *
     * @param alias tuple element alias 别名
     * @param <R> return type 返回值类型
     * @return tuple element value
     * @throws AliasNotSetException if call this method before {@link #alias(TupleAlias...)}.
     *                              在调用 {@link #alias(TupleAlias...)} 之前调用此方法，将抛出{@code AliasNotSetException}
     * @throws AliasNotFoundException if the `alias` not found. 如果元组不存在该别名，抛出异常
     * @since 0.0.1
     */
    <R> R __(TupleAlias alias);

    /**
     * Get tuple element value by alias. Recommend using {@link #__(TupleAlias) }
     *
     * @see #__(TupleAlias)
     * @param alias tuple element alias 别名
     * @param <R> return type 返回值类型
     * @return tuple element value
     * @throws AliasNotSetException if call this method before {@link #alias(String...)}.
     *                              在调用 {@link #alias(String...)} 之前调用此方法，将抛出{@code AliasNotSetException}
     * @throws AliasNotFoundException if the `alias` not found. 如果元组不存在该别名，抛出异常
     */
    <R> R __(String alias);

    /**
     * whether contains alias. 该元组中是否包含别名
     * @param alias alias.　别名
     * @return return {@code true} if contains alias.　返回true，如果此tuple包含该别名。
     * @since 0.0.1
     */
    boolean containsAlias(TupleAlias alias);

    /**
     * whether contains alias. 该元组中是否包含别名
     * @param alias alias.　别名
     * @return return {@code true} if contains alias.　返回true，如果此tuple包含该别名。
     */
    boolean containsAlias(String alias);

    /**
     * Transform this Tuple to Map. <br>
     * 将 tuple 转成 map
     * @param <R> map value type
     * @return a map -- key is alias, value is tuple's element
     * @since 0.0.1
     */
    <R> Map<String, R> toMap();

    /**
     * Transform this Tuple to {@link SI}. <br>
     * 将 tuple 转成 SI
     * @return {@link SI} object.
     * @since 0.0.1
     */
    SI toSI();

    /**
     * The nth element of this tuple<br>
     * 从元组中取第n个元素
     * @param n index 序号
     * @param <R> return type 返回值类型
     * @return return value of sp
     * @throws IndexOutOfBoundsException if the `n` is out of range(n &lt; 0 || n &gt;= arity).
     *              当 n &lt; 0 或者 n &gt;= arity() 时，抛出异常。
     */
    <R> R element(int n);

    /**
     * The nth element with alias of this tuple<br>
     * 从元组中取第n个元素（带别名）
     *
     * @param n index 序号
     * @param <R> return type 返回值类型
     * @return (alias, element)
     * @throws AliasNotSetException if call this method before {@link #alias(TupleAlias...)}.
     *                              在调用 {@link #alias(TupleAlias...)} 之前调用此方法，将抛出{@code AliasNotSetException}
     * @throws IndexOutOfBoundsException if the `n` is out of range(n &lt; 0 || n &gt;= arity).
     *              当 n &lt; 0 或者 n &gt;= arity() 时，抛出异常。
     * @since 0.0.1
     */
    <R> Tuple2<TupleAlias, R> elementWithTupleAlias(int n);

    /**
     * The nth element with alias of this tuple<br>
     * 从元组中取第n个元素（带别名）
     *
     * @param n index 序号
     * @param <R> return type 返回值类型
     * @return (alias, element)
     * @throws AliasNotSetException if call this method before {@link #alias(String...)}.
     *                              在调用 {@link #alias(String...)} 之前调用此方法，将抛出{@code AliasNotSetException}
     * @throws IndexOutOfBoundsException if the `n` is out of range(n &lt; 0 || n &gt;= arity).
     *              当 n &lt; 0 或者 n &gt;= arity() 时，抛出异常。
     */
    <R> Tuple2<String, R> elementWithAlias(int n);

    /**
     * clone a tuple by shallow copy. <br>
     * 通过浅拷贝的方式克隆一个tuple
     * @return new tuple
     * @since 0.0.5
     */
    Tuple copy();

    /**
     * Create empty tuple<br>
     * 创建一个空元组
     *
     * @return the instance of Tuple0.　返回Tuple0的实例
     */
    static Tuple0 empty() {
        return Tuple0.instance();
    }

    /**
     * Create empty tuple<br>
     * 创建一个空元组
     *
     * @return the instance of Tuple0.　返回Tuple0的实例
     */
    static Tuple0 of() {
        return Tuple0.instance();
    }

    /**
     * Create a tuple of 1 element<br>
     * 创建1个元素的元组
     *
     * @param _1 the 1st element.　第1个元素
     * @param <T1> type of the 1st element.　第1个元素的类型
     * @return the instance of Tuple1.　返回Tuple1的实例
     */
    static <T1> Tuple1<T1> of(T1 _1) {
        return new Tuple1<>(_1);
    }

    /**
     * Create a tuple of 2 elements<br>
     * 创建2个元素的元组
     *
     * @param _1 the 1st element.　第1个元素
     * @param _2 the 2nd element.　第2个元素
     * @param <T1> type of the 1st element.　第1个元素的类型
     * @param <T2> type of the 2nd element.　第2个元素的类型
     * @return the instance of Tuple2.　返回Tuple2的实例
     */
    static <T1, T2> Tuple2<T1, T2> of(T1 _1, T2 _2) {
        return new Tuple2<>(_1, _2);
    }

    /**
     * Create a tuple of 3 elements<br>
     * 创建3个元素的元组
     *
     * @param _1 the 1st element.　第1个元素
     * @param _2 the 2nd element.　第2个元素
     * @param _3 the 3rd element.　第3个元素
     * @param <T1> type of the 1st element.　第1个元素的类型
     * @param <T2> type of the 2nd element.　第2个元素的类型
     * @param <T3> type of the 3rd element.　第3个元素的类型
     * @return the instance of Tuple3.　返回Tuple3的实例
     */
    static <T1, T2, T3> Tuple3<T1, T2, T3> of(T1 _1, T2 _2, T3 _3) {
        return new Tuple3<>(_1, _2, _3);
    }

    /**
     * Create a tuple of 4 elements<br>
     * 创建4个元素的元组
     *
     * @param _1 the 1st element.　第1个元素
     * @param _2 the 2nd element.　第2个元素
     * @param _3 the 3rd element.　第3个元素
     * @param _4 the 4th element.　第4个元素
     * @param <T1> type of the 1st element.　第1个元素的类型
     * @param <T2> type of the 2nd element.　第2个元素的类型
     * @param <T3> type of the 3rd element.　第3个元素的类型
     * @param <T4> type of the 4th element.　第4个元素的类型
     * @return the instance of Tuple4.　返回Tuple4的实例
     */
    static <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> of(T1 _1, T2 _2, T3 _3, T4 _4) {
        return new Tuple4<>(_1, _2, _3, _4);
    }

    /**
     * Create a tuple of 5 elements<br>
     * 创建5个元素的元组
     *
     * @param _1 the 1st element.　第1个元素
     * @param _2 the 2nd element.　第2个元素
     * @param _3 the 3rd element.　第3个元素
     * @param _4 the 4th element.　第4个元素
     * @param _5 the 5th element.　第5个元素
     * @param <T1> type of the 1st element.　第1个元素的类型
     * @param <T2> type of the 2nd element.　第2个元素的类型
     * @param <T3> type of the 3rd element.　第3个元素的类型
     * @param <T4> type of the 4th element.　第4个元素的类型
     * @param <T5> type of the 5th element.　第5个元素的类型
     * @return the instance of Tuple5.　返回Tuple5的实例
     */
    static <T1, T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5) {
        return new Tuple5<>(_1, _2, _3, _4, _5);
    }

    /**
     * Create a tuple of 6 elements<br>
     * 创建6个元素的元组
     *
     * @param _1 the 1st element.　第1个元素
     * @param _2 the 2nd element.　第2个元素
     * @param _3 the 3rd element.　第3个元素
     * @param _4 the 4th element.　第4个元素
     * @param _5 the 5th element.　第5个元素
     * @param _6 the 6th element.　第6个元素
     * @param <T1> type of the 1st element.　第1个元素的类型
     * @param <T2> type of the 2nd element.　第2个元素的类型
     * @param <T3> type of the 3rd element.　第3个元素的类型
     * @param <T4> type of the 4th element.　第4个元素的类型
     * @param <T5> type of the 5th element.　第5个元素的类型
     * @param <T6> type of the 6th element.　第6个元素的类型
     * @return the instance of Tuple6.　返回Tuple6的实例
     */
    static <T1, T2, T3, T4, T5, T6> Tuple6<T1, T2, T3, T4, T5, T6> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6) {
        return new Tuple6<>(_1, _2, _3, _4, _5, _6);
    }

    /**
     * Create a tuple of 7 elements<br>
     * 创建7个元素的元组
     *
     * @param _1 the 1st element.　第1个元素
     * @param _2 the 2nd element.　第2个元素
     * @param _3 the 3rd element.　第3个元素
     * @param _4 the 4th element.　第4个元素
     * @param _5 the 5th element.　第5个元素
     * @param _6 the 6th element.　第6个元素
     * @param _7 the 7th element.　第7个元素
     * @param <T1> type of the 1st element.　第1个元素的类型
     * @param <T2> type of the 2nd element.　第2个元素的类型
     * @param <T3> type of the 3rd element.　第3个元素的类型
     * @param <T4> type of the 4th element.　第4个元素的类型
     * @param <T5> type of the 5th element.　第5个元素的类型
     * @param <T6> type of the 6th element.　第6个元素的类型
     * @param <T7> type of the 7th element.　第7个元素的类型
     * @return the instance of Tuple7.　返回Tuple7的实例
     */
    static <T1, T2, T3, T4, T5, T6, T7> Tuple7<T1, T2, T3, T4, T5, T6, T7> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7) {
        return new Tuple7<>(_1, _2, _3, _4, _5, _6, _7);
    }

    /**
     * Create a tuple of 8 elements<br>
     * 创建8个元素的元组
     *
     * @param _1 the 1st element.　第1个元素
     * @param _2 the 2nd element.　第2个元素
     * @param _3 the 3rd element.　第3个元素
     * @param _4 the 4th element.　第4个元素
     * @param _5 the 5th element.　第5个元素
     * @param _6 the 6th element.　第6个元素
     * @param _7 the 7th element.　第7个元素
     * @param _8 the 8th element.　第8个元素
     * @param <T1> type of the 1st element.　第1个元素的类型
     * @param <T2> type of the 2nd element.　第2个元素的类型
     * @param <T3> type of the 3rd element.　第3个元素的类型
     * @param <T4> type of the 4th element.　第4个元素的类型
     * @param <T5> type of the 5th element.　第5个元素的类型
     * @param <T6> type of the 6th element.　第6个元素的类型
     * @param <T7> type of the 7th element.　第7个元素的类型
     * @param <T8> type of the 8th element.　第8个元素的类型
     * @return the instance of Tuple8.　返回Tuple8的实例
     */
    static <T1, T2, T3, T4, T5, T6, T7, T8> Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8) {
        return new Tuple8<>(_1, _2, _3, _4, _5, _6, _7, _8);
    }

    /**
     * Create a tuple of 9 elements<br>
     * 创建9个元素的元组
     *
     * @param _1 the 1st element.　第1个元素
     * @param _2 the 2nd element.　第2个元素
     * @param _3 the 3rd element.　第3个元素
     * @param _4 the 4th element.　第4个元素
     * @param _5 the 5th element.　第5个元素
     * @param _6 the 6th element.　第6个元素
     * @param _7 the 7th element.　第7个元素
     * @param _8 the 8th element.　第8个元素
     * @param _9 the 9th element.　第9个元素
     * @param <T1> type of the 1st element.　第1个元素的类型
     * @param <T2> type of the 2nd element.　第2个元素的类型
     * @param <T3> type of the 3rd element.　第3个元素的类型
     * @param <T4> type of the 4th element.　第4个元素的类型
     * @param <T5> type of the 5th element.　第5个元素的类型
     * @param <T6> type of the 6th element.　第6个元素的类型
     * @param <T7> type of the 7th element.　第7个元素的类型
     * @param <T8> type of the 8th element.　第8个元素的类型
     * @param <T9> type of the 9th element.　第9个元素的类型
     * @return the instance of Tuple9.　返回Tuple9的实例
     */
    static <T1, T2, T3, T4, T5, T6, T7, T8, T9> Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9) {
        return new Tuple9<>(_1, _2, _3, _4, _5, _6, _7, _8, _9);
    }

    /**
     * clone a tuple by shallow copy. <br>
     * 通过浅拷贝的方式克隆一个tuple
     * @param tuple origin tuple
     * @return new tuple
     * @since 0.0.1
     */
    static Tuple clone(Tuple tuple) {
        return tuple == null ? tuple : tuple.copy();
    }
}
