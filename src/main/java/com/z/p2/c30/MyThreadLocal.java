package com.z.p2.c30;

import java.util.HashMap;
import java.util.Map;

class MyThreadLocal<T> {

    private final Map<Thread, T> storage = new HashMap<>();

    public void set(T t) {
        synchronized (this) {
            Thread key = Thread.currentThread();
            storage.put(key, t);
        }
    }

    public T get() {
        synchronized (this) {
            Thread key = Thread.currentThread();
            T t = storage.get(key);
            //此处还不太一样，我们的是每次都会返回一个新的对象
            //而 ThreadLocal 对初始值是做了缓存的，只会返回同一个对象
            if (t == null) {
                return initValue();
            }
            return t;
        }
    }

    public T initValue() {
        return null;
    }
}
