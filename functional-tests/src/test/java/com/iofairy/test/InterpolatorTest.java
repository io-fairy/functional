package com.iofairy.test;

import com.iofairy.except.UndefinedVariableException;
import com.iofairy.except.UnexpectedParameterException;
import com.iofairy.except.UnexpectedTypeException;
import com.iofairy.si.SI;
import com.iofairy.si.StringExtractor;
import com.iofairy.si.StringToken;
import com.iofairy.tcf.Try;
import com.iofairy.tuple.Tuple;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author GG
 * @version 1.0
 */
public class InterpolatorTest {

    @Test
    public void testInterpolator() {
        Tuple t1 = Tuple.of("zs", 123456).alias("NAME", "ID");
        Tuple t2 = Tuple.of(20, "tom", 190.5).alias("age", "nickName", "height");
        // ${nick\nName: Jack}: ${} can't contain \n, it will be ignore
        String parse = SI.of(t1, t2).$("${NAME}--${age}--${nickName}--${nick\nName: Jack}--${ID}--${height}");

        assertEquals("zs--20--tom--${nick\nName: Jack}--123456--190.5", parse);
    }

    @Test
    public void testStaticInterpolator() {
        // ${nick\nName: Jack}: ${} can't contain \n, it will be ignore
        String parse01 = SI.$("${NAME}--${age}--${nickName}--${nick\nName: Jack}--${ID}--${height}", null);
        String parse02 = SI.$(null, null);
        String parse03 = SI.$("null", null);
        String parse04 = SI.$(null, new Object(), "a", "b");
        String parse05 = SI.$("null", new Object(), "a", "b");
        String parse06 = SI.$("${NAME}--${ID: abcd}--${ID::abcd}--${nick\nName: Jack}--${ID::abcd}--${height: 180}", null);
        String parse07 = SI.$("${NAME}--${ID: abcd}--${ID::abcd}--${nick\nName: Jack}--${ID::abcd}--${height: 180}", null, 789, "a");
        String parse08 = SI.$("${NAME}--${ID: abcd}--${ID::abcd}--${nick\nName: Jack}--${ID::abcd}--${height: 180}", null, 789, "a", 'b', "c");
        String parse09 = SI.$("${NAME}--${ID: abcd}--${ID::abcd}--${nick\nName: Jack}--${ID::abcd}--${height: 180}", null, 789, "a", 'b', "c", "d");
        String parse10 = SI.$("${ID: : }--${: empty}--${ID: }--${}--${}{a}--${:}", null);
        String parse11 = SI.$("${ID: : }--${: empty}--${ID: }--${}--${}{a}--${:}", null, 789, "a", 'b', "c", "d", 'e');
        String parse12 = SI.$("${ID: : }--${: empty}--${ID: }--${}--${}{a}--${:}", null, 789, "a");
        StringBuffer sb1 = null;
        StringBuffer sb2 = new StringBuffer();
        StringBuffer sb3 = new StringBuffer();
        sb3.append((Object) null);
        StringBuffer sb4 = new StringBuffer();
        sb4.append((Object) null);
        sb4.append("${: empty}--${");
        sb4.append("ID: }--${");
        sb4.append("}");
        String parse13 = SI.$(sb1, null);
        String parse14 = SI.$(sb2, null);
        String parse15 = SI.$(sb3, null);
        String parse16 = SI.$(sb4, null);
        String parse17 = SI.$(sb1, null, 789, "a", 'b', "c", "d", 'e');
        String parse18 = SI.$(sb2, null, 789, "a", 'b', "c", "d", 'e');
        String parse19 = SI.$(sb3, null, 789, "a", 'b', "c", "d", 'e');
        String parse20 = SI.$(sb4, null, 789, "a", 'b', "c", "d", 'e');

        System.out.println("parse01: " + parse01);
        System.out.println("parse02: " + parse02);
        System.out.println("parse03: " + parse03);
        System.out.println("parse04: " + parse04);
        System.out.println("parse05: " + parse05);
        System.out.println("parse06: " + parse06);
        System.out.println("parse07: " + parse07);
        System.out.println("parse08: " + parse08);
        System.out.println("parse09: " + parse09);
        System.out.println("parse10: " + parse10);
        System.out.println("parse11: " + parse11);
        System.out.println("parse12: " + parse12);
        System.out.println("parse13: " + parse13);
        System.out.println("parse14: " + parse14);
        System.out.println("parse15: " + parse15);
        System.out.println("parse16: " + parse16);
        System.out.println("parse17: " + parse17);
        System.out.println("parse18: " + parse18);
        System.out.println("parse19: " + parse19);
        System.out.println("parse20: " + parse20);

        assertEquals("${NAME}--${age}--${nickName}--${nick\nName: Jack}--${ID}--${height}", parse01);
        assertNull(parse02);
        assertEquals("null", parse03);
        assertNull(parse04);
        assertEquals("null", parse05);
        assertEquals("${NAME}--abcd--${ID::abcd}--${nick\nName: Jack}--${ID::abcd}--180", parse06);
        assertEquals("null--789--a--${nick\nName: Jack}--${ID::abcd}--180", parse07);
        assertEquals("null--789--a--${nick\nName: Jack}--b--c", parse08);
        assertEquals("null--789--a--${nick\nName: Jack}--b--c", parse09);
        assertEquals(": --empty----$--${a}--${:}", parse10);
        assertEquals("null--789--a--$--${a}--b", parse11);
        assertEquals("null--789--a--$--${a}--${:}", parse12);
        assertNull(parse13);
        assertEquals("", parse14);
        assertEquals("null", parse15);
        assertEquals("nullempty----$", parse16);
        assertNull(parse17);
        assertEquals("", parse18);
        assertEquals("null", parse19);
        assertEquals("nullnull--789--$", parse20);
    }

