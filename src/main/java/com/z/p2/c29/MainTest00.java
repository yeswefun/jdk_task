package com.z.p2.c29;

class MainTest00 {

    /*
        Guarded suspension

        手上有东西要做，其它事情先放一放(queue)，
        等我做完之后再处理(queue里面的事件)

        client -> putRequest
            RequestQueue
                server -> getRequest
     */
    public static void main(String[] args) throws InterruptedException {

        RequestQueue queue = new RequestQueue();

        new ClientThread(queue, "Jerry").start();

        ServerThread serverThread = new ServerThread(queue);
        serverThread.start();

//        serverThread.join(); // 停止不了 serverThread
//        Thread.sleep(6_000);
        Thread.sleep(1_000);
        System.out.println("close - before");
        serverThread.close();
        System.out.println("close - after");
    }
}
