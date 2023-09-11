package com.z.j8.j805;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Numberic01 {

    public static void main(String[] args) {
        //1..100
        //result: [a, b, c]
        int a = 9;
        IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .boxed() // Stream<Integer> 的方法 比 IntStream 的方法多
                .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                .map(Arrays::toString)
                .forEach(System.out::println);

        System.out.println("--------------- 2");
        IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                .map(Arrays::toString)
                .forEach(System.out::println);
    }
}
