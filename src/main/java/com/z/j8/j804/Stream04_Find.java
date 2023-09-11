package com.z.j8.j804;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

class Stream04_Find {

    public static void main(String[] args) {

        Stream<Integer> stream = getStream();
        Optional<Integer> optional_1 = stream.filter(i -> i % 2 == 0).findAny();
        System.out.println(optional_1.get());

        System.out.println("--- 2");
        stream = getStream();
        Optional<Integer> optional_2 = stream.filter(i -> i % 2 == 0).findFirst();
        System.out.println(optional_2.get());
        optional_2.ifPresent(System.out::println);

        System.out.println("--- 3");
        stream = getStream();
        Optional<Integer> optional_3 = stream.filter(i -> i > 10).findAny();
//        System.out.println(optional_3.get()); // NoSuchElementException: No value present
        System.out.println(optional_3.orElse(-1));

        System.out.println("--- 4");
        System.out.println(find(new Integer[]{1, 2, 3}, i -> i > 2, -1));
        System.out.println(find(new Integer[]{1, 2, 3}, i -> i > 6, -1));
    }

    private static int find (Integer[] values, Predicate<Integer> predicate, int defaultValue) {
        for (Integer value : values) {
            if (predicate.test(value)) {
                return value;
            }
        }
        return defaultValue;
    }

    private static Stream<Integer> getStream() {
        return Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 6, 5});
    }
}
