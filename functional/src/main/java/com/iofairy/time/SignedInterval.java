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
import com.iofairy.top.O;
import com.iofairy.tcf.Try;
import com.iofairy.top.G;
import com.iofairy.top.S;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.*;
import java.time.temporal.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.*;
import static com.iofairy.validator.Preconditions.*;

/**
 * SignedInterval
 *
 * @since 0.6.0
 */
public class SignedInterval implements ChronoInterval, Comparable<SignedInterval> {
    private static final long serialVersionUID = 7006599586057265L;

    /**
     * 世纪（100年）
     *
     * @see ChronoUnit#CENTURIES
     */
    public final long centuries;
    /**
     * 年
     *
     * @see ChronoUnit#YEARS
     */
    public final long years;
    /**
     * 月
     *
     * @see ChronoUnit#MONTHS
     */
    public final long months;
    /**
     * 日
     *
     * @see ChronoUnit#DAYS
     */
    public final long days;
    /**
     * 时
     *
     * @see ChronoUnit#HOURS
     */
    public final long hours;
    /**
     * 分
     *
     * @see ChronoUnit#MINUTES
     */
    public final long minutes;
    /**
     * 秒
     *
     * @see ChronoUnit#SECONDS
     */
    public final long seconds;
    /**
     * 毫秒
     *
     * @see ChronoUnit#MILLIS
     */
    public final long millis;
    /**
     * 微秒
     *
     * @see ChronoUnit#MICROS
     */
    public final long micros;
    /**
     * 纳秒
     *
     * @see ChronoUnit#NANOS
     */
    public final long nanos;

    /*
     * 换算成每个时间单位的总时间
     */
    protected long totalYears;          // 总年数
    protected long totalMonths;         // 总月数
    protected long totalWeeks;          // 总周数
    protected long totalDays;           // 总天数
    protected long totalHours;          // 总小时数
    protected long totalMinutes;        // 总分钟数
    protected long totalSeconds;        // 总秒数
    protected long totalMillis;         // 总毫秒数
    protected BigInteger totalMicros;   // 总微秒数
    protected BigInteger totalNanos;    // 总纳秒数

    /**
     * 换算成每个时间单位的总时间（此为近似值）
     */
    protected long approxYears, approxMonths, approxWeeks, approxDays, approxHours, approxMinutes, approxSeconds, approxMillis;
    protected BigInteger approxMicros, approxNanos;
    /**
     * 是否计算已经计算近似值
     */
    protected boolean calculateApprox = false;

    /**
     * 开始时间、结束时间
     */
    protected Temporal startTime, endTime;

    public static final SignedInterval ZERO = new SignedInterval(0, 0, 0);

    private static final List<TemporalUnit> SUPPORTED_UNITS =
            Collections.unmodifiableList(Arrays.<TemporalUnit>asList(CENTURIES, YEARS, MONTHS, DAYS, HOURS, MINUTES, SECONDS, MILLIS, MICROS, NANOS));
    /**
     * {@link #between(Temporal, Temporal)}支持的temporal类型
     */
    public static final List<Class<? extends Temporal>> SUPPORTED_TEMPORAL =
            Collections.unmodifiableList(Arrays.asList(ZonedDateTime.class, OffsetDateTime.class, LocalDateTime.class, Instant.class, DateTime.class));
    protected static final String SUPPORTED_TEMPORAL_STRING = SUPPORTED_TEMPORAL.stream().map(Class::getSimpleName).collect(Collectors.joining(", "));

    /**
     * Interval format Pattern
     */
    protected static class IntervalPattern {
        public static final Pattern c   = Pattern.compile("c(?!'(?<='c'))");
        public static final Pattern y   = Pattern.compile("y(?!'(?<='y'))");
        public static final Pattern M   = Pattern.compile("M(?!'(?<='M'))");
        public static final Pattern d   = Pattern.compile("d(?!'(?<='d'))");
        public static final Pattern H   = Pattern.compile("H(?!'(?<='H'))");
        public static final Pattern m   = Pattern.compile("m(?!'(?<='m'))");
        public static final Pattern s   = Pattern.compile("s(?!'(?<='s'))");
        public static final Pattern S   = Pattern.compile("S(?!'(?<='S'))");
        public static final Pattern QcQ = Pattern.compile("'c'");
        public static final Pattern QyQ = Pattern.compile("'y'");
        public static final Pattern QMQ = Pattern.compile("'M'");
        public static final Pattern QdQ = Pattern.compile("'d'");
        public static final Pattern QHQ = Pattern.compile("'H'");
        public static final Pattern QmQ = Pattern.compile("'m'");
        public static final Pattern QsQ = Pattern.compile("'s'");
        public static final Pattern QSQ = Pattern.compile("'S'");
        public static final Pattern totalPattern = Pattern.compile("(t[cyMdHmsS])(\\d*)");
    }


    public SignedInterval(long centuries, long years, long months, long days, long hours, long minutes, long seconds, long millis, long micros, long nanos) {
        this.centuries = centuries;
        this.years = years;
        this.months = months;
        this.days = days;
        // 这里一定要将 hours 也加入计算，否则可能正数的结果，因为没有 hours 的参与，得到负数的结果
        BigInteger totalNanos = toNanos(hours, HOURS).add(toNanos(minutes, MINUTES)).add(toNanos(seconds, SECONDS)).add(toNanos(millis, MILLIS)).add(toNanos(micros, MICROS)).add(toNanos(nanos, NANOS));
        long[] timeValues = standardizingTime(totalNanos);
        this.hours = timeValues[0];
        this.minutes = timeValues[1];
        this.seconds = timeValues[2];
        this.millis = timeValues[3];
        this.micros = timeValues[4];
        this.nanos = timeValues[5];
    }

