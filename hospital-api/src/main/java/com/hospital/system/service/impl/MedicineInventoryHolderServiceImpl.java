package com.hospital.system.service.impl;

import com.hospital.system.dto.DoctorDto;
import com.hospital.system.dto.MedicineInventoryHolderDto;
import com.hospital.system.dto.PatientDto;
import com.hospital.system.dto.requestdto.RequestMedicineInventoryHolderDto;
import com.hospital.system.dto.responsedto.ResponseMedicineInventoryHolderDto;
import com.hospital.system.enity.MedicineInventory;
import com.hospital.system.enity.MedicineInventoryHolder;
import com.hospital.system.enity.Patient;
import com.hospital.system.repo.MedicalInventoryHolderRepo;
import com.hospital.system.service.MedicineInventoryHolderService;
import com.hospital.system.util.mapper.MedicalInventoryHolderMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MedicineInventoryHolderServiceImpl implements MedicineInventoryHolderService {
    private final MedicalInventoryHolderRepo medicalInventoryHolderRepo;
    private final MedicalInventoryHolderMapper medicalInventoryHolderMapper;

    public MedicineInventoryHolderServiceImpl(MedicalInventoryHolderRepo medicalInventoryHolderRepo, MedicalInventoryHolderMapper medicalInventoryHolderMapper) {
        this.medicalInventoryHolderRepo = medicalInventoryHolderRepo;
        this.medicalInventoryHolderMapper = medicalInventoryHolderMapper;
    }

    @Override
    public boolean createMedicineInventoryHolder(RequestMedicineInventoryHolderDto requestMedicineInventoryHolderDto) {
        String userLastId = medicalInventoryHolderRepo.findLastId("HMS-MIH-",
                9);

        String id = "HMS-MIH-1";

        if (null != userLastId) {
            int i = (Integer.parseInt(userLastId.split("HMS-MIH-")[1])) + 1;
            id = "HMS-MIH-" + i;
        }

        MedicineInventoryHolderDto medicineInventoryHolderDto = new MedicineInventoryHolderDto(
                id,
                requestMedicineInventoryHolderDto.getName()
        );

        if (!medicalInventoryHolderRepo.existsById(medicineInventoryHolderDto.getId())){
            medicalInventoryHolderRepo.save(medicalInventoryHolderMapper.toMedicineInventoryHolder(medicineInventoryHolderDto));
        }else {
            throw new RuntimeException("This Medicine Inventory Holder Already Exists");
        }

        return true;
    }

    @Override
    public boolean deleteMedicineInventoryHolder(String id) {
        if (medicalInventoryHolderRepo.existsById(id)){
            medicalInventoryHolderRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<ResponseMedicineInventoryHolderDto> getAllHolders() {
        List<MedicineInventoryHolder> medicineInventoryHolders = medicalInventoryHolderRepo.findAll();

        if (!medicineInventoryHolders.isEmpty()) {
            List<ResponseMedicineInventoryHolderDto> responseMedicineInventoryHolderDtoList = new ArrayList<>();

            for (MedicineInventoryHolder holder : medicineInventoryHolders) {

                ResponseMedicineInventoryHolderDto responseMedicineInventoryHolderDto = new ResponseMedicineInventoryHolderDto(
                        holder.getId(),
                        holder.getName()
                );

                responseMedicineInventoryHolderDtoList.add(responseMedicineInventoryHolderDto);
            }

            return responseMedicineInventoryHolderDtoList;
        } else {
            throw new RuntimeException("No Holders Found");
        }
    }

    @Override
    public long count() {
        return medicalInventoryHolderRepo.count();
    }

    @Override
    public List<String> getAllMedicalInventoryHolderIds() {
        List<MedicineInventoryHolder> medicineInventoryHolderList = medicalInventoryHolderRepo.findAll();
        List<String> medicineInventoryHolderIds = medicineInventoryHolderList.stream()
                .map(MedicineInventoryHolder::getId)
                .collect(Collectors.toList());
        return medicineInventoryHolderIds;
    }

}
