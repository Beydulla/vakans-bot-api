package com.vakans.bot.api.domain.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TelegramView {

    private long id;

    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;

    private long chatId;
    private String confirmationKey;
    private byte confirmed;
    private long userId;
}
