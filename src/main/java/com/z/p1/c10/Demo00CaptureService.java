package com.z.p1.c10;

import java.util.*;

class Demo00CaptureService {

    //表示一个任务
    private static class Control {
    }

    //表示一个任务队列
    private static final LinkedList<Control> CTL = new LinkedList<>();

    private static final int MAX_WORKER = 4;

    /*
        最多同时运行线程数为 N
        需要执行的任务数为 M
        M > N
            如果正在运行的线程数量 小于 最大最多同时运行线程数
                添加到执行队列
            如果正在运行的线程数量 大于 最大最多同时运行线程数
                阻塞
     */
    public static void main(String[] args) {

        List<Thread> list = new ArrayList<>();

        Arrays.asList("W0", "W1", "W2", "W3", "W4", "W5", "W6", "W7")
                .stream()
                .map(Demo00CaptureService::createCaptureThread)
                .forEach(t -> {
                    t.start();
                    list.add(t);
                });

        list.stream().forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Optional.of("------------------------> main Thread").ifPresent(System.out::println);
    }

    /*
        FIFO
     */
    private static Thread createCaptureThread(String name) {
        return new Thread(() -> {

            Optional.of("-> " + Thread.currentThread().getName() + " start").ifPresent(System.out::println);

            synchronized (CTL) {
                while (CTL.size() > MAX_WORKER) {
                    try {
                        CTL.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //插入队尾
                CTL.addLast(new Control());
            }

            //工作
            Optional.of("+++> " + Thread.currentThread().getName() + " work").ifPresent(System.out::println);
            try {
                Thread.sleep(3_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (CTL) {
                Optional.of("------------> " + Thread.currentThread().getName() + " end").ifPresent(System.out::println);
                //移除队头
                CTL.removeFirst();
                CTL.notifyAll();
            }

        }, name);
    }
}
