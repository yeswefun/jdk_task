package com.z.j8.j801;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

class Lambda_FI02 {

    private static List<String> operateApple(List<Apple> list, Function<Apple, String> func) {
        List<String> resultList = new ArrayList<>();
        for (Apple apple : list) {
            String color = func.apply(apple);
            resultList.add(color);
        }
        return resultList;
    }

    private static List<String> operateApple2(List<Apple> list, BiFunction<Long, String, String> func) {
        List<String> resultList = new ArrayList<>();
        for (Apple apple : list) {
            String tag = func.apply(apple.getWeight(), apple.getColor());
            resultList.add(tag);
        }
        return resultList;
    }

    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(
                new Apple("green", 150),
                new Apple("red", 120),
                new Apple("green", 110));

        List<String> resultList = operateApple(list, apple -> apple.getColor());
        System.out.println(resultList);
        System.out.println("------------------------------------");
        List<String> resultList2 = operateApple(list, Apple::getColor);// Method Reference
        System.out.println(resultList2);

        System.out.println("--- BiFunction");
        List<String> resultList3 = operateApple2(list, (weight, color) -> String.format("%d : %s", weight, color));
        System.out.println(resultList3);
    }
}
