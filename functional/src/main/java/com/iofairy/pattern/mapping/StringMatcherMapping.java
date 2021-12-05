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

    public StringVMatcher when(String matchValue, V1<? super String> action) {
        StringVMatcher stringVMatcher = new StringVMatcher(value, patternString);
        return stringVMatcher.when(matchValue, action);
    }

    public StringVMatcher whenNext(String matchValue, V1<? super String> action) {
        StringVMatcher stringVMatcher = new StringVMatcher(value, patternString);
        return stringVMatcher.whenNext(matchValue, action);
    }

    public <R> StringRMatcher<R> when(String matchValue, R1<? super String, ? extends R> action) {
        StringRMatcher<R> stringRMatcher = new StringRMatcher<>(value, patternString);
        return stringRMatcher.when(matchValue, action);
    }

    public <R> StringRMatcher<R> whenNext(String matchValue, R1<? super String, ? extends R> action) {
        StringRMatcher<R> stringRMatcher = new StringRMatcher<>(value, patternString);
        return stringRMatcher.whenNext(matchValue, action);
    }

    public StringVMatcher when(PatternIn<String> matchValues, V1<? super String> action) {
        StringVMatcher stringVMatcher = new StringVMatcher(value, patternString);
        return stringVMatcher.when(matchValues, action);
    }

    public StringVMatcher whenNext(PatternIn<String> matchValues, V1<? super String> action) {
        StringVMatcher stringVMatcher = new StringVMatcher(value, patternString);
        return stringVMatcher.whenNext(matchValues, action);
    }

    public <R> StringRMatcher<R> when(PatternIn<String> matchValues, R1<? super String, ? extends R> action) {
        StringRMatcher<R> stringRMatcher = new StringRMatcher<>(value, patternString);
        return stringRMatcher.when(matchValues, action);
    }

    public <R> StringRMatcher<R> whenNext(PatternIn<String> matchValues, R1<? super String, ? extends R> action) {
        StringRMatcher<R> stringRMatcher = new StringRMatcher<>(value, patternString);
        return stringRMatcher.whenNext(matchValues, action);
    }

    public StringVMatcher when(boolean matchValue, V1<? super String> action) {
        StringVMatcher stringVMatcher = new StringVMatcher(value, patternString);
        return stringVMatcher.when(matchValue, action);
    }

    public StringVMatcher whenNext(boolean matchValue, V1<? super String> action) {
        StringVMatcher stringVMatcher = new StringVMatcher(value, patternString);
        return stringVMatcher.whenNext(matchValue, action);
    }

    public <R> StringRMatcher<R> when(boolean matchValue, R1<? super String, ? extends R> action) {
        StringRMatcher<R> stringRMatcher = new StringRMatcher<>(value, patternString);
        return stringRMatcher.when(matchValue, action);
    }

    public <R> StringRMatcher<R> whenNext(boolean matchValue, R1<? super String, ? extends R> action) {
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
    public <E extends Throwable> StringVMatcher with(String matchValue, VT1<? super String, E> action) throws E {
        StringVMatcher stringVMatcher = new StringVMatcher(value, patternString);
        return stringVMatcher.with(matchValue, action);
    }

    public <E extends Throwable> StringVMatcher withNext(String matchValue, VT1<? super String, E> action) throws E {
        StringVMatcher stringVMatcher = new StringVMatcher(value, patternString);
        return stringVMatcher.withNext(matchValue, action);
    }

    public <R, E extends Throwable> StringRMatcher<R> with(String matchValue, RT1<? super String, ? extends R, E> action) throws E {
        StringRMatcher<R> stringRMatcher = new StringRMatcher<>(value, patternString);
        return stringRMatcher.with(matchValue, action);
    }

    public <R, E extends Throwable> StringRMatcher<R> withNext(String matchValue, RT1<? super String, ? extends R, E> action) throws E {
        StringRMatcher<R> stringRMatcher = new StringRMatcher<>(value, patternString);
        return stringRMatcher.withNext(matchValue, action);
    }

    public <E extends Throwable> StringVMatcher with(PatternIn<String> matchValues, VT1<? super String, E> action) throws E {
        StringVMatcher stringVMatcher = new StringVMatcher(value, patternString);
        return stringVMatcher.with(matchValues, action);
    }

    public <E extends Throwable> StringVMatcher withNext(PatternIn<String> matchValues, VT1<? super String, E> action) throws E {
        StringVMatcher stringVMatcher = new StringVMatcher(value, patternString);
        return stringVMatcher.withNext(matchValues, action);
    }

    public <R, E extends Throwable> StringRMatcher<R> with(PatternIn<String> matchValues, RT1<? super String, ? extends R, E> action) throws E {
        StringRMatcher<R> stringRMatcher = new StringRMatcher<>(value, patternString);
        return stringRMatcher.with(matchValues, action);
    }

    public <R, E extends Throwable> StringRMatcher<R> withNext(PatternIn<String> matchValues, RT1<? super String, ? extends R, E> action) throws E {
        StringRMatcher<R> stringRMatcher = new StringRMatcher<>(value, patternString);
        return stringRMatcher.withNext(matchValues, action);
    }

    public <E extends Throwable> StringVMatcher with(boolean matchValue, VT1<? super String, E> action) throws E {
        StringVMatcher stringVMatcher = new StringVMatcher(value, patternString);
        return stringVMatcher.with(matchValue, action);
    }

    public <E extends Throwable> StringVMatcher withNext(boolean matchValue, VT1<? super String, E> action) throws E {
        StringVMatcher stringVMatcher = new StringVMatcher(value, patternString);
        return stringVMatcher.withNext(matchValue, action);
    }

    public <R, E extends Throwable> StringRMatcher<R> with(boolean matchValue, RT1<? super String, ? extends R, E> action) throws E {
        StringRMatcher<R> stringRMatcher = new StringRMatcher<>(value, patternString);
        return stringRMatcher.with(matchValue, action);
    }

    public <R, E extends Throwable> StringRMatcher<R> withNext(boolean matchValue, RT1<? super String, ? extends R, E> action) throws E {
        StringRMatcher<R> stringRMatcher = new StringRMatcher<>(value, patternString);
        return stringRMatcher.withNext(matchValue, action);
    }
    
}
