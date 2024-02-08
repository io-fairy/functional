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
package com.iofairy.range;

/**
 * Classification of intervals<br>
 * 区间的分类
 *
 * @since 0.5.0
 */
public enum IntervalType {
    OPEN("(a, b)", '(', ')'),
    CLOSED("[a, b]", '[', ']'),
    OPEN_CLOSED("(a, b]", '(', ']'),
    CLOSED_OPEN("[a, b)", '[', ')');

    public final String value;
    public final char leftSign;
    public final char rightSign;

    IntervalType(final String value, final char leftSign, final char rightSign) {
        this.value = value;
        this.leftSign = leftSign;
        this.rightSign = rightSign;
    }

    public boolean isLeftClose() {
        return this == CLOSED || this == CLOSED_OPEN;
    }

    public boolean isRightClose() {
        return this == CLOSED || this == OPEN_CLOSED;
    }

    public boolean isHalfOpen() {
        return this == CLOSED_OPEN || this == OPEN_CLOSED;
    }

}
