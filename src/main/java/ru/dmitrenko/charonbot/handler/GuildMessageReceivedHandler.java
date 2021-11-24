package ru.dmitrenko.charonbot.handler;

import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import ru.dmitrenko.charonbot.model.request.PersonRequest;
import ru.dmitrenko.charonbot.service.domain.PersonDomainService;

import java.awt.*;

import static ru.dmitrenko.charonbot.util.Constant.ADD_PERSON;
import static ru.dmitrenko.charonbot.util.Constant.CHECK_MARK;
import static ru.dmitrenko.charonbot.util.Constant.CROSS_MARK;
import static ru.dmitrenko.charonbot.util.Constant.GET_PEOPLE_BY;
import static ru.dmitrenko.charonbot.util.Constant.GET_PERSON;
import static ru.dmitrenko.charonbot.util.Constant.UPDATE_CASH_ACCOUNT;
import static ru.dmitrenko.charonbot.util.Constant.UPDATE_PERSON;

@Service
@RequiredArgsConstructor
public class GuildMessageReceivedHandler extends ListenerAdapter {

	private final PersonDomainService personDomainService;

	@Override
	public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
		if (event.getMember() != null && event.getMember().getUser().isBot()) return;

		var raws = event.getMessage().getContentDisplay().split("\n");
		//event.getMessage().delete().submit();

		switch (raws[0]) {
			case ADD_PERSON -> addPerson(event, raws);
			case UPDATE_PERSON -> updatePerson(event);
			case UPDATE_CASH_ACCOUNT -> updateCashAccount(event);
			case GET_PERSON -> getPerson(event, raws);
			case GET_PEOPLE_BY -> getPeopleBy(event);
		}
	}

	private void addPerson(GuildMessageReceivedEvent event, String[] raws) {
		var view = personDomainService
			.add(new PersonRequest()
				.setSteamId(Long.parseLong(raws[1]))
				.setName(raws[2])
				.setNickname(raws[3])
				.setDescription(raws[4])
				.setPersonStatus(raws[5])
				.setPartyStatus(raws[6])
				.setPrintLink(event.getAuthor().getAvatarUrl()));

		event.getChannel()
			.sendMessage(new EmbedBuilder()
				.setTitle("Готово смертный. Подтверди что все правильно!")
				.addField("ID", view.getSteamId().toString(), false)
				.addField("ФИО", view.getName(), true)
				.addField("Позывной", view.getNickname(), true)
				.addField("Описание", view.getDescription(), false)
				.addField("Статус", view.getPersonStatus(), true)
				.addField("Группировака", view.getPartyStatus(), true)
				.setImage(view.getPrintLink())
				.setColor(new Color(139, 0, 0))
				.build())
			.queue(this::addReactions);
	}

	private void updatePerson(GuildMessageReceivedEvent event) {
		//TODO
	}

	private void updateCashAccount(GuildMessageReceivedEvent event) {
		//TODO
	}

	private void getPerson(GuildMessageReceivedEvent event, String[] raws) {
		var view = personDomainService.getBySteamId(Long.parseLong(raws[1]));

		var tasks = new StringBuilder();

		view.getTasks().forEach(o -> {
			tasks.append(o.getId());
			tasks.append("\n");
			tasks.append(o.getName());
			tasks.append("\n");
			tasks.append(o.getTaskStatus());
			tasks.append("\n\n");
		});

		event.getChannel()
			.sendMessage(new EmbedBuilder()
				.setTitle("Описание души")
				.addField("ID", view.getSteamId().toString(), false)
				.addField("ФИО", view.getName(), true)
				.addField("Позывной", view.getNickname(), true)
				.addField("Описание", view.getDescription(), false)
				.addField("Статус", view.getPersonStatus(), true)
				.addField("Группировака", view.getPartyStatus(), true)
				.addField("Счет", view.getCashAccount() == null ? "Не открыт" : view.getCashAccount().toString(), false)
				.addField("Задачи", tasks.toString(), false)
				.setImage(view.getPrintLink())
				.setColor(new Color(139, 0, 0))
				.build())
			.submit();
	}

	private void getPeopleBy(GuildMessageReceivedEvent event) {
		//TODO
	}

	private void addReactions(Message message) {
		message.addReaction(CHECK_MARK).queue();
		message.addReaction(CROSS_MARK).queue();
	}
}
