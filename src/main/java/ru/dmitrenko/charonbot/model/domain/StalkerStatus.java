package ru.dmitrenko.charonbot.model.domain;

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
		for (StalkerStatus b : StalkerStatus.values()) {
			if (String.valueOf(b.value).equals(value)) {
				return b;
			}
		}
		throw new IllegalArgumentException("Unexpected value '" + value + "'");
	}

	public String getValue() {
		return value;
	}
}
