package com.springmvc.taskarium.config;

import com.springmvc.taskarium.model.entity.User;
import com.springmvc.taskarium.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final static String ROLE_PREFIX = "ROLE_";
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username is {}",username);
        Optional<User> user=userRepository.findByUsername(username);
        user.orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
        log.info("user is {}",user.get());

        String password = user.get().getPassword();
        log.info("password is {}",password);
        String role = ROLE_PREFIX + user.get().getRole().toString();
        log.info("role is {}",role);

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(role));
        log.info("roles is {}",roles);
        return new CustomUserDetails(username, password, roles);
    }
}
