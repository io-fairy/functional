package com.iofairy.test;

import static com.iofairy.range.IntervalType.*;

import com.iofairy.range.Range;
import com.iofairy.tcf.Try;
import com.iofairy.top.G;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author GG
 * @version 1.0
 * @date 2024/1/31 13:30
 */
public class RangeTest {
    @Test
    public void testRange() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = Try.tcfs(() -> sdf.parse("2024-02-01 10:20:05"));
        Date date2 = Try.tcfs(() -> sdf.parse("2024-01-30 10:20:05"));
        Date date3 = Try.tcfs(() -> sdf.parse("2024-01-31 10:20:05"));
        Date date4 = Try.tcfs(() -> sdf.parse("2023-12-31 10:20:05"));
        System.out.println(G.toString(date1));
        System.out.println(G.toString(date2));
        System.out.println(G.toString(date3));
        System.out.println(G.toString(date4));
        System.out.println("============================================================");

        Range<Comparable<?>> range01 = Range.of(null, null, CLOSED);
        Range<BigDecimal> range02 = Range.of(null, null, OPEN);
        Range<BigDecimal> range03 = Range.of(null, new BigDecimal("0.0"), CLOSED);
        Range<BigDecimal> range04 = Range.of(null, new BigDecimal("-0.0"), CLOSED_OPEN);
        Range<BigDecimal> range05 = Range.of(new BigDecimal("10.0"), new BigDecimal("-0.0"), CLOSED_OPEN);
        Range<Date> range06 = Range.of(date1, date1, CLOSED_OPEN);
        Range<Date> range07 = Range.of(date1, date2, CLOSED_OPEN);

        System.out.println(range01 + " --- " + range01.toSimpleString());
        System.out.println(range02 + " --- " + range02.toSimpleString());
        System.out.println(range03 + " --- " + range03.toSimpleString());
        System.out.println(range04 + " --- " + range04.toSimpleString());
        System.out.println(range05 + " --- " + range05.toSimpleString());
        System.out.println(range06 + " --- " + range06.toSimpleString());
        System.out.println(range07 + " --- " + range07.toSimpleString());

        assertEquals(range01 + " --- " + range01.toSimpleString(), "(-∞, +∞) --- R");
        assertEquals(range02 + " --- " + range02.toSimpleString(), "(-∞, +∞) --- R");
        assertEquals(range03 + " --- " + range03.toSimpleString(), "(-∞, 0.0] --- (-∞, 0.0]");
        assertEquals(range04 + " --- " + range04.toSimpleString(), "(-∞, 0.0) --- (-∞, 0.0)");
        assertEquals(range05 + " --- " + range05.toSimpleString(), "[0.0, 10.0) --- [0.0, 10.0)");
        assertEquals(range06 + " --- " + range06.toSimpleString(), "[2024-02-01 10:20:05.000, 2024-02-01 10:20:05.000) --- ∅");
        assertEquals(range07 + " --- " + range07.toSimpleString(), "[2024-01-30 10:20:05.000, 2024-02-01 10:20:05.000) --- [2024-01-30 10:20:05.000, 2024-02-01 10:20:05.000)");
        System.out.println("============================================================");

        boolean contains01 = range01.contains(new BigDecimal("-0.0"));
        boolean contains02 = range02.contains(new BigDecimal("-0.0"));
        boolean contains03 = range02.contains(null);
        boolean contains04 = range03.contains(new BigDecimal("-0.0"));
        boolean contains05 = range04.contains(new BigDecimal("-0.0"));
        boolean contains06 = range05.contains(new BigDecimal("1"));
        boolean contains07 = range05.contains(new BigDecimal("10.0"));
        boolean contains08 = range05.contains(new BigDecimal("-1"));
        boolean contains09 = range06.contains(date1);
        boolean contains10 = range06.contains(date2);
        boolean contains11 = range06.contains(date3);
        boolean contains12 = range06.contains(date4);
        boolean contains13 = range07.contains(date1);
        boolean contains14 = range07.contains(date2);
        boolean contains15 = range07.contains(date3);
        boolean contains16 = range07.contains(date4);

