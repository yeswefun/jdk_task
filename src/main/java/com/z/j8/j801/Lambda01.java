package com.z.j8.j801;

class Lambda01 {

    @FunctionalInterface
    private interface Adder {
        int add(int x, int y);
    }

//    不止一个抽象方法
//    @FunctionalInterface
//    private interface SmartAdder extends Adder {
//        int add(long x, long y);
//    }

    @FunctionalInterface
    private interface Empty extends Adder {}

//    @FunctionalInterface
//    private interface None {}

    /*
        只有一个抽象方法的 类 或 接口
     */
    public static void main(String[] args) {

    }
}
