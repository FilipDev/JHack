package me.pauzen.jhack.misc;

/*
 * Written by FilipDev on 12/24/14 12:19 AM.
 */

/*
 * Written by FilipDev on 12/23/14 8:55 PM.
 */

import java.util.HashMap;
import java.util.Map;

public final class Primitives {

    private Primitives() {
    }

    private static Map<Class, Class> unboxedPrimitives = new HashMap<>();

    static {
        unboxedPrimitives.put(Integer.class, int.class);
        unboxedPrimitives.put(Long.class, long.class);
        unboxedPrimitives.put(Double.class, double.class);
        unboxedPrimitives.put(Float.class, float.class);
        unboxedPrimitives.put(Boolean.class, boolean.class);
        unboxedPrimitives.put(Byte.class, byte.class);
        unboxedPrimitives.put(Short.class, short.class);
        unboxedPrimitives.put(Character.class, char.class);
        unboxedPrimitives.put(Void.class, void.class);
    }

    /**
     * Unboxes boxed primitive type.
     *
     * @param clazz Boxed primitive type class to unbox.
     * @return Unboxed primitive type.
     */
    public static Class unbox(Class clazz) {
        return unboxedPrimitives.get(clazz);
    }

    /**
     * Unboxes Integer value.
     *
     * @param integerVal Integer value to unbox.
     * @return Unboxed primitive int type.
     */
    public static int unboxInteger(Object integerVal) {
        return ((Integer) integerVal).intValue();
    }

    /**
     * Unboxes Long value.
     *
     * @param longVal Long value to unbox.
     * @return Unboxed primitive long type.
     */
    public static long unboxLong(Object longVal) {
        return ((Long) longVal).longValue();
    }

    /**
     * Unboxes Boolean value.
     *
     * @param booleanVal Boolean value to unbox.
     * @return Unboxed primitive boolean type.
     */
    public static boolean unboxBoolean(Object booleanVal) {
        return ((Boolean) booleanVal).booleanValue();
    }

}
