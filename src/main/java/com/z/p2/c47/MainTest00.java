package com.z.p2.c47;

import java.lang.reflect.Method;

class MainTest00 {

    /*
        加载正常 .class 文件
        public Class<?> loadClass(String name)
            throws ClassNotFoundException {
                return loadClass(name, false);
            }

        加载需要特殊处理的 .class 文件
        protected Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException {
                //...
                if (resolve) {
                    resolveClass(c);
                }
                return c;
            }
     */
    public static void main(String[] args) throws Exception {
//        SimpleObject o = new SimpleObject();
//        System.out.println(o.test("hello"));

        SimpleClassLoader cl = new SimpleClassLoader("SimpleClassLoader");
        Class<?> cls = cl.loadClass("com.z.p2.c47.SimpleObject");

        System.out.println(cls.getClassLoader());

         /*
            类文件不在工程中，可以使用反射来访问类中成员
         */
        Object o = cls.newInstance();
        Method helloMethod = cls.getMethod("test", String.class);
        Object r = helloMethod.invoke(o, "ok");
        System.out.println(r);

        /*
            sun.misc.Launcher$AppClassLoader@18b4aac2
            *************** SimpleObject static block
            [ok]
         */
    }
}
