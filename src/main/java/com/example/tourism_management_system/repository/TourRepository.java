package com.example.tourism_management_system.repository;

import com.example.tourism_management_system.model.entities.TourEntity;
import com.example.tourism_management_system.model.enums.Status;
import com.example.tourism_management_system.model.enums.enumForTour.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TourRepository extends JpaRepository<TourEntity, Long> {

    @Transactional
    @Query("SELECT t FROM TourEntity t WHERE t.status = 'ACTIVE'")
    List<TourEntity> findAllActiveTours();

    @Transactional
    @Query("SELECT t FROM TourEntity t WHERE t.tourName = :tourName and t.tourDate = :tourDate")
    Optional<TourEntity> findTour(String tourName, LocalDate tourDate);

    @Transactional
    @Modifying
    @Query("SELECT t FROM TourEntity t ORDER BY t.cost ASC")
    List<TourEntity> findAllOrderByCost();

    @Transactional
    @Query("SELECT s FROM TourEntity s ORDER BY s.tourDate ASC")
    List<TourEntity> findAllOrderByTourDate();

    @Transactional
    @Query("SELECT s FROM TourEntity s ORDER BY s.generalQuantity ASC")
    List<TourEntity> findAllOrderByQuantity();

    @Transactional
    @Modifying
    @Query("SELECT s FROM TourEntity s ORDER BY s.distance ASC ")
    List<TourEntity> findAllOrderByDistance();

    @Transactional
    @Modifying
    @Query("UPDATE TourEntity t set t.status = :status where t.id =:id")
    void delete(Long id, Status status);
    
    @Transactional
    @Modifying
    @Query("UPDATE TourEntity t set t.status = :status where t.id =:id")
    void done(Long id, Status status);
    
    @Transactional
    @Modifying
    @Query("UPDATE TourEntity t SET t.startTime = :newStartTime where t.tourName = :tourName and t.tourDate = :tourDate")
    void updateStartTime(LocalTime newStartTime, String tourName, LocalDate tourDate);

    @Transactional
    @Modifying
    @Query("UPDATE TourEntity  t SET t.cost = :newCost where t.tourName = :tourName and t.tourDate = :tourDate")
    void updateCost(double newCost, String tourName, LocalDate tourDate);

    @Transactional
    @Modifying
    @Query("UPDATE TourEntity  t SET t.carType = :newCarType where t.tourName = :tourName and t.tourDate = :tourDate")
    void updateCarType(Transport newCarType, String tourName, LocalDate tourDate);

    @Transactional
    @Modifying
    @Query("UPDATE TourEntity  t SET t.generalQuantity = :newGeneralQuantity where t.tourName = :tourName and t.tourDate = :tourDate")
    void updateQuantity(Integer newGeneralQuantity, String tourName, LocalDate tourDate);

    @Transactional
    @Modifying
    @Query("UPDATE TourEntity  t SET t.maxQuantity = :newMaxQuantity where t.tourName = :tourName and t.tourDate = :tourDate")
    void updateMaxQuantity(Integer newMaxQuantity, String tourName, LocalDate tourDate);
    
    @Transactional
    @Modifying
    @Query("UPDATE TourEntity t set t.status = :status where t.id =:id")
    void reserved (Long id, Status status);
}