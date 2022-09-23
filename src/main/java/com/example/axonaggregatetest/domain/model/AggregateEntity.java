package com.example.axonaggregatetest.domain.model;

import com.example.axonaggregatetest.api.DoWithEntityCommand;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;

import java.util.UUID;

@NoArgsConstructor
public class AggregateEntity {
    private UUID entityId;

    @CommandHandler
    public void handle(DoWithEntityCommand command) {
        // nothing to do here
    }
}
