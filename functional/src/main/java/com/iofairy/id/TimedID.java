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
package com.iofairy.id;

import com.iofairy.os.OS;
import com.iofairy.time.DateTime;
import com.iofairy.top.S;

import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.UUID;
import java.util.regex.Pattern;

import static com.iofairy.validator.Preconditions.*;

/**
 * ID with date time info
 *
 * @since 0.6.0
 */
public class TimedID {
    /*###################################################################################
     ************************************************************************************
     ------------------------------------------------------------------------------------
     *************************        默认值及最大最小值设置        ************************
     ------------------------------------------------------------------------------------
     ************************************************************************************
     ###################################################################################*/
    private static final int MIN_ID_LENGTH = 25;
    private static final int MAX_ID_LENGTH = 50;
    private static final int DEFAULT_ID_LENGTH = 25;
    private static final int DEFAULT_WORKER_NUM = 0;
    private static final int MIN_WORKERID_LENGTH = 1;
    private static final int MAX_WORKERID_LENGTH = 6;
    private static final int DEFAULT_WORKERID_LENGTH = 2;
    private static final boolean DEFAULT_UPPER_CASE = true;
    private static final boolean DEFAULT_WITH_UNDERLINE = false;
    private static final int MIN_YEAR_LENGTH = 2;
    private static final int MAX_YEAR_LENGTH = 5;
    private static final int DEFAULT_YEAR_LENGTH = 3;
    private static final boolean DEFAULT_WITH_TIMESTAMP = false;
    private static final Instant DEFAULT_START_INSTANT = Instant.EPOCH;
    private static final int MIN_EPOCH_MILLIS_LENGTH = 12;
    /*###################################################################################
     ************************************************************************************
     ------------------------------------------------------------------------------------
     ************************        预设置常用的 TimedID 实例        **********************
     ------------------------------------------------------------------------------------
     ************************************************************************************
     ###################################################################################*/
    /**
     * 默认的 TimedUUID 实例（仅能设置一次，应在程序初始化时设置）<br>
     * <b>注：</b><br>
     * 如果在设置之前就调用 {@link #getId()}，则会被设置 {@link #TIMED_ID}
     */
    private static TimedID DEFAULT_ID;

    /*#####################################################
     *********          TimedID带时间格式化         *********
     #####################################################*/
    /**
     * 25位（含15位时间）的 TimedUUID 实例
     */
    public static final TimedID TIMED_ID = newBuilder().withYearLength(2).build();
    /**
     * 26位（含16位时间）的 TimedUUID 实例
     */
    public static final TimedID TIMED_ID26 = newBuilder().withIdLength(26).build();
    /**
     * 30位（含16位时间）的 TimedUUID 实例
     */
    public static final TimedID TIMED_ID30 = newBuilder().withIdLength(30).build();
    /**
     * 32位（含17位时间）的 TimedUUID 实例
     */
    public static final TimedID TIMED_ID32 = newBuilder().withIdLength(32).withYearLength(4).build();
    /**
     * 30位（含16位时间）的带 _(下划线) 的 TimedUUID 实例
     */
    public static final TimedID TIMED_ID_UNDERLINE = newBuilder().withUnderline(true).withIdLength(30).withWorkerIdLength(1).build();
    /*#####################################################
     *********           TimedID带时间戳           *********
     #####################################################*/
    /**
     * 25位（含xx位时间戳）的 TimedUUID 实例
     */
    public static final TimedID TS_ID = newBuilder().withTimestamp(true).build();
    /**
     * 26位（含xx位时间戳）的 TimedUUID 实例
     */
    public static final TimedID TS_ID26 = newBuilder().withTimestamp(true).withIdLength(26).build();
    /**
     * 30位（含xx位时间戳）的 TimedUUID 实例
     */
    public static final TimedID TS_ID30 = newBuilder().withTimestamp(true).withIdLength(30).build();
    /**
     * 32位（含xx位时间戳）的 TimedUUID 实例
     */
    public static final TimedID TS_ID32 = newBuilder().withTimestamp(true).withIdLength(32).build();
    /**
     * 30位（含xx位时间戳）的带 _(下划线) 的 TimedUUID 实例
     */
    public static final TimedID TS_ID_UNDERLINE = newBuilder().withTimestamp(true).withUnderline(true).withIdLength(30).withWorkerIdLength(1).build();

