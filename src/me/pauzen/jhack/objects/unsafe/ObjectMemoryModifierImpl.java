package me.pauzen.jhack.objects.unsafe;

import me.pauzen.jhack.classes.Classes;
import me.pauzen.jhack.misc.Address;
import me.pauzen.jhack.misc.Pointer;
import me.pauzen.jhack.objects.Objects;
import me.pauzen.jhack.unsafe.UnsafeProvider;
import sun.misc.Unsafe;

class ObjectMemoryModifierImpl<T> implements ObjectMemoryModifier {

    private static final Unsafe unsafe = UnsafeProvider.getUnsafe();

    private T value;

    public ObjectMemoryModifierImpl(T value) {
        this.value = value;
    }

    @Override
    public Object getObject() {
        return value;
    }

    @Override
    public void setObject(Object value) {
        this.value = (T) value;
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
    public long getLong(long offset) {
        return unsafe.getLong(this.value, offset);
    }

    @Override
    public long toAddress() {
        return Address.shiftIfCompressedOops(Address.normalize(getInt(0L)));
    }

    @Override
    public long getSize() {
        return Classes.getShallowSize(this.value);
    }

    @Override
    public void setClass(Class clazz) {
        Objects.setClass(this.value, clazz);
    }

    @Override
    public Object deepClone() {
        return Objects.deepClone(this.value);
    }

    @Override
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
