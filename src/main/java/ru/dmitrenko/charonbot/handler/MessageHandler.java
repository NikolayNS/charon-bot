package ru.dmitrenko.charonbot.handler;

import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import static ru.dmitrenko.charonbot.util.Constant.CHARON;

@Service
@RequiredArgsConstructor
public class MessageHandler extends ListenerAdapter {

	@Override
	public void onMessageReceived(@NotNull MessageReceivedEvent event) {
		if (event.getMessage().getContentDisplay().equals(CHARON)) {
			event.getChannel()
				.sendMessage("Что тебе нужно смертный?")
				.queue();
		}
	}
}
