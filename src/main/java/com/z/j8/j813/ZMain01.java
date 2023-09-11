package com.z.j8.j813;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

class ZMain01 {

    public static void main(String[] args) {

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

        future.setCompletable(new Completable<String>() {
            @Override
            public void complete(String s) {
                System.out.println(s);
            }

            @Override
            public void exception(Throwable t) {
                System.out.println(t.getMessage());
            }
        });

        System.out.println("--- end");
    }

    private static <T> Future<T> invoke(Callable<T> callable) {

        AtomicReference<T> result = new AtomicReference<>();
        AtomicBoolean done = new AtomicBoolean(false);

        Future<T> future = new Future<T>() {

            private Completable<T> completable;

            @Override
            public T get() {
                return result.get();
            }

            @Override
            public boolean isDone() {
                return done.get();
            }

            @Override
            public void setCompletable(Completable<T> completable) {
                this.completable = completable;
            }

            @Override
            public Completable<T> getCompletable() {
                return completable;
            }
        };

        Thread thread = new Thread(() -> {
            try {
                T value = callable.action();
                result.set(value);
                done.set(true);

                if (future.getCompletable() != null) {
                    future.getCompletable().complete(value);
                }
            } catch (Throwable t) {
                if (future.getCompletable() != null) {
                    future.getCompletable().exception(t);
                }
            }
        });

        thread.start();

        return future;
    }

    private interface Future<T> {

        T get();

        boolean isDone();

        void setCompletable(Completable<T> completable);

        Completable<T> getCompletable();
    }

    private interface Completable<T> {

        void complete(T t);

        void exception(Throwable t);
    }

    private interface Callable<T> {
        T action();
    }
}
