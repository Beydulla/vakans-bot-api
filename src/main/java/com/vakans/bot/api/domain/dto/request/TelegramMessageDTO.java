package com.vakans.bot.api.domain.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TelegramMessageDTO {

    private long chatId;
    private String messageText;
}
