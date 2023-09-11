package com.z.p2.c45;


class MainTest03 {

    public static void main(String[] args) throws ClassNotFoundException {
        /*
            loadClass时，并不会导致类初始化
                因为它并不是 6个主动加载条件 的其中之一
         */
        MyClassLoader cl1 = new MyClassLoader("MyClassLoader-1");
        cl1.setDir("C:/Users/lin/Documents/fuck/cl1_0");

        /*
            Exception in thread "main" java.lang.ClassNotFoundException:
                not found: com.z.c44.MyObject
         */
        Class<?> cls = cl1.loadClass("com.z.c44.MyObject");
        System.out.println(cls.hashCode());
    }
}