    @Test
    public void testInterpolator1() {
        SI si = Tuple.of("zs", 20, "tom", 190.5, 123456).alias("name", "age", "nickName", "height", "id").toSI();

        String parse = si.$("${name}--${age}--${nickName}--${id}--${height}");

        assertEquals("zs--20--tom--123456--190.5", parse);
    }

    @Test
    public void testInterpolator2() {
        SI si = Tuple.of("zs", 20, "tom", 190.5, 123456).alias("name", "age", "nickName", "height", "id").toSI();

        String parse = si.$("--${name}--${age}--${nickName}--${id}--${height}--");

        assertEquals("--zs--20--tom--123456--190.5--", parse);
    }

    @Test
    public void testDefaultValue() {
        // use ": " set default value
        String source = "${NAME}--${NAME: tom}--${age: 20}--${ID1:}" +
                "--${ ID1 }--${ID1: }--${id1}--${age::20}--${ID}" +
                "--${ ID1:  }--${ID: 123456}";
        Tuple t1 = Tuple.of("zs", null).alias("NAME", "ID");
        SI si = SI.of(t1, null, Tuple.empty());
        String parse = si.$(source);
        System.out.println(parse);

        assertEquals("zs--zs--20--${ID1:}--${ ID1 }----${id1}--${age::20}--null-- --null", parse);

    }

    @Test
    public void testDefaultValue1() {
        // use ": " set default value
        String source = "ID1: ${ID1: }--${ID1: : }--${ID: : }--${ID: }--${: }--${ID1: }";
        SI si = Tuple.of("123456", "emptyKey").alias("ID1", "").toSI();
        String parse = si.$(source);
        System.out.println(parse);

        assertEquals("ID1: 123456--123456--: ----emptyKey--123456", parse);

    }

    @Test
    public void testDefaultValue2() {
        // use ": " set default value
        String source = "--ID1: ${ID1: }--${ID1: : }--${ID: : }--${: empty}--${ID: }--";
        SI si = Tuple.of("123456").alias("ID1").toSI();
        String parse = si.$(source);
        System.out.println(parse);

        assertEquals("--ID1: 123456--123456--: --empty----", parse);

    }

    @Test
    public void testDefaultValue3() {
        // use ": " set default value
        String source = "${NAME}--${NAME: tom}--${age: 20}--${ID1:}" +
                "--${ ID1 }--ID1: ${ID1: }--${ID2: }--${id1}--${age::20}--${ID}" +
                "--${ ID1:  }--${ID: 123456}";
        Tuple t1 = Tuple.of("zs", null, "id1-123456").alias("NAME", "ID", "ID1");
        SI si = SI.of(t1, null, Tuple.empty());
        String parse = si.$(source);
        System.out.println(parse);

        assertEquals("zs--zs--20--${ID1:}--${ ID1 }--ID1: id1-123456----${id1}--${age::20}--null-- --null", parse);

    }

