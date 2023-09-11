package com.z.p2.c44;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class MainTest00 {

    /*
        TODO: 测试一下
        Examples of valid class names include:
             "java.lang.String"
             "javax.swing.JSpinner$DefaultEditor"
             "java.security.KeyStore$Builder$FileBuilder$1"
             "java.net.URLClassLoader$3$1"

        C:\Users\lin\Documents\fuck\cl0\
            com/z/c44
                MyObject
     */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        //new MyObject(); // 产生 class 文件, 直接 剪切到上面注释的目录中

        /*
            loadClass时，并不会导致类初始化
                因为它并不是 6个主动加载条件的其中之一
         */
        MyClassLoader cl = new MyClassLoader("MyClassLoader");
        Class<?> cls = cl.loadClass("com.z.c44.MyObject");

        /*
            类文件不在工程中，可以使用反射来访问类中成员
         */
        Object o = cls.newInstance();
        Method helloMethod = cls.getMethod("hello");
        Object r = helloMethod.invoke(o);
        System.out.println(r);

        System.out.println(cls);
        System.out.println(cls.getClassLoader());
        System.out.println(cls.getClassLoader().getParent());
        System.out.println(cls.getClassLoader().getParent().getParent());
        System.out.println(cls.getClassLoader().getParent().getParent().getParent());
        /*
            *************** MyObject static block
            Hello World
            null
            class com.z.c44.MyObject
            com.z.c44.MyClassLoader@4554617c
            sun.misc.Launcher$AppClassLoader@18b4aac2
            sun.misc.Launcher$ExtClassLoader@677327b6
            null
         */
    }
}
