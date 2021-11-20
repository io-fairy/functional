package com.iofairy.test;

import com.iofairy.top.G;
import com.iofairy.tuple.Tuple2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author GG
 * @version 1.0
 */
public class GlobalTest {
    @Test
    public void testEmptyAndNull() {
        int i = 10;
        String s1 = "";
        String s2 = "abcd";
        String s5 = "abcd1";
        String s3 = null;
        Object o1 = new Object();
        Object o2 = null;
        Integer i1 = null;
        String s4 = "";
        String[] nullSs = null;
        String[] ss1 = new String[]{};
        String[] ss2 = new String[]{""};

        assertFalse(G.hasNull(i, o1, s1, s2));
        assertTrue(G.hasNull(i, o1, s1, s3));
        assertFalse(G.hasEmpty(s2, s5));
        assertTrue(G.hasEmpty(s2, s5, s3));
        assertTrue(G.hasEmpty(s2, s5, s1));
        assertFalse(G.allNull(i, s1, s3, i1));
        assertTrue(G.allNull(s3, o2, i1));
        assertFalse(G.allEmpty(s1, s2, s3));
        assertTrue(G.allEmpty(s1, s3, s4));
        assertTrue(G.isEmpty(nullSs));
        assertTrue(G.isEmpty(ss1));
        assertFalse(G.isEmpty(ss2));


    }

    @Test
    public void testBlank() {

        String s = "  \n \t　　　\f  ";
        String s1 = "  \n \t　　a　\f  ";
        String s2 = "  \n\n　　\f  ";
        String s3 = "  aaaa　\f  ";
        String s4 = null;
        String s5 = "";
        String[] nullSs = null;
        String[] nullSs1 = new String[]{};

        assertTrue(G.isBlank(s));
        assertFalse(G.isBlank(s1));
        assertTrue(G.isBlank(s2));
        assertTrue(G.hasBlank(s, s1));
        assertTrue(G.hasBlank(s4, s5));
        assertFalse(G.hasBlank(s1, s3));
        assertTrue(G.hasBlank(nullSs));
        assertTrue(G.hasBlank(nullSs1));
        assertTrue(G.allBlank(s, s2, s4, s5));
        assertFalse(G.allBlank(s, s1, s4, s5));
        assertTrue(G.allBlank(nullSs));
        assertTrue(G.allBlank(nullSs1));

    }

    @Test
    public void testFirstNonNull() {
        String a = null;
        String b = "";
        String c = null;
        String d = "abc";
        Object o = new Object();

        String s = G.firstNonNull(a, b, c);
        String s1 = G.firstNonNull(a, c);
        String s2 = G.firstNonNull();
        String s3 = G.firstNonNull((String[]) null);
        String s4 = G.firstNonNull(a, d, c);
        Object o1 = G.firstNonNull(a, d, c, o);

        assertEquals("", s);
        assertNull(s1);
        assertNull(s2);
        assertNull(s3);
        assertEquals("abc", s4);
        assertEquals("abc", o1);

    }

    @Test
    public void testStackTrace() {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            System.out.println(G.stackTrace(e));
        }
    }

    @Test
    public void testSplitOnce() {
        Tuple2<String, String> t00 = G.splitOnce(":", ": ");
        Tuple2<String, String> t01 = G.splitOnce(null, ": ");
        Tuple2<String, String> t02 = G.splitOnce(": ", null);
        Tuple2<String, String> t03 = G.splitOnce(": ", ": ");
        Tuple2<String, String> t04 = G.splitOnce(": 1", ": ");
        Tuple2<String, String> t05 = G.splitOnce("a: ", ": ");
        Tuple2<String, String> t06 = G.splitOnce("a: 1", ": ");
        Tuple2<String, String> t07 = G.splitOnce(" :  ", ": ");
        Tuple2<String, String> t08 = G.splitOnce(" : : ", ": ");
        Tuple2<String, String> t09 = G.splitOnce(" :abc ", ": ");
        Tuple2<String, String> t10 = G.splitOnce("", "");
        Tuple2<String, String> t11 = G.splitOnce(null, null);

        assertEquals(t00._1 + "||" + t00._2, ":||null");
        assertEquals(t01._1 + "||" + t01._2, "null||null");
        assertEquals(t02._1 + "||" + t02._2, ": ||null");
        assertEquals(t03._1 + "||" + t03._2, "||");
        assertEquals(t04._1 + "||" + t04._2, "||1");
        assertEquals(t05._1 + "||" + t05._2, "a||");
        assertEquals(t06._1 + "||" + t06._2, "a||1");
        assertEquals(t07._1 + "||" + t07._2, " || ");
        assertEquals(t08._1 + "||" + t08._2, " ||: ");
        assertEquals(t09._1 + "||" + t09._2, " :abc ||null");
        assertEquals(t10._1 + "||" + t10._2, "||null");
        assertEquals(t11._1 + "||" + t11._2, "null||null");

    }
}
