package com.z.j8.j801;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

class Lambda_FI00 {

    private static List<Apple> filterApple(List<Apple> appleList, Predicate<Apple> predicate) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : appleList) {
            if (predicate.test(apple)) {
                list.add(apple);
            }
        }
        return list;
    }

    private static List<Apple> filterByWeight(List<Apple> appleList, LongPredicate predicate) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : appleList) {
            if (predicate.test(apple.getWeight())) {
                list.add(apple);
            }
        }
        return list;
    }

    private static List<Apple> filterByWeightAndColor(List<Apple> appleList, BiPredicate<Long, String> predicate) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : appleList) {
            if (predicate.test(apple.getWeight(), apple.getColor())) {
                list.add(apple);
            }
        }
        return list;
    }

    public static void main(String[] args) {

        List<Apple> list = Arrays.asList(
                new Apple("green", 150),
                new Apple("red", 120),
                new Apple("green", 110));

        //Predicate<Apple>
        List<Apple> resultList = filterApple(list, apple -> apple.getWeight() > 115);
        System.out.println(resultList);

        //LongPredicate
        List<Apple> resultList2 = filterByWeight(list, weight -> weight > 115);
        System.out.println(resultList2);

        //BiPredicate<Long, Weight>
        List<Apple> resultList3 = filterByWeightAndColor(list, (weight, color) -> weight > 100 && "red".equals(color));
        System.out.println(resultList3);
    }
}
