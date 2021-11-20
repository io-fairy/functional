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
package com.iofairy.pattern.matcher;

import com.iofairy.tuple.Tuple;
import com.iofairy.tuple.Tuple9;

/**
 * Null9Matcher
 *
 * @since 0.2.0
 */
public class Null9Matcher<T1, T2, T3, T4, T5, T6, T7, T8, R> implements Matcher {
    private final R msg;
    private final T1 value1;
    private final T2 value2;
    private final T3 value3;
    private final T4 value4;
    private final T5 value5;
    private final T6 value6;
    private final T7 value7;
    private final T8 value8;
    private final boolean isMatch;

    Null9Matcher(boolean isMatch, T1 value1, T2 value2, T3 value3, T4 value4, T5 value5, T6 value6, T7 value7, T8 value8, R msg) {
        this.isMatch = isMatch;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
        this.value5 = value5;
        this.value6 = value6;
        this.value7 = value7;
        this.value8 = value8;
        this.msg = msg;
    }

    public Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, R> orElse(R msg) {
        R finalMsg = !isMatch ? msg : this.msg;
        return Tuple.of(value1, value2, value3, value4, value5, value6, value7, value8, finalMsg);
    }

}
