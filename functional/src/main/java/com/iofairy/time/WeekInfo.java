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
import com.iofairy.top.S;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.ValueRange;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

import static com.iofairy.validator.Preconditions.*;

/**
 * Week information for the specified {@link WeekFields} and {@link LocalDate}
 *
 * @since 0.6.0
 */
public class WeekInfo {
    static final Pattern Y = Pattern.compile("Y(?!'(?<='Y'))");
    static final Pattern M = Pattern.compile("M(?!'(?<='M'))");
    static final Pattern W = Pattern.compile("W(?!'(?<='W'))");
    static final Pattern w = Pattern.compile("w(?!'(?<='w'))");
    static final Pattern QYQ = Pattern.compile("'Y'");
    static final Pattern QMQ = Pattern.compile("'M'");
    static final Pattern QWQ = Pattern.compile("'W'");
    static final Pattern qwq = Pattern.compile("'w'");
    static final Pattern WW = Pattern.compile("WW");
    static final Pattern ww = Pattern.compile("ww");

    /**
     * 周跨年时，周前面部分所在的年
     */
    int minYear;
    /**
     * 周跨年时，周后面部分所在的年
     */
    int maxYear;
    /**
     * 周跨月时，周前面部分所在的月
     */
    int minMonth;
    /**
     * 周跨月时，周后面部分所在的月
     */
    int maxMonth;
    /**
     * 跨年的周在 {@link #minYear} 年的天数
     */
    int daysInMinYear;
    /**
     * 跨年的周在 {@link #maxYear} 年的天数
     */
    int daysInMaxYear;
    /**
     * 跨年的周在 {@link #minMonth} 月的天数
     */
    int daysInMinMonth;
    /**
     * 跨年的周在 {@link #maxMonth} 月的天数
     */
    int daysInMaxMonth;
    /**
     * 一个周是否跨越两个月份
     */
    Boolean isCrossMonth;
    /**
     * 一个周是否跨越两个年份
     */
    Boolean isCrossYear;

    /**
     * 周规则信息。<br>
     * 如果此字段为 {@code null}，可计算周相对于月的信息。不能计算周相对于年的信息。
     * <b>为 {@code null} 时，按每月{@code 1}号开始，7天为一周期计算周。一个月最后一周可能不足 7天。（不跨月）</b>
     */
    WeekFields weekFields;
    LocalDate localDate;
    LocalDate startDayOfWeek;
    LocalDate endDayOfWeek;
    /**
     * DayOfWeek 内部是采用默认的 weekFields 来进行相关计算
     */
    DayOfWeek dayOfWeek;

    /**
     * 一周中的第几天（根据指定的 {@link #weekFields} 计算一周中的天数）
     */
    int dayIndexOfWeek;
    /*
     * 以 localDate 所在的年或月计算周信息
     */
    /**
     * {@link  #localDate} 所在的年
     */
    int weekYear;
    /**
     * 基于当前 {@link  #localDate} 所在的年计算周数，并不一定符合 {@link  #weekFields} 的规则
     */
    int weekOfYear;
    /**
     * {@link  #localDate} 所在的月
     */
    int weekMonth;
    /**
     * 基于当前 {@link  #localDate} 所在的月计算周数，并不一定符合 {@link  #weekFields} 的规则
     */
    int weekOfMonth;
    /*
     * 实际周所属的年或月的信息，可能与 localDate 并不属于同一年或同一月
     */
    /**
     * {@link  #localDate} 所在的周真正所属的年，满足 {@link  #weekFields} 的规则，可能与 {@link  #localDate} 在不同的年
     */
    int weekBasedYear;
    /**
     * 基于当前 {@link  #weekFields} 的规则计算 {@link  #localDate} 在 {@link  #weekBasedYear} 中的周数
     */
    int weekOfWeekBasedYear;
    /**
     * {@link  #localDate} 所在的周真正所属的月，满足 {@link  #weekFields} 的规则，可能与 {@link  #localDate} 在不同的月份
     */
    int weekBasedMonth;
    /**
     * 基于当前 {@link  #weekFields} 的规则计算 {@link  #localDate} 在 {@link  #weekBasedMonth} 中的周数
     */
    int weekOfWeekBasedMonth;

    public WeekInfo(LocalDate localDate) {
        this(DTC.MONDAY_MIN7, localDate);
    }

