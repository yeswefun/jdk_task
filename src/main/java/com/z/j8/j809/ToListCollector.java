package com.z.j8.j809;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/*
T, A, R
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

    private void log(String msg) {
        System.out.println(Thread.currentThread() + " -> " + msg);
    }

    @Override
    public Supplier<List<T>> supplier() {
        log("supplier");
        //return () -> new ArrayList();
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        log("accumulator");
        //return (list, item) -> list.add(item);
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        log("combiner");
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        log("finisher");
        //return Function.identity();
        return t -> t;
    }

    @Override
    public Set<Characteristics> characteristics() {
        log("characteristics");
        return Collections.unmodifiableSet(
                EnumSet.of(
                        Characteristics.IDENTITY_FINISH,
                        Characteristics.CONCURRENT
                )
        );
    }
}
