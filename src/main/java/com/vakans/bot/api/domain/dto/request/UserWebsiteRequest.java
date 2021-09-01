package com.vakans.bot.api.domain.dto.request;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class UserWebsiteRequest {

    @NotBlank
    private long userId;
    @NotBlank
    private long websiteId;
}
