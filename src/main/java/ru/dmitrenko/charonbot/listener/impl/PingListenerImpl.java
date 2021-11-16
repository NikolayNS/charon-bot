package ru.dmitrenko.charonbot.listener.impl;

import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.stereotype.Service;
import ru.dmitrenko.charonbot.listener.PingListener;

@Service
public class PingListenerImpl implements PingListener {

	@Override
	public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
		messageCreateEvent.getMessageContent();
		if (messageCreateEvent.getMessageContent().equals("!ping"))
			messageCreateEvent.getChannel().sendMessage("pong!");
	}
}
