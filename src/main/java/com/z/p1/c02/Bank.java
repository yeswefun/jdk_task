package com.z.p1.c02;

class Bank {

    /*
        每个 TicketWindow 都有一个单独的 index
     */
    public static void main(String[] args) {
        BankTicketWindow t1 = new BankTicketWindow("win-1");
        t1.start();

        BankTicketWindow t2 = new BankTicketWindow("win-2");
        t2.start();

        BankTicketWindow t3 = new BankTicketWindow("win-3");
        t3.start();
    }
}
