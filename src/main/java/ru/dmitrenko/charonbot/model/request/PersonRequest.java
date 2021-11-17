package ru.dmitrenko.charonbot.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PersonRequest {
	private Long steamId;
	private String name;
	private String nickname;
	private String description;
	private String personStatus;
	private String partyStatus;
	private String printLink;
}
