package com.duongprj.logistic_service.service;

import com.duongprj.logistic_service.dto.request.UpdateStaffRequest;
import com.duongprj.logistic_service.dto.request.WorkUnitCreationRequest;
import com.duongprj.logistic_service.dto.request.WorkUnitUpdateRequest;
import com.duongprj.logistic_service.dto.response.UserResponse;
import com.duongprj.logistic_service.dto.response.WorkUnitResponse;
import com.duongprj.logistic_service.entity.User;
import com.duongprj.logistic_service.entity.WorkUnit;
import com.duongprj.logistic_service.exception.AppException;
import com.duongprj.logistic_service.exception.ErrorCode;
import com.duongprj.logistic_service.mapper.UserMapper;
import com.duongprj.logistic_service.mapper.WorkUnitMapper;
import com.duongprj.logistic_service.repository.UserRepository;
import com.duongprj.logistic_service.repository.WorkUnitRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@PreAuthorize("hasRole('ADMIN')")
public class AdminService {
    UserRepository userRepository;
    UserMapper userMapper;
    WorkUnitRepository workUnitRepository;
    WorkUnitMapper workUnitMapper;

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    public UserResponse getUser(String id) {
        log.info("In method get User by Id");
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!")));
    }

    public List<WorkUnitResponse> getAllWorkUnits() {
        return workUnitRepository.findAll().stream().map(workUnitMapper::toWorkUnitResponse).toList();
    }

    public WorkUnitResponse createWorkUnit(WorkUnitCreationRequest request) {
        if (workUnitRepository.existsByCode(request.getCode()))
            throw new AppException(ErrorCode.UNIT_EXISTED);

        WorkUnit workUnit = workUnitMapper.toWorkUnit(request);
        return workUnitMapper.toWorkUnitResponse(workUnitRepository.save(workUnit));
    }

    public WorkUnitResponse updateWorkUnit(String workUnitCode, WorkUnitUpdateRequest request) {
        WorkUnit workUnit = workUnitRepository.findByCode(workUnitCode)
                .orElseThrow(() -> new AppException(ErrorCode.UNIT_NOT_EXISTED));
        workUnitMapper.updateWorkUnit(workUnit, request);
        return workUnitMapper.toWorkUnitResponse(workUnitRepository.save(workUnit));
    }

    public void deleteWorkUnit(String workUnitId) {
        workUnitRepository.deleteById(workUnitId);
    }

    public UserResponse userRoleChange(String username, UpdateStaffRequest request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        user.getRoles().add(String.valueOf(request.getRole()));
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public UserResponse updateStaff(String username, UpdateStaffRequest request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        user.getRoles().add(String.valueOf(request.getRole()));

        if (request.getWorkUnit() != null && !workUnitRepository.existsByCode(request.getWorkUnit())) {
            throw new AppException(ErrorCode.UNIT_NOT_EXISTED);
        }

        user.setWorkUnit(request.getWorkUnit());
        return userMapper.toUserResponse(userRepository.save(user));
    }
}
