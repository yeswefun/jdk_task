package com.z.j8.j813;


import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

class FutureAction2 {

    public static void main(String[] args) throws InterruptedException {
/*        Future<String> future = invoke(() -> {
            try {
                Thread.sleep(1000);
                return "finished";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "error";
            }
        });

        System.out.println(future.get());

        Thread.sleep(1000);

        System.out.println(future.get());*/

        System.out.println("--- start");
        String result = block(() -> {
            try {
                Thread.sleep(1000);
                return "finished";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "error";
            }
        });
        System.out.println(result);
        System.out.println("--- stop");
    }

    private static <T> T block(Callable<T> callable) {
        return callable.action();
    }

    private static <T> Future<T> invoke(Callable<T> callable) {

        AtomicReference<T> result = new AtomicReference<>();
        AtomicBoolean done = new AtomicBoolean(false);

        Thread thread = new Thread(() -> {
            T value = callable.action();
            result.set(value);
            done.set(true);
        });

        thread.start();

        Future<T> future = new Future<T>() {
            @Override
            public T get() {
                return result.get();
            }

            @Override
            public boolean isDone() {
                return done.get();
            }
        };

        return future;
    }

    private interface Future<T> {

        T get();

        boolean isDone();
    }

    private interface Callable<T> {
        T action();
    }
}
