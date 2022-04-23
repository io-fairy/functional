package com.iofairy.test;

import com.iofairy.string.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.iofairy.string.CaseConverter.*;
import static com.iofairy.string.StringCase.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author GG
 * @version 1.0
 */
public class StringCaseTest {

    @Test
    public void testStringCase() {
        String case01 = null;
        String case02 = "";
        String case03 = "id";
        String case04 = "iD";
        String case05 = "Id";
        String case06 = "ID";
        String case07 = "___  --__   ";
        String case08 = "aBcDeF";
        String case09 = "WorD_  woRd _WORD";
        String case10 = "!@#$abcDef%^&*()_";
        String case11 = "!@_#$_abc_Def%^&*()";
        String case12 = "a!@#$_abc_Def%^&*()";
        String case13 = " 6 a B cDe f ";
        String case14 = "567A B cDe f ";
        String case15 = "567a_B_cDef  ";
        String case16 = "567aAABcCCDef%^&";
        String case17 = "hello World";
        String case18 = "hello_World";
        String case19 = "hello-World";
        String case20 = "helloWorld";
        String case21 = "HelloWorld";
        CaseAutoConverter caseAuto1 = CaseAutoConverter.of(UPPER_HYPHEN);
        CaseAutoConverter caseAuto2 = CaseAutoConverter.of(UPPER_HYPHEN);
        CaseAutoConverter caseAuto3 = CaseAutoConverter.of(LOWER_UNDERSCORE);

        Map<String, String> ssMap = new HashMap<>();
        ssMap.put(case17, case20);
        ssMap.put(case18, case20);
        ssMap.put(case19, case20);
        ssMap.put(case21, case20);
        CaseMapConverter caseMap1 = CaseMapConverter.of(ssMap, false);
        CaseMapConverter caseMap2 = CaseMapConverter.of(ssMap, true);


        String newStr01 = convert(US_LC, case01);               // null
        String newStr02 = convert(UU_UC, case02);               // ""
        String newStr03 = convert(UC_UU, case03);               // ID
        String newStr04 = convert(UC_LH, case04);               // i-d
        String newStr05 = convert(LC_UC, case04);               // ID
        String newStr06 = convert(UC_LC, case05);               // id
        String newStr07 = convert(LC_UC, case05);               // Id
        String newStr08 = convert(US_LC, case06);               // id
        String newStr09 = convert(LC_LU, case06);               // i_d
        String newStr10 = convert(US_LC, case08);               // abcdef
        String newStr11 = convert(UU_UC, case08);               // Abcdef
        String newStr12 = convert(UC_LC, case08);               // aBcDeF
        String newStr13 = convert(LC_UC, case08);               // ABcDeF
        String newStr14 = convert(LS_LC, case09);               // word_ Word _word
        String newStr15 = convert(LS_UU, case09);               // WORD___WORD__WORD
        String newStr16 = convert(UU_UH, case09);               // WORD-  WORD -WORD
        String newStr17 = convert(UC_UU, case10);               // !@#$ABC_DEF%^&*()_
        String newStr18 = convert(LU_UC, case11);               // !@_#$AbcDef%^&*()
        String newStr19 = convert(UU_LS, case11);               // !@ #$ abc def%^&*()
        String newStr20 = convert(LU_LC, case12);               // a!@#$AbcDef%^&*()
        String newStr21 = convert(LU_UC, case12);               // A!@#$AbcDef%^&*()
        String newStr22 = convert(LS_UH, case13);               // -6-A-B-CDE-F-
        String newStr23 = convert(US_LU, case13);               // _6_a_b_cde_f_
        String newStr24 = convert(US_LC, case14);               // "567aBCdeF "
        String newStr25 = convert(US_UC, case14);               // "567aBCdeF "
        String newStr26 = convert(LU_UC, case15);               // "567aBCdef  "
        String newStr27 = convert(caseAuto1, case07);           // ___----__---
        String newStr28 = convert(caseAuto2, case09);           // WORD_--WORD-_WORD
        String newStr29 = convert(caseAuto2, case10);           // !@#$ABCDEF%^&*()-
        String newStr30 = convert(caseAuto3, case16);           // 567aaabcccdef%^&
        String newStr31 = convert(caseMap1, "hello World");     // helloWorld
        String newStr32 = convert(caseMap2, "hello World");     // helloWorld
        String newStr33 = convert(caseMap1, "heLLo world");     // heLLo world
        String newStr34 = convert(caseMap2, "heLLo world");     // helloWorld
        String newStr35 = convert(caseMap1, "helloworld");      // helloworld
        String newStr36 = convert(caseMap2, "helloworld");      // helloWorld
        String newStr37 = convert(caseMap1, null);              // null
        String newStr38 = convert(caseMap2, null);              // null
        String newStr39 = convert(caseMap1, "");                // ""
        String newStr40 = convert(caseMap2, "");                // ""
        String newStr41 = convert(UC_UU, case08);               // A_BC_DE_F
        String newStr42 = convert(UU_LC, case18);               // helloWorld
        String newStr43 = convert(LU_UC, case18);               // HelloWorld
        String newStr44 = convert(LS_UC, case17);               // HelloWorld
        String newStr45 = convert(US_LC, case17);               // helloWorld

        assertNull(newStr01);
        assertEquals(newStr02, "");
        assertEquals(newStr03, "ID");
        assertEquals(newStr04, "i-d");
        assertEquals(newStr05, "ID");
        assertEquals(newStr06, "id");
        assertEquals(newStr07, "Id");
        assertEquals(newStr08, "id");
        assertEquals(newStr09, "i_d");
        assertEquals(newStr10, "abcdef");
        assertEquals(newStr11, "Abcdef");
        assertEquals(newStr12, "aBcDeF");
        assertEquals(newStr13, "ABcDeF");
        assertEquals(newStr14, "word_ Word _word");
        assertEquals(newStr15, "WORD___WORD__WORD");
        assertEquals(newStr16, "WORD-  WORD -WORD");
        assertEquals(newStr17, "!@#$ABC_DEF%^&*()_");
        assertEquals(newStr18, "!@_#$AbcDef%^&*()");
        assertEquals(newStr19, "!@ #$ abc def%^&*()");
        assertEquals(newStr20, "a!@#$AbcDef%^&*()");
        assertEquals(newStr21, "A!@#$AbcDef%^&*()");
        assertEquals(newStr22, "-6-A-B-CDE-F-");
        assertEquals(newStr23, "_6_a_b_cde_f_");
        assertEquals(newStr24, "567aBCdeF ");
        assertEquals(newStr25, "567aBCdeF ");
        assertEquals(newStr26, "567aBCdef  ");
        assertEquals(newStr27, "___----__---");
        assertEquals(newStr28, "WORD_--WORD-_WORD");
        assertEquals(newStr29, "!@#$ABCDEF%^&*()-");
        assertEquals(newStr30, "567aaabcccdef%^&");
        assertEquals(newStr31, "helloWorld");
        assertEquals(newStr32, "helloWorld");
        assertEquals(newStr33, "heLLo world");
        assertEquals(newStr34, "helloWorld");
        assertEquals(newStr35, "helloworld");
        assertEquals(newStr36, "helloWorld");
        assertNull(newStr37);
        assertNull(newStr38);
        assertEquals(newStr39, "");
        assertEquals(newStr40, "");
        assertEquals(newStr41, "A_BC_DE_F");
        assertEquals(newStr42, "helloWorld");
        assertEquals(newStr43, "HelloWorld");
        assertEquals(newStr44, "HelloWorld");
        assertEquals(newStr45, "helloWorld");

        System.out.println(newStr01);
        System.out.println(newStr02);
        System.out.println(newStr03);
        System.out.println(newStr04);
        System.out.println(newStr05);
        System.out.println(newStr06);
        System.out.println(newStr07);
        System.out.println(newStr08);
        System.out.println(newStr09);
        System.out.println(newStr10);
        System.out.println(newStr11);
        System.out.println(newStr12);
        System.out.println(newStr13);
        System.out.println(newStr14);
        System.out.println(newStr15);
        System.out.println(newStr16);
        System.out.println(newStr17);
        System.out.println(newStr18);
        System.out.println(newStr19);
        System.out.println(newStr20);
        System.out.println(newStr21);
        System.out.println(newStr22);
        System.out.println(newStr23);
        System.out.println(newStr24);
        System.out.println(newStr25);
        System.out.println(newStr26);
        System.out.println(newStr27);
        System.out.println(newStr28);
        System.out.println(newStr29);
        System.out.println(newStr30);
        System.out.println(newStr31);
        System.out.println(newStr32);
        System.out.println(newStr33);
        System.out.println(newStr34);
        System.out.println(newStr35);
        System.out.println(newStr36);
        System.out.println(newStr37);
        System.out.println(newStr38);
        System.out.println(newStr39);
        System.out.println(newStr40);
        System.out.println(newStr41);
        System.out.println(newStr42);
        System.out.println(newStr43);
        System.out.println(newStr44);
        System.out.println(newStr45);
    }

    private String convert(StringConverter converter, String str) {
        return converter.convert(str);
    }
}
