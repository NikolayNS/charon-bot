package ru.dmitrenko.charonbot.model.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.Instant;
import java.util.UUID;

@Data
@MappedSuperclass
public class BaseEntity {

	@Id
	@GeneratedValue
	private UUID id;

	@CreationTimestamp
	@Column(name = "CREATED", nullable = false, updatable = false)
	private Instant created;

	@UpdateTimestamp
	@Column(name = "UPDATED", nullable = false)
	private Instant updated;
}
