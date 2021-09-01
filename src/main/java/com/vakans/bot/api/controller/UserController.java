package com.vakans.bot.api.controller;


import com.vakans.bot.api.domain.dto.request.CreateUserRequest;
import com.vakans.bot.api.domain.dto.request.UserWebsiteRequest;
import com.vakans.bot.api.domain.dto.response.UserView;
import com.vakans.bot.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserView create(@RequestBody @Valid final CreateUserRequest request) {
        return userService.create(request);
    }


    @PostMapping("/add-website")
    public UserView addWebsite(@RequestBody final UserWebsiteRequest userWebsiteRequest){
        return userService.addWebsite(userWebsiteRequest);
    }

}
