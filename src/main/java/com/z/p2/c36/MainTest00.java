package com.z.p2.c36;

import java.util.stream.IntStream;

class MainTest00 {

    /*
        Thread-Per-Message
            每个请求启动一个线程
     */
    public static void main(String[] args) {

        MessageHandler messageHandler = new MessageHandler();

        //连续调用 messageHandler 8次，每次都开一个新线程处理
        IntStream.rangeClosed(1, 8)
                .forEach(i -> messageHandler.request(
                        new Message(String.valueOf(i))
                ));
    }
}
