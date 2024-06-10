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

import com.iofairy.tuple.Tuple;

/**
 * Call {@link Tuple#elementWithAlias} before {@link Tuple#alias}, will throw AliasNotSetException.<br>
 * 在调用 {@link Tuple#alias} 之前调用 {@link Tuple#elementWithAlias}，将抛出此异常
 *
 * @since 0.0.1
 */
public class AliasNotSetException extends BaseRuntimeException {
    private static final long serialVersionUID = 656057270L;

    /**
     * Constructs an {@code AliasNotSetException} with {@code null}
     * as its error detail message.
     */
    public AliasNotSetException() {
        super();
    }

    /**
     * Constructs a {@code AliasNotSetException} <br>
     * <b>Examples:</b>
     * <blockquote><pre>{@code
     * try {
     *     throw new AliasNotSetException("orderId: ${0}, orderName: ${?}, `orderStatus` must be non-empty! ", 10000, "'order_test'");
     * } catch (Exception e) {
     *     assertEquals("orderId: 10000, orderName: 'order_test', `orderStatus` must be non-empty! ", e.getMessage());
     * }
     *
     * try {
     *     throw new AliasNotSetException("userId: ${_}, `phone` must be non-empty! ", 10000);
     * } catch (Exception e) {
     *     assertEquals("userId: 10000, `phone` must be non-empty! ", e.getMessage());
     * }
     *
     * try {
     *     throw new AliasNotSetException("userId: ${…}, `phone` must be non-empty! ", 10000);
     * } catch (Exception e) {
     *     assertEquals("userId: 10000, `phone` must be non-empty! ", e.getMessage());
     * }
     *
     * try {
     *     throw new AliasNotSetException("`orderStatus` must be non-empty! ");
     * } catch (Exception e) {
     *     assertEquals("`orderStatus` must be non-empty! ", e.getMessage());
     * }
     * }</pre></blockquote>
     *
     * @param msgTemplate message template. It is recommended to use any one of <b>{@code ${0}}</b> or <b>{@code ${?}}</b> or <b>{@code ${…}}</b>
     *                    or <b>{@code ${_}}</b> or <b>meaningful names</b> as placeholders
     * @param args        arguments use to fill placeholder
     */
    public AliasNotSetException(String msgTemplate, Object... args) {
        super(msgTemplate, args);
    }

    public AliasNotSetException(Throwable cause, String msgTemplate, Object... args) {
        super(cause, msgTemplate, args);
    }

    public AliasNotSetException(Throwable cause) {
        super(cause);
    }

    @Override
    public AliasNotSetException setCode(String code) {
        this.code = code;
        return this;
    }
}
