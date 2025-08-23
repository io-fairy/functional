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
import java.time.temporal.*;
import java.util.*;

import static java.time.temporal.ChronoUnit.*;
import static com.iofairy.validator.Preconditions.*;

/**
 * Interval
 *
 * @since 0.6.0
 */
public class Interval extends SignedInterval {
    private static final long serialVersionUID = 700655886057265L;

    public static final Interval ZERO = new Interval(0, 0, 0);

    public Interval(long centuries, long years, long months, long days, long hours, long minutes, long seconds, long millis, long micros, long nanos) {
        super(centuries, years, months, days, hours, minutes, seconds, millis, micros, nanos);

        boolean hasParamsLessThan0 = this.centuries < 0 || this.years < 0 || this.months < 0 || this.days < 0 || this.hours < 0
                || this.minutes < 0 || this.seconds < 0 || this.millis < 0 || this.micros < 0 || this.nanos < 0;

        checkArgument(hasParamsLessThan0, OS.IS_ZH_LANG ? "Interval构造函数的所有参数都必须 ≥ 0! " : "Interval Constructor all parameters must not be negative number! ");
    }

    public Interval(long centuries, long years, long months, long days, long hours, long minutes, long seconds, long millis) {
        this(centuries, years, months, days, hours, minutes, seconds, millis, 0, 0);
    }

    public Interval(long years, long months, long days, long hours, long minutes, long seconds, long millis) {
        this(0, years, months, days, hours, minutes, seconds, millis, 0, 0);
    }

    public Interval(long years, long months, long days, long hours, long minutes, long seconds) {
        this(0, years, months, days, hours, minutes, seconds, 0, 0, 0);
    }

    public Interval(long years, long months, long days, long hours, long minutes) {
        this(0, years, months, days, hours, minutes, 0, 0, 0, 0);
    }

    public Interval(long months, long days, long hours, long minutes) {
        this(0, 0, months, days, hours, minutes, 0, 0, 0, 0);
    }

    public Interval(long days, long hours, long minutes) {
        this(0, 0, 0, days, hours, minutes, 0, 0, 0, 0);
    }

    public static Interval of(long centuries, long years, long months, long days, long hours, long minutes, long seconds, long millis, long micros, long nanos) {
        return new Interval(centuries, years, months, days, hours, minutes, seconds, millis, micros, nanos);
    }

    public static Interval of(long centuries, long years, long months, long days, long hours, long minutes, long seconds, long millis) {
        return new Interval(centuries, years, months, days, hours, minutes, seconds, millis, 0, 0);
    }

    public static Interval of(long years, long months, long days, long hours, long minutes, long seconds, long millis) {
        return new Interval(0, years, months, days, hours, minutes, seconds, millis, 0, 0);
    }

    public static Interval of(long years, long months, long days, long hours, long minutes, long seconds) {
        return new Interval(0, years, months, days, hours, minutes, seconds, 0, 0, 0);
    }

    public static Interval of(long years, long months, long days, long hours, long minutes) {
        return new Interval(0, years, months, days, hours, minutes, 0, 0, 0, 0);
    }

    public static Interval of(long months, long days, long hours, long minutes) {
        return new Interval(0, 0, months, days, hours, minutes, 0, 0, 0, 0);
    }

    public static Interval of(long days, long hours, long minutes) {
        return new Interval(0, 0, 0, days, hours, minutes, 0, 0, 0, 0);
    }

    public static Interval of(long amount, TemporalUnit unit) {
        return ZERO.plus(amount, unit);
    }

    public static Interval ofCenturies(long centuries) {
        return ZERO.plus(centuries, CENTURIES);
    }

    public static Interval ofYears(long years) {
        return ZERO.plus(years, YEARS);
    }

    public static Interval ofMonths(long months) {
        return ZERO.plus(months, MONTHS);
    }

    public static Interval ofDays(long days) {
        return ZERO.plus(days, DAYS);
    }

    public static Interval ofHours(long hours) {
        return ZERO.plus(hours, HOURS);
    }

    public static Interval ofMinutes(long minutes) {
        return ZERO.plus(minutes, MINUTES);
    }

    public static Interval ofSeconds(long seconds) {
        return ZERO.plus(seconds, SECONDS);
    }

    public static Interval ofMillis(long millis) {
        return ZERO.plus(millis, MILLIS);
    }

    public static Interval ofMicros(long micros) {
        return ZERO.plus(micros, MICROS);
    }

    public static Interval ofNanos(long nanos) {
        return ZERO.plus(nanos, NANOS);
    }

    public Interval plus(Interval interval) {
        long centuries = this.centuries + interval.centuries;
        long years = this.years + interval.years;
        long months = this.months + interval.months;
        long days = this.days + interval.days;
        long hours = this.hours + interval.hours;
        long minutes = this.minutes + interval.minutes;
        long seconds = this.seconds + interval.seconds;
        long millis = this.millis + interval.millis;
        long micros = this.micros + interval.micros;
        long nanos = this.nanos + interval.nanos;
        return new Interval(centuries, years, months, days, hours, minutes, seconds, millis, micros, nanos);
    }

