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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service managing user profiles, student data, and role assignments.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final UserMapper userMapper;

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponse)
                .toList();
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return userMapper.toResponse(user);
    }

    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        return userMapper.toResponse(user);
    }

    public UserResponse getUserByStudentId(String studentId) {
        User user = userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "studentId", studentId));
        return userMapper.toResponse(user);
    }

    public List<UserResponse> getUsersByRole(Role role) {
        return userRepository.findByRole(role).stream()
                .map(userMapper::toResponse)
                .toList();
    }

    public List<UserResponse> getUsersByDepartment(Long departmentId) {
        return userRepository.findByDepartmentId(departmentId).stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Transactional
    public UserResponse createUser(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("User", "email", request.getEmail());
        }

        Department department = null;
        if (request.getDepartmentId() != null) {
            department = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department", "id", request.getDepartmentId()));
        }

        User user = userMapper.toEntity(request, department);
        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    @Transactional
    public UserResponse updateUser(Long id, UserRequest request) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        if (!request.getEmail().equalsIgnoreCase(existing.getEmail())) {
            if (userRepository.existsByEmail(request.getEmail())) {
                throw new DuplicateResourceException("User", "email", request.getEmail());
            }
        }

        Department department = null;
        if (request.getDepartmentId() != null) {
            department = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department", "id", request.getDepartmentId()));
        }

        existing.setEmail(request.getEmail());
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            existing.setPassword(request.getPassword());
        }
        existing.setFullName(request.getFullName());
        existing.setRole(request.getRole());
        existing.setPhoneNumber(request.getPhoneNumber());
        existing.setStudentId(request.getStudentId());
        existing.setDepartment(department);

        User updated = userRepository.save(existing);
        return userMapper.toResponse(updated);
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User", "id", id);
        }
        userRepository.deleteById(id);
    }
}
