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
import com.iofairy.lambda.RT0;
import com.iofairy.pattern.PatternIn;
import com.iofairy.pattern.type.*;
import java.util.List;
import java.util.Objects;


/**
 * String Matcher with return value
 * @since 0.0.1
 */
public class StringRMatcher<R> extends SimpleRInMatcher<String, String, String, R> {

    private PatternString patternString;
    private String ucValue;     // Upper Case Value
    private boolean ignoreCase = false;
    private boolean isMatchForNext = false;

    public StringRMatcher(String value, PatternString patternString) {
        this(value);
        this.patternString = patternString;

        ucValue = value;
        if (value != null){
            if (patternString == PatternString.ICCONTAIN
                    || patternString == PatternString.ICPREFIX
                    || patternString == PatternString.ICSUFFIX){
                ucValue = value.toUpperCase();
                ignoreCase = true;
            }
        }
    }

    public StringRMatcher(String value, boolean isMatch) {
        super(value, isMatch);
    }

    public StringRMatcher(String value) {
        this(value, false);
    }

    @Override
    public StringRMatcher<R> when(String value, R1<String, R> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (value == null || this.value == null) {
                if (this.value == value) {
                    isMatch = true;
                    returnValue = action.$(this.value);
                }
                return this;
            }

            String ucValue = ignoreCase ? value.toUpperCase() : value;

            switch (patternString) {
                case IGNORECASE:
                    if (ucValue.equalsIgnoreCase(this.ucValue)) {
                        isMatch = true;
                        returnValue = action.$(this.value);
                    }
                    break;
                case CONTAIN:
                case ICCONTAIN:
                    if (this.ucValue.contains(ucValue)) {
                        isMatch = true;
                        returnValue = action.$(this.value);
                    }
                    break;
                case PREFIX:
                case ICPREFIX:
                    if (this.ucValue.startsWith(ucValue)) {
                        isMatch = true;
                        returnValue = action.$(this.value);
                    }
                    break;
                case SUFFIX:
                case ICSUFFIX:
                    if (this.ucValue.endsWith(ucValue)) {
                        isMatch = true;
                        returnValue = action.$(this.value);
                    }
                    break;
                default:
                    if (this.ucValue.equals(ucValue)) {
                        isMatch = true;
                        returnValue = action.$(this.value);
                    }
            }
        }

