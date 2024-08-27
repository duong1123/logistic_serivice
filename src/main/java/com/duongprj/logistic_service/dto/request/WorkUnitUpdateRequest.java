package com.duongprj.logistic_service.dto.request;

import com.duongprj.logistic_service.enums.Region;
import com.duongprj.logistic_service.enums.UnitType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class WorkUnitUpdateRequest {
    String name;
    String code;
    String address;

    @Enumerated(EnumType.STRING)
    UnitType type;

    @Enumerated(EnumType.STRING)
    Region region;
}
