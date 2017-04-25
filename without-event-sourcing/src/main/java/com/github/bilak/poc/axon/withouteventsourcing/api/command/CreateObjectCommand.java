package com.github.bilak.poc.axon.withouteventsourcing.api.command;

import java.io.Serializable;

/**
 * @author lvasek.
 */
public class CreateObjectCommand implements Serializable {

	private final String objectId;
	private final String objectVersionId;
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
