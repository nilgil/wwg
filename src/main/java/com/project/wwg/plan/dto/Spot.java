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
    private Long score;
    private int reviewer;
    private double rating;

    public Spot(@NonNull String title, String info, double lat, double lng, String address, String photo, String phone) {
        this.title = title;
        this.info = info;
        this.lat = lat;
        this.lng = lng;
        this.address = address;
        this.photo = photo;
        this.phone = phone;
    }
}
