package com.z.p2.c28_future;

import java.util.function.Consumer;

class FutureService {

    /*
        T Future#get()

        T FutureTask#call()
     */
    public <T> Future<T> submit(FutureTask<T> task) {
        AsyncFuture<T> asyncFuture = new AsyncFuture<>();
        new Thread(() -> {
            T result = task.call();
            asyncFuture.done(result);
        }).start();
        return asyncFuture;
    }

    // 传入回调后，可以返回 void
    public <T> void submit(FutureTask<T> task, Consumer<T> consumer) {
        AsyncFuture<T> asyncFuture = new AsyncFuture<>();
        new Thread(() -> {
            T result = task.call();
            asyncFuture.done(result);
            consumer.accept(result);
        }).start();
    }
}
