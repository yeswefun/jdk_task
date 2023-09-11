package com.z.p3.c76;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Completable03 {

    /*
        一批任务，批量执行
            等到所有任务都完成 - 收集所有结果
     */
    public static void main(String[] args) throws InterruptedException {

        List<Callable<Integer>> tasks = IntStream.range(0, 8)
                .boxed()
//                .map(i -> (Callable<Integer>) (() -> get()))
                .map(i -> (Callable<Integer>) (Completable03::get))
                .collect(Collectors.toList());

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.invokeAll(tasks)
                .stream()
                //问题: 全部 Future#get 完成之后，才执行下一阶段
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .parallel()
                .forEach(Completable03::display);
    }

    private static void display(int data) {
        System.out.println(Thread.currentThread().getName() + " display " + data);
    }

    private static int get() {
        String name = Thread.currentThread().getName();
        int value = ThreadLocalRandom.current().nextInt(3) + 1;
        try {
            System.out.println(name + " -> sleep: " + value);
            TimeUnit.SECONDS.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " -> done");
        return value;
    }
}
