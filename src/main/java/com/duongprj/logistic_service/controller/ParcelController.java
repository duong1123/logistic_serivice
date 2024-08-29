package com.duongprj.logistic_service.controller;

import com.duongprj.logistic_service.dto.common.ApiResponse;
import com.duongprj.logistic_service.dto.parcel.request.ParcelCreationRequest;
import com.duongprj.logistic_service.dto.parcel.request.ParcelModifyRequest;
import com.duongprj.logistic_service.dto.parcel.response.ParcelResponse;
import com.duongprj.logistic_service.dto.parcel.response.TrackingResponse;
import com.duongprj.logistic_service.enums.TrackingCode;
import com.duongprj.logistic_service.service.ParcelService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/parcel")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class ParcelController {
    ParcelService parcelService;

    @PostMapping("/create")
    public ApiResponse<ParcelResponse> createParcel(@RequestBody ParcelCreationRequest request) {
        return ApiResponse.<ParcelResponse>builder()
                .response(parcelService.parcelCreate(request))
                .build();
    }

    @PostMapping("/cancel")
    public ApiResponse<ParcelResponse> cancelParcel(@RequestBody ParcelModifyRequest request) {
        return ApiResponse.<ParcelResponse>builder()
                .response(parcelService.cancelParcel(request))
                .build();
    }

    @PostMapping("/pick_up")
    public ApiResponse<ParcelResponse> pickedUp(@RequestBody ParcelModifyRequest request) {
        return ApiResponse.<ParcelResponse>builder()
                .response(parcelService.addParcelRecord(request, TrackingCode.F100, Instant.now(), null))
                .build();
    }

    @PostMapping("/drop_off_done")
    public ApiResponse<ParcelResponse> receivedAtPO(@RequestBody ParcelModifyRequest request) {
        return ApiResponse.<ParcelResponse>builder()
                .response(parcelService.addParcelRecord(request, TrackingCode.F098, null, null))
                .build();
    }

    @PostMapping("/dep_hub_arrived")
    public ApiResponse<ParcelResponse> arrivedDepHub(@RequestBody ParcelModifyRequest request) {
        return ApiResponse.<ParcelResponse>builder()
                .response(parcelService.addParcelRecord(request, TrackingCode.F440, null, null))
                .build();
    }

    @PostMapping("/dep_hub_left")
    public ApiResponse<ParcelResponse> leftDepHub(@RequestBody ParcelModifyRequest request) {
        return ApiResponse.<ParcelResponse>builder()
                .response(parcelService.addParcelRecord(request, TrackingCode.F450, null, null))
                .build();
    }

    @PostMapping("/soc_received")
    public ApiResponse<ParcelResponse> enterSortingCenter(@RequestBody ParcelModifyRequest request) {
        return ApiResponse.<ParcelResponse>builder()
                .response(parcelService.addParcelRecord(request, TrackingCode.F510, null, null))
                .build();
    }

    @PostMapping("/soc_left")
    public ApiResponse<ParcelResponse> leftSortingCenter(@RequestBody ParcelModifyRequest request) {
        return ApiResponse.<ParcelResponse>builder()
                .response(parcelService.addParcelRecord(request, TrackingCode.F540, null, null))
                .build();
    }

    @PostMapping("/arr_hub_received")
    public ApiResponse<ParcelResponse> enterArrHub(@RequestBody ParcelModifyRequest request) {
        return ApiResponse.<ParcelResponse>builder()
                .response(parcelService.addParcelRecord(request, TrackingCode.F599, null, null))
                .build();
    }

    @PostMapping("/out_deli")
    public ApiResponse<ParcelResponse> delivery(@RequestBody ParcelModifyRequest request) {
        return ApiResponse.<ParcelResponse>builder()
                .response(parcelService.addParcelRecord(request, TrackingCode.F600, null, null))
                .build();
    }

    @PostMapping("/delivered")
    public ApiResponse<ParcelResponse> delivered(@RequestBody ParcelModifyRequest request) {

        return ApiResponse.<ParcelResponse>builder()
                .response(parcelService.addParcelRecord(request, TrackingCode.F980, null, Instant.now()))
                .build();
    }

    @PostMapping("/deli_failed")
    public ApiResponse<ParcelResponse> deliFail(@RequestBody ParcelModifyRequest request) {
        return ApiResponse.<ParcelResponse>builder()
                .response(parcelService.addParcelRecord(request, TrackingCode.F981, null, null))
                .build();
    }

    @GetMapping("/tracking/{parcelId}")
    public ApiResponse<TrackingResponse> getParcelTracking(@PathVariable String parcelId) {
        return ApiResponse.<TrackingResponse>builder()
                .response(parcelService.getTrackingResponseById(parcelId))
                .build();
    }
}
