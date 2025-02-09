package com.example.multiple_choice_exam.services;

import com.example.multiple_choice_exam.dto.requests.users.CreateUserDTO;
import com.example.multiple_choice_exam.dto.requests.users.DeletedUserDTO;
import com.example.multiple_choice_exam.dto.requests.users.UpdateUserDTO;
import com.example.multiple_choice_exam.dto.requests.users.UserDTO;
import com.example.multiple_choice_exam.enums.Gender;
import com.example.multiple_choice_exam.mappers.UserMapper;
import com.example.multiple_choice_exam.models.User;
import com.example.multiple_choice_exam.repositories.RoleRepository;
import com.example.multiple_choice_exam.repositories.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserService userService;

    private User user;
    private UserDTO userDTO;
    private CreateUserDTO createUserDTO;
    private UpdateUserDTO updateUserDTO;
    private DeletedUserDTO deletedUserDTO;

    void  setUp() {
        user = new User();
        user.setId("030a4ac3-3704-4446-b0e2-079178a5a04a");
        user.setCode("2180608710");
        user.setPassword("2180608710");
        user.setFirstname("Nguyễn Nhựt");
        user.setLastname("Minh");
        user.setDateOfBirth(LocalDate.of(2003, 7, 2));
        user.setGender(Gender.MALE);
        user.setEmail("test@gmail.com");
        user.setPhone("0123456789");
        user.setRoles(new HashSet<>(Set.of(
                roleRepository.findByName("SUDENT").orElseThrow()))
        );

        userDTO = new UserDTO();

    }
}
