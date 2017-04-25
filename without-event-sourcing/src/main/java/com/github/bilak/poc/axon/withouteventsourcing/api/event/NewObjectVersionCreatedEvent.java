package com.github.bilak.poc.axon.withouteventsourcing.api.event;

import java.io.Serializable;

/**
 * @author lvasek.
 */
public class NewObjectVersionCreatedEvent implements Serializable {

	private final String objectId;
	private final String objectVersionId;
	private final String objectName;
	private final Long versionNumber;
	private final String previousObjectVersionId;

	public NewObjectVersionCreatedEvent(String objectId, String objectVersionId, String objectName, Long versionNumber, String previousObjectVersionId) {
		this.objectId = objectId;
		this.objectVersionId = objectVersionId;
		this.objectName = objectName;
		this.versionNumber = versionNumber;
		this.previousObjectVersionId = previousObjectVersionId;
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

	public String getPreviousObjectVersionId() {
		return previousObjectVersionId;
	}
}
