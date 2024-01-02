package com.hospital.system.enity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Medicine {
    @Id
    @Column(name = "medicine_id")
    private String id;

    private String name;
    private String type;
    private int qty;
    private double unitPrice;

    @OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL)
    Set<MedicineInventoryHasMedicine> medicines;

}
