package com.iofairy.test;

import com.iofairy.except.UnexpectedParameterException;
import com.iofairy.si.SI;
import com.iofairy.si.StringExtractor;
import com.iofairy.si.StringToken;
import com.iofairy.tuple.Tuple;
import org.junit.jupiter.api.Test;

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
        //1000次花费时间(ms)： 136, 97, 97, 164, 115, 127
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
        assertThrows(ClassCastException.class, () -> SI.init(new Object(), null));
        assertThrows(RuntimeException.class, () -> SI.init(null, null, null));
        assertThrows(UnexpectedParameterException.class, () -> SI.init("", null));
        assertThrows(UnexpectedParameterException.class, () -> SI.init("->", null));
        assertThrows(UnexpectedParameterException.class, () -> SI.init(" >>>>", null));
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
}
