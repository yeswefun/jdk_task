package com.z.p2.c32;

import java.util.stream.IntStream;

class MainTest00 {

    /*
        多线程执行上下文
            每次在 action 中都要传入 context

        解决: context 是与 线程 绑定的

        线程池: 需要清理上一次请求的 Context
     */
    public static void main(String[] args) {
        IntStream.range(1, 4).forEach(i -> new Thread(new ExecutionTask()).start());
    }
}
