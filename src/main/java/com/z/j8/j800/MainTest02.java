package com.z.j8.j800;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MainTest02 {

    private interface AppleFilter {
        boolean filter(Apple apple);
    }

    private static List<Apple> findApple(List<Apple> appleList, AppleFilter appleFilter) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : appleList) {
            if (appleFilter.filter(apple)) {
                list.add(apple);
            }
        }
        return list;
    }

    /*
        匿名内部类
     */
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
        System.out.println(findApple(list, new AppleFilter() {
            @Override
            public boolean filter(Apple apple) {
                return "green".equals(apple.getColor()) && apple.getWeight() > 120;
            }
        }));
        System.out.println(findApple(list, new AppleFilter() {
            @Override
            public boolean filter(Apple apple) {
                return "red".equals(apple.getColor()) && apple.getWeight() < 150;
            }
        }));
    }
}
