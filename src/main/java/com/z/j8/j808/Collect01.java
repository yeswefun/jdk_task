package com.z.j8.j808;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class Collect01 {

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
//        testAveragingDouble();
//        testAveragingInt();
//        testAveragingLong();

//        testCollectingAndThen();
//        testUnmodifiable();
        testUnmodifiable2();
    }

    private static void testUnmodifiable2() {
        List<Dish> meatList = menu.stream()
                .filter(d -> Dish.Type.MEAT.equals(d.getType()))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));

//        List<Dish> unmodifiableMeatList = Collections.unmodifiableList(meatList);

        //java.lang.UnsupportedOperationException
//        meatList.add(new Dish("xxx", false, 450, Dish.Type.OTHER));

        System.out.println(meatList);
    }

    private static void testUnmodifiable() {
        List<Dish> meatList = menu.stream()
                .filter(d -> Dish.Type.MEAT.equals(d.getType()))
                .collect(Collectors.toList());

        meatList.add(new Dish("xxx", false, 450, Dish.Type.OTHER));

        System.out.println(meatList);
    }

    private static void testCollectingAndThen() {
        Optional.ofNullable(menu.stream().collect(Collectors.collectingAndThen(
                Collectors.averagingInt(Dish::getCalories),
                a -> "result: " + a
        ))).ifPresent(System.out::println);
    }

    private static void testAveragingLong() {
        Optional.ofNullable(menu.stream().collect(Collectors.averagingLong(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    private static void testAveragingInt() {
        Optional.ofNullable(menu.stream().collect(Collectors.averagingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    /*
        Collectors.averagingDouble

        public static <T> Collector<T, ?, Double>
        averagingDouble(ToDoubleFunction<? super T> mapper)
     */
    private static void testAveragingDouble() {
        Optional.ofNullable(menu.stream().collect(Collectors.averagingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);
    }
}
