package com.z.p3.c73;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

class Completion02 {

    /*
        完成任务之后，回调
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        List<Callable<Integer>> callableList = Arrays.asList(
                () -> {
                    sleep(1);
                    System.out.println("sleep 1");
                    return 1;
                },
                () -> {
                    sleep(3);
                    System.out.println("sleep 3");
                    return 3;
                },
                () -> {
                    sleep(2);
                    System.out.println("sleep 2");
                    return 2;
                }
        );

        //jdk1.8之前的做法
        //不需要等待执行时长最长的那个
        List<Future<Integer>> futures = new ArrayList<>();
        futures.add(executorService.submit(callableList.get(0)));
        futures.add(executorService.submit(callableList.get(1)));
        futures.add(executorService.submit(callableList.get(2)));
        for (Future<Integer> future : futures) {
            System.out.println(future.get());
        }

        executorService.shutdown();
    }

    private static void sleep(int sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
