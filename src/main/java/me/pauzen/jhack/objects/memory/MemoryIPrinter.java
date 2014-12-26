package me.pauzen.jhack.objects.memory;

/*
 * Written by FilipDev on 12/24/14 12:19 AM.
 */

public abstract class MemoryIPrinter<T> implements MemoryI<T> {

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
