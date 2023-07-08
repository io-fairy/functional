package com.iofairy.test;

import com.iofairy.top.G;
import com.iofairy.top.O;
import com.iofairy.top.S;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author GG
 * @version 1.0
 * @date 2022/5/14 8:08
 */
public class GlobalNumberTest {

    @Test
    public void testNumberToString() {
        Object[] objects = {"this is string", 1.0f, 100, 1, 'a', null, 0.0d / 0.0, Float.POSITIVE_INFINITY, 0f, 0d, 0, -0.0f, Double.NEGATIVE_INFINITY,
                -10.000198f, 897.66897975569d, new BigDecimal("-100.1000000"), -9999999999.120000000089569999d};
        double[] ds = {0.0d / 0.0, Float.POSITIVE_INFINITY, 1.0f, Double.NEGATIVE_INFINITY, 10.000198f, 897.66897975569d, 9999999999.120000000089569999d};
        Double[] ds1 = {-0.0d / 0.0, -0.01 / 0.0, null, Double.NEGATIVE_INFINITY, 10.000198d, 897.66897975569d, 9999999999.120000000089569999d};
        float[] fs = {0.0f / -0.0f, 0.01f / 0.0f, Float.NEGATIVE_INFINITY, -10.000198f, 897.66897975569f};
        Number[] ns = {0.0d / 0.0, Float.POSITIVE_INFINITY, 1.0f, null, Double.NEGATIVE_INFINITY, 10.000198f, 897.66897975569d,
                new BigDecimal("100.1000000"), 9999999999l, 9999999999.120000000089569999d};
        Object o = 9999999999.120000000089569999d;
        Object o1 = 0.01 / 0.0;
        Object o2 = Double.NEGATIVE_INFINITY;
        Number n = 897.66897975569d;
        Number n1 = 0.01 / 0.0;
        Number n2 = Double.NEGATIVE_INFINITY;
        double d = 897.66897975569d;
        double d1 = 0.01 / 0.0;
        double d2 = Double.NEGATIVE_INFINITY;
        List<Object> objectsList = Arrays.asList(objects);
        List<Number> numberList = Arrays.asList(ns);

        System.out.println(Arrays.toString(objects)); // [this is string, 1.0, 100, 1, a, null, NaN, Infinity, 0.0, 0.0, 0, -0.0, -Infinity, -10.000198, 897.66897975569, -100.1000000, -9.99999999912E9]
        System.out.println(Arrays.toString(ds));      // [NaN, Infinity, 1.0, -Infinity, 10.000198364257812, 897.66897975569, 9.99999999912E9]
        System.out.println(Arrays.toString(ds1));     // [NaN, -Infinity, null, -Infinity, 10.000198, 897.66897975569, 9.99999999912E9]
        System.out.println(Arrays.toString(fs));      // [NaN, Infinity, -Infinity, -10.000198, 897.669]
        System.out.println(Arrays.toString(ns));      // [NaN, Infinity, 1.0, null, -Infinity, 10.000198, 897.66897975569, 100.1000000, 9999999999, 9.99999999912E9]
        System.out.println(o);   // 9.99999999912E9
        System.out.println(o1);  // Infinity
        System.out.println(o2);  // -Infinity
        System.out.println(n);   // 897.66897975569
        System.out.println(n1);  // Infinity
        System.out.println(n2);  // -Infinity
        System.out.println(d);   // 897.66897975569
        System.out.println(d1);  // Infinity
        System.out.println(d2);  // -Infinity
        System.out.println(objectsList);    // [this is string, 1.0, 100, 1, a, null, NaN, Infinity, 0.0, 0.0, 0, -0.0, -Infinity, -10.000198, 897.66897975569, -100.1000000, -9.99999999912E9]
        System.out.println(numberList);     // [NaN, Infinity, 1.0, null, -Infinity, 10.000198, 897.66897975569, 100.1000000, 9999999999, 9.99999999912E9]
        System.out.println("===================================================");
        System.out.println(G.toString(objects)); // ["this is string", 1.0, 100, 1, 'a', null, NaN, ∞, 0.0, 0.0, 0, 0.0, -∞, -10.000198, 897.66898, -100.1, -9999999999.12]
        System.out.println(G.toString(ds));      // [NaN, ∞, 1, -∞, 10.000198, 897.66898, 9999999999.12]
        System.out.println(G.toString(ds1));     // [NaN, -∞, null, -∞, 10.000198, 897.66898, 9999999999.12]
        System.out.println(G.toString(fs));      // [NaN, ∞, -∞, -10.000198, 897.669]
        System.out.println(G.toString(ns));      // [NaN, ∞, 1, null, -∞, 10.000198, 897.66898, 100.1, 9999999999, 9999999999.12]
        System.out.println(G.toString(o));       // 9999999999.12
        System.out.println(G.toString(o1));      // ∞
        System.out.println(G.toString(o2));      // -∞
        System.out.println(G.toString(n));       // 897.66898
        System.out.println(G.toString(n1));      // ∞
        System.out.println(G.toString(n2));      // -∞
        System.out.println(G.toString(d));       // 897.66898
        System.out.println(G.toString(d1));      // ∞
        System.out.println(G.toString(d2));      // -∞
        System.out.println(G.toString(objectsList));    // ["this is string", 1.0, 100, 1, 'a', null, NaN, ∞, 0.0, 0.0, 0, 0.0, -∞, -10.000198, 897.66898, -100.1, -9999999999.12]
        System.out.println(G.toString(numberList));     // [NaN, ∞, 1.0, null, -∞, 10.000198, 897.66898, 100.1, 9999999999, 9999999999.12]
        System.out.println("===================================================");
        assertEquals(G.toString(objects), "[\"this is string\", 1.0, 100, 1, 'a', null, NaN, ∞, 0.0, 0.0, 0, 0.0, -∞, -10.000198, 897.66898, -100.1, -9999999999.12]");
        assertEquals(G.toString(ds), "[NaN, ∞, 1.0, -∞, 10.000198, 897.66898, 9999999999.12]");
        assertEquals(G.toString(ds1), "[NaN, -∞, null, -∞, 10.000198, 897.66898, 9999999999.12]");
        assertEquals(G.toString(fs), "[NaN, ∞, -∞, -10.000198, 897.669]");
        assertEquals(G.toString(ns), "[NaN, ∞, 1.0, null, -∞, 10.000198, 897.66898, 100.1, 9999999999, 9999999999.12]");
        assertEquals(G.toString(o), "9999999999.12");
        assertEquals(G.toString(o1), "∞");
        assertEquals(G.toString(o2), "-∞");
        assertEquals(G.toString(n), "897.66898");
        assertEquals(G.toString(n1), "∞");
        assertEquals(G.toString(n2), "-∞");
        assertEquals(G.toString(d), "897.66898");
        assertEquals(G.toString(d1), "∞");
        assertEquals(G.toString(d2), "-∞");
        assertEquals(G.toString(objectsList), "[\"this is string\", 1.0, 100, 1, 'a', null, NaN, ∞, 0.0, 0.0, 0, 0.0, -∞, -10.000198, 897.66898, -100.1, -9999999999.12]");
        assertEquals(G.toString(numberList), "[NaN, ∞, 1.0, null, -∞, 10.000198, 897.66898, 100.1, 9999999999, 9999999999.12]");
    }


