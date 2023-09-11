package com.z.p2.c43;

import static com.z.util.IO.println;

class MainTest01 {

    /*
        ExtClassLoader
            jre/lib/ext
     */
    public static void main(String[] args) {
        String path = System.getProperty("java.ext.dirs");
        println(path);
        println("***************************");
        for (String p : path.split(";")) {
            println(p);
        }
    }
}
