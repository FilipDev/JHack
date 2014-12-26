package me.pauzen.jhack.misc;

import sun.misc.Unsafe;

/*
 * Written by FilipDev on 12/24/14 12:19 AM.
 */

public final class CurrentSystem {

    public static enum Architecture {

        x64, x86;

        /**
         * Gets architecture of processor.
         *
         * @return Processor architecture.
         */
        public static Architecture getArchitecture() {
            if (Unsafe.ADDRESS_SIZE == 4) return x86;
            return x64;
        }

        /**
         * Gets whether or not the processor architecture is 32 bit.
         *
         * @return If the processor architecture is 32 bit.
         */
        public static boolean is86() {
            return getArchitecture() == Architecture.x86;
        }

        /**
         * Gets whether or not the processor architecture is 64 bit.
         *
         * @return If the processor architecture is 64 bit.
         */
        public static boolean is64() {
            return !is86();
        }

    }

    public static enum OS {

        WINDOWS, MAC, LINUX;

        /**
         * Converts OS name to the enum form.
         *
         * @param osName Name of OS, usually gotten from System.getProperty("os.name")
         * @return Gets OS.
         */
        public static OS getOS(String osName) {
            osName = osName.toLowerCase();
            if (osName.contains("win")) return WINDOWS;
            else if (osName.contains("linux")) return LINUX;
            else if (osName.contains("mac")) return MAC;
            return null;
        }
    }

    private static CurrentSystem currentSystem;

    /**
     * Gets CurrentSystem instance.
     *
     * @return CurrentSystem instance.
     */
    public static CurrentSystem getSystem() {
        return currentSystem == null ? (currentSystem = new CurrentSystem()) : currentSystem;
    }

    private final OS           os;
    private final Architecture architecture;

    private CurrentSystem() {
        this.os = OS.getOS(System.getProperty("os.name"));
        this.architecture = Architecture.getArchitecture();
    }

    public OS getOS() {
        return this.os;
    }

    public Architecture getArchitecture() {
        return this.architecture;
    }

}
