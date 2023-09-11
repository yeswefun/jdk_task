package com.z.p2.c43;

class MainTest04 {

    /*
        类加器机制: 父委托

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
     */
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> cls = Class.forName("java.lang.String");
        System.out.println(cls);

        ClassLoader cl = cls.getClassLoader();
        System.out.println(cl);

        /*
            class java.lang.String
            null
         */
    }
}
