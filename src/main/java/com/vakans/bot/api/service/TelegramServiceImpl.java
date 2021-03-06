package com.vakans.bot.api.service;

import com.google.gson.JsonSyntaxException;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.vakans.bot.api.constants.ChatActionType;
import com.vakans.bot.api.domain.dto.request.TelegramMessageDTO;
import com.vakans.bot.api.domain.dto.response.TelegramView;
import com.vakans.bot.api.domain.model.Telegram;
import com.vakans.bot.api.domain.model.User;
import com.vakans.bot.api.factory.ChatActionFactory;
import com.vakans.bot.api.mapper.TelegramMapper;
import com.vakans.bot.api.repository.TelegramRepository;
import com.vakans.bot.api.service.chat.action.ChatAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TelegramServiceImpl implements TelegramService{

    @Autowired
    private UserService userService;
    @Autowired
    private TelegramRepository telegramRepository;
    @Autowired
    private TelegramMapper telegramMapper;

    @Autowired
    private ChatActionFactory chatActionFactory;
    private TelegramBot telegramBot;

    @Value("${telegram.bot.token}")
    private String telegramToken;
    @Value("${button.skip}")
    private String skipButton;
    @Value("${button.reset}")
    private String resetButton;

    @PostConstruct
    public void init(){
        telegramBot = new TelegramBot(telegramToken);
    }

    @Override
    public void handleMessageRequest(final TelegramMessageDTO telegramMessage) {
        ChatActionType currentActionType = ChatActionType.START;
        Telegram telegram = null;
        if(resetButton.equals(telegramMessage.getMessageText())){
            currentActionType = ChatActionType.RESET;
        }else if(isChatIdExist(telegramMessage.getChatId())){
            telegram = telegramRepository.getTelegramByChatId(telegramMessage.getChatId());
            currentActionType = telegram.getStage();
        }
        final ChatAction action = chatActionFactory.getChatAction(currentActionType);
        if(action != null){
            if(skipButton.equals(telegramMessage.getMessageText())){
                telegramMessage.setMessageText(null);
            }
            action.handleAction(telegramMessage, telegram);
        }
    }

    @Override
    public void sendMessage(final long chatId, final String message, final ReplyKeyboardMarkup replyKeyboardMarkup) {
        final SendMessage request = new SendMessage(chatId, message)
                .parseMode(ParseMode.HTML)
                .replyMarkup(replyKeyboardMarkup);
        try {
            telegramBot.execute(request);
        }catch (JsonSyntaxException ignored){

        }

    }

    @Override
    public void sendMessage(final long chatId, final String message) {
        final SendMessage request = new SendMessage(chatId, message)
                .parseMode(ParseMode.HTML);
        try {
            telegramBot.execute(request);
        }catch (JsonSyntaxException ignored){

        }
    }

    private boolean isChatIdExist(final long chatId){
        return telegramRepository.countTelegramByChatId(chatId) > 0;
    }

    @Override
    public void saveTelegram(final Telegram telegram) {
        telegramRepository.save(telegram);
    }

    @Override
    @Transactional
    public void deleteByChatId(final long chatId) {
        telegramRepository.deleteByChatId(chatId);
    }


    @Override
    public TelegramView create(final long userId){
        final User user = userService.loadUserByUserId(userId);
        final Telegram telegram = telegramRepository.save(initializeTelegram(user));
        return telegramMapper.toTelegramView(telegram);
    }

    private Telegram initializeTelegram(final User user){
        final String confirmationKey = UUID.randomUUID().toString();
        final LocalDateTime localDateTimeNow = LocalDateTime.now();
        return Telegram.builder().createdAt(localDateTimeNow)
                .expiredAt(localDateTimeNow.plusDays(1)).confirmationKey(confirmationKey)
                .confirmed((byte) 0).build();

    }

}
