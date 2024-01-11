package com.hospital.system.service;

import com.hospital.system.dto.PatientDto;
import com.hospital.system.dto.requestdto.RequestPatientDto;

import java.util.List;

public interface PatientService {
    boolean createPatient(RequestPatientDto patientDto);

    boolean updatePatient(String id, RequestPatientDto patientDto);

    boolean deletePatient(String id);

    List<PatientDto> getAllPatients();

    long count();

    PatientDto getPatientById(String patientId);
}
