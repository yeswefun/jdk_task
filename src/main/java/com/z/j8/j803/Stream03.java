package com.z.j8.j803;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Stream03 {

    public static void main(String[] args) {

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

        List<String> dishNameList = getDishNameListByStream(menu);
        System.out.println(dishNameList);

        //stream has already been operated upon or closed
        //一个流只能遍历一次
        Stream<Dish> stream = menu.stream();
        stream.forEach(System.out::println);
        stream.forEach(System.out::println);
    }

    /*
        Source
            menu
        Sequence of elements
            dish
        Data processing operations
            filter
            sorted
            map
            collect
     */
    private static List<String> getDishNameListByStream(List<Dish> dishList) {
        return dishList.stream()
                .filter(dish -> dish.getCalories() < 400)
                //.sorted((d1, d2) -> Integer.compare(d1.getCalories(), d2.getCalories()))
                .sorted(Comparator.comparingInt(Dish::getCalories))
                //.map(dish -> dish.getName())
                .map(Dish::getName)
                .collect(Collectors.toList());

    }
}
