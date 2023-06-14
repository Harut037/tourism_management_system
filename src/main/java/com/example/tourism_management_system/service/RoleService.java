package com.example.tourism_management_system.service;

import com.example.tourism_management_system.model.entities.RoleEntity;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    void saveRole(RoleEntity role);
}