package com.z.p3.c54;

import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

class ReflectTest00 {

    static class A {

        @CallerSensitive
        public static void test() {
            Class<?> cls = Reflection.getCallerClass();
            System.out.println(cls);
        }
    }

    static class B {

        public static void test() {
            A.test();
        }
    }

    /*
        CallerSensitive annotation expected at frame 1

        VM option
            -Xbootclasspath/a:E:\k\task\target\classes

        输出结果: class com.z.p3.c54.ReflectTest00$B

        见 http://openjdk.java.net/jeps/176
        总结就是说 jdk内有些方法，jvm的开发者认为这些方法危险，不希望开发者调用，
        就把这种危险的方法用 @CallerSensitive修饰，并在 "jvm" 级别检查。

        如Reflection.getCallerClass()方法规定，调用它的对象，必须有 @CallerSensitive 注解，
        否则 报异常 Exception in thread "main" java.lang.InternalError: CallerSensitive annotation expected at frame 1
        @CallerSensitive 有个特殊之处，必须由 启动类classloader加载（如rt.jar ），才可以被识别。
        所以rt.jar下面的注解可以正常使用。

        开发者自己写的@CallerSensitive 不可以被识别。
        但是，可以利用jvm参数 -Xbootclasspath/a: path 假装自己的程序是启动类。
     */
    public static void main(String[] args) {
        B.test();
    }
}
