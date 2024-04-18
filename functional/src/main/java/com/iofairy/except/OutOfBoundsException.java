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

import com.iofairy.top.G;
import com.iofairy.top.S;

/**
 * When the value is out of bounds, will throw OutOfBoundsException. <br>
 * 当某个值超出限定范围时，将抛出此异常
 *
 * @since 0.3.9
 */
public class OutOfBoundsException extends RuntimeException {
    private static final long serialVersionUID = 656057285L;

    private static final String MESSAGE = G.IS_ZH_LANG ? "数值超出所允许的范围，当前值为：" : "The value out of range, the current value is: ";
    private static final String PERIOD = G.IS_ZH_LANG ? "。" : ". ";

    /**
     * Constructs an {@code OutOfBoundsException} with {@code null}
     * as its error detail message.
     */
    public OutOfBoundsException() {
        super();
    }

    /**
     * Constructs an {@code OutOfBoundsException} with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method)
     */
    public OutOfBoundsException(String message) {
        super(message);
    }

    /**
     * Constructs an {@code OutOfBoundsException} with the specified detail message
     * and cause.
     *
     * @param message The detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method)
     * @param cause   The cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A null value is permitted,
     *                and indicates that the cause is nonexistent or unknown.)
     */
    public OutOfBoundsException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs an {@code OutOfBoundsException} with the specified cause and a
     * detail message of {@code (cause==null ? null : cause.toString())}
     * (which typically contains the class and detail message of {@code cause}).
     *
     * @param cause The cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).  (A null value is permitted,
     *              and indicates that the cause is nonexistent or unknown.)
     */
    public OutOfBoundsException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new {@code OutOfBoundsException} class with an
     * argument indicating the illegal value.
     *
     * <p>The value is included in this exception's detail message.  The
     * exact presentation format of the detail message is unspecified.
     *
     * @param value the illegal value.
     */
    public OutOfBoundsException(Number value) {
        super(MESSAGE + "[" + G.toString(value, 9) + "]" + PERIOD);
    }

    /**
     * Constructs a new {@code OutOfBoundsException} class with an
     * argument indicating the illegal value.
     *
     * <p>The value is included in this exception's detail message.  The
     * exact presentation format of the detail message is unspecified.
     *
     * @param value   the illegal value.
     * @param message The detail message
     */
    public OutOfBoundsException(Number value, String message) {
        super(MESSAGE + "[" + G.toString(value, 9) + "]" + PERIOD + (S.isBlank(message) ? "" : message));
    }

}
