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
     * NOTE: Can't create {@code Nul.java} file on Windows systems.
     *
     * @since 0.3.6
     */
    public static class Nul {
        public static final Nul NUL = new Nul();
        public final char value = '\u0000';

        private Nul() {
        }
    }

}
