package com.example.tourism_management_system.service.impl;

import com.example.tourism_management_system.model.entities.RoleEntity;
import com.example.tourism_management_system.repository.RoleRepository;
import com.example.tourism_management_system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Saves a RoleEntity object by persisting it in the repository.
     *
     * @param role the RoleEntity object to save
     */
    @Override
    public void saveRole(RoleEntity role) {
        roleRepository.save(role);
    }
}