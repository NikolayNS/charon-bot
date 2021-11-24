package ru.dmitrenko.charonbot.handler;

import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import ru.dmitrenko.charonbot.exception.MessageNotFoundException;
import ru.dmitrenko.charonbot.service.domain.PersonDomainService;
import ru.dmitrenko.charonbot.service.domain.TaskDomainService;

import static ru.dmitrenko.charonbot.util.Constant.CHECK_MARK;
import static ru.dmitrenko.charonbot.util.Constant.CROSS_MARK;

@Service
@RequiredArgsConstructor
public class GuildMessageReactionAddHandler extends ListenerAdapter {

	private final PersonDomainService personDomainService;
	private final TaskDomainService taskDomainService;

	@Override
	public void onGuildMessageReactionAdd(@NotNull GuildMessageReactionAddEvent event) {
		if (event.getUser().isBot()) return;

		switch (getUnicode(event)) {
			case CHECK_MARK -> readMessage(event);
			case CROSS_MARK -> deleteMessage(event);
		}
	}

	private String getUnicode(GuildMessageReactionAddEvent event) {
		return event.getReactionEmote()
			.toString()
			.split(":")
			[1];
	}

	private void readMessage(GuildMessageReactionAddEvent event) {
		var message = findMessage(event);
	}

	private void deleteMessage(GuildMessageReactionAddEvent event) {
		var message = findMessage(event);
		if (event.getUser().equals(message.getAuthor())) {
			message.delete().submit();
		} else {
			event.getReaction()
				.removeReaction(event.getUser())
				.queue();
		}
	}

	private Message findMessage(GuildMessageReactionAddEvent event) {
		return event.getChannel()
			.getHistory()
			.retrievePast(20)
			.complete()
			.stream()
			.filter(o -> o.getId().equals(event.getMessageId()))
			.findFirst()
			.orElseThrow(() -> new MessageNotFoundException(String.format("Message %s not found", event.getMessageId())));
	}
}
