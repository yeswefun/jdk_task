package com.z.p2.c24_STE;

import static com.z.util.IO.println;

/*
资源的使用者
 */
class User2 extends Thread {

    private final String uname;
    private final String uaddress;
    private final Gate2 gate;

    public User2(String name, String address, Gate2 gate) {
        this.uname = name;
        this.uaddress = address;
        this.gate = gate;
    }

    @Override
    public void run() {
        println("run " + uname);
        while (true) {
            gate.pass(uname, uaddress);
        }
    }
}
