package com.hospital.system.controller;

import com.hospital.system.dto.DoctorDto;
import com.hospital.system.dto.MedicineInventoryHolderDto;
import com.hospital.system.dto.requestdto.RequestMedicineInventoryHolderDto;
import com.hospital.system.dto.responsedto.ResponseMedicineInventoryHolderDto;
import com.hospital.system.service.MedicineInventoryHolderService;
import com.hospital.system.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medicine-inventory-holder")
@CrossOrigin
public class MedicineInventoryHolderController {

    private final MedicineInventoryHolderService medicineInventoryHolderService;

    public MedicineInventoryHolderController(MedicineInventoryHolderService medicineInventoryHolderService) {
        this.medicineInventoryHolderService = medicineInventoryHolderService;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<StandardResponse> createMedicineInventoryHolder(
            @RequestBody RequestMedicineInventoryHolderDto requestMedicineInventoryHolderDto
    ){
        boolean isCreated = medicineInventoryHolderService.createMedicineInventoryHolder(requestMedicineInventoryHolderDto);
        return new ResponseEntity<>(
                new StandardResponse(
                        201,
                        "Created",
                        isCreated
                ), HttpStatus.CREATED
        );
    }

    @DeleteMapping(path = "/delete",params = {"id"})
    public ResponseEntity<StandardResponse> createMedicineInventoryHolder(
            @RequestParam (value = "id") String id
    ){
        boolean isDeleted = medicineInventoryHolderService.deleteMedicineInventoryHolder(id);
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
        List<ResponseMedicineInventoryHolderDto> holderDtos = medicineInventoryHolderService.getAllHolders();
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "All Holders",
                        holderDtos
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/count")
    public ResponseEntity<StandardResponse> count() {
        long count = medicineInventoryHolderService.count();
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "Total Count",
                        count
                ),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/get-all-ids")
    public ResponseEntity<StandardResponse> getAllInventoryHolderIds() {
        List<String> holderIds = medicineInventoryHolderService.getAllMedicalInventoryHolderIds();
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "All Holder IDs",
                        holderIds
                ),
                HttpStatus.OK
        );
    }
}
