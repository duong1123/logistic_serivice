package com.duongprj.logistic_service.entity;
import com.duongprj.logistic_service.model.ContactInfo;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String username;
    String password;
    String workUnit;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "fullName", column = @jakarta.persistence.Column(name = "user_full_name")),
            @AttributeOverride(name = "phoneNumber", column = @jakarta.persistence.Column(name = "user_phone_number")),
            @AttributeOverride(name = "province", column = @jakarta.persistence.Column(name = "user_province")),
            @AttributeOverride(name = "district", column = @jakarta.persistence.Column(name = "user_district")),
            @AttributeOverride(name = "ward", column = @jakarta.persistence.Column(name = "user_ward"))
    })
    ContactInfo contactInfo;
    String email;

    @ElementCollection
    Set<String> roles;
}
