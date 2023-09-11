package com.z.p2.c38;

import java.util.Arrays;

import static com.z.util.IO.println;

/*
循环队列
 */
class Channel {

    private static final int MAX_REQUEST = 16;

    private final Request[] requestQueue;

    private int head;

    private int tail;

    private int count;

    private final WorkerThread[] workerPool;

    public Channel(int workerSize) {
        head = tail = count = 0;
        requestQueue = new Request[MAX_REQUEST];
        workerPool = new WorkerThread[workerSize];
        init();
    }

    private void init() {
        for (int i = 0; i < workerPool.length; i++) {
            workerPool[i] = new WorkerThread("W-" + i, this);
        }
    }

    public void start() {
        Arrays.asList(workerPool).forEach(WorkerThread::start);
    }

    public synchronized void put(Request request) {
        while (count >= requestQueue.length) {
            try {
                wait();
            } catch (InterruptedException e) {
                //出现异常怎么办呢?
                e.printStackTrace();
            }
        }
        count++;
        requestQueue[tail] = request;
        tail = (tail + 1) % requestQueue.length;
        notifyAll();
    }

    public synchronized Request take() {
        while (count == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                //出现异常怎么办呢?
                e.printStackTrace();
            }
        }
        count--;
        Request request = requestQueue[head];
        head = (head + 1) % requestQueue.length;
        notifyAll();
        println("request: " + request);
        return request;
    }
}
