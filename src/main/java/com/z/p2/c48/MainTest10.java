package com.z.p2.c48;

import com.z.p2.c47.SimpleObject;

/*
AppClassLoader
 */
class MainTest10 {

    /*
        MainTest10
        com.z.c48
        (RuntimePackage)运行时包名: Boot.Ext.App.com.z.c48

        A: Boot.Ext.App.com.z.c47.SimpleObject
        B: Boot.Ext.App.MyClassLoader.com.z.c47.SimpleObject
            A 看不到 B
            父加载器看不到子加载器加载的类
            各个加载器看不到彼此已经加载的类?
     */
    public static void main(String[] args) throws Exception {
        SimpleClassLoader2 cl = new SimpleClassLoader2("SimpleClassLoader");

        // dir中的也是 c47 的 SimpleObject
        Class<?> cls = cl.loadClass("com.z.p2.c47.SimpleObject");
        System.out.println(cls.getClassLoader());

        /*
            Exception in thread "main" java.lang.ClassCastException:
            com.z.c47.SimpleObject cannot be cast to com.z.c47.SimpleObject
         */
        SimpleObject o = (SimpleObject) cls.newInstance();
    }
}
