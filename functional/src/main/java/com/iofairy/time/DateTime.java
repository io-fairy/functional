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

import com.iofairy.os.JavaVersion;
import com.iofairy.os.OS;
import com.iofairy.range.IntervalType;
import com.iofairy.range.Range;
import com.iofairy.si.SI;
import com.iofairy.top.O;
import com.iofairy.top.S;
import com.iofairy.tuple.Tuple;
import com.iofairy.tuple.Tuple2;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.time.temporal.*;
import java.util.*;
import java.util.stream.Collectors;

import static com.iofairy.validator.Preconditions.*;


/**
 * The {@code DateTime} class wraps a value of the {@link Date} or {@link Calendar} or {@link Instant}
 * or {@link LocalDateTime} or {@link OffsetDateTime} or {@link ZonedDateTime} or {@link LocalDate} type. <br>
 *
 * @implSpec This class is <b>immutable</b> and <b>thread-safe</b>.
 * @since 0.6.0
 */
public class DateTime implements Temporal, Comparable<DateTime>, Serializable {
    private static final long serialVersionUID = 6265106615563566L;

    /**
     * The local date-time. <br>
     * 当 dateTime 是 {@link Instant} 类型时，采用当前默认时区获取 {@code LocalDateTime}
     */
    private final LocalDateTime localDateTime;
    /**
     * 时区偏移量。<br>
     * 当 dateTime 是 {@link Instant} 类型时，时区偏移量采用当前默认时区的偏移量；<br>
     * 当 dateTime 是 {@link LocalDateTime} 类型时，时区偏移量采用当前默认时区的偏移量。
     */
    private final ZoneOffset offset;
    /**
     * 时区。<br>
     * 当 dateTime 是 {@link Instant} 类型时，时区采用当前默认时区 {@link TZ#DEFAULT_ZONE}；<br>
     * 当 dateTime 是 {@link LocalDateTime} 类型时，时区采用当前默认时区 {@link TZ#DEFAULT_ZONE}。
     */
    private final ZoneId zone;
    /**
     * An instantaneous point on the time-line.
     */
    private final Instant instant;
    /**
     * The date-time with a time-zone
     */
    private final ZonedDateTime zonedDateTime;
    /**
     * The date-time with an offset
     */
    private final OffsetDateTime offsetDateTime;

    /**
     * Supported date time
     */
    private static final List<Class<?>> SUPPORTED_DATETIME =
            Arrays.asList(Date.class, Calendar.class, LocalDateTime.class, ZonedDateTime.class, OffsetDateTime.class, Instant.class);
    /**
     * Supported date time string
     */
    private static final String SUPPORTED_DATETIME_STRING = SUPPORTED_DATETIME.stream().map(c -> c == Date.class ? c.getName() : c.getSimpleName()).collect(Collectors.joining(", "));

    /**
     * excluded classes
     */
    private static final List<String> EXCLUDED_CLASS_NAMES = Collections.singletonList("java.sql.Time");

    /**
     * Date time parse error massage template
     */
    private static final String DT_PARSE_ERROR_MSG_TPL = "Text '${text}' could not be parsed, you can specify the DateTime `formatter` manually by calling ${method}. ";

    private static final String ERROR_MSG_FOR_NUMBER_TYPE = "If `dateTime` is of the `Number` type, it must be a `long` type or a type compatible with `long`, " +
            "and can be converted to `long` without loss of precision. ";

    public DateTime(Object dateTime) {
        this(dateTime, true);
    }

    private DateTime(Object dateTime, boolean checkValue) {
        if (dateTime instanceof CharSequence) {
            dateTime = parse(dateTime.toString());
            checkValue = false;
        } else if (dateTime instanceof Number) {
            Number number = (Number) dateTime;
            checkArgument(number instanceof BigInteger || !O.isInteger(number), ERROR_MSG_FOR_NUMBER_TYPE);
            dateTime = Instant.ofEpochMilli(number.longValue());
            checkValue = false;
        } else if (dateTime instanceof LocalDate) {
            dateTime = ((LocalDate) dateTime).atStartOfDay();
            checkValue = false;
        } else if (dateTime instanceof java.sql.Date) {
            dateTime = ((java.sql.Date) dateTime).toLocalDate().atStartOfDay();
            checkValue = false;
        }

        if (dateTime instanceof DateTime) {
            DateTime dt = (DateTime) dateTime;
            this.localDateTime = dt.localDateTime;
            this.offset = dt.offset;
            this.zone = dt.zone;
            this.instant = dt.instant;
            this.zonedDateTime = dt.zonedDateTime;
            this.offsetDateTime = dt.offsetDateTime;
        } else {
            if (checkValue) {
                final Class<?> dateTimeClass = dateTime.getClass();
                checkNullNPE(dateTime, args("dateTime"));
                checkTemporal(SUPPORTED_DATETIME.stream().noneMatch(c -> c.isAssignableFrom(dateTimeClass)),
                        "Only [${…}] is supported for `dateTime` parameter! ", SUPPORTED_DATETIME_STRING);
                checkTemporal(EXCLUDED_CLASS_NAMES.contains(dateTimeClass.getName()),
                        "${…} are unsupported here, you can convert it to the `java.util.Date` first! ", EXCLUDED_CLASS_NAMES);
            }

            Tuple2<Instant, ZonedDateTime> zdtAndInstant = DateTimes.toZDTAndInstant(dateTime);
            this.instant = zdtAndInstant._1;
            this.zonedDateTime = zdtAndInstant._2;
            this.zone = zonedDateTime.getZone();
            this.offset = zonedDateTime.getOffset();
            /*
             注意：
             LocalDateTime 转成 ZonedDateTime 时，或 OffsetDateTime 转成 ZonedDateTime 时，
             可能 ZonedDateTime 中的 LocalDateTime 与原来传入的对象中 LocalDateTime 有细微差别。
             */
            this.localDateTime = zonedDateTime.toLocalDateTime();
            this.offsetDateTime = zonedDateTime.toOffsetDateTime();
        }
    }


    public static DateTime of(Object dateTime) {
        return new DateTime(dateTime, true);
    }

    public static DateTime of(LocalDate localDate) {
        checkNullNPE(localDate, args("localDate"));
        return from(localDate.atStartOfDay());
    }

