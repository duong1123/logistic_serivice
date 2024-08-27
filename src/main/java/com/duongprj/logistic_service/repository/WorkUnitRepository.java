package com.duongprj.logistic_service.repository;

import com.duongprj.logistic_service.entity.WorkUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkUnitRepository extends JpaRepository<WorkUnit, String> {
    boolean existsByCode(String code);
    Optional<WorkUnit> findByCode(String code);
}
