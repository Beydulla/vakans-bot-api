package com.vakans.bot.api.controller;


import com.vakans.bot.api.domain.model.Website;
import com.vakans.bot.api.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/website")
public class WebsiteController {

    @Autowired
    private WebsiteService websiteService;

    @PostMapping
    public Website create(@RequestBody @Valid final Website website) {
        return websiteService.create(website);
    }

}
