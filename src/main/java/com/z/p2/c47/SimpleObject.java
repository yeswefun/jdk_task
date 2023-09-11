package com.z.p2.c47;

public class SimpleObject {

    static {
        System.out.println("*************** SimpleObject static block");
    }

    public String test(String msg) {
        return "[" + msg + "]";
    }
}
