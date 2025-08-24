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
import com.iofairy.si.SI;
import com.iofairy.top.G;
import com.iofairy.tuple.Tuple;
import com.iofairy.tuple.Tuple2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static com.iofairy.validator.Preconditions.*;
import static java.util.concurrent.TimeUnit.*;

/**
 * Stopwatch <br>
 * 秒表
 *
 * @since 0.6.0
 */
public class Stopwatch {
    private long startTick;
    private long lastMarkTick;
    private long stopTick;
    private long elapsedNanos;
    private boolean isRunning;
    private int markIndex = 0;
    private final Map<String, Integer> MARK_INDEX_MAP = new LinkedHashMap<>();
    private final Map<Integer, String> INDEX_MARK_MAP = new LinkedHashMap<>();
    private final Map<Object, Long> MARK_TICK_MAP = new HashMap<>();
    private final String START_MARK = "START";
    private final String STOP_MARK = "STOP";
    private final Pattern MARK_PATTERN = Pattern.compile("MARK\\d+");

    private static final String NANOS_UNIT = OS.IS_ZH_LANG ? "纳秒" : "ns";
    private static final String MICROS_UNIT = OS.IS_ZH_LANG ? "微秒" : "μs";
    private static final String MILLIS_UNIT = OS.IS_ZH_LANG ? "毫秒" : "ms";
    private static final String SECONDS_UNIT = OS.IS_ZH_LANG ? "秒" : "s";
    private static final String MINUTES_UNIT = OS.IS_ZH_LANG ? "分" : "min";
    private static final String HOURS_UNIT = OS.IS_ZH_LANG ? "时" : "h";
    private static final String DAYS_UNIT = OS.IS_ZH_LANG ? "天" : "d";

    private final static Map<TimeUnit, String> UNIT_STRING_MAP = new HashMap<TimeUnit, String>() {{
        put(NANOSECONDS, NANOS_UNIT);
        put(MICROSECONDS, MICROS_UNIT);
        put(MILLISECONDS, MILLIS_UNIT);
        put(SECONDS, SECONDS_UNIT);
        put(MINUTES, MINUTES_UNIT);
        put(HOURS, HOURS_UNIT);
        put(DAYS, DAYS_UNIT);
    }};

    Stopwatch() {
    }

    public static Stopwatch run() {
        return create().start();
    }

    public static Stopwatch create() {
        return new Stopwatch();
    }

    public Stopwatch start() {
        checkState(isRunning, "This stopwatch is already running! ");
        this.isRunning = true;
        this.elapsedNanos = 0;
        this.startTick = System.nanoTime();

        nanoTimeMark(0, this.startTick, START_MARK);

        return this;
    }

    public Stopwatch stop() {
        checkState(!isRunning, "This stopwatch is already stopped! ");
        isRunning = false;
        this.stopTick = System.nanoTime();
        elapsedNanos = stopTick - startTick;

        nanoTimeMark(++markIndex, stopTick, STOP_MARK);

        return this;
    }

    /**
     * 标记点
     *
     * @param name 标记点名称
     * @return Stopwatch
     */
    public Stopwatch mark(String name) {
        checkState(!isRunning, "This stopwatch is already stopped! ");
        checkNullNPE(name, args("name"));
        checkArgument(name.equalsIgnoreCase("START") || name.equalsIgnoreCase("STOP") || MARK_PATTERN.matcher(name.toUpperCase()).matches(),
                "The words 'START' and 'STOP' and ('MARK1', 'MARK2', 'MARK3', 'MARKn' ...) are reserved and can't be used. ");
        checkArgument(MARK_TICK_MAP.containsKey(name), "The mark point [${…}] already exists. ", name);

        nanoTimeMark(++markIndex, System.nanoTime(), name);

        return this;
    }

    public Stopwatch mark() {
        checkState(!isRunning, "This stopwatch is already stopped! ");

        nanoTimeMark(++markIndex, System.nanoTime(), "MARK" + markIndex);
        return this;
    }

    private void nanoTimeMark(int markIndex, long tick, String name) {
        this.lastMarkTick = tick;

        MARK_TICK_MAP.put(markIndex, tick);
        MARK_TICK_MAP.put(name, tick);
        MARK_INDEX_MAP.put(name, markIndex);
        INDEX_MARK_MAP.put(markIndex, name);
    }

    /**
     * 重置且重启
     *
     * @return Stopwatch
     */
    public Stopwatch reset() {
        clearMarks(true);
        this.isRunning = false;
        this.stopTick = 0;
        this.markIndex = 0;
        start();
        return this;
    }

    private long elapsedNanos() {
        return isRunning ? System.nanoTime() - startTick : elapsedNanos;
    }

    public long elapsed(TimeUnit unit) {
        return unit.convert(elapsedNanos(), NANOSECONDS);
    }

