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

import com.iofairy.range.IntervalType;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.stream.Collectors;

import static com.iofairy.range.IntervalType.CLOSED;
import static java.time.temporal.ChronoUnit.*;
import static com.iofairy.validator.Preconditions.*;

/**
 * shift date time<br>
 * 偏移时间
 *
 * @since 0.6.0
 */
class DateTimeShift {
    /**
     * Supported units for {@code datesByShift} and {@code datesFromRange} methods.
     */
    private static final List<ChronoUnit> SUPPORTED_UNITS_FOR_DBS = Arrays.asList(YEARS, MONTHS, DAYS, HOURS, MINUTES, SECONDS);
    private static final String SUPPORTED_UNITS_FOR_DBS_STRING = SUPPORTED_UNITS_FOR_DBS.stream().map(ChronoUnit::toString).collect(Collectors.joining(", "));

    /**
     * 从给定的时间，按指定的时间单位，指定的步长偏移时间，返回每次偏移后的所有时间列表
     *
     * @param fromDate           开始的时间
     * @param zdt                时间对应的 ZonedDateTime
     * @param shiftTimes         偏移的次数，负数：则时间往前偏移；正数：则时间往后偏移
     * @param amountUnit         偏移的步长
     * @param chronoUnit         时间单位
     * @param includeCurrentTime 最后列表是否包含 fromDate
     * @return 每次偏移后的所有时间列表
     */
    public static List<Date> datesByShift(Date fromDate, ZonedDateTime zdt, int shiftTimes, int amountUnit, ChronoUnit chronoUnit, boolean includeCurrentTime) {
        checkNullNPE(chronoUnit, args("chronoUnit"));
        checkTemporal(!SUPPORTED_UNITS_FOR_DBS.contains(chronoUnit),
                "Only [${…}] is supported for `chronoUnit` parameter!", SUPPORTED_UNITS_FOR_DBS_STRING);

        List<Date> dates = new ArrayList<>();
        if (includeCurrentTime) dates.add(fromDate);

        if (shiftTimes == 0 || amountUnit == 0) return dates;

        amountUnit = shiftTimes < 0 ? -Math.abs(amountUnit) : Math.abs(amountUnit);
        long abs = Math.abs(shiftTimes);
        for (long i = 0; i < abs; i++) {
            zdt = zdt.plus(amountUnit, chronoUnit);
            dates.add(Date.from(zdt.toInstant()));
        }
        if (amountUnit < 0) Collections.reverse(dates);
        return dates;
    }

    /**
     * 从给定的时间，按指定的时间单位，指定的步长偏移时间，返回每次偏移后的所有时间列表
     *
     * @param fromCalendar       开始的时间
     * @param shiftTimes         偏移的次数，负数：则时间往前偏移；正数：则时间往后偏移
     * @param amountUnit         偏移的步长
     * @param chronoUnit         时间单位
     * @param includeCurrentTime 最后列表是否包含 fromCalendar
     * @return 每次偏移后的所有时间列表
     */
    public static List<Calendar> datesByShift(Calendar fromCalendar, int shiftTimes, int amountUnit, ChronoUnit chronoUnit, boolean includeCurrentTime) {
        checkNullNPE(chronoUnit, args("chronoUnit"));
        checkTemporal(!SUPPORTED_UNITS_FOR_DBS.contains(chronoUnit),
                "Only [${…}] is supported for `chronoUnit` parameter!", SUPPORTED_UNITS_FOR_DBS_STRING);

        Calendar calendar = DateTimes.clone(fromCalendar);
        List<Calendar> dates = new ArrayList<>();
        if (includeCurrentTime) dates.add(calendar);

        if (shiftTimes == 0 || amountUnit == 0) return dates;

        amountUnit = shiftTimes < 0 ? -Math.abs(amountUnit) : Math.abs(amountUnit);
        long abs = Math.abs(shiftTimes);
        for (long i = 0; i < abs; i++) {
            calendar = DateTimes.clone(calendar);
            addAmountToCalendar(calendar, amountUnit, chronoUnit);
            dates.add(calendar);
        }
        if (amountUnit < 0) Collections.reverse(dates);
        return dates;
    }

