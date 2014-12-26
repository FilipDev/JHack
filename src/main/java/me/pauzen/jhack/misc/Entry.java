package me.pauzen.jhack.misc;

import java.util.AbstractMap;
import java.util.Map;

/*
 * Written by FilipDev on 12/24/14 12:19 AM.
 */

public class Entry<K, V> extends AbstractMap.SimpleEntry<K, V> {

    /**
     * Simple AbstractMap.SimpleEntry wrapper.
     */

    public Entry(K key, V value) {
        super(key, value);
    }

    public Entry(Map.Entry<K, V> entry) {
        super(entry);
    }

    public static me.pauzen.jhack.misc.Entry<String, String> fromString(String string) {
        String[] values = string.split("=");
        return new me.pauzen.jhack.misc.Entry<>(values[0], values[1]);
    }

    /**
     *
     *
     * @param map Map to insert entry into.
     * @param entry Entry object to insert into map.
     * @param <K> Key.
     * @param <V> Value.
     */
    public static <K, V> void insert(Map<K, V> map, me.pauzen.jhack.misc.Entry<K, V> entry) {
        map.put(entry.getKey(), entry.getValue());
    }

    @Override
    public String toString() {
        return getKey() + "=" + getValue();
    }
}
