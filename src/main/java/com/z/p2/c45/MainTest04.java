package com.z.p2.c45;


import java.lang.reflect.InvocationTargetException;

class MainTest04 {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        /*
            loadClass时，并不会导致类初始化
                因为它并不是 6个主动加载条件 的其中之一
         */
        MyClassLoader cl = new MyClassLoader("MyClassLoader");

        /*
            对于同一个类加载器，多次加载，只加载一次
         */
        Class<?> cls1 = cl.loadClass("com.z.c44.MyObject");
        System.out.println(cls1.hashCode());

        Class<?> cls2 = cl.loadClass("com.z.c44.MyObject");
        System.out.println(cls2.hashCode());
        /*
            356573597
            356573597
         */
    }
}
