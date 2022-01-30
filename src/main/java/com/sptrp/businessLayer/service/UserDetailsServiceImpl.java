package com.sptrp.businessLayer.service;

import com.sptrp.businessLayer.model.User;
import com.sptrp.persistenceLayer.UserRepository;
import com.sptrp.util.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(email);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("Not found: " + email);
        }

        return new UserDetailsImpl(user.get());
    }
}
