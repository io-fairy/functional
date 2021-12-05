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

import com.iofairy.base.None;
import com.iofairy.lambda.*;
import com.iofairy.pattern.PatternIn;

import java.util.Objects;

/**
 * ActionNone VMatcher
 *
 * @since 0.0.1
 */
public class ActionNoneVMatcher<P> extends SimpleVInMatcher<None, P, P> {

    private final R1<? super P, Boolean> preAction;

    public ActionNoneVMatcher(None value, boolean isMatch, R1<? super P, Boolean> preAction) {
        super(value, isMatch);
        this.preAction = preAction;
    }

    public ActionNoneVMatcher(None value, R1<? super P, Boolean> preAction) {
        this(value, false, preAction);
    }

    @Override
    public ActionNoneVMatcher<P> when(P value, V1<? super P> action) {
        Objects.requireNonNull(action);
        if (!isMatch && preAction.$(value)) {
            isMatch = true;
            action.$(value);
        }
        return this;
    }

    @Override
    public ActionNoneVMatcher<P> whenNext(P value, V1<? super P> action) {
        Objects.requireNonNull(action);
        if (!isMatch && preAction.$(value)) {
            action.$(value);
        }
        return this;
    }

    @Override
    public <E extends Throwable> ActionNoneVMatcher<P> with(P value, VT1<? super P, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch && preAction.$(value)) {
            isMatch = true;
            action.$(value);
        }
        return this;
    }

    @Override
    public <E extends Throwable> ActionNoneVMatcher<P> withNext(P value, VT1<? super P, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch && preAction.$(value)) {
            action.$(value);
        }
        return this;
    }

    @Override
    public ActionNoneVMatcher<P> when(boolean value, V1<? super P> action) {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            isMatch = true;
            action.$(null);
        }
        return this;
    }

    @Override
    public ActionNoneVMatcher<P> whenNext(boolean value, V1<? super P> action) {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            action.$(null);
        }
        return this;
    }

    @Override
    public <E extends Throwable> ActionNoneVMatcher<P> with(boolean value, VT1<? super P, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            isMatch = true;
            action.$(null);
        }
        return this;
    }

    @Override
    public <E extends Throwable> ActionNoneVMatcher<P> withNext(boolean value, VT1<? super P, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            action.$(null);
        }
        return this;
    }

    @Override
    public ActionNoneVMatcher<P> when(PatternIn<P> values, V1<? super P> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (values == null) {
                if (preAction.$(null)) {
                    isMatch = true;
                    action.$(null);
                }
            } else {
                for (P v : values.getVs()) {
                    if (preAction.$(v)) {
                        isMatch = true;
                        action.$(v);
                        break;
                    }
                }
            }
        }
        return this;
    }

    @Override
    public ActionNoneVMatcher<P> whenNext(PatternIn<P> values, V1<? super P> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (values == null) {
                if (preAction.$(null)) {
                    action.$(null);
                }
            } else {
                for (P v : values.getVs()) {
                    if (preAction.$(v)) {
                        action.$(v);
                        break;
                    }
                }
            }
        }
        return this;
    }

    @Override
    public <E extends Throwable> ActionNoneVMatcher<P> with(PatternIn<P> values, VT1<? super P, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (values == null) {
                if (preAction.$(null)) {
                    isMatch = true;
                    action.$(null);
                }
            } else {
                for (P v : values.getVs()) {
                    if (preAction.$(v)) {
                        isMatch = true;
                        action.$(v);
                        break;
                    }
                }
            }
        }
        return this;
    }

    @Override
    public <E extends Throwable> ActionNoneVMatcher<P> withNext(PatternIn<P> values, VT1<? super P, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (values == null) {
                if (preAction.$(null)) {
                    action.$(null);
                }
            } else {
                for (P v : values.getVs()) {
                    if (preAction.$(v)) {
                        action.$(v);
                        break;
                    }
                }
            }
        }
        return this;
    }

    @Override
    public Void orElse(V1<? super None> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            action.$(null);
        }
        return returnValue;
    }

    @Override
    public <E extends Throwable> Void orWith(VT1<? super None, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch) {
            action.$(null);
        }
        return returnValue;
    }
}
