package com.z.j8.j804;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

class Stream05_Reduce {

    public static void main(String[] args) {
        Stream<Integer> stream = getStream();
        Integer result = stream.reduce(
                0,
                Integer::sum // (i, j) -> i + j
        );
        System.out.println(result);

        stream = getStream();
        stream.reduce(Integer::sum).ifPresent(System.out::println);

        stream = getStream();
        stream.reduce((i, j) -> i > j ? i : j).ifPresent(System.out::println);

        stream = getStream();
        stream.reduce(Integer::max).ifPresent(System.out::println);

        stream = getStream();
        stream.reduce(Integer::min).ifPresent(System.out::println);

        Integer i = null;
//        Optional.of(i).ifPresent(System.out::println); // throws Exception
        i = 6;
        Optional.of(i).ifPresent(System.out::println);
    }

    private static Stream<Integer> getStream() {
        return Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
    }
}
