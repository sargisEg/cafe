package com.cafe.security;

import com.cafe.entity.User;
import com.cafe.entity.UserRole;
import com.cafe.service.core.user.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CafeUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CafeUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final User user = userService.findByUsername(username)
                .orElseThrow();

        final List<SimpleGrantedAuthority> simpleGrantedAuthorities = user.getUserRoles().stream()
                .map(UserRole::getType)
                .map(Enum::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());


        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                new BCryptPasswordEncoder().encode("password"),
                simpleGrantedAuthorities
        );
    }
}
