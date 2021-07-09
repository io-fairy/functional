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

import com.iofairy.lambda.R1;
import com.iofairy.lambda.V1;
import com.iofairy.pattern.matcher.TypeRMatcher;
import com.iofairy.pattern.matcher.TypeVMatcher;

/**
 * Type Matcher Mapping
 * @since 0.0.1
 */
public class TypeMatcherMapping<V> extends MatcherMapping<V>  {
    public TypeMatcherMapping(V value) {
        super(value);
    }

    public <C> TypeVMatcher<V> when(Class<C> matchValue, V1<C> action) {
        TypeVMatcher<V> typeVMatcher = new TypeVMatcher<>(value);
        return typeVMatcher.when(matchValue, action);
    }

    public <C> TypeVMatcher<V> whenNext(Class<C> matchValue, V1<C> action) {
        TypeVMatcher<V> typeVMatcher = new TypeVMatcher<>(value);
        return typeVMatcher.whenNext(matchValue, action);
    }

    public <C, R> TypeRMatcher<V, R> when(Class<C> matchValue, R1<C ,R> action) {
        TypeRMatcher<V, R> typeRMatcher = new TypeRMatcher<>(value);
        return typeRMatcher.when(matchValue, action);
    }

    public <C, R> TypeRMatcher<V, R> whenNext(Class<C> matchValue, R1<C ,R> action) {
        TypeRMatcher<V, R> typeRMatcher = new TypeRMatcher<>(value);
        return typeRMatcher.whenNext(matchValue, action);
    }

}
