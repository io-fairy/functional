package com.iofairy.test;

import com.iofairy.string.CaseConverter;
import com.iofairy.string.Identifier;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author GG
 * @version 1.0
 */
public class IdentifierTest {
    @Test
    public void testIsValidIdentifier() {
        String s1 = "1kfdsalkj";
        String s2 = null;
        String s3 = "   ";
        String s4 = "_lkjfdsa232$_";
        String s5 = "_lkjfdsa232_";
        String s6 = "_lkjfdsa中文232$_";
        String s7 = "_lkjfdsa232中文_";
        String s8 = "中文_lkjf*dsa232中文_";
        String s9 = "public";

        boolean validIdentifier01 = Identifier.isValidIdentifier(s1);
        boolean validIdentifier02 = Identifier.isValidIdentifier(s2);
        boolean validIdentifier03 = Identifier.isValidIdentifier(s3);
        boolean validIdentifier04 = Identifier.isValidIdentifier(s4);
        boolean validIdentifier05 = Identifier.isValidIdentifier(s5);
        boolean validIdentifier06 = Identifier.isValidIdentifier(s6);
        boolean validIdentifier07 = Identifier.isValidIdentifier(s7);
        boolean validIdentifier08 = Identifier.isValidIdentifier(s8);
        boolean validIdentifier09 = Identifier.isValidIdentifier(s9);
        boolean validIdentifier11 = Identifier.isValidIdentifier(s1, true);
        boolean validIdentifier12 = Identifier.isValidIdentifier(s2, true);
        boolean validIdentifier13 = Identifier.isValidIdentifier(s3, true);
        boolean validIdentifier14 = Identifier.isValidIdentifier(s4, true);
        boolean validIdentifier15 = Identifier.isValidIdentifier(s5, true);
        boolean validIdentifier16 = Identifier.isValidIdentifier(s6, true);
        boolean validIdentifier17 = Identifier.isValidIdentifier(s7, true);
        boolean validIdentifier18 = Identifier.isValidIdentifier(s8, true);
        boolean validIdentifier19 = Identifier.isValidIdentifier(s9, true);
        boolean validIdentifier21 = Identifier.isValidIdentifier(s1, true, false);
        boolean validIdentifier22 = Identifier.isValidIdentifier(s2, true, false);
        boolean validIdentifier23 = Identifier.isValidIdentifier(s3, true, false);
        boolean validIdentifier24 = Identifier.isValidIdentifier(s4, true, false);
        boolean validIdentifier25 = Identifier.isValidIdentifier(s5, true, false);
        boolean validIdentifier26 = Identifier.isValidIdentifier(s6, true, false);
        boolean validIdentifier27 = Identifier.isValidIdentifier(s7, true, false);
        boolean validIdentifier28 = Identifier.isValidIdentifier(s8, true, false);
        boolean validIdentifier29 = Identifier.isValidIdentifier(s9, true, false);
        boolean validIdentifier31 = Identifier.isValidIdentifier(s1, false, false);
        boolean validIdentifier32 = Identifier.isValidIdentifier(s2, false, false);
        boolean validIdentifier33 = Identifier.isValidIdentifier(s3, false, false);
        boolean validIdentifier34 = Identifier.isValidIdentifier(s4, false, false);
        boolean validIdentifier35 = Identifier.isValidIdentifier(s5, false, false);
        boolean validIdentifier36 = Identifier.isValidIdentifier(s6, false, false);
        boolean validIdentifier37 = Identifier.isValidIdentifier(s7, false, false);
        boolean validIdentifier38 = Identifier.isValidIdentifier(s8, false, false);
        boolean validIdentifier39 = Identifier.isValidIdentifier(s9, false, false);
        boolean validIdentifier41 = Identifier.isValidIdentifier(s1, false, false, Arrays.asList("assert", "public"));
        boolean validIdentifier42 = Identifier.isValidIdentifier(s2, false, false, Arrays.asList("assert", "public"));
        boolean validIdentifier43 = Identifier.isValidIdentifier(s3, false, false, Arrays.asList("assert", "public"));
        boolean validIdentifier44 = Identifier.isValidIdentifier(s4, false, false, Arrays.asList("assert", "public"));
        boolean validIdentifier45 = Identifier.isValidIdentifier(s5, false, false, Arrays.asList("assert", "public"));
        boolean validIdentifier46 = Identifier.isValidIdentifier(s6, false, false, Arrays.asList("assert", "public"));
        boolean validIdentifier47 = Identifier.isValidIdentifier(s7, false, false, Arrays.asList("assert", "public"));
        boolean validIdentifier48 = Identifier.isValidIdentifier(s8, false, false, Arrays.asList("assert", "public"));
        boolean validIdentifier49 = Identifier.isValidIdentifier(s9, false, false, Arrays.asList("assert", "public"));

        // System.out.println("validIdentifier01: " + validIdentifier01);      // false
        // System.out.println("validIdentifier02: " + validIdentifier02);      // false
        // System.out.println("validIdentifier03: " + validIdentifier03);      // false
        // System.out.println("validIdentifier04: " + validIdentifier04);      // true
        // System.out.println("validIdentifier05: " + validIdentifier05);      // true
        // System.out.println("validIdentifier06: " + validIdentifier06);      // false
        // System.out.println("validIdentifier07: " + validIdentifier07);      // false
        // System.out.println("validIdentifier08: " + validIdentifier08);      // false
        // System.out.println("validIdentifier09: " + validIdentifier09);      // true
        //
        // System.out.println("validIdentifier11: " + validIdentifier11);      // false
        // System.out.println("validIdentifier12: " + validIdentifier12);      // false
        // System.out.println("validIdentifier13: " + validIdentifier13);      // false
        // System.out.println("validIdentifier14: " + validIdentifier14);      // true
        // System.out.println("validIdentifier15: " + validIdentifier15);      // true
        // System.out.println("validIdentifier16: " + validIdentifier16);      // true
        // System.out.println("validIdentifier17: " + validIdentifier17);      // true
        // System.out.println("validIdentifier18: " + validIdentifier18);      // false
        // System.out.println("validIdentifier19: " + validIdentifier19);      // true
        //
        // System.out.println("validIdentifier21: " + validIdentifier21);      // false
        // System.out.println("validIdentifier22: " + validIdentifier22);      // false
        // System.out.println("validIdentifier23: " + validIdentifier23);      // false
        // System.out.println("validIdentifier24: " + validIdentifier24);      // false
        // System.out.println("validIdentifier25: " + validIdentifier25);      // true
        // System.out.println("validIdentifier26: " + validIdentifier26);      // false
        // System.out.println("validIdentifier27: " + validIdentifier27);      // true
        // System.out.println("validIdentifier28: " + validIdentifier28);      // false
        // System.out.println("validIdentifier29: " + validIdentifier29);      // true
        //
        // System.out.println("validIdentifier31: " + validIdentifier31);      // false
        // System.out.println("validIdentifier32: " + validIdentifier32);      // false
        // System.out.println("validIdentifier33: " + validIdentifier33);      // false
        // System.out.println("validIdentifier34: " + validIdentifier34);      // false
        // System.out.println("validIdentifier35: " + validIdentifier35);      // true
        // System.out.println("validIdentifier36: " + validIdentifier36);      // false
        // System.out.println("validIdentifier37: " + validIdentifier37);      // false
        // System.out.println("validIdentifier38: " + validIdentifier38);      // false
        // System.out.println("validIdentifier39: " + validIdentifier39);      // true
        //
        // System.out.println("validIdentifier41: " + validIdentifier41);      // false
        // System.out.println("validIdentifier42: " + validIdentifier42);      // false
        // System.out.println("validIdentifier43: " + validIdentifier43);      // false
        // System.out.println("validIdentifier44: " + validIdentifier44);      // false
        // System.out.println("validIdentifier45: " + validIdentifier45);      // true
        // System.out.println("validIdentifier46: " + validIdentifier46);      // false
        // System.out.println("validIdentifier47: " + validIdentifier47);      // false
        // System.out.println("validIdentifier48: " + validIdentifier48);      // false
        // System.out.println("validIdentifier49: " + validIdentifier49);      // false

        assertFalse(validIdentifier01);      // false
        assertFalse(validIdentifier02);      // false
        assertFalse(validIdentifier03);      // false
        assertTrue(validIdentifier04);      // true
        assertTrue(validIdentifier05);      // true
        assertFalse(validIdentifier06);      // false
        assertFalse(validIdentifier07);      // false
        assertFalse(validIdentifier08);      // false
        assertTrue(validIdentifier09);      // true
        assertFalse(validIdentifier11);      // false
        assertFalse(validIdentifier12);      // false
        assertFalse(validIdentifier13);      // false
        assertTrue(validIdentifier14);      // true
        assertTrue(validIdentifier15);      // true
        assertTrue(validIdentifier16);      // true
        assertTrue(validIdentifier17);      // true
        assertFalse(validIdentifier18);      // false
        assertTrue(validIdentifier19);      // true
        assertFalse(validIdentifier21);      // false
        assertFalse(validIdentifier22);      // false
        assertFalse(validIdentifier23);      // false
        assertFalse(validIdentifier24);      // false
        assertTrue(validIdentifier25);      // true
        assertFalse(validIdentifier26);      // false
        assertTrue(validIdentifier27);      // true
        assertFalse(validIdentifier28);      // false
        assertTrue(validIdentifier29);      // true
        assertFalse(validIdentifier31);      // false
        assertFalse(validIdentifier32);      // false
        assertFalse(validIdentifier33);      // false
        assertFalse(validIdentifier34);      // false
        assertTrue(validIdentifier35);      // true
        assertFalse(validIdentifier36);      // false
        assertFalse(validIdentifier37);      // false
        assertFalse(validIdentifier38);      // false
        assertTrue(validIdentifier39);      // true
        assertFalse(validIdentifier41);      // false
        assertFalse(validIdentifier42);      // false
        assertFalse(validIdentifier43);      // false
        assertFalse(validIdentifier44);      // false
        assertTrue(validIdentifier45);      // true
        assertFalse(validIdentifier46);      // false
        assertFalse(validIdentifier47);      // false
        assertFalse(validIdentifier48);      // false
        assertFalse(validIdentifier49);      // false

    }

