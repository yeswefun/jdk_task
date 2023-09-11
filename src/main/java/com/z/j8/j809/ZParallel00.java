package com.z.j8.j809;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

class ZParallel00 {

    /*
        Stream并行编程体验
     */
    public static void main(String[] args) {
        //System.out.println(Runtime.getRuntime().availableProcessors()); // xeon e3 1245 v3, 4c8t, 8

        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "8");


//        benchmark(Parallel00::iterateStream, 10_000_000);

//        benchmark(Parallel00::parallelStream, 10_000_000);
//        benchmark(Parallel00::parallelStream2, 10_000_000);
        benchmark(ZParallel00::parallelStream3, 10_000_000);

        benchmark(ZParallel00::normal, 10_000_000);
    }

    private static void benchmark(Function<Long, Long> consumer, long limit) {
        long start = System.currentTimeMillis();
        Long result = consumer.apply(limit);
        System.out.println("ms: " + (System.currentTimeMillis() - start));
        System.out.println(result);
    }

    private static long iterateStream(long limit) {
        return Stream.iterate(1L, i -> i + 1).limit(limit).reduce(0L, Long::sum);
    }

    /*
        boxing
     */
    private static long parallelStream(long limit) {
        return Stream.iterate(1L, i -> i + 1).parallel().limit(limit).reduce(0L, Long::sum);
    }

    private static long parallelStream2(long limit) {
        return Stream.iterate(1L, i -> i + 1).map(Long::longValue).parallel().limit(limit).reduce(0L, Long::sum);
    }

    private static long parallelStream3(long limit) {
        return LongStream.rangeClosed(1, limit).parallel().sum();
    }

    private static long normal(long limit) {
        long sum = 0L;
        for (long i = 1L; i <= limit; i++) {
            sum += i;
        }
        return sum;
    }
}
