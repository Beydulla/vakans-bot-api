package com.vakans.bot.api.service.chat.action;

import com.vakans.bot.api.constants.ChatActionType;
import com.vakans.bot.api.domain.dto.request.TelegramMessageDTO;
import com.vakans.bot.api.domain.model.Filter;
import com.vakans.bot.api.domain.model.Telegram;
import com.vakans.bot.api.repository.FilterRepository;
import com.vakans.bot.api.service.TelegramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CompanyAction implements ChatAction{

    @Autowired
    private FilterRepository filterRepository;
    @Autowired
    private TelegramService telegramService;

    @Value("${message.summary}")
    private String summaryMessage;
    @Value("${field.name.tag}")
    private String tagFieldName;
    @Value("${field.name.salary.min}")
    private String minSalaryFieldName;
    @Value("${field.name.salary.max}")
    private String maxSalaryFieldName;
    @Value("${field.name.company}")
    private String companyFieldName;

    @Override
    public void handleAction(TelegramMessageDTO telegramMessageDTO, Telegram telegram) {
        final Filter filter = telegram.getFilter();
        filter.setEmployer(telegramMessageDTO.getMessageText());
        filterRepository.save(filter);

        telegram.setStage(ChatActionType.FINISHED);
        telegramService.saveTelegram(telegram);

        telegramService.sendMessage(telegramMessageDTO.getChatId(), generateSummaryMessage(filter));

    }

    private String generateSummaryMessage(final Filter filter){
        return summaryMessage + tagFieldName + filter.getTags() +"\n" +
                minSalaryFieldName + filter.getMinimumSalary() + "\n" +
                maxSalaryFieldName + filter.getMaximumSalary() + "\n" +
                companyFieldName + filter.getEmployer() + "\n";
    }

    @Override
    public ChatActionType getType() {
        return ChatActionType.COMPANY;
    }
}
