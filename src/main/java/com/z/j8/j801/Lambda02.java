package com.z.j8.j801;

class Lambda02 {

    public static void main(String[] args) {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("--- r1");
            }
        };

        Runnable r2 = () -> System.out.println("r2");

        execute(r1);
        execute(r2);
        execute(() -> System.out.println("r3"));
    }

    private static void execute(Runnable r) {
        new Thread(r).start();
    }
}
