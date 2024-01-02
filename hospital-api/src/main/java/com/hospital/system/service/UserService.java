package com.hospital.system.service;

import com.hospital.system.dto.UserDTO;
import com.hospital.system.dto.requestdto.RequestUserDTO;
import com.hospital.system.dto.requestdto.RequestUserSaveDto;
import com.hospital.system.dto.responsedto.ResponseUserDataDTO;

import java.io.IOException;
import java.util.List;

public interface UserService {
    void initializeUser() throws IOException;
    ResponseUserDataDTO getAllUserData(String token);
    void createUser(RequestUserSaveDto userDTO);
    boolean verifyUser(String type, String token);

    List<UserDTO> getAllUsers();
}
