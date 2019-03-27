package com.github.bilak.poc.axon.withouteventsourcing.command;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.axonframework.messaging.MetaData;
import org.axonframework.modelling.command.EntityId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.github.bilak.poc.axon.withouteventsourcing.api.event.NewObjectVersionCreatedEvent;
import com.github.bilak.poc.axon.withouteventsourcing.api.event.ObjectCreatedEvent;

/**
 * @author lvasek.
 */
@Entity
@Table(name = "object_version")
public class ObjectVersionEntity {

	@Id
	@Column(name = "id", length = 36)
	@EntityId
	private String objectVersionId;

	@Column(name = "object_id", length = 36)
	private String objectId;

	@Column(name = "object_name")
	private String objectName;

	@Column(name = "version_number")
	private Long versionNumber;

	@Version
	@Column(name = "version")
	private Long version;

	@CreatedDate
	@Column(name = "creation_date")
	private Instant created;
	@LastModifiedDate
	@Column(name = "modify_date")
	private Instant modified;

	@SuppressWarnings("unused")
	protected ObjectVersionEntity() {
	}

	public ObjectVersionEntity(ObjectCreatedEvent event, MetaData metaData, Instant created) {
		this.objectVersionId = event.getObjectVersionId();
		this.objectId = event.getObjectId();
		this.objectName = event.getObjectName();
		this.versionNumber = event.getVersionNumber();
		this.created = created;

	}

	public ObjectVersionEntity(NewObjectVersionCreatedEvent event, MetaData metaData, Instant created) {
		this.objectVersionId = event.getObjectVersionId();
		this.objectId = event.getObjectId();
		this.objectName = event.getObjectName();
		this.versionNumber = event.getVersionNumber();
		this.created = created;

	}

	public String getObjectVersionId() {
		return objectVersionId;
	}

	public void setObjectVersionId(String objectVersionId) {
		this.objectVersionId = objectVersionId;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public Long getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(Long versionNumber) {
		this.versionNumber = versionNumber;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Instant getCreated() {
		return created;
	}

	public void setCreated(Instant created) {
		this.created = created;
	}

	public Instant getModified() {
		return modified;
	}

	public void setModified(Instant modified) {
		this.modified = modified;
	}
}
