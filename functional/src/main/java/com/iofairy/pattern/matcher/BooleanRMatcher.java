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

import java.util.Objects;

/**
 * Boolean Matcher with Return value
 *
 * @since 0.0.1
 */
public class BooleanRMatcher<V, R> implements Matcher {
    protected R returnValue;
    protected V value;
    protected boolean isMatch;

    public BooleanRMatcher(V value, boolean isMatch) {
        this.value = value;
        this.isMatch = isMatch;
    }

    public BooleanRMatcher(V value) {
        this(value, false);
    }

    public BooleanRMatcher<V, R> when(boolean value, R1<? super V, ? extends R> action) {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            isMatch = true;
            returnValue = action.$(this.value);
        }
        return this;
    }

    public BooleanRMatcher<V, R> whenNext(boolean value, R1<? super V, ? extends R> action) {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            returnValue = action.$(this.value);
        }
        return this;
    }

    public <E extends Throwable> BooleanRMatcher<V, R> with(boolean value, RT1<? super V, ? extends R, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            isMatch = true;
            returnValue = action.$(this.value);
        }
        return this;
    }

    public <E extends Throwable> BooleanRMatcher<V, R> withNext(boolean value, RT1<? super V, ? extends R, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            returnValue = action.$(this.value);
        }
        return this;
    }

    public R orElse(R1<? super V, ? extends R> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            returnValue = action.$(this.value);
        }
        return returnValue;
    }

    public <E extends Throwable> R orWith(RT1<? super V, ? extends R, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch) {
            returnValue = action.$(this.value);
        }
        return returnValue;
    }
}
