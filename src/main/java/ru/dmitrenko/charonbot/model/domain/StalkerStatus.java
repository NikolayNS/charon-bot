package ru.dmitrenko.charonbot.model.domain;

import java.util.Arrays;

public enum StalkerStatus {
	NEWCOMER("NEWCOMER"),
	HUNTER("HUNTER"),
	ACOLYTE("ACOLYTE"),
	AGENT("AGENT"),
	WANTED("WANTED"),
	ENEMY("ENEMY"),
	NOT_ACTIVE("NOT_ACTIVE");

	private final String value;

	StalkerStatus(String value) {
		this.value = value;
	}

	public static StalkerStatus fromValue(String value) {
		return Arrays.stream(StalkerStatus.values())
			.filter(o -> o.value.equals(value))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Unexpected value '" + value + "'"));
	}

	public String getValue() {
		return value;
	}
}
