package com.hospital.system.enity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class Patient {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "age")
    private int age;
    @Column(name = "mobile")
    private String mobile;
}
