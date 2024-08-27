package com.duongprj.logistic_service.model;

import jakarta.persistence.Embeddable;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Embeddable
public class ContactInfo {
    String fullName;
    String phoneNumber;
    String province;
    String district;
    String ward;
}
