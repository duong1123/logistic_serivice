package com.duongprj.logistic_service.controller;

import com.duongprj.logistic_service.dto.batch.request.BatchAddParcelRequest;
import com.duongprj.logistic_service.dto.batch.request.BatchCreationRequest;
import com.duongprj.logistic_service.dto.batch.request.BatchModifyRequest;
import com.duongprj.logistic_service.dto.batch.response.BatchAddParcelResponse;
import com.duongprj.logistic_service.dto.batch.response.BatchResponse;
import com.duongprj.logistic_service.dto.batch.response.BatchTrackingResponse;
import com.duongprj.logistic_service.dto.common.ApiResponse;
import com.duongprj.logistic_service.enums.TrackingCode;
import com.duongprj.logistic_service.service.BatchService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/batch")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class BatchController {
    BatchService batchService;

    @PostMapping("/create")
    public ApiResponse<BatchResponse> createBatch(@RequestBody BatchCreationRequest request) {
        return ApiResponse.<BatchResponse>builder()
                .response(batchService.batchCreation(request))
                .build();
    }

    @PostMapping("/unbatch")
    public ApiResponse<BatchResponse> unBatch(@RequestBody BatchModifyRequest request) {
        return ApiResponse.<BatchResponse>builder()
                .response(batchService.unBatch(request))
                .build();
    }

    @PostMapping("/add_parcel")
    public ApiResponse<BatchAddParcelResponse> addParcel(@RequestBody BatchAddParcelRequest request) {
        return ApiResponse.<BatchAddParcelResponse>builder()
                .response(batchService.addParcelToBatch(request))
                .build();
    }
    @PostMapping("/dep_hub_left")
    public ApiResponse<BatchTrackingResponse> leftDepHub(@RequestBody BatchModifyRequest request) {
        return ApiResponse.<BatchTrackingResponse>builder()
                .response(batchService.addTrackingRecord(request, TrackingCode.F450))
                .build();
    }

    @PostMapping("/soc_received")
    public ApiResponse<BatchTrackingResponse> enterSortingCenter(@RequestBody BatchModifyRequest request) {
        return ApiResponse.<BatchTrackingResponse>builder()
                .response(batchService.addTrackingRecord(request, TrackingCode.F510))
                .build();
    }

    @PostMapping("/soc_left")
    public ApiResponse<BatchTrackingResponse> leftSortingCenter(@RequestBody BatchModifyRequest request) {
        return ApiResponse.<BatchTrackingResponse>builder()
                .response(batchService.addTrackingRecord(request, TrackingCode.F540))
                .build();
    }

    @PostMapping("/arr_hub_received")
    public ApiResponse<BatchTrackingResponse> enterArrHub(@RequestBody BatchModifyRequest request) {
        return ApiResponse.<BatchTrackingResponse>builder()
                .response(batchService.addTrackingRecord(request, TrackingCode.F599))
                .build();
    }
}
