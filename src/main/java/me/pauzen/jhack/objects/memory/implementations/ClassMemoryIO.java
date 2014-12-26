package me.pauzen.jhack.objects.memory.implementations;

import me.pauzen.jhack.classes.Classes;
import me.pauzen.jhack.objects.memory.MemoryIOWriterPrinter;
import me.pauzen.jhack.objects.memory.utils.Address;
import me.pauzen.jhack.unsafe.UnsafeProvider;
import sun.misc.Unsafe;

/*
 * Written by FilipDev on 12/24/14 12:19 AM.
 */

public class ClassMemoryIO<T> extends MemoryIOWriterPrinter<T> {

    private final Class<T> clazz;
    private static final Unsafe unsafe = UnsafeProvider.getUnsafe();

    public ClassMemoryIO(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * Gets updated address.
     *
     * @return Address.
     */
    public long getUpdated() {
        return Address.shiftOOPs(Classes.getInternalClassValue(clazz));
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
    public void put(long offset, int value) {
        unsafe.putInt(getUpdated() + offset, value);
    }

    @Override
    public void put(long offset, short value) {
        unsafe.putShort(getUpdated() + offset, value);
    }

    @Override
    public void put(long offset, long value) {
        unsafe.putLong(getUpdated() + offset, value);
    }

    @Override
    public void put(long offset, byte value) {
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
    public int getSize() {
        return 152;
    }

    @Override
    public Object get(long offset) {
        return null;
    }
}
