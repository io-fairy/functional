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

import com.iofairy.lambda.*;
import com.iofairy.pattern.PatternIn;
import com.iofairy.pattern.matcher.ActionValueRMatcher;
import com.iofairy.pattern.matcher.ActionValueVMatcher;

/**
 * ActionValue Matcher Mapping
 * @since 0.0.1
 */
public class ActionValueMatcherMapping<V, T> extends MatcherMapping<V> {

    protected final R1<T, V> preAction;

    public ActionValueMatcherMapping(V value, R1<T, V> preAction) {
        super(value);
        this.preAction = preAction;
    }

    public ActionValueVMatcher<V, T> when(T matchValue, V1<V> action) {
        ActionValueVMatcher<V, T> ActionValueVMatcher = new ActionValueVMatcher<>(value, preAction);
        return ActionValueVMatcher.when(matchValue, action);
    }

    public ActionValueVMatcher<V, T> whenNext(T matchValue, V1<V> action) {
        ActionValueVMatcher<V, T> ActionValueVMatcher = new ActionValueVMatcher<>(value, preAction);
        return ActionValueVMatcher.whenNext(matchValue, action);
    }

    public <R> ActionValueRMatcher<V, T, R> when(T matchValue, R1<V, R> action) {
        ActionValueRMatcher<V, T, R> ActionValueRMatcher = new ActionValueRMatcher<>(value, preAction);
        return ActionValueRMatcher.when(matchValue, action);
    }

    public <R> ActionValueRMatcher<V, T, R> whenNext(T matchValue, R1<V, R> action) {
        ActionValueRMatcher<V, T, R> ActionValueRMatcher = new ActionValueRMatcher<>(value, preAction);
        return ActionValueRMatcher.whenNext(matchValue, action);
    }

    public ActionValueVMatcher<V, T> when(PatternIn<T> matchValues, V1<V> action) {
        ActionValueVMatcher<V, T> ActionValueVMatcher = new ActionValueVMatcher<>(value, preAction);
        return ActionValueVMatcher.when(matchValues, action);
    }

    public ActionValueVMatcher<V, T> whenNext(PatternIn<T> matchValues, V1<V> action) {
        ActionValueVMatcher<V, T> ActionValueVMatcher = new ActionValueVMatcher<>(value, preAction);
        return ActionValueVMatcher.whenNext(matchValues, action);
    }

    public <R> ActionValueRMatcher<V, T, R> when(PatternIn<T> matchValues, R1<V, R> action) {
        ActionValueRMatcher<V, T, R> ActionValueRMatcher = new ActionValueRMatcher<>(value, preAction);
        return ActionValueRMatcher.when(matchValues, action);
    }

    public <R> ActionValueRMatcher<V, T, R> whenNext(PatternIn<T> matchValues, R1<V, R> action) {
        ActionValueRMatcher<V, T, R> ActionValueRMatcher = new ActionValueRMatcher<>(value, preAction);
        return ActionValueRMatcher.whenNext(matchValues, action);
    }

    /*
     * ######################################################
     * ******************************************************
     * #####   MatcherMapping with throwing exception   #####
     * ******************************************************
     * ######################################################
     */
    public <E extends Throwable> ActionValueVMatcher<V, T> when(T matchValue, VT0<E> action) throws E {
        ActionValueVMatcher<V, T> ActionValueVMatcher = new ActionValueVMatcher<>(value, preAction);
        return ActionValueVMatcher.when(matchValue, action);
    }

    public <E extends Throwable> ActionValueVMatcher<V, T> whenNext(T matchValue, VT0<E> action) throws E {
        ActionValueVMatcher<V, T> ActionValueVMatcher = new ActionValueVMatcher<>(value, preAction);
        return ActionValueVMatcher.whenNext(matchValue, action);
    }

    public <R, E extends Throwable> ActionValueRMatcher<V, T, R> when(T matchValue, RT0<R, E> action) throws E {
        ActionValueRMatcher<V, T, R> ActionValueRMatcher = new ActionValueRMatcher<>(value, preAction);
        return ActionValueRMatcher.when(matchValue, action);
    }

    public <R, E extends Throwable> ActionValueRMatcher<V, T, R> whenNext(T matchValue, RT0<R, E> action) throws E {
        ActionValueRMatcher<V, T, R> ActionValueRMatcher = new ActionValueRMatcher<>(value, preAction);
        return ActionValueRMatcher.whenNext(matchValue, action);
    }

    public <E extends Throwable> ActionValueVMatcher<V, T> when(PatternIn<T> matchValues, VT0<E> action) throws E {
        ActionValueVMatcher<V, T> ActionValueVMatcher = new ActionValueVMatcher<>(value, preAction);
        return ActionValueVMatcher.when(matchValues, action);
    }

    public <E extends Throwable> ActionValueVMatcher<V, T> whenNext(PatternIn<T> matchValues, VT0<E> action) throws E {
        ActionValueVMatcher<V, T> ActionValueVMatcher = new ActionValueVMatcher<>(value, preAction);
        return ActionValueVMatcher.whenNext(matchValues, action);
    }

    public <R, E extends Throwable> ActionValueRMatcher<V, T, R> when(PatternIn<T> matchValues, RT0<R, E> action) throws E {
        ActionValueRMatcher<V, T, R> ActionValueRMatcher = new ActionValueRMatcher<>(value, preAction);
        return ActionValueRMatcher.when(matchValues, action);
    }

    public <R, E extends Throwable> ActionValueRMatcher<V, T, R> whenNext(PatternIn<T> matchValues, RT0<R, E> action) throws E {
        ActionValueRMatcher<V, T, R> ActionValueRMatcher = new ActionValueRMatcher<>(value, preAction);
        return ActionValueRMatcher.whenNext(matchValues, action);
    }
}