    @Test
    public void testToIdentifier() {
        String input1 = "test-12中3";
        String input2 = " 789 tes啊t";
        String input3 = "test_789@";
        String input4 = "";
        String input5 = "789 tes啊t";
        String input6 = null;
        String input7 = "test_789";

        String identifier01 = Identifier.toIdentifier(input1);
        String identifier02 = Identifier.toIdentifier(input2);
        String identifier03 = Identifier.toIdentifier(input3);
        String identifier04 = Identifier.toIdentifier(input4);
        String identifier05 = Identifier.toIdentifier(input5);
        String identifier06 = Identifier.toIdentifier(input6);
        String identifier07 = Identifier.toIdentifier(input7);
        String identifier11 = Identifier.toIdentifier(input1, null);
        String identifier12 = Identifier.toIdentifier(input2, null);
        String identifier13 = Identifier.toIdentifier(input3, null);
        String identifier14 = Identifier.toIdentifier(input4, null);
        String identifier15 = Identifier.toIdentifier(input5, null);
        String identifier16 = Identifier.toIdentifier(input6, null);
        String identifier17 = Identifier.toIdentifier(input7, null);
        String identifier21 = Identifier.toIdentifier(input1, "a 1_");
        String identifier22 = Identifier.toIdentifier(input2, "a 1_");
        String identifier23 = Identifier.toIdentifier(input3, "a 1_");
        String identifier24 = Identifier.toIdentifier(input4, "a 1_");
        String identifier25 = Identifier.toIdentifier(input5, "a 1_");
        String identifier26 = Identifier.toIdentifier(input6, "a 1_");
        String identifier27 = Identifier.toIdentifier(input7, "a 1_");
        String identifier31 = Identifier.toIdentifier(input1, "aaa_");
        String identifier32 = Identifier.toIdentifier(input2, "aaa_");
        String identifier33 = Identifier.toIdentifier(input3, "aaa_");
        String identifier34 = Identifier.toIdentifier(input4, "aaa_");
        String identifier35 = Identifier.toIdentifier(input5, "aaa_");
        String identifier36 = Identifier.toIdentifier(input6, "aaa_");
        String identifier37 = Identifier.toIdentifier(input7, "aaa_");

        System.out.println("identifier01: " + identifier01);
        System.out.println("identifier02: " + identifier02);
        System.out.println("identifier03: " + identifier03);
        System.out.println("identifier04: " + identifier04);
        System.out.println("identifier05: " + identifier05);
        System.out.println("identifier06: " + identifier06);
        System.out.println("identifier07: " + identifier07);
        System.out.println("identifier11: " + identifier11);
        System.out.println("identifier12: " + identifier12);
        System.out.println("identifier13: " + identifier13);
        System.out.println("identifier14: " + identifier14);
        System.out.println("identifier15: " + identifier15);
        System.out.println("identifier16: " + identifier16);
        System.out.println("identifier17: " + identifier17);
        System.out.println("identifier21: " + identifier21);
        System.out.println("identifier22: " + identifier22);
        System.out.println("identifier23: " + identifier23);
        System.out.println("identifier24: " + identifier24);
        System.out.println("identifier25: " + identifier25);
        System.out.println("identifier26: " + identifier26);
        System.out.println("identifier27: " + identifier27);
        System.out.println("identifier31: " + identifier31);
        System.out.println("identifier32: " + identifier32);
        System.out.println("identifier33: " + identifier33);
        System.out.println("identifier34: " + identifier34);
        System.out.println("identifier35: " + identifier35);
        System.out.println("identifier36: " + identifier36);
        System.out.println("identifier37: " + identifier37);

        assertEquals(identifier01, "test_123");
        assertEquals(identifier02, "ext_789_test");
        assertEquals(identifier03, "test_789");
        assertEquals(identifier04, "ext_");
        assertEquals(identifier05, "ext_789_test");
        assertEquals(identifier06, "ext_null");
        assertEquals(identifier07, "test_789");
        assertEquals(identifier11, "test_123");
        assertEquals(identifier12, "ext_789_test");
        assertEquals(identifier13, "test_789");
        assertEquals(identifier14, "ext_");
        assertEquals(identifier15, "ext_789_test");
        assertEquals(identifier16, "ext_null");
        assertEquals(identifier17, "test_789");
        assertEquals(identifier21, "test_123");
        assertEquals(identifier22, "a_1_789_test");
        assertEquals(identifier23, "test_789");
        assertEquals(identifier24, "a_1_");
        assertEquals(identifier25, "a_1_789_test");
        assertEquals(identifier26, "a_1_null");
        assertEquals(identifier27, "test_789");
        assertEquals(identifier31, "test_123");
        assertEquals(identifier32, "aaa_789_test");
        assertEquals(identifier33, "test_789");
        assertEquals(identifier34, "aaa_");
        assertEquals(identifier35, "aaa_789_test");
        assertEquals(identifier36, "aaa_null");
        assertEquals(identifier37, "test_789");

    }