    @Test
    public void testMetaChar() {
        SI si = Tuple.of("zs", 123456).alias("NAME", "ID").toSI();
        // ${} will be parsed $
        String parse = si.$("${NAME}--$${ID}--$$$${ID}--${}{ID}--${}");

        assertEquals("zs--$123456--$$$123456--${ID}--$", parse);
    }

    @Test
    public void testTupleToSI() {
        String source = "${NAME}--${age: 18}--${nickName}--${ID}--${height}--${_1}--${_2}";

        SI si0 = Tuple.of("zs", 123456).alias("NAME", "ID").toSI();
        String parse = si0.$(source);
        assertEquals("zs--18--${nickName}--123456--${height}--${_1}--${_2}", parse);

        SI si1 = Tuple.of("zs", 123456).toSI();   // alias is _1, _2
        parse = si1.$(source);
        assertEquals("${NAME}--18--${nickName}--${ID}--${height}--zs--123456", parse);

        /*
         * add, del, set
         */
        SI si = Tuple.of().toSI();
        parse = si.$(source);
        assertEquals("${NAME}--18--${nickName}--${ID}--${height}--${_1}--${_2}", parse);


        Tuple t2 = Tuple.of(20, "tom").alias("age", "nickName");
        si.add(t2);         // add
        parse = si.$(source);
        assertEquals("${NAME}--20--tom--${ID}--${height}--${_1}--${_2}", parse);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("height", 175);
        si.add(hashMap);    // add
        parse = si.$(source);
        assertEquals("${NAME}--20--tom--${ID}--175--${_1}--${_2}", parse);

        si.del("age", "nickName");  // delete
        parse = si.$(source);
        assertEquals("${NAME}--18--${nickName}--${ID}--175--${_1}--${_2}", parse);

        Tuple t3 = Tuple.of(20, "tom").alias("age", "nickName");
        si.set(t3);             // set
        parse = si.$(source);
        assertEquals("${NAME}--20--tom--${ID}--${height}--${_1}--${_2}", parse);

        Tuple t4 = Tuple.of();
        si.set(t4);             // set
        parse = si.$(source);
        assertEquals("${NAME}--18--${nickName}--${ID}--${height}--${_1}--${_2}", parse);
    }

    @Test
    public void testAddSetDel() {
        String source = "${NAME}--${age}--${nickName}--${ID}--${height}";

        Tuple t1 = Tuple.of("zs", 123456).alias("NAME", "ID");
        SI si = SI.of(t1);
        String parse = si.$(source);
        System.out.println(parse);
        assertEquals("zs--${age}--${nickName}--123456--${height}", parse);

        Tuple t2 = Tuple.of(20, "tom").alias("age", "nickName");
        si.add(t2);         // add
        parse = si.$(source);
        System.out.println(parse);
        assertEquals("zs--20--tom--123456--${height}", parse);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("height", 175);
        si.add(hashMap);    // add
        parse = si.$(source);
        System.out.println(parse);
        assertEquals("zs--20--tom--123456--175", parse);

        si.del("age", "nickName");  // delete
        parse = si.$(source);
        System.out.println(parse);
        assertEquals("zs--${age}--${nickName}--123456--175", parse);

        Tuple t3 = Tuple.of(20, "tom").alias("age", "nickName");
        si.set(t3);             // set
        parse = si.$(source);
        System.out.println(parse);
        assertEquals("${NAME}--20--tom--${ID}--${height}", parse);

    }

