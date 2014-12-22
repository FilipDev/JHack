package me.pauzen.jhack.misc;

import me.pauzen.jhack.unsafe.UnsafeProvider;

public final class CurrentSystem {

    public static enum Architecture {

        x64, x86;

        public static Architecture getArchitecture() {
            if (UnsafeProvider.getAddressSize() == 4) return x86;
            return x64;
        }

        public static boolean is86() {
            return getArchitecture() == Architecture.x86;
        }

        public static boolean is64() {
            return !is86();
        }

    }

    public static enum OS {

        WINDOWS, MAC, LINUX;

        public static OS getOS(String osName) {
            osName = osName.toLowerCase();
            if (osName.contains("win")) return WINDOWS;
            else if (osName.contains("linux")) return LINUX;
            else if (osName.contains("mac")) return MAC;
            return null;
        }
    }

    private static CurrentSystem currentSystem;

    public static CurrentSystem getSystem() {
        return currentSystem == null ? (currentSystem = new CurrentSystem()) : currentSystem;
    }

    private final OS os;
    private final Architecture architecture;

    private CurrentSystem() {
        this.os = OS.getOS(java.lang.System.getProperty("os.name"));
        this.architecture = Architecture.getArchitecture();
    }

    public OS getOS() {
        return this.os;
    }

    public Architecture getArchitecture() {
        return this.architecture;
    }

}
