package com.iofairy;

import com.iofairy.util.Close;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.sql.*;

/**
 * @author GG
 * @version 1.0
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
            Close.of(rs).run(ResultSet::close);
            Close.of(st).run(Statement::close);
            Close.of(conn).run(Connection::close);

            System.out.println("testClose: Close all resources");
        }
    }

    @Test
    public void testCloseAndThrow() {
        InputStream in = null;
        try {
            in = new FileInputStream("E:/testClose.txt");
        } catch (Exception e) {
            System.out.println("testCloseAndThrow: do something when an exception occurs");
        } finally {
            Close.of(in).run(c -> { c.close(); throw new Exception("occur exception when close"); });

            System.out.println("testCloseAndThrow: Close all resources");
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
            Close.of(rs).run(ResultSet::close, e -> e.printStackTrace());
            Close.of(st).run(Statement::close, e -> e.printStackTrace());
            Close.of(conn).run(Connection::close, e -> e.printStackTrace());

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
            Close.of(rs).run(ResultSet::close, null, f -> System.out.println("finally action"));
            Close.of(st).run(Statement::close, null, f -> System.out.println("finally action"));
            Close.of(conn).run(Connection::close, null, f -> System.out.println("finally action"));

            System.out.println("testFinallyAction: Close all resources");
        }
    }


}
