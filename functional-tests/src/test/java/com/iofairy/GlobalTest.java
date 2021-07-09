package com.iofairy;

import com.iofairy.util.G;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        assertFalse(G.hasNull(i, o1, s1, s2));
        assertTrue(G.hasNull(i, o1, s1, s3));
        assertFalse(G.hasEmpty(s2, s5));
        assertTrue(G.hasEmpty(s2, s5, s3));
        assertTrue(G.hasEmpty(s2, s5, s1));
        assertFalse(G.allNull(i, s1, s3, i1));
        assertTrue(G.allNull(s3, o2, i1));
        assertFalse(G.allEmpty(s1, s2, s3));
        assertTrue(G.allEmpty(s1, s3, s4));

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
}
