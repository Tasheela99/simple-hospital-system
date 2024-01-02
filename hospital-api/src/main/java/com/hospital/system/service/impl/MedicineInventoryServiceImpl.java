package com.hospital.system.service.impl;

import com.hospital.system.dto.DoctorDto;
import com.hospital.system.dto.MedicineInventoryDto;
import com.hospital.system.dto.requestdto.RequestMedicalInventoryDto;
import com.hospital.system.dto.responsedto.ResponseMedicineInventoryDto;
import com.hospital.system.enity.Doctor;
import com.hospital.system.enity.MedicineInventory;
import com.hospital.system.enity.MedicineInventoryHolder;
import com.hospital.system.repo.MedicalInventoryHolderRepo;
import com.hospital.system.repo.MedicalInventoryRepo;
import com.hospital.system.service.MedicineInventoryService;
import com.hospital.system.util.mapper.MedicalInventoryHolderMapper;
import com.hospital.system.util.mapper.MedicalInventoryMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MedicineInventoryServiceImpl implements MedicineInventoryService {
    private final MedicalInventoryRepo medicalInventoryRepo;
    private final MedicalInventoryMapper medicalInventoryMapper;
    private final MedicalInventoryHolderRepo medicalInventoryHolderRepo;
    private final MedicalInventoryHolderMapper medicalInventoryHolderMapper;

    public MedicineInventoryServiceImpl(MedicalInventoryRepo medicalInventoryRepo, MedicalInventoryMapper medicalInventoryMapper, MedicalInventoryHolderRepo medicalInventoryHolderRepo, MedicalInventoryHolderMapper medicalInventoryHolderMapper) {
        this.medicalInventoryRepo = medicalInventoryRepo;
        this.medicalInventoryMapper = medicalInventoryMapper;
        this.medicalInventoryHolderRepo = medicalInventoryHolderRepo;
        this.medicalInventoryHolderMapper = medicalInventoryHolderMapper;
    }

    @Override
    public boolean createMedicalInventory(String holderId, RequestMedicalInventoryDto requestMedicalInventoryDto) {
        Optional<MedicineInventoryHolder> medicineInventoryHolder = medicalInventoryHolderRepo.findById(holderId);
        if (medicineInventoryHolder.isEmpty()) {
            throw new RuntimeException("No Holder Found");
        }
        String lastId = medicalInventoryRepo.findLastId("HMS-MI-",
                8);

        String id = "HMS-MI-1";

        if (null != lastId) {
            int i = (Integer.parseInt(lastId.split("HMS-MI-")[1])) + 1;
            id = "HMS-MI-" + i;
        }

        MedicineInventoryDto medicineInventoryDto = new MedicineInventoryDto(
                id,
                requestMedicalInventoryDto.getName(),
                requestMedicalInventoryDto.getQty(),
                requestMedicalInventoryDto.getUnitPrice(),
                requestMedicalInventoryDto.getManufacturedDate(),
                requestMedicalInventoryDto.getExpiredDate(),
                medicalInventoryHolderMapper.toMedicineInventoryHolderDto(medicineInventoryHolder.get())
        );

        if (!medicalInventoryRepo.existsById(medicineInventoryDto.getId())){
            medicalInventoryRepo.save(medicalInventoryMapper.toMedicineInventory(medicineInventoryDto));
        }else {
            throw new RuntimeException("This Medicine Already Exists");
        }
        return true;
    }

    @Override
    public boolean updateMedicalInventory(String id, RequestMedicalInventoryDto medicineInventoryDto) {
        Optional<MedicineInventory> medicineInventory = medicalInventoryRepo.findById(id);
        if (medicineInventory.isEmpty()){
            throw new RuntimeException("No Medicine Found");
        }else {
            MedicineInventory medicineInventory1 = medicineInventory.get();
            medicineInventory1.setName(medicineInventoryDto.getName());
            medicineInventory1.setQty(medicineInventoryDto.getQty());
            medicineInventory1.setUnitPrice(medicineInventoryDto.getUnitPrice());
            medicineInventory1.setManufacturedDate(medicineInventoryDto.getManufacturedDate());
            medicineInventory1.setExpiredDate(medicineInventoryDto.getExpiredDate());
            medicalInventoryRepo.save(medicineInventory1);
            return true;
        }
    }

    @Override
    public boolean deleteMedicalInventory(String id) {
        if (medicalInventoryRepo.existsById(id)){
            medicalInventoryRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<ResponseMedicineInventoryDto> getAllMedicalInventory() {
        List<MedicineInventory> medicineInventories = medicalInventoryRepo.findAll();

        if (!medicineInventories.isEmpty()) {
            List<ResponseMedicineInventoryDto> responseMedicineInventoryDtoList = new ArrayList<>();

            for (MedicineInventory medicineInventory : medicineInventories) {

                ResponseMedicineInventoryDto responseMedicineInventoryDto = new ResponseMedicineInventoryDto(
                        medicineInventory.getId(),
                        medicineInventory.getName(),
                        medicineInventory.getQty(),
                        medicineInventory.getUnitPrice(),
                        new String(String.valueOf(medicineInventory.getManufacturedDate())),
                        new String(String.valueOf(medicineInventory.getExpiredDate())),
                        medicalInventoryHolderMapper.toMedicineInventoryHolderDto(medicineInventory.getMedicineInventoryHolder())
                );

                responseMedicineInventoryDtoList.add(responseMedicineInventoryDto);
            }

            return responseMedicineInventoryDtoList;
        } else {
            throw new RuntimeException("No Medicines Found");
        }
    }
}
