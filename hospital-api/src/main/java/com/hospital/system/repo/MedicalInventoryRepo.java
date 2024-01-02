package com.hospital.system.repo;

import com.hospital.system.enity.MedicineInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface MedicalInventoryRepo extends JpaRepository<MedicineInventory,String> {
    @Query(value = "SELECT inventory_id FROM medicine_inventory WHERE inventory_id like ?% ORDER BY CAST(SUBSTRING(inventory_id,?) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findLastId(String s, int i);
}
