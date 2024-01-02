package com.hospital.system.enity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Doctor {
    @Id
    private String id;
    private String name;
    private String address;
    private String mobile;
    private String speciality;
}