    /*#####################################################
     *********             其他全局对象             *********
     #####################################################*/
    /**
     * 去除UUID中的横线的正则表达式
     */
    private static final Pattern PATTERN = Pattern.compile("-");

    /*###################################################################################
     ************************************************************************************
     ------------------------------------------------------------------------------------
     ****************************        TimedID 属性        ****************************
     ------------------------------------------------------------------------------------
     ************************************************************************************
     ###################################################################################*/
    /**
     * 工作节点ID（数字）
     */
    public final int workerNum;
    /**
     * 工作节点ID位数，范围 <b>[1, 6]</b>
     */
    public final int workerIdLength;
    /**
     * 工作节点ID（字母序列）
     */
    public final String workerId;
    /**
     * 最后生成的ID位数，范围 <b>[25, 50]</b><br>
     * {@code 时间串(13~18位) + "_"(0~1位) + 工作节点ID(1~6位) + 36进制UUID(25位) = 最大50位 }
     */
    public final int idLength;
    /**
     * 是否大写
     */
    public final boolean upperCase;
    /**
     * 生成的ID是否带下划线（用于区分时间串与UUID）
     */
    public final boolean withUnderline;
    /**
     * 最后生成的“年”的位数，范围 <b>[2, 5]</b>
     */
    public final int yearLength;
    /**
     * UUID前缀是否采用时间戳形式（<b>两种形式：时间戳 或 格式化的时间</b>）
     */
    public final boolean withTimestamp;
    /**
     * 时间戳基准时间（即<b>时间戳以某个时间开始计算</b>，而不是以标准的 {@code 1970-01-01T00:00:00.000Z} 开始计算）
     */
    public final Instant startInstant;
    /**
     * 时间格式串
     */
    private final DateTimeFormatter formatter;
    private final String underlineWithWorkerId;

    /**
     * TimedID Builder
     */
    public static class Builder {
        private int workerNum = DEFAULT_WORKER_NUM;
        private int workerIdLength = DEFAULT_WORKERID_LENGTH;
        private int idLength = DEFAULT_ID_LENGTH;
        private boolean upperCase = DEFAULT_UPPER_CASE;
        private boolean withUnderline = DEFAULT_WITH_UNDERLINE;
        private int yearLength = DEFAULT_YEAR_LENGTH;
        private boolean withTimestamp = DEFAULT_WITH_TIMESTAMP;
        private Instant startInstant = DEFAULT_START_INSTANT;

        Builder() {
        }

        Builder(int idLength, boolean withUnderline, boolean withTimestamp) {
            this.idLength = idLength;
            this.withUnderline = withUnderline;
            this.withTimestamp = withTimestamp;
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public static Builder newBuilder(int idLength, boolean withUnderline, boolean withTimestamp) {
            return new Builder(idLength, withUnderline, withTimestamp);
        }

        public Builder withWorkerNum(int workerNum) {
            this.workerNum = workerNum;
            return this;
        }

        public Builder withWorkerIdLength(int workerIdLength) {
            this.workerIdLength = workerIdLength;
            return this;
        }

        public Builder withIdLength(int idLength) {
            this.idLength = idLength;
            return this;
        }

        public Builder withUpperCase(boolean upperCase) {
            this.upperCase = upperCase;
            return this;
        }

        public Builder withUnderline(boolean withUnderline) {
            this.withUnderline = withUnderline;
            return this;
        }

        public Builder withYearLength(int yearLength) {
            this.yearLength = yearLength;
            return this;
        }

        public Builder withTimestamp(boolean withTimestamp) {
            this.withTimestamp = withTimestamp;
            return this;
        }

        /**
         * 设置时间戳基准时间（<b>以{@code UTC}为准</b>）（即<b>时间戳以某个时间开始计算</b>，而不是以标准的 {@code 1970-01-01T00:00:00.000Z} 开始计算）
         *
         * @param startInstant 时间戳基准时间（<b>以{@code UTC}为准</b>）
         * @return Builder
         */
        public Builder withStartInstant(Instant startInstant) {
            if (startInstant == null) return this;
            checkArgument(startInstant.toEpochMilli() < 0, "The parameter `startInstant` must be after `1970-01-01T00:00:00.000Z`! ");
            Instant nowInstant = Instant.now();
            checkArgument(startInstant.isAfter(nowInstant), "The parameter `startInstant` must be before current date time[${…}]! ", nowInstant);
            this.startInstant = startInstant;
            return this;
        }

        /**
         * 设置时间戳基准时间（即<b>时间戳以某个时间开始计算</b>，而不是以标准的 {@code 1970-01-01T00:00:00.000Z} 开始计算）。格式为：{@code yyyy-MM-dd HH:mm:ss.SSS}
         *
         * @param dateText 时间戳基准时间，格式为：{@code yyyy-MM-dd HH:mm:ss.SSS}
         * @return Builder
         */
        public Builder withStartInstant(String dateText) {
            if (dateText == null) return this;
            Instant startInstant = DateTime.parse(dateText).getInstant();
            return withStartInstant(startInstant);
        }

        public TimedID build() {
            return new TimedID(this);
        }
    }

