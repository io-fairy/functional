package com.iofairy.test.time;

import com.iofairy.time.DTC;
import com.iofairy.time.DateTime;
import com.iofairy.time.DateTimes;
import com.iofairy.time.TZ;
import com.iofairy.top.G;
import com.iofairy.tuple.Tuple2;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.TextStyle;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author GG
 * @version 1.0
 */
public class DateTimesTest {

    @Test
    public void testTzToTZ() {
        LocalDateTime ldt = LocalDateTime.of(2022, 1, 27, 6, 0, 10, 65891000);
        Date date = DateTime.of(ldt).toDate();

        Date date1 = DateTimes.defaultDateToTZ(date, TZ.MOSCOW);
        Date date2 = DateTimes.defaultDateToTZ(date, TZ.HONG_KONG);
        Date date3 = DateTimes.tzDateToTZ(date1, TZ.MOSCOW, TZ.DUBAI);
        Date date4 = DateTimes.tzDateToTZ(date1, TZ.SHANGHAI, TZ.NEW_YORK);
        Date date5 = DateTimes.tzDateToTZ(date1, null, null);

        LocalDateTime ldt1 = DateTimes.defaultLocalDTToTZ(ldt, TZ.MOSCOW);
        LocalDateTime ldt2 = DateTimes.defaultLocalDTToTZ(ldt, TZ.HONG_KONG);
        LocalDateTime ldt3 = DateTimes.tzLocalDTToTZ(ldt1, TZ.MOSCOW, TZ.DUBAI);
        LocalDateTime ldt4 = DateTimes.tzLocalDTToTZ(ldt1, TZ.SHANGHAI, TZ.NEW_YORK);
        LocalDateTime ldt5 = DateTimes.tzLocalDTToTZ(ldt1, null, null);

        ZonedDateTime zdt1 = DateTimes.tzDateToZonedDT(date, null, TZ.NEW_YORK);
        ZonedDateTime zdt2 = DateTimes.tzDateToZonedDT(date, TZ.MOSCOW, TZ.NEW_YORK);
        ZonedDateTime zdt3 = DateTimes.tzDateToZonedDT(date, TZ.NEW_YORK, TZ.DUBAI);
        ZonedDateTime zdt4 = DateTimes.tzDateToZonedDT(date, null, null);
        ZonedDateTime zdt5 = DateTimes.tzDateToZonedDT(date, TZ.NEW_YORK, TZ.NEW_YORK);

        System.out.println("before(+8): " + G.dtSimple(date) + "---after(+3): " + G.dtSimple(date1));   // before(+8): 2022-01-27 06:00:10.065---after(+3): 2022-01-27 01:00:10.065
        System.out.println("before(+8): " + G.dtSimple(date) + "---after(+8): " + G.dtSimple(date2));   // before(+8): 2022-01-27 06:00:10.065---after(+8): 2022-01-27 06:00:10.065
        System.out.println("before(+3): " + G.dtSimple(date1) + "---after(+4): " + G.dtSimple(date3));  // before(+3): 2022-01-27 01:00:10.065---after(+4): 2022-01-27 02:00:10.065
        System.out.println("before(+8): " + G.dtSimple(date1) + "---after(-5): " + G.dtSimple(date4));  // before(+8): 2022-01-27 01:00:10.065---after(-5): 2022-01-26 12:00:10.065
        System.out.println("before(+8): " + G.dtSimple(date1) + "---after(+8): " + G.dtSimple(date5));  // before(+8): 2022-01-27 01:00:10.065---after(+8): 2022-01-27 01:00:10.065
        System.out.println("before(+8): " + G.dtSimple(ldt) + "---after(+3): " + G.dtSimple(ldt1));     // before(+8): 2022-01-27 06:00:10.065---after(+3): 2022-01-27 01:00:10.065
        System.out.println("before(+8): " + G.dtSimple(ldt) + "---after(+8): " + G.dtSimple(ldt2));     // before(+8): 2022-01-27 06:00:10.065---after(+8): 2022-01-27 06:00:10.065
        System.out.println("before(+3): " + G.dtSimple(ldt1) + "---after(+4): " + G.dtSimple(ldt3));    // before(+3): 2022-01-27 01:00:10.065---after(+4): 2022-01-27 02:00:10.065
        System.out.println("before(+8): " + G.dtSimple(ldt1) + "---after(-5): " + G.dtSimple(ldt4));    // before(+8): 2022-01-27 01:00:10.065---after(-5): 2022-01-26 12:00:10.065
        System.out.println("before(+8): " + G.dtSimple(ldt1) + "---after(+8): " + G.dtSimple(ldt5));    // before(+8): 2022-01-27 01:00:10.065---after(+8): 2022-01-27 01:00:10.065

        System.out.println("before(+8): " + G.dtSimple(date) + "---after(-5): " + G.dtSimple(zdt1));
        System.out.println("before(+3): " + G.dtSimple(date) + "---after(-5): " + G.dtSimple(zdt2));
        System.out.println("before(-5): " + G.dtSimple(date) + "---after(+4): " + G.dtSimple(zdt3));
        System.out.println("before(+8): " + G.dtSimple(date) + "---after(+8): " + G.dtSimple(zdt4));
        System.out.println("before(-5): " + G.dtSimple(date) + "---after(-5): " + G.dtSimple(zdt5));

        assertEquals("2022-01-27 01:00:10.065", G.dtSimple(date1));
        assertEquals("2022-01-27 06:00:10.065", G.dtSimple(date2));
        assertEquals("2022-01-27 02:00:10.065", G.dtSimple(date3));
        assertEquals("2022-01-26 12:00:10.065", G.dtSimple(date4));
        assertEquals("2022-01-27 01:00:10.065", G.dtSimple(date5));
        assertEquals("2022-01-27 01:00:10.065", G.dtSimple(ldt1));
        assertEquals("2022-01-27 06:00:10.065", G.dtSimple(ldt2));
        assertEquals("2022-01-27 02:00:10.065", G.dtSimple(ldt3));
        assertEquals("2022-01-26 17:00:10.065 [America/New_York -05:00]", G.dtSimple(zdt1));
        assertEquals("2022-01-26 22:00:10.065 [America/New_York -05:00]", G.dtSimple(zdt2));
        assertEquals("2022-01-27 15:00:10.065 [Asia/Dubai +04:00]", G.dtSimple(zdt3));
        assertEquals("2022-01-27 06:00:10.065", G.dtSimple(zdt4));
        assertEquals("2022-01-27 06:00:10.065 [America/New_York -05:00]", G.dtSimple(zdt5));

    }

