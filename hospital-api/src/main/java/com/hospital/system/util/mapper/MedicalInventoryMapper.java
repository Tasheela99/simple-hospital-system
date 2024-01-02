package com.hospital.system.util.mapper;

import com.hospital.system.dto.MedicineInventoryDto;
import com.hospital.system.enity.MedicineInventory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicalInventoryMapper {
    MedicineInventory toMedicineInventory(MedicineInventoryDto medicineInventoryDto);

    MedicineInventoryDto toMedicineInventoryDto(MedicineInventory medicineInventory);
}
