package me.pauzen.jhack.classes;

import me.pauzen.jhack.hotspot.HotSpotDiagnostic;
import me.pauzen.jhack.misc.CurrentSystem;
import me.pauzen.jhack.objects.Objects;
import me.pauzen.jhack.objects.memory.implementations.ClassMemoryIO;
import me.pauzen.jhack.objects.memory.implementations.factory.MemoryIOFactory;
import me.pauzen.jhack.objects.memory.utils.Address;
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

/*
 * Written by FilipDev on 12/24/14 12:19 AM.
 */

public final class Classes {

    private static Unsafe           unsafe        = UnsafeProvider.getUnsafe();
    private static Map<Class, Integer> SIZED_CLASSES = new HashMap<>();

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

    public static String getString(ConstantPool constantPool, String string) {
        return getString(constantPool, getStringIndex(constantPool, string));
    }

    public static String getString(Class clazz, String string) {
        return getString(getConstantPool(clazz), string);
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

        int size = 0;
        for (Field field : fields) {
            int offset = (int) unsafe.objectFieldOffset(field);
            size = Math.max(size, offset);
        }

        size = ((size >> 2) + 1) << 2; // ADDS PADDING
        SIZED_CLASSES.put(clazz, size);
        return size;
    }

    /**
     * Converts int internal class value to a class.
     *
     * @param address Internal classs value.
     * @return Class object.
     */
    public static Class toClass(int address) {
        Object object = new Object();
        Objects.setClass(object, address);
        return object.getClass();
    }

    /**
     * Converts normalized internal class value to a class.
     *
     * @param normalizedInternalClassValue Internal class value to convert to class.
     * @return Class object.
     */
    public static Class toClass(long normalizedInternalClassValue) {
        return toClass((int) normalizedInternalClassValue);
    }

    private static void printAddresses(Object object) {
        printAddresses(object.getClass());
    }

    private static void printAddresses(Class clazz) {
        printAddresses(Address.shiftOOPs(getInternalClassValue(clazz)));
    }

    private static void printAddresses(long address) {
        for (int i = 0; i < 512; i += 4) System.out.println(i + " " + (int) Address.getValue(address, i));
    }

    /**
     * Get shallow size of Object.
     *
     * @param object Object to get the shallow size of.
     * @return The shallow size of the Object.
     */
    @Deprecated
    public static long getShallowSize(Object object) {
        return getShallowSize(object.getClass());
    }

    /**
     * Gets true internal size of an Object.
     *
     * @param object Object to get the size of.
     * @return The true size.
     */
    public static int getSize(Object object) {
        Integer primSize;
        if ((primSize = primitiveSizes.get(object.getClass())) != null) {
            return primSize;
        }
        if (object.getClass().isArray()) return unsafe.arrayIndexScale(object.getClass()) * Objects.getArrayLength(object) + 16;
        return getSize(object.getClass());
    }

    /**
     * Gets deep size of an object (size of it, and all of it's fields's fields)
     * WARNING: SLOW
     *
     * @return Deep size.
     */
    public static int getDeepSize(Object object) {
        int size = getSize(object);
        for (Field field : ReflectionFactory.getFieldsHierarchic(object.getClass())) {
            field.setAccessible(true);
            try {
                Integer primSize;
                if ((primSize = primitiveSizes.get(field.getType())) != null) {
                    size += primSize;
                    continue;
                }

                Object value = field.get(object);
                size += ((value == null || Objects.isSingleton(value) || Objects.isStatic(field)) ? 0 : getSize(value));
            } catch (IllegalAccessException | ClassCastException e) {
                e.printStackTrace();
            }
        }
        return size;
    }

    private static Map<Class, Integer> primitiveSizes = new HashMap<>();

    static {
        primitiveSizes.put(int.class, 4);
        primitiveSizes.put(long.class, 8);
        primitiveSizes.put(short.class, 2);
        primitiveSizes.put(char.class, 2);
        primitiveSizes.put(byte.class, 1);
        primitiveSizes.put(float.class, 4);
        primitiveSizes.put(double.class, 8);
    }

    private static boolean java8 = HotSpotDiagnostic.getInstance().usingJava8();

    /**
     * Gets size of a class. Does not work for arrays.
     *
     * @param clazz Class to get the size of.
     * @return The true size of the object.
     */
    public static int getSize(Class clazz) {
        if (SIZED_CLASSES.containsKey(clazz))
            return SIZED_CLASSES.get(clazz);
        int size;
        if (java8) size = (int) unsafe.getAddress(getInternalClassValue(clazz) + Unsafe.ADDRESS_SIZE);
        else size = (int) unsafe.getAddress(Address.shiftOOPs(getInternalClassValue(clazz)) + Unsafe.ADDRESS_SIZE * 3);
        SIZED_CLASSES.put(clazz, size);
        return size;
    }

    /**
     * Gets internal Object class value.
     *
     * @param clazz Class to get the internal value of.
     * @return The internal value.
     */ //160L OFFSET FOR NON COMPRESSED OOPS
    public static long getInternalClassValue(Class clazz) {
        if (java8) return unsafe.getLong(clazz, 64L);
        return Address.normalize(unsafe.getInt(clazz, CurrentSystem.Architecture.is86() ? 64L : 84L));
    }

    /**
     * Gets internal Object class value.
     *
     * @param object Object to get the internal class value of.
     * @return The internal class value.
     */
    public static long getInternalClassValue(Object object) {
        return Address.normalize(unsafe.getInt(object, unsafe.addressSize()));
    }

    /**
     * Test method, not guaranteed to work on every version of Java.
     *
     * @param clazz Class to set the super class of.
     * @param super1 New super class.
     */
    private static void setSuper(Class clazz, Class super1) {
        ClassMemoryIO memoryReader = MemoryIOFactory.readClass(clazz);
        ClassMemoryIO superMemoryReader = MemoryIOFactory.readClass(super1);
        long lastSuperOffset = getLastSuperOffset(clazz);
        long lastSuperOffset1 = getLastSuperOffset(super1);
        memoryReader.put(lastSuperOffset, memoryReader.getLong(lastSuperOffset - 8));
        memoryReader.put(lastSuperOffset - 8, superMemoryReader.getLong(lastSuperOffset1 - 8));
        memoryReader.put(128, superMemoryReader.getLong(lastSuperOffset1 - 8));
    }

    /**
     * Gets last offset of super classes.
     *
     * @param clazz Class to find the offset of.
     * @return The offset.
     */
    private static long getLastSuperOffset(Class clazz) {
        ClassMemoryIO memoryReader = MemoryIOFactory.readClass(clazz);
        for (int offset = 56; offset < 120; offset += 8) if (memoryReader.getLong(offset) == 0) return offset;
        return 0;
    }

}