    @Test
    public void testToZonedDT() {
        LocalDateTime ldt = LocalDateTime.of(2022, 1, 27, 6, 0, 10, 65891000);
        DateTime dateTime = DateTime.of(ldt);
        Calendar calendar = dateTime.toUTCCalendar();
        ZonedDateTime zdt = dateTime.toZonedDT(TZ.UTC);

        ZonedDateTime zdt1 = DateTimes.toZonedDT(calendar, null);
        ZonedDateTime zdt2 = DateTimes.toZonedDT(calendar, TZ.DEFAULT_ZONE);

        ZonedDateTime zonedDateTime = dateTime.getZonedDateTime();
        System.out.println(G.toString(zdt));                // 2022-01-26 22:00:10.065 [UTC +00:00]
        System.out.println(G.toString(zonedDateTime));      // 2022-01-27 06:00:10.065
        System.out.println(G.toString(calendar));           // 2022-01-26 22:00:10.065 [UTC +00:00]
        System.out.println(G.toString(zdt1));               // 2022-01-26 22:00:10.065 [UTC +00:00]
        System.out.println(G.toString(zdt2));               // 2022-01-27 06:00:10.065
        System.out.println(zdt.toInstant());                // 2022-01-26T22:00:10.065891Z
        System.out.println(zonedDateTime.toInstant());      // 2022-01-26T22:00:10.065891Z
        System.out.println(calendar.toInstant());           // 2022-01-26T22:00:10.065Z

        assertEquals(G.toString(zdt), "2022-01-26 22:00:10.065 [UTC +00:00]");
        assertEquals(G.toString(zonedDateTime), "2022-01-27 06:00:10.065");
        assertEquals(G.toString(calendar), "2022-01-26 22:00:10.065 [UTC +00:00]");
        assertEquals(G.toString(zdt1), "2022-01-26 22:00:10.065 [UTC +00:00]");
        assertEquals(G.toString(zdt2), "2022-01-27 06:00:10.065");
        assertEquals(zdt.toInstant().toString(), "2022-01-26T22:00:10.065891Z");
        assertEquals(zonedDateTime.toInstant().toString(), "2022-01-26T22:00:10.065891Z");
        assertEquals(calendar.toInstant().toString(), "2022-01-26T22:00:10.065Z");
    }

