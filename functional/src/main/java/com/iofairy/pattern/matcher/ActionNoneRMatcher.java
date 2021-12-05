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
import com.iofairy.lambda.R1;
import com.iofairy.lambda.RT1;
import com.iofairy.pattern.PatternIn;

import java.util.Objects;

/**
 * ActionNone RMatcher
 *
 * @since 0.0.1
 */
public class ActionNoneRMatcher<P, R> extends SimpleRInMatcher<None, P, P, R> {

    private final R1<? super P, Boolean> preAction;

    public ActionNoneRMatcher(None value, boolean isMatch, R1<? super P, Boolean> preAction) {
        super(value, isMatch);
        this.preAction = preAction;
    }

    public ActionNoneRMatcher(None value, R1<? super P, Boolean> preAction) {
        this(value, false, preAction);
    }

    @Override
    public ActionNoneRMatcher<P, R> when(P value, R1<? super P, ? extends R> action) {
        Objects.requireNonNull(action);
        if (!isMatch && preAction.$(value)) {
            isMatch = true;
            returnValue = action.$(value);
        }
        return this;
    }

    @Override
    public ActionNoneRMatcher<P, R> whenNext(P value, R1<? super P, ? extends R> action) {
        Objects.requireNonNull(action);
        if (!isMatch && preAction.$(value)) {
            returnValue = action.$(value);
        }
        return this;
    }

    @Override
    public <E extends Throwable> ActionNoneRMatcher<P, R> with(P value, RT1<? super P, ? extends R, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch && preAction.$(value)) {
            isMatch = true;
            returnValue = action.$(value);
        }
        return this;
    }

    @Override
    public <E extends Throwable> ActionNoneRMatcher<P, R> withNext(P value, RT1<? super P, ? extends R, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch && preAction.$(value)) {
            returnValue = action.$(value);
        }
        return this;
    }

    @Override
    public ActionNoneRMatcher<P, R> when(boolean value, R1<? super P, ? extends R> action) {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            isMatch = true;
            returnValue = action.$(null);
        }
        return this;
    }

    @Override
    public ActionNoneRMatcher<P, R> whenNext(boolean value, R1<? super P, ? extends R> action) {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            returnValue = action.$(null);
        }
        return this;
    }

    @Override
    public <E extends Throwable> ActionNoneRMatcher<P, R> with(boolean value, RT1<? super P, ? extends R, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            isMatch = true;
            returnValue = action.$(null);
        }
        return this;
    }

    @Override
    public <E extends Throwable> ActionNoneRMatcher<P, R> withNext(boolean value, RT1<? super P, ? extends R, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            returnValue = action.$(null);
        }
        return this;
    }

    @Override
    public ActionNoneRMatcher<P, R> when(PatternIn<P> values, R1<? super P, ? extends R> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (values == null) {
                if (preAction.$(null)) {
                    isMatch = true;
                    returnValue = action.$(null);
                }
            } else {
                for (P v : values.getVs()) {
                    if (preAction.$(v)) {
                        isMatch = true;
                        returnValue = action.$(v);
                        break;
                    }
                }
            }
        }
        return this;
    }

    @Override
    public ActionNoneRMatcher<P, R> whenNext(PatternIn<P> values, R1<? super P, ? extends R> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (values == null) {
                if (preAction.$(null)) {
                    returnValue = action.$(null);
                }
            } else {
                for (P v : values.getVs()) {
                    if (preAction.$(v)) {
                        returnValue = action.$(v);
                        break;
                    }
                }
            }
        }
        return this;
    }

    @Override
    public <E extends Throwable> ActionNoneRMatcher<P, R> with(PatternIn<P> values, RT1<? super P, ? extends R, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (values == null) {
                if (preAction.$(null)) {
                    isMatch = true;
                    returnValue = action.$(null);
                }
            } else {
                for (P v : values.getVs()) {
                    if (preAction.$(v)) {
                        isMatch = true;
                        returnValue = action.$(v);
                        break;
                    }
                }
            }
        }
        return this;
    }

    @Override
    public <E extends Throwable> ActionNoneRMatcher<P, R> withNext(PatternIn<P> values, RT1<? super P, ? extends R, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (values == null) {
                if (preAction.$(null)) {
                    returnValue = action.$(null);
                }
            } else {
                for (P v : values.getVs()) {
                    if (preAction.$(v)) {
                        returnValue = action.$(v);
                        break;
                    }
                }
            }
        }
        return this;
    }

    @Override
    public R orElse(R1<? super None, ? extends R> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            returnValue = action.$(null);
        }
        return returnValue;
    }

    @Override
    public <E extends Throwable> R orWith(RT1<? super None, ? extends R, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch) {
            returnValue = action.$(null);
        }
        return returnValue;
    }
}
