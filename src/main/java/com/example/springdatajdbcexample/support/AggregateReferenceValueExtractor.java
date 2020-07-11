package com.example.springdatajdbcexample.support;

import javax.validation.valueextraction.ExtractedValue;
import javax.validation.valueextraction.ValueExtractor;

import org.springframework.data.jdbc.core.mapping.AggregateReference;

public class AggregateReferenceValueExtractor implements ValueExtractor<AggregateReference<?, @ExtractedValue ?>> {
    @Override
    public void extractValues(AggregateReference<?, ?> originalValue, ValueReceiver receiver) {
        receiver.value("id", originalValue.getId());
    }
}