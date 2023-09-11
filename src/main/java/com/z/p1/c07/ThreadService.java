package com.z.p1.c07;

import static com.z.util.IO.println;

class ThreadService {

    private Thread executeThread;
    private boolean finished = false;

    /*
        让执行线程中断，执行线程中断，与执行线程在同一个线程组内的其它守护线程也会结束
            前提是这个线程组内的执行线程全部结束
     */
    public void execute(Runnable task) {
        //主线程(如果是在主线程中调用的话)
        executeThread = new Thread() {
            @Override
            public void run() {
                //子线程
                Thread t = new Thread(task);
                t.setDaemon(true); // t是守护线程
                t.start();
                try {
                    t.join(); // executeThread被阻塞，直到 task 完成为止
                } catch (InterruptedException e) {
                    println("task timeout -> catch");
                } finally {
                    finished = true;
                }
            }
        };
        println("executeThread.start(); before");
        // Thread.currentThread()执行完execute方法，
        // 而 executeThread 是否执行完取决于run方法是否结束
        // executeThread为非守护线程
        executeThread.start();
        println("executeThread.start(); after");
    }

    /*
        在主线程调用
     */
    public void shutdown(long millis) {
        long base = System.currentTimeMillis();
        while (!finished) {
            if (System.currentTimeMillis() - base > millis) {
                println("task timeout");
                executeThread.interrupt();
                break;
            }
            //下面的 Sleep 会导致忙轮询，浪费cpu时间片，
            //改成 wait 的方式，参考 Thread.join(millis)
            try {
                //TODO: 此处 sleep 有什么作用呢，好像不起作用呀 - ThreadSleepTest
                //Thread.sleep(1); // currentThread()休眠 1 ms
                System.out.println("executeThread.sleep(1) - start");
                executeThread.sleep(1); // executeThread 休眠 1 ms
                System.out.println("executeThread.sleep(1) - end");
            } catch (InterruptedException e) {
                println("执行线程被打断");
                break;
            }
        }
        finished = false;
    }
}
