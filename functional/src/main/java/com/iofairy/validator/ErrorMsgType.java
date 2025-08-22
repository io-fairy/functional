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
package com.iofairy.validator;

/**
 * 错误的消息类型
 *
 * @since 0.6.0
 */
public enum ErrorMsgType {
    NULL("null", "non-null"),
    NOT_NULL("non-null", "null"),
    EMPTY("empty", "non-empty"),
    NOT_EMPTY("non-empty", "empty"),
    BLANK("blank", "non-blank"),
    NOT_BLANK("non-blank", "blank"),
    HAS_BLANK("blank", "non-blank"),
    ALL_BLANK("blank", "non-blank"),
    HAS_EMPTY("empty", "non-empty"),
    ALL_EMPTY("empty", "non-empty"),
    HAS_NULL("null", "non-null"),
    ALL_NULL("null", "non-null"),
    ;

    /**
     * 报错原因
     */
    public final String errorReason;
    /**
     * 必须是什么才不报错
     */
    public final String mustBe;

    ErrorMsgType(String errorReason, String mustBe) {
        this.errorReason = errorReason;
        this.mustBe = mustBe;
    }

}