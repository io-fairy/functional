package com.iofairy.test;

import com.iofairy.except.ConditionsNotMetException;
import com.iofairy.except.OutOfBoundsException;
import com.iofairy.top.G;
import org.junit.jupiter.api.Test;

import java.io.IOException;

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
            assertEquals("数值超出所允许的范围，当前值为：[10.987678999]。", e.getMessage());
        }
        System.out.println("---------------------------------------------------------------------------------------");
        try {
            throw new OutOfBoundsException(10.98767000033D, "数值必须小于[10]。");
        } catch (OutOfBoundsException e) {
            System.out.println(G.stackTrace(e));
            assertEquals("数值超出所允许的范围，当前值为：[10.98767]。数值必须小于[10]。", e.getMessage());
        }
    }

    @Test
    public void testConditionsNotMetException() {
        try {
            throw new ConditionsNotMetException("orderId: ${0}, orderName: ${?}, `orderStatus` must be non-empty! ", 10000, "'order_test'");
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
            throw new ConditionsNotMetException("userId: ${…}, `phone` must be non-empty! ", 10000);
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

    @Test
    public void testStackTraceSimple() {
        System.out.println("============================================================");
        try {
            addSuppressed();
        } catch (Exception e) {
            System.out.println(G.stackTraceSimple(e, "com.iofairy" ));
            System.out.println("============================================================");
            System.out.println(G.stackTrace(e));
        }
    }

    private void addSuppressed() {
        Throwable throwable = null;
        try {
            throw new RuntimeException(new IOException("IO异常！"));
        } catch (Exception e) {
            throwable = e;
            throw e;
        } finally {
            RuntimeException runtimeException = new RuntimeException(new IOException("finally抛出的异常！"));
            if (throwable != null) {
                runtimeException.addSuppressed(throwable);
                runtimeException.addSuppressed(new RuntimeException("其他异常！"));
            }
            throw runtimeException;
        }
    }

}
