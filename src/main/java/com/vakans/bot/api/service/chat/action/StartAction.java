package com.vakans.bot.api.service.chat.action;

import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.vakans.bot.api.constants.ChatActionType;
import com.vakans.bot.api.domain.dto.request.TelegramMessageDTO;
import com.vakans.bot.api.domain.model.Telegram;
import com.vakans.bot.api.repository.TelegramRepository;
import com.vakans.bot.api.service.TelegramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class StartAction implements ChatAction{

    @Autowired
    private TelegramRepository telegramRepository;
    @Autowired
    private TelegramService telegramService;
    @Value("${button.skip}")
    private String skipButton;
    @Value("${message.tag}")
    private String tagMessage;

    @Override
    public void handleAction(final TelegramMessageDTO telegramMessageDTO, final Telegram telegram) {
        final Telegram newTelegram = Telegram.builder().chatId(telegramMessageDTO.getChatId())
                .createdAt(LocalDateTime.now()).confirmed((byte) 1).stage(ChatActionType.TAG).build();
        telegramRepository.save(newTelegram);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(skipButton);
        telegramService.sendMessage(telegramMessageDTO.getChatId(), tagMessage, replyKeyboardMarkup);
    }

    @Override
    public ChatActionType getType() {
        return ChatActionType.START;
    }
}
