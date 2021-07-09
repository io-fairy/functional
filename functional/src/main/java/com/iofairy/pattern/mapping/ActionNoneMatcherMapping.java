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
import com.iofairy.lambda.R1;
import com.iofairy.lambda.V1;
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
        ActionNoneVMatcher<T> ActionNoneVMatcher = new ActionNoneVMatcher<>(value, preAction);
        return ActionNoneVMatcher.when(matchValue, action);
    }

    public ActionNoneVMatcher<T> whenNext(T matchValue, V1<T> action) {
        ActionNoneVMatcher<T> ActionNoneVMatcher = new ActionNoneVMatcher<>(value, preAction);
        return ActionNoneVMatcher.whenNext(matchValue, action);
    }

    public <R> ActionNoneRMatcher<T, R> when(T matchValue, R1<T, R> action) {
        ActionNoneRMatcher<T, R> ActionNoneRMatcher = new ActionNoneRMatcher<>(value, preAction);
        return ActionNoneRMatcher.when(matchValue, action);
    }

    public <R> ActionNoneRMatcher<T, R> whenNext(T matchValue, R1<T, R> action) {
        ActionNoneRMatcher<T, R> ActionNoneRMatcher = new ActionNoneRMatcher<>(value, preAction);
        return ActionNoneRMatcher.whenNext(matchValue, action);
    }

    public ActionNoneVMatcher<T> when(PatternIn<T> matchValues, V1<T> action) {
        ActionNoneVMatcher<T> ActionNoneVMatcher = new ActionNoneVMatcher<>(value, preAction);
        return ActionNoneVMatcher.when(matchValues, action);
    }

    public ActionNoneVMatcher<T> whenNext(PatternIn<T> matchValues, V1<T> action) {
        ActionNoneVMatcher<T> ActionNoneVMatcher = new ActionNoneVMatcher<>(value, preAction);
        return ActionNoneVMatcher.whenNext(matchValues, action);
    }

    public <R> ActionNoneRMatcher<T, R> when(PatternIn<T> matchValues, R1<T, R> action) {
        ActionNoneRMatcher<T, R> ActionNoneRMatcher = new ActionNoneRMatcher<>(value, preAction);
        return ActionNoneRMatcher.when(matchValues, action);
    }

    public <R> ActionNoneRMatcher<T, R> whenNext(PatternIn<T> matchValues, R1<T, R> action) {
        ActionNoneRMatcher<T, R> ActionNoneRMatcher = new ActionNoneRMatcher<>(value, preAction);
        return ActionNoneRMatcher.whenNext(matchValues, action);
    }

    public ActionNoneVMatcher<T> when(boolean matchValue, V1<T> action) {
        ActionNoneVMatcher<T> ActionNoneVMatcher = new ActionNoneVMatcher<>(value, preAction);
        return ActionNoneVMatcher.when(matchValue, action);
    }

    public ActionNoneVMatcher<T> whenNext(boolean matchValue, V1<T> action) {
        ActionNoneVMatcher<T> ActionNoneVMatcher = new ActionNoneVMatcher<>(value, preAction);
        return ActionNoneVMatcher.whenNext(matchValue, action);
    }

    public <R> ActionNoneRMatcher<T, R> when(boolean matchValue, R1<T, R> action) {
        ActionNoneRMatcher<T, R> ActionNoneRMatcher = new ActionNoneRMatcher<>(value, preAction);
        return ActionNoneRMatcher.when(matchValue, action);
    }

    public <R> ActionNoneRMatcher<T, R> whenNext(boolean matchValue, R1<T, R> action) {
        ActionNoneRMatcher<T, R> ActionNoneRMatcher = new ActionNoneRMatcher<>(value, preAction);
        return ActionNoneRMatcher.whenNext(matchValue, action);
    }

}
