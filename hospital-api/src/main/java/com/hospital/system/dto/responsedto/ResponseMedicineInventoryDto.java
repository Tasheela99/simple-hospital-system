package com.hospital.system.dto.responsedto;

import com.hospital.system.dto.MedicineInventoryHolderDto;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMedicineInventoryDto {
    private String id;
    private String name;
    private String qty;
    private String unitPrice;
    private String manufacturedDate;
    private String expiredDate;

    private MedicineInventoryHolderDto medicineInventoryHolderDto;
}