    @Test
    public void testCompareNumber() {
        int compare00 = O.compare(null, null);
        int compare01 = O.compare(null, 111111789789.00000);
        int compare02 = O.compare(1, null);
        int compare03 = O.compare(111111789789.0, BigDecimal.valueOf(111111789789.00000));
        int compare04 = O.compare(111111789789.0000102D, BigInteger.valueOf(111111789789L));
        int compare05 = O.compare(111111789789.000001, 111111789789.00010);
        int compare06 = O.compare(Double.NaN, Float.NaN);
        int compare07 = O.compare(Double.NaN, Double.POSITIVE_INFINITY);
        int compare08 = O.compare(new BigDecimal("99999999999999999999999999.9999999999"), Double.POSITIVE_INFINITY);
        int compare09 = O.compare(new BigDecimal("-99999999999999999999999999.9999999999"), Double.NEGATIVE_INFINITY);
        int compare10 = O.compare(-0.0, +0.0);
        int compare11 = O.compare(-1.0, new BigDecimal("-1.0"));
        int compare12 = O.compare(Float.NaN, null);
        int compare13 = O.compare(1.001f, 1.001d);
        int compare14 = O.compare((-0.0001d / 0.0), Double.NEGATIVE_INFINITY);
        int compare15 = O.compare((0.0001d / 0.0), Double.POSITIVE_INFINITY);

        System.out.println("compare00 result: " + compare00);
        System.out.println("compare01 result: " + compare01);
        System.out.println("compare02 result: " + compare02);
        System.out.println("compare03 result: " + compare03);
        System.out.println("compare04 result: " + compare04);
        System.out.println("compare05 result: " + compare05);
        System.out.println("compare06 result: " + compare06);
        System.out.println("compare07 result: " + compare07);
        System.out.println("compare08 result: " + compare08);
        System.out.println("compare09 result: " + compare09);
        System.out.println("compare10 result: " + compare10);
        System.out.println("compare11 result: " + compare11);
        System.out.println("compare12 result: " + compare12);
        System.out.println("compare13 result: " + compare13);
        System.out.println("compare14 result: " + compare14);
        System.out.println("compare15 result: " + compare15);
        assertEquals(compare00, -2);
        assertEquals(compare01, -2);
        assertEquals(compare02, -2);
        assertEquals(compare03, 0);
        assertEquals(compare04, 1);
        assertEquals(compare05, -1);
        assertEquals(compare06, 0);
        assertEquals(compare07, 1);
        assertEquals(compare08, -1);
        assertEquals(compare09, 1);
        assertEquals(compare10, 0);
        assertEquals(compare11, 0);
        assertEquals(compare12, -2);
        assertEquals(compare13, 0);
        assertEquals(compare14, 0);
        assertEquals(compare15, 0);
    }

