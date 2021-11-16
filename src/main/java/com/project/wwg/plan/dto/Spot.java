package com.project.wwg.plan.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Spot {
    @NonNull
    private String title;
    private String info;
    private BigDecimal lat;
    private BigDecimal lng;
    private String address;
    private String photo;
    private String phone;
}
