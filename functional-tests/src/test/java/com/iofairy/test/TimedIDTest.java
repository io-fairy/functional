package com.iofairy.test;

import com.iofairy.except.OutOfBoundsException;
import com.iofairy.id.TimedID;
import com.iofairy.tcf.Try;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author GG
 * @version 1.0
 */
public class TimedIDTest {

    @Test
    public void testTimedID() {
        System.out.println("===========testTimedID===========");
        String id00 = TimedID.getId();
        String id01 = TimedID.newId();
        String id02 = TimedID.shortId();
        String id03 = TimedID.mediumId();
        String id04 = TimedID.longId();
        String id05 = TimedID.linedId();
        String id06 = TimedID.nid();
        String id07 = TimedID.sid();
        String id08 = TimedID.mid();
        String id09 = TimedID.lid();
        String id10 = TimedID._id();

        TimedID timedID1 = TimedID.newBuilder().withTimestamp(true).withStartInstant("20240101000000000").withIdLength(25).build();
        TimedID timedID2 = TimedID.newBuilder().withTimestamp(true).withStartInstant(Instant.now()).withIdLength(25).build();
        TimedID timedID3 = TimedID.newBuilder().withTimestamp(true).withStartInstant("2024-04-08 22:53:00.000").withIdLength(25).build();
        TimedID timedID4 = TimedID.newBuilder().withYearLength(5).build();

        try {
            TimedID.newBuilder().withTimestamp(true).withStartInstant(Instant.now().plusSeconds(1)).withIdLength(25).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertNotNull(e.getMessage());
        }
        try {
            TimedID.newBuilder().withTimestamp(true).withStartInstant("2999-1-1").withIdLength(25).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertNotNull(e.getMessage());
        }

        System.out.println("id00: " + id00);    // id00: 240409064014820AADJWNSV35
        System.out.println("id01: " + id01);    // id01: 240409064015067AAAZSRRRGE
        System.out.println("id02: " + id02);    // id02: 0240409064015067AA9ULRWUBJ
        System.out.println("id03: " + id03);    // id03: 0240409064015067AA55DBDMO7Z3K4
        System.out.println("id04: " + id04);    // id04: 20240409064015068AAEI0SMVHDP274A
        System.out.println("id05: " + id05);    // id05: 0240610215244828_A9CE6NTLK2RAW
        System.out.println("id06: " + id06);    // id06: 1712616015068AA54VB5KZBUX
        System.out.println("id07: " + id07);    // id07: 1712616015068AA2BE42OPHJM8
        System.out.println("id08: " + id08);    // id08: 1712616015068AA8JEK4Q4FQAILX2H
        System.out.println("id09: " + id09);    // id09: 1712616015068AA55F78HAFKRP0S0ZS1
        System.out.println("id10: " + id10);    // id10: 1712616015069_AAYY7KL2ZN8XEQ
        // // TimedID{workerNum=0, workerIdLength=2, workerId='AA', idLength=25, upperCase=true, withUnderline=false, yearLength=3, withTimestamp=true, startInstant=2023-12-31T16:00:00Z, startEpochMillis=1704038400000}
        System.out.println(timedID1);
        System.out.println(timedID1.randomId());
        // TimedID{workerNum=0, workerIdLength=2, workerId='AA', idLength=25, upperCase=true, withUnderline=false, yearLength=3, withTimestamp=true, startInstant=2024-04-08T22:54:11.228149900Z, startEpochMillis=1712616851228}
        System.out.println(timedID2);
        System.out.println(timedID2.randomId());
        TimedID.setDefaultId(timedID2);
        for (int i = 0; i < 5; i++) {
            System.out.println(TimedID.getId());
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(timedID4.randomId());    // 020240419072646356AABAIXO
        }


        assertEquals(id01.length(), 25);
        assertEquals(id02.length(), 26);
        assertEquals(id03.length(), 30);
        assertEquals(id04.length(), 32);
        assertEquals(id05.length(), 30);
        assertEquals(id06.length(), 25);
        assertEquals(id07.length(), 26);
        assertEquals(id08.length(), 30);
        assertEquals(id09.length(), 32);
        assertEquals(id10.length(), 30);

    }


    /**
     * 测试ID生成性能
     */
    @Test
    public void testPerformance() {
        System.out.println("===========testPerformance===========");
        performance();
    }

    private static void performance() {
        AtomicInteger atomicInteger = new AtomicInteger();
        Thread thread = new Thread(() -> {
            for (int j = 0; j < 10000000; j++) {
                // TimedUUID.getId();      // 44612
                TimedID.getId();        // 45857
                // TimedID.mid();          // 52866
                // IdUtil.getSnowflakeNextId();// 1020078

                atomicInteger.incrementAndGet();
            }
        });
        thread.start();
        Try.tcf(() -> thread.join(1000), false);
        /*
         * 不转36进制，单线程每秒可以获取4万多ID
         * 转36进制，单线程每秒可以获取3万多ID
         */
        System.out.println("1秒获取ID数量：" + atomicInteger.get());
    }

    @Test
    public void testDefaultId() {
        TimedID defaultId = TimedID.getDefaultId();
        if (defaultId != null) return;
        System.out.println(defaultId);

        new Thread(() -> {
            String id = TimedID.getId();
            System.out.println(TimedID.getDefaultId());
            System.out.println(id);
        }).start();

        Try.sleep(500);

        String id = TimedID.getId();
        TimedID.setDefaultId(TimedID.TIMED_ID32);
        System.out.println(TimedID.getDefaultId());
        System.out.println(id);
        assertEquals("TimedID{workerNum=0, workerIdLength=2, workerId='AA', idLength=25, upperCase=true, withUnderline=false, yearLength=2, withTimestamp=false, startInstant=1970-01-01T00:00:00Z, startEpochMillis=0}", TimedID.getDefaultId().toString());
        assertEquals(25, id.length());
    }