    @Test
    public void testHoursAndMinutes() {
        List<String> hhs1 = DateTimes.hoursOfDay(0, ":");
        List<String> hhs2 = DateTimes.hoursOfDay(0, "");
        List<String> hhs3 = DateTimes.hoursOfDay(1, ":");
        List<String> hhs4 = DateTimes.hoursOfDay(1, "");
        List<String> hhs5 = DateTimes.hoursOfDay(2, ":");
        List<String> hhs6 = DateTimes.hoursOfDay(2, "");

        List<String> hhs01 = DateTimes.hoursOfDay("20220806", 0);
        List<String> hhs02 = DateTimes.hoursOfDay("20220806", 1);
        List<String> hhs03 = DateTimes.hoursOfDay("20220806", 2);

        List<String> hhs11 = DateTimes.hoursOfDay("2022/08/06 ", 0, ":");
        List<String> hhs12 = DateTimes.hoursOfDay("2022/08/06 ", 1, ":");
        List<String> hhs13 = DateTimes.hoursOfDay("2022/08/06 ", 2, ":");

        List<String> minutes1 = DateTimes.minutesOfHour("", false, ":");
        List<String> minutes2 = DateTimes.minutesOfHour("", false, "");
        List<String> minutes3 = DateTimes.minutesOfHour("15", false, ":");
        List<String> minutes4 = DateTimes.minutesOfHour("15", false, "");
        List<String> minutes5 = DateTimes.minutesOfHour("", true, ":");
        List<String> minutes6 = DateTimes.minutesOfHour("", true, "");
        List<String> minutes7 = DateTimes.minutesOfHour("15", true, ":");
        List<String> minutes8 = DateTimes.minutesOfHour("15", true, "");

        List<String> seconds1 = DateTimes.secondsOfMinute("", ":");
        List<String> seconds2 = DateTimes.secondsOfMinute("", "");
        List<String> seconds3 = DateTimes.secondsOfMinute("56", ":");
        List<String> seconds4 = DateTimes.secondsOfMinute("56", "");

        // System.out.println(hhs1);
        // System.out.println(hhs2);
        // System.out.println(hhs3);
        // System.out.println(hhs4);
        // System.out.println(hhs5);
        // System.out.println(hhs6);

        // System.out.println(hhs01);
        // System.out.println(hhs02);
        // System.out.println(hhs03);
        // System.out.println(hhs11);
        // System.out.println(hhs12);
        // System.out.println(hhs13);

        // System.out.println(minutes1);
        // System.out.println(minutes2);
        // System.out.println(minutes3);
        // System.out.println(minutes4);
        // System.out.println(minutes5);
        // System.out.println(minutes6);
        // System.out.println(minutes7);
        // System.out.println(minutes8);
        // System.out.println(seconds1);
        // System.out.println(seconds2);
        // System.out.println(seconds3);
        // System.out.println(seconds4);

        assertEquals(hhs1.toString(), "[00, 01, 02, 03, 04, 05, 06, 07, 08, 09, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23]");
        assertEquals(hhs2.toString(), "[00, 01, 02, 03, 04, 05, 06, 07, 08, 09, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23]");
        assertEquals(hhs3.toString(), "[00:00, 01:00, 02:00, 03:00, 04:00, 05:00, 06:00, 07:00, 08:00, 09:00, 10:00, 11:00, 12:00, 13:00, 14:00, " +
                "15:00, 16:00, 17:00, 18:00, 19:00, 20:00, 21:00, 22:00, 23:00]");
        assertEquals(hhs4.toString(), "[0000, 0100, 0200, 0300, 0400, 0500, 0600, 0700, 0800, 0900, 1000, 1100, 1200, 1300, 1400, 1500, 1600, 1700, " +
                "1800, 1900, 2000, 2100, 2200, 2300]");
        assertEquals(hhs5.toString(), "[00:00:00, 01:00:00, 02:00:00, 03:00:00, 04:00:00, 05:00:00, 06:00:00, 07:00:00, 08:00:00, 09:00:00, 10:00:00, " +
                "11:00:00, 12:00:00, 13:00:00, 14:00:00, 15:00:00, 16:00:00, 17:00:00, 18:00:00, 19:00:00, 20:00:00, 21:00:00, 22:00:00, 23:00:00]");
        assertEquals(hhs6.toString(), "[000000, 010000, 020000, 030000, 040000, 050000, 060000, 070000, 080000, 090000, 100000, 110000, 120000, 130000, " +
                "140000, 150000, 160000, 170000, 180000, 190000, 200000, 210000, 220000, 230000]");

        assertEquals(hhs01.toString(), "[2022080600, 2022080601, 2022080602, 2022080603, 2022080604, 2022080605, 2022080606, " +
                "2022080607, 2022080608, 2022080609, 2022080610, 2022080611, 2022080612, 2022080613, 2022080614, 2022080615, " +
                "2022080616, 2022080617, 2022080618, 2022080619, 2022080620, 2022080621, 2022080622, 2022080623]");
        assertEquals(hhs02.toString(), "[202208060000, 202208060100, 202208060200, 202208060300, 202208060400, 202208060500, " +
                "202208060600, 202208060700, 202208060800, 202208060900, 202208061000, 202208061100, 202208061200, 202208061300, " +
                "202208061400, 202208061500, 202208061600, 202208061700, 202208061800, 202208061900, 202208062000, 202208062100, " +
                "202208062200, 202208062300]");
        assertEquals(hhs03.toString(), "[20220806000000, 20220806010000, 20220806020000, 20220806030000, 20220806040000, 20220806050000, " +
                "20220806060000, 20220806070000, 20220806080000, 20220806090000, 20220806100000, 20220806110000, 20220806120000, " +
                "20220806130000, 20220806140000, 20220806150000, 20220806160000, 20220806170000, 20220806180000, 20220806190000, " +
                "20220806200000, 20220806210000, 20220806220000, 20220806230000]");
        assertEquals(hhs11.toString(), "[2022/08/06 00, 2022/08/06 01, 2022/08/06 02, 2022/08/06 03, 2022/08/06 04, 2022/08/06 05, " +
                "2022/08/06 06, 2022/08/06 07, 2022/08/06 08, 2022/08/06 09, 2022/08/06 10, 2022/08/06 11, 2022/08/06 12, 2022/08/06 13, " +
                "2022/08/06 14, 2022/08/06 15, 2022/08/06 16, 2022/08/06 17, 2022/08/06 18, 2022/08/06 19, 2022/08/06 20, 2022/08/06 21, " +
                "2022/08/06 22, 2022/08/06 23]");
        assertEquals(hhs12.toString(), "[2022/08/06 00:00, 2022/08/06 01:00, 2022/08/06 02:00, 2022/08/06 03:00, 2022/08/06 04:00, " +
                "2022/08/06 05:00, 2022/08/06 06:00, 2022/08/06 07:00, 2022/08/06 08:00, 2022/08/06 09:00, 2022/08/06 10:00, " +
                "2022/08/06 11:00, 2022/08/06 12:00, 2022/08/06 13:00, 2022/08/06 14:00, 2022/08/06 15:00, 2022/08/06 16:00, " +
                "2022/08/06 17:00, 2022/08/06 18:00, 2022/08/06 19:00, 2022/08/06 20:00, 2022/08/06 21:00, 2022/08/06 22:00, 2022/08/06 23:00]");
        assertEquals(hhs13.toString(), "[2022/08/06 00:00:00, 2022/08/06 01:00:00, 2022/08/06 02:00:00, 2022/08/06 03:00:00, " +
                "2022/08/06 04:00:00, 2022/08/06 05:00:00, 2022/08/06 06:00:00, 2022/08/06 07:00:00, 2022/08/06 08:00:00, " +
                "2022/08/06 09:00:00, 2022/08/06 10:00:00, 2022/08/06 11:00:00, 2022/08/06 12:00:00, 2022/08/06 13:00:00, 2022/08/06 14:00:00, " +
                "2022/08/06 15:00:00, 2022/08/06 16:00:00, 2022/08/06 17:00:00, 2022/08/06 18:00:00, 2022/08/06 19:00:00, 2022/08/06 20:00:00, " +
                "2022/08/06 21:00:00, 2022/08/06 22:00:00, 2022/08/06 23:00:00]");

        assertEquals(minutes1.size(), 60);
        assertEquals(minutes2.size(), 60);
        assertEquals(minutes3.size(), 60);
        assertEquals(minutes4.size(), 60);
        assertEquals(minutes5.size(), 60);
        assertEquals(minutes6.size(), 60);
        assertEquals(minutes7.size(), 60);
        assertEquals(minutes8.size(), 60);
        assertEquals(minutes1.get(29), "29");
        assertEquals(minutes2.get(29), "29");
        assertEquals(minutes3.get(29), "15:29");
        assertEquals(minutes4.get(29), "1529");
        assertEquals(minutes5.get(29), "29:00");
        assertEquals(minutes6.get(29), "2900");
        assertEquals(minutes7.get(29), "15:29:00");
        assertEquals(minutes8.get(29), "152900");

        assertEquals(seconds1.get(3), "03");
        assertEquals(seconds2.get(3), "03");
        assertEquals(seconds3.get(3), "56:03");
        assertEquals(seconds4.get(3), "5603");
        assertEquals(seconds1.get(39), "39");
        assertEquals(seconds2.get(39), "39");
        assertEquals(seconds3.get(39), "56:39");
        assertEquals(seconds4.get(39), "5639");

    }

