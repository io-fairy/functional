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
package com.iofairy.top.base;

/**
 * Padding strategy<br>
 * 填充策略
 *
 * @since 0.5.12
 */
public enum PaddingStrategy {
    /**
     * Left-side padding only <br>
     * 仅向左侧填充字符
     */
    LEFT,
    /**
     * Right-side padding only <br>
     * 仅向右侧填充字符
     */
    RIGHT,
    /**
     * Pads a string on both sides, make string as centered as possible.
     * <b>Left side prioritized padding (left side gets extra character for odd padding)</b> <br>
     * 在字符串两侧添加字符，字符串尽可能居中。<b>左侧优先填充（奇数填充时左侧多一个字符）</b>
     */
    LEFT_MORE,
    /**
     * Pads a string on both sides, make string as centered as possible.
     * <b>Right side prioritized padding (right side gets extra character for odd padding)</b> <br>
     * 在字符串两侧添加字符，字符串尽可能居中。<b>右侧优先填充（奇数填充时右侧多一个字符）</b>
     */
    RIGHT_MORE,

}