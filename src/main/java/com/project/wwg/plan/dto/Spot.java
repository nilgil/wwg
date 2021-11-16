package com.project.wwg.plan.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Spot {
    @NonNull
    private String title;
    private String info;
    private Double lat;
    private Double lng;
    private String address;
    private String photo;
    private String phone;
}