    TimedID(Builder builder) {
        this(builder.workerNum, builder.workerIdLength, builder.idLength, builder.upperCase, builder.withUnderline, builder.yearLength, builder.withTimestamp, builder.startInstant);
    }

    TimedID(int workerNum, int workerIdLength, int idLength, boolean upperCase, boolean withUnderline, int yearLength, boolean withTimestamp, Instant startInstant) {
        this.workerNum = workerNum;
        this.workerIdLength = workerIdLength < MIN_WORKERID_LENGTH ? MIN_WORKERID_LENGTH : (Math.min(workerIdLength, MAX_WORKERID_LENGTH));
        this.idLength = idLength < MIN_ID_LENGTH ? MIN_ID_LENGTH : (Math.min(idLength, MAX_ID_LENGTH));
        this.upperCase = upperCase;
        this.withUnderline = withUnderline;
        this.workerId = numberToLetters(this.workerNum, this.workerIdLength);
        this.yearLength = yearLength < MIN_YEAR_LENGTH ? MIN_YEAR_LENGTH : (Math.min(yearLength, MAX_YEAR_LENGTH));
        this.withTimestamp = withTimestamp;
        this.startInstant = startInstant;

        this.underlineWithWorkerId = (this.withUnderline ? "_" : "") + this.workerId;
        this.formatter = new DateTimeFormatterBuilder()
                .appendValueReduced(ChronoField.YEAR, this.yearLength, this.yearLength, 0)
                .appendPattern("MMddHHmmssSSS")
                .toFormatter();
    }

    public static Builder newBuilder() {
        return Builder.newBuilder();
    }

    /**
     * 从 {@link #DEFAULT_ID} 获取随机ID
     *
     * @return 随机ID
     */
    public static String getId() {
        setDefaultId(TIMED_ID);

        return DEFAULT_ID.randomId();
    }

    /**
     * 从 {@link #TIMED_ID} 获取 <b>25位（含15位时间）</b> 随机ID
     *
     * @return 随机ID
     */
    public static String newId() {
        return TIMED_ID.randomId();
    }

    /**
     * 从 {@link #TIMED_ID26} 获取 <b>26位（含16位时间）</b> 随机ID
     *
     * @return 随机ID
     */
    public static String shortId() {
        return TIMED_ID26.randomId();
    }

    /**
     * 从 {@link #TIMED_ID30} 获取 <b>30位（含16位时间）</b> 随机ID
     *
     * @return 随机ID
     */
    public static String mediumId() {
        return TIMED_ID30.randomId();
    }

    /**
     * 从 {@link #TIMED_ID32} 获取 <b>32位（含17位时间）</b> 随机ID
     *
     * @return 随机ID
     */
    public static String longId() {
        return TIMED_ID32.randomId();
    }

    /**
     * 从 {@link #TIMED_ID_UNDERLINE} 获取 <b>30位（含16位时间）的带 _(下划线)</b> 随机ID
     *
     * @return 随机ID
     */
    public static String linedId() {
        return TIMED_ID_UNDERLINE.randomId();
    }

    /**
     * 从 {@link #TS_ID} 获取 <b>25位（含xx位时间戳）</b> 随机ID
     *
     * @return 随机ID
     */
    public static String nid() {
        return TS_ID.randomId();
    }

    /**
     * 从 {@link #TS_ID26} 获取 <b>26位（含xx位时间戳）</b> 随机ID
     *
     * @return 随机ID
     */
    public static String sid() {
        return TS_ID26.randomId();
    }

