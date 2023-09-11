package com.z.p2.c41;

class MainTest10 {

    public static void main(String[] args) {
//        通过子类访问父类的static变量，不会导致子类的初始化
//        System.out.println(Target.num);   // Target
        System.out.println(Target2.num);  // Target, 只有父类被初始化
    }
}
