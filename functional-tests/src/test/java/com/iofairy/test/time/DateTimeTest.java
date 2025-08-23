package com.iofairy.test.time;

import com.iofairy.os.OS;
import com.iofairy.time.*;
import com.iofairy.range.Range;
import com.iofairy.top.G;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static com.iofairy.range.IntervalType.*;

/**
 * @author GG
 * @version 1.0
 */
public class DateTimeTest {

    @Test
    public void testToDate() {
        LocalDateTime ldt = LocalDateTime.of(2022, 2, 27, 8, 0, 10, 100);
        ZonedDateTime zdt = ldt.atZone(TZ.DEFAULT_ZONE);

        ZonedDateTime zdt1 = zdt.withZoneSameInstant(TZ.MOSCOW);
        Instant instant1 = zdt1.toInstant();
        OffsetDateTime odt1 = zdt1.toOffsetDateTime();
        GregorianCalendar calendar1 = GregorianCalendar.from(zdt1);

        Date date = Date.from(zdt.toInstant());

        Date date01 = DateTime.of(zdt).toDate();
        Date date02 = DateTime.of(zdt1).toDate();
        Date date03 = DateTime.of(odt1).toDate();
        Date date04 = DateTime.of(calendar1).toDate();
        Date date05 = DateTime.of(instant1).toDate();
        System.out.println(G.dtSimple(date));   // 2022-02-27 08:00:10.000
        System.out.println(G.dtSimple(date01)); // 2022-02-27 08:00:10.000
        System.out.println(G.dtSimple(date02)); // 2022-02-27 08:00:10.000
        System.out.println(G.dtSimple(date03)); // 2022-02-27 08:00:10.000
        System.out.println(G.dtSimple(date04)); // 2022-02-27 08:00:10.000
        System.out.println(G.dtSimple(date05)); // 2022-02-27 08:00:10.000

        assertEquals("2022-02-27 08:00:10.000", G.dtSimple(date));
        assertEquals("2022-02-27 08:00:10.000", G.dtSimple(date01));
        assertEquals("2022-02-27 08:00:10.000", G.dtSimple(date02));
        assertEquals("2022-02-27 08:00:10.000", G.dtSimple(date03));
        assertEquals("2022-02-27 08:00:10.000", G.dtSimple(date04));
        assertEquals("2022-02-27 08:00:10.000", G.dtSimple(date05));

        // toDate 带时区的 toDate，已经删除，没有意义
        // Date date01 = DateTime.of(zdt).toDate(TZ.UTC);
        // Date date02 = DateTime.of(zdt1).toDate(TZ.UTC);
        // Date date03 = DateTime.of(odt1).toDate(TZ.UTC);
        // Date date04 = DateTime.of(calendar1).toDate(TZ.UTC);
        // Date date05 = DateTime.of(instant1).toDate(TZ.UTC);
        // Date date06 = DateTime.of(zdt).toDate(null);
        // Date date07 = DateTime.of(zdt1).toDate(null);
        // Date date08 = DateTime.of(odt1).toDate(null);
        // Date date09 = DateTime.of(calendar1).toDate(null);
        // Date date10 = DateTime.of(instant1).toDate(null);
        //
        // System.out.println(G.dtSimple(date));   // 2022-02-27 08:00:10.000
        // System.out.println(G.dtSimple(date01)); // 2022-02-27 08:00:10.000
        // System.out.println(G.dtSimple(date02)); // 2022-02-27 08:00:10.000
        // System.out.println(G.dtSimple(date03)); // 2022-02-27 08:00:10.000
        // System.out.println(G.dtSimple(date04)); // 2022-02-27 08:00:10.000
        // System.out.println(G.dtSimple(date05)); // 2022-02-27 08:00:10.000
        // System.out.println(G.dtSimple(date06)); // 2022-02-27 08:00:10.000
        // System.out.println(G.dtSimple(date07)); // 2022-02-27 08:00:10.000
        // System.out.println(G.dtSimple(date08)); // 2022-02-27 08:00:10.000
        // System.out.println(G.dtSimple(date09)); // 2022-02-27 08:00:10.000
        // System.out.println(G.dtSimple(date10)); // 2022-02-27 08:00:10.000

    }

    @Test
    public void testToOther() {
        LocalDateTime ldt = LocalDateTime.of(2022, 2, 27, 8, 0, 10, 100);
        ZonedDateTime zdt = ldt.atZone(TZ.DEFAULT_ZONE);

        ZonedDateTime zdt1 = zdt.withZoneSameInstant(TZ.MOSCOW);
        Instant instant1 = zdt1.toInstant();
        OffsetDateTime odt1 = zdt1.toOffsetDateTime().withOffsetSameInstant(ZoneOffset.UTC);
        GregorianCalendar calendar1 = (GregorianCalendar) Calendar.getInstance(TimeZone.getTimeZone(TZ.MOSCOW));
        calendar1.setTime(Date.from(zdt.toInstant()));
        Date date1 = calendar1.getTime();

        DateTime dt1 = DateTime.of(ldt);
        DateTime dt2 = DateTime.of(zdt);
        DateTime dt3 = DateTime.of(zdt1);
        DateTime dt4 = DateTime.of(instant1);
        DateTime dt5 = DateTime.of(odt1);
        DateTime dt6 = DateTime.of(calendar1);
        DateTime dt7 = DateTime.of(date1);

        DateTime dt8 = DateTime.of(ldt.toLocalDate());

        System.out.println(dt1.dtDetail());     // 2022-02-27 08:00:10.000000100 [周日]
        System.out.println(dt2.dtDetail());     // 2022-02-27 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周日]
        System.out.println(dt3.dtDetail());     // 2022-02-27 03:00:10.000000100 [Europe/Moscow +03:00 GMT+3 周日]
        System.out.println(dt4.dtDetail());     // 2022-02-27 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周日]
        System.out.println(dt5.dtDetail());     // 2022-02-27 00:00:10.000000100 [+00:00 GMT 周日]
        System.out.println(dt6.dtDetail());     // 2022-02-27 03:00:10.000000000 [Europe/Moscow +03:00 GMT+3 周日]
        System.out.println(dt7.dtDetail());     // 2022-02-27 08:00:10.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        System.out.println(dt8.dtDetail());     // 2022-02-27 00:00:00.000000000 [周日]

        Calendar calendar01 = dt1.toDefaultCalendar();      // 2022-02-27 08:00:10.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        Calendar calendar02 = dt2.toDefaultCalendar();      // 2022-02-27 08:00:10.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        Calendar calendar03 = dt3.toUTCCalendar();      // 2022-02-27 00:00:10.000000000 [UTC +00:00 GMT 周日]
        Calendar calendar04 = dt4.toUTCCalendar();      // 2022-02-27 00:00:10.000000000 [UTC +00:00 GMT 周日]
        Calendar calendar05 = dt5.toCalendar(null);     // 2022-02-27 00:00:10.000000000 [UTC +00:00 GMT 周日]
        Calendar calendar06 = dt6.toCalendar(TZ.DUBAI);     // 2022-02-27 04:00:10.000000000 [Asia/Dubai +04:00 GMT+4 周日]
        LocalDateTime ldt01 = dt6.toLocalDT();        // 2022-02-27 03:00:10.000000000 [周日]
        LocalDateTime ldt02 = dt7.toLocalDT();        // 2022-02-27 08:00:10.000000000 [周日]
        LocalDateTime ldt03 = dt1.toLocalDT();        // 2022-02-27 08:00:10.000000100 [周日]
        LocalDateTime ldt04 = dt2.toLocalDT();        // 2022-02-27 08:00:10.000000100 [周日]
        LocalDateTime ldt05 = dt3.toLocalDT();        // 2022-02-27 03:00:10.000000100 [周日]
        LocalDateTime ldt06 = dt4.toLocalDT();        // 2022-02-27 08:00:10.000000100 [周日]
        Instant instant01 = dt5.toInstant();        // 2022-02-27 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周日]
        Instant instant02 = dt6.toInstant();        // 2022-02-27 08:00:10.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        Instant instant03 = dt7.toInstant();        // 2022-02-27 08:00:10.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        Instant instant04 = dt1.toInstant();        // 2022-02-27 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周日]
        ZonedDateTime zdt01 = dt1.toDefaultZonedDT();       // 2022-02-27 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周日]
        ZonedDateTime zdt02 = dt2.toDefaultZonedDT();       // 2022-02-27 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周日]
        ZonedDateTime zdt03 = dt3.toUTCZonedDT();       // 2022-02-27 00:00:10.000000100 [UTC +00:00 GMT 周日]
        ZonedDateTime zdt04 = dt4.toUTCZonedDT();       // 2022-02-27 00:00:10.000000100 [UTC +00:00 GMT 周日]
        ZonedDateTime zdt05 = dt5.toZonedDT(null);      // 2022-02-27 00:00:10.000000100 [Z +00:00 GMT 周日]
        ZonedDateTime zdt06 = dt6.toZonedDT(TZ.UTC);        // 2022-02-27 00:00:10.000000000 [UTC +00:00 GMT 周日]
        OffsetDateTime odt01 = dt7.toDefaultOffsetDT();     // 2022-02-27 08:00:10.000000000 [+08:00 GMT+8 周日]
        OffsetDateTime odt02 = dt1.toDefaultOffsetDT();     // 2022-02-27 08:00:10.000000100 [+08:00 GMT+8 周日]
        OffsetDateTime odt03 = dt2.toUTCOffsetDT();     // 2022-02-27 00:00:10.000000100 [+00:00 GMT 周日]
        OffsetDateTime odt04 = dt3.toUTCOffsetDT();     // 2022-02-27 00:00:10.000000100 [+00:00 GMT 周日]
        OffsetDateTime odt05 = dt4.toOffsetDT(null);        // 2022-02-27 08:00:10.000000100 [+08:00 GMT+8 周日]
        OffsetDateTime odt06 = dt5.toOffsetDT(ZoneOffset.MIN);      // 2022-02-26 06:00:10.000000100 [-18:00 GMT-18 周六]
        System.out.println("========================================================");
        // System.out.println(G.dtDetail(calendar01));
        // System.out.println(G.dtDetail(calendar02));
        // System.out.println(G.dtDetail(calendar03));
        // System.out.println(G.dtDetail(calendar04));
        // System.out.println(G.dtDetail(calendar05));
        // System.out.println(G.dtDetail(calendar06));
        // System.out.println(G.dtDetail(ldt01));
        // System.out.println(G.dtDetail(ldt02));
        // System.out.println(G.dtDetail(ldt03));
        // System.out.println(G.dtDetail(ldt04));
        // System.out.println(G.dtDetail(ldt05));
        // System.out.println(G.dtDetail(ldt06));
        // System.out.println(G.dtDetail(instant01));
        // System.out.println(G.dtDetail(instant02));
        // System.out.println(G.dtDetail(instant03));
        // System.out.println(G.dtDetail(instant04));
        // System.out.println(G.dtDetail(zdt01));
        // System.out.println(G.dtDetail(zdt02));
        // System.out.println(G.dtDetail(zdt03));
        // System.out.println(G.dtDetail(zdt04));
        // System.out.println(G.dtDetail(zdt05));
        // System.out.println(G.dtDetail(zdt06));
        // System.out.println(G.dtDetail(odt01));
        // System.out.println(G.dtDetail(odt02));
        // System.out.println(G.dtDetail(odt03));
        // System.out.println(G.dtDetail(odt04));
        // System.out.println(G.dtDetail(odt05));
        // System.out.println(G.dtDetail(odt06));

        assertEquals(G.dtDetail(calendar01), "2022-02-27 08:00:10.000000000 [Asia/Shanghai +08:00 GMT+8 周日]");
        assertEquals(G.dtDetail(calendar02), "2022-02-27 08:00:10.000000000 [Asia/Shanghai +08:00 GMT+8 周日]");
        assertEquals(G.dtDetail(calendar03), "2022-02-27 00:00:10.000000000 [UTC +00:00 GMT 周日]");
        assertEquals(G.dtDetail(calendar04), "2022-02-27 00:00:10.000000000 [UTC +00:00 GMT 周日]");
        assertEquals(G.dtDetail(calendar05), "2022-02-27 00:00:10.000000000 [UTC +00:00 GMT 周日]");
        assertEquals(G.dtDetail(calendar06), "2022-02-27 04:00:10.000000000 [Asia/Dubai +04:00 GMT+4 周日]");
        assertEquals(G.dtDetail(ldt01), "2022-02-27 03:00:10.000000000 [周日]");
        assertEquals(G.dtDetail(ldt02), "2022-02-27 08:00:10.000000000 [周日]");
        assertEquals(G.dtDetail(ldt03), "2022-02-27 08:00:10.000000100 [周日]");
        assertEquals(G.dtDetail(ldt04), "2022-02-27 08:00:10.000000100 [周日]");
        assertEquals(G.dtDetail(ldt05), "2022-02-27 03:00:10.000000100 [周日]");
        assertEquals(G.dtDetail(ldt06), "2022-02-27 08:00:10.000000100 [周日]");
        assertEquals(G.dtDetail(instant01), "2022-02-27 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周日]");
        assertEquals(G.dtDetail(instant02), "2022-02-27 08:00:10.000000000 [Asia/Shanghai +08:00 GMT+8 周日]");
        assertEquals(G.dtDetail(instant03), "2022-02-27 08:00:10.000000000 [Asia/Shanghai +08:00 GMT+8 周日]");
        assertEquals(G.dtDetail(instant04), "2022-02-27 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周日]");
        assertEquals(G.dtDetail(zdt01), "2022-02-27 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周日]");
        assertEquals(G.dtDetail(zdt02), "2022-02-27 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周日]");
        assertEquals(G.dtDetail(zdt03), "2022-02-27 00:00:10.000000100 [UTC +00:00 GMT 周日]");
        assertEquals(G.dtDetail(zdt04), "2022-02-27 00:00:10.000000100 [UTC +00:00 GMT 周日]");
        assertEquals(G.dtDetail(zdt05), "2022-02-27 00:00:10.000000100 [Z +00:00 GMT 周日]");
        assertEquals(G.dtDetail(zdt06), "2022-02-27 00:00:10.000000000 [UTC +00:00 GMT 周日]");
        assertEquals(G.dtDetail(odt01), "2022-02-27 08:00:10.000000000 [+08:00 GMT+8 周日]");
        assertEquals(G.dtDetail(odt02), "2022-02-27 08:00:10.000000100 [+08:00 GMT+8 周日]");
        assertEquals(G.dtDetail(odt03), "2022-02-27 00:00:10.000000100 [+00:00 GMT 周日]");
        assertEquals(G.dtDetail(odt04), "2022-02-27 00:00:10.000000100 [+00:00 GMT 周日]");
        assertEquals(G.dtDetail(odt05), "2022-02-27 08:00:10.000000100 [+08:00 GMT+8 周日]");
        assertEquals(G.dtDetail(odt06), "2022-02-26 06:00:10.000000100 [-18:00 GMT-18 周六]");

    }

    @Test
    public void testOf() {
        DateTime dateTime01 = DateTime.of(0);
        DateTime dateTime02 = DateTime.of(1710076101113L);
        DateTime dateTime03 = DateTime.ofEpochSecond(0);
        DateTime dateTime04 = DateTime.ofEpochSecond(1710076101L);
        DateTime dateTime05 = DateTime.of(1970, 1, 1, 0, 0, 0, 26, TZ.UTC);
        DateTime dateTime06 = DateTime.of(1970, 1, 1, 8, 0, 0, 26, null);
        DateTime dateTime07 = DateTime.of(2024, 3, 10, 13, 8, 21, 0, TZ.UTC);
        DateTime dateTime08 = DateTime.of(2024, 3, 10, 21, 8, TZ.DEFAULT_ZONE);
        DateTime dateTime09 = DateTime.of(-805, 3, 10, 21, 8, 0, 26, TZ.DEFAULT_ZONE);
        System.out.println(dateTime01);
        System.out.println(dateTime02);
        System.out.println(dateTime03);
        System.out.println(dateTime04);
        System.out.println(dateTime05);
        System.out.println(dateTime06);
        System.out.println(dateTime07);
        System.out.println(dateTime08);
        System.out.println(dateTime05.toEpochMilli());
        System.out.println(dateTime06.toEpochMilli());
        System.out.println(dateTime07.toEpochMilli());
        System.out.println(dateTime08.toEpochMilli());
        System.out.println(dateTime09.dtDetail());
        String dateFormat = dateTime09.format("u-MM-dd HH:mm:ss.SSSSSSSSS '['VV xxx O E']'");
        System.out.println(dateFormat);
        System.out.println(dateTime09.toDate());
        System.out.println(dateTime09.toDate().getTime());
        System.out.println(dateTime09.toEpochMilli());

        assertEquals(dateTime01.toString(), "1970-01-01 08:00:00.000");
        assertEquals(dateTime02.toString(), "2024-03-10 21:08:21.113");
        assertEquals(dateTime03.toString(), "1970-01-01 08:00:00.000");
        assertEquals(dateTime04.toString(), "2024-03-10 21:08:21.000");
        assertEquals(dateTime05.toString(), "1970-01-01 00:00:00.000 [UTC +00:00]");
        assertEquals(dateTime06.toString(), "1970-01-01 08:00:00.000");
        assertEquals(dateTime07.toString(), "2024-03-10 13:08:21.000 [UTC +00:00]");
        assertEquals(dateTime08.toString(), "2024-03-10 21:08:00.000");
        assertEquals(0L, dateTime05.toEpochMilli());
        assertEquals(0L, dateTime06.toEpochMilli());
        assertEquals(1710076101000L, dateTime07.toEpochMilli());
        assertEquals(1710076080000L, dateTime08.toEpochMilli());

        assertEquals(dateTime09.dtDetail(), "806-03-10 21:08:00.000000026 [Asia/Shanghai +08:05 GMT+8:05:43 周五]");
        assertEquals(dateFormat, "-805-03-10 21:08:00.000000026 [Asia/Shanghai +08:05 GMT+8:05:43 周五]");
        assertEquals(dateTime09.toDate().toString(), "Fri Mar 18 21:02:17 CST 806");
        assertEquals(dateTime09.toDate().getTime(), -87564625063000L);
        assertEquals(dateTime09.toEpochMilli(), -87564625063000L);
    }

    @Test
    public void testDateTimeFormat() {
        LocalDateTime ldt = LocalDateTime.of(2022, 2, 27, 8, 0, 10, 100987456);
        ZonedDateTime zdt = ldt.atZone(TZ.DEFAULT_ZONE);

        ZonedDateTime zdt1 = zdt.withZoneSameInstant(TZ.MOSCOW);
        Instant instant1 = zdt1.toInstant();
        OffsetDateTime odt1 = zdt1.toOffsetDateTime().withOffsetSameInstant(ZoneOffset.UTC);
        GregorianCalendar calendar1 = GregorianCalendar.from(zdt1);
        Date date1 = calendar1.getTime();

        DateTime dt1 = DateTime.of(ldt);
        DateTime dt2 = DateTime.of(zdt);
        DateTime dt3 = DateTime.of(zdt1);
        DateTime dt4 = DateTime.of(instant1);
        DateTime dt5 = DateTime.of(odt1);
        DateTime dt6 = DateTime.of(calendar1);
        DateTime dt7 = DateTime.of(date1);

        String format01 = dt1.format("yyyy/MM/dd HH:mm:ss.SSSSSSSSS '['E']'");
        String format02 = dt2.format("yyyy/MM/dd HH:mm:ss.SSSSSSSSS '['E']'");
        String format03 = dt3.format("yyyy/MM/dd HH:mm:ss.SSSSSSSSS '['E']'");
        String format04 = dt4.format("yyyy/MM/dd HH:mm:ss.SSSSSSSSS '['E']'");
        String format05 = dt5.format("yyyy/MM/dd HH:mm:ss.SSSSSSSSS '['E']'");
        String format06 = dt6.format("yyyy/MM/dd HH:mm:ss.SSSSSSSSS '['E']'");
        String format07 = dt7.format("yyyy/MM/dd HH:mm:ss.SSSSSSSSS '['E']'");
        String format11 = dt1.format("yyyyMMddHHmmss.SSSSSSSSS '['E']'");
        String format12 = dt2.format("yyyyMMddHHmmss.SSSSSSSSS '['E']'");
        String format13 = dt3.format("yyyyMMddHHmmss.SSSSSSSSS '['E']'");
        String format14 = dt4.format("yyyyMMddHHmmss.SSSSSSSSS '['E']'");
        String format15 = dt5.format("yyyyMMddHHmmss.SSSSSSSSS '['E']'");
        String format16 = dt6.format("yyyyMMddHHmmss.SSSSSSSSS '['E']'");
        String format17 = dt7.format("yyyyMMddHHmmss.SSSSSSSSS '['E']'");

        System.out.println(format01);
        System.out.println(format02);
        System.out.println(format03);
        System.out.println(format04);
        System.out.println(format05);
        System.out.println(format06);
        System.out.println(format07);
        System.out.println(format11);
        System.out.println(format12);
        System.out.println(format13);
        System.out.println(format14);
        System.out.println(format15);
        System.out.println(format16);
        System.out.println(format17);

        assertEquals("2022/02/27 08:00:10.100987456 [周日]", format01);
        assertEquals("2022/02/27 08:00:10.100987456 [周日]", format02);
        assertEquals("2022/02/27 03:00:10.100987456 [周日]", format03);
        assertEquals("2022/02/27 08:00:10.100987456 [周日]", format04);
        assertEquals("2022/02/27 00:00:10.100987456 [周日]", format05);
        assertEquals("2022/02/27 03:00:10.100000000 [周日]", format06);
        assertEquals("2022/02/27 08:00:10.100000000 [周日]", format07);
        assertEquals("20220227080010.100987456 [周日]", format11);
        assertEquals("20220227080010.100987456 [周日]", format12);
        assertEquals("20220227030010.100987456 [周日]", format13);
        assertEquals("20220227080010.100987456 [周日]", format14);
        assertEquals("20220227000010.100987456 [周日]", format15);
        assertEquals("20220227030010.100000000 [周日]", format16);
        assertEquals("20220227080010.100000000 [周日]", format17);

    }

