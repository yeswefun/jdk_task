package com.z.p2.c41;

class MainTest20 {
    /*
        链接 / 准备
            为静态变量分配内存，并将其初始化
            int x = 0
            int y = 0
            instance = null

        初始化
            为类的静态变量赋予正确的初始化
            int x = 0
            instance = new MaintTest01()
                x == 1
                y == 1
     */
    public static int x = 0;

    public static int y;

    private static MainTest20 instance = new MainTest20();

    MainTest20() {
        x++;
        y++;
    }

    public static MainTest20 getInstance() {
        return instance;
    }

    /*
        1
        1
     */
    public static void main(String[] args) {
        MainTest20 instance = getInstance();
        System.out.println(instance.x);
        System.out.println(instance.y);
    }
}