        // System.out.println("contains01: " + contains01);
        // System.out.println("contains02: " + contains02);
        // System.out.println("contains03: " + contains03);
        // System.out.println("contains04: " + contains04);
        // System.out.println("contains05: " + contains05);
        // System.out.println("contains06: " + contains06);
        // System.out.println("contains07: " + contains07);
        // System.out.println("contains08: " + contains08);
        // System.out.println("contains09: " + contains09);
        // System.out.println("contains10: " + contains10);
        // System.out.println("contains11: " + contains11);
        // System.out.println("contains12: " + contains12);
        // System.out.println("contains13: " + contains13);
        // System.out.println("contains14: " + contains14);
        // System.out.println("contains15: " + contains15);
        // System.out.println("contains16: " + contains16);

        assertTrue(contains01);
        assertTrue(contains02);
        assertFalse(contains03);
        assertTrue(contains04);
        assertFalse(contains05);
        assertTrue(contains06);
        assertFalse(contains07);
        assertFalse(contains08);
        assertFalse(contains09);
        assertFalse(contains10);
        assertFalse(contains11);
        assertFalse(contains12);
        assertFalse(contains13);
        assertTrue(contains14);
        assertTrue(contains15);
        assertFalse(contains16);

        System.out.println("============================================================");

        System.out.println(range01.toSimpleString() + "---" + range01.hasInfinity());
        System.out.println(range02.toSimpleString() + "---" + range02.hasInfinity());
        System.out.println(range03.toSimpleString() + "---" + range03.hasInfinity());
        System.out.println(range04.toSimpleString() + "---" + range04.hasInfinity());
        System.out.println(range05.toSimpleString() + "---" + range05.hasInfinity());
        System.out.println(range06.toSimpleString() + "---" + range06.hasInfinity());
        System.out.println(range07.toSimpleString() + "---" + range07.hasInfinity());
        System.out.println(range01.toSimpleString() + "---" + range01.isEmpty);
        System.out.println(range02.toSimpleString() + "---" + range02.isEmpty);
        System.out.println(range03.toSimpleString() + "---" + range03.isEmpty);
        System.out.println(range04.toSimpleString() + "---" + range04.isEmpty);
        System.out.println(range05.toSimpleString() + "---" + range05.isEmpty);
        System.out.println(range06.toSimpleString() + "---" + range06.isEmpty);
        System.out.println(range07.toSimpleString() + "---" + range07.isEmpty);

