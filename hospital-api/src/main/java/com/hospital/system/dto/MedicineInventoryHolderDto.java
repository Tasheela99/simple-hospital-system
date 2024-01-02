package com.hospital.system.dto;

import com.hospital.system.enity.MedicineInventory;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class MedicineInventoryHolderDto {
    private String id;
    private String name;

}
