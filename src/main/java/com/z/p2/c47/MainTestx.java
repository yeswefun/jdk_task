package com.z.p2.c47;

class MainTestx {

    /*
        Exception in thread "main" java.lang.NoClassDefFoundError: java/lang/Object

        java.lang.SecurityException: Prohibited package name: java.lang
     */
    public static void main(String[] args) throws Exception {
        SimpleClassLoader cl = new SimpleClassLoader("SimpleClassLoader");
        Class<?> cls = cl.loadClass("java.lang.String");
        System.out.println(cls.getClassLoader());
    }
}