    public Interval plus(long amount, TemporalUnit unit) {
        if (unit instanceof ChronoUnit) {
            long centuries = this.centuries;
            long years = this.years;
            long months = this.months;
            long days = this.days;
            long hours = this.hours;
            long minutes = this.minutes;
            long seconds = this.seconds;
            long millis = this.millis;
            long micros = this.micros;
            long nanos = this.nanos;

            long maxValueLimit = 100000000000000000L;

            ChronoUnit chronoUnit = (ChronoUnit) unit;
            switch (chronoUnit) {
                case CENTURIES:
                    centuries += amount;
                    break;
                case YEARS:
                    years += amount;
                    break;
                case MONTHS:
                    months += amount;
                    break;
                case DAYS:
                    days += amount;
                    break;
                case HOURS:
                    hours += amount;
                    break;
                case MINUTES:
                    minutes += amount;
                    break;
                case SECONDS:
                    seconds += amount;
                    break;
                case MILLIS:
                    // In most cases, numerical overflow can be avoided.
                    if (Math.abs(amount) > maxValueLimit) {
                        long numberOfHours = amount / 3600000L;
                        long remainingMillis = amount % 3600000L;

                        hours += numberOfHours;
                        millis += remainingMillis;
                    } else {
                        millis += amount;
                    }

                    break;
                case MICROS:
                    // In most cases, numerical overflow can be avoided.
                    if (Math.abs(amount) > maxValueLimit) {
                        long numberOfHours = amount / 3600000000L;
                        long remainingMicros = amount % 3600000000L;

                        hours += numberOfHours;
                        micros += remainingMicros;
                    } else {
                        micros += amount;
                    }

                    break;
                case NANOS:
                    // In most cases, numerical overflow can be avoided.
                    if (Math.abs(amount) > maxValueLimit) {
                        long numberOfHours = amount / 3600000000000L;
                        long remainingNanos = amount % 3600000000000L;

                        hours += numberOfHours;
                        nanos += remainingNanos;
                    } else {
                        nanos += amount;
                    }

                    break;
            }
            return new Interval(centuries, years, months, days, hours, minutes, seconds, millis, micros, nanos);
        }
        throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
    }

    public Interval minus(Interval interval) {
        long years = (this.centuries * 100 + this.years) - (interval.centuries * 100 + interval.years);
        long months = this.months - interval.months;
        long days = this.days - interval.days;
        long hours = this.hours - interval.hours;
        long minutes = this.minutes - interval.minutes;
        long seconds = this.seconds - interval.seconds;
        long millis = this.millis - interval.millis;
        long micros = this.micros - interval.micros;
        long nanos = this.nanos - interval.nanos;

        return new Interval(centuries, years, months, days, hours, minutes, seconds, millis, micros, nanos);
    }

    public Interval minus(long amount, TemporalUnit unit) {
        if (unit instanceof ChronoUnit) {
            long centuries = this.centuries;
            long years = this.years;
            long months = this.months;
            long days = this.days;
            long hours = this.hours;
            long minutes = this.minutes;
            long seconds = this.seconds;
            long millis = this.millis;
            long micros = this.micros;
            long nanos = this.nanos;

            long maxValueLimit = 100000000000000000L;

            ChronoUnit chronoUnit = (ChronoUnit) unit;
            switch (chronoUnit) {
                case CENTURIES:
                    centuries -= amount;
                    break;
                case YEARS:
                    years -= amount;
                    break;
                case MONTHS:
                    months -= amount;
                    break;
                case DAYS:
                    days -= amount;
                    break;
                case HOURS:
                    hours -= amount;
                    break;
                case MINUTES:
                    minutes -= amount;
                    break;
                case SECONDS:
                    seconds -= amount;
                    break;
                case MILLIS:
                    // In most cases, numerical overflow can be avoided.
                    if (Math.abs(amount) > maxValueLimit) {
                        long numberOfHours = amount / 3600000L;
                        long remainingMillis = amount % 3600000L;

                        hours -= numberOfHours;
                        millis -= remainingMillis;
                    } else {
                        millis -= amount;
                    }

                    break;
                case MICROS:
                    // In most cases, numerical overflow can be avoided.
                    if (Math.abs(amount) > maxValueLimit) {
                        long numberOfHours = amount / 3600000000L;
                        long remainingMicros = amount % 3600000000L;

                        hours -= numberOfHours;
                        micros -= remainingMicros;
                    } else {
                        micros -= amount;
                    }

                    break;
                case NANOS:
                    // In most cases, numerical overflow can be avoided.
                    if (Math.abs(amount) > maxValueLimit) {
                        long numberOfHours = amount / 3600000000000L;
                        long remainingNanos = amount % 3600000000000L;

                        hours -= numberOfHours;
                        nanos -= remainingNanos;
                    } else {
                        nanos -= amount;
                    }

                    break;
            }
            return new Interval(centuries, years, months, days, hours, minutes, seconds, millis, micros, nanos);
        }
        throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
    }

