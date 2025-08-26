package com.iofairy.test.time;

import com.iofairy.time.TZ;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZoneId;

/**
 * @author GG
 * @version 1.0
 */
public class TZTest {
    @Test
    public void testZoneIds() {
        for (ZoneId zoneId : TZ.ZONE_IDS) {
            System.out.println(zoneId + " (" + zoneId.getRules().getOffset(Instant.now()) + ")");
        }
    }

}
