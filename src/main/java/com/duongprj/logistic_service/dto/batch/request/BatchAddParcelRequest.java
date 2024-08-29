package com.duongprj.logistic_service.dto.batch.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BatchAddParcelRequest {
    String batchId;
    List<String> parcelId;
}
