package com.z.j8.j800;

class MainTest04 {

    /*
        jps
        jps -l
        jstat -gcutil PID 1000 10
     */
    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        }).start();
    }
}
