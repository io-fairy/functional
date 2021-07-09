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

/**
 * A tuple of 9 elements<br>
 * 9个元素的元组
 *
 * @param <T1> type of the 1st element.　第1个元素的类型
 * @param <T2> type of the 2nd element.　第2个元素的类型
 * @param <T3> type of the 3rd element.　第3个元素的类型
 * @param <T4> type of the 4th element.　第4个元素的类型
 * @param <T5> type of the 5th element.　第5个元素的类型
 * @param <T6> type of the 6th element.　第6个元素的类型
 * @param <T7> type of the 7th element.　第7个元素的类型
 * @param <T8> type of the 8th element.　第8个元素的类型
 * @param <T9> type of the 9th element.　第9个元素的类型
 * @since 0.0.1
 */
public class Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> extends TupleBase {
    private static final long serialVersionUID = 10065918009L;

    /**
     * The 1st element of this tuple.
     */
    public final T1 _1;
    /**
     * The 2nd element of this tuple.
     */
    public final T2 _2;
    /**
     * The 3rd element of this tuple.
     */
    public final T3 _3;
    /**
     * The 4th element of this tuple.
     */
    public final T4 _4;
    /**
     * The 5th element of this tuple.
     */
    public final T5 _5;
    /**
     * The 6th element of this tuple.
     */
    public final T6 _6;
    /**
     * The 7th element of this tuple.
     */
    public final T7 _7;
    /**
     * The 8th element of this tuple.
     */
    public final T8 _8;
    /**
     * The 9th element of this tuple.
     */
    public final T9 _9;

    /**
     * Constructs a {@code Tuple9}.　Tuple9构造器。
     * @param _1 The value of 1st element
     * @param _2 The value of 2nd element
     * @param _3 The value of 3rd element
     * @param _4 The value of 4th element
     * @param _5 The value of 5th element
     * @param _6 The value of 6th element
     * @param _7 The value of 7th element
     * @param _8 The value of 8th element
     * @param _9 The value of 9th element
     */
    public Tuple9(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9){
        this._1 = _1;
        this._2 = _2;
        this._3 = _3;
        this._4 = _4;
        this._5 = _5;
        this._6 = _6;
        this._7 = _7;
        this._8 = _8;
        this._9 = _9;
    }

    @Override
    public int arity() {
        return 9;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> alias(TupleAlias... aliases) {
        return (Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>)super.alias(aliases);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> alias(String... aliases) {
        return (Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>)super.alias(aliases);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R> R element(int n) {
        switch (n) {
            case 0:
                return (R) _1;
            case 1:
                return (R) _2;
            case 2:
                return (R) _3;
            case 3:
                return (R) _4;
            case 4:
                return (R) _5;
            case 5:
                return (R) _6;
            case 6:
                return (R) _7;
            case 7:
                return (R) _8;
            case 8:
                return (R) _9;
            default:
                throw new IndexOutOfBoundsException("Index out of range: " + n + ", Size: " + arity());
        }
    }

}