        assertTrue(range01.hasInfinity());
        assertTrue(range02.hasInfinity());
        assertTrue(range03.hasInfinity());
        assertTrue(range04.hasInfinity());
        assertFalse(range05.hasInfinity());
        assertFalse(range06.hasInfinity());
        assertFalse(range07.hasInfinity());
        assertFalse(range01.isEmpty);
        assertFalse(range02.isEmpty);
        assertFalse(range03.isEmpty);
        assertFalse(range04.isEmpty);
        assertFalse(range05.isEmpty);
        assertTrue(range06.isEmpty);
        assertFalse(range07.isEmpty);
    }

    @Test
    public void testRange1() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = Try.tcfs(() -> sdf.parse("2024-02-01 10:20:05"));
        Date date2 = Try.tcfs(() -> sdf.parse("2024-01-30 10:55:05"));

        Range<BigDecimal> r1 = Range.of(new BigDecimal("10.5"), new BigDecimal("-0.15"), CLOSED_OPEN);
        Range<Date> r2 = Range.of(date1, date2, CLOSED_OPEN);
        System.out.println("Range<BigDecimal>: " + r1);
        System.out.println("Range<Date>      : " + r2);

        List<BigDecimal> lowerBounds1 = Arrays.asList(new BigDecimal("-0.15"),
                r1.lowerBound, r1.start, r1.min, r1.from, r1.earliest, r1.left, r1.infimum,
                r1.head, r1.begin, r1.first, r1.origin, r1.source, r1.bottom, r1.floor);
        List<BigDecimal> upperBounds1 = Arrays.asList(new BigDecimal("10.5"),
                r1.upperBound, r1.end, r1.max, r1.to, r1.latest, r1.right, r1.supremum,
                r1.tail, r1.finish, r1.last, r1.destination, r1.target, r1.top, r1.ceiling);
        long count1 = lowerBounds1.stream().distinct().count();
        long count2 = upperBounds1.stream().distinct().count();
        assertEquals(1, count1);
        assertEquals(1, count2);

        List<Date> lowerBounds2 = Arrays.asList(date2,
                r2.lowerBound, r2.start, r2.min, r2.from, r2.earliest, r2.left, r2.infimum,
                r2.head, r2.begin, r2.first, r2.origin, r2.source, r2.bottom, r2.floor);
        List<Date> upperBounds2 = Arrays.asList(date1,
                r2.upperBound, r2.end, r2.max, r2.to, r2.latest, r2.right, r2.supremum,
                r2.tail, r2.finish, r2.last, r2.destination, r2.target, r2.top, r2.ceiling);
        Set<Date> datesSet1 = new HashSet<>(lowerBounds2);
        Set<Date> datesSet2 = new HashSet<>(upperBounds2);
        System.out.println("datesSet1: " + G.toString(datesSet1));
        System.out.println("datesSet2: " + G.toString(datesSet2));

        assertEquals("[2024-01-30 10:55:05.000]", G.toString(datesSet1));
        assertEquals("[2024-02-01 10:20:05.000]", G.toString(datesSet2));

    }

    @Test
    public void testRangeHash() {
        Range<Comparable<?>> range01 = Range.open(null, null);
        Range<Comparable<?>> range02 = Range.openClosed(null, null);
        Range<Comparable<?>> range03 = Range.closedOpen(null, null);
        Range<Comparable<?>> range04 = Range.closed(null, null);
        Range<Integer> range05 = Range.open(1, null);
        Range<Integer> range06 = Range.openClosed(1, null);
        Range<Integer> range07 = Range.closedOpen(1, null);
        Range<Integer> range08 = Range.closed(1, null);
        Range<Integer> range09 = Range.open(null, 1);
        Range<Integer> range10 = Range.openClosed(null, 1);
        Range<Integer> range11 = Range.closedOpen(null, 1);
        Range<Integer> range12 = Range.closed(null, 1);
        Range<Integer> range13 = Range.open(null, 0);
        Range<Integer> range14 = Range.openClosed(null, 0);
        Range<Integer> range15 = Range.closedOpen(null, 0);
        Range<Integer> range16 = Range.closed(null, 0);
        int hashCode01 = range01.hashCode();
        int hashCode02 = range02.hashCode();
        int hashCode03 = range03.hashCode();
        int hashCode04 = range04.hashCode();
        int hashCode05 = range05.hashCode();
        int hashCode06 = range06.hashCode();
        int hashCode07 = range07.hashCode();
        int hashCode08 = range08.hashCode();
        int hashCode09 = range09.hashCode();
        int hashCode10 = range10.hashCode();
        int hashCode11 = range11.hashCode();
        int hashCode12 = range12.hashCode();
        int hashCode13 = range13.hashCode();
        int hashCode14 = range14.hashCode();
        int hashCode15 = range15.hashCode();
        int hashCode16 = range16.hashCode();

        // System.out.println(hashCode01);
        // System.out.println(hashCode02);
        // System.out.println(hashCode03);
        // System.out.println(hashCode04);
        // System.out.println(hashCode05);
        // System.out.println(hashCode06);
        // System.out.println(hashCode07);
        // System.out.println(hashCode08);
        // System.out.println(hashCode09);
        // System.out.println(hashCode10);
        // System.out.println(hashCode11);
        // System.out.println(hashCode12);
        // System.out.println(hashCode13);
        // System.out.println(hashCode14);
        // System.out.println(hashCode15);
        // System.out.println(hashCode16);

        assertEquals(hashCode01, 1236122003);
        assertEquals(hashCode02, 1236122003);
        assertEquals(hashCode03, 1236122003);
        assertEquals(hashCode04, 1236122003);
        assertEquals(hashCode05, 1236122964);
        assertEquals(hashCode06, 1236122964);
        assertEquals(hashCode07, -1598757631);
        assertEquals(hashCode08, -1598757631);
        assertEquals(hashCode09, 1236122034);
        assertEquals(hashCode10, 1236122086);
        assertEquals(hashCode11, 1236122034);
        assertEquals(hashCode12, 1236122086);
        assertEquals(hashCode13, 1236122003);
        assertEquals(hashCode14, 1236122055);
        assertEquals(hashCode15, 1236122003);
        assertEquals(hashCode16, 1236122055);

    }


}
