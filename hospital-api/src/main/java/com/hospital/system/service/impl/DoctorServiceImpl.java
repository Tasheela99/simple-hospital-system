package com.hospital.system.service.impl;

import com.hospital.system.dto.DoctorDto;
import com.hospital.system.dto.DoctorDto;
import com.hospital.system.dto.PatientDto;
import com.hospital.system.dto.requestdto.RequestDoctorDto;
import com.hospital.system.enity.Doctor;
import com.hospital.system.enity.Patient;
import com.hospital.system.repo.DoctorRepo;
import com.hospital.system.repo.PatientRepo;
import com.hospital.system.service.DoctorService;
import com.hospital.system.util.mapper.DoctorMapper;
import com.hospital.system.util.mapper.PatientMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepo doctorRepo;
    private final DoctorMapper doctorMapper;

    public DoctorServiceImpl(DoctorRepo doctorRepo, DoctorMapper doctorMapper) {
        this.doctorRepo = doctorRepo;
        this.doctorMapper = doctorMapper;
    }

    @Override
    public boolean createDoctor(RequestDoctorDto patientDto) {
        String userLastId = doctorRepo.findLastId("HMS-DOC-",
                9);

        String id = "HMS-DOC-1";

        if (null != userLastId) {
            int i = (Integer.parseInt(userLastId.split("HMS-DOC-")[1])) + 1;
            id = "HMS-DOC-" + i;
        }

        DoctorDto doctorDto = new DoctorDto(
                id,
                patientDto.getName(),
                patientDto.getAddress(),
                patientDto.getMobile(),
                patientDto.getSpeciality()
        );

        if (!doctorRepo.existsById(doctorDto.getId())){
            doctorRepo.save(doctorMapper.toDoctor(doctorDto));
        }else {
            throw new RuntimeException("Doctor Already Exists");
        }

        return true;
    }

    @Override
    public boolean updateDoctor(String id, RequestDoctorDto patientDto) {
        Optional<Doctor> doctor = doctorRepo.findById(id);
        if (doctor.isEmpty()){
            throw new RuntimeException("No Doctor Found");
        }else {
            Doctor doc = doctor.get();
            doc.setName(patientDto.getName());
            doc.setAddress(patientDto.getAddress());
            doc.setMobile(patientDto.getMobile());
            doc.setSpeciality(patientDto.getSpeciality());
            doctorRepo.save(doc);
            return true;
        }
    }

    @Override
    public boolean deleteDoctor(String id) {
        if (doctorRepo.existsById(id)){
            doctorRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<DoctorDto> getAllDoctors() {
        List<Doctor> doctors = doctorRepo.findAll();

        if (!doctors.isEmpty()) {
            List<DoctorDto> doctorDtoList = new ArrayList<>();

            for (Doctor doctor : doctors) {

                DoctorDto doctorDto = new DoctorDto(
                        doctor.getId(),
                        doctor.getName(),
                        doctor.getAddress(),
                        doctor.getMobile(),
                        doctor.getSpeciality()
                );

                doctorDtoList.add(doctorDto);
            }

            return doctorDtoList;
        } else {
            throw new RuntimeException("No Doctors Found");
        }
    }

    @Override
    public long countDoctors() {
        long count = doctorRepo.countALl();
        return count;
    }
}
