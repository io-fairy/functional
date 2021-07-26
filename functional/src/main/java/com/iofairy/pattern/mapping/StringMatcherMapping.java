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
import com.iofairy.pattern.matcher.StringRMatcher;
import com.iofairy.pattern.matcher.StringVMatcher;
import com.iofairy.pattern.type.PatternString;

/**
 * String Matcher Mapping
 * @since 0.0.1
 */
public class StringMatcherMapping extends MatcherMapping<String> {

    private PatternString patternString;

    public StringMatcherMapping(String value, PatternString patternString) {
        this(value);
        this.patternString = patternString;
    }

    public StringMatcherMapping(String value) {
        super(value);
    }

    public StringVMatcher when(String matchValue, V1<String> action) {
        StringVMatcher stringVMatcher = new StringVMatcher(value, patternString);
        return stringVMatcher.when(matchValue, action);
    }

    public StringVMatcher whenNext(String matchValue, V1<String> action) {
        StringVMatcher stringVMatcher = new StringVMatcher(value, patternString);
        return stringVMatcher.whenNext(matchValue, action);
    }

    public <R> StringRMatcher<R> when(String matchValue, R1<String, R> action) {
        StringRMatcher<R> stringRMatcher = new StringRMatcher<>(value, patternString);
        return stringRMatcher.when(matchValue, action);
    }

    public <R> StringRMatcher<R> whenNext(String matchValue, R1<String, R> action) {
        StringRMatcher<R> stringRMatcher = new StringRMatcher<>(value, patternString);
        return stringRMatcher.whenNext(matchValue, action);
    }

    public StringVMatcher when(PatternIn<String> matchValues, V1<String> action) {
        StringVMatcher stringVMatcher = new StringVMatcher(value, patternString);
        return stringVMatcher.when(matchValues, action);
    }

    public StringVMatcher whenNext(PatternIn<String> matchValues, V1<String> action) {
        StringVMatcher stringVMatcher = new StringVMatcher(value, patternString);
        return stringVMatcher.whenNext(matchValues, action);
    }

    public <R> StringRMatcher<R> when(PatternIn<String> matchValues, R1<String, R> action) {
        StringRMatcher<R> stringRMatcher = new StringRMatcher<>(value, patternString);
        return stringRMatcher.when(matchValues, action);
    }

    public <R> StringRMatcher<R> whenNext(PatternIn<String> matchValues, R1<String, R> action) {
        StringRMatcher<R> stringRMatcher = new StringRMatcher<>(value, patternString);
        return stringRMatcher.whenNext(matchValues, action);
    }

    public StringVMatcher when(boolean matchValue, V1<String> action) {
        StringVMatcher stringVMatcher = new StringVMatcher(value, patternString);
        return stringVMatcher.when(matchValue, action);
    }

    public StringVMatcher whenNext(boolean matchValue, V1<String> action) {
        StringVMatcher stringVMatcher = new StringVMatcher(value, patternString);
        return stringVMatcher.whenNext(matchValue, action);
    }

    public <R> StringRMatcher<R> when(boolean matchValue, R1<String, R> action) {
        StringRMatcher<R> stringRMatcher = new StringRMatcher<>(value, patternString);
        return stringRMatcher.when(matchValue, action);
    }

    public <R> StringRMatcher<R> whenNext(boolean matchValue, R1<String, R> action) {
        StringRMatcher<R> stringRMatcher = new StringRMatcher<>(value, patternString);
        return stringRMatcher.whenNext(matchValue, action);
    }

    /*
     * ######################################################
     * ******************************************************
     * #####   MatcherMapping with throwing exception   #####
     * ******************************************************
     * ######################################################
     */
    public <E extends Throwable> StringVMatcher when(String matchValue, VT0<E> action) throws E {
        StringVMatcher stringVMatcher = new StringVMatcher(value, patternString);
        return stringVMatcher.when(matchValue, action);
    }

    public <E extends Throwable> StringVMatcher whenNext(String matchValue, VT0<E> action) throws E {
        StringVMatcher stringVMatcher = new StringVMatcher(value, patternString);
        return stringVMatcher.whenNext(matchValue, action);
    }

    public <R, E extends Throwable> StringRMatcher<R> when(String matchValue, RT0<R, E> action) throws E {
        StringRMatcher<R> stringRMatcher = new StringRMatcher<>(value, patternString);
        return stringRMatcher.when(matchValue, action);
    }

    public <R, E extends Throwable> StringRMatcher<R> whenNext(String matchValue, RT0<R, E> action) throws E {
        StringRMatcher<R> stringRMatcher = new StringRMatcher<>(value, patternString);
        return stringRMatcher.whenNext(matchValue, action);
    }

    public <E extends Throwable> StringVMatcher when(PatternIn<String> matchValues, VT0<E> action) throws E {
        StringVMatcher stringVMatcher = new StringVMatcher(value, patternString);
        return stringVMatcher.when(matchValues, action);
    }

    public <E extends Throwable> StringVMatcher whenNext(PatternIn<String> matchValues, VT0<E> action) throws E {
        StringVMatcher stringVMatcher = new StringVMatcher(value, patternString);
        return stringVMatcher.whenNext(matchValues, action);
    }

    public <R, E extends Throwable> StringRMatcher<R> when(PatternIn<String> matchValues, RT0<R, E> action) throws E {
        StringRMatcher<R> stringRMatcher = new StringRMatcher<>(value, patternString);
        return stringRMatcher.when(matchValues, action);
    }

    public <R, E extends Throwable> StringRMatcher<R> whenNext(PatternIn<String> matchValues, RT0<R, E> action) throws E {
        StringRMatcher<R> stringRMatcher = new StringRMatcher<>(value, patternString);
        return stringRMatcher.whenNext(matchValues, action);
    }

    public <E extends Throwable> StringVMatcher when(boolean matchValue, VT0<E> action) throws E {
        StringVMatcher stringVMatcher = new StringVMatcher(value, patternString);
        return stringVMatcher.when(matchValue, action);
    }

    public <E extends Throwable> StringVMatcher whenNext(boolean matchValue, VT0<E> action) throws E {
        StringVMatcher stringVMatcher = new StringVMatcher(value, patternString);
        return stringVMatcher.whenNext(matchValue, action);
    }

    public <R, E extends Throwable> StringRMatcher<R> when(boolean matchValue, RT0<R, E> action) throws E {
        StringRMatcher<R> stringRMatcher = new StringRMatcher<>(value, patternString);
        return stringRMatcher.when(matchValue, action);
    }

    public <R, E extends Throwable> StringRMatcher<R> whenNext(boolean matchValue, RT0<R, E> action) throws E {
        StringRMatcher<R> stringRMatcher = new StringRMatcher<>(value, patternString);
        return stringRMatcher.whenNext(matchValue, action);
    }
    
}
