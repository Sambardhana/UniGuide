package com.uniguide.service;

import com.uniguide.dto.UserRequest;
import com.uniguide.dto.UserResponse;
import com.uniguide.entity.Department;
import com.uniguide.entity.Role;
import com.uniguide.entity.User;
import com.uniguide.exception.DuplicateResourceException;
import com.uniguide.exception.ResourceNotFoundException;
import com.uniguide.mapper.UserMapper;
import com.uniguide.repository.DepartmentRepository;
import com.uniguide.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @Spy
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    private User sampleUser;
    private Department sampleDepartment;
    private UserRequest sampleRequest;

    @BeforeEach
    void setUp() {
        sampleDepartment = Department.builder()
                .id(1L)
                .name("Computer Science")
                .code("CSE")
                .build();

        sampleUser = User.builder()
                .id(10L)
                .email("student@university.edu")
                .password("hashedPassword")
                .fullName("John Doe")
                .role(Role.STUDENT)
                .department(sampleDepartment)
                .build();

        sampleRequest = UserRequest.builder()
                .email("student@university.edu")
                .password("password123")
                .fullName("John Doe")
                .role(Role.STUDENT)
                .departmentId(1L)
                .build();
    }

    @Test
    @DisplayName("getUserById returns UserResponse when found")
    void getUserById_Found() {
        when(userRepository.findById(10L)).thenReturn(Optional.of(sampleUser));

        UserResponse response = userService.getUserById(10L);

        assertNotNull(response);
        assertEquals("student@university.edu", response.getEmail());
        assertEquals("Computer Science", response.getDepartmentName());
        verify(userRepository).findById(10L);
    }

    @Test
    @DisplayName("getUserById throws ResourceNotFoundException when user does not exist")
    void getUserById_NotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(99L));
    }

    @Test
    @DisplayName("createUser throws DuplicateResourceException when email already exists")
    void createUser_DuplicateEmail() {
        when(userRepository.existsByEmail("student@university.edu")).thenReturn(true);

        assertThrows(DuplicateResourceException.class, () -> userService.createUser(sampleRequest));
    }

    @Test
    @DisplayName("createUser succeeds when email is unique and department exists")
    void createUser_Success() {
        when(userRepository.existsByEmail("student@university.edu")).thenReturn(false);
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(sampleDepartment));
        when(userRepository.save(any(User.class))).thenReturn(sampleUser);

        UserResponse response = userService.createUser(sampleRequest);

        assertNotNull(response);
        assertEquals("student@university.edu", response.getEmail());
        verify(userRepository).save(any(User.class));
    }

    @Test
    @DisplayName("getAllUsers returns mapped response list")
    void getAllUsers_ReturnsList() {
        when(userRepository.findAll()).thenReturn(List.of(sampleUser));

        List<UserResponse> list = userService.getAllUsers();

        assertEquals(1, list.size());
        assertEquals("John Doe", list.get(0).getFullName());
    }
}
