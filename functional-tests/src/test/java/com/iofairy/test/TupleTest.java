package com.iofairy.test;

import com.iofairy.except.AliasDuplicateException;
import com.iofairy.except.NumberOfAliasesException;
import com.iofairy.tuple.*;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static com.iofairy.test.MyTupleAlias.*;

/**
 * @author GG
 */
public class TupleTest {

    @Test
    public void testTupleAlias() {
        Tuple4<Integer, String, String, Integer> userInfo = new Tuple4<>(1, "Tom", "nullval", 20);
        userInfo.alias(ID, NAME, null, AGE);
        Integer id  = userInfo.__(ID);             // （推荐）  使用枚举值取tuple中的元素
        String name = userInfo.__("NAME");    // （不推荐）使用枚举值对应的字符串取tuple中的元素
        String nullVal = userInfo.__((TupleAlias) null);
        String nullVal1 = userInfo.__((String) null);
        System.out.println("ID: " + id + "  name: " + name);    // output：ID: 1  name: Tom

        assertEquals(1, id);
        assertEquals("Tom", name);
        assertEquals("nullval", nullVal);
        assertEquals("nullval", nullVal1);

        Tuple2<String, Integer> t2 = new Tuple2<>("abc", 20).alias("NAME", "AGE");
        Integer age = t2.<Integer>__("AGE");
        String name1 = t2.__(NAME);
        System.out.println(age);                // output: 20
        assertEquals(20, age);
        assertEquals("abc", name1);

        Tuple2<String, Integer> zsTuple = new Tuple2<>("zs", 12);

        try {
            zsTuple.alias(A, B, C);
            throwException();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertSame(e.getClass(), NumberOfAliasesException.class);
            assertEquals(e.getMessage(), "参数`aliases`的长度不等于2。");
        }
        try {
            zsTuple.alias(A, B, B);
            throwException();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertSame(e.getClass(), AliasDuplicateException.class);
            assertEquals(e.getMessage(), "别名不能重复！");
        }

    }

    @Test
    public void testCopyAliases() {
        Tuple3<String, Integer, Integer> t3 = new Tuple3<>("a", 1, 2).alias(A, B, C);

        Tuple2<String, Integer> t2 = new Tuple2<>("abc", 20).alias("NAME", "AGE");
        Tuple2<String, Integer> t21 = new Tuple2<>("abc", 20).alias(NAME, AGE);
        Tuple2<String, Integer> t22 = new Tuple2<>("111", 15);
        Tuple2<String, Integer> t23 = new Tuple2<>("111", 15);

        assertEquals("tuple", t3.aliasType());
        System.out.println(t3);

        try {
            t3.copyAliases(t2);
            throwException();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertSame(e.getClass(), NumberOfAliasesException.class);
            assertEquals(e.getMessage(), "参数`tuple`的元素数量不等于3。");
        }

        assertEquals("tuple", t3.aliasType());
        System.out.println(t3);
        t22.copyAliases(t2);
        assertEquals("string", t22.aliasType());
        System.out.println(t22);
        t23.copyAliases(t21);
        assertEquals("tuple", t23.aliasType());
        System.out.println(t23);



    }

    @Test
    public void testForeachElements() {
        Tuple2<String, Integer> t2 = new Tuple2<>("zs", 20).alias("name", "age");
        for (int i = 0; i < t2.arity(); i++) {
            Object element = t2.element(i);                  // 不带别名
            System.out.println(element);                     // output：zs  和   20
            Tuple2<String, Object> elementWithAlias = t2.elementWithAlias(i);   // 带别名
            System.out.println(elementWithAlias);            // output：("name", "zs")   和  ("age", 20)
        }

        System.out.println(t2);
        assertEquals("(name: \"zs\", age: 20)", t2.toString());

    }

    @Test
    public void testTupleEquals() {
        assertTrue(Tuple.of().equals(Tuple0.instance()));
        assertFalse(new Tuple2<>("1", "a").equals(Tuple0.instance()));
        assertTrue(Tuple.of(new Tuple2<>("zs", 123)).equals(new Tuple1<>(Tuple.of("zs", 123))));
        assertTrue(Tuple.of(new Tuple2<>("zs", null)).equals(new Tuple1<>(Tuple.of("zs", null))));
        assertFalse(Tuple.of(new Tuple2<>("zs", 123), 123).equals(new Tuple1<>(Tuple.of("zs", 123))));
        assertTrue(new Tuple2<>("zs", null).equals(new Tuple2<>("zs", null)));
        assertFalse(new Tuple2<>("zs", null).equals(new Tuple2<>("zs", 123)));
        assertFalse(new Tuple2<>("zs", null).equals(null));
        assertFalse(new Tuple2<>("zs", null).equals(new Object()));
        // ================带别名的匹配================
        Tuple2<String, Object> zs = new Tuple2<>("zs", null);
        Tuple2<String, Object> zsWithAlias = new Tuple2<>("zs", null).alias(A, B);
        assertFalse(new Tuple2<>("zs", null).alias(A, B).equals(zs));
        assertTrue(zsWithAlias.equals(zs.copyAliases(zsWithAlias)));

        Tuple4<Integer, Tuple0, Object, Tuple2<String, Object>> tuple1 =
                new Tuple4<>(1, Tuple0.instance(), null, new Tuple2<>("zs", null));
        Tuple4<Integer, Tuple0, Object, Tuple2<String, Object>> tuple2 =
                new Tuple4<>(1, Tuple0.instance(), null, zsWithAlias);
        Tuple4<Integer, Tuple0, Object, Tuple2<String, Object>> tuple3 =
                new Tuple4<>(1, Tuple0.instance(), null, new Tuple2<>("zs", null).alias(A, B));
        Tuple4<Integer, Tuple0, Object, Tuple2<String, Object>> tuple4 =
                new Tuple4<>(1, Tuple0.instance(), null, new Tuple2<>("zs", null).alias("A", "B"));

        assertFalse(tuple1.equals(tuple2));
        assertTrue(tuple2.equals(tuple3));
        assertFalse(tuple3.equals(tuple4));


        System.out.println("testTupleEquals 全部成功！");
    }