    @Test
    public void testIndexOfMaxOrMin1() {
        int i = 5;
        int[] nullIs = null;
        long[] ls = {};
        int[] is = {3, 5, 9, 4, 1};
        short[] ss = {20, 1, 5};
        char[] cs = {'a', 'A', 'z', '9'};
        byte[] bs = {20, -11, 5, 50};
        double[] ds = {20.0, 19.79, Double.NaN, 20.01, 19.81};
        float[] fs = {-20.0f, -19.79f, Float.NaN, -20.01f, -19.81f};
        double[] ds1 = {Double.NaN};
        double[] ds2 = {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY};
        double[] ds3 = {Double.NaN, Double.POSITIVE_INFINITY, 20.01, 19.81, Double.NEGATIVE_INFINITY};
        float[] fs1 = {Float.NaN};
        float[] fs2 = {Float.NaN, Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY};
        float[] fs3 = {Float.NaN, Float.POSITIVE_INFINITY, -20.01f, -19.81f, Float.NEGATIVE_INFINITY};
        // =========================================================
        int max01 = O.indexOfMax(new int[]{i});
        int max02 = O.indexOfMax(nullIs);
        int max03 = O.indexOfMax(ls);
        int max04 = O.indexOfMax(is);
        int max05 = O.indexOfMax(ss);
        int max06 = O.indexOfMax(cs);
        int max07 = O.indexOfMax(bs);
        int max08 = O.indexOfMax(ds);
        int max09 = O.indexOfMax(fs);
        int max10 = O.indexOfMax(ds1);
        int max11 = O.indexOfMax(ds2);
        int max12 = O.indexOfMax(ds3);
        int max13 = O.indexOfMax(fs1);
        int max14 = O.indexOfMax(fs2);
        int max15 = O.indexOfMax(fs3);
        // System.out.println("max01: " + max01);
        // System.out.println("max02: " + max02);
        // System.out.println("max03: " + max03);
        // System.out.println("max04: " + max04);
        // System.out.println("max05: " + max05);
        // System.out.println("max06: " + max06);
        // System.out.println("max07: " + max07);
        // System.out.println("max08: " + max08);
        // System.out.println("max09: " + max09);
        // System.out.println("max10: " + max10);
        // System.out.println("max11: " + max11);
        // System.out.println("max12: " + max12);
        // System.out.println("max13: " + max13);
        // System.out.println("max14: " + max14);
        // System.out.println("max15: " + max15);

        assertEquals(max01, 0);
        assertEquals(max02, -1);
        assertEquals(max03, -1);
        assertEquals(max04, 2);
        assertEquals(max05, 0);
        assertEquals(max06, 2);
        assertEquals(max07, 3);
        assertEquals(max08, 3);
        assertEquals(max09, 1);
        assertEquals(max10, -1);
        assertEquals(max11, 1);
        assertEquals(max12, 1);
        assertEquals(max13, -1);
        assertEquals(max14, 1);
        assertEquals(max15, 1);

        int min01 = O.indexOfMin(new int[]{i});
        int min02 = O.indexOfMin(nullIs);
        int min03 = O.indexOfMin(ls);
        int min04 = O.indexOfMin(is);
        int min05 = O.indexOfMin(ss);
        int min06 = O.indexOfMin(cs);
        int min07 = O.indexOfMin(bs);
        int min08 = O.indexOfMin(ds);
        int min09 = O.indexOfMin(fs);
        int min10 = O.indexOfMin(ds1);
        int min11 = O.indexOfMin(ds2);
        int min12 = O.indexOfMin(ds3);
        int min13 = O.indexOfMin(fs1);
        int min14 = O.indexOfMin(fs2);
        int min15 = O.indexOfMin(fs3);
        // System.out.println("============");
        // System.out.println("min01: " + min01);
        // System.out.println("min02: " + min02);
        // System.out.println("min03: " + min03);
        // System.out.println("min04: " + min04);
        // System.out.println("min05: " + min05);
        // System.out.println("min06: " + min06);
        // System.out.println("min07: " + min07);
        // System.out.println("min08: " + min08);
        // System.out.println("min09: " + min09);
        // System.out.println("min10: " + min10);
        // System.out.println("min11: " + min11);
        // System.out.println("min12: " + min12);
        // System.out.println("min13: " + min13);
        // System.out.println("min14: " + min14);
        // System.out.println("min15: " + min15);

        assertEquals(min01, 0);
        assertEquals(min02, -1);
        assertEquals(min03, -1);
        assertEquals(min04, 4);
        assertEquals(min05, 1);
        assertEquals(min06, 3);
        assertEquals(min07, 1);
        assertEquals(min08, 1);
        assertEquals(min09, 3);
        assertEquals(min10, -1);
        assertEquals(min11, 2);
        assertEquals(min12, 4);
        assertEquals(min13, -1);
        assertEquals(min14, 2);
        assertEquals(min15, 4);

    }

