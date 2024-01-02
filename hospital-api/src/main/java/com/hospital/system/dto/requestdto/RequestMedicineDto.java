package com.hospital.system.dto.requestdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class RequestMedicineDto {
    private String name;
    private String type;
    private int qty;
    private double unitPrice;
}
