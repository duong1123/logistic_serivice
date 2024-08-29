package com.duongprj.logistic_service.dto.batch.response;

import com.duongprj.logistic_service.entity.TrackingRecord;
import com.duongprj.logistic_service.enums.Region;
import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class BatchResponse {
    String id;
    String origin;
    String destination;
    Instant createdTime;
    //Instant departureTime;
    Instant unbachedTime;
    int weight;
    int parcelCount;
    String path;
    Region arrivalRegion;

    @ElementCollection
    List<TrackingRecord> records;

    @ElementCollection
    List<String> parcelIds;
}

