package com.iofairy.test;

import com.iofairy.base.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author GG
 * @version 1.0
 * @date 2022/6/11 18:23
 */
public class BaseTest {
    @Test
    public void testBase() {
        assertFalse(False.FALSE.value);
        assertTrue(True.TRUE.value);
        assertFalse(No.NO.value);
        assertTrue(Yes.YES.value);
        assertEquals(Infinity.INFINITY.negativeInfinity, NegativeInfinity.NEGATIVE_INFINITY.value);
        assertEquals(Infinity.INFINITY.positiveInfinity, PositiveInfinity.POSITIVE_INFINITY.value);
        assertEquals(0, Double.compare(NaN.NAN.value, Float.NaN));
        assertEquals(0, Zero.ZERO.value);
        assertEquals(1, One.ONE.value);
        assertEquals("", Empty.EMPTY.value);
        assertEquals('\0', Top.Nul.NUL.value);
    }
}
