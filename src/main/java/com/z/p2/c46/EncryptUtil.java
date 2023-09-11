package com.z.p2.c46;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public final class EncryptUtil {

    public static final byte ENCRYPT_FACTOR = (byte) 0xff;

    private EncryptUtil() {
        //empty
    }

    public static void encrypt(String srcFilePath, String dstFilePath) {
        try (FileInputStream fis = new FileInputStream(srcFilePath);
             FileOutputStream fos = new FileOutputStream(dstFilePath)) {
            int data;
            while ((data = fis.read()) != -1) {
                fos.write(data ^ ENCRYPT_FACTOR);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String basePath = "C:/Users/lin/Documents/fuck/";
//        encrypt(basePath + "a.txt", basePath + "a_encrypt.txt"); // 加密
//        encrypt(basePath + "a_encrypt.txt", basePath + "a_0.txt"); // 解密

        encrypt(
                basePath + "cl0/com/z/c44/MyObject.class",
                basePath + "cl2/com/z/c44/MyObject.class"
        );
    }
}
