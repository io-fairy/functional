package com.iofairy.test;

import com.iofairy.except.UnexpectedParameterException;
import com.iofairy.tcf.Try;
import com.iofairy.top.*;
import com.iofairy.tuple.Tuple2;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author GG
 * @version 1.0
 */
public class GlobalTest {
    @Test
    public void testEmptyAndNull() {
        int i = 10;
        String s1 = "";
        String s2 = "abcd";
        String s5 = "abcd1";
        String s3 = null;
        Object o1 = new Object();
        Object o2 = null;
        Integer i1 = null;
        String s4 = "";
        String[] nullSs = null;
        String[] ss1 = new String[]{};
        String[] ss2 = new String[]{""};
        Object[] os = {};
        Object[] os1 = {'a', "1"};
        char[] cArr = {};
        List<String> list = Arrays.asList("a", "b");
        Map<Object, Object> map = Map.of();

        assertFalse(G.hasNull());
        assertTrue(S.hasEmpty());
        assertFalse(G.allNull());
        assertTrue(S.allEmpty());
        assertFalse(G.hasNull(i, o1, s1, s2));
        assertTrue(G.hasNull(i, o1, s1, s3));
        assertFalse(S.hasEmpty(s2, s5));
        assertTrue(S.hasEmpty(s2, s5, s3));
        assertTrue(S.hasEmpty(s2, s5, s1));
        assertTrue(S.hasEmpty(ss1));
        assertTrue(S.hasEmpty(ss2));
        assertFalse(G.allNull(i, s1, s3, i1));
        assertTrue(G.allNull(s3, o2, i1));
        assertFalse(G.allNull(os));
        assertFalse(G.allNull(os1));
        assertFalse(S.allEmpty(s1, s2, s3));
        assertTrue(S.allEmpty(s1, s3, s4));
        assertTrue(G.isEmpty(s1));
        assertFalse(G.isEmpty(s2));
        assertTrue(G.isEmpty(nullSs));
        assertTrue(G.isEmpty(ss1));
        assertFalse(G.isEmpty(ss2));
        assertTrue(G.isEmpty(cArr));
        assertFalse(G.isEmpty(list));
        assertTrue(G.isEmpty(map));
        // ----------------------
        assertTrue(isEmptyForVarargs());
        assertTrue(isEmptyForVarargs(null));
        assertTrue(isEmptyForVarargs((Object[]) null));
        assertTrue(isEmptyForVarargs(new Object[]{}));
        assertFalse(isEmptyForVarargs((Object) null));
        assertFalse(isEmptyForVarargs(null, null));

    }

    private boolean isEmptyForVarargs(Object... objects) {
        return G.isEmpty(objects);
    }

    @Test
    public void testEmptyAndNull1() {
        String s1 = "1";
        String s2 = "1";
        String s3 = "1";
        String s4 = "";
        String s5 = "";
        String s6 = "   ";

        List<String> l1 = new ArrayList<>();
        List<String> l2 = Arrays.asList(s1, s2, s3);
        List<String> l3 = Arrays.asList(s1, s2, s4);
        List<String> l4 = Arrays.asList(s1, s2, s6);
        List<String> l5 = Arrays.asList(s4, s5);
        List<String> l6 = Arrays.asList(s6);

        assertTrue(S.hasEmpty(l1));
        assertTrue(S.allEmpty(l1));
        assertTrue(S.hasBlank(l1));
        assertTrue(S.allBlank(l1));
        assertFalse(S.hasEmpty(l2));
        assertFalse(S.hasBlank(l2));
        assertFalse(S.allEmpty(l2));
        assertFalse(S.allBlank(l2));
        assertTrue(S.hasEmpty(l3));
        assertTrue(S.hasBlank(l3));
        assertFalse(S.allEmpty(l3));
        assertFalse(S.allBlank(l3));
        assertFalse(S.hasEmpty(l4));
        assertTrue(S.hasBlank(l4));
        assertFalse(S.allEmpty(l4));
        assertFalse(S.allBlank(l4));
        assertTrue(S.hasEmpty(l5));
        assertTrue(S.hasBlank(l5));
        assertTrue(S.allEmpty(l5));
        assertTrue(S.allBlank(l5));
        assertFalse(S.hasEmpty(l6));
        assertTrue(S.hasBlank(l6));
        assertFalse(S.allEmpty(l6));
        assertTrue(S.allBlank(l6));

        // System.out.println(hasNullForStringVarargs());
        // System.out.println(hasNullForStringVarargs(null));
        // System.out.println(hasNullForStringVarargs((String) null));
        // System.out.println(hasNullForStringVarargs("", "a", ""));
        // System.out.println(hasNullForStringVarargs("", "a", null));
        assertFalse(hasNullForStringVarargs());
        assertTrue(hasNullForStringVarargs(null));
        assertTrue(hasNullForStringVarargs((String) null));
        assertFalse(hasNullForStringVarargs("", "a", ""));
        assertTrue(hasNullForStringVarargs("", "a", null));
    }

    private boolean hasNullForStringVarargs(String... strings) {
        return G.hasNull(strings);
    }

    @Test
    public void testBlank() {

        String s = "  \n \t　　　\f  ";
        String s1 = "  \n \t　　a　\f  ";
        String s2 = "  \n\n　　\f  ";
        String s3 = "  aaaa　\f  ";
        String s4 = null;
        String s5 = "";
        String[] nullSs = null;
        String[] nullSs1 = new String[]{};

        assertTrue(S.isBlank(s));
        assertFalse(S.isBlank(s1));
        assertTrue(S.isBlank(s2));
        assertTrue(S.hasBlank(s, s1));
        assertTrue(S.hasBlank(s4, s5));
        assertFalse(S.hasBlank(s1, s3));
        assertTrue(S.hasBlank(nullSs));
        assertTrue(S.hasBlank(nullSs1));
        assertTrue(S.allBlank(s, s2, s4, s5));
        assertFalse(S.allBlank(s, s1, s4, s5));
        assertTrue(S.allBlank(nullSs));
        assertTrue(S.allBlank(nullSs1));

    }

    @Test
    public void testNotEmptyOrNotNull() {
        int i = 10;
        String s1 = "";
        String s2 = "abcd";
        String s3 = null;
        Object o1 = new Object();
        Integer i1 = null;
        String[] nullSs = null;
        String[] ss1 = new String[]{};
        List<String> list = Arrays.asList("a", "b");
        Map<Object, Object> map = Map.of();

        System.out.println(G.isNotNull(i));
        System.out.println(G.isNotNull(o1));
        System.out.println(G.isNotNull(map));
        System.out.println(G.isNotNull(i1));
        System.out.println(G.isNotEmpty(i));
        System.out.println(G.isNotEmpty(list));
        System.out.println(G.isNotEmpty(nullSs));
        System.out.println(G.isNotEmpty(ss1));
        System.out.println(G.isNotEmpty(map));
        System.out.println(S.isNotBlank(s1));
        System.out.println(S.isNotBlank(s2));
        System.out.println(S.isNotBlank(s3));
        System.out.println(S.isNotEmpty(s2));
        System.out.println(S.isNotEmpty(s3));

        assertTrue(G.isNotNull(i));
        assertTrue(G.isNotNull(o1));
        assertTrue(G.isNotNull(map));
        assertFalse(G.isNotNull(i1));
        assertTrue(G.isNotEmpty(i));
        assertTrue(G.isNotEmpty(list));
        assertFalse(G.isNotEmpty(nullSs));
        assertFalse(G.isNotEmpty(ss1));
        assertFalse(G.isNotEmpty(map));
        assertFalse(S.isNotBlank(s1));
        assertTrue(S.isNotBlank(s2));
        assertFalse(S.isNotBlank(s3));
        assertTrue(S.isNotEmpty(s2));
        assertFalse(S.isNotEmpty(s3));

    }

    @Test
    public void testFirstNonNull() {
        String a = null;
        String b = "";
        String c = null;
        String d = "abc";
        Object o = new Object();

        String s = O.firstNonNull(a, b, c);
        String s1 = O.firstNonNull(a, c);
        String s2 = O.firstNonNull();
        String s3 = O.firstNonNull((String[]) null);
        String s4 = O.firstNonNull(a, d, c);
        Object o1 = O.firstNonNull(a, d, c, o);

        assertEquals("", s);
        assertNull(s1);
        assertNull(s2);
        assertNull(s3);
        assertEquals("abc", s4);
        assertEquals("abc", o1);

    }

