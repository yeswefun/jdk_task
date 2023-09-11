package com.z.j8.j814;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

class Api09 {

    public static void main(String[] args) throws InterruptedException {

        List<CompletableFuture<Long>> list = Arrays.asList(1, 2, 3, 4)
                .stream()
                .map(i -> CompletableFuture.supplyAsync(Api09::get))
                .collect(Collectors.toList());

        CompletableFuture[] toArrary = list.toArray(new CompletableFuture[list.size()]);
        CompletableFuture.allOf(toArrary)
                .thenRun(() -> System.out.println("done"));

        Thread.sleep(1000L);
    }

    private static int index = 0;

    private static long get() {
        synchronized (Api10.class) {
            index++;
        }
        try {
            Thread.sleep(200 + index * 200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long r = System.currentTimeMillis();
        System.out.println(r);
        return r;
    }
}
