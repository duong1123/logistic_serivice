package com.duongprj.logistic_service.repository;

import com.duongprj.logistic_service.entity.Parcel;
import com.duongprj.logistic_service.enums.TrackingCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

public interface ParcelRepository extends JpaRepository<Parcel, String> {
    @Modifying
    @Transactional
    @Query("UPDATE Parcel SET isCancelled = true WHERE id = :id")
    void cancelParcel(String id);

    @Query("SELECT p.creatorUsername FROM Parcel p WHERE p.id = :id")
    String findCreatorUsernameById(String id);

    @Query("SELECT p FROM Parcel p WHERE p.id = :id")
    Parcel findParcelById(String id);

    @Modifying
    @Transactional
    @Query("UPDATE Parcel p SET p.currentStatus = :trackingCode, p.pickupTime = :pickupTime, p.actualPickupTime = :pickupTime WHERE p.id = :id")
    void changeCurrentStatus(String id, TrackingCode trackingCode, Instant pickupTime);
}
