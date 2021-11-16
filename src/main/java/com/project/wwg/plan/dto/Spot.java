package com.project.wwg.plan.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Spot {
    @NonNull
    private String title;
    private String info;
    private double lat;
    private double lng;
    private String address;
    private String photo;
    private String phone;
}