    public static DateTime of(long epochMillis) {
        return from(epochMillis);
    }

    public static DateTime ofEpochSecond(long epochSeconds) {
        return from(epochSeconds * 1000);
    }

    /**
     * Get {@code DateTime} from the date string
     *
     * @param dateStr date string
     * @return {@code DateTime}
     */
    public static DateTime of(CharSequence dateStr) {
        return parse(dateStr);
    }

    public static DateTime of(int year, int month, int dayOfMonth, int hour, int minute, int second, int nanoOfSecond, ZoneId zone) {
        return from(ZonedDateTime.of(year, month, dayOfMonth, hour, minute, second, nanoOfSecond, zone == null ? TZ.DEFAULT_ZONE : zone));
    }

    public static DateTime of(int year, int month, int dayOfMonth, int hour, int minute, int second, ZoneId zone) {
        return of(year, month, dayOfMonth, hour, minute, second, 0, zone);
    }

    public static DateTime of(int year, int month, int dayOfMonth, int hour, int minute, ZoneId zone) {
        return of(year, month, dayOfMonth, hour, minute, 0, 0, zone);
    }

    public static DateTime of(int year, int month, int dayOfMonth, ZoneId zone) {
        return of(year, month, dayOfMonth, 0, 0, 0, 0, zone);
    }

    static DateTime from(Object dateTime) {
        return new DateTime(dateTime, false);
    }

    /**
     * Get current DateTime
     *
     * @return {@code DateTime}
     */
    public static DateTime now() {
        return from(ZonedDateTime.now());
    }

    /**
     * Get current DateTime with the specified zone
     *
     * @param zoneId zoneId
     * @return {@code DateTime}
     */
    public static DateTime nowWith(ZoneId zoneId) {
        ZonedDateTime now = ZonedDateTime.now();
        if (zoneId != null) {
            now = now.withZoneSameInstant(zoneId);
        }
        return from(now);
    }

    /**
     * Get current DateTime with the specified zone
     *
     * @param offset zone-offset
     * @return {@code DateTime}
     */
    public static DateTime nowWith(ZoneOffset offset) {
        OffsetDateTime now = OffsetDateTime.now();
        if (offset != null) {
            now = now.withOffsetSameInstant(offset);
        }
        return from(now);
    }


    /**
     * Returns a copy of this date-time with a different time-zone, retaining the instant.
     *
     * @param zoneId the time-zone to change to. <b>if {@code null}, returns {@code this} without changing the time-zone</b>
     * @return a {@code DateTime} based on this date-time with the requested zone, not null
     * @see ZonedDateTime#withZoneSameInstant(ZoneId)
     */
    public DateTime withZoneSameInstant(ZoneId zoneId) {
        if (zoneId == null) return this;
        return from(zonedDateTime.withZoneSameInstant(zoneId));
    }

    /**
     * Returns a copy of this date-time with a different time-zone, retaining the local date-time if possible.
     *
     * @param zoneId the time-zone to change to. <b>if {@code null}, returns {@code this} without changing the time-zone</b>
     * @return a {@code DateTime} based on this date-time with the requested zone, not null
     * @see ZonedDateTime#withZoneSameLocal(ZoneId)
     */
    public DateTime withZoneSameLocal(ZoneId zoneId) {
        if (zoneId == null) return this;
        return from(zonedDateTime.withZoneSameLocal(zoneId));
    }

    /**
     * Returns a copy of this date-time with a different zone-offset, retaining the instant.
     *
     * @param offset the zone-offset to change to. <b>if {@code null}, returns {@code this} without changing the zone-offset</b>
     * @return a {@code DateTime} based on this date-time with the requested zone, not null
     * @see OffsetDateTime#withOffsetSameInstant(ZoneOffset)
     */
    public DateTime withOffsetSameInstant(ZoneOffset offset) {
        if (offset == null) return this;
        return from(offsetDateTime.withOffsetSameInstant(offset));
    }

    /**
     * Returns a copy of this date-time with a different zone-offset, retaining the local date-time if possible.
     *
     * @param offset the zone-offset to change to. <b>if {@code null}, returns {@code this} without changing the zone-offset</b>
     * @return a {@code DateTime} based on this date-time with the requested zone, not null
     * @see OffsetDateTime#withOffsetSameLocal(ZoneOffset)
     */
    public DateTime withOffsetSameLocal(ZoneOffset offset) {
        if (offset == null) return this;
        return from(offsetDateTime.withOffsetSameLocal(offset));
    }

    /*###################################################################################
     ************************************************************************************
     ------------------------------------------------------------------------------------
     ******************   START: Convert DateTime to another date time   ****************
     ------------------------------------------------------------------------------------
     ************************************************************************************
     ###################################################################################*/

    /**
     * Convert a {@link DateTime} object to an object of the specified date time type
     *
     * @param clazz specified date time type
     * @param <DT>  date time type
     * @return an object of the specified date time type
     */
    public <DT> DT toDT(Class<DT> clazz) {
        checkNullNPE(clazz, args("clazz"));

        if (Date.class == clazz) {
            @SuppressWarnings("unchecked")
            DT dt = (DT) toDate();
            return dt;
        } else if (Calendar.class == clazz) {
            @SuppressWarnings("unchecked")
            DT dt = (DT) toCalendar();
            return dt;
        } else if (LocalDateTime.class == clazz) {
            @SuppressWarnings("unchecked")
            DT dt = (DT) toLocalDT();
            return dt;
        } else if (ZonedDateTime.class == clazz) {
            @SuppressWarnings("unchecked")
            DT dt = (DT) toZonedDT();
            return dt;
        } else if (OffsetDateTime.class == clazz) {
            @SuppressWarnings("unchecked")
            DT dt = (DT) toOffsetDT();
            return dt;
        } else if (Instant.class == clazz) {
            @SuppressWarnings("unchecked")
            DT dt = (DT) toInstant();
            return dt;
        } else if (LocalDate.class == clazz) {
            @SuppressWarnings("unchecked")
            DT dt = (DT) toLocalDate();
            return dt;
        } else if (DateTime.class == clazz) {
            @SuppressWarnings("unchecked")
            DT dt = (DT) this;
            return dt;
        } else if (java.sql.Timestamp.class == clazz) {
            @SuppressWarnings("unchecked")
            DT dt = (DT) toTimestamp();
            return dt;
        } else if (java.sql.Date.class == clazz) {
            @SuppressWarnings("unchecked")
            DT dt = (DT) toSQLDate();
            return dt;
        } else if (java.sql.Time.class == clazz) {
            @SuppressWarnings("unchecked")
            DT dt = (DT) toTime();
            return dt;
        } else {
            throw new UnsupportedTemporalTypeException("Unsupported type: [" + clazz + "]");
        }
    }


