package com.z.p1.c13;

import java.util.LinkedList;
import java.util.stream.IntStream;

import static com.z.util.IO.println;

class ThreadPool00 {

    private static final int DEFAUT_THREAD_SIZE = 8;

    private static final LinkedList<Runnable> taskQueue = new LinkedList<>();

    private int threadSize;

    private static volatile int threadSeq = 0;
    private static final String THREAD_PREFIX = "t-";
    private static final ThreadGroup group = new ThreadGroup("POOL_GROUP");

    public ThreadPool00() {
        this(DEFAUT_THREAD_SIZE);
    }

    public ThreadPool00(int threadSize) {
        this.threadSize = threadSize;
        init();
    }

    private void init() {
        for (int i = 0; i < threadSize; i++) {
            createWorkThread();
        }
    }

    private void createWorkThread() {
        WorkerThread thread = new WorkerThread(group, THREAD_PREFIX + (threadSeq++));
        thread.start();
    }

    private enum TaskState {
        IDLE,
        RUNNING,
        BLOCKED,
        DEAD
    }

    private static class WorkerThread extends Thread {

        private volatile TaskState taskState = TaskState.IDLE;

        public WorkerThread(ThreadGroup group, String name) {
            super(group, name);
        }

        @Override
        public void run() {
            OUT:
            while (taskState != TaskState.DEAD) {
                Runnable r;
                synchronized (taskQueue) {
                    while (taskQueue.isEmpty()) {
                        try {
                            taskState = TaskState.BLOCKED;
                            taskQueue.wait();
                        } catch (InterruptedException e) {
                            break OUT;
                        }
                    }
                    r = taskQueue.removeFirst();
                }

                if (r != null) {
                    taskState = TaskState.RUNNING;
                    r.run();
                    taskState = TaskState.IDLE;
                }
                //线程完成一个任务后继续取任务
            }
        }

        public TaskState getTaskState() {
            return taskState;
        }

        public void close() {
            taskState = TaskState.DEAD;
        }
    }

    /*
        客户端调用，入队任务
     */
    public void submit(Runnable r) {
        synchronized (taskQueue) {
            taskQueue.addLast(r);
            taskQueue.notifyAll();
        }
    }

    /*
        线程池
            任务队列
     */
    public static void main(String[] args) {
        ThreadPool00 pool = new ThreadPool00();
        println("size: " + pool.threadSize);
        IntStream.rangeClosed(0, 30)
                .forEach(i -> pool.submit(() -> {
                    println("task: " + i + ", " + Thread.currentThread() + " start *********************");
                    try {
                        Thread.sleep(1_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    println("task: " + i + ", " + Thread.currentThread() + " end");
                }));
        println("size: " + pool.threadSize);
    }
}