    /**
     * 从给定的时间，按指定的时间单位，指定的步长偏移时间，返回每次偏移后的所有时间列表
     *
     * @param fromTemporal       开始的时间。如果 fromTemporal 的类型是 Instant 时，会转成 {@link DateTimes#defaultOffset()} 时区的 OffsetDateTime 对象，再进行计算。
     * @param shiftTimes         偏移的次数，负数：则时间往前偏移；正数：则时间往后偏移
     * @param amountUnit         偏移的步长
     * @param chronoUnit         时间单位
     * @param includeCurrentTime 最后列表是否包含 fromTemporal
     * @param <T>                fromTemporal的类型
     * @return 每次偏移后的所有时间列表
     */
    @SuppressWarnings("unchecked")
    public static <T extends Temporal> List<T> datesByShift(T fromTemporal, int shiftTimes, int amountUnit, ChronoUnit chronoUnit, boolean includeCurrentTime) {
        checkNullNPE(chronoUnit, args("chronoUnit"));
        checkTemporal(!SUPPORTED_UNITS_FOR_DBS.contains(chronoUnit),
                "Only [${…}] is supported for `chronoUnit` parameter!", SUPPORTED_UNITS_FOR_DBS_STRING);

        // checkTemporal(!DTConst.SUPPORTED_TEMPORAL_COMMON.contains(fromTemporal.getClass()), "Only [${…}] is supported for `temporal` parameter!", DTConst.SUPPORTED_TEMPORAL_COMMON_STRING);


        List<T> dates = new ArrayList<>();
        if (includeCurrentTime) dates.add(fromTemporal);

        if (shiftTimes == 0 || amountUnit == 0) return dates;

        amountUnit = shiftTimes < 0 ? -Math.abs(amountUnit) : Math.abs(amountUnit);
        long abs = Math.abs(shiftTimes);
        if (fromTemporal instanceof Instant) {
            ZonedDateTime zdt = DateTime.from(fromTemporal).getZonedDateTime();
            for (long i = 0; i < abs; i++) {
                zdt = zdt.plus(amountUnit, chronoUnit);
                dates.add((T) zdt.toInstant());
            }
        } else {
            for (long i = 0; i < abs; i++) {
                fromTemporal = (T) fromTemporal.plus(amountUnit, chronoUnit);
                dates.add(fromTemporal);
            }
        }
        if (amountUnit < 0) Collections.reverse(dates);
        return dates;
    }

    /**
     * 从给定的时间范围（开始时间，结束时间），按指定的时间单位，指定的步长偏移时间，返回每次偏移后的所有时间列表
     *
     * @param fromDate     开始的时间
     * @param toDate       结束的时间
     * @param fromZdt      开始时间对应的 ZonedDateTime
     * @param toZdt        结束时间对应的 ZonedDateTime
     * @param amountUnit   偏移的步长
     * @param chronoUnit   时间单位
     * @param intervalType 区间类型
     * @return 每次偏移后的所有时间列表
     */
    public static List<Date> datesFromRange(Date fromDate, Date toDate, ZonedDateTime fromZdt, ZonedDateTime toZdt, int amountUnit, ChronoUnit chronoUnit, IntervalType intervalType) {
        checkHasNullNPE(args(toDate, chronoUnit), args("toDate", "chronoUnit"));
        checkTemporal(!SUPPORTED_UNITS_FOR_DBS.contains(chronoUnit),
                "Only [${…}] is supported for `chronoUnit` parameter!", SUPPORTED_UNITS_FOR_DBS_STRING);

        if (intervalType == null) intervalType = CLOSED;
        List<Date> dates = new ArrayList<>();
        if (intervalType.isLeftClose()) dates.add(fromDate);
        if (amountUnit == 0) return dates;

        SignedInterval signedInterval = SignedInterval.between(fromZdt, toZdt);

        long totalShift = getShiftTimes(chronoUnit, signedInterval);

        amountUnit = totalShift < 0 ? -Math.abs(amountUnit) : Math.abs(amountUnit);
        long shiftTimes = totalShift / amountUnit;                  // 偏移的次数，负数：则时间往前偏移；正数：则时间往后偏移
        boolean isExactDivision = totalShift % amountUnit == 0;     // 是否整除

        if (shiftTimes == 0) return dates;

        long maxIndex = Math.abs(shiftTimes) - 1;
        for (long i = 0; ; i++) {
            fromZdt = fromZdt.plus(amountUnit, chronoUnit);
            if (i == maxIndex) {
                if (isExactDivision) {
                    if (intervalType.isRightClose()) {
                        dates.add(Date.from(fromZdt.toInstant()));
                    }
                } else {
                    dates.add(Date.from(fromZdt.toInstant()));
                }

                break;
            }
            dates.add(Date.from(fromZdt.toInstant()));
        }
        if (amountUnit < 0) Collections.reverse(dates);
        return dates;
    }

