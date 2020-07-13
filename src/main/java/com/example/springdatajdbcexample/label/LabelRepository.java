package com.example.springdatajdbcexample.label;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface LabelRepository extends CrudRepository<Label, UUID> {
}
