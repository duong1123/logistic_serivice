package com.duongprj.logistic_service.repository;

import com.duongprj.logistic_service.entity.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface ParcelRepository extends JpaRepository<Parcel, String> {
    @Modifying
    @Transactional
    @Query("UPDATE Parcel SET isCancelled = true WHERE id = :id")
    void cancelParcel(String id);

    @Query("SELECT p.creatorUsername FROM Parcel p WHERE p.id = :id")
    String findCreatorUsernameById(String id);

    @Modifying
    @Transactional
    @Query("UPDATE Parcel p SET p.isInBatch = true WHERE p.id = :id")
    void setParcelInBatch(String id);

    @Modifying
    @Transactional
    @Query("UPDATE Parcel p SET p.isInBatch = false WHERE p.id = :id")
    void setParcelNotInBatch(String id);

    @Query("SELECT p.isInBatch FROM Parcel p WHERE p.id = :id")
    boolean checkParcelInBatch(String id);

    @Query("SELECT p.weight FROM Parcel p WHERE p.id = :id")
    int getParcelWeight(String id);
}
