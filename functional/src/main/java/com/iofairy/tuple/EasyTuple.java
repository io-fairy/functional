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
package com.iofairy.tuple;

import java.util.*;

/**
 * EasyTuple Interface
 * 
 * @since 0.0.5
 */
public interface EasyTuple extends Tuple {
    long serialVersionUID = 10065917085L;

    /**
     * Transform this EasyTuple to List. <br>
     * 将 EasyTuple 转成 List
     * @param <T> type of elements.　元素的类型
     * @return a list
     */
    default <T> List<T> toList(){
        List<T> ts = new ArrayList<>();
        if (arity() > 0) {
            for (int i = 0; i < arity(); i++) {
                ts.add(element(i));
            }
        }
        return Collections.unmodifiableList(ts);
    }

    /**
     * Create empty tuple<br>
     * 创建一个空元组
     *
     * @return the instance of Tuple0.　返回Tuple0的实例
     */
    static EasyTuple0 empty() {
        return EasyTuple0.instance();
    }

    /**
     * Create empty EasyTuple<br>
     * 创建一个空元组
     *
     * @return the instance of EasyTuple0.　返回EasyTuple0的实例
     */
    static EasyTuple0 of() {
        return EasyTuple0.instance();
    }

    /**
     * Create a EasyTuple of 1 element<br>
     * 创建1个元素的元组
     *
     * @param _1 the 1st element.　第1个元素
     * @param <T> type of element.　元素的类型
     * @return the instance of EasyTuple1.　返回EasyTuple1的实例
     */
    static <T> EasyTuple1<T> of(T _1) {
        return new EasyTuple1<>(_1);
    }

    /**
     * Create a EasyTuple of 2 elements<br>
     * 创建2个元素的元组
     *
     * @param _1 the 1st element.　第1个元素
     * @param _2 the 2nd element.　第2个元素
     * @param <T> type of elements.　元素的类型
     * @return the instance of EasyTuple2.　返回EasyTuple2的实例
     */
    static <T> EasyTuple2<T> of(T _1, T _2) {
        return new EasyTuple2<>(_1, _2);
    }

    /**
     * Create a EasyTuple of 3 elements<br>
     * 创建3个元素的元组
     *
     * @param _1 the 1st element.　第1个元素
     * @param _2 the 2nd element.　第2个元素
     * @param _3 the 3rd element.　第3个元素
     * @param <T> type of elements.　元素的类型
     * @return the instance of EasyTuple3.　返回EasyTuple3的实例
     */
    static <T> EasyTuple3<T> of(T _1, T _2, T _3) {
        return new EasyTuple3<>(_1, _2, _3);
    }

    /**
     * Create a EasyTuple of 4 elements<br>
     * 创建4个元素的元组
     *
     * @param _1 the 1st element.　第1个元素
     * @param _2 the 2nd element.　第2个元素
     * @param _3 the 3rd element.　第3个元素
     * @param _4 the 4th element.　第4个元素
     * @param <T> type of elements.　元素的类型
     * @return the instance of EasyTuple4.　返回EasyTuple4的实例
     */
    static <T> EasyTuple4<T> of(T _1, T _2, T _3, T _4) {
        return new EasyTuple4<>(_1, _2, _3, _4);
    }

    /**
     * Create a EasyTuple of 5 elements<br>
     * 创建5个元素的元组
     *
     * @param _1 the 1st element.　第1个元素
     * @param _2 the 2nd element.　第2个元素
     * @param _3 the 3rd element.　第3个元素
     * @param _4 the 4th element.　第4个元素
     * @param _5 the 5th element.　第5个元素
     * @param <T> type of elements.　元素的类型
     * @return the instance of EasyTuple5.　返回EasyTuple5的实例
     */
    static <T> EasyTuple5<T> of(T _1, T _2, T _3, T _4, T _5) {
        return new EasyTuple5<>(_1, _2, _3, _4, _5);
    }

    /**
     * Create a EasyTuple of 6 elements<br>
     * 创建6个元素的元组
     *
     * @param _1 the 1st element.　第1个元素
     * @param _2 the 2nd element.　第2个元素
     * @param _3 the 3rd element.　第3个元素
     * @param _4 the 4th element.　第4个元素
     * @param _5 the 5th element.　第5个元素
     * @param _6 the 6th element.　第6个元素
     * @param <T> type of elements.　元素的类型
     * @return the instance of EasyTuple6.　返回EasyTuple6的实例
     */
    static <T> EasyTuple6<T> of(T _1, T _2, T _3, T _4, T _5, T _6) {
        return new EasyTuple6<>(_1, _2, _3, _4, _5, _6);
    }

    /**
     * Create a EasyTuple of 7 elements<br>
     * 创建7个元素的元组
     *
     * @param _1 the 1st element.　第1个元素
     * @param _2 the 2nd element.　第2个元素
     * @param _3 the 3rd element.　第3个元素
     * @param _4 the 4th element.　第4个元素
     * @param _5 the 5th element.　第5个元素
     * @param _6 the 6th element.　第6个元素
     * @param _7 the 7th element.　第7个元素
     * @param <T> type of elements.　元素的类型
     * @return the instance of EasyTuple7.　返回EasyTuple7的实例
     */
    static <T> EasyTuple7<T> of(T _1, T _2, T _3, T _4, T _5, T _6, T _7) {
        return new EasyTuple7<>(_1, _2, _3, _4, _5, _6, _7);
    }

    /**
     * Create a EasyTuple of 8 elements<br>
     * 创建8个元素的元组
     *
     * @param _1 the 1st element.　第1个元素
     * @param _2 the 2nd element.　第2个元素
     * @param _3 the 3rd element.　第3个元素
     * @param _4 the 4th element.　第4个元素
     * @param _5 the 5th element.　第5个元素
     * @param _6 the 6th element.　第6个元素
     * @param _7 the 7th element.　第7个元素
     * @param _8 the 8th element.　第8个元素
     * @param <T> type of elements.　元素的类型
     * @return the instance of EasyTuple8.　返回EasyTuple8的实例
     */
    static <T> EasyTuple8<T> of(T _1, T _2, T _3, T _4, T _5, T _6, T _7, T _8) {
        return new EasyTuple8<>(_1, _2, _3, _4, _5, _6, _7, _8);
    }

    /**
     * Create a EasyTuple of 9 elements<br>
     * 创建9个元素的元组
     *
     * @param _1 the 1st element.　第1个元素
     * @param _2 the 2nd element.　第2个元素
     * @param _3 the 3rd element.　第3个元素
     * @param _4 the 4th element.　第4个元素
     * @param _5 the 5th element.　第5个元素
     * @param _6 the 6th element.　第6个元素
     * @param _7 the 7th element.　第7个元素
     * @param _8 the 8th element.　第8个元素
     * @param _9 the 9th element.　第9个元素
     * @param <T> type of elements.　元素的类型
     * @return the instance of EasyTuple9.　返回EasyTuple9的实例
     */
    static <T> EasyTuple9<T> of(T _1, T _2, T _3, T _4, T _5, T _6, T _7, T _8, T _9) {
        return new EasyTuple9<>(_1, _2, _3, _4, _5, _6, _7, _8, _9);
    }

    /**
     * clone a tuple by shallow copy. <br>
     * 通过浅拷贝的方式克隆一个tuple
     * @param tuple origin tuple
     * @return new tuple
     */
    static EasyTuple clone(EasyTuple tuple) {
        return tuple == null ? tuple : (EasyTuple) tuple.copy();
    }
}