    @Test
    public void testStackTrace() {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            System.out.println(G.stackTrace(e));
        }
    }

    @Test
    public void testCauseTrace() {
        try {
            throwException();
        } catch (Exception e) {
            List<Throwable> causes = O.causeTrace(e);
            System.out.println("causeTrace: " + causes);
            List<String> causeClassNames = causes.stream().map(c -> c.getClass().getSimpleName()).collect(Collectors.toList());
            System.out.println("causeClassNames: " + causeClassNames);
            assertEquals(causeClassNames.toString(), "[Exception, RuntimeException, IOException, IOException, ClassCastException]");
            assertTrue(causes.get(2) instanceof IOException);
            System.out.println("------------------------------------------------------------------------");
            System.out.println(G.stackTrace(e));
        }
    }

    void throwException() throws Exception {
        try {
            try {
                try {
                    try {
                        throw new ClassCastException("");
                    } catch (Exception e) {
                        throw new IOException(e);
                    }
                } catch (Exception e) {
                    throw new IOException(e);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test
    public void testSplitOnce() {
        Tuple2<String, String> t00 = S.splitOnce(":", ": ");
        Tuple2<String, String> t01 = S.splitOnce(null, ": ");
        Tuple2<String, String> t02 = S.splitOnce(": ", null);
        Tuple2<String, String> t03 = S.splitOnce(": ", ": ");
        Tuple2<String, String> t04 = S.splitOnce(": 1", ": ");
        Tuple2<String, String> t05 = S.splitOnce("a: ", ": ");
        Tuple2<String, String> t06 = S.splitOnce("a: 1", ": ");
        Tuple2<String, String> t07 = S.splitOnce(" :  ", ": ");
        Tuple2<String, String> t08 = S.splitOnce(" : : ", ": ");
        Tuple2<String, String> t09 = S.splitOnce(" :abc ", ": ");
        Tuple2<String, String> t10 = S.splitOnce("", "");
        Tuple2<String, String> t11 = S.splitOnce(null, null);

        assertEquals(t00._1 + "||" + t00._2, ":||null");
        assertEquals(t01._1 + "||" + t01._2, "null||null");
        assertEquals(t02._1 + "||" + t02._2, ": ||null");
        assertEquals(t03._1 + "||" + t03._2, "||");
        assertEquals(t04._1 + "||" + t04._2, "||1");
        assertEquals(t05._1 + "||" + t05._2, "a||");
        assertEquals(t06._1 + "||" + t06._2, "a||1");
        assertEquals(t07._1 + "||" + t07._2, " || ");
        assertEquals(t08._1 + "||" + t08._2, " ||: ");
        assertEquals(t09._1 + "||" + t09._2, " :abc ||null");
        assertEquals(t10._1 + "||" + t10._2, "||null");
        assertEquals(t11._1 + "||" + t11._2, "null||null");

    }

    @Test
    public void testToString() {
        Object[] os = null;
        char ch = 'a';
        String s = "abc";
        char[] a = {'a', 'b', 'c'};
        char[] a1 = {};
        Character[] c1 = {'a', 'b', null, 'c'};
        Character[] c2 = {};
        CharSequence[] cs = {"a", "b", "", "c", null};
        CharSequence[] cs1 = {};
        String[] ss = {"a", "b", null, "", "c"};
        String[] ss1 = {};
        Integer[] is = {};
        boolean[] bs = {true, false};

        AbstractMap.SimpleEntry<char[], String> entry1 = new AbstractMap.SimpleEntry<>(new char[]{'a', 'b'}, "abc");
        AbstractMap.SimpleEntry<AbstractMap.SimpleEntry<char[], String>, String> entry2 = new AbstractMap.SimpleEntry<>(entry1, "123");

        List<Serializable> arraysArrayList = Arrays.asList("abc", "123", a);    // java.util.Arrays$ArrayList
        List<Serializable> subList = arraysArrayList.subList(0, 2);             // java.util.AbstractList$RandomAccessSubList
        List<String> sList = new ArrayList<>();
        sList.add("a1");
        sList.add("a2");
        sList.add("a3");
        List<String> subList1 = sList.subList(0, 2);            // java.util.ArrayList$SubList

        Map<Integer, Integer> map1 = Map.of(1, 2);                      // java.util.ImmutableCollections$Map1
        Map<? extends Serializable, Integer> mapN = Map.of('1', 2, "3", 4);     // java.util.ImmutableCollections$MapN
        Map<Object, Object> singletonMap = Collections.singletonMap("a", subList);        // java.util.Collections$SingletonMap
        TreeMap<Object, Object> treeMap = new TreeMap<>();
        treeMap.put('1', 1);
        treeMap.put('2', subList1);
        treeMap.put('3', 3);
        NavigableMap<Object, Object> subMap1 = treeMap.subMap('1', true, '3', true);    // java.util.TreeMap$AscendingSubMap
        NavigableMap<Object, Object> subMap2 = treeMap.descendingMap();             // java.util.TreeMap$DescendingSubMap

        List<Object> csList = new ArrayList<>();
        csList.add("a");
        csList.add("");
        csList.add(null);
        csList.add(entry2);
        csList.add(arraysArrayList);
        csList.add(subMap1);
        csList.add(1);

        // --------------------------------------------------------

        Map<Object, Object> hashMap = new HashMap<>();
        hashMap.put(c1, ss);
        hashMap.put(ss1, bs);
        hashMap.put(mapN, subMap2);
        hashMap.put(null, os);
        hashMap.put(singletonMap, csList);
        hashMap.put(subList1, cs);
        hashMap.put(map1, map1);

        String mapToString = G.toString(hashMap);
        /*
         * mapToString的值：
         * {null=null, ['a', 'b', null, 'c']=["a", "b", null, "", "c"], ["a1", "a2"]=["a", "b", "", "c", null], {1=2}={1=2}, []=[true, false],
         * {"3"=4, '1'=2}={'3'=3, '2'=["a1", "a2"], '1'=1}, {"a"=["abc", "123"]}=["a", "", null, ['a', 'b']="abc"="123", ["abc", "123", ['a', 'b', 'c']],
         * {'1'=1, '2'=["a1", "a2"], '3'=3}, 1]}
         */
        // assertEquals("", mapToString);       // map 每次运行打印的kv顺序可能不一样
        System.out.println("G.toString(hashMap): \n" + mapToString);
        System.out.println("-----------------------");
        System.out.println("hashMap: \n" + hashMap);

    }

    @Test
    public void testToString1() {
        Object o = new Object();
        Object[] os = null;
        char ch = 'a';
        String s = "abc";
        Character[] c2 = {};
        CharSequence[] cs1 = {};
        Integer[] is = {};
        IOException ioException = new IOException("this is IOException!");
        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("a", 'a');
        concurrentHashMap.put("1", '1');
        concurrentHashMap.put('2', '2');

        Map<Object, Object> map = new HashMap<>();
        map.put(o, os);
        map.put(ch, s);
        map.put(map, map);
        map.put(c2, ioException);
        map.put("concurrentHashMap", concurrentHashMap);
        map.put(is, cs1);
        System.out.println("map: \n" + map);
        System.out.println("---------------------------");
        System.out.println("G.toString(map): \n" + G.toString(map));
        System.out.println("---------------------------");
        System.out.println("G.toString((Object) map): \n" + G.toString((Object) map));
    }

    @Test
    public void testToString2() {
        AbstractMap.SimpleEntry<String, Object> entry = new AbstractMap.SimpleEntry<>("1", 1);
        entry.setValue(entry);
        assertEquals("\"1\"=(this Entry)", G.toString(entry));
        System.out.println(G.toString(entry));

        // ----------------------------------------------
        Map<String, Object> map = Map.ofEntries(entry);
        String mapToString = G.toString(map);
        assertEquals("{\"1\"=\"1\"=(this Entry)}", mapToString);
        System.out.println(mapToString);

        // ----------------------------------------------
        Map<Object, Object> map1 = new HashMap<>();
        // map1.put(entry, "1");     // entry循环引用，作为key时，会导致堆栈溢出
        map1.put("1", entry);
        String mapToString1 = G.toString(map1);
        assertEquals("{\"1\"=\"1\"=(this Entry)}", mapToString1);
        System.out.println(mapToString1);

        // ----------------------------------------------
        HashMap<Object, Object> map2 = new HashMap<>();
        map2.put("a", 1);
        map2.put("b", 2);
        Set<Map.Entry<Object, Object>> entries = map2.entrySet();
        // 模拟循环引用
        for (Map.Entry<Object, Object> ooEntry : entries) {
            ooEntry.setValue(ooEntry);
            break;
        }
        String mapToString2 = G.toString(map2);
        assertEquals("{\"a\"=(this Entry), \"b\"=2}", mapToString2);
        System.out.println(mapToString2);
        // System.out.println(map2);   // entry循环引用，toString时，会导致堆栈溢出
    }

    @Test
    public void testToString3() {
        Object o = new Object();
        Object[] os = null;
        char[] a = {'a', 'b', 'c'};
        char[] a1 = {};
        Character[] c1 = {'a', 'b', null, 'c'};
        Character[] c2 = {};
        CharSequence[] cs = {"a", "b", "", "c", null};
        CharSequence[] cs1 = {};
        String[] ss = {"a", "b", null, "", "c"};
        String[] ss1 = {};
        Integer[] is = {};
        boolean[] bs = {true, false};
        IOException ioException = new IOException("this is IOException!");
        String s = "abc";
        char ch = 'a';

        List<CharSequence> csList = new ArrayList<>();
        csList.add("a");
        csList.add("");
        csList.add(null);
        csList.add("b");
        csList.add("c");

        String s01 = G.toString(o);
        String s02 = G.toString(os);
        assertEquals("null", s02);
        String s03 = G.toString(a);
        assertEquals("['a', 'b', 'c']", s03);
        String s04 = G.toString(a1);
        assertEquals("[]", s04);
        String s05 = G.toString(c1);
        assertEquals("['a', 'b', null, 'c']", s05);
        String s06 = G.toString(c2);
        assertEquals("[]", s06);
        String s07 = G.toString(cs);
        assertEquals("[\"a\", \"b\", \"\", \"c\", null]", s07);
        String s08 = G.toString(cs1);
        assertEquals("[]", s08);
        String s09 = G.toString(ss);
        assertEquals("[\"a\", \"b\", null, \"\", \"c\"]", s09);
        String s10 = G.toString(ss1);
        assertEquals("[]", s10);
        String s11 = G.toString(is);
        assertEquals("[]", s11);
        String s12 = G.toString(bs);
        assertEquals("[true, false]", s12);
        String s13 = G.toString(ioException);
        String s14 = G.toString(s);
        assertEquals("\"abc\"", s14);
        String s15 = G.toString(ch);
        assertEquals("'a'", s15);
        String s16 = G.toString(csList);
        assertEquals("[\"a\", \"\", null, \"b\", \"c\"]", s16);

        System.out.println(s01);
        System.out.println(s10);
        System.out.println(s11);
        System.out.println(s12);
        System.out.println(s13);
        System.out.println(s14);
        System.out.println(s15);
        System.out.println(s16);
    }

    @Test
    public void testToString4() {
        Object o = new Object();
        Object[] os = null;
        char[] a = {'a', 'b', 'c'};
        char[] a1 = {};
        Character[] c = {'a', 'b', null, 'c'};
        Character[] c1 = {};
        CharSequence[] cs = {"a", "b", "", "c", null};
        CharSequence[] cs1 = {};
        String[] ss = {"a", "b", null, "", "c"};
        String[] ss1 = {};
        Integer[] is = {};
        boolean[] bs = {true, false};
        IOException ioException = new IOException("this is IOException!");
        String s = "abc";
        char ch = 'a';

        List<CharSequence> csList = new ArrayList<>();
        csList.add("a");
        csList.add("");
        csList.add(null);
        csList.add("b");
        csList.add("c");

        String s01 = G.toString(o);
        String s02 = G.toString((Object) os);
        assertEquals("null", s02);
        String s03 = G.toString((Object) a);
        assertEquals("['a', 'b', 'c']", s03);
        String s04 = G.toString((Object) a1);
        assertEquals("[]", s04);
        String s05 = G.toString((Object) c);
        assertEquals("['a', 'b', null, 'c']", s05);
        String s06 = G.toString((Object) c1);
        assertEquals("[]", s06);
        String s07 = G.toString((Object) cs);
        assertEquals("[\"a\", \"b\", \"\", \"c\", null]", s07);
        String s08 = G.toString((Object) cs1);
        assertEquals("[]", s08);
        String s09 = G.toString((Object) ss);
        assertEquals("[\"a\", \"b\", null, \"\", \"c\"]", s09);
        String s10 = G.toString((Object) ss1);
        assertEquals("[]", s10);
        String s11 = G.toString((Object) is);
        assertEquals("[]", s11);
        String s12 = G.toString((Object) bs);
        assertEquals("[true, false]", s12);
        String s13 = G.toString((Object) ioException);
        String s14 = G.toString(s);
        assertEquals("\"abc\"", s14);
        String s15 = G.toString(ch);
        assertEquals("'a'", s15);
        String s16 = G.toString((Object) csList);
        assertEquals("[\"a\", \"\", null, \"b\", \"c\"]", s16);

        System.out.println(s01);
        System.out.println(s10);
        System.out.println(s11);
        System.out.println(s12);
        System.out.println(s13);
        System.out.println(s14);
        System.out.println(s15);
        System.out.println(s16);
    }

    @Test
    public void testToString5() {
        char[][][] chars = {{{'a', 'b'}, null}, {{'1', '2'}}, null, {}};
        Object[][][] objects = {{{'a', "b", null}, null}, {{'1', 2, 1.205}}, null, {}};
        Object[][][] objects1 = null;
        assertEquals("[[['a', 'b'], null], [['1', '2']], null, []]", G.toString(chars));
        assertEquals("[[['a', \"b\", null], null], [['1', 2, 1.205]], null, []]", G.toString(objects));
        assertNull(null, G.toString(objects1));
        System.out.println(G.toString(chars));
        System.out.println(G.toString(objects));
        System.out.println(G.toString(objects1));
    }

    @Test
    public void testDTToString1() {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(2022, 1, 1, 10, 5, 5, 987654789, ZoneId.systemDefault());
        OffsetDateTime offsetDateTime = zonedDateTime.toOffsetDateTime();
        Instant instant = zonedDateTime.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        String dtSimple0 = G.dtSimple(zonedDateTime);
        String toString0 = G.toString(zonedDateTime);
        String dtDetail0 = G.dtDetail(zonedDateTime);
        String dtSimple1 = G.dtSimple(offsetDateTime);
        String toString1 = G.toString(offsetDateTime);
        String dtDetail1 = G.dtDetail(offsetDateTime);
        String dtSimple2 = G.dtSimple(localDateTime);
        String toString2 = G.toString(localDateTime);
        String dtDetail2 = G.dtDetail(localDateTime);
        String dtSimple3 = G.dtSimple(instant);
        String toString3 = G.toString(instant);
        String dtDetail3 = G.dtDetail(instant);
        String dtSimple4 = G.dtSimple(date);
        String toString4 = G.toString(date);
        String dtDetail4 = G.dtDetail(date);
        String dtSimple5 = G.dtSimple(calendar);
        String toString5 = G.toString(calendar);
        String dtDetail5 = G.dtDetail(calendar);
        // zonedDateTime
        assertEquals("2022-01-01 10:05:05.987", dtSimple0);
        assertEquals("2022-01-01 10:05:05.987", toString0);
        assertEquals("2022-01-01 10:05:05.987654789 [Asia/Shanghai +08:00 GMT+8 周六]", dtDetail0);
        // offsetDateTime
        assertEquals("2022-01-01 10:05:05.987", dtSimple1);
        assertEquals("2022-01-01 10:05:05.987", toString1);
        assertEquals("2022-01-01 10:05:05.987654789 [+08:00 GMT+8 周六]", dtDetail1);
        // localDateTime
        assertEquals("2022-01-01 10:05:05.987", dtSimple2);
        assertEquals("2022-01-01 10:05:05.987", toString2);
        assertEquals("2022-01-01 10:05:05.987654789 [周六]", dtDetail2);
        // instant
        assertEquals("2022-01-01 10:05:05.987", dtSimple3);
        assertEquals("2022-01-01 10:05:05.987", toString3);
        assertEquals("2022-01-01 10:05:05.987654789 [Asia/Shanghai +08:00 GMT+8 周六]", dtDetail3);
        // date
        assertEquals("2022-01-01 10:05:05.987", dtSimple4);
        assertEquals("2022-01-01 10:05:05.987", toString4);
        assertEquals("2022-01-01 10:05:05.987000000 [Asia/Shanghai +08:00 GMT+8 周六]", dtDetail4);
        // calendar
        assertEquals("2022-01-01 10:05:05.987", dtSimple5);
        assertEquals("2022-01-01 10:05:05.987", toString5);
        assertEquals("2022-01-01 10:05:05.987000000 [Asia/Shanghai +08:00 GMT+8 周六]", dtDetail5);
        // System.out.println("=========zonedDateTime=========");
        // System.out.println(dtSimple0);
        // System.out.println(toString0);
        // System.out.println(dtDetail0);
        // System.out.println("=========offsetDateTime=========");
        // System.out.println(dtSimple1);
        // System.out.println(toString1);
        // System.out.println(dtDetail1);
        // System.out.println("=========localDateTime=========");
        // System.out.println(dtSimple2);
        // System.out.println(toString2);
        // System.out.println(dtDetail2);
        // System.out.println("=========instant=========");
        // System.out.println(dtSimple3);
        // System.out.println(toString3);
        // System.out.println(dtDetail3);
        // System.out.println("=========date=========");
        // System.out.println(dtSimple4);
        // System.out.println(toString4);
        // System.out.println(dtDetail4);
        // System.out.println("=========calendar=========");
        // System.out.println(dtSimple5);
        // System.out.println(toString5);
        // System.out.println(dtDetail5);
    }

    @Test
    public void testDTToString2() {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(2022, 1, 1, 10, 5, 5, 987654789, ZoneId.of("America/New_York"));
        OffsetDateTime offsetDateTime = zonedDateTime.toOffsetDateTime();
        Instant instant = zonedDateTime.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setTimeZone(TimeZone.getTimeZone(ZoneId.of("America/New_York")));

        String dtSimple0 = G.dtSimple(zonedDateTime);
        String toString0 = G.toString(zonedDateTime);
        String dtDetail0 = G.dtDetail(zonedDateTime);
        String dtSimple1 = G.dtSimple(offsetDateTime);
        String toString1 = G.toString(offsetDateTime);
        String dtDetail1 = G.dtDetail(offsetDateTime);
        String dtSimple2 = G.dtSimple(localDateTime);
        String toString2 = G.toString(localDateTime);
        String dtDetail2 = G.dtDetail(localDateTime);
        String dtSimple3 = G.dtSimple(instant);
        String toString3 = G.toString(instant);
        String dtDetail3 = G.dtDetail(instant);
        String dtSimple4 = G.dtSimple(date);
        String toString4 = G.toString(date);
        String dtDetail4 = G.dtDetail(date);
        String dtSimple5 = G.dtSimple(calendar);
        String toString5 = G.toString(calendar);
        String dtDetail5 = G.dtDetail(calendar);
        // zonedDateTime
        assertEquals("2022-01-01 10:05:05.987 [America/New_York -05:00]", dtSimple0);
        assertEquals("2022-01-01 10:05:05.987 [America/New_York -05:00]", toString0);
        assertEquals("2022-01-01 10:05:05.987654789 [America/New_York -05:00 GMT-5 周六]", dtDetail0);
        // offsetDateTime
        assertEquals("2022-01-01 10:05:05.987 [-05:00]", dtSimple1);
        assertEquals("2022-01-01 10:05:05.987 [-05:00]", toString1);
        assertEquals("2022-01-01 10:05:05.987654789 [-05:00 GMT-5 周六]", dtDetail1);
        // localDateTime
        assertEquals("2022-01-01 23:05:05.987", dtSimple2);
        assertEquals("2022-01-01 23:05:05.987", toString2);
        assertEquals("2022-01-01 23:05:05.987654789 [周六]", dtDetail2);
        // instant
        assertEquals("2022-01-01 23:05:05.987", dtSimple3);
        assertEquals("2022-01-01 23:05:05.987", toString3);
        assertEquals("2022-01-01 23:05:05.987654789 [Asia/Shanghai +08:00 GMT+8 周六]", dtDetail3);
        // date
        assertEquals("2022-01-01 23:05:05.987", dtSimple4);
        assertEquals("2022-01-01 23:05:05.987", toString4);
        assertEquals("2022-01-01 23:05:05.987000000 [Asia/Shanghai +08:00 GMT+8 周六]", dtDetail4);
        // calendar
        assertEquals("2022-01-01 10:05:05.987 [America/New_York -05:00]", dtSimple5);
        assertEquals("2022-01-01 10:05:05.987 [America/New_York -05:00]", toString5);
        assertEquals("2022-01-01 10:05:05.987000000 [America/New_York -05:00 GMT-5 周六]", dtDetail5);
        // System.out.println("=========zonedDateTime=========");
        // System.out.println(dtSimple0);
        // System.out.println(toString0);
        // System.out.println(dtDetail0);
        // System.out.println("=========offsetDateTime=========");
        // System.out.println(dtSimple1);
        // System.out.println(toString1);
        // System.out.println(dtDetail1);
        // System.out.println("=========localDateTime=========");
        // System.out.println(dtSimple2);
        // System.out.println(toString2);
        // System.out.println(dtDetail2);
        // System.out.println("=========instant=========");
        // System.out.println(dtSimple3);
        // System.out.println(toString3);
        // System.out.println(dtDetail3);
        // System.out.println("=========date=========");
        // System.out.println(dtSimple4);
        // System.out.println(toString4);
        // System.out.println(dtDetail4);
        // System.out.println("=========calendar=========");
        // System.out.println(dtSimple5);
        // System.out.println(toString5);
        // System.out.println(dtDetail5);
    }

    @Test
    public void testDTToString3() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.AUGUST, 29, 10, 30, 15);
        calendar.set(Calendar.MILLISECOND, 567);
        long timeInMillis = calendar.getTimeInMillis();
        Date date = calendar.getTime();
        java.sql.Date sqlDate = new java.sql.Date(timeInMillis);
        Time sqlTime = new Time(timeInMillis);
        Timestamp timestamp = new Timestamp(timeInMillis);
        System.out.println("date: " + G.toString(date));
        System.out.println("date: " + G.dtSimple(date));
        System.out.println("date: " + G.dtDetail(date));
        System.out.println("sqlDate: " + G.toString(sqlDate));
        System.out.println("sqlDate: " + G.dtSimple(sqlDate));
        System.out.println("sqlDate: " + G.dtDetail(sqlDate));
        System.out.println("sqlTime: " + G.toString(sqlTime));
        System.out.println("sqlTime: " + G.dtSimple(sqlTime));
        System.out.println("sqlTime: " + G.dtDetail(sqlTime));
        System.out.println("timestamp: " + G.toString(timestamp));
        System.out.println("timestamp: " + G.dtSimple(timestamp));
        System.out.println("timestamp: " + G.dtDetail(timestamp));

        assertEquals("2023-08-29 10:30:15.567", G.toString(date));
        assertEquals("2023-08-29 10:30:15.567", G.dtSimple(date));
        assertEquals("2023-08-29 10:30:15.567000000 [Asia/Shanghai +08:00 GMT+8 周二]", G.dtDetail(date));
        assertEquals("2023-08-29", G.toString(sqlDate));
        assertEquals("2023-08-29", G.dtSimple(sqlDate));
        assertEquals("2023-08-29", G.dtDetail(sqlDate));
        assertEquals("10:30:15", G.toString(sqlTime));
        assertEquals("10:30:15", G.dtSimple(sqlTime));
        assertEquals("10:30:15", G.dtDetail(sqlTime));
        assertEquals("2023-08-29 10:30:15.567", G.toString(timestamp));
        assertEquals("2023-08-29 10:30:15.567", G.dtSimple(timestamp));
        assertEquals("2023-08-29 10:30:15.567000000 [Asia/Shanghai +08:00 GMT+8 周二]", G.dtDetail(timestamp));

    }


    @Test
    public void testArrayEmpty() {
        CharSequence[] cs1 = O.array0(CharSequence[].class);
        CharSequence[] cs2 = O.arrayO(CharSequence.class);
        List<String> strings = new ArrayList<>();
        CharSequence[] sArray1 = strings.toArray(cs1);
        CharSequence[] sArray2 = strings.toArray(cs2);
        System.out.println(G.toString(sArray1) + "---" + sArray1.getClass().getName());
        System.out.println(G.toString(sArray2) + "---" + sArray2.getClass().getName());
        assertEquals("[]", G.toString(sArray1));
        assertEquals("[]", G.toString(sArray2));
        strings.add("a");
        strings.add("bc");
        strings.add("def");
        CharSequence[] sArray3 = strings.toArray(cs1);
        CharSequence[] sArray4 = strings.toArray(cs2);
        System.out.println(G.toString(sArray3) + "    sArray3 type：" + sArray3.getClass().getName() + "    sArray3.length：" + sArray3.length);
        System.out.println(G.toString(sArray4) + "    sArray4 type：" + sArray4.getClass().getName() + "    sArray4.length：" + sArray4.length);
        assertEquals("[\"a\", \"bc\", \"def\"]", G.toString(sArray3));
        assertEquals("[\"a\", \"bc\", \"def\"]", G.toString(sArray4));
        assertEquals("[Ljava.lang.CharSequence;", sArray3.getClass().getName());
        assertEquals("[Ljava.lang.CharSequence;", sArray4.getClass().getName());

        System.out.println(sArray3[0].getClass().getName());        // java.lang.String
        System.out.println(sArray4[0].getClass().getName());        // java.lang.String
        assertEquals(1, sArray3[0].length());
        assertEquals(2, sArray4[1].length());

    }

    @Test
    public void testArrayEmpty1() {
        char[][] cs = O.arrayO(char[].class);
        List<char[]> charsList = new ArrayList<>();
        charsList.add(new char[]{'a'});
        charsList.add(null);
        charsList.add(new char[]{'a', 'b', 'c'});

        char[][] charss = charsList.toArray(cs);
        assertEquals("[['a'], null, ['a', 'b', 'c']]", G.toString(charss));
        assertEquals("['a', 'b', 'c']", G.toString(charss[2]));
        assertEquals('b', charss[2][1]);
        System.out.println(G.toString(charss));                 // [['a'], null, ['a', 'b', 'c']]
        System.out.println(G.toString(charss[2]));              // ['a', 'b', 'c']
        System.out.println(charss[2].getClass().getName());     // [C
        System.out.println(Objects.equals(charss[2][1], 'b'));  // true
    }

    @Test
    public void testArrayN() {
        String[] stringsM = O.arrayM(String[].class, 3);
        String[] stringsN = O.arrayN(String.class, 3);
        stringsM[0] = "a";
        stringsN[0] = "a";
        System.out.println(G.toString(stringsM));
        System.out.println(G.toString(stringsN));
        System.out.println(stringsM[0].getClass());
        System.out.println(stringsN[0].getClass());
        assertEquals(G.toString(stringsM), "[\"a\", null, null]");
        assertEquals(G.toString(stringsN), "[\"a\", null, null]");
        assertEquals(stringsM[0].getClass(), String.class);
        assertEquals(stringsN[0].getClass(), String.class);
    }

    @Test
    public void testArrayFill() {
        Character[] fillM = O.arrayFillM(Character[].class, 3, '?');
        Character[] fillN = O.arrayFillN(Character.class, 3, '?');
        Character[] fillM1 = O.arrayFillM(Character[].class, 3, null);
        Character[] fillN1 = O.arrayFillN(Character.class, 3, null);
        System.out.println(G.toString(fillM));
        System.out.println(G.toString(fillN));
        System.out.println(G.toString(fillM1));
        System.out.println(G.toString(fillN1));
        fillM1[0] = '?';
        fillN1[0] = '?';
        System.out.println(G.toString(fillM1));
        System.out.println(G.toString(fillN1));
        System.out.println(fillM.getClass());
        System.out.println(fillN.getClass());
        assertEquals(G.toString(fillM), "['?', '?', '?']");
        assertEquals(G.toString(fillN), "['?', '?', '?']");
        assertEquals(G.toString(fillM1), "['?', null, null]");
        assertEquals(G.toString(fillN1), "['?', null, null]");
        assertEquals(fillM1[0].getClass(), Character.class);
        assertEquals(fillN1[0].getClass(), Character.class);
        assertEquals(fillM.getClass(), Character[].class);
        assertEquals(fillN.getClass(), Character[].class);
    }

    @Test
    public void testSwapNullEmptyBlank() {
        String s0 = null;
        String s1 = "";
        String s2 = "　 \r\t\n  ";
        String s3 = "abcdefg";

        String nullToEmpty0 = S.nullToEmpty(s0);
        String nullToEmpty1 = S.nullToEmpty(s1);
        String nullToEmpty2 = S.nullToEmpty(s2);
        String nullToEmpty3 = S.nullToEmpty(s3);
        assertEquals("", nullToEmpty0);
        assertEquals("", nullToEmpty1);
        assertEquals(s2, nullToEmpty2);
        assertEquals(s3, nullToEmpty3);

        String emptyToNull0 = S.emptyToNull(s0);
        String emptyToNull1 = S.emptyToNull(s1);
        String emptyToNull2 = S.emptyToNull(s2);
        String emptyToNull3 = S.emptyToNull(s3);
        assertNull(emptyToNull0);
        assertNull(emptyToNull1);
        assertEquals(s2, emptyToNull2);
        assertEquals(s3, emptyToNull3);

        String blankToNull0 = S.blankToNull(s0);
        String blankToNull1 = S.blankToNull(s1);
        String blankToNull2 = S.blankToNull(s2);
        String blankToNull3 = S.blankToNull(s3);
        assertNull(blankToNull0);
        assertNull(blankToNull1);
        assertNull(blankToNull2);
        assertEquals(s3, blankToNull3);

        String blankToEmpty0 = S.blankToEmpty(s0);
        String blankToEmpty1 = S.blankToEmpty(s1);
        String blankToEmpty2 = S.blankToEmpty(s2);
        String blankToEmpty3 = S.blankToEmpty(s3);
        assertEquals("", blankToEmpty0);
        assertEquals("", blankToEmpty1);
        assertEquals("", blankToEmpty2);
        assertEquals(s3, blankToEmpty3);

    }

    @Test
    public void testCountChars() {
        String s1 = "";
        String s2 = "   ";
        String s3 = "abc a bc";
        String s4 = "ab呀呀--abcaaa呀";

        assertEquals(S.countChars(s1, ' '), 0);
        assertEquals(S.countChars(s1, 'a'), 0);
        assertEquals(S.countChars(s2, ' '), 3);
        assertEquals(S.countChars(s2, 'a'), 0);
        assertEquals(S.countChars(s3, ' '), 2);
        assertEquals(S.countChars(s3, 'a'), 2);
        assertEquals(S.countChars(s4, ' '), 0);
        assertEquals(S.countChars(s4, 'a'), 5);
        assertEquals(S.countChars(s4, '呀'), 3);

    }

    @Test
    public void testCountMultiChars() {
        String s1 = null;
        String s2 = "";
        String s3 = "abc- Defgh_IJ--klm  _nop-";
        String s4 = "  -ldf al_lf呀dsa  _lf呀呀dksa-";

        int[] ints1 = S.countMultiChars(s1);
        int[] ints2 = S.countMultiChars(s1, '-');
        int[] ints3 = S.countMultiChars(s2);
        int[] ints4 = S.countMultiChars(s2, '-');
        int[] ints5 = S.countMultiChars(s3, '-', ' ', 'z', '_');
        int[] ints6 = S.countMultiChars(s4, '-', '呀', ' ');

        int[] ints11 = S.countMultiChars(s1, "");
        int[] ints12 = S.countMultiChars(s1, "-");
        int[] ints13 = S.countMultiChars(s2, "");
        int[] ints14 = S.countMultiChars(s2, "-");
        int[] ints15 = S.countMultiChars(s3, "- z_");
        int[] ints16 = S.countMultiChars(s4, "-呀 ");

        assertEquals(G.toString(ints1), "[]");
        assertEquals(G.toString(ints2), "[0]");
        assertEquals(G.toString(ints3), "[]");
        assertEquals(G.toString(ints4), "[0]");
        assertEquals(G.toString(ints5), "[4, 3, 0, 2]");
        assertEquals(G.toString(ints6), "[2, 3, 5]");

        assertEquals(G.toString(ints11), "[]");
        assertEquals(G.toString(ints12), "[0]");
        assertEquals(G.toString(ints13), "[]");
        assertEquals(G.toString(ints14), "[0]");
        assertEquals(G.toString(ints15), "[4, 3, 0, 2]");
        assertEquals(G.toString(ints16), "[2, 3, 5]");

    }

    @Test
    public void testPadLeftChars() {
        String s0 = null;
        String s1 = "";
        String s2 = "abcd";
        Integer i = 10;

        String s00 = S.padLeftChars(s0, '0', 0);        // null
        String s01 = S.padLeftChars(s1, '0', 0);        //
        String s02 = S.padLeftChars(s2, '0', 0);        // abcd
        String s10 = S.padLeftChars(s0, '#', 3);        // null
        String s11 = S.padLeftChars(s1, '#', 3);        // ###
        String s12 = S.padLeftChars(s2, '#', 3);        // abcd
        String s20 = S.padLeftChars(s0, '0', 5);        // null
        String s21 = S.padLeftChars(s1, '0', 5);        // 00000
        String s22 = S.padLeftChars(s2, '0', 5);        // 0abcd
        String i1 = S.padLeftChars(i, '0', 0);          // 10
        String i2 = S.padLeftChars(i, '0', 1);          // 10
        String i3 = S.padLeftChars(i, ' ', 5);          // 00010
        String i4 = S.padLeftChars(i, '#', 3);          // #10

        System.out.println(s00);        // null
        System.out.println(s01);        //
        System.out.println(s02);        // abcd
        System.out.println(s10);        // null
        System.out.println(s11);        // ###
        System.out.println(s12);        // abcd
        System.out.println(s20);        // null
        System.out.println(s21);        // 00000
        System.out.println(s22);        // 0abcd
        System.out.println(i1);         // 10
        System.out.println(i2);         // 10
        System.out.println(i3);         // 00010
        System.out.println(i4);         // #10

        assertNull(s00);
        assertEquals(s01, "");
        assertEquals(s02, "abcd");
        assertNull(s10);
        assertEquals(s11, "###");
        assertEquals(s12, "abcd");
        assertNull(s20);
        assertEquals(s21, "00000");
        assertEquals(s22, "0abcd");
        assertEquals(i1, "10");
        assertEquals(i2, "10");
        assertEquals(i3, "   10");
        assertEquals(i4, "#10");
    }

    @Test
    public void testPadRightChars() {
        String s0 = null;
        String s1 = "";
        String s2 = "abcd";
        Integer i = 10;

        String s00 = S.padRightChars(s0, '0', 0);
        String s01 = S.padRightChars(s1, '0', 0);
        String s02 = S.padRightChars(s2, '0', 0);
        String s10 = S.padRightChars(s0, '#', 3);
        String s11 = S.padRightChars(s1, '#', 3);
        String s12 = S.padRightChars(s2, '#', 3);
        String s20 = S.padRightChars(s0, '0', 5);
        String s21 = S.padRightChars(s1, '0', 5);
        String s22 = S.padRightChars(s2, '0', 5);
        String i1 = S.padRightChars(i, '0', 0);
        String i2 = S.padRightChars(i, '0', 1);
        String i3 = S.padRightChars(i, ' ', 5);
        String i4 = S.padRightChars(i, '#', 3);

        System.out.println(s00);
        System.out.println(s01);
        System.out.println(s02);
        System.out.println(s10);
        System.out.println(s11);
        System.out.println(s12);
        System.out.println(s20);
        System.out.println(s21);
        System.out.println(s22);
        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i3);
        System.out.println(i4);

        assertNull(s00);
        assertEquals(s01, "");
        assertEquals(s02, "abcd");
        assertNull(s10);
        assertEquals(s11, "###");
        assertEquals(s12, "abcd");
        assertNull(s20);
        assertEquals(s21, "00000");
        assertEquals(s22, "abcd0");
        assertEquals(i1, "10");
        assertEquals(i2, "10");
        assertEquals(i3, "10   ");
        assertEquals(i4, "10#");
    }

    @Test
    public void testEnclose() {
        String s1 = S.enclose("abc", '"');
        String s2 = S.enclose("abc", "||");
        String s3 = S.enclose("abc", '>', '<');
        String s4 = S.enclose("abc", ">>", "<<");
        String s5 = S.enclose("abc", null, "<<");
        String s6 = S.enclose(null, ">>", "<<");

        assertEquals(s1, "\"abc\"");
        assertEquals(s2, "||abc||");
        assertEquals(s3, ">abc<");
        assertEquals(s4, ">>abc<<");
        assertEquals(s5, "abc<<");
        assertNull(s6);

    }

    @Test
    public void testIndexOf() {
        assertThrows(NullPointerException.class, () -> S.indexOf(null, "", 1));
        assertThrows(NullPointerException.class, () -> S.indexOf("", null, 1));
        assertThrows(UnexpectedParameterException.class, () -> S.indexOf("", "", 0));
        int i1 = S.indexOf("", "", 1);
        int i2 = S.indexOf("abcabcaaabc", "abc", 3);
        int i3 = S.indexOf("abcabcaaabc", "abc", 6);
        int i4 = S.indexOf("abcabcaaabc", "b", 3);
        int i5 = S.indexOf("abcabcaaabc", "a", 2);
        int i6 = S.indexOf("aaaaaaaa", "aaa", 5);
        int i7 = S.indexOf("aaaaaaaa", "abc", 1);
        int i8 = S.indexOf("", "", 3);
        System.out.println("".indexOf("")); // 0
        System.out.println("a".indexOf(""));    // 0
        System.out.println("".indexOf("a"));    // -1
        // System.out.println("".indexOf(null));    // java.lang.NullPointerException
        System.out.println(i1);     // 0
        System.out.println(i2);     // 8
        System.out.println(i3);     // -1
        System.out.println(i4);     // 9
        System.out.println(i5);     // 3
        System.out.println(i6);     // 4
        System.out.println(i7);     // -1
        System.out.println(i8);     // 0

        assertEquals(i1, 0);
        assertEquals(i2, 8);
        assertEquals(i3, -1);
        assertEquals(i4, 9);
        assertEquals(i5, 3);
        assertEquals(i6, 4);
        assertEquals(i7, -1);
        assertEquals(i8, 0);

    }

    @Test
    public void testEndsWith() {
        boolean b01 = S.endsWith(null, null, -2);       // false
        boolean b02 = S.endsWith(null, null, 0);        // false
        boolean b03 = S.endsWith(null, null, 2);        // false
        boolean b04 = S.endsWith("", null, -2);         // false
        boolean b05 = S.endsWith("", null, 0);          // false
        boolean b06 = S.endsWith("", null, 2);          // false
        boolean b07 = S.endsWith(null, "", -2);         // false
        boolean b08 = S.endsWith(null, "", 0);          // false
        boolean b09 = S.endsWith(null, "", 2);          // false
        boolean b10 = S.endsWith("", "", -2);           // false
        boolean b11 = S.endsWith("", "", 0);            // true
        boolean b12 = S.endsWith("", "", 2);            // false
        boolean b13 = S.endsWith("abc", "", -2);        // false
        boolean b14 = S.endsWith("abc", "", 0);         // true
        boolean b15 = S.endsWith("abc", "", 2);         // true
        boolean b16 = S.endsWith("", "abc", -2);        // false
        boolean b17 = S.endsWith("", "abc", 0);         // false
        boolean b18 = S.endsWith("", "abc", 2);         // false
        boolean b19 = S.endsWith("abcabc", "abc", -2);  // false
        boolean b20 = S.endsWith("abcabc", "abc", 0);   // true
        boolean b21 = S.endsWith("abcabc", "abc", 1);   // false
        boolean b22 = S.endsWith("abcabc", "abc", 3);   // true

        System.out.println("abc".endsWith(""));     // true
        System.out.println("".endsWith(""));        // true

        assertFalse(b01);
        assertFalse(b02);
        assertFalse(b03);
        assertFalse(b04);
        assertFalse(b05);
        assertFalse(b06);
        assertFalse(b07);
        assertFalse(b08);
        assertFalse(b09);
        assertFalse(b10);
        assertTrue(b11);
        assertFalse(b12);
        assertFalse(b13);
        assertTrue(b14);
        assertTrue(b15);
        assertFalse(b16);
        assertFalse(b17);
        assertFalse(b18);
        assertFalse(b19);
        assertTrue(b20);
        assertFalse(b21);
        assertTrue(b22);

    }

    @Test
    public void testTrim() {
        String s01 = S.trimOnce(null, "", "");
        String s02 = S.trimOnce("", null, null);
        String s03 = S.trimOnce("", "", null);
        String s04 = S.trimOnce("abc", null, null);
        String s05 = S.trimOnce("abc", "", "c");
        String s06 = S.trimOnce("abc", "a", "");
        String s07 = S.trimOnce("abc", "abc", "abc");
        String s08 = S.trimOnce("abcabc1111dddddd", "abc", "ddd");
        String s09 = S.trimOnce("abc", "abcd", "abcd");
        String s10 = S.trimOnce("abc", "a", "abc", true);
        String s11 = S.trimOnce("abc", "a", "abc", false);

        assertNull(s01);
        assertEquals(s02, "");
        assertEquals(s03, "");
        assertEquals(s04, "abc");
        assertEquals(s05, "ab");
        assertEquals(s06, "bc");
        assertEquals(s07, "");
        assertEquals(s08, "abc1111ddd");
        assertEquals(s09, "abc");
        assertEquals(s10, "bc");
        assertEquals(s11, "");


        String s21 = S.trim(null, "", "");
        String s22 = S.trim("", null, null);
        String s23 = S.trim("", "", null);
        String s24 = S.trim("abc", null, null);
        String s25 = S.trim("abc", "", "c");
        String s26 = S.trim("abc", "a", "");
        String s27 = S.trim("abc", "abc", "abc");
        String s28 = S.trim("abcabc1111dddddd", "abc", "ddd");
        String s29 = S.trim("abc", "abcd", "abcd");
        String s30 = S.trim("aaaaa", "a", "");
        String s31 = S.trim("aaaaa", "", "a");
        String s32 = S.trim("aaaaa", "a", "a");
        String s33 = S.trim("abcabca", "abc", "");
        String s34 = S.trim("aaabcabc", "", "abc", true);
        String s35 = S.trim("aaabcabc", "", "abc", false);
        String s36 = S.trim("aaabcabcabc", "a", "abc", true);
        String s37 = S.trim("aaabcabcabc", "a", "abc", false);
        String s38 = S.trim("1111abcabcabc", "1", "abc", true);
        String s39 = S.trim("1111abcabcabc", "1", "abc", false);
        String s40 = S.trim("10241024abcabcabc", "1024", "abc", true);
        String s41 = S.trim("10241024abcabcabc", "1024", "abc", false);
        String s42 = S.trim("", "abc", "a", true);
        String s43 = S.trim("", "abc", "a", false);

        assertNull(s21);
        assertEquals(s22, "");
        assertEquals(s23, "");
        assertEquals(s24, "abc");
        assertEquals(s25, "ab");
        assertEquals(s26, "bc");
        assertEquals(s27, "");
        assertEquals(s28, "1111");
        assertEquals(s29, "abc");
        assertEquals(s30, "");
        assertEquals(s31, "");
        assertEquals(s32, "");
        assertEquals(s33, "a");
        assertEquals(s34, "aa");
        assertEquals(s35, "aa");
        assertEquals(s36, "bc");
        assertEquals(s37, "");
        assertEquals(s38, "");
        assertEquals(s39, "");
        assertEquals(s40, "");
        assertEquals(s41, "");
        assertEquals(s42, "");
        assertEquals(s43, "");

        // System.out.println("s21: " + s21);
        // System.out.println("s22: " + s22);
        // System.out.println("s23: " + s23);
        // System.out.println("s24: " + s24);
        // System.out.println("s25: " + s25);
        // System.out.println("s26: " + s26);
        // System.out.println("s27: " + s27);
        // System.out.println("s28: " + s28);
        // System.out.println("s29: " + s29);
        // System.out.println("s30: " + s30);
        // System.out.println("s31: " + s31);
        // System.out.println("s32: " + s32);
        // System.out.println("s33: " + s33);
        // System.out.println("s34: " + s34);
        // System.out.println("s35: " + s35);
        // System.out.println("s36: " + s36);
        // System.out.println("s37: " + s37);
        // System.out.println("s38: " + s38);
        // System.out.println("s39: " + s39);
        // System.out.println("s40: " + s40);
        // System.out.println("s41: " + s41);
        // System.out.println("s42: " + s42);
        // System.out.println("s43: " + s43);

    }

    @Test
    public void testRepeat() {
        String repeat01 = S.repeat(null, -1);
        String repeat02 = S.repeat("", -1);
        String repeat03 = S.repeat("", 10);
        String repeat04 = S.repeat("abc", 20);
        String repeat05 = S.repeat("abc", 0);
        String repeat06 = S.repeat("abc", -1);
        String repeat07 = S.repeat("abc", 1);
        String repeat08 = S.repeat("", Integer.MAX_VALUE);

        System.out.println(repeat01);
        System.out.println(repeat02);
        System.out.println(repeat03);
        System.out.println(repeat04);
        System.out.println(repeat05);
        System.out.println(repeat06);
        System.out.println(repeat07);
        System.out.println(repeat08);

        assertNull(repeat01);
        assertEquals(repeat02, "");
        assertEquals(repeat03, "");
        assertEquals(repeat04, "abcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabc");
        assertEquals(repeat05, "");
        assertEquals(repeat06, "");
        assertEquals(repeat07, "abc");
        assertEquals(repeat08, "");

        try {
            S.repeat("a", Integer.MAX_VALUE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertEquals(e.getMessage(), "Parameter `repeatTimes` must be <= (Integer.MAX_VALUE - 8), otherwise, the memory will overflow! ");
        }


    }

    @Test
    public void testVerifyMapKV() {
        try {
            O.verifyMapKV(false, false, false, null);
        } catch (Exception e) {
            System.out.println("01: " + e.getMessage());
            assertEquals("", e.getMessage());
        }
        try {
            O.verifyMapKV(false, false, false, (Object) null);
        } catch (Exception e) {
            System.out.println("02: " + e.getMessage());
            assertEquals("The parameters length must be even. ", e.getMessage());
        }
        try {
            O.verifyMapKV(false, false, false, (Object[]) null);
        } catch (Exception e) {
            System.out.println("03: " + e.getMessage());
            assertEquals("", e.getMessage());
        }
        try {
            O.verifyMapKV(false, false, false);
        } catch (Exception e) {
            System.out.println("04: " + e.getMessage());
            assertEquals("", e.getMessage());
        }
        try {
            O.verifyMapKV(false, false, false, 1);
        } catch (Exception e) {
            System.out.println("05: " + e.getMessage());
            assertEquals("The parameters length must be even. ", e.getMessage());
        }
        try {
            O.verifyMapKV(false, false, false, 1, 2);
        } catch (Exception e) {
            System.out.println("06: " + e.getMessage());
            assertEquals("", e.getMessage());
        }
        try {
            O.verifyMapKV(false, false, false, "key1", 2);
        } catch (Exception e) {
            System.out.println("07: " + e.getMessage());
            assertEquals("", e.getMessage());
        }
        try {
            O.verifyMapKV(false, false, false, "key1", "1");
        } catch (Exception e) {
            System.out.println("08: " + e.getMessage());
            assertEquals("", e.getMessage());
        }
        try {
            O.verifyMapKV(false, false, false, null, "1");
        } catch (Exception e) {
            System.out.println("09: " + e.getMessage());
            assertEquals("Index: 0. This parameter is a key, the key must be not null. ", e.getMessage());
        }
        try {
            O.verifyMapKV(false, false, false, null, "1", "key2", "2");
        } catch (Exception e) {
            System.out.println("10: " + e.getMessage());
            assertEquals("Index: 0. This parameter is a key, the key must be not null. ", e.getMessage());
        }
        try {
            O.verifyMapKV(false, false, false, "key1", "1", "key2", "2");
        } catch (Exception e) {
            System.out.println("11: " + e.getMessage());
            assertEquals("", e.getMessage());
        }
        try {
            O.verifyMapKV(false, false, false, "key1", "1", "key2", new Object(), new Object(), new Object());
        } catch (Exception e) {
            System.out.println("12: " + e.getMessage());
            assertEquals("", e.getMessage());
        }

        System.out.println("============================================================");
        System.out.println("============================================================");
        try {
            O.verifyMapKV(true, false, false, null);
        } catch (Exception e) {
            System.out.println("01: " + e.getMessage());
            assertEquals("", e.getMessage());
        }
        try {
            O.verifyMapKV(true, false, false, (Object) null);
        } catch (Exception e) {
            System.out.println("02: " + e.getMessage());
            assertEquals("The parameters length must be even. ", e.getMessage());
        }
        try {
            O.verifyMapKV(true, false, false, (Object[]) null);
        } catch (Exception e) {
            System.out.println("03: " + e.getMessage());
            assertEquals("", e.getMessage());
        }
        try {
            O.verifyMapKV(true, false, false);
        } catch (Exception e) {
            System.out.println("04: " + e.getMessage());
            assertEquals("", e.getMessage());
        }
        try {
            O.verifyMapKV(true, false, false, 1);
        } catch (Exception e) {
            System.out.println("05: " + e.getMessage());
            assertEquals("The parameters length must be even. ", e.getMessage());
        }
        try {
            O.verifyMapKV(true, false, false, 1, 2);
        } catch (Exception e) {
            System.out.println("06: " + e.getMessage());
            assertEquals("", e.getMessage());
        }
        try {
            O.verifyMapKV(true, false, false, "key1", 2);
        } catch (Exception e) {
            System.out.println("07: " + e.getMessage());
            assertEquals("", e.getMessage());
        }
        try {
            O.verifyMapKV(true, false, false, "key1", "1");
        } catch (Exception e) {
            System.out.println("08: " + e.getMessage());
            assertEquals("", e.getMessage());
        }
        try {
            O.verifyMapKV(true, false, false, null, "1");
        } catch (Exception e) {
            System.out.println("09: " + e.getMessage());
            assertEquals("", e.getMessage());
        }
        try {
            O.verifyMapKV(true, false, false, null, "1", "key2", "2");
        } catch (Exception e) {
            System.out.println("10: " + e.getMessage());
            assertEquals("", e.getMessage());
        }
        try {
            O.verifyMapKV(true, false, false, "key1", "1", "key2", "2");
        } catch (Exception e) {
            System.out.println("11: " + e.getMessage());
            assertEquals("", e.getMessage());
        }
        try {
            O.verifyMapKV(true, false, false, "key1", "1", "key2", new Object(), new Object(), new Object());
        } catch (Exception e) {
            System.out.println("12: " + e.getMessage());
            assertEquals("", e.getMessage());
        }

        System.out.println("============================================================");
        System.out.println("============================================================");
        try {
            O.verifyMapKV(false, true, false, null);
        } catch (Exception e) {
            System.out.println("01: " + e.getMessage());
            assertEquals("", e.getMessage());
        }
        try {
            O.verifyMapKV(false, true, false, (Object) null);
        } catch (Exception e) {
            System.out.println("02: " + e.getMessage());
            assertEquals("The parameters length must be even. ", e.getMessage());
        }
        try {
            O.verifyMapKV(false, true, false, (Object[]) null);
        } catch (Exception e) {
            System.out.println("03: " + e.getMessage());
            assertEquals("", e.getMessage());
        }
        try {
            O.verifyMapKV(false, true, false);
        } catch (Exception e) {
            System.out.println("04: " + e.getMessage());
            assertEquals("", e.getMessage());
        }
        try {
            O.verifyMapKV(false, true, false, 1);
        } catch (Exception e) {
            System.out.println("05: " + e.getMessage());
            assertEquals("The parameters length must be even. ", e.getMessage());
        }
        try {
            O.verifyMapKV(false, true, false, 1, 2);
        } catch (Exception e) {
            System.out.println("06: " + e.getMessage());
            assertEquals("Index: 0. This parameter is a key, the key must be `java.lang.String` type. ", e.getMessage());
        }
        try {
            O.verifyMapKV(false, true, false, "key1", 2);
        } catch (Exception e) {
            System.out.println("07: " + e.getMessage());
            assertEquals("", e.getMessage());
        }
        try {
            O.verifyMapKV(false, true, false, "key1", "1");
        } catch (Exception e) {
            System.out.println("08: " + e.getMessage());
            assertEquals("", e.getMessage());
        }
        try {
            O.verifyMapKV(false, true, false, null, "1");
        } catch (Exception e) {
            System.out.println("09: " + e.getMessage());
            assertEquals("Index: 0. This parameter is a key, the key must be not null. ", e.getMessage());
        }
        try {
            O.verifyMapKV(false, true, false, null, "1", "key2", "2");
        } catch (Exception e) {
            System.out.println("10: " + e.getMessage());
            assertEquals("Index: 0. This parameter is a key, the key must be not null. ", e.getMessage());
        }
        try {
            O.verifyMapKV(false, true, false, "key1", "1", "key2", "2");
        } catch (Exception e) {
            System.out.println("11: " + e.getMessage());
            assertEquals("", e.getMessage());
        }
        try {
            O.verifyMapKV(false, true, false, "key1", "1", "key2", new Object(), new Object(), new Object());
        } catch (Exception e) {
            System.out.println("12: " + e.getMessage());
            assertEquals("Index: 4. This parameter is a key, the key must be `java.lang.String` type. ", e.getMessage());
        }

        System.out.println("============================================================");
        System.out.println("============================================================");
        try {
            O.verifyMapKV(false, false, true, null);
        } catch (Exception e) {
            System.out.println("01: " + e.getMessage());
            assertEquals("", e.getMessage());
        }
        try {
            O.verifyMapKV(false, false, true, (Object) null);
        } catch (Exception e) {
            System.out.println("02: " + e.getMessage());
            assertEquals("The parameters length must be even. ", e.getMessage());
        }
        try {
            O.verifyMapKV(false, false, true, (Object[]) null);
        } catch (Exception e) {
            System.out.println("03: " + e.getMessage());
            assertEquals("", e.getMessage());
        }
        try {
            O.verifyMapKV(false, false, true);
        } catch (Exception e) {
            System.out.println("04: " + e.getMessage());
            assertEquals("", e.getMessage());
        }
        try {
            O.verifyMapKV(false, false, true, 1);
        } catch (Exception e) {
            System.out.println("05: " + e.getMessage());
            assertEquals("The parameters length must be even. ", e.getMessage());
        }
        try {
            O.verifyMapKV(false, false, true, 1, 2);
        } catch (Exception e) {
            System.out.println("06: " + e.getMessage());
            assertEquals("Index: 1. This parameter is a value, the value must be `java.lang.String` type. ", e.getMessage());
        }
        try {
            O.verifyMapKV(false, false, true, "key1", 2);
        } catch (Exception e) {
            System.out.println("07: " + e.getMessage());
            assertEquals("Index: 1. This parameter is a value, the value must be `java.lang.String` type. ", e.getMessage());
        }
        try {
            O.verifyMapKV(false, false, true, "key1", "1");
        } catch (Exception e) {
            System.out.println("08: " + e.getMessage());
            assertEquals("", e.getMessage());
        }
        try {
            O.verifyMapKV(false, false, true, null, "1");
        } catch (Exception e) {
            System.out.println("09: " + e.getMessage());
            assertEquals("Index: 0. This parameter is a key, the key must be not null. ", e.getMessage());
        }
        try {
            O.verifyMapKV(false, false, true, null, "1", "key2", "2");
        } catch (Exception e) {
            System.out.println("10: " + e.getMessage());
            assertEquals("Index: 0. This parameter is a key, the key must be not null. ", e.getMessage());
        }
        try {
            O.verifyMapKV(false, false, true, "key1", "1", "key2", "2");
        } catch (Exception e) {
            System.out.println("11: " + e.getMessage());
            assertEquals("", e.getMessage());
        }
        try {
            O.verifyMapKV(false, false, true, "key1", "1", "key2", new Object(), new Object(), new Object());
        } catch (Exception e) {
            System.out.println("12: " + e.getMessage());
            assertEquals("Index: 3. This parameter is a value, the value must be `java.lang.String` type. ", e.getMessage());
        }

    }

    @Test
    public void testStackTraceSimple() {
        try {
            divideByZero();
        } catch (Throwable e) {
            try {
                throw new RuntimeException("发生了运行时异常！", e);
            } catch (Exception e1) {
                try {
                    throw new UnexpectedParameterException(e1);
                } catch (Throwable e2) {
                    System.out.println("==============================1==============================");
                    String stackTrace1 = G.stackTraceSimple(e2, null, "com.iofairy", "java.util");
                    System.out.println(stackTrace1);
                    Try.sleep(50);
                    System.out.println("==============================2==============================");
                    String stackTrace2 = G.stackTraceSimple(e2, null, null);
                    System.out.println(stackTrace2);
                    Try.sleep(50);
                    System.out.println("==============================3==============================");
                    String stackTrace3 = G.stackTraceSimple(e2, "com.iofairy", null);
                    System.out.println(stackTrace3);
                    Try.sleep(50);
                    System.out.println("==============================4==============================");
                    String stackTrace4 = G.stackTraceSimple(e2, null);
                    System.out.println(stackTrace4);

                    System.out.println("============================================================");
                    System.out.println("============================================================");
                    System.out.println("==============================1==============================");
                    stackTrace1 = G.stackTraceConcise(e2, null, "com.iofairy", "java.util");
                    System.out.println(stackTrace1);
                    Try.sleep(50);
                    System.out.println("==============================2==============================");
                    stackTrace2 = G.stackTraceConcise(e2, null, null);
                    System.out.println(stackTrace2);
                    Try.sleep(50);
                    System.out.println("==============================3==============================");
                    stackTrace3 = G.stackTraceConcise(e2, "com.iofairy", null);
                    System.out.println(stackTrace3);
                    Try.sleep(50);
                    System.out.println("==============================4==============================");
                    stackTrace4 = G.stackTraceConcise(e2, null);
                    System.out.println(stackTrace4);
                }
            }
        }

    }

    private void divideByZero() {
        int result = 5 / 0; // 这里会抛出 ArithmeticException
    }


    @Test
    public void testArgs() {
        Object o = null;
        String s = null;

        CharSequence[] args1 = O.args(s);
        Object[] args2 = args1;
        Object[] args3 = O.args(o);
        CharSequence[] args4 = O.args(null);
        Object[] args5 = O.args(null, 1);
        Object[] args6 = O.args(null, 1, "abc");
        String[] args7 = O.args(null, "a", "b");
        CharSequence[] args8 = args7;

        System.out.println(G.toString(args1));
        System.out.println(G.toString(args2));
        System.out.println(G.toString(args3));
        System.out.println(G.toString(args4));
        System.out.println(G.toString(args5));
        System.out.println(G.toString(args6));
        System.out.println(G.toString(args7));
        System.out.println(G.toString(args8));

        assertEquals(G.toString(args1), "[null]");
        assertEquals(G.toString(args2), "[null]");
        assertEquals(G.toString(args3), "[null]");
        assertEquals(G.toString(args4), "null");
        assertEquals(G.toString(args5), "[null, 1]");
        assertEquals(G.toString(args6), "[null, 1, \"abc\"]");
        assertEquals(G.toString(args7), "[null, \"a\", \"b\"]");
        assertEquals(G.toString(args8), "[null, \"a\", \"b\"]");

    }

    @Test
    public void testIfElse() {
        int i = 10;
        String s1 = "";
        String s2 = "abcd";
        String s5 = "abcd1";
        String s3 = null;
        Object o1 = new Object();
        Integer i1 = null;
        String s4 = "";
        String[] nullSs = null;
        String[] ss1 = new String[]{};
        String[] ss2 = new String[]{""};
        Object[] os1 = {'a', "1"};
        List<String> list = Arrays.asList("a", "b");
        Map<Object, Object> map = Map.of();

        String valueIfBlank1 = O.valueIfBlank(s1, "aaa");
        String valueIfBlank2 = O.valueIfBlank(s2, "aaa");
        Integer valueIfEmpty1 = O.valueIfEmpty(i, 20);
        String[] valueIfEmpty2 = O.valueIfEmpty(ss1, new String[]{"1"});
        Integer valueIfNull1 = O.valueIfNull(i1, 20);
        String[] valueIfNull2 = O.valueIfNull(ss1, new String[]{"1"});
        Integer valueIfElse1 = O.valueIfElse(os1.length == 2, 1, 2);
        String valueIfElse2 = O.valueIfElse(ss2.length == 2, "1", "2");

        System.out.println(valueIfBlank1);
        System.out.println(valueIfBlank2);
        System.out.println(valueIfEmpty1);
        System.out.println(G.toString(valueIfEmpty2));
        System.out.println(valueIfNull1);
        System.out.println(G.toString(valueIfNull2));
        System.out.println(valueIfElse1);
        System.out.println(valueIfElse2);

        assertEquals("aaa", valueIfBlank1);
        assertEquals("abcd", valueIfBlank2);
        assertEquals(10, valueIfEmpty1);
        assertEquals("[\"1\"]", G.toString(valueIfEmpty2));
        assertEquals(20, valueIfNull1);
        assertEquals("[]", G.toString(valueIfNull2));
        assertEquals(2, valueIfElse1);
        assertEquals("1", valueIfElse2);

    }
}
