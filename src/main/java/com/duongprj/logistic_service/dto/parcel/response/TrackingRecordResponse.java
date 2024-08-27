package com.duongprj.logistic_service.dto.parcel.response;

import lombok.Data;
import java.time.Instant;

@Data
public class TrackingRecordResponse {
    private String code;
    private Instant time;
    private String unitCode;
    private String codeDescription;
}