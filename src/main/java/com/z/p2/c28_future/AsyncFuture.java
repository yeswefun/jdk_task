package com.z.p2.c28_future;

class AsyncFuture<T> implements Future<T> {

    private volatile boolean done = false;

    private T result;

    /*
        done方法的调用方 不一定与 get方法的调用方 在同一线程
     */
    public void done(T result) {
        synchronized (this) {
            this.result = result;
            this.done = true;
            this.notifyAll();
        }
    }

    /*
        如果任务还没有完成，调用 get 方法还是会出现阻塞
     */
    @Override
    public T get() throws InterruptedException {
        synchronized (this) {
            while (!done) {
                wait();
            }
        }
        return result;
    }
}
