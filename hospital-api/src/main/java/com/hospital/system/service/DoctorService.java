package com.hospital.system.service;

import com.hospital.system.dto.DoctorDto;
import com.hospital.system.dto.requestdto.RequestDoctorDto;

import java.util.List;

public interface DoctorService {
    boolean createDoctor(RequestDoctorDto patientDto);

    boolean updateDoctor(String id, RequestDoctorDto patientDto);

    boolean deleteDoctor(String id);

    List<DoctorDto> getAllDoctors();

    long countDoctors();
}