    @Test
    public void testHourMinutesOfDay() {
        List<String> hourMinutes1 = DateTimes.hourMinutesOfDay(false, ":");
        List<String> hourMinutes2 = DateTimes.hourMinutesOfDay(true, ":");
        List<String> hourMinutes3 = DateTimes.hourMinutesOfDay(false, null);
        List<String> hourMinutes4 = DateTimes.hourMinutesOfDay(true, null);
        // System.out.println(hourMinutes1);
        // System.out.println(hourMinutes2);
        // System.out.println(hourMinutes3);
        // System.out.println(hourMinutes4);

        assertEquals(hourMinutes1.size(), 1440);
        assertEquals(hourMinutes2.size(), 1440);
        assertEquals(hourMinutes3.size(), 1440);
        assertEquals(hourMinutes4.size(), 1440);
        assertEquals(hourMinutes1.get(999), "16:39");
        assertEquals(hourMinutes2.get(999), "16:39:00");
        assertEquals(hourMinutes3.get(999), "1639");
        assertEquals(hourMinutes4.get(999), "163900");
        assertEquals(hourMinutes1.get(1439), "23:59");
        assertEquals(hourMinutes2.get(1439), "23:59:00");
        assertEquals(hourMinutes3.get(1439), "2359");
        assertEquals(hourMinutes4.get(1439), "235900");
    }

