package com.example.springdatajdbcexample.comment;

import javax.validation.constraints.PositiveOrZero;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(of = "id")
@ToString
@AllArgsConstructor
public class Comment {
    @Id
    private final Long id;

    @PositiveOrZero
    @Version
    private long version;



}
