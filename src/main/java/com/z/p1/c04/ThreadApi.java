package com.z.p1.c04;

import java.util.Optional;

class ThreadApi {

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            Optional.of("t#run").ifPresent(System.out::println);
            try {
                Thread.sleep(60_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t");

        t.start();

        Optional.of(t.getId()).ifPresent(System.out::println);
        Optional.of(t.getName()).ifPresent(System.out::println);
        Optional.of(t.getPriority()).ifPresent(System.out::println);
    }
}
