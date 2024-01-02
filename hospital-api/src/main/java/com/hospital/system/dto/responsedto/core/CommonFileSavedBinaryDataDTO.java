package com.hospital.system.dto.responsedto.core;


import com.hospital.system.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonFileSavedBinaryDataDTO implements SuperDTO {
    private Blob hash;
    private String directory;
    private Blob fileName;
    private Blob resourceUrl;
}
