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

import java.util.Objects;

/**
 * ActionValue RMatcher
 *
 * @since 0.0.1
 */
public class ActionValueRMatcher<V, P, R> extends SimpleRInMatcher<V, P, V, R> {

    private final R1<? super P, V> preAction;

    public ActionValueRMatcher(V value, boolean isMatch, R1<? super P, V> preAction) {
        super(value, isMatch);
        this.preAction = preAction;
    }

    public ActionValueRMatcher(V value, R1<? super P, V> preAction) {
        this(value, false, preAction);
    }

    @Override
    public ActionValueRMatcher<V, P, R> when(P value, R1<? super V, ? extends R> action) {
        Objects.requireNonNull(action);
        if (!isMatch && Objects.equals(preAction.$(value), this.value)) {
            isMatch = true;
            returnValue = action.$(this.value);
        }
        return this;
    }

    @Override
    public ActionValueRMatcher<V, P, R> whenNext(P value, R1<? super V, ? extends R> action) {
        Objects.requireNonNull(action);
        if (!isMatch && Objects.equals(preAction.$(value), this.value)) {
            returnValue = action.$(this.value);
        }
        return this;
    }

    @Override
    public <E extends Throwable> ActionValueRMatcher<V, P, R> with(P value, RT1<? super V, ? extends R, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch && Objects.equals(preAction.$(value), this.value)) {
            isMatch = true;
            returnValue = action.$(this.value);
        }
        return this;
    }

    @Override
    public <E extends Throwable> ActionValueRMatcher<V, P, R> withNext(P value, RT1<? super V, ? extends R, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch && Objects.equals(preAction.$(value), this.value)) {
            returnValue = action.$(this.value);
        }
        return this;
    }

    @Override
    public ActionValueRMatcher<V, P, R> when(boolean value, R1<? super V, ? extends R> action) {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            isMatch = true;
            returnValue = action.$(this.value);
        }
        return this;
    }

    @Override
    public ActionValueRMatcher<V, P, R> whenNext(boolean value, R1<? super V, ? extends R> action) {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            returnValue = action.$(this.value);
        }
        return this;
    }

    @Override
    public <E extends Throwable> ActionValueRMatcher<V, P, R> with(boolean value, RT1<? super V, ? extends R, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            isMatch = true;
            returnValue = action.$(this.value);
        }
        return this;
    }

    @Override
    public <E extends Throwable> ActionValueRMatcher<V, P, R> withNext(boolean value, RT1<? super V, ? extends R, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            returnValue = action.$(this.value);
        }
        return this;
    }

    @Override
    public ActionValueRMatcher<V, P, R> when(PatternIn<P> values, R1<? super V, ? extends R> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (values == null) {
                if (Objects.equals(this.value, preAction.$(null))) {
                    isMatch = true;
                    returnValue = action.$(this.value);
                }
            } else {
                for (P v : values.getVs()) {
                    if (Objects.equals(this.value, preAction.$(v))) {
                        isMatch = true;
                        returnValue = action.$(this.value);
                        break;
                    }
                }
            }
        }
        return this;
    }

    @Override
    public ActionValueRMatcher<V, P, R> whenNext(PatternIn<P> values, R1<? super V, ? extends R> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (values == null) {
                if (Objects.equals(this.value, preAction.$(null))) {
                    returnValue = action.$(this.value);
                }
            } else {
                for (P v : values.getVs()) {
                    if (Objects.equals(this.value, preAction.$(v))) {
                        returnValue = action.$(this.value);
                        break;
                    }
                }
            }
        }
        return this;
    }

    @Override
    public <E extends Throwable> ActionValueRMatcher<V, P, R> with(PatternIn<P> values, RT1<? super V, ? extends R, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (values == null) {
                if (Objects.equals(this.value, preAction.$(null))) {
                    isMatch = true;
                    returnValue = action.$(this.value);
                }
            } else {
                for (P v : values.getVs()) {
                    if (Objects.equals(this.value, preAction.$(v))) {
                        isMatch = true;
                        returnValue = action.$(this.value);
                        break;
                    }
                }
            }
        }
        return this;
    }

    @Override
    public <E extends Throwable> ActionValueRMatcher<V, P, R> withNext(PatternIn<P> values, RT1<? super V, ? extends R, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (values == null) {
                if (Objects.equals(this.value, preAction.$(null))) {
                    returnValue = action.$(this.value);
                }
            } else {
                for (P v : values.getVs()) {
                    if (Objects.equals(this.value, preAction.$(v))) {
                        returnValue = action.$(this.value);
                        break;
                    }
                }
            }
        }
        return this;
    }

    @Override
    public R orElse(R1<? super V, ? extends R> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            returnValue = action.$(this.value);
        }
        return returnValue;
    }

    @Override
    public <E extends Throwable> R orWith(RT1<? super V, ? extends R, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch) {
            returnValue = action.$(this.value);
        }
        return returnValue;
    }
}
