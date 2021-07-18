package com.iofairy;

import com.iofairy.tcf.Close;
import com.iofairy.tcf.Try;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.sql.*;

/**
 * @author GG
 */
public class CloseTest {

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
            Close.of(in).tcf(c -> { c.close(); throw new Exception("occur exception when close"); }, null);

            System.out.println("testCloseSilently: Close all resources");
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


}
