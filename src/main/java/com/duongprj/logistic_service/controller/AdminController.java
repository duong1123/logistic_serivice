package com.duongprj.logistic_service.controller;

import com.duongprj.logistic_service.dto.common.ApiResponse;
import com.duongprj.logistic_service.dto.request.UpdateStaffRequest;
import com.duongprj.logistic_service.dto.request.WorkUnitCreationRequest;
import com.duongprj.logistic_service.dto.request.WorkUnitUpdateRequest;
import com.duongprj.logistic_service.dto.response.UserResponse;
import com.duongprj.logistic_service.dto.response.WorkUnitResponse;
import com.duongprj.logistic_service.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/getUser/{userId}")
    ApiResponse<UserResponse> getUser(@PathVariable String userId){
        return ApiResponse.<UserResponse>builder()
                .response(adminService.getUser(userId))
                .build();
    }

    @DeleteMapping("/deleteUser/{userId}")
    ApiResponse<String> deleteUser(@PathVariable String userId){
        adminService.deleteUser(userId);
        return ApiResponse.<String>builder()
                .response("User deleted")
                .build();
    }
    @GetMapping("/getUsers")
    ApiResponse<List<UserResponse>> getUsers(){
        var  authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return ApiResponse.<List<UserResponse>>builder()
                .response(adminService.getUsers())
                .build();
    }

    @PostMapping("/createWorkUnit")
    ApiResponse<WorkUnitResponse> createWorkUnit(@RequestBody WorkUnitCreationRequest request){
        return ApiResponse.<WorkUnitResponse>builder()
                .response(adminService.createWorkUnit(request))
                .build();
    }

    @PutMapping("/editWorkUnit/{code}")
    ApiResponse<WorkUnitResponse> editWorkUnit(@PathVariable String code,@RequestBody  WorkUnitUpdateRequest request){
        return ApiResponse.<WorkUnitResponse>builder()
                .response(adminService.updateWorkUnit(code, request))
                .build();
    }

    @DeleteMapping("/deleteWorkUnit/{id}")
    ApiResponse<String> deleteWorkUnit(@PathVariable String id){
        adminService.deleteWorkUnit(id);
        return ApiResponse.<String>builder()
                .response("WorkUnit deleted")
                .build();
    }

    @GetMapping("/getWorkUnits")
    ApiResponse<List<WorkUnitResponse>> getWorkUnits(){
        return ApiResponse.<List<WorkUnitResponse>>builder()
                .response(adminService.getAllWorkUnits())
                .build();
    }

    @PutMapping("/change_role/{username}")
    ApiResponse<UserResponse> editUserRole(@PathVariable String username,@RequestBody UpdateStaffRequest request){
        return ApiResponse.<UserResponse>builder()
                .response(adminService.userRoleChange(username, request))
                .build();
    }

    @PutMapping("/updateStaff/{username}")
    ApiResponse<UserResponse> updateStaff(@PathVariable String username, @RequestBody UpdateStaffRequest request) {
        return ApiResponse.<UserResponse>builder()
                .response(adminService.updateStaff(username, request))
                .build();
    }

}
