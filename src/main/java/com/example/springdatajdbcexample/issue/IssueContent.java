package com.example.springdatajdbcexample.issue;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class IssueContent {

    @NotNull
    String body;

    @NotBlank
    @Size(max = 20)
    String mimeType;
}
