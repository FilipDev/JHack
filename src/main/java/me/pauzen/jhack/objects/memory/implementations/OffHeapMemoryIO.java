package me.pauzen.jhack.objects.memory.implementations;

import me.pauzen.jhack.objects.Objects;
import me.pauzen.jhack.objects.memory.MemoryIOWriterPrinter;
import me.pauzen.jhack.objects.memory.implementations.factory.MemoryIOFactory;
import me.pauzen.jhack.objects.memory.utils.Address;
import me.pauzen.jhack.unsafe.UnsafeProvider;
import sun.misc.Unsafe;

/*
 * Written by FilipDev on 12/24/14 12:19 AM.
 */

public class OffHeapMemoryIO<T> extends MemoryIOWriterPrinter<T> {

    private final Unsafe unsafe = UnsafeProvider.getUnsafe();
    private long address;
    private int size;

    public OffHeapMemoryIO(int size) {
        this.size = size;
        this.address = unsafe.allocateMemory(size);
    }

    public T createObject(long offset) {
        return (T) Objects.toObject(Address.deshiftOOPs(address + offset));
    }

    public long getAddress() {
        return address;
    }

    @Override
    public void put(long offset, int value) {
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
    public void put(long offset, short value) {
        unsafe.putShort(address + offset, value);
    }

    @Override
    public void put(long offset, long value) {
        unsafe.putLong(address + offset, value);
    }

    @Override
    public byte getByte(long offset) {
        return unsafe.getByte(address + offset);
    }

    @Override
    public void put(long offset, byte value) {
        unsafe.putByte(address + offset, value);
    }

    @Override
    public long getLong(long offset) {
        return unsafe.getLong(address + offset);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Object get(long offset) {
        return Objects.toObject(unsafe.getInt(address + offset));
    }

    @Override
    public void put(long offset, Object object) {
        ObjectMemoryIO objectMemoryIO = MemoryIOFactory.newObjectModifier(object);
        int[] ints = objectMemoryIO.readIntegers(0, objectMemoryIO.getSize());
        write(ints, 0, ints.length);
    }

    public long nextAvailableOffset() {
        int currOffset = getSize();
        while ((unsafe.getInt(address + currOffset--)) == 0) ;
        return currOffset;
    }

    private boolean checkAvailable(long startOffset, long endOffset) {
        boolean available = true;
        for (long offset = startOffset; offset <= endOffset; offset++)
            if (unsafe.getInt(address + offset) != 0) available = false;
        return available;
    }
}
