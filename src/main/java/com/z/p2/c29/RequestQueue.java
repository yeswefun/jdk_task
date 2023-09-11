package com.z.p2.c29;

import java.util.LinkedList;

import static com.z.util.IO.println;

class RequestQueue {

    private final LinkedList<Request> queue = new LinkedList<>();

    public Request getRequest() {
        synchronized (queue) {
            while (queue.isEmpty()) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    //break;
                    println("############################################### interrupt wait in RequestQueue");
                    return null;
                }
            }
            return queue.removeFirst();
        }
    }

    public void putRequest(Request request) {
        synchronized (queue) {
            //while (queue.size() > limit) { queue.wait(); }
            queue.addLast(request);
            queue.notifyAll();
        }
    }
}
