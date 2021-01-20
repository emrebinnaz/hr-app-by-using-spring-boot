package com.example.hrapp.hrapp.Service.Impl;

import com.example.hrapp.hrapp.Domain.User;
import com.example.hrapp.hrapp.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

       return userRepository.findByUsername(username);
    }

    public void saveUser(final User user) {
        userRepository.save(user);
    }

    public boolean isUserExists(final String username) {

        return userRepository.existsByUsername(username);
    }

}
