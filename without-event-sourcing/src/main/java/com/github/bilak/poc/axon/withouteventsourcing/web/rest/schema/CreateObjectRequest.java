package com.github.bilak.poc.axon.withouteventsourcing.web.rest.schema;

import javax.validation.constraints.NotNull;

/**
 * @author lvasek.
 */
public class CreateObjectRequest {

	@NotNull
	private String objectName;

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
}