    /**
     * 从给定的时间范围（开始时间，结束时间），按指定的时间单位，指定的步长偏移时间，返回每次偏移后的所有时间列表
     *
     * @param fromCalendar 开始的时间
     * @param toCalendar   结束的时间
     * @param amountUnit   偏移的步长
     * @param chronoUnit   时间单位
     * @param intervalType 区间类型
     * @return 每次偏移后的所有时间列表
     */
    public static List<Calendar> datesFromRange(Calendar fromCalendar, Calendar toCalendar, int amountUnit, ChronoUnit chronoUnit, IntervalType intervalType) {
        checkHasNullNPE(args(toCalendar, chronoUnit), args("toCalendar", "chronoUnit"));
        checkTemporal(!SUPPORTED_UNITS_FOR_DBS.contains(chronoUnit),
                "Only [${…}] is supported for `chronoUnit` parameter!", SUPPORTED_UNITS_FOR_DBS_STRING);

        if (intervalType == null) intervalType = CLOSED;
        Calendar calendar = DateTimes.clone(fromCalendar);
        List<Calendar> dates = new ArrayList<>();
        if (intervalType.isLeftClose()) dates.add(fromCalendar);
        if (amountUnit == 0) return dates;

        SignedInterval signedInterval = SignedInterval.between(calendar, toCalendar);

        long totalShift = getShiftTimes(chronoUnit, signedInterval);
        amountUnit = totalShift < 0 ? -Math.abs(amountUnit) : Math.abs(amountUnit);
        long shiftTimes = totalShift / amountUnit;                  // 偏移的次数，负数：则时间往前偏移；正数：则时间往后偏移
        boolean isExactDivision = totalShift % amountUnit == 0;     // 是否整除

        if (shiftTimes == 0) return dates;

        long maxIndex = Math.abs(shiftTimes) - 1;
        for (long i = 0; ; i++) {
            calendar = DateTimes.clone(calendar);
            addAmountToCalendar(calendar, amountUnit, chronoUnit);
            if (i == maxIndex) {
                if (isExactDivision) {
                    if (intervalType.isRightClose()) {
                        dates.add(calendar);
                    }
                } else {
                    dates.add(calendar);
                }

                break;
            }
            dates.add(calendar);
        }
        if (amountUnit < 0) Collections.reverse(dates);
        return dates;
    }

