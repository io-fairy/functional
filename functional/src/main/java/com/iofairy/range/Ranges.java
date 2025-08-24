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
package com.iofairy.range;

import com.iofairy.os.OS;
import com.iofairy.tcf.Try;
import com.iofairy.time.DateTime;
import com.iofairy.top.S;
import com.iofairy.tuple.Tuple;
import com.iofairy.tuple.Tuple2;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static com.iofairy.validator.Preconditions.*;


/**
 * Range Utils
 *
 * @since 0.6.0
 */
public class Ranges {
    private static final DateTime DT = DateTime.of(0L);

    public static Pattern INFINITY_RANGE = Pattern.compile("^-∞\\s*,\\s+\\+∞$");
    public static Pattern LEFT_INFINITY_RANGE = Pattern.compile("^-∞\\s*,\\s+(.+)$");
    public static Pattern RIGHT_INFINITY_RANGE = Pattern.compile("^(.+)\\s*,\\s+\\+∞$");
    public static Pattern INT_RANGE = Pattern.compile("^([+-]?\\d+)\\s*,\\s+([+-]?\\d+)$");
    public static Pattern DOUBLE_RANGE = Pattern.compile("^([+-]?\\d+(\\.\\d+)?)\\s*,\\s+([+-]?\\d+(\\.\\d+)?)$");
    public static Pattern CHAR_RANGE = Pattern.compile("^'(.)'\\s*,\\s+'(.)'$");
    public static Pattern STRING_RANGE = Pattern.compile("^'(.+)'\\s*,\\s+'(.+)'$");


    /**
     * 从{@code Range}的序列化字符串中，推断{@code Range}的类型参数的实际类型
     *
     * @param rangeStr       {@code Range}的序列化字符串
     * @param maybeTimestamp 是否可能是时间戳（如果true且是13位的数字，则转成{@code DateTime}）
     * @return {@code Range}的类型参数的实际类型
     */
    public static Class<?> inferPossibleRangeType(String rangeStr, boolean maybeTimestamp) {
        checkArgument(S.isBlank(rangeStr), "The string '${?}' cannot be parsed to a `Range` instance. ", rangeStr);
        String tmpRangeStr = rangeStr.trim();

        boolean isEmpty = tmpRangeStr.equals(Range.EMPTY_SET);
        if (isEmpty) return Integer.class;      // 如果传入空集，统一返回 int 类型

        boolean isValidRangeString = (tmpRangeStr.startsWith("[") || tmpRangeStr.startsWith("(")) && (tmpRangeStr.endsWith("]") || tmpRangeStr.endsWith(")")) && tmpRangeStr.contains(", ");
        checkArgument(!isValidRangeString, "The string '${?}' cannot be parsed to a `Range` instance. ", rangeStr);

        String centerSection = rangeStr.substring(1, rangeStr.length() - 1).trim();
        if (INFINITY_RANGE.matcher(centerSection).matches()) return Integer.class;

        Matcher matcher1 = LEFT_INFINITY_RANGE.matcher(centerSection);
        Matcher matcher2 = RIGHT_INFINITY_RANGE.matcher(centerSection);
        String str = matcher1.matches() ? matcher1.group(1).trim() : (matcher2.matches() ? matcher2.group(1).trim() : null);
        if (str != null) {
            checkArgument(S.isBlank(str), "The string '${?}' cannot be parsed to a `Range` instance. ", rangeStr);

            BigDecimal big = Try.tcf(() -> new BigDecimal(str), false);
            if (big == null) {   /*说明不是数值类型*/
                // 长度等于3，则是字符，如： 'A'
                return str.length() == 3 ? Character.class : DateTime.class;
            } else {  /*是数值类型*/
                Class<?> intClass = checkIntegerType(str);
                if (intClass != null) {
                    return isMaybeTimestamp(str, maybeTimestamp) ? DateTime.class : intClass;
                } else {
                    return BigDecimal.class;
                }
            }
        } else {
            if (centerSection.startsWith("'")) {
                matcher1 = CHAR_RANGE.matcher(centerSection);
                matcher2 = STRING_RANGE.matcher(centerSection);
                if (matcher1.matches()) return Character.class;
                if (matcher2.matches()) return DateTime.class;
            } else {
                matcher1 = INT_RANGE.matcher(centerSection);
                if (matcher1.matches()) {
                    String left = matcher1.group(1).trim();
                    String right = matcher1.group(2).trim();
                    if (isMaybeTimestamp(left, maybeTimestamp) && isMaybeTimestamp(right, maybeTimestamp)) return DateTime.class;

                    Class<?> leftClass = checkIntegerType(left);
                    Class<?> rightClass = checkIntegerType(right);
                    checkArgument(leftClass == null || rightClass == null, "The string '${?}' cannot be parsed to a `Range` instance. ", rangeStr);

                    return leftClass == BigInteger.class || rightClass == BigInteger.class ? BigInteger.class : Long.class;
                }

                matcher2 = DOUBLE_RANGE.matcher(centerSection);
                if (matcher2.matches()) return BigDecimal.class;
            }
        }

        throw new IllegalArgumentException("The string '" + rangeStr + "' cannot be parsed to a `Range` instance. ");
    }


