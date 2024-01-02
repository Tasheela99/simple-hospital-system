package com.hospital.system.repo;

import com.hospital.system.enity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface MedicineRepo extends JpaRepository<Medicine,String> {
    @Query(value = "SELECT medicine_id FROM medicine WHERE medicine_id like ?% ORDER BY CAST(SUBSTRING(medicine_id,?) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findLastId(String s, int i);
}
