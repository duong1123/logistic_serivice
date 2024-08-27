package com.duongprj.logistic_service.mapper;

import com.duongprj.logistic_service.dto.request.WorkUnitCreationRequest;
import com.duongprj.logistic_service.dto.request.WorkUnitUpdateRequest;
import com.duongprj.logistic_service.dto.response.WorkUnitResponse;
import com.duongprj.logistic_service.entity.WorkUnit;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface WorkUnitMapper {
    WorkUnit toWorkUnit(WorkUnitCreationRequest request);

    WorkUnitResponse toWorkUnitResponse(WorkUnit workUnit);

    void updateWorkUnit(@MappingTarget WorkUnit workUnit, WorkUnitUpdateRequest request);
}
