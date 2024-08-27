package com.duongprj.logistic_service.dto.request;

import com.duongprj.logistic_service.enums.Region;
import com.duongprj.logistic_service.enums.UnitType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class WorkUnitCreationRequest {
    String name;
    String code;
    String address;
    UnitType type;
    Region region;
}
