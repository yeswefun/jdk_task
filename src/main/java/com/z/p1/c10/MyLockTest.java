package com.z.p1.c10;

import java.util.Optional;
import java.util.stream.Stream;

import static com.z.util.IO.println;

class MyLockTest {
    /*
        synchronized
            争抢锁时，
                争抢失败，阻塞
                    阻塞的线程，还没有办法被打断
                争抢成功的线程需要先执行完, 其它线程才可以再抢锁
     */
    public static void main(String[] args) throws InterruptedException {
        MyBooleanLock booleanLock = new MyBooleanLock();
        Stream.of("T1", "T2", "T3", "T4", "T5", "T6")
                .forEach(name -> new Thread(() -> {
                    try {
                        booleanLock.lock();
                        work();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally { //保证锁肯定释放
                        booleanLock.unlock();
                    }
                }, name).start());

        //问题: 锁可以随意释放
        //解决: 谁加的锁，谁释放
        Thread.sleep(500);
        println("************ main Thread unlock before");
        booleanLock.unlock();
        println("************ main Thread unlock after");
    }

    private static void work() throws InterruptedException {
        Optional.of(Thread.currentThread().getName() + " running")
                .ifPresent(System.out::println);
        Thread.sleep(1_000);
    }
}
