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
import com.iofairy.pattern.type.*;
import com.iofairy.tuple.*;

import java.util.Objects;

/**
 * Null6Matcher
 *
 * @since 0.2.0
 */
public class Null6Matcher<T1, T2, T3, T4, T5, R> implements NullMatcher {
    private final R msg;
    private final T1 value1;
    private final T2 value2;
    private final T3 value3;
    private final T4 value4;
    private final T5 value5;
    private final boolean isMatch;

    Null6Matcher(boolean isMatch, T1 value1, T2 value2, T3 value3, T4 value4, T5 value5, R msg) {
        this.isMatch = isMatch;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
        this.value5 = value5;
        this.msg = msg;
    }


    public <V, NV> Null7Matcher<T1, T2, T3, T4, T5, NV, R> whenV(V matchValue, R1<? super V, ? extends NV> computeValue, R1<? super NV, Boolean> action, R1<Tuple5<T1, T2, T3, T4, T5>, ? extends R> msgAction) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4, value5));
            if (matchValue == null) {
                return new Null7Matcher<>(true, value1, value2, value3, value4, value5, null, msg);
            }

            NV newValue = computeValue.$(matchValue);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null7Matcher<>(doBreak, value1, value2, value3, value4, value5, newValue, localMsg);
        }
        return new Null7Matcher<>(true, value1, value2, value3, value4, value5, null, this.msg);
    }

    public <V, NV> Null7Matcher<T1, T2, T3, T4, T5, NV, R> whenV(V matchValue, R1<? super V, ? extends NV> computeValue, R1<Tuple5<T1, T2, T3, T4, T5>, ? extends R> msgAction) {
        return whenV(matchValue, computeValue, null, msgAction);
    }

    public <V, NV> Null7Matcher<T1, T2, T3, T4, T5, NV, R> whenV(V matchValue, R1<? super V, ? extends NV> computeValue, R1<? super NV, Boolean> action, R msg) {
        Objects.requireNonNull(computeValue);
        if (!isMatch) {
            if (matchValue == null) {
                return new Null7Matcher<>(true, value1, value2, value3, value4, value5, null, msg);
            }

            NV newValue = computeValue.$(matchValue);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null7Matcher<>(doBreak, value1, value2, value3, value4, value5, newValue, localMsg);
        }
        return new Null7Matcher<>(true, value1, value2, value3, value4, value5, null, this.msg);
    }

    public <V, NV> Null7Matcher<T1, T2, T3, T4, T5, NV, R> whenV(V matchValue, R1<? super V, ? extends NV> computeValue, R msg) {
        return whenV(matchValue, computeValue, null, msg);
    }

    public <NV> Null7Matcher<T1, T2, T3, T4, T5, NV, R> whenW(PatternNull1 patternNull, R1<? super T1, ? extends NV> computeValue, R1<? super NV, Boolean> action, R1<Tuple5<T1, T2, T3, T4, T5>, ? extends R> msgAction) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4, value5));
            NV newValue = computeValue.$(value1);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null7Matcher<>(doBreak, value1, value2, value3, value4, value5, newValue, localMsg);
        }
        return new Null7Matcher<>(true, value1, value2, value3, value4, value5, null, this.msg);
    }

    public <NV> Null7Matcher<T1, T2, T3, T4, T5, NV, R> whenW(PatternNull1 patternNull, R1<? super T1, ? extends NV> computeValue, R1<Tuple5<T1, T2, T3, T4, T5>, ? extends R> msgAction) {
        return whenW(patternNull, computeValue, null, msgAction);
    }

    public <NV> Null7Matcher<T1, T2, T3, T4, T5, NV, R> whenW(PatternNull1 patternNull, R1<? super T1, ? extends NV> computeValue, R1<? super NV, Boolean> action, R msg) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        if (!isMatch) {
            NV newValue = computeValue.$(value1);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null7Matcher<>(doBreak, value1, value2, value3, value4, value5, newValue, localMsg);
        }
        return new Null7Matcher<>(true, value1, value2, value3, value4, value5, null, this.msg);
    }

    public <NV> Null7Matcher<T1, T2, T3, T4, T5, NV, R> whenW(PatternNull1 patternNull, R1<? super T1, ? extends NV> computeValue, R msg) {
        return whenW(patternNull, computeValue, null, msg);
    }

    public <NV> Null7Matcher<T1, T2, T3, T4, T5, NV, R> whenW(PatternNull2 patternNull, R1<? super T2, ? extends NV> computeValue, R1<? super NV, Boolean> action, R1<Tuple5<T1, T2, T3, T4, T5>, ? extends R> msgAction) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4, value5));
            NV newValue = computeValue.$(value2);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null7Matcher<>(doBreak, value1, value2, value3, value4, value5, newValue, localMsg);
        }
        return new Null7Matcher<>(true, value1, value2, value3, value4, value5, null, this.msg);
    }

    public <NV> Null7Matcher<T1, T2, T3, T4, T5, NV, R> whenW(PatternNull2 patternNull, R1<? super T2, ? extends NV> computeValue, R1<Tuple5<T1, T2, T3, T4, T5>, ? extends R> msgAction) {
        return whenW(patternNull, computeValue, null, msgAction);
    }

    public <NV> Null7Matcher<T1, T2, T3, T4, T5, NV, R> whenW(PatternNull2 patternNull, R1<? super T2, ? extends NV> computeValue, R1<? super NV, Boolean> action, R msg) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        if (!isMatch) {
            NV newValue = computeValue.$(value2);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null7Matcher<>(doBreak, value1, value2, value3, value4, value5, newValue, localMsg);
        }
        return new Null7Matcher<>(true, value1, value2, value3, value4, value5, null, this.msg);
    }

    public <NV> Null7Matcher<T1, T2, T3, T4, T5, NV, R> whenW(PatternNull2 patternNull, R1<? super T2, ? extends NV> computeValue, R msg) {
        return whenW(patternNull, computeValue, null, msg);
    }

    public <NV> Null7Matcher<T1, T2, T3, T4, T5, NV, R> whenW(PatternNull3 patternNull, R1<? super T3, ? extends NV> computeValue, R1<? super NV, Boolean> action, R1<Tuple5<T1, T2, T3, T4, T5>, ? extends R> msgAction) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4, value5));
            NV newValue = computeValue.$(value3);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null7Matcher<>(doBreak, value1, value2, value3, value4, value5, newValue, localMsg);
        }
        return new Null7Matcher<>(true, value1, value2, value3, value4, value5, null, this.msg);
    }

    public <NV> Null7Matcher<T1, T2, T3, T4, T5, NV, R> whenW(PatternNull3 patternNull, R1<? super T3, ? extends NV> computeValue, R1<Tuple5<T1, T2, T3, T4, T5>, ? extends R> msgAction) {
        return whenW(patternNull, computeValue, null, msgAction);
    }

    public <NV> Null7Matcher<T1, T2, T3, T4, T5, NV, R> whenW(PatternNull3 patternNull, R1<? super T3, ? extends NV> computeValue, R1<? super NV, Boolean> action, R msg) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        if (!isMatch) {
            NV newValue = computeValue.$(value3);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null7Matcher<>(doBreak, value1, value2, value3, value4, value5, newValue, localMsg);
        }
        return new Null7Matcher<>(true, value1, value2, value3, value4, value5, null, this.msg);
    }

    public <NV> Null7Matcher<T1, T2, T3, T4, T5, NV, R> whenW(PatternNull3 patternNull, R1<? super T3, ? extends NV> computeValue, R msg) {
        return whenW(patternNull, computeValue, null, msg);
    }

    public <NV> Null7Matcher<T1, T2, T3, T4, T5, NV, R> whenW(PatternNull4 patternNull, R1<? super T4, ? extends NV> computeValue, R1<? super NV, Boolean> action, R1<Tuple5<T1, T2, T3, T4, T5>, ? extends R> msgAction) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4, value5));
            NV newValue = computeValue.$(value4);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null7Matcher<>(doBreak, value1, value2, value3, value4, value5, newValue, localMsg);
        }
        return new Null7Matcher<>(true, value1, value2, value3, value4, value5, null, this.msg);
    }

    public <NV> Null7Matcher<T1, T2, T3, T4, T5, NV, R> whenW(PatternNull4 patternNull, R1<? super T4, ? extends NV> computeValue, R1<Tuple5<T1, T2, T3, T4, T5>, ? extends R> msgAction) {
        return whenW(patternNull, computeValue, null, msgAction);
    }

    public <NV> Null7Matcher<T1, T2, T3, T4, T5, NV, R> whenW(PatternNull4 patternNull, R1<? super T4, ? extends NV> computeValue, R1<? super NV, Boolean> action, R msg) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        if (!isMatch) {
            NV newValue = computeValue.$(value4);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null7Matcher<>(doBreak, value1, value2, value3, value4, value5, newValue, localMsg);
        }
        return new Null7Matcher<>(true, value1, value2, value3, value4, value5, null, this.msg);
    }

    public <NV> Null7Matcher<T1, T2, T3, T4, T5, NV, R> whenW(PatternNull4 patternNull, R1<? super T4, ? extends NV> computeValue, R msg) {
        return whenW(patternNull, computeValue, null, msg);
    }

    public <NV> Null7Matcher<T1, T2, T3, T4, T5, NV, R> whenW(PatternNull5 patternNull, R1<? super T5, ? extends NV> computeValue, R1<? super NV, Boolean> action, R1<Tuple5<T1, T2, T3, T4, T5>, ? extends R> msgAction) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4, value5));
            NV newValue = computeValue.$(value5);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null7Matcher<>(doBreak, value1, value2, value3, value4, value5, newValue, localMsg);
        }
        return new Null7Matcher<>(true, value1, value2, value3, value4, value5, null, this.msg);
    }

    public <NV> Null7Matcher<T1, T2, T3, T4, T5, NV, R> whenW(PatternNull5 patternNull, R1<? super T5, ? extends NV> computeValue, R1<Tuple5<T1, T2, T3, T4, T5>, ? extends R> msgAction) {
        return whenW(patternNull, computeValue, null, msgAction);
    }

    public <NV> Null7Matcher<T1, T2, T3, T4, T5, NV, R> whenW(PatternNull5 patternNull, R1<? super T5, ? extends NV> computeValue, R1<? super NV, Boolean> action, R msg) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        if (!isMatch) {
            NV newValue = computeValue.$(value5);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null7Matcher<>(doBreak, value1, value2, value3, value4, value5, newValue, localMsg);
        }
        return new Null7Matcher<>(true, value1, value2, value3, value4, value5, null, this.msg);
    }

    public <NV> Null7Matcher<T1, T2, T3, T4, T5, NV, R> whenW(PatternNull5 patternNull, R1<? super T5, ? extends NV> computeValue, R msg) {
        return whenW(patternNull, computeValue, null, msg);
    }

    /*
     * ########################################################################
     * ************************************************************************
     * ################   NullMatcher with throwing exception   ###############
     * ************************************************************************
     * ########################################################################
     */

    public <V, NV, E extends Throwable> Null7Matcher<T1, T2, T3, T4, T5, NV, R> withV(V matchValue, RT1<? super V, ? extends NV, E> computeValue, RT1<? super NV, Boolean, E> action, RT1<Tuple5<T1, T2, T3, T4, T5>, ? extends R, E> msgAction) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4, value5));
            if (matchValue == null) {
                return new Null7Matcher<>(true, value1, value2, value3, value4, value5, null, msg);
            }

            NV newValue = computeValue.$(matchValue);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null7Matcher<>(doBreak, value1, value2, value3, value4, value5, newValue, localMsg);
        }
        return new Null7Matcher<>(true, value1, value2, value3, value4, value5, null, this.msg);
    }

    public <V, NV, E extends Throwable> Null7Matcher<T1, T2, T3, T4, T5, NV, R> withV(V matchValue, RT1<? super V, ? extends NV, E> computeValue, RT1<Tuple5<T1, T2, T3, T4, T5>, ? extends R, E> msgAction) throws E {
        return withV(matchValue, computeValue, null, msgAction);
    }

    public <V, NV, E extends Throwable> Null7Matcher<T1, T2, T3, T4, T5, NV, R> withV(V matchValue, RT1<? super V, ? extends NV, E> computeValue, RT1<? super NV, Boolean, E> action, R msg) throws E {
        Objects.requireNonNull(computeValue);
        if (!isMatch) {
            if (matchValue == null) {
                return new Null7Matcher<>(true, value1, value2, value3, value4, value5, null, msg);
            }

            NV newValue = computeValue.$(matchValue);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null7Matcher<>(doBreak, value1, value2, value3, value4, value5, newValue, localMsg);
        }
        return new Null7Matcher<>(true, value1, value2, value3, value4, value5, null, this.msg);
    }

    public <V, NV, E extends Throwable> Null7Matcher<T1, T2, T3, T4, T5, NV, R> withV(V matchValue, RT1<? super V, ? extends NV, E> computeValue, R msg) throws E {
        return withV(matchValue, computeValue, null, msg);
    }

    public <NV, E extends Throwable> Null7Matcher<T1, T2, T3, T4, T5, NV, R> withW(PatternNull1 patternNull, RT1<? super T1, ? extends NV, E> computeValue, RT1<? super NV, Boolean, E> action, RT1<Tuple5<T1, T2, T3, T4, T5>, ? extends R, E> msgAction) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4, value5));
            NV newValue = computeValue.$(value1);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null7Matcher<>(doBreak, value1, value2, value3, value4, value5, newValue, localMsg);
        }
        return new Null7Matcher<>(true, value1, value2, value3, value4, value5, null, this.msg);
    }

    public <NV, E extends Throwable> Null7Matcher<T1, T2, T3, T4, T5, NV, R> withW(PatternNull1 patternNull, RT1<? super T1, ? extends NV, E> computeValue, RT1<Tuple5<T1, T2, T3, T4, T5>, ? extends R, E> msgAction) throws E {
        return withW(patternNull, computeValue, null, msgAction);
    }

    public <NV, E extends Throwable> Null7Matcher<T1, T2, T3, T4, T5, NV, R> withW(PatternNull1 patternNull, RT1<? super T1, ? extends NV, E> computeValue, RT1<? super NV, Boolean, E> action, R msg) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        if (!isMatch) {
            NV newValue = computeValue.$(value1);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null7Matcher<>(doBreak, value1, value2, value3, value4, value5, newValue, localMsg);
        }
        return new Null7Matcher<>(true, value1, value2, value3, value4, value5, null, this.msg);
    }

    public <NV, E extends Throwable> Null7Matcher<T1, T2, T3, T4, T5, NV, R> withW(PatternNull1 patternNull, RT1<? super T1, ? extends NV, E> computeValue, R msg) throws E {
        return withW(patternNull, computeValue, null, msg);
    }

    public <NV, E extends Throwable> Null7Matcher<T1, T2, T3, T4, T5, NV, R> withW(PatternNull2 patternNull, RT1<? super T2, ? extends NV, E> computeValue, RT1<? super NV, Boolean, E> action, RT1<Tuple5<T1, T2, T3, T4, T5>, ? extends R, E> msgAction) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4, value5));
            NV newValue = computeValue.$(value2);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null7Matcher<>(doBreak, value1, value2, value3, value4, value5, newValue, localMsg);
        }
        return new Null7Matcher<>(true, value1, value2, value3, value4, value5, null, this.msg);
    }

    public <NV, E extends Throwable> Null7Matcher<T1, T2, T3, T4, T5, NV, R> withW(PatternNull2 patternNull, RT1<? super T2, ? extends NV, E> computeValue, RT1<Tuple5<T1, T2, T3, T4, T5>, ? extends R, E> msgAction) throws E {
        return withW(patternNull, computeValue, null, msgAction);
    }

    public <NV, E extends Throwable> Null7Matcher<T1, T2, T3, T4, T5, NV, R> withW(PatternNull2 patternNull, RT1<? super T2, ? extends NV, E> computeValue, RT1<? super NV, Boolean, E> action, R msg) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        if (!isMatch) {
            NV newValue = computeValue.$(value2);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null7Matcher<>(doBreak, value1, value2, value3, value4, value5, newValue, localMsg);
        }
        return new Null7Matcher<>(true, value1, value2, value3, value4, value5, null, this.msg);
    }

    public <NV, E extends Throwable> Null7Matcher<T1, T2, T3, T4, T5, NV, R> withW(PatternNull2 patternNull, RT1<? super T2, ? extends NV, E> computeValue, R msg) throws E {
        return withW(patternNull, computeValue, null, msg);
    }

    public <NV, E extends Throwable> Null7Matcher<T1, T2, T3, T4, T5, NV, R> withW(PatternNull3 patternNull, RT1<? super T3, ? extends NV, E> computeValue, RT1<? super NV, Boolean, E> action, RT1<Tuple5<T1, T2, T3, T4, T5>, ? extends R, E> msgAction) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4, value5));
            NV newValue = computeValue.$(value3);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null7Matcher<>(doBreak, value1, value2, value3, value4, value5, newValue, localMsg);
        }
        return new Null7Matcher<>(true, value1, value2, value3, value4, value5, null, this.msg);
    }

    public <NV, E extends Throwable> Null7Matcher<T1, T2, T3, T4, T5, NV, R> withW(PatternNull3 patternNull, RT1<? super T3, ? extends NV, E> computeValue, RT1<Tuple5<T1, T2, T3, T4, T5>, ? extends R, E> msgAction) throws E {
        return withW(patternNull, computeValue, null, msgAction);
    }

    public <NV, E extends Throwable> Null7Matcher<T1, T2, T3, T4, T5, NV, R> withW(PatternNull3 patternNull, RT1<? super T3, ? extends NV, E> computeValue, RT1<? super NV, Boolean, E> action, R msg) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        if (!isMatch) {
            NV newValue = computeValue.$(value3);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null7Matcher<>(doBreak, value1, value2, value3, value4, value5, newValue, localMsg);
        }
        return new Null7Matcher<>(true, value1, value2, value3, value4, value5, null, this.msg);
    }

    public <NV, E extends Throwable> Null7Matcher<T1, T2, T3, T4, T5, NV, R> withW(PatternNull3 patternNull, RT1<? super T3, ? extends NV, E> computeValue, R msg) throws E {
        return withW(patternNull, computeValue, null, msg);
    }

    public <NV, E extends Throwable> Null7Matcher<T1, T2, T3, T4, T5, NV, R> withW(PatternNull4 patternNull, RT1<? super T4, ? extends NV, E> computeValue, RT1<? super NV, Boolean, E> action, RT1<Tuple5<T1, T2, T3, T4, T5>, ? extends R, E> msgAction) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4, value5));
            NV newValue = computeValue.$(value4);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null7Matcher<>(doBreak, value1, value2, value3, value4, value5, newValue, localMsg);
        }
        return new Null7Matcher<>(true, value1, value2, value3, value4, value5, null, this.msg);
    }

    public <NV, E extends Throwable> Null7Matcher<T1, T2, T3, T4, T5, NV, R> withW(PatternNull4 patternNull, RT1<? super T4, ? extends NV, E> computeValue, RT1<Tuple5<T1, T2, T3, T4, T5>, ? extends R, E> msgAction) throws E {
        return withW(patternNull, computeValue, null, msgAction);
    }

    public <NV, E extends Throwable> Null7Matcher<T1, T2, T3, T4, T5, NV, R> withW(PatternNull4 patternNull, RT1<? super T4, ? extends NV, E> computeValue, RT1<? super NV, Boolean, E> action, R msg) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        if (!isMatch) {
            NV newValue = computeValue.$(value4);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null7Matcher<>(doBreak, value1, value2, value3, value4, value5, newValue, localMsg);
        }
        return new Null7Matcher<>(true, value1, value2, value3, value4, value5, null, this.msg);
    }

    public <NV, E extends Throwable> Null7Matcher<T1, T2, T3, T4, T5, NV, R> withW(PatternNull4 patternNull, RT1<? super T4, ? extends NV, E> computeValue, R msg) throws E {
        return withW(patternNull, computeValue, null, msg);
    }

    public <NV, E extends Throwable> Null7Matcher<T1, T2, T3, T4, T5, NV, R> withW(PatternNull5 patternNull, RT1<? super T5, ? extends NV, E> computeValue, RT1<? super NV, Boolean, E> action, RT1<Tuple5<T1, T2, T3, T4, T5>, ? extends R, E> msgAction) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4, value5));
            NV newValue = computeValue.$(value5);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null7Matcher<>(doBreak, value1, value2, value3, value4, value5, newValue, localMsg);
        }
        return new Null7Matcher<>(true, value1, value2, value3, value4, value5, null, this.msg);
    }

    public <NV, E extends Throwable> Null7Matcher<T1, T2, T3, T4, T5, NV, R> withW(PatternNull5 patternNull, RT1<? super T5, ? extends NV, E> computeValue, RT1<Tuple5<T1, T2, T3, T4, T5>, ? extends R, E> msgAction) throws E {
        return withW(patternNull, computeValue, null, msgAction);
    }

    public <NV, E extends Throwable> Null7Matcher<T1, T2, T3, T4, T5, NV, R> withW(PatternNull5 patternNull, RT1<? super T5, ? extends NV, E> computeValue, RT1<? super NV, Boolean, E> action, R msg) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        if (!isMatch) {
            NV newValue = computeValue.$(value5);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null7Matcher<>(doBreak, value1, value2, value3, value4, value5, newValue, localMsg);
        }
        return new Null7Matcher<>(true, value1, value2, value3, value4, value5, null, this.msg);
    }

    public <NV, E extends Throwable> Null7Matcher<T1, T2, T3, T4, T5, NV, R> withW(PatternNull5 patternNull, RT1<? super T5, ? extends NV, E> computeValue, R msg) throws E {
        return withW(patternNull, computeValue, null, msg);
    }


    public Tuple6<T1, T2, T3, T4, T5, R> orElse(R msg) {
        R finalMsg = !isMatch ? msg : this.msg;
        return Tuple.of(value1, value2, value3, value4, value5, finalMsg);
    }
}
