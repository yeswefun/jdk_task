package com.z.p1.c11;

import java.util.Arrays;
import java.util.Optional;

class TestC {

    public void test() {
        System.out.println("TestC");
        Arrays.asList(Thread.currentThread().getStackTrace())
                .stream()
                .filter(elem -> !elem.isNativeMethod())
                .forEach(elem -> Optional
                        .of(elem.getClassName() + "#" + elem.getMethodName() + ":" + elem.getLineNumber())
                        .ifPresent(System.out::println)
                );
    }
}
