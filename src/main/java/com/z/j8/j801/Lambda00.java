package com.z.j8.j801;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Lambda00 {

    public static void main(String[] args) {
        Comparator<Apple> byWeight = new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return (int) (o1.getWeight() - o2.getWeight());
            }
        };

        //List<Apple> list = Collections.emptyList();
        List<Apple> list = Arrays.asList(
                new Apple("green", 150),
                new Apple("red", 120),
                new Apple("green", 110));

        list.sort(byWeight);

        //lambda
        Comparator<Apple> byWeight2 = (o1, o2) -> (int)(o1.getWeight() - o2.getWeight());
    }
}
