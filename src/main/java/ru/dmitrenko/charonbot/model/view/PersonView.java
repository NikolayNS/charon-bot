package ru.dmitrenko.charonbot.model.view;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;
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
	private BigInteger cashAccount;
	private List<TaskView> tasks;
	private Instant created;
}
