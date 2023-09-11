package com.z.p3.c55;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

final class UnsafeUtil {

    private UnsafeUtil() {
    }

    public static Unsafe getUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);  // private
            return (Unsafe) field.get(null);    // static
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
