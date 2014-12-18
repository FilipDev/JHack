package me.pauzen.jhack.objects.unsafe;

import me.pauzen.jhack.classes.Classes;
import me.pauzen.jhack.misc.Pointer;
import me.pauzen.jhack.objects.Objects;
import me.pauzen.jhack.unsafe.UnsafeProvider;
import sun.misc.Unsafe;

public class ObjectMemoryModifier<T> implements MemoryModifier {

    private static final Unsafe unsafe = UnsafeProvider.getUnsafe();

    private T value;

    protected ObjectMemoryModifier(T value) {
        this.value = value;
    }

    public Object getObject() {
        return value;
    }

    public void setObject(Object value) {
        Objects.writeObject(this.value, Objects.readObject(value));
    }

    @Override
    public void putInt(long offset, int value) {
        unsafe.putInt(this.value, offset, value);
    }

    @Override
    public int getInt(long offset) {
        return unsafe.getInt(this.value, offset);
    }

    @Override
    public void putLong(long offset, long value) {
        unsafe.putLong(this.value, offset, value);
    }

    @Override
    public byte getByte(long offset) {
        return unsafe.getByte(this.value, offset);
    }

    @Override
    public void putByte(long offset, byte value) {
        unsafe.putByte(this.value, offset, value);
    }

    @Override
    public long getLong(long offset) {
        return unsafe.getLong(this.value, offset);
    }

    public long toAddress() {
        return Addresses.shiftIfCompressedOops(Addresses.normalize(getInt(0L)));
    }

    @Override
    public long getSize() {
        return Classes.getShallowSize(this.value);
    }

    public void setClass(Class clazz) {
        Objects.setClass(this.value, clazz);
    }

    public Object deepClone() {
        return Objects.deepClone(this.value);
    }

    public Pointer toPointer() {
        return new Pointer(this.value);
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
