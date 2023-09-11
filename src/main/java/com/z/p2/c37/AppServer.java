package com.z.p2.c37;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
Thread-Per-Message
ThreadPool
 */
class AppServer extends Thread {

    private static final int DEFAULT_PORT = 65432;

    private final int port;

    private volatile boolean start = true;

    private final List<AppClientHandler> clientHandlerList = new ArrayList<>();

    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    private ServerSocket server;

    public AppServer() {
        this(DEFAULT_PORT);
    }

    public AppServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(port);
            while (start) {
                Socket client = server.accept();
                AppClientHandler handler = new AppClientHandler(client);
                executor.submit(handler);
                clientHandlerList.add(handler);
            }
        } catch (IOException e) {
            //将 CheckException 转化为 UncheckException
            throw new RuntimeException(e);
        } finally {
            dispose();
        }
    }

    private void dispose() {
        clientHandlerList.stream().forEach(AppClientHandler::stop);
        executor.shutdown();
        //server.close();
    }

    public void shutdown() throws IOException {
        start = false;
        interrupt();
        server.close();
    }
}
