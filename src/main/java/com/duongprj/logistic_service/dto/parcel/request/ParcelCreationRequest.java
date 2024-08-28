package com.duongprj.logistic_service.dto.parcel.request;

import com.duongprj.logistic_service.enums.ServiceType;
import com.duongprj.logistic_service.model.ContactInfo;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Embedded;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParcelCreationRequest {
    String batchId;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "fullName", column = @jakarta.persistence.Column(name = "sender_full_name")),
            @AttributeOverride(name = "phoneNumber", column = @jakarta.persistence.Column(name = "sender_phone_number")),
            @AttributeOverride(name = "province", column = @jakarta.persistence.Column(name = "sender_province")),
            @AttributeOverride(name = "district", column = @jakarta.persistence.Column(name = "sender_district")),
            @AttributeOverride(name = "ward", column = @jakarta.persistence.Column(name = "sender_ward"))
    })
    ContactInfo pickupAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "fullName", column = @jakarta.persistence.Column(name = "recipient_full_name")),
            @AttributeOverride(name = "phoneNumber", column = @jakarta.persistence.Column(name = "recipient_phone_number")),
            @AttributeOverride(name = "province", column = @jakarta.persistence.Column(name = "recipient_province")),
            @AttributeOverride(name = "district", column = @jakarta.persistence.Column(name = "recipient_district")),
            @AttributeOverride(name = "ward", column = @jakarta.persistence.Column(name = "recipient_ward"))
    })
    ContactInfo deliveryAddress;
    boolean pickupTime;
    ServiceType serviceType;
    boolean coCheck;
    boolean isPaid;
    boolean pickupOption;
    int weight;
}
