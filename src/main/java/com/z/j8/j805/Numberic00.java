package com.z.j8.j805;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Numberic00 {

    public static void main(String[] args) {
        Stream<Integer> stream = getStream();

        Stream<Integer> integerStream = stream.filter(i -> i < 3);
        Optional<Integer> integerOptional = integerStream.reduce(Integer::sum);
        integerOptional.ifPresent(System.out::println);

//        int(32bit/4byte) // 比 Integer 的内存占用量少很多

        stream = getStream();
        IntStream intStream = stream.mapToInt(
                //i -> i.intValue()
                Integer::intValue
        );
        int sum = intStream.sum();

        stream = getStream();
        intStream = stream.mapToInt(
                //i -> i.intValue()
                Integer::intValue
        );
        intStream.filter(i -> i< 4).reduce(
                //(i, j) -> i + j
                Integer::sum
        ).ifPresent(System.out::println);
    }

    private static Stream<Integer> getStream() {
        return Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 6, 5});
    }
}
