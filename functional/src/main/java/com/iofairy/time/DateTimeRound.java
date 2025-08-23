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

import com.iofairy.os.OS;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.*;
import static com.iofairy.validator.Preconditions.*;

/**
 * 对时间进行取整
 *
 * @since 0.6.0
 */
class DateTimeRound {
    /**
     * Supported units for {@code round} methods.
     */
    private static final List<ChronoUnit> SUPPORTED_UNITS_FOR_ROUND = Arrays.asList(YEARS, MONTHS, DAYS, HOURS, MINUTES, SECONDS);
    private static final String SUPPORTED_UNITS_FOR_ROUND_STRING = SUPPORTED_UNITS_FOR_ROUND.stream().map(ChronoUnit::toString).collect(Collectors.joining(", "));
    /**
     * Supported units for {@code roundTime} methods.
     */
    private static final List<ChronoUnit> SUPPORTED_UNITS_FOR_ROUND_TIME = Arrays.asList(HOURS, MINUTES, SECONDS);
    private static final String SUPPORTED_UNITS_FOR_ROUND_TIME_STRING = SUPPORTED_UNITS_FOR_ROUND_TIME.stream().map(ChronoUnit::toString).collect(Collectors.joining(", "));

    /**
     * 对时间进行取整
     *
     * @param localDateTime 时间
     * @param chronoUnit    按此时间单位作为取整后的精度
     * @param roundingDT    取整类型，值为{@code null}默认为：{@link RoundingDT#FLOOR}
     * @return 取整后的时间
     */
    public static LocalDateTime round(LocalDateTime localDateTime, ChronoUnit chronoUnit, RoundingDT roundingDT) {
        // if (localDateTime == null) return localDateTime;
        checkTemporal(!SUPPORTED_UNITS_FOR_ROUND.contains(chronoUnit),
                "Only [${…}] is supported for `chronoUnit` parameter!", SUPPORTED_UNITS_FOR_ROUND_STRING);

        roundingDT = roundingDT == null ? RoundingDT.FLOOR : roundingDT;

        LocalDateTime ldt = localDateTime;
        switch (chronoUnit) {
            case YEARS:
                switch (roundingDT) {
                    case CEILING:
                        if (!(ldt.getMonth() == Month.JANUARY && ldt.getDayOfMonth() == 1 && ldt.getHour() == 0 && ldt.getMinute() == 0 && ldt.getSecond() == 0 && ldt.getNano() == 0)) {
                            ldt = ldt.plusYears(1);
                        }

                        break;
                    case HALF_UP:
                        if (ldt.getMonthValue() >= 7) {     // 7月
                            ldt = ldt.plusYears(1);
                        }
                        break;
                }
                return LocalDateTime.of(ldt.getYear(), Month.JANUARY, 1, 0, 0, 0, 0);
            case MONTHS:
                switch (roundingDT) {
                    case CEILING:
                        if (!(ldt.getDayOfMonth() == 1 && ldt.getHour() == 0 && ldt.getMinute() == 0 && ldt.getSecond() == 0 && ldt.getNano() == 0)) {
                            ldt = ldt.plusMonths(1);
                        }
                        break;
                    case HALF_UP:
                        int dayOfMonth = ldt.getDayOfMonth();    // 当前是几号
                        int halfUpDay = halfUpDay(DateTimes.daysOfMonth(ldt));
                        if (dayOfMonth >= halfUpDay) {
                            ldt = ldt.plusMonths(1);
                        }
                        break;
                }
                return LocalDateTime.of(ldt.getYear(), ldt.getMonth(), 1, 0, 0, 0, 0);
            case DAYS:
                switch (roundingDT) {
                    case CEILING:
                        if (!(ldt.getHour() == 0 && ldt.getMinute() == 0 && ldt.getSecond() == 0 && ldt.getNano() == 0)) {
                            ldt = ldt.plusDays(1);
                        }

                        break;
                    case HALF_UP:
                        if (ldt.getHour() >= 12) {
                            ldt = ldt.plusDays(1);
                        }
                        break;
                }
                return LocalDateTime.of(ldt.getYear(), ldt.getMonth(), ldt.getDayOfMonth(), 0, 0, 0, 0);
            case HOURS:
                switch (roundingDT) {
                    case CEILING:
                        if (!(ldt.getMinute() == 0 && ldt.getSecond() == 0 && ldt.getNano() == 0)) {
                            ldt = ldt.plusHours(1);
                        }

                        break;
                    case HALF_UP:
                        if (ldt.getMinute() >= 30) {
                            ldt = ldt.plusHours(1);
                        }
                        break;
                }
                return LocalDateTime.of(ldt.getYear(), ldt.getMonth(), ldt.getDayOfMonth(), ldt.getHour(), 0, 0, 0);
            case MINUTES:
                switch (roundingDT) {
                    case CEILING:
                        if (!(ldt.getSecond() == 0 && ldt.getNano() == 0)) {
                            ldt = ldt.plusMinutes(1);
                        }
                        break;
                    case HALF_UP:
                        if (ldt.getSecond() >= 30) {
                            ldt = ldt.plusMinutes(1);
                        }
                        break;
                }
                return LocalDateTime.of(ldt.getYear(), ldt.getMonth(), ldt.getDayOfMonth(), ldt.getHour(), ldt.getMinute(), 0, 0);
            default:
                switch (roundingDT) {
                    case CEILING:
                        if (!(ldt.getNano() == 0)) {
                            ldt = ldt.plusSeconds(1);
                        }
                        break;
                    case HALF_UP:
                        if (ldt.getNano() >= 500000000) {
                            ldt = ldt.plusSeconds(1);
                        }
                        break;
                }
                return LocalDateTime.of(ldt.getYear(), ldt.getMonth(), ldt.getDayOfMonth(), ldt.getHour(), ldt.getMinute(), ldt.getSecond(), 0);
        }
    }

