package com.z.p1.c07;

import static com.z.util.IO.println;

class ThreadCloseForce2 {

    public static void main(String[] args) throws InterruptedException {

        ThreadService service = new ThreadService();

        long start = System.currentTimeMillis();
        service.execute(() -> {
            //耗时任务
            int index = 0;
            while (true) {
                println("index: " + (index++));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        service.shutdown(2000); // while -> true
        long end = System.currentTimeMillis();
        println("main Thread, ms: " + (end - start));
    }
}