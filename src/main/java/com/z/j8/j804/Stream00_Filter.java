package com.z.j8.j804;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Stream00_Filter {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 6, 5);

        List<Integer> evenList = list.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());
        System.out.println(evenList);

        List<Integer> distinctList = list.stream().distinct().collect(Collectors.toList());
        System.out.println(distinctList);

        List<Integer> skipList = list.stream().skip(5).collect(Collectors.toList());
        System.out.println(skipList);

        List<Integer> limitList = list.stream().limit(5).collect(Collectors.toList());
        System.out.println(limitList);
    }
}
