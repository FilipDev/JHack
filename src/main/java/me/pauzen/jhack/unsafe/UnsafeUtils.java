package me.pauzen.jhack.unsafe;

/*
 * Written by FilipDev on 12/24/14 12:19 AM.
 */

public final class UnsafeUtils {

    /**
     * Prevents instantiation.
     */
    private UnsafeUtils() {
    }

    /**
     * Throws an exception that does not need to be caught.
     *
     * @param throwable An Exception type that can be thrown.
     */
    public static void throwException(Throwable throwable) {
        UnsafeProvider.getUnsafe().throwException(throwable);
    }

}
