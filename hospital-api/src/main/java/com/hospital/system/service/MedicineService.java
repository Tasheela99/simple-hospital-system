package com.hospital.system.service;

import com.hospital.system.dto.requestdto.RequestMedicineDto;
import com.hospital.system.dto.responsedto.ResponseMedicineDto;

import java.util.List;

public interface MedicineService {
    boolean createMedicine(String inventoryId, RequestMedicineDto requestMedicineDto);

    boolean updateMedicine(String id, RequestMedicineDto medicineDto);

    boolean deleteMedicine(String id);

    List<ResponseMedicineDto> getAllMedicines();

    long count();
}
