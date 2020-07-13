package com.example.springdatajdbcexample.comment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@AllArgsConstructor
@Value
@Builder
public class CommentContent {
    @NotNull
    String body;

    @NotBlank
    @Size(max = 20)
    String mimeType;
}
