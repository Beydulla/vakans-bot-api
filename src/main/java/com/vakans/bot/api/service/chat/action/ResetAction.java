package com.vakans.bot.api.service.chat.action;

import com.vakans.bot.api.constants.ChatActionType;
import com.vakans.bot.api.domain.dto.request.TelegramMessageDTO;
import com.vakans.bot.api.domain.model.Telegram;
import com.vakans.bot.api.service.TelegramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ResetAction implements ChatAction{

    @Autowired
    private TelegramService telegramService;
    @Value("${message.reset}")
    private String resetMessage;

    @Override
    public void handleAction(TelegramMessageDTO telegramMessageDTO, Telegram telegram) {
        telegramService.deleteByChatId(telegramMessageDTO.getChatId());
        telegramService.sendMessage(telegramMessageDTO.getChatId(), resetMessage);
    }

    @Override
    public ChatActionType getType() {
        return ChatActionType.RESET;
    }
}
