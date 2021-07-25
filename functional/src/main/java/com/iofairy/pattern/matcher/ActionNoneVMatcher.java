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

    private final R1<P, Boolean> preAction;

    public ActionNoneVMatcher(None value, boolean isMatch, R1<P, Boolean> preAction) {
        super(value, isMatch);
        this.preAction = preAction;
    }

    public ActionNoneVMatcher(None value, R1<P, Boolean> preAction) {
        this(value, false, preAction);
    }

    @Override
    public ActionNoneVMatcher<P> when(P value, V1<P> action) {
        Objects.requireNonNull(action);
        if (!isMatch && preAction.$(value)) {
            isMatch = true;
            action.$(value);
        }
        return this;
    }

    @Override
    public ActionNoneVMatcher<P> whenNext(P value, V1<P> action) {
        Objects.requireNonNull(action);
        if (!isMatch && preAction.$(value)) {
            action.$(value);
        }
        return this;
    }

    @Override
    public <E extends Throwable> ActionNoneVMatcher<P> when(P value, VT0<E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch && preAction.$(value)) {
            isMatch = true;
            action.$();
        }
        return this;
    }

    @Override
    public <E extends Throwable> ActionNoneVMatcher<P> whenNext(P value, VT0<E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch && preAction.$(value)) {
            action.$();
        }
        return this;
    }

    public ActionNoneVMatcher<P> when(boolean value, V1<P> action) {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            isMatch = true;
            action.$(null);
        }
        return this;
    }

    public ActionNoneVMatcher<P> whenNext(boolean value, V1<P> action) {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            action.$(null);
        }
        return this;
    }

    public <E extends Throwable> ActionNoneVMatcher<P> when(boolean value, VT0<E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            isMatch = true;
            action.$();
        }
        return this;
    }

    public <E extends Throwable> ActionNoneVMatcher<P> whenNext(boolean value, VT0<E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            action.$();
        }
        return this;
    }

    @Override
    public ActionNoneVMatcher<P> when(PatternIn<P> values, V1<P> action) {
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
    public ActionNoneVMatcher<P> whenNext(PatternIn<P> values, V1<P> action) {
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
    public <E extends Throwable> ActionNoneVMatcher<P> when(PatternIn<P> values, VT0<E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (values == null) {
                if (preAction.$(null)) {
                    isMatch = true;
                    action.$();
                }
            } else {
                for (P v : values.getVs()) {
                    if (preAction.$(v)) {
                        isMatch = true;
                        action.$();
                        break;
                    }
                }
            }
        }
        return this;
    }

    @Override
    public <E extends Throwable> ActionNoneVMatcher<P> whenNext(PatternIn<P> values, VT0<E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (values == null) {
                if (preAction.$(null)) {
                    action.$();
                }
            } else {
                for (P v : values.getVs()) {
                    if (preAction.$(v)) {
                        action.$();
                        break;
                    }
                }
            }
        }
        return this;
    }

    @Override
    public Void orElse(V1<None> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            action.$(null);
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