    @Test
    public void testToColumns() {
        List<String> list = Arrays.asList("1", null, null, "2", "a", "  34@#%%  ", "#$%^&a   a", null, null, "abc_", "abc_", "abc_啊--");
        List<String> columns1 = Identifier.toColumns(list);
        List<String> columns2 = Identifier.toColumns("abc_", list);
        List<String> columns3 = Identifier.toColumns("1abc_", list);
        System.out.println(columns1);
        System.out.println(columns2);
        System.out.println(columns3);

        List<String> convertedList1 = columns1.stream().map(e -> CaseConverter.LU_LC.convert(e)).collect(Collectors.toList());
        List<String> convertedList2 = columns2.stream().map(e -> CaseConverter.LU_LC.convert(e)).collect(Collectors.toList());
        List<String> convertedList3 = columns3.stream().map(e -> CaseConverter.LU_LC.convert(e)).collect(Collectors.toList());
        System.out.println(convertedList1);
        System.out.println(convertedList2);
        System.out.println(convertedList3);

        assertEquals(columns1.toString(), "[ext_1, ext_null, ext_null_1, ext_2, a, ext_34, a_a, ext_null_2, ext_null_3, abc_, abc__1, abc__2]");
        assertEquals(columns2.toString(), "[abc_1, abc_null, abc_null_1, abc_2, a, abc_34, a_a, abc_null_2, abc_null_3, abc_, abc__1, abc__2]");
        assertEquals(columns3.toString(), "[ext_1, ext_null, ext_null_1, ext_2, a, ext_34, a_a, ext_null_2, ext_null_3, abc_, abc__1, abc__2]");
        assertEquals(convertedList1.toString(), "[ext1, extNull, extNull1, ext2, a, ext34, aA, extNull2, extNull3, abc, abc1, abc2]");
        assertEquals(convertedList2.toString(), "[abc1, abcNull, abcNull1, abc2, a, abc34, aA, abcNull2, abcNull3, abc, abc1, abc2]");
        assertEquals(convertedList3.toString(), "[ext1, extNull, extNull1, ext2, a, ext34, aA, extNull2, extNull3, abc, abc1, abc2]");

    }

}
