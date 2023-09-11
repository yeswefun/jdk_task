package com.z.j8.j802;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

class MR00 {

    private static <T> void useConsumer(Consumer<T> consumer, T t) {
        consumer.accept(t);
    }

    public static void main(String[] args) {
        Consumer<String> consumer = s -> System.out.println(s);
        useConsumer(consumer, "Java");

        useConsumer(s -> System.out.println(s), "Kotlin");

        useConsumer(System.out::println, "Python");

        List<Apple> apples = Arrays.asList(
                new Apple("red", 200),
                new Apple("red", 100),
                new Apple("red", 120)
        );
        System.out.println(apples);
        //int, apple, apple
        apples.sort((a1, a2) -> (int)(a1.getWeight() - a2.getWeight()));
        System.out.println(apples);

        //Lambda的方法推导
        System.out.println("--- 1");
        apples.stream().forEach(apple -> System.out.println(apple));
        System.out.println("--- 2");
        apples.stream().forEach(System.out::println);
        System.out.println("--- 3");
        apples.forEach(System.out::println);
    }
}
