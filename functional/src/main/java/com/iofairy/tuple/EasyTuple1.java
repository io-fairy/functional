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

import java.util.List;
import java.util.Map;

/**
 * A EasyTuple of 1 element<br>
 * 1个元素的元组
 *
 * @param <T> type of element.　元素的类型
 * @since 0.0.5
 */
public class EasyTuple1<T> extends Tuple1<T> implements EasyTuple {
    private static final long serialVersionUID = 10065918011L;
    /**
     * Constructs a {@code EasyTuple1}.　EasyTuple1构造器。
     *
     * @param _1 The value of 1st element
     */
    public EasyTuple1(T _1) {
        super(_1);
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
    public EasyTuple1<T> alias(TupleAlias... aliases) {
        return (EasyTuple1<T>)super.alias(aliases);
    }

    @Override
    public EasyTuple1<T> alias(String... aliases) {
        return (EasyTuple1<T>)super.alias(aliases);
    }

    @Override
    public EasyTuple1<T> copyAliases(Tuple tuple) {
        return (EasyTuple1<T>)super.copyAliases(tuple);
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

    @Override
    @SuppressWarnings("unchecked")
    public List<T> toList() {
        return EasyTuple.super.<T>toList();
    }

    @Override
    public EasyTuple1<T> copy() {
        return EasyTuple.of(_1).copyAliases(this);
    }

}
