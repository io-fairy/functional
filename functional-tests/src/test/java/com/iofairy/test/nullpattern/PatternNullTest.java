package com.iofairy.test.nullpattern;

import com.iofairy.lambda.R1;
import com.iofairy.top.G;
import com.iofairy.top.S;
import com.iofairy.tuple.*;
import org.junit.jupiter.api.Test;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

import static com.iofairy.pattern.Pattern.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author GG
 * @version 1.0
 */
public class PatternNullTest {
    @Test
    public void testNull1() {
        Account account = new Account("12345", "abc", "aaaa");
        Order order = new Order("order_123456", 10.5, new User("zs", 10, account));

        Tuple9<User, Account, String, Double, String, Integer, String, String, String> values = matchNull()
                .whenV(order,   v -> v.buyer,       "order is null or order.buyer is null!")
                .whenW(VALUE1,  v -> v.account,     "order.buyer.account is null!")
                .whenV(order,   v -> v.orderId,     S::isBlank, "order.orderId is blank!")
                .whenV(order,   v -> v.price,       v -> v < 0, "order.price < 0!")
                .whenW(VALUE1,  v -> v.name,        S::isEmpty, "order.buyer.name is empty!")
                .whenW(VALUE1,  v -> v.age,         v -> v < 0, "order.buyer.age < 0!")
                .whenW(VALUE2,  v -> v.id,          S::isBlank, "order.buyer.account.id is blank!")
                .whenW(VALUE2,  v -> v.userName,    "order.buyer.account.userName is null!")
                .orElse(null);

        String msg = values._9;         // null
        assertNull(msg);

        System.out.println("msg: " + msg);
        if (msg != null) {
            return;
        }

        assertEquals("12345", values._7);

        System.out.println("order.buyer: " + values._1);
        System.out.println("order.buyer.account: " + values._2);
        System.out.println("order.orderId: " + values._3);
        System.out.println("order.price: " + values._4);
        System.out.println("order.buyer.name: " + values._5);
        System.out.println("order.buyer.age: " + values._6);
        System.out.println("order.buyer.account.id: " + values._7);
        System.out.println("order.buyer.account.userName: " + values._8);

    }

    @Test
    public void testNull2() {
        Account account = null;
        Order order = new Order("order_123456", 10.5, new User("zs", 10, account));

        Tuple7<User, Account, String, Double, String, Integer, String> values = matchNull()
                .whenV(order,   v -> v.buyer,   "order is null or order.buyer is null!")
                .whenW(VALUE1,  v -> v.account, v -> "user " + v._1.name + "'s account is null!")
                .whenV(order,   v -> v.orderId, S::isBlank, "order.orderId is blank!")
                .whenV(order,   v -> v.price,   v -> v < 0, "order.price < 0!")
                .whenW(VALUE1,  v -> v.name,    S::isEmpty, "order.buyer.name is empty!")
                .whenW(VALUE1,  v -> v.age,     v -> v < 0, "order.buyer.age < 0!")
                .orElse(null);

        String msg = values._7;
        assertEquals("user zs's account is null!", msg);

        System.out.println("msg: " + msg);      // user zs's account is null!
        if (msg != null) {
            return;
        }

        System.out.println("order.buyer: " + values._1);
        System.out.println("order.buyer.account: " + values._2);
        System.out.println("order.orderId: " + values._3);
        System.out.println("order.price: " + values._4);
        System.out.println("order.buyer.name: " + values._5);
        System.out.println("order.buyer.age: " + values._6);

    }

    @Test
    public void testNull3() {
        Account account = null;
        Order order = new Order("order_123456", 10.5, new User("zs", 10, account));

        String msg = null;
        if (order == null || order.buyer == null) {
            msg = "order is null or order.buyer is null!";
            return;
        }
        User user = order.buyer;
        if (user.account == null) {
            msg = "user " + user.name + "'s account is null!";
            return;
        }
        if (S.isBlank(order.orderId)) {
            msg = "order.orderId is blank!";
            return;
        }
        if (order.price < 0) {
            msg = "order.price < 0!";
            return;
        }
        if (G.isEmpty(user.name)) {
            msg = "order.buyer.name is empty!";
            return;
        }
        if (user.age < 0) {
            msg = "order.buyer.age < 0!";
            return;
        }

    }

