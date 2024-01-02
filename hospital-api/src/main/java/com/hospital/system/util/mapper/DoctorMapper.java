package com.hospital.system.util.mapper;

import com.hospital.system.dto.DoctorDto;
import com.hospital.system.enity.Doctor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    Doctor toDoctor(DoctorDto doctorDto);
}
