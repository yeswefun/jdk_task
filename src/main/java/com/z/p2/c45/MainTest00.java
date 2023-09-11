package com.z.p2.c45;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class MainTest00 {

    /*
        父委派机制(Parent Delegation Model)的优点：
            1.保证安全性，层级关系代表优先级，也就是所有类的加载，优先给启动类加载器，这样就保证了核心类库类。
            2.避免重复，如果父类加载器加载过了，子类加载器就没有必要再去加载了。

        1.类加载器的委托是优先交给父亲加载器先去尝试加载
        2.父加载器和子加载器其实是种包装关系，或者包含关系

        cl0-0
            cl1-1
                app-2
                    ext-3
                        boot-4
                            ok, return
                            not found
                        ok, return
                        not found
                    ok, return
                    not found
                ok, return
                not found
            ok, return
            not found
        ok, return
        not found, ClassNotFoundException
     */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        /*
            loadClass时，并不会导致类初始化
                因为它并不是 6个主动加载条件 的其中之一
         */
        MyClassLoader cl0 = new MyClassLoader("MyClassLoader-0");
        Class<?> cls = cl0.loadClass("com.z.c44.MyObject");
        /*
            类文件不在工程中，可以使用反射来访问类中成员
         */
        Object o = cls.newInstance();
        Method helloMethod = cls.getMethod("hello");
        Object r = helloMethod.invoke(o);
        System.out.println(r);

        System.out.println(cls);
        System.out.println(cls.getClassLoader() + " ---> " + ((MyClassLoader) cls.getClassLoader()).getClassLoaderName());
        System.out.println(cls.getClassLoader().getParent());
        /*
            *************** MyObject static block
            Hello World
            null
            class com.z.c44.MyObject
            com.z.c45.MyClassLoader@4554617c ---> MyClassLoader-0
            sun.misc.Launcher$AppClassLoader@18b4aac2
         */
    }
}
