package me.pauzen.jhack.objects.memory.utils;

import me.pauzen.jhack.misc.CurrentSystem;
import me.pauzen.jhack.unsafe.UnsafeProvider;

public final class Addresses {

    public static long normalize(int value) {
        if (value >= 0) return value;
        return (~0L >>> 32) & value;
    }

    public static int denormalize(long value) {
        return (int) value;
    }

    public static long shiftIfCompressedOops(long value) {
        if (CurrentSystem.Architecture.is64()) return shiftOOPs(value);
        else return value;
    }

    private static long shiftOOPs(long value) {
        return (value << 3);
    }

    private static long deshiftOOPs(long value) {
        return (value >> 3);
    }

    public static long toAddress(int id) {
        return toAddress(normalize(id));
    }

    public static long toAddress(long normalized) {
        return shiftIfCompressedOops(normalized);
    }

    /**
     * Gets normalized
     *
     * @param address
     * @return
     */
    public static long fromAddress(long address) {
        return deshiftOOPs(address);
    }

    /**
     * Gets value at an offset of an address.
     *
     * @param address Address.
     * @param offset Offset.
     * @return Value of offset at address.
     */
    public static long getAddressValue(long address, long offset) {
        return UnsafeProvider.getUnsafe().getAddress(address + offset);
    }

    /**
     * Converts long address to a normalized one.
     * For example 32137421640 would be converted to 4017177705.
     *
     * @param longAddress Long address to convert.
     * @return Normalized address.
     */
    public static long longToNormalized(long longAddress) {
        return longAddress >> 3;
    }
}
