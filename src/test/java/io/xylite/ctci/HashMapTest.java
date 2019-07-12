package io.xylite.ctci;

import org.junit.Test;

public class HashMapTest {
    @Test
    public void put() {
        String key = "test";
        String value = "test";
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(key, value);
        assert(hashMap.containsKey(key));
        assert(hashMap.containsValue(value));
    }

    @Test
    public void size() {
        String key = "test";
        String value = "test";
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(key, value);
        assert(hashMap.size() == 1);
    }

    @Test
    public void containsKey() {
        String key = "test";
        String value = "test";
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(key, value);
        assert(hashMap.containsKey(key));
    }

    @Test
    public void containsValue() {
        String key = "test";
        String value = "test";
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(key, value);
        assert(hashMap.containsValue(value));
    }
}