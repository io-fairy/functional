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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * DateTime Constant
 *
 * @since 0.6.0
 */
public class DTC {
    /*###################################################################################
     ************************************************************************************
     ------------------------------------------------------------------------------------
     ***********************              周规则字段设置             **********************
     ------------------------------------------------------------------------------------
     ************************************************************************************
     ###################################################################################*/
    /**
     * A week starts on <b>Monday</b> and the first week has a minimum of <b>4 days</b>.
     */
    public static final WeekFields WEEK_ISO = WeekFields.ISO;
    /**
     * A week starts on <b>Monday</b> and the first week has a minimum of <b>1 day</b>.
     */
    public static final WeekFields MONDAY_MIN1 = WeekFields.of(DayOfWeek.MONDAY, 1);
    /**
     * A week starts on <b>Monday</b> and the first week has a minimum of <b>4 days</b>.
     */
    public static final WeekFields MONDAY_MIN4 = WeekFields.of(DayOfWeek.MONDAY, 4);
    /**
     * A week starts on <b>Monday</b> and the first week has a minimum of <b>7 days</b>.
     */
    public static final WeekFields MONDAY_MIN7 = WeekFields.of(DayOfWeek.MONDAY, 7);
    /**
     * A week starts on <b>SUNDAY</b> and the first week has a minimum of <b>1 day</b>.
     */
    public static final WeekFields SUNDAY_MIN1 = WeekFields.of(DayOfWeek.SUNDAY, 1);
    /**
     * A week starts on <b>SUNDAY</b> and the first week has a minimum of <b>4 days</b>.
     */
    public static final WeekFields SUNDAY_MIN4 = WeekFields.of(DayOfWeek.SUNDAY, 4);
    /**
     * A week starts on <b>SUNDAY</b> and the first week has a minimum of <b>7 days</b>.
     */
    public static final WeekFields SUNDAY_MIN7 = WeekFields.of(DayOfWeek.SUNDAY, 7);
    /**
     * A week starts on <b>SATURDAY</b> and the first week has a minimum of <b>1 day</b>.
     */
    public static final WeekFields SATURDAY_MIN1 = WeekFields.of(DayOfWeek.SATURDAY, 1);
    /**
     * A week starts on <b>SATURDAY</b> and the first week has a minimum of <b>4 days</b>.
     */
    public static final WeekFields SATURDAY_MIN4 = WeekFields.of(DayOfWeek.SATURDAY, 4);
    /**
     * A week starts on <b>SATURDAY</b> and the first week has a minimum of <b>7 days</b>.
     */
    public static final WeekFields SATURDAY_MIN7 = WeekFields.of(DayOfWeek.SATURDAY, 7);

    /*###################################################################################
     ************************************************************************************
     ------------------------------------------------------------------------------------
     ***********************             各单位的纳秒数              **********************
     ------------------------------------------------------------------------------------
     ************************************************************************************
     ###################################################################################*/
    public final static long NANO       =  ChronoUnit.NANOS.getDuration().toNanos();        // 1
    public final static long MICRO      =  ChronoUnit.MICROS.getDuration().toNanos();       // 1000
    public final static long MILLI      =  ChronoUnit.MILLIS.getDuration().toNanos();       // 1000000
    public final static long SECOND     =  ChronoUnit.SECONDS.getDuration().toNanos();      // 1000000000
    public final static long MINUTE     =  ChronoUnit.MINUTES.getDuration().toNanos();      // 60000000000
    public final static long HOUR       =  ChronoUnit.HOURS.getDuration().toNanos();        // 3600000000000
    public final static long DAY        =  ChronoUnit.DAYS.getDuration().toNanos();         // 86400000000000
    public final static long WEEK       =  ChronoUnit.WEEKS.getDuration().toNanos();        // 604800000000000
    public final static long MONTH      =  ChronoUnit.MONTHS.getDuration().toNanos();       // 2629746000000000
    public final static long YEAR       =  ChronoUnit.YEARS.getDuration().toNanos();        // 31556952000000000
    public final static long DECADE     =  ChronoUnit.DECADES.getDuration().toNanos();      // 315569520000000000
    public final static long CENTURIE   =  ChronoUnit.CENTURIES.getDuration().toNanos();    // 3155695200000000000

    public final static BigInteger ONE_NANO     =  BigInteger.valueOf(NANO);                // 1
    public final static BigInteger ONE_MICRO    =  BigInteger.valueOf(MICRO);               // 1000
    public final static BigInteger ONE_MILLI    =  BigInteger.valueOf(MILLI);               // 1000000
    public final static BigInteger ONE_SECOND   =  BigInteger.valueOf(SECOND);              // 1000000000
    public final static BigInteger ONE_MINUTE   =  BigInteger.valueOf(MINUTE);              // 60000000000
    public final static BigInteger ONE_HOUR     =  BigInteger.valueOf(HOUR);                // 3600000000000
    public final static BigInteger ONE_DAY      =  BigInteger.valueOf(DAY);                 // 86400000000000
    public final static BigInteger ONE_WEEK     =  BigInteger.valueOf(WEEK);                // 604800000000000
    public final static BigInteger ONE_MONTH    =  BigInteger.valueOf(MONTH);               // 2629746000000000
    public final static BigInteger ONE_YEAR     =  BigInteger.valueOf(YEAR);                // 31556952000000000
    public final static BigInteger ONE_DECADE   =  BigInteger.valueOf(DECADE);              // 315569520000000000
    public final static BigInteger ONE_CENTURIE =  BigInteger.valueOf(CENTURIE);            // 3155695200000000000

