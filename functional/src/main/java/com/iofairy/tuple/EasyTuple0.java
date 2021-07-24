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

/**
 * A EasyTuple of no element<br>
 * 一个没有元素的元组
 *
 * @since 0.0.5
 */
public class EasyTuple0 extends TupleBase implements EasyTuple {
    private static final long serialVersionUID = 10065918010L;

    private static final EasyTuple0 INSTANCE = new EasyTuple0();
    /**
     * Constructs a {@code EasyTuple0}.　EasyTuple0构造器。
     */
    private EasyTuple0() { }

    /**
     * Get the instance of EasyTuple0.<br>
     * 获取 EasyTuple0 的实例
     * @return the instance of EasyTuple0
     */
    public static EasyTuple0 instance() {
        return INSTANCE;
    }

    @Override
    public int arity() {
        return 0;
    }

    @Override
    public EasyTuple0 alias(TupleAlias... aliases) {
        return (EasyTuple0)super.alias(aliases);
    }

    @Override
    public EasyTuple0 alias(String... aliases) {
        return (EasyTuple0)super.alias(aliases);
    }

    @Override
    public EasyTuple0 copyAliases(Tuple tuple) {
        return (EasyTuple0)super.copyAliases(tuple);
    }

    @Override
    public <R> R element(int n) {
        throw new IndexOutOfBoundsException("Index out of range: " + n + ", Size: " + arity());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Object> toList() {
        return EasyTuple.super.toList();
    }

    @Override
    public EasyTuple0 copy() {
        return EasyTuple.empty();
    }
}
