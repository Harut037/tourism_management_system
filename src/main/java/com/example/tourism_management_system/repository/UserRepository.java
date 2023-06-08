package com.example.tourism_management_system.repository;

import com.example.tourism_management_system.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
}
