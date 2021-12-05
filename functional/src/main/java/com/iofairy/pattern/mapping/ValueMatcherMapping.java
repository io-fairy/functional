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
import com.iofairy.pattern.matcher.ValueRMatcher;
import com.iofairy.pattern.matcher.ValueVMatcher;

/**
 * Value Matcher Mapping
 * @since 0.0.1
 */
public class ValueMatcherMapping<V> extends MatcherMapping<V>{
    public ValueMatcherMapping(V value) {
        super(value);
    }

    public ValueVMatcher<V> when(V matchValue, V1<? super V> action) {
        ValueVMatcher<V> valueVMatcher = new ValueVMatcher<>(value);
        return valueVMatcher.when(matchValue, action);
    }

    public ValueVMatcher<V> whenNext(V matchValue, V1<? super V> action) {
        ValueVMatcher<V> valueVMatcher = new ValueVMatcher<>(value);
        return valueVMatcher.whenNext(matchValue, action);
    }

    public <R> ValueRMatcher<V, R> when(V matchValue, R1<? super V, ? extends R> action) {
        ValueRMatcher<V, R> valueRMatcher = new ValueRMatcher<>(value);
        return valueRMatcher.when(matchValue, action);
    }

    public <R> ValueRMatcher<V, R> whenNext(V matchValue, R1<? super V, ? extends R> action) {
        ValueRMatcher<V, R> valueRMatcher = new ValueRMatcher<>(value);
        return valueRMatcher.whenNext(matchValue, action);
    }

    public ValueVMatcher<V> when(PatternIn<V> matchValues, V1<? super V> action) {
        ValueVMatcher<V> valueVMatcher = new ValueVMatcher<>(value);
        return valueVMatcher.when(matchValues, action);
    }

    public ValueVMatcher<V> whenNext(PatternIn<V> matchValues, V1<? super V> action) {
        ValueVMatcher<V> valueVMatcher = new ValueVMatcher<>(value);
        return valueVMatcher.whenNext(matchValues, action);
    }

    public <R> ValueRMatcher<V, R> when(PatternIn<V> matchValues, R1<? super V, ? extends R> action) {
        ValueRMatcher<V, R> valueRMatcher = new ValueRMatcher<>(value);
        return valueRMatcher.when(matchValues, action);
    }

    public <R> ValueRMatcher<V, R> whenNext(PatternIn<V> matchValues, R1<? super V, ? extends R> action) {
        ValueRMatcher<V, R> valueRMatcher = new ValueRMatcher<>(value);
        return valueRMatcher.whenNext(matchValues, action);
    }

    public ValueVMatcher<V> when(boolean matchValue, V1<? super V> action) {
        ValueVMatcher<V> valueVMatcher = new ValueVMatcher<>(value);
        return valueVMatcher.when(matchValue, action);
    }

    public ValueVMatcher<V> whenNext(boolean matchValue, V1<? super V> action) {
        ValueVMatcher<V> valueVMatcher = new ValueVMatcher<>(value);
        return valueVMatcher.whenNext(matchValue, action);
    }

    public <R> ValueRMatcher<V, R> when(boolean matchValue, R1<? super V, ? extends R> action) {
        ValueRMatcher<V, R> valueRMatcher = new ValueRMatcher<>(value);
        return valueRMatcher.when(matchValue, action);
    }

    public <R> ValueRMatcher<V, R> whenNext(boolean matchValue, R1<? super V, ? extends R> action) {
        ValueRMatcher<V, R> valueRMatcher = new ValueRMatcher<>(value);
        return valueRMatcher.whenNext(matchValue, action);
    }

    /*
     * ######################################################
     * ******************************************************
     * #####   MatcherMapping with throwing exception   #####
     * ******************************************************
     * ######################################################
     */
    public <E extends Throwable> ValueVMatcher<V> with(V matchValue, VT1<? super V, E> action) throws E {
        ValueVMatcher<V> valueVMatcher = new ValueVMatcher<>(value);
        return valueVMatcher.with(matchValue, action);
    }

    public <E extends Throwable> ValueVMatcher<V> withNext(V matchValue, VT1<? super V, E> action) throws E {
        ValueVMatcher<V> valueVMatcher = new ValueVMatcher<>(value);
        return valueVMatcher.withNext(matchValue, action);
    }

    public <R, E extends Throwable> ValueRMatcher<V, R> with(V matchValue, RT1<? super V, ? extends R, E> action) throws E {
        ValueRMatcher<V, R> valueRMatcher = new ValueRMatcher<>(value);
        return valueRMatcher.with(matchValue, action);
    }

    public <R, E extends Throwable> ValueRMatcher<V, R> withNext(V matchValue, RT1<? super V, ? extends R, E> action) throws E {
        ValueRMatcher<V, R> valueRMatcher = new ValueRMatcher<>(value);
        return valueRMatcher.withNext(matchValue, action);
    }

    public <E extends Throwable> ValueVMatcher<V> with(PatternIn<V> matchValues, VT1<? super V, E> action) throws E {
        ValueVMatcher<V> valueVMatcher = new ValueVMatcher<>(value);
        return valueVMatcher.with(matchValues, action);
    }

    public <E extends Throwable> ValueVMatcher<V> withNext(PatternIn<V> matchValues, VT1<? super V, E> action) throws E {
        ValueVMatcher<V>valueVMatcher = new ValueVMatcher<>(value);
        return valueVMatcher.withNext(matchValues, action);
    }

    public <R, E extends Throwable> ValueRMatcher<V, R> with(PatternIn<V> matchValues, RT1<? super V, ? extends R, E> action) throws E {
        ValueRMatcher<V, R> valueRMatcher = new ValueRMatcher<>(value);
        return valueRMatcher.with(matchValues, action);
    }

    public <R, E extends Throwable> ValueRMatcher<V, R> withNext(PatternIn<V> matchValues, RT1<? super V, ? extends R, E> action) throws E {
        ValueRMatcher<V, R> valueRMatcher = new ValueRMatcher<>(value);
        return valueRMatcher.withNext(matchValues, action);
    }

    public <E extends Throwable> ValueVMatcher<V> with(boolean matchValue, VT1<? super V, E> action) throws E {
        ValueVMatcher<V> valueVMatcher = new ValueVMatcher<>(value);
        return valueVMatcher.with(matchValue, action);
    }

    public <E extends Throwable> ValueVMatcher<V> withNext(boolean matchValue, VT1<? super V, E> action) throws E {
        ValueVMatcher<V> valueVMatcher = new ValueVMatcher<>(value);
        return valueVMatcher.withNext(matchValue, action);
    }

    public <R, E extends Throwable> ValueRMatcher<V, R> with(boolean matchValue, RT1<? super V, ? extends R, E> action) throws E {
        ValueRMatcher<V, R> valueRMatcher = new ValueRMatcher<>(value);
        return valueRMatcher.with(matchValue, action);
    }

    public <R, E extends Throwable> ValueRMatcher<V, R> withNext(boolean matchValue, RT1<? super V, ? extends R, E> action) throws E {
        ValueRMatcher<V, R> valueRMatcher = new ValueRMatcher<>(value);
        return valueRMatcher.withNext(matchValue, action);
    }

}
