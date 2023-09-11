package com.z.j8.j808;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

class Collect03 {

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
//        testGroupingBy();
//        testGroupingBy2();
//        testGroupingBy3();
//        testJoining();
//        testJoining2();
//        testJoining3();
//        testMapping();
//        testMaxBy(); // reduce 也可以做
//        testMinBy();
    }

    private static void testMinBy() {
        menu.stream().collect(
                Collectors.minBy(
                        Comparator.comparing(Dish::getCalories)
                )
        ).ifPresent(System.out::println);
    }

    private static void testMaxBy() {
        menu.stream().collect(
                Collectors.maxBy(
                        Comparator.comparing(Dish::getCalories)
                )
        ).ifPresent(System.out::println);
    }

    private static void testMapping() {
        String result = menu.stream()
                .collect(
                        Collectors.mapping(
                                Dish::getName,
                                Collectors.joining(", ", "(", ")")
                        )
                );
        System.out.println(result);
    }

    private static void testJoining3() {
        String result = menu.stream()
                .map(Dish::getName)
                .collect(
                        Collectors.joining(", ", "(", ")")
                );
        System.out.println(result);
    }

    private static void testJoining2() {
        String result = menu.stream()
                .map(Dish::getName)
                .collect(
                        Collectors.joining(", ")
                );
        System.out.println(result);
    }

    private static void testJoining() {
        String result = menu.stream()
                .map(Dish::getName)
                .collect(
                        Collectors.joining()
                );
        System.out.println(result);
    }

    private static void testGroupingBy4() {
        Map<Dish.Type, Double> map = menu.stream()
                .collect(
                        Collectors.groupingByConcurrent(
                                Dish::getType,
                                Collectors.averagingInt(Dish::getCalories)
                        )
                );
        Optional.ofNullable(map).ifPresent(System.out::println);
    }

    private static void testGroupingBy() {
        ConcurrentMap<Dish.Type, List<Dish>> map = menu.stream()
                .collect(
                        Collectors.groupingByConcurrent(
                                Dish::getType
                        )
                );
        System.out.println(map.getClass()); // class java.util.concurrent.ConcurrentHashMap
        Optional.of(map).ifPresent(System.out::println);
    }

    private static void testGroupingBy2() {
        Map<Dish.Type, Double> map = menu.stream()
                .collect(
                        Collectors.groupingByConcurrent(
                                Dish::getType,
                                Collectors.averagingInt(Dish::getCalories)
                        )
                );
        System.out.println(map.getClass()); // class java.util.concurrent.ConcurrentHashMap
        Optional.of(map).ifPresent(System.out::println);
    }

    private static void testGroupingBy3() {
        Map<Dish.Type, Double> map = menu.stream()
                .collect(
                        Collectors.groupingByConcurrent(
                                Dish::getType,
//                                ConcurrentSkipListMap::new,
                                ConcurrentSkipListMap::new, // 2个参数默认为 ConcurrentHashMap
                                Collectors.averagingInt(Dish::getCalories)
                        )
                );
        System.out.println(map.getClass()); // class java.util.concurrent.ConcurrentHashMap
        Optional.of(map).ifPresent(System.out::println);
    }
}
