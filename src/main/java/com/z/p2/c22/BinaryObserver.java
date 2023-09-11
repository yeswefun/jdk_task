package com.z.p2.c22;

import static com.z.util.IO.println;

class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        println("Binary: " + Integer.toBinaryString(subject.getState()));
    }
}
