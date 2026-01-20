package com.fitness.userservice.services;

import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.mapper.UserMapper;
import com.fitness.userservice.models.User;
import com.fitness.userservice.repo.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserResponse register(RegisterRequest registerRequest) {
        if(userRepository.existsByEmail(registerRequest.getEmail())){
            User existingUser = userRepository.findByEmail(registerRequest.getEmail());
            return userMapper.mapToUserRespone(existingUser);
        }
        log.info("Register request : {}",registerRequest);
        User user = userRepository.save(userMapper.mapToUser(registerRequest));
        log.info("User Saved : {}",user);
        return userMapper.mapToUserRespone(user);
    }

    public UserResponse getUserProfile(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not Found"));

        return userMapper.mapToUserRespone(user);
    }

    public boolean existByUserId(String userId) {
        return userRepository.existsByKeyCloakId(userId);
    }
}
