package com.example.userservice.mapper;

import com.example.userservice.dto.user_dto.CreateUserDTO;
import com.example.userservice.dto.user_dto.UpdateUserDTO;
import com.example.userservice.dto.user_dto.UserDTO;
import com.example.userservice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toCreateUserEntity(CreateUserDTO input);
    User toUpdateUserEntity(@MappingTarget User user, UpdateUserDTO input);
    UserDTO toUserDTO(User user);
}
