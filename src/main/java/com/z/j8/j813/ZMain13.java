package com.z.j8.j813;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ZMain13 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2); // 不会退出
        executorService.execute(() -> System.out.println("test"));
    }
}
