package me.pauzen.jhack.misc;

import me.pauzen.jhack.objects.Objects;
import me.pauzen.jhack.objects.memory.implementations.factory.MemoryIOFactory;
import me.pauzen.jhack.objects.memory.implementations.ObjectMemoryIO;
import me.pauzen.jhack.unsafe.UnsafeProvider;
import sun.misc.Unsafe;

/*
 * Written by FilipDev on 12/24/14 12:19 AM.
 */

public class Pointer<T> {

    private static Unsafe unsafe = UnsafeProvider.getUnsafe();

    private final ObjectMemoryIO<T> objectModifer;

    public Pointer(T object) {
        this.objectModifer = MemoryIOFactory.newObjectModifier(object);
    }

    public static void putAddress(long address1, long address2) {
        unsafe.putLong(address1, address2);
    }

    public Object getObject() {
        return this.objectModifer.getObject();
    }

    public void setObject(T object) {
        objectModifer.setObject(object);
    }

    public long getAddress() {
        return Objects.getAddress(getObject());
    }

    public String getAddressString() {
        return Long.toHexString(getAddress());
    }

    @Override
    public String toString() {
        return "{Value: " + objectModifer.getObject() + ", " + "Size: " + objectModifer.getSize() + ", " + "Address: " + getAddress() + "/" + getAddressString() + "}";
    }
}
