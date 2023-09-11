package com.z.p2.c37;

import java.io.*;
import java.net.Socket;

import static com.z.util.IO.println;

class AppClientHandler implements Runnable {

    private final Socket client;

    private volatile boolean running = true;

    public AppClientHandler(Socket client) {
        this.client = client;
    }

    /*
        Balking
     */
    public void stop() {
        if (!running) {
            return;
        }

        running = false;

        try {
            //if (!client.isClosed())
            client.close();
        } catch (IOException e) {
            //ignore or log
        }
    }

    @Override
    public void run() {
        // 对于客户端是输入，对于服务端是输出
        // 对于客户端是输出，对于服务端是输入
        //半双工，现在
        //全双式，输入和输出分别为于不同线程
        try (InputStream is = client.getInputStream();
             OutputStream os = client.getOutputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(is));
             PrintWriter pw = new PrintWriter(os)) {

            while (running) {
                String msg = br.readLine();
                println("client -> [" + msg + "]");
                pw.write("[" + msg + "]" + System.lineSeparator());
                pw.flush();
            }
        } catch (IOException e) {
            println("--------------------- client disconnected");
            println("client.isClosed(): " + client.isClosed()); // true
            running = false; // 出现异常是否表示 client == null
        } finally {
            stop();
        }
    }
}
