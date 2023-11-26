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

import static com.iofairy.string.StringCase.*;

/**
 * CaseConverter
 *
 * @since 0.3.0
 */
public enum CaseConverter implements StringConverter {
    /**
     * {@link StringCase#LOWER_SPACE} to {@link StringCase#UPPER_SPACE}<br><b>e.g.</b> <b>lower space</b> to <b>UPPER SPACE</b>
     */
    LS_US(LOWER_SPACE, UPPER_SPACE),
    /**
     * {@link StringCase#LOWER_SPACE} to {@link StringCase#LOWER_HYPHEN}<br><b>e.g.</b> <b>lower space</b> to <b>lower-hyphen</b>
     */
    LS_LH(LOWER_SPACE, LOWER_HYPHEN),
    /**
     * {@link StringCase#LOWER_SPACE} to {@link StringCase#UPPER_HYPHEN}<br><b>e.g.</b> <b>lower space</b> to <b>UPPER-HYPHEN</b>
     */
    LS_UH(LOWER_SPACE, UPPER_HYPHEN),
    /**
     * {@link StringCase#LOWER_SPACE} to {@link StringCase#LOWER_UNDERSCORE}<br><b>e.g.</b> <b>lower space</b> to <b>lower_underscore</b>
     */
    LS_LU(LOWER_SPACE, LOWER_UNDERSCORE),
    /**
     * {@link StringCase#LOWER_SPACE} to {@link StringCase#UPPER_UNDERSCORE}<br><b>e.g.</b> <b>lower space</b> to <b>UPPER_UNDERSCORE</b>
     */
    LS_UU(LOWER_SPACE, UPPER_UNDERSCORE),
    /**
     * {@link StringCase#LOWER_SPACE} to {@link StringCase#LOWER_CAMEL}<br><b>e.g.</b> <b>lower space</b> to <b>lowerCamel</b>
     */
    LS_LC(LOWER_SPACE, LOWER_CAMEL),
    /**
     * {@link StringCase#LOWER_SPACE} to {@link StringCase#UPPER_CAMEL}<br><b>e.g.</b> <b>lower space</b> to <b>UpperCamel</b>
     */
    LS_UC(LOWER_SPACE, UPPER_CAMEL),
    /**
     * {@link StringCase#UPPER_SPACE} to {@link StringCase#LOWER_HYPHEN}<br><b>e.g.</b> <b>UPPER SPACE</b> to <b>lower-hyphen</b>
     */
    US_LH(UPPER_SPACE, LOWER_HYPHEN),
    /**
     * {@link StringCase#UPPER_SPACE} to {@link StringCase#UPPER_HYPHEN}<br><b>e.g.</b> <b>UPPER SPACE</b> to <b>UPPER-HYPHEN</b>
     */
    US_UH(UPPER_SPACE, UPPER_HYPHEN),
    /**
     * {@link StringCase#UPPER_SPACE} to {@link StringCase#LOWER_UNDERSCORE}<br><b>e.g.</b> <b>UPPER SPACE</b> to <b>lower_underscore</b>
     */
    US_LU(UPPER_SPACE, LOWER_UNDERSCORE),
    /**
     * {@link StringCase#UPPER_SPACE} to {@link StringCase#UPPER_UNDERSCORE}<br><b>e.g.</b> <b>UPPER SPACE</b> to <b>UPPER_UNDERSCORE</b>
     */
    US_UU(UPPER_SPACE, UPPER_UNDERSCORE),
    /**
     * {@link StringCase#UPPER_SPACE} to {@link StringCase#LOWER_CAMEL}<br><b>e.g.</b> <b>UPPER SPACE</b> to <b>lowerCamel</b>
     */
    US_LC(UPPER_SPACE, LOWER_CAMEL),
    /**
     * {@link StringCase#UPPER_SPACE} to {@link StringCase#UPPER_CAMEL}<br><b>e.g.</b> <b>UPPER SPACE</b> to <b>UpperCamel</b>
     */
    US_UC(UPPER_SPACE, UPPER_CAMEL),
    /**
     * {@link StringCase#LOWER_HYPHEN} to {@link StringCase#UPPER_HYPHEN}<br><b>e.g.</b> <b>lower-hyphen</b> to <b>UPPER-HYPHEN</b>
     */
    LH_UH(LOWER_HYPHEN, UPPER_HYPHEN),
    /**
     * {@link StringCase#LOWER_HYPHEN} to {@link StringCase#LOWER_UNDERSCORE}<br><b>e.g.</b> <b>lower-hyphen</b> to <b>lower_underscore</b>
     */
    LH_LU(LOWER_HYPHEN, LOWER_UNDERSCORE),
    /**
     * {@link StringCase#LOWER_HYPHEN} to {@link StringCase#UPPER_UNDERSCORE}<br><b>e.g.</b> <b>lower-hyphen</b> to <b>UPPER_UNDERSCORE</b>
     */
    LH_UU(LOWER_HYPHEN, UPPER_UNDERSCORE),
    /**
     * {@link StringCase#LOWER_HYPHEN} to {@link StringCase#LOWER_CAMEL}<br><b>e.g.</b> <b>lower-hyphen</b> to <b>lowerCamel</b>
     */
    LH_LC(LOWER_HYPHEN, LOWER_CAMEL),
    /**
     * {@link StringCase#LOWER_HYPHEN} to {@link StringCase#UPPER_CAMEL}<br><b>e.g.</b> <b>lower-hyphen</b> to <b>UpperCamel</b>
     */
    LH_UC(LOWER_HYPHEN, UPPER_CAMEL),
    /**
     * {@link StringCase#UPPER_HYPHEN} to {@link StringCase#LOWER_UNDERSCORE}<br><b>e.g.</b> <b>UPPER-HYPHEN</b> to <b>lower_underscore</b>
     */
    UH_LU(UPPER_HYPHEN, LOWER_UNDERSCORE),
    /**
     * {@link StringCase#UPPER_HYPHEN} to {@link StringCase#UPPER_UNDERSCORE}<br><b>e.g.</b> <b>UPPER-HYPHEN</b> to <b>UPPER_UNDERSCORE</b>
     */
    UH_UU(UPPER_HYPHEN, UPPER_UNDERSCORE),
    /**
     * {@link StringCase#UPPER_HYPHEN} to {@link StringCase#LOWER_CAMEL}<br><b>e.g.</b> <b>UPPER-HYPHEN</b> to <b>lowerCamel</b>
     */
    UH_LC(UPPER_HYPHEN, LOWER_CAMEL),
    /**
     * {@link StringCase#UPPER_HYPHEN} to {@link StringCase#UPPER_CAMEL}<br><b>e.g.</b> <b>UPPER-HYPHEN</b> to <b>UpperCamel</b>
     */
    UH_UC(UPPER_HYPHEN, UPPER_CAMEL),
    /**
     * {@link StringCase#LOWER_UNDERSCORE} to {@link StringCase#UPPER_UNDERSCORE}<br><b>e.g.</b> <b>lower_underscore</b> to <b>UPPER_UNDERSCORE</b>
     */
    LU_UU(LOWER_UNDERSCORE, UPPER_UNDERSCORE),
    /**
     * {@link StringCase#LOWER_UNDERSCORE} to {@link StringCase#LOWER_CAMEL}<br><b>e.g.</b> <b>lower_underscore</b> to <b>lowerCamel</b>
     */
    LU_LC(LOWER_UNDERSCORE, LOWER_CAMEL),
    /**
     * {@link StringCase#LOWER_UNDERSCORE} to {@link StringCase#UPPER_CAMEL}<br><b>e.g.</b> <b>lower_underscore</b> to <b>UpperCamel</b>
     */
    LU_UC(LOWER_UNDERSCORE, UPPER_CAMEL),
    /**
     * {@link StringCase#UPPER_UNDERSCORE} to {@link StringCase#LOWER_CAMEL}<br><b>e.g.</b> <b>UPPER_UNDERSCORE</b> to <b>lowerCamel</b>
     */
    UU_LC(UPPER_UNDERSCORE, LOWER_CAMEL),
    /**
     * {@link StringCase#UPPER_UNDERSCORE} to {@link StringCase#UPPER_CAMEL}<br><b>e.g.</b> <b>UPPER_UNDERSCORE</b> to <b>UpperCamel</b>
     */
    UU_UC(UPPER_UNDERSCORE, UPPER_CAMEL),
    /**
     * {@link StringCase#LOWER_CAMEL} to {@link StringCase#UPPER_CAMEL}<br><b>e.g.</b> <b>lowerCamel</b> to <b>UpperCamel</b>
     */
    LC_UC(LOWER_CAMEL, UPPER_CAMEL),
    /**
     * {@link StringCase#UPPER_CAMEL} to {@link StringCase#LOWER_CAMEL}<br><b>e.g.</b> <b>UpperCamel</b> to <b>lowerCamel</b>
     */
    UC_LC(UPPER_CAMEL, LOWER_CAMEL),
    /**
     * {@link StringCase#UPPER_CAMEL} to {@link StringCase#UPPER_UNDERSCORE}<br><b>e.g.</b> <b>UpperCamel</b> to <b>UPPER_UNDERSCORE</b>
     */
    UC_UU(UPPER_CAMEL, UPPER_UNDERSCORE),
    /**
     * {@link StringCase#UPPER_CAMEL} to {@link StringCase#LOWER_UNDERSCORE}<br><b>e.g.</b> <b>UpperCamel</b> to <b>lower_underscore</b>
     */
    UC_LU(UPPER_CAMEL, LOWER_UNDERSCORE),
    /**
     * {@link StringCase#UPPER_CAMEL} to {@link StringCase#UPPER_HYPHEN}<br><b>e.g.</b> <b>UpperCamel</b> to <b>UPPER-HYPHEN</b>
     */
    UC_UH(UPPER_CAMEL, UPPER_HYPHEN),
    /**
     * {@link StringCase#UPPER_CAMEL} to {@link StringCase#LOWER_HYPHEN}<br><b>e.g.</b> <b>UpperCamel</b> to <b>lower-hyphen</b>
     */
    UC_LH(UPPER_CAMEL, LOWER_HYPHEN),
    /**
     * {@link StringCase#UPPER_CAMEL} to {@link StringCase#UPPER_SPACE}<br><b>e.g.</b> <b>UpperCamel</b> to <b>UPPER SPACE</b>
     */
    UC_US(UPPER_CAMEL, UPPER_SPACE),
    /**
     * {@link StringCase#UPPER_CAMEL} to {@link StringCase#LOWER_SPACE}<br><b>e.g.</b> <b>UpperCamel</b> to <b>lower space</b>
     */
    UC_LS(UPPER_CAMEL, LOWER_SPACE),
    /**
     * {@link StringCase#LOWER_CAMEL} to {@link StringCase#UPPER_UNDERSCORE}<br><b>e.g.</b> <b>lowerCamel</b> to <b>UPPER_UNDERSCORE</b>
     */
    LC_UU(LOWER_CAMEL, UPPER_UNDERSCORE),
    /**
     * {@link StringCase#LOWER_CAMEL} to {@link StringCase#LOWER_UNDERSCORE}<br><b>e.g.</b> <b>lowerCamel</b> to <b>lower_underscore</b>
     */
    LC_LU(LOWER_CAMEL, LOWER_UNDERSCORE),
    /**
     * {@link StringCase#LOWER_CAMEL} to {@link StringCase#UPPER_HYPHEN}<br><b>e.g.</b> <b>lowerCamel</b> to <b>UPPER-HYPHEN</b>
     */
    LC_UH(LOWER_CAMEL, UPPER_HYPHEN),
    /**
     * {@link StringCase#LOWER_CAMEL} to {@link StringCase#LOWER_HYPHEN}<br><b>e.g.</b> <b>lowerCamel</b> to <b>lower-hyphen</b>
     */
    LC_LH(LOWER_CAMEL, LOWER_HYPHEN),
    /**
     * {@link StringCase#LOWER_CAMEL} to {@link StringCase#UPPER_SPACE}<br><b>e.g.</b> <b>lowerCamel</b> to <b>UPPER SPACE</b>
     */
    LC_US(LOWER_CAMEL, UPPER_SPACE),
    /**
     * {@link StringCase#LOWER_CAMEL} to {@link StringCase#LOWER_SPACE}<br><b>e.g.</b> <b>lowerCamel</b> to <b>lower space</b>
     */
    LC_LS(LOWER_CAMEL, LOWER_SPACE),
    /**
     * {@link StringCase#UPPER_UNDERSCORE} to {@link StringCase#LOWER_UNDERSCORE}<br><b>e.g.</b> <b>UPPER_UNDERSCORE</b> to <b>lower_underscore</b>
     */
    UU_LU(UPPER_UNDERSCORE, LOWER_UNDERSCORE),
    /**
     * {@link StringCase#UPPER_UNDERSCORE} to {@link StringCase#UPPER_HYPHEN}<br><b>e.g.</b> <b>UPPER_UNDERSCORE</b> to <b>UPPER-HYPHEN</b>
     */
    UU_UH(UPPER_UNDERSCORE, UPPER_HYPHEN),
    /**
     * {@link StringCase#UPPER_UNDERSCORE} to {@link StringCase#LOWER_HYPHEN}<br><b>e.g.</b> <b>UPPER_UNDERSCORE</b> to <b>lower-hyphen</b>
     */
    UU_LH(UPPER_UNDERSCORE, LOWER_HYPHEN),
    /**
     * {@link StringCase#UPPER_UNDERSCORE} to {@link StringCase#UPPER_SPACE}<br><b>e.g.</b> <b>UPPER_UNDERSCORE</b> to <b>UPPER SPACE</b>
     */
    UU_US(UPPER_UNDERSCORE, UPPER_SPACE),
    /**
     * {@link StringCase#UPPER_UNDERSCORE} to {@link StringCase#LOWER_SPACE}<br><b>e.g.</b> <b>UPPER_UNDERSCORE</b> to <b>lower space</b>
     */
    UU_LS(UPPER_UNDERSCORE, LOWER_SPACE),
    /**
     * {@link StringCase#LOWER_UNDERSCORE} to {@link StringCase#UPPER_HYPHEN}<br><b>e.g.</b> <b>lower_underscore</b> to <b>UPPER-HYPHEN</b>
     */
    LU_UH(LOWER_UNDERSCORE, UPPER_HYPHEN),
    /**
     * {@link StringCase#LOWER_UNDERSCORE} to {@link StringCase#LOWER_HYPHEN}<br><b>e.g.</b> <b>lower_underscore</b> to <b>lower-hyphen</b>
     */
    LU_LH(LOWER_UNDERSCORE, LOWER_HYPHEN),
    /**
     * {@link StringCase#LOWER_UNDERSCORE} to {@link StringCase#UPPER_SPACE}<br><b>e.g.</b> <b>lower_underscore</b> to <b>UPPER SPACE</b>
     */
    LU_US(LOWER_UNDERSCORE, UPPER_SPACE),
    /**
     * {@link StringCase#LOWER_UNDERSCORE} to {@link StringCase#LOWER_SPACE}<br><b>e.g.</b> <b>lower_underscore</b> to <b>lower space</b>
     */
    LU_LS(LOWER_UNDERSCORE, LOWER_SPACE),
    /**
     * {@link StringCase#UPPER_HYPHEN} to {@link StringCase#LOWER_HYPHEN}<br><b>e.g.</b> <b>UPPER-HYPHEN</b> to <b>lower-hyphen</b>
     */
    UH_LH(UPPER_HYPHEN, LOWER_HYPHEN),
    /**
     * {@link StringCase#UPPER_HYPHEN} to {@link StringCase#UPPER_SPACE}<br><b>e.g.</b> <b>UPPER-HYPHEN</b> to <b>UPPER SPACE</b>
     */
    UH_US(UPPER_HYPHEN, UPPER_SPACE),
    /**
     * {@link StringCase#UPPER_HYPHEN} to {@link StringCase#LOWER_SPACE}<br><b>e.g.</b> <b>UPPER-HYPHEN</b> to <b>lower space</b>
     */
    UH_LS(UPPER_HYPHEN, LOWER_SPACE),
    /**
     * {@link StringCase#LOWER_HYPHEN} to {@link StringCase#UPPER_SPACE}<br><b>e.g.</b> <b>lower-hyphen</b> to <b>UPPER SPACE</b>
     */
    LH_US(LOWER_HYPHEN, UPPER_SPACE),
    /**
     * {@link StringCase#LOWER_HYPHEN} to {@link StringCase#LOWER_SPACE}<br><b>e.g.</b> <b>lower-hyphen</b> to <b>lower space</b>
     */
    LH_LS(LOWER_HYPHEN, LOWER_SPACE),
    /**
     * {@link StringCase#UPPER_SPACE} to {@link StringCase#LOWER_SPACE}<br><b>e.g.</b> <b>UPPER SPACE</b> to <b>lower space</b>
     */
    US_LS(UPPER_SPACE, LOWER_SPACE),
    /**
     * {@link StringCase#ALL_SEPARATORS} to {@link StringCase#UPPER_CAMEL}
     */
    AS_UC(ALL_SEPARATORS, UPPER_CAMEL),
    /**
     * {@link StringCase#ALL_SEPARATORS} to {@link StringCase#LOWER_CAMEL}
     */
    AS_LC(ALL_SEPARATORS, LOWER_CAMEL),
    /**
     * {@link StringCase#ALL_SEPARATORS} to {@link StringCase#UPPER_UNDERSCORE}
     */
    AS_UU(ALL_SEPARATORS, UPPER_UNDERSCORE),
    /**
     * {@link StringCase#ALL_SEPARATORS} to {@link StringCase#LOWER_UNDERSCORE}
     */
    AS_LU(ALL_SEPARATORS, LOWER_UNDERSCORE),
    /**
     * {@link StringCase#ALL_SEPARATORS} to {@link StringCase#UPPER_SPACE}
     */
    AS_US(ALL_SEPARATORS, UPPER_SPACE),
    /**
     * {@link StringCase#ALL_SEPARATORS} to {@link StringCase#LOWER_SPACE}
     */
    AS_LS(ALL_SEPARATORS, LOWER_SPACE),
    /**
     * {@link StringCase#ALL_SEPARATORS} to {@link StringCase#UPPER_HYPHEN}
     */
    AS_UH(ALL_SEPARATORS, UPPER_HYPHEN),
    /**
     * {@link StringCase#ALL_SEPARATORS} to {@link StringCase#LOWER_HYPHEN}
     */
    AS_LH(ALL_SEPARATORS, LOWER_HYPHEN);


    /*=======================================================*/
    public final StringCase fromCase;
    public final StringCase toCase;

    CaseConverter(StringCase fromCase, StringCase toCase) {
        this.fromCase = fromCase;
        this.toCase = toCase;
    }

    @Override
    public String convert(String inputStr) {
        return fromCase.to(toCase, inputStr);
    }

}