    /**
     * 从给定的时间范围（开始时间，结束时间），按指定的时间单位，指定的步长偏移时间，返回每次偏移后的所有时间列表
     *
     * @param fromTemporal 开始的时间。如果 fromTemporal 的类型是 Instant 时，会转成 {@link DateTimes#defaultOffset()} 时区的 OffsetDateTime 对象，再进行计算。
     * @param toTemporal   结束的时间
     * @param amountUnit   偏移的步长
     * @param chronoUnit   时间单位
     * @param intervalType 区间类型
     * @param <T>          fromTemporal的类型
     * @return 每次偏移后的所有时间列表
     */
    @SuppressWarnings("unchecked")
    public static <T extends Temporal> List<T> datesFromRange(T fromTemporal, Temporal toTemporal, int amountUnit, ChronoUnit chronoUnit, IntervalType intervalType) {
        checkHasNullNPE(args(toTemporal, chronoUnit), args("toTemporal", "chronoUnit"));
        checkTemporal(!SUPPORTED_UNITS_FOR_DBS.contains(chronoUnit),
                "Only [${…}] is supported for `chronoUnit` parameter!", SUPPORTED_UNITS_FOR_DBS_STRING);

        if (intervalType == null) intervalType = CLOSED;
        List<T> dates = new ArrayList<>();
        if (intervalType.isLeftClose()) dates.add(fromTemporal);
        if (amountUnit == 0) return dates;


        SignedInterval signedInterval = SignedInterval.between(fromTemporal, toTemporal);

        long totalShift = getShiftTimes(chronoUnit, signedInterval);

        amountUnit = totalShift < 0 ? -Math.abs(amountUnit) : Math.abs(amountUnit);
        long shiftTimes = totalShift / amountUnit;                  // 偏移的次数，负数：则时间往前偏移；正数：则时间往后偏移
        boolean isExactDivision = totalShift % amountUnit == 0;     // 是否整除

        if (shiftTimes == 0) return dates;

        long maxIndex = Math.abs(shiftTimes) - 1;
        if (fromTemporal instanceof Instant) {
            ZonedDateTime zdt = DateTime.from(fromTemporal).getZonedDateTime();
            for (long i = 0; ; i++) {
                zdt = zdt.plus(amountUnit, chronoUnit);
                if (i == maxIndex) {
                    if (isExactDivision) {
                        if (intervalType.isRightClose()) {
                            dates.add((T) zdt.toInstant());
                        }
                    } else {
                        dates.add((T) zdt.toInstant());
                    }

                    break;
                }
                dates.add((T) zdt.toInstant());
            }
        } else {
            for (long i = 0; ; i++) {
                fromTemporal = (T) fromTemporal.plus(amountUnit, chronoUnit);
                if (i == maxIndex) {
                    if (isExactDivision) {
                        if (intervalType.isRightClose()) {
                            dates.add(fromTemporal);
                        }
                    } else {
                        dates.add(fromTemporal);
                    }

                    break;
                }
                dates.add(fromTemporal);
            }
        }

        if (amountUnit < 0) Collections.reverse(dates);
        return dates;
    }

    /**
     * Adds or subtracts the specified amount of time to the given calendar field, based on the calendar's rules.
     *
     * @param calendar   calendar
     * @param amountUnit amount
     * @param chronoUnit chronoUnit
     */
    private static void addAmountToCalendar(Calendar calendar, int amountUnit, ChronoUnit chronoUnit) {
        switch (chronoUnit) {
            case YEARS:
                calendar.add(Calendar.YEAR, amountUnit);
                break;
            case MONTHS:
                calendar.add(Calendar.MONTH, amountUnit);
                break;
            case DAYS:
                calendar.add(Calendar.DAY_OF_MONTH, amountUnit);
                break;
            case HOURS:
                calendar.add(Calendar.HOUR_OF_DAY, amountUnit);
                break;
            case MINUTES:
                calendar.add(Calendar.MINUTE, amountUnit);
                break;
            default:
                calendar.add(Calendar.SECOND, amountUnit);
        }
    }

    private static long getShiftTimes(ChronoUnit chronoUnit, SignedInterval signedInterval) {
        switch (chronoUnit) {
            case YEARS:
                return signedInterval.toYears();
            case MONTHS:
                return signedInterval.toMonths();
            case DAYS:
                return signedInterval.toDays();
            case HOURS:
                return signedInterval.toHours();
            case MINUTES:
                return signedInterval.toMinutes();
            default:
                return signedInterval.toSeconds();
        }
    }
}
