package com.example.axonaggregatetest.domain.model;

import com.example.axonaggregatetest.api.DoWithEntityCommand;
import com.example.axonaggregatetest.api.SampleAggregateCreated;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class SampleAggregateTest {
    private FixtureConfiguration<SampleAggregate> fixture;

    @BeforeEach
    void setUp() {
        fixture = new AggregateTestFixture<>(SampleAggregate.class);
    }

    @Test
    void given_SampleAggregateCreated_when_DoWithEntityCommand_then_noEvents() {
        final var aggregateId = UUID.randomUUID();

        fixture.given(
                        new SampleAggregateCreated(aggregateId)
                )
                .when(
                        new DoWithEntityCommand(aggregateId)
                )
                .expectSuccessfulHandlerExecution()
                .expectNoEvents();
    }

}