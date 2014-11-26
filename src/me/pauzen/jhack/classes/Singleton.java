package me.pauzen.jhack.classes;

public abstract class Singleton {

    private static Singleton instance;

    public Singleton() {
        instance = this;
    }

    public static Singleton getValue() {
        return instance;
    }

}
