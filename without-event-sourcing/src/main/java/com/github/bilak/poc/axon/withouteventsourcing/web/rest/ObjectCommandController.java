package com.github.bilak.poc.axon.withouteventsourcing.web.rest;

import com.github.bilak.poc.axon.withouteventsourcing.api.command.CreateObjectCommand;
import com.github.bilak.poc.axon.withouteventsourcing.web.rest.schema.CreateObjectRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * @author lvasek.
 */
@RestController
@RequestMapping("/objects")
public class ObjectCommandController {

	private CommandGateway commandGateway;

	@Autowired
	public ObjectCommandController(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	@PostMapping
	public CompletableFuture<ResponseEntity> createObject(@Valid @RequestBody CreateObjectRequest request) {
		CreateObjectCommand command = new CreateObjectCommand(
				UUID.randomUUID().toString(),
				UUID.randomUUID().toString(),
				request.getObjectName());

		URI location =
				ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(command.getObjectId())
						.toUri();

		return commandGateway.send(command)
				.thenApply(result -> ResponseEntity.accepted().location(location).build());
	}
}
