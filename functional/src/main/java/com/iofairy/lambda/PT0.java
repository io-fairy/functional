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
package com.iofairy.lambda;

/**
 * Represents a <b>predicate</b> (boolean-valued function) of 0 argument, and will throw exception<br>
 * 表示一个接收 0个参数 并返回boolean值且抛出异常的函数
 *
 * @param <E> Throwable or subclass of Throwable.　Throwable类及其子类
 * @since 0.6.0
 */
@FunctionalInterface
public interface PT0<E extends Throwable> extends LambdaRT<Boolean, E>, Lambda0 {
    boolean $() throws E;
}
