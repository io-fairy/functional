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
package com.iofairy.pattern;

import java.util.Arrays;
import java.util.List;

/**
 * PatternIn for matching multi-values in one time.
 * @since 0.0.1
 */
public class PatternIn<V> {

    private List<V> vs;

    /**
     * Match multi-values in one time. <br>
     * 判断 待匹配的值是否在集合中，也可用于一次匹配多个值：<br>
     * <b>Examples:</b>
     * <pre>
     * .when(in(0, 1, 2), v -&gt; {System.out.println("match!");})
     *
     * // it is equivalent to the code below
     * int i = 1;
     * switch (i) {
     *     case 0:
     *     case 1:
     *     case 2:
     *         System.out.println("match!");
     *         break;
     *     default:
     *         ...
     * }
     * </pre>
     * @param values multi-values
     * @param <T> values type
     * @return PatternIn
     * @since 0.0.1
     */
    public static <T> PatternIn<T> in(T... values) {
        PatternIn<T> patternIn = new PatternIn<>();
        if (values == null) {
            patternIn.vs = Arrays.asList((T)null);
            return patternIn;
        }
        if (values.length == 0) throw new RuntimeException("The params's length must be greater than 0. 参数个数必须大于0");
        patternIn.vs = Arrays.asList(values);
        return patternIn;
    }

    public List<V> getVs() {
        return vs;
    }
}
