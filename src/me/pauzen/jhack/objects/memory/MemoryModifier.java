package me.pauzen.jhack.objects.memory;

public interface MemoryModifier<T> {

    public void putInt(long offset, int value);

    void putShort(long offset, short value);

    public void putLong(long offset, long value);

    public void putByte(long offset, byte value);

    public void put(long offset, Object object);
}
