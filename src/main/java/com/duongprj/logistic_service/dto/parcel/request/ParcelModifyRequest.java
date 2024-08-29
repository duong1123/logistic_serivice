package com.duongprj.logistic_service.dto.parcel.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParcelModifyRequest {
    String parcelId;
}
