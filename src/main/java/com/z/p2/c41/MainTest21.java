package com.z.p2.c41;

class MainTest21 {
    /*
        链接 / 准备
            为静态变量分配内存，并将其初始化
            instance = null
            int x = 0
            int y = 0

        初始化
            为类的静态变量赋予正确的初始化
            instance = new MaintTest01()
                x == 1
                y == 1

            int x = 0
     */
    private static MainTest21 instance = new MainTest21();

    public static int x = 0;

    public static int y;


    MainTest21() {
        x++;
        y++;
    }

    public static MainTest21 getInstance() {
        return instance;
    }

    /*
        0
        1
     */
    public static void main(String[] args) {
        MainTest21 instance = getInstance();
        System.out.println(instance.x);
        System.out.println(instance.y);
    }
}