    public WeekInfo(WeekFields weekFields, LocalDate localDate) {
        checkNullNPE(localDate, args("localDate"));

        this.dayOfWeek = localDate.getDayOfWeek();
        this.weekFields = weekFields;
        this.localDate = localDate;
        if (weekFields == null) {
            int dayOfMonth = localDate.getDayOfMonth();
            int remainder = dayOfMonth % 7;
            this.startDayOfWeek = localDate.minusDays((remainder + 6) % 7);
            this.endDayOfWeek = this.startDayOfWeek.plusDays(6);
            if (this.startDayOfWeek.getMonthValue() != this.endDayOfWeek.getMonthValue()) {
                this.endDayOfWeek = this.startDayOfWeek.withDayOfMonth(startDayOfWeek.lengthOfMonth());     // 不跨月
            }
            this.dayIndexOfWeek = remainder == 0 ? 7 : remainder;

            this.minYear = localDate.getYear();
            this.maxYear = this.minYear;
            this.minMonth = this.localDate.getMonthValue();
            this.maxMonth = this.minMonth;
            this.isCrossYear = false;
            this.isCrossMonth = false;

            int days = this.endDayOfWeek.getDayOfMonth() - this.startDayOfWeek.getDayOfMonth() + 1;
            this.daysInMinYear = days;
            this.daysInMaxYear = days;
            this.daysInMinMonth = days;
            this.daysInMaxMonth = days;
        } else {
            this.startDayOfWeek = localDate.with(TemporalAdjusters.previousOrSame(weekFields.getFirstDayOfWeek()));
            this.endDayOfWeek = this.startDayOfWeek.plusDays(6);
            this.dayIndexOfWeek = localDate.get(weekFields.dayOfWeek());

            this.minYear = this.startDayOfWeek.getYear();
            this.maxYear = this.endDayOfWeek.getYear();
            this.minMonth = this.startDayOfWeek.getMonthValue();
            this.maxMonth = this.endDayOfWeek.getMonthValue();
            this.isCrossYear = this.minYear != this.maxYear;
            this.isCrossMonth = this.minMonth != this.maxMonth;

            if (isCrossMonth) {         // 跨月
                int maxMonthDays = this.endDayOfWeek.getDayOfMonth();
                int minMonthDays = 7 - maxMonthDays;
                this.daysInMinMonth = minMonthDays;
                this.daysInMaxMonth = maxMonthDays;
                if (isCrossYear) {
                    this.daysInMinYear = minMonthDays;
                    this.daysInMaxYear = maxMonthDays;
                } else {
                    this.daysInMinYear = 7;
                    this.daysInMaxYear = 7;
                }
            } else {                    // 不跨月
                this.daysInMinYear = 7;
                this.daysInMaxYear = 7;
                this.daysInMinMonth = 7;
                this.daysInMaxMonth = 7;
            }
        }
    }

    public static WeekInfo of(DateTime dateTime) {
        return new WeekInfo(dateTime.toLocalDate());
    }

    public static WeekInfo of(LocalDate localDate) {
        return new WeekInfo(localDate);
    }

    public static WeekInfo of(Date date) {
        return of(DateTime.of(date));
    }

    public static WeekInfo of(WeekFields weekFields, DateTime dateTime) {
        return new WeekInfo(weekFields, dateTime.toLocalDate());
    }

    public static WeekInfo of(WeekFields weekFields, LocalDate localDate) {
        return new WeekInfo(weekFields, localDate);
    }

    public static WeekInfo of(WeekFields weekFields, Date date) {
        return of(weekFields, DateTime.of(date));
    }

