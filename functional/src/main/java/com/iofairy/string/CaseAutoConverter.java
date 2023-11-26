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

import com.iofairy.top.O;
import com.iofairy.top.S;

import static com.iofairy.string.StringCase.*;

/**
 * Auto-check String Case Type, convert to specified {@code stringCase}
 *
 * @since 0.3.0
 */
public class CaseAutoConverter implements StringConverter {

    private StringCase toCase;

    public CaseAutoConverter(StringCase toCase) {
        this.toCase = toCase;
    }

    public static CaseAutoConverter of(StringCase toCase) {
        return new CaseAutoConverter(toCase);
    }

    public StringCase getToCase() {
        return toCase;
    }

    public CaseAutoConverter setToCase(StringCase toCase) {
        this.toCase = toCase;
        return this;
    }

    @Override
    public String convert(String inputStr) {
        if (S.isEmpty(inputStr)) return inputStr;
        StringCase fromCase;
        String chars = " _-";
        int[] charCounts = S.countMultiChars(inputStr, chars);
        int index = O.indexOfMax(charCounts);

        if (charCounts[index] == 0) {               // None of these characters( _-) exist
            int[] ints = Ascii.countCases(inputStr);     // int[]{upperCount, lowerCount, notLetterCount}
            /*
             * NOTE:
             * toLower(str) if count(upper letters) >= count(lower letters)
             */
            if (ints[0] >= ints[1]) {
                inputStr = Ascii.toLower(inputStr);
            }
            fromCase = LOWER_CAMEL;
        } else {
            switch (index) {
                case 0:
                    fromCase = LOWER_SPACE;
                    break;
                case 1:
                    fromCase = LOWER_UNDERSCORE;
                    break;
                default:
                    fromCase = LOWER_HYPHEN;
            }

            if (toCase.isCamelCase) {
                fromCase = ALL_SEPARATORS;
            }
        }

        return fromCase.to(toCase, inputStr);
    }

}
