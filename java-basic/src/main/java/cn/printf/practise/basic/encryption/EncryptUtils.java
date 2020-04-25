package cn.printf.practise.basic.encryption;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.UUID;

public class EncryptUtils {


    private static final String KEY = "1234567887654321";


    private static final String ALGORITHMSTR = "AES/CFB/PKCS5Padding";

    public static void main(String[] args) throws Exception {
        String text = UUID.randomUUID().toString().replaceAll("-","");

        System.out.println(text);

        byte[] xor = xor(text.getBytes(), "123456".getBytes());
        System.out.println(Base64.getEncoder().encode(xor));

        byte[] bytes = aesEncryptToBytes(text, KEY);
        byte[] encode = Base64.getEncoder().encode(bytes);
        System.out.println(new String(bytes));
    }


    private static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));

        return cipher.doFinal(content.getBytes("utf-8"));
    }


    private static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);

        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);

        return new String(decryptBytes);
    }


    private static byte[] xor(final byte[] input, final byte[] secret) {
        final byte[] output = new byte[input.length];
        if (secret.length == 0) {
            throw new IllegalArgumentException("empty security key");
        }
        int spos = 0;
        for (int pos = 0; pos < input.length; ++pos) {
            output[pos] = (byte) (input[pos] ^ secret[spos]);
            ++spos;
            if (spos >= secret.length) {
                spos = 0;
            }
        }
        return output;
    }

}
