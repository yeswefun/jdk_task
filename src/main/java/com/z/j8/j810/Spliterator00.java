package com.z.j8.j810;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

class Spliterator00 {

    public static void main(String[] args) {
        IntStream intStream = IntStream.rangeClosed(0, 10);
        Spliterator.OfInt spliterator = intStream.spliterator();
        Consumer<Integer> consumer = i -> System.out.println(Thread.currentThread() + " --> " + i);
        spliterator.forEachRemaining(consumer);
    }
}
