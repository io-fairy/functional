package com.iofairy.test;

import com.iofairy.string.Ascii;
import com.iofairy.top.G;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author GG
 * @version 1.0
 */
public class AsciiTest {
    @Test
    public void testToUpperLower() {
        String s1 = null;
        String s2 = "";
        String s3 = "toUpperFirstCharABCDEFG";
        String s4 = "ToUpperFirstCharabcdefg";
        String s5 = "_ToUpperFirstCharabcdefg";

        assertNull(Ascii.toLower(s1));
        assertEquals(Ascii.toLower(s2), "");
        assertEquals(Ascii.toLower(s3), "toupperfirstcharabcdefg");
        assertEquals(Ascii.toLower(s4), "toupperfirstcharabcdefg");
        assertEquals(Ascii.toLower(s5), "_toupperfirstcharabcdefg");
        assertNull(Ascii.toUpper(s1));
        assertEquals(Ascii.toUpper(s2), "");
        assertEquals(Ascii.toUpper(s3), "TOUPPERFIRSTCHARABCDEFG");
        assertEquals(Ascii.toUpper(s4), "TOUPPERFIRSTCHARABCDEFG");
        assertEquals(Ascii.toUpper(s5), "_TOUPPERFIRSTCHARABCDEFG");
        assertNull(Ascii.toLowerFirstChar(s1));
        assertEquals(Ascii.toLowerFirstChar(s2), "");
        assertEquals(Ascii.toLowerFirstChar(s3), "toUpperFirstCharABCDEFG");
        assertEquals(Ascii.toLowerFirstChar(s4), "toUpperFirstCharabcdefg");
        assertEquals(Ascii.toLowerFirstChar(s5), "_ToUpperFirstCharabcdefg");
        assertNull(Ascii.toUpperFirstChar(s1));
        assertEquals(Ascii.toUpperFirstChar(s2), "");
        assertEquals(Ascii.toUpperFirstChar(s3), "ToUpperFirstCharABCDEFG");
        assertEquals(Ascii.toUpperFirstChar(s4), "ToUpperFirstCharabcdefg");
        assertEquals(Ascii.toUpperFirstChar(s5), "_ToUpperFirstCharabcdefg");
    }

    @Test
    public void testCountCases() {
        String s1 = null;
        String s2 = "";
        String s3 = "$%^678 AblkjAE hhByyZ";
        String s4 = "$%^678中Ablkj文AEhhB字y符yZ";

        assertEquals(G.toString(Ascii.countCases(s1)), "[0, 0, 0]");
        assertEquals(G.toString(Ascii.countCases(s2)), "[0, 0, 0]");
        assertEquals(G.toString(Ascii.countCases(s3)), "[5, 8, 8]");
        assertEquals(G.toString(Ascii.countCases(s4)), "[5, 8, 10]");
    }
}
