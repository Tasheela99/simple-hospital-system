package com.hospital.system.enity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class MedicineInventoryHolder {
    @Id
    @Column(name = "holder_id")
    private String id;
    private String name;

    @OneToMany(mappedBy="medicineInventoryHolder", cascade = CascadeType.ALL)
    private Set<MedicineInventory> medicineInventory;

    public MedicineInventoryHolder(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
