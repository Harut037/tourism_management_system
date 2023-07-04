package com.example.tourism_management_system.model.entities;

import com.example.tourism_management_system.model.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

/**
 * Base Class For All Entities
 */
@MappedSuperclass
@Data
public class BaseEntity {
    
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long id;
    
    @CreatedDate
    @Column ( name = "created" )
    private LocalDate created;
    
    @LastModifiedDate
    @Column ( name = "updated" )
    private LocalDate updated;
    
    @Enumerated ( EnumType.STRING )
    @Column ( name = "status" )
    private Status status;
}