    @Test
    public void testIndexOfMaxOrMin2() {
        Integer i = null;
        Double[] nullDs = null;
        Long[] ls = new Long[0];
        Number[] ns = {null, BigInteger.valueOf(200), new BigDecimal("100.0005"), 1, null};
        Long nullL = null;
        double d = 0.05;
        BigDecimal bigDecimal = BigDecimal.valueOf(100.50);
        long l = 101;
        byte b = 20;
        Number[] ns1 = {Double.NaN};
        Number[] ns2 = {Double.POSITIVE_INFINITY, null, Float.NaN, Double.NEGATIVE_INFINITY, Double.NaN};
        Number[] ns3 = {Double.POSITIVE_INFINITY, Float.NaN, null, Double.NEGATIVE_INFINITY, Double.NaN, 20.01, 19.81, Double.NEGATIVE_INFINITY};
        Number[] ns4 = {i, nullL, d, bigDecimal, l, b};

        int max01 = O.indexOfMax(nullDs);
        int max02 = O.indexOfMax(ls);
        int max03 = O.indexOfMax(ns);
        int max04 = O.indexOfMax(ns1);
        int max05 = O.indexOfMax(ns2);
        int max06 = O.indexOfMax(ns3);
        int max07 = O.indexOfMax(ns4);

        assertEquals(max01, -1);
        assertEquals(max02, -1);
        assertEquals(max03, 1);
        assertEquals(max04, 0);
        assertEquals(max05, 2);
        assertEquals(max06, 1);
        assertEquals(max07, 4);

        // System.out.println(max01);
        // System.out.println(max02);
        // System.out.println(max03);
        // System.out.println(max04);
        // System.out.println(max05);
        // System.out.println(max06);
        // System.out.println(max07);

        int min01 = O.indexOfMin(nullDs);
        int min02 = O.indexOfMin(ls);
        int min03 = O.indexOfMin(ns);
        int min04 = O.indexOfMin(ns1);
        int min05 = O.indexOfMin(ns2);
        int min06 = O.indexOfMin(ns3);
        int min07 = O.indexOfMin(ns4);
        assertEquals(min01, -1);
        assertEquals(min02, -1);
        assertEquals(min03, 3);
        assertEquals(min04, 0);
        assertEquals(min05, 3);
        assertEquals(min06, 3);
        assertEquals(min07, 2);
        // System.out.println("==========");
        // System.out.println(min01);
        // System.out.println(min02);
        // System.out.println(min03);
        // System.out.println(min04);
        // System.out.println(min05);
        // System.out.println(min06);
        // System.out.println(min07);

    }

