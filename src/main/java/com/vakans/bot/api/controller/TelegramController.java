package com.vakans.bot.api.controller;


import com.vakans.bot.api.domain.dto.response.TelegramView;
import com.vakans.bot.api.service.TelegramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/telegram")
public class TelegramController {

    @Autowired
    private TelegramService telegramService;

    @PostMapping("/{userId}")
    public TelegramView create(@PathVariable final long userId) {
        System.out.println(userId);
        return telegramService.create(userId);
    }

}
