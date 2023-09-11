package com.z.p2.c34_pc;

import java.util.LinkedList;

/*
MessageQueue 不是一个 不可变类，但却是一个线程安全的
 */
class MessageQueue {

    private final LinkedList<Message> queue;

    private static final int DEFAULT_MAX_LIMIT = 32;

    private final int limit;

    public MessageQueue() {
        this(DEFAULT_MAX_LIMIT);
    }

    public MessageQueue(int limit) {
        this.limit = limit;
        queue = new LinkedList<>();
    }

    public void put(Message msg) throws InterruptedException {
        synchronized (queue) {
            while (queue.size() > limit) {
                queue.wait();
            }
            queue.addLast(msg);
            queue.notifyAll();
        }
    }

    public Message take() throws InterruptedException {
        synchronized (queue) {
            while (queue.isEmpty()) {
                queue.wait();
            }
            Message msg = queue.removeFirst();
            queue.notifyAll();
            return msg;
        }
    }

    //无锁操作
    public int getQueueMaxLimit() {
        return limit;
    }

    public int getQueueSize() {
        synchronized (queue) {
            return queue.size();
        }
    }
}
