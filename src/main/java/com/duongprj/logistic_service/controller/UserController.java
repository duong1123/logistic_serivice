package com.duongprj.logistic_service.controller;

import com.duongprj.logistic_service.dto.common.ApiResponse;
import com.duongprj.logistic_service.dto.user.request.UserCreationRequest;
import com.duongprj.logistic_service.dto.user.response.UserUpdateRequest;
import com.duongprj.logistic_service.dto.user.request.UserResponse;
import com.duongprj.logistic_service.entity.User;
import com.duongprj.logistic_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/create")
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request){
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResponse(userService.createUser(request));
        return apiResponse;
    }


    @GetMapping("/myInfo")
    ApiResponse<UserResponse> getMyInfo(){
        return ApiResponse.<UserResponse>builder()
                .response(userService.getMyInfo())
                .build();
    }

    @PutMapping("/infoUpdate")
    ApiResponse<UserResponse> updateUser(@RequestBody UserUpdateRequest request){
        return ApiResponse.<UserResponse>builder()
                .response(userService.updateUser(request))
                .build();
    }
}
