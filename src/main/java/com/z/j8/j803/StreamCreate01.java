package com.z.j8.j803;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

class StreamCreate01 {

    public static void main(String[] args) {
//        createStreamFromIterator().forEach(System.out::println);
//        createStreamFromIterator2().forEach(System.out::println);
//        createStreamFromGenerate().forEach(System.out::println);
//        createStreamFromGenerate2().forEach(System.out::println);
        createObjStreamFromGenerate().forEach(System.out::println);
    }

    private static Stream<Obj> createObjStreamFromGenerate() {
        return Stream.generate(new ObjSupplier()).limit(6);
    }

    private static Stream<Double> createStreamFromGenerate2() {
        return Stream.generate(Math::random).limit(6);
    }

    /*
        无限
     */
    private static Stream<Double> createStreamFromGenerate() {
        return Stream.generate(Math::random);
    }

    private static Stream<Integer> createStreamFromIterator2() {
        Stream<Integer> stream = Stream.iterate(0, n -> n + 2).limit(3);
        return stream;
    }

    /*
        无限
     */
    private static Stream<Integer> createStreamFromIterator() {
        Stream<Integer> stream = Stream.iterate(0, n -> n + 2);
        return stream;
    }

    static class ObjSupplier implements Supplier<Obj> {

        private int index = 0;

        private Random random = new Random(System.currentTimeMillis());

        @Override
        public Obj get() {
            return new Obj(index++, "name-" + random.nextInt(100));
        }
    }

    private static class Obj {
        private int id;
        private String name;

        public Obj(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Obj{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