    @Test
    public void testInit() {
        String infoTemplate = "ip: ${ip}---port: ${port}---db: ${db}---otherInfo: ${other_info}";

        SI s1 = SI.init("         ip ->", "127.0.0.1",
                        "         db ->", "testdb",
                        "       port ->", 3306,
                        "     dbType ->", "mysql",
                        " other_info ->", Tuple.of("isCluster", true),
                        "description ->", new Object());

        String dbInfo = s1.$(infoTemplate);
        assertEquals("ip: 127.0.0.1---port: 3306---db: testdb---otherInfo: (\"isCluster\", true)", dbInfo);

        SI s2 = SI.init("ip          ->", "127.0.0.1",
                        "db          ->", "testdb",
                        "port        ->", 3306,
                        "dbType      ->", "mysql",
                        "other_info  ->", Tuple.of("isCluster", true),
                        "description ->", new Object());
        dbInfo = s2.$(infoTemplate);
        assertEquals("ip: 127.0.0.1---port: 3306---db: testdb---otherInfo: (\"isCluster\", true)", dbInfo);

        SI s3 = SI.init("ip          ->　", "127.0.0.1",
                        "db          ->　", "testdb",
                        "port        ->　", 3306,
                        "dbType      ->　", "mysql",
                        "other_info  ->　", Tuple.of("isCluster", true),
                        "            ->　", null,
                        "description ->　", new Object());
        dbInfo = s3.$(infoTemplate);
        assertEquals("ip: 127.0.0.1---port: 3306---db: testdb---otherInfo: (\"isCluster\", true)", dbInfo);

        SI s4 = SI.init("ip          >>> \n　", "127.0.0.1",
                        "db          >>> \n　", "testdb",
                        "port        >>> \n　", 3306,
                        "dbType      >>> \n　", "mysql",
                        "other_info  >>> \n　", Tuple.of("isCluster", true),
                        "            >>> \n　", null,
                        "description >>> \n　", new Object());
        dbInfo = s4.$(infoTemplate);
        assertEquals("ip: 127.0.0.1---port: 3306---db: testdb---otherInfo: (\"isCluster\", true)", dbInfo);

        SI s5 = SI.init("ip          >>　", "127.0.0.1",
                        "db          >>　", "testdb",
                        "port        >>　", 3306,
                        "dbType      >>　", "mysql",
                        "other_info  >>　", Tuple.of("isCluster", true),
                        "            >>　", null,
                        "description >>　", new Object());
        dbInfo = s5.$(infoTemplate);
        assertEquals("ip: 127.0.0.1---port: 3306---db: testdb---otherInfo: (\"isCluster\", true)", dbInfo);

        SI s6 = SI.init("         ip >>> ", "127.0.0.1",
                        "         db >>> ", "testdb",
                        "       port >>> ", 3306,
                        "            >>> ", null,
                        "description >>> ", new Object());

        s6.fill("     dbType >>> ", "mysql",
                " other_info >>> ", Tuple.of("isCluster", true));

        dbInfo = s6.$(infoTemplate);
        assertEquals("ip: 127.0.0.1---port: 3306---db: testdb---otherInfo: (\"isCluster\", true)", dbInfo);
    }

    @Test
    public void testLoad() {
        SI si = SI.load("ip ->", "127.0.0.1",
                        "port ->", 3306,
                        "db ->", "testdb",
                        "dbType ->", "mysql",
                        "other_info ->", Tuple.of("isCluster", true),
                        "description ->", new Object());

        String dbInfo = si.$("ip: ${ip}---port: ${port}---db: ${db}---otherInfo: ${other_info}");
        assertEquals("ip: 127.0.0.1---port: 3306---db: testdb---otherInfo: (\"isCluster\", true)", dbInfo);

        SI s2 = SI.load("   ip  ->", "127.0.0.1",
                        "   port    ->", 3306,
                        "   db  ->", "testdb",
                        "   dbType  ->", "mysql",
                        "   other_info ->", Tuple.of("isCluster", true),
                        "   description ->", new Object());

        String dbInfo2 = s2.$("ip: ${   ip }---port: ${   port   }---db: ${   db }---otherInfo: ${   other_info}");
        assertEquals("ip: 127.0.0.1---port: 3306---db: testdb---otherInfo: (\"isCluster\", true)", dbInfo2);
    }

