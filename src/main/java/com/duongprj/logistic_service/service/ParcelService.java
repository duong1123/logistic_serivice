package com.duongprj.logistic_service.service;

import com.duongprj.logistic_service.dto.parcel.ParcelCreationRequest;
import com.duongprj.logistic_service.dto.parcel.ParcelResponse;
import com.duongprj.logistic_service.dto.parcel.TrackingResponse;
import com.duongprj.logistic_service.entity.Parcel;
import com.duongprj.logistic_service.entity.TrackingRecord;
import com.duongprj.logistic_service.enums.Region;
import com.duongprj.logistic_service.enums.TrackingCode;
import com.duongprj.logistic_service.mapper.ParcelMapper;
import com.duongprj.logistic_service.repository.ParcelRepository;
import com.duongprj.logistic_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class ParcelService {
    private static final Logger log = LoggerFactory.getLogger(ParcelService.class);
    ParcelRepository parcelRepository;
    ParcelMapper parcelMapper;
    UserRepository userRepository;

    @PreAuthorize("hasRole('USER')")
    public ParcelResponse parcelCreate(ParcelCreationRequest request){
        var context = SecurityContextHolder.getContext();
        var name = context.getAuthentication().getName();

        Parcel parcel = parcelMapper.toParcel(request);
        parcel.setCurrentStatus(TrackingCode.F000);
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
    public ParcelResponse cancelParcel(String id) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
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
}
