package com.iofairy;

import com.iofairy.lambda.RT0;
import com.iofairy.tcf.Try;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author GG
 */
public class TryTest {
    @Test
    public void testTry() {
        long startTime = System.currentTimeMillis() / 1000;
        System.out.println("start time: " + startTime);
        Try.tcf(() -> System.out.println("delay 1 second..."), true, 1);
        long endTime = System.currentTimeMillis() / 1000;
        System.out.println("end time: " + endTime);
        System.out.println("cost time: " + (endTime - startTime) + "(s)");
        Try.tcf(() -> { throw new NullPointerException("null pointer..."); });
    }

    @Test
    public void testTryDelay1() {
        long startTime = System.currentTimeMillis();

        Try.tcf(() -> { throw new NullPointerException("null pointer..."); }, true, 1);

        long cost = System.currentTimeMillis() - startTime;
        System.out.println("cost time: " + cost + " ms");
        assertTrue(cost > 1000);
    }

    @Test
    public void testTryDelay2() {
        long startTime = System.currentTimeMillis();

        Try.tcf(this::throwException, true, TimeUnit.MILLISECONDS, 2000);

        long cost = System.currentTimeMillis() - startTime;
        System.out.println("cost time: " + cost + " ms");
        assertTrue(cost > 1900);
    }

    @Test
    public void testTryDefaultReturn() {

        Integer tcf = Try.tcf(this::throwException1, -10, true);

        assertEquals(-10, tcf);
    }

    @Test
    public void testTryDefaultReturn1() {

        Integer tcf = Try.tcf(this::throwException1, -10, null);
        Integer tcf1 = Try.tcf(this::throwException1, null);

        assertEquals(-10, tcf);
        assertEquals(null, tcf1);
    }

    void throwException() {
        throw new NullPointerException("null pointer...");
    }

    Integer throwException1() {
        throw new NullPointerException("null pointer...");
    }


}