    @Test
    public void testIsDouble() {
        Number number = null;
        boolean isDouble1 = O.isDouble(null);
        boolean isDouble2 = O.isDouble(0.1f);
        boolean isDouble3 = O.isDouble(0.2d);
        boolean isDouble4 = O.isDouble(987);
        boolean isDouble5 = O.isDouble(number);
        boolean isDouble6 = O.isDouble(Double.NaN);
        boolean isDouble7 = O.isDouble(Double.NEGATIVE_INFINITY);
        boolean isDouble8 = O.isDouble(Double.POSITIVE_INFINITY);
        boolean isDouble9 = O.isDouble(Float.NaN);
        boolean isDouble10 = O.isDouble(Float.NEGATIVE_INFINITY);
        boolean isDouble11 = O.isDouble(Float.POSITIVE_INFINITY);
        System.out.println(isDouble1);
        System.out.println(isDouble2);
        System.out.println(isDouble3);
        System.out.println(isDouble4);
        System.out.println(isDouble5);
        System.out.println(isDouble6);
        System.out.println(isDouble7);
        System.out.println(isDouble8);
        System.out.println(isDouble9);
        System.out.println(isDouble10);
        System.out.println(isDouble11);
        assertFalse(isDouble1);
        assertFalse(isDouble2);
        assertTrue(isDouble3);
        assertFalse(isDouble4);
        assertFalse(isDouble5);
        assertTrue(isDouble6);
        assertTrue(isDouble7);
        assertTrue(isDouble8);
        assertFalse(isDouble9);
        assertFalse(isDouble10);
        assertFalse(isDouble11);
    }

    @Test
    public void testToBigDecimal() {
        assertThrows(NumberFormatException.class, () -> O.toBigDecimal(Double.NaN));
        assertThrows(NumberFormatException.class, () -> O.toBigDecimal(0.0d / 0.0));
        assertThrows(NumberFormatException.class, () -> O.toBigDecimal(Double.POSITIVE_INFINITY));
        assertThrows(NumberFormatException.class, () -> O.toBigDecimal(Double.NEGATIVE_INFINITY));
        assertThrows(NumberFormatException.class, () -> O.toBigDecimal(Float.NaN));
        assertThrows(NumberFormatException.class, () -> O.toBigDecimal(Float.POSITIVE_INFINITY));
        assertThrows(NumberFormatException.class, () -> O.toBigDecimal(Float.NEGATIVE_INFINITY));
        assertNull(O.toBigDecimal(null));
        assertEquals(G.toString(O.toBigDecimal(1.0001f)), "1.0001");
        assertEquals(G.toString(O.toBigDecimal(9999999999.120000000089569999d)), "9999999999.12");
        assertEquals(G.toString(O.toBigDecimal(new BigDecimal("100.1000000"))), "100.1");
        assertEquals(G.toString(O.toBigDecimal(new BigInteger("-789789789789789789889789779"))), "-789789789789789789889789779.0");

        BigDecimal bd1 = BigDecimal.valueOf(0.1f);
        BigDecimal bd2 = BigDecimal.valueOf(0.000001f);
        System.out.println(bd1);                                // 0.10000000149011612
        System.out.println(O.toBigDecimal(0.1f));               // 0.1
        System.out.println(bd2);                                // 9.999999974752427E-7
        System.out.println(O.toBigDecimal(0.000001f));          // 0.0000010
        assertEquals(G.toString(O.toBigDecimal(0.1f)), "0.1");
        assertEquals(G.toString(O.toBigDecimal(0.000001f)), "0.000001");
    }