    @Test
    public void testInitTime() {

        //        for (int i = 0; i < 800; i++) {
        //            SI.KEY_CACHE.put("aldkfslj" + i, "aldkfslj" + i);
        //        }
        //        System.out.println("size: " + SI.KEY_CACHE.size());

        System.out.println("---start calculate---");
        long startTime = System.currentTimeMillis();

        String dbInfo = null;
        for (int i = 0; i < 1000; i++) {
            SI si = SI.init(
                    "          ip -> ", "127.0.0.1",
                    "          db -> ", "testdb",
                    "        port -> ", 3306,
                    "      dbType -> ", "mysql",
                    "  other_info -> ", Tuple.of("isCluster", true),
                    " description -> ", new Object(),
                    "         ip1 -> ", "127.0.0.1",
                    "       port1 -> ", 3306,
                    "     dbType1 -> ", "mysql");

            si.fill("         db1 -> ", "testdb",
                    " other_info1 -> ", Tuple.of("isCluster1", true),
                    "description1 -> ", new Object());

            si.add("dbName", "this is dbName!");

            String infoTemplate =
                    "${}---ip: ${ip}---port: ${port}---db: ${db}---otherInfo: ${other_info}---" +
                            "ip1: ${ip1}---port: ${port1}---db1: ${db1}---description1: ${description1}---" +
                            "ip: ${ip}---port: ${port}---dbName: ${dbName}---otherInfo1: ${==${other_info1}==}";
            dbInfo = si.$(infoTemplate);
        }
        System.out.println("dbInfo >>> " + dbInfo);
        long timeDiff = System.currentTimeMillis() - startTime;
        System.out.println("cost time: " + timeDiff);
        // 1000次花费时间(ms)： 136, 97, 97, 164, 115, 127
        // 加了两种后缀符(ms)：298，338，338，349，425，405, 273, 294, 298, 300
        // 加了缓存(ms)：114, 105, 83, 92, 124, 77, 112, 149, 109, 100
    }

    @Test
    public void testInitAndLoad() {
        System.out.println("---start calculate---");
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 500; i++) {
            String infoTemplate = "ip: ${ip}---port: ${port}---db: ${db}---otherInfo: ${other_info}";

            SI s1 = SI.init("         ip -> ", "127.0.0.1",
                            "         db -> ", "testdb",
                            "       port -> ", 3306,
                            "     dbType -> ", "mysql",
                            " other_info -> ", Tuple.of("isCluster", true),
                            "description -> ", new Object());

            String dbInfo = s1.$(infoTemplate);
            assertEquals("ip: 127.0.0.1---port: 3306---db: testdb---otherInfo: (\"isCluster\", true)", dbInfo);

            SI s2 = SI.load("ip ->", "127.0.0.1",
                    "port ->", 3306,
                    "db ->", "testdb",
                    "dbType ->", "mysql",
                    "other_info ->", Tuple.of("isCluster", true),
                    "description ->", new Object());

            dbInfo = s2.$("ip: ${ip}---port: ${port}---db: ${db}---otherInfo: ${other_info}");
            assertEquals("ip: 127.0.0.1---port: 3306---db: testdb---otherInfo: (\"isCluster\", true)", dbInfo);
        }

