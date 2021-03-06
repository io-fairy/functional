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
 * Null7Matcher
 *
 * @since 0.2.0
 */
public class Null7Matcher<T1, T2, T3, T4, T5, T6, R> implements NullMatcher {
    private final R msg;
    private final T1 value1;
    private final T2 value2;
    private final T3 value3;
    private final T4 value4;
    private final T5 value5;
    private final T6 value6;
    private final boolean isMatch;

    Null7Matcher(boolean isMatch, T1 value1, T2 value2, T3 value3, T4 value4, T5 value5, T6 value6, R msg) {
        this.isMatch = isMatch;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
        this.value5 = value5;
        this.value6 = value6;
        this.msg = msg;
    }


    public <V, NV> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> whenV(V matchValue, R1<? super V, ? extends NV> computeValue, R1<? super NV, Boolean> action, R1<Tuple6<T1, T2, T3, T4, T5, T6>, ? extends R> msgAction) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4, value5, value6));
            if (matchValue == null) {
                return new Null8Matcher<>(true, value1, value2, value3, value4, value5, value6, null, msg);
            }

            NV newValue = computeValue.$(matchValue);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null8Matcher<>(doBreak, value1, value2, value3, value4, value5, value6, newValue, localMsg);
        }
        return new Null8Matcher<>(true, value1, value2, value3, value4, value5, value6, null, this.msg);
    }

    public <V, NV> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> whenV(V matchValue, R1<? super V, ? extends NV> computeValue, R1<Tuple6<T1, T2, T3, T4, T5, T6>, ? extends R> msgAction) {
        return whenV(matchValue, computeValue, null, msgAction);
    }

    public <V, NV> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> whenV(V matchValue, R1<? super V, ? extends NV> computeValue, R1<? super NV, Boolean> action, R msg) {
        Objects.requireNonNull(computeValue);
        if (!isMatch) {
            if (matchValue == null) {
                return new Null8Matcher<>(true, value1, value2, value3, value4, value5, value6, null, msg);
            }

            NV newValue = computeValue.$(matchValue);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null8Matcher<>(doBreak, value1, value2, value3, value4, value5, value6, newValue, localMsg);
        }
        return new Null8Matcher<>(true, value1, value2, value3, value4, value5, value6, null, this.msg);
    }

    public <V, NV> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> whenV(V matchValue, R1<? super V, ? extends NV> computeValue, R msg) {
        return whenV(matchValue, computeValue, null, msg);
    }

    public <NV> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> whenW(PatternNull1 patternNull, R1<? super T1, ? extends NV> computeValue, R1<? super NV, Boolean> action, R1<Tuple6<T1, T2, T3, T4, T5, T6>, ? extends R> msgAction) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4, value5, value6));
            NV newValue = computeValue.$(value1);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null8Matcher<>(doBreak, value1, value2, value3, value4, value5, value6, newValue, localMsg);
        }
        return new Null8Matcher<>(true, value1, value2, value3, value4, value5, value6, null, this.msg);
    }

    public <NV> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> whenW(PatternNull1 patternNull, R1<? super T1, ? extends NV> computeValue, R1<Tuple6<T1, T2, T3, T4, T5, T6>, ? extends R> msgAction) {
        return whenW(patternNull, computeValue, null, msgAction);
    }

    public <NV> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> whenW(PatternNull1 patternNull, R1<? super T1, ? extends NV> computeValue, R1<? super NV, Boolean> action, R msg) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        if (!isMatch) {
            NV newValue = computeValue.$(value1);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null8Matcher<>(doBreak, value1, value2, value3, value4, value5, value6, newValue, localMsg);
        }
        return new Null8Matcher<>(true, value1, value2, value3, value4, value5, value6, null, this.msg);
    }

    public <NV> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> whenW(PatternNull1 patternNull, R1<? super T1, ? extends NV> computeValue, R msg) {
        return whenW(patternNull, computeValue, null, msg);
    }

    public <NV> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> whenW(PatternNull2 patternNull, R1<? super T2, ? extends NV> computeValue, R1<? super NV, Boolean> action, R1<Tuple6<T1, T2, T3, T4, T5, T6>, ? extends R> msgAction) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4, value5, value6));
            NV newValue = computeValue.$(value2);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null8Matcher<>(doBreak, value1, value2, value3, value4, value5, value6, newValue, localMsg);
        }
        return new Null8Matcher<>(true, value1, value2, value3, value4, value5, value6, null, this.msg);
    }

    public <NV> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> whenW(PatternNull2 patternNull, R1<? super T2, ? extends NV> computeValue, R1<Tuple6<T1, T2, T3, T4, T5, T6>, ? extends R> msgAction) {
        return whenW(patternNull, computeValue, null, msgAction);
    }

    public <NV> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> whenW(PatternNull2 patternNull, R1<? super T2, ? extends NV> computeValue, R1<? super NV, Boolean> action, R msg) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        if (!isMatch) {
            NV newValue = computeValue.$(value2);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null8Matcher<>(doBreak, value1, value2, value3, value4, value5, value6, newValue, localMsg);
        }
        return new Null8Matcher<>(true, value1, value2, value3, value4, value5, value6, null, this.msg);
    }

    public <NV> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> whenW(PatternNull2 patternNull, R1<? super T2, ? extends NV> computeValue, R msg) {
        return whenW(patternNull, computeValue, null, msg);
    }

    public <NV> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> whenW(PatternNull3 patternNull, R1<? super T3, ? extends NV> computeValue, R1<? super NV, Boolean> action, R1<Tuple6<T1, T2, T3, T4, T5, T6>, ? extends R> msgAction) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4, value5, value6));
            NV newValue = computeValue.$(value3);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null8Matcher<>(doBreak, value1, value2, value3, value4, value5, value6, newValue, localMsg);
        }
        return new Null8Matcher<>(true, value1, value2, value3, value4, value5, value6, null, this.msg);
    }

    public <NV> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> whenW(PatternNull3 patternNull, R1<? super T3, ? extends NV> computeValue, R1<Tuple6<T1, T2, T3, T4, T5, T6>, ? extends R> msgAction) {
        return whenW(patternNull, computeValue, null, msgAction);
    }

    public <NV> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> whenW(PatternNull3 patternNull, R1<? super T3, ? extends NV> computeValue, R1<? super NV, Boolean> action, R msg) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        if (!isMatch) {
            NV newValue = computeValue.$(value3);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null8Matcher<>(doBreak, value1, value2, value3, value4, value5, value6, newValue, localMsg);
        }
        return new Null8Matcher<>(true, value1, value2, value3, value4, value5, value6, null, this.msg);
    }

    public <NV> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> whenW(PatternNull3 patternNull, R1<? super T3, ? extends NV> computeValue, R msg) {
        return whenW(patternNull, computeValue, null, msg);
    }

    public <NV> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> whenW(PatternNull4 patternNull, R1<? super T4, ? extends NV> computeValue, R1<? super NV, Boolean> action, R1<Tuple6<T1, T2, T3, T4, T5, T6>, ? extends R> msgAction) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4, value5, value6));
            NV newValue = computeValue.$(value4);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null8Matcher<>(doBreak, value1, value2, value3, value4, value5, value6, newValue, localMsg);
        }
        return new Null8Matcher<>(true, value1, value2, value3, value4, value5, value6, null, this.msg);
    }

    public <NV> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> whenW(PatternNull4 patternNull, R1<? super T4, ? extends NV> computeValue, R1<Tuple6<T1, T2, T3, T4, T5, T6>, ? extends R> msgAction) {
        return whenW(patternNull, computeValue, null, msgAction);
    }

    public <NV> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> whenW(PatternNull4 patternNull, R1<? super T4, ? extends NV> computeValue, R1<? super NV, Boolean> action, R msg) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        if (!isMatch) {
            NV newValue = computeValue.$(value4);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null8Matcher<>(doBreak, value1, value2, value3, value4, value5, value6, newValue, localMsg);
        }
        return new Null8Matcher<>(true, value1, value2, value3, value4, value5, value6, null, this.msg);
    }

    public <NV> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> whenW(PatternNull4 patternNull, R1<? super T4, ? extends NV> computeValue, R msg) {
        return whenW(patternNull, computeValue, null, msg);
    }

    public <NV> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> whenW(PatternNull5 patternNull, R1<? super T5, ? extends NV> computeValue, R1<? super NV, Boolean> action, R1<Tuple6<T1, T2, T3, T4, T5, T6>, ? extends R> msgAction) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4, value5, value6));
            NV newValue = computeValue.$(value5);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null8Matcher<>(doBreak, value1, value2, value3, value4, value5, value6, newValue, localMsg);
        }
        return new Null8Matcher<>(true, value1, value2, value3, value4, value5, value6, null, this.msg);
    }

    public <NV> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> whenW(PatternNull5 patternNull, R1<? super T5, ? extends NV> computeValue, R1<Tuple6<T1, T2, T3, T4, T5, T6>, ? extends R> msgAction) {
        return whenW(patternNull, computeValue, null, msgAction);
    }

    public <NV> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> whenW(PatternNull5 patternNull, R1<? super T5, ? extends NV> computeValue, R1<? super NV, Boolean> action, R msg) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        if (!isMatch) {
            NV newValue = computeValue.$(value5);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null8Matcher<>(doBreak, value1, value2, value3, value4, value5, value6, newValue, localMsg);
        }
        return new Null8Matcher<>(true, value1, value2, value3, value4, value5, value6, null, this.msg);
    }

    public <NV> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> whenW(PatternNull5 patternNull, R1<? super T5, ? extends NV> computeValue, R msg) {
        return whenW(patternNull, computeValue, null, msg);
    }

    public <NV> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> whenW(PatternNull6 patternNull, R1<? super T6, ? extends NV> computeValue, R1<? super NV, Boolean> action, R1<Tuple6<T1, T2, T3, T4, T5, T6>, ? extends R> msgAction) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4, value5, value6));
            NV newValue = computeValue.$(value6);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null8Matcher<>(doBreak, value1, value2, value3, value4, value5, value6, newValue, localMsg);
        }
        return new Null8Matcher<>(true, value1, value2, value3, value4, value5, value6, null, this.msg);
    }

    public <NV> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> whenW(PatternNull6 patternNull, R1<? super T6, ? extends NV> computeValue, R1<Tuple6<T1, T2, T3, T4, T5, T6>, ? extends R> msgAction) {
        return whenW(patternNull, computeValue, null, msgAction);
    }

    public <NV> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> whenW(PatternNull6 patternNull, R1<? super T6, ? extends NV> computeValue, R1<? super NV, Boolean> action, R msg) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        if (!isMatch) {
            NV newValue = computeValue.$(value6);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null8Matcher<>(doBreak, value1, value2, value3, value4, value5, value6, newValue, localMsg);
        }
        return new Null8Matcher<>(true, value1, value2, value3, value4, value5, value6, null, this.msg);
    }

    public <NV> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> whenW(PatternNull6 patternNull, R1<? super T6, ? extends NV> computeValue, R msg) {
        return whenW(patternNull, computeValue, null, msg);
    }

    /*
     * ########################################################################
     * ************************************************************************
     * ################   NullMatcher with throwing exception   ###############
     * ************************************************************************
     * ########################################################################
     */

    public <V, NV, E extends Throwable> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> withV(V matchValue, RT1<? super V, ? extends NV, E> computeValue, RT1<? super NV, Boolean, E> action, RT1<Tuple6<T1, T2, T3, T4, T5, T6>, ? extends R, E> msgAction) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4, value5, value6));
            if (matchValue == null) {
                return new Null8Matcher<>(true, value1, value2, value3, value4, value5, value6, null, msg);
            }

            NV newValue = computeValue.$(matchValue);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null8Matcher<>(doBreak, value1, value2, value3, value4, value5, value6, newValue, localMsg);
        }
        return new Null8Matcher<>(true, value1, value2, value3, value4, value5, value6, null, this.msg);
    }

    public <V, NV, E extends Throwable> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> withV(V matchValue, RT1<? super V, ? extends NV, E> computeValue, RT1<Tuple6<T1, T2, T3, T4, T5, T6>, ? extends R, E> msgAction) throws E {
        return withV(matchValue, computeValue, null, msgAction);
    }

    public <V, NV, E extends Throwable> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> withV(V matchValue, RT1<? super V, ? extends NV, E> computeValue, RT1<? super NV, Boolean, E> action, R msg) throws E {
        Objects.requireNonNull(computeValue);
        if (!isMatch) {
            if (matchValue == null) {
                return new Null8Matcher<>(true, value1, value2, value3, value4, value5, value6, null, msg);
            }

            NV newValue = computeValue.$(matchValue);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null8Matcher<>(doBreak, value1, value2, value3, value4, value5, value6, newValue, localMsg);
        }
        return new Null8Matcher<>(true, value1, value2, value3, value4, value5, value6, null, this.msg);
    }

    public <V, NV, E extends Throwable> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> withV(V matchValue, RT1<? super V, ? extends NV, E> computeValue, R msg) throws E {
        return withV(matchValue, computeValue, null, msg);
    }

    public <NV, E extends Throwable> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> withW(PatternNull1 patternNull, RT1<? super T1, ? extends NV, E> computeValue, RT1<? super NV, Boolean, E> action, RT1<Tuple6<T1, T2, T3, T4, T5, T6>, ? extends R, E> msgAction) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4, value5, value6));
            NV newValue = computeValue.$(value1);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null8Matcher<>(doBreak, value1, value2, value3, value4, value5, value6, newValue, localMsg);
        }
        return new Null8Matcher<>(true, value1, value2, value3, value4, value5, value6, null, this.msg);
    }

    public <NV, E extends Throwable> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> withW(PatternNull1 patternNull, RT1<? super T1, ? extends NV, E> computeValue, RT1<Tuple6<T1, T2, T3, T4, T5, T6>, ? extends R, E> msgAction) throws E {
        return withW(patternNull, computeValue, null, msgAction);
    }

    public <NV, E extends Throwable> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> withW(PatternNull1 patternNull, RT1<? super T1, ? extends NV, E> computeValue, RT1<? super NV, Boolean, E> action, R msg) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        if (!isMatch) {
            NV newValue = computeValue.$(value1);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null8Matcher<>(doBreak, value1, value2, value3, value4, value5, value6, newValue, localMsg);
        }
        return new Null8Matcher<>(true, value1, value2, value3, value4, value5, value6, null, this.msg);
    }

    public <NV, E extends Throwable> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> withW(PatternNull1 patternNull, RT1<? super T1, ? extends NV, E> computeValue, R msg) throws E {
        return withW(patternNull, computeValue, null, msg);
    }

    public <NV, E extends Throwable> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> withW(PatternNull2 patternNull, RT1<? super T2, ? extends NV, E> computeValue, RT1<? super NV, Boolean, E> action, RT1<Tuple6<T1, T2, T3, T4, T5, T6>, ? extends R, E> msgAction) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4, value5, value6));
            NV newValue = computeValue.$(value2);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null8Matcher<>(doBreak, value1, value2, value3, value4, value5, value6, newValue, localMsg);
        }
        return new Null8Matcher<>(true, value1, value2, value3, value4, value5, value6, null, this.msg);
    }

    public <NV, E extends Throwable> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> withW(PatternNull2 patternNull, RT1<? super T2, ? extends NV, E> computeValue, RT1<Tuple6<T1, T2, T3, T4, T5, T6>, ? extends R, E> msgAction) throws E {
        return withW(patternNull, computeValue, null, msgAction);
    }

    public <NV, E extends Throwable> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> withW(PatternNull2 patternNull, RT1<? super T2, ? extends NV, E> computeValue, RT1<? super NV, Boolean, E> action, R msg) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        if (!isMatch) {
            NV newValue = computeValue.$(value2);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null8Matcher<>(doBreak, value1, value2, value3, value4, value5, value6, newValue, localMsg);
        }
        return new Null8Matcher<>(true, value1, value2, value3, value4, value5, value6, null, this.msg);
    }

    public <NV, E extends Throwable> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> withW(PatternNull2 patternNull, RT1<? super T2, ? extends NV, E> computeValue, R msg) throws E {
        return withW(patternNull, computeValue, null, msg);
    }

    public <NV, E extends Throwable> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> withW(PatternNull3 patternNull, RT1<? super T3, ? extends NV, E> computeValue, RT1<? super NV, Boolean, E> action, RT1<Tuple6<T1, T2, T3, T4, T5, T6>, ? extends R, E> msgAction) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4, value5, value6));
            NV newValue = computeValue.$(value3);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null8Matcher<>(doBreak, value1, value2, value3, value4, value5, value6, newValue, localMsg);
        }
        return new Null8Matcher<>(true, value1, value2, value3, value4, value5, value6, null, this.msg);
    }

    public <NV, E extends Throwable> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> withW(PatternNull3 patternNull, RT1<? super T3, ? extends NV, E> computeValue, RT1<Tuple6<T1, T2, T3, T4, T5, T6>, ? extends R, E> msgAction) throws E {
        return withW(patternNull, computeValue, null, msgAction);
    }

    public <NV, E extends Throwable> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> withW(PatternNull3 patternNull, RT1<? super T3, ? extends NV, E> computeValue, RT1<? super NV, Boolean, E> action, R msg) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        if (!isMatch) {
            NV newValue = computeValue.$(value3);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null8Matcher<>(doBreak, value1, value2, value3, value4, value5, value6, newValue, localMsg);
        }
        return new Null8Matcher<>(true, value1, value2, value3, value4, value5, value6, null, this.msg);
    }

    public <NV, E extends Throwable> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> withW(PatternNull3 patternNull, RT1<? super T3, ? extends NV, E> computeValue, R msg) throws E {
        return withW(patternNull, computeValue, null, msg);
    }

    public <NV, E extends Throwable> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> withW(PatternNull4 patternNull, RT1<? super T4, ? extends NV, E> computeValue, RT1<? super NV, Boolean, E> action, RT1<Tuple6<T1, T2, T3, T4, T5, T6>, ? extends R, E> msgAction) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4, value5, value6));
            NV newValue = computeValue.$(value4);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null8Matcher<>(doBreak, value1, value2, value3, value4, value5, value6, newValue, localMsg);
        }
        return new Null8Matcher<>(true, value1, value2, value3, value4, value5, value6, null, this.msg);
    }

    public <NV, E extends Throwable> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> withW(PatternNull4 patternNull, RT1<? super T4, ? extends NV, E> computeValue, RT1<Tuple6<T1, T2, T3, T4, T5, T6>, ? extends R, E> msgAction) throws E {
        return withW(patternNull, computeValue, null, msgAction);
    }

    public <NV, E extends Throwable> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> withW(PatternNull4 patternNull, RT1<? super T4, ? extends NV, E> computeValue, RT1<? super NV, Boolean, E> action, R msg) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        if (!isMatch) {
            NV newValue = computeValue.$(value4);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null8Matcher<>(doBreak, value1, value2, value3, value4, value5, value6, newValue, localMsg);
        }
        return new Null8Matcher<>(true, value1, value2, value3, value4, value5, value6, null, this.msg);
    }

    public <NV, E extends Throwable> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> withW(PatternNull4 patternNull, RT1<? super T4, ? extends NV, E> computeValue, R msg) throws E {
        return withW(patternNull, computeValue, null, msg);
    }

    public <NV, E extends Throwable> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> withW(PatternNull5 patternNull, RT1<? super T5, ? extends NV, E> computeValue, RT1<? super NV, Boolean, E> action, RT1<Tuple6<T1, T2, T3, T4, T5, T6>, ? extends R, E> msgAction) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4, value5, value6));
            NV newValue = computeValue.$(value5);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null8Matcher<>(doBreak, value1, value2, value3, value4, value5, value6, newValue, localMsg);
        }
        return new Null8Matcher<>(true, value1, value2, value3, value4, value5, value6, null, this.msg);
    }

    public <NV, E extends Throwable> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> withW(PatternNull5 patternNull, RT1<? super T5, ? extends NV, E> computeValue, RT1<Tuple6<T1, T2, T3, T4, T5, T6>, ? extends R, E> msgAction) throws E {
        return withW(patternNull, computeValue, null, msgAction);
    }

    public <NV, E extends Throwable> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> withW(PatternNull5 patternNull, RT1<? super T5, ? extends NV, E> computeValue, RT1<? super NV, Boolean, E> action, R msg) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        if (!isMatch) {
            NV newValue = computeValue.$(value5);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null8Matcher<>(doBreak, value1, value2, value3, value4, value5, value6, newValue, localMsg);
        }
        return new Null8Matcher<>(true, value1, value2, value3, value4, value5, value6, null, this.msg);
    }

    public <NV, E extends Throwable> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> withW(PatternNull5 patternNull, RT1<? super T5, ? extends NV, E> computeValue, R msg) throws E {
        return withW(patternNull, computeValue, null, msg);
    }

    public <NV, E extends Throwable> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> withW(PatternNull6 patternNull, RT1<? super T6, ? extends NV, E> computeValue, RT1<? super NV, Boolean, E> action, RT1<Tuple6<T1, T2, T3, T4, T5, T6>, ? extends R, E> msgAction) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4, value5, value6));
            NV newValue = computeValue.$(value6);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null8Matcher<>(doBreak, value1, value2, value3, value4, value5, value6, newValue, localMsg);
        }
        return new Null8Matcher<>(true, value1, value2, value3, value4, value5, value6, null, this.msg);
    }

    public <NV, E extends Throwable> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> withW(PatternNull6 patternNull, RT1<? super T6, ? extends NV, E> computeValue, RT1<Tuple6<T1, T2, T3, T4, T5, T6>, ? extends R, E> msgAction) throws E {
        return withW(patternNull, computeValue, null, msgAction);
    }

    public <NV, E extends Throwable> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> withW(PatternNull6 patternNull, RT1<? super T6, ? extends NV, E> computeValue, RT1<? super NV, Boolean, E> action, R msg) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        if (!isMatch) {
            NV newValue = computeValue.$(value6);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null8Matcher<>(doBreak, value1, value2, value3, value4, value5, value6, newValue, localMsg);
        }
        return new Null8Matcher<>(true, value1, value2, value3, value4, value5, value6, null, this.msg);
    }

    public <NV, E extends Throwable> Null8Matcher<T1, T2, T3, T4, T5, T6, NV, R> withW(PatternNull6 patternNull, RT1<? super T6, ? extends NV, E> computeValue, R msg) throws E {
        return withW(patternNull, computeValue, null, msg);
    }


    public Tuple7<T1, T2, T3, T4, T5, T6, R> orElse(R msg) {
        R finalMsg = !isMatch ? msg : this.msg;
        return Tuple.of(value1, value2, value3, value4, value5, value6, finalMsg);
    }

}