    /**
     * 对时间进行取整
     *
     * @param temporal      时间
     * @param localDateTime localDateTime
     * @param zoneId        时区
     * @param chronoUnit    按此时间单位作为取整后的精度
     * @param roundingDT    取整类型，值为{@code null}默认为：{@link RoundingDT#FLOOR}
     * @param <T>           时间类型，若类型为 {@link Instant}，则以 {@link TZ#DEFAULT_ZONE} 时区为准，进行取整运算。
     * @return 取整后的时间
     */
    @SuppressWarnings("unchecked")
    public static <T extends Temporal> T round(T temporal, LocalDateTime localDateTime, ZoneId zoneId, ChronoUnit chronoUnit, RoundingDT roundingDT) {

        LocalDateTime ldt = round(localDateTime, chronoUnit, roundingDT);
        if (temporal instanceof ZonedDateTime) {
            return (T) ldt.atZone(zoneId);
        }
        if (temporal instanceof OffsetDateTime) {
            return (T) ldt.atOffset((ZoneOffset) zoneId);
        }
        if (temporal instanceof Instant) {
            return (T) ldt.atZone(zoneId).toInstant();
        }

        return (T) ldt;
    }

    /**
     * 对时间进行取整
     *
     * @param date       时间
     * @param chronoUnit 按此时间单位作为取整后的精度
     * @param roundingDT 取整类型，值为{@code null}默认为：{@link RoundingDT#FLOOR}
     * @return 取整后的时间
     */
    public static Date round(Date date, ChronoUnit chronoUnit, RoundingDT roundingDT) {
        // if (date == null) return date;
        return round(DateTimes.toCalendar(date, null), chronoUnit, roundingDT).getTime();
    }

