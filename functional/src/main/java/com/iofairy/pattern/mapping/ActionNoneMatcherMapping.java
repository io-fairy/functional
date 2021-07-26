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
package com.iofairy.pattern.mapping;

import com.iofairy.base.None;
import com.iofairy.lambda.*;
import com.iofairy.pattern.PatternIn;
import com.iofairy.pattern.matcher.ActionNoneRMatcher;
import com.iofairy.pattern.matcher.ActionNoneVMatcher;

/**
 * ActionNone Matcher Mapping
 * @since 0.0.1
 */
public class ActionNoneMatcherMapping<T> extends MatcherMapping<None>{

    protected final R1<T, Boolean> preAction;

    public ActionNoneMatcherMapping(None value, R1<T, Boolean> preAction) {
        super(value);
        this.preAction = preAction;
    }

    public ActionNoneVMatcher<T> when(T matchValue, V1<T> action) {
        ActionNoneVMatcher<T> actionNoneVMatcher = new ActionNoneVMatcher<>(value, preAction);
        return actionNoneVMatcher.when(matchValue, action);
    }

    public ActionNoneVMatcher<T> whenNext(T matchValue, V1<T> action) {
        ActionNoneVMatcher<T> actionNoneVMatcher = new ActionNoneVMatcher<>(value, preAction);
        return actionNoneVMatcher.whenNext(matchValue, action);
    }

    public <R> ActionNoneRMatcher<T, R> when(T matchValue, R1<T, R> action) {
        ActionNoneRMatcher<T, R> actionNoneRMatcher = new ActionNoneRMatcher<>(value, preAction);
        return actionNoneRMatcher.when(matchValue, action);
    }

    public <R> ActionNoneRMatcher<T, R> whenNext(T matchValue, R1<T, R> action) {
        ActionNoneRMatcher<T, R> actionNoneRMatcher = new ActionNoneRMatcher<>(value, preAction);
        return actionNoneRMatcher.whenNext(matchValue, action);
    }

    public ActionNoneVMatcher<T> when(PatternIn<T> matchValues, V1<T> action) {
        ActionNoneVMatcher<T> actionNoneVMatcher = new ActionNoneVMatcher<>(value, preAction);
        return actionNoneVMatcher.when(matchValues, action);
    }

    public ActionNoneVMatcher<T> whenNext(PatternIn<T> matchValues, V1<T> action) {
        ActionNoneVMatcher<T> actionNoneVMatcher = new ActionNoneVMatcher<>(value, preAction);
        return actionNoneVMatcher.whenNext(matchValues, action);
    }

    public <R> ActionNoneRMatcher<T, R> when(PatternIn<T> matchValues, R1<T, R> action) {
        ActionNoneRMatcher<T, R> actionNoneRMatcher = new ActionNoneRMatcher<>(value, preAction);
        return actionNoneRMatcher.when(matchValues, action);
    }

    public <R> ActionNoneRMatcher<T, R> whenNext(PatternIn<T> matchValues, R1<T, R> action) {
        ActionNoneRMatcher<T, R> actionNoneRMatcher = new ActionNoneRMatcher<>(value, preAction);
        return actionNoneRMatcher.whenNext(matchValues, action);
    }

    public ActionNoneVMatcher<T> when(boolean matchValue, V1<T> action) {
        ActionNoneVMatcher<T> actionNoneVMatcher = new ActionNoneVMatcher<>(value, preAction);
        return actionNoneVMatcher.when(matchValue, action);
    }

    public ActionNoneVMatcher<T> whenNext(boolean matchValue, V1<T> action) {
        ActionNoneVMatcher<T> actionNoneVMatcher = new ActionNoneVMatcher<>(value, preAction);
        return actionNoneVMatcher.whenNext(matchValue, action);
    }

    public <R> ActionNoneRMatcher<T, R> when(boolean matchValue, R1<T, R> action) {
        ActionNoneRMatcher<T, R> actionNoneRMatcher = new ActionNoneRMatcher<>(value, preAction);
        return actionNoneRMatcher.when(matchValue, action);
    }

    public <R> ActionNoneRMatcher<T, R> whenNext(boolean matchValue, R1<T, R> action) {
        ActionNoneRMatcher<T, R> actionNoneRMatcher = new ActionNoneRMatcher<>(value, preAction);
        return actionNoneRMatcher.whenNext(matchValue, action);
    }

