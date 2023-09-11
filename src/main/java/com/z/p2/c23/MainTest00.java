package com.z.p2.c23;

import java.util.Arrays;

class MainTest00 {
    /*
        借助 观察者设计模式 监控线程状态
     */
    public static void main(String[] args) {
        new ThreadLifecycleObserver().concurrentQuery(Arrays.asList("1", "2", "3"));
    }
}
