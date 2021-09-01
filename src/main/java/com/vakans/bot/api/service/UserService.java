package com.vakans.bot.api.service;


import com.vakans.bot.api.domain.dto.request.CreateUserRequest;
import com.vakans.bot.api.domain.dto.request.UserWebsiteRequest;
import com.vakans.bot.api.domain.dto.response.UserView;
import com.vakans.bot.api.domain.model.Role;
import com.vakans.bot.api.domain.model.User;
import com.vakans.bot.api.domain.model.Website;
import com.vakans.bot.api.exceptionhandlers.exception.RecordNotFoundException;
import com.vakans.bot.api.mapper.UserMapper;
import com.vakans.bot.api.repository.UserRepository;
import com.vakans.bot.api.repository.WebsiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService
{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private WebsiteRepository websiteRepository;

    @PostConstruct
    public void init(){
        final Role role = Role.builder().authority("Admin").build();
        final Set<Role> authorities = new HashSet<>();
        authorities.add(role);
        final String password = passwordEncoder.encode("test");
        final User user = User.builder().authorities(authorities).createdAt(LocalDateTime.now()).username("beydu").password(password).build();
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(final String username){
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(format("User with username - %s, not found", username)));
    }

    public User loadUserByUserId(final long id){
        return userRepository.findById(id).orElseThrow(
                () -> new RecordNotFoundException(format("User with id - %s, not found", id)));
    }

    @Transactional
    public UserView create(final CreateUserRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new ValidationException("Username exists!");
        }
        if (!request.getPassword().equals(request.getRePassword())) {
            throw new ValidationException("Passwords don't match!");
        }
        User user = userMapper.toUser(request);
        user.setAuthorities(Collections.emptySet());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user = userRepository.save(user);
        return userMapper.toUserView(user);

    }

    public UserView addWebsite(final UserWebsiteRequest userWebsiteRequest){
        final User user = loadUserByUserId(userWebsiteRequest.getUserId());
        final Website website = websiteRepository.findById(userWebsiteRequest.getWebsiteId()).orElseThrow(
                () -> new RecordNotFoundException(format("Website with id - %s, not found", userWebsiteRequest.getWebsiteId())));
        user.addWebsite(website);
        userRepository.save(user);
        return userMapper.toUserView(user);
    }
}
