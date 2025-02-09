package com.example.multiple_choice_exam.services;

import com.example.multiple_choice_exam.dto.requests.users.CreateUserDTO;
import com.example.multiple_choice_exam.dto.requests.users.DeletedUserDTO;
import com.example.multiple_choice_exam.dto.requests.users.UpdateUserDTO;
import com.example.multiple_choice_exam.dto.requests.users.UserDTO;
import com.example.multiple_choice_exam.dto.responses.ApiResponse;
import com.example.multiple_choice_exam.exceptions.custom.AlreadyExistedException;
import com.example.multiple_choice_exam.exceptions.custom.NotFoundException;
import com.example.multiple_choice_exam.mappers.UserMapper;
import com.example.multiple_choice_exam.models.Role;
import com.example.multiple_choice_exam.models.User;
import com.example.multiple_choice_exam.repositories.RoleRepository;
import com.example.multiple_choice_exam.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public ApiResponse<List<UserDTO>> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOList = users.stream()
                .map(userMapper::toUserDTO)
                .toList();
        return new ApiResponse<>("Lấy danh sách người dùng thành công", userDTOList);
    }

    public ApiResponse<UserDTO> getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("Không tìm thấy dữ liệu người dùng được yêu cầu")
        );
        UserDTO userDTO = userMapper.toUserDTO(user);
        return new ApiResponse<>("Lấy dữ liệu người dùng thành công", userDTO);
    }

    public ApiResponse<User> createUser(CreateUserDTO input) {
        if (userRepository.existsByCode(input.getCode()))
            throw new AlreadyExistedException("Mã " + input.getCode() + " đã tồn tại");
        if (userRepository.existsByEmail(input.getEmail()))
            throw new AlreadyExistedException("Email " + input.getEmail() + " đã tồn tại");
        if (userRepository.existsByPhone(input.getPhone()))
            throw new AlreadyExistedException("Số điện thoại " + input.getPhone() + " đã tồn tại");
        Role role = roleRepository.findByName(input.getRoles()).orElseThrow(
                () -> new NotFoundException("Không tìm thấy vai trò " + input.getRoles())
        );
        User user = userMapper.toCreateUserEntity(input);
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setRoles(new HashSet<>(Set.of(role)));
        userRepository.save(user);
        return new ApiResponse<>("Thêm người dùng thành công", user);
    }

    public ApiResponse<User> updateUser(String userId, UpdateUserDTO input) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("Không tìm thấy dữ liệu người dùng được yêu cầu")
        );
        if (!user.getEmail().equals(input.getEmail()) && userRepository.existsByEmail(input.getEmail()))
            throw new AlreadyExistedException("Email " + input.getEmail() + " đã tồn tại");
        if (!user.getPhone().equals(input.getPhone()) && userRepository.existsByPhone(input.getPhone()))
            throw new AlreadyExistedException("Số điện thoại " + input.getPhone() + " đã tồn tại");
        User updatedUser = userMapper.toUpdateUserEntity(user, input);
        userRepository.save(updatedUser);
        return new ApiResponse<>("Cập nhật người dùng thành công", updatedUser);
    }

    public ApiResponse<User> deletedUser(String userId, DeletedUserDTO deleted) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("Không tìm thấy dữ liệu người dùng được yêu cầu")
        );
        user.setDeleted(deleted.isDeleted());
        userRepository.save(user);
        return new ApiResponse<>("Xóa người dùng thành công", user);
    }
}