    /**
     * Convert {@link DateTime} to {@link Date} <br>
     * <b>注：</b><br>
     * {@link Date} 是总是代表<b>当前默认时区</b>的时间，无法改变{@link Date}的时区
     *
     * @return {@link Date}
     */
    public Date toDate() {
        return Date.from(instant);
    }

    public Calendar toCalendar() {
        return DateTimes.toCalendar(zonedDateTime);
    }

    public ZonedDateTime toZonedDT() {
        return zonedDateTime;
    }

    public OffsetDateTime toOffsetDT() {
        return offsetDateTime;
    }

    public LocalDateTime toLocalDT() {
        return localDateTime;
    }

    public LocalDate toLocalDate() {
        return localDateTime.toLocalDate();
    }

    public Instant toInstant() {
        return instant;
    }

    public long toEpochSecond() {
        return instant.getEpochSecond();
    }

    public long toEpochMilli() {
        return instant.toEpochMilli();
    }

    public java.sql.Timestamp toTimestamp() {
        return new java.sql.Timestamp(toEpochMilli());
    }

    public java.sql.Date toSQLDate() {
        return new java.sql.Date(toEpochMilli());
    }

    public java.sql.Time toTime() {
        return new java.sql.Time(toEpochMilli());
    }

    /**
     * 将 DateTime 转为 {@link TZ#UTC} 时区的 {@code ZonedDateTime}
     *
     * @return ZonedDateTime
     */
    public ZonedDateTime toUTCZonedDT() {
        return toZonedDT(TZ.UTC);
    }

    /**
     * 将 DateTime 转为 {@link TZ#DEFAULT_ZONE} 时区的 {@code ZonedDateTime}
     *
     * @return ZonedDateTime
     */
    public ZonedDateTime toDefaultZonedDT() {
        return toZonedDT(TZ.DEFAULT_ZONE);
    }

    /**
     * 将 DateTime 转为指定时区的 {@code ZonedDateTime}
     *
     * @param zoneId zoneId. 当 zoneId为{@code null}时，且 dateTime 是 {@code ZonedDateTime} 或 {@code OffsetDateTime} 或 {@code Calendar} 类型时，
     *               默认采用dateTime自己的时区；否则，默认采用{@link TZ#DEFAULT_ZONE}
     * @return ZonedDateTime
     */
    public ZonedDateTime toZonedDT(ZoneId zoneId) {
        if (zoneId == null || this.zone.equals(zoneId)) return zonedDateTime;
        return zonedDateTime.withZoneSameInstant(zoneId);
    }

    /**
     * 将 DateTime 转为 {@link ZoneOffset#UTC} 时区的 {@code OffsetDateTime}
     *
     * @return OffsetDateTime
     */
    public OffsetDateTime toUTCOffsetDT() {
        return toOffsetDT(ZoneOffset.UTC);
    }

    /**
     * 将 DateTime 转为 {@link DateTimes#defaultOffset()} 时区的 {@code OffsetDateTime}
     *
     * @return OffsetDateTime
     */
    public OffsetDateTime toDefaultOffsetDT() {
        return toOffsetDT(DateTimes.defaultOffset());
    }

    /**
     * 将 DateTime 转为指定时区的 {@code OffsetDateTime}
     *
     * @param zoneOffset zoneOffset. 当 zoneOffset为{@code null}时，且 datetime 是 {@code OffsetDateTime} 或 {@code ZonedDateTime} 或 {@code Calendar} 类型时，
     *                   默认采用datetime自己的时区偏移；否则，默认采用{@link DateTimes#defaultOffset()}时区偏移
     * @return OffsetDateTime
     */
    public OffsetDateTime toOffsetDT(ZoneOffset zoneOffset) {
        if (zoneOffset == null || this.offset.equals(zoneOffset)) return offsetDateTime;
        return this.offsetDateTime.withOffsetSameInstant(zoneOffset);
    }

    /**
     * 将 DateTime 转为 {@link TZ#UTC} 时区的 {@code Calendar}
     *
     * @return Calendar
     */
    public Calendar toUTCCalendar() {
        return toCalendar(TZ.UTC);
    }

    /**
     * 将 DateTime 转为 {@link TZ#DEFAULT_ZONE} 时区的 {@code Calendar}
     *
     * @return Calendar
     */
    public Calendar toDefaultCalendar() {
        return toCalendar(TZ.DEFAULT_ZONE);
    }

    /**
     * 将 DateTime 转为指定时区的 Calendar。
     *
     * @param zoneId 时区。当 zoneId为{@code null}时，且 dateTime 是 {@code ZonedDateTime} 或 {@code OffsetDateTime} 或 {@code Calendar} 类型时，
     *               默认采用dateTime自己的时区；否则，默认采用{@link TZ#DEFAULT_ZONE}
     * @return Calendar
     */
    public Calendar toCalendar(ZoneId zoneId) {
        return DateTimes.toCalendar(toZonedDT(zoneId));
    }

    /**
     * ZonedDateTime 转成 Calendar，且返回的 Calendar 与参数 calendar 相同类型
     *
     * @param calendar      calendar，用于指示返回的 Calendar 的类型
     * @param zonedDateTime zonedDateTime
     * @return 返回与参数 calendar 相同类型的 Calendar
     */
    Calendar toCalendar(Calendar calendar, ZonedDateTime zonedDateTime) {
        calendar.setTimeZone(TimeZone.getTimeZone(zonedDateTime.getZone()));
        calendar.setTimeInMillis(zonedDateTime.toInstant().toEpochMilli());
        return calendar;
    }

    /*###################################################################################
     ************************************************************************************
     ------------------------------------------------------------------------------------
     ******************   END: Convert DateTime to another date time   ******************
     ------------------------------------------------------------------------------------
     ************************************************************************************
     ###################################################################################*/


