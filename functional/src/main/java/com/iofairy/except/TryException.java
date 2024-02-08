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
import com.iofairy.tcf.Try;

/**
 * When using {@link Try}, {@code TryException} may be thrown. <br>
 * 使用 {@link Try} 中的方法时，可能会抛出此异常
 *
 * @since 0.5.0
 */
public class TryException extends RuntimeException {

    private static final long serialVersionUID = 999315723685987666L;

    /**
     * error code
     */
    protected String code;

    /**
     * Constructs a {@code TryException} <br>
     * <b>Examples:</b>
     * <blockquote><pre>{@code
     * try {
     *     throw new TryException("orderId: ${0}, orderName: ${0}, `orderStatus` must be non-empty! ", 10000, "'order_test'");
     * } catch (Exception e) {
     *     assertEquals("orderId: 10000, orderName: 'order_test', `orderStatus` must be non-empty! ", e.getMessage());
     * }
     *
     * try {
     *     throw new TryException("userId: ${_}, `phone` must be non-empty! ", 10000);
     * } catch (Exception e) {
     *     assertEquals("userId: 10000, `phone` must be non-empty! ", e.getMessage());
     * }
     *
     * try {
     *     throw new TryException("userId: ${…}, `phone` must be non-empty! ", 10000);
     * } catch (Exception e) {
     *     assertEquals("userId: 10000, `phone` must be non-empty! ", e.getMessage());
     * }
     *
     * try {
     *     throw new TryException("`orderStatus` must be non-empty! ");
     * } catch (Exception e) {
     *     assertEquals("`orderStatus` must be non-empty! ", e.getMessage());
     * }
     * }</pre></blockquote>
     *
     * @param msgTemplate message template, use <b>{@code ${_}}</b> or <b>{@code ${0}}</b> or <b>{@code ${…}}</b> as a placeholder
     * @param args        arguments use to fill placeholder
     */
    public TryException(String msgTemplate, Object... args) {
        super(getMsg(msgTemplate, args));
    }

    public TryException(Throwable cause, String msgTemplate, Object... args) {
        super(getMsg(msgTemplate, args), cause);
    }

    public TryException(Throwable cause) {
        super(cause);
    }

    public String getCode() {
        return code;
    }

    public TryException setCode(String code) {
        this.code = code;
        return this;
    }

    private static String getMsg(String msgTemplate, Object... args) {
        if (msgTemplate == null) return null;
        return SI.$(msgTemplate, args);
    }

}