package me.pauzen.jhack.objects.memory.implementations;

import me.pauzen.jhack.classes.Classes;
import me.pauzen.jhack.objects.memory.MemoryModifier;
import me.pauzen.jhack.objects.memory.MemoryPrinter;
import me.pauzen.jhack.objects.memory.utils.Addresses;
import me.pauzen.jhack.unsafe.UnsafeProvider;
import sun.misc.Unsafe;

public class ClassMemoryModifier<T> extends MemoryPrinter implements MemoryModifier {

    private final Class<T> clazz;
    private static final Unsafe unsafe = UnsafeProvider.getUnsafe();

    public ClassMemoryModifier(Class<T> clazz) {
        this.clazz = clazz;
    }

    public long getUpdated() {
        return Addresses.toAddress(Classes.getInternalClassValue(clazz));
    }

    @Override
    public int getInt(long offset) {
        return (int) unsafe.getAddress(getUpdated() + offset);
    }

    @Override
    public long getLong(long offset) {
        return unsafe.getAddress(getUpdated() + offset);
    }

    @Override
    public short getShort(long offset) {
        return (short) unsafe.getAddress(getUpdated() + offset);
    }

    @Override
    public void putInt(long offset, int value) {
        unsafe.putInt(getUpdated() + offset, value);
    }

    @Override
    public void putShort(long offset, short value) {
        unsafe.putShort(getUpdated() + offset, value);
    }

    @Override
    public void putLong(long offset, long value) {
        unsafe.putLong(getUpdated() + offset, value);
    }

    @Override
    public void putByte(long offset, byte value) {
        unsafe.putByte(getUpdated() + offset, value);
    }

    @Override
    public void put(long offset, Object object) {
    }

    @Override
    public byte getByte(long offset) {
        return (byte) unsafe.getAddress(getUpdated() + offset);
    }

    @Override
    public long getSize() {
        return 152L;
    }

    @Override
    public Object get(long offset) {
        return null;
    }
}
