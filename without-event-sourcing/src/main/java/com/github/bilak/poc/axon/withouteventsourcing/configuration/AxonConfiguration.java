package com.github.bilak.poc.axon.withouteventsourcing.configuration;

import com.github.bilak.poc.axon.withouteventsourcing.command.ObjectAggregateRoot;
import org.axonframework.commandhandling.model.GenericJpaRepository;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.messaging.annotation.ParameterResolverFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lvasek.
 */
@Configuration
public class AxonConfiguration {

	@Bean
	Repository<ObjectAggregateRoot> objectAggregateRootRepository(EntityManagerProvider entityManagerProvider, EventBus eventBus,
			ParameterResolverFactory parameterResolverFactory) {
		return new GenericJpaRepository<>(
				entityManagerProvider,
				ObjectAggregateRoot.class,
				eventBus,
				parameterResolverFactory
		);
	}
}