    /**
     * 从 {@link #TS_ID30} 获取 <b>30位（含xx位时间戳）</b> 随机ID
     *
     * @return 随机ID
     */
    public static String mid() {
        return TS_ID30.randomId();
    }

    /**
     * 从 {@link #TS_ID32} 获取 <b>32位（含xx位时间戳）</b> 随机ID
     *
     * @return 随机ID
     */
    public static String lid() {
        return TS_ID32.randomId();
    }

    /**
     * 从 {@link #TS_ID_UNDERLINE} 获取 <b>30位（含xx位时间戳）的带 _(下划线)</b> 随机ID
     *
     * @return 随机ID
     */
    public static String _id() {
        return TS_ID_UNDERLINE.randomId();
    }

    /**
     * 获取随机ID
     *
     * @return 随机ID
     */
    public String randomId() {
        String timestamp = S.padLeftChars(Instant.now().toEpochMilli() - startInstant.toEpochMilli(), '0', MIN_EPOCH_MILLIS_LENGTH);
        String dateTime = withTimestamp ? timestamp : formatter.format(LocalDateTime.now());
        String base36 = uuidToBase36();

        String id = dateTime + underlineWithWorkerId + base36;
        id = id.substring(0, Math.min(idLength, id.length()));
        return upperCase ? id.toUpperCase() : id.toLowerCase();
    }

    public static TimedID getDefaultId() {
        return DEFAULT_ID;
    }

    public static void setDefaultId(TimedID defaultId) {
        if (DEFAULT_ID == null) {
            DEFAULT_ID = defaultId;
        }
    }

    private static String uuidToBase36() {
        String hexUuid = UUID.randomUUID().toString();
        hexUuid = PATTERN.matcher(hexUuid).replaceAll("");

        int radix = 36;
        BigInteger bigInteger = new BigInteger(hexUuid, 16);
        String base36 = bigInteger.toString(radix);
        /*
         * 16进制UUID 转 36进制，最大25位
         */
        if (base36.length() < 25) {
            base36 = S.padRightChars(base36, '0', 25);  // 这里采用末位补0的方式，因为末位可能被裁剪。
        }
        return base36;
    }


    /**
     * 将数字映射成指定位数的字母<b>（大写）</b> <br>
     * 注：<br>
     * <ul>
     *     <li> 映射成1位字母，number取值 {@code [0, 25]}
     *     <li> 映射成2位字母，number取值 {@code [0, 675]}
     *     <li> 映射成3位字母，number取值 {@code [0, 17575]}
     *     <li> 映射成4位字母，number取值 {@code [0, 456975]}
     *     <li> 映射成5位字母，number取值 {@code [0, 11881375]}
     *     <li> 映射成6位字母，number取值 {@code [0, 308915775]}
     * </ul>
     *
     * @param number       要转成字母的数字
     * @param letterLength 字母位数，取值范围 {@code [1, 6]}
     * @return 指定位数的字母<b>（大写）</b>
     */
    public static String numberToLetters(int number, int letterLength) {
        checkOutOfBounds(number < 0, number, OS.IS_ZH_LANG ? "参数`number`应为非负数！" : "Parameter `number` must be non-negative! ");
        checkOutOfBounds(letterLength < 1 || letterLength > 6, number, OS.IS_ZH_LANG ? "参数`letterLength`取值范围为：[1, 6]！" : "Parameter `letterLength` must be in [1, 6]! ");

        int maxNumber = (int) Math.pow(26, letterLength) - 1;

        checkArgument(number > maxNumber, "参数`number`超出范围，当前位数[${letterLength}]下，最大允许值[${maxNumber}]，无法映射到指定位数的字母！", letterLength, maxNumber);

        char[] letters = new char[letterLength];

        for (int i = letterLength - 1; i >= 0; i--) {
            letters[i] = (char) ('A' + number % 26);
            number /= 26;
        }

        return String.valueOf(letters);
    }

    @Override
    public String toString() {
        return "TimedID{" +
                "workerNum=" + workerNum +
                ", workerIdLength=" + workerIdLength +
                ", workerId='" + workerId + '\'' +
                ", idLength=" + idLength +
                ", upperCase=" + upperCase +
                ", withUnderline=" + withUnderline +
                ", yearLength=" + yearLength +
                ", withTimestamp=" + withTimestamp +
                ", startInstant=" + startInstant +
                ", startEpochMillis=" + startInstant.toEpochMilli() +
                '}';
    }
}
