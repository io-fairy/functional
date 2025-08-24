package com.iofairy.test;

import com.iofairy.except.ConditionsNotMetException;
import com.iofairy.except.OutOfBoundsException;
import com.iofairy.range.Range;
import com.iofairy.range.Ranges;
import com.iofairy.time.DateTime;
import com.iofairy.time.TZ;
import com.iofairy.top.G;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author GG
 * @version 1.0
 * @date 2022/2/14 22:00
 */
public class RangesTest {
    @Test
    public void testDivideRange() {
        List<Range<Long>> ranges1 = Ranges.split(0, 500, 5, 0);
        List<Range<Long>> ranges2 = Ranges.split(-600, -100, 5, -0.1f);
        List<Range<Long>> ranges3 = Ranges.split(-100, 400, 5, 0.1f);
        List<Range<Long>> ranges4 = Ranges.split(2, 48900568, 6, -0.1f);
        List<Range<Long>> ranges5 = Ranges.split(0, 5, 5, 0);
        List<Range<Long>> ranges6 = Ranges.split(0, 5, 5, -0.2f);
        System.out.println(ranges1);
        System.out.println(ranges2);
        System.out.println(ranges3);
        System.out.println(ranges4);
        System.out.println(ranges5);
        System.out.println(ranges6);

        assertEquals("[[0, 100), [100, 200), [200, 300), [300, 400), [400, 500)]", ranges1.toString());
        assertEquals("[[-600, -478), [-478, -368), [-368, -269), [-269, -180), [-180, -100)]", ranges2.toString());
        assertEquals("[[-100, -18), [-18, 72), [72, 171), [171, 280), [280, 400)]", ranges3.toString());
        assertEquals("[[2, 10436576), [10436576, 19829492), [19829492, 28283116), [28283116, 35891378), [35891378, 42738813), [42738813, 48900568)]", ranges4.toString());
        assertEquals("[[0, 1), [1, 2), [2, 3), [3, 4), [4, 5)]", ranges5.toString());
        assertEquals("[[0, 1), [1, 2), [2, 3), [3, 4), [4, 5)]", ranges6.toString());
        System.out.println("============================================================");
        List<Range<Long>> ranges7 = Ranges.split(Range.openClosed(-100L, 400L), 5, 0.1f);
        List<Range<Long>> ranges8 = Ranges.split(Range.openClosed(2L, 48900568L), 6, -0.1f);
        System.out.println(ranges7);
        System.out.println(ranges8);
        assertEquals("[(-100, -18], (-18, 72], (72, 171], (171, 280], (280, 400]]", ranges7.toString());
        assertEquals("[(2, 10436576], (10436576, 19829492], (19829492, 28283116], (28283116, 35891378], (35891378, 42738813], (42738813, 48900568]]", ranges8.toString());
        System.out.println("============================================================");

    }

    @Test
    public void testCheckArgument() {
        try {
            Ranges.split(Range.open(0L, 500L), 1, 0);
            throwException();
        } catch (Exception e) {
            assertSame(e.getClass(), ConditionsNotMetException.class);
            assertEquals("The `range` must be half open interval! ", e.getMessage());
        }
        try {
            Ranges.split(Range.closedOpen(0L, 500L), 1, 0);
            throwException();
        } catch (Exception e) {
            assertSame(e.getClass(), ConditionsNotMetException.class);
            assertEquals("Parameter `splitCount` must ≥ 2! ", e.getMessage());
        }
        try {
            Ranges.split(0, 4, 5, 0);
            throwException();
        } catch (Exception e) {
            assertSame(e.getClass(), ConditionsNotMetException.class);
            assertEquals("(`endIndex` - `beginIndex`) must be ≥ `splitCount`! ", e.getMessage());
        }
        try {
            Ranges.split(Range.closedOpen(0L, 500L), 5, -1);
            throwException();
        } catch (Exception e) {
            assertSame(e.getClass(), OutOfBoundsException.class);
            assertEquals("数值超出所允许的范围，当前值为：[-1.0]。参数`skewRatio`的取值范围为：(-1, 1)。", e.getMessage());
        }

        try {
            Ranges.split(Range.closedOpen(null, null), 5, -1);
            throwException();
        } catch (Exception e) {
            assertSame(e.getClass(), ConditionsNotMetException.class);
            assertEquals("The `range` must be half open interval! ", e.getMessage());
        }

        try {
            Ranges.split(Range.closedOpen(1L, null), 5, -1);
            throwException();
        } catch (Exception e) {
            assertSame(e.getClass(), ConditionsNotMetException.class);
            assertEquals("The `range` can't be an infinite interval! ", e.getMessage());
        }
    }

