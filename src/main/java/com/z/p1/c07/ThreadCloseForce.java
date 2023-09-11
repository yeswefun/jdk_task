package com.z.p1.c07;

class ThreadCloseForce {

    private static class Worker extends Thread {

        private boolean flag = true;

        @Override
        public void run() {
            while (flag) {
                // 思考: connection -> block -> no chance to read flag
            }
        }
    }

    /*
        如何及时停止线程?
     */
    public static void main(String[] args) throws InterruptedException {
        Worker w = new Worker();
        w.start();
        Thread.sleep(3000);
        w.interrupt();
    }
}