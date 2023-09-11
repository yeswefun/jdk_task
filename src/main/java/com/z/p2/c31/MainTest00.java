package com.z.p2.c31;

import java.util.stream.IntStream;

class MainTest00 {

    /*
        多线程执行上下文
            每次在 action 中都要传入 context
     */
    public static void main(String[] args) {
        IntStream.range(1, 4)
                .forEach(i -> new Thread(new ExecutionTask()).start());
    }
}
