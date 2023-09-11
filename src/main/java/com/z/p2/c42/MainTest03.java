package com.z.p2.c42;

import static com.z.util.IO.println;

class MainTest03 {

    private static class P {

        protected static int A = 1;

        static {
            println("---> init parent");
            A = 2;
        }
    }

    private static class C extends P {

        protected static int B = A;

        static {
            println("---> init child");
        }
    }

    public static void main(String[] args) {
        System.out.println(C.B);
    }
}


