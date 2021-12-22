package com.vakans.bot.api.service.chat.action;

import com.vakans.bot.api.constants.ChatActionType;
import com.vakans.bot.api.domain.dto.request.TelegramMessageDTO;
import com.vakans.bot.api.domain.model.Telegram;

public interface ChatAction {

    void handleAction(final TelegramMessageDTO telegramMessageDTO, final Telegram telegram);

    ChatActionType getType();
}
