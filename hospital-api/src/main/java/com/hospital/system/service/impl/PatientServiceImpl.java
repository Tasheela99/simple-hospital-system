package com.hospital.system.service.impl;

import com.hospital.system.dto.PatientDto;
import com.hospital.system.dto.requestdto.RequestPatientDto;
import com.hospital.system.enity.Patient;
import com.hospital.system.repo.PatientRepo;
import com.hospital.system.service.PatientService;
import com.hospital.system.util.mapper.PatientMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepo patientRepo;
    private final PatientMapper patientMapper;

    public PatientServiceImpl(PatientRepo patientRepo, PatientMapper patientMapper) {
        this.patientRepo = patientRepo;
        this.patientMapper = patientMapper;
    }

    @Override
    public boolean createPatient(RequestPatientDto patientDto) {
        String userLastId = patientRepo.findLastId("HMS-P-",
                7);

        String id = "HMS-P-1";

        if (null != userLastId) {
            int i = (Integer.parseInt(userLastId.split("HMS-P-")[1])) + 1;
            id = "HMS-P-" + i;
        }

        PatientDto patientDto1 = new PatientDto(
                id,
                patientDto.getName(),
                patientDto.getAddress(),
                patientDto.getAge(),
                patientDto.getMobile()
        );

        if (!patientRepo.existsById(patientDto1.getId())){
            patientRepo.save(patientMapper.toPatient(patientDto1));
        }else {
            throw new RuntimeException("Patient Already Exists");
        }

        return true;
    }

    @Override
    public boolean updatePatient(String id, RequestPatientDto patientDto) {
        Optional<Patient> patient = patientRepo.findById(id);
        if (patient.isEmpty()){
            throw new RuntimeException("No Patient Found");
        }else {
            Patient patient1 = patient.get();
            patient1.setName(patientDto.getName());
            patient1.setAddress(patientDto.getAddress());
            patient1.setAge(patientDto.getAge());
            patient1.setMobile(patientDto.getMobile());
            patientRepo.save(patient1);
            return true;
        }
    }

    @Override
    public boolean deletePatient(String id) {
        if (patientRepo.existsById(id)){
            patientRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<PatientDto> getAllPatients() {
        List<Patient> patients = patientRepo.findAll();

        if (!patients.isEmpty()) {
            List<PatientDto> patientDtoList = new ArrayList<>();

            for (Patient patient : patients) {

                PatientDto patientDto = new PatientDto(
                        patient.getId(),
                        patient.getName(),
                        patient.getAddress(),
                        patient.getAge(),
                        patient.getMobile()
                );

                patientDtoList.add(patientDto);
            }

            return patientDtoList;
        } else {
            throw new RuntimeException("No Patients Found");
        }
    }

    @Override
    public long count() {
        return patientRepo.count();
    }

    @Override
    public PatientDto getPatientById(String patientId) {
        Patient patient = patientRepo.getById(patientId);
        return patientMapper.toPatientDto(patient);
    }
}
