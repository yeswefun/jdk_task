package com.z.p2.c36;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.z.util.IO.println;

class MessageHandler2 {

    private static final Random r = new Random(System.currentTimeMillis());

    private static final Executor executor = Executors.newFixedThreadPool(4);

    public void request(Message msg) {
        executor.execute(() -> {
            String value = msg.getValue();
            try {
                Thread.sleep(r.nextInt(1000));
                println(Thread.currentThread().getName() + ", value: " + value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    /*
        默认的动作: 等待线程池中的任务执行完之后才关闭线程池
     */
    public void shutdown() {
        System.out.println("--- shutdown threadPool");
        ((ExecutorService) executor).shutdown();
    }
}
