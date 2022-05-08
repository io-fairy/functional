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
 * String Token. <br>
 *
 * @since 0.0.1
 */
public class StringToken {
    StringType type;
    String value;
    String originValue;

    public StringToken() {
    }

    public StringToken(StringType type, String value, String originValue) {
        this.type = type;
        this.value = value;
        this.originValue = originValue;
    }

    public StringType getType() {
        return type;
    }

    public void setType(StringType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getOriginValue() {
        return originValue;
    }

    public void setOriginValue(String originValue) {
        this.originValue = originValue;
    }

    @Override
    public String toString() {
        return "StringToken{" +
                "type=" + type +
                ", value='" + value + '\'' +
                ", originValue='" + originValue + '\'' +
                '}';
    }
}
