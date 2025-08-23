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
package com.iofairy.time;

/**
 * 时间串分隔符的类型
 *
 * @since 0.6.0
 */
public enum DateTimeSeparator {
    /**
     * 带 - 的日期串<br>
     * 如：2000-01-01
     */
    DASH,
    /**
     * 带 - 的时间串<br>
     * 如：2000-01-01 10:00:00
     */
    DASH_COLON,
    /**
     * 带 - 和毫秒时间串<br>
     * 如：2000-01-01 10:00:00.001
     */
    DASH_COLON_DOT,
    /**
     * 带 . 的时间串<br>
     * 如：2000.01.01
     */
    DOT,
    /**
     * 带 . 的时间串<br>
     * 如：2000.01.01 10:00:00
     */
    DOT_COLON,
    /**
     * 带 . 和毫秒的时间串<br>
     * 如：2000.01.01 10:00:00.001
     */
    DOT_COLON_DOT,
    /**
     * 带 / 的时间串<br>
     * 如：2000/01/01
     */
    SLASH,
    /**
     * 带 / 的时间串<br>
     * 如：2000/01/01 10:00:00
     */
    SLASH_COLON,
    /**
     * 带 / 和毫秒的时间串<br>
     * 如：2000/01/01 10:00:00.001
     */
    SLASH_COLON_DOT,
    /**
     * 带 空格 的时间串（说明同时包含日期与时间）<br>
     * 如：2000/01/01 10:00:00
     */
    SPACE,
    /**
     * 带冒号的时间串<br>
     * 如：10:00:00
     */
    COLON,
    /**
     * 带冒号和毫秒的时间串<br>
     * 如：10:00:00.001
     */
    COLON_DOT,
    /**
     * 带 中文 的日期串<br>
     * 如：2000年01月01日
     */
    ZH_DATE,
    /**
     * 带 中文 时间串<br>
     * 如：<br>
     * <code>10时00分00秒</code> 或 <code>10点00分00秒</code><br>
     */
    ZH_TIME,
    /**
     * 带毫秒的 中文 时间串<br>
     * 如：<br>
     * <code>10时00分00秒</code> 或 <code>10点00分00秒006毫秒</code><br>
     */
    ZH_TIME_MS,
    /**
     * 带 中文 的日期时间串<br>
     * 如：<code>2000年01月01日10时00分00秒</code>
     */
    ZH_ZH,
    /**
     * 带毫秒的 中文 日期时间串<br>
     * 如：<code>2000年01月01日10时00分00秒006毫秒</code>
     */
    ZH_ZH_MS,
    /**
     * 什么都不带的时间串<br>
     * 1、4位数（年）：<code>2000</code><br>
     * 2、6位数（年月）：<code>200001</code><br>
     * 3、8位数（年月日）：<code>20000101</code><br>
     * 4、10位数（年月日时）：<code>2000010110</code><br>
     * 5、12位数（年月日时分）：<code>200001011000</code><br>
     * 6、14位数（年月日时分秒）：<code>20000101100000</code><br>
     * 8、17位数（年月日时分秒毫秒）：<code>20000101100000100</code><br>
     */
    NO_SEPARATOR
}
