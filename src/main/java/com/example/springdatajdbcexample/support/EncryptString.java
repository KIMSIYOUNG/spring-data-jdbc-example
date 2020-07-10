package com.example.springdatajdbcexample.support;

import javax.validation.constraints.NotNull;

import lombok.Value;

@Value
public class EncryptString {
    @NotNull
    String value;
}
