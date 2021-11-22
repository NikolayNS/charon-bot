package ru.dmitrenko.charonbot.model.view;

import com.fasterxml.jackson.annotation.JsonFormat;
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

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC+3")
	private Instant startDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC+3")
	private Instant endDate;

	private String taskStatus;
	private Long personSteamId;
}
