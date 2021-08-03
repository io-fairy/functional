package com.iofairy;

import com.iofairy.tcf.Close;
import com.iofairy.tcf.Try;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.sql.*;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author GG
 */
public class CloseTest {

    @Test
    public void testCloseMethod() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            System.out.println("testCloseMethod: do something");
            throw new Exception();
        } catch (Exception e) {
            System.out.println("testCloseMethod: do something when an exception occurs");
        } finally {
            Close.close(rs);
            Close.close(st);
            Close.close(conn);

            System.out.println("testCloseMethod: Close all resources");
        }
    }

    @Test
    public void testCloseMethod1() {
        InputStream in = null;
        try {
            in = CloseTest.class.getClassLoader().getResourceAsStream("TestClose.txt");
        } catch (Exception e) {
            System.out.println("testCloseMethod1: do something when an exception occurs");
        } finally {
            long startTime = System.currentTimeMillis();
            Close.close(in, true, 1);
            assertTrue(System.currentTimeMillis() - startTime > 900);

            System.out.println("testCloseMethod1: Close all resources");
        }
    }

    @Test
    public void testClose() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            System.out.println("testClose: do something");
            throw new Exception();
        } catch (Exception e) {
            System.out.println("testClose: do something when an exception occurs");
        } finally {
            Close.of(rs).tcf(ResultSet::close);
            Close.of(st).tcf(Statement::close);
            Close.of(conn).tcf(Connection::close);

            System.out.println("testClose: Close all resources");
        }
    }

    @Test
    public void testCloseAndThrow() {
        InputStream in = null;
        try {
            in = CloseTest.class.getClassLoader().getResourceAsStream("TestClose.txt");
        } catch (Exception e) {
            System.out.println("testCloseAndThrow: do something when an exception occurs");
        } finally {
            /*
             * will print log if an exception occurs during close
             */
            Close.of(in).tcf(c -> { c.close(); throw new Exception("occur exception when close"); });

            Try.tcf(() -> Thread.sleep(10));
            System.out.println("testCloseAndThrow: Close all resources");
        }
    }

    @Test
    public void testCloseSilently() {
        InputStream in = null;
        try {
            in = CloseTest.class.getClassLoader().getResourceAsStream("TestClose.txt");
        } catch (Exception e) {
            System.out.println("testCloseSilently: do something when an exception occurs");
        } finally {
            /*
             * will be silently(do nothing) if an exception occurs during close
             */
            Close.of(in).tcf(c -> { c.close(); throw new Exception("occur exception when close"); }, false);

            System.out.println("testCloseSilently: Close all resources");
        }
    }

    @Test
    public void testCloseDelay1() {
        InputStream in = null;
        try {
            in = CloseTest.class.getClassLoader().getResourceAsStream("TestClose.txt");
        } catch (Exception e) {
            System.out.println("testCloseDelay1: do something when an exception occurs");
        } finally {
            long startTime = System.currentTimeMillis();
            /*
             * delay 1 second before close
             */
            Close.of(in).tcf(c -> { c.close(); throw new Exception("occur exception when close"); }, false, 1);

            System.out.println("cost time: " + (System.currentTimeMillis() - startTime));

            assertTrue((System.currentTimeMillis() - startTime) > 1000);

            System.out.println("testCloseDelay1: Close all resources");
        }
    }

    @Test
    public void testCloseDelay2() {
        InputStream in = null;
        try {
            in = CloseTest.class.getClassLoader().getResourceAsStream("TestClose.txt");
        } catch (Exception e) {
            System.out.println("testCloseDelay2: do something when an exception occurs");
        } finally {
            long startTime = System.currentTimeMillis();
            /*
             * delay 1 second before close
             */
            Close.of(in).tcf(c -> { c.close(); throw new Exception("occur exception when close"); }, null, null, TimeUnit.MILLISECONDS, 1000);
            System.out.println("cost time: " + (System.currentTimeMillis() - startTime));

            assertTrue((System.currentTimeMillis() - startTime) > 900);

            System.out.println("testCloseDelay2: Close all resources");
        }
    }

    @Test
    public void testCloseMethodThrowAction() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            System.out.println("testCloseMethodThrowAction: do something");
            throw new Exception();
        } catch (Exception e) {
            System.out.println("testCloseMethodThrowAction: do something when an exception occurs");
        } finally {
            Close.close(rs, (c, e) -> e.printStackTrace());
            Close.close(st, (c, e) -> e.printStackTrace());
            Close.close(conn, (c, e) -> e.printStackTrace());

            System.out.println("testCloseMethodThrowAction: Close all resources");
        }
    }

    @Test
    public void testThrowAction() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            System.out.println("testThrowAction: do something");
            throw new Exception();
        } catch (Exception e) {
            System.out.println("testThrowAction: do something when an exception occurs");
        } finally {
            Close.of(rs).tcf(ResultSet::close, (c, e) -> e.printStackTrace());
            Close.of(st).tcf(Statement::close, (c, e) -> e.printStackTrace());
            Close.of(conn).tcf(Connection::close, (c, e) -> e.printStackTrace());

            System.out.println("testThrowAction: Close all resources");
        }
    }

    @Test
    public void testCloseMethodFinallyAction() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            System.out.println("testCloseMethodFinallyAction: do something");
            throw new Exception();
        } catch (Exception e) {
            System.out.println("testCloseMethodFinallyAction: do something when an exception occurs");
        } finally {
            Close.close(rs, null, f -> System.out.println("finally action"));
            Close.close(st, null, f -> System.out.println("finally action"));
            Close.close(conn, null, f -> System.out.println("finally action"));

            System.out.println("testCloseMethodFinallyAction: Close all resources");
        }
    }

    @Test
    public void testFinallyAction() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            System.out.println("testFinallyAction: do something");
            throw new Exception();
        } catch (Exception e) {
            System.out.println("testFinallyAction: do something when an exception occurs");
        } finally {
            Close.of(rs).tcf(ResultSet::close, null, f -> System.out.println("finally action"));
            Close.of(st).tcf(Statement::close, null, f -> System.out.println("finally action"));
            Close.of(conn).tcf(Connection::close, null, f -> System.out.println("finally action"));

            System.out.println("testFinallyAction: Close all resources");
        }
    }

    @Test
    public void testCloseMethodDelay() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            System.out.println("testCloseMethodDelay: do something");
            throw new Exception();
        } catch (Exception e) {
            System.out.println("testCloseMethodDelay: do something when an exception occurs");
        } finally {
            Close.close(rs, null, null, TimeUnit.MILLISECONDS, 1000);
            Close.close(st, null, f -> System.out.println("finally action"));
            Close.close(conn, null, f -> System.out.println("finally action"));

            System.out.println("testCloseMethodDelay: Close all resources");
        }
    }

    @Test
    public void testDelay() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            System.out.println("testDelay: do something");
            throw new Exception();
        } catch (Exception e) {
            System.out.println("testDelay: do something when an exception occurs");
        } finally {
            Close.of(rs).tcf(ResultSet::close, null, null, TimeUnit.MILLISECONDS, 1000);
            Close.of(st).tcf(Statement::close, null, f -> System.out.println("finally action"));
            Close.of(conn).tcf(Connection::close, null, f -> System.out.println("finally action"));

            System.out.println("testDelay: Close all resources");
        }
    }


}