    @Test
    public void testFormat() {
        String pattern1 = "#.###";
        String pattern2 = "0.000";
        String pattern3 = "0.00";
        String pattern4 = "0.0%";
        String pattern5 = "0.00%";
        String pattern6 = "#.##%";

        String format01 = S.format(0.0d / 0.0, pattern1);
        String format02 = S.format(Float.POSITIVE_INFINITY, pattern1);
        String format03 = S.format(Double.NEGATIVE_INFINITY, pattern1);
        String format04 = S.format(1.0f, pattern1);
        String format05 = S.format(9999999999.120000000089569999d, pattern1);
        String format06 = S.format(9999999999l, pattern1);
        String format07 = S.format(-10.000198f, pattern1);
        String format08 = S.format(-0.01 / 0.0, pattern1);
        String format09 = S.format(897.66897975569f, pattern1);
        String format10 = S.format(Double.NaN, pattern1);
        String format11 = S.format(0.0d / 0.0, pattern2);
        String format12 = S.format(Float.POSITIVE_INFINITY, pattern2);
        String format13 = S.format(Double.NEGATIVE_INFINITY, pattern2);
        String format14 = S.format(1.0f, pattern3);
        String format15 = S.format(9999999999.120000000089569999d, pattern2);
        String format16 = S.format(9999999999l, pattern2);
        String format17 = S.format(-10.000198f, pattern2);
        String format18 = S.format(-0.01 / 0.0, pattern2);
        String format19 = S.format(897.66897975569f, pattern2);
        String format20 = S.format(Float.NaN, pattern2);
        String format21 = S.format(0.0d / 0.0, pattern4);
        String format22 = S.format(Float.POSITIVE_INFINITY, pattern5);
        String format23 = S.format(Double.NEGATIVE_INFINITY, pattern5);
        String format24 = S.format(1.0f, pattern6);
        String format25 = S.format(9999999999.120000000089569999d, pattern6);
        String format26 = S.format(9999999999l, pattern5);
        String format27 = S.format(-10.000198f, pattern5);
        String format28 = S.format(-0.01 / 0.0, pattern5);
        String format29 = S.format(897.66897975569f, pattern5);
        String format30 = S.format(Float.NaN, pattern5);

        System.out.println(pattern1 + ": " + format01);
        System.out.println(pattern1 + ": " + format02);
        System.out.println(pattern1 + ": " + format03);
        System.out.println(pattern1 + ": " + format04);
        System.out.println(pattern1 + ": " + format05);
        System.out.println(pattern1 + ": " + format06);
        System.out.println(pattern1 + ": " + format07);
        System.out.println(pattern1 + ": " + format08);
        System.out.println(pattern1 + ": " + format09);
        System.out.println(pattern1 + ": " + format10);
        System.out.println(pattern2 + ": " + format11);
        System.out.println(pattern2 + ": " + format12);
        System.out.println(pattern2 + ": " + format13);
        System.out.println(pattern3 + ": " + format14);
        System.out.println(pattern2 + ": " + format15);
        System.out.println(pattern2 + ": " + format16);
        System.out.println(pattern2 + ": " + format17);
        System.out.println(pattern2 + ": " + format18);
        System.out.println(pattern2 + ": " + format19);
        System.out.println(pattern2 + ": " + format20);
        System.out.println(pattern4 + ": " + format21);
        System.out.println(pattern5 + ": " + format22);
        System.out.println(pattern5 + ": " + format23);
        System.out.println(pattern6 + ": " + format24);
        System.out.println(pattern6 + ": " + format25);
        System.out.println(pattern5 + ": " + format26);
        System.out.println(pattern5 + ": " + format27);
        System.out.println(pattern5 + ": " + format28);
        System.out.println(pattern5 + ": " + format29);
        System.out.println(pattern5 + ": " + format30);

        assertEquals(format01, "NaN");
        assertEquals(format02, "∞");
        assertEquals(format03, "-∞");
        assertEquals(format04, "1");
        assertEquals(format05, "9999999999.12");
        assertEquals(format06, "9999999999");
        assertEquals(format07, "-10");
        assertEquals(format08, "-∞");
        assertEquals(format09, "897.669");
        assertEquals(format10, "NaN");
        assertEquals(format11, "NaN");
        assertEquals(format12, "∞");
        assertEquals(format13, "-∞");
        assertEquals(format14, "1.00");
        assertEquals(format15, "9999999999.120");
        assertEquals(format16, "9999999999.000");
        assertEquals(format17, "-10.000");
        assertEquals(format18, "-∞");
        assertEquals(format19, "897.669");
        assertEquals(format20, "NaN");
        assertEquals(format21, "NaN");
        assertEquals(format22, "∞%");
        assertEquals(format23, "-∞%");
        assertEquals(format24, "100%");
        assertEquals(format25, "999999999912%");
        assertEquals(format26, "999999999900.00%");
        assertEquals(format27, "-1000.02%");
        assertEquals(format28, "-∞%");
        assertEquals(format29, "89766.90%");
        assertEquals(format30, "NaN");

    }

}
