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
 * A tuple of 6 elements<br>
 * 6个元素的元组
 *
 * @param <T1> type of the 1st element.　第1个元素的类型
 * @param <T2> type of the 2nd element.　第2个元素的类型
 * @param <T3> type of the 3rd element.　第3个元素的类型
 * @param <T4> type of the 4th element.　第4个元素的类型
 * @param <T5> type of the 5th element.　第5个元素的类型
 * @param <T6> type of the 6th element.　第6个元素的类型
 * @since 0.0.1
 */
public class Tuple6<T1, T2, T3, T4, T5, T6> extends TupleBase {
    private static final long serialVersionUID = 10065918006L;

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
     * Constructs a {@code Tuple6}.　Tuple6构造器。
     * @param _1 The value of 1st element
     * @param _2 The value of 2nd element
     * @param _3 The value of 3rd element
     * @param _4 The value of 4th element
     * @param _5 The value of 5th element
     * @param _6 The value of 6th element
     */
    public Tuple6(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6){
        this._1 = _1;
        this._2 = _2;
        this._3 = _3;
        this._4 = _4;
        this._5 = _5;
        this._6 = _6;
    }

    @Override
    public int arity() {
        return 6;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Tuple6<T1, T2, T3, T4, T5, T6> alias(TupleAlias... aliases) {
        return (Tuple6<T1, T2, T3, T4, T5, T6>)super.alias(aliases);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Tuple6<T1, T2, T3, T4, T5, T6> alias(String... aliases) {
        return (Tuple6<T1, T2, T3, T4, T5, T6>)super.alias(aliases);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Tuple6<T1, T2, T3, T4, T5, T6> copyAliases(Tuple tuple) {
        return (Tuple6<T1, T2, T3, T4, T5, T6>)super.copyAliases(tuple);
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
            default:
                throw new IndexOutOfBoundsException("Index out of range: " + n + ", Size: " + arity());
        }
    }

    @Override
    public Tuple6<T1, T2, T3, T4, T5, T6> copy() {
        return Tuple.of(_1, _2, _3, _4, _5, _6).copyAliases(this);
    }


}
