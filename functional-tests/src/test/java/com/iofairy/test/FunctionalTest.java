package com.iofairy.test;

import com.iofairy.lambda.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author GG
 */
public class FunctionalTest {

    @Test
    public void testV2() {
        // Java 8 之前：使用匿名内部类，调用v2AsParams
        v2AsParams(new V2<String, String>() {
            @Override
            public void $(String s1, String s2) {
                System.out.println(s1 + " -- " + s2);
            }
        });
        //Java 8 及以后：使用 Lambda 表达式，调用v2AsParams
        int res = v2AsParams((s1, s2) -> System.out.println(s1 + " -- " + s2));

        assertEquals(0, res);
    }

    @Test
    public void testR1() {
        List<String> ls = Arrays.asList("1", "2", "3", "4");
        List<Integer> map = map(ls, s -> Integer.parseInt(s) + 20);
        System.out.println(map);         // output：[21, 22, 23, 24]

        assertArrayEquals(new Integer[]{21, 22, 23, 24}, map.toArray());
    }

    //测试R2接口（不支持抛出异常） 处理异常示例
    @Test
    public void testR2Exception() {
        // 必须在 lambda 表达式中使用 try-catch 块处理，无法将异常继续向外抛出
        R2<String, Integer, String> r2 = (s, i) -> {
            if (i == 5) {
                try {
                    // 必须使用 try-catch 处理，否则报错
                    throw new IOException("抛出异常");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return s + i;
        };
        // 由于R2不支持抛出异常，所以调用 $函数没有异常
        String res = r2.$("abcd", 1);

        assertEquals("abcd1", res);
    }

    // RT2接口（支持抛出异常） 处理异常示例
    @Test
    public void testRT2Exception() {
        RT2<String, Integer, String, IOException> rt2 = (s, i) -> {
            // 使用 RT2 在lambda 表达式中，不用处理异常，等到调用 $ 函数时再处理
            if (i == 5) throw new IOException("抛出异常");
            return s + i;
        };
        //第一种方式：使用 try-catch 处理异常
        try {
            String res = rt2.$("abcd", 1);
            assertEquals("abcd1", res);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //第二种方式：继续向外抛出异常，在函数上申明异常：  public void testRT2Exception() throws IOException
        // String s1 = rt2.$("1234", 5);
        // String s2 = rt2.$("1234", 56);
    }


    @Test
    public void testLambdaInterface() {
        R0<Integer> r0 = () -> 1;
        RT1<String, Integer, Exception> rt1 = s -> {
            if (s.equals("1")) {
                return 1;
            }else {
                throw new Exception();
            }
        };
        V2<Integer, Integer> v2 = (i1, i2) -> System.out.println(i1 + i2);
        VT2<String, Integer, Exception> vt2 = (s, i) -> {
            if (i == 1) {
                throw new Exception();
            } else {
                System.out.println(s);
            }
        };

        assertTrue(r0.arity() == 0);
        assertFalse(r0.isVoid());
        assertFalse(r0.hasThrows());
        assertTrue(rt1.arity() == 1);
        assertFalse(rt1.isVoid());
        assertTrue(rt1.hasThrows());
        assertTrue(v2.arity() == 2);
        assertTrue(v2.isVoid());
        assertFalse(v2.hasThrows());
        assertTrue(vt2.arity() == 2);
        assertTrue(vt2.isVoid());
        assertTrue(vt2.hasThrows());
    }


    // 当一个函数需要接收一个 `两个参数无返回值的函数接口` 时，可以使用现有的 V2<T1, T2>，而不用重新构造一个接口
    private int v2AsParams(V2<String, String> v2) {
        v2.$("abcd", "1234");
        return 0;
    }

    // 当一个函数需要接收一个 `接收一个参数，并返回值的函数接口` 时，可以使用 R1<T, R>，不用重新构造一个接口，
    // 如：java.util.stream.Stream 中的 map 函数
    private <T, R> List<R> map(List<T> ls, R1<T, R> r1) {
        ArrayList<R> rs = new ArrayList<>();
        for (T l : ls) rs.add(r1.$(l));
        return rs;
    }


}
