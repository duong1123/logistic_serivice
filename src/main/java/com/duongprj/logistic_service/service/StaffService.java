package com.duongprj.logistic_service.service;

import com.duongprj.logistic_service.dto.batch.request.BatchCreationRequest;
import com.duongprj.logistic_service.dto.batch.response.BatchResponse;
import com.duongprj.logistic_service.dto.parcel.response.ParcelResponse;
import com.duongprj.logistic_service.entity.Batch;
import com.duongprj.logistic_service.entity.Parcel;
import com.duongprj.logistic_service.entity.TrackingRecord;
import com.duongprj.logistic_service.entity.User;
import com.duongprj.logistic_service.enums.Region;
import com.duongprj.logistic_service.enums.TrackingCode;
import com.duongprj.logistic_service.mapper.BatchMapper;
import com.duongprj.logistic_service.mapper.ParcelMapper;
import com.duongprj.logistic_service.repository.BatchRepository;
import com.duongprj.logistic_service.repository.ParcelRepository;
import com.duongprj.logistic_service.repository.UserRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@PreAuthorize("hasRole('STAFF')")
public class StaffService {
    ParcelMapper parcelMapper;
    ParcelRepository parcelRepository;
    UserRepository userRepository;
    BatchMapper batchMapper;
    BatchRepository batchRepository;

}
