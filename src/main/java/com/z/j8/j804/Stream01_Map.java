package com.z.j8.j804;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Stream01_Map {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 6, 5);

        List<Integer> mapList = list.stream().map(i -> i * 2).collect(Collectors.toList());
        System.out.println(mapList);

        List<Dish> menu = Arrays.asList(
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

        List<String> dishNameList = menu.stream()
                //.map(dish -> dish.getName())
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println(dishNameList);

        System.out.println("---------------");
        menu.stream()
                .map(Dish::getName)
                .forEach(System.out::println);
    }
}
