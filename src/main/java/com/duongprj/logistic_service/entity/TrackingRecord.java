package com.duongprj.logistic_service.entity;

import com.duongprj.logistic_service.enums.TrackingCode;
import jakarta.annotation.Nullable;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class TrackingRecord {
    TrackingCode code;
    Instant time;
    @Nullable
    String staffUsername;
    @Nullable
    String unitCode;

    public TrackingRecord(TrackingCode code, Instant time) {
        this.code = code;
        this.time = time;
    }
}