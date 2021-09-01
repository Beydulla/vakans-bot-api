package com.vakans.bot.api.service;

import com.vakans.bot.api.domain.model.Website;
import com.vakans.bot.api.repository.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebsiteService {

    @Autowired
    private WebsiteRepository websiteRepository;

    public Website create(final Website website){
        return websiteRepository.save(website);
    }
}
