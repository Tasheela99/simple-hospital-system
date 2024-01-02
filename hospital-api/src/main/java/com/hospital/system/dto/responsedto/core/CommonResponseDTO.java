package com.hospital.system.dto.responsedto.core;

import com.hospital.system.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonResponseDTO implements SuperDTO {
    private int code;
    private String message;
    private Object data;
    private ArrayList<Object> records;
}
