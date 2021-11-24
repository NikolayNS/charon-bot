package ru.dmitrenko.charonbot.exception;

public class MessageNotFoundException extends RuntimeException {

	public MessageNotFoundException() {
		super();
	}

	public MessageNotFoundException(String message) {
		super(message);
	}
}
