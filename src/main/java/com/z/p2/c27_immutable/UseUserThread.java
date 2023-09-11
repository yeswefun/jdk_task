package com.z.p2.c27_immutable;

import static com.z.util.IO.println;

class UseUserThread extends Thread {

    private User user;

    public UseUserThread(String name, User user) {
        super(name);
        this.user = user;
    }

    @Override
    public void run() {
        while (true) {
            println(Thread.currentThread().getName() + " -> " + user.toString());
        }
    }
}
