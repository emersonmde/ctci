package io.xylite.ctci;

import io.xylite.ctci.data_structures.HashTable;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class HashTableTest {
    private HashTable<String, String> hashTable;

    @Before
    public void setUp() {
        hashTable = new HashTable<>();
        hashTable.put("testKey1", "testValue1");
    }

    @Test
    public void put() {
        String key = "testKey2";
        String value = "testValue2";
        hashTable.put(key, value);
        assertTrue(hashTable.containsKey(key));
        assertTrue(hashTable.containsValue(value));
    }

    @Test
    public void get() {
        assertEquals(hashTable.get("testKey1"), "testValue1");
    }

    @Test
    public void size() {
        String key = "test";
        String value = "test";
        HashTable<String, String> testHashTable = new HashTable<>();
        testHashTable.put(key, value);
        assertEquals(testHashTable.size(), Integer.valueOf(1));
    }

    @Test
    public void containsKey() {
        assertTrue(hashTable.containsKey("testKey1"));
        assertFalse(hashTable.containsKey("NONKEY"));
    }

    @Test
    public void containsValue() {
        assertTrue(hashTable.containsValue("testValue1"));
        assertFalse(hashTable.containsValue("NONVALUE"));
    }

    @Test
    public void testAutoResize() {
        HashTable<String, String> testHashTable = new HashTable<>();
        for (int i = 0; i < 100; i++) {
            testHashTable.put("testKey" + i, "testValue" + i);
        }
        assertEquals(testHashTable.size(), Integer.valueOf(100));
    }

    @Test
    public void testUpdateValue() {
        hashTable.put("testKey1", "testValue1");
        hashTable.put("testKey1", "testValue2");
        assertEquals(hashTable.get("testKey1"), "testValue2");
    }

    @Test
    public void testMissingKey() {
        assertNull(hashTable.get("NONKEY"));
    }
}