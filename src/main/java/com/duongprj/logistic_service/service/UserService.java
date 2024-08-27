package com.duongprj.logistic_service.service;

import com.duongprj.logistic_service.dto.user.request.UserCreationRequest;
import com.duongprj.logistic_service.dto.user.response.UserUpdateRequest;
import com.duongprj.logistic_service.dto.user.request.UserResponse;
import com.duongprj.logistic_service.entity.User;
import com.duongprj.logistic_service.enums.Role;
import com.duongprj.logistic_service.exception.AppException;
import com.duongprj.logistic_service.exception.ErrorCode;
import com.duongprj.logistic_service.mapper.UserMapper;
import com.duongprj.logistic_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public User createUser(UserCreationRequest request){
        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);

        return userRepository.save(user);
    }

    public UserResponse getMyInfo(){
        var context = SecurityContextHolder.getContext();
        var name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUser(UserUpdateRequest request){
        var context = SecurityContextHolder.getContext();
        var name = context.getAuthentication().getName();
        log.info("Update user info: {}", name);
        User user = userRepository.findByUsername(name).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));

        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userMapper.toUserResponse(userRepository.save(user));
    }

}
