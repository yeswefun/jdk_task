package com.z.p1.c00_phase_1;

/*
ThreadGroup#add
    threads = Arrays.copyOf(threads, nthreads * 2);

ThreadGroup#remove
    System.arraycopy(threads, i + 1, threads, i, --nthreads - i);





代码块
    普通代码块
    静态代码块
    构造代码块
    同步代码


Throwable
    Error
    Exception
        RuntimeException

    CheckException，需要捕获
        Exception类 中除了 RuntimeException之外的类

    UncheckException，不需要捕获
        Error
        RuntimeException
        及以上两个类的子类


final
    类，不可被继承
    方法，不可被重写
    字段，不可被修改
    局部变量
        面试题：final修饰局部变量的问题
		    基本类型：基本类型的值不能发生改变
		    引用类型：引用类型的地址值不能发生改变，但是，该对象的堆内存的值是可以改变的


引用类型
    强引用，Strong Reference
    软引用，Soft Reference
    弱引用，weak Reference
    虚引用，PhantomReference




区分本类对象还是本类的子类对象, Random
    public Random(long seed) {
        if (getClass() == Random.class)
            this.seed = new AtomicLong(initialScramble(seed));
        else {
            // subclass might have overriden setSeed
            this.seed = new AtomicLong();
            setSeed(seed);
        }
    }


禁止 clone 对象, Thread
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }


数组字符串形式输出，AtomicIntegerArray
    @Override
    public String toString() {
        int iMax = array.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(getRaw(byteOffset(i)));
            if (i == iMax)
                return b.append(']').toString();
            b.append(',').append(' ');
        }
    }
 */
public interface ZMils {
}
