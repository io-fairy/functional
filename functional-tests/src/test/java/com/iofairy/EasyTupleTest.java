package com.iofairy;

import com.iofairy.except.AliasDuplicateException;
import com.iofairy.except.NumberOfAliasesException;
import com.iofairy.tuple.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.iofairy.MyTupleAlias.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author GG
 */
public class EasyTupleTest {

    @Test
    public void testEasyTuple() {
        EasyTuple0 instance = EasyTuple0.instance();
        EasyTuple0 empty = EasyTuple.empty();
        EasyTuple0 of = EasyTuple.of();
        assertEquals(instance, empty);
        assertEquals(of, empty);
        assertEquals("()", empty.toString());
        assertThrows(UnsupportedOperationException.class, () -> empty.alias(A));

        List<Object> objects = empty.toList();
        System.out.println(objects);  // []

    }

    @Test
    public void testEasyTupleAlias() {
        EasyTuple4<String> userInfo = EasyTuple.of("1", "Tom", "nullval", "打篮球");

        assertEquals("(\"1\", \"Tom\", \"nullval\", \"打篮球\")", userInfo.toString());

        userInfo.alias(ID, NAME, null, HOBBY);

        assertEquals("(ID: \"1\", NAME: \"Tom\", `null`: \"nullval\", HOBBY: \"打篮球\")", userInfo.toString());

        String id  = userInfo.__(ID);             // （推荐）  使用枚举值取tuple中的元素
        String name = userInfo.__("NAME");    // （不推荐）使用枚举值对应的字符串取tuple中的元素
        String nullVal = userInfo.__((TupleAlias) null);
        String nullVal1 = userInfo.__((String) null);
        System.out.println("ID: " + id + "  name: " + name);    // output：ID: 1  name: Tom

        assertEquals("1", id);
        assertEquals("Tom", name);
        assertEquals("nullval", nullVal);
        assertEquals("nullval", nullVal1);

        userInfo.clearAlias();
        assertEquals("(\"1\", \"Tom\", \"nullval\", \"打篮球\")", userInfo.toString());

        EasyTuple2<Integer> t2 = new EasyTuple2<>(123456, 20).alias("ID", "AGE");
        Integer id1 = t2.__("ID");
        Integer age = t2.__(AGE);

        assertEquals(123456, id1);
        assertEquals(20, age);

        EasyTuple2<String> zsTuple = EasyTuple.of("zs", "12");
        assertThrows(NumberOfAliasesException.class, () -> zsTuple.alias(A, B, C));
        assertThrows(AliasDuplicateException.class, () -> zsTuple.alias(A, B, B));
    }

    @Test
    public void testCopyAliases() {
        Tuple3<String, Integer, Integer> t3 = new Tuple3<>("a", 1, 2).alias(A, B, C);

        EasyTuple2<String> et2 = EasyTuple.of("1", "Tom").alias(D, E);
        EasyTuple2<String> et21 = EasyTuple.of("1", "Tom").alias("D", "E");
        EasyTuple3<String> userInfo = EasyTuple.of("1", "Tom", "nullval").alias(D, E, F);

        EasyTuple3<String> userInfo1 = EasyTuple.of("1", "Tom", "nullval");

        Tuple2<String, Integer> t2 = new Tuple2<>("abc", 20).alias("NAME", "AGE");
        Tuple2<String, Integer> t21 = new Tuple2<>("abc", 20);

        assertEquals("null", userInfo1.aliasType());
        userInfo1.copyAliases(userInfo);
        assertEquals("tuple", userInfo1.aliasType());
        assertThrows(NumberOfAliasesException.class, () -> userInfo1.copyAliases(et2));
        userInfo1.copyAliases(t3);
        assertEquals("(A: \"1\", B: \"Tom\", C: \"nullval\")", userInfo1.toString());
        t21.copyAliases(et21);
        assertEquals("(D: \"abc\", E: 20)", t21.toString());
        assertEquals("string", t21.aliasType());

    }

    @Test
    public void testForeachElements() {
        EasyTuple4<String> userInfo = EasyTuple.of("1", "Tom", "nullval", "打篮球").alias(ID, NAME, null, HOBBY);

        for (int i = 0; i < userInfo.arity(); i++) {
            String e = userInfo.element(i);
            System.out.println(e);
            Tuple2<String, String> elementWithAlias = userInfo.elementWithAlias(i);// 带别名
            System.out.println(elementWithAlias);
        }

        assertEquals("(ID: \"1\", NAME: \"Tom\", `null`: \"nullval\", HOBBY: \"打篮球\")", userInfo.toString());

        List<String> es = userInfo.toList();
        assertEquals("[1, Tom, nullval, 打篮球]", es.toString());

    }

    @Test
    public void testEasyTupleEquals() {
        EasyTuple0 copy = EasyTuple.empty().copy();
        assertEquals("()", copy.toString());

        EasyTuple4<String> userInfo = EasyTuple.of("1", "Tom", "nullval", "打篮球").alias(ID, NAME, null, HOBBY);
        EasyTuple4<String> copy1 = userInfo.copy();

        assertEquals("(ID: \"1\", NAME: \"Tom\", `null`: \"nullval\", HOBBY: \"打篮球\")", copy1.toString());

        EasyTuple4<String> clone = (EasyTuple4<String>) EasyTuple.clone(userInfo);
        assertEquals("(ID: \"1\", NAME: \"Tom\", `null`: \"nullval\", HOBBY: \"打篮球\")", clone.toString());

        EasyTuple4<String> userInfo111 = EasyTuple.of("1", "Tom", "nullval", "打篮球");
        EasyTuple4<String> copy2 = userInfo111.copy();
        assertEquals("(\"1\", \"Tom\", \"nullval\", \"打篮球\")", copy2.toString());

    }


    @Test
    public void testEasyTupleToMap() {

        EasyTuple2<String> et2 = new EasyTuple2<>("zs", "ls");
        Map<String, String> etMap1 = et2.toMap();
        assertEquals("{_1=zs, _2=ls}", etMap1.toString());
        et2.alias("name", "nickName");
        Map<String, String> etMap2 = et2.toMap();
        assertEquals("{nickName=ls, name=zs}", etMap2.toString());


    }

}
