package com.duongprj.logistic_service.service;

import com.duongprj.logistic_service.dto.parcel.ParcelResponse;
import com.duongprj.logistic_service.entity.Parcel;
import com.duongprj.logistic_service.entity.TrackingRecord;
import com.duongprj.logistic_service.entity.User;
import com.duongprj.logistic_service.enums.TrackingCode;
import com.duongprj.logistic_service.mapper.ParcelMapper;
import com.duongprj.logistic_service.repository.ParcelRepository;
import com.duongprj.logistic_service.repository.UserRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@PreAuthorize("hasRole('STAFF')")
public class StaffService {
    private static final Logger log = LoggerFactory.getLogger(StaffService.class);
    ParcelRepository parcelRepository;
    UserRepository userRepository;
    ParcelMapper parcelMapper;

    public ParcelResponse addParcelRecord(String parcelId, TrackingCode code, @Nullable Instant actualPickup, @Nullable Instant actualDelivery) {
        var context = SecurityContextHolder.getContext();
        var name = context.getAuthentication().getName();

        Parcel parcel = parcelRepository.findById(parcelId)
                .orElseThrow(() -> new RuntimeException("Parcel not found!"));
        var currentTime = Instant.now();
        TrackingRecord record = new TrackingRecord(
                code,
                currentTime,
                name,
                getStaffUnitCode(name)
        );

        if (actualPickup != null) {
            parcel.setActualPickupTime(actualPickup);
        }

        if (actualDelivery != null) {
            parcel.setDeliveryTime(actualDelivery);
        }

        parcel.getRecords().add(record);
        parcel.setCurrentStatus(code);
        parcelRepository.save(parcel);

        return parcelMapper.toParcelResponse(parcel);
    }

    public String getStaffUnitCode(String username) {
        return userRepository.findByUsername(username)
                .map(User::getWorkUnit)
                .orElseThrow(() -> new RuntimeException("User not found!"));
    }
}
