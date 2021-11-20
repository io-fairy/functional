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
import com.iofairy.pattern.type.PatternNull1;
import com.iofairy.tuple.*;

import java.util.Objects;

/**
 * Null2Matcher
 *
 * @since 0.2.0
 */
public class Null2Matcher<T1, R> implements Matcher {
    private final R msg;
    private final T1 value1;
    private final boolean isMatch;

    Null2Matcher(boolean isMatch, T1 value1, R msg) {
        this.isMatch = isMatch;
        this.value1 = value1;
        this.msg = msg;
    }


    public <V, NV> Null3Matcher<T1, NV, R> whenV(V matchValue, R1<V, NV> computeValue, R1<NV, Boolean> action, R1<Tuple1<T1>, R> msgAction) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1));
            if (matchValue == null) {
                return new Null3Matcher<>(true, value1, null, msg);
            }

            NV newValue = computeValue.$(matchValue);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null3Matcher<>(doBreak, value1, newValue, localMsg);
        }
        return new Null3Matcher<>(true, value1, null, this.msg);
    }

    public <V, NV> Null3Matcher<T1, NV, R> whenV(V matchValue, R1<V, NV> computeValue, R1<Tuple1<T1>, R> msgAction) {
        return whenV(matchValue, computeValue, null, msgAction);
    }

    public <V, NV> Null3Matcher<T1, NV, R> whenV(V matchValue, R1<V, NV> computeValue, R1<NV, Boolean> action, R msg) {
        Objects.requireNonNull(computeValue);
        if (!isMatch) {
            if (matchValue == null) {
                return new Null3Matcher<>(true, value1, null, msg);
            }

            NV newValue = computeValue.$(matchValue);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null3Matcher<>(doBreak, value1, newValue, localMsg);
        }
        return new Null3Matcher<>(true, value1, null, this.msg);
    }

    public <V, NV> Null3Matcher<T1, NV, R> whenV(V matchValue, R1<V, NV> computeValue, R msg) {
        return whenV(matchValue, computeValue, null, msg);
    }

    public <NV> Null3Matcher<T1, NV, R> whenW(PatternNull1 patternNull, R1<T1, NV> computeValue, R1<NV, Boolean> action, R1<Tuple1<T1>, R> msgAction) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1));
            NV newValue = computeValue.$(value1);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null3Matcher<>(doBreak, value1, newValue, localMsg);
        }
        return new Null3Matcher<>(true, value1, null, this.msg);
    }

    public <NV> Null3Matcher<T1, NV, R> whenW(PatternNull1 patternNull, R1<T1, NV> computeValue, R1<Tuple1<T1>, R> msgAction) {
        return whenW(patternNull, computeValue, null, msgAction);
    }

    public <NV> Null3Matcher<T1, NV, R> whenW(PatternNull1 patternNull, R1<T1, NV> computeValue, R1<NV, Boolean> action, R msg) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        if (!isMatch) {
            NV newValue = computeValue.$(value1);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null3Matcher<>(doBreak, value1, newValue, localMsg);
        }
        return new Null3Matcher<>(true, value1, null, this.msg);
    }

    public <NV> Null3Matcher<T1, NV, R> whenW(PatternNull1 patternNull, R1<T1, NV> computeValue, R msg) {
        return whenW(patternNull, computeValue, null, msg);
    }


    /*
     * ########################################################################
     * ************************************************************************
     * ################   NullMatcher with throwing exception   ###############
     * ************************************************************************
     * ########################################################################
     */

    public <V, NV, E extends Throwable> Null3Matcher<T1, NV, R> withV(V matchValue, RT1<V, NV, E> computeValue, RT1<NV, Boolean, E> action, RT1<Tuple1<T1>, R, E> msgAction) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1));
            if (matchValue == null) {
                return new Null3Matcher<>(true, value1, null, msg);
            }

            NV newValue = computeValue.$(matchValue);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null3Matcher<>(doBreak, value1, newValue, localMsg);
        }
        return new Null3Matcher<>(true, value1, null, this.msg);
    }

    public <V, NV, E extends Throwable> Null3Matcher<T1, NV, R> withV(V matchValue, RT1<V, NV, E> computeValue, RT1<Tuple1<T1>, R, E> msgAction) throws E {
        return withV(matchValue, computeValue, null, msgAction);
    }

    public <V, NV, E extends Throwable> Null3Matcher<T1, NV, R> withV(V matchValue, RT1<V, NV, E> computeValue, RT1<NV, Boolean, E> action, R msg) throws E {
        Objects.requireNonNull(computeValue);
        if (!isMatch) {
            if (matchValue == null) {
                return new Null3Matcher<>(true, value1, null, msg);
            }

            NV newValue = computeValue.$(matchValue);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null3Matcher<>(doBreak, value1, newValue, localMsg);
        }
        return new Null3Matcher<>(true, value1, null, this.msg);
    }

    public <V, NV, E extends Throwable> Null3Matcher<T1, NV, R> withV(V matchValue, RT1<V, NV, E> computeValue, R msg) throws E {
        return withV(matchValue, computeValue, null, msg);
    }

    public <NV, E extends Throwable> Null3Matcher<T1, NV, R> withW(PatternNull1 patternNull, RT1<T1, NV, E> computeValue, RT1<NV, Boolean, E> action, RT1<Tuple1<T1>, R, E> msgAction) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1));
            NV newValue = computeValue.$(value1);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null3Matcher<>(doBreak, value1, newValue, localMsg);
        }
        return new Null3Matcher<>(true, value1, null, this.msg);
    }

    public <NV, E extends Throwable> Null3Matcher<T1, NV, R> withW(PatternNull1 patternNull, RT1<T1, NV, E> computeValue, RT1<Tuple1<T1>, R, E> msgAction) throws E {
        return withW(patternNull, computeValue, null, msgAction);
    }

    public <NV, E extends Throwable> Null3Matcher<T1, NV, R> withW(PatternNull1 patternNull, RT1<T1, NV, E> computeValue, RT1<NV, Boolean, E> action, R msg) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        if (!isMatch) {
            NV newValue = computeValue.$(value1);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null3Matcher<>(doBreak, value1, newValue, localMsg);
        }
        return new Null3Matcher<>(true, value1, null, this.msg);
    }

    public <NV, E extends Throwable> Null3Matcher<T1, NV, R> withW(PatternNull1 patternNull, RT1<T1, NV, E> computeValue, R msg) throws E {
        return withW(patternNull, computeValue, null, msg);
    }


    public Tuple2<T1, R> orElse(R msg) {
        R finalMsg = !isMatch ? msg : this.msg;
        return Tuple.of(value1, finalMsg);
    }

}
