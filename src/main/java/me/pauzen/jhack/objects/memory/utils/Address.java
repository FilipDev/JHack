package me.pauzen.jhack.objects.memory.utils;

import me.pauzen.jhack.hotspot.HotSpotDiagnostic;
import me.pauzen.jhack.misc.CurrentSystem;
import me.pauzen.jhack.objects.Objects;
import me.pauzen.jhack.unsafe.UnsafeProvider;

/*
 * Written by FilipDev on 12/24/14 12:24 AM.
 */


public class Address {

    private Object object;

    public Address(Object object) {
        this.object = object;
    }

    public long getAddress() {
        return shiftOOPs(getNormalized());
    }

    public static long normalize(int value) {
        if (value >= 0) return value;
        return (~0L >>> 32) & value;
    }

    public static long shiftOOPs(long normalized) {
        if (CurrentSystem.Architecture.is64() && !HotSpotDiagnostic.getInstance().usingJava8()) return normalized << 3;
        else return normalized;
    }

    public long getNormalized() {
        return normalize(Objects.toIntID(object));
    }

    public static long deshiftOOPs(long address) {
        if (CurrentSystem.Architecture.is64() && !HotSpotDiagnostic.getInstance().usingJava8()) return address >> 3;
        else return address;
    }

    /**
     * Gets value at an offset of an address.
     *
     * @param offset Offset.
     * @return Value of offset at address.
     */
    public long getValue(long offset) {
        return getValue(getAddress(), offset);
    }

    public static long getValue(long address, long offset) {
        return UnsafeProvider.getUnsafe().getAddress(address + offset);
    }
}
