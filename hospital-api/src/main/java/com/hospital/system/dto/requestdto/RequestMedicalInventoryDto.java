package com.hospital.system.dto.requestdto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class RequestMedicalInventoryDto {
    private String name;
    private String qty;
    private String unitPrice;
    private Date manufacturedDate;
    private Date expiredDate;
}
