package com.project.wwg.plan.dto;

import java.math.BigDecimal;

public class Item {
    private String title;
    private String info;
    private BigDecimal lat;
    private BigDecimal lng;
    private String address;
    private String photo;
    private String phone;

    public Item() {
    }

    public Item(String title, String info, BigDecimal lat, BigDecimal lng, String address, String photo, String phone) {
        this.title = title;
        this.info = info;
        this.lat = lat;
        this.lng = lng;
        this.address = address;
        this.photo = photo;
        this.phone = phone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
