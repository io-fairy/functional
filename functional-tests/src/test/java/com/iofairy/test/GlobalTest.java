package com.iofairy.test;

import com.iofairy.top.G;
import com.iofairy.tuple.Tuple2;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Serializable;
import java.time.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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
        assertTrue(G.hasEmpty());
        assertFalse(G.allNull());
        assertTrue(G.allEmpty());
        assertFalse(G.hasNull(i, o1, s1, s2));
        assertTrue(G.hasNull(i, o1, s1, s3));
        assertFalse(G.hasEmpty(s2, s5));
        assertTrue(G.hasEmpty(s2, s5, s3));
        assertTrue(G.hasEmpty(s2, s5, s1));
        assertTrue(G.hasEmpty(ss1));
        assertTrue(G.hasEmpty(ss2));
        assertFalse(G.allNull(i, s1, s3, i1));
        assertTrue(G.allNull(s3, o2, i1));
        assertFalse(G.allNull(os));
        assertFalse(G.allNull(os1));
        assertFalse(G.allEmpty(s1, s2, s3));
        assertTrue(G.allEmpty(s1, s3, s4));
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
    public void testBlank() {

        String s = "  \n \t　　　\f  ";
        String s1 = "  \n \t　　a　\f  ";
        String s2 = "  \n\n　　\f  ";
        String s3 = "  aaaa　\f  ";
        String s4 = null;
        String s5 = "";
        String[] nullSs = null;
        String[] nullSs1 = new String[]{};

        assertTrue(G.isBlank(s));
        assertFalse(G.isBlank(s1));
        assertTrue(G.isBlank(s2));
        assertTrue(G.hasBlank(s, s1));
        assertTrue(G.hasBlank(s4, s5));
        assertFalse(G.hasBlank(s1, s3));
        assertTrue(G.hasBlank(nullSs));
        assertTrue(G.hasBlank(nullSs1));
        assertTrue(G.allBlank(s, s2, s4, s5));
        assertFalse(G.allBlank(s, s1, s4, s5));
        assertTrue(G.allBlank(nullSs));
        assertTrue(G.allBlank(nullSs1));

    }

    @Test
    public void testFirstNonNull() {
        String a = null;
        String b = "";
        String c = null;
        String d = "abc";
        Object o = new Object();

        String s = G.firstNonNull(a, b, c);
        String s1 = G.firstNonNull(a, c);
        String s2 = G.firstNonNull();
        String s3 = G.firstNonNull((String[]) null);
        String s4 = G.firstNonNull(a, d, c);
        Object o1 = G.firstNonNull(a, d, c, o);

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
    public void testSplitOnce() {
        Tuple2<String, String> t00 = G.splitOnce(":", ": ");
        Tuple2<String, String> t01 = G.splitOnce(null, ": ");
        Tuple2<String, String> t02 = G.splitOnce(": ", null);
        Tuple2<String, String> t03 = G.splitOnce(": ", ": ");
        Tuple2<String, String> t04 = G.splitOnce(": 1", ": ");
        Tuple2<String, String> t05 = G.splitOnce("a: ", ": ");
        Tuple2<String, String> t06 = G.splitOnce("a: 1", ": ");
        Tuple2<String, String> t07 = G.splitOnce(" :  ", ": ");
        Tuple2<String, String> t08 = G.splitOnce(" : : ", ": ");
        Tuple2<String, String> t09 = G.splitOnce(" :abc ", ": ");
        Tuple2<String, String> t10 = G.splitOnce("", "");
        Tuple2<String, String> t11 = G.splitOnce(null, null);

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
        assertEquals("2022-01-01 10:05:05.987654789 [Asia/Shanghai +08:00 GMT+8 周六]", dtDetail2);
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
        assertEquals("2022-01-01 23:05:05.987654789 [Asia/Shanghai +08:00 GMT+8 周六]", dtDetail2);
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
    public void testArrayEmpty() {
        CharSequence[] cs1 = G.array0(CharSequence[].class);
        CharSequence[] cs2 = G.arrayO(CharSequence.class);
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
        char[][] cs = G.arrayO(char[].class);
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
    public void testSwapNullEmptyBlank() {
        String s0 = null;
        String s1 = "";
        String s2 = "　 \r\t\n  ";
        String s3 = "abcdefg";

        String nullToEmpty0 = G.nullToEmpty(s0);
        String nullToEmpty1 = G.nullToEmpty(s1);
        String nullToEmpty2 = G.nullToEmpty(s2);
        String nullToEmpty3 = G.nullToEmpty(s3);
        assertEquals("", nullToEmpty0);
        assertEquals("", nullToEmpty1);
        assertEquals(s2, nullToEmpty2);
        assertEquals(s3, nullToEmpty3);

        String emptyToNull0 = G.emptyToNull(s0);
        String emptyToNull1 = G.emptyToNull(s1);
        String emptyToNull2 = G.emptyToNull(s2);
        String emptyToNull3 = G.emptyToNull(s3);
        assertNull(emptyToNull0);
        assertNull(emptyToNull1);
        assertEquals(s2, emptyToNull2);
        assertEquals(s3, emptyToNull3);

        String blankToNull0 = G.blankToNull(s0);
        String blankToNull1 = G.blankToNull(s1);
        String blankToNull2 = G.blankToNull(s2);
        String blankToNull3 = G.blankToNull(s3);
        assertNull(blankToNull0);
        assertNull(blankToNull1);
        assertNull(blankToNull2);
        assertEquals(s3, blankToNull3);

        String blankToEmpty0 = G.blankToEmpty(s0);
        String blankToEmpty1 = G.blankToEmpty(s1);
        String blankToEmpty2 = G.blankToEmpty(s2);
        String blankToEmpty3 = G.blankToEmpty(s3);
        assertEquals("", blankToEmpty0);
        assertEquals("", blankToEmpty1);
        assertEquals("", blankToEmpty2);
        assertEquals(s3, blankToEmpty3);

    }
}
