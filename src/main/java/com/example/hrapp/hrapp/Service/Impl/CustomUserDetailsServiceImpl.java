package com.example.hrapp.hrapp.Service.Impl;

import com.example.hrapp.hrapp.Domain.User;
import com.example.hrapp.hrapp.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user =  userRepository.findByUsername(username);
        //TODO: THROW EXCEPION


        return user;
    }

    public void saveUser(final User user) {
        userRepository.save(user);
    }


}
