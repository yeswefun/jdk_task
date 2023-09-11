package com.z.j8.j811;

class Default01 {

    private void confuse(Object o) {
        System.out.println("Object");
    }

    private void confuse(int[] arr) {
        System.out.println("int[]");
    }

    /*
        调用 信息 更为 具体 的方法

        书籍: Java Puzzlers
     */
    public static void main(String[] args) {
        Default01 default01 = new Default01();
        default01.confuse(null); // int[]

        int[] arr = null;
        Object o = arr;
        default01.confuse(o); // Object
    }
}
