package com.duongprj.logistic_service.entity;

import com.duongprj.logistic_service.enums.Region;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Entity
public class Batch {
    @Id
    @GeneratedValue(generator = "batch-id-generator")
    @GenericGenerator(name = "batch-id-generator", strategy = "com.duongprj.logistic_service.utils.BatchIdGenerator")
    String id;
    String origin;
    String destination;
    Instant createdTime;
    //Instant departureTime;
    Instant unbatchedTime;
    int weight;
    int parcelCount;
    String path;
    Region arrivalRegion;
    boolean unBatched;

    @ElementCollection
    List<TrackingRecord> records;

    @ElementCollection
    List<String> parcelIds;
}