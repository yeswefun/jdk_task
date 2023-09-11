package com.z.p2.c40_phase_2;

/*
内容
    1.ClassLoader介绍
    2.类加载详解
    3.类的链接详解
    4.类的初始化详解
    5.类加载器(JDK自带)详细介绍
    6.类加载的父委托机制
    7.命名空间&运行时包
    8.自定义类加载器
    9.类的卸载
    10.自定义解密加密类加载器
    11.线程上下文类加载器
    12.关于JVM类加载器的总结



1.ClassLoader介绍
    查看ClassLoader文档注释

    结束JVM运行的方式
        正常运行java程序结束
        System.exit(0);
        遇到 Error 或 Exception
        Crash
        OS problems and others

    类加载的三个阶段
        加载(Loading): 查找并且加载类的二进制数据

        链接(Linking):
            验证(Verifying): 确保被加载类的正确性
                magic number
                    比如 0001 代表 exe
                    exe 改后缀为 png, 危险
            准备(Preparing): 为类的静态变量分配内存，并将其初始化为默认值
            解析(Resolving): 把类中的符号引用转换为常量池中的直接引用
                javap -v Xxx

        初始化(Initializing): 为类的静态变量赋子正确的初始值

    Java程序对类的使用方式
        1.主动使用
        2.被动使用

        所有的JVM实现必须在每个类或者接口被Java程序 "首次主动使用" 时才初始化它们，
        当然现代JVM有可能根据程序的上下文语义推断出接下来可能初始化谁

    主动使用的分类
        new, 直接使用
        访问某个类或者接口的静态变量
        访问某个类的静态方法
        Class.forName, 反射某个类
        初始化一个子类，父类被主动使用
        启动类，java HelloWorld
        除了上述六种方式以外，其余的都是被动使用，不会导致类的初始化



2.类加载详解
    将class文件中的二进制数据读取到内存中，将其放在方法区中，
    然后再堆区中创建一个java.lang.Class对象，用来封装在方法区的数据结构

    类的加载的最终产物是位于堆区中的Class对象

    class文件的加载方式
        从本地磁盘中加载
        从内存中加载
        通过网络加载
        从 zip, jar 等归档文件中加载
        从数据库中加载
        动态编译，asm

    JVM 内存模型
        线程共享
            方法区
            堆区
        线程私有
            虚拟机栈
            本地方法栈
            程序计数器

    Class对象和Object对象在内存中的布局

    创建一个对象的过程



3.类的链接详解
    在加载阶段完成后，虚拟机外部的二进制数据就会按照虚拟机所需要的格式存储在方法区中(数据结构)，
    然后在堆区中创建一个Class对象，这个对象作为程序访问方法区中这些数据结构的外部接口。

    加载阶段与链接阶段的部分内容可以是交叉进行的，比如一部分代码加载完成就可以进行验证，提高效率

    验证
        主要的目的是确保Class文件中的字节流中包含的信息符合虚拟机的要求，
        并且不会损害到JVM自身的安全

        VerifyError

        文件格式验证
            魔术因子是否正确，0xCAFEBABE
            主从版本号是否符合当前虚拟机
            常量池中的常量类型是不是不支持
            ...

        元数据验证
            是否有父类
            父类是不是允许继承
            是否实现了抽象方法
            是否覆盖了父类的final字段
            其他的语义检查

        字节码验证
            主要进行数据流和控制流的分析，不会出现这样的情况，
            在操作栈中放置了一个int类型，但是却给了一个long行的数据

        符号引用验证
            调用了一个不存在方法，字段等等
            符号引用验证的目的是确保解析动作能正常执行，如果无法通过符号引用验证，
            将会抛出一个java.lang.IncompatibleClassChangeError 异常的子类，
            如java.lang.IllegalAccessError, java.lang.NoSuchFieldError, java.lang.NoSuchMethodError 等

    准备
        给类变量分配初始值
            int         0
            double      0.0
            char        '\u0000'
            boolean     false
            reference   null

    解析
        类或者接口的解析
        字段解析
        类方法解析
        接口方法解析

        虚拟机实现会根据需要来判断，到底是
        在类被加载器加载时就对常量池中的符号引用进行解析，
        还是等到一个符号引用将要被使用前才去解析它。



4.类的初始化详解
    类加载过程中的最后一步

    初始化阶段是执行<clinit>()方法的过程

    <clinit>()方法是由编译器自动收集类中的所有变量的赋值动作和静态语句块中的语句合并产生的

    静态语句块中只能访问到定义在静态语句块之前的变量，而定义在它之后的变量，只能赋值，不能访问
        Corretto-8.322.06.1，这个 jdk 直接不能访问(读写)

    <clinit>方法与类的构造函数有点区别，他不需要显示的调用父类的构造函数，
    虚拟机会保证在子类的<clinit>执行之前，先执行父类的<clinit>，
    因此在虚拟机中首先被执行的是Object的<clinit>()方法

    由于父类的<clinit>方法要先执行，也就意味着父类中定义的静态语句块，要优先于子类

    <clinit>()方法对于一个类来说并不是必须的
    接口中照样存在<clinit>()方法
    虚拟机有义务保证<clinit>()方法的线程安全
        单例设计模式中的Holder方式



5.类加载器(JDK自带)详细介绍
    BoostrapClassLoader
        jre/lib
        sun.boot.class
        没有继承 ClassLoader, 没有父加载器，native层实现

    ExtClassLoader
        jre/lib/ext
        java.ext.dirs

    AppClassLoader
        CLASSPATH
        java.class.path
        它是用户自定义的类加载器的默认父加载器



6.类加载的父委托机制

    定义类加载器
        cl1
    初始类加载器
        cl0
        cl0.getParent()
        cl0.getParent().getParent()
        ...

    父子类加载器之间的真实关系
        装饰器设计模式

    查找类的流程
        系统加载器
            扩展加载器
                根加载器
                    根加载器尝试加载
                        成功，则返回
                        失败，让扩展加载器尝试加载

                扩展加载器尝试加载
                    成功，则返回
                    失败，让系统加载器尝试加载

            系统加载器尝试加载
                成功，则返回
                失败，ClassNotFoundException

    父亲委托机制的优点就是能够提高系统的安全性，在此机制下，
    用户自定义的类加载器不可能加载应该由父加载器加载的可靠类，
    因此可以防止恶意的代码代替父加载器的可靠代码

        static class AppClassLoader
            extends URLClassLoader {}

        public class URLClassLoader
            extends SecureClassLoader
            implements Closeable {}

        public class SecureClassLoader
            extends ClassLoader {}

    父委派机制(Parent Delegation Model)的优点：
        1.保证安全性，层级关系代表优先级，也就是所有类的加载，优先给启动类加载器，这样就保证了核心类库类。
        2.避免重复，如果父类加载器加载过了，子类加载器就没有必要再去加载了。

    父委派特点
        1.类加载器的委托是优先交给父亲加载器先去尝试加载
        2.父加载器和子加载器其实是种包装关系，或者包含关系


    对于同一个类加载器，多次加载，只加载一次
    对于不同的类加载器，各自加载一次


7.类加载器的命名空间与运行时包
    运行时包
        父类加载器看不到子类加载器加载的类
        不同命名空间下的类加载器之间的类互相不可访问



8.自定义类加载器

    创建一个类，继承自 ClassLoader，并重写 findClass 方法
        Class<?> cls = cl.loadClass("com.z.c44.MyObject");

    loadClass时，并不会导致类初始化
        因为它并不是 6个主动加载条件的其中之一

    类文件不在工程中，可以使用反射来访问类中成员

    Examples of valid class names include:
        "java.lang.String"
        "javax.swing.JSpinner$DefaultEditor"
        "java.security.KeyStore$Builder$FileBuilder$1"
        "java.net.URLClassLoader$3$1"


9.类的卸载以及classloader的卸载
    JVM中的Class只有满足以下三个条件，才能被GC回收，
    也就是该Class被卸载(unload):
        该类所有的实例都已经被GC
        加载该类的ClassLoader实例已经被GC(先干掉被引用的, Class持有ClassLoader的引用)
        该类的java.lang.Class对象没有在任何地方被引用

        注: GC的时机是不可控的，那么同样的对于Class的卸载也是不可控的



10.自定义解密加密类加载器


11.线程上下文类加载器


12.关于JVM类加载器的总结


 */
interface Z06_ClassLoader {
}
