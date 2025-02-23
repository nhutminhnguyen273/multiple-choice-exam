package com.example.userservice.service;

import com.example.sharedlibrary.exception.custom.AlreadyExistedException;
import com.example.sharedlibrary.exception.custom.NotFoundException;
import com.example.sharedlibrary.response.Response;
import com.example.userservice.dto.user_dto.CreateUserDTO;
import com.example.userservice.dto.user_dto.UpdateUserDTO;
import com.example.userservice.dto.user_dto.UserDTO;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public Response<List<UserDTO>> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            List<UserDTO> userDTOList = users
                    .stream()
                    .map(userMapper::toUserDTO)
                    .toList();
            return new Response<>("Success", userDTOList);
        } catch (Exception ex) {
            return new Response<>(
                    500,
                    ex.getMessage()
            );
        }
    }

    public Response<UserDTO> findUserById(String userId) {
        try {
            User user = userRepository.findById(userId).orElseThrow(
                    () -> new NotFoundException("Không tìm thấy người dùng")
            );
            UserDTO userDTO = userMapper.toUserDTO(user);
            return new Response<>("Success", userDTO);
        } catch (NotFoundException ex) {
            return new Response<>(
                    404,
                    ex.getMessage()
            );
        } catch (Exception ex) {
            return new Response<>(
                    500,
                    ex.getMessage()
            );
        }
    }

    public Response<UserDTO> createUser(CreateUserDTO input) {
        try {
            if (userRepository.existsByCode(input.getCode()))
                throw new AlreadyExistedException("Mã " + input.getCode() + " đã tồn tại");
            if (userRepository.existsByEmail(input.getEmail()))
                throw new AlreadyExistedException("Email " + input.getEmail() + " đã tồn tại");
            if (userRepository.existsByPhone(input.getPhone()))
                throw new AlreadyExistedException("Số điện thoại " + input.getPhone() + " đã tồn tại");
            User user = userMapper.toCreateUserEntity(input);
            userRepository.save(user);
            UserDTO userDTO = userMapper.toUserDTO(user);
            return new Response<>("Success", userDTO);
        } catch (AlreadyExistedException ex) {
            return new Response<>(
                    400,
                    ex.getMessage()
            );
        } catch (Exception ex) {
            return new Response<>(
                    500,
                    ex.getMessage()
            );
        }
    }

    public Response<UserDTO> updateUser(String userId, UpdateUserDTO input) {
        try {
            User user = userRepository.findById(userId).orElseThrow(
                    () -> new NotFoundException("Không tìm thấy người dùng")
            );
            if (!user.getEmail().equals(input.getEmail()) && userRepository.existsByEmail(input.getEmail()))
                throw new AlreadyExistedException("Email " + input.getEmail() + " đã tồn tại");
            if (!user.getPhone().equals(input.getPhone()) && userRepository.existsByPhone(input.getPhone()))
                throw new AlreadyExistedException("Email " + input.getPhone() + " đã tồn tại");
            User updatedUser = userMapper.toUpdateUserEntity(user, input);
            userRepository.save(updatedUser);
            UserDTO userDTO = userMapper.toUserDTO(updatedUser);
            return new Response<>("Success", userDTO);
        } catch (NotFoundException ex) {
            return new Response<>(
                    404,
                    ex.getMessage()
            );
        } catch (AlreadyExistedException ex) {
            return new Response<>(
                    400,
                    ex.getMessage()
            );
        } catch (Exception ex) {
            return new Response<>(
                    500,
                    ex.getMessage()
            );
        }
    }

    public Response<Void> deleteUser(String userId) {
        try {
            User user = userRepository.findById(userId).orElseThrow(
                    () -> new NotFoundException("Không tìm thấy người dùng")
            );
            userRepository.delete(user);
            return new Response<>(
                    200,
                    "Xóa tài khoản thành công"
            );
        } catch (NotFoundException ex) {
            return new Response<>(
                    404,
                    ex.getMessage()
            );
        } catch (Exception ex) {
            return new Response<>(
                    400,
                    ex.getMessage()
            );
        }
    }
}
