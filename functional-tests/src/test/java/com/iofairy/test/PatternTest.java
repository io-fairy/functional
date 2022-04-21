package com.iofairy.test;

import com.iofairy.lambda.R1;
import com.iofairy.pattern.PatternIn;
import com.iofairy.tuple.*;
import com.iofairy.top.G;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static com.iofairy.pattern.Pattern.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * @author GG
 */
public class PatternTest {

    @Test
    public void testPatternValue1() {
        String s = "5";
        String result = match(s)
                .when("1",                      v -> v + v)
                .when("2",                      v -> v + "a")
                .when(in("3", "4", "5", "6"),   v -> v + " - abcd")
                .orElse(                        v -> "no match");

        System.out.println("match result: " + result);
        assertEquals("5 - abcd", result);

        /*
         * it is equivalent to the code below
         */
        String switchResult;
        switch (s) {
            case "1":
                switchResult = s + s;
                break;
            case "2":
                switchResult = s + "a";
                break;
            case "3":
            case "4":
            case "5":
            case "6":
                switchResult = s + " - abcd";
                break;
            default:
                switchResult = "no match";
        }

        assertEquals("5 - abcd", switchResult);


        Season season = Season.AUTUMN;
        String sRes = match(season)
                .when(Season.SPRING, v -> "it is " + v)
                .when(Season.SUMMER, v -> "it is " + v)
                .when(Season.AUTUMN, v -> "it is " + v)
                .orElse(v -> "it is " + v);
        System.out.println("season: " + sRes);
        assertEquals("it is AUTUMN", sRes);
    }

    public enum Season {
        SPRING, SUMMER, AUTUMN, WINTER
    }

    @Test
    public void testPatternValue2() {
        int i = 10;
        Void nullValue = match(i)
                .when(1,
                        /*
                         * if you want to match `when(V matchValue, V1<V> action)` not `when(V matchValue, R1<V, R> action)`,
                         * you need add `{ }`, see: void-compatible and value-compatible,
                         */
                        v -> { System.out.println("match value：" + v); })  // add {} to void-compatible
                .whenNext(10,
                        v -> System.out.println("match value：" + v + " whenNext continue..."))
                .when(20,
                        v -> System.out.println("match value：" + v))
                .orElse(
                        v -> System.out.println("--orElse--"));
        /*
         * output:
         * match value：10 whenNext continue...
         * --orElse--
         */

        match(i)
                .when(1,
                        v -> { System.out.println("match value：" + v); })  // add {} to void-compatible
                .whenNext(10,
                        v -> System.out.println("match value：" + v + " whenNext continue..."))
                .when(20,
                        v -> System.out.println("match value：" + v))
                .orElse(
                        v -> { });        // Lambda 空执行

    }


    @Test
    public void testPatternValue3() {
        Tuple2<Integer, String> t2 = Tuple.of(1, "zs").alias("id", "name");
        Tuple2<Integer, String> t21 = Tuple.of(1, "zs").alias("id", "name");

        Tuple2<Integer, String> t22 = Tuple.of(1, "zs");
        Tuple1<Integer> t1 = Tuple.of(1);


        String res = match(t2)
                .when(t21,              v -> "match Tuple2 with alias")
                .when(t1.arity() == 2,  v -> "tuple arity is 2")
                .when(t22,              v -> "match Tuple2 no alias")
                .orElse(                v -> "not match");

        assertEquals("match Tuple2 with alias", res);
    }

    @Test
    public void testPatternValue4() {
        Tuple2<Integer, String> t2 = Tuple.of(1, "zs").alias("id", "name");
        Tuple2<Integer, String> t21 = Tuple.of(1, "zs").alias("id", "name");

        Tuple2<Integer, String> t22 = Tuple.of(1, "zs");
        Tuple1<Integer> t1 = Tuple.of(1);


        String res = match(t2)
                .when(t1.arity() == 2,  v -> "tuple arity is 2")
                .when(t22,              v -> "match Tuple2 no alias")
                .when(t21,              v -> "match Tuple2 with alias")
                .orElse(                v -> "not match");

        assertEquals("match Tuple2 with alias", res);
    }

    @Test
    public void testPatternValue5() {
        Tuple2<Integer, String> t2 = Tuple.of(1, "zs").alias("id", "name");
        Tuple2<Integer, String> t21 = Tuple.of(1, "zs").alias("id", "name");

        Tuple2<Integer, String> t22 = Tuple.of(1, "zs");
        Tuple1<Integer> t1 = Tuple.of(1);


        String res = match(t2)
                .when(t1.arity() != 2, v -> "t1.arity() != 2")
                .when(t1.aliasType().equals("string"), v -> "t1.aliasType().equals(\"string\")")
                .when(t21, v -> "match Tuple2 with alias")
                .orElse(v -> "not match");

        assertEquals("t1.arity() != 2", res);
    }

