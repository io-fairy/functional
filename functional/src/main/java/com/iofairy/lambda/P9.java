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
 * Represents a <b>predicate</b> (boolean-valued function) of 9 arguments.<br>
 * 表示一个接收 9个参数 并返回boolean值的函数
 *
 * @param <T1> type of the 1st param.　第1个参数类型
 * @param <T2> type of the 2nd param.　第2个参数类型
 * @param <T3> type of the 3rd param.　第3个参数类型
 * @param <T4> type of the 4th param.　第4个参数类型
 * @param <T5> type of the 5th param.　第5个参数类型
 * @param <T6> type of the 6th param.　第6个参数类型
 * @param <T7> type of the 7th param.　第7个参数类型
 * @param <T8> type of the 8th param.　第8个参数类型
 * @param <T9> type of the 9th param.　第9个参数类型
 * @since 0.4.2
 */
@FunctionalInterface
public interface P9<T1, T2, T3, T4, T5, T6, T7, T8, T9> extends LambdaRN<Boolean>, Lambda9<T1, T2, T3, T4, T5, T6, T7, T8, T9> {
    boolean $(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9);
}
