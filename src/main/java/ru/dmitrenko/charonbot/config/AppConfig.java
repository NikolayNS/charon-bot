package ru.dmitrenko.charonbot.config;

import lombok.RequiredArgsConstructor;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dmitrenko.charonbot.listener.PersonListener;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

	@Value("${application.token}")
	private String token;

	private final PersonListener personListener;

	@Bean
	@ConfigurationProperties(value = "discord-api")
	public DiscordApi discordApi() {
		return new DiscordApiBuilder()
			.addListener(personListener)
			.setToken(token)
			.login()
			.join();
	}
}