    @Test
    public void testPatternValue6() {
        Tuple2<Integer, String> t2 = Tuple.of(1, "zs").alias("id", "name");
        Tuple2<Integer, String> t21 = Tuple.of(1, "zs").alias("id", "name");

        Tuple2<Integer, String> t22 = Tuple.of(1, "zs");
        Tuple1<Integer> t1 = Tuple.of(1);


        String res = match(t2)
                .when(t22, v -> "match Tuple2 with alias")
                .when(t1.arity() == 2, v -> "t1.arity() != 2")
                .when(t1.aliasType().equals("null"), v -> "t1.aliasType().equals(\"null\")")
                .orElse(v -> "not match");

        assertEquals("t1.aliasType().equals(\"null\")", res);
    }


    @Test
    public void testPatternNullValue() {
        String s = null;
        String strResult1 = match(s)
                // Avoid "Ambiguous method call", if you want to match `null` value, you need use `(String) null` or in(null)
                .when((String) null,    v -> "string null value")
                .when("abcd",           v -> "is not null")
                .orElse(                v -> "other value");

        assertEquals("string null value", strResult1);

        String strResult2 = match(s)
                .when(in(null), v -> "contains null value")
                .when("abcd",   v -> "is not null")
                .orElse(        v -> "other value");

        assertEquals("contains null value", strResult2);

        String strResult3 = match(s)
                .when(in("a", "b", null, "c"),  v -> "contains null value")
                .when("abcd",                   v -> "is not null")
                .orElse(                        v -> "other value");

        assertEquals("contains null value", strResult3);

        String strResult4 = match(s)
                .when(s == null,    v -> "contains null value")
                .when("abcd",       v -> "is not null")
//                .when(s.equals("a"), v -> "is equals a")   // 会报错，因为 s.equals("a") 总是会被执行
                .when("a",          v -> s.equals("a") + "")     // 正常执行，不报错
                .orElse(            v -> "other value");

        assertEquals("contains null value", strResult4);


        Tuple2<String, Integer> t2 = null;
        String classMatch = match(t2, TYPE)
                .when(Integer.class,    v -> "integer class")
                .when(null,             v -> "value is null: " + v)
                .when(Tuple2.class,     v -> "tuple2 class")
                .orElse(                v -> "other class");

        assertEquals("value is null: " + null, classMatch);

        String res = match(null, VALUE)
                .when(null, v -> "null value")
                .orElse(    v -> "other value");
        assertEquals("null value", res);
    }


    @Test
    public void testPatternType() {
        Object o = Tuple.of("zs", 20);

        // add `TYPE` to match Class<?>
        Integer result = match(o, TYPE)
                .when(Integer.class, v -> v + 10)
                .when(Tuple2.class,  v -> v.arity())
                .when(String.class,  v -> v.contains("abc") ? 20 : 30)
                .orElse(v -> 40);

        System.out.println(result);
        assertEquals(2, result);

        /*
         * it is equivalent to the code below
         */
        Integer ifResult;
        if (o instanceof Integer) {
            ifResult = (Integer) o + 10;
        } else if (o instanceof Tuple2) {
            ifResult = ((Tuple2) o).arity();
        } else if (o instanceof String) {
            ifResult = ((String) o).contains("abc") ? 20 : 30;
        } else {
            ifResult = 40;
        }

        assertEquals(2, ifResult);
    }

    @Test
    public void testStringValue1() {
        String str = "aBcdE123.$fGHIj";
        // ignore case match
        String matchRes1 = match(str, IGNORECASE)
                .when((String) null,        v -> "match null")
                .when("abcd",               v -> "match abcd")
                .when("abcde123.$fGHIj",    v -> "ignore case match")
                .orElse(                    v -> "no match");
        assertEquals("ignore case match", matchRes1);
        // CONTAIN match
        String matchRes2 = match(str, CONTAIN)
                .when("abcd",   v -> "abcd")
                .when("E123",   v -> "E123")
                .orElse(        v -> "no match");
        assertEquals("E123", matchRes2);
        // ignore case for contain
        String matchRes3 = match(str, ICCONTAIN)
                .when("abcd1", v -> "abcd1")
                .when(in(null, "aaa", ".$fghi", "123"), v -> ".$fghi")
                .orElse(v -> "no match");
        assertEquals(".$fghi", matchRes3);
        // PREFIX
        String matchRes4 = match(str, PREFIX)
                .when("abcd",   v -> "abcd")
                .when("aBcd",   v -> "aBcd")
                .orElse(        v -> "no match");
        assertEquals("aBcd", matchRes4);
        // ignore case for suffix
        String matchRes5 = match(str, ICSUFFIX)
                .when("fghij",  v -> "fGHIj")
                .when("aBcd",   v -> "aBcd")
                .orElse(        v -> "no match");
        assertEquals("fGHIj", matchRes5);
    }

