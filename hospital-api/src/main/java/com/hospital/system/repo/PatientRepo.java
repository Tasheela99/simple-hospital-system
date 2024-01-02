package com.hospital.system.repo;

import com.hospital.system.enity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface PatientRepo extends JpaRepository<Patient,String> {
    @Query(value = "SELECT id FROM patient WHERE id like ?% ORDER BY CAST(SUBSTRING(id,?) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findLastId(String s, int i);
}
