package com.z.p2.c48;

class MainTest14 {

    /*
        MainTest10
        com.z.c48
        运行时包名: Boot.Ext.App.com.z.c48

        A: Boot.Ext.App.com.z.c48.MainTest10
        B: Boot.Ext.App.MyClassLoader.com.z.c48.MainTest10
            A 看不到 B
            父加载器看不到子加载器加载的类
            各个加载器看不到彼此已经加载的类?
     */
    public static void main(String[] args) throws Exception {
        SimpleClassLoader2 cl = new SimpleClassLoader2("SimpleClassLoader");

        Class<?> cls = cl.loadClass("com.z.p2.c47.SimpleObject");
        System.out.println(cls.getClassLoader());

        Class<?> cls2 = cl.loadClass("com.z.p2.c48.MainTest14");
        System.out.println(cls2.getClassLoader());
    }
}
