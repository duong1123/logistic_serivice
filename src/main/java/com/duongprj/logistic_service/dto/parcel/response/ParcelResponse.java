package com.duongprj.logistic_service.dto.parcel.response;

import com.duongprj.logistic_service.entity.TrackingRecord;
import com.duongprj.logistic_service.enums.Region;
import com.duongprj.logistic_service.enums.ServiceType;
import com.duongprj.logistic_service.model.ContactInfo;
import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ParcelResponse {
    String id;
    String creatorUsername;
    Instant createdTime;
    ContactInfo pickupAddress;
    ContactInfo deliveryAddress;
    boolean pickupTime;
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

    @ElementCollection
    List<String> parcelIds;
}