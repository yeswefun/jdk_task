package com.z.p3.c51;

class JITTest00 {

    private static boolean init = false;

    /*
        编译器优化了
     */
    public static void main(String[] args) throws InterruptedException {
        new Thread() {
            @Override
            public void run() {
                //while (!init) {}
                //编译器 -> while(true) {}
                //编译器认为不会对 init 进行改变
                while (!init) { // while(!false){} //因为没有修改 init 的语句
                }
            }
        }.start();

        Thread.sleep(200);

        new Thread() {
            @Override
            public void run() {
                init = true;
                System.out.println("********************* set init to true");
            }
        }.start();
    }
}
