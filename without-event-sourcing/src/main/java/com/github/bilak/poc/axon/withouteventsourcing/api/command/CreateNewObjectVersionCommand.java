package com.github.bilak.poc.axon.withouteventsourcing.api.command;


import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.modelling.command.TargetAggregateVersion;


/**
 * @author lvasek.
 */
public class CreateNewObjectVersionCommand implements Serializable {

	@TargetAggregateIdentifier
	@NotNull
	@Size(min = 1, max = 36)
	private final String objectId;
	@NotNull
	@Size(min = 1, max = 36)
	private final String objectVersionId;
	@NotNull
	@Size(min = 1, max = 255)
	private final String objectName;
	@TargetAggregateVersion
	private final Long aggregateVersion;

	public CreateNewObjectVersionCommand(String objectId, String objectVersionId, String objectName, Long aggregateVersion) {
		this.objectId = objectId;
		this.objectVersionId = objectVersionId;
		this.objectName = objectName;
		this.aggregateVersion = aggregateVersion;
	}

	public String getObjectId() {
		return objectId;
	}

	public String getObjectVersionId() {
		return objectVersionId;
	}

	public String getObjectName() {
		return objectName;
	}

	public Long getAggregateVersion() {
		return aggregateVersion;
	}
}
