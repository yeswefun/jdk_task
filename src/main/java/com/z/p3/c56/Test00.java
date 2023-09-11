package com.z.p3.c56;

class Test00 {

    public static void main(String[] args) {
        for (int i = 0; i < 16; i++) {
            run(new Runnable() {
                @Override
                public void run() {
                    //Variable 'i' is accessed from within inner class,
                    //needs to be final or effectively final
                    //System.out.println(i);
                }
            });
        }
    }

    private static void run(Runnable r) {
        r.run();
    }
}
