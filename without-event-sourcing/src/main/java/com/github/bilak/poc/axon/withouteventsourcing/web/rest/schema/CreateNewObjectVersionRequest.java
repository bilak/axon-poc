package com.github.bilak.poc.axon.withouteventsourcing.web.rest.schema;

import javax.validation.constraints.Size;

/**
 * @author lvasek.
 */
public class CreateNewObjectVersionRequest {

	@Size(min = 1, max = 255)
	private String objectName;
	private Long version;

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}
