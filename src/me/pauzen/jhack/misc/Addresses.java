package me.pauzen.jhack.misc;

import me.pauzen.jhack.hotspot.HotSpotDiagnostic;
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
        if (HotSpotDiagnostic.getInstance().useCompressedOops())
            return shiftOOPs(value);
        else return value;
    }

    private static long shiftOOPs(long value) {
        return UnsafeProvider.getUnsafe().getAddress((value << 3));
    }

}
