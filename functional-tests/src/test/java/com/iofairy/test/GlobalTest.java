package com.iofairy.test;

import com.iofairy.except.UnexpectedParameterException;
import com.iofairy.range.Range;
import com.iofairy.tcf.Try;
import com.iofairy.top.*;
import com.iofairy.top.base.PaddingStrategy;
import com.iofairy.tuple.Tuple2;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
        assertTrue(G.hasEmpty("", ss1, ss2, map));
        assertTrue(G.hasEmpty(Arrays.asList("", ss1, ss2, map)));
        assertTrue(G.hasEmpty((String[]) null));
        assertTrue(G.hasEmpty((Collection<?>) null));
        assertFalse(G.allEmpty("", ss1, ss2, map));
        assertFalse(G.allEmpty(Arrays.asList("", ss1, ss2, map)));
        assertTrue(G.allEmpty("", ss1, nullSs, map));
        assertTrue(G.allEmpty(Arrays.asList("", ss1, nullSs, map)));
        assertTrue(G.allEmpty((String[]) null));
        assertTrue(G.allEmpty((Collection<?>) null));
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
        String s5 = O.firstNonValue(null, null, "a", null, "b");
        String s6 = O.firstNonValue("a", null, "a", null, "b");
        Tuple2<String, Integer> s7 = O.firstNonValueWithIndex(null, null, "a", null, "b");
        Tuple2<String, Integer> s8 = O.firstNonValueWithIndex("a", null, "a", null, "b");
        String s9 = O.firstNotInValues(O.args(), "abc", "a", null, "b");
        String s10 = O.firstNotInValues(null, "c", "a", null, "b");
        Tuple2<String, Integer> s11 = O.firstNotInValuesWithIndex(O.args(), "abc", "a", null, "b");
        Tuple2<String, Integer> s12 = O.firstNotInValuesWithIndex(null, "c", "a", null, "b");
        Tuple2<String, Integer> s13 = O.firstNotInValuesWithIndex(O.args());
        Tuple2<String, Integer> s14 = O.firstNotInValuesWithIndex(null);
        String s15 = O.firstNotInValues(O.args((String) null), null, "abc", "a", null, "b");
        String s16 = O.firstNotInValues(O.args(null, "abc"), "abc", "abc", null, "a", null, "b");
        Tuple2<String, Integer> s17 = O.firstNotInValuesWithIndex(O.args(null), null, "abc", "a", null, "b");
        Tuple2<String, Integer> s18 = O.firstNotInValuesWithIndex(O.args(null, "abc"), "abc", "abc", null, "a", null, "b");


        assertEquals("", s);
        assertNull(s1);
        assertNull(s2);
        assertNull(s3);
        assertEquals("abc", s4);
        assertEquals("abc", o1);
        assertEquals(s5, "a");
        assertNull(s6);
        assertEquals(s7.toString(), "(\"a\", 1)");
        assertEquals(s8.toString(), "(null, 0)");
        assertEquals(s9.toString(), "abc");
        assertEquals(s10, "c");
        assertEquals(s11.toString(), "(\"abc\", 0)");
        assertEquals(s12.toString(), "(\"c\", 0)");
        assertNull(s13);
        assertNull(s14);
        assertEquals(s15, "abc");
        assertEquals(s16, "a");
        assertEquals(s17.toString(), "(null, 0)");
        assertEquals(s18.toString(), "(\"a\", 3)");

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

        ZonedDateTime zonedDateTime1 = ZonedDateTime.of(995, 3, 1, 8, 5, 13, 987654789, ZoneId.systemDefault());
        Date date1 = Date.from(zonedDateTime1.toInstant());
        ZonedDateTime zonedDateTime2 = ZonedDateTime.of(15093, 6, 1, 10, 5, 5, 987654789, ZoneId.systemDefault());
        Date date2 = Date.from(zonedDateTime2.toInstant());

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
        String toString6 = G.toString(zonedDateTime1);
        String dtDetail6 = G.dtDetail(zonedDateTime1);
        String toString7 = G.toString(zonedDateTime2);
        String dtDetail7 = G.dtDetail(zonedDateTime2);
        String toString8 = G.toString(date1);
        String dtDetail8 = G.dtDetail(date1);
        String toString9 = G.toString(date2);
        String dtDetail9 = G.dtDetail(date2);

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

        assertEquals(toString6, "995-03-01 08:05:13.987");
        assertEquals(dtDetail6, "995-03-01 08:05:13.987654789 [Asia/Shanghai +08:05 GMT+8:05:43 周日]");
        assertEquals(toString7, "15093-06-01 10:05:05.987");
        assertEquals(dtDetail7, "15093-06-01 10:05:05.987654789 [Asia/Shanghai +08:00 GMT+8 周四]");
        assertEquals(toString8, "995-03-01 08:05:13.987");
        assertEquals(dtDetail8, "995-03-01 08:05:13.987000000 [Asia/Shanghai +08:05 GMT+8:05:43 周日]");
        assertEquals(toString9, "15093-06-01 10:05:05.987");
        assertEquals(dtDetail9, "15093-06-01 10:05:05.987000000 [Asia/Shanghai +08:00 GMT+8 周四]");
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

        System.out.println(toString6);
        System.out.println(dtDetail6);
        System.out.println(toString7);
        System.out.println(dtDetail7);
        System.out.println(toString8);
        System.out.println(dtDetail8);
        System.out.println(toString9);
        System.out.println(dtDetail9);
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
    public void testPadBothSidesChars() {
        String s0 = null;
        String s1 = "";
        String s2 = "abcd";
        float f = 10.10f;

        String s00 = S.padBothSidesChars(s0, '0', 0, true);
        String s01 = S.padBothSidesChars(s0, '#', 3, true);
        String s02 = S.padBothSidesChars(s0, '0', 5, true);
        String s03 = S.padBothSidesChars(s0, '0', 5, false);
        String s04 = S.padBothSidesChars(s0, '0', 6, true);
        String s05 = S.padBothSidesChars(s0, '0', 6, false);
        String s06 = S.padBothSidesChars(s1, '0', 0, false);
        String s07 = S.padBothSidesChars(s1, '#', 3, false);
        String s08 = S.padBothSidesChars(s1, '0', 5, true);
        String s09 = S.padBothSidesChars(s1, '0', 5, false);
        String s10 = S.padBothSidesChars(s1, '0', 6, true);
        String s11 = S.padBothSidesChars(s1, '0', 6, false);
        String s12 = S.padBothSidesChars(s2, '0', 0, true);
        String s13 = S.padBothSidesChars(s2, '#', 3, true);
        String s14 = S.padBothSidesChars(s2, '0', 5, true);
        String s15 = S.padBothSidesChars(s2, '0', 5, false);
        String s16 = S.padBothSidesChars(s2, '0', 6, true);
        String s17 = S.padBothSidesChars(s2, '0', 6, false);
        String f01 = S.padBothSidesChars(f, '0', 0, true);
        String f02 = S.padBothSidesChars(f, '0', 0, false);
        String f03 = S.padBothSidesChars(f, '0', 1, true);
        String f04 = S.padBothSidesChars(f, '0', 1, false);
        String f05 = S.padBothSidesChars(f, '#', 3, true);
        String f06 = S.padBothSidesChars(f, '#', 3, false);
        String f07 = S.padBothSidesChars(f, ' ', 9, true);
        String f08 = S.padBothSidesChars(f, ' ', 9, false);
        String f09 = S.padBothSidesChars(f, '#', 10, true);
        String f10 = S.padBothSidesChars(f, '#', 10, false);
        String f11 = S.padChars(f, '#', 9, PaddingStrategy.LEFT);
        String f12 = S.padChars(f, '#', 9, PaddingStrategy.RIGHT);
        String f13 = S.padChars(f, '#', 9, PaddingStrategy.LEFT_MORE);
        String f14 = S.padChars(f, '#', 9, PaddingStrategy.RIGHT_MORE);
        String f15 = S.padChars(f, '#', 10, PaddingStrategy.LEFT);
        String f16 = S.padChars(f, '#', 10, PaddingStrategy.RIGHT);
        String f17 = S.padChars(f, '#', 10, PaddingStrategy.LEFT_MORE);
        String f18 = S.padChars(f, '#', 10, PaddingStrategy.RIGHT_MORE);


        System.out.println("s00: " + s00);
        System.out.println("s01: " + s01);
        System.out.println("s02: " + s02);
        System.out.println("s03: " + s03);
        System.out.println("s04: " + s04);
        System.out.println("s05: " + s05);
        System.out.println("s06: " + s06);
        System.out.println("s07: " + s07);
        System.out.println("s08: " + s08);
        System.out.println("s09: " + s09);
        System.out.println("s10: " + s10);
        System.out.println("s11: " + s11);
        System.out.println("s12: " + s12);
        System.out.println("s13: " + s13);
        System.out.println("s14: " + s14);
        System.out.println("s15: " + s15);
        System.out.println("s16: " + s16);
        System.out.println("s17: " + s17);
        System.out.println("f01: " + f01);
        System.out.println("f02: " + f02);
        System.out.println("f03: " + f03);
        System.out.println("f04: " + f04);
        System.out.println("f05: " + f05);
        System.out.println("f06: " + f06);
        System.out.println("f07: " + f07);
        System.out.println("f08: " + f08);
        System.out.println("f09: " + f09);
        System.out.println("f10: " + f10);
        System.out.println("f11: " + f11);
        System.out.println("f12: " + f12);
        System.out.println("f13: " + f13);
        System.out.println("f14: " + f14);
        System.out.println("f15: " + f15);
        System.out.println("f16: " + f16);
        System.out.println("f17: " + f17);
        System.out.println("f18: " + f18);

        assertNull(s00);
        assertNull(s01);
        assertNull(s02);
        assertNull(s03);
        assertNull(s04);
        assertNull(s05);
        assertEquals(s06, "");
        assertEquals(s07, "###");
        assertEquals(s08, "00000");
        assertEquals(s09, "00000");
        assertEquals(s10, "000000");
        assertEquals(s11, "000000");
        assertEquals(s12, "abcd");
        assertEquals(s13, "abcd");
        assertEquals(s14, "0abcd");
        assertEquals(s15, "abcd0");
        assertEquals(s16, "0abcd0");
        assertEquals(s17, "0abcd0");
        assertEquals(f01, "10.1");
        assertEquals(f02, "10.1");
        assertEquals(f03, "10.1");
        assertEquals(f04, "10.1");
        assertEquals(f05, "10.1");
        assertEquals(f06, "10.1");
        assertEquals(f07, "   10.1  ");
        assertEquals(f08, "  10.1   ");
        assertEquals(f09, "###10.1###");
        assertEquals(f10, "###10.1###");
        assertEquals(f11, "#####10.1");
        assertEquals(f12, "10.1#####");
        assertEquals(f13, "###10.1##");
        assertEquals(f14, "##10.1###");
        assertEquals(f15, "######10.1");
        assertEquals(f16, "10.1######");
        assertEquals(f17, "###10.1###");
        assertEquals(f18, "###10.1###");

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
    public void testIndexOfRegex() {
        assertThrows(NullPointerException.class, () -> S.indexOfRegex(null, "", 1));
        assertThrows(NullPointerException.class, () -> S.indexOfRegex("", (String) null, 1));
        assertThrows(UnexpectedParameterException.class, () -> S.indexOfRegex("", "", 0));

        String source = "aaa   bbb ccc ddd     eee   ";
        Range<Integer> t01 = S.indexOfRegex(source, "", 3);
        Range<Integer> t02 = S.indexOfRegex(source, "\\s+", 1);
        Range<Integer> t03 = S.indexOfRegex(source, "\\s+", 2);
        Range<Integer> t04 = S.indexOfRegex(source, "\\s+", 3);
        Range<Integer> t05 = S.indexOfRegex(source, "\\s+", 4);
        Range<Integer> t06 = S.indexOfRegex(source, "\\s+", 6);
        Range<Integer> t07 = S.indexOfRegex(source, "\\s+", 7);

        System.out.println(t01);
        System.out.println(t02);
        System.out.println(t03);
        System.out.println(t04);
        System.out.println(t05);
        System.out.println(t06);
        System.out.println(t07);

        assertEquals(t01.toString(), "[2, 2)");
        assertEquals(t02.toString(), "[3, 6)");
        assertEquals(t03.toString(), "[9, 10)");
        assertEquals(t04.toString(), "[13, 14)");
        assertEquals(t05.toString(), "[17, 22)");
        assertEquals(t06.toString(), "(-1, -1)");
        assertEquals(t07.toString(), "(-1, -1)");

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
        Tuple2<String, String> t12 = S.splitOnce("a b c d  ", " ", 2);
        Tuple2<String, String> t13 = S.splitOnce("a b c d  ", " ", 5);
        Tuple2<String, String> t14 = S.splitOnce("a b c d  ", " ", 6);
        assertThrows(UnexpectedParameterException.class, () -> S.splitOnce("a b c d  ", " ", 0));

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
        assertEquals(t12._1 + "||" + t12._2, "a b||c d  ");
        assertEquals(t13._1 + "||" + t13._2, "a b c d ||");
        assertEquals(t14._1 + "||" + t14._2, "a b c d  ||null");

    }

    @Test
    public void testSplitOnceByRegex() {
        Tuple2<String, String> t1 = S.splitOnceByRegex(null, "", 1);
        Tuple2<String, String> t2 = S.splitOnceByRegex("", (String) null, 1);
        Tuple2<String, String> t3 = S.splitOnceByRegex("", "", 0);
        Tuple2<String, String> t4 = S.splitOnceByRegex("abc", "", 1);
        Tuple2<String, String> t5 = S.splitOnceByRegex("abc", "", 2);

        String source = "aaa   bbb ccc ddd     eee   ";
        assertThrows(UnexpectedParameterException.class, () -> S.splitOnceByRegex(source, "\\s+", 0));
        Tuple2<String, String> t01 = S.splitOnceByRegex(source, "\\s+", 1);
        Tuple2<String, String> t02 = S.splitOnceByRegex(source, "\\s+", 2);
        Tuple2<String, String> t03 = S.splitOnceByRegex(source, "\\s+", 3);
        Tuple2<String, String> t04 = S.splitOnceByRegex(source, "\\s+", 4);
        Tuple2<String, String> t05 = S.splitOnceByRegex(source, "\\s+", 5);
        Tuple2<String, String> t06 = S.splitOnceByRegex(source, "\\s+", 6);
        Tuple2<String, String> t07 = S.splitOnceByRegex(source, "\\s+", 7);


        System.out.println(t01);
        System.out.println(t02);
        System.out.println(t03);
        System.out.println(t04);
        System.out.println(t05);
        System.out.println(t06);
        System.out.println(t07);

        assertEquals(t1._1 + ", " + t1._2, "null, null");
        assertEquals(t2._1 + ", " + t2._2, ", null");
        assertEquals(t3._1 + ", " + t3._2, ", null");
        assertEquals(t4._1 + ", " + t4._2, ", abc");
        assertEquals(t5._1 + ", " + t5._2, "a, bc");

        assertEquals(t01._1 + ", " + t01._2, "aaa, bbb ccc ddd     eee   ");
        assertEquals(t02._1 + ", " + t02._2, "aaa   bbb, ccc ddd     eee   ");
        assertEquals(t03._1 + ", " + t03._2, "aaa   bbb ccc, ddd     eee   ");
        assertEquals(t04._1 + ", " + t04._2, "aaa   bbb ccc ddd, eee   ");
        assertEquals(t05._1 + ", " + t05._2, "aaa   bbb ccc ddd     eee, ");
        assertEquals(t06._1 + ", " + t06._2, "aaa   bbb ccc ddd     eee   , null");
        assertEquals(t07._1 + ", " + t07._2, "aaa   bbb ccc ddd     eee   , null");

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

    @Test
    public void testEquals() {
        Byte b = null;
        Short s0 = null;
        byte b1 = 1;
        Short s = 1;
        short s1 = 1;
        Integer i = 1;
        Integer i1 = 0;
        BigInteger bigInteger = new BigInteger("1");
        double d1 = -0.0d;
        double d2 = +0.0d;
        Float f = -0.0f;
        Double d4 = +0.0d;
        double d5 = 1008.230953d;
        BigDecimal bigDecimal = new BigDecimal("1008.230953");
        long l = 5669887456526599999L;
        BigInteger bigInteger1 = new BigInteger("5669887456526599999");
        BigInteger bigInteger2 = new BigInteger("89746549865465465415465498448888335433878565656364585658");
        BigInteger bigInteger3 = new BigInteger("89746549865465465415465498448888335433878565656364585658");
        float f0 = Float.NaN;
        Double d6 = Double.NaN;
        Double d7 = Double.POSITIVE_INFINITY;
        Double d8 = Double.NEGATIVE_INFINITY;
        Float f1 = Float.POSITIVE_INFINITY;
        Float f2 = Float.NEGATIVE_INFINITY;

        String s01 = G.toString(b) + ", " + G.toString(b1) + "---" + Objects.equals(b, b1) + "---" + O.equals(b, b1);
        String s02 = G.toString(b) + ", " + G.toString(d1) + "---" + Objects.equals(b, d1) + "---" + O.equals(b, d1);
        String s03 = G.toString(b) + ", " + G.toString(s0) + "---" + Objects.equals(b, s0) + "---" + O.equals(b, s0);
        String s04 = G.toString(s) + ", " + G.toString(s1) + "---" + Objects.equals(s, s1) + "---" + O.equals(s, s1);
        String s05 = G.toString(s) + ", " + G.toString(i) + "---" + Objects.equals(s, i) + "---" + O.equals(s, i);
        String s06 = G.toString(b1) + ", " + G.toString(s1) + "---" + Objects.equals(b1, s1) + "---" + O.equals(b1, s1);
        String s07 = G.toString(b1) + ", " + G.toString(bigInteger) + "---" + Objects.equals(b1, bigInteger) + "---" + O.equals(b1, bigInteger);
        String s08 = G.toString(s1) + ", " + G.toString(bigInteger) + "---" + Objects.equals(s1, bigInteger) + "---" + O.equals(s1, bigInteger);
        String s09 = G.toString(i1) + ", " + G.toString(d1) + "---" + Objects.equals(i1, d1) + "---" + O.equals(i1, d1);
        String s10 = G.toString(d1) + ", " + G.toString(d2) + "---" + Objects.equals(d1, d2) + "---" + O.equals(d1, d2);
        String s11 = G.toString(d1) + ", " + G.toString(f) + "---" + Objects.equals(d1, f) + "---" + O.equals(d1, f);
        String s12 = G.toString(f) + ", " + G.toString(d4) + "---" + Objects.equals(f, d4) + "---" + O.equals(f, d4);
        String s13 = G.toString(f) + ", " + G.toString(d5) + "---" + Objects.equals(f, d5) + "---" + O.equals(f, d5);
        String s14 = G.toString(bigDecimal) + ", " + G.toString(d5) + "---" + Objects.equals(bigDecimal, d5) + "---" + O.equals(bigDecimal, d5);
        String s15 = G.toString(l) + ", " + G.toString(bigInteger1) + "---" + Objects.equals(l, bigInteger1) + "---" + O.equals(l, bigInteger1);
        String s16 = G.toString(bigInteger2) + ", " + G.toString(bigInteger3) + "---" + Objects.equals(bigInteger2, bigInteger3) + "---" + O.equals(bigInteger2, bigInteger3);
        String s17 = G.toString(f0) + ", " + G.toString(d6) + "---" + Objects.equals(f0, d6) + "---" + O.equals(f0, d6);
        String s18 = G.toString(d7) + ", " + G.toString(f1) + "---" + Objects.equals(d7, f1) + "---" + O.equals(d7, f1);
        String s19 = G.toString(d8) + ", " + G.toString(f2) + "---" + Objects.equals(d8, f2) + "---" + O.equals(d8, f2);

        System.out.println("01、 " + s01);
        System.out.println("02、 " + s02);
        System.out.println("03、 " + s03);
        System.out.println("04、 " + s04);
        System.out.println("05、 " + s05);
        System.out.println("06、 " + s06);
        System.out.println("07、 " + s07);
        System.out.println("08、 " + s08);
        System.out.println("09、 " + s09);
        System.out.println("10、 " + s10);
        System.out.println("11、 " + s11);
        System.out.println("12、 " + s12);
        System.out.println("13、 " + s13);
        System.out.println("14、 " + s14);
        System.out.println("15、 " + s15);
        System.out.println("16、 " + s16);
        System.out.println("17、 " + s17);
        System.out.println("18、 " + s18);
        System.out.println("19、 " + s19);

        assertEquals(s01, "null, 1---false---false");
        assertEquals(s02, "null, 0.0---false---false");
        assertEquals(s03, "null, null---true---true");
        assertEquals(s04, "1, 1---true---true");
        assertEquals(s05, "1, 1---false---true");
        assertEquals(s06, "1, 1---false---true");
        assertEquals(s07, "1, 1---false---true");
        assertEquals(s08, "1, 1---false---true");
        assertEquals(s09, "0, 0.0---false---false");
        assertEquals(s10, "0.0, 0.0---false---true");
        assertEquals(s11, "0.0, 0.0---false---true");
        assertEquals(s12, "0.0, 0.0---false---true");
        assertEquals(s13, "0.0, 1008.230953---false---false");
        assertEquals(s14, "1008.230953, 1008.230953---false---true");
        assertEquals(s15, "5669887456526599999, 5669887456526599999---false---true");
        assertEquals(s16, "89746549865465465415465498448888335433878565656364585658, 89746549865465465415465498448888335433878565656364585658---true---true");
        assertEquals(s17, "NaN, NaN---false---true");
        assertEquals(s18, "∞, ∞---false---true");
        assertEquals(s19, "-∞, -∞---false---true");

    }

    @Test
    public void testEqualsAny() {
        boolean equalsAny01 = O.equalsAny(null, null);                              // false
        boolean equalsAny02 = O.equalsAny(null, (Object[]) null);                   // false
        boolean equalsAny03 = O.equalsAny(null, (Object) null);                     // true
        boolean equalsAny04 = O.equalsAny(null, null, null);                        // true
        boolean equalsAny05 = O.equalsAny(null, new Object[]{});                    // false
        boolean equalsAny06 = O.equalsAny(null, new Object[]{null});                // true
        boolean equalsAny07 = O.equalsAny(new BigInteger("5669887456526599999"), 2, 3, 5669887456526599999L);   // true
        boolean equalsAny08 = O.equalsAny(1, 2, 3, 1.0f);                           // false
        boolean equalsAny09 = O.equalsAny("ab", 2, "", 1.0f);                       // false
        boolean equalsAny10 = O.equalsAny("ab", 2, "ab", 1.0f);                     // true
        boolean equalsAny11 = O.equalsAny("ab", 2, "aB", 1.0f);                     // false
        boolean equalsAny12 = O.equalsAny(new BigDecimal("1.8960"), 2, 3, 1.896f);  // true

        System.out.println("equalsAny01: " + equalsAny01);          // equalsAny01: false
        System.out.println("equalsAny02: " + equalsAny02);          // equalsAny02: false
        System.out.println("equalsAny03: " + equalsAny03);          // equalsAny03: true
        System.out.println("equalsAny04: " + equalsAny04);          // equalsAny04: true
        System.out.println("equalsAny05: " + equalsAny05);          // equalsAny05: false
        System.out.println("equalsAny06: " + equalsAny06);          // equalsAny06: true
        System.out.println("equalsAny07: " + equalsAny07);          // equalsAny07: true
        System.out.println("equalsAny08: " + equalsAny08);          // equalsAny08: false
        System.out.println("equalsAny09: " + equalsAny09);          // equalsAny09: false
        System.out.println("equalsAny10: " + equalsAny10);          // equalsAny10: true
        System.out.println("equalsAny11: " + equalsAny11);          // equalsAny11: false
        System.out.println("equalsAny12: " + equalsAny12);          // equalsAny12: true

        assertFalse(equalsAny01);
        assertFalse(equalsAny02);
        assertTrue(equalsAny03);
        assertTrue(equalsAny04);
        assertFalse(equalsAny05);
        assertTrue(equalsAny06);
        assertTrue(equalsAny07);
        assertFalse(equalsAny08);
        assertFalse(equalsAny09);
        assertTrue(equalsAny10);
        assertFalse(equalsAny11);
        assertTrue(equalsAny12);
    }

    @Test
    public void testIsAnInteger() {
        double d1 = 15.0d;
        double d2 = 50.46542d;
        float f1 = 2.0f;
        float f2 = 2.00001f;
        BigDecimal bd1 = new BigDecimal("100.00000");
        BigDecimal bd2 = new BigDecimal("100.00000001");
        BigInteger bi = new BigInteger("100");

        boolean isAnInteger01 = O.isAnInteger(d1);  // true
        boolean isAnInteger02 = O.isAnInteger(d2);  // false
        boolean isAnInteger03 = O.isAnInteger(f1);  // true
        boolean isAnInteger04 = O.isAnInteger(f2);  // false
        boolean isAnInteger05 = O.isAnInteger(bd1); // true
        boolean isAnInteger06 = O.isAnInteger(bd2); // false
        boolean isAnInteger07 = O.isAnInteger(bi);  // true

        System.out.println(isAnInteger01);
        System.out.println(isAnInteger02);
        System.out.println(isAnInteger03);
        System.out.println(isAnInteger04);
        System.out.println(isAnInteger05);
        System.out.println(isAnInteger06);
        System.out.println(isAnInteger07);

        assertTrue(isAnInteger01);
        assertFalse(isAnInteger02);
        assertTrue(isAnInteger03);
        assertFalse(isAnInteger04);
        assertTrue(isAnInteger05);
        assertFalse(isAnInteger06);
        assertTrue(isAnInteger07);

    }

    @Test
    public void testSneakyThrow() {
        try {
            sneakyThrow(new IOException());
        } catch (Exception e) {
            assertEquals(e.getClass(), IOException.class);
        }
        // sneakyThrow(new IOException());
    }

    private void sneakyThrow(Throwable e) {
        O.sneakyThrows(e);
    }

    @Test
    public void testIsZhLang() {
        System.out.println("是否中文环境：" + G.IS_ZH_LANG);
    }


    @Test
    public void testDivide() {
        BigDecimal divide01 = O.divide(BigDecimal.valueOf(1), BigDecimal.valueOf(3));
        BigDecimal divide02 = O.divide(BigDecimal.valueOf(1), BigDecimal.valueOf(1024L * 1024 * 1024 * 1024L), true);
        BigDecimal divide03 = O.divide(BigDecimal.valueOf(1), BigDecimal.valueOf(1024L * 1024 * 1024 * 1024L), false);
        BigDecimal divide04 = O.divide(BigInteger.valueOf(1), BigInteger.valueOf(33333333333333333L));
        BigDecimal divide05 = O.divide(BigInteger.valueOf(1), BigInteger.valueOf(33333333333333333L), 30);
        BigDecimal divide06 = O.divide(BigInteger.valueOf(1), BigInteger.valueOf(1024L * 1024 * 1024 * 1024L), 15, true);
        BigDecimal divide07 = O.divide(BigInteger.valueOf(1), BigInteger.valueOf(1024L * 1024 * 1024 * 1024L), false);
        BigDecimal divide08 = O.divide(1, BigDecimal.valueOf(3));
        assertThrows(ArithmeticException.class, () -> O.divide(BigDecimal.valueOf(1), 0), "/ by zero");
        assertThrows(NumberFormatException.class, () -> O.divide(BigDecimal.valueOf(1), Double.NaN), "The `number` is NaN or Infinity, can't convert to BigDecimal");
        System.out.println(divide01.toPlainString());     // 0.333333
        System.out.println(divide02.toPlainString());     // 0.000000
        System.out.println(divide03.toPlainString());     // 0.0000000000009094947017729282379150390625
        System.out.println(divide04.toPlainString());     // 0.000000
        System.out.println(divide05.toPlainString());     // 0.000000000000000030000000000000
        System.out.println(divide06.toPlainString());     // 0.000000000000909
        System.out.println(divide07.toPlainString());     // 0.0000000000009094947017729282379150390625
        System.out.println(divide08.toPlainString());     // 0.333333

        assertEquals(divide01.toPlainString(), "0.333333");
        assertEquals(divide02.toPlainString(), "0.000000");
        assertEquals(divide03.toPlainString(), "0.0000000000009094947017729282379150390625");
        assertEquals(divide04.toPlainString(), "0.000000");
        assertEquals(divide05.toPlainString(), "0.000000000000000030000000000000");
        assertEquals(divide06.toPlainString(), "0.000000000000909");
        assertEquals(divide07.toPlainString(), "0.0000000000009094947017729282379150390625");
        assertEquals(divide08.toPlainString(), "0.333333");
    }

    @Test
    public void testDividePower() {
        Number divide01 = O.dividePower(BigDecimal.valueOf(1), BigDecimal.valueOf(3));
        Number divide02 = O.dividePower(BigDecimal.valueOf(1), BigDecimal.valueOf(1024L * 1024 * 1024 * 1024L), true);
        Number divide03 = O.dividePower(BigDecimal.valueOf(1), BigDecimal.valueOf(1024L * 1024 * 1024 * 1024L), false);
        Number divide04 = O.dividePower(BigInteger.valueOf(1), BigInteger.valueOf(33333333333333333L));
        Number divide05 = O.dividePower(BigInteger.valueOf(1), BigInteger.valueOf(33333333333333333L), 30);
        Number divide06 = O.dividePower(BigInteger.valueOf(1), BigInteger.valueOf(1024L * 1024 * 1024 * 1024L), 15, true);
        Number divide07 = O.dividePower(BigInteger.valueOf(1), BigInteger.valueOf(1024L * 1024 * 1024 * 1024L), false);
        Number divide08 = O.dividePower(1, 3);
        Number divide09 = O.dividePower(1, 3.0);
        Number divide10 = O.dividePower(-1, 0.0);
        Number divide11 = O.dividePower(1, Double.NaN);
        assertThrows(ArithmeticException.class, () -> O.dividePower(1, 0), "/ by zero");
        assertThrows(ArithmeticException.class, () -> O.dividePower(BigDecimal.valueOf(1), 0), "/ by zero");
        assertThrows(NumberFormatException.class, () -> O.dividePower(BigDecimal.valueOf(1), Double.NaN), "The `number` is NaN or Infinity, can't convert to BigDecimal");
        System.out.println(G.toString(divide01));     // 0.333333
        System.out.println(G.toString(divide02));     // 0.000000
        System.out.println(G.toString(divide03, 50));     // 0.0000000000009094947017729282379150390625
        System.out.println(G.toString(divide04));     // 0.000000
        System.out.println(G.toString(divide05, 50));     // 0.000000000000000030000000000000
        System.out.println(G.toString(divide06));     // 0.000000000000909
        System.out.println(G.toString(divide07, 50));     // 0
        System.out.println(G.toString(divide08));     // 0
        System.out.println(G.toString(divide09));
        System.out.println(G.toString(divide10));
        System.out.println(G.toString(divide11));

        assertEquals(G.toString(divide01), "0.333333");
        assertEquals(G.toString(divide02), "0.0");
        assertEquals(G.toString(divide03, 50), "0.0000000000009094947017729282379150390625");
        assertEquals(G.toString(divide04), "0");
        assertEquals(G.toString(divide05, 50), "0");
        assertEquals(G.toString(divide06), "0");
        assertEquals(G.toString(divide07, 50), "0");
        assertEquals(G.toString(divide08), "0");
        assertEquals(G.toString(divide09), "0.333333");
        assertEquals(G.toString(divide10), "-∞");
        assertEquals(G.toString(divide11), "NaN");
    }

    @Test
    public void testIsValidDouble() {
        String s1 = "94965465465165.5445646546";
        String s2 = "9496546546516567.5445646546";
        String s3 = "0.949654654";
        String s4 = "9999991.999999998";
        String s5 = "999999199999848";
        String s6 = "9999991999998489";
        String s7 = ".999999998";
        String s8 = "a.999999998";
        String s9 = "-9999991.999999998";


        assertFalse(S.isDouble(s1));
        assertFalse(S.isDouble(s2));
        assertTrue(S.isDouble(s3));
        assertTrue(S.isDouble(s4));
        assertTrue(S.isDouble(s5));
        assertFalse(S.isDouble(s6));
        assertTrue(S.isDouble(s7));
        assertFalse(S.isDouble(s8));
        assertTrue(S.isDouble(s9));

        assertEquals(s3, G.toString(Double.valueOf(s3), 10));
        assertEquals(s4, G.toString(Double.valueOf(s4), 10));
        assertEquals(s5 + ".0", G.toString(Double.valueOf(s5), 10));
        assertEquals("0" + s7, G.toString(Double.valueOf(s7), 10));
        assertEquals(s9, G.toString(Double.valueOf(s9), 10));

    }

}