    public SignedInterval(long centuries, long years, long months, long days, long hours, long minutes, long seconds, long millis) {
        this(centuries, years, months, days, hours, minutes, seconds, millis, 0, 0);
    }

    public SignedInterval(long years, long months, long days, long hours, long minutes, long seconds, long millis) {
        this(0, years, months, days, hours, minutes, seconds, millis, 0, 0);
    }

    public SignedInterval(long years, long months, long days, long hours, long minutes, long seconds) {
        this(0, years, months, days, hours, minutes, seconds, 0, 0, 0);
    }

    public SignedInterval(long years, long months, long days, long hours, long minutes) {
        this(0, years, months, days, hours, minutes, 0, 0, 0, 0);
    }

    public SignedInterval(long months, long days, long hours, long minutes) {
        this(0, 0, months, days, hours, minutes, 0, 0, 0, 0);
    }

    public SignedInterval(long days, long hours, long minutes) {
        this(0, 0, 0, days, hours, minutes, 0, 0, 0, 0);
    }

    public static SignedInterval of(long centuries, long years, long months, long days, long hours, long minutes, long seconds, long millis, long micros, long nanos) {
        return new SignedInterval(centuries, years, months, days, hours, minutes, seconds, millis, micros, nanos);
    }

    public static SignedInterval of(long centuries, long years, long months, long days, long hours, long minutes, long seconds, long millis) {
        return new SignedInterval(centuries, years, months, days, hours, minutes, seconds, millis, 0, 0);
    }

    public static SignedInterval of(long years, long months, long days, long hours, long minutes, long seconds, long millis) {
        return new SignedInterval(0, years, months, days, hours, minutes, seconds, millis, 0, 0);
    }

    public static SignedInterval of(long years, long months, long days, long hours, long minutes, long seconds) {
        return new SignedInterval(0, years, months, days, hours, minutes, seconds, 0, 0, 0);
    }

    public static SignedInterval of(long years, long months, long days, long hours, long minutes) {
        return new SignedInterval(0, years, months, days, hours, minutes, 0, 0, 0, 0);
    }

    public static SignedInterval of(long months, long days, long hours, long minutes) {
        return new SignedInterval(0, 0, months, days, hours, minutes, 0, 0, 0, 0);
    }

    public static SignedInterval of(long days, long hours, long minutes) {
        return new SignedInterval(0, 0, 0, days, hours, minutes, 0, 0, 0, 0);
    }

    public static SignedInterval of(long amount, TemporalUnit unit) {
        return ZERO.plus(amount, unit);
    }

    public static SignedInterval ofCenturies(long centuries) {
        return ZERO.plus(centuries, CENTURIES);
    }

    public static SignedInterval ofYears(long years) {
        return ZERO.plus(years, YEARS);
    }

    public static SignedInterval ofMonths(long months) {
        return ZERO.plus(months, MONTHS);
    }

    public static SignedInterval ofDays(long days) {
        return ZERO.plus(days, DAYS);
    }

    public static SignedInterval ofHours(long hours) {
        return ZERO.plus(hours, HOURS);
    }

    public static SignedInterval ofMinutes(long minutes) {
        return ZERO.plus(minutes, MINUTES);
    }

    public static SignedInterval ofSeconds(long seconds) {
        return ZERO.plus(seconds, SECONDS);
    }

    public static SignedInterval ofMillis(long millis) {
        return ZERO.plus(millis, MILLIS);
    }

    public static SignedInterval ofMicros(long micros) {
        return ZERO.plus(micros, MICROS);
    }

    public static SignedInterval ofNanos(long nanos) {
        return ZERO.plus(nanos, NANOS);
    }

    public SignedInterval plus(SignedInterval signedInterval) {
        long centuries  = this.centuries + signedInterval.centuries;
        long years      = this.years + signedInterval.years;
        long months     = this.months + signedInterval.months;
        long days       = this.days + signedInterval.days;
        long hours      = this.hours + signedInterval.hours;
        long minutes    = this.minutes + signedInterval.minutes;
        long seconds    = this.seconds + signedInterval.seconds;
        long millis     = this.millis + signedInterval.millis;
        long micros     = this.micros + signedInterval.micros;
        long nanos      = this.nanos + signedInterval.nanos;
        return new SignedInterval(centuries, years, months, days, hours, minutes, seconds, millis, micros, nanos);
    }

