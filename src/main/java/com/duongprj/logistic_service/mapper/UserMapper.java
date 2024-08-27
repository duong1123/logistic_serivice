package com.duongprj.logistic_service.mapper;

import com.duongprj.logistic_service.dto.request.UserCreationRequest;
import com.duongprj.logistic_service.dto.request.UserUpdateRequest;
import com.duongprj.logistic_service.dto.response.UserResponse;
import com.duongprj.logistic_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}