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
    public void testStringValueAndThrow0() {
        String str = "aBcdE123.$fGHIj";
        boolean isThrow = false;
        // ignore case match
        try {
            String matchRes1 = match(str, IGNORECASE)
                    .with((String) null,        v -> isThrow ? nullException(isThrow) : "match null")
                    .when("abcd",               v -> "match abcd")
                    .with("abcdefg",            v -> isThrow ? xmlException(isThrow) : "match abcdefg")
                    .with("abcd1",              v -> isThrow ? concatException(isThrow) : "match abcd1")
                    .with("abcd123",            v -> isThrow ? ioException(isThrow, "IOException...") : "match abcd123")
                    .with(str.contains("H"),    v -> isThrow ? ioException(isThrow, "IOException...") : "str.contains(\"H\")")
                    .when("abcde123.$fGHIj",    v -> "ignore case match")
                    .orElse(                    v -> "no match");
            assertEquals("str.contains(\"H\")", matchRes1);
        } catch (StringConcatException | XMLStreamException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStringValueAndThrow1() {
        String str = "aBcdE123.$fGHIj";
        boolean isThrow = false;
        // ignore case match
        try {
            String matchRes1 = match(str, IGNORECASE)
                    .with((String) null,
                            v -> isThrow ? nullException(isThrow) : "match null")
                    .when("abcd",
                            v -> "match abcd")
                    .with("abcdefg",
                            v -> isThrow ? xmlException(isThrow) : "match abcdefg")
                    .with("abcd1",
                            v -> isThrow ? concatException(isThrow) : "match abcd1")
                    .with("abcd123",
                            v -> isThrow ? ioException(isThrow, "IOException...") : "match abcd123")
                    .with(str.contains("X"),
                            v -> isThrow ? ioException(isThrow, "IOException...") : "str.contains(\"H\")")
                    .when("abcde123.$fGHIj",
                            v -> "ignore case match")
                    .orElse(v -> "no match");
            assertEquals("ignore case match", matchRes1);
        } catch (IOException | StringConcatException | XMLStreamException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStringValueAndThrow2() {
        String str = "aBcdE123.$fGHIj";
        boolean isThrow = false;
        // ignore case match
        String matchRes1 = null;
        try {
            matchRes1 = match(str, IGNORECASE)
                    .with((String) null,
                            v -> isThrow ? concatException(isThrow) : "match null")
                    .with("abcd",
                            v -> "match abcd")
                    .with("abcd1",
                            v -> isThrow ? concatException(isThrow) : "match abcd1")
                    .with("abcd123",
                            v -> isThrow ? ioException(isThrow, "IOException...") : "match abcd123")
                    .with("abcde123.$fGHIj",
                            v -> isThrow ? ioException(isThrow, "IOException...") : "ignore case match")
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
                        .with((String) null,
                                v -> isThrow ? concatException(isThrow) : "match null")
                        .with("abcd",
                                v -> "match abcd")
                        .with("abcd1",
                                v -> isThrow ? concatException(isThrow) : "match abcd1")
                        .with("abcd123",
                                v -> isThrow ? ioException(isThrow, "IOException...") : "match abcd123")
                        .with("abcde123.$fGHIj",
                                v -> isThrow ? ioException(isThrow, "IOException...") : "ignore case match")
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
                        .with((String) null,
                                v -> isThrow ? concatException(isThrow) : "match null")
                        .when("abcd",
                                v -> "match abcd")
                        .with("abcd1",
                                v -> isThrow ? concatException(isThrow) : "match abcd1")
                        .with("abcd123",
                                v -> isThrow ? ioException(isThrow, "IOException...") : "match abcd123")
                        .with("abcde123.$fGHIj",
                                v -> !isThrow ? ioException(isMatch, "IOException...") : "ignore case match")
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
        String matchRes1 = Try.tcf(() ->
                match(str, IGNORECASE)
                        .with((String) null,
                                v -> isThrow ? concatException(isThrow) : "match null")
                        .when("abcd",
                                v -> "match abcd")
                        .with("abcd1",
                                v -> isThrow ? concatException(isThrow) : "match abcd1")
                        .with("abcd123",
                                v -> isThrow ? ioException(isThrow, "IOException...") : "match abcd123")
                        .with("abcde123.$fGHIj",
                                v -> !isThrow ? ioException(isMatch, "IOException...match: abcde123.$fGHIj") : "ignore case match")
                        .orElse(v -> "no match")
        );

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
                    .with((String) null,
                            v -> isThrow ? concatException(isThrow) : "match null")
                    .when("abcd",
                            v -> "match abcd")
                    .with("abcd1",
                            v -> isThrow ? concatException(isThrow) : "match abcd1")
                    .with("abcd123",
                            v -> isThrow ? ioException(isThrow, "IOException...") : "match abcd123")
                    .with(str.contains("H"),
                            v -> !isThrow ? ioException(isMatch, "IOException...str.contains(\"H\")") : "str.contains(\"H\")")
                    .orElse(v -> "no match");
        } catch (IOException | StringConcatException e) {
            e.printStackTrace();
        }

        assertNull(matchRes1);
    }

    @Test
    public void testStringValueAndThrow7() {
        String str = "aBcdE123.$fGHIj";
        boolean isThrow = false;
        boolean isMatch = true;
        // ignore case match
        String matchRes1 = null;
        try {
            matchRes1 = match(str, IGNORECASE)
                    .with((String) null,
                            v -> isThrow ? concatException(isThrow) : "match null")
                    .when("abcd",
                            v -> "match abcd")
                    .with("abcd1",
                            v -> isThrow ? concatException(isThrow) : "match abcd1")
                    .with("abcd123",
                            v -> isThrow ? ioException(isThrow, "IOException...") : "match abcd123")
                    .with("abcde123.$f",
                            v -> isThrow ? ioException(isMatch, "IOException...") : "ignore case match")
                    .orWith(
                            v -> isThrow ? ioException(isThrow, "IOException...") : "no match");

        } catch (IOException | StringConcatException e) {
            e.printStackTrace();
        }

        assertEquals("no match", matchRes1);
    }


    private String ioException(boolean isThrow, String s) throws IOException {
        if (isThrow) throw new IOException(s);
        else return null;
    }

    private String concatException(boolean isThrow) throws StringConcatException {
        if (isThrow) throw new StringConcatException("StringConcatException...");
        else return null;
    }

    private String xmlException(boolean isThrow) throws XMLStreamException {
        if (isThrow) throw new XMLStreamException("XMLStreamException...");
        else return null;
    }

    private String nullException(boolean isThrow) {
        if (isThrow) throw new NullPointerException("NullPointerException...");
        else return null;
    }
}
