package me.pauzen.jhack.objects.memory.implementations.factory;

import me.pauzen.jhack.classes.Classes;
import me.pauzen.jhack.objects.memory.implementations.ClassMemoryIO;
import me.pauzen.jhack.objects.memory.implementations.ObjectMemoryIO;
import me.pauzen.jhack.objects.memory.implementations.OffHeapMemoryIO;

/*
 * Written by FilipDev on 12/24/14 12:19 AM.
 */

public final class MemoryIOFactory {

    private MemoryIOFactory() {
    }

    /**
     * Creates an ObjectMemoryIO object.
     *
     * @param object Object to create ObjectMemoryIO for.
     * @param <T> Object type.
     * @return ObjectMemoryIO object.
     */
    public static <T> ObjectMemoryIO<T> newObjectModifier(T object) {
        return new ObjectMemoryIO<>(object);
    }

    /**
     * Puts an object off the heap.
     * WARNING: UNFINISHED.
     *
     * @param object Object to put off heap.
     * @param <T> Object type.
     * @return Offheap object.
     */
    public static <T> OffHeapMemoryIO<T> storeOffHeap(T object) {
        OffHeapMemoryIO<T> offHeapMemoryIO = new OffHeapMemoryIO<>(Classes.getDeepSize(object));
        int[] ints = MemoryIOFactory.newObjectModifier(object).readIntegers(0, Classes.getSize(object));
        offHeapMemoryIO.write(ints, 0, ints.length);
        return offHeapMemoryIO;
    }

    /**
     * Reads values in the internal klass.
     *
     * @param clazz Class to read from.
     * @param <T> Class type.
     * @return ClassMemoryIO object.
     */
    public static <T> ClassMemoryIO<T> readClass(Class<T> clazz) {
        return new ClassMemoryIO<>(clazz);
    }
}
