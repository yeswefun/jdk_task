package com.z.p3.c54;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

final class UnsafeUtil {

    private UnsafeUtil() {
    }

    public static Unsafe getUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
