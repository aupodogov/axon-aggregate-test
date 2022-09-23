package com.example.axonaggregatetest.api;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Value
public class CreateSampleAggregateCommand {
    @TargetAggregateIdentifier
    private final UUID id;
}
