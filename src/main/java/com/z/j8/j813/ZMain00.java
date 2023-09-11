package com.z.j8.j813;

import java.util.concurrent.*;

class ZMain00 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<String> future = executorService.submit(() -> {
            try {
                Thread.sleep(1000);
                return "finished";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "error";
            }
        });

        //...
        //...
        System.out.println("--- start");
        System.out.println(future.get()); //blocked
//        System.out.println(future.get(500, TimeUnit.MILLISECONDS)); //timeout
        System.out.println("--- stop");

        Thread.sleep(1200);

        System.out.println(future.get());
        executorService.shutdown();
    }
}
