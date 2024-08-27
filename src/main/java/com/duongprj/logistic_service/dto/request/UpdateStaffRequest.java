package com.duongprj.logistic_service.dto.request;

import com.duongprj.logistic_service.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)

public class UpdateStaffRequest {
    Role role;
    String workUnit;    //Optional
}