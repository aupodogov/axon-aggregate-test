package com.example.axonaggregatetest.domain.model;

import com.example.axonaggregatetest.api.CreateSampleAggregateCommand;
import com.example.axonaggregatetest.api.DoWithEntityCommand;
import com.example.axonaggregatetest.api.SampleAggregateCreated;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.InterceptorChain;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.modelling.command.CommandHandlerInterceptor;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Slf4j
@Aggregate
@NoArgsConstructor
public class SampleAggregate {
    @AggregateIdentifier
    private UUID id;
    @AggregateMember
    private AggregateEntity entity;

    @CommandHandler
    public SampleAggregate(CreateSampleAggregateCommand command) {
        apply(new SampleAggregateCreated(command.getId()));
    }

    @CommandHandlerInterceptor
    public void intercept(DoWithEntityCommand command,
                          InterceptorChain interceptorChain) throws Exception {
        if (this.entity == null) {
            log.info("Command [{}] ignored!", command);
            return;
        }
        interceptorChain.proceed();
    }

    @EventSourcingHandler
    public void on(SampleAggregateCreated event) {
        this.id = event.getId();
    }
}
