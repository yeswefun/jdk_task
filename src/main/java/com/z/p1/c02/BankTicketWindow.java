package com.z.p1.c02;


import static com.z.util.IO.println;

class BankTicketWindow extends Thread {

    private final String name;
    private static final int MAX = 30;

    //每个对象都打印了30次
//    private int index = 1;

    //重复打印相同号码
    private static int index = 1;


    public BankTicketWindow(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public void run() {
        while (index <= MAX) {
            println(name + " -> num: " + (index++));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
