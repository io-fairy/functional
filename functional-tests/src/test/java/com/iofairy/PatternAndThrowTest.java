package com.iofairy;

import com.iofairy.tcf.Try;
import org.junit.jupiter.api.Test;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.lang.invoke.StringConcatException;

import static com.iofairy.pattern.Pattern.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author GG
 */
public class PatternAndThrowTest {

    @Test
    public void testStringValueAndThrow1() {
        String str = "aBcdE123.$fGHIj";
        boolean isThrow = false;
        // ignore case match
        String matchRes1 = null;
        try {
            matchRes1 = match(str, IGNORECASE)
                    .when((String) null,
                            () -> { if (isThrow) throw new NullPointerException("NullPointerException..."); return "match null"; })
                    .when("abcd",
                            v -> "match abcd")
                    .when("abcdefg",
                            () -> { if (isThrow) throw new XMLStreamException("XMLStreamException..."); return "match abcdefg"; })
                    .when("abcd1",
                            () -> { if (isThrow) throw new StringConcatException("StringConcatException..."); return "match abcd1"; })
                    .when("abcd123",
                            () -> { if (isThrow) throw new IOException("IOException..."); return "match abcd123"; })
                    .when("abcde123.$fGHIj",
                            v -> "ignore case match")
                    .orElse(v -> "no match");
        } catch (IOException | StringConcatException | XMLStreamException e) {
            e.printStackTrace();
        }

        assertEquals("ignore case match", matchRes1);
    }

    @Test
    public void testStringValueAndThrow2() {
        String str = "aBcdE123.$fGHIj";
        boolean isThrow = false;
        // ignore case match
        String matchRes1 = null;
        try {
            matchRes1 = match(str, IGNORECASE)
                    .when((String) null,
                            () -> { if (isThrow) throw new StringConcatException("StringConcatException..."); return "match null"; })
                    .when("abcd",
                            v -> "match abcd")
                    .when("abcd1",
                            () -> { if (isThrow) throw new StringConcatException("StringConcatException..."); return "match abcd1"; })
                    .when("abcd123",
                            () -> { if (isThrow) throw new IOException("IOException..."); return "match abcd123"; })
                    .when("abcde123.$fGHIj",
                            () -> { if (isThrow) throw new IOException("IOException..."); return "ignore case match"; })
                    .orElse(v -> "no match");
        } catch (IOException | StringConcatException e) {
            e.printStackTrace();
        }

        assertEquals("ignore case match", matchRes1);
    }

    @Test
    public void testStringValueAndThrow3() {
        String str = "aBcdE123.$fGHIj";
        boolean isThrow = false;
        // ignore case match
        String matchRes1 = Try.tcf(() ->
                            match(str, IGNORECASE)
                                .when((String) null,
                                        () -> { if (isThrow) throw new StringConcatException("StringConcatException..."); return "match null"; })
                                .when("abcd",
                                        v -> "match abcd")
                                .when("abcd1",
                                        () -> { if (isThrow) throw new StringConcatException("StringConcatException..."); return "match abcd1"; })
                                .when("abcd123",
                                        () -> { if (isThrow) throw new IOException("IOException..."); return "match abcd123"; })
                                .when("abcde123.$fGHIj",
                                        () -> { if (isThrow) throw new IOException("IOException..."); return "ignore case match"; })
                                .orElse(v -> "no match")
                            );

        assertEquals("ignore case match", matchRes1);
    }

    @Test
    public void testStringValueAndThrow4() {
        String str = "aBcdE123.$fGHIj";
        boolean isThrow = false;
        boolean isMatch = true;
        // ignore case match
        String matchRes1 = Try.tcf(() ->
                            match(str, IGNORECASE)
                                .when((String) null,
                                        () -> { if (isThrow) throw new StringConcatException("StringConcatException..."); return "match null"; })
                                .when("abcd",
                                        v -> "match abcd")
                                .when("abcd1",
                                        () -> { if (isThrow) throw new StringConcatException("StringConcatException..."); return "match abcd1"; })
                                .when("abcd123",
                                        () -> { if (isThrow) throw new IOException("IOException..."); return "match abcd123"; })
                                .when("abcde123.$fGHIj",
                                        () -> { if (isMatch) throw new IOException("IOException..."); return "ignore case match"; })
                                .orElse(v -> "no match")
                            );

        assertNull(matchRes1);
    }

    @Test
    public void testStringValueAndThrow5() {
        String str = "aBcdE123.$fGHIj";
        boolean isThrow = false;
        boolean isMatch = true;
        // ignore case match
        String matchRes1 = null;
        try {
            matchRes1 = match(str, IGNORECASE)
                    .when((String) null,
                            () -> { if (isThrow) throw new StringConcatException("StringConcatException..."); return "match null"; })
                    .when("abcd",
                            v -> "match abcd")
                    .when("abcd1",
                            () -> { if (isThrow) throw new StringConcatException("StringConcatException..."); return "match abcd1"; })
                    .when("abcd123",
                            () -> { if (isThrow) throw new IOException("IOException..."); return "match abcd123"; })
                    .when("abcde123.$fGHIj",
                            () -> { if (isMatch) throw new IOException("IOException..."); return "ignore case match"; })
                    .orElse(v -> "no match");
        } catch (IOException | StringConcatException e) {
            e.printStackTrace();
        }

        assertNull(matchRes1);
    }

    @Test
    public void testStringValueAndThrow6() {
        String str = "aBcdE123.$fGHIj";
        boolean isThrow = false;
        boolean isMatch = true;
        // ignore case match
        String matchRes1 = null;
        try {
            matchRes1 = match(str, IGNORECASE)
                    .when((String) null,
                            () -> { if (isThrow) throw new StringConcatException("StringConcatException..."); return "match null"; })
                    .when("abcd",
                            v -> "match abcd")
                    .when("abcd1",
                            () -> { if (isThrow) throw new StringConcatException("StringConcatException..."); return "match abcd1"; })
                    .when("abcd123",
                            () -> { if (isThrow) throw new IOException("IOException..."); return "match abcd123"; })
                    .when("abcde123.$f",
                            () -> { if (isMatch) throw new IOException("IOException..."); return "ignore case match"; })
                    .orElse(
                            () -> { if (isThrow) throw new IOException("IOException..."); return "no match"; });

        } catch (IOException | StringConcatException e) {
            e.printStackTrace();
        }

        assertEquals("no match", matchRes1);
    }

}
