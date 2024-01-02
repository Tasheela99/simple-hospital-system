package com.hospital.system.util.mapper;

import com.hospital.system.dto.MedicineInventoryHolderDto;
import com.hospital.system.enity.MedicineInventoryHolder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicalInventoryHolderMapper {
    MedicineInventoryHolder toMedicineInventoryHolder(MedicineInventoryHolderDto medicineInventoryHolderDto);

    MedicineInventoryHolderDto toMedicineInventoryHolderDto(MedicineInventoryHolder medicineInventoryHolder);
}
