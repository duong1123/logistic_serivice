package com.duongprj.logistic_service.mapper;

import com.duongprj.logistic_service.dto.parcel.request.ParcelCreationRequest;
import com.duongprj.logistic_service.dto.parcel.response.ParcelResponse;
import com.duongprj.logistic_service.dto.parcel.response.TrackingRecordResponse;
import com.duongprj.logistic_service.dto.parcel.response.TrackingResponse;
import com.duongprj.logistic_service.entity.Parcel;
import com.duongprj.logistic_service.entity.TrackingRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ParcelMapper {

    Parcel toParcel(ParcelCreationRequest request);

    ParcelResponse toParcelResponse(Parcel parcel);

    void updateParcel(@MappingTarget Parcel parcel, ParcelCreationRequest request);

    @Mapping(target = "records", source = "records")
    TrackingResponse toTrackingResponse(Parcel parcel);

    @Named("mapTrackingRecords")
    default List<TrackingRecordResponse> mapTrackingRecords(List<TrackingRecord> records) {
        return records.stream().map(record -> {
            TrackingRecordResponse response = new TrackingRecordResponse();
            response.setCode(record.getCode().name());
            response.setTime(record.getTime());
            response.setUnitCode(record.getUnitCode());
            response.setCodeDescription(record.getCode().getDescription());
            return response;
        }).collect(Collectors.toList());
    }
}
