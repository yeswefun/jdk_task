package com.z.p2.c46;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/*
从文件中加载类
 */
class MyDecryptClassLoader extends ClassLoader {

    private static final String DEFAULT_DIR = "C:/Users/lin/Documents/fuck/cl2";

    private String dir = DEFAULT_DIR;

    public MyDecryptClassLoader() {
    }

    /*
        不传入 ClassLoader，默认使用 创建本类 的 ClassLoader 作为 parent
     */
    public MyDecryptClassLoader(ClassLoader parent) {
        super(parent);
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
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
            throw new ClassNotFoundException("not found: " + name + " in dir: " + dir);
        }
        byte[] classBytes = loadClassBytes(classfile);
        if (classBytes == null || classBytes.length == 0) {
            throw new ClassNotFoundException("failed to load: " + name);
        }
        //return super.findClass(name);
        return defineClass(name, classBytes, 0, classBytes.length);
    }

    /*
        加密逻辑
     */
    private byte[] loadClassBytes(File file) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
            FileInputStream fis = new FileInputStream(file)) {
            int data;
            while ((data = fis.read()) != -1) {
                baos.write(data ^ EncryptUtil.ENCRYPT_FACTOR);
            }
            baos.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
