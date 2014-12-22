package me.pauzen.jhack.objects.memory;

public interface MemoryReader<T> {

    public Object get(long offset);

    public byte getByte(long offset);

    public short getShort(long offset);

    public long getLong(long offset);

    public int getInt(long offset);

    public long getSize();

    public void printInternalIntegers();

    public void printInternalBytes();

    public void printInternalShorts();

    public void printInternalLongs();

    public void printInternalIntegers(long endOffset);

    public void printInternalBytes(long endOffset);

    public void printInternalShorts(long endOffset);

    public void printInternalLongs(long endOffset);
}
