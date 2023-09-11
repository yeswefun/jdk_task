package com.z.p2.c48;


class MainTest02 {

    public static void main(String[] args) throws Exception {
        /*
            loadClass 时，并不会导致类初始化
                因为它并不是 6个主动加载条件 的其中之一
         */
        MyClassLoader cl0 = new MyClassLoader("MyClassLoader-0");

        MyClassLoader cl1 = new MyClassLoader("MyClassLoader-1");
        cl1.setDir("C:/Users/lin/Documents/fuck/cl1_1");

        Class<?> cls00 = cl0.loadClass("com.z.c44.MyObject");
        Class<?> cls10 = cl1.loadClass("com.z.c44.MyObject");

        /*
            对于同一个类加载器，多次加载，只加载一次
            对于不同的类加载器，各自加载一次
         */
        System.out.println(cls00.hashCode());
        System.out.println(cls10.hashCode());
        /*
            21685669
            2133927002
         */
    }
}
