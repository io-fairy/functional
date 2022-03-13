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
public class ActionValueMatcherMapping<V, T> extends PatternMatcherMapping<V> {

    protected final R1<? super T, V> preAction;

    public ActionValueMatcherMapping(V value, R1<? super T, V> preAction) {
        super(value);
        this.preAction = preAction;
    }

    public ActionValueVMatcher<V, T> when(T matchValue, V1<? super V> action) {
        ActionValueVMatcher<V, T> actionValueVMatcher = new ActionValueVMatcher<>(value, preAction);
        return actionValueVMatcher.when(matchValue, action);
    }

    public ActionValueVMatcher<V, T> whenNext(T matchValue, V1<? super V> action) {
        ActionValueVMatcher<V, T> actionValueVMatcher = new ActionValueVMatcher<>(value, preAction);
        return actionValueVMatcher.whenNext(matchValue, action);
    }

    public <R> ActionValueRMatcher<V, T, R> when(T matchValue, R1<? super V, ? extends R> action) {
        ActionValueRMatcher<V, T, R> actionValueRMatcher = new ActionValueRMatcher<>(value, preAction);
        return actionValueRMatcher.when(matchValue, action);
    }

    public <R> ActionValueRMatcher<V, T, R> whenNext(T matchValue, R1<? super V, ? extends R> action) {
        ActionValueRMatcher<V, T, R> actionValueRMatcher = new ActionValueRMatcher<>(value, preAction);
        return actionValueRMatcher.whenNext(matchValue, action);
    }

    public ActionValueVMatcher<V, T> when(PatternIn<T> matchValues, V1<? super V> action) {
        ActionValueVMatcher<V, T> actionValueVMatcher = new ActionValueVMatcher<>(value, preAction);
        return actionValueVMatcher.when(matchValues, action);
    }

    public ActionValueVMatcher<V, T> whenNext(PatternIn<T> matchValues, V1<? super V> action) {
        ActionValueVMatcher<V, T> actionValueVMatcher = new ActionValueVMatcher<>(value, preAction);
        return actionValueVMatcher.whenNext(matchValues, action);
    }

    public <R> ActionValueRMatcher<V, T, R> when(PatternIn<T> matchValues, R1<? super V, ? extends R> action) {
        ActionValueRMatcher<V, T, R> actionValueRMatcher = new ActionValueRMatcher<>(value, preAction);
        return actionValueRMatcher.when(matchValues, action);
    }

    public <R> ActionValueRMatcher<V, T, R> whenNext(PatternIn<T> matchValues, R1<? super V, ? extends R> action) {
        ActionValueRMatcher<V, T, R> actionValueRMatcher = new ActionValueRMatcher<>(value, preAction);
        return actionValueRMatcher.whenNext(matchValues, action);
    }

    public ActionValueVMatcher<V, T> when(boolean matchValue, V1<? super V> action) {
        ActionValueVMatcher<V, T> actionValueVMatcher = new ActionValueVMatcher<>(value, preAction);
        return actionValueVMatcher.when(matchValue, action);
    }

    public ActionValueVMatcher<V, T> whenNext(boolean matchValue, V1<? super V> action) {
        ActionValueVMatcher<V, T> actionValueVMatcher = new ActionValueVMatcher<>(value, preAction);
        return actionValueVMatcher.whenNext(matchValue, action);
    }

    public <R> ActionValueRMatcher<V, T, R> when(boolean matchValue, R1<? super V, ? extends R> action) {
        ActionValueRMatcher<V, T, R> actionValueRMatcher = new ActionValueRMatcher<>(value, preAction);
        return actionValueRMatcher.when(matchValue, action);
    }

    public <R> ActionValueRMatcher<V, T, R> whenNext(boolean matchValue, R1<? super V, ? extends R> action) {
        ActionValueRMatcher<V, T, R> actionValueRMatcher = new ActionValueRMatcher<>(value, preAction);
        return actionValueRMatcher.whenNext(matchValue, action);
    }

