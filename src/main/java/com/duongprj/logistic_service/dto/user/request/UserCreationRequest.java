package com.duongprj.logistic_service.dto.user.request;

import com.duongprj.logistic_service.dto.common.ContactInfoDto;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 6, message = "INVALID_USERNAME")
    String username;
    @Size(min = 8, message = "INVALID_PASSWORD")
    String password;
    ContactInfoDto contactInfo;
    String email;
}