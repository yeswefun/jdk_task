package com.z.p2.c30;

import static com.z.util.IO.println;

class MainTest02 {

    /*
        泛型 在 匿名类时必须要给
     */
    private static final ThreadLocal<String> tl = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "Java";
        }
    };

    /*
        JVM start main thread
     */
    public static void main(String[] args) throws InterruptedException {
        println(tl.get());
    }
}
