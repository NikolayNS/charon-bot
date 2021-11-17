package ru.dmitrenko.charonbot.listener.impl;

import lombok.RequiredArgsConstructor;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.stereotype.Service;
import ru.dmitrenko.charonbot.listener.PersonListener;
import ru.dmitrenko.charonbot.service.domain.PersonDomainService;

import static ru.dmitrenko.charonbot.util.Constant.*;

@Service
@RequiredArgsConstructor
public class PersonListenerImpl implements PersonListener {

	private final PersonDomainService personDomainService;

	@Override
	public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
		switch (messageCreateEvent.getMessageContent()) {
			case ADD_PERSON -> add(messageCreateEvent);
			case UPDATE_PERSON -> update(messageCreateEvent);
			case UPDATE_CASH_ACCOUNT -> updateCashAccount(messageCreateEvent);
			case GET_PERSON -> getPerson(messageCreateEvent);
			case GET_PERSONS_BY -> getPersonsBy(messageCreateEvent);
		}
	}

	private void add(MessageCreateEvent messageCreateEvent) {
		messageCreateEvent
			.getChannel()
			.sendMessage(String.format("Что случилось %s? Давай все по форме!", messageCreateEvent.getMessageAuthor().getDisplayName()));
	}

	private void update(MessageCreateEvent messageCreateEvent) {

	}

	private void updateCashAccount(MessageCreateEvent messageCreateEvent) {

	}

	private void getPerson(MessageCreateEvent messageCreateEvent) {

	}

	private void getPersonsBy(MessageCreateEvent messageCreateEvent) {

	}
}
