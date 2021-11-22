package ru.dmitrenko.charonbot.config;

import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.security.auth.login.LoginException;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

	@Value("${application.token}")
	private String token;

	@Bean
	public JDA jda(List<ListenerAdapter> listenerAdapters) throws LoginException {
		return JDABuilder
			.createDefault(token)
			.addEventListeners(listenerAdapters)
			.setActivity(Activity.listening("!help"))
			.build();
	}
}
