package me.pauzen.jhack.objects.memory;

/*
 * Written by FilipDev on 12/24/14 5:29 PM.
 */

import me.pauzen.jhack.classes.Classes;

public abstract class MemoryIOWriterPrinter<T> implements MemoryI<T>, MemoryO<T> {

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

    public void printInternalIntegers() {
        printInternalIntegers(getSize());
    }

    public void printInternalBytes() {
        printInternalBytes(getSize());
    }

    public void printInternalShorts() {
        printInternalShorts(getSize());
    }

    public void printInternalLongs() {
        printInternalLongs(getSize());
    }

    public void printInternalIntegers(long endOffset) {
        for (int i = 0; i < endOffset; i += 4) System.out.println(i + " " + getInt(i));
    }

    public void printInternalBytes(long endOffset) {
        for (int i = 0; i < endOffset; i += 1) System.out.println(i + " " + getByte(i));
    }

    public void printInternalShorts(long endOffset) {
        for (int i = 0; i < endOffset; i += 2) System.out.println(i + " " + getShort(i));
    }

    public void printInternalLongs(long endOffset) {
        for (int i = 0; i < endOffset; i += 8) System.out.println(i + " " + getLong(i));
    }

    @Override
    public byte[] readBytes(long offset, int amount) {
        byte[] bytes = new byte[amount];
        for (int i = 0; i < bytes.length; i++) bytes[i] = getByte(offset + i);
        return bytes;
    }

    @Override
    public int[] readIntegers(long offset, int amount) {
        int[] ints = new int[amount];
        for (int i = 0; i < ints.length; i++) ints[i] = getInt(offset + i);
        return ints;
    }

    @Override
    public long[] readLongs(long offset, int amount) {
        long[] longs = new long[amount];
        for (int i = 0; i < longs.length; i++) longs[i] = getLong(offset + i);
        return longs;
    }

    @Override
    public short[] readShorts(long offset, int amount) {
        short[] shorts = new short[amount];
        for (int i = 0; i < shorts.length; i++) shorts[i] = getShort(offset + i);
        return shorts;
    }
}
