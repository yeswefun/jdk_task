package com.z.j8.j804;

import java.util.Arrays;
import java.util.stream.Stream;

class Stream03_Match {

    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 6, 5});
        Stream<Integer> stream2 = Arrays.stream(new Integer[]{1, 2, 3, 4, 5});
        Stream<Integer> stream3 = Arrays.stream(new Integer[]{6, 7, 8, 9});

//        System.out.println(stream.allMatch(i -> i > 5)); // false
//        System.out.println(stream2.allMatch(i -> i > 5));// false
//        System.out.println(stream3.allMatch(i -> i > 5));// true

//        System.out.println(stream.noneMatch(i -> i > 5)); // false
//        System.out.println(stream2.noneMatch(i -> i > 5));// true
//        System.out.println(stream3.noneMatch(i -> i > 5));// false

        System.out.println(stream.anyMatch(i -> i > 5)); // true
        System.out.println(stream2.anyMatch(i -> i > 5));// false
        System.out.println(stream3.anyMatch(i -> i > 5));// true
    }
}
