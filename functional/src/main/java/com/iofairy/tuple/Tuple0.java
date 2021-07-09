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
 * A tuple of no element<br>
 * 一个没有元素的元组
 * @since 0.0.1
 */
public class Tuple0 extends TupleBase {
    private static final long serialVersionUID = 10065918000L;

    private static final Tuple0 INSTANCE = new Tuple0();

    /**
     * Constructs a {@code Tuple0}.　Tuple0构造器。
     */
    private Tuple0() { }

    /**
     * Get the instance of Tuple0.<br>
     * 获取 Tuple0 的实例
     * @return the instance of Tuple0
     */
    public static Tuple0 instance() {
        return INSTANCE;
    }

    @Override
    public int arity() {
        return 0;
    }

    @Override
    public Tuple0 alias(TupleAlias... aliases) {
        return (Tuple0)super.alias(aliases);
    }

    @Override
    public Tuple0 alias(String... aliases) {
        return (Tuple0)super.alias(aliases);
    }

    @Override
    public <R> R element(int n) {
        throw new IndexOutOfBoundsException("Index out of range: " + n + ", Size: " + arity());
    }


}