    /*###################################################################################
     ************************************************************************************
     ------------------------------------------------------------------------------------
     ***********************              时间互转系数               **********************
     ------------------------------------------------------------------------------------
     ************************************************************************************
     ###################################################################################*/
    /**
     * 一个世纪100年
     */
    public final static long CENTURIE2YEARS = 100;
    public final static BigInteger CENTURIE_YEARS = BigInteger.valueOf(CENTURIE2YEARS);
    /**
     * 10年
     */
    public final static long DECADE2YEARS = 10;
    public final static BigInteger DECADE_YEARS = BigInteger.valueOf(DECADE2YEARS);
    /**
     * 一年平均天数：<b>365.2425天</b>。参考：<a href="https://en.wikipedia.org/wiki/Gregorian_calendar">Gregorian calendar</a>
     */
    public final static double YEAR2DAYS = 365.2425d;
    public final static BigDecimal YEAR_DAYS = BigDecimal.valueOf(YEAR2DAYS);
    /**
     * 一月平均天数：<b>30.436875</b> (365.2425/12)
     */
    public final static double MONTH2DAYS = 30.436875d;
    public final static BigDecimal MONTH_DAYS = BigDecimal.valueOf(MONTH2DAYS);
    /**
     * 一年平均周数：<b>52.1775</b> (365.2425/7)
     */
    public final static double YEAR2WEEKS = 52.1775d;
    public final static BigDecimal YEAR_WEEKS = BigDecimal.valueOf(YEAR2WEEKS);
    /**
     * 一个月平均周数：<b>4.348125</b> (30.436875/7)
     */
    public final static double MONTH2WEEKS = 4.348125d;
    public final static BigDecimal MONTH_WEEKS = BigDecimal.valueOf(MONTH2WEEKS);
    /**
     * 一年平均秒数：<b>31556952秒</b> (365.2425天 * 24 * 3600)
     */
    public final static long YEAR2SECONDS = 31556952;
    public final static BigInteger YEAR_SECONDS = BigInteger.valueOf(YEAR2SECONDS);
    /**
     * 一个月平均秒数：<b>2629746秒</b> (30.436875天 * 24 * 3600)
     */
    public final static long MONTH2SECONDS = 2629746;
    public final static BigInteger MONTH_SECONDS = BigInteger.valueOf(MONTH2SECONDS);
    /**
     * 一年12个月
     */
    public final static long YEAR2MONTHS = 12;
    public final static BigInteger YEAR_MONTHS = BigInteger.valueOf(YEAR2MONTHS);
    /**
     * 一周7天
     */
    public final static long WEEK2DAYS = 7;
    public final static BigInteger WEEK_DAYS = BigInteger.valueOf(WEEK2DAYS);
    /**
     * 一天24小时
     */
    public final static long DAY2HOURS = 24;
    public final static BigInteger DAY_HOURS = BigInteger.valueOf(DAY2HOURS);
    /**
     * 时转分，分转秒
     */
    public final static long HOUR2MINUTES = 60;
    public final static long MINUTE2SECONDS = 60;
    public final static BigInteger HOUR_MINUTES = BigInteger.valueOf(HOUR2MINUTES);
    public final static BigInteger MINUTE_SECONDS = BigInteger.valueOf(MINUTE2SECONDS);
    /**
     * 时/分互转、分/秒互转系数
     */
    public final static long X60 = 60;
    public final static BigInteger X_60 = BigInteger.valueOf(X60);
    /**
     * 秒转毫秒，毫秒转微秒，微秒转纳秒
     */
    public final static long SECOND2MILLIS = 1000;
    public final static long MILLI2MICROS = 1000;
    public final static long MICRO2NANOS = 1000;
    public final static BigInteger SECOND_MILLIS = BigInteger.valueOf(SECOND2MILLIS);
    public final static BigInteger MILLI_MICROS = BigInteger.valueOf(MILLI2MICROS);
    public final static BigInteger MICRO_NANOS = BigInteger.valueOf(MICRO2NANOS);
    /**
     * 秒/毫秒互转、毫秒/微秒互转、微秒/纳秒互转的系数
     */
    public final static long X1000 = 1000;
    public final static BigInteger X_1000 = BigInteger.valueOf(X1000);


    /*###################################################################################
     ************************************************************************************
     ------------------------------------------------------------------------------------
     ***********************                  其他                  **********************
     ------------------------------------------------------------------------------------
     ************************************************************************************
     ###################################################################################*/
    /**
     * Universal supported temporal
     */
    public static final List<Class<? extends Temporal>> SUPPORTED_TEMPORAL_COMMON =
            Collections.unmodifiableList(Arrays.asList(LocalDateTime.class, ZonedDateTime.class, OffsetDateTime.class, Instant.class));
    /**
     * Universal supported temporal string
     */
    public static final String SUPPORTED_TEMPORAL_COMMON_STRING = SUPPORTED_TEMPORAL_COMMON.stream().map(Class::getSimpleName).collect(Collectors.joining(", "));

    /**
     * hours of a day. <br>
     * {@code
     * ["00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
     * "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"]}
     */
    public static final List<String> HHs = Collections.unmodifiableList(
            IntStream.range(0, 24).boxed().map(i -> String.format("%02d", i)).collect(Collectors.toList()));

    /**
     * minutes of a hour or seconds of a minute. <br>
     * {@code
     * [
     * "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
     * "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39",
     * "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" ]}
     */
    public static final List<String> MSs = Collections.unmodifiableList(
            IntStream.range(0, 60).boxed().map(i -> String.format("%02d", i)).collect(Collectors.toList()));


}
