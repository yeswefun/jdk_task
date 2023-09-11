package com.z.p2.c20;

import java.util.stream.IntStream;

class Singleton03 {

    static {
        System.out.println("*** Singleton03");
    }

    private Singleton03() {
    }

    /*
        枚举类型是线程安全的
            构造函数只会被装载一次
     */
    private enum Singleton {

        INSTANCE;

        static {
            System.out.println("*** Singleton");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private final Singleton03 instance;

        //Modifier 'private' is redundant for enum constructors
        Singleton() {
            System.out.println("@@@ Singleton03");
            instance = new Singleton03();
        }

        public Singleton03 getInstance() {
            return instance;
        }
    }

    public static Singleton03 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    /*
        javap 查看字节码
     */
    public static void main(String[] args) {
        System.out.println("------> main");
        IntStream.rangeClosed(1, 8)
                .forEach(i -> new Thread(String.valueOf(i)) {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName() + " ---> " + Singleton03.getInstance());
                    }
                }.start());
    }
}
