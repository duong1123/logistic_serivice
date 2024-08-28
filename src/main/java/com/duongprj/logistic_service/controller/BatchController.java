package com.duongprj.logistic_service.controller;

import com.duongprj.logistic_service.dto.batch.request.BatchCreationRequest;
import com.duongprj.logistic_service.dto.batch.request.BatchModifyRequest;
import com.duongprj.logistic_service.dto.batch.response.BatchResponse;
import com.duongprj.logistic_service.dto.common.ApiResponse;
import com.duongprj.logistic_service.service.BatchService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class BatchController {
    BatchService batchService;
    @PostMapping("/create")
    public ApiResponse<BatchResponse> createBatch(@RequestBody BatchCreationRequest request){
        return ApiResponse.<BatchResponse>builder()
                .response(batchService.batchCreation(request))
                .build();
    }

    @PostMapping("/unbatch")
    public ApiResponse<BatchResponse> unBatch(@RequestBody BatchModifyRequest request){
        return ApiResponse.<BatchResponse>builder()
                .response(batchService.unBatch(request))
                .build();
    }
}
