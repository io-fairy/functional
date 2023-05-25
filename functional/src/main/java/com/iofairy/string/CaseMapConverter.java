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
    private boolean keyIgnoreCase;

    public CaseMapConverter(Map<String, String> convertMap, boolean keyIgnoreCase) {
        setConvertMap(convertMap, keyIgnoreCase);
    }

    public static CaseMapConverter of(Map<String, String> convertMap, boolean keyIgnoreCase) {
        return new CaseMapConverter(convertMap, keyIgnoreCase);
    }

    public Map<String, String> getConvertMap() {
        return convertMap;
    }

    public CaseMapConverter setConvertMap(Map<String, String> convertMap) {
        setConvertMap(convertMap, keyIgnoreCase);
        return this;
    }

    public boolean isKeyIgnoreCase() {
        return keyIgnoreCase;
    }

    public CaseMapConverter setKeyIgnoreCase(boolean keyIgnoreCase) {
        this.keyIgnoreCase = keyIgnoreCase;
        return this;
    }

    private void setConvertMap(Map<String, String> convertMap, boolean keyIgnoreCase) {
        Map<String, String> tmpConvertMap = new HashMap<>();
        if (keyIgnoreCase) {
            for (Map.Entry<String, String> ssEntry : convertMap.entrySet()) {
                tmpConvertMap.put(Ascii.toUpper(ssEntry.getKey()), ssEntry.getValue());
            }
        }
        this.keyIgnoreCase = keyIgnoreCase;
        this.convertMap = keyIgnoreCase ? Collections.unmodifiableMap(tmpConvertMap) : Collections.unmodifiableMap(convertMap);
    }

    @Override
    public String convert(String str) {
        if (keyIgnoreCase) {
            str = Ascii.toUpper(str);
        }
        return convertMap.getOrDefault(str, str);
    }
}