    @Test
    public void testCloneAndCopy() {
        Tuple2<String, Integer> t2 = new Tuple2<>("zs", 20).alias("NAME", "AGE");
        Tuple2<String, Integer> clone1 = (Tuple2<String, Integer>) Tuple.clone(t2);
        Tuple4<Integer, Tuple0, Object, Tuple2<String, Object>> tuple =
                new Tuple4<>(1, Tuple0.instance(), null, new Tuple2<>("zs", null).alias(A, B));
        Tuple4<Integer, Tuple0, Object, Tuple2<String, Object>> clone2 =
                (Tuple4<Integer, Tuple0, Object, Tuple2<String, Object>>)Tuple.clone(tuple);

        Tuple4<Integer, Tuple0, Object, Tuple2<String, Object>> copy = tuple.copy();

        assertFalse(t2 == clone1);
        assertFalse(tuple == clone2);
        assertEquals("string", clone1.aliasType());
        assertEquals("null", clone2.aliasType());
        assertEquals("(A: \"zs\", B: null)", clone2._4.toString());
        assertEquals("(1, (), null, (A: \"zs\", B: null))", copy.toString());
    }

    @Test
    public void testTupleToMap() {
        Tuple2<String, Integer> t2 = new Tuple2<>("zs", 20);
        Map<String, Object> map = t2.toMap();
        assertEquals("{_1=zs, _2=20}", map.toString());
        t2.alias("NAME", "AGE");
        Map<String, Object> map1 = t2.toMap();
        assertEquals("{NAME=zs, AGE=20}", map1.toString());

        Tuple4<Integer, String, String, Integer> userInfo = new Tuple4<>(1, "Tom", "nullval", 20);
        userInfo.alias(ID, NAME, null, AGE);
        Map<String, Object> userMap = userInfo.toMap();
        assertEquals("{null=nullval, ID=1, NAME=Tom, AGE=20}", userMap.toString());
        System.out.println(userMap);
        System.out.println(userMap.get(null));

    }

    @Test
    public void testTupleToString() {
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
        double[] ds = {1.123456, 2.20987, 3.36543};

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

        Tuple9<Object[], Object, char[], char[], ? extends Map<?, Integer>, Character, Map<Object, Object>, double[], CharSequence[]> tuple =
                Tuple.of(os, null, a, a1, mapN, ch, hashMap, ds, cs1).alias("1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th");

        /*
         * >>> tupleString的值
         * 未使用别名：
         * (null, null, ['a', 'b', 'c'], [], {"3"=4, '1'=2}, 'a', {null=null, ['a', 'b', null, 'c']=["a", "b", null, "", "c"],
         * ["a1", "a2"]=["a", "b", "", "c", null], {1=2}={1=2}, {"3"=4, '1'=2}={'3'=3, '2'=["a1", "a2"], '1'=1}, []=[true, false],
         * {"a"=["abc", "123"]}=["a", "", null, ['a', 'b']="abc"="123", ["abc", "123", ['a', 'b', 'c']], {'1'=1, '2'=["a1", "a2"], '3'=3}, 1]},
         * [1.123456, 2.20987, 3.36543], [])
         * 使用别名：
         * (1st: null, 2nd: null, 3rd: ['a', 'b', 'c'], 4th: [], 5th: {'1'=2, "3"=4}, 6th: 'a',
         * 7th: {null=null, []=[true, false], ["a1", "a2"]=["a", "b", "", "c", null], {1=2}={1=2}, {'1'=2, "3"=4}={'3'=3, '2'=["a1", "a2"], '1'=1},
         * ['a', 'b', null, 'c']=["a", "b", null, "", "c"], {"a"=["abc", "123"]}=["a", "", null, ['a', 'b']="abc"="123", ["abc", "123", ['a', 'b', 'c']],
         * {'1'=1, '2'=["a1", "a2"], '3'=3}, 1]},
         * 8th: [1.123456, 2.20987, 3.36543], 9th: [])
         *
         */
        String tupleString = tuple.toString();
        // assertEquals("", tupleString);    // 由于包含map，每次运行 tupleString 中的 map kv对的顺序有细微差别
        System.out.println("testTupleToString: \n" + tuple);

    }

    @Test
    public void testTupleToString1() {
        char[][][] chars = {{{'a', 'b'}, null}, {{'1', '2'}}, null, {}};
        Object[][][] objects = {{{'a', "b", null}, null}, {{'1', 2, 1.205}}, null, {}};
        Object[][][] objects1 = null;

        Tuple3<char[][][], Object[][][], Object[][][]> tuple = Tuple.of(chars, objects, objects1).alias("3DCharArr", "3DObjectArr", "3DObjectArr1");
        assertEquals("(3DCharArr: [[['a', 'b'], null], [['1', '2']], null, []], " +
                "3DObjectArr: [[['a', \"b\", null], null], [['1', 2, 1.205]], null, []], 3DObjectArr1: null)", tuple.toString());
        System.out.println("testTupleToString1: \n" + tuple);
    }

    private void throwException() {
        throw new RuntimeException();
    }
}
