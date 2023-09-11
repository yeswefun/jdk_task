package com.z.p2.c45;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class MainTest01 {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        /*
            loadClass时，并不会导致类初始化
                因为它并不是 6个主动加载条件 的其中之一
         */
        MyClassLoader cl0 = new MyClassLoader("MyClassLoader-0");
        MyClassLoader cl1 = new MyClassLoader("MyClassLoader-1", cl0);
        // cl1 的路径中不存在 MyObject，但在 cl0 的路径中存在 MyObject,
        // 不管 cl1 的路径中是否存在，优先使用 cl0 (父加载器)
        cl1.setDir("C:/Users/lin/Documents/fuck/cl1_1");
        Class<?> cls = cl1.loadClass("com.z.c44.MyObject");
        /*
            类文件不在工程中，可以使用反射来访问类中成员
         */
        Object o = cls.newInstance();
        Method helloMethod = cls.getMethod("hello");
        Object r = helloMethod.invoke(o);
        System.out.println(r);

        System.out.println(cls);
        System.out.println(cls.getClassLoader() + " ---> " + ((MyClassLoader) cls.getClassLoader()).getClassLoaderName());
        System.out.println(cls.getClassLoader().getParent());

        /*
            *************** MyObject static block
            Hello World
            null
            class com.z.c44.MyObject
            com.z.c45.MyClassLoader@4554617c ---> MyClassLoader-0
            sun.misc.Launcher$AppClassLoader@18b4aac2
         */
    }
}
