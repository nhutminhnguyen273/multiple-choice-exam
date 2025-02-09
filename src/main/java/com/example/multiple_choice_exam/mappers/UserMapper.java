package com.example.multiple_choice_exam.mappers;

import com.example.multiple_choice_exam.dto.requests.users.CreateUserDTO;
import com.example.multiple_choice_exam.dto.requests.users.UpdateUserDTO;
import com.example.multiple_choice_exam.dto.requests.users.UserDTO;
import com.example.multiple_choice_exam.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // Chuyển từ DTO sang Entity
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User toCreateUserEntity(CreateUserDTO userDTO);
    User toUpdateUserEntity(@MappingTarget User user, UpdateUserDTO userDTO);

    // Chuyển từ Entity sang DTO
    UserDTO toUserDTO(User user);
}
