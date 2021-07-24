package com.iofairy;

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

        Try.tcf(() -> { throw new NullPointerException("null pointer..."); }, true, TimeUnit.MILLISECONDS, 2000);

        long cost = System.currentTimeMillis() - startTime;
        System.out.println("cost time: " + cost + " ms");
        assertTrue(cost > 2000);
    }

}
