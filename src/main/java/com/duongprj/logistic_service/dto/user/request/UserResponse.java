package com.duongprj.logistic_service.dto.user.request;

import com.duongprj.logistic_service.dto.common.ContactInfoDto;
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
public class UserResponse {
    String id;
    String username;
    String workUnit;
    ContactInfoDto contactInfo;
    String email;
}

