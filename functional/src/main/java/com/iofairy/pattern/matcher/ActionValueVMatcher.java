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
import com.iofairy.lambda.V1;
import com.iofairy.pattern.PatternIn;

import java.util.Objects;

/**
 * ActionValue VMatcher
 *
 * @since 0.0.1
 */
public class ActionValueVMatcher<V, P> extends SimpleVMatcher<V, P, V> {

    private final R1<P, V> preAction;

    public ActionValueVMatcher(V value, boolean isMatch, R1<P, V> preAction) {
        super(value, isMatch);
        this.preAction = preAction;
    }

    public ActionValueVMatcher(V value, R1<P, V> preAction) {
        this(value, false, preAction);
    }

    @Override
    public ActionValueVMatcher<V, P> when(P value, V1<V> action) {
        Objects.requireNonNull(action);
        if (!isMatch && Objects.equals(preAction.$(value), this.value)) {
            isMatch = true;
            action.$(this.value);
        }
        return this;
    }

    @Override
    public ActionValueVMatcher<V, P> whenNext(P value, V1<V> action) {
        Objects.requireNonNull(action);
        if (!isMatch && Objects.equals(preAction.$(value), this.value)) {
            action.$(this.value);
        }
        return this;
    }

    public ActionValueVMatcher<V, P> when(PatternIn<P> values, V1<V> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (values == null) {
                if (Objects.equals(this.value, preAction.$(null))) {
                    isMatch = true;
                    action.$(this.value);
                }
            } else {
                for (P v : values.getVs()) {
                    if (Objects.equals(this.value, preAction.$(v))) {
                        isMatch = true;
                        action.$(this.value);
                        break;
                    }
                }
            }
        }
        return this;
    }

    public ActionValueVMatcher<V, P> whenNext(PatternIn<P> values, V1<V> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (values == null) {
                if (Objects.equals(this.value, preAction.$(null))) {
                    action.$(this.value);
                }
            } else {
                for (P v : values.getVs()) {
                    if (Objects.equals(this.value, preAction.$(v))) {
                        action.$(this.value);
                        break;
                    }
                }
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
}
