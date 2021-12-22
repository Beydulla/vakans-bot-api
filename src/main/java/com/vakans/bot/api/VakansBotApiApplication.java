package com.vakans.bot.api;

import com.vakans.bot.api.polling.TelegramPollingBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
//@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
public class VakansBotApiApplication {

	@Autowired
	private TelegramPollingBot telegramPollingBot;

	public static void main(String[] args) {
		SpringApplication.run(VakansBotApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner schedulingRunner() {
		return args -> {
			try {
				final TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
				botsApi.registerBot(telegramPollingBot);

			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		};
	}
}
