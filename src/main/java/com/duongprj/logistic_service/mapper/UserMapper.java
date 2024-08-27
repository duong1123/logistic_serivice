package com.duongprj.logistic_service.mapper;

import com.duongprj.logistic_service.dto.user.request.UserCreationRequest;
import com.duongprj.logistic_service.dto.user.response.UserUpdateRequest;
import com.duongprj.logistic_service.dto.user.request.UserResponse;
import com.duongprj.logistic_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}