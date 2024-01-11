package com.hospital.system.service;

import com.hospital.system.dto.requestdto.RequestMedicalInventoryDto;
import com.hospital.system.dto.responsedto.ResponseMedicineInventoryDto;

import java.util.List;

public interface MedicineInventoryService {
    boolean createMedicalInventory(String holderId, RequestMedicalInventoryDto requestMedicalInventoryDto);

    boolean updateMedicalInventory(String id, RequestMedicalInventoryDto medicineInventoryDto);

    boolean deleteMedicalInventory(String id);

    List<ResponseMedicineInventoryDto> getAllMedicalInventory();

    List<String> getAllMedicalInventoryIds();
}