    @Test
    public void testDefaultId1() {
        TimedID timedID = TimedID.newBuilder().withTimestamp(true).withStartInstant("2024-5-7 20:0:0.0").withIdLength(25).build();
        TimedID.setDefaultId(timedID);
        TimedID.setDefaultId(TimedID.TIMED_ID); // 无效设置
        for (int i = 0; i < 10; i++) {
            /*
             * 000003554589AABX6J4CSZBWJ
             * 000003554856AA71Q4F2MN9J7
             * 000003554856AA7OKJMZ1HZCE
             * 000003554856AA8X1081ZY4UJ
             * 000003554856AA73NY6R93QQS
             * 000003554856AA7NKRK5HDOMA
             * 000003554856AA9XUAO2FT6YJ
             * 000003554857AAH0UH5M2ULG8
             * 000003554857AA88EFGLRXXME
             * 000003554857AAOTZOXXNQSJB
             */
            System.out.println(TimedID.getId());
        }

        System.out.println(timedID.randomId()); // 000003554857AACYV7HG33PYG
    }

    @Test
    public void testPrintId() {
        System.out.println("===========TimedID.newId()===========");
        for (int i = 0; i < 5; i++) {
            System.out.println(TimedID.newId());
        }
        System.out.println("===========TimedID.shortId()===========");
        for (int i = 0; i < 5; i++) {
            System.out.println(TimedID.shortId());
        }
        System.out.println("===========TimedID.mediumId()===========");
        for (int i = 0; i < 5; i++) {
            System.out.println(TimedID.mediumId());
        }
        System.out.println("===========TimedID.longId()===========");
        for (int i = 0; i < 5; i++) {
            System.out.println(TimedID.longId());
        }
        System.out.println("===========TimedID.linedId()===========");
        for (int i = 0; i < 5; i++) {
            System.out.println(TimedID.linedId());
        }
        System.out.println("===========TimedID.nid()===========");
        for (int i = 0; i < 5; i++) {
            System.out.println(TimedID.nid());
        }
        System.out.println("===========TimedID.sid()===========");
        for (int i = 0; i < 5; i++) {
            System.out.println(TimedID.sid());
        }
        System.out.println("===========TimedID.mid()===========");
        for (int i = 0; i < 5; i++) {
            System.out.println(TimedID.mid());
        }
        System.out.println("===========TimedID.lid()===========");
        for (int i = 0; i < 5; i++) {
            System.out.println(TimedID.lid());
        }
        System.out.println("===========TimedID._id()===========");
        for (int i = 0; i < 5; i++) {
            System.out.println(TimedID._id());
        }
    }

    @Test
    public void testException() {
        TimedID.newBuilder().withWorkerNum(0).build();
        TimedID.newBuilder().withWorkerNum(1).build();
        TimedID.newBuilder().withWorkerIdLength(1).build();
        TimedID.newBuilder().withWorkerIdLength(6).build();
        TimedID.newBuilder().withWorkerNum(0).withWorkerIdLength(1).build();
        TimedID.newBuilder().withWorkerNum(20).withWorkerIdLength(1).build();
        TimedID.newBuilder().withWorkerNum(0).withWorkerIdLength(6).build();
        TimedID.newBuilder().withWorkerNum(308915775).withWorkerIdLength(6).build();

        try {
            TimedID.newBuilder().withWorkerNum(-1).build();
            throwException();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertSame(e.getClass(), OutOfBoundsException.class);
            assertEquals(e.getMessage(), "数值超出所允许的范围，当前值为：[-1]。参数`number`应为非负数！");
        }
        try {
            TimedID.numberToLetters(0, 0);
            throwException();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertSame(e.getClass(), OutOfBoundsException.class);
            assertEquals(e.getMessage(), "数值超出所允许的范围，当前值为：[0]。参数`letterLength`取值范围为：[1, 6]！");
        }
        try {
            TimedID.numberToLetters(0, 7);
            throwException();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertSame(e.getClass(), OutOfBoundsException.class);
            assertEquals(e.getMessage(), "数值超出所允许的范围，当前值为：[0]。参数`letterLength`取值范围为：[1, 6]！");
        }
        try {
            TimedID.newBuilder().withWorkerNum(26).withWorkerIdLength(1).build();
            throwException();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertSame(e.getClass(), IllegalArgumentException.class);
            assertEquals(e.getMessage(), "参数`number`超出范围，当前位数[1]下，最大允许值[25]，无法映射到指定位数的字母！");
        }
        try {
            TimedID.newBuilder().withWorkerNum(20000).withWorkerIdLength(3).build();
            throwException();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertSame(e.getClass(), IllegalArgumentException.class);
            assertEquals(e.getMessage(), "参数`number`超出范围，当前位数[3]下，最大允许值[17575]，无法映射到指定位数的字母！");
        }
        try {
            TimedID.newBuilder().withWorkerNum(308915776).withWorkerIdLength(6).build();
            throwException();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertSame(e.getClass(), IllegalArgumentException.class);
            assertEquals(e.getMessage(), "参数`number`超出范围，当前位数[6]下，最大允许值[308915775]，无法映射到指定位数的字母！");
        }

    }


    private void throwException() {
        throw new RuntimeException();
    }

}
