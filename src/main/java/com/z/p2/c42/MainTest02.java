package com.z.p2.c42;

import static com.z.util.IO.println;

class MainTest02 {

    private static class P {
        static {
            println("---> init parent");
        }
    }

    private static class C extends P {

        private static String x = "C";

        static {
            println("---> init child");
        }
    }

    public static void main(String[] args) {
        println(C.x);
    }
}


