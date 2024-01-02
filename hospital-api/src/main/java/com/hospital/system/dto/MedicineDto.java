package com.hospital.system.dto;

import com.hospital.system.enity.MedicineInventoryHasMedicine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class MedicineDto {
    private String id;
    private String name;
    private String type;
    private int qty;
    private double unitPrice;

}
