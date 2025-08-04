package com.deathexxsize.TheTwitterKiller.service;


import com.deathexxsize.TheTwitterKiller.exception.UserNotFoundException;
import com.deathexxsize.TheTwitterKiller.model.User;
import com.deathexxsize.TheTwitterKiller.model.UserPrincipal;
import com.deathexxsize.TheTwitterKiller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->new UserNotFoundException(username +" not found"));

        return new UserPrincipal(user);
    }
}
