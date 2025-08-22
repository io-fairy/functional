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
package com.iofairy.os;

/**
 * Java Version.<br>
 * See: <a href="https://en.wikipedia.org/wiki/Java_(programming_language)#Versions">Java Versions</a>
 *
 * @since 0.6.0
 */
public enum JavaVersion {
    JAVA_0("1.0", "1.0", 1.0f, 45.0f, "1996-01-23"),
    JAVA_1("1.1", "1.1", 1.1f, 45.0f, "1997-02-19"),
    JAVA_2("1.2", "1.2", 1.2f, 46.0f, "1998-12-08"),
    JAVA_3("1.3", "1.3", 1.3f, 47.0f, "2000-05-08"),
    JAVA_4("1.4", "1.4", 1.4f, 48.0f, "2002-02-06"),
    JAVA_5("1.5", "5", 1.5f, 49.0f, "2004-09-30"),
    JAVA_6("1.6", "6", 1.6f, 50.0f, "2006-12-11"),
    JAVA_7("1.7", "7", 1.7f, 51.0f, "2011-07-28"),
    JAVA_8("1.8", "8", 1.8f, 52.0f, "2014-03-18"),
    JAVA_9("9", "9", 9f, 53.0f, "2017-09-21"),
    JAVA_10("10", "10", 10f, 54.0f, "2018-03-20"),
    JAVA_11("11", "11", 11f, 55.0f, "2018-09-25"),
    JAVA_12("12", "12", 12f, 56.0f, "2019-03-19"),
    JAVA_13("13", "13", 13f, 57.0f, "2019-09-17"),
    JAVA_14("14", "14", 14f, 58.0f, "2020-03-17"),
    JAVA_15("15", "15", 15f, 59.0f, "2020-09-15"),
    JAVA_16("16", "16", 16f, 60.0f, "2021-03-16"),
    JAVA_17("17", "17", 17f, 61.0f, "2021-09-14"),
    JAVA_18("18", "18", 18f, 62.0f, "2022-03-22"),
    JAVA_19("19", "19", 19f, 63.0f, "2022-09-20"),
    JAVA_20("20", "20", 20f, 64.0f, "2023-03-21"),
    JAVA_21("21", "21", 21f, 65.0f, "2023-09-19"),
    JAVA_22("22", "22", 22f, 66.0f, "2024-03-19"),
    JAVA_23("23", "23", 23f, 67.0f, "2024-09-17"),
    JAVA_24("24", "24", 24f, 68.0f, "2025-03-18"),
    ;

    /**
     * Java version name
     */
    public final String name;
    /**
     * Java version alias
     */
    public final String alias;
    /**
     * Java version number
     */
    public final float version;
    /**
     * Class File Bytecode Version
     */
    public final float bytecodeVersion;
    /**
     * Java version release date
     */
    public final String releaseDate;

    JavaVersion(final String name, final String alias, float version, float bytecodeVersion, String releaseDate) {
        this.name = name;
        this.alias = alias;
        this.version = version;
        this.bytecodeVersion = bytecodeVersion;
        this.releaseDate = releaseDate;
    }

    public static JavaVersion of(final String versionName) {
        if (versionName == null) return null;

        switch (versionName) {
            case "1.0":
                return JAVA_0;
            case "1.1":
                return JAVA_1;
            case "1.2":
                return JAVA_2;
            case "1.3":
                return JAVA_3;
            case "1.4":
                return JAVA_4;
            case "1.5":
            case "5":
                return JAVA_5;
            case "1.6":
            case "6":
                return JAVA_6;
            case "1.7":
            case "7":
                return JAVA_7;
            case "1.8":
            case "8":
                return JAVA_8;
            case "9":
                return JAVA_9;
            case "10":
                return JAVA_10;
            case "11":
                return JAVA_11;
            case "12":
                return JAVA_12;
            case "13":
                return JAVA_13;
            case "14":
                return JAVA_14;
            case "15":
                return JAVA_15;
            case "16":
                return JAVA_16;
            case "17":
                return JAVA_17;
            case "18":
                return JAVA_18;
            case "19":
                return JAVA_19;
            case "20":
                return JAVA_20;
            case "21":
                return JAVA_21;
            case "22":
                return JAVA_22;
            case "23":
                return JAVA_23;
            case "24":
                return JAVA_24;
        }
        return null;
    }

    public static int compare(final JavaVersion javaVersion1, final JavaVersion javaVersion2) {
        return Float.compare(javaVersion1.version, javaVersion2.version);
    }

    /**
     * This JavaVersion greater than or equal to specified JavaVersion.
     *
     * @param javaVersion specified JavaVersion
     * @return {@code true} or {@code false}
     */
    public boolean gte(final JavaVersion javaVersion) {
        return this.version >= javaVersion.version;
    }

    /**
     * This JavaVersion less than or equal to specified JavaVersion.
     *
     * @param javaVersion specified JavaVersion
     * @return {@code true} or {@code false}
     */
    public boolean lte(final JavaVersion javaVersion) {
        return this.version <= javaVersion.version;
    }

    /**
     * This JavaVersion greater than specified JavaVersion.
     *
     * @param javaVersion specified JavaVersion
     * @return {@code true} or {@code false}
     */
    public boolean gt(final JavaVersion javaVersion) {
        return this.version > javaVersion.version;
    }

    /**
     * This JavaVersion less than specified JavaVersion.
     *
     * @param javaVersion specified JavaVersion
     * @return {@code true} or {@code false}
     */
    public boolean lt(final JavaVersion javaVersion) {
        return this.version < javaVersion.version;
    }

    /**
     * This JavaVersion equal to specified JavaVersion.
     *
     * @param javaVersion specified JavaVersion
     * @return {@code true} or {@code false}
     */
    public boolean eq(final JavaVersion javaVersion) {
        return this.version == javaVersion.version;
    }

    /**
     * This JavaVersion not equal to specified JavaVersion.
     *
     * @param javaVersion specified JavaVersion
     * @return {@code true} or {@code false}
     */
    public boolean neq(final JavaVersion javaVersion) {
        return this.version != javaVersion.version;
    }

}