    /*
     * ######################################################
     * ******************************************************
     * #####   MatcherMapping with throwing exception   #####
     * ******************************************************
     * ######################################################
     */
    public <E extends Throwable> ActionNoneVMatcher<T> when(T matchValue, VT0<E> action) throws E {
        ActionNoneVMatcher<T> actionNoneVMatcher = new ActionNoneVMatcher<>(value, preAction);
        return actionNoneVMatcher.when(matchValue, action);
    }

    public <E extends Throwable> ActionNoneVMatcher<T> whenNext(T matchValue, VT0<E> action) throws E {
        ActionNoneVMatcher<T> actionNoneVMatcher = new ActionNoneVMatcher<>(value, preAction);
        return actionNoneVMatcher.whenNext(matchValue, action);
    }

    public <R, E extends Throwable> ActionNoneRMatcher<T, R> when(T matchValue, RT0<R, E> action) throws E {
        ActionNoneRMatcher<T, R> actionNoneRMatcher = new ActionNoneRMatcher<>(value, preAction);
        return actionNoneRMatcher.when(matchValue, action);
    }

    public <R, E extends Throwable> ActionNoneRMatcher<T, R> whenNext(T matchValue, RT0<R, E> action) throws E {
        ActionNoneRMatcher<T, R> actionNoneRMatcher = new ActionNoneRMatcher<>(value, preAction);
        return actionNoneRMatcher.whenNext(matchValue, action);
    }

    public <E extends Throwable> ActionNoneVMatcher<T> when(PatternIn<T> matchValues, VT0<E> action) throws E {
        ActionNoneVMatcher<T> actionNoneVMatcher = new ActionNoneVMatcher<>(value, preAction);
        return actionNoneVMatcher.when(matchValues, action);
    }

    public <E extends Throwable> ActionNoneVMatcher<T> whenNext(PatternIn<T> matchValues, VT0<E> action) throws E {
        ActionNoneVMatcher<T> actionNoneVMatcher = new ActionNoneVMatcher<>(value, preAction);
        return actionNoneVMatcher.whenNext(matchValues, action);
    }

    public <R, E extends Throwable> ActionNoneRMatcher<T, R> when(PatternIn<T> matchValues, RT0<R, E> action) throws E {
        ActionNoneRMatcher<T, R> actionNoneRMatcher = new ActionNoneRMatcher<>(value, preAction);
        return actionNoneRMatcher.when(matchValues, action);
    }

    public <R, E extends Throwable> ActionNoneRMatcher<T, R> whenNext(PatternIn<T> matchValues, RT0<R, E> action) throws E {
        ActionNoneRMatcher<T, R> actionNoneRMatcher = new ActionNoneRMatcher<>(value, preAction);
        return actionNoneRMatcher.whenNext(matchValues, action);
    }

    public <E extends Throwable> ActionNoneVMatcher<T> when(boolean matchValue, VT0<E> action) throws E {
        ActionNoneVMatcher<T> actionNoneVMatcher = new ActionNoneVMatcher<>(value, preAction);
        return actionNoneVMatcher.when(matchValue, action);
    }

    public <E extends Throwable> ActionNoneVMatcher<T> whenNext(boolean matchValue, VT0<E> action) throws E {
        ActionNoneVMatcher<T> actionNoneVMatcher = new ActionNoneVMatcher<>(value, preAction);
        return actionNoneVMatcher.whenNext(matchValue, action);
    }

    public <R, E extends Throwable> ActionNoneRMatcher<T, R> when(boolean matchValue, RT0<R, E> action) throws E {
        ActionNoneRMatcher<T, R> actionNoneRMatcher = new ActionNoneRMatcher<>(value, preAction);
        return actionNoneRMatcher.when(matchValue, action);
    }

    public <R, E extends Throwable> ActionNoneRMatcher<T, R> whenNext(boolean matchValue, RT0<R, E> action) throws E {
        ActionNoneRMatcher<T, R> actionNoneRMatcher = new ActionNoneRMatcher<>(value, preAction);
        return actionNoneRMatcher.whenNext(matchValue, action);
    }

}
