package com.hospital.system.util.mapper;

import com.hospital.system.dto.PatientDto;
import com.hospital.system.enity.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    Patient toPatient(PatientDto patientDto1);
    PatientDto toPatientDto(Patient patient);
}
