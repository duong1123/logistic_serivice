package com.duongprj.logistic_service.repository;

import com.duongprj.logistic_service.entity.WorkUnit;
import com.duongprj.logistic_service.enums.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkUnitRepository extends JpaRepository<WorkUnit, String> {
    boolean existsByCode(String code);
    Optional<WorkUnit> findByCode(String code);

    @Query("SELECT w.region FROM WorkUnit w WHERE w.code = :code")
    Region findRegionByCode(String code);
}
