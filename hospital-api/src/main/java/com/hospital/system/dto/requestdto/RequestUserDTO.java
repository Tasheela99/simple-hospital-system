package com.hospital.system.dto.requestdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class RequestUserDTO {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String userRoleId;
}
