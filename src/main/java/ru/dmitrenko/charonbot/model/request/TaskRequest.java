package ru.dmitrenko.charonbot.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;

@Data
@Accessors(chain = true)
public class TaskRequest {
	private String name;
	private String description;
	private Instant startDate;
	private Instant endDate;
	private String taskStatus;
	private Long personSteamId;
}
