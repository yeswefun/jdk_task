package com.z.p2.c22;

import static com.z.util.IO.println;

class OctalObserver extends Observer {

    public OctalObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        println("Octal: " + Integer.toOctalString(subject.getState()));
    }
}
