package com.vakans.bot.api.mapper;


import com.vakans.bot.api.domain.dto.request.FilterRequest;
import com.vakans.bot.api.domain.model.Filter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class FilterMapper {

    @Mapping(target = "telegram", ignore = true)
    public abstract Filter toFilter(final FilterRequest filterRequest);
}
