package com.z.j8.j809;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

class Collect10 {

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
//        testPartitionWithPredicate();
//        testPartitionWithPredicate2();
//        testReducingMax();
//        testReducingMin();
//        testReducingBinaryOperator();
//        testReducingBinaryOperator2();
        testSummarizingDouble();
        testSummarizingLong();
        testSummarizingInt();
    }

    private static void testSummarizingInt() {
        Optional.of(menu.stream().collect(
                Collectors.summarizingInt(Dish::getCalories)
        )).ifPresent(System.out::println);
    }

    private static void testSummarizingLong() {
        Optional.of(menu.stream().collect(
                Collectors.summarizingLong(Dish::getCalories)
        )).ifPresent(System.out::println);
    }

    private static void testSummarizingDouble() {
        Optional.of(menu.stream().collect(
                Collectors.summarizingDouble(Dish::getCalories)
        )).ifPresent(System.out::println);
    }

    private static void testReducingBinaryOperator2() {
        Integer result = menu.stream()
                .collect(
                        Collectors.reducing(0, Dish::getCalories, (d1, d2) -> d1 + d2)
                );
        Optional.ofNullable(result).ifPresent(System.out::println);
    }

    private static void testReducingBinaryOperator() {
        Integer result = menu.stream()
                .map(Dish::getCalories)
                .collect(
                        Collectors.reducing(0, (d1, d2) -> d1 + d2)
                );
        Optional.ofNullable(result).ifPresent(System.out::println);
    }

    private static void testReducingMin() {
        menu.stream().collect(
                Collectors.reducing(
                        BinaryOperator.minBy(Comparator.comparing(Dish::getCalories))
                )
        ).ifPresent(System.out::println);
    }


    private static void testReducingMax() {
        menu.stream().collect(
                Collectors.reducing(
                        BinaryOperator.maxBy(Comparator.comparing(Dish::getCalories))
                )
        ).ifPresent(System.out::println);
    }

    /*
        {false=530.0, true=387.5}
     */
    private static void testPartitionWithPredicate2() {
        Map<Boolean, Double> map = menu.stream().collect(
                Collectors.partitioningBy(
                        Dish::isVegetarian,
                        Collectors.averagingInt(Dish::getCalories)
                )
        );
        Optional.of(map).ifPresent(System.out::println);
    }

    /*
        {
            true: []
            false: []
        }
     */
    private static void testPartitionWithPredicate() {
        Map<Boolean, List<Dish>> map = menu.stream().collect(
                Collectors.partitioningBy(Dish::isVegetarian)
        );
        Optional.of(map).ifPresent(System.out::println);
    }
}
