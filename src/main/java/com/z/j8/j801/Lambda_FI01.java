package com.z.j8.j801;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

class Lambda_FI01 {

    private static void operateApple(List<Apple> list, Consumer<Apple> consumer) {
        for (Apple apple : list) {
            consumer.accept(apple);
        }
    }

    private static void operateApple2(List<Apple> list, BiConsumer<Long, String> consumer) {
        for (Apple apple : list) {
            consumer.accept(apple.getWeight(), apple.getColor());
        }
    }

    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(
                new Apple("green", 150),
                new Apple("red", 120),
                new Apple("green", 110));

        operateApple(list, apple -> System.out.println(apple));
        System.out.println("------------------------------------");
        operateApple(list, System.out::println); // Method Reference

        System.out.println("--- BiConsumer");
        operateApple2(list, (weight, color) -> System.out.println(String.format("%d : %s", weight, color)));
    }
}
