package com.z.p2.c46;

class MainTest00 {

    private static final byte ENCRYPT_FACTOR = (byte) 0xff;

    /*
        对称加密
            加密，解密过程中使用的密钥相同

        非对称加密
            加密，解密过程中使用的密钥不同
     */
    public static void main(String[] args) {

        String plain = "hello world";
        byte[] encrypt = encode(plain);
        System.out.println(new String(encrypt));
        String origin = decode(encrypt);
        System.out.println(origin);
    }

    private static String decode(byte[] encrypt) {
        byte[] bytes = new byte[encrypt.length];
        for (int i = 0; i < encrypt.length; i++) {
            bytes[i] = (byte) (encrypt[i] ^ ENCRYPT_FACTOR);
        }
        return new String(bytes);
    }

    private static byte[] encode(String plain) {
        //plain.getBytes(StandardCharsets.UTF_8)
        byte[] bytes = plain.getBytes();
        byte[] encrypt = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            encrypt[i] = (byte) (bytes[i] ^ ENCRYPT_FACTOR);
        }
        return encrypt;
    }
}
