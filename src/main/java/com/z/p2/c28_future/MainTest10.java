package com.z.p2.c28_future;

import static com.z.util.IO.println;

class MainTest10 {

    /*
        调用后马上返回
            Future          -> 未来的凭据
            FutureTask      -> 将调用逻辑进行隔离
            FutureService   -> 桥接 Future 和 FutureTask
                Future 不需要知道 FutureTask 的存在
                FutureTask 不需要知道 Future 的存在
                调用方只需要知道 FutureService
     */
    public static void main(String[] args) throws InterruptedException {
        FutureService service = new FutureService();
        Future<String> future = service.submit(() -> {
            try {
                Thread.sleep(4_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "finish";
        });

        println("main thread do something...");
        Thread.sleep(1_000);
        println("main thread get result...before");
        println(future.get()); //此时如果任务还没有完成，就会被阻塞
        println("main thread get result...after");
    }
}
