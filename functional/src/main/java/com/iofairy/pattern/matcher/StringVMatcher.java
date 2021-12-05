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

import com.iofairy.lambda.V1;
import com.iofairy.lambda.VT1;
import com.iofairy.pattern.PatternIn;
import com.iofairy.pattern.type.*;
import java.util.List;
import java.util.Objects;


/**
 * String Matcher with void
 * @since 0.0.1
 */
public class StringVMatcher extends SimpleVInMatcher<String, String, String> {

    private PatternString patternString;

    private String ucValue;     // Upper Case Value
    private boolean ignoreCase = false;
    private boolean isMatchForNext = false;

    public StringVMatcher(String value, PatternString patternString) {
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

    public StringVMatcher(String value, boolean isMatch) {
        super(value, isMatch);
    }

    public StringVMatcher(String value) {
        this(value, false);
    }

    @Override
    public StringVMatcher when(String value, V1<? super String> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (value == null || this.value == null) {
                if (this.value == value) {
                    isMatch = true;
                    action.$(this.value);
                }
                return this;
            }

            String ucValue = ignoreCase ? value.toUpperCase() : value;

            switch (patternString) {
                case IGNORECASE:
                    if (ucValue.equalsIgnoreCase(this.ucValue)) {
                        isMatch = true;
                        action.$(this.value);
                    }
                    break;
                case CONTAIN:
                case ICCONTAIN:
                    if (this.ucValue.contains(ucValue)) {
                        isMatch = true;
                        action.$(this.value);
                    }
                    break;
                case PREFIX:
                case ICPREFIX:
                    if (this.ucValue.startsWith(ucValue)) {
                        isMatch = true;
                        action.$(this.value);
                    }
                    break;
                case SUFFIX:
                case ICSUFFIX:
                    if (this.ucValue.endsWith(ucValue)) {
                        isMatch = true;
                        action.$(this.value);
                    }
                    break;
                default:
                    if (this.ucValue.equals(ucValue)) {
                        isMatch = true;
                        action.$(this.value);
                    }
            }
        }

        return this;
    }

    @Override
    public StringVMatcher whenNext(String value, V1<? super String> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (value == null || this.value == null) {
                if (this.value == value) {
                    action.$(this.value);
                }
                return this;
            }

            String ucValue = ignoreCase ? value.toUpperCase() : value;

            switch (patternString) {
                case IGNORECASE:
                    if (ucValue.equalsIgnoreCase(this.ucValue)) {
                        isMatchForNext = true;
                        action.$(this.value);
                    }
                    break;
                case CONTAIN:
                case ICCONTAIN:
                    if (this.ucValue.contains(ucValue)) {
                        isMatchForNext = true;
                        action.$(this.value);
                    }
                    break;
                case PREFIX:
                case ICPREFIX:
                    if (this.ucValue.startsWith(ucValue)) {
                        isMatchForNext = true;
                        action.$(this.value);
                    }
                    break;
                case SUFFIX:
                case ICSUFFIX:
                    if (this.ucValue.endsWith(ucValue)) {
                        isMatchForNext = true;
                        action.$(this.value);
                    }
                    break;
                default:
                    if (this.ucValue.equals(ucValue)) {
                        isMatchForNext = true;
                        action.$(this.value);
                    }
            }
        }

