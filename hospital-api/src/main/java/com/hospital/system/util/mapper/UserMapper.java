package com.hospital.system.util.mapper;

import com.hospital.system.dto.UserDTO;
import com.hospital.system.enity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDto(User user);
    User toUser(UserDTO userDTO);

}
