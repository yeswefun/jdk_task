package com.z.p1.c11;

class Demo20 {

    public static void main(String[] args) {
        test(1, 0);
    }

    private static void test(int a, int b) {
        Thread t = new Thread(() -> {
            //只能try-catch, 没有办法抛出来(因为接口的方法签名约束了)
            //抛出来也捕获不了呀，不是吗?
            try {
                Thread.sleep(1000);
                int ret = a / b;
                System.out.println(ret);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //核心逻辑，zh
        t.setUncaughtExceptionHandler((thread, error) -> {
            System.out.println(thread);
            System.out.println(error);
        });

        t.start();
        System.out.println("****************** main Thread finish");
    }
}
