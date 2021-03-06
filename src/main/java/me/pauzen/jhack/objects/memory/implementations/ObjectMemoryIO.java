package me.pauzen.jhack.objects.memory.implementations;

import me.pauzen.jhack.classes.Classes;
import me.pauzen.jhack.misc.Pointer;
import me.pauzen.jhack.objects.Objects;
import me.pauzen.jhack.objects.memory.MemoryIOWriterPrinter;
import me.pauzen.jhack.unsafe.UnsafeProvider;
import sun.misc.Unsafe;

/*
 * Written by FilipDev on 12/24/14 12:19 AM.
 */

public class ObjectMemoryIO<T> extends MemoryIOWriterPrinter<T> {

    private static final Unsafe unsafe = UnsafeProvider.getUnsafe();

    private T value;

    public ObjectMemoryIO(T value) {
        this.value = value;
    }

    public Object getObject() {
        return value;
    }

    public void setObject(Object value) {
        Objects.writeObject(this.value, Objects.readObject(value));
    }

    @Override
    public void put(long offset, int value) {
        unsafe.putInt(this.value, offset, value);
    }

    @Override
    public void put(long offset, long value) {
        unsafe.putLong(this.value, offset, value);
    }

    @Override
    public void put(long offset, byte value) {
        unsafe.putByte(this.value, offset, value);
    }

    @Override
    public void put(long offset, short value) {
        unsafe.putShort(this.value, offset, value);
    }

    @Override
    public int getInt(long offset) {
        return unsafe.getInt(this.value, offset);
    }

    @Override
    public byte getByte(long offset) {
        return unsafe.getByte(this.value, offset);
    }

    @Override
    public long getLong(long offset) {
        return unsafe.getLong(this.value, offset);
    }

    @Override
    public short getShort(long offset) {
        return unsafe.getShort(this.value, offset);
    }

    @Override
    public int getSize() {
        return Classes.getSize(this.value);
    }

    public void setClass(Class clazz) {
        Objects.setClass(this.value, clazz);
    }

    public Object deepClone() {
        return Objects.deepClone(this.value);
    }

    public Pointer toPointer() {
        return new Pointer<>(this.value);
    }

    @Override
    public Object get(long offset) {
        return unsafe.getObject(this.value, offset);
    }

    @Override
    public void put(long offset, Object object) {
        unsafe.putObject(this.value, offset, object);
    }
}
