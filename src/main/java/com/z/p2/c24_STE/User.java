package com.z.p2.c24_STE;

import static com.z.util.IO.println;

/*
资源的使用者
 */
class User extends Thread {

    private final String uname;
    private final String uaddress;
    //同一个对象
    private final Gate gate;

    public User(String name, String address, Gate gate) {
        this.uname = name;
        this.uaddress = address;
        this.gate = gate;
    }

    /*
         ****************** broken: Gate{counter=1161220, name='S', address='GuangZhou'}
            线程A: gate.pass("G", "GuangZhou");   // 执行完，还没有 verify，失去运行权
            线程B: gate.pass("S", "ShangHai");    // 开始执行到 name 赋值的位置，失去运行权
            ...
            线程A: gate.pass("G", "GuangZhou");   // 获取到执行权，输出

         ****************** broken: Gate{counter=1160885, name='B', address='BeiJing'}
            线程A: gate.pass("G", "GuangZhou");   // 执行完，还没有 verify，失去运行权
            线程B: gate.pass("S", "ShangHai");    // 开始执行到 name 赋值的位置，失去运行权
            线程A: gate.pass("G", "GuangZhou");   // 获取到执行权，执行了 verify，还没有输出，失去运行权
            线程C: gate.pass("B", "BeiJing");     // 执行到 name 和 address 赋值的位置，失去运行权
            线程A: gate.pass("G", "GuangZhou");   // 获取到执行权，执行输出
     */
    @Override
    public void run() {
        println("run " + uname);
        while (true) {
            gate.pass(uname, uaddress);
        }
    }
}
