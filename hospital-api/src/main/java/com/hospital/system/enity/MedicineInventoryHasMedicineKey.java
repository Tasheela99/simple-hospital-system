package com.hospital.system.enity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
public class MedicineInventoryHasMedicineKey implements Serializable {
    @Column(name = "medicine_id")
    String medicineId;

    @Column(name = "inventory_id")
    String inventoryId;
}