    /**
     * Obtain two {@link Temporal} datetime interval. It is recommended to call {@link #isSupported(Temporal)}
     * to check if the specified temporal is supported first.<br>
     * 获取两个{@link Temporal}的时间间隔。建议在调用此方法之前先确认是否支持该时间类型 {@link #isSupported(Temporal)}。
     *
     * @param startTemporal start Temporal
     * @param endTemporal   end Temporal
     * @return Interval
     */
    public static Interval between(Temporal startTemporal, Temporal endTemporal) {
        checkHasNullNPE(args(startTemporal, endTemporal), args("startTemporal", "endTemporal"));
        checkTemporal(!isSupported(startTemporal) || !isSupported(endTemporal),
                "Only [${…}] is supported for `startTemporal` and `endTemporal` parameters!", SUPPORTED_TEMPORAL_STRING);

        DateTime startDT = DateTime.from(startTemporal);
        DateTime endDT = DateTime.from(endTemporal);

        boolean isBefore = startDT.isBefore(endDT);
        Temporal tmpStartTemporal = isBefore ? startTemporal : endTemporal;
        Temporal tmpEndTemporal = isBefore ? endTemporal : startTemporal;

        SignedInterval signedInterval = SignedInterval.between(tmpStartTemporal, tmpEndTemporal);
        Interval interval = new Interval(signedInterval.centuries, signedInterval.years, signedInterval.months, signedInterval.days, signedInterval.hours,
                signedInterval.minutes, signedInterval.seconds, signedInterval.millis, signedInterval.micros, signedInterval.nanos);
        interval.startTime = tmpStartTemporal;
        interval.endTime = tmpEndTemporal;
        interval.totalYears = signedInterval.totalYears;
        interval.totalMonths = signedInterval.totalMonths;
        interval.totalWeeks = signedInterval.totalWeeks;
        interval.totalDays = signedInterval.totalDays;
        interval.totalHours = signedInterval.totalHours;
        interval.totalMinutes = signedInterval.totalMinutes;
        interval.totalSeconds = signedInterval.totalSeconds;
        interval.totalMillis = signedInterval.totalMillis;
        interval.totalMicros = signedInterval.totalMicros;
        interval.totalNanos = signedInterval.totalNanos;
        return interval;
    }

    /**
     * Obtain two {@link Date} datetime interval. <br>
     * 获取两个{@link Date}的时间间隔。
     *
     * @param startDate start Date
     * @param endDate   end Date
     * @return Interval
     */
    public static Interval between(Date startDate, Date endDate) {
        checkHasNullNPE(args(startDate, endDate), args("startDate", "endDate"));
        return between(DateTime.from(startDate).getZonedDateTime(), DateTime.from(endDate).getZonedDateTime());
    }

    /**
     * Obtain two {@link Calendar} datetime interval. <br>
     * 获取两个{@link Calendar}的时间间隔。
     *
     * @param startCalendar start Calendar
     * @param endCalendar   end Calendar
     * @return Interval
     */
    public static Interval between(Calendar startCalendar, Calendar endCalendar) {
        checkHasNullNPE(args(startCalendar, endCalendar), args("startCalendar", "endCalendar"));
        return between(DateTime.from(startCalendar).getZonedDateTime(), DateTime.from(endCalendar).getZonedDateTime());
    }


    @Override
    public Temporal subtractFrom(Temporal temporal) {
        checkNullNPE(temporal, args("temporal"));
        checkTemporal(!isSupported(temporal), "Only [${…}] is supported for `temporal` parameter!", SUPPORTED_TEMPORAL_STRING);

        boolean isInstant = temporal instanceof Instant;
        temporal = isInstant ? ZonedDateTime.ofInstant((Instant) temporal, TZ.DEFAULT_ZONE) : temporal;

        temporal = minus(temporal, nanos, NANOS);
        temporal = minus(temporal, micros, MICROS);
        temporal = minus(temporal, millis, MILLIS);
        temporal = minus(temporal, seconds, SECONDS);
        temporal = minus(temporal, minutes, MINUTES);
        temporal = minus(temporal, hours, HOURS);
        temporal = minus(temporal, days, DAYS);
        temporal = minus(temporal, months, MONTHS);
        temporal = minus(temporal, centuries * 100 + years, YEARS);
        return isInstant ? ((ZonedDateTime) temporal).toInstant() : temporal;
    }

    /**
     * 计算近似值
     *
     * @return 当前对象
     */
    @Override
    public Interval calculateApprox() {
        return (Interval) super.calculateApprox();
    }

}
