package com.z.p1.c11;

class Demo02 {
    /*
        linux下钩子函数监听程序崩溃回调

        JVM 存在 client 和 server 两种模式

        $ nohup java [-cp .] Demo &
        $ tail -f nohup.out
     */
    public static void main(String[] args) {

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("ooh! Error occured!");
            notifyAndRelease();
        }));

        int index = 0;
        while (true) {
            try {
                Thread.sleep(500);
                System.out.println("index: " + (index++));
            } catch (InterruptedException e) {
                //ignore
            }
            //模拟抛出异常
            if (index > 10) {
                throw new RuntimeException("Error");
            }
        }
    }

    private static void notifyAndRelease() {
        System.out.println("********************* notifyAndRelease before");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("********************* notifyAndRelease after");
    }
}
