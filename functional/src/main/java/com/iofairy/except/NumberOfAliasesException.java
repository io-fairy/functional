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
 * @since 0.0.1
 */
public class NumberOfAliasesException extends RuntimeException {
    private static final long serialVersionUID = 656057275L;


    /**
     * Constructs an {@code NumberOfAliasesException} with {@code null}
     * as its error detail message.
     */
    public NumberOfAliasesException() {
        super();
    }

    /**
     * Constructs an {@code NumberOfAliasesException} with the specified detail message.
     *
     * @param message
     *        The detail message (which is saved for later retrieval
     *        by the {@link #getMessage()} method)
     */
    public NumberOfAliasesException(String message) {
        super(message);
    }

    /**
     * Constructs an {@code NumberOfAliasesException} with the specified detail message
     * and cause.
     *
     * @param message
     *        The detail message (which is saved for later retrieval
     *        by the {@link #getMessage()} method)
     *
     * @param cause
     *        The cause (which is saved for later retrieval by the
     *        {@link #getCause()} method).  (A null value is permitted,
     *        and indicates that the cause is nonexistent or unknown.)
     */
    public NumberOfAliasesException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs an {@code NumberOfAliasesException} with the specified cause and a
     * detail message of {@code (cause==null ? null : cause.toString())}
     * (which typically contains the class and detail message of {@code cause}).
     *
     * @param cause
     *        The cause (which is saved for later retrieval by the
     *        {@link #getCause()} method).  (A null value is permitted,
     *        and indicates that the cause is nonexistent or unknown.)
     *
     */
    public NumberOfAliasesException(Throwable cause) {
        super(cause);
    }

}
