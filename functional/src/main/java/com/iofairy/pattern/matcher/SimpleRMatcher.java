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

import com.iofairy.lambda.*;

/**
 * Simple Matcher with return value
 * @since 0.0.1
 */
public abstract class SimpleRMatcher<V, P, L, R> extends SimpleMatcher<V, P, L, R> {
    protected R returnValue;
    protected V value;
    protected boolean isMatch;

    public SimpleRMatcher(V value, boolean isMatch) {
        this.value = value;
        this.isMatch = isMatch;
    }

    public SimpleRMatcher(V value) {
        this(value, false);
    }

    public abstract SimpleRMatcher<V, P, L, R> when(P value, R1<L, R> action);

    public abstract SimpleRMatcher<V, P, L, R> whenNext(P value, R1<L, R> action);

    public abstract R orElse(R1<V, R> action);
}
