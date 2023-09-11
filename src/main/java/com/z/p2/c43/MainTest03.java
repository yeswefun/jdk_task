package com.z.p2.c43;

class MainTest03 {

    /*
        static class AppClassLoader
            extends URLClassLoader {}

        public class URLClassLoader
            extends SecureClassLoader
            implements Closeable {}

        public class SecureClassLoader
            extends ClassLoader {}
     */
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> cls = Class.forName("com.z.p2.c43.Target");
        ClassLoader cl = cls.getClassLoader();

        /*
            sun.misc.Launcher$AppClassLoader@18b4aac2
            sun.misc.Launcher$ExtClassLoader@1b6d3586
            null
         */
        System.out.println(cl);

        System.out.println(cl.getParent());

        System.out.println(cl.getParent().getParent());
    }
}
