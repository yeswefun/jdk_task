package com.z.j8.j810;

import java.util.Objects;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

class Spliterator01 {

    private static final String TEXT = "haha\nhehe\nhihi\njiji\njaja";

    public static void main(String[] args) {
        MyTextSpliterator myTextSpliterator = new MyTextSpliterator(TEXT);

        System.out.println("--------- 1");
        Optional.of(myTextSpliterator.stream().count()).ifPresent(System.out::println);

        System.out.println("--------- 2");
        myTextSpliterator.stream().forEach(System.out::println);

        System.out.println("--------- 3");
        myTextSpliterator.stream().filter(s -> !s.equals("")).forEach(System.out::println);

        IntStream intStream = IntStream.rangeClosed(0, 10);

//声明式编程
//        intStream.parallel()
//                .filter(null)
//                .sequential()
//                .map(null)
//                .parallel() // 以最后一个为主
//                .forEach(null);
    }

    static class MyTextSpliterator {

        private final String[] data;

        public MyTextSpliterator(String text) {
            Objects.requireNonNull(text, "text cannot be null");
            this.data = text.split("\n");
        }

        public Stream<String> stream() {
            return StreamSupport.stream(new MyTextSpliterator.MySpliterator(), false);
        }

        public Stream<String> parallelStream() {
            return StreamSupport.stream(new MyTextSpliterator.MySpliterator(), true);
        }

        private class MySpliterator implements Spliterator<String> {

            private int start, end;

            public MySpliterator() {
                start = 0;
                end = MyTextSpliterator.this.data.length - 1;
            }

            public MySpliterator(int start, int end) {
                this.start = start;
                this.end = end;
            }

            //stream
            @Override
            public boolean tryAdvance(Consumer<? super String> action) {
                if (start <= end) {
                    action.accept(MyTextSpliterator.this.data[start++]);
                    return true;
                }
                return false;
            }

            //parallelStream
            @Override
            public Spliterator<String> trySplit() {
                int mid = (end - start) / 2;
                if (mid <= 1) {
                    return null;
                }
                int left = start;
                int right = start + mid;
                start = start + mid + 1;
                return new MySpliterator(left, right);
            }

            @Override
            public long estimateSize() {
                return end - start;
            }

            @Override
            public long getExactSizeIfKnown() {
                //return Spliterator.super.getExactSizeIfKnown();
                return estimateSize();
            }

            @Override
            public int characteristics() {
                return IMMUTABLE | SIZED | SUBSIZED;
            }
        }
    }
}
