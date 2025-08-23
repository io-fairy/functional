package com.iofairy.test.time;

import com.iofairy.time.DateTimePattern;
import com.iofairy.time.Stopwatch;
import com.iofairy.tcf.Try;
import com.iofairy.top.G;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author GG
 * @version 1.0
 */
public class DateTimePatternTest {
    @Test
    public void testForDate() {
        String forSDF01 = DateTimePattern.forSDF("2020年1月2日6时6分6秒6毫秒");     // yyyy年MM月dd日HH时mm分ss秒SSS毫秒
        String forSDF02 = DateTimePattern.forSDF("1月2日6时6分6秒6毫秒");      // MM月dd日HH时mm分ss秒SSS毫秒
        String forSDF03 = DateTimePattern.forSDF("2020年1月2日6时6分6秒");        // yyyy年MM月dd日HH时mm分ss秒
        String forSDF04 = DateTimePattern.forSDF("2020年1月2日6点6分");      // yyyy年MM月dd日HH点mm分
        String forSDF05 = DateTimePattern.forSDF("999.1.2 6:6:6.6");        // yyyy.MM.dd HH:mm:ss.SSS
        String forSDF06 = DateTimePattern.forSDF("2020-1-2 6:6:6.6");       // yyyy-MM-dd HH:mm:ss.SSS
        String forSDF07 = DateTimePattern.forSDF("2020/1/2 6:6:6.6");       // yyyy/MM/dd HH:mm:ss.SSS
        String forSDF08 = DateTimePattern.forSDF("2020-01-02 06:06:06");        // yyyy-MM-dd HH:mm:ss
        String forSDF09 = DateTimePattern.forSDF("999-1-2 6:6:6");      // yyyy-MM-dd HH:mm:ss
        String forSDF10 = DateTimePattern.forSDF("01.2 6:6:6.6");       // MM.dd HH:mm:ss.SSS
        String forSDF11 = DateTimePattern.forSDF("01-2 6:6:6.6");       // MM-dd HH:mm:ss.SSS
        String forSDF12 = DateTimePattern.forSDF("6:6.6");      // mm:ss.SSS
        String forSDF13 = DateTimePattern.forSDF("6:6.655");        // mm:ss.SSS
        String forSDF14 = DateTimePattern.forSDF("20200102060606006");      // yyyyMMddHHmmssSSS
        String forSDF15 = DateTimePattern.forSDF("20200102060606");     // yyyyMMddHHmmss
        String forSDF16 = DateTimePattern.forSDF("999");        // yyyy
        String forSDF17 = DateTimePattern.forSDF("2020");       // yyyy
        String forSDF18 = DateTimePattern.forSDF("2020年1月2日");      // yyyy年MM月dd日
        String forSDF19 = DateTimePattern.forSDF("2020年01月02日");        // yyyy年MM月dd日
        String forSDF20 = DateTimePattern.forSDF("1月2日6时6分6秒6毫秒");      // MM月dd日HH时mm分ss秒SSS毫秒
        String forSDF21 = DateTimePattern.forSDF("1月2日6点6分6秒6毫秒");      // MM月dd日HH点mm分ss秒SSS毫秒
        String forSDF22 = DateTimePattern.forSDF("2020-01-02");     // yyyy-MM-dd
        String forSDF23 = DateTimePattern.forSDF("999-1-2");        // yyyy-MM-dd
        String forSDF24 = DateTimePattern.forSDF("2020.01.02");     // yyyy.MM.dd
        String forSDF25 = DateTimePattern.forSDF("999.1.2");        // yyyy.MM.dd
        String forSDF26 = DateTimePattern.forSDF("06:06:6");        // HH:mm:ss
        String forSDF27 = DateTimePattern.forSDF("6:6:6");      // HH:mm:ss
        String forSDF28 = DateTimePattern.forSDF("1.2");        // MM.dd
        String forSDF29 = DateTimePattern.forSDF("01.02");      // MM.dd
        String forSDF30 = DateTimePattern.forSDF("2020/1/2 6:6.:6.6");      // null
        String forSDF31 = DateTimePattern.forSDF("20201266665");        // null
        String forSDF32 = DateTimePattern.forSDF("99");     // null
        String forSDF33 = DateTimePattern.forSDF("yyyy年MM月dd日HH时mm分ss秒SSS毫秒");      // null
        String forSDF34 = DateTimePattern.forSDF("yyyy年MM月dd日HH时mm分ss秒SSSS毫秒");     // null
        String forSDF35 = DateTimePattern.forSDF("9990807T100501");     // null
        String forSDF36 = DateTimePattern.forSDF("19990807T100501");        // yyyyMMdd'T'HHmmss
        String forSDF37 = DateTimePattern.forSDF("2020/1/2T6:06:6.6");      // yyyy/MM/dd'T'HH:mm:ss.SSS
        String forSDF38 = DateTimePattern.forSDF("999/1/2T6:6:06.6");       // yyyy/MM/dd'T'HH:mm:ss.SSS
        String forSDF39 = DateTimePattern.forSDF("20/1/2T6:06:6.6");        // null
        String forSDF40 = DateTimePattern.forSDF("2020-1-2T6:06:6.6");      // yyyy-MM-dd'T'HH:mm:ss.SSS
        String forSDF41 = DateTimePattern.forSDF("999-1-2T6:06:6.6");       // yyyy-MM-dd'T'HH:mm:ss.SSS
        String forSDF42 = DateTimePattern.forSDF("20-1-2T06:6:6.6");        // null
        String forSDF43 = DateTimePattern.forSDF("2020.1.2T16:6:6.96");     // yyyy.MM.dd'T'HH:mm:ss.SSS
        String forSDF44 = DateTimePattern.forSDF("999.1.2T16:6:6.6");       // yyyy.MM.dd'T'HH:mm:ss.SSS
        String forSDF45 = DateTimePattern.forSDF("20.1.2T16:6:6.6");        // null
        String forSDF46 = DateTimePattern.forSDF("2020/1/2T6:06:6");        // yyyy/MM/dd'T'HH:mm:ss
        String forSDF47 = DateTimePattern.forSDF("999/1/2T6:6:06");     // yyyy/MM/dd'T'HH:mm:ss
        String forSDF48 = DateTimePattern.forSDF("20/1/2T6:06:6");      // null
        String forSDF49 = DateTimePattern.forSDF("2020-1-2T6:06:6");        // yyyy-MM-dd'T'HH:mm:ss
        String forSDF50 = DateTimePattern.forSDF("999-1-2T6:06:6");     // yyyy-MM-dd'T'HH:mm:ss
        String forSDF51 = DateTimePattern.forSDF("20-1-2T06:6:6");      // null
        String forSDF52 = DateTimePattern.forSDF("2020.1.2T16:6:6");        // yyyy.MM.dd'T'HH:mm:ss
        String forSDF53 = DateTimePattern.forSDF("999.1.2T16:6:6");     // yyyy.MM.dd'T'HH:mm:ss
        String forSDF54 = DateTimePattern.forSDF("20.1.2T16:6:6");      // null
        String forSDF55 = DateTimePattern.forSDF("2020.1.2T16:6");      // null
        String forSDF56 = DateTimePattern.forSDF("20090807T100501550");        // yyyyMMdd'T'HHmmssSSS
        String forSDF57 = DateTimePattern.forSDF("09990803T221500007");        // yyyyMMdd'T'HHmmssSSS

        // System.out.println("forSDF01: " + forSDF01);
        // System.out.println("forSDF02: " + forSDF02);
        // System.out.println("forSDF03: " + forSDF03);
        // System.out.println("forSDF04: " + forSDF04);
        // System.out.println("forSDF05: " + forSDF05);
        // System.out.println("forSDF06: " + forSDF06);
        // System.out.println("forSDF07: " + forSDF07);
        // System.out.println("forSDF08: " + forSDF08);
        // System.out.println("forSDF09: " + forSDF09);
        // System.out.println("forSDF10: " + forSDF10);
        // System.out.println("forSDF11: " + forSDF11);
        // System.out.println("forSDF12: " + forSDF12);
        // System.out.println("forSDF13: " + forSDF13);
        // System.out.println("forSDF14: " + forSDF14);
        // System.out.println("forSDF15: " + forSDF15);
        // System.out.println("forSDF16: " + forSDF16);
        // System.out.println("forSDF17: " + forSDF17);
        // System.out.println("forSDF18: " + forSDF18);
        // System.out.println("forSDF19: " + forSDF19);
        // System.out.println("forSDF20: " + forSDF20);
        // System.out.println("forSDF21: " + forSDF21);
        // System.out.println("forSDF22: " + forSDF22);
        // System.out.println("forSDF23: " + forSDF23);
        // System.out.println("forSDF24: " + forSDF24);
        // System.out.println("forSDF25: " + forSDF25);
        // System.out.println("forSDF26: " + forSDF26);
        // System.out.println("forSDF27: " + forSDF27);
        // System.out.println("forSDF28: " + forSDF28);
        // System.out.println("forSDF29: " + forSDF29);
        // System.out.println("forSDF30: " + forSDF30);
        // System.out.println("forSDF31: " + forSDF31);
        // System.out.println("forSDF32: " + forSDF32);
        // System.out.println("forSDF33: " + forSDF33);
        // System.out.println("forSDF34: " + forSDF34);
        // System.out.println("forSDF35: " + forSDF35);
        // System.out.println("forSDF36: " + forSDF36);
        // System.out.println("forSDF37: " + forSDF37);
        // System.out.println("forSDF38: " + forSDF38);
        // System.out.println("forSDF39: " + forSDF39);
        // System.out.println("forSDF40: " + forSDF40);
        // System.out.println("forSDF41: " + forSDF41);
        // System.out.println("forSDF42: " + forSDF42);
        // System.out.println("forSDF43: " + forSDF43);
        // System.out.println("forSDF44: " + forSDF44);
        // System.out.println("forSDF45: " + forSDF45);
        // System.out.println("forSDF46: " + forSDF46);
        // System.out.println("forSDF47: " + forSDF47);
        // System.out.println("forSDF48: " + forSDF48);
        // System.out.println("forSDF49: " + forSDF49);
        // System.out.println("forSDF50: " + forSDF50);
        // System.out.println("forSDF51: " + forSDF51);
        // System.out.println("forSDF52: " + forSDF52);
        // System.out.println("forSDF53: " + forSDF53);
        // System.out.println("forSDF54: " + forSDF54);
        // System.out.println("forSDF55: " + forSDF55);
        // System.out.println("forSDF56: " + forSDF56);

        String sdfFormat01 = sdfFormat("yyyy年MM月dd日HH时mm分ss秒SSS毫秒", "2020年1月2日6时6分6秒6毫秒");          // 2020-01-02 06:06:06.006
        String sdfFormat02 = sdfFormat("MM月dd日HH时mm分ss秒SSS毫秒", "1月2日6时6分6秒6毫秒");            // 1970-01-02 06:06:06.006
        String sdfFormat03 = sdfFormat("yyyy年MM月dd日HH时mm分ss秒", "2020年1月2日6时6分6秒");          // 2020-01-02 06:06:06.000
        String sdfFormat04 = sdfFormat("yyyy年MM月dd日HH点mm分", "2020年1月2日6点6分");           // 2020-01-02 06:06:00.000
        String sdfFormat05 = sdfFormat("yyyy.MM.dd HH:mm:ss.SSS", "999.1.2 6:6:6.6");           // 0999-01-02 06:06:06.006
        String sdfFormat06 = sdfFormat("yyyy-MM-dd HH:mm:ss.SSS", "2020-1-2 6:6:6.6");          // 2020-01-02 06:06:06.006
        String sdfFormat07 = sdfFormat("yyyy/MM/dd HH:mm:ss.SSS", "2020/1/2 6:6:6.6");          // 2020-01-02 06:06:06.006
        String sdfFormat08 = sdfFormat("yyyy-MM-dd HH:mm:ss", "2020-01-02 06:06:06");           // 2020-01-02 06:06:06.000
        String sdfFormat09 = sdfFormat("yyyy-MM-dd HH:mm:ss", "999-1-2 6:6:6");         // 0999-01-02 06:06:06.000
        String sdfFormat10 = sdfFormat("MM.dd HH:mm:ss.SSS", "01.2 6:6:6.6");           // 1970-01-02 06:06:06.006
        String sdfFormat11 = sdfFormat("MM-dd HH:mm:ss.SSS", "01-2 6:6:6.6");           // 1970-01-02 06:06:06.006
        String sdfFormat12 = sdfFormat("mm:ss.SSS", "6:6.6");           // 1970-01-01 00:06:06.006
        String sdfFormat13 = sdfFormat("mm:ss.SSS", "6:6.655");         // 1970-01-01 00:06:06.655
        String sdfFormat14 = sdfFormat("yyyyMMddHHmmssSSS", "20200102060606006");           // 2020-01-02 06:06:06.006
        String sdfFormat15 = sdfFormat("yyyyMMddHHmmss", "20200102060606");         // 2020-01-02 06:06:06.000
        String sdfFormat16 = sdfFormat("yyyy", "999");          // 0999-01-01 00:00:00.000
        String sdfFormat17 = sdfFormat("yyyy", "2020");         // 2020-01-01 00:00:00.000
        String sdfFormat18 = sdfFormat("yyyy年MM月dd日", "2020年1月2日");         // 2020-01-02 00:00:00.000
        String sdfFormat19 = sdfFormat("yyyy年MM月dd日", "2020年01月02日");           // 2020-01-02 00:00:00.000
        String sdfFormat20 = sdfFormat("MM月dd日HH时mm分ss秒SSS毫秒", "1月2日6时6分6秒6毫秒");            // 1970-01-02 06:06:06.006
        String sdfFormat21 = sdfFormat("MM月dd日HH点mm分ss秒SSS毫秒", "1月2日6点6分6秒6毫秒");            // 1970-01-02 06:06:06.006
        String sdfFormat22 = sdfFormat("yyyy-MM-dd", "2020-01-02");         // 2020-01-02 00:00:00.000
        String sdfFormat23 = sdfFormat("yyyy-MM-dd", "999-1-2");            // 0999-01-02 00:00:00.000
        String sdfFormat24 = sdfFormat("yyyy.MM.dd", "2020.01.02");         // 2020-01-02 00:00:00.000
        String sdfFormat25 = sdfFormat("yyyy.MM.dd", "999.1.2");            // 0999-01-02 00:00:00.000
        String sdfFormat26 = sdfFormat("HH:mm:ss", "06:06:6");          // 1970-01-01 06:06:06.000
        String sdfFormat27 = sdfFormat("HH:mm:ss", "6:6:6");            // 1970-01-01 06:06:06.000
        String sdfFormat28 = sdfFormat("MM.dd", "1.2");         // 1970-01-02 00:00:00.000
        String sdfFormat29 = sdfFormat("MM.dd", "01.02");           // 1970-01-02 00:00:00.000
        String sdfFormat30 = sdfFormat(null, "2020/1/2 6:6.:6.6");          // null
        String sdfFormat31 = sdfFormat(null, "20201266665");            // null
        String sdfFormat32 = sdfFormat(null, "99");         // null
        String sdfFormat33 = sdfFormat(null, "yyyy年MM月dd日HH时mm分ss秒SSS毫秒");          // null
        String sdfFormat34 = sdfFormat(null, "yyyy年MM月dd日HH时mm分ss秒SSSS毫秒");         // null
        String sdfFormat35 = sdfFormat(null, "9990807T100501");         // null
        String sdfFormat36 = sdfFormat("yyyyMMdd'T'HHmmss", "19990807T100501");         // 1999-08-07 10:05:01.000
        String sdfFormat37 = sdfFormat("yyyy/MM/dd'T'HH:mm:ss.SSS", "2020/1/2T6:06:6.6");           // 2020-01-02 06:06:06.006
        String sdfFormat38 = sdfFormat("yyyy/MM/dd'T'HH:mm:ss.SSS", "999/1/2T6:6:06.6");            // 0999-01-02 06:06:06.006
        String sdfFormat39 = sdfFormat(null, "20/1/2T6:06:6.6");            // null
        String sdfFormat40 = sdfFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", "2020-1-2T6:06:6.6");           // 2020-01-02 06:06:06.006
        String sdfFormat41 = sdfFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", "999-1-2T6:06:6.6");            // 0999-01-02 06:06:06.006
        String sdfFormat42 = sdfFormat(null, "20-1-2T06:6:6.6");            // null
        String sdfFormat43 = sdfFormat("yyyy.MM.dd'T'HH:mm:ss.SSS", "2020.1.2T16:6:6.96");          // 2020-01-02 16:06:06.096
        String sdfFormat44 = sdfFormat("yyyy.MM.dd'T'HH:mm:ss.SSS", "999.1.2T16:6:6.6");            // 0999-01-02 16:06:06.006
        String sdfFormat45 = sdfFormat(null, "20.1.2T16:6:6.6");            // null
        String sdfFormat46 = sdfFormat("yyyy/MM/dd'T'HH:mm:ss", "2020/1/2T6:06:6");         // 2020-01-02 06:06:06.000
        String sdfFormat47 = sdfFormat("yyyy/MM/dd'T'HH:mm:ss", "999/1/2T6:6:06");          // 0999-01-02 06:06:06.000
        String sdfFormat48 = sdfFormat(null, "20/1/2T6:06:6");          // null
        String sdfFormat49 = sdfFormat("yyyy-MM-dd'T'HH:mm:ss", "2020-1-2T6:06:6");         // 2020-01-02 06:06:06.000
        String sdfFormat50 = sdfFormat("yyyy-MM-dd'T'HH:mm:ss", "999-1-2T6:06:6");          // 0999-01-02 06:06:06.000
        String sdfFormat51 = sdfFormat(null, "20-1-2T06:6:6");          // null
        String sdfFormat52 = sdfFormat("yyyy.MM.dd'T'HH:mm:ss", "2020.1.2T16:6:6");         // 2020-01-02 16:06:06.000
        String sdfFormat53 = sdfFormat("yyyy.MM.dd'T'HH:mm:ss", "999.1.2T16:6:6");          // 0999-01-02 16:06:06.000
        String sdfFormat54 = sdfFormat(null, "20.1.2T16:6:6");          // null
        String sdfFormat55 = sdfFormat(null, "2020.1.2T16:6");          // null
        String sdfFormat56 = sdfFormat("yyyyMMdd'T'HHmmssSSS", "20090807T100501550");           // 2009-08-07 10:05:01.550
        String sdfFormat57 = sdfFormat("yyyyMMdd'T'HHmmssSSS", "09990803T221500007");           // 0999-08-03 22:15:00.007

        // System.out.println("sdfFormat01: " + sdfFormat01);
        // System.out.println("sdfFormat02: " + sdfFormat02);
        // System.out.println("sdfFormat03: " + sdfFormat03);
        // System.out.println("sdfFormat04: " + sdfFormat04);
        // System.out.println("sdfFormat05: " + sdfFormat05);
        // System.out.println("sdfFormat06: " + sdfFormat06);
        // System.out.println("sdfFormat07: " + sdfFormat07);
        // System.out.println("sdfFormat08: " + sdfFormat08);
        // System.out.println("sdfFormat09: " + sdfFormat09);
        // System.out.println("sdfFormat10: " + sdfFormat10);
        // System.out.println("sdfFormat11: " + sdfFormat11);
        // System.out.println("sdfFormat12: " + sdfFormat12);
        // System.out.println("sdfFormat13: " + sdfFormat13);
        // System.out.println("sdfFormat14: " + sdfFormat14);
        // System.out.println("sdfFormat15: " + sdfFormat15);
        // System.out.println("sdfFormat16: " + sdfFormat16);
        // System.out.println("sdfFormat17: " + sdfFormat17);
        // System.out.println("sdfFormat18: " + sdfFormat18);
        // System.out.println("sdfFormat19: " + sdfFormat19);
        // System.out.println("sdfFormat20: " + sdfFormat20);
        // System.out.println("sdfFormat21: " + sdfFormat21);
        // System.out.println("sdfFormat22: " + sdfFormat22);
        // System.out.println("sdfFormat23: " + sdfFormat23);
        // System.out.println("sdfFormat24: " + sdfFormat24);
        // System.out.println("sdfFormat25: " + sdfFormat25);
        // System.out.println("sdfFormat26: " + sdfFormat26);
        // System.out.println("sdfFormat27: " + sdfFormat27);
        // System.out.println("sdfFormat28: " + sdfFormat28);
        // System.out.println("sdfFormat29: " + sdfFormat29);
        // System.out.println("sdfFormat30: " + sdfFormat30);
        // System.out.println("sdfFormat31: " + sdfFormat31);
        // System.out.println("sdfFormat32: " + sdfFormat32);
        // System.out.println("sdfFormat33: " + sdfFormat33);
        // System.out.println("sdfFormat34: " + sdfFormat34);
        // System.out.println("sdfFormat35: " + sdfFormat35);
        // System.out.println("sdfFormat36: " + sdfFormat36);
        // System.out.println("sdfFormat37: " + sdfFormat37);
        // System.out.println("sdfFormat38: " + sdfFormat38);
        // System.out.println("sdfFormat39: " + sdfFormat39);
        // System.out.println("sdfFormat40: " + sdfFormat40);
        // System.out.println("sdfFormat41: " + sdfFormat41);
        // System.out.println("sdfFormat42: " + sdfFormat42);
        // System.out.println("sdfFormat43: " + sdfFormat43);
        // System.out.println("sdfFormat44: " + sdfFormat44);
        // System.out.println("sdfFormat45: " + sdfFormat45);
        // System.out.println("sdfFormat46: " + sdfFormat46);
        // System.out.println("sdfFormat47: " + sdfFormat47);
        // System.out.println("sdfFormat48: " + sdfFormat48);
        // System.out.println("sdfFormat49: " + sdfFormat49);
        // System.out.println("sdfFormat50: " + sdfFormat50);
        // System.out.println("sdfFormat51: " + sdfFormat51);
        // System.out.println("sdfFormat52: " + sdfFormat52);
        // System.out.println("sdfFormat53: " + sdfFormat53);
        // System.out.println("sdfFormat54: " + sdfFormat54);
        // System.out.println("sdfFormat55: " + sdfFormat55);
        // System.out.println("sdfFormat56: " + sdfFormat56);
        // System.out.println("sdfFormat57: " + sdfFormat57);

        assertEquals("2020-01-02 06:06:06.006", sdfFormat01);
        assertEquals("1970-01-02 06:06:06.006", sdfFormat02);
        assertEquals("2020-01-02 06:06:06.000", sdfFormat03);
        assertEquals("2020-01-02 06:06:00.000", sdfFormat04);
        assertEquals("0999-01-02 06:06:06.006", sdfFormat05);
        assertEquals("2020-01-02 06:06:06.006", sdfFormat06);
        assertEquals("2020-01-02 06:06:06.006", sdfFormat07);
        assertEquals("2020-01-02 06:06:06.000", sdfFormat08);
        assertEquals("0999-01-02 06:06:06.000", sdfFormat09);
        assertEquals("1970-01-02 06:06:06.006", sdfFormat10);
        assertEquals("1970-01-02 06:06:06.006", sdfFormat11);
        assertEquals("1970-01-01 00:06:06.006", sdfFormat12);
        assertEquals("1970-01-01 00:06:06.655", sdfFormat13);
        assertEquals("2020-01-02 06:06:06.006", sdfFormat14);
        assertEquals("2020-01-02 06:06:06.000", sdfFormat15);
        assertEquals("0999-01-01 00:00:00.000", sdfFormat16);
        assertEquals("2020-01-01 00:00:00.000", sdfFormat17);
        assertEquals("2020-01-02 00:00:00.000", sdfFormat18);
        assertEquals("2020-01-02 00:00:00.000", sdfFormat19);
        assertEquals("1970-01-02 06:06:06.006", sdfFormat20);
        assertEquals("1970-01-02 06:06:06.006", sdfFormat21);
        assertEquals("2020-01-02 00:00:00.000", sdfFormat22);
        assertEquals("0999-01-02 00:00:00.000", sdfFormat23);
        assertEquals("2020-01-02 00:00:00.000", sdfFormat24);
        assertEquals("0999-01-02 00:00:00.000", sdfFormat25);
        assertEquals("1970-01-01 06:06:06.000", sdfFormat26);
        assertEquals("1970-01-01 06:06:06.000", sdfFormat27);
        assertEquals("1970-01-02 00:00:00.000", sdfFormat28);
        assertEquals("1970-01-02 00:00:00.000", sdfFormat29);
        assertNull(sdfFormat30);
        assertNull(sdfFormat31);
        assertNull(sdfFormat32);
        assertNull(sdfFormat33);
        assertNull(sdfFormat34);
        assertNull(sdfFormat35);
        assertEquals("1999-08-07 10:05:01.000", sdfFormat36);
        assertEquals("2020-01-02 06:06:06.006", sdfFormat37);
        assertEquals("0999-01-02 06:06:06.006", sdfFormat38);
        assertNull(sdfFormat39);
        assertEquals("2020-01-02 06:06:06.006", sdfFormat40);
        assertEquals("0999-01-02 06:06:06.006", sdfFormat41);
        assertNull(sdfFormat42);
        assertEquals("2020-01-02 16:06:06.096", sdfFormat43);
        assertEquals("0999-01-02 16:06:06.006", sdfFormat44);
        assertNull(sdfFormat45);
        assertEquals("2020-01-02 06:06:06.000", sdfFormat46);
        assertEquals("0999-01-02 06:06:06.000", sdfFormat47);
        assertNull(sdfFormat48);
        assertEquals("2020-01-02 06:06:06.000", sdfFormat49);
        assertEquals("0999-01-02 06:06:06.000", sdfFormat50);
        assertNull(sdfFormat51);
        assertEquals("2020-01-02 16:06:06.000", sdfFormat52);
        assertEquals("0999-01-02 16:06:06.000", sdfFormat53);
        assertNull(sdfFormat54);
        assertNull(sdfFormat55);
        assertEquals("2009-08-07 10:05:01.550", sdfFormat56);
        assertEquals("0999-08-03 22:15:00.007", sdfFormat57);

    }

