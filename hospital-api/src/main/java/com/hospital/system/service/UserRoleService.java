package com.hospital.system.service;

import com.hospital.system.dto.responsedto.ResponseUserRoleDTO;
import com.hospital.system.dto.responsedto.core.CommonResponseDTO;

import java.util.List;

public interface UserRoleService {
    public void initializeUserRoles();

    List<ResponseUserRoleDTO> findAllUserRole();

    CommonResponseDTO deleteUserRole(String userRoleId);
}