    @Test
    public void testParseRange() {
        Range<Byte> byteRange = Ranges.parseRange("( 0 ,  -1 )", byte.class);
        Range<BigInteger> biRange = Ranges.parseRange("[-∞ ,  -1]", BigInteger.class);
        Range<Character> charRange = Ranges.parseRange("(-∞ ,  +∞]", char.class);
        Range<Character> charRange1 = Ranges.parseRange("(''' ,  'a']", char.class);
        DateTime dt = DateTime.of("2025-07-05");
        Range<DateTime> dtRange = Ranges.parseRange("∅", (Class<DateTime>) dt.getClass());
        Range<LocalDate> ldRange = Ranges.parseRange("[ '2025/01/01' ,   '2025/3/1')", LocalDate.class);
        Range<Double> doubleRange = Ranges.parseRange("[-9032565856500000000.69420144556655484565455 ,  1.5]", double.class);
        Range<Double> doubleRange1 = Ranges.parseRange("[-903256585 ,  1]", double.class);
        Range<BigDecimal> dbRange = Ranges.parseRange("[-9032565856500000000.69420144556655484565455 ,  1.4654654649494616165]", BigDecimal.class);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("y M d H:m");
        Range<DateTime> dtRange1 = Ranges.parseRange("( '2025 7 6 18:50' ,  '2025 7 7 10:2' ]", DateTime.class, dtf);
        dtf = dtf.withZone(TZ.UTC);
        Range<DateTime> dtRange2 = Ranges.parseRange("( '2025 7 6 18:50' ,  '2025 7 7 10:2' ]", DateTime.class, dtf);
        Range<DateTime> dtRange3 = Ranges.parseRange("('2025-07-06 18:50:20.000 [UTC +00:00]', '2025-07-07 10:02:16.000 [UTC +00:00]']", DateTime.class, null);
        Range<DateTime> dtRange4 = Ranges.parseRange("(1735660800000 ,  1755571819180]", DateTime.class, null);
        System.out.println(byteRange);
        System.out.println(biRange);
        System.out.println(charRange);
        System.out.println(charRange1);
        System.out.println(charRange1.lowerBound == '\'');
        System.out.println(dtRange);
        System.out.println(ldRange);
        System.out.println(doubleRange);
        System.out.println(doubleRange1);
        System.out.println(dbRange);
        System.out.println(G.toString(dbRange.lowerBound, 30));
        System.out.println(dtRange1);
        System.out.println(dtRange2);
        System.out.println(dtRange3);
        System.out.println(dtRange4);

        assertEquals(byteRange.toString(), "(0, -1)");
        assertEquals(biRange.toString(), "(-∞, -1]");
        assertEquals(charRange.toString(), "(-∞, +∞)");
        assertEquals(charRange1.toString(), "(''', 'a']");
        assertTrue(charRange1.lowerBound == '\'');
        assertEquals(dtRange.toString(), "('1970-01-01 08:00:00.000', '1970-01-01 08:00:00.000')");
        assertEquals(ldRange.toString(), "['2025-01-01', '2025-03-01')");
        assertEquals(doubleRange.toString(), "[-9032565856499999700.0, 1.5]");
        assertEquals(doubleRange1.toString(), "[-903256585.0, 1.0]");
        assertEquals(dbRange.toString(), "[-9032565856500000000.6942014456, 1.4654654649]");
        assertEquals(G.toString(dbRange.lowerBound, 30), "-9032565856500000000.69420144556655484565455");
        assertEquals(dtRange1.toString(), "('2025-07-06 18:50:00.000', '2025-07-07 10:02:00.000']");
        assertEquals(dtRange2.toString(), "('2025-07-06 18:50:00.000 [UTC +00:00]', '2025-07-07 10:02:00.000 [UTC +00:00]']");
        assertEquals(dtRange3.toString(), "('2025-07-06 18:50:20.000 [UTC +00:00]', '2025-07-07 10:02:16.000 [UTC +00:00]']");
        assertEquals(dtRange4.toString(), "('2025-01-01 00:00:00.000', '2025-08-19 10:50:19.180']");
    }

