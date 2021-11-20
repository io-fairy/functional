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
 * Null5Matcher
 *
 * @since 0.2.0
 */
public class Null5Matcher<T1, T2, T3, T4, R> implements Matcher {
    private final R msg;
    private final T1 value1;
    private final T2 value2;
    private final T3 value3;
    private final T4 value4;
    private final boolean isMatch;

    Null5Matcher(boolean isMatch, T1 value1, T2 value2, T3 value3, T4 value4, R msg) {
        this.isMatch = isMatch;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
        this.msg = msg;
    }


    public <V, NV> Null6Matcher<T1, T2, T3, T4, NV, R> whenV(V matchValue, R1<V, NV> computeValue, R1<NV, Boolean> action, R1<Tuple4<T1, T2, T3, T4>, R> msgAction) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4));
            if (matchValue == null) {
                return new Null6Matcher<>(true, value1, value2, value3, value4, null, msg);
            }

            NV newValue = computeValue.$(matchValue);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null6Matcher<>(doBreak, value1, value2, value3, value4, newValue, localMsg);
        }
        return new Null6Matcher<>(true, value1, value2, value3, value4, null, this.msg);
    }

    public <V, NV> Null6Matcher<T1, T2, T3, T4, NV, R> whenV(V matchValue, R1<V, NV> computeValue, R1<Tuple4<T1, T2, T3, T4>, R> msgAction) {
        return whenV(matchValue, computeValue, null, msgAction);
    }

    public <V, NV> Null6Matcher<T1, T2, T3, T4, NV, R> whenV(V matchValue, R1<V, NV> computeValue, R1<NV, Boolean> action, R msg) {
        Objects.requireNonNull(computeValue);
        if (!isMatch) {
            if (matchValue == null) {
                return new Null6Matcher<>(true, value1, value2, value3, value4, null, msg);
            }

            NV newValue = computeValue.$(matchValue);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null6Matcher<>(doBreak, value1, value2, value3, value4, newValue, localMsg);
        }
        return new Null6Matcher<>(true, value1, value2, value3, value4, null, this.msg);
    }

    public <V, NV> Null6Matcher<T1, T2, T3, T4, NV, R> whenV(V matchValue, R1<V, NV> computeValue, R msg) {
        return whenV(matchValue, computeValue, null, msg);
    }

    public <NV> Null6Matcher<T1, T2, T3, T4, NV, R> whenW(PatternNull1 patternNull, R1<T1, NV> computeValue, R1<NV, Boolean> action, R1<Tuple4<T1, T2, T3, T4>, R> msgAction) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4));
            NV newValue = computeValue.$(value1);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null6Matcher<>(doBreak, value1, value2, value3, value4, newValue, localMsg);
        }
        return new Null6Matcher<>(true, value1, value2, value3, value4, null, this.msg);
    }

    public <NV> Null6Matcher<T1, T2, T3, T4, NV, R> whenW(PatternNull1 patternNull, R1<T1, NV> computeValue, R1<Tuple4<T1, T2, T3, T4>, R> msgAction) {
        return whenW(patternNull, computeValue, null, msgAction);
    }

    public <NV> Null6Matcher<T1, T2, T3, T4, NV, R> whenW(PatternNull1 patternNull, R1<T1, NV> computeValue, R1<NV, Boolean> action, R msg) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        if (!isMatch) {
            NV newValue = computeValue.$(value1);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null6Matcher<>(doBreak, value1, value2, value3, value4, newValue, localMsg);
        }
        return new Null6Matcher<>(true, value1, value2, value3, value4, null, this.msg);
    }

    public <NV> Null6Matcher<T1, T2, T3, T4, NV, R> whenW(PatternNull1 patternNull, R1<T1, NV> computeValue, R msg) {
        return whenW(patternNull, computeValue, null, msg);
    }

    public <NV> Null6Matcher<T1, T2, T3, T4, NV, R> whenW(PatternNull2 patternNull, R1<T2, NV> computeValue, R1<NV, Boolean> action, R1<Tuple4<T1, T2, T3, T4>, R> msgAction) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4));
            NV newValue = computeValue.$(value2);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null6Matcher<>(doBreak, value1, value2, value3, value4, newValue, localMsg);
        }
        return new Null6Matcher<>(true, value1, value2, value3, value4, null, this.msg);
    }

    public <NV> Null6Matcher<T1, T2, T3, T4, NV, R> whenW(PatternNull2 patternNull, R1<T2, NV> computeValue, R1<Tuple4<T1, T2, T3, T4>, R> msgAction) {
        return whenW(patternNull, computeValue, null, msgAction);
    }

    public <NV> Null6Matcher<T1, T2, T3, T4, NV, R> whenW(PatternNull2 patternNull, R1<T2, NV> computeValue, R1<NV, Boolean> action, R msg) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        if (!isMatch) {
            NV newValue = computeValue.$(value2);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null6Matcher<>(doBreak, value1, value2, value3, value4, newValue, localMsg);
        }
        return new Null6Matcher<>(true, value1, value2, value3, value4, null, this.msg);
    }

    public <NV> Null6Matcher<T1, T2, T3, T4, NV, R> whenW(PatternNull2 patternNull, R1<T2, NV> computeValue, R msg) {
        return whenW(patternNull, computeValue, null, msg);
    }

    public <NV> Null6Matcher<T1, T2, T3, T4, NV, R> whenW(PatternNull3 patternNull, R1<T3, NV> computeValue, R1<NV, Boolean> action, R1<Tuple4<T1, T2, T3, T4>, R> msgAction) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4));
            NV newValue = computeValue.$(value3);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null6Matcher<>(doBreak, value1, value2, value3, value4, newValue, localMsg);
        }
        return new Null6Matcher<>(true, value1, value2, value3, value4, null, this.msg);
    }

    public <NV> Null6Matcher<T1, T2, T3, T4, NV, R> whenW(PatternNull3 patternNull, R1<T3, NV> computeValue, R1<Tuple4<T1, T2, T3, T4>, R> msgAction) {
        return whenW(patternNull, computeValue, null, msgAction);
    }

    public <NV> Null6Matcher<T1, T2, T3, T4, NV, R> whenW(PatternNull3 patternNull, R1<T3, NV> computeValue, R1<NV, Boolean> action, R msg) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        if (!isMatch) {
            NV newValue = computeValue.$(value3);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null6Matcher<>(doBreak, value1, value2, value3, value4, newValue, localMsg);
        }
        return new Null6Matcher<>(true, value1, value2, value3, value4, null, this.msg);
    }

    public <NV> Null6Matcher<T1, T2, T3, T4, NV, R> whenW(PatternNull3 patternNull, R1<T3, NV> computeValue, R msg) {
        return whenW(patternNull, computeValue, null, msg);
    }

    public <NV> Null6Matcher<T1, T2, T3, T4, NV, R> whenW(PatternNull4 patternNull, R1<T4, NV> computeValue, R1<NV, Boolean> action, R1<Tuple4<T1, T2, T3, T4>, R> msgAction) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4));
            NV newValue = computeValue.$(value4);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null6Matcher<>(doBreak, value1, value2, value3, value4, newValue, localMsg);
        }
        return new Null6Matcher<>(true, value1, value2, value3, value4, null, this.msg);
    }

    public <NV> Null6Matcher<T1, T2, T3, T4, NV, R> whenW(PatternNull4 patternNull, R1<T4, NV> computeValue, R1<Tuple4<T1, T2, T3, T4>, R> msgAction) {
        return whenW(patternNull, computeValue, null, msgAction);
    }

    public <NV> Null6Matcher<T1, T2, T3, T4, NV, R> whenW(PatternNull4 patternNull, R1<T4, NV> computeValue, R1<NV, Boolean> action, R msg) {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        if (!isMatch) {
            NV newValue = computeValue.$(value4);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null6Matcher<>(doBreak, value1, value2, value3, value4, newValue, localMsg);
        }
        return new Null6Matcher<>(true, value1, value2, value3, value4, null, this.msg);
    }

    public <NV> Null6Matcher<T1, T2, T3, T4, NV, R> whenW(PatternNull4 patternNull, R1<T4, NV> computeValue, R msg) {
        return whenW(patternNull, computeValue, null, msg);
    }

    /*
     * ########################################################################
     * ************************************************************************
     * ################   NullMatcher with throwing exception   ###############
     * ************************************************************************
     * ########################################################################
     */

    public <V, NV, E extends Throwable> Null6Matcher<T1, T2, T3, T4, NV, R> withV(V matchValue, RT1<V, NV, E> computeValue, RT1<NV, Boolean, E> action, RT1<Tuple4<T1, T2, T3, T4>, R, E> msgAction) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4));
            if (matchValue == null) {
                return new Null6Matcher<>(true, value1, value2, value3, value4, null, msg);
            }

            NV newValue = computeValue.$(matchValue);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null6Matcher<>(doBreak, value1, value2, value3, value4, newValue, localMsg);
        }
        return new Null6Matcher<>(true, value1, value2, value3, value4, null, this.msg);
    }

    public <V, NV, E extends Throwable> Null6Matcher<T1, T2, T3, T4, NV, R> withV(V matchValue, RT1<V, NV, E> computeValue, RT1<Tuple4<T1, T2, T3, T4>, R, E> msgAction) throws E {
        return withV(matchValue, computeValue, null, msgAction);
    }

    public <V, NV, E extends Throwable> Null6Matcher<T1, T2, T3, T4, NV, R> withV(V matchValue, RT1<V, NV, E> computeValue, RT1<NV, Boolean, E> action, R msg) throws E {
        Objects.requireNonNull(computeValue);
        if (!isMatch) {
            if (matchValue == null) {
                return new Null6Matcher<>(true, value1, value2, value3, value4, null, msg);
            }

            NV newValue = computeValue.$(matchValue);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null6Matcher<>(doBreak, value1, value2, value3, value4, newValue, localMsg);
        }
        return new Null6Matcher<>(true, value1, value2, value3, value4, null, this.msg);
    }

    public <V, NV, E extends Throwable> Null6Matcher<T1, T2, T3, T4, NV, R> withV(V matchValue, RT1<V, NV, E> computeValue, R msg) throws E {
        return withV(matchValue, computeValue, null, msg);
    }

    public <NV, E extends Throwable> Null6Matcher<T1, T2, T3, T4, NV, R> withW(PatternNull1 patternNull, RT1<T1, NV, E> computeValue, RT1<NV, Boolean, E> action, RT1<Tuple4<T1, T2, T3, T4>, R, E> msgAction) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4));
            NV newValue = computeValue.$(value1);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null6Matcher<>(doBreak, value1, value2, value3, value4, newValue, localMsg);
        }
        return new Null6Matcher<>(true, value1, value2, value3, value4, null, this.msg);
    }

    public <NV, E extends Throwable> Null6Matcher<T1, T2, T3, T4, NV, R> withW(PatternNull1 patternNull, RT1<T1, NV, E> computeValue, RT1<Tuple4<T1, T2, T3, T4>, R, E> msgAction) throws E {
        return withW(patternNull, computeValue, null, msgAction);
    }

    public <NV, E extends Throwable> Null6Matcher<T1, T2, T3, T4, NV, R> withW(PatternNull1 patternNull, RT1<T1, NV, E> computeValue, RT1<NV, Boolean, E> action, R msg) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        if (!isMatch) {
            NV newValue = computeValue.$(value1);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null6Matcher<>(doBreak, value1, value2, value3, value4, newValue, localMsg);
        }
        return new Null6Matcher<>(true, value1, value2, value3, value4, null, this.msg);
    }

    public <NV, E extends Throwable> Null6Matcher<T1, T2, T3, T4, NV, R> withW(PatternNull1 patternNull, RT1<T1, NV, E> computeValue, R msg) throws E {
        return withW(patternNull, computeValue, null, msg);
    }

    public <NV, E extends Throwable> Null6Matcher<T1, T2, T3, T4, NV, R> withW(PatternNull2 patternNull, RT1<T2, NV, E> computeValue, RT1<NV, Boolean, E> action, RT1<Tuple4<T1, T2, T3, T4>, R, E> msgAction) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4));
            NV newValue = computeValue.$(value2);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null6Matcher<>(doBreak, value1, value2, value3, value4, newValue, localMsg);
        }
        return new Null6Matcher<>(true, value1, value2, value3, value4, null, this.msg);
    }

    public <NV, E extends Throwable> Null6Matcher<T1, T2, T3, T4, NV, R> withW(PatternNull2 patternNull, RT1<T2, NV, E> computeValue, RT1<Tuple4<T1, T2, T3, T4>, R, E> msgAction) throws E {
        return withW(patternNull, computeValue, null, msgAction);
    }

    public <NV, E extends Throwable> Null6Matcher<T1, T2, T3, T4, NV, R> withW(PatternNull2 patternNull, RT1<T2, NV, E> computeValue, RT1<NV, Boolean, E> action, R msg) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        if (!isMatch) {
            NV newValue = computeValue.$(value2);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null6Matcher<>(doBreak, value1, value2, value3, value4, newValue, localMsg);
        }
        return new Null6Matcher<>(true, value1, value2, value3, value4, null, this.msg);
    }

    public <NV, E extends Throwable> Null6Matcher<T1, T2, T3, T4, NV, R> withW(PatternNull2 patternNull, RT1<T2, NV, E> computeValue, R msg) throws E {
        return withW(patternNull, computeValue, null, msg);
    }

    public <NV, E extends Throwable> Null6Matcher<T1, T2, T3, T4, NV, R> withW(PatternNull3 patternNull, RT1<T3, NV, E> computeValue, RT1<NV, Boolean, E> action, RT1<Tuple4<T1, T2, T3, T4>, R, E> msgAction) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4));
            NV newValue = computeValue.$(value3);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null6Matcher<>(doBreak, value1, value2, value3, value4, newValue, localMsg);
        }
        return new Null6Matcher<>(true, value1, value2, value3, value4, null, this.msg);
    }

    public <NV, E extends Throwable> Null6Matcher<T1, T2, T3, T4, NV, R> withW(PatternNull3 patternNull, RT1<T3, NV, E> computeValue, RT1<Tuple4<T1, T2, T3, T4>, R, E> msgAction) throws E {
        return withW(patternNull, computeValue, null, msgAction);
    }

    public <NV, E extends Throwable> Null6Matcher<T1, T2, T3, T4, NV, R> withW(PatternNull3 patternNull, RT1<T3, NV, E> computeValue, RT1<NV, Boolean, E> action, R msg) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        if (!isMatch) {
            NV newValue = computeValue.$(value3);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null6Matcher<>(doBreak, value1, value2, value3, value4, newValue, localMsg);
        }
        return new Null6Matcher<>(true, value1, value2, value3, value4, null, this.msg);
    }

    public <NV, E extends Throwable> Null6Matcher<T1, T2, T3, T4, NV, R> withW(PatternNull3 patternNull, RT1<T3, NV, E> computeValue, R msg) throws E {
        return withW(patternNull, computeValue, null, msg);
    }

    public <NV, E extends Throwable> Null6Matcher<T1, T2, T3, T4, NV, R> withW(PatternNull4 patternNull, RT1<T4, NV, E> computeValue, RT1<NV, Boolean, E> action, RT1<Tuple4<T1, T2, T3, T4>, R, E> msgAction) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        Objects.requireNonNull(msgAction);
        if (!isMatch) {
            R msg = msgAction.$(Tuple.of(value1, value2, value3, value4));
            NV newValue = computeValue.$(value4);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null6Matcher<>(doBreak, value1, value2, value3, value4, newValue, localMsg);
        }
        return new Null6Matcher<>(true, value1, value2, value3, value4, null, this.msg);
    }

    public <NV, E extends Throwable> Null6Matcher<T1, T2, T3, T4, NV, R> withW(PatternNull4 patternNull, RT1<T4, NV, E> computeValue, RT1<Tuple4<T1, T2, T3, T4>, R, E> msgAction) throws E {
        return withW(patternNull, computeValue, null, msgAction);
    }

    public <NV, E extends Throwable> Null6Matcher<T1, T2, T3, T4, NV, R> withW(PatternNull4 patternNull, RT1<T4, NV, E> computeValue, RT1<NV, Boolean, E> action, R msg) throws E {
        Objects.requireNonNull(computeValue);
        Objects.requireNonNull(patternNull);
        if (!isMatch) {
            NV newValue = computeValue.$(value4);
            boolean doBreak = newValue == null || (action != null && action.$(newValue));
            R localMsg = doBreak ? msg : null;
            return new Null6Matcher<>(doBreak, value1, value2, value3, value4, newValue, localMsg);
        }
        return new Null6Matcher<>(true, value1, value2, value3, value4, null, this.msg);
    }

    public <NV, E extends Throwable> Null6Matcher<T1, T2, T3, T4, NV, R> withW(PatternNull4 patternNull, RT1<T4, NV, E> computeValue, R msg) throws E {
        return withW(patternNull, computeValue, null, msg);
    }


    public Tuple5<T1, T2, T3, T4, R> orElse(R msg) {
        R finalMsg = !isMatch ? msg : this.msg;
        return Tuple.of(value1, value2, value3, value4, finalMsg);
    }
}
