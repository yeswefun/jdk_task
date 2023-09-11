package com.z.j8.j808;

import java.util.*;
import java.util.stream.Collectors;

class Collect00 {

    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(
                new Apple("green", 150),
                new Apple("red", 120),
                new Apple("green", 110),
                new Apple("red", 80),
                new Apple("green", 100),
                new Apple("blue", 180),
                new Apple("green", 140)
        );

        List<Apple> greenAppleList = list.stream()
                .filter(a -> "green".equals(a.getColor()))
                .collect(Collectors.toList());

        Optional.ofNullable(greenAppleList).ifPresent(System.out::println);

        Optional.ofNullable(groupBy(list)).ifPresent(System.out::println);

        Optional.ofNullable(groupBy2(list)).ifPresent(System.out::println);
        Optional.ofNullable(groupBy3(list)).ifPresent(System.out::println);
    }

    private static Map<String, List<Apple>> groupBy3(List<Apple> apples) {
        return apples.stream().collect(Collectors.groupingBy(Apple::getColor));
    }
    private static Map<String, List<Apple>> groupBy2(List<Apple> apples) {
        Map<String, List<Apple>> map = new HashMap<>();
        //apples.forEach(a -> {});
        apples.stream().forEach(a -> {
            List<Apple> targetList = Optional.ofNullable(map.get(a.getColor()))
                    .orElseGet(() -> {
                        ArrayList<Apple> list = new ArrayList<>();
                        map.put(a.getColor(), list);
                        return list;
                    });
            targetList.add(a);
        });
        //map = null; // map -> final
        return map;
    }
    private static Map<String, List<Apple>> groupBy(List<Apple> apples) {
        Map<String, List<Apple>> map = new HashMap<>();
        for (Apple apple : apples) {
            List<Apple> list = map.get(apple.getColor());
            if (list == null) {
                list = new ArrayList<>();
                map.put(apple.getColor(), list);
            }
            list.add(apple);
        }
        return map;
    }
}
