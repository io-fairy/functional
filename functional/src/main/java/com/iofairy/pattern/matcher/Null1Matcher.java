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

import com.iofairy.lambda.R1;
import com.iofairy.lambda.RT1;

import java.util.Objects;

/**
 * Null1Matcher
 *
 * @since 0.2.0
 */
public class Null1Matcher<R> implements NullMatcher {

    public <V, NV> Null2Matcher<NV, R> whenV(V matchValue, R1<? super V, ? extends NV> computeValue, R1<? super NV, Boolean> action, R msg) {
        Objects.requireNonNull(computeValue);
        if (matchValue == null) {
            return new Null2Matcher<>(true, null, msg);
        }

        NV newValue = computeValue.$(matchValue);
        boolean doBreak = newValue == null || (action != null && action.$(newValue));
        R localMsg = doBreak ? msg : null;
        return new Null2Matcher<>(doBreak, newValue, localMsg);
    }

    public <V, NV> Null2Matcher<NV, R> whenV(V matchValue, R1<? super V, ? extends NV> computeValue, R msg) {
        return whenV(matchValue, computeValue, null, msg);
    }

    /*
     * ################################################################
     * ****************************************************************
     * ############   NullMatcher with throwing exception   ###########
     * ****************************************************************
     * ################################################################
     */

    public <V, NV, E extends Throwable> Null2Matcher<NV, R> withV(V matchValue, RT1<? super V, ? extends NV, E> computeValue, RT1<? super NV, Boolean, E> action, R msg) throws E {
        Objects.requireNonNull(computeValue);
        if (matchValue == null) {
            return new Null2Matcher<>(true, null, msg);
        }

        NV newValue = computeValue.$(matchValue);
        boolean doBreak = newValue == null || (action != null && action.$(newValue));
        R localMsg = doBreak ? msg : null;
        return new Null2Matcher<>(doBreak, newValue, localMsg);
    }

    public <V, NV, E extends Throwable> Null2Matcher<NV, R> withV(V matchValue, RT1<? super V, ? extends NV, E> computeValue, R msg) throws E {
        return withV(matchValue, computeValue, null, msg);
    }

}
