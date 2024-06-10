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
public class OutOfBoundsException extends BaseRuntimeException {
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
     * Constructs a {@code OutOfBoundsException} <br>
     * <b>Examples:</b>
     * <blockquote><pre>{@code
     * try {
     *     throw new OutOfBoundsException("orderId: ${0}, orderName: ${?}, `orderStatus` must be non-empty! ", 10000, "'order_test'");
     * } catch (Exception e) {
     *     assertEquals("orderId: 10000, orderName: 'order_test', `orderStatus` must be non-empty! ", e.getMessage());
     * }
     *
     * try {
     *     throw new OutOfBoundsException("userId: ${_}, `phone` must be non-empty! ", 10000);
     * } catch (Exception e) {
     *     assertEquals("userId: 10000, `phone` must be non-empty! ", e.getMessage());
     * }
     *
     * try {
     *     throw new OutOfBoundsException("userId: ${…}, `phone` must be non-empty! ", 10000);
     * } catch (Exception e) {
     *     assertEquals("userId: 10000, `phone` must be non-empty! ", e.getMessage());
     * }
     *
     * try {
     *     throw new OutOfBoundsException("`orderStatus` must be non-empty! ");
     * } catch (Exception e) {
     *     assertEquals("`orderStatus` must be non-empty! ", e.getMessage());
     * }
     * }</pre></blockquote>
     *
     * @param msgTemplate message template. It is recommended to use any one of <b>{@code ${0}}</b> or <b>{@code ${?}}</b> or <b>{@code ${…}}</b>
     *                    or <b>{@code ${_}}</b> or <b>meaningful names</b> as placeholders
     * @param args        arguments use to fill placeholder
     */
    public OutOfBoundsException(String msgTemplate, Object... args) {
        super(msgTemplate, args);
    }

    public OutOfBoundsException(Throwable cause, String msgTemplate, Object... args) {
        super(cause, msgTemplate, args);
    }

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

    @Override
    public OutOfBoundsException setCode(String code) {
        this.code = code;
        return this;
    }
}