    /**
     * 对 DateTime 进行增减操作。<br>
     * 注：
     * {@link Instant} 类型的时间会先转成 {@link TZ#DEFAULT_ZONE} 时区的 ZonedDateTime 进行增减。
     *
     * @param amount 增减的量
     * @param unit   时间单位
     * @return 增减后的 DateTime
     */
    @Override
    public DateTime plus(long amount, TemporalUnit unit) {
        if (amount == 0) return this;
        return from(zonedDateTime.plus(amount, unit));
    }

    /**
     * 对 DateTime 进行增减操作。
     *
     * @param amountToAdd 增减的量
     * @return 增减后的 DateTime
     */
    @Override
    public DateTime plus(TemporalAmount amountToAdd) {
        if (amountToAdd instanceof ChronoInterval) {
            return ((ChronoInterval) amountToAdd).plusTo(this);
        } else {
            return from(amountToAdd.addTo(zonedDateTime));
        }
    }

    public DateTime plusYears(long years) {
        return plus(years, ChronoUnit.YEARS);
    }

    public DateTime plusMonths(long months) {
        return plus(months, ChronoUnit.MONTHS);
    }

    public DateTime plusWeeks(long weeks) {
        return plus(weeks, ChronoUnit.WEEKS);
    }

    public DateTime plusDays(long days) {
        return plus(days, ChronoUnit.DAYS);
    }

    public DateTime plusHours(long hours) {
        return plus(hours, ChronoUnit.HOURS);
    }

    public DateTime plusMinutes(long minutes) {
        return plus(minutes, ChronoUnit.MINUTES);
    }

    public DateTime plusSeconds(long seconds) {
        return plus(seconds, ChronoUnit.SECONDS);
    }

    public DateTime plusMillis(long millis) {
        return plus(millis, ChronoUnit.MILLIS);
    }

    public DateTime plusMicros(long micros) {
        return plus(micros, ChronoUnit.MICROS);
    }

    public DateTime plusNanos(long nanos) {
        return plus(nanos, ChronoUnit.NANOS);
    }

    /**
     * 对 DateTime 进行增减操作。<br>
     *
     * @param amount 增减的量
     * @param unit   时间单位
     * @return 增减后的 DateTime
     */
    @Override
    public DateTime minus(long amount, TemporalUnit unit) {
        if (amount == 0) return this;
        return from(zonedDateTime.minus(amount, unit));
    }

    /**
     * 对 DateTime 进行增减操作。
     *
     * @param amountToSubtract 增减的量
     * @return 增减后的 DateTime
     */
    public DateTime minus(TemporalAmount amountToSubtract) {
        if (amountToSubtract instanceof ChronoInterval) {
            return ((ChronoInterval) amountToSubtract).minusFrom(this);
        } else {
            return from(amountToSubtract.subtractFrom(zonedDateTime));
        }
    }

    public DateTime minusYears(long years) {
        return minus(years, ChronoUnit.YEARS);
    }

    public DateTime minusMonths(long months) {
        return minus(months, ChronoUnit.MONTHS);
    }

    public DateTime minusWeeks(long weeks) {
        return minus(weeks, ChronoUnit.WEEKS);
    }

    public DateTime minusDays(long days) {
        return minus(days, ChronoUnit.DAYS);
    }

    public DateTime minusHours(long hours) {
        return minus(hours, ChronoUnit.HOURS);
    }

    public DateTime minusMinutes(long minutes) {
        return minus(minutes, ChronoUnit.MINUTES);
    }

    public DateTime minusSeconds(long seconds) {
        return minus(seconds, ChronoUnit.SECONDS);
    }

    public DateTime minusMillis(long millis) {
        return minus(millis, ChronoUnit.MILLIS);
    }

    public DateTime minusMicros(long micros) {
        return minus(micros, ChronoUnit.MICROS);
    }

    public DateTime minusNanos(long nanos) {
        return minus(nanos, ChronoUnit.NANOS);
    }

    @Override
    public DateTime with(TemporalAdjuster adjuster) {
        return from(zonedDateTime.with(adjuster));
    }

    @Override
    public DateTime with(TemporalField field, long newValue) {
        return from(zonedDateTime.with(field, newValue));
    }

    /**
     * Returns a copy of this {@code DateTime} with the specified {@code LocalDateTime} set to a new value.
     *
     * @param localDateTime localDateTime
     * @return {@code DateTime}
     */
    public DateTime withLocalDateTime(LocalDateTime localDateTime) {
        return from(ZonedDateTime.of(localDateTime, zone));
    }

    /**
     * Returns a copy of this {@code DateTime} with the specified {@code LocalDate} set to a new value.
     *
     * @param localDate localDate
     * @return {@code DateTime}
     */
    public DateTime withLocalDate(LocalDate localDate) {
        return from(zonedDateTime.withYear(localDate.getYear()).withMonth(localDate.getMonthValue()).withDayOfMonth(localDate.getDayOfMonth()));
    }

    /**
     * Returns a copy of this {@code DateTime} with the specified {@code LocalTime} set to a new value.
     *
     * @param localTime localTime
     * @return {@code DateTime}
     */
    public DateTime withLocalTime(LocalTime localTime) {
        return from(zonedDateTime.withHour(localTime.getHour())
                .withMinute(localTime.getMinute())
                .withSecond(localTime.getSecond())
                .withNano(localTime.getNano()));
    }

    public DateTime withYear(int year) {
        return with(ChronoField.YEAR, year);
    }

    public DateTime withMonth(int month) {
        return with(ChronoField.MONTH_OF_YEAR, month);
    }

    public DateTime withDayOfYear(int dayOfYear) {
        return with(ChronoField.DAY_OF_YEAR, dayOfYear);
    }

    public DateTime withDayOfMonth(int dayOfMonth) {
        return with(ChronoField.DAY_OF_MONTH, dayOfMonth);
    }

    public DateTime withHour(int hour) {
        return with(ChronoField.HOUR_OF_DAY, hour);
    }

    public DateTime withMinute(int minute) {
        return with(ChronoField.MINUTE_OF_HOUR, minute);
    }