    /**
     * 获取基于月的周信息
     *
     * @return WeekInfo
     */
    public WeekInfo baseMonth() {
        this.weekMonth = localDate.getMonthValue();
        this.weekYear = localDate.getYear();

        if (weekFields == null) {
            int dayOfMonth = localDate.getDayOfMonth();
            int week = dayOfMonth / 7;
            int remainder = dayOfMonth % 7;
            if (remainder != 0) week = week + 1;    // 如果不能整除，则 +1 周

            this.weekOfMonth = week;
            this.weekBasedMonth = this.weekMonth;
            this.weekOfWeekBasedMonth = this.weekOfMonth;
            this.weekBasedYear = this.weekYear;

        } else {
            this.weekOfMonth = localDate.get(weekFields.weekOfMonth());

            if (weekOfMonth == 1 || weekOfMonth == 2 || weekOfMonth == 3) {
                this.weekBasedMonth = this.weekMonth;
                this.weekOfWeekBasedMonth = this.weekOfMonth;
                this.weekBasedYear = this.weekYear;
            } else {
                if (weekOfMonth == 0) {     // 跨月
                    LocalDate ld = localDate.minusMonths(1);
                    this.weekBasedMonth = ld.getMonthValue();
                    this.weekBasedYear = ld.getYear();
                    ld = ld.withDayOfMonth(ld.lengthOfMonth());
                    this.weekOfWeekBasedMonth = ld.get(weekFields.weekOfMonth());
                } else {
                    this.weekBasedMonth = this.weekMonth;
                    this.weekOfWeekBasedMonth = this.weekOfMonth;
                    this.weekBasedYear = this.weekYear;

                    if (this.isCrossMonth) {        // 跨月
                        LocalDate ld = localDate.plusMonths(1).withDayOfMonth(1);
                        int weekOfThisMonth = ld.get(weekFields.weekOfMonth());
                        if (weekOfThisMonth != 0) {     // 跨月，且周不属于上个月
                            this.weekBasedMonth = ld.getMonthValue();
                            this.weekOfWeekBasedMonth = weekOfThisMonth;
                            this.weekBasedYear = ld.getYear();
                        }
                    }
                }
            }
        }

        return this;
    }

    /**
     * 获取基于年的周信息
     *
     * @return WeekInfo
     */
    public WeekInfo baseYear() {
        if (weekFields == null) {
            this.weekYear = localDate.getYear();
            this.weekOfYear = 0;
            this.weekBasedYear = this.weekYear;
            this.weekOfWeekBasedYear = 0;
        } else {
            this.weekYear = localDate.getYear();
            this.weekOfYear = localDate.get(weekFields.weekOfYear());
            this.weekBasedYear = this.weekBasedYear == 0 ? localDate.get(weekFields.weekBasedYear()) : this.weekBasedYear;
            this.weekOfWeekBasedYear = localDate.get(weekFields.weekOfWeekBasedYear());
        }

        return this;
    }

    /**
     * 获取基于年和月的周信息
     *
     * @return WeekInfo
     */
    public WeekInfo baseYearMonth() {
        baseMonth();
        baseYear();
        return this;
    }


    /**
     * 取指定年月的第 {@code weekNo} 周的第一天日期
     *
     * @param year   年
     * @param month  月
     * @param weekNo 周序号
     * @return 指定年月的第 {@code weekNo} 周的第一天日期
     */
    public static LocalDate baseMonth(int year, int month, int weekNo) {
        return baseMonth(DTC.MONDAY_MIN7, YearMonth.of(year, month), weekNo);
    }

    /**
     * 取指定年月的第 {@code weekNo} 周的第一天日期
     *
     * @param weekFields 周字段规则
     * @param year       年
     * @param month      月
     * @param weekNo     周序号
     * @return 指定年月的第 {@code weekNo} 周的第一天日期
     */
    public static LocalDate baseMonth(WeekFields weekFields, int year, int month, int weekNo) {
        return baseMonth(weekFields, YearMonth.of(year, month), weekNo);
    }

    /**
     * 取指定年月的第 {@code weekNo} 周的第一天日期
     *
     * @param yearMonth 年月
     * @param weekNo    周序号
     * @return 指定年月的第 {@code weekNo} 周的第一天日期
     */
    public static LocalDate baseMonth(YearMonth yearMonth, int weekNo) {
        return baseMonth(DTC.MONDAY_MIN7, yearMonth, weekNo);
    }

    /**
     * 取指定年月的第 {@code weekNo} 周的第一天日期
     *
     * @param weekFields 周字段规则
     * @param yearMonth  年月
     * @param weekNo     周序号
     * @return 指定年月的第 {@code weekNo} 周的第一天日期
     */
    public static LocalDate baseMonth(WeekFields weekFields, YearMonth yearMonth, int weekNo) {
        checkOutOfBounds(weekNo < 0 || weekNo > 6, weekNo,
                OS.IS_ZH_LANG ? "指定月中的周序号时，参数`weekNo`的取值范围为：[0, 6]。"
                        : "The `weekNo`'s range is [0, 6] when getting the week of the month. ");

        ValueRange valueRange = weekRangeInMonth(weekFields, yearMonth);
        int minimum = (int) valueRange.getMinimum();  // 0、1
        int maximum = (int) valueRange.getMaximum();  // 4、5、6
        if (weekNo < minimum) weekNo = minimum;
        if (weekNo > maximum) weekNo = maximum;

        LocalDate firstDayOfMonth = yearMonth.atDay(1);

        if (weekFields == null) return firstDayOfMonth.plusWeeks(weekNo - 1);

        DayOfWeek firstDayOfWeek = weekFields.getFirstDayOfWeek();
        LocalDate firstDayInThisWeek = firstDayOfMonth.with(TemporalAdjusters.previousOrSame(firstDayOfWeek));

        return firstDayInThisWeek.plusWeeks(weekNo - minimum);
    }

