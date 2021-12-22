package com.vakans.bot.api.service;

import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.vakans.bot.api.domain.dto.request.TelegramMessageDTO;
import com.vakans.bot.api.domain.dto.response.TelegramView;
import com.vakans.bot.api.domain.model.Telegram;

public interface TelegramService {

    TelegramView create(final long userId);

    void handleMessageRequest(final TelegramMessageDTO telegramMessageDTO);

    void sendMessage(final long chatId, final String message);

    void sendMessage(final long chatId, final String message, final ReplyKeyboardMarkup replyKeyboardMarkup);

    void saveTelegram(final Telegram telegram);
}
