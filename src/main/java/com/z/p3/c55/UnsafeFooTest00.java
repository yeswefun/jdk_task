package com.z.p3.c55;

import sun.misc.Unsafe;

class UnsafeFooTest00 {

    static class Simple {

        private long i;

        public Simple() {
            i = 1;
            System.out.println("simple.<init>");
        }

        public long get() {
            return i;
        }
    }

    public static void main(String[] args) throws Exception {
//        会调用构造函数
//        Simple simple = new Simple();
//        System.out.println(simple.get());

//        会调用构造函数
//        Simple simple = Simple.class.newInstance();

//        不会实例化对象，但会触发类的加载(执行类的静态代码块)
//        Class.forName("com.z.p3.c55.UnsafeFooTest00$Simple");

//      产生实例对象时，不调用构造函数
        Unsafe unsafe = UnsafeUtil.getUnsafe();
        Simple o = (Simple) unsafe.allocateInstance(Simple.class);
        System.out.println(o.get());
        System.out.println(o.getClass());
        System.out.println(o.getClass().getClassLoader());
        /*
            0
            class com.z.p3.c55.UnsafeFooTest00$Simple
            sun.misc.Launcher$AppClassLoader@18b4aac2
         */
    }
}
