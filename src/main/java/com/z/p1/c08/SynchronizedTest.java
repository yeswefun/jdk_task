package com.z.p1.c08;

class SynchronizedTest {

    private static final Object LOCK = new Object();

    /*
        jps

        jstack PID

        javap -c TicketWindowRunnableFix.class

        javap --help
     */
    public static void main(String[] args) {
        Runnable r = () -> {
            synchronized (LOCK) {
                try {
                    Thread.sleep(1000_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(r).start();
        new Thread(r).start();
        new Thread(r).start();
    }
}
