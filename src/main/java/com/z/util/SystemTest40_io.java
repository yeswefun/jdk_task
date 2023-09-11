package com.z.util;

import java.io.IOException;

class SystemTest40_io {
    public static void main(String[] args) {
        System.err.println("err");
        System.out.println("out");
        try {
            int x = System.in.read();
            System.out.println(x);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
