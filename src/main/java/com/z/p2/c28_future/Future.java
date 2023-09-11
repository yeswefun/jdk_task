package com.z.p2.c28_future;

interface Future<T> {

    T get() throws InterruptedException;
}
