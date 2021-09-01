package com.vakans.bot.api.mapper;


import com.vakans.bot.api.domain.dto.response.TelegramView;
import com.vakans.bot.api.domain.model.Telegram;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public abstract class TelegramMapper {

    @Mapping(target = "userId", ignore = true)
    public abstract TelegramView toTelegramView(final Telegram telegram);

    @AfterMapping
    protected void afterToTelegramView(final Telegram telegram, @MappingTarget TelegramView telegramView) {
        telegramView.setUserId(telegram.getUser().getId());
    }
}
