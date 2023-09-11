package com.z.p3.c55;

import sun.misc.Unsafe;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

class UnsafeFooTest02 {

    private static byte[] loadClassBytes(File file) {
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
            return new byte[0];
        }
    }

    /*
        加载字节码文件
     */
    public static void main(String[] args) throws Exception {
//        Simple simple = new Simple();

        byte[] bytes = loadClassBytes(new File("C:/Users/lin/Documents/fuck/Simple.class"));

        Unsafe unsafe = UnsafeUtil.getUnsafe();
        Class<?> cls = unsafe.defineClass(null, bytes, 0, bytes.length, null, null);
        long i = (long) cls.getDeclaredMethod("get").invoke(cls.newInstance());
        System.out.println(i);
        /*
            simple.<init>
            1
         */
    }
}
