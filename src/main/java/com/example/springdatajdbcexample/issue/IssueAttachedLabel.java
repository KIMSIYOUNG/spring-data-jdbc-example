package com.example.springdatajdbcexample.issue;

import java.time.Instant;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.data.jdbc.core.mapping.AggregateReference;

import com.example.springdatajdbcexample.label.Label;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class IssueAttachedLabel {
    AggregateReference<Label, @NotNull Long> labelId;

    @NotNull
    @PastOrPresent
    Instant attachedAt;

}
