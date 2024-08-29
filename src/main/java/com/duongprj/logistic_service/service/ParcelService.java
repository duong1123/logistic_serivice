package com.duongprj.logistic_service.service;

import com.duongprj.logistic_service.dto.parcel.request.ParcelCreationRequest;
import com.duongprj.logistic_service.dto.parcel.request.ParcelModifyRequest;
import com.duongprj.logistic_service.dto.parcel.response.ParcelResponse;
import com.duongprj.logistic_service.dto.parcel.response.TrackingResponse;
import com.duongprj.logistic_service.entity.Parcel;
import com.duongprj.logistic_service.entity.TrackingRecord;
import com.duongprj.logistic_service.entity.User;
import com.duongprj.logistic_service.enums.Region;
import com.duongprj.logistic_service.enums.TrackingCode;
import com.duongprj.logistic_service.exception.AppException;
import com.duongprj.logistic_service.exception.ErrorCode;
import com.duongprj.logistic_service.mapper.ParcelMapper;
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
public class ParcelService {
    UserRepository userRepository;
    ParcelRepository parcelRepository;
    ParcelMapper parcelMapper;

    @PreAuthorize("hasRole('USER')")
    public ParcelResponse parcelCreate(ParcelCreationRequest request){
        var context = SecurityContextHolder.getContext();
        var name = context.getAuthentication().getName();

        Parcel parcel = parcelMapper.toParcel(request);
        parcel.setCreatorUsername(name);
        parcel.setCreatedTime(Instant.now());
        parcel.setOriginRegion(Region.getRegionByProvince(parcel.getPickupAddress().getProvince()));
        parcel.setDestinationRegion(Region.getRegionByProvince(parcel.getDeliveryAddress().getProvince()));

        TrackingRecord initialRecord = new TrackingRecord(
                TrackingCode.F000,
                Instant.now());
        parcel.setRecords(new ArrayList<>());
        parcel.getRecords().add(initialRecord);

        parcelRepository.save(parcel);

        return parcelMapper.toParcelResponse(parcel);
    }

    @PreAuthorize("hasRole('USER')")
    public ParcelResponse cancelParcel(ParcelModifyRequest request) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        String id = request.getParcelId();
        String creatorUsername = parcelRepository.findCreatorUsernameById(id);

        boolean isAdminOrStaff = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN") || grantedAuthority.getAuthority().equals("ROLE_STAFF"));

        if (!currentUsername.equals(creatorUsername) && !isAdminOrStaff) {
            throw new RuntimeException("You do not have permission to cancel this parcel!");
        }

        parcelRepository.cancelParcel(id);
        Parcel updatedParcel = parcelRepository.findById(id).orElseThrow(() -> new RuntimeException("Parcel not found!"));
        return parcelMapper.toParcelResponse(updatedParcel);
    }

    public TrackingResponse getTrackingResponseById(String id) {
        Parcel parcel = parcelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parcel not found!"));
        return parcelMapper.toTrackingResponse(parcel);
    }

    @PreAuthorize("hasRole('STAFF')")
    public ParcelResponse addParcelRecord(ParcelModifyRequest request, TrackingCode code, @Nullable Instant actualPickup, @Nullable Instant actualDelivery) {
        var parcelId = request.getParcelId();
        var context = SecurityContextHolder.getContext();
        var name = context.getAuthentication().getName();

        Parcel parcel = parcelRepository.findById(parcelId)
                .orElseThrow(() -> new RuntimeException("Parcel not found!"));

        if (parcel.isCancelled()) {
            throw new AppException(ErrorCode.PARCEL_CANCELLED);
        }

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
        parcelRepository.save(parcel);

        return parcelMapper.toParcelResponse(parcel);
    }

    public String getStaffUnitCode(String username) {
        return userRepository.findByUsername(username)
                .map(User::getWorkUnit)
                .orElseThrow(() -> new RuntimeException("User not found!"));
    }
}
