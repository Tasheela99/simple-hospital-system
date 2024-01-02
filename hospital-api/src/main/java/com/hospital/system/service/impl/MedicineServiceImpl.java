package com.hospital.system.service.impl;

import com.hospital.system.dto.DoctorDto;
import com.hospital.system.dto.MedicineDto;
import com.hospital.system.dto.MedicineInventoryDto;
import com.hospital.system.dto.requestdto.RequestMedicineDto;
import com.hospital.system.dto.responsedto.ResponseMedicineDto;
import com.hospital.system.enity.Doctor;
import com.hospital.system.enity.Medicine;
import com.hospital.system.enity.MedicineInventory;
import com.hospital.system.enity.MedicineInventoryHolder;
import com.hospital.system.repo.MedicalInventoryRepo;
import com.hospital.system.repo.MedicineRepo;
import com.hospital.system.service.MedicineService;
import com.hospital.system.util.mapper.MedicalInventoryMapper;
import com.hospital.system.util.mapper.MedicineMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MedicineServiceImpl implements MedicineService {
    private final MedicineRepo medicineRepo;
    private final MedicineMapper medicineMapper;
    private final MedicalInventoryMapper medicalInventoryMapper;
    private final MedicalInventoryRepo medicalInventoryRepo;

    public MedicineServiceImpl(MedicineRepo medicineRepo, MedicineMapper medicineMapper, MedicalInventoryMapper medicalInventoryMapper, MedicalInventoryRepo medicalInventoryRepo) {
        this.medicineRepo = medicineRepo;
        this.medicineMapper = medicineMapper;
        this.medicalInventoryMapper = medicalInventoryMapper;
        this.medicalInventoryRepo = medicalInventoryRepo;
    }

    @Override
    public boolean createMedicine(String inventoryId, RequestMedicineDto requestMedicineDto) {
        Optional<MedicineInventory> medicineInventory = medicalInventoryRepo.findById(inventoryId);
        if (medicineInventory.isEmpty()) {
            throw new RuntimeException("No Inventory Found");
        }
        String lastId = medicineRepo.findLastId("HMS-M-",
                7);

        String id = "HMS-M-1";

        if (null != lastId) {
            int i = (Integer.parseInt(lastId.split("HMS-M-")[1])) + 1;
            id = "HMS-M-" + i;
        }
        MedicineDto medicineDto = new MedicineDto(
                id,
                requestMedicineDto.getName(),
                requestMedicineDto.getType(),
                requestMedicineDto.getQty(),
                requestMedicineDto.getUnitPrice()
        );

        if (!medicineRepo.existsById(medicineDto.getId())){
            medicineRepo.save(medicineMapper.toMedicine(medicineDto));
        }else {
            throw new RuntimeException("This Medicine Already Exists");
        }
        return true;
    }

    @Override
    public boolean updateMedicine(String id, RequestMedicineDto medicineDto) {
        Optional<Medicine> medicine = medicineRepo.findById(id);
        if (medicine.isEmpty()){
            throw new RuntimeException("No Medicine Found");
        }else {
            Medicine medicine1 = medicine.get();
            medicine1.setName(medicineDto.getName());
            medicine1.setType(medicineDto.getType());
            medicine1.setQty(medicineDto.getQty());
            medicine1.setUnitPrice(medicineDto.getUnitPrice());

            medicineRepo.save(medicine1);
            return true;
        }
    }

    @Override
    public boolean deleteMedicine(String id) {
        if (medicineRepo.existsById(id)){
            medicineRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<ResponseMedicineDto> getAllMedicines() {
        List<Medicine> medicines = medicineRepo.findAll();

        if (!medicines.isEmpty()) {
            List<ResponseMedicineDto> medicineDtoList = new ArrayList<>();

            for (Medicine medicine : medicines) {

                ResponseMedicineDto doctorDto = new ResponseMedicineDto(
                        medicine.getId(),
                        medicine.getName(),
                        medicine.getType(),
                        medicine.getQty(),
                        medicine.getUnitPrice()
                );

                medicineDtoList.add(doctorDto);
            }

            return medicineDtoList;
        } else {
            throw new RuntimeException("No Medicines Found");
        }
    }

    @Override
    public long count() {
        return medicineRepo.count();
    }
}
