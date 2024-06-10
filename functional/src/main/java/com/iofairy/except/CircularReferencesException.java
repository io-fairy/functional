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
package com.iofairy.except;

/**
 * When the circular reference occurs, will throw {@code CircularReferencesException}. <br>
 * 当出现循环引用时，将抛出此异常
 *
 * @since 0.3.12
 */
public class CircularReferencesException extends BaseRuntimeException {
    private static final long serialVersionUID = 99936560555729000L;

    public CircularReferencesException() {
        super();
    }

    /**
     * Constructs a {@code CircularReferencesException} <br>
     *
     * @param msgTemplate message template. It is recommended to use any one of <b>{@code ${0}}</b> or <b>{@code ${?}}</b> or <b>{@code ${…}}</b>
     *                    or <b>{@code ${_}}</b> or <b>meaningful names</b> as placeholders
     * @param args        arguments use to fill placeholder
     * @since 0.5.4
     */
    public CircularReferencesException(String msgTemplate, Object... args) {
        super(msgTemplate, args);
    }

    /**
     * Constructs a {@code CircularReferencesException} <br>
     *
     * @param cause       The cause (which is saved for later retrieval by the {@link #getCause()} method).
     *                    (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     * @param msgTemplate message template. It is recommended to use any one of <b>{@code ${0}}</b> or <b>{@code ${?}}</b> or <b>{@code ${…}}</b>
     *                    or <b>{@code ${_}}</b> or <b>meaningful names</b> as placeholders
     * @param args        arguments use to fill placeholder
     * @since 0.5.4
     */
    public CircularReferencesException(Throwable cause, String msgTemplate, Object... args) {
        super(cause, msgTemplate, args);
    }

    public CircularReferencesException(Throwable cause) {
        super(cause);
    }

    @Override
    public CircularReferencesException setCode(String code) {
        this.code = code;
        return this;
    }

}
