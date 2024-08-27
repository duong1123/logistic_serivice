package com.duongprj.logistic_service.entity;

import com.duongprj.logistic_service.enums.Region;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Entity
public class Batch {
    @Id
    String id;
    String origin;
    String destination;
    Instant createdTime;
    String currentStatus;
    Instant departureTime;
    Instant arrivalTime;
    int weight;
    int parcelCount;
    String path;
    Region arrivalRegion;
}
