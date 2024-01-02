package com.hospital.system.dto.responsedto.core;


import com.hospital.system.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonFileSavedSimpleDataDTO implements SuperDTO {
    private String hash;
    private String directory;
    private String fileName;
    private String resourceUrl;
}