    public double elapsedExact(TimeUnit unit) {
        return getElapsedDouble(unit, elapsedNanos());
    }

    public Duration elapsed() {
        return Duration.ofNanos(elapsedNanos());
    }

    private long elapsedLastNanos() {
        return isRunning ? System.nanoTime() - lastMarkTick : stopTick - lastMarkTick;
    }

    /**
     * 离上次标记点所经过的时间，如果 {@code isRunning == false}，则返回停止点到上次标记点的时间
     *
     * @param unit 时间单位
     * @return 离上次标记点所经过的时间，如果 {@code isRunning == false}，则返回停止点到上次标记点的时间
     */
    public long elapsedLast(TimeUnit unit) {
        return unit.convert(elapsedLastNanos(), NANOSECONDS);
    }

    /**
     * 离上次标记点所经过的时间（精确到小数点后4位），如果 {@code isRunning == false}，则返回停止点到上次标记点的时间
     *
     * @param unit 时间单位
     * @return 离上次标记点所经过的时间（精确到小数点后4位），如果 {@code isRunning == false}，则返回停止点到上次标记点的时间
     */
    public double elapsedLastExact(TimeUnit unit) {
        return getElapsedDouble(unit, elapsedLastNanos());
    }

    /**
     * 离上次标记点所经过的时间，如果 {@code isRunning == false}，则返回停止点到上次标记点的时间
     *
     * @return 离上次标记点所经过的时间，如果 {@code isRunning == false}，则返回停止点到上次标记点的时间
     */
    public Duration elapsedLast() {
        return Duration.ofNanos(elapsedLastNanos());
    }

    /**
     * 离上次标记点所经过的时间格式化后的字符串，如果 {@code isRunning == false}，则返回停止点到上次标记点的时间格式化后的字符串
     *
     * @return 离上次标记点所经过的时间格式化后的字符串，如果 {@code isRunning == false}，则返回停止点到上次标记点的时间格式化后的字符串
     */
    public String elapsedLastString() {
        return elapsedString(elapsedLastNanos());
    }


    /**
     * 离上次标记点所经过的时间并<b>打个标记点{@link #mark()}</b>，如果 {@code isRunning == false}，则返回停止点到上次标记点的时间
     *
     * @param unit 时间单位
     * @return 离上次标记点所经过的时间，如果 {@code isRunning == false}，则返回停止点到上次标记点的时间
     */
    public long elapsedLastAndMark(TimeUnit unit) {
        long elapsed = elapsedLast(unit);
        mark();
        return elapsed;
    }

    /**
     * 离上次标记点所经过的时间（精确到小数点后4位）并<b>打个标记点{@link #mark()}</b>，如果 {@code isRunning == false}，则返回停止点到上次标记点的时间
     *
     * @param unit 时间单位
     * @return 离上次标记点所经过的时间（精确到小数点后4位），如果 {@code isRunning == false}，则返回停止点到上次标记点的时间
     */
    public double elapsedLastExactAndMark(TimeUnit unit) {
        double elapsedLastExact = elapsedLastExact(unit);
        mark();
        return elapsedLastExact;
    }

    /**
     * 离上次标记点所经过的时间并<b>打个标记点{@link #mark()}</b>，如果 {@code isRunning == false}，则返回停止点到上次标记点的时间
     *
     * @return 离上次标记点所经过的时间，如果 {@code isRunning == false}，则返回停止点到上次标记点的时间
     */
    public Duration elapsedLastAndMark() {
        Duration elapsedLast = elapsedLast();
        mark();
        return elapsedLast;
    }

    /**
     * 离上次标记点所经过的时间格式化后的字符串并<b>打个标记点{@link #mark()}</b>，如果 {@code isRunning == false}，则返回停止点到上次标记点的时间格式化后的字符串
     *
     * @return 离上次标记点所经过的时间格式化后的字符串，如果 {@code isRunning == false}，则返回停止点到上次标记点的时间格式化后的字符串
     */
    public String elapsedLastStringAndMark() {
        String elapsedString = elapsedLastString();
        mark();
        return elapsedString;
    }


    /**
     * 根据起止标记点，获取时长
     *
     * @param from 开始
     * @param to   结束
     * @return 时长
     */
    public Elapsed elapsed(Object from, Object to) {
        Tuple2<Object, Object> fromTo = adjustFromTo(from, to);
        from = fromTo._1;
        to = fromTo._2;

        int fromIndex;
        int toIndex;
        String fromMark;
        String toMark;
        long elapsedNanos = MARK_TICK_MAP.get(to) - MARK_TICK_MAP.get(from);

        /*
         * from 与 to 的类型判断
         */
        if (from instanceof Integer) {
            fromIndex = (int) from;
            fromMark = INDEX_MARK_MAP.get(from);
        } else {
            fromIndex = MARK_INDEX_MAP.get(from);
            fromMark = (String) from;
        }

        if (to instanceof Integer) {
            toIndex = (int) to;
            toMark = INDEX_MARK_MAP.get(to);
        } else {
            toIndex = MARK_INDEX_MAP.get(to);
            toMark = (String) to;
        }

        return new Elapsed(fromIndex, toIndex, fromMark, toMark, elapsedNanos);
    }

