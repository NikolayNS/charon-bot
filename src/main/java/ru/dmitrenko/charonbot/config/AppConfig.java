package ru.dmitrenko.charonbot.config;

import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dmitrenko.charonbot.handler.GuildMessageReactionAddHandler;
import ru.dmitrenko.charonbot.handler.GuildMessageReceivedHandler;

import javax.security.auth.login.LoginException;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

	@Value("${application.token}")
	private String token;

	private final GuildMessageReceivedHandler guildMessageReceivedHandler;
	private final GuildMessageReactionAddHandler guildMessageReactionAddHandler;

	@Bean
	public JDA jda() throws LoginException {
		return JDABuilder
			.createDefault(token)
			.addEventListeners(guildMessageReceivedHandler, guildMessageReactionAddHandler)
			.setActivity(Activity.listening("каждого страждущего"))
			.build();
	}
}
