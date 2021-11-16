package ru.dmitrenko.charonbot.exception;

public class EnumValueNotFoundException extends RuntimeException {

	public EnumValueNotFoundException() {
		super();
	}

	public EnumValueNotFoundException(String message) {
		super(message);
	}
}
