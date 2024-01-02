package com.hospital.system.util.mapper;


import com.hospital.system.dto.UserRoleDTO;
import com.hospital.system.dto.responsedto.ResponseUserRoleDTO;
import com.hospital.system.enity.UserRole;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {
    UserRoleDTO toUserRoleDto(UserRole userRole);

    UserRole toUserRole(UserRoleDTO userRoleDTO);

    List<ResponseUserRoleDTO> toUserRoleList(List<UserRole> all);

}
