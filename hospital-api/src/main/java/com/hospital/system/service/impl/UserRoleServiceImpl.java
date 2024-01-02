package com.hospital.system.service.impl;

import com.hospital.system.config.SystemConfig;
import com.hospital.system.dto.responsedto.ResponseUserRoleDTO;
import com.hospital.system.dto.responsedto.core.CommonResponseDTO;
import com.hospital.system.enity.UserRole;
import com.hospital.system.exception.EntryNotFoundException;
import com.hospital.system.repo.UserRoleRepo;
import com.hospital.system.service.UserRoleService;
import com.hospital.system.util.Generator;
import com.hospital.system.util.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepo userRoleRepo;
    private final Generator generator;
    private final SystemConfig config;

    private final UserRoleMapper userRoleMapper;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepo userRoleRepo, Generator generator, SystemConfig config, UserRoleMapper userRoleMapper) {
        this.userRoleRepo = userRoleRepo;
        this.generator = generator;
        this.config = config;
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public void initializeUserRoles() {
        List<UserRole> all = userRoleRepo.findAll();
        if (all.isEmpty()) {

            UserRole adminUserRole = new UserRole(
                    config.getGeneralKeyPrefix() + "UR-" + generator.generateGeneralId(config.getGeneralIdLength()),
                    "ADMIN",
                    "Admin User",
                    true
            );
            UserRole managerRole = new UserRole(
                    config.getGeneralKeyPrefix() + "UR-" + generator.generateGeneralId(config.getGeneralIdLength()),
                    "MANAGER",
                    "Manager User",
                    true
            );
            UserRole customerUserRole = new UserRole(
                    config.getGeneralKeyPrefix() + "UR-" + generator.generateGeneralId(config.getGeneralIdLength()),
                    "CUSTOMER",
                    "Customer User",
                    true
            );

            userRoleRepo.saveAll(List.of(
                    adminUserRole, managerRole,customerUserRole));
        }
    }

    @Override
    public List<ResponseUserRoleDTO> findAllUserRole() {
        List<UserRole> all = userRoleRepo.findAll();
        return userRoleMapper.toUserRoleList(all);
    }

    @Override
    public CommonResponseDTO deleteUserRole(String userRoleId) {
        if (userRoleRepo.existsById(userRoleId)){
            userRoleRepo.deleteById(userRoleId);

            return new CommonResponseDTO(200, "User Role has been deleted!", userRoleId, new ArrayList<>());
        }else {
            throw new EntryNotFoundException("Can't fine user role");
        }
    }


}

