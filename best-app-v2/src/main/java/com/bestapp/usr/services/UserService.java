package com.bestapp.usr.services;

import com.bestapp.usr.repository.UserRepository;
import com.bestapp.usr.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User myUser = userRepository.findByUserName(username);

        if (myUser == null) {
            logger.info("User does not exists: " + username);
            throw new UsernameNotFoundException("User does not exists: ".concat(username));
        }

        if (myUser.getRoles().isEmpty()) {
            logger.info("User does not have any role: ".concat(username));
            throw new UsernameNotFoundException("User does not have any role: ".concat(username));
        }

        final List<SimpleGrantedAuthority> roles =
                myUser.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(
                        Collectors.toList());

        return new org.springframework.security.core.userdetails.User(myUser.getUserName(), myUser.getPassword(),
                myUser.getEnabled(), true, true, true, roles);
    }

}
