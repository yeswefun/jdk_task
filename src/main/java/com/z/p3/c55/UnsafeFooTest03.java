package com.z.p3.c55;

import j8.test.Simple;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

class UnsafeFooTest03 {

    private static long sizeof(Object o) {
        Unsafe unsafe = UnsafeUtil.getUnsafe();
        Set<Field> set = new HashSet<>(); // 子类可以重写父类的field
        Class<?> cls = o.getClass();
        while (cls != Object.class) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if ((field.getModifiers() & Modifier.STATIC) == 0) { // 非静态
                    set.add(field);
                }
            }
            cls = cls.getSuperclass();
        }
        long maxOffset = 0;
        for (Field field : set) {
            long offset = unsafe.objectFieldOffset(field);
            if (offset > maxOffset) {
                maxOffset = offset;
            }
        }
        //padding, 下面公式的依据
        return ((maxOffset / 8) + 1) * 8;
    }

    public static void main(String[] args) {
        System.out.println(sizeof(new Simple()));
    }
}
