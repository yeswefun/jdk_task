package com.z.p2.c22;

class MainTest00 {

    public static void main(String[] args) {
        Subject subject = new Subject();
        new BinaryObserver(subject);
        new OctalObserver(subject);

        subject.setState(1);
        subject.setState(1);

        subject.setState(8);
        subject.setState(8);
    }
}
