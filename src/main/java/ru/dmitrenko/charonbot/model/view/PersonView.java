package ru.dmitrenko.charonbot.model.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.List;

@Data
@Accessors(chain = true)
public class PersonView {
	private Long steamId;
	private String name;
	private String nickname;
	private String description;
	private String personStatus;
	private String partyStatus;
	private String printLink;
	private Long cashAccount;
	private List<TaskView> tasks;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC+3")
	private Instant created;
}
