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
 * When type of parameter are not expected, will throw UnexpectedTypeException. <br>
 * 当传入的参数类型不符合要求时，会抛出此异常
 *
 * @since 0.4.19
 */
public class UnexpectedTypeException extends BaseRuntimeException {
    private static final long serialVersionUID = 996665356057205L;

    /**
     * Constructs an {@code UnexpectedTypeException} with {@code null}
     * as its error detail message.
     */
    public UnexpectedTypeException() {
        super();
    }

    /**
     * Constructs a {@code UnexpectedTypeException} <br>
     * <b>Examples:</b>
     * <blockquote><pre>{@code
     * try {
     *     throw new UnexpectedTypeException("orderId: ${0}, orderName: ${?}, `orderStatus` must be non-empty! ", 10000, "'order_test'");
     * } catch (Exception e) {
     *     assertEquals("orderId: 10000, orderName: 'order_test', `orderStatus` must be non-empty! ", e.getMessage());
     * }
     *
     * try {
     *     throw new UnexpectedTypeException("userId: ${_}, `phone` must be non-empty! ", 10000);
     * } catch (Exception e) {
     *     assertEquals("userId: 10000, `phone` must be non-empty! ", e.getMessage());
     * }
     *
     * try {
     *     throw new UnexpectedTypeException("userId: ${…}, `phone` must be non-empty! ", 10000);
     * } catch (Exception e) {
     *     assertEquals("userId: 10000, `phone` must be non-empty! ", e.getMessage());
     * }
     *
     * try {
     *     throw new UnexpectedTypeException("`orderStatus` must be non-empty! ");
     * } catch (Exception e) {
     *     assertEquals("`orderStatus` must be non-empty! ", e.getMessage());
     * }
     * }</pre></blockquote>
     *
     * @param msgTemplate message template. It is recommended to use any one of <b>{@code ${0}}</b> or <b>{@code ${?}}</b> or <b>{@code ${…}}</b>
     *                    or <b>{@code ${_}}</b> or <b>meaningful names</b> as placeholders
     * @param args        arguments use to fill placeholder
     */
    public UnexpectedTypeException(String msgTemplate, Object... args) {
        super(msgTemplate, args);
    }

    public UnexpectedTypeException(Throwable cause, String msgTemplate, Object... args) {
        super(cause, msgTemplate, args);
    }

    public UnexpectedTypeException(Throwable cause) {
        super(cause);
    }

    @Override
    public UnexpectedTypeException setCode(String code) {
        this.code = code;
        return this;
    }
}
