package com.z.p3.c54;

import sun.misc.Unsafe;

class UnsafeTest00 {
    /*
        Bootstrap, 底层是由C++实现的, java层看不到，所以为 null
        Ext
        App
     */
    public static void main(String[] args) {
        //sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(UnsafeTest00.class.getClassLoader());

        //Exception in thread "main" java.lang.SecurityException
        Unsafe unsafe = Unsafe.getUnsafe();
        System.out.println(unsafe);
    }
}
