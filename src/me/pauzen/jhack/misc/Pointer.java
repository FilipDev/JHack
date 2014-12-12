package me.pauzen.jhack.misc;

import me.pauzen.jhack.objects.Objects;
import me.pauzen.jhack.objects.unsafe.ObjectMemoryModifier;
import me.pauzen.jhack.objects.unsafe.ObjectMemoryModifierFactory;
import me.pauzen.jhack.unsafe.UnsafeProvider;
import sun.misc.Unsafe;

public class Pointer<T> {

    private static Unsafe unsafe = UnsafeProvider.getUnsafe();

    private final ObjectMemoryModifier<T> objectModifer;

    public Pointer(Object object) {
        this.objectModifer = ObjectMemoryModifierFactory.newModifier(object);
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
