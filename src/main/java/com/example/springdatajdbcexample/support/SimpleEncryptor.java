package com.example.springdatajdbcexample.support;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class SimpleEncryptor implements Encryptor {
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
    private static final String ALGORITHM = "AES";
    private static final byte[] KEY_BYTES = "thisisa128bitkey".getBytes(StandardCharsets.UTF_8);

    @Override
    public byte[] encrypt(String value) {
        if(Objects.isNull(value)) {
            return null;
        }

        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            SecretKey secretKey = new SecretKeySpec(KEY_BYTES, ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
        } catch (Exception ex) {
            throw new RuntimeException("Encrypt value 값이 잘못되었습니다.", ex);
        }
    }

    @Override
    public String decrypt(byte[] encrypted) {
        if(Objects.isNull(encrypted)) {
            return null;
        }

        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            SecretKey secretKey = new SecretKeySpec(KEY_BYTES, ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decrypted = cipher.doFinal(encrypted);
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            throw new RuntimeException("Decrypt value 값이 잘못되었습니다.", ex);
        }
    }
}
