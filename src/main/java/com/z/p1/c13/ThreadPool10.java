package com.z.p1.c13;

import java.util.LinkedList;
import java.util.stream.IntStream;

import static com.z.util.IO.println;

class ThreadPool10 {

    //默认创建的线程数量
    private static final int DEFAUT_THREAD_SIZE = 8;

    //默认存放任务的队列容量
    private static final int DEFAUT_MAX_TASK_SIZE = 16;

    //存放任务的队列
    private static final LinkedList<Runnable> taskQueue = new LinkedList<>();

    //线程数量
    private int threadSize;

    //队列容量
    private int maxTaskSize;

    private static volatile int threadSeq = 0;
    private static final String THREAD_PREFIX = "t-";
    private static final ThreadGroup group = new ThreadGroup("POOL_GROUP");

    //--------------------------------------------------------- discard policy
    private final DiscardPolicy discardPolicy;

    public static final DiscardPolicy DEFAULT_DISCARD_POLICY = () -> {
        throw new DiscardPolicyException("Discard this task");
    };

    public static class DiscardPolicyException extends RuntimeException {
        public DiscardPolicyException(String message) {
            super(message);
        }
    }

    public interface DiscardPolicy {
        void discard() throws DiscardPolicyException;
    }

    public void submit(Runnable r) {
        synchronized (taskQueue) {
            if (taskQueue.size() > maxTaskSize) {
                discardPolicy.discard();
                return;
            }
            taskQueue.addLast(r);
            taskQueue.notifyAll();
        }
    }
    //--------------------------------------------------------- discard policy

    public ThreadPool10() {
        this(DEFAUT_THREAD_SIZE, DEFAUT_MAX_TASK_SIZE, DEFAULT_DISCARD_POLICY);
    }

    public ThreadPool10(int threadSize, int maxTaskSize, DiscardPolicy discardPolicy) {
        this.threadSize = threadSize;
        this.maxTaskSize = maxTaskSize;
        this.discardPolicy = discardPolicy;
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
        线程池
            任务队列
            拒绝策略
                抛出异常
                直接丢弃
                阻塞
                临时队列
     */
    public static void main(String[] args) {
        ThreadPool10 pool = new ThreadPool10(8, 16, DEFAULT_DISCARD_POLICY);
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
