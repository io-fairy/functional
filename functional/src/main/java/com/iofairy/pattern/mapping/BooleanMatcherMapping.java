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
import com.iofairy.pattern.matcher.BooleanRMatcher;
import com.iofairy.pattern.matcher.BooleanVMatcher;

/**
 * Boolean Matcher Mapping
 * @since 0.0.1
 */
public class BooleanMatcherMapping<V> extends PatternMatcherMapping<V> {
    public BooleanMatcherMapping(V value) {
        super(value);
    }

    public BooleanVMatcher<V> when(boolean matchValue, V1<? super V> action) {
        BooleanVMatcher<V> booleanVMatcher = new BooleanVMatcher<V>(value);
        return booleanVMatcher.when(matchValue, action);
    }

    public BooleanVMatcher<V> whenNext(boolean matchValue, V1<? super V> action) {
        BooleanVMatcher<V> booleanVMatcher = new BooleanVMatcher<V>(value);
        return booleanVMatcher.whenNext(matchValue, action);
    }

    public <R> BooleanRMatcher<V, R> when(boolean matchValue, R1<? super V, ? extends R> action) {
        BooleanRMatcher<V, R> booleanRMatcher = new BooleanRMatcher<>(value);
        return booleanRMatcher.when(matchValue, action);
    }

    public <R> BooleanRMatcher<V, R> whenNext(boolean matchValue, R1<? super V, ? extends R> action) {
        BooleanRMatcher<V, R> booleanRMatcher = new BooleanRMatcher<>(value);
        return booleanRMatcher.whenNext(matchValue, action);
    }

    /*
     * ######################################################
     * ******************************************************
     * #####   MatcherMapping with throwing exception   #####
     * ******************************************************
     * ######################################################
     */
    public <E extends Throwable> BooleanVMatcher<V> with(boolean matchValue, VT1<? super V, E> action) throws E {
        BooleanVMatcher<V> booleanVMatcher = new BooleanVMatcher<V>(value);
        return booleanVMatcher.with(matchValue, action);
    }

    public <E extends Throwable> BooleanVMatcher<V> withNext(boolean matchValue, VT1<? super V, E> action) throws E {
        BooleanVMatcher<V> booleanVMatcher = new BooleanVMatcher<V>(value);
        return booleanVMatcher.withNext(matchValue, action);
    }

    public <R, E extends Throwable> BooleanRMatcher<V, R> with(boolean matchValue, RT1<? super V, ? extends R, E> action) throws E {
        BooleanRMatcher<V, R> booleanRMatcher = new BooleanRMatcher<>(value);
        return booleanRMatcher.with(matchValue, action);
    }

    public <R, E extends Throwable> BooleanRMatcher<V, R> withNext(boolean matchValue, RT1<? super V, ? extends R, E> action) throws E {
        BooleanRMatcher<V, R> booleanRMatcher = new BooleanRMatcher<>(value);
        return booleanRMatcher.withNext(matchValue, action);
    }

}
