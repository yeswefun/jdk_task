package com.z.p1.c10;

import java.util.Optional;
import java.util.stream.Stream;

import static com.z.util.IO.println;

class MyLockTest2 {
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
                        booleanLock.lock(200);
                        work();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (MyLock.TimeoutException e) {
                        //e.printStackTrace();
                        println(e.getMessage());
                    } finally {
                        //问题: 超时，争抢不到锁的线程也会调用
                        booleanLock.unlock();
                    }
                }, name).start());
    }

    private static void work() throws InterruptedException {
        Optional.of(Thread.currentThread().getName() + " running")
                .ifPresent(System.out::println);
        Thread.sleep(2_000);
    }
}