    @Test
    public void testStringValue2() {
        String str = null;
        // ignore case match
        String matchRes1 = match(str, IGNORECASE)
                .when("abcd",               v -> "match abcd")
                .when((String) null,        v -> "match null")
                .when("abcde123.$fGHIj",    v -> "ignore case match")
                .orElse(                    v -> "no match");
        assertEquals("match null", matchRes1);
        // CONTAIN match
        String matchRes2 = match(str, CONTAIN)
                .when("abcd",   v -> "abcd")
                .when(in(null), v -> "null")
                .orElse(        v -> "no match");
        assertEquals("null", matchRes2);
        // ignore case for contain
        String matchRes3 = match(str, ICCONTAIN)
                .when("abcd1", v -> "abcd1")
                .when(in(null, "aaa", ".$fghi", "123"), v -> ".$fghi")
                .orElse(v -> "no match");
        assertEquals(".$fghi", matchRes3);
        // PREFIX
        String matchRes4 = match(str, PREFIX)
                .when("abcd",                   v -> "abcd")
                .when((PatternIn<String>) null, v -> "PatternIn<String>")
                .orElse(                        v -> "no match");
        assertEquals("PatternIn<String>", matchRes4);
        // ignore case for suffix
        String matchRes5 = match(str, ICSUFFIX)
                .when(in(null, null, null), v -> "all null")
                .when("aBcd",               v -> "aBcd")
                .orElse(                    v -> "no match");
        assertEquals("all null", matchRes5);
    }

    @Test
    public void testNone() {
        int i = 10;
        String s = "abc";
        Object o = new Object();
        String res = match()
                .when(i == 5,           v -> "i == 5")
                .when(s.equals("abc"),  v -> "abc")
                .when(o == null,        v -> "object is null")
                .orElse(                v -> "orElse");

        String res1 = match(NONE)
                .when(i == 10,          v -> "i == 10")
                .when(s.equals("abc"),  v -> "abc")
                .when(o == null,        v -> "object is null")
                .orElse(                v -> null);

        assertEquals("abc", res);
        assertEquals("i == 10", res1);

    }

    @Test
    public void testPreAction() {
        String str = "123abc";

        R1<String, String> preAction = s -> "123" + (s == null ? null : s.toLowerCase());
        String res1 = match(str, preAction, String.class)
                .when(G.isEmpty(str),           v -> "0 -- is empty")
                .when("123",                    v -> "1 " + v + "-- 123")
                .when("123ABC",                 v -> "2 " + v + "-- 123ABC")
                .when((PatternIn<String>) null, v -> "3 " + v + "-- null")
                .when("ABC",                    v -> "4 " + v + "-- ABC")
                .orElse(                        v -> "orElse " + v);
        System.out.println(res1);
        assertEquals("4 123abc-- ABC", res1);

        String res2 = match(str, preAction, String.class)
                .when(G.isEmpty(str),           v -> "0 -- is empty")
                .when("123",                    v -> "1 " + v + "-- 123")
                .when(str.endsWith("abc"),      v -> "2 " + v + "-- end with 'abc'")
                .when((PatternIn<String>) null, v -> "3 " + v + "-- null")
                .when("ABC",                    v -> "4 " + v + "-- ABC")
                .orElse(                        v -> "orElse " + v);
        System.out.println(res2);
        assertEquals("2 123abc-- end with 'abc'", res2);


        String ip = "127.0.0.1";
        String dbName = "";
        String dbType = null;
        String userName = "user1";

        String msg = match(s -> s == null || s.isEmpty(), String.class)
                .when(ip,       v -> "ip is empty")
                .when(dbName,   v -> "dbName is null or empty")
                .when(dbType,   v -> "dbType is null or empty")
                .orElse(        v -> null);

        System.out.println(msg);
        assertEquals("dbName is null or empty", msg);

        String msg1 = match(s -> s == null || s.isEmpty(), String.class)
                .when(ip,                   v -> "ip is empty")
                .when(in(userName, dbName), v -> "userName or dbName is null or empty")
                .when(dbType,               v -> "dbType is null or empty")
                .orElse(                    v -> null);

        System.out.println(msg1);
        assertEquals("userName or dbName is null or empty", msg1);

        String msg2 = match(Objects::isNull, String.class)
                .when(ip,       v -> "ip is null")
                .when(dbName,   v -> "dbName is null")
                .when(dbType,   v -> "dbType is null")
                .orElse(        v -> null);

        System.out.println(msg2);
        assertEquals("dbType is null", msg2);


    }