        return this;
    }

    @Override
    public StringRMatcher<R> whenNext(String value, R1<String, R> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (value == null || this.value == null) {
                if (this.value == value) {
                    returnValue = action.$(this.value);
                }
                return this;
            }

            String ucValue = ignoreCase ? value.toUpperCase() : value;

            switch (patternString) {
                case IGNORECASE:
                    if (ucValue.equalsIgnoreCase(this.ucValue)) {
                        isMatchForNext = true;
                        returnValue = action.$(this.value);
                    }
                    break;
                case CONTAIN:
                case ICCONTAIN:
                    if (this.ucValue.contains(ucValue)) {
                        isMatchForNext = true;
                        returnValue = action.$(this.value);
                    }
                    break;
                case PREFIX:
                case ICPREFIX:
                    if (this.ucValue.startsWith(ucValue)) {
                        isMatchForNext = true;
                        returnValue = action.$(this.value);
                    }
                    break;
                case SUFFIX:
                case ICSUFFIX:
                    if (this.ucValue.endsWith(ucValue)) {
                        isMatchForNext = true;
                        returnValue = action.$(this.value);
                    }
                    break;
                default:
                    if (this.ucValue.equals(ucValue)) {
                        isMatchForNext = true;
                        returnValue = action.$(this.value);
                    }
            }
        }

        return this;
    }

    @Override
    public <E extends Throwable> StringRMatcher<R> when(String value, RT0<R, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (value == null || this.value == null) {
                if (this.value == value) {
                    isMatch = true;
                    returnValue = action.$();
                }
                return this;
            }

            String ucValue = ignoreCase ? value.toUpperCase() : value;

            switch (patternString) {
                case IGNORECASE:
                    if (ucValue.equalsIgnoreCase(this.ucValue)) {
                        isMatch = true;
                        returnValue = action.$();
                    }
                    break;
                case CONTAIN:
                case ICCONTAIN:
                    if (this.ucValue.contains(ucValue)) {
                        isMatch = true;
                        returnValue = action.$();
                    }
                    break;
                case PREFIX:
                case ICPREFIX:
                    if (this.ucValue.startsWith(ucValue)) {
                        isMatch = true;
                        returnValue = action.$();
                    }
                    break;
                case SUFFIX:
                case ICSUFFIX:
                    if (this.ucValue.endsWith(ucValue)) {
                        isMatch = true;
                        returnValue = action.$();
                    }
                    break;
                default:
                    if (this.ucValue.equals(ucValue)) {
                        isMatch = true;
                        returnValue = action.$();
                    }
            }
        }

        return this;
    }

    @Override
    public <E extends Throwable> StringRMatcher<R> whenNext(String value, RT0<R, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (value == null || this.value == null) {
                if (this.value == value) {
                    returnValue = action.$();
                }
                return this;
            }

            String ucValue = ignoreCase ? value.toUpperCase() : value;

            switch (patternString) {
                case IGNORECASE:
                    if (ucValue.equalsIgnoreCase(this.ucValue)) {
                        isMatchForNext = true;
                        returnValue = action.$();
                    }
                    break;
                case CONTAIN:
                case ICCONTAIN:
                    if (this.ucValue.contains(ucValue)) {
                        isMatchForNext = true;
                        returnValue = action.$();
                    }
                    break;
                case PREFIX:
                case ICPREFIX:
                    if (this.ucValue.startsWith(ucValue)) {
                        isMatchForNext = true;
                        returnValue = action.$();
                    }
                    break;
                case SUFFIX:
                case ICSUFFIX:
                    if (this.ucValue.endsWith(ucValue)) {
                        isMatchForNext = true;
                        returnValue = action.$();
                    }
                    break;
                default:
                    if (this.ucValue.equals(ucValue)) {
                        isMatchForNext = true;
                        returnValue = action.$();
                    }
            }
        }

        return this;
    }

    @Override
    public StringRMatcher<R> when(boolean value, R1<String, R> action) {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            isMatch = true;
            returnValue = action.$(this.value);
        }
        return this;
    }

    @Override
    public StringRMatcher<R> whenNext(boolean value, R1<String, R> action) {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            returnValue = action.$(this.value);
        }
        return this;
    }

    @Override
    public <E extends Throwable> StringRMatcher<R> when(boolean value, RT0<R, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            isMatch = true;
            returnValue = action.$();
        }
        return this;
    }

    @Override
    public <E extends Throwable> StringRMatcher<R> whenNext(boolean value, RT0<R, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            returnValue = action.$();
        }
        return this;
    }

    @Override
    public StringRMatcher<R> when(PatternIn<String> values, R1<String, R> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (this.value == null) {
                if (values == null || values.getVs().contains(this.value)) {
                    isMatch = true;
                    returnValue = action.$(this.value);
                }
                return this;
            }

            if (values != null) {
                List<String> vs = values.getVs();
                for (String v : vs) {
                    if (v != null) {
                        when(v, action);
                        if (isMatch) break;
                    }
                }
            }
        }
        return this;
    }

    @Override
    public StringRMatcher<R> whenNext(PatternIn<String> values, R1<String, R> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (this.value == null) {
                if (values == null || values.getVs().contains(this.value)) {
                    returnValue = action.$(this.value);
                }
                return this;
            }else {
                if (values != null) {
                    List<String> vs = values.getVs();
                    for (String v : vs) {
                        if (v != null) {
                            whenNext(v, action);
                            if (isMatchForNext) break;
                        }
                    }
                }
            }
        }
        return this;
    }

    @Override
    public <E extends Throwable> StringRMatcher<R> when(PatternIn<String> values, RT0<R, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (this.value == null) {
                if (values == null || values.getVs().contains(this.value)) {
                    isMatch = true;
                    returnValue = action.$();
                }
                return this;
            }

            if (values != null) {
                List<String> vs = values.getVs();
                for (String v : vs) {
                    if (v != null) {
                        when(v, action);
                        if (isMatch) break;
                    }
                }
            }
        }
        return this;
    }

    @Override
    public <E extends Throwable> StringRMatcher<R> whenNext(PatternIn<String> values, RT0<R, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (this.value == null) {
                if (values == null || values.getVs().contains(this.value)) {
                    returnValue = action.$();
                }
                return this;
            }else {
                if (values != null) {
                    List<String> vs = values.getVs();
                    for (String v : vs) {
                        if (v != null) {
                            whenNext(v, action);
                            if (isMatchForNext) break;
                        }
                    }
                }
            }
        }
        return this;
    }


    @Override
    public R orElse(R1<String, R> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            returnValue = action.$(this.value);
        }
        return returnValue;
    }

    @Override
    public <E extends Throwable> R orElse(RT0<R, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch) {
            returnValue = action.$();
        }
        return returnValue;
    }
}
