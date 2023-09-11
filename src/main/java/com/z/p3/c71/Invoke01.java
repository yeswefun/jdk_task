package com.z.p3.c71;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Invoke01 {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Callable<Integer>> callableList = IntStream.range(0, 5).boxed().map(i -> (Callable<Integer>) () -> {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10) + 5);
            //其中一个返回了，其它的 Callable 是否还会继续执行? 其它的被取消掉了
            System.out.println(Thread.currentThread().getName() + " : " + i);
            return i;
        }).collect(Collectors.toList());

        Integer value = executorService.invokeAny(callableList, 3, TimeUnit.SECONDS);// block
        System.out.println("----------------- finished");
        System.out.println(value);
    }
}
