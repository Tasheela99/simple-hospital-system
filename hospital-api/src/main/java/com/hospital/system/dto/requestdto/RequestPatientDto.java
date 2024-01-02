package com.hospital.system.dto.requestdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestPatientDto {
    private String name;
    private String address;
    private int age;
    private String mobile;
}