    @Test
    public void testInferPossibleRangeType() {
        String s01 = "(''' ,  'a']";
        String s02 = "∅";
        String s03 = "[-∞ ,  -1]";
        String s04 = "[ '2025/01/01' ,   '2025/3/1')";
        String s05 = "[-9032565856500000000.69420144556655484565455 ,  1.5]";
        String s06 = "[-903256585 ,  1]";
        String s07 = "[-9032565856500000000.69420144556655484565455 ,  1.4654654649494616165]";
        String s08 = "(-∞ ,  +∞]";
        String s09 = "(''' ,  '']";
        String s10 = null;
        String s11 = "[null ,  1]";
        String s12 = "[56944656546465465465464984898 ,  -1]";
        String s13 = "( 1756985546550 ,  1756995546550]";
        String s14 = "( 1756985546550 ,  -175699554655]";
        String s15 = "( 1756985546550 ,  +∞]";

        Class<?> class01 = Ranges.inferPossibleRangeType(s01, false);
        Class<?> class02 = Ranges.inferPossibleRangeType(s02, false);
        Class<?> class03 = Ranges.inferPossibleRangeType(s03, false);
        Class<?> class04 = Ranges.inferPossibleRangeType(s04, false);
        Class<?> class05 = Ranges.inferPossibleRangeType(s05, false);
        Class<?> class06 = Ranges.inferPossibleRangeType(s06, false);
        Class<?> class07 = Ranges.inferPossibleRangeType(s07, false);
        Class<?> class08 = Ranges.inferPossibleRangeType(s08, false);
        try {
            Class<?> class09 = Ranges.inferPossibleRangeType(s09, false);
            throwException();
        } catch (Exception e) {
            assertSame(e.getClass(), IllegalArgumentException.class);
            assertEquals("The string '(''' ,  '']' cannot be parsed to a `Range` instance. ", e.getMessage());
        }
        try {
            Class<?> class10 = Ranges.inferPossibleRangeType(s10, false);
            throwException();
        } catch (Exception e) {
            assertSame(e.getClass(), IllegalArgumentException.class);
            assertEquals("The string 'null' cannot be parsed to a `Range` instance. ", e.getMessage());
        }
        try {
            Class<?> class11 = Ranges.inferPossibleRangeType(s11, false);
            throwException();
        } catch (Exception e) {
            assertSame(e.getClass(), IllegalArgumentException.class);
            assertEquals("The string '[null ,  1]' cannot be parsed to a `Range` instance. ", e.getMessage());
        }

        Class<?> class12 = Ranges.inferPossibleRangeType(s12, false);
        Class<?> class13 = Ranges.inferPossibleRangeType(s13, false);
        Class<?> class14 = Ranges.inferPossibleRangeType(s14, true);
        Class<?> class15 = Ranges.inferPossibleRangeType(s15, true);
        Class<?> class16 = Ranges.inferPossibleRangeType(s12, true);
        Class<?> class17 = Ranges.inferPossibleRangeType(s13, true);

        // System.out.println("class01: " + class01);
        // System.out.println("class02: " + class02);
        // System.out.println("class03: " + class03);
        // System.out.println("class04: " + class04);
        // System.out.println("class05: " + class05);
        // System.out.println("class06: " + class06);
        // System.out.println("class07: " + class07);
        // System.out.println("class08: " + class08);
        // System.out.println("class12: " + class12);
        // System.out.println("class13: " + class13);
        // System.out.println("class14-true: " + class14);
        // System.out.println("class15-true: " + class15);
        // System.out.println("class12-true: " + class16);
        // System.out.println("class13-true: " + class17);

        assertSame(class01, Character.class);
        assertSame(class02, Integer.class);
        assertSame(class03, Long.class);
        assertSame(class04, DateTime.class);
        assertSame(class05, BigDecimal.class);
        assertSame(class06, Long.class);
        assertSame(class07, BigDecimal.class);
        assertSame(class08, Integer.class);
        assertSame(class12, BigInteger.class);
        assertSame(class13, Long.class);
        assertSame(class14, Long.class);
        assertSame(class15, DateTime.class);
        assertSame(class16, BigInteger.class);
        assertSame(class17, DateTime.class);


    }

    private void throwException() {
        throw new RuntimeException();
    }


}
