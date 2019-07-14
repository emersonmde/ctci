package io.xylite.ctci.strings;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IsUniqueTest {
    @Test
    public void allCharactersUnique1() {
        HashMap<String, Boolean> tests = new HashMap<>();
        tests.put("uncopyrightable", true);
        tests.put("abcdefga", false);
        tests.put("aaaaaaaaab", false);
        tests.put("zyxwvut", true);
        tests.put("uncopyrightableuncopyrightable", false);
        tests.put("", true);
        tests.put("_!@#$_", false);

        for (Map.Entry<String, Boolean> entry : tests.entrySet()) {
            if (entry.getValue()) {
                assertTrue(IsUnique.allCharactersUnique1(entry.getKey()));
                assertTrue(IsUnique.allCharactersUnique2(entry.getKey()));
            } else {
                assertFalse(IsUnique.allCharactersUnique1(entry.getKey()));
                assertFalse(IsUnique.allCharactersUnique2(entry.getKey()));
            }
        }
    }
}