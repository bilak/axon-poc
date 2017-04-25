package com.github.bilak.poc.axon.withouteventsourcing.api.event;

import java.io.Serializable;

/**
 * @author lvasek.
 */
public class ObjectCreatedEvent implements Serializable {

	private final String objectId;
	private final String objectVersionId;
	private final String objectName;
	private final Long versionNumber;

	public ObjectCreatedEvent(String objectId, String objectVersionId, String objectName, Long versionNumber) {
		this.objectId = objectId;
		this.objectVersionId = objectVersionId;
		this.objectName = objectName;
		this.versionNumber = versionNumber;
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

	public Long getVersionNumber() {
		return versionNumber;
	}
}
