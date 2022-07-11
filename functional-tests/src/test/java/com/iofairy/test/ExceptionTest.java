package com.iofairy.test;

import com.iofairy.except.OutOfBoundsException;
import com.iofairy.top.G;
import org.junit.jupiter.api.Test;

/**
 * @author GG
 * @version 1.0
 */
public class ExceptionTest {
    @Test
    public void testOutOfBoundsException() {
        try {
            throw new OutOfBoundsException(10.98767899876D);
        } catch (OutOfBoundsException e) {
            System.out.println(G.stackTrace(e));
        }
        System.out.println("---------------------------------------------------------------------------------------");
        try {
            throw new OutOfBoundsException(10.98767000033D, "The value must be less than [10]. ");
        } catch (OutOfBoundsException e) {
            System.out.println(G.stackTrace(e));
        }
    }
}
