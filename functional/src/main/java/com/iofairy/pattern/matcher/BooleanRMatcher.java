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

import java.util.Objects;

/**
 * Boolean Matcher with Return value
 * @since 0.0.1
 */
public class BooleanRMatcher<V, R> extends SimpleRMatcher<V, Boolean, V, R> {

    public BooleanRMatcher(V value, boolean isMatch) {
        super(value, isMatch);
    }

    public BooleanRMatcher(V value) {
        this(value, false);
    }

    @Override
    public BooleanRMatcher<V, R> when(Boolean value, R1<V, R> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (value == null || this.value == null) {
                if (this.value == null && value == null) {
                    isMatch = true;
                    returnValue = action.$(this.value);
                }
            } else if (value) {
                isMatch = true;
                returnValue = action.$(this.value);
            }
        }
        return this;
    }

    @Override
    public BooleanRMatcher<V, R> whenNext(Boolean value, R1<V, R> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (value == null || this.value == null) {
                if (this.value == null && value == null)
                    returnValue = action.$(this.value);
            } else if (value) returnValue = action.$(this.value);
        }
        return this;
    }

    @Override
    public R orElse(R1<V, R> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            returnValue = action.$(this.value);
        }
        return returnValue;
    }
}