    public static String sdfFormat(String expectValue, String dateTime) {
        String format = DateTimePattern.forSDF(dateTime);
        if (expectValue == null) {
            assertNull(format);
            return null;
        } else {
            assertEquals(expectValue, format);
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = Try.tcf(() -> sdf.parse(dateTime), true);
        if (date == null) return null;
        return sdf1.format(date);
    }

    @Test
    public void testforLocalDT() {
        String forDTF01 = DateTimePattern.forDTF("2020年1月2日6时6分6秒6毫秒");     // null
        String forDTF02 = DateTimePattern.forDTF("1月2日6时6分6秒6毫秒");      // null
        String forDTF03 = DateTimePattern.forDTF("2020年1月2日6时6分6秒");        // y年M月d日H时m分s秒
        String forDTF04 = DateTimePattern.forDTF("2020年1月2日6点6分");      // y年M月d日H点m分
        String forDTF05 = DateTimePattern.forDTF("999.1.2 6:6:6.6");        // y.M.d H:m:s[.SSS][.SS][.S]
        String forDTF06 = DateTimePattern.forDTF("2020-1-2 6:6:6.6");       // y-M-d H:m:s[.SSS][.SS][.S]
        String forDTF07 = DateTimePattern.forDTF("2020/1/2 6:6:6.6");       // y/M/d H:m:s[.SSS][.SS][.S]
        String forDTF08 = DateTimePattern.forDTF("2020-01-02 06:06:06");        // y-M-d H:m:s
        String forDTF09 = DateTimePattern.forDTF("999-1-2 6:6:6");              // y-M-d H:m:s
        String forDTF10 = DateTimePattern.forDTF("01.2 6:6:6.6");               // null
        String forDTF11 = DateTimePattern.forDTF("01-2 6:6:6.6");               // null
        String forDTF12 = DateTimePattern.forDTF("6:6.6");          // null
        String forDTF13 = DateTimePattern.forDTF("6:6.655");        // null
        String forDTF14 = DateTimePattern.forDTF("20200102060606006");      // yyyyMMddHHmmssSSS
        String forDTF15 = DateTimePattern.forDTF("20200102060606");     // yyyyMMddHHmmss
        String forDTF16 = DateTimePattern.forDTF("999");        // y
        String forDTF17 = DateTimePattern.forDTF("2020");       // y
        String forDTF18 = DateTimePattern.forDTF("2020年1月2日");      // y年M月d日
        String forDTF19 = DateTimePattern.forDTF("2020年01月02日");        // y年M月d日
        String forDTF20 = DateTimePattern.forDTF("1月2日6时6分6秒6毫秒");      // null
        String forDTF21 = DateTimePattern.forDTF("1月2日6点6分6秒6毫秒");      // null
        String forDTF22 = DateTimePattern.forDTF("2020-01-02");         // yyyy-MM-dd
        String forDTF23 = DateTimePattern.forDTF("999-1-2");            // y-M-d
        String forDTF24 = DateTimePattern.forDTF("2020.01.02");         // y.M.d
        String forDTF25 = DateTimePattern.forDTF("999.1.2");            // y.M.d
        String forDTF26 = DateTimePattern.forDTF("06:06:6");            // H:m:s
        String forDTF27 = DateTimePattern.forDTF("6:6:6");              // H:m:s
        String forDTF28 = DateTimePattern.forDTF("1.2");                // null
        String forDTF29 = DateTimePattern.forDTF("01.02");              // null
        String forDTF30 = DateTimePattern.forDTF("2020/1/2 6:6.:6.6");      // null
        String forDTF31 = DateTimePattern.forDTF("20201266665");        // null
        String forDTF32 = DateTimePattern.forDTF("99");     // null
        String forDTF33 = DateTimePattern.forDTF("yyyy年MM月dd日HH时mm分ss秒SSS毫秒");      // null
        String forDTF34 = DateTimePattern.forDTF("yyyy年MM月dd日HH时mm分ss秒SSSS毫秒");     // null
        String forDTF35 = DateTimePattern.forDTF("9990807T100501");     // null
        String forDTF36 = DateTimePattern.forDTF("19990807T100501");            // yyyyMMdd'T'HHmmss
        String forDTF37 = DateTimePattern.forDTF("2020/1/2T6:06:6.6");          // y/M/d'T'H:m:s[.SSS][.SS][.S]
        String forDTF38 = DateTimePattern.forDTF("999/1/2T6:6:06.6");           // y/M/d'T'H:m:s[.SSS][.SS][.S]
        String forDTF39 = DateTimePattern.forDTF("20/1/2T6:06:6.6");        // null
        String forDTF40 = DateTimePattern.forDTF("2020-1-2T6:06:6.6");          // y-M-d'T'H:m:s[.SSS][.SS][.S]
        String forDTF41 = DateTimePattern.forDTF("999-1-2T6:06:6.6");           // y-M-d'T'H:m:s[.SSS][.SS][.S]
        String forDTF42 = DateTimePattern.forDTF("20-1-2T06:6:6.6");        // null
        String forDTF43 = DateTimePattern.forDTF("2020.1.2T16:6:6.96");         // y.M.d'T'H:m:s[.SSS][.SS][.S]
        String forDTF44 = DateTimePattern.forDTF("999.1.2T16:6:6.6");           // y.M.d'T'H:m:s[.SSS][.SS][.S]
        String forDTF45 = DateTimePattern.forDTF("20.1.2T16:6:6.6");        // null
        String forDTF46 = DateTimePattern.forDTF("2020/1/2T6:06:6");            // y/M/d'T'H:m:s
        String forDTF47 = DateTimePattern.forDTF("999/1/2T6:6:06");             // y/M/d'T'H:m:s
        String forDTF48 = DateTimePattern.forDTF("20/1/2T6:06:6");      // null
        String forDTF49 = DateTimePattern.forDTF("2020-1-2T6:06:6");            // y-M-d'T'H:m:s
        String forDTF50 = DateTimePattern.forDTF("999-1-2T6:06:6");             // y-M-d'T'H:m:s
        String forDTF51 = DateTimePattern.forDTF("20-1-2T06:6:6");      // null
        String forDTF52 = DateTimePattern.forDTF("2020.1.2T16:6:6");            // y.M.d'T'H:m:s
        String forDTF53 = DateTimePattern.forDTF("999.1.2T6:6:6");             // y.M.d'T'H:m:s
        String forDTF54 = DateTimePattern.forDTF("20.1.2T16:6:6");      // null
        String forDTF55 = DateTimePattern.forDTF("2020.1.2T16:6");      // null
        String forDTF56 = DateTimePattern.forDTF("20090807T100501550");         // yyyyMMdd'T'HHmmssSSS
        String forDTF57 = DateTimePattern.forDTF("09990803T221500007");         // yyyyMMdd'T'HHmmssSSS
        String forDTF58 = DateTimePattern.forDTF("2020年1月2日6时6分6秒006毫秒");     // y年M月d日H时m分s秒[SSS毫秒]
        String forDTF59 = DateTimePattern.forDTF("2020年1月2日6点6分6秒060毫秒");     // y年M月d日H点m分s秒[SSS毫秒]
        String forDTF60 = DateTimePattern.forDTF("6时6分6秒006毫秒");     // H时m分s秒[SSS毫秒]
        String forDTF61 = DateTimePattern.forDTF("6点6分6秒060毫秒");     // H点m分s秒[SSS毫秒]
        String forDTF62 = DateTimePattern.forDTF("2020/01/2T6:16:6Z");        // y/M/d'T'H:m:sVV
        String forDTF63 = DateTimePattern.forDTF("2020-01-2T6:16:6Z");        // y-M-d'T'H:m:sVV
        String forDTF64 = DateTimePattern.forDTF("2020.01.2T6:16:6Z");        // y.M.d'T'H:m:sVV
        String forDTF65 = DateTimePattern.forDTF("999/01/2T6:16:6.6Z");      // y/M/d'T'H:m:s[.SSS][.SS][.S]VV
        String forDTF66 = DateTimePattern.forDTF("2020-01-2T6:16:6.6Z");      // y-M-d'T'H:m:s[.SSS][.SS][.S]VV
        String forDTF67 = DateTimePattern.forDTF("2020.01.2T6:16:6.6Z");      // y.M.d'T'H:m:s[.SSS][.SS][.S]VV
        String forDTF68 = DateTimePattern.forDTF("2020/01/2T6:16:6.65Z");     // y/M/d'T'H:m:s[.SSS][.SS][.S]VV
        String forDTF69 = DateTimePattern.forDTF("2020-01-2T6:16:6.65Z");     // y-M-d'T'H:m:s[.SSS][.SS][.S]VV
        String forDTF70 = DateTimePattern.forDTF("2020.01.2T6:16:6.65Z");     // y.M.d'T'H:m:s[.SSS][.SS][.S]VV
        String forDTF71 = DateTimePattern.forDTF("2020/01/2T6:16:6.036Z");    // y/M/d'T'H:m:s[.SSS][.SS][.S]VV
        String forDTF72 = DateTimePattern.forDTF("2020-01-2T6:16:6.036Z");    // y-M-d'T'H:m:s[.SSS][.SS][.S]VV
        String forDTF73 = DateTimePattern.forDTF("2020.01.2T6:16:6.036Z");    // y.M.d'T'H:m:s[.SSS][.SS][.S]VV
        String forDTF74 = DateTimePattern.forDTF("2020-01-02T06:16:26.036Z"); // y-M-d'T'H:m:s[.SSS][.SS][.S]VV
        String forDTF75 = DateTimePattern.forDTF("2020.01.02T06:16:26.036Z"); // y.M.d'T'H:m:s[.SSS][.SS][.S]VV
        String forDTF76 = DateTimePattern.forDTF("999-1-2 6:6:6 [GMT+04:00 +04:00]");               //
        String forDTF77 = DateTimePattern.forDTF("999/1/2 6:6:6.6 [Asia/Shanghai +08:00]");         //
        String forDTF78 = DateTimePattern.forDTF("2025-01-02 06:16:26.036 [Asia/Shanghai +08:00]"); //
        String forDTF79 = DateTimePattern.forDTF("999-1-2 6:6:6 [+04:00]");                         //
        String forDTF80 = DateTimePattern.forDTF("999.1.2 6:6:6.6 [+08:00]");                       //
        String forDTF81 = DateTimePattern.forDTF("2025-01-02 06:16:26.036 [+08:00]");               //
        String forDTF82 = DateTimePattern.forDTF("2025.01.02 06:16:26.036 [+08:00]");               //
        String forDTF83 = DateTimePattern.forDTF("2025/01/02 06:16:26.036 [+08:00 +08:00]");        //
        String forDTF84 = DateTimePattern.forDTF("2025.01.02 06:16:26.036 [GMT+04:00 +04:00]");     //

        // System.out.println("forDTF01: " + forDTF01);
        // System.out.println("forDTF02: " + forDTF02);
        // System.out.println("forDTF03: " + forDTF03);
        // System.out.println("forDTF04: " + forDTF04);
        // System.out.println("forDTF05: " + forDTF05);
        // System.out.println("forDTF06: " + forDTF06);
        // System.out.println("forDTF07: " + forDTF07);
        // System.out.println("forDTF08: " + forDTF08);
        // System.out.println("forDTF09: " + forDTF09);
        // System.out.println("forDTF10: " + forDTF10);
        // System.out.println("forDTF11: " + forDTF11);
        // System.out.println("forDTF12: " + forDTF12);
        // System.out.println("forDTF13: " + forDTF13);
        // System.out.println("forDTF14: " + forDTF14);
        // System.out.println("forDTF15: " + forDTF15);
        // System.out.println("forDTF16: " + forDTF16);
        // System.out.println("forDTF17: " + forDTF17);
        // System.out.println("forDTF18: " + forDTF18);
        // System.out.println("forDTF19: " + forDTF19);
        // System.out.println("forDTF20: " + forDTF20);
        // System.out.println("forDTF21: " + forDTF21);
        // System.out.println("forDTF22: " + forDTF22);
        // System.out.println("forDTF23: " + forDTF23);
        // System.out.println("forDTF24: " + forDTF24);
        // System.out.println("forDTF25: " + forDTF25);
        // System.out.println("forDTF26: " + forDTF26);
        // System.out.println("forDTF27: " + forDTF27);
        // System.out.println("forDTF28: " + forDTF28);
        // System.out.println("forDTF29: " + forDTF29);
        // System.out.println("forDTF30: " + forDTF30);
        // System.out.println("forDTF31: " + forDTF31);
        // System.out.println("forDTF32: " + forDTF32);
        // System.out.println("forDTF33: " + forDTF33);
        // System.out.println("forDTF34: " + forDTF34);
        // System.out.println("forDTF35: " + forDTF35);
        // System.out.println("forDTF36: " + forDTF36);
        // System.out.println("forDTF37: " + forDTF37);
        // System.out.println("forDTF38: " + forDTF38);
        // System.out.println("forDTF39: " + forDTF39);
        // System.out.println("forDTF40: " + forDTF40);
        // System.out.println("forDTF41: " + forDTF41);
        // System.out.println("forDTF42: " + forDTF42);
        // System.out.println("forDTF43: " + forDTF43);
        // System.out.println("forDTF44: " + forDTF44);
        // System.out.println("forDTF45: " + forDTF45);
        // System.out.println("forDTF46: " + forDTF46);
        // System.out.println("forDTF47: " + forDTF47);
        // System.out.println("forDTF48: " + forDTF48);
        // System.out.println("forDTF49: " + forDTF49);
        // System.out.println("forDTF50: " + forDTF50);
        // System.out.println("forDTF51: " + forDTF51);
        // System.out.println("forDTF52: " + forDTF52);
        // System.out.println("forDTF53: " + forDTF53);
        // System.out.println("forDTF54: " + forDTF54);
        // System.out.println("forDTF55: " + forDTF55);
        // System.out.println("forDTF56: " + forDTF56);
        // System.out.println("forDTF57: " + forDTF57);
        // System.out.println("forDTF58: " + forDTF58);
        // System.out.println("forDTF59: " + forDTF59);
        // System.out.println("forDTF60: " + forDTF60);
        // System.out.println("forDTF61: " + forDTF61);
        // System.out.println("forDTF62: " + forDTF62);
        // System.out.println("forDTF63: " + forDTF63);
        // System.out.println("forDTF64: " + forDTF64);
        // System.out.println("forDTF65: " + forDTF65);
        // System.out.println("forDTF66: " + forDTF66);
        // System.out.println("forDTF67: " + forDTF67);
        // System.out.println("forDTF68: " + forDTF68);
        // System.out.println("forDTF69: " + forDTF69);
        // System.out.println("forDTF70: " + forDTF70);
        // System.out.println("forDTF71: " + forDTF71);
        // System.out.println("forDTF72: " + forDTF72);
        // System.out.println("forDTF73: " + forDTF73);
        // System.out.println("forDTF76: " + forDTF76);
        // System.out.println("forDTF77: " + forDTF77);
        // System.out.println("forDTF78: " + forDTF78);
        // System.out.println("forDTF79: " + forDTF79);
        // System.out.println("forDTF80: " + forDTF80);
        // System.out.println("forDTF81: " + forDTF81);
        // System.out.println("forDTF82: " + forDTF82);
        // System.out.println("forDTF83: " + forDTF83);
        // System.out.println("forDTF84: " + forDTF84);
        System.out.println("============================================================");

        String dtfFormat01 = dtfFormat(null, "2020年1月2日6时6分6秒6毫秒");   // null
        String dtfFormat02 = dtfFormat(null, "1月2日6时6分6秒6毫秒");        // null
        String dtfFormat03 = dtfFormat("y年M月d日H时m分s秒", "2020年1月2日6时6分6秒");      // 2020-01-02 06:06:06.000
        String dtfFormat04 = dtfFormat("y年M月d日H点m分", "2020年1月2日6点6分");        // 2020-01-02 06:06:00.000
        String dtfFormat05 = dtfFormat("y.M.d H:m:s[.SSS][.SS][.S]", "999.1.2 6:6:6.6");      // 0999-01-02 06:06:06.600
        String dtfFormat06 = dtfFormat("y-M-d H:m:s[.SSS][.SS][.S]", "2020-1-2 6:6:6.6");     // 2020-01-02 06:06:06.600
        String dtfFormat07 = dtfFormat("y/M/d H:m:s[.SSS][.SS][.S]", "2020/1/2 6:6:6.6");     // 2020-01-02 06:06:06.600
        String dtfFormat08 = dtfFormat("y-M-d H:m:s", "2020-01-02 06:06:06");  // 2020-01-02 06:06:06.000
        String dtfFormat09 = dtfFormat("y-M-d H:m:s", "999-1-2 6:6:6");        // 0999-01-02 06:06:06.000
        String dtfFormat10 = dtfFormat(null, "01.2 6:6:6.6");         // null
        String dtfFormat11 = dtfFormat(null, "01-2 6:6:6.6");         // null
        String dtfFormat12 = dtfFormat(null, "6:6.6");        // null
        String dtfFormat13 = dtfFormat(null, "6:6.655");  // null
        String dtfFormat14 = dtfFormat("yyyyMMddHHmmssSSS", "20200102060606006");    // 2020-01-02 06:06:06.006
        String dtfFormat15 = dtfFormat("yyyyMMddHHmmss", "20200102060606");       // 2020-01-02 06:06:06.000
        String dtfFormat16 = dtfFormat("y", "999");          // 0999-01-01 00:00:00.000
        String dtfFormat17 = dtfFormat("y", "2020");         // 2020-01-01 00:00:00.000
        String dtfFormat18 = dtfFormat("y年M月d日", "2020年1月2日");        // 2020-01-02 00:00:00.000
        String dtfFormat19 = dtfFormat("y年M月d日", "2020年01月02日");          // 2020-01-02 00:00:00.000
        String dtfFormat20 = dtfFormat(null, "1月2日6时6分6秒6毫秒");        // null
        String dtfFormat21 = dtfFormat(null, "1月2日6点6分6秒6毫秒");        // null
        String dtfFormat22 = dtfFormat("yyyy-MM-dd", "2020-01-02");   // 2020-01-02 00:00:00.000
        String dtfFormat23 = dtfFormat("y-M-d", "999-1-2");      // 0999-01-02 00:00:00.000
        String dtfFormat24 = dtfFormat("y.M.d", "2020.01.02");   // 2020-01-02 00:00:00.000
        String dtfFormat25 = dtfFormat("y.M.d", "999.1.2");      // 0999-01-02 00:00:00.000
        String dtfFormat26 = dtfFormat("H:m:s", "06:06:6");      // 1970-01-01 06:06:06.000
        String dtfFormat27 = dtfFormat("H:m:s", "6:6:6");        // 1970-01-01 06:06:06.000
        String dtfFormat28 = dtfFormat(null, "1.2");          // null
        String dtfFormat29 = dtfFormat(null, "01.02");        // null
        String dtfFormat30 = dtfFormat(null, "2020/1/2 6:6.:6.6");    // null
        String dtfFormat31 = dtfFormat(null, "20201266665");          // null
        String dtfFormat32 = dtfFormat(null, "99");           // null
        String dtfFormat33 = dtfFormat(null, "yyyy年MM月dd日HH时mm分ss秒SSS毫秒");        // null
        String dtfFormat34 = dtfFormat(null, "yyyy年MM月dd日HH时mm分ss秒SSSS毫秒");       // null
        String dtfFormat35 = dtfFormat(null, "9990807T100501");           // null
        String dtfFormat36 = dtfFormat("yyyyMMdd'T'HHmmss", "19990807T100501");          // 1999-08-07 10:05:01.000
        String dtfFormat37 = dtfFormat("y/M/d'T'H:m:s[.SSS][.SS][.S]", "2020/1/2T6:06:6.6");        // 2020-01-02 06:06:06.600
        String dtfFormat38 = dtfFormat("y/M/d'T'H:m:s[.SSS][.SS][.S]", "999/1/2T6:6:06.6");         // 0999-01-02 06:06:06.600
        String dtfFormat39 = dtfFormat(null, "20/1/2T6:06:6.6");          // null
        String dtfFormat40 = dtfFormat("y-M-d'T'H:m:s[.SSS][.SS][.S]", "2020-1-2T6:06:6.6");        // 2020-01-02 06:06:06.600
        String dtfFormat41 = dtfFormat("y-M-d'T'H:m:s[.SSS][.SS][.S]", "999-1-2T6:06:6.6");         // 0999-01-02 06:06:06.600
        String dtfFormat42 = dtfFormat(null, "20-1-2T06:6:6.6");          // null
        String dtfFormat43 = dtfFormat("y.M.d'T'H:m:s[.SSS][.SS][.S]", "2020.1.2T16:6:6.96");       // 2020-01-02 16:06:06.960
        String dtfFormat44 = dtfFormat("y.M.d'T'H:m:s[.SSS][.SS][.S]", "999.1.2T16:6:6.6");         // 0999-01-02 16:06:06.600
        String dtfFormat45 = dtfFormat(null, "20.1.2T16:6:6.6");          // null
        String dtfFormat46 = dtfFormat("y/M/d'T'H:m:s", "2020/1/2T6:06:6");          // 2020-01-02 06:06:06.000
        String dtfFormat47 = dtfFormat("y/M/d'T'H:m:s", "999/1/2T6:6:06");           // 0999-01-02 06:06:06.000
        String dtfFormat48 = dtfFormat(null, "20/1/2T6:06:6");            // null
        String dtfFormat49 = dtfFormat("y-M-d'T'H:m:s", "2020-1-2T6:06:6");          // 2020-01-02 06:06:06.000
        String dtfFormat50 = dtfFormat("y-M-d'T'H:m:s", "999-1-2T6:06:6");           // 0999-01-02 06:06:06.000
        String dtfFormat51 = dtfFormat(null, "20-1-2T06:6:6");            // null
        String dtfFormat52 = dtfFormat("y.M.d'T'H:m:s", "2020.1.2T16:6:6");          // 2020-01-02 16:06:06.000
        String dtfFormat53 = dtfFormat("y.M.d'T'H:m:s", "999.1.2T6:6:6");           // 0999-01-02 16:06:06.000
        String dtfFormat54 = dtfFormat(null, "20.1.2T16:6:6");            // null
        String dtfFormat55 = dtfFormat(null, "2020.1.2T16:6");            // null
        String dtfFormat56 = dtfFormat("yyyyMMdd'T'HHmmssSSS", "20090807T100501550");       // 2009-08-07 10:05:01.550
        String dtfFormat57 = dtfFormat("yyyyMMdd'T'HHmmssSSS", "09990803T221500007");       // 0999-08-03 22:15:00.007
        String dtfFormat58 = dtfFormat("y年M月d日H时m分s秒[SSS毫秒]", "2020年1月2日6时6分6秒006毫秒");       // 2020-01-02 06:06:06.006
        String dtfFormat59 = dtfFormat("y年M月d日H点m分s秒[SSS毫秒]", "2020年1月2日6点6分6秒060毫秒");       // 2020-01-02 06:06:06.060
        String dtfFormat60 = dtfFormat("H时m分s秒[SSS毫秒]", "6时6分6秒006毫秒");       // 1970-01-01 06:06:06.006
        String dtfFormat61 = dtfFormat("H点m分s秒[SSS毫秒]", "6点6分6秒060毫秒");       // 1970-01-01 06:06:06.060
        String dtfFormat62 = dtfFormat("y/M/d'T'H:m:sVV", "2020/01/2T6:16:6Z");                        // 2020-01-02 06:16:06.000 [Z +00:00]
        String dtfFormat63 = dtfFormat("y-M-d'T'H:m:sVV", "2020-01-2T6:16:6Z");                        // 2020-01-02 06:16:06.000 [Z +00:00]
        String dtfFormat64 = dtfFormat("y.M.d'T'H:m:sVV", "2020.01.2T6:16:6Z");                        // 2020-01-02 06:16:06.000 [Z +00:00]
        String dtfFormat65 = dtfFormat("y/M/d'T'H:m:s[.SSS][.SS][.S]VV", "999/01/2T6:16:6.6Z");        // 999-01-02 06:16:06.600 [Z +00:00]
        String dtfFormat66 = dtfFormat("y-M-d'T'H:m:s[.SSS][.SS][.S]VV", "2020-01-2T6:16:6.6Z");       // 2020-01-02 06:16:06.600 [Z +00:00]
        String dtfFormat67 = dtfFormat("y.M.d'T'H:m:s[.SSS][.SS][.S]VV", "2020.01.2T6:16:6.6Z");       // 2020-01-02 06:16:06.600 [Z +00:00]
        String dtfFormat68 = dtfFormat("y/M/d'T'H:m:s[.SSS][.SS][.S]VV", "2020/01/2T6:16:6.65Z");      // 2020-01-02 06:16:06.650 [Z +00:00]
        String dtfFormat69 = dtfFormat("y-M-d'T'H:m:s[.SSS][.SS][.S]VV", "2020-01-2T6:16:6.65Z");      // 2020-01-02 06:16:06.650 [Z +00:00]
        String dtfFormat70 = dtfFormat("y.M.d'T'H:m:s[.SSS][.SS][.S]VV", "2020.01.2T6:16:6.65Z");      // 2020-01-02 06:16:06.650 [Z +00:00]
        String dtfFormat71 = dtfFormat("y/M/d'T'H:m:s[.SSS][.SS][.S]VV", "2020/01/2T6:16:6.036Z");     // 2020-01-02 06:16:06.036 [Z +00:00]
        String dtfFormat72 = dtfFormat("y-M-d'T'H:m:s[.SSS][.SS][.S]VV", "2020-01-2T6:16:6.036Z");     // 2020-01-02 06:16:06.036 [Z +00:00]
        String dtfFormat73 = dtfFormat("y.M.d'T'H:m:s[.SSS][.SS][.S]VV", "2020.01.2T6:16:6.036Z");     // 2020-01-02 06:16:06.036 [Z +00:00]
        String dtfFormat74 = dtfFormat("y-M-d'T'H:m:s[.SSS][.SS][.S]VV", "2020-01-02T06:16:26.536Z");  // 2020-01-02 06:16:26.536 [Z +00:00]
        String dtfFormat75 = dtfFormat("y.M.d'T'H:m:s[.SSS][.SS][.S]VV", "2020.01.02T06:16:26.536Z");  // 2020-01-02 06:16:26.536 [Z +00:00]
        String dtfFormat76 = dtfFormat("y-M-d H:m:s '['VV xxx']'", "999-1-2 6:6:6 [GMT+04:00 +04:00]");
        String dtfFormat77 = dtfFormat("y/M/d H:m:s[.SSS][.SS][.S] '['VV xxx']'", "999/1/2 6:6:6.6 [Asia/Shanghai +08:00]");
        String dtfFormat78 = dtfFormat("y-M-d H:m:s[.SSS][.SS][.S] '['VV xxx']'", "2025-01-02 06:16:26.036 [Asia/Shanghai +08:00]");
        String dtfFormat79 = dtfFormat("y-M-d H:m:s '['xxx']'", "999-1-2 6:6:6 [+04:00]");
        String dtfFormat80 = dtfFormat("y.M.d H:m:s[.SSS][.SS][.S] '['xxx']'", "999.1.2 6:6:6.6 [+08:00]");
        String dtfFormat81 = dtfFormat("y-M-d H:m:s[.SSS][.SS][.S] '['xxx']'", "2025-01-02 06:16:26.036 [+08:00]");
        String dtfFormat82 = dtfFormat("y.M.d H:m:s[.SSS][.SS][.S] '['xxx']'", "2025.01.02 06:16:26.036 [+08:00]");
        String dtfFormat83 = dtfFormat("y/M/d H:m:s[.SSS][.SS][.S] '['VV xxx']'", "2025/01/02 06:16:26.036 [+08:00 +08:00]");
        String dtfFormat84 = dtfFormat("y.M.d H:m:s[.SSS][.SS][.S] '['VV xxx']'", "2025.01.02 06:16:26.036 [GMT+04:00 +04:00]");

        System.out.println("dtfFormat01: " + dtfFormat01);
        System.out.println("dtfFormat02: " + dtfFormat02);
        System.out.println("dtfFormat03: " + dtfFormat03);
        System.out.println("dtfFormat04: " + dtfFormat04);
        System.out.println("dtfFormat05: " + dtfFormat05);
        System.out.println("dtfFormat06: " + dtfFormat06);
        System.out.println("dtfFormat07: " + dtfFormat07);
        System.out.println("dtfFormat08: " + dtfFormat08);
        System.out.println("dtfFormat09: " + dtfFormat09);
        System.out.println("dtfFormat10: " + dtfFormat10);
        System.out.println("dtfFormat11: " + dtfFormat11);
        System.out.println("dtfFormat12: " + dtfFormat12);
        System.out.println("dtfFormat13: " + dtfFormat13);
        System.out.println("dtfFormat14: " + dtfFormat14);
        System.out.println("dtfFormat15: " + dtfFormat15);
        System.out.println("dtfFormat16: " + dtfFormat16);
        System.out.println("dtfFormat17: " + dtfFormat17);
        System.out.println("dtfFormat18: " + dtfFormat18);
        System.out.println("dtfFormat19: " + dtfFormat19);
        System.out.println("dtfFormat20: " + dtfFormat20);
        System.out.println("dtfFormat21: " + dtfFormat21);
        System.out.println("dtfFormat22: " + dtfFormat22);
        System.out.println("dtfFormat23: " + dtfFormat23);
        System.out.println("dtfFormat24: " + dtfFormat24);
        System.out.println("dtfFormat25: " + dtfFormat25);
        System.out.println("dtfFormat26: " + dtfFormat26);
        System.out.println("dtfFormat27: " + dtfFormat27);
        System.out.println("dtfFormat28: " + dtfFormat28);
        System.out.println("dtfFormat29: " + dtfFormat29);
        System.out.println("dtfFormat30: " + dtfFormat30);
        System.out.println("dtfFormat31: " + dtfFormat31);
        System.out.println("dtfFormat32: " + dtfFormat32);
        System.out.println("dtfFormat33: " + dtfFormat33);
        System.out.println("dtfFormat34: " + dtfFormat34);
        System.out.println("dtfFormat35: " + dtfFormat35);
        System.out.println("dtfFormat36: " + dtfFormat36);
        System.out.println("dtfFormat37: " + dtfFormat37);
        System.out.println("dtfFormat38: " + dtfFormat38);
        System.out.println("dtfFormat39: " + dtfFormat39);
        System.out.println("dtfFormat40: " + dtfFormat40);
        System.out.println("dtfFormat41: " + dtfFormat41);
        System.out.println("dtfFormat42: " + dtfFormat42);
        System.out.println("dtfFormat43: " + dtfFormat43);
        System.out.println("dtfFormat44: " + dtfFormat44);
        System.out.println("dtfFormat45: " + dtfFormat45);
        System.out.println("dtfFormat46: " + dtfFormat46);
        System.out.println("dtfFormat47: " + dtfFormat47);
        System.out.println("dtfFormat48: " + dtfFormat48);
        System.out.println("dtfFormat49: " + dtfFormat49);
        System.out.println("dtfFormat50: " + dtfFormat50);
        System.out.println("dtfFormat51: " + dtfFormat51);
        System.out.println("dtfFormat52: " + dtfFormat52);
        System.out.println("dtfFormat53: " + dtfFormat53);
        System.out.println("dtfFormat54: " + dtfFormat54);
        System.out.println("dtfFormat55: " + dtfFormat55);
        System.out.println("dtfFormat56: " + dtfFormat56);
        System.out.println("dtfFormat57: " + dtfFormat57);
        System.out.println("dtfFormat58: " + dtfFormat58);
        System.out.println("dtfFormat59: " + dtfFormat59);
        System.out.println("dtfFormat60: " + dtfFormat60);
        System.out.println("dtfFormat61: " + dtfFormat61);
        System.out.println("dtfFormat62: " + dtfFormat62);
        System.out.println("dtfFormat63: " + dtfFormat63);
        System.out.println("dtfFormat64: " + dtfFormat64);
        System.out.println("dtfFormat65: " + dtfFormat65);
        System.out.println("dtfFormat66: " + dtfFormat66);
        System.out.println("dtfFormat67: " + dtfFormat67);
        System.out.println("dtfFormat68: " + dtfFormat68);
        System.out.println("dtfFormat69: " + dtfFormat69);
        System.out.println("dtfFormat70: " + dtfFormat70);
        System.out.println("dtfFormat71: " + dtfFormat71);
        System.out.println("dtfFormat72: " + dtfFormat72);
        System.out.println("dtfFormat73: " + dtfFormat73);
        System.out.println("dtfFormat74: " + dtfFormat74);
        System.out.println("dtfFormat75: " + dtfFormat75);
        System.out.println("dtfFormat76: " + dtfFormat76);
        System.out.println("dtfFormat77: " + dtfFormat77);
        System.out.println("dtfFormat78: " + dtfFormat78);
        System.out.println("dtfFormat79: " + dtfFormat79);
        System.out.println("dtfFormat80: " + dtfFormat80);
        System.out.println("dtfFormat81: " + dtfFormat81);
        System.out.println("dtfFormat82: " + dtfFormat82);
        System.out.println("dtfFormat83: " + dtfFormat83);
        System.out.println("dtfFormat84: " + dtfFormat84);

        assertEquals(null, dtfFormat01);
        assertEquals(null, dtfFormat02);
        assertEquals("2020-01-02 06:06:06.000", dtfFormat03);
        assertEquals("2020-01-02 06:06:00.000", dtfFormat04);
        assertEquals("999-01-02 06:06:06.600", dtfFormat05);
        assertEquals("2020-01-02 06:06:06.600", dtfFormat06);
        assertEquals("2020-01-02 06:06:06.600", dtfFormat07);
        assertEquals("2020-01-02 06:06:06.000", dtfFormat08);
        assertEquals("999-01-02 06:06:06.000", dtfFormat09);
        assertEquals(null, dtfFormat10);
        assertEquals(null, dtfFormat11);
        assertEquals(null, dtfFormat12);
        assertEquals(null, dtfFormat13);
        assertEquals("2020-01-02 06:06:06.006", dtfFormat14);
        assertEquals("2020-01-02 06:06:06.000", dtfFormat15);
        assertEquals("999-01-01 00:00:00.000", dtfFormat16);
        assertEquals("2020-01-01 00:00:00.000", dtfFormat17);
        assertEquals("2020-01-02 00:00:00.000", dtfFormat18);
        assertEquals("2020-01-02 00:00:00.000", dtfFormat19);
        assertEquals(null, dtfFormat20);
        assertEquals(null, dtfFormat21);
        assertEquals("2020-01-02 00:00:00.000", dtfFormat22);
        assertEquals("999-01-02 00:00:00.000", dtfFormat23);
        assertEquals("2020-01-02 00:00:00.000", dtfFormat24);
        assertEquals("999-01-02 00:00:00.000", dtfFormat25);
        assertEquals("1970-01-01 06:06:06.000", dtfFormat26);
        assertEquals("1970-01-01 06:06:06.000", dtfFormat27);
        assertEquals(null, dtfFormat28);
        assertEquals(null, dtfFormat29);
        assertNull(dtfFormat30);
        assertNull(dtfFormat31);
        assertNull(dtfFormat32);
        assertNull(dtfFormat33);
        assertNull(dtfFormat34);
        assertNull(dtfFormat35);
        assertEquals("1999-08-07 10:05:01.000", dtfFormat36);
        assertEquals("2020-01-02 06:06:06.600", dtfFormat37);
        assertEquals("999-01-02 06:06:06.600", dtfFormat38);
        assertNull(dtfFormat39);
        assertEquals("2020-01-02 06:06:06.600", dtfFormat40);
        assertEquals("999-01-02 06:06:06.600", dtfFormat41);
        assertNull(dtfFormat42);
        assertEquals("2020-01-02 16:06:06.960", dtfFormat43);
        assertEquals("999-01-02 16:06:06.600", dtfFormat44);
        assertNull(dtfFormat45);
        assertEquals("2020-01-02 06:06:06.000", dtfFormat46);
        assertEquals("999-01-02 06:06:06.000", dtfFormat47);
        assertNull(dtfFormat48);
        assertEquals("2020-01-02 06:06:06.000", dtfFormat49);
        assertEquals("999-01-02 06:06:06.000", dtfFormat50);
        assertNull(dtfFormat51);
        assertEquals("2020-01-02 16:06:06.000", dtfFormat52);
        assertEquals("999-01-02 06:06:06.000", dtfFormat53);
        assertNull(dtfFormat54);
        assertNull(dtfFormat55);
        assertEquals("2009-08-07 10:05:01.550", dtfFormat56);
        assertEquals("999-08-03 22:15:00.007", dtfFormat57);
        assertEquals("2020-01-02 06:06:06.006", dtfFormat58);
        assertEquals("2020-01-02 06:06:06.060", dtfFormat59);
        assertEquals("1970-01-01 06:06:06.006", dtfFormat60);
        assertEquals("1970-01-01 06:06:06.060", dtfFormat61);
        assertEquals("2020-01-02 06:16:06.000 [Z +00:00]", dtfFormat62);
        assertEquals("2020-01-02 06:16:06.000 [Z +00:00]", dtfFormat63);
        assertEquals("2020-01-02 06:16:06.000 [Z +00:00]", dtfFormat64);
        assertEquals( "999-01-02 06:16:06.600 [Z +00:00]",  dtfFormat65);
        assertEquals("2020-01-02 06:16:06.600 [Z +00:00]", dtfFormat66);
        assertEquals("2020-01-02 06:16:06.600 [Z +00:00]", dtfFormat67);
        assertEquals("2020-01-02 06:16:06.650 [Z +00:00]", dtfFormat68);
        assertEquals("2020-01-02 06:16:06.650 [Z +00:00]", dtfFormat69);
        assertEquals("2020-01-02 06:16:06.650 [Z +00:00]", dtfFormat70);
        assertEquals("2020-01-02 06:16:06.036 [Z +00:00]", dtfFormat71);
        assertEquals("2020-01-02 06:16:06.036 [Z +00:00]", dtfFormat72);
        assertEquals("2020-01-02 06:16:06.036 [Z +00:00]", dtfFormat73);
        assertEquals("2020-01-02 06:16:26.536 [Z +00:00]", dtfFormat74);
        assertEquals("2020-01-02 06:16:26.536 [Z +00:00]", dtfFormat75);
        assertEquals("999-01-02 06:06:06.000 [GMT+04:00 +04:00]", dtfFormat76);
        assertEquals("999-01-02 06:11:49.600", dtfFormat77);
        assertEquals("2025-01-02 06:16:26.036", dtfFormat78);
        assertEquals("999-01-02 06:06:06.000 [+04:00 +04:00]", dtfFormat79);
        assertEquals("999-01-02 06:06:06.600 [+08:00 +08:00]", dtfFormat80);
        assertEquals("2025-01-02 06:16:26.036 [+08:00 +08:00]", dtfFormat81);
        assertEquals("2025-01-02 06:16:26.036 [+08:00 +08:00]", dtfFormat82);
        assertEquals("2025-01-02 06:16:26.036 [+08:00 +08:00]", dtfFormat83);
        assertEquals("2025-01-02 06:16:26.036 [GMT+04:00 +04:00]", dtfFormat84);

    }

    public static String dtfFormat(String expectValue, String dateTime) {
        String format = DateTimePattern.forDTF(dateTime);
        if (expectValue == null) {
            assertNull(format);
            return null;
        } else {
            assertEquals(expectValue, format);
        }

        DateTimeFormatter dtf = DateTimePattern.getDTF(format);
        if (format.contains("V") || format.contains("x")) {
            return G.toString(ZonedDateTime.parse(dateTime, dtf));
        }
        LocalDateTime ldt = LocalDateTime.parse(dateTime, dtf);
        return G.toString(ldt);
    }


    @Test
    public void testCostTime() {
        Try.sleep(1000);

        int times = 5000000;

        Stopwatch stopwatch = Stopwatch.run();

        for (int i = 0; i < times; i++) {
            String fm = DateTimePattern.forSDF("2020-01-2 06:6:06");
        }

        System.out.println(times + " times cost forSDF: " + stopwatch.elapsedLastStringAndMark()); // 692.688(毫秒)
        for (int i = 0; i < times; i++) {
            String fm = DateTimePattern.forDTF("2020-01-2 06:6:06");
        }
        System.out.println(times + " times cost forDTF: " + stopwatch.elapsedLastStringAndMark()); // 654.059(毫秒)
        for (int i = 0; i < times; i++) {
            String fm = DateTimePattern.forSDF("2020-01-2 06:6:06");
        }
        System.out.println(times + " times cost forSDF: " + stopwatch.elapsedLastString()); // 571.825(毫秒)
        stopwatch.stop();
    }

}