    /**
     * 取指定年的第 {@code weekNo} 周的第一天日期
     *
     * @param year   年
     * @param weekNo 周序号
     * @return 指定年的第 {@code weekNo} 周的第一天日期
     */
    public static LocalDate baseYear(int year, int weekNo) {
        return baseYear(DTC.MONDAY_MIN7, year, weekNo);
    }

    /**
     * 取指定年的第 {@code weekNo} 周的第一天日期
     *
     * @param weekFields 周字段规则
     * @param year       年
     * @param weekNo     周序号
     * @return 指定年的第 {@code weekNo} 周的第一天日期
     */
    public static LocalDate baseYear(WeekFields weekFields, int year, int weekNo) {
        checkNullNPE(weekFields, args("weekFields"));
        checkOutOfBounds(weekNo < 0 || weekNo > 54, weekNo,
                OS.IS_ZH_LANG ? "指定年中的周序号时，参数`weekNo`的取值范围为：[0, 54]。"
                        : "The `weekNo`'s range is [0, 54] when getting the week of the year.");

        ValueRange valueRange = weekRangeInYear(weekFields, year);
        int minimum = (int) valueRange.getMinimum();  // 0、1
        int maximum = (int) valueRange.getMaximum();  // 52、53、54
        if (weekNo < minimum) weekNo = minimum;
        if (weekNo > maximum) weekNo = maximum;

        DayOfWeek firstDayOfWeek = weekFields.getFirstDayOfWeek();
        LocalDate firstDayOfYear = LocalDate.of(year, 1, 1);
        LocalDate firstDayInThisWeek = firstDayOfYear.with(TemporalAdjusters.previousOrSame(firstDayOfWeek));

        return firstDayInThisWeek.plusWeeks(weekNo - minimum);
    }

    /**
     * 指定月的周序号范围
     *
     * @param weekFields 周字段规则
     * @param year       年
     * @param month      月
     * @return 周序号范围
     */
    public static ValueRange weekRangeInMonth(WeekFields weekFields, int year, int month) {
        return weekRangeInMonth(weekFields, YearMonth.of(year, month));
    }

    /**
     * 指定月的周序号范围
     *
     * @param weekFields 周字段规则
     * @param yearMonth  年月
     * @return 周序号范围
     */
    public static ValueRange weekRangeInMonth(WeekFields weekFields, YearMonth yearMonth) {
        checkNullNPE(yearMonth, args("yearMonth"));

        if (weekFields == null) {
            int daysOfMonth = yearMonth.lengthOfMonth();
            int maxWeeks = daysOfMonth / 7;
            if (daysOfMonth % 7 != 0) maxWeeks = maxWeeks + 1;    // 如果不能整除，则 +1 周
            return ValueRange.of(1, maxWeeks);
        } else {
            return ValueRange.of(yearMonth.atDay(1).get(weekFields.weekOfMonth()), yearMonth.atEndOfMonth().get(weekFields.weekOfMonth()));
        }
    }

    /**
     * 指定年的周序号范围
     *
     * @param weekFields 周字段规则
     * @param year       年
     * @return 周序号范围
     */
    public static ValueRange weekRangeInYear(WeekFields weekFields, int year) {
        checkNullNPE(weekFields, args("weekFields"));

        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);

