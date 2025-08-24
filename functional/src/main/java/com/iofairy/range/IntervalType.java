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

import com.iofairy.top.S;

import java.util.Arrays;
import java.util.List;

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

    /**
     * Convert the left and right signs of the interval to an interval type
     *
     * @param leftSign  left sign of interval
     * @param rightSign right sign of interval
     * @return interval type
     * @since 0.6.0
     */
    public static IntervalType of(final char leftSign, final char rightSign) {
        switch ("" + leftSign + rightSign) {
            case "()":
                return OPEN;
            case "[]":
                return CLOSED;
            case "(]":
                return OPEN_CLOSED;
            case "[)":
                return CLOSED_OPEN;
            default:
                return null;
        }
    }

    /**
     * Convert the name of the interval to an interval type
     *
     * @param name name of interval
     * @return interval type
     * @since 0.6.0
     */
    public static IntervalType of(final String name) {
        if (S.isBlank(name)) return null;
        switch (name) {
            case "OPEN":
            case "()":
                return OPEN;
            case "CLOSED":
            case "[]":
                return CLOSED;
            case "OPEN_CLOSED":
            case "(]":
                return OPEN_CLOSED;
            case "CLOSED_OPEN":
            case "[)":
                return CLOSED_OPEN;
            default:
                return null;
        }
    }

    public static List<String> validNames() {
        return Arrays.asList("OPEN", "CLOSED", "OPEN_CLOSED", "CLOSED_OPEN", "()", "[]", "(]", "[)");
    }

}