        return this;
    }

    @Override
    public <E extends Throwable> StringVMatcher with(String value, VT1<? super String, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (value == null || this.value == null) {
                if (this.value == value) {
                    isMatch = true;
                    action.$(this.value);
                }
                return this;
            }

            String ucValue = ignoreCase ? value.toUpperCase() : value;

            switch (patternString) {
                case IGNORECASE:
                    if (ucValue.equalsIgnoreCase(this.ucValue)) {
                        isMatch = true;
                        action.$(this.value);
                    }
                    break;
                case CONTAIN:
                case ICCONTAIN:
                    if (this.ucValue.contains(ucValue)) {
                        isMatch = true;
                        action.$(this.value);
                    }
                    break;
                case PREFIX:
                case ICPREFIX:
                    if (this.ucValue.startsWith(ucValue)) {
                        isMatch = true;
                        action.$(this.value);
                    }
                    break;
                case SUFFIX:
                case ICSUFFIX:
                    if (this.ucValue.endsWith(ucValue)) {
                        isMatch = true;
                        action.$(this.value);
                    }
                    break;
                default:
                    if (this.ucValue.equals(ucValue)) {
                        isMatch = true;
                        action.$(this.value);
                    }
            }
        }

        return this;
    }

    @Override
    public <E extends Throwable> StringVMatcher withNext(String value, VT1<? super String, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (value == null || this.value == null) {
                if (this.value == value) {
                    action.$(this.value);
                }
                return this;
            }

            String ucValue = ignoreCase ? value.toUpperCase() : value;

            switch (patternString) {
                case IGNORECASE:
                    if (ucValue.equalsIgnoreCase(this.ucValue)) {
                        isMatchForNext = true;
                        action.$(this.value);
                    }
                    break;
                case CONTAIN:
                case ICCONTAIN:
                    if (this.ucValue.contains(ucValue)) {
                        isMatchForNext = true;
                        action.$(this.value);
                    }
                    break;
                case PREFIX:
                case ICPREFIX:
                    if (this.ucValue.startsWith(ucValue)) {
                        isMatchForNext = true;
                        action.$(this.value);
                    }
                    break;
                case SUFFIX:
                case ICSUFFIX:
                    if (this.ucValue.endsWith(ucValue)) {
                        isMatchForNext = true;
                        action.$(this.value);
                    }
                    break;
                default:
                    if (this.ucValue.equals(ucValue)) {
                        isMatchForNext = true;
                        action.$(this.value);
                    }
            }
        }

        return this;
    }

    @Override
    public StringVMatcher when(boolean value, V1<? super String> action) {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            isMatch = true;
            action.$(this.value);
        }
        return this;
    }

    @Override
    public StringVMatcher whenNext(boolean value, V1<? super String> action) {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            action.$(this.value);
        }
        return this;
    }

    @Override
    public <E extends Throwable> StringVMatcher with(boolean value, VT1<? super String, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            isMatch = true;
            action.$(this.value);
        }
        return this;
    }

    @Override
    public <E extends Throwable> StringVMatcher withNext(boolean value, VT1<? super String, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch && value) {
            action.$(this.value);
        }
        return this;
    }

    @Override
    public StringVMatcher when(PatternIn<String> values, V1<? super String> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (this.value == null) {
                if (values == null || values.getVs().contains(this.value)) {
                    isMatch = true;
                    action.$(this.value);
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
    public StringVMatcher whenNext(PatternIn<String> values, V1<? super String> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (this.value == null) {
                if (values == null || values.getVs().contains(this.value)) {
                    action.$(this.value);
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
    public <E extends Throwable> StringVMatcher with(PatternIn<String> values, VT1<? super String, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (this.value == null) {
                if (values == null || values.getVs().contains(this.value)) {
                    isMatch = true;
                    action.$(this.value);
                }
                return this;
            }

            if (values != null) {
                List<String> vs = values.getVs();
                for (String v : vs) {
                    if (v != null) {
                        with(v, action);
                        if (isMatch) break;
                    }
                }
            }
        }
        return this;
    }

    @Override
    public <E extends Throwable> StringVMatcher withNext(PatternIn<String> values, VT1<? super String, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch) {
            if (this.value == null) {
                if (values == null || values.getVs().contains(this.value)) {
                    action.$(this.value);
                }
                return this;
            }else {
                if (values != null) {
                    List<String> vs = values.getVs();
                    for (String v : vs) {
                        if (v != null) {
                            withNext(v, action);
                            if (isMatchForNext) break;
                        }
                    }
                }
            }
        }
        return this;
    }

    @Override
    public Void orElse(V1<? super String> action) {
        Objects.requireNonNull(action);
        if (!isMatch) {
            action.$(this.value);
        }
        return returnValue;
    }

    @Override
    public <E extends Throwable> Void orWith(VT1<? super String, E> action) throws E {
        Objects.requireNonNull(action);
        if (!isMatch) {
            action.$(this.value);
        }
        return returnValue;
    }
}