    public DateTime withSecond(int second) {
        return with(ChronoField.SECOND_OF_MINUTE, second);
    }

    public DateTime withNano(int nanoOfSecond) {
        return with(ChronoField.NANO_OF_SECOND, nanoOfSecond);
    }

    public int get(TemporalField field) {
        return zonedDateTime.get(field);
    }

    public int getYear() {
        return localDateTime.getYear();
    }

    public int getMonthValue() {
        return localDateTime.getMonthValue();
    }

    public Month getMonth() {
        return localDateTime.getMonth();
    }

    public int getDayOfYear() {
        return localDateTime.getDayOfYear();
    }

    public int getDayOfMonth() {
        return localDateTime.getDayOfMonth();
    }

    public DayOfWeek getDayOfWeek() {
        return localDateTime.getDayOfWeek();
    }

    public int getHour() {
        return localDateTime.getHour();
    }

    public int getMinute() {
        return localDateTime.getMinute();
    }

    public int getSecond() {
        return localDateTime.getSecond();
    }

    public int getMillis() {
        return localDateTime.get(ChronoField.MILLI_OF_SECOND);
    }

    public int getMicros() {
        return localDateTime.get(ChronoField.MICRO_OF_SECOND);
    }

    public int getNano() {
        return localDateTime.getNano();
    }

    /**
     * 对 DateTime 进行取整
     *
     * @param chronoUnit 按此时间单位作为取整后的精度
     * @param roundingDT 取整类型，值为{@code null}默认为：{@link RoundingDT#FLOOR}
     * @return 取整后的时间
     */
    public DateTime round(ChronoUnit chronoUnit, RoundingDT roundingDT) {
        return from(DateTimeRound.round(zonedDateTime, localDateTime, zone, chronoUnit, roundingDT));
    }

    /**
     * 对 DateTime 的 时，分，秒 进行取整。
     *
     * @param chronoUnit 按此时间单位作为取整后的精度
     * @param amountUnit 时间步长（不分正负）
     * @param roundingDT 取整类型，值为{@code null}默认为：{@link RoundingDT#FLOOR}
     * @return 取整后的时间
     */
    public DateTime roundTime(ChronoUnit chronoUnit, int amountUnit, RoundingDT roundingDT) {
        return from(DateTimeRound.roundTime(zonedDateTime, localDateTime, zone, chronoUnit, amountUnit, roundingDT));
    }

    /**
     * 从给定的时间，按指定的时间单位，指定的步长偏移时间，返回每次偏移后的所有时间列表
     *
     * @param shiftTimes         偏移的次数，负数：则时间往前偏移；正数：则时间往后偏移
     * @param amountUnit         偏移的步长（不分正负）
     * @param chronoUnit         时间单位
     * @param includeCurrentTime 最后列表是否包含 fromDate
     * @return 每次偏移后的所有时间列表
     */
    public List<DateTime> datesByShift(int shiftTimes, int amountUnit, ChronoUnit chronoUnit, boolean includeCurrentTime) {
        if (chronoUnit == ChronoUnit.WEEKS) {
            chronoUnit = ChronoUnit.DAYS;
            amountUnit = amountUnit * 7;
        }
        List<ZonedDateTime> dateTimes = DateTimeShift.datesByShift(zonedDateTime, shiftTimes, amountUnit, chronoUnit, includeCurrentTime);
        List<DateTime> dts = new ArrayList<>();
        for (ZonedDateTime dateTime : dateTimes) {
            dts.add(from(dateTime));
        }
        return dts;
    }

    /**
     * 从给定的时间，按指定的时间单位，指定的步长偏移时间，返回每次偏移后的所有时间列表
     *
     * @param shiftTimes         偏移的次数，负数：则时间往前偏移；正数：则时间往后偏移
     * @param chronoUnit         时间单位
     * @param includeCurrentTime 最后列表是否包含 fromDate
     * @return 每次偏移后的所有时间列表
     */
    public List<DateTime> datesByShift(int shiftTimes, ChronoUnit chronoUnit, boolean includeCurrentTime) {
        return datesByShift(shiftTimes, 1, chronoUnit, includeCurrentTime);
    }

    /**
     * 从给定的时间，按指定的时间单位，指定的步长偏移时间，返回每次偏移后的所有时间列表
     *
     * @param shiftTimes 偏移的次数，负数：则时间往前偏移；正数：则时间往后偏移
     * @param chronoUnit 时间单位
     * @return 每次偏移后的所有时间列表
     */
    public List<DateTime> datesByShift(int shiftTimes, ChronoUnit chronoUnit) {
        return datesByShift(shiftTimes, 1, chronoUnit, true);
    }

    /**
     * 从给定的时间范围（开始时间，结束时间），按指定的时间单位，指定的步长偏移时间，返回每次偏移后的所有时间列表
     *
     * @param toDateTime   结束的时间
     * @param amountUnit   偏移的步长（不分正负）
     * @param chronoUnit   时间单位
     * @param intervalType 区间类型
     * @return 每次偏移后的所有时间列表
     */
    public List<DateTime> datesFromRange(DateTime toDateTime, int amountUnit, ChronoUnit chronoUnit, IntervalType intervalType) {
        checkNullNPE(toDateTime, args("toDateTime"));

        if (chronoUnit == ChronoUnit.WEEKS) {
            chronoUnit = ChronoUnit.DAYS;
            amountUnit = amountUnit * 7;
        }

        List<ZonedDateTime> dateTimes = DateTimeShift.datesFromRange(zonedDateTime, toDateTime.zonedDateTime, amountUnit, chronoUnit, intervalType);
        List<DateTime> dts = new ArrayList<>();
        for (ZonedDateTime dateTime : dateTimes) {
            dts.add(from(dateTime));
        }
        return dts;
    }

    /**
     * 从给定的时间范围（开始时间，结束时间），按指定的时间单位，指定的步长偏移时间，返回每次偏移后的所有时间列表
     *
     * @param toDateTime   结束的时间
     * @param chronoUnit   时间单位
     * @param intervalType 区间类型
     * @return 每次偏移后的所有时间列表
     */
    public List<DateTime> datesFromRange(DateTime toDateTime, ChronoUnit chronoUnit, IntervalType intervalType) {
        return datesFromRange(toDateTime, 1, chronoUnit, intervalType);
    }