        long timeDiff = System.currentTimeMillis() - startTime;
        System.out.println("cost time: " + timeDiff);

    }

    @Test
    public void testOfPairs() {
        SI si = SI.of("ip", "127.0.0.1",
                "port", 3306,
                "db", "testdb",
                "dbType", "mysql",
                "other_info", Tuple.of("isCluster", true),
                "description", new Object());

        String dbInfo = si.$("ip: ${ip}---port: ${port}---db: ${db}---otherInfo: ${other_info}");
        assertEquals("ip: 127.0.0.1---port: 3306---db: testdb---otherInfo: (\"isCluster\", true)", dbInfo);
    }

    @Test
    public void testCalculate() {
        System.out.println("---start calculate---");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            String source = "${NAME}--${age: 18}--${nickName}--${ID}--${height}--${_1}--${_2}"
                    + "${NAME}--${age: 18}--${nickName}--${ID}--${==${height}}--${_1}--${_2}"
                    + "${NAME}--${age: 18}--${nickName}--${ID}--${==${height}}--${_1}--${_2}";

            SI si0 = Tuple.of("zs", 123456).alias("NAME", "ID").toSI();
            String parse = si0.$(source);

            SI si1 = Tuple.of("zs", 123456).toSI();   // alias is _1, _2
            parse = si1.$(source);

            /*
             * add, del, set
             */
            SI si = Tuple.of().toSI();
            parse = si.$(source);


            Tuple t2 = Tuple.of(20, "tom").alias("age", "nickName");
            si.add(t2);         // add
            parse = si.$(source);

            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("height", 175);
            si.add(hashMap);    // add
            parse = si.$(source);

            si.del("age", "nickName");  // delete
            parse = si.$(source);

            Tuple t3 = Tuple.of(20, "tom").alias("age", "nickName");
            si.set(t3);             // set
            parse = si.$(source);
        }

        long timeDiff = System.currentTimeMillis() - startTime;
        System.out.println("cost time: " + timeDiff);
        // 没用缓存：431, 439, 482, 466, 479
        // 使用缓存：161, 184, 155, 201, 148
    }

    @Test
    public void testNull() {
        SI si = new SI(null, Tuple.empty());
        SI si1 = new SI((Tuple) null);
        SI si2 = Tuple.of(null).toSI();
        Map<String, Object> nullMap = null;
        SI.of();
        SI.of((Tuple[]) null);
        SI.of((Map<String, Object>) null);
        SI.of(nullMap);
        SI.load();
        SI.load(null);
        SI.init();
        SI.init(null);
        SI.init(" ->", null);
        SI.init("   ->   ", null);
        SI.init("   >>>   ", null);
        SI.init("   >>   ", null);
        SI.load(" ->", null);
        SI.load(" >>>", null);
        SI.load(" >>", null);
        assertThrows(NullPointerException.class, () -> SI.init(null, null));
        assertThrows(UnexpectedTypeException.class, () -> SI.init(new Object(), null));
        assertThrows(RuntimeException.class, () -> SI.init(null, null, null));
        assertThrows(UnexpectedParameterException.class, () -> SI.init("", null));
        assertThrows(UnexpectedParameterException.class, () -> SI.init("->", null));

        try {
            SI init = SI.init(" >>>>", null);
            throwException();
        } catch (Exception e) {
            assertSame(e.getClass(), UnexpectedParameterException.class);
            assertEquals(e.getMessage(), "Index: 0. This parameter is a key, the key must be end with \" ->\" or \" >>>\" or \" >>\". ");
        }
        assertThrows(UnexpectedParameterException.class, () -> SI.load("->", null));
        assertThrows(UnexpectedParameterException.class, () -> SI.load(" -> ", null));
        assertThrows(UnexpectedParameterException.class, () -> SI.load(" >>> ", null));
        assertThrows(UnexpectedParameterException.class, () -> SI.load(" >> ", null));

    }

    @Test
    public void testNormalString() {
        SI si0 = Tuple.of("zs", 123456).alias("NAME", "ID").toSI();
        String parse = si0.$("");
        assertEquals("", parse);
        parse = si0.$("abcdefg");
        assertEquals("abcdefg", parse);
        parse = si0.$("abcdefg${==");
        assertEquals("abcdefg${==", parse);
    }

    @Test
    public void testStringExtractor() {
        String s = "";
        String s1 = "abcd===1234===";
        List<StringToken> tokens = StringExtractor.split(s);
        System.out.println(tokens);     // []
        List<StringToken> tokens1 = StringExtractor.split(s1);
        System.out.println(tokens1);    // [StringToken{type=STRING, value='abcd===1234===', originValue='abcd===1234==='}]
    }

    @Test
    public void testInterpolatorException() {
        Tuple t1 = Tuple.of("zs", 123456).alias("NAME", "ID");
        Tuple t2 = Tuple.of(20, "tom", 190.5).alias("age", "nickName", "height");
        SI si = SI.of(t1, t2).setEnableUndefinedVariableException(true);

        try {
            String $ = si.$("${NAME}--${age}--${nickName}--${name}--${ID}--${height}");
        } catch (UndefinedVariableException e) {
            System.out.println(e.getMessage());
            assertEquals(e.getMessage(), "Cannot resolve variable `name` in \"${NAME}--${age}--${nickName}--${name}--${ID}--${height}\". ");
        }

    }


    @Test
    public void testCheckCyclic() {
        Try.sleep(500);
        System.out.println("================");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("one", "-${four}-${three}-${two}");
        hashMap.put("two", "${three}");
        hashMap.put("three", "${a: ${a: ${number\n}}}");
        hashMap.put("four", 4);
        hashMap.put("five", 5);
        hashMap.put("five$", "${number\n}");
        hashMap.put("six", "${}");
        hashMap.put("number\n", "${one}");

        SI si = SI.of(hashMap).setEnableNestedSI(true);

        String tpl = "${five}--${number\n}";
        try {
            String $ = si.$(tpl);
            System.out.println($);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertEquals(e.getMessage(), "Circular references in string interpolation of \"${five}--${number\n}\": number\n -> one -> three -> number\n");
        }
        System.out.println("================================");

        tpl = "${five: ${number\n}}";
        String $1 = si.$(tpl);
        System.out.println($1);
        System.out.println("================================");

        tpl = "${five${six}}";
        try {
            String $ = si.$(tpl);
            System.out.println($);
            throwException();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertEquals(e.getMessage(), "Circular references in string interpolation of \"${five${six}}\": five$ -> number\n -> one -> three -> number\n");
        }
        System.out.println("================================");

        tpl = "${five: ${key}}";
        try {
            String $ = si.$(tpl);
            System.out.println($);
            assertEquals($, "5");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testCheckCyclic1() {
        Map<String, Object> map = new HashMap<>();
        map.put("one", "${number.${order.${level}}}");
        map.put("order", "1");
        map.put("order.1", "2");
        map.put("level", "1");
        map.put("number.2", "${one}");
        map.put("number.1", "${one}");
        String tpl = "${number.${order.${level}}}abc}";
        SI si = SI.of(map).setEnableNestedSI(true);
        try {
            String $ = si.$(tpl);
            throwException();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertEquals(e.getMessage(), "Circular references in string interpolation of \"${number.${order.${level}}}abc}\": number.2 -> one -> number.2");
        }
        si.setEnableSIInValues(false);
        String $ = si.$(tpl);
        assertEquals($, "${one}abc}");
    }

    @Test
    public void testEnableNestedParse() {
        String tpl = "${key}{}";
        SI si = Tuple.of("$").alias("key").toSI();
        String $ = si.$(tpl);
        System.out.println($);
        assertEquals($, "${}");

        tpl = "${a.${}${b}: ${c}.${d}.${}name}--${e${f}}}";

        si = Tuple.of("6", "7", "8", "e8value").alias("b", "c", "f", "e8").toSI();
        $ = si.$(tpl);
        System.out.println($);
        assertEquals($, "${a.$6: 7.${d}.$name}--${e8}}");

        si = Tuple.of("6", "7", "8").alias("b", "c", "f").toSI();
        $ = si.$(tpl);
        System.out.println($);
        assertEquals($, "${a.$6: 7.${d}.$name}--${e8}}");

        si = Tuple.of("6", "7", "8").alias("b", "c", "d").toSI();
        $ = si.$(tpl);
        System.out.println($);
        assertEquals($, "${a.$6: 7.8.$name}--${e${f}}}");

    }

    @Test
    public void testEnableNestedParse1() {
        String tpl = "${${dbType}.${order}.dbName: ${${defaultDbType}.${defaultOrder}.dbName}}---}}}" +
                "---${${dbType}.${order}.password: ${${defaultDbType}.${defaultOrder}.password: mysql**sa-pass}}";
        Map<String, String> map = new HashMap<>();
        map.put("dbType", "ORACLE");
        map.put("order", "1");
        map.put("defaultDbType", "mysql");

        SI si = SI.of(map).setEnableNestedSI(true);

        String $ = si.$(tpl);
        System.out.println($);
        assertEquals($, "${mysql.${defaultOrder}.dbName}---}}}---mysql**sa-pass");

        si.add("defaultOrder", "2", "mysql.2.dbName", "mysqlDb");
        $ = si.$(tpl);
        System.out.println($);
        assertEquals($, "mysqlDb---}}}---mysql**sa-pass");

        si.add("ORACLE.1.dbName", "oracledb", "mysql.2.password", "\\mysql**edb");
        $ = si.$(tpl);
        System.out.println($);
        assertEquals($, "oracledb---}}}---\\mysql**edb");

        si.add("ORACLE.1.password", "oracl{}{abc}*edb");
        $ = si.$(tpl);
        System.out.println($);
        assertEquals($, "oracledb---}}}---oracl{}{abc}*edb");

    }

    @Test
    public void testEnableNestedParse2() {
        String tpl = "${}{${dbType: sqlserver}.${order: 2}.dbName: ${${defaultDbType: mysql}.${defaultOrder: 1}.password: mysql**sa-pass}}";

        SI si = SI.of().setEnableNestedSI(true);

        String $ = si.$(tpl);
        assertEquals($, "${sqlserver.2.dbName: mysql**sa-pass}");

    }

    @Test
    public void testEnableNestedParse3() {
        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("order", "这是order的值");
        valueMap.put("带{的key", "value这是带{的值！");
        valueMap.put("level", "1");
        valueMap.put("number.1", new ArrayList<Tuple>() {{
            add(Tuple.of("${带{的key}", "${a2: ${a3: ${}{a\nb}}}", "${c\ne}", "${a3: ${: }}"));
        }});
        valueMap.put("${a\nb}", "带换行符");
        valueMap.put("a", "--${b}--");
        valueMap.put("b", null);
        valueMap.put("a1", "**${a2: ${level}-${level}}**");
        valueMap.put("c1", "${a3: ${a4}}");


        String tpl = "${--${{--${number: ${number.${level}}}--${a}--${a}--${a1}--${c1}--${: }--${";

        SI si = SI.of(valueMap).setEnableNestedSI(true);

        String $ = si.$(tpl);
        System.out.println($);
        assertEquals($, "${--${{--[(\"value这是带{的值！\", \"${a\nb}\", \"${c\ne}\", \"\")]----null------null----**1-1**--${a4}----${");

        si.add("", "empty string value.");
        $ = si.$(tpl);
        System.out.println($);
        assertEquals($, "${--${{--[(\"value这是带{的值！\", \"${a\nb}\", \"${c\ne}\", \"empty string value.\")]----null------null----**1-1**--${a4}--empty string value.--${");

        si.add("c\ne", "${a}");
        si.add("b", "${${}{e}}");   // ${${}{e}} 中的 key 为 ${e 而不是：${e}
        si.add("${e", "${c\ne}");

        try {
            $ = si.$(tpl);
            throwException();
        } catch (Exception e) {
            System.out.println("============================================================");
            System.out.println(e.getMessage());
            System.out.println("============================================================");
            assertEquals(e.getMessage(), "Circular references in string interpolation of \"${--${{--${number: ${number.${level}}}--${a}--${a}--${a1}--${c1}--${: }--${\": number.1 -> c\ne -> a -> b -> ${e -> c\ne");
        }

        si.setEnableUndefinedVariableException(true);
        try {
            $ = si.$(tpl);
            throwException();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertEquals(e.getMessage(), "Cannot resolve variable `number` in \"${--${{--${number: ${number.${level}}}--${a}--${a}--${a1}--${c1}--${: }--${\". ");
        }

    }

    @Test
    public void testInitForNestedParse() {
        String infoTemplate = "ip: ${ip}---port: ${port}---db: ${db}---otherInfo: ${other_info}";

        SI s1 = SI.init("         ip ->", "127.0.0.1",
                        "         db ->", "testdb",
                        "       port ->", 3306,
                        "     dbType ->", "mysql",
                        " other_info ->", Tuple.of("isCluster", "${: ${这是一个描述}}"),
                        " 这是一个描述 ->", "this is description.")
                .setEnableNestedSI(true);

        String dbInfo = s1.$(infoTemplate);
        assertEquals("ip: 127.0.0.1---port: 3306---db: testdb---otherInfo: (\"isCluster\", \"this is description.\")", dbInfo);

        s1 = SI.init("         ip >> ", "127.0.0.1",
                     "         db >> ", "testdb",
                     "       port >> ", 3306,
                     "     dbType >> ", "mysql",
                     " other_info >> ", Tuple.of("isCluster", "${: ${这是一个描述}}"),
                     " 这是一个描述 >> ", "this is description.")
                .setEnableNestedSI(true);

        dbInfo = s1.$(infoTemplate);
        assertEquals("ip: 127.0.0.1---port: 3306---db: testdb---otherInfo: (\"isCluster\", \"this is description.\")", dbInfo);

    }

    private void throwException() {
        throw new RuntimeException();
    }

}
