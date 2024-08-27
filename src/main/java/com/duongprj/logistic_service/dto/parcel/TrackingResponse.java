package com.duongprj.logistic_service.dto.parcel;

import lombok.Data;
import java.util.List;

@Data
public class TrackingResponse {
    private List<TrackingRecordResponse> records;
}