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

import java.util.Map;

/**
 * A tuple of 3 same type elements<br>
 * 具有相同类型的3个元素的元组
 * @since 0.0.1
 */
public class EasyTuple3<T> extends Tuple3<T, T, T> {
    private static final long serialVersionUID = 10065918013L;

    public EasyTuple3(T _1, T _2, T _3) {
        super(_1, _2, _3);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T __(TupleAlias alias) {
        return super.__(alias);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T __(String alias) {
        return super.__(alias);
    }

    @Override
    public EasyTuple3<T> alias(TupleAlias... aliases) {
        return (EasyTuple3<T>)super.alias(aliases);
    }

    @Override
    public EasyTuple3<T> alias(String... aliases) {
        return (EasyTuple3<T>)super.alias(aliases);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, T> toMap() {
        return super.toMap();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T element(int n) {
        return super.element(n);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Tuple2<TupleAlias, T> elementWithTupleAlias(int n) {
        return super.elementWithTupleAlias(n);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Tuple2<String, T> elementWithAlias(int n) {
        return super.elementWithAlias(n);
    }

}
