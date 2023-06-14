package com.example.tourism_management_system.repository;

import com.example.tourism_management_system.model.entities.TourEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<TourEntity, Long> {

    @Transactional
    @Query("SELECT t FROM TourEntity t WHERE t.flag = true")
    List<TourEntity> findAll();

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
    @Query("UPDATE TourEntity t set t.flag = false where t.id =:id")
    void flag(Long id);
}
