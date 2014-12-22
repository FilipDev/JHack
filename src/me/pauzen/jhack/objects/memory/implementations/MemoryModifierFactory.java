package me.pauzen.jhack.objects.memory.implementations;

import me.pauzen.jhack.classes.Classes;
import me.pauzen.jhack.objects.Objects;

public final class MemoryModifierFactory {

    private MemoryModifierFactory() {
    }

    public static <T> ObjectMemoryModifier<T> newModifier(T object) {
        return new ObjectMemoryModifier<>(object);
    }

    public static <T> MemoryModificationStore putOffHeap(T object) {
        MemoryModificationStore<T> memoryModificationStore = new MemoryModificationStore<>(Classes.getShallowSize(object));

        byte[] bytes = Objects.readObject(object);
        memoryModificationStore.writeBytes(bytes, 0, bytes.length);

        return memoryModificationStore;
    }

    public static <T> ClassMemoryModifier read(Class<T> clazz) {
        return new ClassMemoryModifier<>(clazz);
    }
}
