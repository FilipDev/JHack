package me.pauzen.jhack.objects.memory.implementations;

import me.pauzen.jhack.objects.Objects;
import me.pauzen.jhack.objects.memory.MemoryModifier;
import me.pauzen.jhack.objects.memory.MemoryPrinter;
import me.pauzen.jhack.objects.memory.MemoryReader;
import me.pauzen.jhack.unsafe.UnsafeProvider;
import sun.misc.Unsafe;

public class MemoryModificationStore<T> extends MemoryPrinter implements MemoryModifier, MemoryReader {

    private final Unsafe unsafe = UnsafeProvider.getUnsafe();
    private long address;
    private long size;

    public MemoryModificationStore(long size) {
        this.size = size;
        this.address = unsafe.allocateMemory(size);
        System.out.println(this.address);
    }

    public T createObject(Class<T> type) {
        T object = Objects.createObject(type, null);
        Objects.writeObject(object, readBytes());
        return object;
    }

    @Override
    public void putInt(long offset, int value) {
        unsafe.putInt(address + offset, value);
    }

    @Override
    public int getInt(long offset) {
        return unsafe.getInt(address + offset);
    }

    @Override
    public short getShort(long offset) {
        return unsafe.getShort(address + offset);
    }

    @Override
    public void putShort(long offset, short value) {
        unsafe.putShort(address + offset, value);
    }

    @Override
    public void putLong(long offset, long value) {
        unsafe.putLong(address + offset, value);
    }

    @Override
    public byte getByte(long offset) {
        return unsafe.getByte(address + offset);
    }

    @Override
    public void putByte(long offset, byte value) {
        unsafe.putByte(address + offset, value);
    }

    @Override
    public long getLong(long offset) {
        return unsafe.getLong(address + offset);
    }

    @Override
    public long getSize() {
        return size;
    }

    @Override
    public Object get(long offset) {
        return Objects.toObject(unsafe.getInt(address + offset));
    }

    @Override
    public void put(long offset, Object object) {
        unsafe.putInt(address + offset, Objects.toIntID(object));
    }

    public void writeBytes(byte[] bytes, long offset, long amount) {
        for (int i = 0; i < amount; i++) unsafe.putByte(address + offset + i, bytes[i]);
    }

    public byte[] readBytes() {
        byte[] bytes = new byte[(int) size];
        for (int i = 0; i < bytes.length; i++) bytes[i] = getByte(i);
        return bytes;
    }

    public long nextAvailableOffset() {
        int currOffset = 0;
        while ((unsafe.getInt(address + currOffset++)) != 0) ;
        return currOffset;
    }

    private boolean checkAvailable(long startOffset, long endOffset) {
        boolean available = true;
        for (long offset = startOffset; offset <= endOffset; offset++)
            if (unsafe.getInt(address + offset) != 0) available = false;
        return available;
    }
}