    private static Class<?> checkIntegerType(String str) {
        if (str == null || str.isEmpty()) return null;
        // 忽略int值的判断，直接选择 Long 就好
        try {
            Long.parseLong(str);
            return Long.class;
        } catch (NumberFormatException e) {
            try {
                new BigInteger(str);
                return BigInteger.class;
            } catch (NumberFormatException e2) {
                return null;
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    private static boolean isMaybeTimestamp(String str, boolean maybeTimestamp) {
        return maybeTimestamp && str.length() == 13 && !str.contains("-") && !str.contains("+");
    }

    /**
     * 将字符串转成{@code Range}对象
     *
     * @param rangeStr 字符串
     * @param clazz    {@code Range}对象的泛型类型
     * @param <T>      {@code Range}对象的泛型类型
     * @return {@code Range}对象
     */
    public static <T extends Comparable<? super T>> Range<T> parseRange(String rangeStr, Class<T> clazz) {
        return parseRange(rangeStr, clazz, null);
    }

    /**
     * 将字符串转成{@code Range}对象
     *
     * @param rangeStr  字符串
     * @param clazz     {@code Range}对象的泛型类型
     * @param formatter 时间格式化器
     * @param <T>       {@code Range}对象的泛型类型
     * @return {@code Range}对象
     */
    public static <T extends Comparable<? super T>> Range<T> parseRange(String rangeStr, Class<T> clazz, DateTimeFormatter formatter) {
        checkNullNPE(clazz, args("clazz"));

        if (S.isBlank(rangeStr)) return null;
        String tmpRangeStr = rangeStr.trim();

        boolean isEmpty = tmpRangeStr.equals(Range.EMPTY_SET);
        boolean isValidRangeString = (tmpRangeStr.startsWith("[") || tmpRangeStr.startsWith("(")) && (tmpRangeStr.endsWith("]") || tmpRangeStr.endsWith(")")) && tmpRangeStr.contains(", ");

        if (isEmpty) {
            if (Byte.class == clazz || byte.class == clazz) {
                @SuppressWarnings("unchecked")
                Range<T> range = (Range<T>) Range.open((byte) 0, (byte) 0);
                return range;
            } else if (Character.class == clazz || char.class == clazz) {
                @SuppressWarnings("unchecked")
                Range<T> range = (Range<T>) Range.open((char) 0, (char) 0);
                return range;
            } else if (Short.class == clazz || short.class == clazz) {
                @SuppressWarnings("unchecked")
                Range<T> range = (Range<T>) Range.open((short) 0, (short) 0);
                return range;
            } else if (Integer.class == clazz || int.class == clazz) {
                @SuppressWarnings("unchecked")
                Range<T> range = (Range<T>) Range.open(0, 0);
                return range;
            } else if (Long.class == clazz || long.class == clazz) {
                @SuppressWarnings("unchecked")
                Range<T> range = (Range<T>) Range.open((long) 0, (long) 0);
                return range;
            } else if (Float.class == clazz || float.class == clazz) {
                @SuppressWarnings("unchecked")
                Range<T> range = (Range<T>) Range.open((float) 0, (float) 0);
                return range;
            } else if (Double.class == clazz || double.class == clazz) {
                @SuppressWarnings("unchecked")
                Range<T> range = (Range<T>) Range.open((double) 0, (double) 0);
                return range;
            } else if (BigInteger.class == clazz) {
                @SuppressWarnings("unchecked")
                Range<T> range = (Range<T>) Range.open(BigInteger.ZERO, BigInteger.ZERO);
                return range;
            } else if (BigDecimal.class == clazz) {
                @SuppressWarnings("unchecked")
                Range<T> range = (Range<T>) Range.open(BigDecimal.ZERO, BigDecimal.ZERO);
                return range;
            } else if (Date.class == clazz) {
                @SuppressWarnings("unchecked")
                Range<T> range = (Range<T>) Range.open(DT.toDate(), DT.toDate());
                return range;
            } else if (Calendar.class == clazz) {
                @SuppressWarnings("unchecked")
                Range<T> range = (Range<T>) Range.open(DT.toDefaultCalendar(), DT.toDefaultCalendar());
                return range;
            } else if (LocalDateTime.class == clazz) {
                @SuppressWarnings("unchecked")
                Range<T> range = (Range<T>) Range.open(DT.toLocalDT(), DT.toLocalDT());
                return range;
            } else if (ZonedDateTime.class == clazz) {
                @SuppressWarnings("unchecked")
                Range<T> range = (Range<T>) Range.open(DT.toDefaultZonedDT(), DT.toDefaultZonedDT());
                return range;
            } else if (OffsetDateTime.class == clazz) {
                @SuppressWarnings("unchecked")
                Range<T> range = (Range<T>) Range.open(DT.toDefaultOffsetDT(), DT.toDefaultOffsetDT());
                return range;
            } else if (Instant.class == clazz) {
                @SuppressWarnings("unchecked")
                Range<T> range = (Range<T>) Range.open(DT.toInstant(), DT.toInstant());
                return range;
            } else if (DateTime.class.isAssignableFrom(clazz)) {
                @SuppressWarnings("unchecked")
                Range<T> range = (Range<T>) Range.open(DT, DT);
                return range;
            } else {
                throw new IllegalArgumentException("Unsupported unit: " + clazz.getName());
            }
        } else if (isValidRangeString) {
            char startChar = tmpRangeStr.charAt(0);
            char endChar = tmpRangeStr.charAt(tmpRangeStr.length() - 1);
            IntervalType intervalType = IntervalType.of(startChar, endChar);

            String centerSection = tmpRangeStr.substring(1, tmpRangeStr.length() - 1).trim();

            if (Byte.class == clazz || byte.class == clazz) {
                Tuple2<BigInteger, BigInteger> tuple = parseIntRange(centerSection);
                if (tuple != null) {
                    @SuppressWarnings("unchecked")
                    Range<T> range = (Range<T>) Range.of(tuple._1 != null ? tuple._1.byteValue() : null, tuple._2 != null ? tuple._2.byteValue() : null, intervalType);
                    return range;
                }
            } else if (Character.class == clazz || char.class == clazz) {
                Tuple2<String, String> tuple = parseCharRange(centerSection);
                if (tuple != null) {
                    @SuppressWarnings("unchecked")
                    Range<T> range = (Range<T>) Range.of(tuple._1 != null ? tuple._1.charAt(0) : null, tuple._2 != null ? tuple._2.charAt(0) : null, intervalType);
                    return range;
                }
            } else if (Short.class == clazz || short.class == clazz) {
                Tuple2<BigInteger, BigInteger> tuple = parseIntRange(centerSection);
                if (tuple != null) {
                    @SuppressWarnings("unchecked")
                    Range<T> range = (Range<T>) Range.of(tuple._1 != null ? tuple._1.shortValue() : null, tuple._2 != null ? tuple._2.shortValue() : null, intervalType);
                    return range;
                }
            } else if (Integer.class == clazz || int.class == clazz) {
                Tuple2<BigInteger, BigInteger> tuple = parseIntRange(centerSection);
                if (tuple != null) {
                    @SuppressWarnings("unchecked")
                    Range<T> range = (Range<T>) Range.of(tuple._1 != null ? tuple._1.intValue() : null, tuple._2 != null ? tuple._2.intValue() : null, intervalType);
                    return range;
                }
            } else if (Long.class == clazz || long.class == clazz) {
                Tuple2<BigInteger, BigInteger> tuple = parseIntRange(centerSection);
                if (tuple != null) {
                    @SuppressWarnings("unchecked")
                    Range<T> range = (Range<T>) Range.of(tuple._1 != null ? tuple._1.longValue() : null, tuple._2 != null ? tuple._2.longValue() : null, intervalType);
                    return range;
                }
            } else if (BigInteger.class == clazz) {
                Tuple2<BigInteger, BigInteger> tuple = parseIntRange(centerSection);
                if (tuple != null) {
                    @SuppressWarnings("unchecked")
                    Range<T> range = (Range<T>) Range.of(tuple._1, tuple._2, intervalType);
                    return range;
                }
            } else if (Float.class == clazz || float.class == clazz) {
                Tuple2<BigDecimal, BigDecimal> tuple = parseDoubleRange(centerSection);
                if (tuple != null) {
                    @SuppressWarnings("unchecked")
                    Range<T> range = (Range<T>) Range.of(tuple._1 != null ? tuple._1.floatValue() : null, tuple._2 != null ? tuple._2.floatValue() : null, intervalType);
                    return range;
                }
            } else if (Double.class == clazz || double.class == clazz) {
                Tuple2<BigDecimal, BigDecimal> tuple = parseDoubleRange(centerSection);
                if (tuple != null) {
                    @SuppressWarnings("unchecked")
                    Range<T> range = (Range<T>) Range.of(tuple._1 != null ? tuple._1.doubleValue() : null, tuple._2 != null ? tuple._2.doubleValue() : null, intervalType);
                    return range;
                }
            } else if (BigDecimal.class == clazz) {
                Tuple2<BigDecimal, BigDecimal> tuple = parseDoubleRange(centerSection);
                if (tuple != null) {
                    @SuppressWarnings("unchecked")
                    Range<T> range = (Range<T>) Range.of(tuple._1, tuple._2, intervalType);
                    return range;
                }
            } else if (Date.class == clazz || Calendar.class == clazz || LocalDateTime.class == clazz || ZonedDateTime.class == clazz
                    || OffsetDateTime.class == clazz || Instant.class == clazz || LocalDate.class == clazz || DateTime.class.isAssignableFrom(clazz)) {
                Tuple2<String, String> stringTuple = parseStringRange(centerSection);
                if (stringTuple != null) {
                    DateTime lowerBound = stringTuple._1 != null ? (formatter == null ? DateTime.parse(stringTuple._1) : DateTime.parse(stringTuple._1, formatter)) : null;
                    DateTime upperBound = stringTuple._2 != null ? (formatter == null ? DateTime.parse(stringTuple._2) : DateTime.parse(stringTuple._2, formatter)) : null;

                    if (DateTime.class.isAssignableFrom(clazz)) {
                        @SuppressWarnings("unchecked")
                        Range<T> range = (Range<T>) Range.of(lowerBound, upperBound, intervalType);
                        return range;
                    } else {
                        return Range.of(lowerBound == null ? null : lowerBound.toDT(clazz), upperBound == null ? null : upperBound.toDT(clazz), intervalType);
                    }
                } else {
                    /*
                     时间戳 转 时间
                     */
                    Tuple2<BigInteger, BigInteger> longTuple = parseIntRange(centerSection);
                    if (longTuple != null) {
                        DateTime lowerBound = longTuple._1 != null ? DateTime.of(longTuple._1.longValue()) : null;
                        DateTime upperBound = longTuple._2 != null ? DateTime.of(longTuple._2.longValue()) : null;

                        if (DateTime.class.isAssignableFrom(clazz)) {
                            @SuppressWarnings("unchecked")
                            Range<T> range = (Range<T>) Range.of(lowerBound, upperBound, intervalType);
                            return range;
                        } else {
                            return Range.of(lowerBound == null ? null : lowerBound.toDT(clazz), upperBound == null ? null : upperBound.toDT(clazz), intervalType);
                        }
                    }
                }
            } else {
                throw new IllegalArgumentException("Unsupported type: " + clazz.getName());
            }
        }

        throw new IllegalArgumentException("The string '" + rangeStr + "' cannot be parsed to a `Range` instance with type '" + clazz.getName() + "'. ");
    }

    /**
     * 解析<b>整型</b>的 {@code range} 字符串
     *
     * @param centerSection range字符串中心部分
     * @return range最小值与最大值的元组
     */
    private static Tuple2<BigInteger, BigInteger> parseIntRange(String centerSection) {
        Matcher matcher = INT_RANGE.matcher(centerSection);
        if (matcher.matches()) {
            String left = matcher.group(1).trim();
            String right = matcher.group(2).trim();
            return Tuple.of(new BigInteger(left), new BigInteger(right));
        } else {
            matcher = INFINITY_RANGE.matcher(centerSection);
            if (matcher.matches()) {
                return Tuple.of(null, null);
            } else {
                matcher = LEFT_INFINITY_RANGE.matcher(centerSection);
                if (matcher.matches()) {
                    String num = matcher.group(1).trim();
                    return Tuple.of(null, new BigInteger(num));
                } else {
                    matcher = RIGHT_INFINITY_RANGE.matcher(centerSection);
                    if (matcher.matches()) {
                        String num = matcher.group(1).trim();
                        return Tuple.of(new BigInteger(num), null);
                    }
                }
            }
        }

        return null;
    }

    /**
     * 解析<b>浮点型</b>的 {@code range} 字符串
     *
     * @param centerSection range字符串中心部分
     * @return range最小值与最大值的元组
     */
    private static Tuple2<BigDecimal, BigDecimal> parseDoubleRange(String centerSection) {
        Matcher matcher = DOUBLE_RANGE.matcher(centerSection);
        if (matcher.matches()) {
            String left = matcher.group(1).trim();
            String right = matcher.group(3).trim();
            return Tuple.of(new BigDecimal(left), new BigDecimal(right));
        } else {
            matcher = INFINITY_RANGE.matcher(centerSection);
            if (matcher.matches()) {
                return Tuple.of(null, null);
            } else {
                matcher = LEFT_INFINITY_RANGE.matcher(centerSection);
                if (matcher.matches()) {
                    String num = matcher.group(1).trim();
                    return Tuple.of(null, new BigDecimal(num));
                } else {
                    matcher = RIGHT_INFINITY_RANGE.matcher(centerSection);
                    if (matcher.matches()) {
                        String num = matcher.group(1).trim();
                        return Tuple.of(new BigDecimal(num), null);
                    }
                }
            }
        }

        return null;
    }

    /**
     * 解析<b>CHAR类型</b>的 {@code range} 字符串
     *
     * @param centerSection range字符串中心部分
     * @return range最小值与最大值的元组
     */
    private static Tuple2<String, String> parseCharRange(String centerSection) {
        Matcher matcher = CHAR_RANGE.matcher(centerSection);
        if (matcher.matches()) {
            String left = matcher.group(1).trim();
            String right = matcher.group(2).trim();
            return Tuple.of(left, right);
        } else {
            matcher = INFINITY_RANGE.matcher(centerSection);
            if (matcher.matches()) {
                return Tuple.of(null, null);
            } else {
                matcher = LEFT_INFINITY_RANGE.matcher(centerSection);
                if (matcher.matches()) {
                    String str = matcher.group(1).trim();
                    return Tuple.of(null, str);
                } else {
                    matcher = RIGHT_INFINITY_RANGE.matcher(centerSection);
                    if (matcher.matches()) {
                        String str = matcher.group(1).trim();
                        return Tuple.of(str, null);
                    }
                }
            }
        }

        return null;
    }

    /**
     * 解析<b>字符串类型</b>的 {@code range} 字符串
     *
     * @param centerSection range字符串中心部分
     * @return range最小值与最大值的元组
     */
    private static Tuple2<String, String> parseStringRange(String centerSection) {
        Matcher matcher = STRING_RANGE.matcher(centerSection);
        if (matcher.matches()) {
            String left = matcher.group(1).trim();
            String right = matcher.group(2).trim();
            return Tuple.of(left, right);
        } else {
            matcher = INFINITY_RANGE.matcher(centerSection);
            if (matcher.matches()) {
                return Tuple.of(null, null);
            } else {
                matcher = LEFT_INFINITY_RANGE.matcher(centerSection);
                if (matcher.matches()) {
                    String str = matcher.group(1).trim();
                    return Tuple.of(null, str);
                } else {
                    matcher = RIGHT_INFINITY_RANGE.matcher(centerSection);
                    if (matcher.matches()) {
                        String str = matcher.group(1).trim();
                        return Tuple.of(str, null);
                    }
                }
            }
        }

        return null;
    }


    /**
     * 将一个Range（范围）分成多个小的Range（范围）
     *
     * @param beginIndex range开始序号（包含）
     * @param endIndex   range结束序号（不包含）
     * @param splitCount 分成几份，取值范围：[2, +(endIndex - beginIndex)]。
     * @param skewRatio  数据倾斜率，取值范围：(-1, 1)。
     *                   如：skewRatio为0.1，则 每一个Range都比前一个Range多10%的大小；skewRatio为-0.1，则 每一个Range都比前一个Range少10%的大小。
     * @return range list
     */
    public static List<Range<Long>> split(long beginIndex, long endIndex, int splitCount, float skewRatio) {
        long dataCount = endIndex - beginIndex;
        checkCondition(dataCount < splitCount, "(`endIndex` - `beginIndex`) must be ≥ `splitCount`! ");

        return split(Range.closedOpen(beginIndex, endIndex), splitCount, skewRatio);
    }

    /**
     * 将一个Range（范围）分成多个小的Range（范围）
     *
     * @param range      区间
     * @param splitCount 分成几份，取值范围：[2, +(range.end - range.start)]。
     * @param skewRatio  数据倾斜率，取值范围：(-1, 1)。
     *                   如：skewRatio为0.1，则 每一个Range都比前一个Range多10%的大小；skewRatio为-0.1，则 每一个Range都比前一个Range少10%的大小。
     * @return range list
     */
    public static List<Range<Long>> split(final Range<Long> range, final int splitCount, final float skewRatio) {
        checkNullNPE(range, args("range"));
        checkCondition(!range.intervalType.isHalfOpen(), "The `range` must be half open interval! ");
        checkCondition(range.hasInfinity, "The `range` can't be an infinite interval! ");
        long dataCount = range.end - range.start;
        checkCondition(splitCount < 2, "Parameter `splitCount` must ≥ 2! ");
        checkCondition(dataCount < splitCount, "(`range.end` - `range.start`) must be ≥ `splitCount`! ");
        checkOutOfBounds(skewRatio <= -1 || skewRatio >= 1, skewRatio, OS.IS_ZH_LANG ? "参数`skewRatio`的取值范围为：(-1, 1)。" : "Parameter `skewRatio` must be in (-1, 1)! ");

        /*
         * x 为首个range大小
         *   x + x*(1+skewRatio)+ x*(1+skewRatio)² + x*(1+skewRatio)³... = dataCount
         *   x * (1 + (1+skewRatio) + (1+skewRatio)² + (1+skewRatio)³... ) = dataCount
         *   ratioSum = (1 + (1+skewRatio) + (1+skewRatio)² + (1+skewRatio)³... )
         *   x = dataCount / ratioSum
         */

        double ratioSum = IntStream.range(0, splitCount).boxed().mapToDouble(i -> Math.pow(1 + skewRatio, i)).sum();
        ratioSum = new BigDecimal(ratioSum).setScale(4, RoundingMode.DOWN).doubleValue();

        List<Range<Long>> rangeList = new ArrayList<>();
        float floatFirstSize = new BigDecimal(dataCount / ratioSum).setScale(4, RoundingMode.DOWN).floatValue();
        long firstSize = new BigDecimal(floatFirstSize).setScale(0, RoundingMode.HALF_UP).longValue();
        rangeList.add(Range.of(range.start, firstSize + range.start, range.intervalType));
        for (int i = 1; i < splitCount; i++) {
            Range<Long> preRange = rangeList.get(i - 1);
            long lineSize = new BigDecimal(floatFirstSize * Math.pow(1 + skewRatio, i)).setScale(0, RoundingMode.HALF_UP).longValue();
            rangeList.add(Range.of(preRange.end, i < splitCount - 1 ? preRange.end + lineSize : range.end, range.intervalType));
        }
        return rangeList;
    }


}
