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
package com.iofairy.pattern.matcher;

/**
 * Simple Matcher
 * @param <V> 待匹配的值的类型 match(value) 中，value的类型
 * @param <P> 匹配的模式的类型 .when(pattern, action) 中，pattern的类型
 * @param <L> lambda表达式参数类型
 * @param <R> 返回值类型
 * @since 0.0.1
 */
public abstract class SimpleMatcher<V, P, L, R> implements Matcher {

}
