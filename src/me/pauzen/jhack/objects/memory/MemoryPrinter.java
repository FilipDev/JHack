package me.pauzen.jhack.objects.memory;

public abstract class MemoryPrinter implements MemoryReader {

    public void printInternalIntegers() {
        printInternalIntegers(getSize());    }

    public void printInternalBytes() {
        printInternalBytes(getSize());    }

    public void printInternalShorts() {
        printInternalShorts(getSize());
    }

    public void printInternalLongs() {
        printInternalLongs(getSize());
    }

    public void printInternalIntegers(long endOffset) {
        for (int i = 0; i < endOffset; i += 4) System.out.println(i + " " + getInt(i));
    }

    public void printInternalBytes(long endOffset) {
        for (int i = 0; i < endOffset; i += 1) System.out.println(i + " " + getByte(i));
    }

    public void printInternalShorts(long endOffset) {
        for (int i = 0; i < endOffset; i += 2) System.out.println(i + " " + getShort(i));
    }

    public void printInternalLongs(long endOffset) {
        for (int i = 0; i < endOffset; i += 8) System.out.println(i + " " + getLong(i));
    }

}
