package ru.dmitrenko.charonbot.model.view;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.UUID;

@Data
@Accessors(chain = true)
public class TaskView {
	private UUID id;
	private String name;
	private String description;
	private Instant startDate;
	private Instant endDate;
	private String taskStatus;
	private Long personSteamId;
}
