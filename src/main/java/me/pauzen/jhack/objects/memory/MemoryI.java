package me.pauzen.jhack.objects.memory;

/*
 * Written by FilipDev on 12/24/14 12:19 AM.
 */

public interface MemoryI<T> {

    /**
     * Gets Object value at offset.
     *
     * @param offset Offset to get value at.
     * @return Object value.
     */
    public Object get(long offset);

    /**
     * Gets byte value at offset.
     *
     * @param offset Offset to get value at.
     * @return Byte value.
     */
    public byte getByte(long offset);

    /**
     * Gets short value at offset.
     *
     * @param offset Offset to get value at.
     * @return Short value.
     */
    public short getShort(long offset);

    /**
     * Gets long value at offset.
     *
     * @param offset Offset to get value at.
     * @return Long value.
     */
    public long getLong(long offset);

    /**
     * Gets int value at offset.
     *
     * @param offset Offset to get value at.
     * @return Integer value.
     */
    public int getInt(long offset);

    /**
     * Gets size.
     *
     * @return Size.
     */
    public int getSize();

    public void printInternalIntegers();

    public void printInternalBytes();

    public void printInternalShorts();

    public void printInternalLongs();

    public void printInternalIntegers(long endOffset);

    public void printInternalBytes(long endOffset);

    public void printInternalShorts(long endOffset);

    public void printInternalLongs(long endOffset);

    public int[] readIntegers(long offset, int amount);

    public byte[] readBytes(long offset, int amount);

    public long[] readLongs(long offset, int amount);

    public short[] readShorts(long offset, int amount);
}
