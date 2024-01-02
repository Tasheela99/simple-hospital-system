package com.hospital.system.repo;

import com.hospital.system.enity.MedicineInventoryHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface MedicalInventoryHolderRepo extends JpaRepository<MedicineInventoryHolder,String> {
    @Query(value = "SELECT holder_id FROM medicine_inventory_holder WHERE holder_id like ?% ORDER BY CAST(SUBSTRING(holder_id,?) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findLastId(String s, int i);
}
