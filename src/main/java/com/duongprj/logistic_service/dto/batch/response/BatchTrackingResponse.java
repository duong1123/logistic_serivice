package com.duongprj.logistic_service.dto.batch.response;

import com.duongprj.logistic_service.entity.TrackingRecord;
import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class BatchTrackingResponse {
    @ElementCollection
    List<TrackingRecord> records;
}
