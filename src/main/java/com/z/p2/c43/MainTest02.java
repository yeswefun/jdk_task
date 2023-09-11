package com.z.p2.c43;

import static com.z.util.IO.println;

class MainTest02 {

    /*
        AppClassLoader
            CLASSPATH
     */
    public static void main(String[] args) {
        String path = System.getProperty("java.class.path");
        println(path);
        println("***************************");
        for (String p : path.split(";")) {
            println(p);
        }
    }
}
