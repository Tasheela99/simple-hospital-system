package com.hospital.system.controller;

import com.hospital.system.dto.PatientDto;
import com.hospital.system.dto.requestdto.RequestPatientDto;
import com.hospital.system.service.PatientService;
import com.hospital.system.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/patient")
@CrossOrigin
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<StandardResponse> createPatient(
            @RequestBody RequestPatientDto patientDto
            ){
        boolean isCreated = patientService.createPatient(patientDto);
        return new ResponseEntity<>(
                new StandardResponse(
                        201,
                        "Created",
                        isCreated
                ), HttpStatus.CREATED
        );
    }
    @PutMapping(path = "/update",params = {"id"})
    public ResponseEntity<StandardResponse> updatePatient(
            @RequestParam(value = "id") String id,
            @RequestBody RequestPatientDto patientDto

    ){
        boolean isUpdated = patientService.updatePatient(id,patientDto);
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "Updated",
                        isUpdated
                ), HttpStatus.OK
        );
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<StandardResponse> deletePatient(
            @RequestParam(value = "id") String id
    ){
        boolean isDeleted = patientService.deletePatient(id);
        return new ResponseEntity<>(
                new StandardResponse(
                        204,
                        "Deleted",
                        isDeleted
                ), HttpStatus.NO_CONTENT
        );
    }

    @GetMapping(path = "/get-all")
    public ResponseEntity<StandardResponse> getAllPatients(){
        List<PatientDto> patients = patientService.getAllPatients();
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "All Patients",
                        patients
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/count")
    public ResponseEntity<StandardResponse> count() {
        long count = patientService.count();
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "Total Count",
                        count
                ),
                HttpStatus.OK
        );
    }

}