    /*
     * ######################################################
     * ******************************************************
     * #####   MatcherMapping with throwing exception   #####
     * ******************************************************
     * ######################################################
     */
    public <E extends Throwable> ActionValueVMatcher<V, T> with(T matchValue, VT1<? super V, E> action) throws E {
        ActionValueVMatcher<V, T> actionValueVMatcher = new ActionValueVMatcher<>(value, preAction);
        return actionValueVMatcher.with(matchValue, action);
    }

    public <E extends Throwable> ActionValueVMatcher<V, T> withNext(T matchValue, VT1<? super V, E> action) throws E {
        ActionValueVMatcher<V, T> actionValueVMatcher = new ActionValueVMatcher<>(value, preAction);
        return actionValueVMatcher.withNext(matchValue, action);
    }

    public <R, E extends Throwable> ActionValueRMatcher<V, T, R> with(T matchValue, RT1<? super V, ? extends R, E> action) throws E {
        ActionValueRMatcher<V, T, R> actionValueRMatcher = new ActionValueRMatcher<>(value, preAction);
        return actionValueRMatcher.with(matchValue, action);
    }

    public <R, E extends Throwable> ActionValueRMatcher<V, T, R> withNext(T matchValue, RT1<? super V, ? extends R, E> action) throws E {
        ActionValueRMatcher<V, T, R> actionValueRMatcher = new ActionValueRMatcher<>(value, preAction);
        return actionValueRMatcher.withNext(matchValue, action);
    }

    public <E extends Throwable> ActionValueVMatcher<V, T> with(PatternIn<T> matchValues, VT1<? super V, E> action) throws E {
        ActionValueVMatcher<V, T> actionValueVMatcher = new ActionValueVMatcher<>(value, preAction);
        return actionValueVMatcher.with(matchValues, action);
    }

    public <E extends Throwable> ActionValueVMatcher<V, T> withNext(PatternIn<T> matchValues, VT1<? super V, E> action) throws E {
        ActionValueVMatcher<V, T> actionValueVMatcher = new ActionValueVMatcher<>(value, preAction);
        return actionValueVMatcher.withNext(matchValues, action);
    }

    public <R, E extends Throwable> ActionValueRMatcher<V, T, R> with(PatternIn<T> matchValues, RT1<? super V, ? extends R, E> action) throws E {
        ActionValueRMatcher<V, T, R> actionValueRMatcher = new ActionValueRMatcher<>(value, preAction);
        return actionValueRMatcher.with(matchValues, action);
    }

    public <R, E extends Throwable> ActionValueRMatcher<V, T, R> withNext(PatternIn<T> matchValues, RT1<? super V, ? extends R, E> action) throws E {
        ActionValueRMatcher<V, T, R> actionValueRMatcher = new ActionValueRMatcher<>(value, preAction);
        return actionValueRMatcher.withNext(matchValues, action);
    }

    public <E extends Throwable> ActionValueVMatcher<V, T> with(boolean matchValue, VT1<? super V, E> action) throws E {
        ActionValueVMatcher<V, T> actionValueVMatcher = new ActionValueVMatcher<>(value, preAction);
        return actionValueVMatcher.with(matchValue, action);
    }

    public <E extends Throwable> ActionValueVMatcher<V, T> withNext(boolean matchValue, VT1<? super V, E> action) throws E {
        ActionValueVMatcher<V, T> actionValueVMatcher = new ActionValueVMatcher<>(value, preAction);
        return actionValueVMatcher.withNext(matchValue, action);
    }

    public <R, E extends Throwable> ActionValueRMatcher<V, T, R> with(boolean matchValue, RT1<? super V, ? extends R, E> action) throws E {
        ActionValueRMatcher<V, T, R> actionValueRMatcher = new ActionValueRMatcher<>(value, preAction);
        return actionValueRMatcher.with(matchValue, action);
    }

    public <R, E extends Throwable> ActionValueRMatcher<V, T, R> withNext(boolean matchValue, RT1<? super V, ? extends R, E> action) throws E {
        ActionValueRMatcher<V, T, R> actionValueRMatcher = new ActionValueRMatcher<>(value, preAction);
        return actionValueRMatcher.withNext(matchValue, action);
    }
    
}
