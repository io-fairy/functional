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
 * A tuple of 2 elements<br>
 * 2个元素的元组
 *
 * @param <T1> type of the 1st element.　第1个元素的类型
 * @param <T2> type of the 2nd element.　第2个元素的类型
 * @since 0.0.1
 */
public class Tuple2<T1, T2> extends TupleBase {
    private static final long serialVersionUID = 10065918002L;

    /**
     * The 1st element of this tuple.
     */
    public final T1 _1;
    /**
     * The 2nd element of this tuple.
     */
    public final T2 _2;

    /**
     * Constructs a {@code Tuple2}.　Tuple2构造器。
     * @param _1 The value of 1st element
     * @param _2 The value of 2nd element
     */
    public Tuple2(T1 _1, T2 _2){
        this._1 = _1;
        this._2 = _2;
    }

    @Override
    public int arity() {
        return 2;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Tuple2<T1, T2> alias(TupleAlias... aliases) {
        return (Tuple2<T1, T2>)super.alias(aliases);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Tuple2<T1, T2> alias(String... aliases) {
        return (Tuple2<T1, T2>)super.alias(aliases);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Tuple2<T1, T2> copyAliases(Tuple tuple) {
        return (Tuple2<T1, T2>)super.copyAliases(tuple);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R> R element(int n) {
        switch (n) {
            case 0:
                return (R) _1;
            case 1:
                return (R) _2;
            default:
                throw new IndexOutOfBoundsException("Index out of range: " + n + ", Size: " + arity());
        }
    }

    @Override
    public Tuple2<T1, T2> copy() {
        return Tuple.of(_1, _2).copyAliases(this);
    }


}
