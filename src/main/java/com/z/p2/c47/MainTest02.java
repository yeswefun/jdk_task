package com.z.p2.c47;

import java.lang.reflect.Method;

class MainTest02 {

    /*
        fix - Exception in thread "main" java.lang.NoClassDefFoundError: java/lang/Object
     */
    public static void main(String[] args) throws Exception {

        SimpleClassLoader2 cl = new SimpleClassLoader2("SimpleClassLoader");
        Class<?> cls = cl.loadClass("com.z.p2.c47.SimpleObject");

        System.out.println(cls.getClassLoader());

         /*
            类文件不在工程中，可以使用反射来访问类中成员
         */
        Object o = cls.newInstance();
        Method helloMethod = cls.getMethod("test", String.class);
        Object r = helloMethod.invoke(o, "ok");
        System.out.println(r);
    }
}
