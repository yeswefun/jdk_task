package com.z.p2.c24_STE;

import static com.z.util.IO.println;

/*
共享资源
 */
class Gate {
    private int counter = 0;
    private String name = "Nobody";
    private String address = "Nowhere";

    public void pass(String name, String address) {
        counter++;
        this.name = name;
        this.address = address;
        verify();
    }

    private void verify() {
        if (name.charAt(0) != address.charAt(0)) {
            println("****************** broken: " + this);
        }
    }

    @Override
    public String toString() {
        return counter + ", [" + name + "]" + address;
    }
}
