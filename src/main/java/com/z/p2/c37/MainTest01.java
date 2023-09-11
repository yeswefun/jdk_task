package com.z.p2.c37;

import java.io.IOException;

class MainTest01 {

    public static void main(String[] args) throws InterruptedException, IOException {
        AppServer server = new AppServer(6666);
        server.start();

        Thread.sleep(6_000);
        server.shutdown();
    }
}
