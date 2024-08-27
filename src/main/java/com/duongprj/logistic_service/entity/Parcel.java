package com.duongprj.logistic_service.entity;
import com.duongprj.logistic_service.enums.Region;
import com.duongprj.logistic_service.enums.ServiceType;
import com.duongprj.logistic_service.enums.TrackingCode;
import com.duongprj.logistic_service.model.ContactInfo;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Parcel {
    @Id
    @GeneratedValue(generator = "parcel-id-generator")
    @GenericGenerator(name = "parcel-id-generator", strategy = "com.duongprj.logistic_service.utils.ParcelIdGenerator")
    String id;
    String creatorUsername;
    String batchId;
    Instant createdTime;
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
    TrackingCode currentStatus;
    Instant actualPickupTime;
    ServiceType serviceType;
    boolean coCheck;
    boolean isPaid;
    boolean pickupOption;
    boolean isCancelled;
    Instant deliveryTime;
    int weight;
    Region originRegion;
    Region destinationRegion;

    @ElementCollection
    List<TrackingRecord> records;

}
