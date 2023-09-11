package com.z.p2.c49;

class MainTest00 {

    /*
        线程上下文类加载器
     */
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        System.out.println(mainThread.getContextClassLoader());
        mainThread.setContextClassLoader(new MyClassLoader());
        System.out.println(mainThread.getContextClassLoader());

        /*
            sun.misc.Launcher$AppClassLoader@18b4aac2
            com.z.c49.MyClassLoader@4554617c
         */
    }
}
