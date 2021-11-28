package com.vakans.bot.api.service;

import com.vakans.bot.api.domain.dto.response.TelegramView;
import com.vakans.bot.api.domain.model.Telegram;
import com.vakans.bot.api.domain.model.User;
import com.vakans.bot.api.mapper.TelegramMapper;
import com.vakans.bot.api.repository.TelegramRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TelegramService {

    @Autowired
    private UserService userService;
    @Autowired
    private TelegramRepository telegramRepository;
    @Autowired
    private TelegramMapper telegramMapper;

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
                .confirmed((byte) 0).user(user).build();
    }
}
