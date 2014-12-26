package me.pauzen.jhack.objects.memory;

/*
 * Written by FilipDev on 12/24/14 3:36 PM.
 */

import me.pauzen.jhack.classes.Classes;

public abstract class MemoryOWriter<T> implements MemoryO<T> {

    public void write(byte[] bytes, long offset, long amount) {
        for (int i = 0; i < amount; i++) put(offset + i, bytes[i]);
    }

    public void write(int[] ints, long offset, long amount) {
        for (int i = 0; i < amount; i += 4) put(offset + i, ints[i]);
    }

    public void write(long[] longs, long offset, long amount) {
        for (int i = 0; i < amount; i += 8) put(offset + i, longs[i]);
    }

    public void write(short[] shorts, long offset, long amount) {
        for (int i = 0; i < amount; i += 2) put(offset + i, shorts[i]);
    }

    public void write(Object[] objects, long offset) {

        long lastOffset = offset;

        for (Object object : objects) {
            long size = Classes.getSize(object);
            put(lastOffset, object);
            lastOffset += size;
        }
    }

}
