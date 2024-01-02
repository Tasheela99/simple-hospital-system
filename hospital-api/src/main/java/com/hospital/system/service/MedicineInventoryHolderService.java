package com.hospital.system.service;

import com.hospital.system.dto.requestdto.RequestMedicineInventoryHolderDto;
import com.hospital.system.dto.responsedto.ResponseMedicineInventoryHolderDto;

import java.util.List;

public interface MedicineInventoryHolderService {
    boolean createMedicineInventoryHolder(RequestMedicineInventoryHolderDto requestMedicineInventoryHolderDto);

    boolean deleteMedicineInventoryHolder(String id);

    List<ResponseMedicineInventoryHolderDto> getAllHolders();

    long count();
}
