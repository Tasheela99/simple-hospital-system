package com.hospital.system.controller;

import com.hospital.system.dto.DoctorDto;
import com.hospital.system.dto.PatientDto;
import com.hospital.system.dto.requestdto.RequestDoctorDto;
import com.hospital.system.service.DoctorService;
import com.hospital.system.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctor")
@CrossOrigin
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<StandardResponse> createDoctor(
            @RequestBody RequestDoctorDto patientDto
    ){
        boolean isCreated = doctorService.createDoctor(patientDto);
        return new ResponseEntity<>(
                new StandardResponse(
                        201,
                        "Created",
                        isCreated
                ), HttpStatus.CREATED
        );
    }
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<StandardResponse> updateDoctor(
            @PathVariable(value = "id") String id,
            @RequestBody RequestDoctorDto patientDto

    ){
        boolean isUpdated = doctorService.updateDoctor(id,patientDto);
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "Updated",
                        isUpdated
                ), HttpStatus.OK
        );
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<StandardResponse> deleteDoctor(
            @RequestParam(value = "id") String id
    ){
        boolean isDeleted = doctorService.deleteDoctor(id);
        return new ResponseEntity<>(
                new StandardResponse(
                        204,
                        "Deleted",
                        isDeleted
                ), HttpStatus.NO_CONTENT
        );
    }

    @GetMapping(path = "/get-all")
    public ResponseEntity<StandardResponse> getAllDoctors(){
        List<DoctorDto> patients = doctorService.getAllDoctors();
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "All Doctors",
                        patients
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/count")
    public ResponseEntity<StandardResponse> countDoctors() {
        long doctorCount = doctorService.countDoctors();
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "Total Doctor Count",
                        doctorCount
                ),
                HttpStatus.OK
        );
    }
}
