package com.z.j8.j800;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MainTest00 {

    /*
        绿色苹果
        红色苹果
        ...
     */
    private static List<Apple> findGreenApple(List<Apple> appleList) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : appleList) {
            if ("green".equals(apple.getColor())) {
                list.add(apple);
            }
        }
        return list;
    }

    /*
        颜色
        重量
        ...
        需求变化
     */
    private static List<Apple> findApple(List<Apple> appleList, String color) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : appleList) {
            if (color.equals(apple.getColor())) {
                list.add(apple);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(
                new Apple("green", 150),
                new Apple("red", 120),
                new Apple("green", 110),
                new Apple("green", 100),
                new Apple("blue", 180),
                new Apple("green", 140)
        );
        System.out.println(findGreenApple(list));
        System.out.println(findApple(list, "red"));
    }
}
