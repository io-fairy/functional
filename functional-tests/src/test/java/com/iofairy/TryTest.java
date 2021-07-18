package com.iofairy;

import com.iofairy.tcf.Try;
import org.junit.jupiter.api.Test;

/**
 * @author GG
 */
public class TryTest {
    @Test
    public void testTry() {
        long l = System.currentTimeMillis();
        System.out.println("start time: " + l);

        Try.tcf(() -> Thread.sleep(500));

        long cost = System.currentTimeMillis() - l;
        System.out.println("end time: " + System.currentTimeMillis() + "\ncost time: " + cost + " ms");
    }

}
