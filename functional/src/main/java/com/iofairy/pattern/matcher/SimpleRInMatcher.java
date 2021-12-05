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

import com.iofairy.lambda.R1;
import com.iofairy.lambda.RT1;
import com.iofairy.pattern.PatternIn;

/**
 * Simple Matcher with return value
 * @since 0.0.5
 */
public abstract class SimpleRInMatcher<V, P, L, R> extends SimpleRMatcher<V, P, L, R> {

    public SimpleRInMatcher(V value, boolean isMatch) {
        super(value, isMatch);
    }

    public SimpleRInMatcher(V value) {
        super(value);
    }

    public abstract SimpleRInMatcher<V, P, L, R> when(PatternIn<P> values, R1<? super L, ? extends R> action);

    public abstract SimpleRInMatcher<V, P, L, R> whenNext(PatternIn<P> values, R1<? super L, ? extends R> action);

    public abstract <E extends Throwable> SimpleRInMatcher<V, P, L, R> with(PatternIn<P> values, RT1<? super L, ? extends R, E> action) throws E;

    public abstract <E extends Throwable> SimpleRInMatcher<V, P, L, R> withNext(PatternIn<P> values, RT1<? super L, ? extends R, E> action) throws E;

}