    /**
     * 对时间进行取整
     *
     * @param calendar   时间
     * @param chronoUnit 按此时间单位作为取整后的精度
     * @param roundingDT 取整类型，值为{@code null}默认为：{@link RoundingDT#FLOOR}
     * @return 取整后的时间
     */
    public static Calendar round(Calendar calendar, ChronoUnit chronoUnit, RoundingDT roundingDT) {
        // if (calendar == null) return calendar;
        checkTemporal(!SUPPORTED_UNITS_FOR_ROUND.contains(chronoUnit),
                "Only [${…}] is supported for `chronoUnit` parameter!", SUPPORTED_UNITS_FOR_ROUND_STRING);

        roundingDT = roundingDT == null ? RoundingDT.FLOOR : roundingDT;

        switch (chronoUnit) {
            case YEARS:
                switch (roundingDT) {
                    case CEILING:
                        if (!(calendar.get(Calendar.MONTH) == Calendar.JANUARY && calendar.get(Calendar.DAY_OF_MONTH) == 1
                                && calendar.get(Calendar.HOUR_OF_DAY) == 0 && calendar.get(Calendar.MINUTE) == 0
                                && calendar.get(Calendar.SECOND) == 0 && calendar.get(Calendar.MILLISECOND) == 0)) {
                            calendar.add(Calendar.YEAR, 1);
                        }

                        break;
                    case HALF_UP:
                        if (calendar.get(Calendar.MONTH) >= 6) {     // 6 代表 7月
                            calendar.add(Calendar.YEAR, 1);
                        }
                        break;
                }
                calendar.set(calendar.get(Calendar.YEAR), Calendar.JANUARY, 1, 0, 0, 0);

                break;
            case MONTHS:
                switch (roundingDT) {
                    case CEILING:
                        if (!(calendar.get(Calendar.DAY_OF_MONTH) == 1 && calendar.get(Calendar.HOUR_OF_DAY) == 0
                                && calendar.get(Calendar.MINUTE) == 0 && calendar.get(Calendar.SECOND) == 0
                                && calendar.get(Calendar.MILLISECOND) == 0)) {
                            calendar.add(Calendar.MONTH, 1);
                        }
                        break;
                    case HALF_UP:
                        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);    // 当前是几号
                        int halfUpDay = halfUpDay(DateTime.from(calendar).daysOfMonth());
                        if (dayOfMonth >= halfUpDay) {
                            calendar.add(Calendar.MONTH, 1);
                        }
                        break;
                }
                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1, 0, 0, 0);

                break;
            case DAYS:
                switch (roundingDT) {
                    case CEILING:
                        if (!(calendar.get(Calendar.HOUR_OF_DAY) == 0 && calendar.get(Calendar.MINUTE) == 0
                                && calendar.get(Calendar.SECOND) == 0 && calendar.get(Calendar.MILLISECOND) == 0)) {
                            calendar.add(Calendar.DAY_OF_MONTH, 1);
                        }
                        break;
                    case HALF_UP:
                        if (calendar.get(Calendar.HOUR_OF_DAY) >= 12) {
                            calendar.add(Calendar.DAY_OF_MONTH, 1);
                        }
                        break;
                }
                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