    /**
     * 从给定的时间范围（开始时间，结束时间），按指定的时间单位，指定的步长偏移时间，返回每次偏移后的所有时间列表
     *
     * @param toDateTime 结束的时间
     * @param chronoUnit 时间单位
     * @return 每次偏移后的所有时间列表
     */
    public List<DateTime> datesFromRange(DateTime toDateTime, ChronoUnit chronoUnit) {
        return datesFromRange(toDateTime, 1, chronoUnit, IntervalType.CLOSED);
    }

    /**
     * 保留所提供的chronoUnit以及比chronoUnit大的单位的值，填充比 chronoUnit 小的单位的值为 0。
     *
     * @param chronoUnit 时间单位
     * @return DateTime
     */
    public DateTime fill0(ChronoUnit chronoUnit) {
        return round(chronoUnit, RoundingDT.FLOOR);
    }

    /**
     * 保留所提供的chronoUnit以及比chronoUnit大的单位的值，填充比 chronoUnit 小的单位的值为 所允许的最大值。
     *
     * @param chronoUnit 时间单位
     * @return DateTime
     */
    public DateTime fill9(ChronoUnit chronoUnit) {
        return round(chronoUnit, RoundingDT.FLOOR).plus(1, chronoUnit).minusNanos(1);
    }

    /**
     * 保留所提供的chronoUnit以及比chronoUnit大的单位的值，填充比 chronoUnit 小的单位的值为 0。
     *
     * @param chronoUnit 时间单位
     * @return DateTime
     */
    public DateTime withMin(ChronoUnit chronoUnit) {
        return fill0(chronoUnit);
    }

    /**
     * 保留所提供的chronoUnit以及比chronoUnit大的单位的值，填充比 chronoUnit 小的单位的值为 所允许的最大值。
     *
     * @param chronoUnit 时间单位
     * @return DateTime
     */
    public DateTime withMax(ChronoUnit chronoUnit) {
        return fill9(chronoUnit);
    }

    /**
     * 获取某个月的总天数
     *
     * @return 当前月的总天数
     */
    public int daysOfMonth() {
        return DateTimes.daysOfMonth(localDateTime.getYear(), localDateTime.getMonthValue());
    }


    /**
     * Parse string to {@link DateTime} <br>
     *
     * @param text date time string
     * @return {@code DateTime}
     */
    public static DateTime parse(CharSequence text) {
        checkNullNPE(text, args("text"));
        String dateText = DateTimes.formatZhMillis(text.toString());

        String dateFormat = DateTimePattern.forDTF(dateText);
        if (S.isEmpty(dateFormat)) throw new DateTimeParseException(SI.$(DT_PARSE_ERROR_MSG_TPL, text, "`parse(CharSequence, String)`"), text, 0);
        return parse(dateText, dateFormat);
    }

    /**
     * Parse string to {@link DateTime} <br>
     * <b>注：</b><br>
     * {@code dtPattern} 如果<b>带有时区标识如：{@code VV}</b>，且设置了 {@code zoneId}，则 {@code dtPattern} 中解析的时区优先级更高。<br>
     * {@code dtPattern} 如果<b>只带时区偏移标识如：{@code xxx}</b>，且设置了 {@code zoneId}，分两种情况：
     * <ul>
     *     <li> <b>Java 8</b> 中，则 {@code zoneId} 中代表的时区优先级更高，覆盖原本的时区偏移量；
     *     <li> <b>Java 9+</b>中，则先采用 {@code xxx} 的时区偏移量，再转成 {@code zoneId} 代表的时区。
     * </ul>
     *
     * @param text   date time string
     * @param zoneId zone id
     * @return {@code DateTime}
     */
    public static DateTime parse(CharSequence text, ZoneId zoneId) {
        checkNullNPE(text, args("text"));
        String dateText = DateTimes.formatZhMillis(text.toString());

        String dateFormat = DateTimePattern.forDTF(dateText);
        if (S.isEmpty(dateFormat)) throw new DateTimeParseException(SI.$(DT_PARSE_ERROR_MSG_TPL, text, "`parse(CharSequence, String, ZoneId)`"), text, 0);
        return parse(dateText, dateFormat, zoneId);
    }

    /**
     * Parse string to {@link DateTime} <br>
     *
     * @param text      date time string
     * @param dtPattern date time pattern
     * @return {@code DateTime}
     */
    public static DateTime parse(CharSequence text, String dtPattern) {
        checkNullNPE(text, args("text"));

        if (S.isEmpty(dtPattern)) {
            return parse(text);
        } else {
            Tuple2<CharSequence, String> text_pattern = compatibleFormatter(text, dtPattern);
            text = text_pattern._1;
            dtPattern = text_pattern._2;
            return parse(text, DateTimePattern.getDTF(dtPattern));
        }
    }

    /**
     * Parse string to {@link DateTime} <br>
     * <b>注：</b><br>
     * {@code dtPattern} 如果<b>带有时区标识如：{@code VV}</b>，且设置了 {@code zoneId}，则 {@code dtPattern} 中解析的时区优先级更高。<br>
     * {@code dtPattern} 如果<b>只带时区偏移标识如：{@code xxx}</b>，且设置了 {@code zoneId}，分两种情况：
     * <ul>
     *     <li> <b>Java 8</b> 中，则 {@code zoneId} 中代表的时区优先级更高，覆盖原本的时区偏移量；
     *     <li> <b>Java 9+</b>中，则先采用 {@code xxx} 的时区偏移量，再转成 {@code zoneId} 代表的时区。
     * </ul>
     *
     * @param text      date time string
     * @param dtPattern date time pattern
     * @param zoneId    zone id
     * @return {@code DateTime}
     */
    public static DateTime parse(CharSequence text, String dtPattern, ZoneId zoneId) {
        checkNullNPE(text, args("text"));

        if (S.isEmpty(dtPattern)) {
            return parse(text, zoneId);
        } else {
            Tuple2<CharSequence, String> text_pattern = compatibleFormatter(text, dtPattern);
            text = text_pattern._1;
            dtPattern = text_pattern._2;
            DateTimeFormatter dtf = DateTimePattern.getDTF(dtPattern);
            if (zoneId != null) {   // 不为 null才设置，不要设置成 “默认时区”，因为 dtf 也许已经设置了时区
                dtf = dtf.withZone(zoneId);
            }
            return parse(text, dtf);
        }
    }

