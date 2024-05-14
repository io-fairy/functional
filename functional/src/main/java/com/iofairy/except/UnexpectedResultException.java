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

import com.iofairy.si.SI;

/**
 * When results are not expected, will throw {@code UnexpectedResultException}. <br>
 * 当出现预期外的结果时，将抛出此异常
 *
 * @since 0.5.5
 */
public class UnexpectedResultException extends RuntimeException {

    private static final long serialVersionUID = 9999956893658666L;

    /**
     * error code
     */
    protected String code;

    /**
     * Constructs a {@code UnexpectedResultException} <br>
     * <b>Examples:</b>
     * <blockquote><pre>{@code
     * try {
     *     throw new UnexpectedResultException("orderId: ${0}, orderName: ${?}, `orderStatus` must be non-empty! ", 10000, "'order_test'");
     * } catch (Exception e) {
     *     assertEquals("orderId: 10000, orderName: 'order_test', `orderStatus` must be non-empty! ", e.getMessage());
     * }
     *
     * try {
     *     throw new UnexpectedResultException("userId: ${_}, `phone` must be non-empty! ", 10000);
     * } catch (Exception e) {
     *     assertEquals("userId: 10000, `phone` must be non-empty! ", e.getMessage());
     * }
     *
     * try {
     *     throw new UnexpectedResultException("userId: ${…}, `phone` must be non-empty! ", 10000);
     * } catch (Exception e) {
     *     assertEquals("userId: 10000, `phone` must be non-empty! ", e.getMessage());
     * }
     *
     * try {
     *     throw new UnexpectedResultException("`orderStatus` must be non-empty! ");
     * } catch (Exception e) {
     *     assertEquals("`orderStatus` must be non-empty! ", e.getMessage());
     * }
     * }</pre></blockquote>
     *
     * @param msgTemplate message template. It is recommended to use any one of <b>{@code ${0}}</b> or <b>{@code ${?}}</b> or <b>{@code ${…}}</b>
     *                    or <b>{@code ${_}}</b> or <b>meaningful names</b> as placeholders
     * @param args        arguments use to fill placeholder
     */
    public UnexpectedResultException(String msgTemplate, Object... args) {
        super(getMsg(msgTemplate, args));
    }

    public UnexpectedResultException(Throwable cause, String msgTemplate, Object... args) {
        super(getMsg(msgTemplate, args), cause);
    }

    public String getCode() {
        return code;
    }

    public UnexpectedResultException setCode(String code) {
        this.code = code;
        return this;
    }

    private static String getMsg(String msgTemplate, Object... args) {
        if (msgTemplate == null) return null;
        return SI.$(msgTemplate, args);
    }

}