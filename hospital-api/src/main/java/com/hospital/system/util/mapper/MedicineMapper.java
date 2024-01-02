package com.hospital.system.util.mapper;

import com.hospital.system.dto.MedicineDto;
import com.hospital.system.enity.Medicine;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicineMapper {
    Medicine toMedicine(MedicineDto medicineDto);
}
