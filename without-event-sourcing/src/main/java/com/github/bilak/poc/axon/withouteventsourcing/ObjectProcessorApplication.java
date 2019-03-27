package com.github.bilak.poc.axon.withouteventsourcing;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.common.IdentifierFactory;
import org.axonframework.springboot.autoconfig.AxonServerAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import com.github.bilak.poc.axon.withouteventsourcing.api.command.CreateObjectCommand;

/**
 * @author lvasek.
 */
@SpringBootApplication(exclude = {AxonServerAutoConfiguration.class})
public class ObjectProcessorApplication {

	private static final Logger logger = LoggerFactory.getLogger(ObjectProcessorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ObjectProcessorApplication.class, args);
	}

	@Service
	public static class Initializer implements ApplicationListener<ApplicationReadyEvent> {

		private CommandGateway commandGateway;

		public Initializer(CommandGateway commandGateway) {
			this.commandGateway = commandGateway;
		}

		@Override
		public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
			logger.debug("Initializing application with objects ...");
			commandGateway
					.send(new CreateObjectCommand(
							IdentifierFactory.getInstance().generateIdentifier(),
							IdentifierFactory.getInstance().generateIdentifier(),
							"ObjectInitialized"))
					.exceptionally(throwable -> {
						logger.error("Unable to process command", throwable);
						return throwable;
					});
			logger.debug("Initialization finished.");
		}
	}
}
