package com.vakans.bot.api.service;

import com.vakans.bot.api.domain.dto.request.FilterRequest;
import com.vakans.bot.api.domain.model.Filter;
import com.vakans.bot.api.mapper.FilterMapper;
import com.vakans.bot.api.repository.FilterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilterService {

    @Autowired
    private UserService userService;
    @Autowired
    private FilterRepository filterRepository;
    @Autowired
    private FilterMapper filterMapper;

    public Filter create(final FilterRequest filterRequest){
        final Filter filter = filterMapper.toFilter(filterRequest);
        filter.setUser(userService.loadUserByUserId(filterRequest.getUserId()));
        filterRepository.save(filter);
        return filter;
    }
}
