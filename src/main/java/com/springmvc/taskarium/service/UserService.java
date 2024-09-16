package com.springmvc.taskarium.service;

import com.springmvc.taskarium.model.dto.UserDto;
import com.springmvc.taskarium.model.dto.UserLoginDto;
import com.springmvc.taskarium.model.dto.UserRegistrationDto;
import com.springmvc.taskarium.model.dto.UserUpdateDto;
import com.springmvc.taskarium.model.entity.User;

import java.util.List;


public interface UserService {
    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    UserDto getUserByUsername(String username);

    void registerUser(UserRegistrationDto userDto);

    void loginUser(UserLoginDto userDto);

    void updateUser(UserUpdateDto userDto);

    void deleteUser(Long id);

    User getCurrentUser();
}
