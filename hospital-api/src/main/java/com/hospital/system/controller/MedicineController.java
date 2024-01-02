package com.hospital.system.controller;

import com.hospital.system.dto.DoctorDto;
import com.hospital.system.dto.MedicineDto;
import com.hospital.system.dto.requestdto.RequestDoctorDto;
import com.hospital.system.dto.requestdto.RequestMedicineDto;
import com.hospital.system.dto.responsedto.ResponseMedicineDto;
import com.hospital.system.service.MedicineService;
import com.hospital.system.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medicine")
@CrossOrigin
public class MedicineController {
    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @PostMapping(path = "/create",params = {"inventoryId"})
    public ResponseEntity<StandardResponse> createMedicine(
            @RequestParam(value = "inventoryId") String inventoryId,
            @RequestBody RequestMedicineDto requestMedicineDto
    ){
        boolean isCreated = medicineService.createMedicine(inventoryId,requestMedicineDto);
        return new ResponseEntity<>(
                new StandardResponse(
                        201,
                        "Created",
                        isCreated
                ), HttpStatus.CREATED
        );
    }

    @PutMapping(path = "/update",params = {"id"})
    public ResponseEntity<StandardResponse> updateMedicine(
            @RequestParam(value = "id") String id,
            @RequestBody RequestMedicineDto medicineDto

    ){
        boolean isUpdated = medicineService.updateMedicine(id,medicineDto);
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "Updated",
                        isUpdated
                ), HttpStatus.OK
        );
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<StandardResponse> deleteMedicine(
            @RequestParam(value = "id") String id
    ){
        boolean isDeleted = medicineService.deleteMedicine(id);
        return new ResponseEntity<>(
                new StandardResponse(
                        204,
                        "Deleted",
                        isDeleted
                ), HttpStatus.NO_CONTENT
        );
    }

    @GetMapping(path = "/get-all")
    public ResponseEntity<StandardResponse> getAllMedicines(){
        List<ResponseMedicineDto> medicines = medicineService.getAllMedicines();
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "All Medicines",
                        medicines
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/count")
    public ResponseEntity<StandardResponse> count() {
        long count = medicineService.count();
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
