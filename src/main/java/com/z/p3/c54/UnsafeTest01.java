package com.z.p3.c54;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

class UnsafeTest01 {
    /*
        sun.misc.Unsafe@4554617c
     */
    public static void main(String[] args) {
        Unsafe unsafe = getUnsafe();
        System.out.println(unsafe);
    }

    private static Unsafe getUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true); // private
            return (Unsafe) field.get(null); // static
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