    /**
     * 调整 from 和 to 的标记点的顺序（如果 from标记点的时间 {@code >} to标记点的时间，则对调 from和to 标记点 返回）
     *
     * @param from 起始
     * @param to   终止
     * @return 调整后的起止标记点
     */
    public Tuple2<Object, Object> adjustFromTo(Object from, Object to) {
        checkArgument(!(from instanceof Integer || from instanceof String) || !(to instanceof Integer || to instanceof String),
                "The types of parameters `from` and `to` must be either [String] or [Integer]! ");
        checkArgument(!MARK_TICK_MAP.containsKey(from), "Not found for this mark point [${…}]. ", from);
        checkArgument(!MARK_TICK_MAP.containsKey(to), "Not found for this mark point [${…}]. ", to);

        return MARK_TICK_MAP.get(to) - MARK_TICK_MAP.get(from) >= 0 ? Tuple.of(from, to) : Tuple.of(to, from);
    }

    /**
     * 获取标记点
     *
     * @return 标记点
     */
    public Map<Integer, String> getMarks() {
        return Collections.unmodifiableMap(INDEX_MARK_MAP);
    }

    /**
     * 清除所有标记点（但保留最开始的标记点）
     */
    public void clearMarks() {
        clearMarks(false);
    }

    private void clearMarks(boolean isReset) {
        this.markIndex = 0;

        MARK_INDEX_MAP.clear();
        INDEX_MARK_MAP.clear();
        MARK_TICK_MAP.clear();

        if (!isReset) {
            if (isRunning) {
                nanoTimeMark(0, this.startTick, START_MARK);
            } else {
                if (this.stopTick != 0) {
                    nanoTimeMark(0, this.startTick, START_MARK);
                    nanoTimeMark(1, this.stopTick, STOP_MARK);
                }
            }
        }
    }

    @Override
    public String toString() {
        return elapsedString(elapsedNanos());
    }

    public static String elapsedString(long nanos) {
        TimeUnit unit = chooseUnit(nanos);
        double value = (double) nanos / unit.toNanos(1);

        return G.toString(value, 3) + '(' + UNIT_STRING_MAP.get(unit) + ')';
    }

    static double getElapsedDouble(TimeUnit unit, long nanos) {
        return BigDecimal.valueOf((double) nanos / unit.toNanos(1)).setScale(4, RoundingMode.HALF_UP).doubleValue();
    }

    private static TimeUnit chooseUnit(long nanos) {
        if (NANOSECONDS.toDays(nanos) > 0) return DAYS;
        if (NANOSECONDS.toHours(nanos) > 0) return HOURS;
        if (NANOSECONDS.toMinutes(nanos) > 0) return MINUTES;
        if (NANOSECONDS.toSeconds(nanos) > 0) return SECONDS;
        if (NANOSECONDS.toMillis(nanos) > 0) return MILLISECONDS;
        if (NANOSECONDS.toMicros(nanos) > 0) return MICROSECONDS;
        return NANOSECONDS;
    }

    /**
     * Time elapsed between two mark point. <br>
     * 两个标记点之间所经过的时间
     */
    public static class Elapsed {
        public final int fromIndex;
        public final int toIndex;
        public final String from;
        public final String to;
        public final long elapsedNanos;

        Elapsed(int fromIndex, int toIndex, String from, String to, long elapsedNanos) {
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
            this.from = from;
            this.to = to;
            this.elapsedNanos = elapsedNanos;
        }

        public long elapsed(TimeUnit unit) {
            return unit.convert(elapsedNanos, NANOSECONDS);
        }

        public double elapsedExact(TimeUnit unit) {
            return getElapsedDouble(unit, elapsedNanos);
        }

        public Duration elapsed() {
            return Duration.ofNanos(elapsedNanos);
        }

        @Override
        public String toString() {
            return elapsedString(elapsedNanos);
        }

        public String toFullString() {
            return SI.$("${elapsed} (index: ${fromIndex} -> ${toIndex}, mark: ${from} -> ${to})", elapsedString(elapsedNanos), fromIndex, toIndex, from, to);
        }
    }


    public boolean isRunning() {
        return isRunning;
    }

    public long getStartTick() {
        return startTick;
    }

    public long getLastMarkTick() {
        return lastMarkTick;
    }

    public long getStopTick() {
        return stopTick;
    }

    public long getElapsedNanos() {
        return elapsedNanos;
    }

    public int getMarkIndex() {
        return markIndex;
    }

}
