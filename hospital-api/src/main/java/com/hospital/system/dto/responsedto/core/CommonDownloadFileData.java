package com.hospital.system.dto.responsedto.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonDownloadFileData {
    private String filename;
    private byte[] file;
}
