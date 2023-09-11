package com.z.j8.j813;


import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

class FutureAction {

    public static void main(String[] args) throws InterruptedException {
        Future<String> future = invoke(() -> {
            try {
                Thread.sleep(1000);
                return "finished";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "error";
            }
        });

        System.out.println("--- start");
        System.out.println(future.get());
        System.out.println("--- stop");

        Thread.sleep(1200);

        System.out.println(future.get());
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
