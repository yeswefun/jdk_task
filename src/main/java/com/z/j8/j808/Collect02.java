package com.z.j8.j808;

import java.util.*;
import java.util.stream.Collectors;

class Collect02 {

    private static final List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH)
    );

    public static void main(String[] args) {
//        testCounting();
//        testGroupingBy();
//        testGroupingBy2();
//        testGroupingBy3();
        testSummarizingInt();
    }

    /*
        IntSummaryStatistics{count=9, sum=4200, min=120, average=466.666667, max=800}
     */
    private static void testSummarizingInt() {
        IntSummaryStatistics r = menu.stream().collect(
                Collectors.summarizingInt(Dish::getCalories)
        );
        Optional.of(r).ifPresent(System.out::println);
    }

    private static void testGroupingBy3() {
        Map<Dish.Type, Double> map = menu.stream()
                .collect(
                        Collectors.groupingBy(
                                Dish::getType,
                                TreeMap::new, // 2个参数默认为 HashMap
                                Collectors.averagingInt(Dish::getCalories)
                        )
                );
        System.out.println(map.getClass()); // class java.util.HashMap
    }

    private static void testGroupingBy2() {
        Optional.of(
//                menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.counting()))
                menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.averagingInt(Dish::getCalories)))
        ).ifPresent(System.out::println);
    }

    private static void testGroupingBy() {
        Optional.of(menu.stream().collect(Collectors.groupingBy(Dish::getType))).ifPresent(System.out::println);
    }

    private static void testCounting() {
//        Optional.of(menu.stream().collect(Collectors.counting())).ifPresent(System.out::println);
        Optional.of(menu.stream().count()).ifPresent(System.out::println);
//        Optional.of((long) menu.size()).ifPresent(System.out::println);
    }
}
