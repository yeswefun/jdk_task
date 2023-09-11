package com.z.p2.c36;

import java.util.stream.IntStream;

class MainTest01 {

    /*
        引入线程池，关闭线程池
     */
    public static void main(String[] args) {

        MessageHandler2 messageHandler = new MessageHandler2();

        IntStream.rangeClosed(1, 8)
                .forEach(i -> messageHandler.request(
                        new Message(String.valueOf(i))
                ));

        System.out.println("------------------ main thread finish");
        messageHandler.shutdown();
    }
}
