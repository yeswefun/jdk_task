package com.z.p2.c24_STE;

import static com.z.util.IO.println;

/*
共享资源
 */
class Gate2 {
    private int counter = 0;
    private String name = "Nobody";
    private String address = "Nowhere";

    /*
        只有一个线程能够通过
        写操作
     */
    public synchronized void pass(String name, String address) {
        counter++;
        this.name = name;
        this.address = address;
        verify();
        //pass方法调用完之后才会释放 this
    }

    private void verify() {
        if (name.charAt(0) != address.charAt(0)) {
            println("****************** broken: " + this);
        }
    }

    /*
        读操作
     */
    @Override
    public synchronized String toString() {
        return "Gate{" +
                "counter=" + counter +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
