package com.z.j8.j810;

import java.util.concurrent.ForkJoinPool;

class ForkJoin00 {

    private static final int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    public static void main(String[] args) {

        System.out.println("result: " + calc());

        //task
        AccumulatorRecursiveTask task = new AccumulatorRecursiveTask(0, data.length, data);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Integer result = forkJoinPool.invoke(task);
        System.out.println(result);

        //action
        AccumulatorRecursiveAction action = new AccumulatorRecursiveAction(0, data.length, data);
        forkJoinPool.invoke(action);
        System.out.println(AccumulatorRecursiveAction.AccumulatorHelper.getResult());
    }

    private static int calc() {
        int result = 0;
        for (int num : data) {
            result += num;
        }
        return result;
    }
}
