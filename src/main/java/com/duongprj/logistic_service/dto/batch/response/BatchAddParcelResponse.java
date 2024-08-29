package com.duongprj.logistic_service.dto.batch.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BatchAddParcelResponse {
    String batchId;
    List<String> parcelId;
    int parcelCount;
    int weight;
}
