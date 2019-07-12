package io.xylite.ctci;

import java.util.Arrays;
import java.util.LinkedList;

public class CtciHashMap<K, V> {
    private static final Integer DEFAULT_SIZE = 3;
    private static final Float LOAD_FACTOR = 0.75f;
    private Integer numEntries;
    private LinkedList<CtciEntrySet<K, V>>[] map;

    CtciHashMap() {
        numEntries = 0;
        map = new LinkedList[DEFAULT_SIZE];
    }

    public V put(K key, V value) {
        // Make sure we stay under LOAD_FACTOR
        numEntries++;
        resize();

        Integer location = index(key.hashCode());

        if (map[location] == null) {
            map[location] = new LinkedList<CtciEntrySet<K, V>>();
        }
        map[location].add(new CtciEntrySet<>(key, value));
        return value;
    }

    public Integer size() {
        return map.length;
    }

    public Boolean containsKey(K key) {
        Integer location = index(key.hashCode());
        if (map[location] == null) {
            return false;
        }
        for (CtciEntrySet<K, V> entrySet : map[location]) {
            if (entrySet.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public Boolean containsValue(V value) {
        for (LinkedList<CtciEntrySet<K, V>> entrySets : map) {
            if (entrySets == null) {
                continue;
            }
            for (CtciEntrySet<K, V> entrySet : entrySets) {
                if (entrySet.getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
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
    private void fullRehash(LinkedList<CtciEntrySet<K, V>>[] newMap) {
        for (LinkedList<CtciEntrySet<K, V>> CtciEntrySets : map) {
            if (CtciEntrySets == null) {
                continue;
            }
            for (CtciEntrySet<K, V> CtciEntrySet : CtciEntrySets) {
                Integer location = index(CtciEntrySet.getKey().hashCode());

                if (newMap[location] == null) {
                    newMap[location] = new LinkedList<CtciEntrySet<K, V>>();
                }
                newMap[location].add(CtciEntrySet);
            }
        }
        map = newMap;
    }

    private Integer index(Integer hashCode) {
        return hash(hashCode) & (map.length - 1);
    }

    private Integer hash(Integer hashCode) {
        hashCode ^= (hashCode >>> 20) ^ (hashCode >>> 12);
        return hashCode ^ (hashCode >>> 7) ^ (hashCode >>> 4);
    }

    public static void main(String[] args) {
        CtciHashMap<String, String> a = new CtciHashMap<>();
        System.out.println(Integer.toString(23).hashCode());
        System.out.println("23".hashCode());
        a.put(Integer.toString(23), "23");
        System.out.println(a);
        System.out.println(a.containsKey("23"));
        System.out.println(a.containsValue("23"));
    }
}
