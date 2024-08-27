package com.duongprj.logistic_service.controller;

import com.duongprj.logistic_service.dto.common.ApiResponse;
import com.duongprj.logistic_service.dto.parcel.request.ParcelCreationRequest;
import com.duongprj.logistic_service.dto.parcel.response.ParcelResponse;
import com.duongprj.logistic_service.dto.parcel.response.TrackingResponse;
import com.duongprj.logistic_service.enums.TrackingCode;
import com.duongprj.logistic_service.service.ParcelService;
import com.duongprj.logistic_service.service.StaffService;
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
    private final StaffService staffService;

    @PostMapping("/create")
    public ApiResponse<ParcelResponse> createParcel(@RequestBody ParcelCreationRequest request) {
        return ApiResponse.<ParcelResponse>builder()
                .response(parcelService.parcelCreate(request))
                .build();
    }

    @PostMapping("/cancel/{id}")
    public ApiResponse<ParcelResponse> cancelParcel(@PathVariable String id) {
        return ApiResponse.<ParcelResponse>builder()
                .response(parcelService.cancelParcel(id))
                .build();
    }

    @PostMapping("/pick_up/{id}")
    public ApiResponse<ParcelResponse> pickedUp(@PathVariable String id) {
        return ApiResponse.<ParcelResponse>builder()
                .response(staffService.addParcelRecord(id, TrackingCode.F100, Instant.now(), null))
                .build();
    }

    @PostMapping("/drop_off_done/{id}")
    public ApiResponse<ParcelResponse> receivedAtPO(@PathVariable String id) {
        return ApiResponse.<ParcelResponse>builder()
                .response(staffService.addParcelRecord(id, TrackingCode.F098, null, null))
                .build();
    }

    @PostMapping("/dep_hub_arrived/{id}")
    public ApiResponse<ParcelResponse> arrivedDepHub(@PathVariable String id) {
        return ApiResponse.<ParcelResponse>builder()
                .response(staffService.addParcelRecord(id, TrackingCode.F440, null, null))
                .build();
    }

    @PostMapping("/dep_hub_left/{id}")
    public ApiResponse<ParcelResponse> leftDepHub(@PathVariable String id) {
        return ApiResponse.<ParcelResponse>builder()
                .response(staffService.addParcelRecord(id, TrackingCode.F450, null, null))
                .build();
    }

    @PostMapping("/soc_received/{id}")
    public ApiResponse<ParcelResponse> enterSortingCenter(@PathVariable String id) {
        return ApiResponse.<ParcelResponse>builder()
                .response(staffService.addParcelRecord(id, TrackingCode.F510, null, null))
                .build();
    }

    @PostMapping("/soc_left/{id}")
    public ApiResponse<ParcelResponse> leftSortingCenter(@PathVariable String id) {
        return ApiResponse.<ParcelResponse>builder()
                .response(staffService.addParcelRecord(id, TrackingCode.F540, null, null))
                .build();
    }

    @PostMapping("/arr_hub_received/{id}")
    public ApiResponse<ParcelResponse> enterArrHub(@PathVariable String id) {
        return ApiResponse.<ParcelResponse>builder()
                .response(staffService.addParcelRecord(id, TrackingCode.F599, null, null))
                .build();
    }

    @PostMapping("/out_deli/{id}")
    public ApiResponse<ParcelResponse> delivery(@PathVariable String id) {
        return ApiResponse.<ParcelResponse>builder()
                .response(staffService.addParcelRecord(id, TrackingCode.F600, null, null))
                .build();
    }

    @PostMapping("/delivered/{id}")
    public ApiResponse<ParcelResponse> delivered(@PathVariable String id) {

        return ApiResponse.<ParcelResponse>builder()
                .response(staffService.addParcelRecord(id, TrackingCode.F980, null, Instant.now()))
                .build();
    }

    @PostMapping("/deli_failed/{id}")
    public ApiResponse<ParcelResponse> deliFail(@PathVariable String id) {
        return ApiResponse.<ParcelResponse>builder()
                .response(staffService.addParcelRecord(id, TrackingCode.F981, null, null))
                .build();
    }

    @GetMapping("/{id}/tracking")
    public TrackingResponse getParcelTracking(@PathVariable String id) {
        return parcelService.getTrackingResponseById(id);
    }
}
