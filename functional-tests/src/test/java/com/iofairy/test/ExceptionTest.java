package com.iofairy.test;

import com.iofairy.except.ConditionsNotMetException;
import com.iofairy.except.OutOfBoundsException;
import com.iofairy.top.G;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author GG
 * @version 1.0
 */
public class ExceptionTest {
    @Test
    public void testOutOfBoundsException() {
        try {
            throw new OutOfBoundsException(10.98767899876D);
        } catch (OutOfBoundsException e) {
            System.out.println(G.stackTrace(e));
            assertEquals("The value out of range, the current value is: [10.987678999]. ", e.getMessage());
        }
        System.out.println("---------------------------------------------------------------------------------------");
        try {
            throw new OutOfBoundsException(10.98767000033D, "The value must be less than [10]. ");
        } catch (OutOfBoundsException e) {
            System.out.println(G.stackTrace(e));
            assertEquals("The value out of range, the current value is: [10.98767]. The value must be less than [10]. ", e.getMessage());
        }
    }

    @Test
    public void testConditionsNotMetException() {
        try {
            throw new ConditionsNotMetException("orderId: ${0}, orderName: ${0}, `orderStatus` must be non-empty! ", 10000, "'order_test'");
        } catch (Exception e) {
            System.out.println(G.stackTraceSimple(e, "com.iofairy"));
            System.out.println("=====================");
            System.out.println(G.stackTrace(e));
            assertEquals("orderId: 10000, orderName: 'order_test', `orderStatus` must be non-empty! ", e.getMessage());
        }
        System.out.println("============================================================");
        System.out.println("============================================================");
        try {
            throw new ConditionsNotMetException("userId: ${_}, `phone` must be non-empty! ", 10000);
        } catch (Exception e) {
            System.out.println(G.stackTraceSimple(e, "com.iofairy"));
            System.out.println("=====================");
            System.out.println(G.stackTrace(e));
            assertEquals("userId: 10000, `phone` must be non-empty! ", e.getMessage());
        }
        System.out.println("============================================================");
        System.out.println("============================================================");
        try {
            throw new ConditionsNotMetException("`orderStatus` must be non-empty! ");
        } catch (Exception e) {
            System.out.println(G.stackTraceSimple(e, "com.iofairy"));
            System.out.println("=====================");
            System.out.println(G.stackTrace(e));
            assertEquals("`orderStatus` must be non-empty! ", e.getMessage());
        }

        System.out.println("============================================================");
        System.out.println("============================================================");
        try {
            throw new ConditionsNotMetException(new RuntimeException("发生了运行时异常！"), "userId: ${_}, `phone` must be non-empty! ", 10000);
        } catch (Exception e) {
            System.out.println(G.stackTraceSimple(e, "com.iofairy"));
            System.out.println("=====================");
            System.out.println(G.stackTrace(e));
            assertEquals("userId: 10000, `phone` must be non-empty! ", e.getMessage());
        }
        System.out.println("============================================================");
        System.out.println("============================================================");
        try {
            throw new ConditionsNotMetException(new RuntimeException("发生了运行时异常！"), "`orderStatus` must be non-empty! ");
        } catch (Exception e) {
            System.out.println(G.stackTraceSimple(e, "com.iofairy"));
            System.out.println("=====================");
            System.out.println(G.stackTrace(e));
            assertEquals("`orderStatus` must be non-empty! ", e.getMessage());
        }

    }
}
