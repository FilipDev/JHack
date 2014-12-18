package me.pauzen.jhack.objects.unsafe;

public interface MemoryModifier<T> {

    public int getInt(long offset);

    public void putInt(long offset, int value);

    public long getLong(long offset);

    public void putLong(long offset, long value);

    public byte getByte(long offset);

    public void putByte(long offset, byte value);

    public long getSize();

    public Object get(long offset);

    public void put(long offset, Object object);

}
