package io.xylite.ctci.data_structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * A simple HashMap implementation
 * @param <K> the type used for keys
 * @param <V> the type used for values
 */
public class HashTable<K, V> {
    private static final Integer DEFAULT_SIZE = 3;
    private static final Float LOAD_FACTOR = 0.75f;
    private Integer numEntries;
    private LinkedList<Entry<K, V>>[] map;

    /**
     * Constructs an empty HashMap
     */
    HashTable() {
        numEntries = 0;
        map = new LinkedList[DEFAULT_SIZE];
    }

    /**
     * Put a new entry in the HashMap
     * @param key the key to calculate the hash
     * @param value the value use for the entry
     * @return the value that was used
     * @implNote resizes and rehashes the map if this entry exceeds
     * the given load factor
     * @see #resize
     */
    public V put(K key, V value) {
        // Resize map array before we hash the key
        resize();
        Integer index = index(key.hashCode());
        numEntries++;

        // If we already have the key, just update the value
        if (map[index] != null) {
            for (Entry<K, V> entry : map[index]) {
                if (entry.getKey().equals(key)) {
                    entry.setValue(value);
                    return value;
                }
            }
        } else {
            map[index] = new LinkedList<Entry<K, V>>();
        }

        map[index].add(new Entry<>(key, value));
        return value;
    }

    /**
     * Returns the value for a given key
     * @param key the key to find
     * @return the value for a given key
     */
    public V get(K key) {
        Integer index = index(key.hashCode());
        if (map[index] == null) {
            return null;
        }
        for (Entry<K, V> entry : map[index]) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * Get the number of entries in the hash
     * @return the number of entries in the hash
     */
    public Integer size() {
        return numEntries;
    }

    /**
     * Whether or not the hash contains a given key
     * @param key the key to search for
     * @return {@code true} if found; {@code false} otherwise
     */
    public Boolean containsKey(K key) {
        Integer location = index(key.hashCode());
        if (map[location] == null) {
            return false;
        }
        for (Entry<K, V> entry : map[location]) {
            if (entry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Whether or not the hash contains a given key
     * @param value the value to search for
     * @return {@code true} if found; {@code false} otherwise
     */
    public Boolean containsValue(V value) {
        for (LinkedList<Entry<K, V>> entries : map) {
            if (entries == null) {
                continue;
            }
            for (Entry<K, V> entry : entries) {
                if (entry.getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new HashSet<>();
        for (LinkedList<Entry<K, V>> entries : map) {
            set.addAll(entries);
        }
        return set;
    }

    @Override
    public String toString() {
        return "CtciHashMap{" +
                "size=" + map.length +
                ", map=" + Arrays.toString(map) +
                '}';
    }

    /**
     * Resizes the map array to the next Mersenne number then rehashes each key
     */
    private void resize() {
        if (((float) numEntries / map.length) < LOAD_FACTOR) return;

        // We start with a prime, then use the formula 2^n - 1 to generate the next size
        // in order to find Mersenne number
        LinkedList[] newMap = new LinkedList[((map.length + 1) << 1) - 1];
        fullRehash(newMap);
    }

    /**
     * Rehash all entries in the current entry map
     *
     * @param newMap An empty map array
     * @implNote We can't simply copy over the CtciEntrySets to the new location
     *  since the keys might not collide anymore with the larger array size
     */
    private void fullRehash(LinkedList<Entry<K, V>>[] newMap) {
        for (LinkedList<Entry<K, V>> CtciEntrySets : map) {
            if (CtciEntrySets == null) {
                continue;
            }
            for (Entry<K, V> CtciEntrySet : CtciEntrySets) {
                Integer location = index(CtciEntrySet.getKey().hashCode());

                if (newMap[location] == null) {
                    newMap[location] = new LinkedList<Entry<K, V>>();
                }
                newMap[location].add(CtciEntrySet);
            }
        }
        map = newMap;
    }

    /**
     * Find the correct bucket index for a given hashCode
     * @param hashCode The hash code of the key object
     * @return the index of the map array
     */
    private Integer index(Integer hashCode) {
        return hash(hashCode) & (map.length - 1);
    }

    /**
     * Rehash to further spread bad hashCode implementations
     * @param hashCode The hash code of the key object
     * @return a 32 bit Integer hash
     */
    private Integer hash(Integer hashCode) {
        hashCode ^= (hashCode >>> 20) ^ (hashCode >>> 12);
        return hashCode ^ (hashCode >>> 7) ^ (hashCode >>> 4);
    }

    public static class Entry<T, U> {
        private T key;
        private U value;

        Entry(T key, U value) {
            this.key = key;
            this.value = value;
        }

        public T getKey() {
            return key;
        }

        public void setKey(T key) {
            this.key = key;
        }

        public U getValue() {
            return value;
        }

        public void setValue(U value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}
