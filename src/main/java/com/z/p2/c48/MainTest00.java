package com.z.p2.c48;

class MainTest00 {

    public static void main(String[] args) throws Exception {
        ClassLoader cl = MainTest00.class.getClassLoader();
        Class<?> cls1 = cl.loadClass("java.lang.String");
        Class<?> cls2 = cl.loadClass("java.lang.String");
        System.out.println(cls1.hashCode());
        System.out.println(cls2.hashCode());
        /*
            460141958
            460141958
         */
    }
}
