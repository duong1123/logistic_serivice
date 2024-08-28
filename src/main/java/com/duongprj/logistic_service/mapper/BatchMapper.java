package com.duongprj.logistic_service.mapper;

import com.duongprj.logistic_service.dto.batch.request.BatchCreationRequest;
import com.duongprj.logistic_service.dto.batch.response.BatchResponse;
import com.duongprj.logistic_service.entity.Batch;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BatchMapper {
    Batch toBatch(BatchCreationRequest request);
    BatchResponse toBatchResponse(Batch batch);
}
