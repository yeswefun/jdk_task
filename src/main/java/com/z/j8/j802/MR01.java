package com.z.j8.j802;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

class MR01 {

    public static void main(String[] args) {

        int value = Integer.parseInt("123");

        Function<String, Integer> func = Integer::parseInt;
        Integer num = func.apply("123");
        System.out.println(num);

        BiFunction<String, Integer, Character> charAt = String::charAt; // 类
        Character c = charAt.apply("Java", 2);
        System.out.println(c);

        String str = "Java";
        Function<Integer, Character> charAt2 = str::charAt; // 对象
        Character c2 = charAt2.apply(2);
        System.out.println(c2);

        /*
            Integer 与 Long 不能自动转换，它们的共同父类为 Object
         */
        BiFunction<String, Long, Apple> fnNew = Apple::new; // 构造
        Apple apple = fnNew.apply("green", 666L);
        System.out.println(apple);

        TripleFunction<String, String, Long, ComplexApple> fnNew2 = ComplexApple::new;
        ComplexApple apple2 = fnNew2.apply("fuji apple", "red", 888L);
        System.out.println(apple2);

        List<Apple> apples = Arrays.asList(
                new Apple("red", 200),
                new Apple("red", 100),
                new Apple("red", 120)
        );
        System.out.println(apples);
        apples.sort(Comparator.comparing(Apple::getWeight)); // think
        System.out.println(apples);

//        Objects.requireNonNull(o);
    }
}
