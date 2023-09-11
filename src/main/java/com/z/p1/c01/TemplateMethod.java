package com.z.p1.c01;

import static com.z.util.IO.println;

abstract class TemplateMethod {

    /*
        final修饰方法禁止子类重写
            操作逻辑固定
     */
    public final void log(String msg) {
        println("#####################");
        run(msg);
        println("*********************");
    }

    protected abstract void run(String msg);

    public static void main(String[] args) {
        TemplateMethod t1 = new TemplateMethod() {
            @Override
            protected void run(String msg) {
                println("TemplateMethod#run: [" + msg + "]");
            }
        };
        t1.log("message");

        TemplateMethod t2 = new TemplateMethod() {
            @Override
            protected void run(String msg) {
                println("TemplateMethod#run: (" + msg + ")");
            }
        };
        t2.log("message");
    }
}
