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
import com.iofairy.lambda.RT0;
import com.iofairy.pattern.PatternIn;

import java.util.Objects;

/**
 * Value Matcher with return value
 *
 * @since 0.0.1
 */
public class ValueRMatcher<V, R> extends SimpleRInMatcher<V, V, V, R> {

    public ValueRMatcher(V value, boolean isMatch) {
        super(value, isMatch);
    }

    public ValueRMatcher(V value) {
        this(value, false);
    }

    @Override
    public ValueRMatcher<V, R> when(V value, R1<V, R> action) {
        Objects.requireNonNull(action);
        if (!isMatch && Objects.equals(this.value, value)) {
            isMatch = true;
            returnValue = action.$(this.value);
        }
        return this;
    }

    @Override
    public ValueRMatcher<V, R> whenNext(V value, R1<V, R> action) {
        Objects.requireNonNull(action);
        if (!isMatch && Objects.equals(this.value, value)) {
            returnValue = action.$(this.value);
        }
        return this;
    }

    @Override
    public <E extends Throwable> ValueRMatcher<V, R> when(V value, RT0<R, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch && Objects.equals(this.value, value)) {
            isMatch = true;
            returnValue = action.$();
        }
        return this;
    }

    @Override
    public <E extends Throwable> ValueRMatcher<V, R> whenNext(V value, RT0<R, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch && Objects.equals(this.value, value)) {
            returnValue = action.$();
        }
        return this;
    }

    @Override
    public ValueRMatcher<V, R> when(boolean value, R1<V, R> action) {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            isMatch = true;
            returnValue = action.$(this.value);
        }
        return this;
    }

    @Override
    public ValueRMatcher<V, R> whenNext(boolean value, R1<V, R> action) {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            returnValue = action.$(this.value);
        }
        return this;
    }

    @Override
    public <E extends Throwable> ValueRMatcher<V, R> when(boolean value, RT0<R, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            isMatch = true;
            returnValue = action.$();
        }
        return this;
    }

    @Override
    public <E extends Throwable> ValueRMatcher<V, R> whenNext(boolean value, RT0<R, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            returnValue = action.$();
        }
        return this;
    }

    @Override
    public ValueRMatcher<V, R> when(PatternIn<V> values, R1<V, R> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (this.value == null) {
                if (values == null || values.getVs().contains(this.value)) {
                    isMatch = true;
                    returnValue = action.$(this.value);
                }
            }else {
                if (values != null && values.getVs().contains(this.value)) {
                    isMatch = true;
                    returnValue = action.$(this.value);
                }
            }
        }
        return this;
    }

    @Override
    public ValueRMatcher<V, R> whenNext(PatternIn<V> values, R1<V, R> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (this.value == null) {
                if (values == null || values.getVs().contains(this.value))
                    returnValue = action.$(this.value);
            }else {
                if (values != null && values.getVs().contains(this.value))
                    returnValue = action.$(this.value);
            }
        }
        return this;
    }

    @Override
    public <E extends Throwable> ValueRMatcher<V, R> when(PatternIn<V> values, RT0<R, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (this.value == null) {
                if (values == null || values.getVs().contains(this.value)) {
                    isMatch = true;
                    returnValue = action.$();
                }
            }else {
                if (values != null && values.getVs().contains(this.value)) {
                    isMatch = true;
                    returnValue = action.$();
                }
            }
        }
        return this;
    }

    @Override
    public <E extends Throwable> ValueRMatcher<V, R> whenNext(PatternIn<V> values, RT0<R, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (this.value == null) {
                if (values == null || values.getVs().contains(this.value))
                    returnValue = action.$();
            }else {
                if (values != null && values.getVs().contains(this.value))
                    returnValue = action.$();
            }
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

    @Override
    public <E extends Throwable> R orElse(RT0<R, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch) {
            returnValue = action.$();
        }
        return returnValue;
    }

}
