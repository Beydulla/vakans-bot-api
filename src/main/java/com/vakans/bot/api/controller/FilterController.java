package com.vakans.bot.api.controller;

import com.vakans.bot.api.domain.dto.request.FilterRequest;
import com.vakans.bot.api.domain.model.Filter;
import com.vakans.bot.api.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/filter")
public class FilterController {

    @Autowired
    private FilterService filterService;

    @PostMapping
    public Filter create(@RequestBody final FilterRequest filterRequest) {
        return filterService.create(filterRequest);
    }
}
