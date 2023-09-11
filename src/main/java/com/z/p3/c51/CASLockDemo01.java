package com.z.p3.c51;

class CASLockDemo01 {

    private static final CASLock LOCK = new CASLock();

    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            new Thread("t" + i) {
                @Override
                public void run() {
                    try {
                        test();
                    } catch (CASLock.GetLockFailedException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

    /*
        争抢锁的时候，争抢失败后去其它事情，然后再来争抢
     */
    private static void test() throws CASLock.GetLockFailedException, InterruptedException {
        try {
            LOCK.tryLock();
            System.out.println(Thread.currentThread().getName() + " get lock");
            Thread.sleep(2_000);
        } finally {
            LOCK.unlock();
        }
    }
}
