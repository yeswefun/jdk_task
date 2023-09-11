package com.z.p2.c38;

import static com.z.util.IO.println;

class Request {

    private final String name;

    private final int number;

    public Request(String name, int number) {
        this.name = name;
        this.number = number;
    }

    /*
        做什么
     */
    public void execute() {
        println(Thread.currentThread().getName() + " ------------------> " + this);
    }

    @Override
    public String toString() {
        return name + "@" + number;
    }
}
