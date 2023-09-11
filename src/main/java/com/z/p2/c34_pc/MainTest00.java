package com.z.p2.c34_pc;

import java.util.stream.IntStream;

class MainTest00 {

    /*
        生产者 和 消费者 模型
     */
    public static void main(String[] args) {

        MessageQueue mq = new MessageQueue(16);

        IntStream.rangeClosed(1, 4).forEach(i -> new ConsumerThread(mq, i).start());
        IntStream.rangeClosed(1, 2).forEach(i -> new ProducerThread(mq, i).start());
    }
}