                break;
            case HOURS:
                switch (roundingDT) {
                    case CEILING:
                        if (!(calendar.get(Calendar.MINUTE) == 0 && calendar.get(Calendar.SECOND) == 0 && calendar.get(Calendar.MILLISECOND) == 0)) {
                            calendar.add(Calendar.HOUR_OF_DAY, 1);
                        }
                        break;
                    case HALF_UP:
                        if (calendar.get(Calendar.MINUTE) >= 30) {
                            calendar.add(Calendar.HOUR_OF_DAY, 1);
                        }
                        break;
                }
                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                        calendar.get(Calendar.HOUR_OF_DAY), 0, 0);

                break;
            case MINUTES:
                switch (roundingDT) {
                    case CEILING:
                        if (!(calendar.get(Calendar.SECOND) == 0 && calendar.get(Calendar.MILLISECOND) == 0)) {
                            calendar.add(Calendar.MINUTE, 1);
                        }
                        break;
                    case HALF_UP:
                        if (calendar.get(Calendar.SECOND) >= 30) {
                            calendar.add(Calendar.MINUTE, 1);
                        }
                        break;
                }
                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                        calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), 0);
                break;
            default:
                switch (roundingDT) {
                    case CEILING:
                        if (!(calendar.get(Calendar.MILLISECOND) == 0)) {
                            calendar.add(Calendar.SECOND, 1);
                        }
                        break;
                    case HALF_UP:
                        if (calendar.get(Calendar.MILLISECOND) >= 500) {
                            calendar.add(Calendar.SECOND, 1);
                        }
                        break;
                }
                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                        calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
        }
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    /**
     * 对时间的 时，分，秒 进行取整。
     *
     * @param calendar   calendar
     * @param chronoUnit 时间取整的精度
     * @param amountUnit 时间步长
     * @param roundingDT 取整类型，值为{@code null}默认为：{@link RoundingDT#FLOOR}
     * @return 取整后的时间
     */
    public static Calendar roundTime(Calendar calendar, ChronoUnit chronoUnit, int amountUnit, RoundingDT roundingDT) {
        // if (calendar == null) return calendar;

        checkTemporal(!SUPPORTED_UNITS_FOR_ROUND_TIME.contains(chronoUnit),
                "Only [${…}] is supported for `chronoUnit` parameter!", SUPPORTED_UNITS_FOR_ROUND_TIME_STRING);

        roundingDT = roundingDT == null ? RoundingDT.FLOOR : roundingDT;

        checkValidAmountUnit(chronoUnit, amountUnit);
        final int newAmountUnit = Math.abs(amountUnit);

        if (newAmountUnit == 0) {
            switch (chronoUnit) {
                case HOURS:
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);
                    break;
                case MINUTES:
                    calendar.set(Calendar.SECOND, 0);
                    break;
            }
            calendar.set(Calendar.MILLISECOND, 0);
            return calendar;
        }


        int remainder = 0;
        int halfUpValue = halfUp(newAmountUnit);
        int amountToAdd = 0;
        switch (chronoUnit) {
            case HOURS:
                int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
                remainder = hourOfDay % newAmountUnit;
                amountToAdd = newAmountUnit - remainder;
                if (hourOfDay + amountToAdd > 24) {
                    amountToAdd = amountToAdd - (hourOfDay + amountToAdd - 24);
                }
                switch (roundingDT) {
                    case FLOOR:
                        calendar.add(Calendar.HOUR_OF_DAY, -remainder);
                        break;
                    case HALF_UP:
                        if (halfUpValue == -1) {
                            calendar.add(Calendar.HOUR_OF_DAY, -remainder);
                        } else {
                            if (remainder >= halfUpValue) {
                                calendar.add(Calendar.HOUR_OF_DAY, amountToAdd);
                            } else {
                                calendar.add(Calendar.HOUR_OF_DAY, -remainder);
                            }
                        }

                        break;
                    default:
                        if (!(remainder == 0 && calendar.get(Calendar.MINUTE) == 0 && calendar.get(Calendar.SECOND) == 0
                                && calendar.get(Calendar.MILLISECOND) == 0)) {
                            calendar.add(Calendar.HOUR_OF_DAY, amountToAdd);
                        }
                }
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);

                break;
            case MINUTES:
                int minute = calendar.get(Calendar.MINUTE);
                remainder = minute % newAmountUnit;
                amountToAdd = newAmountUnit - remainder;
                if (minute + amountToAdd > 60) {
                    amountToAdd = amountToAdd - (minute + amountToAdd - 60);
                }
                switch (roundingDT) {
                    case FLOOR:
                        calendar.add(Calendar.MINUTE, -remainder);
                        break;
                    case HALF_UP:
                        if (halfUpValue == -1) {
                            calendar.add(Calendar.MINUTE, -remainder);
                        } else {
                            if (remainder >= halfUpValue) {
                                calendar.add(Calendar.MINUTE, amountToAdd);
                            } else {
                                calendar.add(Calendar.MINUTE, -remainder);
                            }
                        }

                        break;
                    default:
                        if (!(remainder == 0 && calendar.get(Calendar.SECOND) == 0 && calendar.get(Calendar.MILLISECOND) == 0)) {
                            calendar.add(Calendar.MINUTE, amountToAdd);
                        }
                }
                calendar.set(Calendar.SECOND, 0);
                break;
            default:
                int second = calendar.get(Calendar.SECOND);
                remainder = second % newAmountUnit;
                amountToAdd = newAmountUnit - remainder;
                if (second + amountToAdd > 60) {
                    amountToAdd = amountToAdd - (second + amountToAdd - 60);
                }
                switch (roundingDT) {
                    case FLOOR:
                        calendar.add(Calendar.SECOND, -remainder);
                        break;
                    case HALF_UP:
                        if (halfUpValue == -1) {
                            calendar.add(Calendar.SECOND, -remainder);
                        } else {
                            if (remainder >= halfUpValue) {
                                calendar.add(Calendar.SECOND, amountToAdd);
                            } else {
                                calendar.add(Calendar.SECOND, -remainder);
                            }
                        }

                        break;
                    default:
                        if (!(remainder == 0 && calendar.get(Calendar.MILLISECOND) == 0)) {
                            calendar.add(Calendar.SECOND, amountToAdd);
                        }
                }
        }

        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    /**
     * 对时间的 时，分，秒 进行取整。
     *
     * @param date       date
     * @param chronoUnit 时间取整的精度
     * @param amountUnit 时间步长
     * @param roundingDT 取整类型，值为{@code null}默认为：{@link RoundingDT#FLOOR}
     * @return 取整后的时间
     */
    public static Date roundTime(Date date, ChronoUnit chronoUnit, int amountUnit, RoundingDT roundingDT) {
        // if (date == null) return date;
        return roundTime(DateTimes.toCalendar(date, null), chronoUnit, amountUnit, roundingDT).getTime();
    }

    /**
     * 对时间的 时，分，秒 进行取整。
     *
     * @param localDateTime localDateTime
     * @param chronoUnit    时间取整的精度
     * @param amountUnit    时间步长
     * @param roundingDT    取整类型，值为{@code null}默认为：{@link RoundingDT#FLOOR}
     * @return 取整后的时间
     */
    public static LocalDateTime roundTime(LocalDateTime localDateTime, ChronoUnit chronoUnit, int amountUnit, RoundingDT roundingDT) {

        checkTemporal(!SUPPORTED_UNITS_FOR_ROUND_TIME.contains(chronoUnit),
                "Only [${…}] is supported for `chronoUnit` parameter!", SUPPORTED_UNITS_FOR_ROUND_TIME_STRING);

        roundingDT = roundingDT == null ? RoundingDT.FLOOR : roundingDT;

        checkValidAmountUnit(chronoUnit, amountUnit);
        final int newAmountUnit = Math.abs(amountUnit);

        LocalDateTime ldt = localDateTime;
        if (newAmountUnit == 0) {
            switch (chronoUnit) {
                case HOURS:
                    return LocalDateTime.of(ldt.getYear(), ldt.getMonth(), ldt.getDayOfMonth(), ldt.getHour(), 0, 0, 0);
                case MINUTES:
                    return LocalDateTime.of(ldt.getYear(), ldt.getMonth(), ldt.getDayOfMonth(), ldt.getHour(), ldt.getMinute(), 0, 0);
                default:
                    return LocalDateTime.of(ldt.getYear(), ldt.getMonth(), ldt.getDayOfMonth(), ldt.getHour(), ldt.getMinute(), ldt.getSecond(), 0);
            }
        }

        int remainder = 0;
        int halfUpValue = halfUp(newAmountUnit);
        int amountToAdd = 0;
        switch (chronoUnit) {
            case HOURS:
                int hourOfDay = ldt.getHour();
                remainder = hourOfDay % newAmountUnit;
                amountToAdd = newAmountUnit - remainder;
                if (hourOfDay + amountToAdd > 24) {
                    amountToAdd = amountToAdd - (hourOfDay + amountToAdd - 24);
                }
                switch (roundingDT) {
                    case FLOOR:
                        ldt = ldt.plusHours(-remainder);
                        break;
                    case HALF_UP:
                        if (halfUpValue == -1) {
                            ldt = ldt.plusHours(-remainder);
                        } else {
                            if (remainder >= halfUpValue) {
                                ldt = ldt.plusHours(amountToAdd);
                            } else {
                                ldt = ldt.plusHours(-remainder);
                            }
                        }
                        break;
                    default:
                        if (!(remainder == 0 && ldt.getMinute() == 0 && ldt.getSecond() == 0 && ldt.getNano() == 0)) {
                            ldt = ldt.plusHours(amountToAdd);
                        }

                }
                return LocalDateTime.of(ldt.getYear(), ldt.getMonth(), ldt.getDayOfMonth(), ldt.getHour(), 0, 0, 0);
            case MINUTES:
                int minute = ldt.getMinute();
                remainder = minute % newAmountUnit;
                amountToAdd = newAmountUnit - remainder;
                if (minute + amountToAdd > 60) {
                    amountToAdd = amountToAdd - (minute + amountToAdd - 60);
                }
                switch (roundingDT) {
                    case FLOOR:
                        ldt = ldt.plusMinutes(-remainder);
                        break;
                    case HALF_UP:
                        if (halfUpValue == -1) {
                            ldt = ldt.plusMinutes(-remainder);
                        } else {
                            if (remainder >= halfUpValue) {
                                ldt = ldt.plusMinutes(amountToAdd);
                            } else {
                                ldt = ldt.plusMinutes(-remainder);
                            }
                        }
                        break;
                    default:
                        if (!(remainder == 0 && ldt.getSecond() == 0 && ldt.getNano() == 0)) {
                            ldt = ldt.plusMinutes(amountToAdd);
                        }
                }
                return LocalDateTime.of(ldt.getYear(), ldt.getMonth(), ldt.getDayOfMonth(), ldt.getHour(), ldt.getMinute(), 0, 0);
            default:
                int second = ldt.getSecond();
                remainder = second % newAmountUnit;
                amountToAdd = newAmountUnit - remainder;
                if (second + amountToAdd > 60) {
                    amountToAdd = amountToAdd - (second + amountToAdd - 60);
                }
                switch (roundingDT) {
                    case FLOOR:
                        ldt = ldt.plusSeconds(-remainder);
                        break;
                    case HALF_UP:
                        if (halfUpValue == -1) {
                            ldt = ldt.plusSeconds(-remainder);
                        } else {
                            if (remainder >= halfUpValue) {
                                ldt = ldt.plusSeconds(amountToAdd);
                            } else {
                                ldt = ldt.plusSeconds(-remainder);
                            }
                        }
                        break;
                    default:
                        if (!(remainder == 0 && ldt.getNano() == 0)) {
                            ldt = ldt.plusSeconds(amountToAdd);
                        }
                }
                return LocalDateTime.of(ldt.getYear(), ldt.getMonth(), ldt.getDayOfMonth(), ldt.getHour(), ldt.getMinute(), ldt.getSecond(), 0);
        }
    }

    /**
     * 对时间进行取整
     *
     * @param temporal      时间
     * @param localDateTime localDateTime
     * @param zoneId        时区
     * @param chronoUnit    按此时间单位作为取整后的精度
     * @param amountUnit    时间步长
     * @param roundingDT    取整类型，值为{@code null}默认为：{@link RoundingDT#FLOOR}
     * @param <T>           时间类型，若类型为 {@link Instant}，则以 {@link TZ#DEFAULT_ZONE} 时区为准，进行取整运算。
     * @return 取整后的时间
     */
    @SuppressWarnings("unchecked")
    public static <T extends Temporal> T roundTime(T temporal, LocalDateTime localDateTime, ZoneId zoneId, ChronoUnit chronoUnit, int amountUnit, RoundingDT roundingDT) {

        LocalDateTime ldt = roundTime(localDateTime, chronoUnit, amountUnit, roundingDT);
        if (temporal instanceof ZonedDateTime) {
            return (T) ldt.atZone(zoneId);
        }
        if (temporal instanceof OffsetDateTime) {
            return (T) ldt.atOffset((ZoneOffset) zoneId);
        }
        if (temporal instanceof Instant) {
            return (T) ldt.atZone(zoneId).toInstant();
        }

        return (T) ldt;
    }

    /**
     * 根据指定的 ChronoUnit 校验 amountUnit 的值是否合法
     *
     * @param chronoUnit 时间单位
     * @param amountUnit 时间步长
     */
    private static void checkValidAmountUnit(ChronoUnit chronoUnit, int amountUnit) {
        if (chronoUnit == HOURS) {
            checkOutOfBounds(amountUnit < -24 || amountUnit > 24, amountUnit, OS.IS_ZH_LANG ? "当`chronoUnit`为`HOURS`时，参数`amountUnit`的取值范围为：[-24, 24]。" : "The `amountUnit`'s range is [-24, 24] when `chronoUnit` is `HOURS`.");
        } else {
            checkOutOfBounds(amountUnit < -60 || amountUnit > 60, amountUnit, OS.IS_ZH_LANG ? "当`chronoUnit`为`MINUTES`或`SECONDS`时，参数`amountUnit`的取值范围为：[-60, 60]。" : "The `amountUnit`'s range is [-60, 60] when `chronoUnit` is `MINUTES` or `SECONDS`.");
        }
    }

    /**
     * 获取需要向上取整的最小天数
     *
     * @param daysOfMonth 当前月份的总天数
     * @return 需要向上取整的最小天数
     */
    private static int halfUpDay(int daysOfMonth) {
        return daysOfMonth == 28 || daysOfMonth == 29 ? 15 : 16;
    }

    /**
     * 获取需要向上取整的最小值
     *
     * @param value 待计算的值
     * @return 需要向上取整的最小值（-1，代表不支持四舍五入，则采用 向下取整）
     */
    private static int halfUp(int value) {
        int quotient = value / 2;
        int remainder = value % 2;
        return value <= 2 ? -1 : (remainder == 0 ? quotient : quotient + 1);
    }

}
