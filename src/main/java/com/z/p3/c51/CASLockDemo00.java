package com.z.p3.c51;

class CASLockDemo00 {

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread() {
                @Override
                public void run() {
                    test();
                }
            }.start();
        }
    }

    /*
        争抢锁的时候，争抢失败后去其它事情，然后再来争抢
     */
    private static void test() {
        synchronized (CASLockDemo00.class) {
            System.out.println(Thread.currentThread().getName() + " get lock");
            try {
                Thread.sleep(300_0000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
