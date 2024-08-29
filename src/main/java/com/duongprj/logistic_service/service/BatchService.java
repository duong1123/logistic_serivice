package com.duongprj.logistic_service.service;

import com.duongprj.logistic_service.dto.batch.request.BatchAddParcelRequest;
import com.duongprj.logistic_service.dto.batch.request.BatchCreationRequest;
import com.duongprj.logistic_service.dto.batch.request.BatchModifyRequest;
import com.duongprj.logistic_service.dto.batch.response.BatchAddParcelResponse;
import com.duongprj.logistic_service.dto.batch.response.BatchResponse;
import com.duongprj.logistic_service.entity.Batch;
import com.duongprj.logistic_service.entity.TrackingRecord;
import com.duongprj.logistic_service.entity.User;
import com.duongprj.logistic_service.enums.Region;
import com.duongprj.logistic_service.enums.TrackingCode;
import com.duongprj.logistic_service.exception.AppException;
import com.duongprj.logistic_service.exception.ErrorCode;
import com.duongprj.logistic_service.mapper.BatchMapper;
import com.duongprj.logistic_service.repository.BatchRepository;
import com.duongprj.logistic_service.repository.ParcelRepository;
import com.duongprj.logistic_service.repository.UserRepository;
import com.duongprj.logistic_service.repository.WorkUnitRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@PreAuthorize("hasRole('STAFF')")
public class BatchService {
    ParcelRepository parcelRepository;
    UserRepository userRepository;
    BatchMapper batchMapper;
    BatchRepository batchRepository;
    private final WorkUnitRepository workUnitRepository;

    public BatchResponse batchCreation(BatchCreationRequest request) {
        var context = SecurityContextHolder.getContext();
        var name = context.getAuthentication().getName();

        Batch batch = batchMapper.toBatch(request);
        batch.setCreatedTime(Instant.now());
        batch.setOrigin(getStaffUnitCode(name));
        batch.setParcelCount(request.getParcelIds().size());
        batch.setUnBatched(false);

        // Check if any parcel is already in another batch
        for (String parcelId : request.getParcelIds()) {
            var isInBatch = parcelRepository.checkParcelInBatch(parcelId);
            if (isInBatch) {
                throw new AppException(
                        ErrorCode.PARCEL_ALREADY_IN_ANOTHER_BATCH,
                        parcelId
                );
            }
        }

        TrackingRecord initialRecord = new TrackingRecord(
                TrackingCode.F300,
                Instant.now(),
                name,
                getStaffUnitCode(name)
        );
        batch.setRecords(new ArrayList<>());
        batch.getRecords().add(initialRecord);

        Region destinationRegion = workUnitRepository.findRegionByCode(request.getDestination());
        batch.setArrivalRegion(destinationRegion);

        batch.setParcelIds(new ArrayList<>());
        batch.getParcelIds().addAll(request.getParcelIds());

        // Set all parcels in batch
        for (String parcelId : request.getParcelIds()) {
            parcelRepository.setParcelInBatch(parcelId);
        }

        batchRepository.save(batch);
        return batchMapper.toBatchResponse(batch);
    }

    public BatchResponse unBatch(BatchModifyRequest request){
        String batchId = request.getBatchId();
        Batch batch = batchRepository.findById(batchId).
                orElseThrow(() -> new RuntimeException("Batch not found!"));

        for (String parcelId : batch.getParcelIds()) {
            parcelRepository.setParcelNotInBatch(parcelId);
        }
        batch.setUnbatchedTime(Instant.now());
        batch.setUnBatched(true);

        batchRepository.save(batch);

        return batchMapper.toBatchResponse(batch);
    }

    public String getStaffUnitCode(String username) {
        return userRepository.findByUsername(username)
                .map(User::getWorkUnit)
                .orElseThrow(() -> new RuntimeException("User not found!"));
    }

    public BatchAddParcelResponse addParcelToBatch(BatchAddParcelRequest request){
        String batchId = request.getBatchId();
        Batch batch = batchRepository.findById(batchId)
                .orElseThrow(() -> new RuntimeException("Batch not found!"));

        List<String> parcelIds = request.getParcelId();
        for (String parcelId : parcelIds) {
            var isInBatch = parcelRepository.checkParcelInBatch(parcelId);
            if (isInBatch) {
                throw new AppException(
                        ErrorCode.PARCEL_ALREADY_IN_ANOTHER_BATCH,
                        parcelId
                );
            }
        }

        for (String parcelId : parcelIds) {
            parcelRepository.setParcelInBatch(parcelId);
        }
        // Update weight and parcel count
        int addedWeight = parcelIds.stream()
                .mapToInt(parcelRepository::getParcelWeight)
                .sum();
        batch.setWeight(batch.getWeight() + addedWeight);
        batch.setParcelCount(batch.getParcelCount() + parcelIds.size());
        batch.getParcelIds().addAll(parcelIds);

        batchRepository.save(batch);

        return batchMapper.toBatchAddParcelResponse(batch);
    }



}
