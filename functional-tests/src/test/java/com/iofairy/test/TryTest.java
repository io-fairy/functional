package com.iofairy.test;

import com.iofairy.tcf.Try;
import com.iofairy.top.G;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author GG
 */
public class TryTest {
    @Test
    public void testTry() {
        System.out.println(">>>>>testTry<<<<<");
        long startTime = System.currentTimeMillis() / 1000;
        System.out.println("start time: " + startTime);
        Try.sleep(1000);
        Try.tcf(() -> System.out.println("delay 1 second..."), true);
        long endTime = System.currentTimeMillis() / 1000;
        Try.sleep(10);
        System.out.println("end time: " + endTime);
        System.out.println("cost time: " + (endTime - startTime) + "(s)");
        Try.sleep(1000);
        Try.tcf(() -> {
            throw new NullPointerException("null pointer...");
        }, false);
    }

    @Test
    public void testTryDelay1() {
        System.out.println(">>>>>testTryDelay1<<<<<");
        long startTime = System.currentTimeMillis();

        Try.sleep(1000);
        Try.tcf(() -> {
            throw new NullPointerException("null pointer...");
        }, true);

        long cost = System.currentTimeMillis() - startTime;
        Try.sleep(10);
        System.out.println("cost time: " + cost + " ms");
        assertTrue(cost > 900);
    }

    @Test
    public void testTryDelay2() {
        System.out.println(">>>>>testTryDelay2<<<<<");
        long startTime = System.currentTimeMillis();

        Try.sleep(2000);
        Try.tcf(this::throwException);

        long cost = System.currentTimeMillis() - startTime;
        Try.sleep(10);
        System.out.println("cost time: " + cost + " ms");
        assertTrue(cost > 1900);
    }

    @Test
    public void testTryDelay3() {
        System.out.println(">>>>>testTryDelay2<<<<<");
        long startTime = System.currentTimeMillis();

        Try.sleep(TimeUnit.SECONDS, 2);
        Try.tcf(this::throwException, true);

        long cost = System.currentTimeMillis() - startTime;
        Try.sleep(10);
        System.out.println("cost time: " + cost + " ms");
        assertTrue(cost > 1900);
    }

    @Test
    public void testTryDefaultReturn() {
        System.out.println(">>>>>testTryDefaultReturn<<<<<");
        Integer tcf = Try.tcf(this::throwException1, -10, true);

        assertEquals(-10, tcf);
    }

    @Test
    public void testTryDefaultReturn1() {
        System.out.println(">>>>>testTryDefaultReturn1<<<<<");
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

    @Test
    public void testNewTcf() {
        Try.tcft(this::throwException1, -10);
        Try.sleep(100);
        Try.tcft(this::throwException1, -10, "orderId: ${orderId} has been closed! ", 10000);
        Try.sleep(100);
        Try.tcft(this::throwException, "orderId: ${orderId} has been closed! ");
        Try.sleep(100);
        Integer tcft = Try.tcft(this::throwException1, -10, "orderId: ${orderId} has been closed! ", 10000);
        System.out.println("tcft result: " + tcft);

        System.out.println("============================================================");
        System.out.println("============================================================");
        System.out.println("============================================================");
        Try.tcfl(this::throwException1, -10);
        Try.sleep(100);
        Try.tcfl(this::throwException1, -10, "orderId: ${orderId} has been closed! ", 10000);
        Try.sleep(100);
        Try.tcfl(this::throwException, "orderId: ${orderId} has been closed! ");
        Try.sleep(100);

        System.out.println("============================================================");
        System.out.println("============================================================");
        System.out.println("============================================================");
        Try.tcfs(this::throwException1, -10);
        Try.sleep(100);
        Try.tcfs(this::throwException1);
        Try.sleep(100);
        Try.tcfs(this::throwException);
        Try.sleep(100);

        System.out.println("============================================================");
        System.out.println("============================================================");
        System.out.println("============================================================");
        try {
            Try.tcfr(this::throwException1, -10);
        } catch (Exception e) {
            System.out.println("An exception was caught externally! ");
            assertNull(e.getMessage());
            System.out.println(G.stackTrace(e));
        }
        Try.sleep(100);
        try {
            Try.tcfr(this::throwException1, -10, "orderId: ${orderId} has been closed! ", 10000);
        } catch (Exception e) {
            System.out.println("An exception was caught externally! ");
            assertEquals("orderId: 10000 has been closed! ", e.getMessage());
            System.out.println(G.stackTrace(e));
        }
        Try.sleep(100);
        try {
            Try.tcfr(this::throwException, "orderId: ${orderId} has been closed! ");
        } catch (Exception e) {
            System.out.println("An exception was caught externally! ");
            assertEquals("orderId: ${orderId} has been closed! ", e.getMessage());
            System.out.println(G.stackTrace(e));
        }
        Try.sleep(100);

        System.out.println("============================================================");
        System.out.println("============================================================");
        System.out.println("============================================================");
        Try.sleep(100);
        Try.tcfc(() -> throwException1());
        Try.tcfc(this::throwException, t -> {
            System.out.println(t.getMessage());
            System.out.println("sleep 1s! ");
            Try.sleep(1000);
            System.out.println(" >> catch and do something");
        });
        Try.sleep(100);
        Try.tcfc(() -> throwException1(), t -> System.out.println(t.getMessage() + " >> catch and do something"));
        Try.sleep(100);
        Try.tcfc(this::throwException1, -10, t -> System.out.println(t.getMessage() + "catch and do something"));
        Try.sleep(100);
        Try.sleep(100);
        Try.tcfc(this::throwException);

    }
}