    @Test
    public void testToUTCZonedDT() {
        LocalDateTime localDateTime = LocalDateTime.of(2022, 1, 1, 10, 10, 5, 987656789);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, TZ.NEW_YORK);
        OffsetDateTime offsetDateTime = OffsetDateTime.of(localDateTime, ZoneOffset.UTC);
        Instant instant = localDateTime.toInstant(ZoneOffset.ofHours(8));
        // 转换成 ZonedDateTime
        ZonedDateTime zonedDateTime1 = DateTime.of(localDateTime).toZonedDT(null);
        ZonedDateTime zonedDateTime2 = DateTime.of(zonedDateTime).toZonedDT(null);
        ZonedDateTime zonedDateTime3 = DateTime.of(offsetDateTime).toZonedDT(null);
        ZonedDateTime zonedDateTime4 = DateTime.of(instant).toZonedDT(null);
        // System.out.println(localDateTime);
        // System.out.println(zonedDateTime);
        // System.out.println(offsetDateTime);
        // System.out.println(instant);
        // System.out.println(G.dtDetail(zonedDateTime1));
        // System.out.println(G.dtDetail(zonedDateTime2));
        // System.out.println(G.dtDetail(zonedDateTime3));
        // System.out.println(G.dtDetail(zonedDateTime4));
        assertEquals("2022-01-01 10:10:05.987656789 [Asia/Shanghai +08:00 GMT+8 周六]", G.dtDetail(zonedDateTime1));
        assertEquals("2022-01-01 10:10:05.987656789 [America/New_York -05:00 GMT-5 周六]", G.dtDetail(zonedDateTime2));
        assertEquals("2022-01-01 10:10:05.987656789 [Z +00:00 GMT 周六]", G.dtDetail(zonedDateTime3));
        assertEquals("2022-01-01 10:10:05.987656789 [Asia/Shanghai +08:00 GMT+8 周六]", G.dtDetail(zonedDateTime4));
    }

    @Test
    public void testDateToTZ() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse("2020-01-01 5:01:50");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("origin date: \n" + sdf.format(date));
        System.out.println("-------------------------");

        Date utcDate = DateTimes.defaultDateToTZ(date, ZoneId.of("UTC"));
        String utcDateStr = sdf.format(utcDate);
        System.out.println("local to utc date: \n" + utcDateStr);
        assertEquals("2019-12-31 21:01:50", utcDateStr);
        System.out.println("-------------------------");

        // ZoneId.of("Australia/Victoria")  +11:00
        Date utcDate1 = DateTimes.tzDateToTZ(date, ZoneId.of("Australia/Victoria"), ZoneId.of("UTC"));
        System.out.println("Australia/Victoria to utc date: \n" + sdf.format(utcDate1));
        assertEquals("2019-12-31 18:01:50", sdf.format(utcDate1));
        System.out.println("-------------------------");

    }

    @Test
    public void testCalendarToTZ() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse("2019-12-31 22:01:50");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("origin date: \n" + sdf.format(date));
        System.out.println("-------------------------");

        Calendar utcCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        utcCalendar.setTime(date);
        System.out.println("utcCalendar: \n" + utcCalendar);
        System.out.println("utcCalendar date: \n" + sdf.format(utcCalendar.getTime()));
        assertEquals("2019-12-31 22:01:50", sdf.format(utcCalendar.getTime()));
        System.out.println("-------------------------");

    }

    @Test
    public void testCalendarToTZ1() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse("2019-12-31 22:01:50");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("origin date: \n" + sdf.format(date));
        System.out.println("-------------------------");

        Calendar utcCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        utcCalendar.setTime(date);
        System.out.println("utcCalendar: \n" + utcCalendar);
        System.out.println("utcCalendar date: \n" + sdf.format(utcCalendar.getTime()));
        assertEquals("2019-12-31 22:01:50", sdf.format(utcCalendar.getTime()));
        System.out.println("-------------------------");

    }

    @Test
    public void testCalendarToTZ2() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse("2019-12-31 22:01:50");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("origin date: \n" + sdf.format(date));
        System.out.println("-------------------------");

        Calendar localCalendar = Calendar.getInstance();
        localCalendar.setTime(date);
        System.out.println("Calendar: \n" + localCalendar);
        System.out.println("localCalendar date: \n" + sdf.format(localCalendar.getTime()));
        assertEquals("2019-12-31 22:01:50", sdf.format(localCalendar.getTime()));
        System.out.println("-------------------------");

    }

    @Test
    public void testBetweenDayOfWeeks() {
        Tuple2<Integer, Integer> between1 = DateTimes.daysBetween(DayOfWeek.MONDAY, DayOfWeek.FRIDAY);
        Tuple2<Integer, Integer> between2 = DateTimes.daysBetween(DayOfWeek.FRIDAY, DayOfWeek.MONDAY);
        Tuple2<Integer, Integer> between3 = DateTimes.daysBetween(DayOfWeek.WEDNESDAY, DayOfWeek.WEDNESDAY);
        Tuple2<Integer, Integer> between4 = DateTimes.daysBetween(DayOfWeek.TUESDAY, DayOfWeek.SUNDAY);
        Tuple2<Integer, Integer> between5 = DateTimes.daysBetween(DayOfWeek.SUNDAY, DayOfWeek.TUESDAY);
        Tuple2<Integer, Integer> between6 = DateTimes.daysBetween(DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY);
        Tuple2<Integer, Integer> between7 = DateTimes.daysBetween(DayOfWeek.THURSDAY, DayOfWeek.WEDNESDAY);

        // System.out.println(between1);
        // System.out.println(between2);
        // System.out.println(between3);
        // System.out.println(between4);
        // System.out.println(between5);
        // System.out.println(between6);
        // System.out.println(between7);

        assertEquals("(3, 4)", between1.toString());
        assertEquals("(4, 3)", between2.toString());
        assertEquals("(0, 0)", between3.toString());
        assertEquals("(2, 5)", between4.toString());
        assertEquals("(5, 2)", between5.toString());
        assertEquals("(6, 1)", between6.toString());
        assertEquals("(1, 6)", between7.toString());

    }

    @Test
    public void testGetLastDayOfWeek() {
        DayOfWeek[] dayOfWeeks = DayOfWeek.values();
        for (DayOfWeek firstDayOfWeek : dayOfWeeks) {
            DayOfWeek lastDayOfWeek = DateTimes.getLastDayOfWeek(firstDayOfWeek);
            System.out.println(firstDayOfWeek + "---" + lastDayOfWeek);
        }
        assertEquals(DayOfWeek.SUNDAY, DateTimes.getLastDayOfWeek(DayOfWeek.MONDAY));
        assertEquals(DayOfWeek.SATURDAY, DateTimes.getLastDayOfWeek(DayOfWeek.SUNDAY));
    }

    @Test
    public void testDTConst() {
        System.out.println(DTC.WEEK_ISO);
        System.out.println(DTC.MONDAY_MIN1);
        System.out.println(DTC.MONDAY_MIN4);
        System.out.println(DTC.SUNDAY_MIN1);
        System.out.println(DTC.SUNDAY_MIN4);
        System.out.println(DTC.SATURDAY_MIN1);
        System.out.println(DTC.SATURDAY_MIN4);

    }

    @Test
    public void testNameOfDayOfWeek() {
        String nameOfDayOfWeek01 = DateTimes.nameOfDayOfWeek(DayOfWeek.MONDAY, TextStyle.FULL, Locale.US);
        String nameOfDayOfWeek02 = DateTimes.nameOfDayOfWeek(DayOfWeek.WEDNESDAY, TextStyle.SHORT, Locale.ENGLISH);
        String nameOfDayOfWeek03 = DateTimes.nameOfDayOfWeek(DayOfWeek.WEDNESDAY, TextStyle.NARROW, Locale.ENGLISH);
        String nameOfDayOfWeek04 = DateTimes.nameOfDayOfWeek(DayOfWeek.WEDNESDAY, TextStyle.NARROW, Locale.CHINESE);
        String nameOfDayOfWeek05 = DateTimes.nameOfDayOfWeek(DayOfWeek.FRIDAY, TextStyle.NARROW_STANDALONE, Locale.CHINESE);
        String nameOfDayOfWeek06 = DateTimes.nameOfDayOfWeek(DayOfWeek.WEDNESDAY, Locale.ENGLISH);
        String nameOfDayOfWeek07 = DateTimes.nameOfDayOfWeek(DayOfWeek.SUNDAY, Locale.CHINESE);
        String nameOfDayOfWeek08 = DateTimes.nameOfDayOfWeek(DayOfWeek.SUNDAY);
        String nameOfDayOfWeek10 = DateTimes.nameOfDayOfWeek(DayOfWeek.TUESDAY, TextStyle.NARROW);

        System.out.println(nameOfDayOfWeek01);
        System.out.println(nameOfDayOfWeek02);
        System.out.println(nameOfDayOfWeek03);
        System.out.println(nameOfDayOfWeek04);
        System.out.println(nameOfDayOfWeek05);
        System.out.println(nameOfDayOfWeek06);
        System.out.println(nameOfDayOfWeek07);
        System.out.println(nameOfDayOfWeek08);
        System.out.println(nameOfDayOfWeek10);
        try {
            String nameOfDayOfWeek09 = DateTimes.nameOfDayOfWeek(null);
            throwException();
        } catch (Exception e) {
            assertSame(e.getClass(), NullPointerException.class);
            assertEquals("The parameter `dayOfWeek` must be non-null! ", e.getMessage());
        }

        assertEquals("Monday", nameOfDayOfWeek01);
        assertEquals("Wed", nameOfDayOfWeek02);
        assertEquals("W", nameOfDayOfWeek03);
        assertEquals("周三", nameOfDayOfWeek04);
        assertEquals("五", nameOfDayOfWeek05);
        assertEquals("Wednesday", nameOfDayOfWeek06);
        assertEquals("星期日", nameOfDayOfWeek07);
        assertEquals("星期日", nameOfDayOfWeek08);
        assertEquals("周二", nameOfDayOfWeek10);

    }

    private void throwException() {
        throw new RuntimeException();
    }
}
