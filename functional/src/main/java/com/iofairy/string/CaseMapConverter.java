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
package com.iofairy.string;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Case Converter use Map
 *
 * @since 0.3.0
 */
public class CaseMapConverter implements StringConverter {

    private Map<String, String> convertMap;
    private final boolean keyIgnoreCase;
    /**
     * If the key does not exist: <br>
     * {@code setDefaultValueAsNull} is {@code true}, <b>null</b> is returned;<br>
     * {@code setDefaultValueAsNull} is {@code false}, the <b>input string itself</b> is returned
     */
    private boolean setDefaultValueAsNull;

    public CaseMapConverter(Map<String, String> convertMap, boolean keyIgnoreCase, boolean setDefaultValueAsNull) {
        this.keyIgnoreCase = keyIgnoreCase;
        this.setDefaultValueAsNull = setDefaultValueAsNull;
        this.convertMap = new HashMap<>();
        setConvertMap(convertMap);
    }

    public static CaseMapConverter of(Map<String, String> convertMap, boolean keyIgnoreCase) {
        return new CaseMapConverter(convertMap, keyIgnoreCase, false);
    }

    public static CaseMapConverter of(Map<String, String> convertMap, boolean keyIgnoreCase, boolean setDefaultValueAsNull) {
        return new CaseMapConverter(convertMap, keyIgnoreCase, setDefaultValueAsNull);
    }

    public CaseMapConverter put(String key, String value) {
        if (keyIgnoreCase) {
            key = Ascii.toLower(key);
        }
        this.convertMap.put(key, value);
        return this;
    }

    public CaseMapConverter remove(String key) {
        if (keyIgnoreCase) {
            key = Ascii.toLower(key);
        }
        this.convertMap.remove(key);
        return this;
    }

    public CaseMapConverter clear() {
        this.convertMap.clear();
        return this;
    }

    public Map<String, String> getConvertMap() {
        return Collections.unmodifiableMap(convertMap);
    }

    public CaseMapConverter setConvertMap(Map<String, String> convertMap) {
        if (convertMap != null) {
            this.convertMap.clear();
            if (keyIgnoreCase) {
                for (Map.Entry<String, String> ssEntry : convertMap.entrySet()) {
                    this.convertMap.put(Ascii.toLower(ssEntry.getKey()), ssEntry.getValue());
                }
            } else {
                this.convertMap.putAll(convertMap);
            }
        }
        return this;
    }

    public boolean isKeyIgnoreCase() {
        return keyIgnoreCase;
    }

    public boolean isSetDefaultValueAsNull() {
        return setDefaultValueAsNull;
    }

    public CaseMapConverter setSetDefaultValueAsNull(boolean setDefaultValueAsNull) {
        this.setDefaultValueAsNull = setDefaultValueAsNull;
        return this;
    }

    @Override
    public String convert(String inputStr) {
        if (keyIgnoreCase) {
            inputStr = Ascii.toLower(inputStr);
        }
        return convertMap.getOrDefault(inputStr, setDefaultValueAsNull ? null : inputStr);
    }

}
