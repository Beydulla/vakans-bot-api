package com.vakans.bot.api.polling;

import com.vakans.bot.api.domain.dto.request.TelegramMessageDTO;
import com.vakans.bot.api.service.TelegramServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;


@Component
public class TelegramPollingBot extends TelegramLongPollingBot {

    private final static Logger LOGGER = LoggerFactory.getLogger(TelegramPollingBot.class);

    @Autowired
    private TelegramServiceImpl telegramService;

    @Value("${telegram.bot.username}")
    private String botUsername;
    @Value("${telegram.bot.token}")
    private String botToken;


    @Override
    public void onUpdateReceived(Update update) {
        final TelegramMessageDTO telegramMessageDTO = TelegramMessageDTO.builder()
                .messageText(update.getMessage().getText()).chatId(update.getMessage().getChatId()).build();
        LOGGER.info("Received message text; {}", telegramMessageDTO.getMessageText());
        telegramService.handleMessageRequest(telegramMessageDTO);
        LOGGER.info("Finished handling request");
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}