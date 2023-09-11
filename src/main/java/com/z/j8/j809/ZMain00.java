package com.z.j8.j809;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

class ZMain00 {

    public static void main(String[] args) {
//        test00();
        test01();
    }

    /*
        Thread[main,5,main] -> characteristics
        Thread[main,5,main] -> characteristics
        Thread[main,5,main] -> supplier
        Thread[main,5,main] -> accumulator
        Thread[main,5,main] -> combiner
        Thread[main,5,main] -> characteristics
        Thread[main,5,main] -> characteristics
        [C, C++, java, Js]
     */
    private static void test01() {
        Collector<String, List<String>, List<String>> collector = new ToListCollector<>();

        String[] arr = new String[] {
                "C", "C++", "java", "Kotlin", "Python", "Js"
        };

        List<String> list = Arrays.asList(arr)
                .parallelStream()
//                .stream()
                .filter(s -> s.length() < 5)
                .collect(collector);

        System.out.println(list);
    }

    /*
        Thread[main,5,main] -> supplier
        Thread[main,5,main] -> accumulator
        Thread[main,5,main] -> combiner
        Thread[main,5,main] -> characteristics
        Thread[main,5,main] -> characteristics
        [C, C++, java, Js]
     */
    private static void test00() {
        Collector<String, List<String>, List<String>> collector = new ToListCollector<>();

        String[] arr = new String[] {
                "C", "C++", "java", "Kotlin", "Python", "Js"
        };

        List<String> list = Arrays.stream(arr)
                .filter(s -> s.length() < 5)
                .collect(collector);

        System.out.println(list);
    }
}
