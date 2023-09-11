package com.z.j8.j804;

import java.util.Arrays;

class Stream02_FlatMap {

    public static void main(String[] args) {
        String[] words = {"hello", "world"};
        Arrays.stream(words) // Stream<String>
                .map(s -> s.split("")) // Stream<String[]>, { {h, e, l, l, o}, {w, o, r, l, d} }
                .flatMap(Arrays::stream) // Stream<String>, {h, e, l, l, o, w, o, r, l, d}
                //.flatMap(arr -> Arrays.stream(arr))
                .distinct()
                .forEach(System.out::println);
    }
}
