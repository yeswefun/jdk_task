package com.z.p2.c42;

import static com.z.util.IO.println;

class MainTest00 {

    /*
        用不同的 classloader 加载 同一个 class 会出现不同的 getClass()
            命名空间
                classloader + 包名
     */
    public static void main(String[] args) {
        Demo d1 = new Demo();
        Demo d2 = new Demo();
        Demo d3 = new Demo();

        println("********************* obj");
        println(d1.toString());
        println(d2.toString());
        println(d3.toString());

        println("********************* cls");
        println(d1.getClass().hashCode() + "");
        println(d2.getClass().hashCode() + "");
        println(d3.getClass().hashCode() + "");

        println("********************* ==");
        println(": " + (d1.getClass() == d2.getClass()));
        println(": " + (d2.getClass() == d3.getClass()));
    }
}
