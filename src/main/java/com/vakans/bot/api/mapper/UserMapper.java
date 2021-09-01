package com.vakans.bot.api.mapper;


import com.vakans.bot.api.domain.dto.request.CreateUserRequest;
import com.vakans.bot.api.domain.dto.response.UserView;
import com.vakans.bot.api.domain.model.Role;
import com.vakans.bot.api.domain.model.User;
import org.mapstruct.*;

import static java.util.stream.Collectors.toSet;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Mapping(target = "authorities", ignore = true)
    public abstract User toUser(final CreateUserRequest request);

    public abstract UserView toUserView(User user);


}
