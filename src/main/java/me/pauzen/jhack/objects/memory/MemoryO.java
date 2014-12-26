package me.pauzen.jhack.objects.memory;

/*
 * Written by FilipDev on 12/24/14 12:19 AM.
 */

public interface MemoryO<T> {

    /**
     * Puts an int value at an offset.
     *
     * @param offset Offset to put int at.
     * @param value Integer value to put at offset.
     */
    public void put(long offset, int value);

    /**
     * Puts a short value at an offset.
     *
     * @param offset Offset to put short at.
     * @param value Short value to put at offset.
     */
    public void put(long offset, short value);

    /**
     * Puts a long value at an offset.
     *
     * @param offset Offset to put long at.
     * @param value Long value to put at offset.
     */
    public void put(long offset, long value);

    /**
     * Puts a byte value at an offset.
     *
     * @param offset Offset to put byte at.
     * @param value Byte value to put at offset.
     */
    public void put(long offset, byte value);

    /**
     * Puts an object at a specific offset.
     *
     * @param offset Start offset to put object at.
     * @param object Object to put.
     */
    public void put(long offset, Object object);

    /**
     * Writes array of bytes at a specified offset.
     *
     * @param bytes Byte array to put.
     * @param offset Offset to start writing array at.
     * @param amount Amount of array values to put.
     */
    public void write(byte[] bytes, long offset, long amount);

    /**
     * Writes array of ints at a specified offset.
     *
     * @param ints Integer array to put.
     * @param offset Offset to start writing array at.
     * @param amount Amount of array values to put.
     */
    public void write(int[] ints, long offset, long amount);

    /**
     * Writes array of longs at a specified offset.
     *
     * @param longs Long array to put.
     * @param offset Offset to start writing array at.
     * @param amount Amount of array values to put.
     */
    public void write(long[] longs, long offset, long amount);

    /**
     * Writes array of bytes at a specified offset.
     *
     * @param shorts Short array to put.
     * @param offset Offset to start writing array at.
     * @param amount Amount of array values to put.
     */
    public void write(short[] shorts, long offset, long amount);

    /**
     * Writes array of objects at a specified offset.
     *
     * @param objects Object array to put.
     * @param offset Offset to start writing array at.
     */
    public void write(Object[] objects, long offset);
}