    /**
     * Parse string to {@link DateTime} <br>
     *
     * @param text      date time string
     * @param formatter date time formatter
     * @return {@code DateTime}
     */
    public static DateTime parse(CharSequence text, DateTimeFormatter formatter) {
        checkNullNPE(text, args("text"));
        checkNullNPE(formatter, args("formatter"));

        ZonedDateTime parse = null;
        try {
            parse = ZonedDateTime.parse(text, formatter);
        } catch (Exception e) {
            if (e.getCause() != null && e.getCause().getCause() != null) {
                String message = e.getCause().getCause().getMessage();
                if (message.startsWith("Unable to obtain ZoneId from TemporalAccessor")) {
                    parse = ZonedDateTime.parse(text, formatter.withZone(TZ.DEFAULT_ZONE));
                }
            } else {
                throw e;
            }
        }
        return from(parse);
    }

    /**
     * The date time format is compatible with {@code yyyyMMddHHmmssSSS} or {@code yyyyyMMddHHmmssSSS}.<br>
     * <b>NOTE:</b> <br>
     * Java 8 bug: <a href="https://bugs.openjdk.org/browse/JDK-8213027">DateTimeFormatter fails on parsing "yyyyMMddHHmmssSSS"</a>
     *
     * @param text      date time string
     * @param dtPattern date time pattern
     * @return parsed {@code text} and {@code dtPattern}
     */
    private static Tuple2<CharSequence, String> compatibleFormatter(CharSequence text, String dtPattern) {
        if (OS.J_VERSION == null || OS.J_VERSION.lte(JavaVersion.JAVA_8)) {
            String textStr = text.toString();
            if (text.length() == 17 && dtPattern.equals("yyyyMMddHHmmssSSS")) {
                text = textStr.substring(0, 8) + "T" + textStr.substring(8);
                dtPattern = "yyyyMMdd'T'HHmmssSSS";
            } else if (text.length() == 18 && dtPattern.equals("yyyyyMMddHHmmssSSS")) {
                text = textStr.substring(0, 9) + "T" + textStr.substring(9);
                dtPattern = "yyyyyMMdd'T'HHmmssSSS";
            }
        }
        return Tuple.of(text, dtPattern);
    }


    @Override
    public long until(Temporal endExclusive, TemporalUnit unit) {
        checkNullNPE(endExclusive, args("endExclusive"));

        return zonedDateTime.until(from(endExclusive).zonedDateTime, unit);
    }

    @Override
    public boolean isSupported(TemporalUnit unit) {
        return zonedDateTime.isSupported(unit);
    }

    @Override
    public boolean isSupported(TemporalField field) {
        return zonedDateTime.isSupported(field);
    }

    @Override
    public long getLong(TemporalField field) {
        return zonedDateTime.getLong(field);
    }

    /**
     * 获取此 {@link DateTime} 所在的月的名称
     *
     * @return 月份的名称
     */
    public String nameOfMonth() {
        return nameOfMonth(TextStyle.FULL, Locale.getDefault());
    }

    /**
     * 获取此 {@link DateTime} 所在的月的名称
     *
     * @param textStyle 文本样式
     * @param locale    区域设置
     * @return 月份的名称
     */
    public String nameOfMonth(TextStyle textStyle, Locale locale) {
        return getMonth().getDisplayName(textStyle, locale);
    }

    /**
     * 获取当前日期所在周的任意星期几的日期<b>（默认每周的第一天为 星期一）</b>
     *
     * @param dayOfWeek 取星期几的日期
     * @return 当前日期所在周的任意星期几的日期
     */
    public DateTime dtInThisWeek(DayOfWeek dayOfWeek) {
        checkNullNPE(dayOfWeek, args("dayOfWeek"));

        return dtInThisWeek(DayOfWeek.MONDAY, dayOfWeek);
    }

    /**
     * 获取当前日期所在周的任意星期几的日期
     *
     * @param firstDayOfWeek 指定每周的第一天是星期几
     * @param dayOfWeek      取星期几的日期
     * @return 当前日期所在周的任意星期几的日期
     */
    public DateTime dtInThisWeek(DayOfWeek firstDayOfWeek, DayOfWeek dayOfWeek) {
        checkHasNullNPE(args(firstDayOfWeek, dayOfWeek), args("firstDayOfWeek", "dayOfWeek"));

        Tuple2<Integer, Integer> days1 = DateTimes.daysBetween(firstDayOfWeek, localDateTime.getDayOfWeek());
        Tuple2<Integer, Integer> days2 = DateTimes.daysBetween(firstDayOfWeek, dayOfWeek);
        return this.plusDays(days2._2 - days1._2);
    }

    /**
     * 获取当前日期所在周的第一天<b>（默认每周的第一天为 星期一）</b>
     *
     * @return 当前日期所在周的第一天
     */
    public DateTime firstInThisWeek() {
        return firstInThisWeek(DayOfWeek.MONDAY);
    }

    /**
     * 获取当前日期所在周的第一天
     *
     * @param firstDayOfWeek 指定每周的第一天是星期几
     * @return 当前日期所在周的第一天
     */
    public DateTime firstInThisWeek(DayOfWeek firstDayOfWeek) {
        checkNullNPE(firstDayOfWeek, args("firstDayOfWeek"));

        TemporalAdjuster temporalAdjuster = TemporalAdjusters.previousOrSame(firstDayOfWeek);
        return this.with(temporalAdjuster);
    }

    /**
     * 获取当前日期所在周的最后一天<b>（默认每周的第一天为 星期一）</b>
     *
     * @return 当前日期所在周的最后一天
     */
    public DateTime lastInThisWeek() {
        return lastInThisWeek(DayOfWeek.MONDAY);
    }

    /**
     * 获取当前日期所在周的最后一天
     *
     * @param firstDayOfWeek 指定每周的第一天是星期几
     * @return 当前日期所在周的最后一天
     */
    public DateTime lastInThisWeek(DayOfWeek firstDayOfWeek) {
        checkNullNPE(firstDayOfWeek, args("firstDayOfWeek"));

        DayOfWeek lastDayOfWeek = DateTimes.getLastDayOfWeek(firstDayOfWeek);
        TemporalAdjuster temporalAdjuster = TemporalAdjusters.nextOrSame(lastDayOfWeek);
        return this.with(temporalAdjuster);
    }

