package com.z.p2.c27_immutable;

class Demo01StringTest {

    /*
        String 加上 synchronized 的地方都是由于外面传入了一个可变对象
            需要确保传入的可变对象不能为 null
            public String(@NonNull StringBuffer buffer)
            public boolean contentEquals(@NonNull CharSequence cs)
     */
    public static void main(String[] args) {
        String s = "hello";
        String s2 = s.replace("l", "X");    // return new string
        System.out.println(s.hashCode());
        System.out.println(s2.hashCode());
    }
}
