package com.hospital.system.controller;
import com.hospital.system.dto.requestdto.RequestMedicalInventoryDto;
import com.hospital.system.dto.responsedto.ResponseMedicineInventoryDto;
import com.hospital.system.service.MedicineInventoryService;
import com.hospital.system.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medicine-inventory")
public class MedicineInventoryController {

    private final MedicineInventoryService medicineInventoryService;

    public MedicineInventoryController(MedicineInventoryService medicineInventoryService) {
        this.medicineInventoryService = medicineInventoryService;
    }

    @PostMapping(path = "/create",params = {"holderId"})
    public ResponseEntity<StandardResponse> createMedicalInventory(
            @RequestParam (value = "holderId") String holderId,
            @RequestBody RequestMedicalInventoryDto requestMedicalInventoryDto
    ){
        boolean isCreated = medicineInventoryService.createMedicalInventory(holderId, requestMedicalInventoryDto);
        return new ResponseEntity<>(
                new StandardResponse(
                        201,
                        "Created",
                        isCreated
                ), HttpStatus.CREATED
        );
    }

    @PutMapping(path = "/update",params = {"id"})
    public ResponseEntity<StandardResponse> updateMedicalInventory(
            @RequestParam(value = "id") String id,
            @RequestBody RequestMedicalInventoryDto medicineInventoryDto

    ){
        boolean isUpdated = medicineInventoryService.updateMedicalInventory(id,medicineInventoryDto);
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "Updated",
                        isUpdated
                ), HttpStatus.OK
        );
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<StandardResponse> deleteMedicalInventory(
            @RequestParam(value = "id") String id
    ){
        boolean isDeleted = medicineInventoryService.deleteMedicalInventory(id);
        return new ResponseEntity<>(
                new StandardResponse(
                        204,
                        "Deleted",
                        isDeleted
                ), HttpStatus.NO_CONTENT
        );
    }

    @GetMapping(path = "/get-all")
    public ResponseEntity<StandardResponse> getAllMedicalInventory(){
        List<ResponseMedicineInventoryDto> medicineInventory = medicineInventoryService.getAllMedicalInventory();
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "All Medicines",
                        medicineInventory
                ),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/get-all-ids")
    public ResponseEntity<StandardResponse> getAllMedicalInventoryIds() {
        List<String> medicineInventoryIds = medicineInventoryService.getAllMedicalInventoryIds();
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "All Medicine IDs",
                        medicineInventoryIds
                ),
                HttpStatus.OK
        );
    }
}
