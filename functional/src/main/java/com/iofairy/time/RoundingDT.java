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
 * Rounding date time.<br>
 * 对日期取整
 *
 * @since 0.6.0
 */
public enum RoundingDT {
    /**
     * 对日期向上取整
     */
    CEILING,
    /**
     * 对日期向下取整
     */
    FLOOR,
    /**
     * 对日期四舍五入（从最小日期开始）
     */
    HALF_UP

}
