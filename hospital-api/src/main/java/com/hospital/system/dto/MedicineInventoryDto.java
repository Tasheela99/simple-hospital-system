package com.hospital.system.dto;

import com.hospital.system.enity.MedicineInventoryHasMedicine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class MedicineInventoryDto {
    private String id;
    private String name;
    private String qty;
    private String unitPrice;
    private Date manufacturedDate;
    private Date expiredDate;

    private MedicineInventoryHolderDto medicineInventoryHolder;


}
