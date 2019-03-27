package com.github.bilak.poc.axon.withouteventsourcing.configuration;

import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.messaging.annotation.ParameterResolverFactory;
import org.axonframework.modelling.command.GenericJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.bilak.poc.axon.withouteventsourcing.command.ObjectAggregateRoot;

/**
 * @author lvasek.
 */
@Configuration
public class AxonConfiguration {

	@Bean
	GenericJpaRepository.Builder<ObjectAggregateRoot> objectAggregateRootRepositoryBuilder(
			EntityManagerProvider entityManagerProvider, EventBus eventBus,
			ParameterResolverFactory parameterResolverFactory) {

		return GenericJpaRepository.builder(ObjectAggregateRoot.class)
				.entityManagerProvider(entityManagerProvider)
				.eventBus(eventBus)
				.parameterResolverFactory(parameterResolverFactory);
	}
}
