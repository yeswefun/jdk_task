package com.z.p2.c47;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/*
原文件名: MyClassLoader
从文件中加载类
 */
class SimpleClassLoader2 extends ClassLoader {

    private static final String DEFAULT_DIR = "C:/Users/lin/Documents/fuck/cl3";

    private String dir = DEFAULT_DIR;

    private String classLoaderName;

    public SimpleClassLoader2() {
    }

    public SimpleClassLoader2(String classLoaderName) {
        this.classLoaderName = classLoaderName;
    }

    /*
        不传入 ClassLoader，默认使用 创建本类 的 ClassLoader 作为 parent
     */
    public SimpleClassLoader2(String classLoaderName, ClassLoader parent) {
        super(parent);
        this.classLoaderName = classLoaderName;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getClassLoaderName() {
        return classLoaderName;
    }

    /*
        xxx.xxx.xxx.Demo
        xxx/xxx/xxx/Demo
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String classpath = name.replace(".", "/");
        File classfile = new File(dir, classpath + ".class");
        if (!classfile.exists()) {
            throw new ClassNotFoundException("not found: " + name);
        }
        byte[] classBytes = loadClassBytes(classfile);
        if (classBytes == null || classBytes.length == 0) {
            throw new ClassNotFoundException("failed to load: " + name);
        }
        //return super.findClass(name);
        return defineClass(name, classBytes, 0, classBytes.length);
    }

    private byte[] loadClassBytes(File file) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             FileInputStream fis = new FileInputStream(file)) {
            int len;
            byte[] buffer = new byte[1024];
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //****************************************** increment
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {

        if (name.startsWith("java.")) {
            try {
                ClassLoader systemClassLoader = getSystemClassLoader();
                Class<?> c = systemClassLoader.loadClass(name);
                if (c != null) {
                    if (resolve) {
                        resolveClass(c);
                    }
                    return c;
                }
            } catch (Exception e) {
                //ignore
            }
        }

        Class<?> c = null;
        try {
            c = findClass(name);
        } catch (Exception e) {
            //ignore
        }

        if (c == null && getParent() != null) {
            getParent().loadClass(name);
        }

        return c;
    }
}
