package com.hospital.system.enity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicineInventory {

    @Id
    @Column(name = "inventory_id")
    private String id;
    private String name;
    private String qty;
    private String unitPrice;
    private Date manufacturedDate;
    private Date expiredDate;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "medicine_inventory_holder_id", referencedColumnName = "holder_id")
    private MedicineInventoryHolder medicineInventoryHolder;

    @OneToMany(mappedBy = "medicineInventory",cascade = CascadeType.ALL)
    Set<MedicineInventoryHasMedicine> medicines;

}
