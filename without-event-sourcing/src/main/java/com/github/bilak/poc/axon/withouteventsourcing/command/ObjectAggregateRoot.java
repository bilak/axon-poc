package com.github.bilak.poc.axon.withouteventsourcing.command;

import com.github.bilak.poc.axon.withouteventsourcing.api.command.CreateObjectCommand;
import com.github.bilak.poc.axon.withouteventsourcing.api.event.ObjectCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateMember;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.axonframework.messaging.MetaData;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author lvasek.
 */
@Aggregate
@Entity
@Table(name = "object_aggregate")
public class ObjectAggregateRoot {

	@Id
	@AggregateIdentifier
	@Column(name = "id", length = 36)
	private String id;

	@Column(name = "current_version")
	private Long currentVersion;

	@AggregateMember
	@JoinColumn(name = "object_id")
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE })
	private Set<ObjectVersionEntity> objectVersions = new LinkedHashSet<>();

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
	protected ObjectAggregateRoot() {
	}

	@CommandHandler
	public ObjectAggregateRoot(CreateObjectCommand command, MetaData metaData) {
		this.id = command.getObjectId();
		objectVersions.add(new ObjectVersionEntity(command, metaData));
	}

	@EventHandler
	private void on(ObjectCreatedEvent event, MetaData metaData, @Timestamp Instant eventTimestamp) {
		this.id = event.getObjectId();
		this.currentVersion = event.getVersionNumber();
		setCreationAttributes(metaData, eventTimestamp);
		objectVersions.stream()
				.filter(ov -> event.getObjectVersionId().equalsIgnoreCase(ov.getObjectVersionId()))
				.findFirst()
				.ifPresent(ov -> {
					ov.setCreated(eventTimestamp);
				});
	}

	private void setCreationAttributes(MetaData metaData, Instant creationDate) {
		setCreated(creationDate);
	}

	private void setModificationAttributes(MetaData metaData, Instant modifyDate) {
		setModified(modifyDate);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(Long currentVersion) {
		this.currentVersion = currentVersion;
	}

	public Set<ObjectVersionEntity> getObjectVersions() {
		return objectVersions;
	}

	public void setObjectVersions(Set<ObjectVersionEntity> objectVersions) {
		this.objectVersions = objectVersions;
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
