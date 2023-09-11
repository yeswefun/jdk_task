package com.z.p2.c29;

import java.util.Random;

import static com.z.util.IO.println;

class ServerThread extends Thread {

    private final RequestQueue queue;

    private final Random random;

    //closed
    private volatile boolean flag = true;

    public ServerThread(RequestQueue queue) {
        this.queue = queue;
        random = new Random(System.currentTimeMillis());
    }

    /*
        中断
            flag
            interrupt -> queue.wait
            interrupt -> Thread.sleep
     */
    @Override
    public void run() {
        while (flag) {
            println("*************************** flag: " + flag);
            Request request = queue.getRequest();
            if (request == null) {
                println("###########################  empty request");
                continue;
            }
            println("server -> " + request.getValue());
            try {
                Thread.sleep(random.nextInt(500));
            } catch (InterruptedException e) {
                println("@@@@@@@@@@@@@@@@@@@@@@@@@@@  interrupt sleep in ServerThread");
                return;
            }
        }
        println("--------------------------- ServerThread close");
    }

    public void close() {
        flag = false;
        interrupt();
    }
}
