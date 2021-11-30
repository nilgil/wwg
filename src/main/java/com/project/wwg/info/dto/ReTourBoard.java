package com.project.wwg.info.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ReTourBoard {
private int tour_re_no;
private int rtour_no;
private String username;
private String tour_re_content;
private Date tour_re_regdate;
}