    @Test
    public void testPreAction1() {
        String msg = getMsg1("127.0.0.1", "", null);
        System.out.println(msg);
        assertEquals("ip is 127.0.0.1", msg);

        String msg1 = getMsg2("127.0.0.1", "db", null);
        System.out.println(msg1);
        assertEquals("dbType is null", msg1);

        String msg2 = getMsg1("127.0.0.2", "", null);
        System.out.println(msg2);
        assertEquals("dbName is null or empty", msg2);

        String msg3 = getMsg3("", "", null);
        System.out.println(msg3);
        assertEquals("dbType is null", msg3);

        String msg4 = getMsg3("", "", "mysql");
        System.out.println(msg4);
        assertEquals("null orelse", msg4);
    }

    private String getMsg1(String ip, String dbName, String dbType) {
        boolean isLocalhost = ip.equals("127.0.0.1");
        String msg = match(G::isEmpty, String.class)
                .when(isLocalhost,  v -> "ip is 127.0.0.1")
                .when(dbName,       v -> "dbName is null or empty")
                .when(dbType,       v -> "dbType is null or empty")
                .orElse(            v -> null);
        return msg;
    }

    private String getMsg2(String ip, String dbName, String dbType) {
        String msg = match(G::isEmpty, String.class)
                .when(ip,               v -> "ip is empty")
                .when(dbName,           v -> "dbName is null or empty")
                .when(dbType == null,   v -> "dbType is null")
                .orElse(                v -> null);
        return msg;
    }

    private String getMsg3(String ip, String dbName, String dbType) {
        System.out.println("----getMsg3----");
        String msg = match(G::isEmpty, String.class)
                .whenNext(ip, v -> {
                    System.out.println("ip: " + ip + " continue...");
                    return "ip is empty";
                })
                .whenNext(dbName, v -> {
                    System.out.println("dbName: " + dbName + " continue...");
                    return "dbName is null or empty";
                })
                .when(dbType == null, v -> "dbType is null")
                .orElse(v -> "null orelse");
        return msg;
    }

    @Test
    public void testPatternBoolean() {
        int i = 10;
        String result = match()
                .when(i == 0,
                        v -> "i is zero")
                .when(i < 5 && i > 0,
                        v -> "i is between 0~5")
                .when(i > 5,
                        v -> "i is greater than 5")
                .orElse(v -> "i is equals to: " + v);
        System.out.println("match result：" + result);
        assertEquals("i is greater than 5", result);

        /*
         * it is equivalent to the code below
         */
        String ifResult;
        if (i == 0) {
            ifResult = "i is zero";
        } else if (i < 5 && i > 0) {
            ifResult = "i is between 0~5";
        } else if (i > 5) {
            ifResult = "i is greater than 5";
        } else {
            ifResult = "i is equals to: " + i;
        }
        assertEquals("i is greater than 5", ifResult);
    }

    @Test
    public void testPatternClassValue() {
        Class<String> strClass = String.class;
        Tuple2<String, Integer> t2 = Tuple.of("1", 1);

        match(t2.getClass())
                .when(t2._2 == 1,
                        v -> { System.out.println("match bool value"); })
                .when(Objects.class,
                        v -> System.out.println("match objects"))
                .when(String.class,
                        v -> System.out.println("match stringclass"))
                .when(in(Long.class, Tuple2.class, Integer.class),
                        v -> System.out.println("match tuple class: " + v))
                .orElse(v -> System.out.println("not match"));

        match(t2.getClass())
                .when(t2._2 == 2,
                        v -> { System.out.println("match bool value"); })
                .when(Objects.class,
                        v -> System.out.println("match objects"))
                .when(String.class,
                        v -> System.out.println("match stringclass"))
                .when(in(Long.class, Tuple2.class, Integer.class),
                        v -> System.out.println("match tuple class: " + v))
                .when(t2._2 == 2,
                        v -> System.out.println("match bool value"))
                .orElse(v -> System.out.println("not match"));
    }

    @Test
    public void testCovariance() {
        R1<String, Number> snR1 = s -> s.length();
        R1<CharSequence, Integer> ciR1 = s -> 10;
        R1<CharSequence, Double> cdR1 = s -> 180.5d;

        assertEquals(180.5, useCovariance(snR1, ciR1, cdR1, "abc"));
        assertEquals(1, useCovariance(snR1, ciR1, cdR1, "a"));
        assertEquals(10, useCovariance(snR1, ciR1, cdR1, "b"));
    }

    private Number useCovariance(R1<String, Number> snR1, R1<CharSequence, Integer> ciR1, R1<CharSequence, Double> cdR1, String s) {
        return match(s)
               .when("a", snR1)
               .when("b", ciR1)
               .orElse(cdR1);
    }
}
