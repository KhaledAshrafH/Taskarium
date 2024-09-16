package com.springmvc.taskarium.service.impl;

import com.springmvc.taskarium.exception.UserNotFoundException;
import com.springmvc.taskarium.exception.UsernameAlreadyExistsException;
import com.springmvc.taskarium.model.dto.UserDto;
import com.springmvc.taskarium.model.dto.UserLoginDto;
import com.springmvc.taskarium.model.dto.UserRegistrationDto;
import com.springmvc.taskarium.model.dto.UserUpdateDto;
import com.springmvc.taskarium.model.entity.User;
import com.springmvc.taskarium.model.enums.RoleType;
import com.springmvc.taskarium.model.mapper.UserMapper;
import com.springmvc.taskarium.repository.UserRepository;
import com.springmvc.taskarium.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;


    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toDTOs(users);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        return userMapper.toDTO(user);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        return userMapper.toDTO(user);
    }

    @Override
    public void registerUser(UserRegistrationDto userDto) {
        if (userRepository.findByUsername(userDto.getUsername()).isPresent())
            throw new UsernameAlreadyExistsException("Username already exists");

        User user = userMapper.toEntity(userDto);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }

    @Override
    public void loginUser(UserLoginDto userDto) {
//        // Retrieve UserDetails by username
//        UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
//        log.warn("UserDetails: {}",userDetails.getPassword());
//        log.warn("UserDto: {}",userDto.getPassword());
//        log.warn("passwordEncoder.matches: {}",passwordEncoder.matches(userDto.getPassword(), userDetails.getPassword()));
//        // Check password using PasswordEncoder
//        if (!passwordEncoder.matches(userDto.getPassword(), userDetails.getPassword())){
//            log.warn("Invalid password");
//            throw new BadCredentialsException("Invalid username or password");
//        }
//
//
//        // Authentication successful, create Authentication object
//        Authentication authentication = new UsernamePasswordAuthenticationToken(
//                userDetails.getUsername(), null, userDetails.getAuthorities());
//
//        // 4. Set Authentication in SecurityContext
//        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
        if (!passwordEncoder.matches(userDto.getPassword(), userDetails.getPassword())) {
            log.warn("Invalid password");
            throw new BadCredentialsException("Invalid username or password");
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    @Override
    public void updateUser(UserUpdateDto userDto) {

    }

    @Override
    public void deleteUser(Long id) {

    }


    public User getCurrentUser() {
        // Get the authenticated user
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            throw new RuntimeException("User not found");
        }

        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
