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

import com.iofairy.lambda.V1;
import com.iofairy.lambda.VT0;
import com.iofairy.pattern.PatternIn;

/**
 * Simple Matcher with void
 * @since 0.0.5
 */
public abstract class SimpleVInMatcher<V, P, L> extends SimpleVMatcher<V, P, L> {

    public SimpleVInMatcher(V value, boolean isMatch) {
        super(value, isMatch);
    }

    public SimpleVInMatcher(V value) {
        super(value);
    }

    public abstract SimpleVInMatcher<V, P, L> when(PatternIn<P> values, V1<L> action);

    public abstract SimpleVInMatcher<V, P, L> whenNext(PatternIn<P> values, V1<L> action);

    public abstract <E extends Throwable> SimpleVInMatcher<V, P, L> when(PatternIn<P> values, VT0<E> action) throws E;

    public abstract <E extends Throwable> SimpleVInMatcher<V, P, L> whenNext(PatternIn<P> values, VT0<E> action) throws E;

}
