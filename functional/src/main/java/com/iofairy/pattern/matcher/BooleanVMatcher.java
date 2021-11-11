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
import com.iofairy.lambda.VT1;

import java.util.Objects;

/**
 * Boolean Matcher with void
 *
 * @since 0.0.1
 */
public class BooleanVMatcher<V> implements Matcher {
    protected Void returnValue;
    protected V value;
    protected boolean isMatch;

    public BooleanVMatcher(V value, boolean isMatch) {
        this.value = value;
        this.isMatch = isMatch;
    }

    public BooleanVMatcher(V value) {
        this(value, false);
    }

    public BooleanVMatcher<V> when(boolean value, V1<V> action) {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            isMatch = true;
            action.$(this.value);
        }
        return this;
    }

    public BooleanVMatcher<V> whenNext(boolean value, V1<V> action) {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            action.$(this.value);
        }
        return this;
    }

    public <E extends Throwable> BooleanVMatcher<V> with(boolean value, VT1<V, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            isMatch = true;
            action.$(this.value);
        }
        return this;
    }

    public <E extends Throwable> BooleanVMatcher<V> withNext(boolean value, VT1<V, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            action.$(this.value);
        }
        return this;
    }

    public Void orElse(V1<V> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            action.$(this.value);
        }
        return returnValue;
    }

    public <E extends Throwable> Void orWith(VT1<V, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch) {
            action.$(this.value);
        }
        return returnValue;
    }
}
