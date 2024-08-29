package com.duongprj.logistic_service.mapper;

import com.duongprj.logistic_service.dto.batch.request.BatchCreationRequest;
import com.duongprj.logistic_service.dto.batch.response.BatchAddParcelResponse;
import com.duongprj.logistic_service.dto.batch.response.BatchResponse;
import com.duongprj.logistic_service.dto.batch.response.BatchTrackingResponse;
import com.duongprj.logistic_service.entity.Batch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BatchMapper {
    Batch toBatch(BatchCreationRequest request);

    BatchResponse toBatchResponse(Batch batch);

    @Mapping(source = "id", target = "batchId")
    @Mapping(source = "parcelIds", target = "parcelId")
    @Mapping(source = "parcelCount", target = "parcelCount")
    @Mapping(source = "weight", target = "weight")
    BatchAddParcelResponse toBatchAddParcelResponse(Batch batch);

    BatchTrackingResponse toBatchTrackingResponse(Batch batch);
}
