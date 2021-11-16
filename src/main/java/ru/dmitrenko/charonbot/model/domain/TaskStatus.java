package ru.dmitrenko.charonbot.model.domain;

import ru.dmitrenko.charonbot.exception.EnumValueNotFoundException;

import java.util.Arrays;

public enum TaskStatus {
	IN_PROGRESS("IN_PROGRESS"),
	PROLONG("PROLONG"),
	FAIL("FAIL"),
	CANCEL("CANCEL");

	private final String value;

	TaskStatus(String value) {
		this.value = value;
	}

	public static TaskStatus fromValue(String value) {
		return Arrays.stream(TaskStatus.values())
			.filter(o -> o.value.equals(value))
			.findFirst()
			.orElseThrow(() -> new EnumValueNotFoundException(String.format("Unexpected TaskStatus value %s", value)));
	}

	public String getValue() {
		return value;
	}

}
