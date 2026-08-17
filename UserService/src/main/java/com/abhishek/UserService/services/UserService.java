package com.abhishek.UserService.services;

import com.abhishek.UserService.dto.RegisterRequest;
import com.abhishek.UserService.dto.UserResponse;
import com.abhishek.UserService.models.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    public UserResponse register(RegisterRequest request) {

        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        User savedUser = userRepository.save(user);
        UserResponse userResponse = new UserResponse();
        userResponse.setId(String.valueOf(savedUser.getId()));
        userResponse.setEmail(savedUser.getEmail());
        userResponse.setPassword(savedUser.getPassword());
        userResponse.setFirstName(savedUser.getFirstName());
        userResponse.setLastName(savedUser.getLastName());
        userResponse.setUpdatedAt(savedUser.getUpdatedAt());
        userResponse.setCreatedAt(savedUser.getCreatedAt());
        return userResponse;

    }

    public UserResponse getUserProfile(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("User not found"));

        UserResponse userResponse = new UserResponse();
        userResponse.setId(String.valueOf(user.getId()));
        userResponse.setEmail(user.getEmail());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setUpdatedAt(user.getUpdatedAt());
        userResponse.setCreatedAt(user.getCreatedAt());
        return userResponse;
    }

    public Boolean existByUserId(Long userId) {
        log.info("Calling User service for userId: {}", userId);
        return userRepository.existsById(userId);
    }
}
