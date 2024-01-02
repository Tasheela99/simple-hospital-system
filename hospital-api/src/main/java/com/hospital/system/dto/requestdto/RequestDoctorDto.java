package com.hospital.system.dto.requestdto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class RequestDoctorDto {
    private String name;
    private String address;
    private String mobile;
    private String speciality;
}
