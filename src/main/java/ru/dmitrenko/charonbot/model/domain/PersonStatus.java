package ru.dmitrenko.charonbot.model.domain;

import ru.dmitrenko.charonbot.exception.EnumValueNotFoundException;

import java.util.Arrays;

public enum PersonStatus {
	NEWCOMER("NEWCOMER"),
	HUNTER("HUNTER"),
	ACOLYTE("ACOLYTE"),
	AGENT("AGENT"),
	WANTED("WANTED"),
	FRIEND("FRIEND"),
	ENEMY("ENEMY"),
	NOT_ACTIVE("NOT_ACTIVE"),
	DEAD("DEAD");

	private final String value;

	PersonStatus(String value) {
		this.value = value;
	}

	public static PersonStatus fromValue(String value) {
		return Arrays.stream(PersonStatus.values())
			.filter(o -> o.value.equals(value))
			.findFirst()
			.orElseThrow(() -> new EnumValueNotFoundException(String.format("Unexpected PersonStatus value %s", value)));
	}

	public String getValue() {
		return value;
	}

}
