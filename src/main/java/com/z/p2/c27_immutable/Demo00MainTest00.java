package com.z.p2.c27_immutable;

import java.util.stream.IntStream;

class Demo00MainTest00 {

    /*
        不可变对象
            String, immutable
            基本类型的包装类
                Integer
                Long

            不可变对象 一定是 线程安全
                里面的任何属性或引用类型的属性都不能被修改
            可变对象 不一定是 线程不安全
                StringBuffer
                    synchronized

        线程安全的类
            StringBuffer 是一个线程安全的类，但不是一个不可变对象，synchronized
            StringBuilder 是一个线程不安全的类

            servlet 不是线程安全的
     */
    public static void main(String[] args) {
        //share data
        User user = new User("Jerry", "GuangDong");
        IntStream.rangeClosed(0, 6)
                .forEach(i -> new UseUserThread(String.valueOf(i), user).start());
    }
}
