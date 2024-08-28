package com.duongprj.logistic_service.dto.batch.request;

import com.duongprj.logistic_service.enums.Region;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BatchCreationRequest {
    String destination;
    int weight;
    private List<String> parcelIds;
}
