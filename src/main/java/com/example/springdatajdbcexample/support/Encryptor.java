package com.example.springdatajdbcexample.support;

public interface Encryptor {
    byte[] encrypt(String value);

    String decrypt(byte[] encrypted);
}
