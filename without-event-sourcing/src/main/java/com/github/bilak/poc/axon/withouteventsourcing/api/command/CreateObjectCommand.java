package com.github.bilak.poc.axon.withouteventsourcing.api.command;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

import org.axonframework.modelling.command.TargetAggregateIdentifier;


/**
 * @author lvasek.
 */
public class CreateObjectCommand implements Serializable {

	@TargetAggregateIdentifier
	@NotNull
	@Size(min = 1, max = 36)
	private final String objectId;
	@NotNull
	@Size(min = 1, max = 36)
	private final String objectVersionId;
	@Size(min = 1, max = 255)
	private final String objectName;

	public CreateObjectCommand(String objectId, String objectVersionId, String objectName) {
		this.objectId = objectId;
		this.objectVersionId = objectVersionId;
		this.objectName = objectName;
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
}
