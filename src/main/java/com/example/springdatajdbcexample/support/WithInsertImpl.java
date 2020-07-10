package com.example.springdatajdbcexample.support;

import org.springframework.data.jdbc.core.JdbcAggregateOperations;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WithInsertImpl<T> implements WithInsert<T>{
    private final JdbcAggregateOperations jdbcAggregateOperations;

    @Override
    public JdbcAggregateOperations getJdbcAggregateOperations() {
        return this.jdbcAggregateOperations;
    }
}