        return ValueRange.of(startDate.get(weekFields.weekOfYear()), endDate.get(weekFields.weekOfYear()));
    }

    /**
     * 格式化 {@code weekBased} 相关的周信息，默认格式形如：2023-07-W01
     *
     * @return 格式化后的周信息字符串
     */
    public String format() {
        return format(null);
    }

    /**
     * Format week info about {@code weekBased} to string. <br><br>
     * <p>
     * The following pattern letters are defined:
     * <pre>
     *  Symbol  Meaning                     Presentation      Examples
     *  ------  -------                     ------------      -------
     *   M       week-based-month            number            07
     *   Y       week-based-year             year              1996
     *   w       week-of-week-based-year     number            27
     *   W       week-of-week-based-month    number            4
     *
     *   '       escape for text             delimiter
     *   ''      single quote                literal           '
     * </pre>
     * <p>
     *
     * @param pattern the pattern to use
     * @return the formatted string based on the pattern, not null
     */
    public String format(String pattern) {
        String weekBasedYearStr = String.format("%04d", weekBasedYear);
        String weekOfWeekBasedYearStr = String.format("%02d", weekOfWeekBasedYear);
        String weekBasedMonthStr = String.format("%02d", weekBasedMonth);
        String weekOfWeekBasedMonthStr = String.format("%02d", weekOfWeekBasedMonth);

        if (S.isBlank(pattern)) return weekBasedYearStr + "-" + weekBasedMonthStr + "-W" + weekOfWeekBasedMonthStr;

        // Here only perform simple regex substitution, not suitable for complex scenarios.
        pattern = WW.matcher(pattern).replaceAll(weekOfWeekBasedMonthStr);
        pattern = ww.matcher(pattern).replaceAll(weekOfWeekBasedYearStr);
        pattern = Y.matcher(pattern).replaceAll(weekBasedYearStr);
        pattern = M.matcher(pattern).replaceAll(weekBasedMonthStr);
        pattern = W.matcher(pattern).replaceAll(weekOfWeekBasedMonth + "");
        pattern = w.matcher(pattern).replaceAll(weekOfWeekBasedYear + "");
        pattern = QYQ.matcher(pattern).replaceAll("Y");
        pattern = QMQ.matcher(pattern).replaceAll("M");
        pattern = QWQ.matcher(pattern).replaceAll("W");
        pattern = qwq.matcher(pattern).replaceAll("w");

        return pattern;
    }

    /**
     * 格式化周信息，默认格式形如：2023-07-W01
     *
     * @return 格式化后的周信息字符串
     */
    public String formatWeek() {
        return formatWeek(null);
    }

    /**
     * Format week info to string. <br><br>
     * <p>
     * The following pattern letters are defined:
     * <pre>
     *  Symbol  Meaning                     Presentation      Examples
     *  ------  -------                     ------------      -------
     *   M       week-month                  number            07
     *   Y       week-year                   year              1996
     *   w       week-of-year                number            27
     *   W       week-of-month               number            4
     *
     *   '       escape for text             delimiter
     *   ''      single quote                literal           '
     * </pre>
     * <p>
     *
     * @param pattern the pattern to use
     * @return the formatted string based on the pattern, not null
     */
    public String formatWeek(String pattern) {
        String weekYearStr = String.format("%04d", weekYear);
        String weekOfYearStr = String.format("%02d", weekOfYear);
        String weekMonthStr = String.format("%02d", weekMonth);
        String weekOfMonthStr = String.format("%02d", weekOfMonth);

        if (S.isBlank(pattern)) return weekYearStr + "-" + weekMonthStr + "-W" + weekOfMonthStr;

        // Here only perform simple regex substitution, not suitable for complex scenarios.
        pattern = WW.matcher(pattern).replaceAll(weekOfMonthStr);
        pattern = ww.matcher(pattern).replaceAll(weekOfYearStr);
        pattern = Y.matcher(pattern).replaceAll(weekYearStr);
        pattern = M.matcher(pattern).replaceAll(weekMonthStr);
        pattern = W.matcher(pattern).replaceAll(weekOfMonth + "");
        pattern = w.matcher(pattern).replaceAll(weekOfYear + "");
        pattern = QYQ.matcher(pattern).replaceAll("Y");
        pattern = QMQ.matcher(pattern).replaceAll("M");
        pattern = QWQ.matcher(pattern).replaceAll("W");
        pattern = qwq.matcher(pattern).replaceAll("w");

        return pattern;
    }


    /**
     * 获取此{@link #localDate}所在的星期几的名称
     *
     * @return 星期几的名称
     */
    public String nameOfDayOfWeek() {
        return nameOfDayOfWeek(null, null);
    }

    /**
     * 输入区域设置返回此{@link #localDate}所在的星期几的名称
     *
     * @param locale 区域设置
     * @return 星期几的名称
     */
    public String nameOfDayOfWeek(Locale locale) {
        return nameOfDayOfWeek(null, locale);
    }

    /**
     * 输入文本样式返回此{@link #localDate}所在的星期几的名称
     *
     * @param textStyle 文本样式
     * @return 星期几的名称
     */
    public String nameOfDayOfWeek(TextStyle textStyle) {
        return nameOfDayOfWeek(textStyle, null);
    }

    /**
     * 输入文本样式和区域设置返回此{@link #localDate}所在的星期几的名称
     *
     * @param textStyle 文本样式
     * @param locale    区域设置
     * @return 星期几的名称
     */
    public String nameOfDayOfWeek(TextStyle textStyle, Locale locale) {
        return DateTimes.nameOfDayOfWeek(getDayOfWeek(), textStyle, locale);
    }

    public int getMinYear() {
        return minYear;
    }

    public int getMaxYear() {
        return maxYear;
    }

    public int getMinMonth() {
        return minMonth;
    }

    public int getMaxMonth() {
        return maxMonth;
    }

    public int getDaysInMinYear() {
        return daysInMinYear;
    }

    public int getDaysInMaxYear() {
        return daysInMaxYear;
    }

    public int getDaysInMinMonth() {
        return daysInMinMonth;
    }

    public int getDaysInMaxMonth() {
        return daysInMaxMonth;
    }

    public Boolean getCrossMonth() {
        return isCrossMonth;
    }

    public Boolean getCrossYear() {
        return isCrossYear;
    }

    public WeekFields getWeekFields() {
        return weekFields;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public LocalDate getStartDayOfWeek() {
        return startDayOfWeek;
    }

    public LocalDate getEndDayOfWeek() {
        return endDayOfWeek;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public int getDayIndexOfWeek() {
        return dayIndexOfWeek;
    }

    public int getWeekYear() {
        return weekYear;
    }

    public int getWeekOfYear() {
        return weekOfYear;
    }

    public int getWeekMonth() {
        return weekMonth;
    }

    public int getWeekOfMonth() {
        return weekOfMonth;
    }

    public int getWeekBasedYear() {
        return weekBasedYear;
    }

    public int getWeekOfWeekBasedYear() {
        return weekOfWeekBasedYear;
    }

    public int getWeekBasedMonth() {
        return weekBasedMonth;
    }

    public int getWeekOfWeekBasedMonth() {
        return weekOfWeekBasedMonth;
    }

    @Override
    public String toString() {
        String weekOfYearStr = String.format("%02d", weekOfYear);
        String weekMonthStr = String.format("%02d", weekMonth);
        String weekOfMonthStr = String.format("%02d", weekOfMonth);
        String weekOfWeekBasedYearStr = String.format("%02d", weekOfWeekBasedYear);
        String weekBasedMonthStr = String.format("%02d", weekBasedMonth);
        String weekOfWeekBasedMonthStr = String.format("%02d", weekOfWeekBasedMonth);
        String minMonthStr = String.format("%02d", minMonth);
        String maxMonthStr = String.format("%02d", maxMonth);
        String isCrossYearStr = isCrossYear == null || isCrossYear ? isCrossYear + " " : "false";
        String isCrossMonthStr = isCrossMonth == null || isCrossMonth ? isCrossMonth + " " : "false";

        String weekFieldsStr = (weekFields == null ? null : weekFields.getFirstDayOfWeek()) + ", ";
        weekFieldsStr = weekFieldsStr + S.padLeftChars((weekFields == null ? null : weekFields.getMinimalDaysInFirstWeek()) + "", ' ', 11 - weekFieldsStr.length());
        String displayName = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault());
        String nameOfDayOfWeek = S.padRightChars(displayName, ' ', 3);

        return "WeekInfo{" +
                "[dayOfWeek, firstDayOfWeek, minDaysInFirstWeek]=(" + nameOfDayOfWeek + ", " + weekFieldsStr + ")" +
                ", [localDate<dayIndexOfWeek>]=(" + localDate + "<" + dayIndexOfWeek + ">" + ", " + startDayOfWeek + " ~ " + endDayOfWeek + ")" +
                ", [minYear<days>, maxYear<days>, isCrossYear]=(" + minYear + "<" + daysInMinYear + ">" + ", " + maxYear + "<" + daysInMaxYear + ">" + ", " + isCrossYearStr + ")" +
                ", [minMonth<days>, maxMonth<days>, isCrossMonth]=(" + minMonthStr + "<" + daysInMinMonth + ">" + ", " + maxMonthStr + "<" + daysInMaxMonth + ">" + ", " + isCrossMonthStr + ")" +
                ", [weekYear, weekBasedYear]=(" + weekYear + "-W" + weekOfYearStr + ", " + weekBasedYear + "-W" + weekOfWeekBasedYearStr + ")" +
                ", [weekMonth, weekBasedMonth]=(" + weekMonthStr + "-W" + weekOfMonthStr + ", " + weekBasedMonthStr + "-W" + weekOfWeekBasedMonthStr + ")" +
                '}';

    }

}
