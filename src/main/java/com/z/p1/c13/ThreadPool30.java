package com.z.p1.c13;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import static com.z.util.IO.println;

class ThreadPool30 extends Thread {

    private static final int DEFAUT_MAX_TASK_SIZE = 64; // 改为 8 时，线程池无法停下来

    private static final LinkedList<Runnable> taskQueue = new LinkedList<>();

    private static volatile int threadSeq = 0;
    private static final String THREAD_PREFIX = "t-";
    private static final ThreadGroup group = new ThreadGroup("POOL_GROUP");

    private int threadSize;
    private int maxTaskSize;

    public int getThreadSize() {
        return threadSize;
    }

    public int getMaxTaskSize() {
        return maxTaskSize;
    }

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
        if (destroy) {
            throw new IllegalStateException("thread pool has shutdown!");
        }
        synchronized (taskQueue) {
            if (taskQueue.size() > maxTaskSize) {
                discardPolicy.discard();
                return;
            }
            taskQueue.addLast(r);
            taskQueue.notifyAll();
        }
    }

    private static final List<WorkerThread> threadQueue = new ArrayList<>();
    private volatile boolean destroy = false;

    public boolean isDestroy() {
        return destroy;
    }

    /*
        factor
            size的取值范围
            size是动态变化的
     */
    private int min;
    private int max;
    private int active;

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getActive() {
        return active;
    }

    public ThreadPool30() {
        this(2, 8, 12, DEFAUT_MAX_TASK_SIZE, DEFAULT_DISCARD_POLICY);
    }

    public ThreadPool30(int min, int active, int max, int maxTaskSize, DiscardPolicy discardPolicy) {
        this.min = min;
        this.active = active;
        this.max = max;
        this.maxTaskSize = maxTaskSize;
        this.discardPolicy = discardPolicy;
        init();
    }

    private void init() {
        for (int i = 0; i < min; i++) {
            createWorkThread();
        }
        threadSize = min;
        start();
    }

    @Override
    public void run() {
        while (!destroy) {
            System.out.printf("----------------------------------------------------------\n" +
                            "pool, min: %d, active: %d, max: %d, size: %d, queueSize: %d\n" +
                            "----------------------------------------------------------\n",
                    min, active, max, threadSize, taskQueue.size());
            try {
                Thread.sleep(3000);
                if (taskQueue.size() > active && threadSize < active) {
                    for (int i = threadSize; i < active; i++) {
                        createWorkThread();
                    }
                    threadSize = active;
                    println("+++++++++++++++++ increase thread");
                } else if (taskQueue.size() > max && threadSize < max) {
                    for (int i = threadSize; i < max; i++) {
                        createWorkThread();
                    }
                    threadSize = max;
                    println("+++++++++++++++++ increase thread ---> max");
                }

                synchronized (threadQueue) { // in case doing submit
                    if (taskQueue.isEmpty() && threadSize > active) {
                        int releaseSize = threadSize - active;
                        //iterator在遍历的过程中是可以remove
                        for (Iterator<WorkerThread> it = threadQueue.iterator(); it.hasNext(); ) {
                            if (releaseSize <= 0) {
                                break;
                            }
                            WorkerThread t = it.next();
                            t.close();
                            t.interrupt();
                            it.remove();
                            --releaseSize;
                        }
                        threadSize = active;
                        println("------------------ decrease thread ---> " + threadSize);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void createWorkThread() {
        WorkerThread thread = new WorkerThread(group, THREAD_PREFIX + (threadSeq++));
        thread.start();
        threadQueue.add(thread);
    }

    /*
        给外面调用
     */
    public void shutdown() throws InterruptedException {

        //还有工作要做，等待一下
        while (!taskQueue.isEmpty()) {
            println("shutdown: loop-1");
            Thread.sleep(100);
        }

        synchronized (threadQueue) {
            //taskQueue移除最后一个，但是将要执行
            int initVal = threadQueue.size();
            while (initVal > 0) {
                for (WorkerThread t : threadQueue) {
                    //只有 taskQueue.wait() 时，都会出现 TaskState.BLOCKED
                    if (t.getTaskState() == TaskState.BLOCKED) {
                        //interrupt 打断 wait
                        t.interrupt();
                        //interrupt 打断 wait 之前，线程从 wait 中醒了过来并去执行任务了
                        //让它不要再进入下一次循环, taskState = TaskState.DEAD
                        t.close();
                        initVal--;
                    } else {
                        //让t有时间完成任务，否则不停循环会浪费cpu的时间片
                        Thread.sleep(10);
                    }
                }
            }
        }
        this.destroy = true;
        println("threadPool has shutdown! activeCount: " + group.activeCount());
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
                            println("--------- CLOSED");
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

            停止线程池
                submit

            参数
                min
                active
                max

                max >= active >= min
     */
    public static void main(String[] args) throws InterruptedException {
//        ThreadPool10 pool = new ThreadPool10(8, 16, DEFAULT_DISCARD_POLICY); // 拒绝策略
        ThreadPool30 pool = new ThreadPool30();

        IntStream.rangeClosed(0, 64)
                .forEach(i -> pool.submit(() -> {
                    println("task: " + i + ", " + Thread.currentThread() + " start *********************");
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {//shutdown
                        e.printStackTrace();
                    }
                    println("task: " + i + ", " + Thread.currentThread() + " end");
                }));

        println("fuck ------------------> main before");
        Thread.sleep(8000);
        pool.shutdown();
        println("fuck ------------------> main after");

//        pool.submit(new Runnable() {
//            @Override
//            public void run() {
//                println("*** run ***");
//            }
//        });
    }
}
