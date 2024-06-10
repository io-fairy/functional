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
 * When setting tuple's aliases and the number of aliases is not match for tuple's elements {@link Tuple#arity} , will throw NumberOfAliasesException<br>
 * 为元组（Tuple）的元素设置别名时，如果设置的别名的数量与元组的元素数量不匹配，将会抛出此异常
 *
 * @since 0.0.1
 */
public class NumberOfAliasesException extends BaseRuntimeException {
    private static final long serialVersionUID = 656057275L;


    /**
     * Constructs an {@code NumberOfAliasesException} with {@code null}
     * as its error detail message.
     */
    public NumberOfAliasesException() {
        super();
    }

    /**
     * Constructs a {@code NumberOfAliasesException} <br>
     * <b>Examples:</b>
     * <blockquote><pre>{@code
     * try {
     *     throw new NumberOfAliasesException("orderId: ${0}, orderName: ${?}, `orderStatus` must be non-empty! ", 10000, "'order_test'");
     * } catch (Exception e) {
     *     assertEquals("orderId: 10000, orderName: 'order_test', `orderStatus` must be non-empty! ", e.getMessage());
     * }
     *
     * try {
     *     throw new NumberOfAliasesException("userId: ${_}, `phone` must be non-empty! ", 10000);
     * } catch (Exception e) {
     *     assertEquals("userId: 10000, `phone` must be non-empty! ", e.getMessage());
     * }
     *
     * try {
     *     throw new NumberOfAliasesException("userId: ${…}, `phone` must be non-empty! ", 10000);
     * } catch (Exception e) {
     *     assertEquals("userId: 10000, `phone` must be non-empty! ", e.getMessage());
     * }
     *
     * try {
     *     throw new NumberOfAliasesException("`orderStatus` must be non-empty! ");
     * } catch (Exception e) {
     *     assertEquals("`orderStatus` must be non-empty! ", e.getMessage());
     * }
     * }</pre></blockquote>
     *
     * @param msgTemplate message template. It is recommended to use any one of <b>{@code ${0}}</b> or <b>{@code ${?}}</b> or <b>{@code ${…}}</b>
     *                    or <b>{@code ${_}}</b> or <b>meaningful names</b> as placeholders
     * @param args        arguments use to fill placeholder
     */
    public NumberOfAliasesException(String msgTemplate, Object... args) {
        super(msgTemplate, args);
    }

    public NumberOfAliasesException(Throwable cause, String msgTemplate, Object... args) {
        super(cause, msgTemplate, args);
    }

    public NumberOfAliasesException(Throwable cause) {
        super(cause);
    }

    @Override
    public NumberOfAliasesException setCode(String code) {
        this.code = code;
        return this;
    }
}
