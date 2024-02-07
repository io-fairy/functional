package com.iofairy.test;

import static com.iofairy.range.IntervalType.*;
import com.iofairy.range.Range;
import com.iofairy.tcf.Try;
import com.iofairy.top.G;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    }
}
