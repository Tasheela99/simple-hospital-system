package com.hospital.system.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.checkerframework.checker.units.qual.A;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ResponseMedicineInventoryHolderDto {
    private String id;
    private String name;
}
