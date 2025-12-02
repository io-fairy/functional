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
package com.iofairy.base;

/**
 * Top
 *
 * @since 0.3.6
 */
public final class Top {
    private Top() {
    }

    /**
     * Nul <br>
     * NOTE: The error occurs when attempting to create a file named {@code Nul.java} on <b>Windows 10</b>, which reports: "The specified device name is invalid. ".
     * While the same operation succeeds on <b>Windows 11</b>. <br>
     * 注意：在<b>Windows 10</b>上尝试创建名为 {@code Nul.java} 的文件时，会报错："指定的设备名无效。"。 而在<b>Windows 11</b>上操作成功。
     *
     * @since 0.3.6
     */
    public final static class Nul {
        public static final Nul NUL = new Nul();
        public final char value = '\u0000';

        private Nul() {
        }
    }

}