    /**
     * 获取当前日期所在的周信息
     *
     * @return 周信息
     */
    public WeekInfo getWeekInfo() {
        return WeekInfo.of(localDateTime.toLocalDate()).baseYearMonth();
    }

    /**
     * 获取当前日期所在的周信息
     *
     * @param weekFields 周规则
     * @return 周信息
     */
    public WeekInfo getWeekInfo(WeekFields weekFields) {
        return WeekInfo.of(weekFields, localDateTime.toLocalDate()).baseYearMonth();
    }

    /**
     * 获取此 {@link DateTime} 所在的星期几的名称
     *
     * @return 星期几的名称
     */
    public String nameOfDayOfWeek() {
        return nameOfDayOfWeek(null, null);
    }

    /**
     * 输入区域设置返回此 {@link DateTime} 所在的星期几的名称
     *
     * @param locale 区域设置
     * @return 星期几的名称
     */
    public String nameOfDayOfWeek(Locale locale) {
        return nameOfDayOfWeek(null, locale);
    }

    /**
     * 输入文本样式返回此 {@link DateTime} 所在的星期几的名称
     *
     * @param textStyle 文本样式
     * @return 星期几的名称
     */
    public String nameOfDayOfWeek(TextStyle textStyle) {
        return nameOfDayOfWeek(textStyle, null);
    }

    /**
     * 输入文本样式和区域设置返回此 {@link DateTime} 所在的星期几的名称
     *
     * @param textStyle 文本样式
     * @param locale    区域设置
     * @return 星期几的名称
     */
    public String nameOfDayOfWeek(TextStyle textStyle, Locale locale) {
        return DateTimes.nameOfDayOfWeek(getDayOfWeek(), textStyle, locale);
    }

    /**
     * 获取当前日期所在周的所有7天的日期<b>（默认每周的第一天为 星期一）</b>
     *
     * @return 当前日期所在周的所有7天的日期
     */
    public List<DateTime> allDaysInThisWeek() {
        return allDaysInThisWeek(DayOfWeek.MONDAY);
    }

    /**
     * 获取当前日期所在周的所有7天的日期
     *
     * @param firstDayOfWeek 指定每周的第一天是星期几
     * @return 当前日期所在周的所有7天的日期
     */
    public List<DateTime> allDaysInThisWeek(DayOfWeek firstDayOfWeek) {
        checkNullNPE(firstDayOfWeek, args("firstDayOfWeek"));

        DateTime firstDateTime = firstInThisWeek(firstDayOfWeek);
        return firstDateTime.datesByShift(6, ChronoUnit.DAYS);
    }

    public boolean isBefore(DateTime otherDT) {
        return this.compareTo(otherDT) < 0;
    }

    public boolean isAfter(DateTime otherDT) {
        return this.compareTo(otherDT) > 0;
    }

    public boolean isBeforeOrEquals(DateTime otherDT) {
        return this.compareTo(otherDT) <= 0;
    }

    public boolean isAfterOrEquals(DateTime otherDT) {
        return this.compareTo(otherDT) >= 0;
    }

    /**
     * 判断当前 DateTime 是否在提供的两个 DateTime 之间
     *
     * @param startDT      开始 DateTime
     * @param endDT        结束 DateTime
     * @param intervalType 区间类型。
     * @return 当前 DateTime 在提供的两个 DateTime 之间，则返回 {@code true}，否则返回 {@code false}
     */
    public boolean in(DateTime startDT, DateTime endDT, IntervalType intervalType) {
        if (intervalType == null) intervalType = IntervalType.CLOSED;
        return in(Range.of(startDT, endDT, intervalType));
    }

    /**
     * 判断当前 DateTime 是否在提供的区间中
     *
     * @param dtRange dateTime区间
     * @return 当前 DateTime 在提供的两个 DateTime 之间，则返回 {@code true}，否则返回 {@code false}
     */
    public boolean in(Range<DateTime> dtRange) {
        return dtRange.contains(this);
    }


    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public ZoneOffset getOffset() {
        return offset;
    }

    public ZoneId getZone() {
        return zone;
    }

    public Instant getInstant() {
        return instant;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    public OffsetDateTime getOffsetDateTime() {
        return offsetDateTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;

        if (obj instanceof DateTime) {
            DateTime otherDT = (DateTime) obj;
            int i = this.compareTo(otherDT);
            return i == 0;
        }
        return false;
    }

    @Override
    public int compareTo(DateTime otherDT) {
        checkNullNPE(otherDT, args("otherDT"));

        Instant otherInstant = otherDT.toInstant();
        if (instant.equals(otherInstant)) return 0;
        return instant.isBefore(otherInstant) ? -1 : 1;
    }


    /**
     * Formats this date-time using the specified DateTime Pattern
     *
     * @param dtPattern DateTime Pattern, using {@code yyyy-MM-dd HH:mm:ss.SSS} pattern when {@code dtPattern} is empty
     * @return the formatted date-time string, not null
     */
    public String format(String dtPattern) {
        return format(S.isBlank(dtPattern) ? DateTimes.DTF_MS_STD : DateTimeFormatter.ofPattern(dtPattern));
    }

    /**
     * Formats this date-time using the specified formatter
     *
     * @param formatter DateTimeFormatter
     * @return the formatted date-time string, not null
     */
    public String format(DateTimeFormatter formatter) {
        return zonedDateTime.format(formatter);
    }

    /**
     * Formats this date-time using {@code yyyy-MM-dd HH:mm:ss} pattern
     *
     * @return the formatted date-time string, not null
     */
    public String formatYMDHMS() {
        return format(DateTimes.DTF_STD);
    }

    /**
     * 详细的 toString
     *
     * @return 时间格式化输出
     */
    public String dtDetail() {
        return DateTimes.dtDetail(zonedDateTime);
    }

    @Override
    public String toString() {
        return DateTimes.dtSimple(zonedDateTime);
    }

    /**
     * A hash code for this DateTime.
     *
     * @return hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(instant, zonedDateTime);
    }

}
