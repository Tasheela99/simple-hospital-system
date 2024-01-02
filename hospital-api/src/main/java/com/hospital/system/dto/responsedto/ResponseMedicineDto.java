package com.hospital.system.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ResponseMedicineDto {
    private String id;

    private String name;
    private String type;
    private int qty;
    private double unitPrice;
}
