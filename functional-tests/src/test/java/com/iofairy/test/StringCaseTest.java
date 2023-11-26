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
        String case22 = "___ abc  bcd &* aa ** --__   ";
        String case23 = "heLLo world";
        String case24 = "helloworld";
        String case25 = "aa_bb_中c_4a_中";

        CaseAutoConverter caseAuto1 = CaseAutoConverter.of(UPPER_HYPHEN);
        CaseAutoConverter caseAuto2 = CaseAutoConverter.of(UPPER_HYPHEN);
        CaseAutoConverter caseAuto3 = CaseAutoConverter.of(LOWER_UNDERSCORE);
        CaseAutoConverter caseAuto4 = CaseAutoConverter.of(UPPER_CAMEL);

        Map<String, String> ssMap = new HashMap<>();
        ssMap.put(case17, case20);      // hello World  -->  helloWorld
        ssMap.put(case18, case20);      // hello_World  -->  helloWorld
        ssMap.put(case19, case20);      // hello-World  -->  helloWorld
        ssMap.put(case21, case20);      // HelloWorld   -->  helloWorld
        CaseMapConverter caseMap1 = CaseMapConverter.of(ssMap, false);
        CaseMapConverter caseMap2 = CaseMapConverter.of(ssMap, true);


        String newStr01 = convert(US_LC, case01);
        String newStr02 = convert(UU_UC, case02);
        String newStr03 = convert(UC_UU, case03);
        String newStr04 = convert(UC_LH, case04);
        String newStr05 = convert(LC_UC, case04);
        String newStr06 = convert(UC_LC, case05);
        String newStr07 = convert(LC_UC, case05);
        String newStr08 = convert(US_LC, case06);
        String newStr09 = convert(LC_LU, case06);
        String newStr10 = convert(US_LC, case08);
        String newStr11 = convert(UU_UC, case08);
        String newStr12 = convert(UC_LC, case08);
        String newStr13 = convert(LC_UC, case08);
        String newStr14 = convert(LS_LC, case09);
        String newStr15 = convert(LS_UU, case09);
        String newStr16 = convert(UU_UH, case09);
        String newStr17 = convert(UC_UU, case10);
        String newStr18 = convert(LU_UC, case11);
        String newStr19 = convert(UU_LS, case11);
        String newStr20 = convert(LU_LC, case12);
        String newStr21 = convert(LU_UC, case12);
        String newStr22 = convert(LS_UH, case13);
        String newStr23 = convert(US_LU, case13);
        String newStr24 = convert(US_LC, case14);
        String newStr25 = convert(US_UC, case14);
        String newStr26 = convert(LU_UC, case15);
        String newStr27 = convert(caseAuto1, case07);
        String newStr28 = convert(caseAuto2, case09);
        String newStr29 = convert(caseAuto2, case10);
        String newStr30 = convert(caseAuto3, case16);
        String newStr31 = convert(caseMap1, case17);
        String newStr32 = convert(caseMap2, case17);
        String newStr33 = convert(caseMap1, case23);
        String newStr34 = convert(caseMap2, case23);
        String newStr35 = convert(caseMap1, case24);
        String newStr36 = convert(caseMap2, case24);
        String newStr37 = convert(caseMap1, case01);
        String newStr38 = convert(caseMap2, case01);
        String newStr39 = convert(caseMap1, case02);
        String newStr40 = convert(caseMap2, case02);
        String newStr41 = convert(UC_UU, case08);
        String newStr42 = convert(UU_LC, case18);
        String newStr43 = convert(LU_UC, case18);
        String newStr44 = convert(LS_UC, case17);
        String newStr45 = convert(US_LC, case17);
        String newStr46 = convert(caseAuto3, case07);
        String newStr47 = convert(caseAuto3, case22);
        String newStr48 = convert(caseAuto4, case07);
        String newStr49 = convert(caseAuto4, case22);
        String newStr50 = convert(AS_LC, case07);
        String newStr51 = convert(AS_LC, case22);
        String newStr52 = convert(AS_UC, case07);
        String newStr53 = convert(AS_UC, case22);
        String newStr54 = convert(LU_UC, case07);
        String newStr55 = convert(LU_UC, case22);
        String newStr56 = convert(LU_LC, case07);
        String newStr57 = convert(LU_LC, case22);
        String newStr58 = convert(LU_LH, case07);
        String newStr59 = convert(LU_LH, case22);
        String newStr60 = convert(LU_UH, case07);
        String newStr61 = convert(LU_UH, case22);
        String newStr62 = convert(AS_LC, case25);
        String newStr63 = convert(AS_UC, case25);
        String newStr64 = convert(LU_LC, case25);
        String newStr65 = convert(UU_UC, case25);
        String newStr66 = convert(AS_LU, case07);
        String newStr67 = convert(AS_UU, case22);
        String newStr68 = convert(AS_LS, case07);
        String newStr69 = convert(AS_US, case22);
        String newStr70 = convert(AS_LH, case07);
        String newStr71 = convert(AS_UH, case22);


        System.out.println("US_LC: <" + case01 + "> to <" + newStr01 + ">");            // US_LC: <null> to <null>
        System.out.println("UU_UC: <" + case02 + "> to <" + newStr02 + ">");            // UU_UC: <> to <>
        System.out.println("UC_UU: <" + case03 + "> to <" + newStr03 + ">");            // UC_UU: <id> to <ID>
        System.out.println("UC_LH: <" + case04 + "> to <" + newStr04 + ">");            // UC_LH: <iD> to <i-d>
        System.out.println("LC_UC: <" + case04 + "> to <" + newStr05 + ">");            // LC_UC: <iD> to <ID>
        System.out.println("UC_LC: <" + case05 + "> to <" + newStr06 + ">");            // UC_LC: <Id> to <id>
        System.out.println("LC_UC: <" + case05 + "> to <" + newStr07 + ">");            // LC_UC: <Id> to <Id>
        System.out.println("US_LC: <" + case06 + "> to <" + newStr08 + ">");            // US_LC: <ID> to <id>
        System.out.println("LC_LU: <" + case06 + "> to <" + newStr09 + ">");            // LC_LU: <ID> to <i_d>
        System.out.println("US_LC: <" + case08 + "> to <" + newStr10 + ">");            // US_LC: <aBcDeF> to <abcdef>
        System.out.println("UU_UC: <" + case08 + "> to <" + newStr11 + ">");            // UU_UC: <aBcDeF> to <abcdef>
        System.out.println("UC_LC: <" + case08 + "> to <" + newStr12 + ">");            // UC_LC: <aBcDeF> to <aBcDeF>
        System.out.println("LC_UC: <" + case08 + "> to <" + newStr13 + ">");            // LC_UC: <aBcDeF> to <ABcDeF>
        System.out.println("LS_LC: <" + case09 + "> to <" + newStr14 + ">");            // LS_LC: <WorD_  woRd _WORD> to <word_Word_word>
        System.out.println("LS_UU: <" + case09 + "> to <" + newStr15 + ">");            // LS_UU: <WorD_  woRd _WORD> to <WORD___WORD__WORD>
        System.out.println("UU_UH: <" + case09 + "> to <" + newStr16 + ">");            // UU_UH: <WorD_  woRd _WORD> to <WORD-  WORD -WORD>
        System.out.println("UC_UU: <" + case10 + "> to <" + newStr17 + ">");            // UC_UU: <!@#$abcDef%^&*()_> to <!@#$ABC_DEF%^&*()_>
        System.out.println("LU_UC: <" + case11 + "> to <" + newStr18 + ">");            // LU_UC: <!@_#$_abc_Def%^&*()> to <!@#$AbcDef%^&*()>
        System.out.println("UU_LS: <" + case11 + "> to <" + newStr19 + ">");            // UU_LS: <!@_#$_abc_Def%^&*()> to <!@ #$ abc def%^&*()>
        System.out.println("LU_LC: <" + case12 + "> to <" + newStr20 + ">");            // LU_LC: <a!@#$_abc_Def%^&*()> to <a!@#$AbcDef%^&*()>
        System.out.println("LU_UC: <" + case12 + "> to <" + newStr21 + ">");            // LU_UC: <a!@#$_abc_Def%^&*()> to <a!@#$AbcDef%^&*()>
        System.out.println("LS_UH: <" + case13 + "> to <" + newStr22 + ">");            // LS_UH: < 6 a B cDe f > to <-6-A-B-CDE-F->
        System.out.println("US_LU: <" + case13 + "> to <" + newStr23 + ">");            // US_LU: < 6 a B cDe f > to <_6_a_b_cde_f_>
        System.out.println("US_LC: <" + case14 + "> to <" + newStr24 + ">");            // US_LC: <567A B cDe f > to <567aBCdeF>
        System.out.println("US_UC: <" + case14 + "> to <" + newStr25 + ">");            // US_UC: <567A B cDe f > to <567aBCdeF>
        System.out.println("LU_UC: <" + case15 + "> to <" + newStr26 + ">");            // LU_UC: <567a_B_cDef  > to <567aBCdef  >
        System.out.println("caseAuto1: <" + case07 + "> to <" + newStr27 + ">");        // caseAuto1: <___  --__   > to <___----__--->
        System.out.println("caseAuto2: <" + case09 + "> to <" + newStr28 + ">");        // caseAuto2: <WorD_  woRd _WORD> to <WORD_--WORD-_WORD>
        System.out.println("caseAuto2: <" + case10 + "> to <" + newStr29 + ">");        // caseAuto2: <!@#$abcDef%^&*()_> to <!@#$ABCDEF%^&*()->
        System.out.println("caseAuto3: <" + case16 + "> to <" + newStr30 + ">");        // caseAuto3: <567aAABcCCDef%^&> to <567aaabcccdef%^&>
        System.out.println("caseMap1: <" + case17 + "> to <" + newStr31 + ">");         // caseMap1: <hello World> to <helloWorld>
        System.out.println("caseMap2: <" + case17 + "> to <" + newStr32 + ">");         // caseMap2: <hello World> to <helloWorld>
        System.out.println("caseMap1: <" + case23 + "> to <" + newStr33 + ">");         // caseMap1: <heLLo world> to <heLLo world>
        System.out.println("caseMap2: <" + case23 + "> to <" + newStr34 + ">");         // caseMap2: <heLLo world> to <helloWorld>
        System.out.println("caseMap1: <" + case24 + "> to <" + newStr35 + ">");         // caseMap1: <helloworld> to <helloworld>
        System.out.println("caseMap2: <" + case24 + "> to <" + newStr36 + ">");         // caseMap2: <helloworld> to <helloWorld>
        System.out.println("caseMap1: <" + case01 + "> to <" + newStr37 + ">");         // caseMap1: <null> to <null>
        System.out.println("caseMap2: <" + case01 + "> to <" + newStr38 + ">");         // caseMap2: <null> to <null>
        System.out.println("caseMap1: <" + case02 + "> to <" + newStr39 + ">");         // caseMap1: <> to <>
        System.out.println("caseMap2: <" + case02 + "> to <" + newStr40 + ">");         // caseMap2: <> to <>
        System.out.println("UC_UU: <" + case08 + "> to <" + newStr41 + ">");            // UC_UU: <aBcDeF> to <A_BC_DE_F>
        System.out.println("UU_LC: <" + case18 + "> to <" + newStr42 + ">");            // UU_LC: <hello_World> to <helloWorld>
        System.out.println("LU_UC: <" + case18 + "> to <" + newStr43 + ">");            // LU_UC: <hello_World> to <helloWorld>
        System.out.println("LS_UC: <" + case17 + "> to <" + newStr44 + ">");            // LS_UC: <hello World> to <helloWorld>
        System.out.println("US_LC: <" + case17 + "> to <" + newStr45 + ">");            // US_LC: <hello World> to <helloWorld>
        System.out.println("caseAuto3: <" + case07 + "> to <" + newStr46 + ">");        // caseAuto3: <___  --__   > to <_____--_____>
        System.out.println("caseAuto3: <" + case22 + "> to <" + newStr47 + ">");        // caseAuto3: <___ abc  bcd &* aa ** --__   > to <____abc__bcd_&*_aa_**_--_____>
        System.out.println("caseAuto4: <" + case07 + "> to <" + newStr48 + ">");        // caseAuto4: <___  --__   > to <>
        System.out.println("caseAuto4: <" + case22 + "> to <" + newStr49 + ">");        // caseAuto4: <___ abc  bcd &* aa ** --__   > to <AbcBcd&*Aa**>
        System.out.println("AS_LC: <" + case07 + "> to <" + newStr50 + ">");            // AS_LC: <___  --__   > to <>
        System.out.println("AS_LC: <" + case22 + "> to <" + newStr51 + ">");            // AS_LC: <___ abc  bcd &* aa ** --__   > to <abcBcd&*Aa**>
        System.out.println("AS_UC: <" + case07 + "> to <" + newStr52 + ">");            // AS_UC: <___  --__   > to <>
        System.out.println("AS_UC: <" + case22 + "> to <" + newStr53 + ">");            // AS_UC: <___ abc  bcd &* aa ** --__   > to <AbcBcd&*Aa**>
        System.out.println("LU_UC: <" + case07 + "> to <" + newStr54 + ">");            // LU_UC: <___  --__   > to <  --   >
        System.out.println("LU_UC: <" + case22 + "> to <" + newStr55 + ">");            // LU_UC: <___ abc  bcd &* aa ** --__   > to < abc  bcd &* aa ** --   >
        System.out.println("LU_LC: <" + case07 + "> to <" + newStr56 + ">");            // LU_LC: <___  --__   > to <  --   >
        System.out.println("LU_LC: <" + case22 + "> to <" + newStr57 + ">");            // LU_LC: <___ abc  bcd &* aa ** --__   > to < abc  bcd &* aa ** --   >
        System.out.println("LU_LH: <" + case07 + "> to <" + newStr58 + ">");            // LU_LH: <___  --__   > to <---  ----   >
        System.out.println("LU_LH: <" + case22 + "> to <" + newStr59 + ">");            // LU_LH: <___ abc  bcd &* aa ** --__   > to <--- abc  bcd &* aa ** ----   >
        System.out.println("LU_UH: <" + case07 + "> to <" + newStr60 + ">");            // LU_UH: <___  --__   > to <---  ----   >
        System.out.println("LU_UH: <" + case22 + "> to <" + newStr61 + ">");            // LU_UH: <___ abc  bcd &* aa ** --__   > to <--- ABC  BCD &* AA ** ----   >
        System.out.println("AS_LC: <" + case25 + "> to <" + newStr62 + ">");            // AS_LC: <aa_bb_中c_4a_中> to <aaBb中c4a中>
        System.out.println("AS_UC: <" + case25 + "> to <" + newStr63 + ">");            // AS_UC: <aa_bb_中c_4a_中> to <AaBb中c4a中>
        System.out.println("LU_LC: <" + case25 + "> to <" + newStr64 + ">");            // LU_LC: <aa_bb_中c_4a_中> to <aaBb中c4a中>
        System.out.println("UU_UC: <" + case25 + "> to <" + newStr65 + ">");            // UU_UC: <aa_bb_中c_4a_中> to <AaBb中c4a中>
        System.out.println("AS_LU: <" + case07 + "> to <" + newStr66 + ">");            // AS_LU: <___  --__   > to <____________>
        System.out.println("AS_UU: <" + case22 + "> to <" + newStr67 + ">");            // AS_UU: <___ abc  bcd &* aa ** --__   > to <____ABC__BCD_&*_AA_**________>
        System.out.println("AS_LS: <" + case07 + "> to <" + newStr68 + ">");            // AS_LS: <___  --__   > to <            >
        System.out.println("AS_US: <" + case22 + "> to <" + newStr69 + ">");            // AS_US: <___ abc  bcd &* aa ** --__   > to <    ABC  BCD &* AA **        >
        System.out.println("AS_LH: <" + case07 + "> to <" + newStr70 + ">");            // AS_LH: <___  --__   > to <------------>
        System.out.println("AS_UH: <" + case22 + "> to <" + newStr71 + ">");            // AS_UH: <___ abc  bcd &* aa ** --__   > to <----ABC--BCD-&*-AA-**-------->

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
        assertEquals(newStr14, "word_Word_word");
        assertEquals(newStr15, "WORD___WORD__WORD");
        assertEquals(newStr16, "WORD-  WORD -WORD");
        assertEquals(newStr17, "!@#$ABC_DEF%^&*()_");
        assertEquals(newStr18, "!@#$AbcDef%^&*()");
        assertEquals(newStr19, "!@ #$ abc def%^&*()");
        assertEquals(newStr20, "a!@#$AbcDef%^&*()");
        assertEquals(newStr21, "A!@#$AbcDef%^&*()");
        assertEquals(newStr22, "-6-A-B-CDE-F-");
        assertEquals(newStr23, "_6_a_b_cde_f_");
        assertEquals(newStr24, "567aBCdeF");
        assertEquals(newStr25, "567aBCdeF");
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
        assertEquals(newStr46, "_____--_____");
        assertEquals(newStr47, "____abc__bcd_&*_aa_**_--_____");
        assertEquals(newStr48, "");
        assertEquals(newStr49, "AbcBcd&*Aa**");
        assertEquals(newStr50, "");
        assertEquals(newStr51, "abcBcd&*Aa**");
        assertEquals(newStr52, "");
        assertEquals(newStr53, "AbcBcd&*Aa**");
        assertEquals(newStr54, "  --   ");
        assertEquals(newStr55, " abc  bcd &* aa ** --   ");
        assertEquals(newStr56, "  --   ");
        assertEquals(newStr57, " abc  bcd &* aa ** --   ");
        assertEquals(newStr58, "---  ----   ");
        assertEquals(newStr59, "--- abc  bcd &* aa ** ----   ");
        assertEquals(newStr60, "---  ----   ");
        assertEquals(newStr61, "--- ABC  BCD &* AA ** ----   ");
        assertEquals(newStr62, "aaBb中c4a中");
        assertEquals(newStr63, "AaBb中c4a中");
        assertEquals(newStr64, "aaBb中c4a中");
        assertEquals(newStr65, "AaBb中c4a中");
        assertEquals(newStr66, "____________");
        assertEquals(newStr67, "____ABC__BCD_&*_AA_**________");
        assertEquals(newStr68, "            ");
        assertEquals(newStr69, "    ABC  BCD &* AA **        ");
        assertEquals(newStr70, "------------");
        assertEquals(newStr71, "----ABC--BCD-&*-AA-**--------");


        try {
            String newString = UPPER_CAMEL.to(ALL_SEPARATORS, "abcDef");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Parameter `toCase` cannot be `StringCase.ALL_SEPARATORS`! ");
        }

    }

    private String convert(StringConverter converter, String str) {
        return converter.convert(str);
    }


}
