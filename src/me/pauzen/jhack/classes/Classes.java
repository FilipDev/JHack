package me.pauzen.jhack.classes;

import me.pauzen.jhack.hotspot.HotSpotDiagnostic;
import me.pauzen.jhack.misc.Addresses;
import me.pauzen.jhack.objects.Objects;
import me.pauzen.jhack.reflection.ReflectionFactory;
import me.pauzen.jhack.unsafe.UnsafeProvider;
import sun.misc.SharedSecrets;
import sun.misc.Unsafe;
import sun.reflect.ConstantPool;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class Classes {

    private static Unsafe           unsafe        = UnsafeProvider.getUnsafe();
    private static Map<Class, Long> SIZED_CLASSES = new HashMap<>();

    private Classes() {
    }

    /**
     * Returns the ConstantPool for a class.
     *
     * @param clazz The class to get the Constant Pool of.
     * @return The retrieved Constant Pool instance.
     */
    public static ConstantPool getConstantPool(Class clazz) {
        return SharedSecrets.getJavaLangAccess().getConstantPool(clazz);
    }

    /**
     * Gets String at an index in the CP.
     *
     * @param constantPool The Constant Pool to retrieve the String from.
     * @param index        The index at which the String is located in the CP.
     * @return The String at the location in the CP.
     */
    public static String getString(ConstantPool constantPool, int index) {
        return constantPool.getStringAt(index);
    }

    /**
     * Gets String at an index in the CP.
     *
     * @param clazz The class to get the Constant Pool of.
     * @param index The index at which the String is located in the CP.
     * @return The String at the location in the CP.
     */
    public static String getString(Class clazz, int index) {
        return getString(getConstantPool(clazz), index);
    }

    /**
     * Gets the CP location of a String.
     *
     * @param constantPool The Constant Pool the String is located in.
     * @param string       The String to get the index of in the Constant Pool.
     * @return The found index. -1 if the String is not found.
     */
    public static int getStringIndex(ConstantPool constantPool, String string) {
        for (int index = 0; index <= constantPool.getSize(); index++)
            try {
                if (constantPool.getStringAt(index).equals(string))
                    return index;
            } catch (IllegalArgumentException ignored) {
            }
        return -1;
    }

    /**
     * Get the CP location of a String.
     *
     * @param clazz  The class to get the Constant Pool of.
     * @param string The String to get the index of in the Constant Pool.
     * @return The found index. -1 if the String is not found.
     */
    public static int getStringIndex(Class clazz, String string) {
        return getStringIndex(getConstantPool(clazz), string);
    }

    /**
     * Returns a Map of all of the Strings in the CP.
     *
     * @param constantPool The Constant Pool to retrieve the Strings from.
     * @return A Map of the Strings.
     */
    public static Map<Integer, String> getStrings(ConstantPool constantPool) {
        Map<Integer, String> strings = new HashMap<>();
        for (int index = 0; index <= constantPool.getSize(); index++)
            try {
                strings.put(index, constantPool.getStringAt(index));
            } catch (IllegalArgumentException ignored) {
            }
        return strings;
    }

    /**
     * Returns a Map of all of the Strings in the Class's CP.
     *
     * @param clazz The class to get the Constant Pool of.
     * @return A map of the Strings.
     */
    public static Map<Integer, String> getStrings(Class clazz) {
        return getStrings(getConstantPool(clazz));
    }

    /**
     * Gets the shallow size of the Class.
     *
     * @param clazz Class to get the shallow size of.
     * @return The size of the Object.
     */
    public static long getShallowSize(Class clazz) {
        if (SIZED_CLASSES.containsKey(clazz))
            return SIZED_CLASSES.get(clazz);
        Set<Field> fields = new HashSet<>();
        for (Field field : ReflectionFactory.getFieldsHierarchic(clazz))
            if (!Modifier.isStatic(field.getModifiers())) fields.add(field);

        System.out.println(clazz);
        long size = 0;
        for (Field field : fields) {
            long offset = unsafe.objectFieldOffset(field);
            size = Math.max(size, offset);
            System.out.println(size + " " + field);
        }

        size = ((size >> 2) + 1) << 2; // ADDS PADDING
        SIZED_CLASSES.put(clazz, size);
        return size;
    }

    public static Class toClass(int address) {
        Object object = new Object();
        Objects.setClass(object, address);
        return object.getClass();
    }

    public static long getSuperAddress(Object object) {
        System.out.println(Classes.getInternalClassValue(object.getClass().getSuperclass()));
        System.out.println("-------");
        System.out.println(Classes.getInternalClassValue(Singleton.class));
        System.out.println("-------");
        //System.out.println(Objects.fromAddress(4017094853L));
        //System.out.println((int) UnsafeProvider.getUnsafe().getAddress(Addresses.shiftIfCompressedOops(Objects.getAddress(object) + 72)));
        for (int i = -32000; i < 32000; i += 4) {
            System.out.println(i + " " + Addresses.normalize((int) UnsafeProvider.getUnsafe().getAddress(Addresses.shiftIfCompressedOops(Objects.getAddress(object) + i))));
        }
        return 0;
    }

    /**
     * Get shallow size of Object.
     *
     * @param object Object to get the shallow size of.
     * @return The shallow size of the Object.
     */
    public static long getShallowSize(Object object) {
        return getShallowSize(object.getClass());
    }

    /**
     * Gets internal Object class value.
     *
     * @param clazz Class to get the internal value of.
     * @return The internal value.
     */ //160L OFFSET FOR NON COMPRESSED OOPS
    public static long getInternalClassValue(Class clazz) {
        return Addresses.normalize(unsafe.getInt(clazz, HotSpotDiagnostic.isArchitecture32() ? 64L : 84L));
    }

    /**
     * Gets internal Object class value.
     *
     * @param object Object to get the internal class value of.
     * @return The internal class value.
     */
    public static int getInternalClassValue(Object object) {
        return unsafe.getInt(object, unsafe.addressSize());
    }

}
