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
import java.util.Objects;

/**
 * Value Matcher with void
 *
 * @since 0.0.1
 */
public class ValueVMatcher<V> extends SimpleVInMatcher<V, V, V> {

    public ValueVMatcher(V value, boolean isMatch) {
        super(value, isMatch);
    }

    public ValueVMatcher(V value) {
        this(value, false);
    }

    @Override
    public ValueVMatcher<V> when(V value, V1<V> action) {
        Objects.requireNonNull(action);
        if (!isMatch && Objects.equals(this.value, value)) {
            isMatch = true;
            action.$(this.value);
        }
        return this;
    }

    @Override
    public ValueVMatcher<V> whenNext(V value, V1<V> action) {
        Objects.requireNonNull(action);
        if (!isMatch && Objects.equals(this.value, value)) {
            action.$(this.value);
        }
        return this;
    }

    @Override
    public <E extends Throwable> ValueVMatcher<V> when(V value, VT0<E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch && Objects.equals(this.value, value)) {
            isMatch = true;
            action.$();
        }
        return this;
    }

    @Override
    public <E extends Throwable> ValueVMatcher<V> whenNext(V value, VT0<E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch && Objects.equals(this.value, value)) {
            action.$();
        }
        return this;
    }

    @Override
    public ValueVMatcher<V> when(boolean value, V1<V> action) {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            isMatch = true;
            action.$(this.value);
        }
        return this;
    }

    @Override
    public ValueVMatcher<V> whenNext(boolean value, V1<V> action) {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            action.$(this.value);
        }
        return this;
    }

    @Override
    public <E extends Throwable> ValueVMatcher<V> when(boolean value, VT0<E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            isMatch = true;
            action.$();
        }
        return this;
    }

    @Override
    public <E extends Throwable> ValueVMatcher<V> whenNext(boolean value, VT0<E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            action.$();
        }
        return this;
    }

    @Override
    public ValueVMatcher<V> when(PatternIn<V> values, V1<V> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (this.value == null) {
                if (values == null || values.getVs().contains(this.value)) {
                    isMatch = true;
                    action.$(this.value);
                }
            }else {
                if (values != null && values.getVs().contains(this.value)) {
                    isMatch = true;
                    action.$(this.value);
                }
            }
        }
        return this;
    }

    @Override
    public ValueVMatcher<V> whenNext(PatternIn<V> values, V1<V> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (this.value == null) {
                if (values == null || values.getVs().contains(this.value)) action.$(this.value);
            }else {
                if (values != null && values.getVs().contains(this.value)) action.$(this.value);
            }
        }
        return this;
    }

    @Override
    public <E extends Throwable> ValueVMatcher<V> when(PatternIn<V> values, VT0<E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (this.value == null) {
                if (values == null || values.getVs().contains(this.value)) {
                    isMatch = true;
                    action.$();
                }
            }else {
                if (values != null && values.getVs().contains(this.value)) {
                    isMatch = true;
                    action.$();
                }
            }
        }
        return this;
    }

    @Override
    public <E extends Throwable> ValueVMatcher<V> whenNext(PatternIn<V> values, VT0<E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (this.value == null) {
                if (values == null || values.getVs().contains(this.value)) action.$();
            }else {
                if (values != null && values.getVs().contains(this.value)) action.$();
            }
        }
        return this;
    }

    @Override
    public Void orElse(V1<V> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            action.$(this.value);
        }
        return returnValue;
    }

    @Override
    public <E extends Throwable> Void orElse(VT0<E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch) {
            action.$();
        }
        return returnValue;
    }

}
