package ru.dmitrenko.charonbot.model.domain;

import ru.dmitrenko.charonbot.exception.EnumValueNotFoundException;

import java.util.Arrays;

public enum PartyStatus {
	STALKER("STALKER"),
	MILITARY("MILITARY"),
	DUTY("DUTY"),
	FREEDOM("FREEDOM"),
	BANDIT("BANDIT"),
	MERCURY("MERCURY"),
	CLEAR_SKY("CLEAR_SKY"),
	MONOLITH("MONOLITH"),
	RENEGADE("RENEGADE"),
	SMUGGLER("SMUGGLER"),
	SCIENTIST("SCIENTIST");

	private final String value;

	PartyStatus(String value) {
		this.value = value;
	}

	public static PartyStatus fromValue(String value) {
		return Arrays.stream(PartyStatus.values())
			.filter(o -> o.value.equals(value))
			.findFirst()
			.orElseThrow(() -> new EnumValueNotFoundException(String.format("Unexpected PartyStatus value %s", value)));
	}

	public String getValue() {
		return value;
	}

}
