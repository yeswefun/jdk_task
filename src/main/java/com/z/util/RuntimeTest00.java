package com.z.util;

class RuntimeTest00 {
    /*
        Runtime
            An application cannot create its own instance of this class.

        Runtime
            void addShutdownHook(Thread hook)
                Registers a new virtual-machine shutdown hook.
            boolean removeShutdownHook(Thread hook)
                De-registers a previously-registered virtual-machine shutdown hook.

            exit
            exec

            void load(String filename)
                Loads the native library specified by the filename argument.
            void loadLibrary(String libname)
                Loads the native library specified by the libname argument.
     */
    public static void main(String[] args) {
        Runtime rt = Runtime.getRuntime();
        System.out.println("available process: " + rt.availableProcessors());

        System.out.println("maxMemory(JVM): " + readableUnit(rt.maxMemory()));
        System.out.println("totalMemory(JVM): " + readableUnit(rt.totalMemory()));
        System.out.println("freeMemory(JVM): " + readableUnit(rt.freeMemory()));
        rt.gc();
        System.out.println("freeMemory(JVM): " + readableUnit(rt.freeMemory()));
    }

    /*
        1    Byte   == 8 bit
        1024 Byte   == 1 KB
        1024 KB     == 1 MB
        1024 MB     == 1 GB

        1KB == 1024 == 2^10
        1MB == 1024 * 1024 == 2^10 * 2^10 == 2^20
        1GB == 1024 * 1024 * 1024 == 2^10 * 2^10 * 2^10 == 2^30
     */
    private static final int UNIT_KB = 2 << 10;
    private static final int UNIT_MB = 2 << 20;
    private static final int UNIT_GB = 2 << 30;

    private static String readableUnit(long x) {
        return (x / UNIT_MB) + "MB";
    }
}
