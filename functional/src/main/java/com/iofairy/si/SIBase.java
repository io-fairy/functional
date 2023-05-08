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
package com.iofairy.si;

/**
 * String Interpolator basic constants. <br>
 * 字符串插值必要的常量
 *
 * @since 0.4.1
 */
public class SIBase {
    /**
     * ${} will parse to $ (String literal). <br>
     * 遇到 ${} 则解析成字符 $
     */
    public final static String $__ = "${}";
    public final static String $ = "$";
    public final static String DEFAULT_VALUE_DELIMITER = ": ";
    // DEFAULT_VALUE_DELIMITER (DVD) LENGTH
    public final static int DVD_LENGTH = DEFAULT_VALUE_DELIMITER.length();

    public final static String PREFIX = "${";
    public final static String SUFFIX = "}";

}
