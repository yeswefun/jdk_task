package com.z.p2.c28_future;

import static com.z.util.IO.println;

class MainTest00 {

    /*
        模拟耗时任务
            调用后直接阻塞
     */
    public static void main(String[] args) {
        println("********************* before");
        String s = get();
        println(s);
        println("********************* after");
    }

    private static String get() {
        try {
            Thread.sleep(6_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "finish";
    }
}
