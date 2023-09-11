package com.z.j8.j809;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Collect11 {

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
//        testSummingDouble();
//        testSummingLong();
//        testSummingInt();

//        testToCollection();
//        testToConcurrentMap();
//        testToConcurrentMap2();
//        testToConcurrentMap3();

//        testToList();
//        testToSet();
//        testToMap();
//        testToMap2();
        testToMap3();
//        hashTable 是 线程安全的
    }

    /*
        HashMap 并不是线程安全的
        转成线程安全的
     */
    private static void testToMap3() {
        Map<Dish.Type, Long> map = menu.stream()
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toMap(Dish::getType, v -> 1L, (a, b) -> a + b),
                                Collections::synchronizedMap
                        )
                );
        Optional.of(map).ifPresent(v -> {
            System.out.println(v);
            System.out.println(v.getClass());
        });
    }

    private static void testToMap2() {
        Map<Dish.Type, Long> map = menu.stream()
                .collect(Collectors.toMap(
                            Dish::getType, v -> 1L, (a, b) -> a + b, Hashtable::new
                        )
                );

        Optional.of(map).ifPresent(v -> {
            System.out.println(v);
            System.out.println(v.getClass());
        });
    }

    private static void testToMap() {
        Map<Dish.Type, Long> map = menu.stream()
                .collect(Collectors.toMap(Dish::getType, v -> 1L, (a, b) -> a + b));

        Optional.of(map).ifPresent(v -> {
            System.out.println(v);
            System.out.println(v.getClass());
        });
    }

    private static void testToSet() {
        Set<Dish> set = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toSet());

        Optional.of(set).ifPresent(v -> {
            System.out.println(v);
            System.out.println(v.getClass());
        });
    }

    private static void testToList() {
        List<Dish> list = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());

        Optional.of(list).ifPresent(v -> {
            System.out.println(v);
            System.out.println(v.getClass());
        });
    }

    private static void testToConcurrentMap3() {
        ConcurrentMap<Dish.Type, Long> map = menu.stream().collect(
                Collectors.toConcurrentMap(
                        Dish::getType, v -> 1L, (a, b) -> a + b, ConcurrentSkipListMap::new
                )
        );
        System.out.println(map);
        System.out.println(map.getClass());
    }

    /*
        Type: Total
        {FISH=2, OTHER=4, MEAT=3}
     */
    private static void testToConcurrentMap2() {
        ConcurrentMap<Dish.Type, Long> map = menu.stream().collect(
                Collectors.toConcurrentMap(
                        Dish::getType, v -> 1L, (a, b) -> a + b
                )
        );
        System.out.println(map);
        System.out.println(map.getClass());
    }

    /*
        calories : name
     */
    private static void testToConcurrentMap() {
        ConcurrentMap<String, Integer> map = menu.stream().collect(
                Collectors.toConcurrentMap(
                        Dish::getName, Dish::getCalories
                )
        );
        System.out.println(map);
    }

    private static void testToCollection() {
        LinkedList<Dish> list = menu.stream()
                .filter(d -> d.getCalories() > 600)
                .collect(
                        Collectors.toCollection(LinkedList::new)
                );
        System.out.println(list);
    }

    private static void testSummingInt() {
        Integer result = menu.stream()
                .collect(
                        Collectors.summingInt(Dish::getCalories)
                );
        Optional.of(result).ifPresent(System.out::println);
    }

    private static void testSummingLong() {
        Long result = menu.stream().collect(
                Collectors.summingLong(Dish::getCalories)
        );
        Optional.of(result).ifPresent(System.out::println);
    }

    private static void testSummingDouble() {
        Double result = menu.stream().collect(
                Collectors.summingDouble(Dish::getCalories)
        );
        Optional.of(result).ifPresent(System.out::println);

        Stream<Integer> integerStream = menu.stream().map(Dish::getCalories);
        IntStream intStream = integerStream.mapToInt(Integer::intValue);
        int sum = intStream.sum();
        System.out.println(sum);
    }
}
