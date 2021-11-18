package com.project.wwg.plan.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plan {
    private int idx;
    private String user;
    private String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone = "Asia/Seoul")
    private Date departure;
    private int days;
    private String plans;
    private int totalExpense;
    private int hit;
    private int like;
    private Date regDate;

//
//    public void setDeparture(Date departure) {
//        this.departure = departure;
//        setDepartureWeek();
//    }
//
//    public void setDepartureWeek() {
//        this.departureWeek = this.departure.toLocalDate().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
//    }
}
