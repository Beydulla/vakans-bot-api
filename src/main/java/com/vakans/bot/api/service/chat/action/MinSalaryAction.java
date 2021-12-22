package com.vakans.bot.api.service.chat.action;

import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.vakans.bot.api.constants.ChatActionType;
import com.vakans.bot.api.domain.dto.request.TelegramMessageDTO;
import com.vakans.bot.api.domain.model.Filter;
import com.vakans.bot.api.domain.model.Telegram;
import com.vakans.bot.api.repository.FilterRepository;
import com.vakans.bot.api.repository.TelegramRepository;
import com.vakans.bot.api.service.TelegramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MinSalaryAction implements ChatAction{

    @Autowired
    private FilterRepository filterRepository;
    @Autowired
    private TelegramRepository telegramRepository;
    @Autowired
    private TelegramService telegramService;
    @Value("${button.skip}")
    private String skipButton;
    @Value("${message.salary.max}")
    private String maxSalaryMessage;

    @Override
    public void handleAction(TelegramMessageDTO telegramMessageDTO, Telegram telegram) {
        final Filter filter = telegram.getFilter();
        filter.setMinimumSalary(Integer.parseInt(telegramMessageDTO.getMessageText()));
        filterRepository.save(filter);

        telegram.setStage(ChatActionType.MAXIMUM_SALARY);
        telegramRepository.save(telegram);

        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(skipButton);
        telegramService.sendMessage(telegramMessageDTO.getChatId(), maxSalaryMessage, replyKeyboardMarkup);

    }

    @Override
    public ChatActionType getType() {
        return ChatActionType.MINIMUM_SALARY;
    }
}