    public SignedInterval plus(long amount, TemporalUnit unit) {
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
            return new SignedInterval(centuries, years, months, days, hours, minutes, seconds, millis, micros, nanos);
        }
        throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
    }

    public SignedInterval minus(SignedInterval signedInterval) {
        long centuries  = this.centuries - signedInterval.centuries;
        long years      = this.years - signedInterval.years;
        long months     = this.months - signedInterval.months;
        long days       = this.days - signedInterval.days;
        long hours      = this.hours - signedInterval.hours;
        long minutes    = this.minutes - signedInterval.minutes;
        long seconds    = this.seconds - signedInterval.seconds;
        long millis     = this.millis - signedInterval.millis;
        long micros     = this.micros - signedInterval.micros;
        long nanos      = this.nanos - signedInterval.nanos;
        return new SignedInterval(centuries, years, months, days, hours, minutes, seconds, millis, micros, nanos);
    }

    public SignedInterval minus(long amount, TemporalUnit unit) {
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
            return new SignedInterval(centuries, years, months, days, hours, minutes, seconds, millis, micros, nanos);
        }
        throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
    }

    /**
     * Obtain two {@link Temporal} datetime interval. It is recommended to call {@link #isSupported(Temporal)}
     * to check if the specified temporal is supported first.<br>
     * 获取两个{@link Temporal}的时间间隔。建议在调用此方法之前先确认是否支持该时间类型 {@link #isSupported(Temporal)}。
     *
     * @param startTemporal start Temporal. If {@code startTemporal} is instance of Instant, it will convert to {@code ZonedDateTime} with {@link TZ#DEFAULT_ZONE}
     * @param endTemporal   end Temporal. If {@code endTemporal} is instance of Instant, it will convert to {@code ZonedDateTime} with {@link TZ#DEFAULT_ZONE}
     * @return SignedInterval
     */
    public static SignedInterval between(Temporal startTemporal, Temporal endTemporal) {
        checkHasNullNPE(args(startTemporal, endTemporal), args("startTemporal", "endTemporal"));
        checkTemporal(!isSupported(startTemporal) || !isSupported(endTemporal),
                "Only [${…}] is supported for `startTemporal` and `endTemporal` parameters!", SUPPORTED_TEMPORAL_STRING);

        /*
         * 保存最初始的时间
         */
        Temporal originalStartTemporal = startTemporal;
        Temporal originalEndTemporal = endTemporal;


        startTemporal = DateTime.from(startTemporal);
        endTemporal = DateTime.from(endTemporal);


        long totalYears = startTemporal.until(endTemporal, YEARS);
        long totalMonths = startTemporal.until(endTemporal, MONTHS);
        long totalWeeks = startTemporal.until(endTemporal, WEEKS);
        long totalDays = startTemporal.until(endTemporal, DAYS);
        long totalHours = startTemporal.until(endTemporal, HOURS);
        long totalMinutes = startTemporal.until(endTemporal, MINUTES);
        long totalSeconds = startTemporal.until(endTemporal, SECONDS);
        long totalMillis = startTemporal.until(endTemporal, MILLIS);

        long centuries = startTemporal.until(endTemporal, CENTURIES);
        startTemporal = startTemporal.plus(centuries, CENTURIES);

        long years = startTemporal.until(endTemporal, YEARS);
        startTemporal = startTemporal.plus(years, YEARS);

        long months = startTemporal.until(endTemporal, MONTHS);
        startTemporal = startTemporal.plus(months, MONTHS);

        long days = startTemporal.until(endTemporal, DAYS);
        startTemporal = startTemporal.plus(days, DAYS);

        long hours = startTemporal.until(endTemporal, HOURS);
        startTemporal = startTemporal.plus(hours, HOURS);

        long minutes = startTemporal.until(endTemporal, MINUTES);
        startTemporal = startTemporal.plus(minutes, MINUTES);

        long seconds = startTemporal.until(endTemporal, SECONDS);
        startTemporal = startTemporal.plus(seconds, SECONDS);

        long millis = startTemporal.until(endTemporal, MILLIS);
        startTemporal = startTemporal.plus(millis, MILLIS);

        long micros = startTemporal.until(endTemporal, MICROS);
        startTemporal = startTemporal.plus(micros, MICROS);

        long nanos = startTemporal.until(endTemporal, NANOS);

        BigInteger totalMicros = BigInteger.valueOf(totalMillis).multiply(DTC.X_1000).add(BigInteger.valueOf(micros));
        BigInteger totalNanos = totalMicros.multiply(DTC.X_1000).add(BigInteger.valueOf(nanos));
        SignedInterval signedInterval = new SignedInterval(centuries, years, months, days, hours, minutes, seconds, millis, micros, nanos);
        signedInterval.startTime = originalStartTemporal;
        signedInterval.endTime = originalEndTemporal;
        signedInterval.totalYears = totalYears;
        signedInterval.totalMonths = totalMonths;
        signedInterval.totalWeeks = totalWeeks;
        signedInterval.totalDays = totalDays;
        signedInterval.totalHours = totalHours;
        signedInterval.totalMinutes = totalMinutes;
        signedInterval.totalSeconds = totalSeconds;
        signedInterval.totalMillis = totalMillis;
        signedInterval.totalMicros = totalMicros;
        signedInterval.totalNanos = totalNanos;
        return signedInterval;
    }

    /**
     * Obtain two {@link Date} datetime interval. <br>
     * 获取两个{@link Date}的时间间隔。
     *
     * @param startDate start Date
     * @param endDate   end Date
     * @return SignedInterval
     */
    public static SignedInterval between(Date startDate, Date endDate) {
        checkHasNullNPE(args(startDate, endDate), args("startDate", "endDate"));
        return between(DateTime.from(startDate).getZonedDateTime(), DateTime.from(endDate).getZonedDateTime());
    }

    /**
     * Obtain two {@link Calendar} datetime interval. <br>
     * 获取两个{@link Calendar}的时间间隔。
     *
     * @param startCalendar start Calendar
     * @param endCalendar   end Calendar
     * @return SignedInterval
     */
    public static SignedInterval between(Calendar startCalendar, Calendar endCalendar) {
        checkHasNullNPE(args(startCalendar, endCalendar), args("startCalendar", "endCalendar"));
        return between(DateTime.from(startCalendar).getZonedDateTime(), DateTime.from(endCalendar).getZonedDateTime());
    }

    /**
     * Checks if the specified temporal is supported.
     *
     * @param temporal temporal
     * @return true if the temporal can be added/subtracted, false if not
     */
    public static boolean isSupported(final Temporal temporal) {
        return temporal != null && SUPPORTED_TEMPORAL.stream().anyMatch(c -> c.isAssignableFrom(temporal.getClass()));
    }

    @Override
    public long get(TemporalUnit unit) {
        if (unit instanceof ChronoUnit) {
            ChronoUnit chronoUnit = (ChronoUnit) unit;
            switch (chronoUnit) {
                case CENTURIES:
                    return centuries;
                case YEARS:
                    return years;
                case MONTHS:
                    return months;
                case DAYS:
                    return days;
                case HOURS:
                    return hours;
                case MINUTES:
                    return minutes;
                case SECONDS:
                    return seconds;
                case MILLIS:
                    return millis;
                case MICROS:
                    return micros;
                case NANOS:
                    return nanos;
            }
        }
        throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
    }

    @Override
    public List<TemporalUnit> getUnits() {
        return SUPPORTED_UNITS;
    }

    @Override
    public Temporal addTo(Temporal temporal) {
        checkNullNPE(temporal, args("temporal"));
        checkTemporal(!isSupported(temporal), "Only [${…}] is supported for `temporal` parameter!", SUPPORTED_TEMPORAL_STRING);

        boolean isInstant = temporal instanceof Instant;
        temporal = isInstant ? ZonedDateTime.ofInstant((Instant) temporal, TZ.DEFAULT_ZONE) : temporal;

        temporal = plus(temporal, centuries * 100 + years, YEARS);
        temporal = plus(temporal, months, MONTHS);
        temporal = plus(temporal, days, DAYS);
        temporal = plus(temporal, hours, HOURS);
        temporal = plus(temporal, minutes, MINUTES);
        temporal = plus(temporal, seconds, SECONDS);
        temporal = plus(temporal, millis, MILLIS);
        temporal = plus(temporal, micros, MICROS);
        temporal = plus(temporal, nanos, NANOS);
        return isInstant ? ((ZonedDateTime) temporal).toInstant() : temporal;
    }

    protected Temporal plus(Temporal temporal, long amountToAdd, TemporalUnit unit) {
        return amountToAdd == 0 ? temporal : temporal.plus(amountToAdd, unit);
    }

    @Override
    public Temporal subtractFrom(Temporal temporal) {
        checkNullNPE(temporal, args("temporal"));
        checkTemporal(!isSupported(temporal), "Only [${…}] is supported for `temporal` parameter!!", SUPPORTED_TEMPORAL_STRING);

        boolean isInstant = temporal instanceof Instant;
        temporal = isInstant ? ZonedDateTime.ofInstant((Instant) temporal, TZ.DEFAULT_ZONE) : temporal;

        temporal = minus(temporal, centuries * 100 + years, YEARS);
        temporal = minus(temporal, months, MONTHS);
        temporal = minus(temporal, days, DAYS);
        temporal = minus(temporal, hours, HOURS);
        temporal = minus(temporal, minutes, MINUTES);
        temporal = minus(temporal, seconds, SECONDS);
        temporal = minus(temporal, millis, MILLIS);
        temporal = minus(temporal, micros, MICROS);
        temporal = minus(temporal, nanos, NANOS);
        return isInstant ? ((ZonedDateTime) temporal).toInstant() : temporal;
    }

    protected Temporal minus(Temporal temporal, long amountToSubtract, TemporalUnit unit) {
        return amountToSubtract == 0 ? temporal : temporal.minus(amountToSubtract, unit);
    }

    public long toYears() {
        return totalYears;
    }

    public long toMonths() {
        return totalMonths;
    }

    public long toWeeks() {
        return totalWeeks;
    }

    public long toDays() {
        return totalDays;
    }

    public long toHours() {
        return totalHours;
    }

    public long toMinutes() {
        return totalMinutes;
    }

    public long toSeconds() {
        return totalSeconds;
    }

    public long toMillis() {
        return totalMillis;
    }

    public BigInteger toMicros() {
        return totalMicros;
    }

    public BigInteger toNanos() {
        return totalNanos;
    }

    public Temporal getStartTime() {
        return startTime;
    }

    public Temporal getEndTime() {
        return endTime;
    }

    /**
     * 间隔总年数（近似值）
     *
     * @return 间隔总年数（近似值）
     * @see #totalYears
     */
    public long getApproxYears() {
        return approxYears;
    }

    /**
     * 间隔总月数（近似值）
     *
     * @return 间隔总月数（近似值）
     * @see #totalYears
     */
    public long getApproxMonths() {
        return approxMonths;
    }

    /**
     * 间隔总周数（近似值）
     *
     * @return 间隔总周数（近似值）
     * @see #totalYears
     */
    public long getApproxWeeks() {
        return approxWeeks;
    }

    /**
     * 间隔总天数（近似值）
     *
     * @return 间隔总天数（近似值）
     * @see #totalYears
     */
    public long getApproxDays() {
        return approxDays;
    }

    /**
     * 间隔总小时数（近似值）
     *
     * @return 间隔总小时数（近似值）
     * @see #totalYears
     */
    public long getApproxHours() {
        return approxHours;
    }

    /**
     * 间隔总分钟数（近似值）
     *
     * @return 间隔总分钟数（近似值）
     * @see #totalYears
     */
    public long getApproxMinutes() {
        return approxMinutes;
    }

    /**
     * 间隔总秒数（近似值）
     *
     * @return 间隔总秒数（近似值）
     * @see #totalYears
     */
    public long getApproxSeconds() {
        return approxSeconds;
    }

    /**
     * 间隔总毫秒数（近似值）
     *
     * @return 间隔总毫秒数（近似值）
     * @see #totalYears
     */
    public long getApproxMillis() {
        return approxMillis;
    }

    /**
     * 间隔总微秒数（近似值）
     *
     * @return 间隔总微秒数（近似值）
     * @see #totalYears
     */
    public BigInteger getApproxMicros() {
        return approxMicros;
    }

    /**
     * 间隔总纳秒数（近似值）
     *
     * @return 间隔总纳秒数（近似值）
     * @see #totalYears
     */
    public BigInteger getApproxNanos() {
        return approxNanos;
    }

    /**
     * 是否已计算近似值
     *
     * @return 是否已计算近似值
     */
    public boolean isCalculateApprox() {
        return calculateApprox;
    }

    /**
     * 计算此Interval换成某个时间单位的精确值（保留3位小数）
     *
     * @param unit 时间单位
     * @return 返回此Interval换成某个时间单位的精确值（保留3位小数）
     */
    public double unitExact(ChronoUnit unit) {
        return unitExact(unit, 3);
    }

    /**
     * 计算此Interval换成某个时间单位的精确值
     *
     * @param unit  时间单位
     * @param scale 精确小数位数
     * @return 返回此Interval换成某个时间单位的精确值
     */
    public double unitExact(ChronoUnit unit, int scale) {
        checkNullNPE(unit, args("unit"));
        return nanosDivide(unit, scale).doubleValue();
    }

    /**
     * 计算此Interval换成某个时间单位的精确值（保留3位小数）
     *
     * @param unit 时间单位
     * @return 返回此Interval换成某个时间单位的精确值（保留3位小数）
     */
    public String unitExactString(ChronoUnit unit) {
        return unitExactString(unit, 3);
    }

    /**
     * 计算此Interval换成某个时间单位的精确值
     *
     * @param unit  时间单位
     * @param scale 精确小数位数
     * @return 返回此Interval换成某个时间单位的精确值
     */
    public String unitExactString(ChronoUnit unit, int scale) {
        checkNullNPE(unit, args("unit"));
        return nanosDivide(unit, scale).stripTrailingZeros().toPlainString();
    }

    @Override
    public boolean equals(Object baseInterval) {
        return baseInterval instanceof SignedInterval && compareTo((SignedInterval) baseInterval) == 0;
    }

    @Override
    public int compareTo(SignedInterval other) {
        checkNullNPE(other, args("other"));

        if (this.totalNanos != null && other.totalNanos != null) {
            return this.totalNanos.compareTo(other.totalNanos);
        }
        long thisMonths = (centuries * 100 + years) * 12 + months;
        long otherMonths = (other.centuries * 100 + other.years) * 12 + other.months;
        if (thisMonths == otherMonths) {
            if (days == other.days) {
                long thisNanos = (((((hours * 60) + minutes) * 60 + seconds) * 1000 + millis) * 1000 + micros) * 1000 + nanos;
                long otherNanos = (((((other.hours * 60) + other.minutes) * 60 + other.seconds) * 1000 + other.millis) * 1000 + other.micros) * 1000 + other.nanos;
                return Long.compare(thisNanos, otherNanos);
            } else return Long.compare(days, other.days);
        } else return Long.compare(thisMonths, otherMonths);
    }


    /**
     * Format Interval info to string. <br><br>
     * <p>
     * The following pattern letters are defined:
     * <pre>
     *  Symbol        Meaning
     *  ------        -------
     *   c            centuries
     *   y            years
     *   M            months
     *   d            days
     *   H            hours
     *   m            minutes
     *   s            seconds
     *   tc           total centuries
     *   ty           total years
     *   tM           total months
     *   td           total days
     *   tH           total hours
     *   tm           total minutes
     *   ts           total seconds
     *   'c'          c char literal
     *   'y'          y char literal
     *   'M'          M char literal
     *   'd'          d char literal
     *   'H'          H char literal
     *   'm'          m char literal
     *   's'          s char literal
     *
     *   '            escape for text
     *   ''           single quote
     * </pre>
     * <p>
     *
     * @param pattern the pattern to use
     * @return the formatted string based on the pattern, not null
     */
    public String format(String pattern) {
        if (S.isBlank(pattern)) return pattern;
        calculateApprox();  // 计算近似值

        /*
         替换总时间pattern
         */
        Matcher matcher = IntervalPattern.totalPattern.matcher(pattern);
        StringBuffer formatResult = new StringBuffer();
        while (matcher.find()) {
            String unit = matcher.group(1);
            int scale = Try.tcf(() -> Integer.parseInt(matcher.group(2)), 0, false);
            /*
             scale 大于 15 时，scale = 0
             */
            if (scale > 15) scale = 0;

            Number totalValue = 0;
            switch (unit) {
                case "tc":
                    totalValue = scale == 0 ? (Number) (approxYears / 100) : unitExact(CENTURIES, scale);
                    break;
                case "ty":
                    totalValue = scale == 0 ? (Number) approxYears : unitExact(YEARS, scale);
                    break;
                case "tM":
                    totalValue = scale == 0 ? (Number) approxMonths : unitExact(MONTHS, scale);
                    break;
                case "td":
                    totalValue = scale == 0 ? (Number) approxDays : unitExact(DAYS, scale);
                    break;
                case "tH":
                    totalValue = scale == 0 ? (Number) approxHours : unitExact(HOURS, scale);
                    break;
                case "tm":
                    totalValue = scale == 0 ? (Number) approxMinutes : unitExact(MINUTES, scale);
                    break;
                case "ts":
                    totalValue = scale == 0 ? (Number) approxSeconds : unitExact(SECONDS, scale);
                    break;
                case "tS":
                    totalValue = scale == 0 ? (Number) approxMillis : unitExact(MILLIS, scale);
                    break;
            }

            String totalString = G.toString(totalValue, scale);
            matcher.appendReplacement(formatResult, totalString);
        }
        matcher.appendTail(formatResult);
        String result = formatResult.toString();

        /*
         替换单时间pattern
         */
        result = IntervalPattern.c.matcher(result).replaceAll(String.valueOf(centuries));
        result = IntervalPattern.y.matcher(result).replaceAll(String.valueOf(years));
        result = IntervalPattern.M.matcher(result).replaceAll(String.valueOf(months));
        result = IntervalPattern.d.matcher(result).replaceAll(String.valueOf(days));
        result = IntervalPattern.H.matcher(result).replaceAll(String.valueOf(hours));
        result = IntervalPattern.m.matcher(result).replaceAll(String.valueOf(minutes));
        result = IntervalPattern.s.matcher(result).replaceAll(String.valueOf(seconds));
        result = IntervalPattern.S.matcher(result).replaceAll(String.valueOf(millis));
        result = IntervalPattern.QcQ.matcher(result).replaceAll("c");
        result = IntervalPattern.QyQ.matcher(result).replaceAll("y");
        result = IntervalPattern.QMQ.matcher(result).replaceAll("M");
        result = IntervalPattern.QdQ.matcher(result).replaceAll("d");
        result = IntervalPattern.QHQ.matcher(result).replaceAll("H");
        result = IntervalPattern.QmQ.matcher(result).replaceAll("m");
        result = IntervalPattern.QsQ.matcher(result).replaceAll("s");
        result = IntervalPattern.QSQ.matcher(result).replaceAll("S");

        return result;
    }


    public String toFullString() {
        String equivalentlyStr  = OS.IS_ZH_LANG ? "\n相当于：\n" : "\nAlternative time units: \n";
        String yearsStr         = OS.IS_ZH_LANG ? " 年\n" : " years\n";
        String monthsStr        = OS.IS_ZH_LANG ? " 月\n" : " months\n";
        String weeksStr         = OS.IS_ZH_LANG ? " 周\n" : " weeks\n";
        String daysStr          = OS.IS_ZH_LANG ? " 天\n" : " days\n";
        String hoursStr         = OS.IS_ZH_LANG ? " 时\n" : " hours\n";
        String minutesStr       = OS.IS_ZH_LANG ? " 分\n" : " minutes\n";
        String secondsStr       = OS.IS_ZH_LANG ? " 秒\n" : " seconds\n";
        String millisStr        = OS.IS_ZH_LANG ? " 毫秒\n" : " millis\n";
        String microsStr        = OS.IS_ZH_LANG ? " 微秒\n" : " micros\n";
        String nanosStr         = OS.IS_ZH_LANG ? " 纳秒" : " nanos";

        String yearsStrApprox   = OS.IS_ZH_LANG ? " 年(近似值)\n" : " years (approx.)\n";
        String monthsStrApprox  = OS.IS_ZH_LANG ? " 月(近似值)\n" : " months (approx.)\n";
        String weeksStrApprox   = OS.IS_ZH_LANG ? " 周(近似值)\n" : " weeks (approx.)\n";
        String daysStrApprox    = OS.IS_ZH_LANG ? " 天(近似值)\n" : " days (approx.)\n";
        String hoursStrApprox   = OS.IS_ZH_LANG ? " 时(近似值)\n" : " hours (approx.)\n";
        String minutesStrApprox = OS.IS_ZH_LANG ? " 分(近似值)\n" : " minutes (approx.)\n";
        String secondsStrApprox = OS.IS_ZH_LANG ? " 秒(近似值)\n" : " seconds (approx.)\n";
        String millisStrApprox  = OS.IS_ZH_LANG ? " 毫秒(近似值)\n" : " millis (approx.)\n";
        String microsStrApprox  = OS.IS_ZH_LANG ? " 微秒(近似值)\n" : " micros (approx.)\n";
        String nanosStrApprox   = OS.IS_ZH_LANG ? " 纳秒(近似值)" : " nanos (approx.)";

        if (startTime != null && endTime != null) {
            return this + equivalentlyStr +
                    "● " + totalYears + yearsStr +
                    "● " + totalMonths + monthsStr +
                    "● " + totalWeeks + weeksStr +
                    "● " + totalDays + daysStr +
                    "● " + totalHours + hoursStr +
                    "● " + totalMinutes + minutesStr +
                    "● " + totalSeconds + secondsStr +
                    "● " + totalMillis + millisStr +
                    "● " + (totalMicros == null ? "0" : totalMicros.toString()) + microsStr +
                    "● " + (totalNanos == null ? "0" : totalNanos.toString()) + nanosStr;
        } else {
            calculateApprox();      // 计算近似值
            return this + equivalentlyStr +
                    "● " + approxYears + yearsStrApprox +
                    "● " + approxMonths + monthsStrApprox +
                    "● " + approxWeeks + weeksStrApprox +
                    "● " + approxDays + daysStrApprox +
                    "● " + approxHours + hoursStrApprox +
                    "● " + approxMinutes + minutesStrApprox +
                    "● " + approxSeconds + secondsStrApprox +
                    "● " + approxMillis + millisStrApprox +
                    "● " + (approxMicros == null ? "0" : approxMicros.toString()) + microsStrApprox +
                    "● " + (approxNanos == null ? "0" : approxNanos.toString()) + nanosStrApprox;
        }

    }

    @Override
    public String toString() {
        String centuriesStr = OS.IS_ZH_LANG ? "世纪" : " centuries ";
        String yearsStr     = OS.IS_ZH_LANG ? "年" : " years ";
        String monthsStr    = OS.IS_ZH_LANG ? "月" : " months ";
        String daysStr      = OS.IS_ZH_LANG ? "天" : " days ";
        String hoursStr     = OS.IS_ZH_LANG ? "时" : " hours ";
        String minutesStr   = OS.IS_ZH_LANG ? "分" : " minutes ";
        String secondsStr   = OS.IS_ZH_LANG ? "秒" : " seconds ";
        String millisStr    = OS.IS_ZH_LANG ? "毫秒" : " millis";
        String microsStr    = OS.IS_ZH_LANG ? "微秒" : " micros";
        String nanosStr     = OS.IS_ZH_LANG ? "纳秒" : " nanos";

        String intervalStr = "";
        if (centuries != 0) {
            intervalStr += centuries + centuriesStr +
                    years + yearsStr +
                    months + monthsStr +
                    days + daysStr +
                    hours + hoursStr +
                    minutes + minutesStr +
                    seconds + secondsStr +
                    millis + millisStr;
        } else if (years != 0) {
            intervalStr += years + yearsStr +
                    months + monthsStr +
                    days + daysStr +
                    hours + hoursStr +
                    minutes + minutesStr +
                    seconds + secondsStr +
                    millis + millisStr;
        } else if (months != 0) {
            intervalStr += months + monthsStr +
                    days + daysStr +
                    hours + hoursStr +
                    minutes + minutesStr +
                    seconds + secondsStr +
                    millis + millisStr;
        } else if (days != 0) {
            intervalStr += days + daysStr +
                    hours + hoursStr +
                    minutes + minutesStr +
                    seconds + secondsStr +
                    millis + millisStr;
        } else if (hours != 0) {
            intervalStr += hours + hoursStr +
                    minutes + minutesStr +
                    seconds + secondsStr +
                    millis + millisStr;
        } else if (minutes != 0) {
            intervalStr += minutes + minutesStr +
                    seconds + secondsStr +
                    millis + millisStr;
        } else if (seconds != 0) {
            intervalStr += seconds + secondsStr +
                    millis + millisStr;
        } else {
            intervalStr += millis + millisStr;
        }
        if (micros != 0) intervalStr += micros + microsStr;
        if (nanos != 0) intervalStr += nanos + nanosStr;

        return intervalStr;
    }

    public String toSimpleString() {
        String centuriesStr = OS.IS_ZH_LANG ? "世纪" : " centuries ";
        String yearsStr     = OS.IS_ZH_LANG ? "年" : " years ";
        String monthsStr    = OS.IS_ZH_LANG ? "月" : " months ";
        String daysStr      = OS.IS_ZH_LANG ? "天" : " days ";
        String hoursStr     = OS.IS_ZH_LANG ? "时" : " hours ";
        String minutesStr   = OS.IS_ZH_LANG ? "分" : " minutes ";
        String secondsStr   = OS.IS_ZH_LANG ? "秒" : " seconds ";
        String millisStr    = OS.IS_ZH_LANG ? "毫秒" : " millis";
        String microsStr    = OS.IS_ZH_LANG ? "微秒" : " micros";
        String nanosStr     = OS.IS_ZH_LANG ? "纳秒" : " nanos";

        if (centuries == 0 && years == 0 && months == 0 && days == 0 && hours == 0
                && minutes == 0 && seconds == 0 && millis == 0 && micros == 0 && nanos == 0) {
            return nanos + nanosStr;
        }

        String intervalStr = "";

        if (centuries != 0) intervalStr += centuries + centuriesStr;
        if (years != 0)     intervalStr += years + yearsStr;
        if (months != 0)    intervalStr += months + monthsStr;
        if (days != 0)      intervalStr += days + daysStr;
        if (hours != 0)     intervalStr += hours + hoursStr;
        if (minutes != 0)   intervalStr += minutes + minutesStr;
        if (seconds != 0)   intervalStr += seconds + secondsStr;
        if (millis != 0)    intervalStr += millis + millisStr;
        if (micros != 0)    intervalStr += micros + microsStr;
        if (nanos != 0)     intervalStr += nanos + nanosStr;

        return intervalStr;
    }

    /**
     * A hash code for this interval.
     *
     * @return hashCode
     */
    @Override
    public int hashCode() {
        if (startTime != null && endTime != null) {
            return Objects.hash(startTime, endTime);
        } else {
            long nanos = (((((hours * 60) + minutes) * 60 + seconds) * 1000 + millis) * 1000 + micros) * 1000 + this.nanos;
            return Objects.hash(centuries * 100 + years, months, days, nanos);
        }
    }

    /**
     * 计算近似值
     *
     * @return 当前对象
     */
    public SignedInterval calculateApprox() {
        if (this.calculateApprox) return this;
        this.calculateApprox = true;

        if (startTime != null && endTime != null) {
            this.approxNanos = this.totalNanos;
            this.approxMicros = this.totalMicros;
            this.approxMillis = this.totalMillis;
            this.approxSeconds = this.totalSeconds;
            this.approxMinutes = this.totalMinutes;
            this.approxHours = this.totalHours;
            this.approxDays = this.totalDays;
            this.approxWeeks = this.totalWeeks;
            this.approxMonths = this.totalMonths;
            this.approxYears = this.totalYears;
            return this;
        } else {
            this.approxNanos = BigInteger.valueOf(this.nanos).add(BigInteger.valueOf(this.micros).multiply(DTC.ONE_MICRO)).add(BigInteger.valueOf(this.millis).multiply(DTC.ONE_MILLI))
                    .add(BigInteger.valueOf(this.seconds).multiply(DTC.ONE_SECOND)).add(BigInteger.valueOf(this.minutes).multiply(DTC.ONE_MINUTE))
                    .add(BigInteger.valueOf(this.hours).multiply(DTC.ONE_HOUR)).add(BigInteger.valueOf(this.days).multiply(DTC.ONE_DAY))
                    .add(BigInteger.valueOf(this.months).multiply(DTC.ONE_MONTH)).add(BigInteger.valueOf(this.years).multiply(DTC.ONE_YEAR))
                    .add(BigInteger.valueOf(this.centuries).multiply(DTC.ONE_CENTURIE));
            this.approxMicros = this.approxNanos.divide(DTC.ONE_MICRO);
            this.approxMillis = this.approxMicros.divide(DTC.MILLI_MICROS).longValue();
            this.approxSeconds = this.approxMillis / DTC.SECOND2MILLIS;
            this.approxMinutes = this.approxSeconds / DTC.MINUTE2SECONDS;
            this.approxHours = this.approxMinutes / DTC.HOUR2MINUTES;
            this.approxDays = this.approxHours / DTC.DAY2HOURS;
            this.approxWeeks = this.approxDays / DTC.WEEK2DAYS;
            this.approxMonths = (long) (this.approxSeconds * 1.0f / DTC.MONTH2SECONDS);
            this.approxYears = (long) (this.approxSeconds * 1.0f / DTC.YEAR2SECONDS);
            return this;
        }
    }

    /**
     * 将总纳秒数转化成标准的 <b>时-分-秒-毫秒-微秒-纳秒</b>
     *
     * @param totalNanos 总纳秒数
     * @return 时-分-秒-毫秒-微秒-纳秒
     */
    protected long[] standardizingTime(BigInteger totalNanos) {
        long[] timeValues = new long[6];

        if (!totalNanos.equals(BigInteger.ZERO)) {
            BigInteger[] hoursAndRemainder = totalNanos.divideAndRemainder(DTC.ONE_HOUR);
            timeValues[0] = hoursAndRemainder[0].longValue();
            if (!hoursAndRemainder[1].equals(BigInteger.ZERO)) {
                BigInteger[] minutesAndRemainder = hoursAndRemainder[1].divideAndRemainder(DTC.ONE_MINUTE);
                timeValues[1] = minutesAndRemainder[0].longValue();
                if (!minutesAndRemainder[1].equals(BigInteger.ZERO)) {
                    BigInteger[] secondsAndRemainder = minutesAndRemainder[1].divideAndRemainder(DTC.ONE_SECOND);
                    timeValues[2] = secondsAndRemainder[0].longValue();
                    if (!secondsAndRemainder[1].equals(BigInteger.ZERO)) {
                        BigInteger[] millisAndRemainder = secondsAndRemainder[1].divideAndRemainder(DTC.ONE_MILLI);
                        timeValues[3] = millisAndRemainder[0].longValue();
                        if (!millisAndRemainder[1].equals(BigInteger.ZERO)) {
                            BigInteger[] microsAndRemainder = millisAndRemainder[1].divideAndRemainder(DTC.ONE_MICRO);
                            timeValues[4] = microsAndRemainder[0].longValue();
                            timeValues[5] = microsAndRemainder[1].longValue();
                        }
                    }
                }
            }
        }

        return timeValues;
    }

    /**
     * 将某个时间类型转为微秒
     *
     * @param amount     时间量
     * @param chronoUnit 时间单位
     * @return 总微秒数
     */
    protected BigInteger toMicros(long amount, ChronoUnit chronoUnit) {
        return DateTimes.toMicros(amount, chronoUnit);
    }

    /**
     * 将某个时间类型转为纳秒
     *
     * @param amount     时间量
     * @param chronoUnit 时间单位
     * @return 总纳秒数
     */
    protected BigInteger toNanos(long amount, ChronoUnit chronoUnit) {
        return DateTimes.toNanos(amount, chronoUnit);
    }

    protected BigDecimal nanosDivide(ChronoUnit unit, int scale) {
        return O.divide(totalNanos == null ? this.calculateApprox().approxNanos : totalNanos, unit.getDuration().toNanos(), scale);
    }

}
