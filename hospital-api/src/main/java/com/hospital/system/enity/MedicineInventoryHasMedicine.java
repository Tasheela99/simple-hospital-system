package com.hospital.system.enity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class MedicineInventoryHasMedicine {
    @EmbeddedId
    MedicineInventoryHasMedicineKey id;

    @ManyToOne
    @MapsId("medicineId")
    @JoinColumn(name = "medicine_id")
    Medicine medicine;

    @ManyToOne
    @MapsId("inventoryId")
    @JoinColumn(name = "inventory_id")
    MedicineInventory medicineInventory;
}
