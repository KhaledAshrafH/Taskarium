package com.springmvc.taskarium.model.mapper;

import com.springmvc.taskarium.model.dto.*;
import com.springmvc.taskarium.model.entity.Note;
import com.springmvc.taskarium.model.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDto userDto);
    User toEntity(UserRegistrationDto userDto);
    User toEntity(UserUpdateDto userDto);

    UserDto toDTO(User user);
    List<UserDto> toDTOs(List<User> users);
}