    @Test
    public void testDTPlusAndMinus() {
        LocalDateTime ldt = LocalDateTime.of(2022, 2, 27, 8, 0, 10, 100);
        ZonedDateTime zdt = ldt.atZone(TZ.DEFAULT_ZONE);

        ZonedDateTime zdt1 = zdt.withZoneSameInstant(TZ.MOSCOW);
        Instant instant = zdt1.toInstant();
        OffsetDateTime odt = zdt1.toOffsetDateTime().withOffsetSameInstant(ZoneOffset.UTC);
        GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance(TimeZone.getTimeZone(TZ.NEW_YORK));
        calendar.setTime(Date.from(zdt.toInstant()));
        Date date = calendar.getTime();

        DateTime dt1 = DateTime.of(ldt);
        DateTime dt2 = DateTime.of(zdt);
        DateTime dt3 = DateTime.of(zdt1);
        DateTime dt4 = DateTime.of(instant);
        DateTime dt5 = DateTime.of(odt);
        DateTime dt6 = DateTime.of(calendar);
        DateTime dt7 = DateTime.of(date);

        System.out.println(dt1.dtDetail());     // 2022-02-27 08:00:10.000000100 [周日]
        System.out.println(dt2.dtDetail());     // 2022-02-27 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周日]
        System.out.println(dt3.dtDetail());     // 2022-02-27 03:00:10.000000100 [Europe/Moscow +03:00 GMT+3 周日]
        System.out.println(dt4.dtDetail());     // 2022-02-27 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周日]
        System.out.println(dt5.dtDetail());     // 2022-02-27 00:00:10.000000100 [+00:00 GMT 周日]
        System.out.println(dt6.dtDetail());     // 2022-02-26 19:00:10.000000000 [America/New_York -05:00 GMT-5 周六]
        System.out.println(dt7.dtDetail());     // 2022-02-27 08:00:10.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        System.out.println("=======================================");
        DateTime dt01 = dt1.plusDays(2);
        DateTime dt02 = dt2.plusMonths(5);
        DateTime dt03 = dt3.plusMillis(1500);
        DateTime dt04 = dt4.plusHours(39);
        DateTime dt05 = dt5.plusDays(30);
        DateTime dt06 = dt6.plusMonths(5);
        DateTime dt07 = dt7.plusMicros(56987599);

        DateTime dt08 = dt6.plusWeeks(-2);
        DateTime dt09 = dt7.minusWeeks(-3);

        System.out.println(dt01.dtDetail());        // 2022-03-01 08:00:10.000000100 [周二]
        System.out.println(dt02.dtDetail());        // 2022-07-27 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周三]
        System.out.println(dt03.dtDetail());        // 2022-02-27 03:00:11.500000100 [Europe/Moscow +03:00 GMT+3 周日]
        System.out.println(dt04.dtDetail());        // 2022-02-28 23:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周一]
        System.out.println(dt05.dtDetail());        // 2022-03-29 00:00:10.000000100 [+00:00 GMT 周二]
        System.out.println(dt06.dtDetail());        // 2022-07-26 19:00:10.000000000 [America/New_York -04:00 GMT-4 周二]
        System.out.println(dt07.dtDetail());        // 2022-02-27 08:01:06.987000000 [Asia/Shanghai +08:00 GMT+8 周日]
        System.out.println(dt08.dtDetail());        // 2022-02-12 19:00:10.000000000 [America/New_York -05:00 GMT-5 周六]
        System.out.println(dt09.dtDetail());        // 2022-03-20 08:00:10.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        System.out.println("=======================================");
        DateTime dt11 = dt01.minusDays(2);
        DateTime dt12 = dt02.minusMonths(5);
        DateTime dt13 = dt03.minusMillis(1500);
        DateTime dt14 = dt04.minusHours(39);
        DateTime dt15 = dt05.minusDays(30);
        DateTime dt16 = dt06.minusMonths(5);
        DateTime dt17 = dt07.minusMicros(56987599);
        System.out.println(dt11.dtDetail());        // 2022-02-27 08:00:10.000000100 [周日]
        System.out.println(dt12.dtDetail());        // 2022-02-27 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周日]
        System.out.println(dt13.dtDetail());        // 2022-02-27 03:00:10.000000100 [Europe/Moscow +03:00 GMT+3 周日]
        System.out.println(dt14.dtDetail());        // 2022-02-27 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周日]
        System.out.println(dt15.dtDetail());        // 2022-02-27 00:00:10.000000100 [+00:00 GMT 周日]
        System.out.println(dt16.dtDetail());        // 2022-02-26 19:00:10.000000000 [America/New_York -05:00 GMT-5 周六]
        System.out.println(dt17.dtDetail());        // 2022-02-27 08:00:09.999000000 [Asia/Shanghai +08:00 GMT+8 周日]

        assertEquals("2022-03-01 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周二]", dt01.dtDetail());
        assertEquals("2022-07-27 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周三]", dt02.dtDetail());
        assertEquals("2022-02-27 03:00:11.500000100 [Europe/Moscow +03:00 GMT+3 周日]", dt03.dtDetail());
        assertEquals("2022-02-28 23:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周一]", dt04.dtDetail());
        assertEquals("2022-03-29 00:00:10.000000100 [Z +00:00 GMT 周二]", dt05.dtDetail());
        assertEquals("2022-07-26 19:00:10.000000000 [America/New_York -04:00 GMT-4 周二]", dt06.dtDetail());
        assertEquals("2022-02-27 08:01:06.987599000 [Asia/Shanghai +08:00 GMT+8 周日]", dt07.dtDetail());
        assertEquals("2022-02-12 19:00:10.000000000 [America/New_York -05:00 GMT-5 周六]", dt08.dtDetail());
        assertEquals("2022-03-20 08:00:10.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dt09.dtDetail());


        assertEquals("2022-02-27 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周日]", dt11.dtDetail());
        assertEquals("2022-02-27 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周日]", dt12.dtDetail());
        assertEquals("2022-02-27 03:00:10.000000100 [Europe/Moscow +03:00 GMT+3 周日]", dt13.dtDetail());
        assertEquals("2022-02-27 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周日]", dt14.dtDetail());
        assertEquals("2022-02-27 00:00:10.000000100 [Z +00:00 GMT 周日]", dt15.dtDetail());
        assertEquals("2022-02-26 19:00:10.000000000 [America/New_York -05:00 GMT-5 周六]", dt16.dtDetail());
        assertEquals("2022-02-27 08:00:10.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dt17.dtDetail());

    }

    @Test
    public void testDTWithAndDaysOfMonthAndGet() {
        LocalDateTime ldt = LocalDateTime.of(2022, 2, 27, 8, 0, 10, 100);
        ZonedDateTime zdt = ldt.atZone(TZ.DEFAULT_ZONE);

        ZonedDateTime zdt1 = zdt.withZoneSameInstant(TZ.MOSCOW);
        Instant instant = zdt1.toInstant();
        OffsetDateTime odt = zdt1.toOffsetDateTime().withOffsetSameInstant(ZoneOffset.UTC);
        GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance(TimeZone.getTimeZone(TZ.NEW_YORK));
        calendar.setTime(Date.from(zdt.toInstant()));
        Date date = calendar.getTime();

        DateTime dt1 = DateTime.of(ldt);
        DateTime dt2 = DateTime.of(zdt);
        DateTime dt3 = DateTime.of(zdt1);
        DateTime dt4 = DateTime.of(instant);
        DateTime dt5 = DateTime.of(odt);
        DateTime dt6 = DateTime.of(calendar);
        DateTime dt7 = DateTime.of(date);

        System.out.println(dt1.dtDetail());     // 2022-02-27 08:00:10.000000100 [周日]
        System.out.println(dt2.dtDetail());     // 2022-02-27 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周日]
        System.out.println(dt3.dtDetail());     // 2022-02-27 03:00:10.000000100 [Europe/Moscow +03:00 GMT+3 周日]
        System.out.println(dt4.dtDetail());     // 2022-02-27 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周日]
        System.out.println(dt5.dtDetail());     // 2022-02-27 00:00:10.000000100 [+00:00 GMT 周日]
        System.out.println(dt6.dtDetail());     // 2022-02-26 19:00:10.000000000 [America/New_York -05:00 GMT-5 周六]
        System.out.println(dt7.dtDetail());     // 2022-02-27 08:00:10.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        System.out.println("=======================================");

        DateTime dt01 = dt1.withYear(2020);
        DateTime dt02 = dt2.withMonth(6);
        DateTime dt03 = dt3.withDayOfYear(365);
        DateTime dt04 = dt4.withDayOfMonth(15);
        DateTime dt05 = dt5.withHour(10);
        DateTime dt06 = dt6.withNano(987965856);
        DateTime dt07 = dt7.withSecond(35);
        System.out.println(dt01.dtDetail());        // 2020-02-27 08:00:10.000000100 [周四]
        System.out.println(dt02.dtDetail());        // 2022-06-27 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周一]
        System.out.println(dt03.dtDetail());        // 2022-12-31 03:00:10.000000100 [Europe/Moscow +03:00 GMT+3 周六]
        System.out.println(dt04.dtDetail());        // 2022-02-15 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周二]
        System.out.println(dt05.dtDetail());        // 2022-02-27 10:00:10.000000100 [+00:00 GMT 周日]
        System.out.println(dt06.dtDetail());        // 2022-02-26 19:00:10.987000000 [America/New_York -05:00 GMT-5 周六]
        System.out.println(dt07.dtDetail());        // 2022-02-27 08:00:35.000000000 [Asia/Shanghai +08:00 GMT+8 周日]

        assertEquals("2020-02-27 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周四]", dt01.dtDetail());
        assertEquals("2022-06-27 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周一]", dt02.dtDetail());
        assertEquals("2022-12-31 03:00:10.000000100 [Europe/Moscow +03:00 GMT+3 周六]", dt03.dtDetail());
        assertEquals("2022-02-15 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周二]", dt04.dtDetail());
        assertEquals("2022-02-27 10:00:10.000000100 [Z +00:00 GMT 周日]", dt05.dtDetail());
        assertEquals("2022-02-26 19:00:10.987965856 [America/New_York -05:00 GMT-5 周六]", dt06.dtDetail());
        assertEquals("2022-02-27 08:00:35.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dt07.dtDetail());

        int daysOfMonth1 = dt01.daysOfMonth();
        int daysOfMonth2 = dt02.daysOfMonth();
        int daysOfMonth3 = dt03.daysOfMonth();
        int daysOfMonth4 = dt04.daysOfMonth();
        int daysOfMonth5 = dt05.daysOfMonth();
        int daysOfMonth6 = dt06.daysOfMonth();
        int daysOfMonth7 = dt07.daysOfMonth();

        int year = dt01.getYear();
        Month month = dt02.getMonth();
        int monthValue = dt03.getMonthValue();
        int dayOfYear = dt04.getDayOfYear();
        int dayOfMonth = dt05.getDayOfMonth();
        int hour = dt06.getHour();
        DayOfWeek dayOfWeek = dt07.getDayOfWeek();

        System.out.println(daysOfMonth1);       // 29
        System.out.println(daysOfMonth2);       // 30
        System.out.println(daysOfMonth3);       // 31
        System.out.println(daysOfMonth4);       // 28
        System.out.println(daysOfMonth5);       // 28
        System.out.println(daysOfMonth6);       // 28
        System.out.println(daysOfMonth7);       // 28
        System.out.println(year);           // 2020
        System.out.println(month);          // JUNE
        System.out.println(monthValue);         // 12
        System.out.println(dayOfYear);          // 46
        System.out.println(dayOfMonth);         // 27
        System.out.println(hour);               // 19
        System.out.println(dayOfWeek);          // SUNDAY

        assertEquals(29, daysOfMonth1);
        assertEquals(30, daysOfMonth2);
        assertEquals(31, daysOfMonth3);
        assertEquals(28, daysOfMonth4);
        assertEquals(28, daysOfMonth5);
        assertEquals(28, daysOfMonth6);
        assertEquals(28, daysOfMonth7);
        assertEquals(2020, year);
        assertEquals(Month.JUNE, month);
        assertEquals(12, monthValue);
        assertEquals(46, dayOfYear);
        assertEquals(27, dayOfMonth);
        assertEquals(19, hour);
        assertEquals(DayOfWeek.SUNDAY, dayOfWeek);

    }

    @Test
    public void testRound() {
        LocalDateTime ldt = LocalDateTime.of(2021, 12, 27, 8, 0, 10, 100);
        ZonedDateTime zdt = ldt.atZone(TZ.DEFAULT_ZONE);

        ZonedDateTime zdt1 = zdt.withZoneSameInstant(TZ.NEW_YORK);
        Instant instant = zdt1.toInstant();
        GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance(TimeZone.getTimeZone(TZ.NEW_YORK));
        calendar.setTime(Date.from(zdt.toInstant()));
        Date date = calendar.getTime();

        DateTime dt1 = DateTime.of(zdt1);
        DateTime dt2 = DateTime.of(instant);
        DateTime dt3 = DateTime.of(date);

        System.out.println("ZonedDateTime: " + dt1.dtDetail()); // ZonedDateTime: 2021-12-26 19:00:10.000000100 [America/New_York -05:00 GMT-5 周日]
        System.out.println("Instant: " + dt2.dtDetail());       // Instant: 2021-12-27 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周一]
        System.out.println("Date: " + dt3.dtDetail());          // Date: 2021-12-27 08:00:10.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        System.out.println("========================================");
        DateTime roundFloor01 = dt1.round(ChronoUnit.MONTHS, RoundingDT.FLOOR);
        DateTime roundCeiling01 = dt1.round(ChronoUnit.MONTHS, RoundingDT.CEILING);
        DateTime roundHalfUp01 = dt1.round(ChronoUnit.MONTHS, RoundingDT.HALF_UP);
        DateTime roundFloor02 = dt2.round(ChronoUnit.MONTHS, RoundingDT.FLOOR);
        DateTime roundCeiling02 = dt2.round(ChronoUnit.MONTHS, RoundingDT.CEILING);
        DateTime roundHalfUp02 = dt2.round(ChronoUnit.MONTHS, RoundingDT.HALF_UP);
        DateTime roundFloor03 = dt3.round(ChronoUnit.MONTHS, RoundingDT.FLOOR);
        DateTime roundCeiling03 = dt3.round(ChronoUnit.MONTHS, RoundingDT.CEILING);
        DateTime roundHalfUp03 = dt3.round(ChronoUnit.MONTHS, RoundingDT.HALF_UP);
        DateTime roundFloor11 = dt1.round(ChronoUnit.DAYS, RoundingDT.FLOOR);
        DateTime roundCeiling11 = dt1.round(ChronoUnit.DAYS, RoundingDT.CEILING);
        DateTime roundHalfUp11 = dt1.round(ChronoUnit.DAYS, RoundingDT.HALF_UP);
        DateTime roundFloor12 = dt2.round(ChronoUnit.DAYS, RoundingDT.FLOOR);
        DateTime roundCeiling12 = dt2.round(ChronoUnit.DAYS, RoundingDT.CEILING);
        DateTime roundHalfUp12 = dt2.round(ChronoUnit.DAYS, RoundingDT.HALF_UP);
        DateTime roundFloor13 = dt3.round(ChronoUnit.DAYS, RoundingDT.FLOOR);
        DateTime roundCeiling13 = dt3.round(ChronoUnit.DAYS, RoundingDT.CEILING);
        DateTime roundHalfUp13 = dt3.round(ChronoUnit.DAYS, RoundingDT.HALF_UP);
        DateTime roundFloor21 = dt1.round(ChronoUnit.SECONDS, RoundingDT.FLOOR);
        DateTime roundCeiling21 = dt1.round(ChronoUnit.SECONDS, RoundingDT.CEILING);
        DateTime roundHalfUp21 = dt1.round(ChronoUnit.SECONDS, RoundingDT.HALF_UP);
        DateTime roundFloor22 = dt2.round(ChronoUnit.SECONDS, RoundingDT.FLOOR);
        DateTime roundCeiling22 = dt2.round(ChronoUnit.SECONDS, RoundingDT.CEILING);
        DateTime roundHalfUp22 = dt2.round(ChronoUnit.SECONDS, RoundingDT.HALF_UP);
        DateTime roundFloor23 = dt3.round(ChronoUnit.SECONDS, RoundingDT.FLOOR);
        DateTime roundCeiling23 = dt3.round(ChronoUnit.SECONDS, RoundingDT.CEILING);
        DateTime roundHalfUp23 = dt3.round(ChronoUnit.SECONDS, RoundingDT.HALF_UP);

        // System.out.println(roundFloor01.dtDetail());        // 2021-12-01 00:00:00.000000000 [America/New_York -05:00 GMT-5 周三]
        // System.out.println(roundCeiling01.dtDetail());      // 2022-01-01 00:00:00.000000000 [America/New_York -05:00 GMT-5 周六]
        // System.out.println(roundHalfUp01.dtDetail());       // 2022-01-01 00:00:00.000000000 [America/New_York -05:00 GMT-5 周六]
        // System.out.println(roundFloor02.dtDetail());        // 2021-12-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周三]
        // System.out.println(roundCeiling02.dtDetail());      // 2022-01-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周六]
        // System.out.println(roundHalfUp02.dtDetail());       // 2022-01-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周六]
        // System.out.println(roundFloor03.dtDetail());        // 2021-12-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周三]
        // System.out.println(roundCeiling03.dtDetail());      // 2022-01-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周六]
        // System.out.println(roundHalfUp03.dtDetail());       // 2022-01-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周六]
        // System.out.println(roundFloor11.dtDetail());        // 2021-12-26 00:00:00.000000000 [America/New_York -05:00 GMT-5 周日]
        // System.out.println(roundCeiling11.dtDetail());      // 2021-12-27 00:00:00.000000000 [America/New_York -05:00 GMT-5 周一]
        // System.out.println(roundHalfUp11.dtDetail());       // 2021-12-27 00:00:00.000000000 [America/New_York -05:00 GMT-5 周一]
        // System.out.println(roundFloor12.dtDetail());        // 2021-12-27 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        // System.out.println(roundCeiling12.dtDetail());      // 2021-12-28 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周二]
        // System.out.println(roundHalfUp12.dtDetail());       // 2021-12-27 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        // System.out.println(roundFloor13.dtDetail());        // 2021-12-27 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        // System.out.println(roundCeiling13.dtDetail());      // 2021-12-28 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周二]
        // System.out.println(roundHalfUp13.dtDetail());       // 2021-12-27 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        // System.out.println(roundFloor21.dtDetail());        // 2021-12-26 19:00:10.000000000 [America/New_York -05:00 GMT-5 周日]
        // System.out.println(roundCeiling21.dtDetail());      // 2021-12-26 19:00:11.000000000 [America/New_York -05:00 GMT-5 周日]
        // System.out.println(roundHalfUp21.dtDetail());       // 2021-12-26 19:00:10.000000000 [America/New_York -05:00 GMT-5 周日]
        // System.out.println(roundFloor22.dtDetail());        // 2021-12-27 08:00:10.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        // System.out.println(roundCeiling22.dtDetail());      // 2021-12-27 08:00:11.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        // System.out.println(roundHalfUp22.dtDetail());       // 2021-12-27 08:00:10.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        // System.out.println(roundFloor23.dtDetail());        // 2021-12-27 08:00:10.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        // System.out.println(roundCeiling23.dtDetail());      // 2021-12-27 08:00:10.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        // System.out.println(roundHalfUp23.dtDetail());       // 2021-12-27 08:00:10.000000000 [Asia/Shanghai +08:00 GMT+8 周一]

        DateTime dt01 = dt1.withNano(500000000);
        DateTime dt02 = dt2.withNano(500000000);
        DateTime dt03 = dt3.withNano(500000000);
        System.out.println("========================================");
        System.out.println(dt01.dtDetail());    // 2021-12-26 19:00:10.500000000 [America/New_York -05:00 GMT-5 周日]
        System.out.println(dt02.dtDetail());    // 2021-12-27 08:00:10.500000000 [Asia/Shanghai +08:00 GMT+8 周一]
        System.out.println(dt03.dtDetail());    // 2021-12-27 08:00:10.500000000 [Asia/Shanghai +08:00 GMT+8 周一]
        DateTime roundFloor31 = dt01.round(ChronoUnit.SECONDS, RoundingDT.FLOOR);
        DateTime roundCeiling31 = dt01.round(ChronoUnit.SECONDS, RoundingDT.CEILING);
        DateTime roundHalfUp31 = dt01.round(ChronoUnit.SECONDS, RoundingDT.HALF_UP);
        DateTime roundFloor32 = dt02.round(ChronoUnit.SECONDS, RoundingDT.FLOOR);
        DateTime roundCeiling32 = dt02.round(ChronoUnit.SECONDS, RoundingDT.CEILING);
        DateTime roundHalfUp32 = dt02.round(ChronoUnit.SECONDS, RoundingDT.HALF_UP);
        DateTime roundFloor33 = dt03.round(ChronoUnit.SECONDS, RoundingDT.FLOOR);
        DateTime roundCeiling33 = dt03.round(ChronoUnit.SECONDS, RoundingDT.CEILING);
        DateTime roundHalfUp33 = dt03.round(ChronoUnit.SECONDS, RoundingDT.HALF_UP);

        // System.out.println(roundFloor31.dtDetail());    // 2021-12-26 19:00:10.000000000 [America/New_York -05:00 GMT-5 周日]
        // System.out.println(roundCeiling31.dtDetail());  // 2021-12-26 19:00:11.000000000 [America/New_York -05:00 GMT-5 周日]
        // System.out.println(roundHalfUp31.dtDetail());   // 2021-12-26 19:00:11.000000000 [America/New_York -05:00 GMT-5 周日]
        // System.out.println(roundFloor32.dtDetail());    // 2021-12-27 08:00:10.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        // System.out.println(roundCeiling32.dtDetail());  // 2021-12-27 08:00:11.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        // System.out.println(roundHalfUp32.dtDetail());   // 2021-12-27 08:00:11.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        // System.out.println(roundFloor33.dtDetail());    // 2021-12-27 08:00:10.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        // System.out.println(roundCeiling33.dtDetail());  // 2021-12-27 08:00:11.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        // System.out.println(roundHalfUp33.dtDetail());   // 2021-12-27 08:00:11.000000000 [Asia/Shanghai +08:00 GMT+8 周一]

        assertEquals("2021-12-01 00:00:00.000000000 [America/New_York -05:00 GMT-5 周三]", roundFloor01.dtDetail());
        assertEquals("2022-01-01 00:00:00.000000000 [America/New_York -05:00 GMT-5 周六]", roundCeiling01.dtDetail());
        assertEquals("2022-01-01 00:00:00.000000000 [America/New_York -05:00 GMT-5 周六]", roundHalfUp01.dtDetail());
        assertEquals("2021-12-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周三]", roundFloor02.dtDetail());
        assertEquals("2022-01-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周六]", roundCeiling02.dtDetail());
        assertEquals("2022-01-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周六]", roundHalfUp02.dtDetail());
        assertEquals("2021-12-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周三]", roundFloor03.dtDetail());
        assertEquals("2022-01-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周六]", roundCeiling03.dtDetail());
        assertEquals("2022-01-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周六]", roundHalfUp03.dtDetail());
        assertEquals("2021-12-26 00:00:00.000000000 [America/New_York -05:00 GMT-5 周日]", roundFloor11.dtDetail());
        assertEquals("2021-12-27 00:00:00.000000000 [America/New_York -05:00 GMT-5 周一]", roundCeiling11.dtDetail());
        assertEquals("2021-12-27 00:00:00.000000000 [America/New_York -05:00 GMT-5 周一]", roundHalfUp11.dtDetail());
        assertEquals("2021-12-27 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周一]", roundFloor12.dtDetail());
        assertEquals("2021-12-28 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周二]", roundCeiling12.dtDetail());
        assertEquals("2021-12-27 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周一]", roundHalfUp12.dtDetail());
        assertEquals("2021-12-27 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周一]", roundFloor13.dtDetail());
        assertEquals("2021-12-28 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周二]", roundCeiling13.dtDetail());
        assertEquals("2021-12-27 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周一]", roundHalfUp13.dtDetail());
        assertEquals("2021-12-26 19:00:10.000000000 [America/New_York -05:00 GMT-5 周日]", roundFloor21.dtDetail());
        assertEquals("2021-12-26 19:00:11.000000000 [America/New_York -05:00 GMT-5 周日]", roundCeiling21.dtDetail());
        assertEquals("2021-12-26 19:00:10.000000000 [America/New_York -05:00 GMT-5 周日]", roundHalfUp21.dtDetail());
        assertEquals("2021-12-27 08:00:10.000000000 [Asia/Shanghai +08:00 GMT+8 周一]", roundFloor22.dtDetail());
        assertEquals("2021-12-27 08:00:11.000000000 [Asia/Shanghai +08:00 GMT+8 周一]", roundCeiling22.dtDetail());
        assertEquals("2021-12-27 08:00:10.000000000 [Asia/Shanghai +08:00 GMT+8 周一]", roundHalfUp22.dtDetail());
        assertEquals("2021-12-27 08:00:10.000000000 [Asia/Shanghai +08:00 GMT+8 周一]", roundFloor23.dtDetail());
        assertEquals("2021-12-27 08:00:10.000000000 [Asia/Shanghai +08:00 GMT+8 周一]", roundCeiling23.dtDetail());
        assertEquals("2021-12-27 08:00:10.000000000 [Asia/Shanghai +08:00 GMT+8 周一]", roundHalfUp23.dtDetail());
        assertEquals("2021-12-26 19:00:10.000000000 [America/New_York -05:00 GMT-5 周日]", roundFloor31.dtDetail());
        assertEquals("2021-12-26 19:00:11.000000000 [America/New_York -05:00 GMT-5 周日]", roundCeiling31.dtDetail());
        assertEquals("2021-12-26 19:00:11.000000000 [America/New_York -05:00 GMT-5 周日]", roundHalfUp31.dtDetail());
        assertEquals("2021-12-27 08:00:10.000000000 [Asia/Shanghai +08:00 GMT+8 周一]", roundFloor32.dtDetail());
        assertEquals("2021-12-27 08:00:11.000000000 [Asia/Shanghai +08:00 GMT+8 周一]", roundCeiling32.dtDetail());
        assertEquals("2021-12-27 08:00:11.000000000 [Asia/Shanghai +08:00 GMT+8 周一]", roundHalfUp32.dtDetail());
        assertEquals("2021-12-27 08:00:10.000000000 [Asia/Shanghai +08:00 GMT+8 周一]", roundFloor33.dtDetail());
        assertEquals("2021-12-27 08:00:11.000000000 [Asia/Shanghai +08:00 GMT+8 周一]", roundCeiling33.dtDetail());
        assertEquals("2021-12-27 08:00:11.000000000 [Asia/Shanghai +08:00 GMT+8 周一]", roundHalfUp33.dtDetail());

    }

    @Test
    public void testRoundTime() {
        LocalDateTime ldt = LocalDateTime.of(2021, 2, 28, 9, 0, 16, 100);
        ZonedDateTime zdt = ldt.atZone(TZ.DEFAULT_ZONE);

        ZonedDateTime zdt1 = zdt.withZoneSameInstant(TZ.NEW_YORK);
        Instant instant = zdt1.toInstant();
        GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance(TimeZone.getTimeZone(TZ.NEW_YORK));
        calendar.setTime(Date.from(zdt.toInstant()));
        Date date = calendar.getTime();

        DateTime dt1 = DateTime.of(zdt1);
        DateTime dt2 = DateTime.of(date);

        System.out.println(dt1.dtDetail());     // 2021-02-27 20:00:16.000000100 [America/New_York -05:00 GMT-5 周六]
        System.out.println(dt2.dtDetail());     // 2021-02-28 09:00:16.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        // System.out.println("========================================");

        // Try.tcf(() -> dt1.roundTime(ChronoUnit.HOURS, 25, RoundingDT.FLOOR));
        // Try.tcf(() -> dt1.roundTime(ChronoUnit.SECONDS, -61, RoundingDT.FLOOR));
        System.out.println("========================================");

        DateTime dtZdt01 = dt1.roundTime(ChronoUnit.HOURS, 0, RoundingDT.FLOOR);    // 2021-02-27 20:00:00.000000000 [America/New_York -05:00 GMT-5 周六]
        DateTime dtZdt02 = dt1.roundTime(ChronoUnit.HOURS, 1, RoundingDT.FLOOR);    // 2021-02-27 20:00:00.000000000 [America/New_York -05:00 GMT-5 周六]
        DateTime dtZdt03 = dt1.roundTime(ChronoUnit.HOURS, -2, RoundingDT.FLOOR);   // 2021-02-27 20:00:00.000000000 [America/New_York -05:00 GMT-5 周六]
        DateTime dtZdt04 = dt1.roundTime(ChronoUnit.HOURS, 3, RoundingDT.FLOOR);    // 2021-02-27 18:00:00.000000000 [America/New_York -05:00 GMT-5 周六]
        DateTime dtZdt05 = dt1.roundTime(ChronoUnit.HOURS, -5, RoundingDT.FLOOR);   // 2021-02-27 20:00:00.000000000 [America/New_York -05:00 GMT-5 周六]
        DateTime dtZdt06 = dt1.roundTime(ChronoUnit.HOURS, 0, RoundingDT.CEILING);  // 2021-02-27 20:00:00.000000000 [America/New_York -05:00 GMT-5 周六]
        DateTime dtZdt07 = dt1.roundTime(ChronoUnit.HOURS, 1, RoundingDT.CEILING);  // 2021-02-27 21:00:00.000000000 [America/New_York -05:00 GMT-5 周六]
        DateTime dtZdt08 = dt1.roundTime(ChronoUnit.HOURS, -2, RoundingDT.CEILING); // 2021-02-27 22:00:00.000000000 [America/New_York -05:00 GMT-5 周六]
        DateTime dtZdt09 = dt1.roundTime(ChronoUnit.HOURS, 3, RoundingDT.CEILING);  // 2021-02-27 21:00:00.000000000 [America/New_York -05:00 GMT-5 周六]
        DateTime dtZdt10 = dt1.roundTime(ChronoUnit.HOURS, -5, RoundingDT.CEILING); // 2021-02-28 00:00:00.000000000 [America/New_York -05:00 GMT-5 周日]
        DateTime dtZdt11 = dt1.roundTime(ChronoUnit.HOURS, 0, RoundingDT.HALF_UP);  // 2021-02-27 20:00:00.000000000 [America/New_York -05:00 GMT-5 周六]
        DateTime dtZdt12 = dt1.roundTime(ChronoUnit.HOURS, 1, RoundingDT.HALF_UP);  // 2021-02-27 20:00:00.000000000 [America/New_York -05:00 GMT-5 周六]
        DateTime dtZdt13 = dt1.roundTime(ChronoUnit.HOURS, -2, RoundingDT.HALF_UP); // 2021-02-27 20:00:00.000000000 [America/New_York -05:00 GMT-5 周六]
        DateTime dtZdt14 = dt1.roundTime(ChronoUnit.HOURS, 3, RoundingDT.HALF_UP);  // 2021-02-27 21:00:00.000000000 [America/New_York -05:00 GMT-5 周六]
        DateTime dtZdt15 = dt1.roundTime(ChronoUnit.HOURS, -5, RoundingDT.HALF_UP); // 2021-02-27 20:00:00.000000000 [America/New_York -05:00 GMT-5 周六]
        DateTime dtDate01 = dt2.roundTime(ChronoUnit.SECONDS, 0, RoundingDT.FLOOR);          // 2021-02-28 09:00:16.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate02 = dt2.roundTime(ChronoUnit.SECONDS, 1, RoundingDT.FLOOR);          // 2021-02-28 09:00:16.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate03 = dt2.roundTime(ChronoUnit.SECONDS, -2, RoundingDT.FLOOR);         // 2021-02-28 09:00:16.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate04 = dt2.roundTime(ChronoUnit.SECONDS, 3, RoundingDT.FLOOR);          // 2021-02-28 09:00:15.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate05 = dt2.roundTime(ChronoUnit.SECONDS, -5, RoundingDT.FLOOR);         // 2021-02-28 09:00:15.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate06 = dt2.roundTime(ChronoUnit.SECONDS, 0, RoundingDT.CEILING);        // 2021-02-28 09:00:16.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate07 = dt2.roundTime(ChronoUnit.SECONDS, 1, RoundingDT.CEILING);        // 2021-02-28 09:00:16.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate08 = dt2.roundTime(ChronoUnit.SECONDS, -2, RoundingDT.CEILING);       // 2021-02-28 09:00:16.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate09 = dt2.roundTime(ChronoUnit.SECONDS, 3, RoundingDT.CEILING);        // 2021-02-28 09:00:18.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate10 = dt2.roundTime(ChronoUnit.SECONDS, -5, RoundingDT.CEILING);       // 2021-02-28 09:00:20.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate11 = dt2.roundTime(ChronoUnit.SECONDS, 0, RoundingDT.HALF_UP);        // 2021-02-28 09:00:16.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate12 = dt2.roundTime(ChronoUnit.SECONDS, 1, RoundingDT.HALF_UP);        // 2021-02-28 09:00:16.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate13 = dt2.roundTime(ChronoUnit.SECONDS, -2, RoundingDT.HALF_UP);       // 2021-02-28 09:00:16.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate14 = dt2.roundTime(ChronoUnit.SECONDS, 3, RoundingDT.HALF_UP);        // 2021-02-28 09:00:15.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate15 = dt2.roundTime(ChronoUnit.SECONDS, -6, RoundingDT.HALF_UP);       // 2021-02-28 09:00:18.000000000 [Asia/Shanghai +08:00 GMT+8 周日]

        // System.out.println(dtZdt01.dtDetail());
        // System.out.println(dtZdt02.dtDetail());
        // System.out.println(dtZdt03.dtDetail());
        // System.out.println(dtZdt04.dtDetail());
        // System.out.println(dtZdt05.dtDetail());
        // System.out.println(dtZdt06.dtDetail());
        // System.out.println(dtZdt07.dtDetail());
        // System.out.println(dtZdt08.dtDetail());
        // System.out.println(dtZdt09.dtDetail());
        // System.out.println(dtZdt10.dtDetail());
        // System.out.println(dtZdt11.dtDetail());
        // System.out.println(dtZdt12.dtDetail());
        // System.out.println(dtZdt13.dtDetail());
        // System.out.println(dtZdt14.dtDetail());
        // System.out.println(dtZdt15.dtDetail());
        // System.out.println(dtDate01.dtDetail());
        // System.out.println(dtDate02.dtDetail());
        // System.out.println(dtDate03.dtDetail());
        // System.out.println(dtDate04.dtDetail());
        // System.out.println(dtDate05.dtDetail());
        // System.out.println(dtDate06.dtDetail());
        // System.out.println(dtDate07.dtDetail());
        // System.out.println(dtDate08.dtDetail());
        // System.out.println(dtDate09.dtDetail());
        // System.out.println(dtDate10.dtDetail());
        // System.out.println(dtDate11.dtDetail());
        // System.out.println(dtDate12.dtDetail());
        // System.out.println(dtDate13.dtDetail());
        // System.out.println(dtDate14.dtDetail());
        // System.out.println(dtDate15.dtDetail());

        DateTime dt3 = dt2.withNano(500000000);
        DateTime dt4 = dt3.withSecond(59);
        System.out.println("========================================");
        System.out.println(dt3.dtDetail());     // 2021-02-28 09:00:16.500000000 [Asia/Shanghai +08:00 GMT+8 周日]
        System.out.println(dt4.dtDetail());     // 2021-02-28 09:00:59.500000000 [Asia/Shanghai +08:00 GMT+8 周日]

        DateTime dtDate001 = dt3.roundTime(ChronoUnit.SECONDS, 0, RoundingDT.FLOOR);          // 2021-02-28 09:00:16.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate002 = dt3.roundTime(ChronoUnit.SECONDS, 1, RoundingDT.FLOOR);          // 2021-02-28 09:00:16.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate003 = dt3.roundTime(ChronoUnit.SECONDS, -2, RoundingDT.FLOOR);         // 2021-02-28 09:00:16.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate004 = dt3.roundTime(ChronoUnit.SECONDS, 3, RoundingDT.FLOOR);          // 2021-02-28 09:00:15.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate005 = dt3.roundTime(ChronoUnit.SECONDS, -7, RoundingDT.FLOOR);         // 2021-02-28 09:00:14.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate006 = dt3.roundTime(ChronoUnit.SECONDS, 0, RoundingDT.CEILING);        // 2021-02-28 09:00:16.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate007 = dt3.roundTime(ChronoUnit.SECONDS, 1, RoundingDT.CEILING);        // 2021-02-28 09:00:17.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate008 = dt3.roundTime(ChronoUnit.SECONDS, -2, RoundingDT.CEILING);       // 2021-02-28 09:00:18.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate009 = dt3.roundTime(ChronoUnit.SECONDS, 3, RoundingDT.CEILING);        // 2021-02-28 09:00:18.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate010 = dt3.roundTime(ChronoUnit.SECONDS, -7, RoundingDT.CEILING);       // 2021-02-28 09:00:21.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate011 = dt3.roundTime(ChronoUnit.SECONDS, 0, RoundingDT.HALF_UP);        // 2021-02-28 09:00:16.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate012 = dt3.roundTime(ChronoUnit.SECONDS, 1, RoundingDT.HALF_UP);        // 2021-02-28 09:00:16.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate013 = dt3.roundTime(ChronoUnit.SECONDS, -2, RoundingDT.HALF_UP);       // 2021-02-28 09:00:16.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate014 = dt3.roundTime(ChronoUnit.SECONDS, 3, RoundingDT.HALF_UP);        // 2021-02-28 09:00:15.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate015 = dt3.roundTime(ChronoUnit.SECONDS, -7, RoundingDT.HALF_UP);       // 2021-02-28 09:00:14.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate101 = dt4.roundTime(ChronoUnit.SECONDS, 0, RoundingDT.FLOOR);          // 2021-02-28 09:00:59.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate102 = dt4.roundTime(ChronoUnit.SECONDS, 1, RoundingDT.FLOOR);          // 2021-02-28 09:00:59.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate103 = dt4.roundTime(ChronoUnit.SECONDS, -2, RoundingDT.FLOOR);         // 2021-02-28 09:00:58.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate104 = dt4.roundTime(ChronoUnit.SECONDS, 3, RoundingDT.FLOOR);          // 2021-02-28 09:00:57.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate105 = dt4.roundTime(ChronoUnit.SECONDS, -9, RoundingDT.FLOOR);         // 2021-02-28 09:00:54.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate106 = dt4.roundTime(ChronoUnit.SECONDS, 0, RoundingDT.CEILING);        // 2021-02-28 09:00:59.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate107 = dt4.roundTime(ChronoUnit.SECONDS, 1, RoundingDT.CEILING);        // 2021-02-28 09:01:00.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate108 = dt4.roundTime(ChronoUnit.SECONDS, -2, RoundingDT.CEILING);       // 2021-02-28 09:01:00.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate109 = dt4.roundTime(ChronoUnit.SECONDS, 3, RoundingDT.CEILING);        // 2021-02-28 09:01:00.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate110 = dt4.roundTime(ChronoUnit.SECONDS, -9, RoundingDT.CEILING);       // 2021-02-28 09:01:00.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate111 = dt4.roundTime(ChronoUnit.SECONDS, 0, RoundingDT.HALF_UP);        // 2021-02-28 09:00:59.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate112 = dt4.roundTime(ChronoUnit.SECONDS, 1, RoundingDT.HALF_UP);        // 2021-02-28 09:00:59.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate113 = dt4.roundTime(ChronoUnit.SECONDS, -2, RoundingDT.HALF_UP);       // 2021-02-28 09:00:58.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate114 = dt4.roundTime(ChronoUnit.SECONDS, 3, RoundingDT.HALF_UP);        // 2021-02-28 09:01:00.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dtDate115 = dt4.roundTime(ChronoUnit.SECONDS, -9, RoundingDT.HALF_UP);       // 2021-02-28 09:01:00.000000000 [Asia/Shanghai +08:00 GMT+8 周日]

        DateTime dt01 = dt3.roundTime(ChronoUnit.HOURS, 24, RoundingDT.FLOOR);        // 2021-02-28 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dt02 = dt3.roundTime(ChronoUnit.HOURS, 24, RoundingDT.CEILING);      // 2021-03-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        DateTime dt03 = dt3.roundTime(ChronoUnit.HOURS, 24, RoundingDT.HALF_UP);      // 2021-02-28 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dt04 = dt4.roundTime(ChronoUnit.SECONDS, 60, RoundingDT.FLOOR);      // 2021-02-28 09:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dt05 = dt4.roundTime(ChronoUnit.SECONDS, 60, RoundingDT.CEILING);    // 2021-02-28 09:01:00.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dt06 = dt4.roundTime(ChronoUnit.SECONDS, 60, RoundingDT.HALF_UP);    // 2021-02-28 09:01:00.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        System.out.println("========================================");
        // System.out.println(dtDate001.dtDetail());
        // System.out.println(dtDate002.dtDetail());
        // System.out.println(dtDate003.dtDetail());
        // System.out.println(dtDate004.dtDetail());
        // System.out.println(dtDate005.dtDetail());
        // System.out.println(dtDate006.dtDetail());
        // System.out.println(dtDate007.dtDetail());
        // System.out.println(dtDate008.dtDetail());
        // System.out.println(dtDate009.dtDetail());
        // System.out.println(dtDate010.dtDetail());
        // System.out.println(dtDate011.dtDetail());
        // System.out.println(dtDate012.dtDetail());
        // System.out.println(dtDate013.dtDetail());
        // System.out.println(dtDate014.dtDetail());
        // System.out.println(dtDate015.dtDetail());
        // System.out.println(dtDate101.dtDetail());
        // System.out.println(dtDate102.dtDetail());
        // System.out.println(dtDate103.dtDetail());
        // System.out.println(dtDate104.dtDetail());
        // System.out.println(dtDate105.dtDetail());
        // System.out.println(dtDate106.dtDetail());
        // System.out.println(dtDate107.dtDetail());
        // System.out.println(dtDate108.dtDetail());
        // System.out.println(dtDate109.dtDetail());
        // System.out.println(dtDate110.dtDetail());
        // System.out.println(dtDate111.dtDetail());
        // System.out.println(dtDate112.dtDetail());
        // System.out.println(dtDate113.dtDetail());
        // System.out.println(dtDate114.dtDetail());
        // System.out.println(dtDate115.dtDetail());

        System.out.println(dt01.dtDetail());
        System.out.println(dt02.dtDetail());
        System.out.println(dt03.dtDetail());
        System.out.println(dt04.dtDetail());
        System.out.println(dt05.dtDetail());
        System.out.println(dt06.dtDetail());

        assertEquals("2021-02-27 20:00:00.000000000 [America/New_York -05:00 GMT-5 周六]", dtZdt01.dtDetail());
        assertEquals("2021-02-27 20:00:00.000000000 [America/New_York -05:00 GMT-5 周六]", dtZdt02.dtDetail());
        assertEquals("2021-02-27 20:00:00.000000000 [America/New_York -05:00 GMT-5 周六]", dtZdt03.dtDetail());
        assertEquals("2021-02-27 18:00:00.000000000 [America/New_York -05:00 GMT-5 周六]", dtZdt04.dtDetail());
        assertEquals("2021-02-27 20:00:00.000000000 [America/New_York -05:00 GMT-5 周六]", dtZdt05.dtDetail());
        assertEquals("2021-02-27 20:00:00.000000000 [America/New_York -05:00 GMT-5 周六]", dtZdt06.dtDetail());
        assertEquals("2021-02-27 21:00:00.000000000 [America/New_York -05:00 GMT-5 周六]", dtZdt07.dtDetail());
        assertEquals("2021-02-27 22:00:00.000000000 [America/New_York -05:00 GMT-5 周六]", dtZdt08.dtDetail());
        assertEquals("2021-02-27 21:00:00.000000000 [America/New_York -05:00 GMT-5 周六]", dtZdt09.dtDetail());
        assertEquals("2021-02-28 00:00:00.000000000 [America/New_York -05:00 GMT-5 周日]", dtZdt10.dtDetail());
        assertEquals("2021-02-27 20:00:00.000000000 [America/New_York -05:00 GMT-5 周六]", dtZdt11.dtDetail());
        assertEquals("2021-02-27 20:00:00.000000000 [America/New_York -05:00 GMT-5 周六]", dtZdt12.dtDetail());
        assertEquals("2021-02-27 20:00:00.000000000 [America/New_York -05:00 GMT-5 周六]", dtZdt13.dtDetail());
        assertEquals("2021-02-27 21:00:00.000000000 [America/New_York -05:00 GMT-5 周六]", dtZdt14.dtDetail());
        assertEquals("2021-02-27 20:00:00.000000000 [America/New_York -05:00 GMT-5 周六]", dtZdt15.dtDetail());
        assertEquals("2021-02-28 09:00:16.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate01.dtDetail());
        assertEquals("2021-02-28 09:00:16.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate02.dtDetail());
        assertEquals("2021-02-28 09:00:16.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate03.dtDetail());
        assertEquals("2021-02-28 09:00:15.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate04.dtDetail());
        assertEquals("2021-02-28 09:00:15.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate05.dtDetail());
        assertEquals("2021-02-28 09:00:16.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate06.dtDetail());
        assertEquals("2021-02-28 09:00:16.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate07.dtDetail());
        assertEquals("2021-02-28 09:00:16.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate08.dtDetail());
        assertEquals("2021-02-28 09:00:18.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate09.dtDetail());
        assertEquals("2021-02-28 09:00:20.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate10.dtDetail());
        assertEquals("2021-02-28 09:00:16.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate11.dtDetail());
        assertEquals("2021-02-28 09:00:16.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate12.dtDetail());
        assertEquals("2021-02-28 09:00:16.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate13.dtDetail());
        assertEquals("2021-02-28 09:00:15.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate14.dtDetail());
        assertEquals("2021-02-28 09:00:18.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate15.dtDetail());

        assertEquals("2021-02-28 09:00:16.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate001.dtDetail());
        assertEquals("2021-02-28 09:00:16.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate002.dtDetail());
        assertEquals("2021-02-28 09:00:16.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate003.dtDetail());
        assertEquals("2021-02-28 09:00:15.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate004.dtDetail());
        assertEquals("2021-02-28 09:00:14.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate005.dtDetail());
        assertEquals("2021-02-28 09:00:16.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate006.dtDetail());
        assertEquals("2021-02-28 09:00:17.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate007.dtDetail());
        assertEquals("2021-02-28 09:00:18.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate008.dtDetail());
        assertEquals("2021-02-28 09:00:18.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate009.dtDetail());
        assertEquals("2021-02-28 09:00:21.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate010.dtDetail());
        assertEquals("2021-02-28 09:00:16.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate011.dtDetail());
        assertEquals("2021-02-28 09:00:16.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate012.dtDetail());
        assertEquals("2021-02-28 09:00:16.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate013.dtDetail());
        assertEquals("2021-02-28 09:00:15.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate014.dtDetail());
        assertEquals("2021-02-28 09:00:14.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate015.dtDetail());
        assertEquals("2021-02-28 09:00:59.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate101.dtDetail());
        assertEquals("2021-02-28 09:00:59.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate102.dtDetail());
        assertEquals("2021-02-28 09:00:58.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate103.dtDetail());
        assertEquals("2021-02-28 09:00:57.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate104.dtDetail());
        assertEquals("2021-02-28 09:00:54.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate105.dtDetail());
        assertEquals("2021-02-28 09:00:59.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate106.dtDetail());
        assertEquals("2021-02-28 09:01:00.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate107.dtDetail());
        assertEquals("2021-02-28 09:01:00.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate108.dtDetail());
        assertEquals("2021-02-28 09:01:00.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate109.dtDetail());
        assertEquals("2021-02-28 09:01:00.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate110.dtDetail());
        assertEquals("2021-02-28 09:00:59.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate111.dtDetail());
        assertEquals("2021-02-28 09:00:59.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate112.dtDetail());
        assertEquals("2021-02-28 09:00:58.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate113.dtDetail());
        assertEquals("2021-02-28 09:01:00.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate114.dtDetail());
        assertEquals("2021-02-28 09:01:00.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dtDate115.dtDetail());

        assertEquals("2021-02-28 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dt01.dtDetail());
        assertEquals("2021-03-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周一]", dt02.dtDetail());
        assertEquals("2021-02-28 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dt03.dtDetail());
        assertEquals("2021-02-28 09:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dt04.dtDetail());
        assertEquals("2021-02-28 09:01:00.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dt05.dtDetail());
        assertEquals("2021-02-28 09:01:00.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dt06.dtDetail());

    }

    @Test
    public void testDatesByShift() {
        LocalDateTime ldt = LocalDateTime.of(2022, 2, 27, 8, 0, 10, 100);
        ZonedDateTime zdt = ldt.atZone(TZ.DEFAULT_ZONE);

        ZonedDateTime zdt1 = zdt.withZoneSameInstant(TZ.MOSCOW);
        Instant instant = zdt1.toInstant();
        OffsetDateTime odt = zdt1.toOffsetDateTime().withOffsetSameInstant(ZoneOffset.UTC);
        GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance(TimeZone.getTimeZone(TZ.NEW_YORK));
        calendar.setTime(Date.from(zdt.toInstant()));
        Date date = calendar.getTime();

        DateTime dt1 = DateTime.of(ldt);
        DateTime dt2 = DateTime.of(zdt);
        DateTime dt3 = DateTime.of(zdt1);
        DateTime dt4 = DateTime.of(instant);
        DateTime dt5 = DateTime.of(odt);
        DateTime dt6 = DateTime.of(calendar);
        DateTime dt7 = DateTime.of(date);

        System.out.println("dt1: " + dt1.dtDetail());     // 2022-02-27 08:00:10.000000100 [周日]
        System.out.println("dt2: " + dt2.dtDetail());     // 2022-02-27 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周日]
        System.out.println("dt3: " + dt3.dtDetail());     // 2022-02-27 03:00:10.000000100 [Europe/Moscow +03:00 GMT+3 周日]
        System.out.println("dt4: " + dt4.dtDetail());     // 2022-02-27 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周日]
        System.out.println("dt5: " + dt5.dtDetail());     // 2022-02-27 00:00:10.000000100 [+00:00 GMT 周日]
        System.out.println("dt6: " + dt6.dtDetail());     // 2022-02-26 19:00:10.000000000 [America/New_York -05:00 GMT-5 周六]
        System.out.println("dt7: " + dt7.dtDetail());     // 2022-02-27 08:00:10.000000000 [Asia/Shanghai +08:00 GMT+8 周日]

        System.out.println("============================================");

        List<DateTime> zonedDateTimes1 = dt3.datesByShift(3, ChronoUnit.DAYS);
        List<DateTime> zonedDateTimes2 = dt3.datesByShift(-3, ChronoUnit.HOURS);
        List<DateTime> instants1 = dt4.datesByShift(3, 3, ChronoUnit.DAYS, true);
        List<DateTime> instants2 = dt4.datesByShift(-3, 3, ChronoUnit.DAYS, false);
        List<DateTime> offsetDateTimes1 = dt5.datesByShift(-3, 90, ChronoUnit.SECONDS, true);
        List<DateTime> offsetDateTimes2 = dt5.datesByShift(3, 90, ChronoUnit.SECONDS, false);
        List<DateTime> calendars1 = dt6.datesByShift(-2, -15, ChronoUnit.HOURS, true);
        List<DateTime> calendars2 = dt6.datesByShift(3, -15, ChronoUnit.HOURS, false);

        List<DateTime> dateTimes1 = dt1.datesByShift(-2, 2, ChronoUnit.WEEKS, true);
        List<DateTime> dateTimes2 = dt1.datesByShift(2, -2, ChronoUnit.WEEKS, false);
        List<DateTime> dateTimes3 = dt1.datesByShift(2, 0, ChronoUnit.WEEKS, true);
        List<DateTime> dateTimes4 = dt1.datesByShift(0, 0, ChronoUnit.WEEKS, false);

        // List<String> dates1 = zonedDateTimes1.stream().map(G::dtDetail).collect(Collectors.toList());
        // List<String> dates2 = zonedDateTimes2.stream().map(G::dtDetail).collect(Collectors.toList());
        // List<String> dates3 = instants1.stream().map(G::dtDetail).collect(Collectors.toList());
        // List<String> dates4 = instants2.stream().map(G::dtDetail).collect(Collectors.toList());
        // List<String> dates5 = offsetDateTimes1.stream().map(G::dtDetail).collect(Collectors.toList());
        // List<String> dates6 = offsetDateTimes2.stream().map(G::dtDetail).collect(Collectors.toList());
        // List<String> dates7 = calendars1.stream().map(G::dtDetail).collect(Collectors.toList());
        // List<String> dates8 = calendars2.stream().map(G::dtDetail).collect(Collectors.toList());

        System.out.println(G.toString(zonedDateTimes1));
        System.out.println(G.toString(zonedDateTimes2));
        System.out.println(G.toString(instants1));
        System.out.println(G.toString(instants2));
        System.out.println(G.toString(offsetDateTimes1));
        System.out.println(G.toString(offsetDateTimes2));
        System.out.println(G.toString(calendars1));
        System.out.println(G.toString(calendars2));
        System.out.println(G.toString(dateTimes1));
        System.out.println(G.toString(dateTimes2));
        System.out.println(G.toString(dateTimes3));
        System.out.println(G.toString(dateTimes4));


        assertEquals("[2022-02-27 03:00:10.000 [Europe/Moscow +03:00], 2022-02-28 03:00:10.000 [Europe/Moscow +03:00], 2022-03-01 03:00:10.000 [Europe/Moscow +03:00], 2022-03-02 03:00:10.000 [Europe/Moscow +03:00]]", G.toString(zonedDateTimes1));
        assertEquals("[2022-02-27 00:00:10.000 [Europe/Moscow +03:00], 2022-02-27 01:00:10.000 [Europe/Moscow +03:00], 2022-02-27 02:00:10.000 [Europe/Moscow +03:00], 2022-02-27 03:00:10.000 [Europe/Moscow +03:00]]", G.toString(zonedDateTimes2));
        assertEquals("[2022-02-27 08:00:10.000, 2022-03-02 08:00:10.000, 2022-03-05 08:00:10.000, 2022-03-08 08:00:10.000]", G.toString(instants1));
        assertEquals("[2022-02-18 08:00:10.000, 2022-02-21 08:00:10.000, 2022-02-24 08:00:10.000]", G.toString(instants2));
        assertEquals("[2022-02-26 23:55:40.000 [Z +00:00], 2022-02-26 23:57:10.000 [Z +00:00], 2022-02-26 23:58:40.000 [Z +00:00], 2022-02-27 00:00:10.000 [Z +00:00]]", G.toString(offsetDateTimes1));
        assertEquals("[2022-02-27 00:01:40.000 [Z +00:00], 2022-02-27 00:03:10.000 [Z +00:00], 2022-02-27 00:04:40.000 [Z +00:00]]", G.toString(offsetDateTimes2));
        assertEquals("[2022-02-25 13:00:10.000 [America/New_York -05:00], 2022-02-26 04:00:10.000 [America/New_York -05:00], 2022-02-26 19:00:10.000 [America/New_York -05:00]]", G.toString(calendars1));
        assertEquals("[2022-02-27 10:00:10.000 [America/New_York -05:00], 2022-02-28 01:00:10.000 [America/New_York -05:00], 2022-02-28 16:00:10.000 [America/New_York -05:00]]", G.toString(calendars2));
        assertEquals("[2022-01-30 08:00:10.000, 2022-02-13 08:00:10.000, 2022-02-27 08:00:10.000]", G.toString(dateTimes1));
        assertEquals("[2022-03-13 08:00:10.000, 2022-03-27 08:00:10.000]", G.toString(dateTimes2));
        assertEquals("[2022-02-27 08:00:10.000]", G.toString(dateTimes3));
        assertEquals("[]", G.toString(dateTimes4));
    }

    @Test
    public void testDatesFromRange() {
        LocalDateTime ldt1 = LocalDateTime.of(2022, 2, 27, 8, 0, 10, 100);
        LocalDateTime ldt2 = LocalDateTime.of(2022, 3, 8, 8, 0, 11, 100);
        LocalDateTime ldt3 = LocalDateTime.of(2022, 3, 8, 8, 0, 9, 100);

        LocalDateTime ldt4 = LocalDateTime.of(2022, 2, 28, 23, 55, 40, 800000000);
        LocalDateTime ldt5 = LocalDateTime.of(2022, 3, 1, 0, 0, 10, 900000000);
        LocalDateTime ldt6 = LocalDateTime.of(2022, 3, 1, 0, 0, 10, 700000000);

        ZonedDateTime zdt1 = ldt1.atZone(TZ.DEFAULT_ZONE);
        ZonedDateTime zdt2 = ldt2.atZone(TZ.DEFAULT_ZONE);
        ZonedDateTime zdt3 = ldt3.atZone(TZ.DEFAULT_ZONE);
        ZonedDateTime zdt4 = ldt4.atZone(TZ.DEFAULT_ZONE);
        ZonedDateTime zdt5 = ldt5.atZone(TZ.DEFAULT_ZONE);
        ZonedDateTime zdt6 = ldt6.atZone(TZ.DEFAULT_ZONE);

        Instant instant1 = zdt1.toInstant();
        OffsetDateTime odt1 = zdt2.toOffsetDateTime().withOffsetSameInstant(ZoneOffset.UTC);
        Calendar calendar1 = DateTime.of(zdt3).toCalendar(TZ.MOSCOW);

        Instant instant2 = zdt4.toInstant();
        OffsetDateTime odt2 = zdt5.toOffsetDateTime().withOffsetSameInstant(ZoneOffset.UTC);
        Calendar calendar2 = DateTime.of(zdt6).toCalendar(TZ.NEW_YORK);

        DateTime dt01 = DateTime.of(instant1);         // 2022-02-27 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dt02 = DateTime.of(odt1);      // 2022-03-08 00:00:11.000000100 [+00:00 GMT 周二] --> 2022-03-08 08:00:11.000000100 [Asia/Shanghai +08:00 GMT+8 周二]
        DateTime dt03 = DateTime.of(calendar1);       // 2022-03-08 03:00:09.000000000 [Europe/Moscow +03:00 GMT+3 周二] --> 2022-03-08 08:00:09.000000000 [Asia/Shanghai +08:00 GMT+8 周二]

        DateTime dt11 = DateTime.of(instant2);         // 2022-02-28 23:55:40.800000000 [Asia/Shanghai +08:00 GMT+8 周一]
        DateTime dt12 = DateTime.of(odt2);      // 2022-02-28 16:00:10.900000000 [+00:00 GMT 周一] --> 2022-03-01 00:00:10.900000000 [Asia/Shanghai +08:00 GMT+8 周二]
        DateTime dt13 = DateTime.of(calendar2);       // 2022-02-28 11:00:10.700000000 [America/New_York -05:00 GMT-5 周一] --> 2022-03-01 00:00:10.700000000 [Asia/Shanghai +08:00 GMT+8 周二]

        List<DateTime> instants01 = dt01.datesFromRange(dt02, 3, ChronoUnit.DAYS, CLOSED);
        List<DateTime> instants02 = dt01.datesFromRange(dt03, 3, ChronoUnit.DAYS, CLOSED);
        List<DateTime> instants03 = dt01.datesFromRange(dt02, 3, ChronoUnit.DAYS, CLOSED_OPEN);
        List<DateTime> instants04 = dt01.datesFromRange(dt03, 3, ChronoUnit.DAYS, CLOSED_OPEN);
        List<DateTime> instants05 = dt01.datesFromRange(dt02, 3, ChronoUnit.DAYS, OPEN);
        List<DateTime> instants06 = dt01.datesFromRange(dt03, 3, ChronoUnit.DAYS, OPEN);
        List<DateTime> instants07 = dt01.datesFromRange(dt02, 3, ChronoUnit.DAYS, OPEN_CLOSED);
        List<DateTime> instants08 = dt01.datesFromRange(dt03, 3, ChronoUnit.DAYS, OPEN_CLOSED);
        List<DateTime> offsetDateTimes01 = dt02.datesFromRange(dt01, 3, ChronoUnit.DAYS, CLOSED);
        List<DateTime> calendars01 = dt03.datesFromRange(dt01, 3, ChronoUnit.DAYS, CLOSED);
        List<DateTime> offsetDateTimes02 = dt02.datesFromRange(dt01, 3, ChronoUnit.DAYS, CLOSED_OPEN);
        List<DateTime> calendars02 = dt03.datesFromRange(dt01, 3, ChronoUnit.DAYS, CLOSED_OPEN);
        List<DateTime> offsetDateTimes03 = dt02.datesFromRange(dt01, 3, ChronoUnit.DAYS, OPEN);
        List<DateTime> calendars03 = dt03.datesFromRange(dt01, 3, ChronoUnit.DAYS, OPEN);
        List<DateTime> offsetDateTimes04 = dt02.datesFromRange(dt01, 3, ChronoUnit.DAYS, OPEN_CLOSED);
        List<DateTime> calendars04 = dt03.datesFromRange(dt01, 3, ChronoUnit.DAYS, OPEN_CLOSED);

        System.out.println(G.toString(instants01));
        System.out.println(G.toString(instants02));
        System.out.println(G.toString(instants03));
        System.out.println(G.toString(instants04));
        System.out.println(G.toString(instants05));
        System.out.println(G.toString(instants06));
        System.out.println(G.toString(instants07));
        System.out.println(G.toString(instants08));
        System.out.println(G.toString(offsetDateTimes01));
        System.out.println(G.toString(calendars01));
        System.out.println(G.toString(offsetDateTimes02));
        System.out.println(G.toString(calendars02));
        System.out.println(G.toString(offsetDateTimes03));
        System.out.println(G.toString(calendars03));
        System.out.println(G.toString(offsetDateTimes04));
        System.out.println(G.toString(calendars04));

        List<DateTime> instants11 = dt11.datesFromRange(dt12, -90, ChronoUnit.SECONDS, CLOSED);
        List<DateTime> instants12 = dt11.datesFromRange(dt13, -90, ChronoUnit.SECONDS, CLOSED);
        List<DateTime> instants13 = dt11.datesFromRange(dt12, -90, ChronoUnit.SECONDS, CLOSED_OPEN);
        List<DateTime> instants14 = dt11.datesFromRange(dt13, -90, ChronoUnit.SECONDS, CLOSED_OPEN);
        List<DateTime> instants15 = dt11.datesFromRange(dt12, -90, ChronoUnit.SECONDS, OPEN);
        List<DateTime> instants16 = dt11.datesFromRange(dt13, -90, ChronoUnit.SECONDS, OPEN);
        List<DateTime> instants17 = dt11.datesFromRange(dt12, -90, ChronoUnit.SECONDS, OPEN_CLOSED);
        List<DateTime> instants18 = dt11.datesFromRange(dt13, -90, ChronoUnit.SECONDS, OPEN_CLOSED);
        List<DateTime> offsetDateTimes11 = dt12.datesFromRange(dt11, -90, ChronoUnit.SECONDS, CLOSED);
        List<DateTime> calendars11 = dt13.datesFromRange(dt11, -90, ChronoUnit.SECONDS, CLOSED);
        List<DateTime> offsetDateTimes12 = dt12.datesFromRange(dt11, -90, ChronoUnit.SECONDS, CLOSED_OPEN);
        List<DateTime> calendars12 = dt13.datesFromRange(dt11, -90, ChronoUnit.SECONDS, CLOSED_OPEN);
        List<DateTime> offsetDateTimes13 = dt12.datesFromRange(dt11, -90, ChronoUnit.SECONDS, OPEN);
        List<DateTime> calendars13 = dt13.datesFromRange(dt11, -90, ChronoUnit.SECONDS, OPEN);
        List<DateTime> offsetDateTimes14 = dt12.datesFromRange(dt11, -90, ChronoUnit.SECONDS, OPEN_CLOSED);
        List<DateTime> calendars14 = dt13.datesFromRange(dt11, -90, ChronoUnit.SECONDS, OPEN_CLOSED);

        System.out.println("============================================");
        System.out.println(G.toString(instants11));
        System.out.println(G.toString(instants12));
        System.out.println(G.toString(instants13));
        System.out.println(G.toString(instants14));
        System.out.println(G.toString(instants15));
        System.out.println(G.toString(instants16));
        System.out.println(G.toString(instants17));
        System.out.println(G.toString(instants18));
        System.out.println(G.toString(offsetDateTimes11));
        System.out.println(G.toString(calendars11));
        System.out.println(G.toString(offsetDateTimes12));
        System.out.println(G.toString(calendars12));
        System.out.println(G.toString(offsetDateTimes13));
        System.out.println(G.toString(calendars13));
        System.out.println(G.toString(offsetDateTimes14));
        System.out.println(G.toString(calendars14));

        assertEquals(G.toString(instants01), "[2022-02-27 08:00:10.000, 2022-03-02 08:00:10.000, 2022-03-05 08:00:10.000, 2022-03-08 08:00:10.000]");
        assertEquals(G.toString(instants02), "[2022-02-27 08:00:10.000, 2022-03-02 08:00:10.000, 2022-03-05 08:00:10.000]");
        assertEquals(G.toString(instants03), "[2022-02-27 08:00:10.000, 2022-03-02 08:00:10.000, 2022-03-05 08:00:10.000]");
        assertEquals(G.toString(instants04), "[2022-02-27 08:00:10.000, 2022-03-02 08:00:10.000, 2022-03-05 08:00:10.000]");
        assertEquals(G.toString(instants05), "[2022-03-02 08:00:10.000, 2022-03-05 08:00:10.000]");
        assertEquals(G.toString(instants06), "[2022-03-02 08:00:10.000, 2022-03-05 08:00:10.000]");
        assertEquals(G.toString(instants07), "[2022-03-02 08:00:10.000, 2022-03-05 08:00:10.000, 2022-03-08 08:00:10.000]");
        assertEquals(G.toString(instants08), "[2022-03-02 08:00:10.000, 2022-03-05 08:00:10.000]");
        assertEquals(G.toString(offsetDateTimes01), "[2022-02-27 00:00:11.000 [Z +00:00], 2022-03-02 00:00:11.000 [Z +00:00], 2022-03-05 00:00:11.000 [Z +00:00], 2022-03-08 00:00:11.000 [Z +00:00]]");
        assertEquals(G.toString(calendars01), "[2022-03-02 03:00:09.000 [Europe/Moscow +03:00], 2022-03-05 03:00:09.000 [Europe/Moscow +03:00], 2022-03-08 03:00:09.000 [Europe/Moscow +03:00]]");
        assertEquals(G.toString(offsetDateTimes02), "[2022-03-02 00:00:11.000 [Z +00:00], 2022-03-05 00:00:11.000 [Z +00:00], 2022-03-08 00:00:11.000 [Z +00:00]]");
        assertEquals(G.toString(calendars02), "[2022-03-02 03:00:09.000 [Europe/Moscow +03:00], 2022-03-05 03:00:09.000 [Europe/Moscow +03:00], 2022-03-08 03:00:09.000 [Europe/Moscow +03:00]]");
        assertEquals(G.toString(offsetDateTimes03), "[2022-03-02 00:00:11.000 [Z +00:00], 2022-03-05 00:00:11.000 [Z +00:00]]");
        assertEquals(G.toString(calendars03), "[2022-03-02 03:00:09.000 [Europe/Moscow +03:00], 2022-03-05 03:00:09.000 [Europe/Moscow +03:00]]");
        assertEquals(G.toString(offsetDateTimes04), "[2022-02-27 00:00:11.000 [Z +00:00], 2022-03-02 00:00:11.000 [Z +00:00], 2022-03-05 00:00:11.000 [Z +00:00]]");
        assertEquals(G.toString(calendars04), "[2022-03-02 03:00:09.000 [Europe/Moscow +03:00], 2022-03-05 03:00:09.000 [Europe/Moscow +03:00]]");
        assertEquals(G.toString(instants11), "[2022-02-28 23:55:40.800, 2022-02-28 23:57:10.800, 2022-02-28 23:58:40.800, 2022-03-01 00:00:10.800]");
        assertEquals(G.toString(instants12), "[2022-02-28 23:55:40.800, 2022-02-28 23:57:10.800, 2022-02-28 23:58:40.800]");
        assertEquals(G.toString(instants13), "[2022-02-28 23:55:40.800, 2022-02-28 23:57:10.800, 2022-02-28 23:58:40.800]");
        assertEquals(G.toString(instants14), "[2022-02-28 23:55:40.800, 2022-02-28 23:57:10.800, 2022-02-28 23:58:40.800]");
        assertEquals(G.toString(instants15), "[2022-02-28 23:57:10.800, 2022-02-28 23:58:40.800]");
        assertEquals(G.toString(instants16), "[2022-02-28 23:57:10.800, 2022-02-28 23:58:40.800]");
        assertEquals(G.toString(instants17), "[2022-02-28 23:57:10.800, 2022-02-28 23:58:40.800, 2022-03-01 00:00:10.800]");
        assertEquals(G.toString(instants18), "[2022-02-28 23:57:10.800, 2022-02-28 23:58:40.800]");
        assertEquals(G.toString(offsetDateTimes11), "[2022-02-28 15:55:40.900 [Z +00:00], 2022-02-28 15:57:10.900 [Z +00:00], 2022-02-28 15:58:40.900 [Z +00:00], 2022-02-28 16:00:10.900 [Z +00:00]]");
        assertEquals(G.toString(calendars11), "[2022-02-28 10:57:10.700 [America/New_York -05:00], 2022-02-28 10:58:40.700 [America/New_York -05:00], 2022-02-28 11:00:10.700 [America/New_York -05:00]]");
        assertEquals(G.toString(offsetDateTimes12), "[2022-02-28 15:57:10.900 [Z +00:00], 2022-02-28 15:58:40.900 [Z +00:00], 2022-02-28 16:00:10.900 [Z +00:00]]");
        assertEquals(G.toString(calendars12), "[2022-02-28 10:57:10.700 [America/New_York -05:00], 2022-02-28 10:58:40.700 [America/New_York -05:00], 2022-02-28 11:00:10.700 [America/New_York -05:00]]");
        assertEquals(G.toString(offsetDateTimes13), "[2022-02-28 15:57:10.900 [Z +00:00], 2022-02-28 15:58:40.900 [Z +00:00]]");
        assertEquals(G.toString(calendars13), "[2022-02-28 10:57:10.700 [America/New_York -05:00], 2022-02-28 10:58:40.700 [America/New_York -05:00]]");
        assertEquals(G.toString(offsetDateTimes14), "[2022-02-28 15:55:40.900 [Z +00:00], 2022-02-28 15:57:10.900 [Z +00:00], 2022-02-28 15:58:40.900 [Z +00:00]]");
        assertEquals(G.toString(calendars14), "[2022-02-28 10:57:10.700 [America/New_York -05:00], 2022-02-28 10:58:40.700 [America/New_York -05:00]]");

    }

    @Test
    public void testDatesFromRange1() {
        LocalDateTime ldt1 = LocalDateTime.of(2022, 2, 27, 8, 0, 10, 100);
        LocalDateTime ldt2 = LocalDateTime.of(2022, 3, 30, 8, 0, 11, 100);
        DateTime dt01 = DateTime.of(ldt1);
        DateTime dt02 = DateTime.of(ldt2);

        List<DateTime> dateTimes01 = dt01.datesFromRange(dt02, 2, ChronoUnit.WEEKS, CLOSED);
        List<DateTime> dateTimes02 = dt02.datesFromRange(dt01, -2, ChronoUnit.WEEKS, CLOSED);
        List<DateTime> dateTimes03 = dt01.datesFromRange(dt02, 2, ChronoUnit.WEEKS, CLOSED_OPEN);
        List<DateTime> dateTimes04 = dt02.datesFromRange(dt01, -2, ChronoUnit.WEEKS, CLOSED_OPEN);
        List<DateTime> dateTimes05 = dt01.datesFromRange(dt02, 2, ChronoUnit.WEEKS, OPEN);
        List<DateTime> dateTimes06 = dt02.datesFromRange(dt01, -2, ChronoUnit.WEEKS, OPEN);
        List<DateTime> dateTimes07 = dt01.datesFromRange(dt02, 2, ChronoUnit.WEEKS, OPEN_CLOSED);
        List<DateTime> dateTimes08 = dt02.datesFromRange(dt01, -2, ChronoUnit.WEEKS, OPEN_CLOSED);
        List<DateTime> dateTimes09 = dt01.datesFromRange(dt01, 2, ChronoUnit.WEEKS, CLOSED);
        List<DateTime> dateTimes10 = dt01.datesFromRange(dt01, 2, ChronoUnit.WEEKS, CLOSED_OPEN);
        List<DateTime> dateTimes11 = dt01.datesFromRange(dt01, -2, ChronoUnit.WEEKS, OPEN);
        List<DateTime> dateTimes12 = dt01.datesFromRange(dt01, -2, ChronoUnit.WEEKS, OPEN_CLOSED);

        System.out.println(G.toString(dateTimes01));
        System.out.println(G.toString(dateTimes02));
        System.out.println(G.toString(dateTimes03));
        System.out.println(G.toString(dateTimes04));
        System.out.println(G.toString(dateTimes05));
        System.out.println(G.toString(dateTimes06));
        System.out.println(G.toString(dateTimes07));
        System.out.println(G.toString(dateTimes08));
        System.out.println(G.toString(dateTimes09));
        System.out.println(G.toString(dateTimes10));
        System.out.println(G.toString(dateTimes11));
        System.out.println(G.toString(dateTimes12));

        assertEquals(G.toString(dateTimes01), "[2022-02-27 08:00:10.000, 2022-03-13 08:00:10.000, 2022-03-27 08:00:10.000]");
        assertEquals(G.toString(dateTimes02), "[2022-03-02 08:00:11.000, 2022-03-16 08:00:11.000, 2022-03-30 08:00:11.000]");
        assertEquals(G.toString(dateTimes03), "[2022-02-27 08:00:10.000, 2022-03-13 08:00:10.000, 2022-03-27 08:00:10.000]");
        assertEquals(G.toString(dateTimes04), "[2022-03-02 08:00:11.000, 2022-03-16 08:00:11.000, 2022-03-30 08:00:11.000]");
        assertEquals(G.toString(dateTimes05), "[2022-03-13 08:00:10.000, 2022-03-27 08:00:10.000]");
        assertEquals(G.toString(dateTimes06), "[2022-03-02 08:00:11.000, 2022-03-16 08:00:11.000]");
        assertEquals(G.toString(dateTimes07), "[2022-03-13 08:00:10.000, 2022-03-27 08:00:10.000]");
        assertEquals(G.toString(dateTimes08), "[2022-03-02 08:00:11.000, 2022-03-16 08:00:11.000]");
        assertEquals(G.toString(dateTimes09), "[2022-02-27 08:00:10.000]");
        assertEquals(G.toString(dateTimes10), "[2022-02-27 08:00:10.000]");
        assertEquals(G.toString(dateTimes11), "[]");
        assertEquals(G.toString(dateTimes12), "[]");

    }


    @Test
    public void testParse() {
        System.out.println("testParse-jdk: " + OS.JAVA_VERSION);

        DateTime dt0 = DateTime.parse("2022-8-01 10:5:15");
        DateTime dt1 = DateTime.parse("2022-8-01T10:5:15", "y-M-d'T'H:m:s");
        DateTime dt2 = DateTime.parse("2022-8-01T10:5:15.987", "yyyy-MM-dd'T'HH:mm:ss.SSS");
        DateTime dt3 = DateTime.parse("2022", "y");
        DateTime dt4 = DateTime.parse("10点5分", "HH点mm分");
        DateTime dt5 = DateTime.parse("9/22/2024", "M/d/y");
        DateTime dt6 = DateTime.parse("9/22/2024 9", "M/d/y H");
        DateTime dt00 = DateTime.parse("2022-8-01 10:5:15", TZ.UTC);
        DateTime dt01 = DateTime.parse("2022-8-01T10:5:15.987", "yyyy-MM-dd'T'HH:mm:ss.SSS");
        DateTime dt02 = DateTime.parse("2022-8-01T10:5:15.987", "y-M-d'T'H:m:s.SSS", TZ.MOSCOW);
        DateTime dt03 = DateTime.parse("2022", "y");
        DateTime dt04 = DateTime.parse("2点5分", "H点m分", TZ.NEW_YORK);
        DateTime dt05 = DateTime.parse("9/22/2024", "M/d/y");
        DateTime dt06 = DateTime.parse("9/22/2024 23", "M/d/y H");
        System.out.println(dt0.dtDetail());         // 2022-08-01 10:05:15.000000000 [周一]
        System.out.println(dt1.dtDetail());         // 2022-08-01 10:05:15.000000000 [周一]
        System.out.println(dt2.dtDetail());         // 2022-08-01 10:05:15.987000000 [周一]
        System.out.println(dt3.dtDetail());         // 2022-01-01 00:00:00.000000000 [周六]
        System.out.println(dt4.dtDetail());         // 1970-01-01 10:05:00.000000000 [周四]
        System.out.println(dt5.dtDetail());         // 2024-09-22 00:00:00.000000000 [周日]
        System.out.println(dt6.dtDetail());         // 2024-09-22 09:00:00.000000000 [周日]
        System.out.println(dt00.dtDetail());        // 2022-08-01 18:05:15.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        System.out.println(dt01.dtDetail());        // 2022-08-01 10:05:15.987000000 [Asia/Shanghai +08:00 GMT+8 周一]
        System.out.println(dt02.dtDetail());        // 2022-08-01 15:05:15.987000000 [Asia/Shanghai +08:00 GMT+8 周一]
        System.out.println(dt03.dtDetail());        // 2022-01-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周六]
        System.out.println(dt04.dtDetail());        // 1970-01-01 15:05:00.000000000 [Asia/Shanghai +08:00 GMT+8 周四]
        System.out.println(dt05.dtDetail());        // 2024-09-22 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        System.out.println(dt06.dtDetail());        // 2024-09-22 23:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周日]

        assertEquals(dt0.dtDetail(), "2022-08-01 10:05:15.000000000 [Asia/Shanghai +08:00 GMT+8 周一]");
        assertEquals(dt1.dtDetail(), "2022-08-01 10:05:15.000000000 [Asia/Shanghai +08:00 GMT+8 周一]");
        assertEquals(dt2.dtDetail(), "2022-08-01 10:05:15.987000000 [Asia/Shanghai +08:00 GMT+8 周一]");
        assertEquals(dt3.dtDetail(), "2022-01-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周六]");
        assertEquals(dt4.dtDetail(), "1970-01-01 10:05:00.000000000 [Asia/Shanghai +08:00 GMT+8 周四]");
        assertEquals(dt5.dtDetail(), "2024-09-22 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周日]");
        assertEquals(dt6.dtDetail(), "2024-09-22 09:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周日]");
        assertEquals(dt00.dtDetail(), "2022-08-01 10:05:15.000000000 [UTC +00:00 GMT 周一]");
        assertEquals(dt01.dtDetail(), "2022-08-01 10:05:15.987000000 [Asia/Shanghai +08:00 GMT+8 周一]");
        assertEquals(dt02.dtDetail(), "2022-08-01 10:05:15.987000000 [Europe/Moscow +03:00 GMT+3 周一]");
        assertEquals(dt03.dtDetail(), "2022-01-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周六]");
        assertEquals(dt04.dtDetail(), "1970-01-01 02:05:00.000000000 [America/New_York -05:00 GMT-5 周四]");
        assertEquals(dt05.dtDetail(), "2024-09-22 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周日]");
        assertEquals(dt06.dtDetail(), "2024-09-22 23:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周日]");

    }

    @Test
    public void testParseAuto() {
        System.out.println("testParseAuto-jdk: " + OS.JAVA_VERSION);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("y-MM-dd HH:mm:ss.SSS '['VV xxx']'");
        DateTimeFormatter dtfWithZone = DateTimeFormatter.ofPattern("y-MM-dd HH:mm:ss.SSS '['VV xxx']'").withZone(ZoneId.of("GMT+4"));
        DateTimeFormatter offsetDtf = DateTimeFormatter.ofPattern("y-MM-dd HH:mm:ss.SSS '['xxx']'");
        DateTimeFormatter offsetDtfWithZone = DateTimeFormatter.ofPattern("y-MM-dd HH:mm:ss.SSS '['xxx']'").withZone(ZoneId.of("GMT+4"));

        DateTime dt0 = DateTime.parse("2022-8-01 10:5:15");  // 2022-08-01 10:05:15.000000000 [周一]
        DateTime dt1 = DateTime.parse("2022-8-01T10:5:15");  // 2022-08-01 10:05:15.000000000 [周一]
        DateTime dt2 = DateTime.parse("2022-8-01T10:5:15.987");  // 2022-08-01 10:05:15.987000000 [周一]
        DateTime dt3 = DateTime.parse("2022");   // 2022-01-01 00:00:00.000000000 [周六]
        DateTime dt4 = DateTime.parse("999");    // 0999-01-01 00:00:00.000000000 [周二]
        DateTime dt5 = DateTime.parse("10点5分");  // 1970-01-01 10:05:00.000000000 [周四]
        DateTime dt6 = DateTime.parse("2022-8-01T10:5:15.98");   // 2022-08-01 10:05:15.980000000 [周一]
        DateTime dt7 = DateTime.parse("202208"); // 2022-08-01 00:00:00.000000000 [周一]
        DateTime dt8 = DateTime.parse("20220810");   // 2022-08-10 00:00:00.000000000 [周三]
        DateTime dt9 = DateTime.parse("2022081017"); // 2022-08-10 17:00:00.000000000 [周三]
        DateTime dt10 = DateTime.parse("202208101706");  // 2022-08-10 17:06:00.000000000 [周三]
        DateTime dt11 = DateTime.parse("20220810170650");    // 2022-08-10 17:06:50.000000000 [周三]
        DateTime dt12 = DateTime.parse("20220810170650666"); // 2022-08-10 17:06:50.666000000 [周三]
        DateTime dt13 = DateTime.parse("20220810T170650666"); // 2022-08-10 17:06:50.666000000 [周三]
        DateTime dt14 = DateTime.parse("2点5分10秒98毫秒");       // 1970-01-01 02:05:10.098000000 [周四]
        DateTime dt15 = DateTime.parse("120220810170650666", "yyyyyMMddHHmmssSSS");  // 12022-08-10 17:06:50.666000000 [周三]
        DateTime dt16 = DateTime.parse("5分10秒8毫秒");           // 1970-01-01 00:05:10.008000000 [周四]
        DateTime dt17 = DateTime.parse("5分10秒98毫秒");          // 1970-01-01 00:05:10.098000000 [周四]
        DateTime ldt01 = DateTime.parse("999/01/2T6:16:6.6Z");   // 999-01-02 06:16:06.600000000 [周三]
        DateTime ldt02 = DateTime.parse("2020-01-2T6:16:6.65Z");     // 2020-01-02 06:16:06.650000000 [周四]
        DateTime ldt03 = DateTime.parse("2020.01.2T6:16:6.036Z");    // 2020-01-02 06:16:06.036000000 [周四]

        DateTime dt00 = DateTime.parse("2022-8-01 10:5:15", TZ.UTC);  // 2022-08-01 18:05:15.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        DateTime dt01 = DateTime.parse("2022-8-01T10:5:15");  // 2022-08-01 10:05:15.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        DateTime dt02 = DateTime.parse("2022-8-01T10:5:15.987");  // 2022-08-01 10:05:15.987000000 [Asia/Shanghai +08:00 GMT+8 周一]
        DateTime dt03 = DateTime.parse("2022");   // 2022-01-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周六]
        DateTime dt04 = DateTime.parse("999");    // 0999-01-01 00:00:00.000000000 [Asia/Shanghai +08:05 GMT+8:05:43 周二]
        DateTime dt05 = DateTime.parse("2点5分", TZ.NEW_YORK);  // 1970-01-01 15:05:00.000000000 [Asia/Shanghai +08:00 GMT+8 周四]
        DateTime dt06 = DateTime.parse("2022-8-01T10:5:15.98");   // 2022-08-01 10:05:15.980000000 [Asia/Shanghai +08:00 GMT+8 周一]
        DateTime dt07 = DateTime.parse("20220810T170650666");     // 2022-08-10 17:06:50.666000000 [Asia/Shanghai +08:00 GMT+8 周三]
        DateTime dt08 = DateTime.parse("20220810170650666"); // 2022-08-10 17:06:50.666000000 [Asia/Shanghai +08:00 GMT+8 周三]
        DateTime dt20 = DateTime.parse("2点5分10秒98毫秒");        // 1970-01-01 02:05:10.098000000 [Asia/Shanghai +08:00 GMT+8 周四]
        DateTime dt21 = DateTime.parse("2点5分10秒098毫秒");       // 1970-01-01 02:05:10.098000000 [Asia/Shanghai +08:00 GMT+8 周四]
        DateTime dt22 = DateTime.parse("2023年10月1日2点5分10秒1毫秒");   // 2023-10-01 02:05:10.001000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dt23 = DateTime.parse("2023年10月1日2时5分10秒100毫秒"); // 2023-10-01 02:05:10.100000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dt24 = DateTime.parse("020220810170650666", "yyyyyMMddHHmmssSSS");   // 2022-08-10 17:06:50.666000000 [Asia/Shanghai +08:00 GMT+8 周三]
        DateTime dt25 = DateTime.parse("5分10秒8毫秒");            // 1970-01-01 00:05:10.008000000 [Asia/Shanghai +08:00 GMT+8 周四]
        DateTime dt26 = DateTime.parse("5分10秒98毫秒");           // 1970-01-01 00:05:10.098000000 [Asia/Shanghai +08:00 GMT+8 周四]
        DateTime dt27 = DateTime.parse("5分10秒");                 // 1970-01-01 00:05:10.000000000 [Asia/Shanghai +08:00 GMT+8 周四]
        DateTime dt28 = DateTime.parse("999/01/2T6:16:6.6Z");     // 999-01-02 14:21:49.600000000 [Asia/Shanghai +08:05 GMT+8:05:43 周三]
        DateTime dt29 = DateTime.parse("2020-01-2T6:16:6.65Z");   // 2020-01-02 14:16:06.650000000 [Asia/Shanghai +08:00 GMT+8 周四]
        DateTime dt30 = DateTime.parse("2020.01.2T6:16:6.036Z");  // 2020-01-02 14:16:06.036000000 [Asia/Shanghai +08:00 GMT+8 周四]
        DateTime dt31 = DateTime.of("999/01/2T6:16:6.6Z");         // 999-01-02 14:21:49.600000000 [Asia/Shanghai +08:05 GMT+8:05:43 周三]
        DateTime dt32 = DateTime.of("2020-01-2T6:16:6.65Z");      // 2020-01-02 14:16:06.650000000 [Asia/Shanghai +08:00 GMT+8 周四]
        DateTime dt33 = DateTime.of("2020.01.2T6:16:6.036Z");     // 2020-01-02 14:16:06.036000000 [Asia/Shanghai +08:00 GMT+8 周四]
        DateTime dt34 = DateTime.parse("999-1-2 6:6:6 [GMT+04:00 +04:00]");       // 999-01-02 10:11:49.000000000 [Asia/Shanghai +08:05 GMT+8:05:43 周三]
        DateTime dt35 = DateTime.parse("999/1/2 6:6:6.6 [Asia/Shanghai +08:00]"); // 999-01-02 06:11:49.600000000 [Asia/Shanghai +08:05 GMT+8:05:43 周三]
        DateTime dt36 = DateTime.parse("2025-01-02 06:16:26.036 [Asia/Shanghai +08:00]"); // 2025-01-02 06:16:26.036000000 [Asia/Shanghai +08:00 GMT+8 周四]
        DateTime dt37 = DateTime.parse("999-1-2 6:6:6 [+04:00]");         // 999-01-02 10:11:49.000000000 [Asia/Shanghai +08:05 GMT+8:05:43 周三]
        DateTime dt38 = DateTime.parse("999.1.2 6:6:6.6 [+08:00]");       // 999-01-02 06:11:49.600000000 [Asia/Shanghai +08:05 GMT+8:05:43 周三]
        DateTime dt39 = DateTime.parse("2025-01-02 06:16:26.036 [+08:00]");           // 2025-01-02 06:16:26.036000000 [Asia/Shanghai +08:00 GMT+8 周四]
        DateTime dt40 = DateTime.parse("2025.01.02 06:16:26.036 [+08:00]");           // 2025-01-02 06:16:26.036000000 [Asia/Shanghai +08:00 GMT+8 周四]
        DateTime dt41 = DateTime.parse("2025/01/02 06:16:26.036 [+08:00 +08:00]");    // 2025-01-02 06:16:26.036000000 [Asia/Shanghai +08:00 GMT+8 周四]
        DateTime dt42 = DateTime.parse("2025.01.02 06:16:26.036 [GMT+04:00 +04:00]"); // 2025-01-02 10:16:26.036000000 [Asia/Shanghai +08:00 GMT+8 周四]

        DateTime dt50 = DateTime.parse("2022-8-01 10:5:15");              // 2022-08-01 10:05:15.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        DateTime dt51 = DateTime.parse("2022-8-01T10:5:15");              // 2022-08-01 10:05:15.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        DateTime dt52 = DateTime.parse("2022-8-01T10:5:15.987");              // 2022-08-01 10:05:15.987000000 [Asia/Shanghai +08:00 GMT+8 周一]
        DateTime dt53 = DateTime.parse("2022");               // 2022-01-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周六]
        DateTime dt54 = DateTime.parse("999");                // 999-01-01 00:00:00.000000000 [Asia/Shanghai +08:05 GMT+8:05:43 周二]
        DateTime dt55 = DateTime.parse("2点5分");               // 1970-01-01 02:05:00.000000000 [Asia/Shanghai +08:00 GMT+8 周四]
        DateTime dt56 = DateTime.parse("2022-8-01T10:5:15.98");               // 2022-08-01 10:05:15.980000000 [Asia/Shanghai +08:00 GMT+8 周一]
        DateTime dt57 = DateTime.parse("20220810T170650666");             // 2022-08-10 17:06:50.666000000 [Asia/Shanghai +08:00 GMT+8 周三]
        DateTime dt58 = DateTime.parse("20220810170650666");              // 2022-08-10 17:06:50.666000000 [Asia/Shanghai +08:00 GMT+8 周三]
        DateTime dt59 = DateTime.parse("2点5分10秒98毫秒");                // 1970-01-01 02:05:10.098000000 [Asia/Shanghai +08:00 GMT+8 周四]
        DateTime dt60 = DateTime.parse("2点5分10秒098毫秒");               // 1970-01-01 02:05:10.098000000 [Asia/Shanghai +08:00 GMT+8 周四]
        DateTime dt61 = DateTime.parse("2023年10月1日2点5分10秒1毫秒");               // 2023-10-01 02:05:10.001000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dt62 = DateTime.parse("2023年10月1日2时5分10秒100毫秒");             // 2023-10-01 02:05:10.100000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dt63 = DateTime.parse("020220810170650666", "yyyyyMMddHHmmssSSS");   // 2022-08-10 17:06:50.666000000 [Asia/Shanghai +08:00 GMT+8 周三]
        DateTime dt64 = DateTime.parse("5分10秒8毫秒");               // 1970-01-01 00:05:10.008000000 [Asia/Shanghai +08:00 GMT+8 周四]
        DateTime dt65 = DateTime.parse("5分10秒98毫秒");              // 1970-01-01 00:05:10.098000000 [Asia/Shanghai +08:00 GMT+8 周四]
        DateTime dt66 = DateTime.parse("5分10秒");              // 1970-01-01 00:05:10.000000000 [Asia/Shanghai +08:00 GMT+8 周四]
        DateTime dt67 = DateTime.parse("999/01/2T6:16:6.6Z");             // 999-01-02 06:16:06.600000000 [Z +00:00 GMT 周三]
        DateTime dt68 = DateTime.parse("2020-01-2T6:16:6.65Z");               // 2020-01-02 06:16:06.650000000 [Z +00:00 GMT 周四]
        DateTime dt69 = DateTime.parse("2020.01.2T6:16:6.036Z");              // 2020-01-02 06:16:06.036000000 [Z +00:00 GMT 周四]
        DateTime dt70 = DateTime.parse("2023年10月1日2点5分10秒1毫秒", TZ.DUBAI);
        DateTime dt71 = DateTime.parse("2020-01-2T6:16:6.65Z", TZ.DUBAI);

        String date1 = "2025-01-02 06:16:06.036 [Z +00:00]";
        String date2 = "2025-01-02 06:16:06.036 [Asia/Dubai +04:00]";
        String date3 = "2025-01-02 06:16:06.036 [Asia/Shanghai +08:00]";
        String date4 = "2025-01-02 06:16:06.036 [+00:00]";
        String date5 = "2025-01-02 06:16:06.036 [+04:00]";
        String date6 = "2025-01-02 06:16:06.036 [+08:00]";
        System.out.println("============================================================");
        DateTime dt72 = DateTime.parse(date1, dtf);                   // 2025-01-02 06:16:06.036000000 [Z +00:00 GMT 周四]
        DateTime dt73 = DateTime.parse(date2, dtf);                   // 2025-01-02 06:16:06.036000000 [Asia/Dubai +04:00 GMT+4 周四]
        DateTime dt74 = DateTime.parse(date3, dtf);                   // 2025-01-02 06:16:06.036000000 [Asia/Shanghai +08:00 GMT+8 周四]
        DateTime dt75 = DateTime.parse(date1, dtfWithZone);           // 2025-01-02 06:16:06.036000000 [Z +00:00 GMT 周四]
        DateTime dt76 = DateTime.parse(date2, dtfWithZone);           // 2025-01-02 06:16:06.036000000 [Asia/Dubai +04:00 GMT+4 周四]
        DateTime dt77 = DateTime.parse(date3, dtfWithZone);           // 2025-01-02 06:16:06.036000000 [Asia/Shanghai +08:00 GMT+8 周四]
        DateTime dt78 = DateTime.parse(date4, offsetDtf);             // 2025-01-02 06:16:06.036000000 [Z +00:00 GMT 周四]
        DateTime dt79 = DateTime.parse(date5, offsetDtf);             // 2025-01-02 06:16:06.036000000 [+04:00 +04:00 GMT+4 周四]
        DateTime dt80 = DateTime.parse(date6, offsetDtf);             // 2025-01-02 06:16:06.036000000 [+08:00 +08:00 GMT+8 周四]
        DateTime dt81 = DateTime.parse(date4, offsetDtfWithZone);     // 2025-01-02 10:16:06.036000000 [GMT+04:00 +04:00 GMT+4 周四]
        DateTime dt82 = DateTime.parse(date5, offsetDtfWithZone);     // 2025-01-02 06:16:06.036000000 [GMT+04:00 +04:00 GMT+4 周四]
        DateTime dt83 = DateTime.parse(date6, offsetDtfWithZone);     // 2025-01-02 02:16:06.036000000 [GMT+04:00 +04:00 GMT+4 周四]

        DateTime dt84 = DateTime.parse("999-1-2 6:6:6 [GMT+04:00 +04:00]");
        DateTime dt85 = DateTime.parse("999/1/2 6:6:6.6 [Asia/Shanghai +08:00]");
        DateTime dt86 = DateTime.parse("2025-01-02 06:16:26.036 [Asia/Shanghai +08:00]");
        DateTime dt87 = DateTime.parse("999-1-2 6:6:6 [+04:00]");
        DateTime dt88 = DateTime.parse("999.1.2 6:6:6.6 [+08:00]");
        DateTime dt89 = DateTime.parse("2025-01-02 06:16:26.036 [+08:00]");
        DateTime dt90 = DateTime.parse("2025.01.02 06:16:26.036 [+08:00]");
        DateTime dt91 = DateTime.parse("2025/01/02 06:16:26.036 [+08:00 +08:00]");
        DateTime dt92 = DateTime.parse("2025.01.02 06:16:26.036 [GMT+04:00 +04:00]");

        System.out.println("dt0: " + dt0.dtDetail());         // 2022-08-01 10:05:15.000000000 [周一]
        System.out.println("dt1: " + dt1.dtDetail());         // 2022-08-01 10:05:15.000000000 [周一]
        System.out.println("dt2: " + dt2.dtDetail());         // 2022-08-01 10:05:15.987000000 [周一]
        System.out.println("dt3: " + dt3.dtDetail());         // 2022-01-01 00:00:00.000000000 [周六]
        System.out.println("dt4: " + dt4.dtDetail());         // 0999-01-01 00:00:00.000000000 [周二]
        System.out.println("dt5: " + dt5.dtDetail());         // 1970-01-01 10:05:00.000000000 [周四]
        System.out.println("dt6: " + dt6.dtDetail());         // 2022-08-01 10:05:15.980000000 [周一]
        System.out.println("dt7: " + dt7.dtDetail());         // 2022-08-01 00:00:00.000000000 [周一]
        System.out.println("dt8: " + dt8.dtDetail());         // 2022-08-10 00:00:00.000000000 [周三]
        System.out.println("dt9: " + dt9.dtDetail());         // 2022-08-10 17:00:00.000000000 [周三]
        System.out.println("dt10: " + dt10.dtDetail());        // 2022-08-10 17:06:00.000000000 [周三]
        System.out.println("dt11: " + dt11.dtDetail());        // 2022-08-10 17:06:50.000000000 [周三]
        System.out.println("dt12: " + dt12.dtDetail());        // 2022-08-10 17:06:50.666000000 [周三]
        System.out.println("dt13: " + dt13.dtDetail());        // 2022-08-10 17:06:50.666000000 [周三]
        System.out.println("dt14: " + dt14.dtDetail());
        System.out.println("dt15: " + dt15.dtDetail());
        System.out.println("dt16: " + dt16.dtDetail());
        System.out.println("dt17: " + dt17.dtDetail());
        System.out.println("ldt01: " + ldt01.dtDetail());
        System.out.println("ldt02: " + ldt02.dtDetail());
        System.out.println("ldt03: " + ldt03.dtDetail());
        System.out.println("============================================================================");
        System.out.println("dt00: " + dt00.dtDetail());        // 2022-08-01 18:05:15.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        System.out.println("dt01: " + dt01.dtDetail());        // 2022-08-01 10:05:15.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        System.out.println("dt02: " + dt02.dtDetail());        // 2022-08-01 10:05:15.987000000 [Asia/Shanghai +08:00 GMT+8 周一]
        System.out.println("dt03: " + dt03.dtDetail());        // 2022-01-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周六]
        System.out.println("dt04: " + dt04.dtDetail());        // 0999-01-01 00:00:00.000000000 [Asia/Shanghai +08:05 GMT+8:05:43 周二]
        System.out.println("dt05: " + dt05.dtDetail());        // 1970-01-01 15:05:00.000000000 [Asia/Shanghai +08:00 GMT+8 周四]
        System.out.println("dt06: " + dt06.dtDetail());        // 2022-08-01 10:05:15.980000000 [Asia/Shanghai +08:00 GMT+8 周一]
        System.out.println("dt07: " + dt07.dtDetail());        // 2022-08-10 17:06:50.666000000 [Asia/Shanghai +08:00 GMT+8 周三]
        System.out.println("dt08: " + dt08.dtDetail());        // 2022-08-10 17:06:50.666000000 [Asia/Shanghai +08:00 GMT+8 周三]
        System.out.println("dt20: " + dt20.dtDetail());
        System.out.println("dt21: " + dt21.dtDetail());
        System.out.println("dt22: " + dt22.dtDetail());
        System.out.println("dt23: " + dt23.dtDetail());
        System.out.println("dt24: " + dt24.dtDetail());
        System.out.println("dt25: " + dt25.dtDetail());
        System.out.println("dt26: " + dt26.dtDetail());
        System.out.println("dt27: " + dt27.dtDetail());
        System.out.println("dt28: " + dt28.dtDetail());
        System.out.println("dt29: " + dt29.dtDetail());
        System.out.println("dt30: " + dt30.dtDetail());
        System.out.println("dt31: " + dt31.dtDetail());
        System.out.println("dt32: " + dt32.dtDetail());
        System.out.println("dt33: " + dt33.dtDetail());
        System.out.println("dt34: " + dt34.dtDetail());
        System.out.println("dt35: " + dt35.dtDetail());
        System.out.println("dt36: " + dt36.dtDetail());
        System.out.println("dt37: " + dt37.dtDetail());
        System.out.println("dt38: " + dt38.dtDetail());
        System.out.println("dt39: " + dt39.dtDetail());
        System.out.println("dt40: " + dt40.dtDetail());
        System.out.println("dt41: " + dt41.dtDetail());
        System.out.println("dt42: " + dt42.dtDetail());
        System.out.println("============================================================================");
        System.out.println("dt50: " + dt50.dtDetail());
        System.out.println("dt51: " + dt51.dtDetail());
        System.out.println("dt52: " + dt52.dtDetail());
        System.out.println("dt53: " + dt53.dtDetail());
        System.out.println("dt54: " + dt54.dtDetail());
        System.out.println("dt55: " + dt55.dtDetail());
        System.out.println("dt56: " + dt56.dtDetail());
        System.out.println("dt57: " + dt57.dtDetail());
        System.out.println("dt58: " + dt58.dtDetail());
        System.out.println("dt59: " + dt59.dtDetail());
        System.out.println("dt60: " + dt60.dtDetail());
        System.out.println("dt61: " + dt61.dtDetail());
        System.out.println("dt62: " + dt62.dtDetail());
        System.out.println("dt63: " + dt63.dtDetail());
        System.out.println("dt64: " + dt64.dtDetail());
        System.out.println("dt65: " + dt65.dtDetail());
        System.out.println("dt66: " + dt66.dtDetail());
        System.out.println("dt67: " + dt67.dtDetail());
        System.out.println("dt68: " + dt68.dtDetail());
        System.out.println("dt69: " + dt69.dtDetail());
        System.out.println("dt70: " + dt70.dtDetail());
        System.out.println("dt71: " + dt71.dtDetail());
        System.out.println("============================================================");
        System.out.println("dt72: " + dt72.dtDetail());
        System.out.println("dt73: " + dt73.dtDetail());
        System.out.println("dt74: " + dt74.dtDetail());
        System.out.println("dt75: " + dt75.dtDetail());
        System.out.println("dt76: " + dt76.dtDetail());
        System.out.println("dt77: " + dt77.dtDetail());
        System.out.println("dt78: " + dt78.dtDetail());
        System.out.println("dt79: " + dt79.dtDetail());
        System.out.println("dt80: " + dt80.dtDetail());
        System.out.println("dt81: " + dt81.dtDetail());
        System.out.println("dt82: " + dt82.dtDetail());
        System.out.println("dt83: " + dt83.dtDetail());
        System.out.println("dt84: " + dt84.dtDetail());
        System.out.println("dt85: " + dt85.dtDetail());
        System.out.println("dt86: " + dt86.dtDetail());
        System.out.println("dt87: " + dt87.dtDetail());
        System.out.println("dt88: " + dt88.dtDetail());
        System.out.println("dt89: " + dt89.dtDetail());
        System.out.println("dt90: " + dt90.dtDetail());
        System.out.println("dt91: " + dt91.dtDetail());
        System.out.println("dt92: " + dt92.dtDetail());


        assertEquals(dt0.dtDetail(), "2022-08-01 10:05:15.000000000 [Asia/Shanghai +08:00 GMT+8 周一]");
        assertEquals(dt1.dtDetail(), "2022-08-01 10:05:15.000000000 [Asia/Shanghai +08:00 GMT+8 周一]");
        assertEquals(dt2.dtDetail(), "2022-08-01 10:05:15.987000000 [Asia/Shanghai +08:00 GMT+8 周一]");
        assertEquals(dt3.dtDetail(), "2022-01-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周六]");
        assertEquals(dt4.dtDetail(), "999-01-01 00:00:00.000000000 [Asia/Shanghai +08:05 GMT+8:05:43 周二]");
        assertEquals(dt5.dtDetail(), "1970-01-01 10:05:00.000000000 [Asia/Shanghai +08:00 GMT+8 周四]");
        assertEquals(dt6.dtDetail(), "2022-08-01 10:05:15.980000000 [Asia/Shanghai +08:00 GMT+8 周一]");
        assertEquals(dt7.dtDetail(), "2022-08-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周一]");
        assertEquals(dt8.dtDetail(), "2022-08-10 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周三]");
        assertEquals(dt9.dtDetail(), "2022-08-10 17:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周三]");
        assertEquals(dt10.dtDetail(), "2022-08-10 17:06:00.000000000 [Asia/Shanghai +08:00 GMT+8 周三]");
        assertEquals(dt11.dtDetail(), "2022-08-10 17:06:50.000000000 [Asia/Shanghai +08:00 GMT+8 周三]");
        assertEquals(dt12.dtDetail(), "2022-08-10 17:06:50.666000000 [Asia/Shanghai +08:00 GMT+8 周三]");
        assertEquals(dt13.dtDetail(), "2022-08-10 17:06:50.666000000 [Asia/Shanghai +08:00 GMT+8 周三]");
        assertEquals(dt14.dtDetail(), "1970-01-01 02:05:10.098000000 [Asia/Shanghai +08:00 GMT+8 周四]");
        assertEquals(dt15.dtDetail(), "12022-08-10 17:06:50.666000000 [Asia/Shanghai +08:00 GMT+8 周三]");
        assertEquals(dt16.dtDetail(), "1970-01-01 00:05:10.008000000 [Asia/Shanghai +08:00 GMT+8 周四]");
        assertEquals(dt17.dtDetail(), "1970-01-01 00:05:10.098000000 [Asia/Shanghai +08:00 GMT+8 周四]");
        assertEquals(ldt01.dtDetail(), "999-01-02 06:16:06.600000000 [Z +00:00 GMT 周三]");
        assertEquals(ldt02.dtDetail(), "2020-01-02 06:16:06.650000000 [Z +00:00 GMT 周四]");
        assertEquals(ldt03.dtDetail(), "2020-01-02 06:16:06.036000000 [Z +00:00 GMT 周四]");

        assertEquals(dt00.dtDetail(), "2022-08-01 10:05:15.000000000 [UTC +00:00 GMT 周一]");
        assertEquals(dt01.dtDetail(), "2022-08-01 10:05:15.000000000 [Asia/Shanghai +08:00 GMT+8 周一]");
        assertEquals(dt02.dtDetail(), "2022-08-01 10:05:15.987000000 [Asia/Shanghai +08:00 GMT+8 周一]");
        assertEquals(dt03.dtDetail(), "2022-01-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周六]");
        assertEquals(dt04.dtDetail(), "999-01-01 00:00:00.000000000 [Asia/Shanghai +08:05 GMT+8:05:43 周二]");
        assertEquals(dt05.dtDetail(), "1970-01-01 02:05:00.000000000 [America/New_York -05:00 GMT-5 周四]");
        assertEquals(dt06.dtDetail(), "2022-08-01 10:05:15.980000000 [Asia/Shanghai +08:00 GMT+8 周一]");
        assertEquals(dt07.dtDetail(), "2022-08-10 17:06:50.666000000 [Asia/Shanghai +08:00 GMT+8 周三]");
        assertEquals(dt08.dtDetail(), "2022-08-10 17:06:50.666000000 [Asia/Shanghai +08:00 GMT+8 周三]");
        assertEquals(dt20.dtDetail(), "1970-01-01 02:05:10.098000000 [Asia/Shanghai +08:00 GMT+8 周四]");
        assertEquals(dt21.dtDetail(), "1970-01-01 02:05:10.098000000 [Asia/Shanghai +08:00 GMT+8 周四]");
        assertEquals(dt22.dtDetail(), "2023-10-01 02:05:10.001000000 [Asia/Shanghai +08:00 GMT+8 周日]");
        assertEquals(dt23.dtDetail(), "2023-10-01 02:05:10.100000000 [Asia/Shanghai +08:00 GMT+8 周日]");
        assertEquals(dt24.dtDetail(), "2022-08-10 17:06:50.666000000 [Asia/Shanghai +08:00 GMT+8 周三]");
        assertEquals(dt25.dtDetail(), "1970-01-01 00:05:10.008000000 [Asia/Shanghai +08:00 GMT+8 周四]");
        assertEquals(dt26.dtDetail(), "1970-01-01 00:05:10.098000000 [Asia/Shanghai +08:00 GMT+8 周四]");
        assertEquals(dt27.dtDetail(), "1970-01-01 00:05:10.000000000 [Asia/Shanghai +08:00 GMT+8 周四]");
        assertEquals(dt28.dtDetail(), "999-01-02 06:16:06.600000000 [Z +00:00 GMT 周三]");
        assertEquals(dt29.dtDetail(), "2020-01-02 06:16:06.650000000 [Z +00:00 GMT 周四]");
        assertEquals(dt30.dtDetail(), "2020-01-02 06:16:06.036000000 [Z +00:00 GMT 周四]");
        assertEquals(dt31.dtDetail(), "999-01-02 06:16:06.600000000 [Z +00:00 GMT 周三]");
        assertEquals(dt32.dtDetail(), "2020-01-02 06:16:06.650000000 [Z +00:00 GMT 周四]");
        assertEquals(dt33.dtDetail(), "2020-01-02 06:16:06.036000000 [Z +00:00 GMT 周四]");
        assertEquals(dt34.dtDetail(), "999-01-02 06:06:06.000000000 [GMT+04:00 +04:00 GMT+4 周三]");
        assertEquals(dt35.dtDetail(), "999-01-02 06:11:49.600000000 [Asia/Shanghai +08:05 GMT+8:05:43 周三]");
        assertEquals(dt36.dtDetail(), "2025-01-02 06:16:26.036000000 [Asia/Shanghai +08:00 GMT+8 周四]");
        assertEquals(dt37.dtDetail(), "999-01-02 06:06:06.000000000 [+04:00 +04:00 GMT+4 周三]");
        assertEquals(dt38.dtDetail(), "999-01-02 06:06:06.600000000 [+08:00 +08:00 GMT+8 周三]");
        assertEquals(dt39.dtDetail(), "2025-01-02 06:16:26.036000000 [+08:00 +08:00 GMT+8 周四]");
        assertEquals(dt40.dtDetail(), "2025-01-02 06:16:26.036000000 [+08:00 +08:00 GMT+8 周四]");
        assertEquals(dt41.dtDetail(), "2025-01-02 06:16:26.036000000 [+08:00 +08:00 GMT+8 周四]");
        assertEquals(dt42.dtDetail(), "2025-01-02 06:16:26.036000000 [GMT+04:00 +04:00 GMT+4 周四]");

        assertEquals(dt50.dtDetail(), "2022-08-01 10:05:15.000000000 [Asia/Shanghai +08:00 GMT+8 周一]");
        assertEquals(dt51.dtDetail(), "2022-08-01 10:05:15.000000000 [Asia/Shanghai +08:00 GMT+8 周一]");
        assertEquals(dt52.dtDetail(), "2022-08-01 10:05:15.987000000 [Asia/Shanghai +08:00 GMT+8 周一]");
        assertEquals(dt53.dtDetail(), "2022-01-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周六]");
        assertEquals(dt54.dtDetail(), "999-01-01 00:00:00.000000000 [Asia/Shanghai +08:05 GMT+8:05:43 周二]");
        assertEquals(dt55.dtDetail(), "1970-01-01 02:05:00.000000000 [Asia/Shanghai +08:00 GMT+8 周四]");
        assertEquals(dt56.dtDetail(), "2022-08-01 10:05:15.980000000 [Asia/Shanghai +08:00 GMT+8 周一]");
        assertEquals(dt57.dtDetail(), "2022-08-10 17:06:50.666000000 [Asia/Shanghai +08:00 GMT+8 周三]");
        assertEquals(dt58.dtDetail(), "2022-08-10 17:06:50.666000000 [Asia/Shanghai +08:00 GMT+8 周三]");
        assertEquals(dt59.dtDetail(), "1970-01-01 02:05:10.098000000 [Asia/Shanghai +08:00 GMT+8 周四]");
        assertEquals(dt60.dtDetail(), "1970-01-01 02:05:10.098000000 [Asia/Shanghai +08:00 GMT+8 周四]");
        assertEquals(dt61.dtDetail(), "2023-10-01 02:05:10.001000000 [Asia/Shanghai +08:00 GMT+8 周日]");
        assertEquals(dt62.dtDetail(), "2023-10-01 02:05:10.100000000 [Asia/Shanghai +08:00 GMT+8 周日]");
        assertEquals(dt63.dtDetail(), "2022-08-10 17:06:50.666000000 [Asia/Shanghai +08:00 GMT+8 周三]");
        assertEquals(dt64.dtDetail(), "1970-01-01 00:05:10.008000000 [Asia/Shanghai +08:00 GMT+8 周四]");
        assertEquals(dt65.dtDetail(), "1970-01-01 00:05:10.098000000 [Asia/Shanghai +08:00 GMT+8 周四]");
        assertEquals(dt66.dtDetail(), "1970-01-01 00:05:10.000000000 [Asia/Shanghai +08:00 GMT+8 周四]");
        assertEquals(dt67.dtDetail(), "999-01-02 06:16:06.600000000 [Z +00:00 GMT 周三]");
        assertEquals(dt68.dtDetail(), "2020-01-02 06:16:06.650000000 [Z +00:00 GMT 周四]");
        assertEquals(dt69.dtDetail(), "2020-01-02 06:16:06.036000000 [Z +00:00 GMT 周四]");
        assertEquals(dt70.dtDetail(), "2023-10-01 02:05:10.001000000 [Asia/Dubai +04:00 GMT+4 周日]");
        assertEquals(dt71.dtDetail(), "2020-01-02 06:16:06.650000000 [Z +00:00 GMT 周四]");
        assertEquals(dt72.dtDetail(), "2025-01-02 06:16:06.036000000 [Z +00:00 GMT 周四]");
        assertEquals(dt73.dtDetail(), "2025-01-02 06:16:06.036000000 [Asia/Dubai +04:00 GMT+4 周四]");
        assertEquals(dt74.dtDetail(), "2025-01-02 06:16:06.036000000 [Asia/Shanghai +08:00 GMT+8 周四]");
        assertEquals(dt75.dtDetail(), "2025-01-02 06:16:06.036000000 [Z +00:00 GMT 周四]");
        assertEquals(dt76.dtDetail(), "2025-01-02 06:16:06.036000000 [Asia/Dubai +04:00 GMT+4 周四]");
        assertEquals(dt77.dtDetail(), "2025-01-02 06:16:06.036000000 [Asia/Shanghai +08:00 GMT+8 周四]");
        assertEquals(dt78.dtDetail(), "2025-01-02 06:16:06.036000000 [Z +00:00 GMT 周四]");
        assertEquals(dt79.dtDetail(), "2025-01-02 06:16:06.036000000 [+04:00 +04:00 GMT+4 周四]");
        assertEquals(dt80.dtDetail(), "2025-01-02 06:16:06.036000000 [+08:00 +08:00 GMT+8 周四]");
        assertEquals(dt81.dtDetail(), "2025-01-02 10:16:06.036000000 [GMT+04:00 +04:00 GMT+4 周四]");
        assertEquals(dt82.dtDetail(), "2025-01-02 06:16:06.036000000 [GMT+04:00 +04:00 GMT+4 周四]");
        assertEquals(dt83.dtDetail(), "2025-01-02 02:16:06.036000000 [GMT+04:00 +04:00 GMT+4 周四]");
        assertEquals(dt84.dtDetail(), "999-01-02 06:06:06.000000000 [GMT+04:00 +04:00 GMT+4 周三]");
        assertEquals(dt85.dtDetail(), "999-01-02 06:11:49.600000000 [Asia/Shanghai +08:05 GMT+8:05:43 周三]");
        assertEquals(dt86.dtDetail(), "2025-01-02 06:16:26.036000000 [Asia/Shanghai +08:00 GMT+8 周四]");
        assertEquals(dt87.dtDetail(), "999-01-02 06:06:06.000000000 [+04:00 +04:00 GMT+4 周三]");
        assertEquals(dt88.dtDetail(), "999-01-02 06:06:06.600000000 [+08:00 +08:00 GMT+8 周三]");
        assertEquals(dt89.dtDetail(), "2025-01-02 06:16:26.036000000 [+08:00 +08:00 GMT+8 周四]");
        assertEquals(dt90.dtDetail(), "2025-01-02 06:16:26.036000000 [+08:00 +08:00 GMT+8 周四]");
        assertEquals(dt91.dtDetail(), "2025-01-02 06:16:26.036000000 [+08:00 +08:00 GMT+8 周四]");
        assertEquals(dt92.dtDetail(), "2025-01-02 06:16:26.036000000 [GMT+04:00 +04:00 GMT+4 周四]");

        assertThrows(DateTimeParseException.class, () -> DateTime.parse("2022081017065066"));
        assertThrows(DateTimeParseException.class, () -> DateTime.parse("20228"));
        assertThrows(DateTimeParseException.class, () -> DateTime.parse("2022810", TZ.NEW_YORK));

    }

    @Test
    public void testFill() {
        DateTime dt1 = DateTime.parse("2022-08-01 10:05:15.987", "yyyy-MM-dd HH:mm:ss.SSS");
        DateTime dt2 = DateTime.parse("2022-08-01 10:05:15.987", "yyyy-MM-dd HH:mm:ss.SSS");
        DateTime dt3 = DateTime.parse("2022-07-10 10:05:15.987", "yyyy-MM-dd HH:mm:ss.SSS");
        DateTime dt4 = DateTime.parse("2022-07-10 10:05:15.987", "yyyy-MM-dd HH:mm:ss.SSS");

        DateTime zonedDT = DateTime.of(DateTime.parse("2022-02-10 10:05:15.987654789", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS").toZonedDT(TZ.DUBAI));
        System.out.println(zonedDT.dtDetail());        // 2022-02-10 10:05:15.987654789 [Asia/Dubai +04:00 GMT+4 周四]

        DateTime dt01 = dt1.fill0(ChronoUnit.MONTHS);        // 2022-08-01 00:00:00.000000000 [周一]
        DateTime dt02 = dt1.fill0(ChronoUnit.DAYS);      // 2022-08-01 00:00:00.000000000 [周一]
        DateTime dt03 = dt1.fill0(ChronoUnit.HOURS);     // 2022-08-01 10:00:00.000000000 [周一]
        DateTime dt04 = dt2.fill0(ChronoUnit.MONTHS);     // 2022-08-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        DateTime dt05 = dt2.fill0(ChronoUnit.DAYS);       // 2022-08-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        DateTime dt06 = dt2.fill0(ChronoUnit.HOURS);      // 2022-08-01 10:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        DateTime dt07 = dt3.fill0(ChronoUnit.MONTHS);        // 2022-07-01 00:00:00.000000000 [周五]
        DateTime dt08 = dt3.fill0(ChronoUnit.DAYS);      // 2022-07-10 00:00:00.000000000 [周日]
        DateTime dt09 = dt3.fill0(ChronoUnit.HOURS);     // 2022-07-10 10:00:00.000000000 [周日]
        DateTime dt10 = dt4.fill0(ChronoUnit.MONTHS);     // 2022-07-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周五]
        DateTime dt11 = dt4.fill0(ChronoUnit.DAYS);       // 2022-07-10 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dt12 = dt4.fill0(ChronoUnit.HOURS);      // 2022-07-10 10:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dt21 = dt1.fill9(ChronoUnit.MONTHS);        // 2022-08-31 23:59:59.999999999 [周三]
        DateTime dt22 = dt1.fill9(ChronoUnit.DAYS);      // 2022-08-01 23:59:59.999999999 [周一]
        DateTime dt23 = dt1.fill9(ChronoUnit.HOURS);     // 2022-08-01 10:59:59.999999999 [周一]
        DateTime dt24 = dt2.fill9(ChronoUnit.MONTHS);     // 2022-08-31 23:59:59.999000000 [Asia/Shanghai +08:00 GMT+8 周三]
        DateTime dt25 = dt2.fill9(ChronoUnit.DAYS);       // 2022-08-01 23:59:59.999000000 [Asia/Shanghai +08:00 GMT+8 周一]
        DateTime dt26 = dt2.fill9(ChronoUnit.HOURS);      // 2022-08-01 10:59:59.999000000 [Asia/Shanghai +08:00 GMT+8 周一]
        DateTime dt27 = dt3.fill9(ChronoUnit.MONTHS);        // 2022-07-31 23:59:59.999999999 [周日]
        DateTime dt28 = dt3.fill9(ChronoUnit.DAYS);      // 2022-07-10 23:59:59.999999999 [周日]
        DateTime dt29 = dt3.fill9(ChronoUnit.HOURS);     // 2022-07-10 10:59:59.999999999 [周日]
        DateTime dt30 = dt4.fill9(ChronoUnit.MONTHS);     // 2022-07-31 23:59:59.999000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dt31 = dt4.fill9(ChronoUnit.DAYS);       // 2022-07-10 23:59:59.999000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dt32 = dt4.fill9(ChronoUnit.HOURS);      // 2022-07-10 10:59:59.999000000 [Asia/Shanghai +08:00 GMT+8 周日]

        DateTime dateTime1 = dt1.fill0(ChronoUnit.YEARS);        // 2022-01-01 00:00:00.000000000 [周六]
        DateTime dateTime2 = dt2.fill0(ChronoUnit.YEARS);             // 2022-01-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周六]
        DateTime dateTime3 = dt3.fill0(ChronoUnit.YEARS);        // 2022-01-01 00:00:00.000000000 [周六]
        DateTime dateTime4 = dt4.fill0(ChronoUnit.YEARS);             // 2022-01-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周六]
        DateTime dateTime5 = dt1.fill9(ChronoUnit.YEARS);        // 2022-12-31 23:59:59.999999999 [周六]
        DateTime dateTime6 = dt2.fill9(ChronoUnit.YEARS);             // 2022-12-31 23:59:59.999000000 [Asia/Shanghai +08:00 GMT+8 周六]
        DateTime dateTime7 = dt3.fill9(ChronoUnit.YEARS);        // 2022-12-31 23:59:59.999999999 [周六]
        DateTime dateTime8 = dt4.fill9(ChronoUnit.YEARS);             // 2022-12-31 23:59:59.999000000 [Asia/Shanghai +08:00 GMT+8 周六]

        DateTime zdt01 = zonedDT.fill0(ChronoUnit.YEARS);    // 2022-01-01 00:00:00.000000000 [Asia/Dubai +04:00 GMT+4 周六]
        DateTime zdt02 = zonedDT.fill0(ChronoUnit.MONTHS);   // 2022-02-01 00:00:00.000000000 [Asia/Dubai +04:00 GMT+4 周二]
        DateTime zdt03 = zonedDT.fill0(ChronoUnit.DAYS);     // 2022-02-10 00:00:00.000000000 [Asia/Dubai +04:00 GMT+4 周四]
        DateTime zdt04 = zonedDT.fill0(ChronoUnit.HOURS);    // 2022-02-10 10:00:00.000000000 [Asia/Dubai +04:00 GMT+4 周四]
        DateTime zdt05 = zonedDT.fill0(ChronoUnit.MINUTES);  // 2022-02-10 10:05:00.000000000 [Asia/Dubai +04:00 GMT+4 周四]
        DateTime zdt06 = zonedDT.fill9(ChronoUnit.YEARS);    // 2022-12-31 23:59:59.999999999 [Asia/Dubai +04:00 GMT+4 周六]
        DateTime zdt07 = zonedDT.fill9(ChronoUnit.MONTHS);   // 2022-02-28 23:59:59.999999999 [Asia/Dubai +04:00 GMT+4 周一]
        DateTime zdt08 = zonedDT.fill9(ChronoUnit.DAYS);     // 2022-02-10 23:59:59.999999999 [Asia/Dubai +04:00 GMT+4 周四]
        DateTime zdt09 = zonedDT.fill9(ChronoUnit.HOURS);    // 2022-02-10 10:59:59.999999999 [Asia/Dubai +04:00 GMT+4 周四]
        DateTime zdt10 = zonedDT.fill9(ChronoUnit.MINUTES);  // 2022-02-10 10:05:59.999999999 [Asia/Dubai +04:00 GMT+4 周四]

        System.out.println(dt01.dtDetail());
        System.out.println(dt02.dtDetail());
        System.out.println(dt03.dtDetail());
        System.out.println(dt04.dtDetail());
        System.out.println(dt05.dtDetail());
        System.out.println(dt06.dtDetail());
        System.out.println(dt07.dtDetail());
        System.out.println(dt08.dtDetail());
        System.out.println(dt09.dtDetail());
        System.out.println(dt10.dtDetail());
        System.out.println(dt11.dtDetail());
        System.out.println(dt12.dtDetail());
        System.out.println(dt21.dtDetail());
        System.out.println(dt22.dtDetail());
        System.out.println(dt23.dtDetail());
        System.out.println(dt24.dtDetail());
        System.out.println(dt25.dtDetail());
        System.out.println(dt26.dtDetail());
        System.out.println(dt27.dtDetail());
        System.out.println(dt28.dtDetail());
        System.out.println(dt29.dtDetail());
        System.out.println(dt30.dtDetail());
        System.out.println(dt31.dtDetail());
        System.out.println(dt32.dtDetail());

        System.out.println(dateTime1.dtDetail());
        System.out.println(dateTime2.dtDetail());
        System.out.println(dateTime3.dtDetail());
        System.out.println(dateTime4.dtDetail());
        System.out.println(dateTime5.dtDetail());
        System.out.println(dateTime6.dtDetail());
        System.out.println(dateTime7.dtDetail());
        System.out.println(dateTime8.dtDetail());

        System.out.println(zdt01.dtDetail());
        System.out.println(zdt02.dtDetail());
        System.out.println(zdt03.dtDetail());
        System.out.println(zdt04.dtDetail());
        System.out.println(zdt05.dtDetail());
        System.out.println(zdt06.dtDetail());
        System.out.println(zdt07.dtDetail());
        System.out.println(zdt08.dtDetail());
        System.out.println(zdt09.dtDetail());
        System.out.println(zdt10.dtDetail());

        assertEquals(dt01.dtDetail(), "2022-08-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周一]");
        assertEquals(dt02.dtDetail(), "2022-08-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周一]");
        assertEquals(dt03.dtDetail(), "2022-08-01 10:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周一]");
        assertEquals(dt04.dtDetail(), "2022-08-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周一]");
        assertEquals(dt05.dtDetail(), "2022-08-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周一]");
        assertEquals(dt06.dtDetail(), "2022-08-01 10:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周一]");
        assertEquals(dt07.dtDetail(), "2022-07-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周五]");
        assertEquals(dt08.dtDetail(), "2022-07-10 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周日]");
        assertEquals(dt09.dtDetail(), "2022-07-10 10:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周日]");
        assertEquals(dt10.dtDetail(), "2022-07-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周五]");
        assertEquals(dt11.dtDetail(), "2022-07-10 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周日]");
        assertEquals(dt12.dtDetail(), "2022-07-10 10:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周日]");
        assertEquals(dt21.dtDetail(), "2022-08-31 23:59:59.999999999 [Asia/Shanghai +08:00 GMT+8 周三]");
        assertEquals(dt22.dtDetail(), "2022-08-01 23:59:59.999999999 [Asia/Shanghai +08:00 GMT+8 周一]");
        assertEquals(dt23.dtDetail(), "2022-08-01 10:59:59.999999999 [Asia/Shanghai +08:00 GMT+8 周一]");
        assertEquals(dt24.dtDetail(), "2022-08-31 23:59:59.999999999 [Asia/Shanghai +08:00 GMT+8 周三]");
        assertEquals(dt25.dtDetail(), "2022-08-01 23:59:59.999999999 [Asia/Shanghai +08:00 GMT+8 周一]");
        assertEquals(dt26.dtDetail(), "2022-08-01 10:59:59.999999999 [Asia/Shanghai +08:00 GMT+8 周一]");
        assertEquals(dt27.dtDetail(), "2022-07-31 23:59:59.999999999 [Asia/Shanghai +08:00 GMT+8 周日]");
        assertEquals(dt28.dtDetail(), "2022-07-10 23:59:59.999999999 [Asia/Shanghai +08:00 GMT+8 周日]");
        assertEquals(dt29.dtDetail(), "2022-07-10 10:59:59.999999999 [Asia/Shanghai +08:00 GMT+8 周日]");
        assertEquals(dt30.dtDetail(), "2022-07-31 23:59:59.999999999 [Asia/Shanghai +08:00 GMT+8 周日]");
        assertEquals(dt31.dtDetail(), "2022-07-10 23:59:59.999999999 [Asia/Shanghai +08:00 GMT+8 周日]");
        assertEquals(dt32.dtDetail(), "2022-07-10 10:59:59.999999999 [Asia/Shanghai +08:00 GMT+8 周日]");

        assertEquals(dateTime1.dtDetail(), "2022-01-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周六]");
        assertEquals(dateTime2.dtDetail(), "2022-01-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周六]");
        assertEquals(dateTime3.dtDetail(), "2022-01-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周六]");
        assertEquals(dateTime4.dtDetail(), "2022-01-01 00:00:00.000000000 [Asia/Shanghai +08:00 GMT+8 周六]");
        assertEquals(dateTime5.dtDetail(), "2022-12-31 23:59:59.999999999 [Asia/Shanghai +08:00 GMT+8 周六]");
        assertEquals(dateTime6.dtDetail(), "2022-12-31 23:59:59.999999999 [Asia/Shanghai +08:00 GMT+8 周六]");
        assertEquals(dateTime7.dtDetail(), "2022-12-31 23:59:59.999999999 [Asia/Shanghai +08:00 GMT+8 周六]");
        assertEquals(dateTime8.dtDetail(), "2022-12-31 23:59:59.999999999 [Asia/Shanghai +08:00 GMT+8 周六]");

        assertEquals(zdt01.dtDetail(), "2022-01-01 00:00:00.000000000 [Asia/Dubai +04:00 GMT+4 周六]");
        assertEquals(zdt02.dtDetail(), "2022-02-01 00:00:00.000000000 [Asia/Dubai +04:00 GMT+4 周二]");
        assertEquals(zdt03.dtDetail(), "2022-02-10 00:00:00.000000000 [Asia/Dubai +04:00 GMT+4 周四]");
        assertEquals(zdt04.dtDetail(), "2022-02-10 06:00:00.000000000 [Asia/Dubai +04:00 GMT+4 周四]");
        assertEquals(zdt05.dtDetail(), "2022-02-10 06:05:00.000000000 [Asia/Dubai +04:00 GMT+4 周四]");
        assertEquals(zdt06.dtDetail(), "2022-12-31 23:59:59.999999999 [Asia/Dubai +04:00 GMT+4 周六]");
        assertEquals(zdt07.dtDetail(), "2022-02-28 23:59:59.999999999 [Asia/Dubai +04:00 GMT+4 周一]");
        assertEquals(zdt08.dtDetail(), "2022-02-10 23:59:59.999999999 [Asia/Dubai +04:00 GMT+4 周四]");
        assertEquals(zdt09.dtDetail(), "2022-02-10 06:59:59.999999999 [Asia/Dubai +04:00 GMT+4 周四]");
        assertEquals(zdt10.dtDetail(), "2022-02-10 06:05:59.999999999 [Asia/Dubai +04:00 GMT+4 周四]");

    }

    @Test
    public void testIn() {
        DateTime ldt1 = DateTime.parse("2022-8-06 9:10:21.689");
        DateTime ldt2 = DateTime.parse("2022-8-06 11:10:21.689");
        DateTime ldt3 = DateTime.parse("2022-8-06 10:10:21.689");
        DateTime date1 = DateTime.parse("2022-8-06 9:10:21.689");
        DateTime date2 = DateTime.parse("2022-8-06 11:10:21.689");
        DateTime date3 = DateTime.parse("2022-8-06 10:10:21.689");

        System.out.println("ldt1: " + ldt1);
        System.out.println("ldt2: " + ldt2);
        System.out.println("ldt3: " + ldt3);
        System.out.println("date1: " + date1);
        System.out.println("date2: " + date2);
        System.out.println("date3: " + date3);
        System.out.println("============================================================");

        boolean in01 = ldt1.in(date1, ldt2, CLOSED);            // true
        boolean in02 = ldt1.in(date1, ldt2, CLOSED_OPEN);       // true
        boolean in03 = ldt1.in(date1, ldt2, OPEN);              // false
        boolean in04 = ldt1.in(date1, ldt2, OPEN_CLOSED);       // false
        boolean in05 = date2.in(ldt3, ldt2, CLOSED);            // true
        boolean in06 = date2.in(ldt3, ldt2, CLOSED_OPEN);       // false
        boolean in07 = date2.in(ldt3, ldt2, OPEN);              // false
        boolean in08 = date2.in(ldt3, ldt2, OPEN_CLOSED);       // true
        boolean in09 = date3.in(ldt1, ldt2, CLOSED);            // true
        boolean in10 = date3.in(ldt1, ldt2, CLOSED_OPEN);       // true
        boolean in11 = date3.in(ldt1, ldt2, OPEN);              // true
        boolean in12 = date3.in(ldt1, ldt2, OPEN_CLOSED);       // true

        assertTrue(in01);
        assertTrue(in02);
        assertFalse(in03);
        assertFalse(in04);
        assertTrue(in05);
        assertFalse(in06);
        assertFalse(in07);
        assertTrue(in08);
        assertTrue(in09);
        assertTrue(in10);
        assertTrue(in11);
        assertTrue(in12);

        boolean in13 = ldt1.in(date2, ldt2, CLOSED_OPEN);   // 空集
        boolean in14 = ldt1.in(date2, ldt3, CLOSED_OPEN);
        boolean in15 = ldt1.in(date1, ldt1, CLOSED_OPEN);   // 空集
        System.out.println(date1.equals(ldt1));
        System.out.println(in13);
        System.out.println(in14);
        System.out.println(in15);

        assertFalse(in13);
        assertFalse(in14);
        assertFalse(in15);

        Range<DateTime> range = Range.closedOpen(date1, ldt1);
        System.out.println(range);          // [2022-08-06 09:10:21.689, 2022-08-06 09:10:21.689)
        System.out.println(range.toSimpleString()); // ∅

    }

    @Test
    public void testInThisWeek() {
        DateTime dt1 = DateTime.parse("2021-12-27 06:01:50", "yyyy-MM-dd HH:mm:ss");  // 星期一
        DateTime dt2 = DateTime.parse("2021-12-29 06:01:50", "yyyy-MM-dd HH:mm:ss");  // 星期三
        DateTime dt3 = DateTime.parse("2022-01-01 06:01:50", "yyyy-MM-dd HH:mm:ss");  // 星期六
        DateTime dt4 = DateTime.parse("2022-01-02 06:01:50", "yyyy-MM-dd HH:mm:ss");  // 星期日

        DateTime dt01 = dt1.dtInThisWeek(DayOfWeek.TUESDAY);              // 2021-12-28 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周二]
        DateTime dt02 = dt1.dtInThisWeek(DayOfWeek.THURSDAY);             // 2021-12-30 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周四]
        DateTime dt03 = dt1.dtInThisWeek(DayOfWeek.MONDAY);               // 2021-12-27 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        DateTime dt04 = dt1.dtInThisWeek(DayOfWeek.SUNDAY);               // 2022-01-02 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dt05 = dt1.firstInThisWeek();                // 2021-12-27 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        DateTime dt06 = dt1.lastInThisWeek();             // 2022-01-02 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dt11 = dt2.dtInThisWeek(DayOfWeek.TUESDAY);              // 2021-12-28 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周二]
        DateTime dt12 = dt2.dtInThisWeek(DayOfWeek.THURSDAY);             // 2021-12-30 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周四]
        DateTime dt13 = dt2.dtInThisWeek(DayOfWeek.MONDAY);               // 2021-12-27 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        DateTime dt14 = dt2.dtInThisWeek(DayOfWeek.SUNDAY);               // 2022-01-02 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dt15 = dt2.firstInThisWeek();                // 2021-12-27 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        DateTime dt16 = dt2.lastInThisWeek();             // 2022-01-02 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dt21 = dt3.dtInThisWeek(DayOfWeek.TUESDAY);              // 2021-12-28 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周二]
        DateTime dt22 = dt3.dtInThisWeek(DayOfWeek.THURSDAY);             // 2021-12-30 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周四]
        DateTime dt23 = dt3.dtInThisWeek(DayOfWeek.MONDAY);               // 2021-12-27 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        DateTime dt24 = dt3.dtInThisWeek(DayOfWeek.SUNDAY);               // 2022-01-02 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dt25 = dt3.firstInThisWeek();                // 2021-12-27 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        DateTime dt26 = dt3.lastInThisWeek();             // 2022-01-02 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dt31 = dt4.dtInThisWeek(DayOfWeek.TUESDAY);              // 2021-12-28 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周二]
        DateTime dt32 = dt4.dtInThisWeek(DayOfWeek.THURSDAY);             // 2021-12-30 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周四]
        DateTime dt33 = dt4.dtInThisWeek(DayOfWeek.MONDAY);               // 2021-12-27 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        DateTime dt34 = dt4.dtInThisWeek(DayOfWeek.SUNDAY);               // 2022-01-02 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dt35 = dt4.firstInThisWeek();                // 2021-12-27 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        DateTime dt36 = dt4.lastInThisWeek();             // 2022-01-02 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dt001 = dt1.dtInThisWeek(DayOfWeek.SUNDAY, DayOfWeek.TUESDAY);               // 2021-12-28 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周二]
        DateTime dt002 = dt1.dtInThisWeek(DayOfWeek.SUNDAY, DayOfWeek.THURSDAY);              // 2021-12-30 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周四]
        DateTime dt003 = dt1.dtInThisWeek(DayOfWeek.SUNDAY, DayOfWeek.MONDAY);                // 2021-12-27 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        DateTime dt004 = dt1.dtInThisWeek(DayOfWeek.SUNDAY, DayOfWeek.SUNDAY);                // 2021-12-26 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dt005 = dt1.firstInThisWeek(DayOfWeek.SUNDAY);               // 2021-12-26 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dt006 = dt1.lastInThisWeek(DayOfWeek.SUNDAY);                // 2022-01-01 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周六]
        DateTime dt011 = dt2.dtInThisWeek(DayOfWeek.SUNDAY, DayOfWeek.TUESDAY);               // 2021-12-28 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周二]
        DateTime dt012 = dt2.dtInThisWeek(DayOfWeek.SUNDAY, DayOfWeek.THURSDAY);              // 2021-12-30 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周四]
        DateTime dt013 = dt2.dtInThisWeek(DayOfWeek.SUNDAY, DayOfWeek.MONDAY);                // 2021-12-27 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        DateTime dt014 = dt2.dtInThisWeek(DayOfWeek.SUNDAY, DayOfWeek.SUNDAY);                // 2021-12-26 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dt015 = dt2.firstInThisWeek(DayOfWeek.SUNDAY);               // 2021-12-26 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dt016 = dt2.lastInThisWeek(DayOfWeek.SUNDAY);                // 2022-01-01 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周六]
        DateTime dt021 = dt3.dtInThisWeek(DayOfWeek.SUNDAY, DayOfWeek.TUESDAY);               // 2021-12-28 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周二]
        DateTime dt022 = dt3.dtInThisWeek(DayOfWeek.SUNDAY, DayOfWeek.THURSDAY);              // 2021-12-30 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周四]
        DateTime dt023 = dt3.dtInThisWeek(DayOfWeek.SUNDAY, DayOfWeek.MONDAY);                // 2021-12-27 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        DateTime dt024 = dt3.dtInThisWeek(DayOfWeek.SUNDAY, DayOfWeek.SUNDAY);                // 2021-12-26 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dt025 = dt3.firstInThisWeek(DayOfWeek.SUNDAY);               // 2021-12-26 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dt026 = dt3.lastInThisWeek(DayOfWeek.SUNDAY);                // 2022-01-01 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周六]
        DateTime dt031 = dt4.dtInThisWeek(DayOfWeek.SUNDAY, DayOfWeek.TUESDAY);               // 2022-01-04 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周二]
        DateTime dt032 = dt4.dtInThisWeek(DayOfWeek.SUNDAY, DayOfWeek.THURSDAY);              // 2022-01-06 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周四]
        DateTime dt033 = dt4.dtInThisWeek(DayOfWeek.SUNDAY, DayOfWeek.MONDAY);                // 2022-01-03 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周一]
        DateTime dt034 = dt4.dtInThisWeek(DayOfWeek.SUNDAY, DayOfWeek.SUNDAY);                // 2022-01-02 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dt035 = dt4.firstInThisWeek(DayOfWeek.SUNDAY);               // 2022-01-02 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime dt036 = dt4.lastInThisWeek(DayOfWeek.SUNDAY);                // 2022-01-08 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周六]

        assertEquals("2021-12-28 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周二]", dt01.dtDetail());
        assertEquals("2021-12-30 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周四]", dt02.dtDetail());
        assertEquals("2021-12-27 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周一]", dt03.dtDetail());
        assertEquals("2022-01-02 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dt04.dtDetail());
        assertEquals("2021-12-27 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周一]", dt05.dtDetail());
        assertEquals("2022-01-02 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dt06.dtDetail());
        assertEquals("2021-12-28 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周二]", dt11.dtDetail());
        assertEquals("2021-12-30 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周四]", dt12.dtDetail());
        assertEquals("2021-12-27 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周一]", dt13.dtDetail());
        assertEquals("2022-01-02 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dt14.dtDetail());
        assertEquals("2021-12-27 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周一]", dt15.dtDetail());
        assertEquals("2022-01-02 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dt16.dtDetail());
        assertEquals("2021-12-28 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周二]", dt21.dtDetail());
        assertEquals("2021-12-30 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周四]", dt22.dtDetail());
        assertEquals("2021-12-27 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周一]", dt23.dtDetail());
        assertEquals("2022-01-02 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dt24.dtDetail());
        assertEquals("2021-12-27 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周一]", dt25.dtDetail());
        assertEquals("2022-01-02 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dt26.dtDetail());
        assertEquals("2021-12-28 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周二]", dt31.dtDetail());
        assertEquals("2021-12-30 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周四]", dt32.dtDetail());
        assertEquals("2021-12-27 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周一]", dt33.dtDetail());
        assertEquals("2022-01-02 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dt34.dtDetail());
        assertEquals("2021-12-27 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周一]", dt35.dtDetail());
        assertEquals("2022-01-02 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dt36.dtDetail());
        assertEquals("2021-12-28 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周二]", dt001.dtDetail());
        assertEquals("2021-12-30 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周四]", dt002.dtDetail());
        assertEquals("2021-12-27 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周一]", dt003.dtDetail());
        assertEquals("2021-12-26 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dt004.dtDetail());
        assertEquals("2021-12-26 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dt005.dtDetail());
        assertEquals("2022-01-01 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周六]", dt006.dtDetail());
        assertEquals("2021-12-28 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周二]", dt011.dtDetail());
        assertEquals("2021-12-30 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周四]", dt012.dtDetail());
        assertEquals("2021-12-27 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周一]", dt013.dtDetail());
        assertEquals("2021-12-26 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dt014.dtDetail());
        assertEquals("2021-12-26 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dt015.dtDetail());
        assertEquals("2022-01-01 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周六]", dt016.dtDetail());
        assertEquals("2021-12-28 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周二]", dt021.dtDetail());
        assertEquals("2021-12-30 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周四]", dt022.dtDetail());
        assertEquals("2021-12-27 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周一]", dt023.dtDetail());
        assertEquals("2021-12-26 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dt024.dtDetail());
        assertEquals("2021-12-26 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dt025.dtDetail());
        assertEquals("2022-01-01 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周六]", dt026.dtDetail());
        assertEquals("2022-01-04 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周二]", dt031.dtDetail());
        assertEquals("2022-01-06 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周四]", dt032.dtDetail());
        assertEquals("2022-01-03 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周一]", dt033.dtDetail());
        assertEquals("2022-01-02 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dt034.dtDetail());
        assertEquals("2022-01-02 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周日]", dt035.dtDetail());
        assertEquals("2022-01-08 06:01:50.000000000 [Asia/Shanghai +08:00 GMT+8 周六]", dt036.dtDetail());

        List<DateTime> dateTimes1 = dt1.allDaysInThisWeek();
        List<DateTime> dateTimes2 = dt2.allDaysInThisWeek();
        List<DateTime> dateTimes3 = dt3.allDaysInThisWeek();
        List<DateTime> dateTimes4 = dt4.allDaysInThisWeek();
        List<DateTime> dateTimes5 = dt1.allDaysInThisWeek(DayOfWeek.SUNDAY);
        List<DateTime> dateTimes6 = dt2.allDaysInThisWeek(DayOfWeek.SUNDAY);
        List<DateTime> dateTimes7 = dt3.allDaysInThisWeek(DayOfWeek.SUNDAY);
        List<DateTime> dateTimes8 = dt4.allDaysInThisWeek(DayOfWeek.SUNDAY);

        assertEquals("[2021-12-27 06:01:50.000, 2021-12-28 06:01:50.000, 2021-12-29 06:01:50.000, 2021-12-30 06:01:50.000, " +
                "2021-12-31 06:01:50.000, 2022-01-01 06:01:50.000, 2022-01-02 06:01:50.000]", dateTimes1.toString());
        assertEquals("[2021-12-27 06:01:50.000, 2021-12-28 06:01:50.000, 2021-12-29 06:01:50.000, 2021-12-30 06:01:50.000, " +
                "2021-12-31 06:01:50.000, 2022-01-01 06:01:50.000, 2022-01-02 06:01:50.000]", dateTimes2.toString());
        assertEquals("[2021-12-27 06:01:50.000, 2021-12-28 06:01:50.000, 2021-12-29 06:01:50.000, 2021-12-30 06:01:50.000, " +
                "2021-12-31 06:01:50.000, 2022-01-01 06:01:50.000, 2022-01-02 06:01:50.000]", dateTimes3.toString());
        assertEquals("[2021-12-27 06:01:50.000, 2021-12-28 06:01:50.000, 2021-12-29 06:01:50.000, 2021-12-30 06:01:50.000, " +
                "2021-12-31 06:01:50.000, 2022-01-01 06:01:50.000, 2022-01-02 06:01:50.000]", dateTimes4.toString());
        assertEquals("[2021-12-26 06:01:50.000, 2021-12-27 06:01:50.000, 2021-12-28 06:01:50.000, 2021-12-29 06:01:50.000, " +
                "2021-12-30 06:01:50.000, 2021-12-31 06:01:50.000, 2022-01-01 06:01:50.000]", dateTimes5.toString());
        assertEquals("[2021-12-26 06:01:50.000, 2021-12-27 06:01:50.000, 2021-12-28 06:01:50.000, 2021-12-29 06:01:50.000, " +
                "2021-12-30 06:01:50.000, 2021-12-31 06:01:50.000, 2022-01-01 06:01:50.000]", dateTimes6.toString());
        assertEquals("[2021-12-26 06:01:50.000, 2021-12-27 06:01:50.000, 2021-12-28 06:01:50.000, 2021-12-29 06:01:50.000, " +
                "2021-12-30 06:01:50.000, 2021-12-31 06:01:50.000, 2022-01-01 06:01:50.000]", dateTimes7.toString());
        assertEquals("[2022-01-02 06:01:50.000, 2022-01-03 06:01:50.000, 2022-01-04 06:01:50.000, 2022-01-05 06:01:50.000, " +
                "2022-01-06 06:01:50.000, 2022-01-07 06:01:50.000, 2022-01-08 06:01:50.000]", dateTimes8.toString());


    }

    @Test
    public void testNow() {
        DateTime now = DateTime.now();
        DateTime nowWithZone = DateTime.nowWith(TZ.MOSCOW);
        DateTime nowWithOffset = DateTime.nowWith(ZoneOffset.ofHours(-2));
        System.out.println("DateTime.now(): " + now);
        System.out.println("DateTime.nowWithZone(): " + nowWithZone);
        System.out.println("DateTime.nowWithOffset(): " + nowWithOffset);
    }


    @Test
    public void testExcludeClass() {
        long timeMillis = DateTime.parse("2023-03-05 12:53:10.098").toInstant().toEpochMilli();
        java.sql.Date date = new java.sql.Date(timeMillis);
        Time time = new Time(timeMillis);
        Timestamp timestamp = new Timestamp(timeMillis);

        DateTime timestampDT = DateTime.of(timestamp);
        DateTime timestampDT01 = timestampDT.plusDays(1);
        DateTime timestampDT02 = timestampDT.minusYears(1);
        DateTime timestampDT03 = timestampDT.minusDays(-10);
        DateTime timestampDTWithMax = timestampDT.withMax(ChronoUnit.HOURS);
        DateTime timestampDTFill0 = timestampDT.fill0(ChronoUnit.DAYS);
        System.out.println("timestampDT: " + timestampDT);
        System.out.println("timestampDT01: " + timestampDT01);
        System.out.println("timestampDT02: " + timestampDT02);
        System.out.println("timestampDT03: " + timestampDT03);
        System.out.println("timestampDTWithMax: " + timestampDTWithMax);
        System.out.println("timestampDTFill0: " + timestampDTFill0);

        assertEquals("2023-03-05 12:53:10.098", timestampDT.toString());
        assertEquals("2023-03-06 12:53:10.098", timestampDT01.toString());
        assertEquals("2022-03-05 12:53:10.098", timestampDT02.toString());
        assertEquals("2023-03-15 12:53:10.098", timestampDT03.toString());
        assertEquals("2023-03-05 12:59:59.999", timestampDTWithMax.toString());
        assertEquals("2023-03-05 00:00:00.000", timestampDTFill0.toString());

        assertThrows(UnsupportedTemporalTypeException.class, () -> DateTime.of(date));
        assertThrows(UnsupportedTemporalTypeException.class, () -> DateTime.of(time));

    }

    @Test
    public void testUntil() {
        DateTime fromDT = DateTime.parse("2023-06-30");
        DateTime toDT = DateTime.of(LocalDate.of(2023, 6, 1));
        Instant toInstant = toDT.getInstant();
        ZonedDateTime toZDT = toDT.toUTCZonedDT();

        System.out.println(fromDT.dtDetail());
        System.out.println(toDT.dtDetail());
        System.out.println(toInstant);
        System.out.println(toZDT);

        try {
            DateTime.of((Temporal) LocalDate.of(2023, 6, 1));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertEquals(e.getMessage(), "The `dateTime` is of type `LocalDate`, please call the `DateTime.of(LocalDate)` function! ");
        }

        assertEquals(-4, fromDT.until(toDT, ChronoUnit.WEEKS));
        assertEquals(-4, fromDT.until(toInstant, ChronoUnit.WEEKS));
        assertEquals(-4, fromDT.until(toZDT, ChronoUnit.WEEKS));
        assertEquals(-29, fromDT.until(toDT, ChronoUnit.DAYS));
        assertEquals(-29, fromDT.until(toInstant, ChronoUnit.DAYS));
        assertEquals(-29, fromDT.until(toZDT, ChronoUnit.DAYS));
    }


    @Test
    public void testWithLocalDateAndTime() {
        LocalDateTime ldt = LocalDateTime.of(2022, 2, 27, 8, 0, 10, 100);
        ZonedDateTime zdt = ldt.atZone(TZ.DEFAULT_ZONE);

        ZonedDateTime zdt1 = zdt.withZoneSameInstant(TZ.MOSCOW);
        Instant instant = zdt1.toInstant();
        OffsetDateTime odt = zdt1.toOffsetDateTime().withOffsetSameInstant(ZoneOffset.ofHours(-10));
        GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance(TimeZone.getTimeZone(TZ.NEW_YORK));
        calendar.setTime(Date.from(zdt.toInstant()));
        Date date = calendar.getTime();

        DateTime dt1 = DateTime.of(ldt);
        DateTime dt2 = DateTime.of(zdt);
        DateTime dt3 = DateTime.of(zdt1);
        DateTime dt4 = DateTime.of(instant);
        DateTime dt5 = DateTime.of(odt);
        DateTime dt6 = DateTime.of(calendar);
        DateTime dt7 = DateTime.of(date);

        System.out.println(dt1.dtDetail());     // 2022-02-27 08:00:10.000000100 [周日]
        System.out.println(dt2.dtDetail());     // 2022-02-27 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周日]
        System.out.println(dt3.dtDetail());     // 2022-02-27 03:00:10.000000100 [Europe/Moscow +03:00 GMT+3 周日]
        System.out.println(dt4.dtDetail());     // 2022-02-27 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周日]
        System.out.println(dt5.dtDetail());     // 2022-02-26 14:00:10.000000100 [-10:00 GMT-10 周六]
        System.out.println(dt6.dtDetail());     // 2022-02-26 19:00:10.000000000 [America/New_York -05:00 GMT-5 周六]
        System.out.println(dt7.dtDetail());     // 2022-02-27 08:00:10.000000000 [Asia/Shanghai +08:00 GMT+8 周日]
        System.out.println("=======================================");

        LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 1, 11, 3, 10, 3654789);
        LocalDate localDate = localDateTime.toLocalDate();
        LocalTime localTime = localDateTime.toLocalTime();

        DateTime newDt01 = dt1.withLocalDateTime(localDateTime);     // 2023-11-01 11:03:10.003654789 [周三]
        DateTime newDt02 = dt2.withLocalDateTime(localDateTime);     // 2023-11-01 11:03:10.003654789 [Asia/Shanghai +08:00 GMT+8 周三]
        DateTime newDt03 = dt3.withLocalDateTime(localDateTime);     // 2023-11-01 11:03:10.003654789 [Europe/Moscow +03:00 GMT+3 周三]
        DateTime newDt04 = dt4.withLocalDateTime(localDateTime);           // 2023-11-01 11:03:10.003654789 [Asia/Shanghai +08:00 GMT+8 周三]
        DateTime newDt05 = dt5.withLocalDateTime(localDateTime);    // 2023-11-01 11:03:10.003654789 [-10:00 GMT-10 周三]
        DateTime newDt06 = dt6.withLocalDateTime(localDateTime);          // 2023-11-01 11:03:10.003000000 [America/New_York -04:00 GMT-4 周三]
        DateTime newDt07 = dt7.withLocalDateTime(localDateTime);              // 2023-11-01 11:03:10.003000000 [Asia/Shanghai +08:00 GMT+8 周三]
        DateTime newDt11 = dt1.withLocalDate(localDate);             // 2023-11-01 08:00:10.000000100 [周三]
        DateTime newDt12 = dt2.withLocalDate(localDate);             // 2023-11-01 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周三]
        DateTime newDt13 = dt3.withLocalDate(localDate);             // 2023-11-01 03:00:10.000000100 [Europe/Moscow +03:00 GMT+3 周三]
        DateTime newDt14 = dt4.withLocalDate(localDate);                   // 2023-11-01 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周三]
        DateTime newDt15 = dt5.withLocalDate(localDate);            // 2023-11-01 14:00:10.000000100 [-10:00 GMT-10 周三]
        DateTime newDt16 = dt6.withLocalDate(localDate);                  // 2023-11-01 19:00:10.000000000 [America/New_York -04:00 GMT-4 周三]
        DateTime newDt17 = dt7.withLocalDate(localDate);                      // 2023-11-01 08:00:10.000000000 [Asia/Shanghai +08:00 GMT+8 周三]
        DateTime newDt21 = dt1.withLocalTime(localTime);             // 2022-02-27 11:03:10.003654789 [周日]
        DateTime newDt22 = dt2.withLocalTime(localTime);             // 2022-02-27 11:03:10.003654789 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime newDt23 = dt3.withLocalTime(localTime);             // 2022-02-27 11:03:10.003654789 [Europe/Moscow +03:00 GMT+3 周日]
        DateTime newDt24 = dt4.withLocalTime(localTime);                   // 2022-02-27 11:03:10.003654789 [Asia/Shanghai +08:00 GMT+8 周日]
        DateTime newDt25 = dt5.withLocalTime(localTime);            // 2022-02-26 11:03:10.003654789 [-10:00 GMT-10 周六]
        DateTime newDt26 = dt6.withLocalTime(localTime);                  // 2022-02-26 11:03:10.003000000 [America/New_York -05:00 GMT-5 周六]
        DateTime newDt27 = dt7.withLocalTime(localTime);                      // 2022-02-27 11:03:10.003000000 [Asia/Shanghai +08:00 GMT+8 周日]

        System.out.println(newDt01.dtDetail());
        System.out.println(newDt02.dtDetail());
        System.out.println(newDt03.dtDetail());
        System.out.println(newDt04.dtDetail());
        System.out.println(newDt05.dtDetail());
        System.out.println(newDt06.dtDetail());
        System.out.println(newDt07.dtDetail());
        System.out.println("============================================================");
        System.out.println(newDt11.dtDetail());
        System.out.println(newDt12.dtDetail());
        System.out.println(newDt13.dtDetail());
        System.out.println(newDt14.dtDetail());
        System.out.println(newDt15.dtDetail());
        System.out.println(newDt16.dtDetail());
        System.out.println(newDt17.dtDetail());
        System.out.println("============================================================");
        System.out.println(newDt21.dtDetail());
        System.out.println(newDt22.dtDetail());
        System.out.println(newDt23.dtDetail());
        System.out.println(newDt24.dtDetail());
        System.out.println(newDt25.dtDetail());
        System.out.println(newDt26.dtDetail());
        System.out.println(newDt27.dtDetail());

        assertEquals(newDt01.dtDetail(), "2023-11-01 11:03:10.003654789 [Asia/Shanghai +08:00 GMT+8 周三]");
        assertEquals(newDt02.dtDetail(), "2023-11-01 11:03:10.003654789 [Asia/Shanghai +08:00 GMT+8 周三]");
        assertEquals(newDt03.dtDetail(), "2023-11-01 11:03:10.003654789 [Europe/Moscow +03:00 GMT+3 周三]");
        assertEquals(newDt04.dtDetail(), "2023-11-01 11:03:10.003654789 [Asia/Shanghai +08:00 GMT+8 周三]");
        assertEquals(newDt05.dtDetail(), "2023-11-01 11:03:10.003654789 [-10:00 -10:00 GMT-10 周三]");
        assertEquals(newDt06.dtDetail(), "2023-11-01 11:03:10.003654789 [America/New_York -04:00 GMT-4 周三]");
        assertEquals(newDt07.dtDetail(), "2023-11-01 11:03:10.003654789 [Asia/Shanghai +08:00 GMT+8 周三]");
        assertEquals(newDt11.dtDetail(), "2023-11-01 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周三]");
        assertEquals(newDt12.dtDetail(), "2023-11-01 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周三]");
        assertEquals(newDt13.dtDetail(), "2023-11-01 03:00:10.000000100 [Europe/Moscow +03:00 GMT+3 周三]");
        assertEquals(newDt14.dtDetail(), "2023-11-01 08:00:10.000000100 [Asia/Shanghai +08:00 GMT+8 周三]");
        assertEquals(newDt15.dtDetail(), "2023-11-01 14:00:10.000000100 [-10:00 -10:00 GMT-10 周三]");
        assertEquals(newDt16.dtDetail(), "2023-11-01 19:00:10.000000000 [America/New_York -04:00 GMT-4 周三]");
        assertEquals(newDt17.dtDetail(), "2023-11-01 08:00:10.000000000 [Asia/Shanghai +08:00 GMT+8 周三]");
        assertEquals(newDt21.dtDetail(), "2022-02-27 11:03:10.003654789 [Asia/Shanghai +08:00 GMT+8 周日]");
        assertEquals(newDt22.dtDetail(), "2022-02-27 11:03:10.003654789 [Asia/Shanghai +08:00 GMT+8 周日]");
        assertEquals(newDt23.dtDetail(), "2022-02-27 11:03:10.003654789 [Europe/Moscow +03:00 GMT+3 周日]");
        assertEquals(newDt24.dtDetail(), "2022-02-27 11:03:10.003654789 [Asia/Shanghai +08:00 GMT+8 周日]");
        assertEquals(newDt25.dtDetail(), "2022-02-26 11:03:10.003654789 [-10:00 -10:00 GMT-10 周六]");
        assertEquals(newDt26.dtDetail(), "2022-02-26 11:03:10.003654789 [America/New_York -05:00 GMT-5 周六]");
        assertEquals(newDt27.dtDetail(), "2022-02-27 11:03:10.003654789 [Asia/Shanghai +08:00 GMT+8 周日]");

    }


    @Test
    public void testNameOfDayOfWeek() {
        DateTime dt1 = DateTime.parse("2021-12-27 06:01:50", "yyyy-MM-dd HH:mm:ss");  // 星期一
        DateTime dt2 = DateTime.parse("2021-12-29 06:01:50", "yyyy-MM-dd HH:mm:ss");  // 星期三
        DateTime dt3 = DateTime.parse("2022-01-01 06:01:50", "yyyy-MM-dd HH:mm:ss");  // 星期六
        DateTime dt4 = DateTime.parse("2022-01-02 06:01:50", "yyyy-MM-dd HH:mm:ss");  // 星期日

        String nameOfDayOfWeek01 = dt1.nameOfDayOfWeek();
        String nameOfDayOfWeek02 = dt2.nameOfDayOfWeek();
        String nameOfDayOfWeek03 = dt3.nameOfDayOfWeek();
        String nameOfDayOfWeek04 = dt4.nameOfDayOfWeek();
        String nameOfDayOfWeek11 = dt1.nameOfDayOfWeek(TextStyle.FULL);
        String nameOfDayOfWeek12 = dt2.nameOfDayOfWeek(TextStyle.NARROW);
        String nameOfDayOfWeek13 = dt3.nameOfDayOfWeek(TextStyle.SHORT);
        String nameOfDayOfWeek14 = dt4.nameOfDayOfWeek(TextStyle.NARROW_STANDALONE);
        String nameOfDayOfWeek21 = dt1.nameOfDayOfWeek(TextStyle.FULL, Locale.ENGLISH);
        String nameOfDayOfWeek22 = dt2.nameOfDayOfWeek(TextStyle.NARROW, Locale.ENGLISH);
        String nameOfDayOfWeek23 = dt3.nameOfDayOfWeek(TextStyle.SHORT, Locale.ENGLISH);
        String nameOfDayOfWeek24 = dt4.nameOfDayOfWeek(TextStyle.NARROW_STANDALONE, Locale.ENGLISH);

        System.out.println(nameOfDayOfWeek01);
        System.out.println(nameOfDayOfWeek02);
        System.out.println(nameOfDayOfWeek03);
        System.out.println(nameOfDayOfWeek04);
        System.out.println(nameOfDayOfWeek11);
        System.out.println(nameOfDayOfWeek12);
        System.out.println(nameOfDayOfWeek13);
        System.out.println(nameOfDayOfWeek14);
        System.out.println(nameOfDayOfWeek21);
        System.out.println(nameOfDayOfWeek22);
        System.out.println(nameOfDayOfWeek23);
        System.out.println(nameOfDayOfWeek24);

        assertEquals(nameOfDayOfWeek01, "星期一");
        assertEquals(nameOfDayOfWeek02, "星期三");
        assertEquals(nameOfDayOfWeek03, "星期六");
        assertEquals(nameOfDayOfWeek04, "星期日");

        assertEquals(nameOfDayOfWeek11, "星期一");
        assertEquals(nameOfDayOfWeek12, "周三");
        assertEquals(nameOfDayOfWeek13, "周六");
        assertEquals(nameOfDayOfWeek14, "日");

        assertEquals(nameOfDayOfWeek21, "Monday");
        assertEquals(nameOfDayOfWeek22, "W");
        assertEquals(nameOfDayOfWeek23, "Sat");
        assertEquals(nameOfDayOfWeek24, "S");

    }


    @Test
    public void testDTThrow() {
        try {
            DateTime.of(new Object());
            throwException();
        } catch (Exception e) {
            assertSame(e.getClass(), UnsupportedTemporalTypeException.class);
            assertEquals(e.getMessage(), "Only [java.util.Date, Calendar, LocalDateTime, ZonedDateTime, OffsetDateTime, Instant] is supported for `dateTime` parameter! ");
        }
        try {
            new DateTime(LocalDate.of(2024, 1, 1));
            throwException();
        } catch (Exception e) {
            assertSame(e.getClass(), UnsupportedTemporalTypeException.class);
            assertEquals(e.getMessage(), "The `dateTime` is of type `LocalDate`, please call the `DateTime.of(LocalDate)` function! ");
        }
        try {
            DateTime.of(java.sql.Date.valueOf(LocalDate.of(2023, 1, 1)));
            throwException();
        } catch (Exception e) {
            assertSame(e.getClass(), UnsupportedTemporalTypeException.class);
            assertEquals(e.getMessage(), "[java.sql.Date, java.sql.Time] are unsupported here, you can convert it to the `java.util.Date` first! ");
        }
    }

    @Test
    public void testZonedIds() {
        System.out.println("===============================所有可用时区ID=============================");
        System.out.println(ZoneId.getAvailableZoneIds());
        System.out.println(TZ.ZONE_IDS);
        assertEquals(ZoneId.getAvailableZoneIds().toString(), TZ.ZONE_IDS.toString());
    }

    private void throwException() {
        throw new RuntimeException();
    }

}
