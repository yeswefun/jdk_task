package com.z.p2.c43;

import static com.z.util.IO.println;

class MainTest00 {

    /*
        BootstrapClassLoader
            jre/lib
     */
    public static void main(String[] args) {
        String path = System.getProperty("sun.boot.class.path");
        println(path);
        println("***************************");
        for (String p : path.split(";")) {
            println(p);
        }
    }
}
