package com.z.p3.c75;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ComplexDemo4 {

    /*
        ExecutorCompletionService 并不是 ExecutorService 的子类

            CompletionService 只关注完成
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService service = Executors.newSingleThreadExecutor(); // note
        ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(service);

        List<Callable<Integer>> taskList = IntStream.range(0, 5)
                .boxed()
                .map(MyTask::new)
                .collect(Collectors.toList());

        taskList.forEach(completionService::submit);

        TimeUnit.SECONDS.sleep(2);
        //20秒之后，获取还有哪些任务没有执行 - 可是 ExecutorCompletionService$QueueingFuture@4e50df2e
        service.shutdownNow();

        taskList.stream()
                .filter(callable -> !((MyTask)callable).isSuccess())
                .forEach(System.out::println);
    }

    private static class MyTask implements Callable<Integer> {

        private boolean success = false;

        private final int value;

        public MyTask(int value) {
            this.value = value;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println("task: " + value);
            TimeUnit.SECONDS.sleep(1 + value * 2L);
            System.out.println("task: " + value + " ---> finished!");
            success = true;
            return value;
        }

        public boolean isSuccess() {
            return success;
        }
    }
}
