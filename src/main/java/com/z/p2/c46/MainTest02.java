package com.z.p2.c46;


import java.lang.reflect.Method;

class MainTest02 {

    public static void main(String[] args) throws Exception {
        /*
            loadClass 时，并不会导致类初始化
                因为它并不是 6个主动加载条件 的其中之一
         */
        MyDecryptClassLoader cl = new MyDecryptClassLoader();
        Class<?> cls = cl.loadClass("com.z.c44.MyObject");
        /*
            类文件不在工程中，可以使用反射来访问类中成员
         */
        Object o = cls.newInstance();
        Method helloMethod = cls.getMethod("hello");
        Object r = helloMethod.invoke(o);
        System.out.println(r);
    }
}