    @Test
    public void testNull4() {
        Account account = new Account("12345", "abc", "aaaa");
        Order order = new Order("order_123456", 10.5, null);

        Tuple9<User, Account, String, Double, String, Integer, String, String, String> values = matchNull()
                .whenV(order,   v -> v.buyer,       "order is null or order.buyer is null!")
                .whenW(VALUE1,  v -> v.account,     "order.buyer.account is null!")
                .whenV(order,   v -> v.orderId,     S::isBlank, "order.orderId is blank!")
                .whenV(order,   v -> v.price,       v -> v < 0, "order.price < 0!")
                .whenW(VALUE1,  v -> v.name,        S::isEmpty, "order.buyer.name is empty!")
                .whenW(VALUE1,  v -> v.age,         v -> v < 0, "order.buyer.age < 0!")
                .whenW(VALUE2,  v -> v.id,          S::isBlank, "order.buyer.account.id is blank!")
                .whenW(VALUE2,  v -> v.userName,    "order.buyer.account.userName is null!")
                .orElse(null);

        String msg = values._9;
        assertEquals("order is null or order.buyer is null!", msg);

        System.out.println("msg: " + msg);      // order is null or order.buyer is null!
        if (msg != null) {
            return;
        }

        System.out.println("order.buyer: " + values._1);
        System.out.println("order.buyer.account: " + values._2);
        System.out.println("order.orderId: " + values._3);
        System.out.println("order.price: " + values._4);
        System.out.println("order.buyer.name: " + values._5);
        System.out.println("order.buyer.age: " + values._6);
        System.out.println("order.buyer.account.id: " + values._7);
        System.out.println("order.buyer.account.userName: " + values._8);

    }

    @Test
    public void testNullPatternCovariance() {
        Account account = null;
        Order order = new Order("order_123456", 10.5, new User("zs", 10, account));

        R1<Object, Account> oaR1 = v -> ((User)v).account;

        Tuple7<User, Account, String, Double, String, Integer, String> values = matchNull()
                .whenV(order,   v -> v.buyer,   "order is null or order.buyer is null!")
                .whenW(VALUE1,  oaR1,           v -> "user " + v._1.name + "'s account is null!")
                .whenV(order,   v -> v.orderId, S::isBlank, "order.orderId is blank!")
                .whenV(order,   v -> v.price,   v -> v < 0, "order.price < 0!")
                .whenW(VALUE1,  v -> v.name,    S::isEmpty, "order.buyer.name is empty!")
                .whenW(VALUE1,  v -> v.age,     v -> v < 0, "order.buyer.age < 0!")
                .orElse(null);

        String msg = values._7;
        assertEquals("user zs's account is null!", msg);

        System.out.println("msg: " + msg);      // user zs's account is null!
        if (msg != null) {
            return;
        }

        System.out.println("order.buyer: " + values._1);
        System.out.println("order.buyer.account: " + values._2);
        System.out.println("order.orderId: " + values._3);
        System.out.println("order.price: " + values._4);
        System.out.println("order.buyer.name: " + values._5);
        System.out.println("order.buyer.age: " + values._6);

    }

    @Test
    public void testNullWithThrow() {
        Account account = new Account("12345", "abc", "aaaa");
        Order order = new Order("order_123456", 10.5, new User("", 10, account));

        Tuple9<User, Account, String, String, String, Integer, String, String, String> values = null;
        try {
            values = matchNull()
                    .whenV(order,   v -> v.buyer,       "order is null or order.buyer is null!")
                    .whenW(VALUE1,  v -> v.account,     "order.buyer.account is null!")
                    .whenV(order,   v -> v.orderId,     S::isBlank, "order.orderId is blank!")
                    .withV(order,   v -> throwXMLException(), "throwXMLException!")
                    .whenW(VALUE1,  v -> v.name,        S::isEmpty, "order.buyer.name is empty!")
                    .whenW(VALUE1,  v -> v.age,         v -> v < 0, "order.buyer.age < 0!")
                    .whenW(VALUE2,  v -> v.id,          S::isBlank, "order.buyer.account.id is blank!")
                    .withW(VALUE2,  v -> v.userName,    v -> throwIOException(), "order.buyer.account.userName is null!")
                    .orElse(null);
        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }

        String msg = values._9;
        assertEquals("order.buyer.name is empty!", msg);

        System.out.println("msg: " + msg);      // order is null or order.buyer is null!
        if (msg != null) {
            return;
        }

        System.out.println("order.buyer: " + values._1);
        System.out.println("order.buyer.account: " + values._2);
        System.out.println("order.orderId: " + values._3);
        System.out.println("order.price: " + values._4);
        System.out.println("order.buyer.name: " + values._5);
        System.out.println("order.buyer.age: " + values._6);
        System.out.println("order.buyer.account.id: " + values._7);
        System.out.println("order.buyer.account.userName: " + values._8);

    }

    private boolean throwIOException() throws IOException {
        throw new IOException();
    }

    private String throwXMLException() throws XMLStreamException {
        if (false) {
            throw new XMLStreamException("XMLStreamException...");
        }
        return "";
    }

}
