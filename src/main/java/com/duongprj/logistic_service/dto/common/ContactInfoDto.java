package com.duongprj.logistic_service.dto.common;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContactInfoDto {
    String fullName;
    String phoneNumber;
    String province;
    String district;
    String ward;
}